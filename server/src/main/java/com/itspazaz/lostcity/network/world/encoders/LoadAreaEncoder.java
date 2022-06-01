package com.itspazaz.lostcity.network.world.encoders;

import com.itspazaz.lostcity.network.Encoder;
import com.itspazaz.lostcity.network.Message;
import com.itspazaz.lostcity.network.world.serverprot.LoadAreaMessage;
import com.jagex.core.io.Buffer;
import rs2.ServerProt;

public class LoadAreaEncoder extends Encoder {

    @Override
    public boolean execute(Buffer data, Message message) {
        LoadAreaMessage msg = (LoadAreaMessage) message;

        data.p1(ServerProt.LOAD_AREA);
        data.p2(0);
        int start = data.pos;

        data.p2(msg.baseX);
        data.p2(msg.baseZ);

        for (int i = 0; i < msg.areas.length; ++i) {
            if (msg.areas[i] == null) {
                continue;
            }

            data.p1(msg.areas[i].x);
            data.p1(msg.areas[i].z);
            data.p4(msg.areas[i].landCrc);
            data.p4(msg.areas[i].locCrc);
        }

        data.psize2(data.pos - start);
        return true;
    }

}
