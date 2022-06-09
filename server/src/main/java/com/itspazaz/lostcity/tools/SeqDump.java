package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.config.FloType;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.graphics.seq.SeqBase;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SeqDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive models = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "models")));
            SeqBase.decode(models);
            SeqFrame.decode(models);

            FileArchive config225 = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
            SeqType.decode(config225);

            try {
                PrintWriter out = new PrintWriter("dump/seq.def");

                for (int i = 0; i < SeqType.count; ++i) {
                    out.println(SeqType.instances[i].toJagConfig());
                }

                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
