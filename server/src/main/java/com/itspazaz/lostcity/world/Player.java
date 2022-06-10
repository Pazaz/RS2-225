package com.itspazaz.lostcity.world;

import com.itspazaz.lostcity.Server;
import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.network.NioServer;
import com.itspazaz.lostcity.utils.Position;
import com.jagex.game.runetek3.config.IdkType;
import com.jagex.game.runetek3.entity.PlayerEntity;
import rs2.ServerProt;
import com.jagex.core.io.Buffer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Player {
    Connection con;

    public PlayerEntity entity = new PlayerEntity();
    private final Buffer appearance = Buffer.reserve(0);
    public long name37;

    public boolean placement = true;
    public boolean appearanceUpdated = true;
    public boolean loading = true;
    public boolean loaded = true;

    public int[] steps = new int[0];
    public int step = -1;
    public int walkDir = -1;
    public int runDir = -1;
    public int lastLoadedX = 0;
    public int lastLoadedZ = 0;

    public int lastSongX = 0;
    public int lastSongZ = 0;

    public Player(Connection con) {
        this.con = con;

        entity.x = 3222;
        entity.z = 3222;
        entity.body[4] = IdkType.findPart(2, 0);
        entity.body[6] = IdkType.findPart(3, 0);
        entity.body[7] = IdkType.findPart(5, 0);
        entity.body[8] = IdkType.findPart(4, 0);
        entity.body[9] = IdkType.findPart(6, 0);
        entity.body[10] = IdkType.findPart(1, 0);
    }

    public void load() {
        try {
            Buffer file = new Buffer(Files.readAllBytes(Paths.get(Server.dataDir.toString(), "players", con.username)));
            entity.x = file.g2();
            entity.z = file.g2();
            entity.plane = file.g1();
        } catch (Exception ignored) {
        }
    }

    public void save() {
        try {
            Buffer buffer = Buffer.reserve(1);
            buffer.p2(entity.x);
            buffer.p2(entity.z);
            buffer.p1(entity.plane);
            Files.write(Paths.get(Server.dataDir.toString(), "players", con.username), buffer.take());
        } catch (Exception ignored) {
        }
    }

    public void login() {
        setSidebar(0, 5855);
        setSidebar(1, 3917);
        setSidebar(2, 638);
        setSidebar(3, 3213);
        setSidebar(4, 1644);
        setSidebar(5, 5608);
        setSidebar(6, 1151);
        setSidebar(8, 5065);
        setSidebar(9, 5715);
        setSidebar(10, 2449);
        setSidebar(11, 904);
        setSidebar(12, 147);
        setSidebar(13, 962);

        sendGameMessage("Welcome to RuneScape.");
    }

    public void openInterface(int id) {
        con.out.pos = 0;

        con.out.p1isaac(ServerProt.IF_OPENTOP);
        con.out.p2(id);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void logout() {
        con.out.pos = 0;

        con.out.p1isaac(ServerProt.LOGOUT);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void parseCheat(String cheat) {
        if (cheat == null) {
            return;
        }

        String[] args = cheat.split("\\s+");
        String cmd = args[0];

        if (cmd.equalsIgnoreCase("tele")) {
            if (args.length < 3) {
                return;
            }

            int x = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            int plane = args.length >= 4 ? Integer.parseInt(args[3]) : 0;
            teleport(x, z, plane);
            sendGameMessage("Teleported to " + x + " " + z);
        } else if (cmd.equalsIgnoreCase("head")) {
            if (args.length < 2) {
                return;
            }

            entity.headicons = Integer.parseInt(args[1]);
            appearanceUpdated = true;
        } else if (cmd.equalsIgnoreCase("exists")) {
            if (args.length < 3) {
                return;
            }

            int x = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            boolean landExists = Files.isReadable(Paths.get(Server.dataDir.toString(), "maps", "m" + x + "_" + z));
            boolean locExists = Files.isReadable(Paths.get(Server.dataDir.toString(), "maps", "l" + x + "_" + z));
            sendGameMessage(x + " " + z + ": m(" + landExists + ") l(" + locExists + ")");
        } else if (cmd.equalsIgnoreCase("region")) {
            if (args.length < 3) {
                return;
            }

            int x = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            int plane = args.length >= 4 ? Integer.parseInt(args[3]) : 0;
            teleport((x << 6) + 32, (z << 6) + 32, plane);
            sendGameMessage("Teleported to region " + x + " " + z);
        } else if (cmd.equalsIgnoreCase("pos")) {
            sendGameMessage("Your position is: " + entity.x + ", " + entity.z + ", " + entity.plane + " (region: " + Position.file(entity.x) + ", " + Position.file(entity.z) + ")");
        } else if (cmd.equalsIgnoreCase("over")) {
            // teleport above ground
            if (entity.z > 6400) {
                teleport(entity.x, entity.z - 6400);
            }
        } else if (cmd.equalsIgnoreCase("under")) {
            // teleport under ground
            if (entity.z < 6400) {
                teleport(entity.x, entity.z + 6400);
            }
        } else if (cmd.equalsIgnoreCase("song")) {
            if (args.length < 2) {
                return;
            } else if (args.length > 2) {
                sendGameMessage("Multiple spaces detected: please use underscores");
            }

            playMidi(args[1]);
            sendGameMessage("Requesting to play song " + args[1]);
        } else if (cmd.equalsIgnoreCase("home")) {
            teleport(3222, 3222, 0);
        } else if (cmd.equalsIgnoreCase("up")) {
            if (entity.plane < 3) {
                teleport(entity.x, entity.z, entity.plane + 1);
            }
        } else if (cmd.equalsIgnoreCase("down")) {
            if (entity.plane > 0) {
                teleport(entity.x, entity.z, entity.plane - 1);
            }
        } else if (cmd.equalsIgnoreCase("inter")) {
            if (args.length < 2) {
                return;
            }

            openInterface(Integer.parseInt(args[1]));
        }
    }

    public void teleport(int x, int z) {
        teleport(x, z, entity.plane);
    }

    public void teleport(int x, int z, int plane) {
        entity.x = x;
        entity.z = z;
        entity.plane = plane;
        placement = true;
        loading = false;
        loaded = false;
        step = -1;
    }

    public void sendGameMessage(String msg) {
        con.out.pos = 0;

        con.out.p1isaac(ServerProt.MESSAGE_GAME);
        con.out.p1(0);
        int start = con.out.pos;
        con.out.pjstr(msg);
        con.out.psize1(con.out.pos - start);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playMidi(String name) {
        try {
            name = name.replaceAll(" ", "_");
            byte[] file = Files.readAllBytes(Paths.get(Server.songDir.toString(), name + ".mid"));

            con.out.pos = 0;
            con.out.p1isaac(ServerProt.MIDI_SONG);
            con.out.p1(0);
            int start = con.out.pos;
            con.out.pjstr(name);
            con.out.p4(Buffer.crc32(file));
            con.out.p4(file.length);
            con.out.psize1(con.out.pos - start);
            NioServer.write(con.key, con.out);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public void setSidebar(int tab, int id) {
        con.out.pos = 0;

        con.out.p1isaac(ServerProt.IF_SETTAB);
        con.out.p2(id);
        con.out.p1(tab);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendLandFile(int x, int z) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(Server.dataDir.toString(), "maps", "m" + x + "_" + z));
        int offset = 0;

        int CHUNK_SIZE = 1000;
        while (offset < data.length) {
            con.out.pos = 0;
            con.out.p1isaac(ServerProt.DATA_LAND);
            con.out.p2(0);
            int start = con.out.pos;
            con.out.p1(x);
            con.out.p1(z);
            con.out.p2(offset);
            con.out.p2(data.length);
            int remaining = Math.min(data.length - offset, CHUNK_SIZE);
            con.out.pdata(data, offset, remaining);
            con.out.psize2(con.out.pos - start);

            try {
                NioServer.write(con.key, con.out);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            offset += remaining;
        }

        con.out.pos = 0;
        con.out.p1isaac(ServerProt.DATA_LAND_DONE);
        con.out.p1(x);
        con.out.p1(z);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendLocFile(int x, int z) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(Server.dataDir.toString(), "maps", "l" + x + "_" + z));
        int offset = 0;

        int CHUNK_SIZE = 1000;
        while (offset < data.length) {
            con.out.pos = 0;
            con.out.p1isaac(ServerProt.DATA_LOC);
            con.out.p2(0);
            int start = con.out.pos;
            con.out.p1(x);
            con.out.p1(z);
            con.out.p2(offset);
            con.out.p2(data.length);
            int remaining = Math.min(data.length - offset, CHUNK_SIZE);
            con.out.pdata(data, offset, remaining);
            con.out.psize2(con.out.pos - start);

            try {
                NioServer.write(con.key, con.out);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            offset += CHUNK_SIZE;
        }

        con.out.pos = 0;
        con.out.p1isaac(ServerProt.DATA_LOC_DONE);
        con.out.p1(x);
        con.out.p1(z);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendLoadArea() {
        loading = true;
        loaded = false;

        con.out.p1isaac(ServerProt.LOAD_AREA);
        con.out.p2(0);
        int start = con.out.pos;

        con.out.p2(Position.region(entity.x));
        con.out.p2(Position.region(entity.z));
        lastLoadedX = entity.x;
        lastLoadedZ = entity.z;

        // for region ...
        // TODO: simplify this so it doesn't need to send 3x3 every time
        for (int z = -1; z <= 1; ++z) {
            for (int x = -1; x <= 1; ++x) {
                int fileX = Position.file(entity.x) + x;
                int fileZ = Position.file(entity.z) + z;
                boolean landExists = Files.isReadable(Paths.get(Server.dataDir.toString(), "maps", "m" + fileX + "_" + fileZ));
                boolean locExists = Files.isReadable(Paths.get(Server.dataDir.toString(), "maps", "l" + fileX + "_" + fileZ));
                if (!landExists && !locExists) {
                    continue;
                }

                con.out.p1(fileX);
                con.out.p1(fileZ);
                try {
                    int landCrc = Buffer.crc32(Files.readAllBytes(Paths.get(Server.dataDir.toString(), "maps", "m" + fileX + "_" + fileZ)));
                    con.out.p4(landCrc);
                } catch (Exception ex) {
                    con.out.p4(0);
                }
                try {
                    int locCrc = Buffer.crc32(Files.readAllBytes(Paths.get(Server.dataDir.toString(), "maps", "l" + fileX + "_" + fileZ)));
                    con.out.p4(locCrc);
                } catch (Exception ex) {
                    con.out.p4(0);
                }
            }
        }
        con.out.psize2(con.out.pos - start);
        // end

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int updateMovement() {
        int dst = steps[step];
        int dstX = dst >> 16;
        int dstZ = dst & 0xFFFF;
        int dir = Position.face(entity.x, entity.z, dstX, dstZ);

        entity.x = Position.moveX(entity.x, dir);
        entity.z = Position.moveZ(entity.z, dir);

        if (dir == -1) {
            step--;
            if (step < steps.length - 1 && step != -1) {
                dir = updateMovement(); // seek ahead to avoid pausing for a tick
            }
        }

        return dir;
    }
    
    public void sendPlayerInfo() {
        if (appearance.pos == 0 || appearanceUpdated) {
            generateAppearance();
        }

        int mask = 0;
        if (appearanceUpdated) {
            mask |= 0x1;
        }

        if (!placement && step != -1 && step < steps.length) {
            walkDir = updateMovement();
            if (step != -1 && step < steps.length) {
                runDir = updateMovement();
            } else {
                runDir = -1;
            }
        } else {
            walkDir = -1;
            runDir = -1;
        }

        con.out.p1isaac(ServerProt.PLAYER_INFO);
        con.out.p2(0);
        int updateStart = con.out.pos;
        con.out.accessBits();
        con.out.pBit(1, (placement || mask > 0 || walkDir != -1) ? 1 : 0); // update local player
        if (placement) {
            con.out.pBit(2, 3); // placement
            con.out.pBit(2, entity.plane);
            con.out.pBit(7, Position.local(entity.x));
            con.out.pBit(7, Position.local(entity.z));
            con.out.pBit(1, 1); // warp
            con.out.pBit(1, mask > 0 ? 1 : 0);
            placement = false;
        } else if (runDir != -1) {
            con.out.pBit(2, 2); // run
            con.out.pBit(3, walkDir);
            con.out.pBit(3, runDir);
            con.out.pBit(1, mask > 0 ? 1 : 0);
        } else if (walkDir != -1) {
            con.out.pBit(2, 1); // walk
            con.out.pBit(3, walkDir);
            con.out.pBit(1, mask > 0 ? 1 : 0);
        } else if (mask > 0) {
            con.out.pBit(2, 0); // mask update
        }
        con.out.pBit(8, 0);
        con.out.pBit(11, 2047);
        con.out.accessBytes();
        if (mask > 0) {
            con.out.p1(mask);
        }
        if ((mask & 0x1) == 1) {
            con.out.p1(appearance.pos);
            con.out.pdata(appearance.sliceBytes(0, appearance.pos), 0, appearance.pos);
            appearanceUpdated = false;
        }
        con.out.psize2(con.out.pos - updateStart);

        try {
            NioServer.write(con.key, con.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generateAppearance() {
        appearance.pos = 0;
        appearance.p1(entity.gender); // gender
        appearance.p1(entity.headicons); // head icons

        for (int i = 0; i < entity.body.length; ++i) {
            int part = entity.body[i];
            if (part >= 0) {
                part |= 0x100;
            }
            if (part > 255) {
                appearance.p2(part);
            } else {
                appearance.p1(part);
            }
        }

        for (int i = 0; i < entity.colors.length; ++i) {
            appearance.p1(entity.colors[i]);
        }

        appearance.p2(0x328);
        appearance.p2(0x337);
        appearance.p2(0x333);
        appearance.p2(0x334);
        appearance.p2(0x335);
        appearance.p2(0x336);
        appearance.p2(0x338);

        appearance.p8(name37);
        appearance.p1(entity.combatLevel);
    }
}
