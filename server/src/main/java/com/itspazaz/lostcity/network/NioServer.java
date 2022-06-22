package com.itspazaz.lostcity.network;

import com.itspazaz.lostcity.Server;
import com.itspazaz.lostcity.network.world.decoders.*;
import com.itspazaz.lostcity.network.world.encoders.LoadAreaEncoder;
import com.itspazaz.lostcity.world.World;
import com.jagex.core.io.Buffer;
import com.jagex.core.isaac.IsaacRandom;
import com.jagex.core.stringutils.StringUtils;
import rs2.ClientProt;
import rs2.ServerProt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class NioServer implements Runnable {
    public static String HOSTNAME = "127.0.0.1";
    public static int PORT = 43594;
    public static long TIMEOUT = 10000;

    private ServerSocketChannel serverChannel;
    private static Selector selector;

    private static final Map<SocketChannel, Buffer> writeQueue = new HashMap<SocketChannel, Buffer>();

    public NioServer() {
        init();
    }

    public static void main(String[] args) {
        Thread server = new Thread(new NioServer());
        server.start();
    }

    private void init() {
        if (selector != null) return;
        if (serverChannel != null) return;

        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(HOSTNAME, PORT));
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Listening on " + HOSTNAME + ":" + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Decoder[] decoders = new Decoder[256];

    public void registerDecoders() {
        decoders[ClientProt.IF_BUTTON] = new InterfaceButtonDecoder();

        decoders[ClientProt.MOVE_GAMECLICK] = new MoveGameClickDecoder();
        decoders[ClientProt.MOVE_MINIMAPCLICK] = new MoveMinimapClickDecoder();
        decoders[ClientProt.MOVE_OPCLICK] = new MoveGameClickDecoder();

        decoders[ClientProt.MAP_REQUEST_AREAS] = new RequestMapDecoder();

        decoders[ClientProt.CLIENT_CHEAT] = new CheatDecoder();
        decoders[ClientProt.MESSAGE_PUBLIC] = new MessagePublicDecoder();

        // These packets intentionally ignored
        decoders[ClientProt.NO_TIMEOUT] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPPLAYER2] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPLOC4] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_IF_BUTTON5] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPHELD1] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPLOC5] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_UPDATE_LOCS] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPNPC3] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_DRAW] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPNPC5] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_UPDATE] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_UPDATE_PLAYERS] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_OPHELD4] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_SIDEBAR_INPUT] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_UPDATE2] = new NopDecoder();
        decoders[ClientProt.ANTICHEAT_IF_BUTTON4] = new NopDecoder();
        decoders[ClientProt.EVENT_CAMERA_POSITION] = new NopDecoder();
    }

    public static Encoder[] encoders = new Encoder[256];

    public void registerEncoders() {
        encoders[ServerProt.LOAD_AREA] = new LoadAreaEncoder();
    }

    @Override
    public void run() {
        try {
            registerDecoders();
            registerEncoders();

            while (!Thread.currentThread().isInterrupted()) {
                selector.select(TIMEOUT);

                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    try {
                        if (!key.isValid()) {
                            continue;
                        }

                        if (key.isAcceptable()) {
                            accept(key);
                        }

                        if (key.isWritable()) {
                            write(key);
                        }

                        if (key.isReadable()) {
                            read(key);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        if (key.isValid()) {
                            SocketChannel channel = (SocketChannel) key.channel();
                            World.players.get(channel).connected = false;
                            channel.close();
                            key.cancel();
                        }
                    }

                    try {
                        Thread.sleep(1);
                    } catch (Exception ignored) {}
                }

                try {
                    Thread.sleep(1);
                } catch (Exception ignored) {}
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        if (selector != null) {
            try {
                selector.close();
                serverChannel.socket().close();
                serverChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel channel = server.accept();
        channel.configureBlocking(false);

        Buffer buffer = Buffer.reserve(1);
        buffer.p8(ThreadLocalRandom.current().nextLong());
        channel.register(selector, SelectionKey.OP_WRITE);
        writeQueue.put(channel, buffer);
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        Buffer data = writeQueue.get(channel);
        writeQueue.remove(channel);

        ByteBuffer buffer = ByteBuffer.allocate(data.pos);
        buffer.clear();
        buffer.put(data.take());
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }

        key.interestOps(SelectionKey.OP_READ);
    }

    public static void write(SelectionKey key, Buffer data) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (!channel.isConnected()) {
            return;
        }

        ByteBuffer buffer = ByteBuffer.allocate(data.pos);
        buffer.clear();
        buffer.put(data.take());
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    public static void encode(SelectionKey key, Buffer data, int opcode, Message message) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (!channel.isConnected()) {
            return;
        }

        if (encoders[opcode] == null) {
            return;
        }

        data.pos = 0;
        if (!encoders[opcode].execute(data, message)) {
            return;
        }

        // encrypt opcode here
        data.data[0] = (byte) (data.data[0] + data.isaac.nextInt());

        ByteBuffer buffer = ByteBuffer.allocate(data.pos);
        buffer.clear();
        buffer.put(data.take());
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        key.interestOps(SelectionKey.OP_READ);
    }

    private void disconnect(SelectionKey key, SocketChannel channel) throws IOException {
        if (World.players.get(channel) != null) {
            World.players.get(channel).connected = false;
        }

        if (key.isValid()) {
            channel.close();
            key.cancel();
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1000);
        readBuffer.clear();

        int read;
        try {
            read = channel.read(readBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            key.cancel();
            channel.close();
            return;
        }

        if (read == -1) {
            disconnect(key, channel);
            return;
        }

        readBuffer.flip();

        Connection con = World.players.get(channel);
        if (con == null) {
            Buffer in = Buffer.reserve(1);
            Buffer out = Buffer.reserve(1);

            readBuffer.get(in.data, in.pos, read);

            int opcode = in.g1();
            if (opcode == LoginProt.CONNECTING || opcode == LoginProt.RECONNECTING) {
                int length = in.g1();
                if (length < read - 2) {
                    out.p1(LoginProt.ERR_UNKNOWN);
                    write(key, out);
                    return;
                }

                int revision = in.g1();
                if (revision != 225) {
                    out.p1(LoginProt.ERR_OUT_OF_DATE);
                    write(key, out);
                    return;
                }

                int info = in.g1();

                int[] checksums = new int[9];
                for (int i = 0; i < 9; ++i) {
                    checksums[i] = in.g4();
                }

                in.rsadec(Server.RSA_MODULUS, Server.RSA_EXPONENT);
                if (in.g1() != 10) {
                    out.p1(LoginProt.ERR_UNKNOWN);
                    write(key, out);
                    return;
                }

                con = new Connection();
                con.channel = channel;
                con.key = key;
                con.lowMemory = (info & 0x1) == 0x1;
                con.webclient = (info & 0x2) == 0x2;

                int[] seed = new int[4];
                for (int i = 0; i < 4; ++i) {
                    seed[i] = in.g4();
                }

                con.in.isaac = new IsaacRandom(seed);
                for (int i = 0; i < 4; ++i) {
                    seed[i] += 50;
                }
                con.out.isaac = new IsaacRandom(seed);

                con.uid = in.g4();
                con.username = in.gstr();
                con.player.entity.name = con.username;
                con.player.name37 = StringUtils.toBase37(con.username);
                String password = in.gstr();

                con.player.load();
                World.players.put(channel, con);

                if (opcode == LoginProt.CONNECTING) {
                    out.p1(LoginProt.SUCCESS_STAFF);
                } else {
                    out.p1(LoginProt.SUCCESS_RECONNECT);
                }
                write(key, out);

                con.player.login();
            } else {
                disconnect(key, channel);
            }
        } else {
            if (!con.connected) {
                disconnect(key, channel);
                return;
            }

            con.in.pos = 0;
            con.out.pos = 0;

            readBuffer.get(con.in.data, con.in.pos, read);

            while (con.in.pos < read) {
                int opcode = con.in.g1isaac();
                int length = ClientProt.PACKET_LENGTHS[opcode];
                if (length == -1) {
                    length = con.in.g1();
                }

                Buffer data;
                if (length > 0) {
                    data = con.in.slice(con.in.pos, length);
                    con.in.pos += length;
                } else {
                    data = new Buffer(new byte[0]);
                }

                if (decoders[opcode] != null) {
                    try {
                        decoders[opcode].execute(con, data);
                    } catch (Exception ex) {
                        System.err.println("Error while decoding incoming packet");
                        ex.printStackTrace();
                    }
                } else {
                    System.err.println("Unhandled opcode: " + opcode + " (len: " + length + ")");
                }
            }
        }
    }
}
