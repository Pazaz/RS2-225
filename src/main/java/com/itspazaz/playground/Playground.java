package com.itspazaz.playground;

import com.jagex.runescape.formats.*;
import com.jagex.runetek3.GameShell;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.Model;
import com.jagex.runetek3.formats.SeqBase;
import com.jagex.runetek3.formats.SeqFrame;
import com.jagex.runetek3.formats.SeqType;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.sound.SoundTrack;
import com.jagex.runetek3.util.BZip2InputStream;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Signlink;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.net.InetAddress;

public class Playground extends GameShell {
    private IndexedFont p11, p12, b12, q8;
    private Sprite backgroundLeft;
    private Sprite backgroundRight;
    private ObjType obj;
    private Model model;

    private int pitch;
    private int yaw;
    private int roll;

    private int frame;

    public static void main(String[] args) {
        try {
            Draw3D.lowMemory = false;

            Signlink.startpriv(InetAddress.getLocalHost());

            Playground program = new Playground();
            program.initFrame(768, 1024, "Playground");
        } catch (Exception ex) {}
    }

    @Override
    public void load() {
        setLoopRate(165);
        drawArea.bind();
        Draw3D.prepareOffsets();

        // play some background music
        playSong("autumn voyage");

        // load some cache files
        loadTitle();
        loadMedia();
        loadTextures();
        loadModels();
        loadConfig(); // has to come after models
        loadWordEnc();
        loadSounds();

        showProgress("Finished loading", 90);
        obj = ObjType.get(995);
        yaw = obj.iconYaw;
        roll = obj.iconRoll;
        model = obj.getModel(12345);
    }

    @Override
    public void unload() {
        Signlink.midifade = 0;
        Signlink.midi = "stop";
        LocType.unload();
        NpcType.unload();
        ObjType.unload();
        FloType.instances = null;
        IdkType.instances = null;
        InterfaceComponent.instances = null;
        SeqType.animations = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarpType.instances = null;
        drawArea = null;
        Draw3D.unload();
        Model.unload();
        SeqBase.instance = null;
        SeqFrame.instance = null;
        System.gc();
    }

    @Override
    public void update() {
        // Rotate X axis
        if (keyDown[GameShell.KEY_UP] == 1) {
            pitch--; // flipped to be more natural
        } else if (keyDown[GameShell.KEY_DOWN] == 1) {
            pitch++;
        }
        if (pitch > 2047) {
            pitch = 0;
        } else if (pitch < 0) {
            pitch = 2047;
        }

        // Rotate on Y axis
        if (keyDown[GameShell.KEY_LEFT] == 1) {
            yaw++;
        } else if (keyDown[GameShell.KEY_RIGHT] == 1) {
            yaw--;
        }
        if (yaw > 2047) {
            yaw = 0;
        } else if (yaw < 0) {
            yaw = 2047;
        }

        // Rotate on Z axis
        if (keyDown[GameShell.KEY_HOME] == 1) {
            roll++;
        } else if (keyDown[GameShell.KEY_END] == 1) {
            roll--;
        }
        if (roll > 2047) {
            roll = 0;
        } else if (roll < 0) {
            roll = 2047;
        }

        if (keyDown['e'] == 1) {
            exportImage(drawArea.pixels, "dump/test" + frame++);
        }
    }

    @Override
    public void draw() {
        // Clear the old buffer
        Draw2D.clear();

        // Draw the background
        //backgroundLeft.drawOpaque(0, 0);
        //backgroundRight.drawOpaque(backgroundLeft.width, 0);
        Draw2D.fillRect(0, 0, 0x00FF00, Draw2D.width, Draw2D.height);

        // Draw a model
        model.draw(pitch, yaw, roll, obj.iconCameraPitch, 0, 200, 220);

        // Debug text
        //p12.draw(1, 13, 0x000000, "FPS: " + fps);
        //p12.draw(0, 12, 0xFFFF00, "FPS: " + fps);

        // Render the frame
        drawArea.drawImage(0, graphics, 0);
    }

    @Override
    public void refresh() {
    }

