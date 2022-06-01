package com.itspazaz.lostcity.network.world.decoders;

import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.network.Decoder;
import com.jagex.core.io.Buffer;
import com.jagex.core.stringutils.TextEncoder;

public class MessagePublicDecoder extends Decoder {

    @Override
    public void execute(Connection con, Buffer data) {
        String spoken = TextEncoder.read(data, data.length).trim();
        System.out.println(con.username + ": " + spoken);
    }

}
