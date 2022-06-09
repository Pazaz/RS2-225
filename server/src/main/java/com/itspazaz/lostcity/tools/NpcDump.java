package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.config.NpcType;
import com.jagex.game.runetek3.config.ObjType;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NpcDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive config225 = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
            NpcType.decode(config225);

            try {
                PrintWriter out = new PrintWriter("dump/npc.def");

                for (int i = 0; i < NpcType.count; ++i) {
                    NpcType npc = NpcType.get(i);
                    out.println(npc.toJagConfig());
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
