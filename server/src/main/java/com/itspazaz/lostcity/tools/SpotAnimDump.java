package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.config.SeqType;
import com.jagex.game.runetek3.config.SpotAnimType;
import com.jagex.game.runetek3.graphics.seq.SeqBase;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpotAnimDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive config225 = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
            SpotAnimType.decode(config225);

            try {
                PrintWriter out = new PrintWriter("dump/spotanim.def");

                for (int i = 0; i < SpotAnimType.count; ++i) {
                    out.println(SpotAnimType.instances[i].toJagConfig());
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