    private void playSong(String name) {
        try {
            byte[] src = Signlink.cacheload(name + ".mid");
            int length = (new Buffer(src)).readDWord();
            byte[] decompressed = new byte[length];
            BZip2InputStream.read(decompressed, length, src, 0, 4);
            Signlink.midifade = 0;
            Signlink.midisave(decompressed, length);
        } catch (Exception ex) {}
    }

    private void loadTitle() {
        showProgress("Loading title", 10);

        FileArchive title = new FileArchive(Signlink.cacheload("title"));
        p11 = new IndexedFont(title, "p11");
        p12 = new IndexedFont(title, "p12");
        b12 = new IndexedFont(title, "b12");
        q8 = new IndexedFont(title, "q8");

        /*backgroundLeft = new Sprite(title.read("title.dat", null), this);
        backgroundRight = new Sprite(title.read("title.dat", null), this);
        // flip the background
        int[] tmp = new int[backgroundRight.width];
        for (int y = 0; y < backgroundRight.height; y++) {
            for (int x = 0; x < backgroundRight.width; x++) {
                tmp[x] = backgroundRight.pixels[(backgroundRight.width - x - 1) + backgroundRight.width * y];
            }

            System.arraycopy(tmp, 0, backgroundRight.pixels, backgroundRight.width * y, backgroundRight.width);
        }*/
    }

    private void loadModels() {
        showProgress("Loading title", 50);

        FileArchive models = new FileArchive(Signlink.cacheload("models"));
        Model.load(models);
        SeqBase.load(models);
        SeqFrame.load(models);
    }

    private void loadTextures() {
        showProgress("Loading title", 40);

        FileArchive textures = new FileArchive(Signlink.cacheload("textures"));
        Draw3D.unpackTextures(textures);
        Draw3D.setBrightness(0.80000000000000004D);
        Draw3D.setupPools(20);
    }

    private void loadConfig() {
        showProgress("Loading title", 60);

        FileArchive config = new FileArchive(Signlink.cacheload("config"));
        SeqType.load(config);
        LocType.load(config);
        FloType.load(config);
        ObjType.load(config);
        NpcType.load(config);
        IdkType.load(config);
        SpotAnimType.load(config);
        VarpType.load(config);
    }

    private void loadMedia() {
        showProgress("Loading media", 20);

        FileArchive media = new FileArchive(Signlink.cacheload("media"));

        loadInterface(media);
    }

    private void loadInterface(FileArchive media) {
        showProgress("Loading interface", 30);

        FileArchive archive = new FileArchive(Signlink.cacheload("interface"));
        IndexedFont[] fonts = { p11, p12, b12, q8 };
        InterfaceComponent.load(media, fonts, archive);
    }

    private void loadWordEnc() {
        showProgress("Loading title", 70);

        FileArchive wordenc = new FileArchive(Signlink.cacheload("wordenc"));
        WordEncoding.load(wordenc);
    }

    private void loadSounds() {
        showProgress("Loading title", 80);

        FileArchive sounds = new FileArchive(Signlink.cacheload("sounds"));
        byte[] src = sounds.read("sounds.dat", null);
        Buffer buffer = new Buffer(src);
        SoundTrack.load(buffer);
    }

    private void exportImage(int[] pixels, String filename) {
        byte[] raw = new byte[pixels.length * 3];
        int offset = 0;
        for (int n = 0; n < pixels.length; n++) {
            int rgb = pixels[n];
            raw[offset++] = (byte) (rgb >> 16);
            raw[offset++] = (byte) (rgb >> 8);
            raw[offset++] = (byte) rgb;
        }

        try {
            DataBuffer buffer = new DataBufferByte(raw, raw.length);
            int samplesPerPixel = 3;
            int[] bandOffsets = { 0, 1, 2 };
            ColorModel colorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), false, true, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);
            WritableRaster raster = Raster.createInterleavedRaster(buffer, gameWidth, gameHeight, samplesPerPixel * gameWidth, samplesPerPixel, bandOffsets, null);
            BufferedImage image = new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
            ImageIO.write(image, "PNG", new File(filename + ".png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
