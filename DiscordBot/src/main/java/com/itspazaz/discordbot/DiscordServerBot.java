package com.itspazaz.discordbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagex.runescape.Game;
import com.jagex.runetek3.cache.*;
import com.jagex.runetek3.formats.*;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.graphics.Component;
import com.jagex.runetek3.graphics.Font;
import com.jagex.runetek3.scene.*;
import com.jagex.runetek3.sound.*;
import com.jagex.runetek3.util.*;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.util.Color;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.awt.image.BufferedImage;

import org.w3c.dom.Node;

import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.imageio.stream.ImageOutputStream;

public class DiscordServerBot extends java.awt.Component {
    private static final int COLOR_BACKGROUND = 0x7F555555;

    public static final int SCROLLBAR_TRACK = 0x23201b;
    public static final int SCROLLBAR_GRIP_FOREGROUND = 0x4d4233;
    public static final int SCROLLBAR_GRIP_HIGHLIGHT = 0x766654;
    public static final int SCROLLBAR_GRIP_LOWLIGHT = 0x332d25;
    public IndexedSprite scrollbar1;
    public IndexedSprite scrollbar2;
    public IndexedSprite redstone1;
    public IndexedSprite redstone2;
    public IndexedSprite redstone3;
    public IndexedSprite redstone1h;
    public IndexedSprite redstone2h;
    public IndexedSprite backbase1;
    public IndexedSprite backbase2;
    public IndexedSprite backhmid1;
    public IndexedSprite redstone1v;
    public IndexedSprite redstone2v;
    public IndexedSprite redstone3v;
    public IndexedSprite redstone1hv;
    public IndexedSprite redstone2hv;
    public IndexedSprite inback;
    public Sprite backleft1;
    public Sprite backleft2;
    public Sprite backright1;
    public Sprite backright2;
    public Sprite backtop1;
    public Sprite backtop2;
    public Sprite backvmid1;
    public Sprite backvmid2;
    public Sprite backvmid3;
    public Sprite backhmid2;
    public IndexedSprite[] sideicons = new IndexedSprite[13];
    public IndexedSprite sideicons1;
    public IndexedSprite sideicons2;

    public int[] variables = new int[2000];
    public int[] skillLevelReal = new int[50];
    public int[] skillLevel = new int[50];
    public int[] skillExperience = new int[50];
    public static int[] EXPERIENCE_TABLE;
    public int combatLevel = 3;
    public int energy = 100;
    public int weightCarried = 0;

    private DrawArea drawArea;
    private Font p11, p12, b12, q8;
    private final Camera camera = new Camera();
    private int pitch;
    private int yaw;
    private int roll;

    public int[] characterDesigns = new int[7];

    static {
        EXPERIENCE_TABLE = new int[99];
        int i = 0;
        for (int j = 0; j < 99; j++) {
            int k = j + 1;
            int l = (int) ((double) k + 300D * Math.pow(2D, (double) k / 7D));
            i += l;
            EXPERIENCE_TABLE[j] = i / 4;
        }
    }

