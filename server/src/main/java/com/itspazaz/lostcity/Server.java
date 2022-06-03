package com.itspazaz.lostcity;

import com.itspazaz.lostcity.cachedelivery.FileServer;
import com.itspazaz.lostcity.network.NioServer;
import com.itspazaz.lostcity.world.World;
import com.jagex.core.io.FileArchive;
import com.jagex.core.stringutils.StringUtils;
import com.jagex.core.stringutils.WordPack;
import com.jagex.game.runetek3.config.*;
import com.jagex.game.runetek3.graphics.model.Model;
import com.jagex.game.runetek3.graphics.seq.SeqBase;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;
import com.jagex.game.runetek3.graphics.ui.Component;
import com.jagex.game.runetek3.graphics.ui.Font;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server implements Runnable {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");
    public static Path songDir = Paths.get(System.getProperty("user.dir") + "/data/songs");

    private static final FileServer fileServer = new FileServer();

    public static final BigInteger RSA_EXPONENT = new BigInteger("4563042879983685819415859508309337987464904274730456483940553788384065737798175536144539635545496149193181089921240252410947054964044522362195913220892133");
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

    public static int httpPort = 80;
    public static boolean ready = true;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("client")) {
            Server.ready = false;
        }

        Thread server = new Thread(new Server());
        server.start();
    }

    @Override
    public void run() {
        try {
            // start delivering the cache ASAP
            fileServer.start();

            if (ready) {
                System.out.println("Loading cache");

                FileArchive models = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "models")));
                Model.decode(models);
                SeqBase.decode(models);
                SeqFrame.decode(models);

                FileArchive config = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
                SeqType.decode(config);
                LocType.decode(config);
                FloType.decode(config);
                ObjType.decode(config);
                NpcType.decode(config);
                IdkType.decode(config);
                SpotAnimType.decode(config);
                VarpType.decode(config);

                FileArchive interfaces = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "interface")));
                FileArchive title = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "title")));
                Font plain11 = new Font(title, "p11");
                Font plain12 = new Font(title, "p12");
                Font bold12 = new Font(title, "b12");
                Font quill8 = new Font(title, "q8");
                Font[] fonts = new Font[]{plain11, plain12, bold12, quill8};
                FileArchive media = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "media")));
                Component.decode(media, fonts, interfaces);

                FileArchive wordenc = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "wordenc")));
                WordPack.decode(wordenc);

                System.out.println("Cache ready to use");
            } else {
                System.out.println("Deferring cache loading to client");

                while (!Server.ready) {
                    Thread.sleep(100);
                }

                System.out.println("Cache ready to use");
            }

            Thread gameServer = new Thread(new NioServer());
            gameServer.start();

            long lastUpdate = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    long current = System.currentTimeMillis();
                    if (current - lastUpdate >= 600) {
                        World.tick();
                        lastUpdate = current;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    Thread.sleep(1);
                } catch (Exception ignored) {}
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileServer.stop();
        }
    }
}
