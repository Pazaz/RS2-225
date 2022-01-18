package com.runescape.scene;

public class TileOverlay {

    public TileOverlay(int tileX, int shape, int southeastColor2, int southeastY, int northeastColor1, int rotation, int southwestColor1,
                       int northwestY, int overlayRGB, int southwestColor2, int triangleIndex, int northwestColor2, int underlayRGB, int northeastY,
                       int northeastColor2, int northwestColor1, int southwestY, int tileZ, int southeastColor1) {
        isFlat = southwestY == southeastY && southwestY == northeastY && southwestY == northwestY;

        this.shape = shape;
        this.rotation = rotation;
        this.underlayRGB = underlayRGB;
        this.overlayRGB = overlayRGB;

        int tileSize = 128;
        int halfSize = tileSize / 2;
        int quarterSize = tileSize / 4;
        int threeQuarterSize = (tileSize * 3) / 4;

        int[] vertices = SHAPE_VERTICES[shape];
        int vertexCount = vertices.length;

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];

        int[] colors1 = new int[vertexCount];
        int[] colors2 = new int[vertexCount];

        int sceneX = tileX * tileSize;
        int sceneZ = tileZ * tileSize;

        for (int v = 0; v < vertexCount; v++) {
            int vertex = vertices[v];
            if ((vertex & 1) == 0 && vertex <= 8) {
                vertex = (vertex - rotation - rotation - 1 & 7) + 1;
            }

            if (vertex > 8 && vertex <= 12) {
                vertex = (vertex - 9 - rotation & 3) + 9;
            }

            if (vertex > 12 && vertex <= 16) {
                vertex = (vertex - 13 - rotation & 3) + 13;
            }

            int x;
            int y;
            int z;
            int color1;
            int color2;

            if (vertex == 1) {
                x = sceneX;
                z = sceneZ;
                y = southwestY;
                color1 = southwestColor1;
                color2 = southwestColor2;
            } else if (vertex == 2) {
                x = sceneX + halfSize;
                z = sceneZ;
                y = southwestY + southeastY >> 1;
                color1 = southwestColor1 + southeastColor1 >> 1;
                color2 = southwestColor2 + southeastColor2 >> 1;
            } else if (vertex == 3) {
                x = sceneX + tileSize;
                z = sceneZ;
                y = southeastY;
                color1 = southeastColor1;
                color2 = southeastColor2;
            } else if (vertex == 4) {
                x = sceneX + tileSize;
                z = sceneZ + halfSize;
                y = southeastY + northeastY >> 1;
                color1 = southeastColor1 + northeastColor1 >> 1;
                color2 = southeastColor2 + northeastColor2 >> 1;
            } else if (vertex == 5) {
                x = sceneX + tileSize;
                z = sceneZ + tileSize;
                y = northeastY;
                color1 = northeastColor1;
                color2 = northeastColor2;
            } else if (vertex == 6) {
                x = sceneX + halfSize;
                z = sceneZ + tileSize;
                y = northeastY + northwestY >> 1;
                color1 = northeastColor1 + northwestColor1 >> 1;
                color2 = northeastColor2 + northwestColor2 >> 1;
            } else if (vertex == 7) {
                x = sceneX;
                z = sceneZ + tileSize;
                y = northwestY;
                color1 = northwestColor1;
                color2 = northwestColor2;
            } else if (vertex == 8) {
                x = sceneX;
                z = sceneZ + halfSize;
                y = northwestY + southwestY >> 1;
                color1 = northwestColor1 + southwestColor1 >> 1;
                color2 = northwestColor2 + southwestColor2 >> 1;
            } else if (vertex == 9) {
                x = sceneX + halfSize;
                z = sceneZ + quarterSize;
                y = southwestY + southeastY >> 1;
                color1 = southwestColor1 + southeastColor1 >> 1;
                color2 = southwestColor2 + southeastColor2 >> 1;
            } else if (vertex == 10) {
                x = sceneX + threeQuarterSize;
                z = sceneZ + halfSize;
                y = southeastY + northeastY >> 1;
                color1 = southeastColor1 + northeastColor1 >> 1;
                color2 = southeastColor2 + northeastColor2 >> 1;
            } else if (vertex == 11) {
                x = sceneX + halfSize;
                z = sceneZ + threeQuarterSize;
                y = northeastY + northwestY >> 1;
                color1 = northeastColor1 + northwestColor1 >> 1;
                color2 = northeastColor2 + northwestColor2 >> 1;
            } else if (vertex == 12) {
                x = sceneX + quarterSize;
                z = sceneZ + halfSize;
                y = northwestY + southwestY >> 1;
                color1 = northwestColor1 + southwestColor1 >> 1;
                color2 = northwestColor2 + southwestColor2 >> 1;
            } else if (vertex == 13) {
                x = sceneX + quarterSize;
                z = sceneZ + quarterSize;
                y = southwestY;
                color1 = southwestColor1;
                color2 = southwestColor2;
            } else if (vertex == 14) {
                x = sceneX + threeQuarterSize;
                z = sceneZ + quarterSize;
                y = southeastY;
                color1 = southeastColor1;
                color2 = southeastColor2;
            } else if (vertex == 15) {
                x = sceneX + threeQuarterSize;
                z = sceneZ + threeQuarterSize;
                y = northeastY;
                color1 = northeastColor1;
                color2 = northeastColor2;
            } else {
                x = sceneX + quarterSize;
                z = sceneZ + threeQuarterSize;
                y = northwestY;
                color1 = northwestColor1;
                color2 = northwestColor2;
            }

            vertexX[v] = x;
            vertexY[v] = y;
            vertexZ[v] = z;

            colors1[v] = color1;
            colors2[v] = color2;
        }

