package com.jagex.runescape.scene;

public class TileUnderlay {

    public TileUnderlay(int southwestColor, int southeastColor, int northeastColor, int northwestColor, int textureIndex, int color, boolean isFlat) {
        this.southwestColor = southwestColor;
        this.southeastColor = southeastColor;
        this.northeastColor = northeastColor;
        this.northwestColor = northwestColor;
        this.textureIndex = textureIndex;
        this.color = color;
        this.isFlat = isFlat;
    }

    public int southwestColor;
    public int southeastColor;
    public int northeastColor;
    public int northwestColor;
    public int textureIndex;
    public boolean isFlat;
    public int color;
}
