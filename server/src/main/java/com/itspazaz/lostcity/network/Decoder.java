package com.itspazaz.lostcity.network;

import com.jagex.core.io.Buffer;

public abstract class Decoder {

    public abstract void execute(Connection con, Buffer data);

}
