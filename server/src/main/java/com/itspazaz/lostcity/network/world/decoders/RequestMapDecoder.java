package com.itspazaz.lostcity.network.world.decoders;

import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.network.Decoder;
import com.jagex.core.io.Buffer;
import rs2.ClientProt;

public class RequestMapDecoder extends Decoder {

    @Override
    public void execute(Connection con, Buffer data) {
        if (data.length == 0) {
            con.player.loading = false;
            con.player.loaded = true;
            return;
        }

        con.player.loading = true;
        con.player.loaded = false;

        int requested = data.length / 3;
        for (int i = 0; i < requested; ++i) {
            int type = data.g1();
            int x = data.g1();
            int z = data.g1();

            try {
                if (type == 0) {
                    con.player.sendLandFile(x, z);
                } else {
                    con.player.sendLocFile(x, z);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        con.player.loading = false;
        con.player.loaded = true;
    }

}