    public static void main(String[] args) {
        try {
            Draw3D.lowMemory = false;
            Signlink.startpriv(InetAddress.getLocalHost());
            new DiscordServerBot().run(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProgress(String message, int progress) {
        System.out.println(message + " - " + progress);
    }

    private void loadTitle() {
        showProgress("Loading title", 10);

        try {
            FileArchive title = new FileArchive(Signlink.cacheload("title", false));
            p11 = new Font(title, "p11");
            p12 = new Font(title, "p12");
            b12 = new Font(title, "b12");
            q8 = new Font(title, "q8");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void loadModels() {
        showProgress("Loading models", 50);

        FileArchive models = new FileArchive(Signlink.cacheload("models", false));
        Model.load(models);
        SeqSkeleton.load(models);
        SeqFrame.load(models);
    }

    private void loadTextures() {
        showProgress("Loading textures", 40);

        FileArchive textures = new FileArchive(Signlink.cacheload("textures", false));
        Draw3D.unpackTextures(textures);
        Draw3D.setBrightness(0.8D);
        Draw3D.setupPools(20);
    }

    private void loadConfig() {
        showProgress("Loading config", 60);

        FileArchive config = new FileArchive(Signlink.cacheload("config", false));
        SeqType.load(config);
        LocType.load(config);
        FloorType.load(config);
        ObjType.load(config);
        NPCType.load(config);
        IDKType.load(config);
        SpotAnimType.load(config);
        VarType.load(config);
    }

    private void loadMedia() {
        showProgress("Loading media", 20);

        FileArchive media = new FileArchive(Signlink.cacheload("media", false));
        inback = new IndexedSprite(media, "invback", 0);

        scrollbar1 = new IndexedSprite(media, "scrollbar", 0);
        scrollbar2 = new IndexedSprite(media, "scrollbar", 1);

        redstone1 = new IndexedSprite(media, "redstone1", 0);
        redstone2 = new IndexedSprite(media, "redstone2", 0);
        redstone3 = new IndexedSprite(media, "redstone3", 0);

        redstone1h = new IndexedSprite(media, "redstone1", 0);
        redstone1h.flipHorizontally();

        redstone2h = new IndexedSprite(media, "redstone2", 0);
        redstone2h.flipHorizontally();

        redstone1v = new IndexedSprite(media, "redstone1", 0);
        redstone1v.flipVertically();

        redstone2v = new IndexedSprite(media, "redstone2", 0);
        redstone2v.flipVertically();

        redstone3v = new IndexedSprite(media, "redstone3", 0);
        redstone3v.flipVertically();

        redstone1hv = new IndexedSprite(media, "redstone1", 0);
        redstone1hv.flipHorizontally();
        redstone1hv.flipVertically();

        redstone2hv = new IndexedSprite(media, "redstone2", 0);
        redstone2hv.flipHorizontally();
        redstone2hv.flipVertically();

        backleft1 = new Sprite(media, "backleft1", 0);
        backleft2 = new Sprite(media, "backleft2", 0);
        backright1 = new Sprite(media, "backright1", 0);
        backright2 = new Sprite(media, "backright2", 0);
        backtop1 = new Sprite(media, "backtop1", 0);
        backtop2 = new Sprite(media, "backtop2", 0);
        backvmid1 = new Sprite(media, "backvmid1", 0);
        backvmid2 = new Sprite(media, "backvmid2", 0);
        backvmid3 = new Sprite(media, "backvmid3", 0);
        backbase1 = new IndexedSprite(media, "backbase1", 0);
        backbase2 = new IndexedSprite(media, "backbase2", 0);
        backhmid1 = new IndexedSprite(media, "backhmid1", 0);
        backhmid2 = new Sprite(media, "backhmid2", 0);

        if (Signlink.clientversion > 194) {
            try {
                for (int n = 0; n < 14; n++) {
                    sideicons[n] = new IndexedSprite(media, "sideicons", n);
                }
            } catch (Exception _ex) {
            }
        } else {
            try {
                sideicons1 = new IndexedSprite(media, "sideicons1", 0);
                sideicons2 = new IndexedSprite(media, "sideicons2", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        loadInterface(media);
    }

    private void loadInterface(FileArchive media) {
        showProgress("Loading interface", 30);

        FileArchive archive = new FileArchive(Signlink.cacheload("interface", false));
        Font[] fonts = { p11, p12, b12, q8 };
        Component.load(media, fonts, archive);
    }

    private void loadWordEnc() {
        showProgress("Loading word encoding", 70);

        FileArchive wordenc = new FileArchive(Signlink.cacheload("wordenc", false));
        WordPack.load(wordenc);
    }

    private void loadSounds() {
        showProgress("Loading sounds", 80);

        FileArchive sounds = new FileArchive(Signlink.cacheload("sounds", false));
        byte[] src = sounds.read("sounds.dat");
        Buffer buffer = new Buffer(src);
        SoundTrack.load(buffer);
    }

    private BufferedImage exportImage(int[] pixels) {
        byte[] raw = new byte[pixels.length * 4];
        int offset = 0;
        for (int rgb : pixels) {
            raw[offset++] = (byte) (rgb >> 16); // red
            raw[offset++] = (byte) (rgb >> 8); // green
            raw[offset++] = (byte) (rgb); // blue

            // set transparency for background color
            if (rgb >> 24 == 0x7F) {
                raw[offset++] = (byte) 0;
            } else {
                raw[offset++] = (byte) 0xFF;
            }
        }

        DataBuffer buffer = new DataBufferByte(raw, raw.length);
        int samplesPerPixel = 4;
        int[] bandOffsets = { 0, 1, 2, 3 };
        ColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, Draw2D.width, Draw2D.height, samplesPerPixel * Draw2D.width, samplesPerPixel, bandOffsets, null);
        BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
        return image;
    }

    private InputStream getImageStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outStream);
        return new ByteArrayInputStream(outStream.toByteArray());
    }

    void load(int revision) {
        Signlink.clientversion = revision;
        Signlink.cachedir = "." + revision + "_store";

        loadTitle();
        loadTextures();
        loadModels();
        loadMedia();
        loadConfig(); // has to come after models
        loadWordEnc();
        loadSounds();
    }

    void unload() {
        LocType.unload();
        NPCType.unload();
        ObjType.unload();
        FloorType.instances = null;
        IDKType.instances = null;
        Component.instances = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarType.instances = null;
        Model.unload();
        SeqType.instances = null;
        SeqSkeleton.instances = null;
        SeqFrame.instances = null;
        System.gc();
    }

    public void min() {
        for (int i = 0; i < skillLevel.length; ++i) {
            skillLevel[i] = 1;
            skillLevelReal[i] = 1;
            skillExperience[i] = 0;
        }
        skillExperience[3] = 1154;
        skillLevel[3] = 10;
        skillLevelReal[3] = 10;
        combatLevel = 3;
    }

    public void max() {
        for (int i = 0; i < skillLevel.length; ++i) {
            skillExperience[i] = 13034431;
            skillLevel[i] = 99;
            skillLevelReal[i] = 99;
        }
        combatLevel = 126;
    }

    /*FileOutputStream output = new FileOutputStream("dump/" + id + "." + name + "." + type);
    int DEFAULT_BUFFER_SIZE = 1024;
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    n = stream.read(buffer, 0, DEFAULT_BUFFER_SIZE);
    while (n >= 0) {
        output.write(buffer, 0, n);
        n = stream.read(buffer, 0, DEFAULT_BUFFER_SIZE);
    }*/

    public void run(String API_TOKEN) {
        min();

        load(225);
        System.out.println("Loaded cache, connecting to Discord...");

        ObjectMapper mapper = new ObjectMapper();
        DiscordClient.create(API_TOKEN).withGateway(client -> client.on(MessageCreateEvent.class, event -> {
            try {
                Message message = event.getMessage();
                String command = message.getContent().toLowerCase();
                if (!command.startsWith("!")) {
                    return Mono.empty();
                }

                String[] commandArgs = command.trim().split(" ");
                if (commandArgs[0].equalsIgnoreCase("!item") && commandArgs.length > 1) {
                    int stack = 1;
                    boolean advanced = false;
                    try {
                        if (commandArgs.length > 2) {
                            stack = Integer.parseInt(commandArgs[2]);
                        }
                        if (commandArgs.length > 3) {
                            advanced = commandArgs[3].equalsIgnoreCase("json");
                        }
                    } catch (Exception ignored) {
                        if (commandArgs[2].equalsIgnoreCase("json")) {
                            advanced = true;
                        }
                    }

                    // render
                    drawArea = new DrawArea(32, 32);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);
                    int id = Integer.parseInt(commandArgs[1]);
                    ObjType item = ObjType.get(id);
                    Sprite sprite = ObjType.getSprite(id, stack);
                    sprite.draw(0, 0);

                    String name = "";
                    if (item.name != null && item.name.length() > 0) {
                        name = item.name;
                    }

                    String description = "";
                    if (item.description != null && item.description.length() > 0) {
                        description = item.description;
                    }

                    // export
                    EmbedCreateSpec embed = EmbedCreateSpec.builder()
                        .color(Color.of(0xE67E22))
                        .title(name)
                        .url("https://oldschool.runescape.wiki/w/" + URLEncoder.encode(name.replaceAll("\\s", "_"), StandardCharsets.UTF_8.toString()))
                        .description(description + (advanced ? "\n```json\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item) + "```": ""))
                        .thumbnail("attachment://" + item.index + ".png")
                        .addField("Members", item.members ? "Yes" : "No", true)
                        .addField("Stackable", item.stackable ? "Yes" : "No", true)
                        .footer("Revision " + Signlink.clientversion + (stack > 1 && item.stackable ? " | Quantity: " + stack : ""), "")
                        .build();

                    InputStream image = getImageStream(exportImage(drawArea.pixels));
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(item.index + ".png", image)
                            .addEmbed(embed)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!obj") && commandArgs.length > 1) {
                    // load
                    int id = Integer.parseInt(commandArgs[1]);
                    LocType loc = LocType.get(id);
                    loc.computeVertexColors = false;

                    if (commandArgs.length > 2) {
                        try {
                            loc.seqIndex = Integer.parseInt(commandArgs[2]);
                        } catch (Exception ex) {}
                    }

                    String name = "";
                    if (loc.name != null && loc.name.length() > 0) {
                        name = loc.name;
                    }

                    String description = "";
                    if (loc.description != null && loc.description.length() > 0) {
                        description = loc.description;
                    }

                    // reposition camera
                    pitch = 0;
                    yaw = 143;
                    roll = 0;
                    camera.pitch = 192;
                    camera.x = 0;
                    camera.z = 357;
                    camera.y = 427;

                    // render
                    drawArea = new DrawArea(384, 640);
                    drawArea.bind();
                    Draw3D.prepareOffsets();

                    InputStream stream;
                    String type = "png";
                    if (loc.seqIndex != -1) {
                        SeqType seq = SeqType.instances[loc.seqIndex];
                        BufferedImage[] images = new BufferedImage[seq.primaryFrames.length];
                        String[] delays = new String[seq.primaryFrames.length];

                        int locFrame = 0;
                        for (int i = 0; i < seq.primaryFrames.length; ++i) {
                            Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);
                            loc.getModel(loc.modelTypes[0], 0, -400, -400, -400, -400, seq.primaryFrames[locFrame])
                                .draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);
                            images[i] = exportImage(drawArea.pixels);
                            delays[i] = "15";
                            locFrame++;
                        }

                        type = "gif";
                        stream = new ByteArrayInputStream(saveAnimate(images, delays).toByteArray());
                    } else {
                        Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);
                        loc.getModel(loc.modelTypes[0], 0, -400, -400, -400, -400, -1)
                            .draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);
                        stream = getImageStream(exportImage(drawArea.pixels));
                    }

                    // export
                    EmbedCreateSpec embed = EmbedCreateSpec.builder()
                        .color(Color.of(0xE67E22))
                        .title(name)
                        .url("https://oldschool.runescape.wiki/w/" + URLEncoder.encode(name.replaceAll("\\s", "_"), StandardCharsets.UTF_8.toString()))
                        .description(description)
                        .image("attachment://" + id + "." + type)
                        .footer("Revision " + Signlink.clientversion, "")
                        .build();
                    String finalType = type;
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + "." + finalType, stream)
                            .addEmbed(embed)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!npc") && commandArgs.length > 1) {
                    // load
                    int id = Integer.parseInt(commandArgs[1]);
                    NPCEntity npc = new NPCEntity();
                    npc.info = NPCType.get(id);
                    npc.primarySeq = npc.info.standSeq;

                    if (commandArgs.length > 2) {
                        try {
                            npc.primarySeq = Integer.parseInt(commandArgs[2]);
                        } catch (Exception ex) {}
                    }

                    String name = "";
                    if (npc.info.name != null && npc.info.name.length() > 0) {
                        name = npc.info.name;
                    }

                    String description = "";
                    if (npc.info.description != null && npc.info.description.length() > 0) {
                        description = npc.info.description;
                    }

                    // reposition camera
                    pitch = 0;
                    yaw = 169;
                    roll = 0;
                    camera.pitch = 128;
                    camera.x = 0;
                    camera.z = 190;
                    camera.y = 195;

                    // render
                    drawArea = new DrawArea(384, 640);
                    drawArea.bind();
                    Draw3D.prepareOffsets();

                    SeqType seq = SeqType.instances[npc.primarySeq];
                    BufferedImage[] images = new BufferedImage[seq.primaryFrames.length];
                    String[] delays = new String[seq.primaryFrames.length];

                    for (int i = 0; i < seq.primaryFrames.length; ++i) {
                        Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);
                        npc.getModel().draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);

                        images[i] = exportImage(drawArea.pixels);
                        delays[i] = "15";
                        npc.primarySeqFrame++;
                    }

                    InputStream stream = new ByteArrayInputStream(saveAnimate(images, delays).toByteArray());

                    // export
                    EmbedCreateSpec embed = EmbedCreateSpec.builder()
                        .color(Color.of(0xE67E22))
                        .title(name)
                        .url("https://oldschool.runescape.wiki/w/" + URLEncoder.encode(name.replaceAll("\\s", "_"), StandardCharsets.UTF_8.toString()))
                        .description(description)
                        .image("attachment://" + id + ".gif")
                        .footer("Revision " + Signlink.clientversion, "")
                        .build();
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".gif", stream)
                            .addEmbed(embed)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!sound") && commandArgs.length > 1) {
                    int id = Integer.parseInt(commandArgs[1]);
                    Buffer sound = SoundTrack.generate(0, id);
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".wav", new ByteArrayInputStream(sound.data, 0, sound.offset))
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!inter") && commandArgs.length > 1) {
                    // Load
                    int id = Integer.parseInt(commandArgs[1]);
                    Game.resetParentComponentSeq(id);
                    Component inter = Component.instances[id];

                    // render
                    drawArea = new DrawArea(inter.width, inter.height);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

                    resetCharacterDesign();
                    animateInterface(id, 40);
                    drawInterface(0, 0, inter, 0, -1);

                    // export
                    InputStream stream = getImageStream(exportImage(drawArea.pixels));
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".png", stream)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!tab") && commandArgs.length > 1) {
                    // Load
                    int id = Integer.parseInt(commandArgs[1]);
                    Game.resetParentComponentSeq(id);
                    Component inter = Component.instances[id];

                    // render
                    drawArea = new DrawArea(inback.width, inback.height);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

                    inback.draw(0, 0);

                    resetCharacterDesign();
                    animateInterface(id, 40);
                    drawInterface(0, 0, inter, 0, -1);

                    // export
                    InputStream stream = getImageStream(exportImage(drawArea.pixels));
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".png", stream)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!skills") && commandArgs.length == 1) {
                    // Load
                    int id = 3917;
                    if (Signlink.clientversion == 194) {
                        id = 3131;
                    }
                    Game.resetParentComponentSeq(id);
                    Component inter = Component.instances[id];

                    // render
                    int xOffset = 3;

                    drawArea = new DrawArea(269 - xOffset, 341);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

                    backhmid1.draw(-26, -xOffset);
                    backvmid2.draw((backhmid1.height - 26), -xOffset);
                    backhmid2.draw((backhmid1.height - 26) + backvmid2.height, -520 - xOffset);
                    backvmid3.draw((backhmid1.height - 26) + backvmid2.height + backhmid2.height, -19 - xOffset);
                    backbase2.draw(301, -19 - xOffset);
                    backright2.draw(40, 232 - xOffset);
                    redstone2.draw(4, 56);
                    if (Signlink.clientversion > 194) {
                        sideicons[1].draw(6, 56);
                    } else {
                        sideicons1.draw(6, 36);
                    }
                    inback.draw((backhmid1.height - 26), backvmid2.width - xOffset);
                    drawInterface((backhmid1.height - 26), backvmid2.width - xOffset, inter, 0, -1);

                    // export
                    InputStream stream = getImageStream(exportImage(drawArea.pixels));
                    int finalId = id;
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(finalId + ".png", stream)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!max") && commandArgs.length == 1) {
                    max();
                } else if (commandArgs[0].equalsIgnoreCase("!min") && commandArgs.length == 1) {
                    min();
                } else if (commandArgs[0].equalsIgnoreCase("!rc") && commandArgs.length == 1) {
                    // Load
                    int id = 3917;
                    Game.resetParentComponentSeq(id);
                    Component inter = Component.instances[id];

                    // render
                    drawArea = new DrawArea(inback.width, inback.height);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

                    inback.draw(0, 0);

                    resetCharacterDesign();
                    animateInterface(id, 40);
                    drawInterface(0, 0, inter, 0, 4160);

                    // export
                    InputStream stream = getImageStream(exportImage(drawArea.pixels));
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".png", stream)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!model") && commandArgs.length > 1) {
                    // Load
                    int id = Integer.parseInt(commandArgs[1]);
                    Model model = new Model(id);
                    model.applyLighting(64, 768, -50, -10, -50, true);

                    // reposition camera
                    pitch = 0;
                    yaw = 0;
                    roll = 0;
                    camera.pitch = 0;
                    camera.x = 0;
                    camera.z = 0;
                    camera.y = 400;

                    // render
                    drawArea = new DrawArea(512, 384);
                    drawArea.bind();
                    Draw3D.prepareOffsets();
                    Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);

                    model.draw(pitch, yaw, roll, camera.pitch, camera.x, camera.z, camera.y);

                    // export
                    InputStream stream = getImageStream(exportImage(drawArea.pixels));
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .addFile(id + ".png", stream)
                            .build()
                        )
                    );
                } else if (commandArgs[0].equalsIgnoreCase("!rev") && commandArgs.length > 1) {
                    int lastRev = Signlink.clientversion;
                    try {
                        int rev = Integer.parseInt(commandArgs[1]);
                        unload();
                        load(rev);
                        return message.getChannel().flatMap(channel ->
                            channel.createMessage(MessageCreateSpec.builder()
                                .content("Switched to revision " + rev)
                                .build()
                            )
                        );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        unload();
                        load(lastRev);
                        return message.getChannel().flatMap(channel ->
                            channel.createMessage(MessageCreateSpec.builder()
                                .content("Unable to switch to revision")
                                .build()
                            )
                        );
                    }
                } else if (commandArgs[0].equalsIgnoreCase("!runewiki") && commandArgs.length == 1) {
                    return message.getChannel().flatMap(channel ->
                        channel.createMessage(MessageCreateSpec.builder()
                            .content("Commands:\n!rev <194/225> - Change revisions\n!item <id> (amount/json) (json) - render an item sprite\n!obj <id> (animation) - render an object, with an optional animation\n!npc <id> (animation) - render an ID, with an optional animation\n!model <id> - render a model ID\n!sound <id> - convert a sound to wav\n!inter <id> - display an interface\n!tab <id> - display an interface with the inventory sprite\n!skills - !tab 3917 but with data and a border\n!max - max skill levels\n!min - min skill levels\n!rc - !skills, but focused on RuneCrafting")
                            .build()
                        )
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return Mono.empty();
        })).block();
    }

    public void resetCharacterDesign() {
        for (int n = 0; n < 7; n++) {
            characterDesigns[n] = -1;
            for (int type = 0; type < IDKType.count; type++) {
                if (IDKType.instances[type].validStyle || IDKType.instances[type].type != n) {
                    continue;
                }

                characterDesigns[n] = type;
                break;
            }
        }
    }

    public boolean animateInterface(int id, int sceneDelta) {
        boolean animated = false;

        Component parent = Component.instances[id];
        if (parent == null || parent.children == null) {
            return false;
        }

        for (int n = 0; n < parent.children.length; n++) {
            if (parent.children[n] == -1) {
                break;
            }

            Component child = Component.instances[parent.children[n]];
            if (child.type == 1) {
                animated |= animateInterface(child.id, sceneDelta);
            }

            if (child.type == 6 && (child.seqId != -1 || child.activeSeqId != -1)) {
                int seqId = child.seqId;
                if (seqId != -1) {
                    SeqType seqType = SeqType.instances[seqId];
                    for (child.seqCycle += sceneDelta; child.seqCycle > seqType.frameDelay[child.seqFrame]; ) {
                        child.seqCycle -= seqType.frameDelay[child.seqFrame] + 1;
                        child.seqFrame++;
                        if (child.seqFrame >= seqType.frameCount) {
                            child.seqFrame -= seqType.delay;
                            if (child.seqFrame < 0 || child.seqFrame >= seqType.frameCount) {
                                child.seqFrame = 0;
                            }
                        }

                        animated = true;
                    }
                }
            }
        }

        return animated;
    }

    public void updateComponentContent(Component inter) {
        int type = inter.contentType;
        if (type >= 1 && type <= 100) {
            inter.text = "";
            inter.buttonType = 0;
        } else if (type >= 101 && type <= 200) {
            inter.text = "";
            inter.buttonType = 0;
        } else if (type == 203) {
            inter.scrollHeight = 0;
        } else if (type >= 401 && type <= 500) {
            inter.text = "";
            inter.buttonType = 0;
        } else if (type == 503) {
            inter.scrollHeight = 0;
        } else if (type == 327) {
            inter.modelEyePitch = 150;
            inter.modelYaw = (int) (Math.sin((double) 40 / 40D) * 256D) & 0x7ff;
            Model[] models = new Model[7];

            int parts = 0;
            for (int part = 0; part < 7; part++) {
                int idk = characterDesigns[part];
                if (idk >= 0) {
                    models[parts++] = IDKType.instances[idk].getModel();
                }
            }

            Model m = new Model(models, parts);
            m.applyGroups();
            m.applyFrame(SeqType.instances[0x328].primaryFrames[0]);
            m.applyLighting(64, 850, -30, -50, -30, true);
            inter.modelDisabled = m;
        } else if (type == 600) {
            inter.text = "";
        } else if (type == 613) {
            inter.text = "";
        } else if (type == 650 || type == 655) {
            inter.text = "";
        } else if (type == 651) {
            inter.text = "";
        } else if (type == 652) {
            inter.text = "";
        } else if (type == 653) {
            inter.text = "";
        } else if (type == 654) {
            inter.text = "";
        }
    }

    public void drawScrollbar(int x, int k, int l, int i1, int j1) {
        scrollbar1.draw(k, x);
        scrollbar2.draw((k + j1) - 16, x);
        Draw2D.fillRect(k + 16, x, SCROLLBAR_TRACK, 16, j1 - 32);
        int xOff = ((j1 - 32) * j1) / i1;
        if (xOff < 8) {
            xOff = 8;
        }
        int yOff = ((j1 - 32 - xOff) * l) / (i1 - j1);
        Draw2D.fillRect(k + 16 + yOff, x, SCROLLBAR_GRIP_FOREGROUND, 16, xOff);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + yOff, xOff, x);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + yOff, xOff, x + 1);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + yOff, 16, x);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 17 + yOff, 16, x);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 16 + yOff, xOff, x + 15);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 17 + yOff, xOff - 1, x + 14);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 15 + yOff + xOff, 16, x);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 14 + yOff + xOff, 15, x + 1);
    }


    public int executeInterface(Component inter, int script) {
        if (inter.script == null || script >= inter.script.length) {
            return -2;
        }

        try {
            int[] code = inter.script[script];
            int a = 0;
            int position = 0;

            do {
                int opcode;
                do {
                    opcode = code[position++];
                    if (opcode == 0) {
                        return a;
                    }
                    if (opcode == 1) {
                        a += skillLevelReal[code[position++]];
                    }
                    if (opcode == 2) {
                        a += skillLevel[code[position++]];
                    }
                    if (opcode == 3) {
                        a += skillExperience[code[position++]];
                    }
                    if (opcode == 4) {
                        Component c = Component.instances[code[position++]];
                        int k1 = code[position++] + 1;
                        for (int j2 = 0; j2 < c.inventoryIndices.length; j2++) {
                            if (c.inventoryIndices[j2] == k1) {
                                a += c.inventoryAmount[j2];
                            }
                        }
                    }
                    if (opcode == 5) {
                        a += variables[code[position++]];
                    }
                    if (opcode == 6) {
                        a += EXPERIENCE_TABLE[skillLevel[code[position++]] - 1];
                    }
                    if (opcode == 7) {
                        a += (variables[code[position++]] * 100) / 46875;
                    }
                    if (opcode == 8) {
                        a += combatLevel;
                    }
                    if (opcode == 9) {
                        for (int i1 = 0; i1 < 19; i1++) {
                            if (i1 == 18) {
                                i1 = 20;
                            }
                            a += skillLevel[i1];
                        }
                    }
                    if (opcode == 10) {
                        Component c = Component.instances[code[position++]];
                        int l1 = code[position++] + 1;
                        for (int k2 = 0; k2 < c.inventoryIndices.length; k2++) {
                            if (c.inventoryIndices[k2] != l1) {
                                continue;
                            }
                            a += 0x3b9ac9ff;
                            break;
                        }
                    }
                    if (opcode == 11) {
                        a += energy;
                    }
                    if (opcode == 12) {
                        a += weightCarried;
                    }
                } while (opcode != 13);
                int j1 = variables[code[position++]];
                int i2 = code[position++];
                a += (j1 & 1 << i2) == 0 ? 0 : 1;
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    private void drawInterface(int y, int x, Component inter, int scrollY, int hoveredInter) {
        if (inter.type != 0 || inter.children == null) {
            return;
        }

        if (inter.hidden && hoveredInter != inter.id) {
            return;
        }

        int left = Draw2D.left;
        int top = Draw2D.top;
        int right = Draw2D.right;
        int bottom = Draw2D.bottom;
        Draw2D.setBounds(y + inter.height, y, x + inter.width, x);

        int count = inter.children.length;
        for (int n = 0; n < count; n++) {
            int offsetX = inter.childX[n] + x;
            int offsetY = (inter.childY[n] + y) - scrollY;
            Component child = Component.instances[inter.children[n]];
            offsetX += child.x;
            offsetY += child.y;
            if (child.contentType > 0) {
                updateComponentContent(child);
            }

            if (child.type == 0) {
                if (child.scrollY > child.scrollHeight - child.height) {
                    child.scrollY = child.scrollHeight - child.height;
                }

                if (child.scrollY < 0) {
                    child.scrollY = 0;
                }

                drawInterface(offsetY, offsetX, child, child.scrollY, hoveredInter);
                if (child.scrollHeight > child.height) {
                    drawScrollbar(offsetX + child.width, offsetY, child.scrollY, child.scrollHeight, child.height);
                }
            } else if (child.type == 2) {
                int i3 = 0;
                for (int childY = 0; childY < child.height; childY++) {
                    for (int childX = 0; childX < child.width; childX++) {
                        int j5 = offsetX + childX * (32 + child.inventoryMarginX);
                        int i6 = offsetY + childY * (32 + child.inventoryMarginY);
                        if (i3 < 20) {
                            j5 += child.inventoryOffsetX[i3];
                            i6 += child.inventoryOffsetY[i3];
                        }
                        if (child.inventoryIndices[i3] > 0) {
                            int k6 = 0;
                            int k8 = 0;
                            int i9 = child.inventoryIndices[i3] - 1;
                            if (j5 >= -32 && j5 <= 512 && i6 >= -32 && i6 <= 334) {
                                Sprite itemIcon = ObjType.getSprite(i9, child.inventoryAmount[i3]);
                                itemIcon.draw(i6, j5);
                                if (itemIcon.cropW == 33 || child.inventoryAmount[i3] != 1) {
                                    int k9 = child.inventoryAmount[i3];
                                    p11.draw(j5 + 1 + k6, i6 + 10 + k8, 0, Game.formatObjAmount(k9));
                                    p11.draw(j5 + k6, i6 + 9 + k8, 0xffff00, Game.formatObjAmount(k9));
                                }
                            }
                        } else if (child.inventorySprite != null && i3 < 20) {
                            Sprite sprite = child.inventorySprite[i3];
                            if (sprite != null) {
                                sprite.draw(i6, j5);
                            }
                        }
                        i3++;
                    }
                }
            } else if (child.type == 3) {
                if (child.fill) {
                    Draw2D.fillRect(offsetY, offsetX, child.color, child.width, child.height);
                } else {
                    Draw2D.drawRect(offsetX, child.color, child.height, offsetY, child.width);
                }
            } else if (child.type == 4) {
                Font font = child.font;
                int color = child.color;
                String text = child.text;

                if (hoveredInter == child.id) {
                    color = child.hoverColor;
                }

                if (child.buttonType == 6) {
                    text = "Please wait...";
                    color = child.color;
                }

                for (int textY = offsetY + font.height; text.length() > 0; textY += font.height) {
                    if (text.contains("%")) {
                        do {
                            int l6 = text.indexOf("%1");
                            if (l6 == -1)
                                break;
                            text = text.substring(0, l6) + Game.getIntString(executeInterface(child, 0)) + text.substring(l6 + 2);
                        } while (true);
                        do {
                            int i7 = text.indexOf("%2");
                            if (i7 == -1) {
                                break;
                            }
                            text = text.substring(0, i7) + Game.getIntString(executeInterface(child, 1)) + text.substring(i7 + 2);
                        } while (true);
                        do {
                            int j7 = text.indexOf("%3");
                            if (j7 == -1) {
                                break;
                            }
                            text = text.substring(0, j7) + Game.getIntString(executeInterface(child, 2)) + text.substring(j7 + 2);
                        } while (true);
                        do {
                            int k7 = text.indexOf("%4");
                            if (k7 == -1) {
                                break;
                            }
                            text = text.substring(0, k7) + Game.getIntString(executeInterface(child, 3)) + text.substring(k7 + 2);
                        } while (true);
                        do {
                            int l7 = text.indexOf("%5");
                            if (l7 == -1) {
                                break;
                            }
                            text = text.substring(0, l7) + Game.getIntString(executeInterface(child, 4)) + text.substring(l7 + 2);
                        } while (true);
                    }

                    int newline = text.indexOf("\\n");
                    String str;
                    if (newline != -1) {
                        str = text.substring(0, newline);
                        text = text.substring(newline + 2);
                    } else {
                        str = text;
                        text = "";
                    }

                    if (child.center) {
                        font.drawCentered(offsetX + child.width / 2, color, child.shadow, textY, str);
                    } else {
                        font.draw(offsetX, textY, str, child.shadow, color);
                    }
                }
            } else if (child.type == 5) {
                Sprite image = child.image;
                if (image != null) {
                    image.draw(offsetY, offsetX);
                }
            } else if (child.type == 6) {
                int oldX = Draw3D.centerX;
                int oldY = Draw3D.centerY;
                Draw3D.centerX = offsetX + child.width / 2;
                Draw3D.centerY = offsetY + child.height / 2;

                int sin = Draw3D.sin[child.modelEyePitch] * child.modelZoom >> 16;
                int cos = Draw3D.cos[child.modelEyePitch] * child.modelZoom >> 16;

//                int seqId = child.seqId;
//                Model model;
//                if (seqId == -1) {
//                    model = child.getModel(-1, -1, false);
//                } else {
//                    SeqType seqType = SeqType.animations[seqId];
//                    model = child.getModel(seqType.primaryFrames[child.seqFrame], seqType.secondaryFrames[child.seqFrame], false);
//                }
                Model model = child.modelDisabled;
                if (model != null) {
                    model.applyLighting(64, 768, -50, -10, -50, true);
                    model.draw(0, child.modelYaw, 0, child.modelEyePitch, 0, sin, cos);
                }

                Draw3D.centerX = oldX;
                Draw3D.centerY = oldY;
            } else if (child.type == 7) {
                Font font = child.font;
                int invItem = 0;
                for (int offY = 0; offY < child.height; offY++) {
                    for (int offX = 0; offX < child.width; offX++) {
                        if (child.inventoryIndices[invItem] > 0) {
                            ObjType objType = ObjType.get(child.inventoryIndices[invItem] - 1);
                            String name = objType.name;
                            if (objType.stackable || child.inventoryAmount[invItem] != 1) {
                                name = name + " x" + Game.formatNumber(child.inventoryAmount[invItem]);
                            }
                            int invX = offsetX + offX * (115 + child.inventoryMarginX);
                            int invY = offsetY + offY * (12 + child.inventoryMarginY);
                            if (child.center) {
                                font.drawCentered(invX + child.width / 2, child.color, child.shadow, invY, name);
                            } else {
                                font.draw(invX, invY, name, child.shadow, child.color);
                            }
                        }
                        invItem++;
                    }
                }
            }
        }

        Draw2D.setBounds(bottom, top, right, left);
    }

    public static void configure(IIOMetadata meta, String delayTime, int imageIndex) {
        String metaFormat = meta.getNativeMetadataFormatName();

        if (!"javax_imageio_gif_image_1.0".equals(metaFormat)) {
            throw new IllegalArgumentException("Unfamiliar gif metadata format: " + metaFormat);
        }

        Node root = meta.getAsTree(metaFormat);

        //find the GraphicControlExtension node
        Node child = root.getFirstChild();
        while (child != null) {
            if ("GraphicControlExtension".equals(child.getNodeName())) {
                break;
            }
            child = child.getNextSibling();
        }

        IIOMetadataNode gce = (IIOMetadataNode) child;
        gce.setAttribute("userDelay", "FALSE");
        gce.setAttribute("delayTime", delayTime);
        gce.setAttribute("disposalMethod", "restoreToBackgroundColor");

        //only the first node needs the ApplicationExtensions node
        if (imageIndex == 0) {
            IIOMetadataNode aes = new IIOMetadataNode("ApplicationExtensions");
            IIOMetadataNode ae = new IIOMetadataNode("ApplicationExtension");
            ae.setAttribute("applicationID", "NETSCAPE");
            ae.setAttribute("authenticationCode", "2.0");
            byte[] uo = new byte[]{
                //last two bytes is an unsigned short (little endian) that
                //indicates the the number of times to loop.
                //0 means loop forever.
                0x1, 0x0, 0x0
            };
            ae.setUserObject(uo);
            aes.appendChild(ae);
            root.appendChild(aes);
        }

        try {
            meta.setFromTree(metaFormat, root);
        } catch (IIOInvalidTreeException e) {
            //shouldn't happen
            throw new Error(e);
        }
    }

    public static ByteArrayOutputStream saveAnimate(BufferedImage[] frames, String[] delayTimes) throws Exception {
        ImageWriter iw = ImageIO.getImageWritersByFormatName("gif").next();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(stream);
        iw.setOutput(ios);
        iw.prepareWriteSequence(null);

        for (int i = 0; i < frames.length; i++) {
            BufferedImage src = frames[i];
            ImageWriteParam iwp = iw.getDefaultWriteParam();
            IIOMetadata metadata = iw.getDefaultImageMetadata(new ImageTypeSpecifier(src), iwp);
            configure(metadata, delayTimes[i], i);
            IIOImage ii = new IIOImage(src, null, metadata);
            iw.writeToSequence(ii, null);
        }

        iw.endWriteSequence();
        ios.close();
        return stream;
    }
}
