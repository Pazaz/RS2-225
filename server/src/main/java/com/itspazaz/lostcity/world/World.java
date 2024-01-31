package com.itspazaz.lostcity.world;

import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.utils.Position;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class World {
    public static final Map<SocketChannel, Connection> players = new HashMap<SocketChannel, Connection>();

    public static long tick = 0;

    private static final RegionSong[] SONGS = new RegionSong[] {
        new RegionSong(36, 52, "tree spirits"),
        new RegionSong(36, 53, "gnome village2"),
        new RegionSong(36, 54, "gnomeball"),
        new RegionSong(37, 49, "serenade"),
        new RegionSong(37, 51, "moody"),
        new RegionSong(37, 52, "tree spirits"),
        new RegionSong(37, 53, "gnome village2"),
        new RegionSong(37, 54, "gnomeball"),
        new RegionSong(37, 55, "gnomeball"),
        new RegionSong(38, 49, "serenade"),
        new RegionSong(38, 50, "expecting"),
        new RegionSong(38, 51, "moody"),
        new RegionSong(38, 52, "neverland"),
        new RegionSong(38, 53, "gnome village"),
        new RegionSong(38, 54, "gnome king"),
        new RegionSong(38, 55, "gnome king"),
        new RegionSong(39, 46, "soundscape"),
        new RegionSong(39, 47, "gaol"),
        new RegionSong(39, 48, "big chords"),
        new RegionSong(39, 49, "emotion"),
        new RegionSong(39, 50, "attack1"),
        new RegionSong(39, 51, "sad meadow"),
        new RegionSong(39, 52, "march2"),
        new RegionSong(39, 53, "waterfall"),
        new RegionSong(39, 54, "voyage"),
        new RegionSong(39, 55, "legion"),
        new RegionSong(40, 46, "grumpy"),
        new RegionSong(40, 47, "in the manor"),
        new RegionSong(40, 48, "magic dance"),
        new RegionSong(40, 49, "attack4"),
        new RegionSong(40, 50, "ballad of enchantment"),
        new RegionSong(40, 51, "knightly"),
        new RegionSong(40, 52, "the tower"),
        new RegionSong(40, 53, "mellow"),
        new RegionSong(40, 54, "theme"),
        new RegionSong(41, 46, "chompy hunt"),
        new RegionSong(41, 48, "long ago"),
        new RegionSong(41, 49, "fanfare3"),
        new RegionSong(41, 50, "upcoming"),
        new RegionSong(41, 51, "baroque"),
        new RegionSong(41, 52, "wonderous"),
        new RegionSong(41, 53, "lasting"),
        new RegionSong(41, 54, "talking forest"),
        new RegionSong(41, 55, "lullaby"),
        new RegionSong(42, 49, "landlubber"),
        new RegionSong(42, 50, "jungly2"),
        new RegionSong(42, 51, "riverside"),
        new RegionSong(42, 52, "trinity"),
        new RegionSong(42, 53, "magical journey"),
        new RegionSong(42, 54, "overture"),
        new RegionSong(42, 55, "monarch waltz"),
        new RegionSong(43, 45, "spooky jungle"),
        new RegionSong(43, 46, "jungly1"),
        new RegionSong(43, 47, "jungly3"),
        new RegionSong(43, 48, "nomad"),
        new RegionSong(43, 49, "high seas"),
        new RegionSong(43, 50, "jolly-r"),
        new RegionSong(43, 51, "fishing"),
        new RegionSong(43, 52, "background2"),
        new RegionSong(43, 53, "lightwalk"),
        new RegionSong(43, 54, "camelot"),
        new RegionSong(43, 55, "camelot"),
        new RegionSong(44, 45, "jungle island"),
        new RegionSong(44, 46, "ambient jungle"),
        new RegionSong(44, 47, "tribal"),
        new RegionSong(44, 48, "tribal background"),
        new RegionSong(44, 49, "jungle island"),
        new RegionSong(44, 50, "the shadow"),
        new RegionSong(44, 51, "the shadow"),
        new RegionSong(44, 52, "background2"),
        new RegionSong(44, 53, "fishing"),
        new RegionSong(44, 54, "ice melody"),
        new RegionSong(44, 55, "ice melody"),
        new RegionSong(45, 45, "reggae"),
        new RegionSong(45, 46, "tribal2"),
        new RegionSong(45, 47, "reggae2"),
        new RegionSong(45, 48, "spooky jungle"),
        new RegionSong(45, 49, "sea shanty"),
        new RegionSong(45, 50, "emperor"),
        new RegionSong(45, 51, "miles away"),
        new RegionSong(45, 52, "arrival"),
        new RegionSong(45, 53, "horizon"),
        new RegionSong(45, 54, "splendour"),
        new RegionSong(45, 55, "splendour"),
        new RegionSong(46, 45, "reggae"),
        new RegionSong(46, 46, "tribal2"),
        new RegionSong(46, 47, "fanfare2"),
        new RegionSong(46, 49, "attention"),
        new RegionSong(46, 50, "long way home"),
        new RegionSong(46, 51, "nightfall"),
        new RegionSong(46, 52, "fanfare"),
        new RegionSong(46, 53, "scape soft"),
        new RegionSong(46, 54, "gnome"),
        new RegionSong(46, 55, "wonder"),
        new RegionSong(46, 56, "lightness"),
        new RegionSong(46, 57, "troubled"),
        new RegionSong(46, 58, "wilderness4"),
        new RegionSong(46, 59, "deep wildy"),
        new RegionSong(46, 60, "sad meadow"),
        new RegionSong(46, 61, "serene"),
        new RegionSong(47, 47, "newbie melody"),
        new RegionSong(47, 48, "newbie melody"),
        new RegionSong(47, 49, "tomorrow"),
        new RegionSong(47, 50, "sea shanty2"),
        new RegionSong(47, 51, "wander"),
        new RegionSong(47, 52, "workshop"),
        new RegionSong(47, 53, "gnome theme"),
        new RegionSong(47, 54, "alone"),
        new RegionSong(47, 55, "inspiration"),
        new RegionSong(47, 56, "army of darkness"),
        new RegionSong(47, 57, "legion"),
        new RegionSong(47, 58, "gaol"),
        new RegionSong(47, 59, "wilderness3"),
        new RegionSong(47, 60, "forever"),
        new RegionSong(47, 61, "book of spells"),
        new RegionSong(48, 47, "newbie melody"),
        new RegionSong(48, 48, "newbie melody"),
        new RegionSong(48, 49, "vision"),
        new RegionSong(48, 50, "unknown land"),
        new RegionSong(48, 51, "start"),
        new RegionSong(48, 52, "spooky2"),
        new RegionSong(48, 53, "dark2"),
        new RegionSong(48, 54, "forever"),
        new RegionSong(48, 55, "dangerous"),
        new RegionSong(48, 56, "deep wildy"),
        new RegionSong(48, 57, "undercurrent"),
        new RegionSong(48, 58, "wilderness2"),
        new RegionSong(48, 59, "wilderness3"),
        new RegionSong(48, 60, "forever"),
        new RegionSong(48, 61, "mage arena"),
        new RegionSong(49, 47, "the desert"),
        new RegionSong(49, 48, "newbie melody"),
        new RegionSong(49, 49, "book of spells"),
        new RegionSong(49, 50, "dream1"),
        new RegionSong(49, 51, "flute salad"),
        new RegionSong(49, 52, "greatness"),
        new RegionSong(49, 53, "spirit"),
        new RegionSong(49, 54, "doorways"),
        new RegionSong(49, 55, "lightness"),
        new RegionSong(49, 56, "moody"),
        new RegionSong(49, 57, "wilderness3"),
        new RegionSong(49, 58, "close quarters"),
        new RegionSong(49, 59, "wolf mountain"),
        new RegionSong(49, 60, "scape wild1"),
        new RegionSong(49, 61, "expanse"),
        new RegionSong(50, 47, "the desert"),
        new RegionSong(50, 48, "arabian3"),
        new RegionSong(50, 49, "yesteryear"),
        new RegionSong(50, 50, "harmony"),
        new RegionSong(50, 51, "autumn voyage"),
        new RegionSong(50, 52, "expanse"),
        new RegionSong(50, 53, "garden"),
        new RegionSong(50, 54, "adventure"),
        new RegionSong(50, 55, "crystal sword"),
        new RegionSong(50, 56, "underground"),
        new RegionSong(50, 57, "scape wild1"),
        new RegionSong(50, 58, "shining"),
        new RegionSong(50, 59, "wolf mountain"),
        new RegionSong(50, 60, "scape wild1"),
        new RegionSong(50, 61, "nightfall"),
        new RegionSong(51, 46, "desert voyage"),
        new RegionSong(51, 47, "desert voyage"),
        new RegionSong(51, 48, "egypt"),
        new RegionSong(51, 49, "al kharid"),
        new RegionSong(51, 50, "arabian"),
        new RegionSong(51, 51, "arabian2"),
        new RegionSong(51, 52, "still night"),
        new RegionSong(51, 53, "medieval"),
        new RegionSong(51, 54, "parade"),
        new RegionSong(51, 55, "forbidden"),
        new RegionSong(51, 56, "underground"),
        new RegionSong(51, 57, "dark2"),
        new RegionSong(51, 58, "witching"),
        new RegionSong(51, 59, "dangerous"),
        new RegionSong(51, 60, "scape sad1"),
        new RegionSong(51, 61, "regal2"),
        new RegionSong(52, 47, "desert voyage"),
        new RegionSong(52, 48, "egypt"),
        new RegionSong(52, 49, "al kharid"),
        new RegionSong(52, 50, "duel arena"),
        new RegionSong(52, 51, "shine"),
        new RegionSong(52, 52, "venture"),
        new RegionSong(52, 53, "lullaby"),
        new RegionSong(52, 54, "parade"),
        new RegionSong(52, 55, "forbidden"),
        new RegionSong(52, 56, "underground"),
        new RegionSong(52, 57, "dark2"),
        new RegionSong(52, 58, "witching"),
        new RegionSong(52, 59, "dangerous"),
        new RegionSong(52, 60, "scape sad1"),
        new RegionSong(52, 61, "regal2"),
        new RegionSong(29, 75, "trawler"),
        new RegionSong(30, 75, "trawler minor"),
        new RegionSong(31, 75, "trawler minor"),
        new RegionSong(33, 71, "iban"),
        new RegionSong(33, 72, "iban"),
        new RegionSong(33, 73, "iban"),
        new RegionSong(33, 75, "quest"),
        new RegionSong(34, 75, "quest"),
        new RegionSong(37, 73, "voodoo cult"),
        new RegionSong(37, 75, "understanding"),
        new RegionSong(39, 75, "heart and mind"),
        new RegionSong(40, 75, "quest"),
        new RegionSong(41, 73, "miles away"),
        new RegionSong(41, 75, "quest"),
        new RegionSong(42, 75, "zealot"),
        new RegionSong(43, 73, "emotion"),
        new RegionSong(43, 75, "miracle dance"),
        new RegionSong(44, 75, "serene"),
        new RegionSong(45, 75, "rune essence"),
        new RegionSong(36, 153, "intrepid"),
        new RegionSong(36, 154, "intrepid"),
        new RegionSong(37, 147, "expedition"),
        new RegionSong(37, 149, "upass1"),
        new RegionSong(37, 150, "upass1"),
        new RegionSong(37, 151, "cursed"),
        new RegionSong(38, 150, "expecting"),
        new RegionSong(38, 151, "cursed"),
        new RegionSong(39, 147, "gaol"),
        new RegionSong(39, 150, "scape sad1"),
        new RegionSong(39, 153, "waterfall"),
        new RegionSong(39, 155, "legion"),
        new RegionSong(40, 147, "attack6"),
        new RegionSong(40, 148, "cavern"),
        new RegionSong(40, 149, "attack4"),
        new RegionSong(40, 150, "alone"),
        new RegionSong(40, 151, "scape sad1"),
        new RegionSong(40, 154, "theme"),
        new RegionSong(41, 146, "chompy hunt"),
        new RegionSong(41, 152, "chain of command"),
        new RegionSong(41, 153, "chain of command"),
        new RegionSong(41, 154, "chain of command"),
        new RegionSong(42, 151, "escape"),
        new RegionSong(42, 152, "trinity"),
        new RegionSong(42, 153, "attack5"),
        new RegionSong(43, 145, "voodoo cult"),
        new RegionSong(43, 146, "jungly1"),
        new RegionSong(43, 153, "arabique"),
        new RegionSong(44, 148, "tribal background"),
        new RegionSong(44, 149, "attack2"),
        new RegionSong(44, 150, "attack2"),
        new RegionSong(44, 152, "underground"),
        new RegionSong(44, 153, "arabique"),
        new RegionSong(44, 154, "beyond"),
        new RegionSong(44, 155, "beyond"),
        new RegionSong(45, 145, "beyond"),
        new RegionSong(45, 146, "oriental"),
        new RegionSong(45, 148, "spooky jungle"),
        new RegionSong(45, 151, "royale"),
        new RegionSong(45, 152, "dunjun"),
        new RegionSong(45, 153, "arabique"),
        new RegionSong(45, 154, "arabique"),
        new RegionSong(46, 149, "starlight"),
        new RegionSong(46, 152, "dunjun"),
        new RegionSong(46, 153, "cave background1"),
        new RegionSong(47, 149, "starlight"),
        new RegionSong(47, 152, "cave background1"),
        new RegionSong(47, 153, "cave background1"),
        new RegionSong(47, 160, "attack3"),
        new RegionSong(47, 161, "cavern"),
        new RegionSong(48, 148, "scape cave"),
        new RegionSong(48, 149, "vision"),
        new RegionSong(48, 153, "dark2"),
        new RegionSong(48, 154, "forever"),
        new RegionSong(48, 155, "forever"),
        new RegionSong(48, 156, "forever"),
        new RegionSong(49, 148, "faerie"),
        new RegionSong(49, 149, "faerie"),
        new RegionSong(49, 153, "cellar song1"),
        new RegionSong(49, 154, "scape cave"),
        new RegionSong(50, 149, "crystal cave"),
        new RegionSong(50, 150, "harmony2"),
        new RegionSong(50, 152, "garden"),
        new RegionSong(50, 153, "garden"),
        new RegionSong(50, 154, "scape cave"),
        new RegionSong(51, 147, "lonesome"),
        new RegionSong(51, 154, "scape cave"),
        new RegionSong(52, 152, "venture2"),
        new RegionSong(52, 153, "venture2")
    };

    public static void tick() {
        System.out.println("Tick " + tick + " (" + players.size() + " players)");
        for (Connection con : players.values()) {
            try {
                if (!con.connected) {
                    con.player.save();
                    players.remove(con.channel);
                    continue;
                }

                con.out.pos = 0;
                con.in.pos = 0;

                if (Math.abs(con.player.lastLoadedX - con.player.entity.x) >= 36 || Math.abs(con.player.lastLoadedZ - con.player.entity.z) >= 36) {
                    con.player.loaded = false;
                    con.player.loading = false;
                }

                if (!con.player.loaded && !con.player.loading) {
                    con.player.sendLoadArea();
                }

                con.player.sendPlayerInfo();

                if (Position.file(con.player.entity.x) != con.player.lastSongX || Position.file(con.player.entity.z) != con.player.lastSongZ) {
                    // moved into a new region, new song!
                    int fileX = Position.file(con.player.entity.x);
                    int fileZ = Position.file(con.player.entity.z);
                    for (RegionSong song : SONGS) {
                        if (song.x == fileX && song.z == fileZ) {
                            con.player.playMidi(song.name);
                            break;
                        }
                    }
                    con.player.lastSongX = fileX;
                    con.player.lastSongZ = fileZ;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        tick++;
    }
}