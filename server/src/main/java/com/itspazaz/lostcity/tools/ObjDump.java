package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.config.ObjType;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive config225 = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "config")));
            ObjType.decode(config225);

            try {
                PrintWriter out = new PrintWriter("dump/obj.def");

                for (int i = 0; i < ObjType.count; ++i) {
                    ObjType obj = ObjType.get(i);
//                    if (obj.certlink != -1 && obj.certlink != i - 1) {
//                        System.out.println(obj.id + " " + obj.name + " " + obj.certlink);
//                    }
                    out.println(obj.toJagConfig());
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
