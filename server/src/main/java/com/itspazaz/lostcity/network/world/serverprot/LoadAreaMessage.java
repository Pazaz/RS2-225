package com.itspazaz.lostcity.network.world.serverprot;

import com.itspazaz.lostcity.network.Message;

public class LoadAreaMessage extends Message {

    public static class Area {
        public int x;
        public int z;
        public int landCrc;
        public int locCrc;
    }

    public int baseX;
    public int baseZ;

    public Area[] areas;

}
