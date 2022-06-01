package com.itspazaz.lostcity.network;

import com.itspazaz.lostcity.world.Player;
import com.jagex.core.io.Buffer;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Connection {

    public SocketChannel channel;

    public SelectionKey key;

    public boolean connected = true;

    public Buffer in = Buffer.reserve(1);

    public Buffer out = Buffer.reserve(2);

    public int lowMemory;

    public int uid;

    public String username;

    public Player player = new Player(this);

}