        int[] paths = SHAPE_PATHS[shape];
        int triangleCount = paths.length / 4;

        triangleVertexA = new int[triangleCount];
        triangleVertexB = new int[triangleCount];
        triangleVertexC = new int[triangleCount];

        triangleColorA = new int[triangleCount];
        triangleColorB = new int[triangleCount];
        triangleColorC = new int[triangleCount];

        if (triangleIndex != -1) {
            triangleTextureIndex = new int[triangleCount];
        }

        int i = 0;
        for (int n = 0; n < triangleCount; n++) {
            int path = paths[i];
            int a = paths[i + 1];
            int b = paths[i + 2];
            int c = paths[i + 3];

            i += 4;

            if (a < 4) {
                a = a - rotation & 3;
            }

            if (b < 4) {
                b = b - rotation & 3;
            }

            if (c < 4) {
                c = c - rotation & 3;
            }

            triangleVertexA[n] = a;
            triangleVertexB[n] = b;
            triangleVertexC[n] = c;

            if (path == 0) {
                triangleColorA[n] = colors1[a];
                triangleColorB[n] = colors1[b];
                triangleColorC[n] = colors1[c];
                if (triangleTextureIndex != null) {
                    triangleTextureIndex[n] = -1;
                }
            } else {
                triangleColorA[n] = colors2[a];
                triangleColorB[n] = colors2[b];
                triangleColorC[n] = colors2[c];
                if (triangleTextureIndex != null) {
                    triangleTextureIndex[n] = triangleIndex;
                }
            }
        }
    }

    public int[] vertexX;
    public int[] vertexY;
    public int[] vertexZ;

    public int[] triangleColorA;
    public int[] triangleColorB;
    public int[] triangleColorC;

    public int[] triangleVertexA;
    public int[] triangleVertexB;
    public int[] triangleVertexC;

    public int[] triangleTextureIndex;

    public boolean isFlat;

    public int shape;
    public int rotation;

    public int underlayRGB;
    public int overlayRGB;

    public static int[] tmpScreenX = new int[6];
    public static int[] tmpScreenY = new int[6];

    public static int[] vertexSceneX = new int[6];
    public static int[] vertexSceneY = new int[6];
    public static int[] vertexSceneZ = new int[6];

    public static final int[][] SHAPE_VERTICES = {
        {1, 3, 5, 7},
        {1, 3, 5, 7},
        {1, 3, 5, 7},
        {1, 3, 5, 7, 6},
        {1, 3, 5, 7, 6},
        {1, 3, 5, 7, 6},
        {1, 3, 5, 7, 6},
        {1, 3, 5, 7, 2, 6},
        {1, 3, 5, 7, 2, 8},
        {1, 3, 5, 7, 2, 8},
        {1, 3, 5, 7, 11, 12},
        {1, 3, 5, 7, 11, 12},
        {1, 3, 5, 7, 13, 14}
    };

    public static final int[][] SHAPE_PATHS = {
        {0, 1, 2, 3, 0, 0, 1, 3},
        {1, 1, 2, 3, 1, 0, 1, 3},
        {0, 1, 2, 3, 1, 0, 1, 3},
        {0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3},
        {0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4},
        {0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4},
        {0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3},
        {0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3},
        {0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5},
        {0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5},
        {0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3},
        {1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3},
        {1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5}
    };
}
