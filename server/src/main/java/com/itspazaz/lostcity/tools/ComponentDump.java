package com.itspazaz.lostcity.tools;

import com.jagex.core.io.FileArchive;
import com.jagex.game.runetek3.graphics.ui.Component;
import com.jagex.game.runetek3.graphics.ui.Font;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComponentDump {
    public static Path dataDir = Paths.get(System.getProperty("user.dir") + "/data");
    public static Path cacheDir = Paths.get(System.getProperty("user.dir") + "/data/cache");

    public static void main(String[] args) {
        try {
            FileArchive interfaces = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "interface")));
            FileArchive title = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "title")));
            Font plain11 = new Font(title, "p11");
            Font plain12 = new Font(title, "p12");
            Font bold12 = new Font(title, "b12");
            Font quill8 = new Font(title, "q8");
            Font[] fonts = new Font[]{plain11, plain12, bold12, quill8};
            FileArchive media = new FileArchive(Files.readAllBytes(Paths.get(cacheDir.toString(), "media")));
            Component.decode(media, fonts, interfaces);

            // read ahead to generate a localized ID range
//            try {
//                int widget = 0;
//                for (int i = 0; i < Component.count; ++i) {
//                    Component parent = Component.instances[i];
//                    if (parent == null || parent.id != parent.parent) {
//                        continue;
//                    }
//                    parent.isWidget = true;
//                    parent.generateNewIds(widget++);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }

            // now dump using the new ID range
            try {
                int widget = 0;

                for (int i = 0; i < Component.count; ++i) {
                    Component parent = Component.instances[i];
                    // get top-level widgets
                    if (parent == null || parent.id != parent.parent) { // || !parent.isWidget) {
                        continue;
                    }

                    PrintWriter out = new PrintWriter("dump/ifs/" + i + ".if");
                    out.print(parent.toJagConfig(widget));
                    out.close();

                    widget++;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
