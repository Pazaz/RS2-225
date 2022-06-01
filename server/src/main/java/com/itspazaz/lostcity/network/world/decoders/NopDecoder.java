package com.itspazaz.lostcity.network.world.decoders;

import com.itspazaz.lostcity.network.Connection;
import com.itspazaz.lostcity.network.Decoder;
import com.jagex.core.io.Buffer;

public class NopDecoder extends Decoder {

    @Override
    public void execute(Connection con, Buffer data) {
    }

}
