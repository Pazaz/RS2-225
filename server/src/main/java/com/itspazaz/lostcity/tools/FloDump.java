package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.config.FloType;
import com.jagex.game.runetek3.config.NpcType;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FloDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive config225 = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
            FloType.decode(config225);

            try {
                PrintWriter out = new PrintWriter("dump/flo.def");

                for (int i = 0; i < FloType.count; ++i) {
                    out.println(FloType.instances[i].toJagConfig());
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
