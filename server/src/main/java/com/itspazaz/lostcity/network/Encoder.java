package com.itspazaz.lostcity.network;

import com.jagex.core.io.Buffer;

public abstract class Encoder {

    public abstract boolean execute(Buffer data, Message message);

}
