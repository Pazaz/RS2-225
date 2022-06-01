package com.itspazaz.lostcity.network.world.decoders;

import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.network.Decoder;
import com.jagex.core.io.Buffer;

public class InterfaceButtonDecoder extends Decoder {

    @Override
    public void execute(Connection con, Buffer data) {
        int id = data.g2();

        if (id == 2458) {
            con.player.logout();
        } else {
            con.player.sendGameMessage("if_button[id=" + id + "]");
        }
    }

}
