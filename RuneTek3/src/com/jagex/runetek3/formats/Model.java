package com.jagex.runetek3.formats;

import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.CacheableNode;
import com.jagex.runetek3.graphics.Draw2D;
import com.jagex.runetek3.graphics.Draw3D;
import com.jagex.runetek3.scene.VertexNormal;

public class Model extends CacheableNode {

    public static void unload() {
        metadata = null;
        obhead = null;
        obface1 = null;
        obface2 = null;
        obface3 = null;
        obface4 = null;
        obface5 = null;
        obpoint1 = null;
        obpoint2 = null;
        obpoint3 = null;
        obpoint4 = null;
        obpoint5 = null;
        obvertex1 = null;
        obvertex2 = null;
        obaxis = null;
        testTriangleX = new boolean[4096];
        projectTriangle = new boolean[4096];
        vertexScreenX = new int[4096];
        vertexScreenY = new int[4096];
        vertexDepth = new int[4096];
        projectSceneX = new int[4096];
        projectSceneY = new int[4096];
        projectSceneZ = new int[4096];
        depthTriangleCount = new int[1500];
        depthTriangles = new int[1500][512];
        priorityTriangleCounts = new int[12];
        priorityTriangles = new int[12][2000];
        normalTrianglePriority = new int[2000];
        highTrianglePriority = new int[2000];
        lowTrianglePriority = new int[12];
    }

    public static void load(FileArchive fileArchive) {
        sin = Draw3D.sin;
        cos = Draw3D.cos;
        palette = Draw3D.palette;
        oneOverFixed1616 = Draw3D.reciprical16;

        try {
            obhead = new Buffer(fileArchive.read("ob_head.dat", null));
            obface1 = new Buffer(fileArchive.read("ob_face1.dat", null));
            obface2 = new Buffer(fileArchive.read("ob_face2.dat", null));
            obface3 = new Buffer(fileArchive.read("ob_face3.dat", null));
            obface4 = new Buffer(fileArchive.read("ob_face4.dat", null));
            obface5 = new Buffer(fileArchive.read("ob_face5.dat", null));
            obpoint1 = new Buffer(fileArchive.read("ob_point1.dat", null));
            obpoint2 = new Buffer(fileArchive.read("ob_point2.dat", null));
            obpoint3 = new Buffer(fileArchive.read("ob_point3.dat", null));
            obpoint4 = new Buffer(fileArchive.read("ob_point4.dat", null));
            obpoint5 = new Buffer(fileArchive.read("ob_point5.dat", null));
            obvertex1 = new Buffer(fileArchive.read("ob_vertex1.dat", null));
            obvertex2 = new Buffer(fileArchive.read("ob_vertex2.dat", null));
            obaxis = new Buffer(fileArchive.read("ob_axis.dat", null));

            obhead.offset = 0;
            obpoint1.offset = 0;
            obpoint2.offset = 0;
            obpoint3.offset = 0;
            obpoint4.offset = 0;
            obvertex1.offset = 0;
            obvertex2.offset = 0;

            int count = obhead.readWord();
            metadata = new Metadata[count + 100];

            int vertexTextureDataOffset = 0;
            int labelDataOffset = 0;
            int triangleColorDataOffset = 0;
            int triangleInfoDataOffset = 0;
            int trianglePriorityDataOffset = 0;
            int triangleAlphaDataOffset = 0;
            int triangleSkinDataOffset = 0;

            for (int n = 0; n < count; n++) {
                int index = obhead.readWord();
                Metadata meta = Model.metadata[index] = new Metadata();

                meta.vertexCount = obhead.readWord();
                meta.triangleCount = obhead.readWord();
                meta.texturedCount = obhead.readByte();

                meta.vertexFlagDataOffset = obpoint1.offset;
                meta.vertexXDataOffset = obpoint2.offset;
                meta.vertexYDataOffset = obpoint3.offset;
                meta.vertexZDataOffset = obpoint4.offset;
                meta.vertexIndexDataOffset = obvertex1.offset;
                meta.triangleTypeDataOffset = obvertex2.offset;

                int hasInfo = obhead.readByte();
                int hasPriorities = obhead.readByte();
                int hasAlpha = obhead.readByte();
                int hasSkins = obhead.readByte();
                int hasLabels = obhead.readByte();

                for (int v = 0; v < meta.vertexCount; v++) {
                    int flags = obpoint1.readByte();

                    if ((flags & 1) != 0) {
                        obpoint2.readSmartSigned();
                    }

                    if ((flags & 2) != 0) {
                        obpoint3.readSmartSigned();
                    }

                    if ((flags & 4) != 0) {
                        obpoint4.readSmartSigned();
                    }
                }

                for (int t = 0; t < meta.triangleCount; t++) {
                    int type = obvertex2.readByte();

                    if (type == 1) {
                        obvertex1.readSmartSigned();
                        obvertex1.readSmartSigned();
                    }

                    obvertex1.readSmartSigned();
                }

                meta.triangleColorDataOffset = triangleColorDataOffset;
                triangleColorDataOffset += meta.triangleCount * 2;

                if (hasInfo == 1) {
                    meta.triangleInfoDataOffset = triangleInfoDataOffset;
                    triangleInfoDataOffset += meta.triangleCount;
                } else {
                    meta.triangleInfoDataOffset = -1;
                }

                if (hasPriorities == 255) {
                    meta.trianglePriorityDataOffset = trianglePriorityDataOffset;
                    trianglePriorityDataOffset += meta.triangleCount;
                } else {
                    meta.trianglePriorityDataOffset = -hasPriorities - 1;
                }

                if (hasAlpha == 1) {
                    meta.triangleAlphaDataOffset = triangleAlphaDataOffset;
                    triangleAlphaDataOffset += meta.triangleCount;
                } else {
                    meta.triangleAlphaDataOffset = -1;
                }

                if (hasSkins == 1) {
                    meta.triangleSkinDataOffset = triangleSkinDataOffset;
                    triangleSkinDataOffset += meta.triangleCount;
                } else {
                    meta.triangleSkinDataOffset = -1;
                }

                if (hasLabels == 1) {
                    meta.vertexLabelDataOffset = labelDataOffset;
                    labelDataOffset += meta.vertexCount;
                } else {
                    meta.vertexLabelDataOffset = -1;
                }

                meta.triangleTextureDataOffset = vertexTextureDataOffset;
                vertexTextureDataOffset += meta.texturedCount;
            }
        } catch (Exception exception) {
            System.out.println("Error loading model index");
            exception.printStackTrace();
        }
    }

    public Model(int index) {
        pickable = false;

        if (metadata == null) {
            return;
        }

        Metadata meta = Model.metadata[index];
        if (meta == null) {
            System.out.println("Error model:" + index + " not found!");
            return;
        }

        vertexCount = meta.vertexCount;
        triangleCount = meta.triangleCount;
        texturedCount = meta.texturedCount;

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];

        triangleVertexA = new int[triangleCount];
        triangleVertexB = new int[triangleCount];
        triangleVertexC = new int[triangleCount];

        textureVertexA = new int[texturedCount];
        textureVertexB = new int[texturedCount];
        textureVertexC = new int[texturedCount];

        if (meta.vertexLabelDataOffset >= 0) {
            vertexLabel = new int[vertexCount];
        }

        if (meta.triangleInfoDataOffset >= 0) {
            triangleInfo = new int[triangleCount];
        }

        if (meta.trianglePriorityDataOffset >= 0) {
            trianglePriorities = new int[triangleCount];
        } else {
            priority = -meta.trianglePriorityDataOffset - 1;
        }

        if (meta.triangleAlphaDataOffset >= 0) {
            triangleAlpha = new int[triangleCount];
        }

        if (meta.triangleSkinDataOffset >= 0) {
            triangleSkin = new int[triangleCount];
        }

        unmodifiedTriangleColor = new int[triangleCount];

        obpoint1.offset = meta.vertexFlagDataOffset;
        obpoint2.offset = meta.vertexXDataOffset;
        obpoint3.offset = meta.vertexYDataOffset;
        obpoint4.offset = meta.vertexZDataOffset;
        obpoint5.offset = meta.vertexLabelDataOffset;

        int x = 0;
        int y = 0;
        int z = 0;

        for (int v = 0; v < vertexCount; v++) {
            int flags = obpoint1.readByte();

            int x0 = 0;
            if ((flags & 1) != 0) {
                x0 = obpoint2.readSmartSigned();
            }

            int y0 = 0;
            if ((flags & 2) != 0) {
                y0 = obpoint3.readSmartSigned();
            }

            int z0 = 0;
            if ((flags & 4) != 0) {
                z0 = obpoint4.readSmartSigned();
            }

            vertexX[v] = x + x0;
            vertexY[v] = y + y0;
            vertexZ[v] = z + z0;

            x = vertexX[v];
            y = vertexY[v];
            z = vertexZ[v];

            if (vertexLabel != null) {
                vertexLabel[v] = obpoint5.readByte();
            }
        }

        obface1.offset = meta.triangleColorDataOffset;
        obface2.offset = meta.triangleInfoDataOffset;
        obface3.offset = meta.trianglePriorityDataOffset;
        obface4.offset = meta.triangleAlphaDataOffset;
        obface5.offset = meta.triangleSkinDataOffset;

        for (int n = 0; n < triangleCount; n++) {
            unmodifiedTriangleColor[n] = obface1.readWord();

            if (triangleInfo != null) {
                triangleInfo[n] = obface2.readByte();
            }

            if (trianglePriorities != null) {
                trianglePriorities[n] = obface3.readByte();
            }

            if (triangleAlpha != null) {
                triangleAlpha[n] = obface4.readByte();
            }

            if (triangleSkin != null) {
                triangleSkin[n] = obface5.readByte();
            }
        }

        obvertex1.offset = meta.vertexIndexDataOffset;
        obvertex2.offset = meta.triangleTypeDataOffset;

        int a = 0;
        int b = 0;
        int c = 0;
        int last = 0;

        for (int n = 0; n < triangleCount; n++) {
            int type = obvertex2.readByte();

            if (type == 1) {
                a = obvertex1.readSmartSigned() + last;
                last = a;

                b = obvertex1.readSmartSigned() + last;
                last = b;

                c = obvertex1.readSmartSigned() + last;
                last = c;

                triangleVertexA[n] = a;
                triangleVertexB[n] = b;
                triangleVertexC[n] = c;
            } else if (type == 2) {
                b = c;
                c = obvertex1.readSmartSigned() + last;
                last = c;

                triangleVertexA[n] = a;
                triangleVertexB[n] = b;
                triangleVertexC[n] = c;
            } else if (type == 3) {
                a = c;
                c = obvertex1.readSmartSigned() + last;
                last = c;

                triangleVertexA[n] = a;
                triangleVertexB[n] = b;
                triangleVertexC[n] = c;
            } else if (type == 4) {
                int b0 = a;
                a = b;
                b = b0;
                c = obvertex1.readSmartSigned() + last;
                last = c;

                triangleVertexA[n] = a;
                triangleVertexB[n] = b;
                triangleVertexC[n] = c;
            }
        }

        obaxis.offset = meta.triangleTextureDataOffset * 6;

        for (int t = 0; t < texturedCount; t++) {
            textureVertexA[t] = obaxis.readWord();
            textureVertexB[t] = obaxis.readWord();
            textureVertexC[t] = obaxis.readWord();
        }
    }

    // difference: keep skins
    public Model(Model[] models, int count) {
        pickable = false;

        boolean keepInfo = false;
        boolean keepPriorities = false;
        boolean keepAlpha = false;
        boolean keepSkins = false;

        vertexCount = 0;
        triangleCount = 0;
        texturedCount = 0;
        priority = -1;

        for (int n = 0; n < count; n++) {
            Model model = models[n];
            if (model != null) {
                vertexCount += model.vertexCount;
                triangleCount += model.triangleCount;
                texturedCount += model.texturedCount;
                keepInfo |= model.triangleInfo != null;

                if (model.trianglePriorities != null) {
                    keepPriorities = true;
                } else {
                    if (priority == -1) {
                        priority = model.priority;
                    }

                    if (priority != model.priority) {
                        keepPriorities = true;
                    }
                }

                keepAlpha |= model.triangleAlpha != null;
                keepSkins |= model.triangleSkin != null;
            }
        }

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];
        vertexLabel = new int[vertexCount];
        triangleVertexA = new int[triangleCount];
        triangleVertexB = new int[triangleCount];
        triangleVertexC = new int[triangleCount];
        textureVertexA = new int[texturedCount];
        textureVertexB = new int[texturedCount];
        textureVertexC = new int[texturedCount];

        if (keepInfo) {
            triangleInfo = new int[triangleCount];
        }

        if (keepPriorities) {
            trianglePriorities = new int[triangleCount];
        }

        if (keepAlpha) {
            triangleAlpha = new int[triangleCount];
        }

        if (keepSkins) {
            triangleSkin = new int[triangleCount];
        }

        unmodifiedTriangleColor = new int[triangleCount];
        vertexCount = 0;
        triangleCount = 0;
        texturedCount = 0;

        for (int n = 0; n < count; n++) {
            Model model = models[n];
            if (model != null) {
                for (int t = 0; t < model.triangleCount; t++) {
                    if (keepInfo) {
                        if (model.triangleInfo == null) {
                            triangleInfo[triangleCount] = 0;
                        } else {
                            triangleInfo[triangleCount] = model.triangleInfo[t];
                        }
                    }

                    if (keepPriorities) {
                        if (model.trianglePriorities == null) {
                            trianglePriorities[triangleCount] = model.priority;
                        } else {
                            trianglePriorities[triangleCount] = model.trianglePriorities[t];
                        }
                    }

                    if (keepAlpha) {
                        if (model.triangleAlpha == null) {
                            triangleAlpha[triangleCount] = 0;
                        } else {
                            triangleAlpha[triangleCount] = model.triangleAlpha[t];
                        }
                    }

                    if (keepSkins && model.triangleSkin != null) {
                        triangleSkin[triangleCount] = model.triangleSkin[t];
                    }

                    unmodifiedTriangleColor[triangleCount] = model.unmodifiedTriangleColor[t];
                    triangleVertexA[triangleCount] = copyVertex(model, model.triangleVertexA[t]);
                    triangleVertexB[triangleCount] = copyVertex(model, model.triangleVertexB[t]);
                    triangleVertexC[triangleCount] = copyVertex(model, model.triangleVertexC[t]);
                    triangleCount++;
                }

                for (int v = 0; v < model.texturedCount; v++) {
                    textureVertexA[texturedCount] = copyVertex(model, model.textureVertexA[v]);
                    textureVertexB[texturedCount] = copyVertex(model, model.textureVertexB[v]);
                    textureVertexC[texturedCount] = copyVertex(model, model.textureVertexC[v]);
                    texturedCount++;
                }
            }
        }
    }

    // difference: keep color
    public Model(Model[] models, byte dummy0, int count) {
        pickable = false;

        boolean keepInfo = false;
        boolean keepPriorities = false;
        boolean keepAlpha = false;
        boolean keepColor = false;

        vertexCount = 0;
        triangleCount = 0;
        texturedCount = 0;
        priority = -1;

        for (int n = 0; n < count; n++) {
            Model model = models[n];
            if (model != null) {
                vertexCount += model.vertexCount;
                triangleCount += model.triangleCount;
                texturedCount += model.texturedCount;
                keepInfo |= model.triangleInfo != null;

                if (model.trianglePriorities != null) {
                    keepPriorities = true;
                } else {
                    if (priority == -1) {
                        priority = model.priority;
                    }

                    if (priority != model.priority) {
                        keepPriorities = true;
                    }
                }

                keepAlpha |= model.triangleAlpha != null;
                keepColor |= model.unmodifiedTriangleColor != null;
            }
        }

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];

        triangleVertexA = new int[triangleCount];
        triangleVertexB = new int[triangleCount];
        triangleVertexC = new int[triangleCount];

        colorA = new int[triangleCount];
        colorB = new int[triangleCount];
        colorC = new int[triangleCount];

        textureVertexA = new int[texturedCount];
        textureVertexB = new int[texturedCount];
        textureVertexC = new int[texturedCount];

        if (keepInfo) {
            triangleInfo = new int[triangleCount];
        }

        if (keepPriorities) {
            trianglePriorities = new int[triangleCount];
        }

        if (keepAlpha) {
            triangleAlpha = new int[triangleCount];
        }

        if (keepColor) {
            unmodifiedTriangleColor = new int[triangleCount];
        }

        vertexCount = 0;
        triangleCount = 0;
        texturedCount = 0;

        for (int n = 0; n < count; n++) {
            Model model = models[n];
            if (model != null) {
                int lastVertex = vertexCount;
                for (int v = 0; v < model.vertexCount; v++) {
                    vertexX[vertexCount] = model.vertexX[v];
                    vertexY[vertexCount] = model.vertexY[v];
                    vertexZ[vertexCount] = model.vertexZ[v];
                    vertexCount++;
                }

                for (int t = 0; t < model.triangleCount; t++) {
                    triangleVertexA[triangleCount] = model.triangleVertexA[t] + lastVertex;
                    triangleVertexB[triangleCount] = model.triangleVertexB[t] + lastVertex;
                    triangleVertexC[triangleCount] = model.triangleVertexC[t] + lastVertex;

                    colorA[triangleCount] = model.colorA[t];
                    colorB[triangleCount] = model.colorB[t];
                    colorC[triangleCount] = model.colorC[t];

                    if (keepInfo) {
                        if (model.triangleInfo == null) {
                            triangleInfo[triangleCount] = 0;
                        } else {
                            triangleInfo[triangleCount] = model.triangleInfo[t];
                        }
                    }

                    if (keepPriorities) {
                        if (model.trianglePriorities == null) {
                            trianglePriorities[triangleCount] = model.priority;
                        } else {
                            trianglePriorities[triangleCount] = model.trianglePriorities[t];
                        }
                    }

                    if (keepAlpha) {
                        if (model.triangleAlpha == null) {
                            triangleAlpha[triangleCount] = 0;
                        } else {
                            triangleAlpha[triangleCount] = model.triangleAlpha[t];
                        }
                    }

                    if (keepColor && model.unmodifiedTriangleColor != null) {
                        unmodifiedTriangleColor[triangleCount] = model.unmodifiedTriangleColor[t];
                    }

                    triangleCount++;
                }

                for (int t = 0; t < model.texturedCount; t++) {
                    textureVertexA[texturedCount] = model.textureVertexA[t] + lastVertex;
                    textureVertexB[texturedCount] = model.textureVertexB[t] + lastVertex;
                    textureVertexC[texturedCount] = model.textureVertexC[t] + lastVertex;
                    texturedCount++;
                }
            }
        }

        calculateYBoundaries();
    }

    public Model(Model from, boolean keepColors, boolean keepAlpha, boolean keepVertices) {
        pickable = false;

        vertexCount = from.vertexCount;
        triangleCount = from.triangleCount;
        texturedCount = from.texturedCount;

        if (keepVertices) {
            vertexX = from.vertexX;
            vertexY = from.vertexY;
            vertexZ = from.vertexZ;
        } else {
            vertexX = new int[vertexCount];
            vertexY = new int[vertexCount];
            vertexZ = new int[vertexCount];

            for (int v = 0; v < vertexCount; v++) {
                vertexX[v] = from.vertexX[v];
                vertexY[v] = from.vertexY[v];
                vertexZ[v] = from.vertexZ[v];
            }
        }

        if (keepColors) {
            unmodifiedTriangleColor = from.unmodifiedTriangleColor;
        } else {
            unmodifiedTriangleColor = new int[triangleCount];
            System.arraycopy(from.unmodifiedTriangleColor, 0, unmodifiedTriangleColor, 0, triangleCount);
        }

        if (keepAlpha) {
            triangleAlpha = from.triangleAlpha;
        } else {
            triangleAlpha = new int[triangleCount];
            if (from.triangleAlpha == null) {
                for (int l = 0; l < triangleCount; l++) {
                    triangleAlpha[l] = 0;
                }
            } else {
                System.arraycopy(from.triangleAlpha, 0, triangleAlpha, 0, triangleCount);
            }
        }

        vertexLabel = from.vertexLabel;
        triangleSkin = from.triangleSkin;
        triangleInfo = from.triangleInfo;
        triangleVertexA = from.triangleVertexA;
        triangleVertexB = from.triangleVertexB;
        triangleVertexC = from.triangleVertexC;
        trianglePriorities = from.trianglePriorities;
        priority = from.priority;
        textureVertexA = from.textureVertexA;
        textureVertexB = from.textureVertexB;
        textureVertexC = from.textureVertexC;
    }

    public Model(Model from, boolean keepVertices, boolean keepColors) {
        pickable = false;

        vertexCount = from.vertexCount;
        triangleCount = from.triangleCount;
        texturedCount = from.texturedCount;

        if (keepVertices) {
            vertexY = new int[vertexCount];
            System.arraycopy(from.vertexY, 0, vertexY, 0, vertexCount);
        } else {
            vertexY = from.vertexY;
        }

        if (keepColors) {
            colorA = new int[triangleCount];
            colorB = new int[triangleCount];
            colorC = new int[triangleCount];
            for (int j = 0; j < triangleCount; j++) {
                colorA[j] = from.colorA[j];
                colorB[j] = from.colorB[j];
                colorC[j] = from.colorC[j];
            }

            triangleInfo = new int[triangleCount];
            if (from.triangleInfo == null) {
                for (int k = 0; k < triangleCount; k++) {
                    triangleInfo[k] = 0;
                }
            } else {
                System.arraycopy(from.triangleInfo, 0, triangleInfo, 0, triangleCount);
            }

            vertexNormals = new VertexNormal[vertexCount];
            for (int v = 0; v < vertexCount; v++) {
                VertexNormal normal = vertexNormals[v] = new VertexNormal();
                VertexNormal oldNormal = from.vertexNormals[v];
                normal.x = oldNormal.x;
                normal.y = oldNormal.y;
                normal.z = oldNormal.z;
                normal.magnitude = oldNormal.magnitude;
            }

            unmodifiedVertexNormals = from.unmodifiedVertexNormals;
        } else {
            colorA = from.colorA;
            colorB = from.colorB;
            colorC = from.colorC;
            triangleInfo = from.triangleInfo;
        }

        vertexX = from.vertexX;
        vertexZ = from.vertexZ;
        unmodifiedTriangleColor = from.unmodifiedTriangleColor;
        triangleAlpha = from.triangleAlpha;
        trianglePriorities = from.trianglePriorities;
        priority = from.priority;
        triangleVertexA = from.triangleVertexA;
        triangleVertexB = from.triangleVertexB;
        triangleVertexC = from.triangleVertexC;
        textureVertexA = from.textureVertexA;
        textureVertexB = from.textureVertexB;
        textureVertexC = from.textureVertexC;
        maxBoundY = from.maxBoundY;
        minBoundY = from.minBoundY;
        lengthXZ = from.lengthXZ;
        minDepth = from.minDepth;
        maxDepth = from.maxDepth;
        minBoundX = from.minBoundX;
        maxBoundZ = from.maxBoundZ;
        minBoundZ = from.minBoundZ;
        maxBoundX = from.maxBoundX;
    }

    public Model(Model from, boolean keepAlpha) {
        pickable = false;

        vertexCount = from.vertexCount;
        triangleCount = from.triangleCount;
        texturedCount = from.texturedCount;

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];

        for (int j = 0; j < vertexCount; j++) {
            vertexX[j] = from.vertexX[j];
            vertexY[j] = from.vertexY[j];
            vertexZ[j] = from.vertexZ[j];
        }

        if (keepAlpha) {
            triangleAlpha = from.triangleAlpha;
        } else {
            triangleAlpha = new int[triangleCount];
            if (from.triangleAlpha == null) {
                for (int k = 0; k < triangleCount; k++) {
                    triangleAlpha[k] = 0;
                }
            } else {
                System.arraycopy(from.triangleAlpha, 0, triangleAlpha, 0, triangleCount);
            }
        }

        triangleInfo = from.triangleInfo;
        unmodifiedTriangleColor = from.unmodifiedTriangleColor;
        trianglePriorities = from.trianglePriorities;
        priority = from.priority;
        skinTriangle = from.skinTriangle;
        labelVertices = from.labelVertices;
        triangleVertexA = from.triangleVertexA;
        triangleVertexB = from.triangleVertexB;
        triangleVertexC = from.triangleVertexC;
        colorA = from.colorA;
        colorB = from.colorB;
        colorC = from.colorC;
        textureVertexA = from.textureVertexA;
        textureVertexB = from.textureVertexB;
        textureVertexC = from.textureVertexC;
    }

    public int copyVertex(Model from, int index) {
        int selected = -1;
        int x = from.vertexX[index];
        int y = from.vertexY[index];
        int z = from.vertexZ[index];

        for (int v = 0; v < vertexCount; v++) {
            if (x != vertexX[v] || y != vertexY[v] || z != vertexZ[v]) {
                continue;
            }

            selected = v;
            break;
        }

        if (selected == -1) {
            vertexX[vertexCount] = x;
            vertexY[vertexCount] = y;
            vertexZ[vertexCount] = z;

            if (from.vertexLabel != null) {
                vertexLabel[vertexCount] = from.vertexLabel[index];
            }

            selected = vertexCount++;
        }

        return selected;
    }

    public void calculateYBoundaries() {
        maxBoundY = 0;
        lengthXZ = 0;
        minBoundY = 0;

        for (int v = 0; v < vertexCount; v++) {
            int x = vertexX[v];
            int y = vertexY[v];
            int z = vertexZ[v];

            if (-y > maxBoundY) {
                maxBoundY = -y;
            }

            if (y > minBoundY) {
                minBoundY = y;
            }

            int lenghSquared = x * x + z * z;
            if (lenghSquared > lengthXZ) {
                lengthXZ = lenghSquared;
            }
        }

        lengthXZ = (int) (Math.sqrt(lengthXZ) + 0.98999999999999999D);
        minDepth = (int) (Math.sqrt(lengthXZ * lengthXZ + maxBoundY * maxBoundY) + 0.98999999999999999D);
        maxDepth = minDepth + (int) (Math.sqrt(lengthXZ * lengthXZ + minBoundY * minBoundY) + 0.98999999999999999D);
    }

    public void calculateYBoundaries2() {
        maxBoundY = 0;
        minBoundY = 0;

        for (int v = 0; v < vertexCount; v++) {
            int y = vertexY[v];

            if (-y > maxBoundY) {
                maxBoundY = -y;
            }

            if (y > minBoundY) {
                minBoundY = y;
            }
        }

        minDepth = (int) (Math.sqrt(lengthXZ * lengthXZ + maxBoundY * maxBoundY) + 0.98999999999999999D);
        maxDepth = minDepth + (int) (Math.sqrt(lengthXZ * lengthXZ + minBoundY * minBoundY) + 0.98999999999999999D);
    }

    public void calculateBoundaries() {
        maxBoundY = 0;
        lengthXZ = 0;
        minBoundY = 0;

        minBoundX = 999999;
        maxBoundX = -999999;
        maxBoundZ = -99999;
        minBoundZ = 99999;

        for (int v = 0; v < vertexCount; v++) {
            int x = vertexX[v];
            int y = vertexY[v];
            int z = vertexZ[v];

            if (x < minBoundX) {
                minBoundX = x;
            }
            if (x > maxBoundX) {
                maxBoundX = x;
            }

            if (z < minBoundZ) {
                minBoundZ = z;
            }
            if (z > maxBoundZ) {
                maxBoundZ = z;
            }

            if (-y > maxBoundY) {
                maxBoundY = -y;
            }
            if (y > minBoundY) {
                minBoundY = y;
            }

            int lenghSquared = x * x + z * z;
            if (lenghSquared > lengthXZ) {
                lengthXZ = lenghSquared;
            }
        }

        lengthXZ = (int) Math.sqrt(lengthXZ);
        minDepth = (int) Math.sqrt(lengthXZ * lengthXZ + maxBoundY * maxBoundY);
        maxDepth = minDepth + (int) Math.sqrt(lengthXZ * lengthXZ + minBoundY * minBoundY);
    }

    public void applyGroups() {
        if (vertexLabel != null) {
            int[] labelCount = new int[256];
            int topLabel = 0;

            for (int v = 0; v < vertexCount; v++) {
                int j1 = vertexLabel[v];
                labelCount[j1]++;
                if (j1 > topLabel) {
                    topLabel = j1;
                }
            }

            labelVertices = new int[topLabel + 1][];
            for (int v = 0; v <= topLabel; v++) {
                labelVertices[v] = new int[labelCount[v]];
                labelCount[v] = 0;
            }

            for (int v = 0; v < vertexCount; v++) {
                int lbl = vertexLabel[v];
                labelVertices[lbl][labelCount[lbl]++] = v;
            }

            vertexLabel = null;
        }

        if (triangleSkin != null) {
            int[] skinCount = new int[256];
            int topSkin = 0;

            for (int v = 0; v < triangleCount; v++) {
                int l1 = triangleSkin[v];
                skinCount[l1]++;
                if (l1 > topSkin) {
                    topSkin = l1;
                }
            }

            skinTriangle = new int[topSkin + 1][];
            for (int v = 0; v <= topSkin; v++) {
                skinTriangle[v] = new int[skinCount[v]];
                skinCount[v] = 0;
            }

            for (int v = 0; v < triangleCount; v++) {
                int s = triangleSkin[v];
                skinTriangle[s][skinCount[s]++] = v;
            }

            triangleSkin = null;
        }
    }

    public void applyFrame(int frame) {
        if (labelVertices == null) {
            return;
        }

        if (frame == -1) {
            return;
        }

        SeqFrame f = SeqFrame.instances[frame];
        SeqBase t = f.transform;

        transformX = 0;
        transformY = 0;
        transformZ = 0;

        for (int n = 0; n < f.groupCount; n++) {
            int group = f.groups[n];
            transform(t.types[group], t.groupLabels[group], f.x[n], f.y[n], f.z[n]);
        }
    }

    public void applyFrames(int secondaryFrame, int primaryFrame, int[] labelGroups) {
        if (primaryFrame == -1) {
            return;
        }

        if (labelGroups == null || secondaryFrame == -1) {
            applyFrame(primaryFrame);
            return;
        }

        SeqFrame primary = SeqFrame.instances[primaryFrame];
        SeqFrame secondary = SeqFrame.instances[secondaryFrame];
        SeqBase t = primary.transform;

        transformX = 0;
        transformY = 0;
        transformZ = 0;

        int index = 0;
        int current = labelGroups[index++];

        for (int g = 0; g < primary.groupCount; g++) {
            int group;
            for (group = primary.groups[g]; group > current; current = labelGroups[index++]) {
            }

            if (group != current || t.types[group] == 0) {
                transform(t.types[group], t.groupLabels[group], primary.x[g], primary.y[g], primary.z[g]);
            }
        }

        transformX = 0;
        transformY = 0;
        transformZ = 0;

        index = 0;
        current = labelGroups[index++];

        for (int h = 0; h < secondary.groupCount; h++) {
            int group;
            for (group = secondary.groups[h]; group > current; current = labelGroups[index++]) {
            }

            if (group == current || t.types[group] == 0) {
                transform(t.types[group], t.groupLabels[group], secondary.x[h], secondary.y[h], secondary.z[h]);
            }
        }
    }

    public void transform(int type, int[] labels, int x, int y, int z) {
        int count = labels.length;

        if (type == 0) {
            int counter = 0;

            transformX = 0;
            transformY = 0;
            transformZ = 0;

            for (int n = 0; n < count; n++) {
                int label = labels[n];
                if (label < labelVertices.length) {
                    int[] vertices = labelVertices[label];

                    for (int v = 0; v < vertices.length; v++) {
                        int index = vertices[v];
                        transformX += vertexX[index];
                        transformY += vertexY[index];
                        transformZ += vertexZ[index];
                        counter++;
                    }
                }
            }

            if (counter > 0) {
                transformX = transformX / counter + x;
                transformY = transformY / counter + y;
                transformZ = transformZ / counter + z;
            } else {
                transformX = x;
                transformY = y;
                transformZ = z;
            }
        } else if (type == 1) {
            for (int n = 0; n < count; n++) {
                int label = labels[n];
                if (label < labelVertices.length) {
                    int[] vertices = labelVertices[label];
                    for (int v = 0; v < vertices.length; v++) {
                        int index = vertices[v];
                        vertexX[index] += x;
                        vertexY[index] += y;
                        vertexZ[index] += z;
                    }
                }
            }
        } else if (type == 2) {
            for (int i = 0; i < count; i++) {
                int label = labels[i];
                if (label < labelVertices.length) {
                    int[] vertices = labelVertices[label];
                    for (int v = 0; v < vertices.length; v++) {
                        int index = vertices[v];

                        vertexX[index] -= transformX;
                        vertexY[index] -= transformY;
                        vertexZ[index] -= transformZ;

                        int pitch = (x & 0xff) * 8;
                        int yaw = (y & 0xff) * 8;
                        int roll = (z & 0xff) * 8;

                        if (roll != 0) {
                            int s = sin[roll];
                            int c = cos[roll];
                            int x0 = vertexY[index] * s + vertexX[index] * c >> 16;
                            vertexY[index] = vertexY[index] * c - vertexX[index] * s >> 16;
                            vertexX[index] = x0;
                        }

                        if (pitch != 0) {
                            int s = sin[pitch];
                            int c = cos[pitch];
                            int y0 = vertexY[index] * c - vertexZ[index] * s >> 16;
                            vertexZ[index] = vertexY[index] * s + vertexZ[index] * c >> 16;
                            vertexY[index] = y0;
                        }

                        if (yaw != 0) {
                            int s = sin[yaw];
                            int c = cos[yaw];
                            int z0 = vertexZ[index] * s + vertexX[index] * c >> 16;
                            vertexZ[index] = vertexZ[index] * c - vertexX[index] * s >> 16;
                            vertexX[index] = z0;
                        }

                        vertexX[index] += transformX;
                        vertexY[index] += transformY;
                        vertexZ[index] += transformZ;
                    }
                }
            }
        } else if (type == 3) {
            for (int i = 0; i < count; i++) {
                int label = labels[i];
                if (label < labelVertices.length) {
                    int[] vertices = labelVertices[label];
                    for (int v = 0; v < vertices.length; v++) {
                        int index = vertices[v];

                        vertexX[index] -= transformX;
                        vertexY[index] -= transformY;
                        vertexZ[index] -= transformZ;

                        vertexX[index] = (vertexX[index] * x) / 128;
                        vertexY[index] = (vertexY[index] * y) / 128;
                        vertexZ[index] = (vertexZ[index] * z) / 128;

                        vertexX[index] += transformX;
                        vertexY[index] += transformY;
                        vertexZ[index] += transformZ;
                    }
                }
            }
        } else if (type == 5 && skinTriangle != null && triangleAlpha != null) {
            for (int i = 0; i < count; i++) {
                int label = labels[i];
                if (label < skinTriangle.length) {
                    int[] triangles = skinTriangle[label];
                    for (int t = 0; t < triangles.length; t++) {
                        int index = triangles[t];
                        triangleAlpha[index] += x * 8;
                        if (triangleAlpha[index] < 0) {
                            triangleAlpha[index] = 0;
                        } else if (triangleAlpha[index] > 255) {
                            triangleAlpha[index] = 255;
                        }
                    }
                }
            }
        }
    }

    public void rotateCounterClockwise() {
        for (int v = 0; v < vertexCount; v++) {
            int x = vertexX[v];
            vertexX[v] = vertexZ[v];
            vertexZ[v] = -x;
        }
    }

    public void rotatePitch(int angle) {
        int s = sin[angle];
        int c = cos[angle];

        for (int v = 0; v < vertexCount; v++) {
            int y = vertexY[v] * c - vertexZ[v] * s >> 16;
            vertexZ[v] = vertexY[v] * s + vertexZ[v] * c >> 16;
            vertexY[v] = y;
        }
    }

    public void translate(int y, int x, int z) {
        for (int v = 0; v < vertexCount; v++) {
            vertexX[v] += x;
            vertexY[v] += y;
            vertexZ[v] += z;
        }
    }

    public void recolor(int from, int to) {
        for (int t = 0; t < triangleCount; t++) {
            if (unmodifiedTriangleColor[t] == from) {
                unmodifiedTriangleColor[t] = to;
            }
        }
    }

    public void flipBackwards() {
        for (int v = 0; v < vertexCount; v++) {
            vertexZ[v] = -vertexZ[v];
        }

        for (int t = 0; t < triangleCount; t++) {
            int a = triangleVertexA[t];
            triangleVertexA[t] = triangleVertexC[t];
            triangleVertexC[t] = a;
        }
    }

    public void scale(int z, int y, int x) {
        for (int v = 0; v < vertexCount; v++) {
            vertexX[v] = (vertexX[v] * x) / 128;
            vertexY[v] = (vertexY[v] * y) / 128;
            vertexZ[v] = (vertexZ[v] * z) / 128;
        }
    }

    public void applyLighting(int baseLightness, int intensity, int x, int y, int z, boolean calculateLighting) {
        int lightLength = (int) Math.sqrt(x * x + y * y + z * z);
        int lightIntensity = intensity * lightLength >> 8;

        if (colorA == null) {
            colorA = new int[triangleCount];
            colorB = new int[triangleCount];
            colorC = new int[triangleCount];
        }

        if (vertexNormals == null) {
            vertexNormals = new VertexNormal[vertexCount];
            for (int v = 0; v < vertexCount; v++) {
                vertexNormals[v] = new VertexNormal();
            }
        }

        for (int t = 0; t < triangleCount; t++) {
            int a = triangleVertexA[t];
            int b = triangleVertexB[t];
            int c = triangleVertexC[t];

            int dxAB = vertexX[b] - vertexX[a];
            int dyAB = vertexY[b] - vertexY[a];
            int dzAB = vertexZ[b] - vertexZ[a];

            int dxCA = vertexX[c] - vertexX[a];
            int dyCA = vertexY[c] - vertexY[a];
            int dzCA = vertexZ[c] - vertexZ[a];

            int x0 = dyAB * dzCA - dyCA * dzAB;
            int y0 = dzAB * dxCA - dzCA * dxAB;
            int z0 = dxAB * dyCA - dxCA * dyAB;

            for (; x0 > 8192 || y0 > 8192 || z0 > 8192 || x0 < -8192 || y0 < -8192 || z0 < -8192; ) {
                x0 >>= 1;
                y0 >>= 1;
                z0 >>= 1;
            }

            int length = (int) Math.sqrt(x0 * x0 + y0 * y0 + z0 * z0);

            if (length <= 0) {
                length = 1;
            }

            x0 = (x0 * 256) / length;
            y0 = (y0 * 256) / length;
            z0 = (z0 * 256) / length;

            if (triangleInfo == null || (triangleInfo[t] & 1) == 0) {
                VertexNormal n = vertexNormals[a];
                n.x += x0;
                n.y += y0;
                n.z += z0;
                n.magnitude++;

                n = vertexNormals[b];
                n.x += x0;
                n.y += y0;
                n.z += z0;
                n.magnitude++;

                n = vertexNormals[c];
                n.x += x0;
                n.y += y0;
                n.z += z0;
                n.magnitude++;
            } else {
                int lightness = baseLightness + (x * x0 + y * y0 + z * z0) / (lightIntensity + lightIntensity / 2);
                colorA[t] = adjustHSLLightness(unmodifiedTriangleColor[t], lightness, triangleInfo[t]);
            }
        }

        if (calculateLighting) {
            calculateLighting(baseLightness, lightIntensity, x, y, z);
        } else {
            unmodifiedVertexNormals = new VertexNormal[vertexCount];
            for (int k2 = 0; k2 < vertexCount; k2++) {
                VertexNormal current = vertexNormals[k2];
                VertexNormal copy = unmodifiedVertexNormals[k2] = new VertexNormal();
                copy.x = current.x;
                copy.y = current.y;
                copy.z = current.z;
                copy.magnitude = current.magnitude;
            }
        }

        if (calculateLighting) {
            calculateYBoundaries();
        } else {
            calculateBoundaries();
        }
    }

    public void calculateLighting(int minIntensity, int intensity, int x, int y, int z) {
        if (unmodifiedTriangleColor == null) {
            return;
        }

        for (int t = 0; t < triangleCount; t++) {
            int a = triangleVertexA[t];
            int b = triangleVertexB[t];
            int c = triangleVertexC[t];

            if (triangleInfo == null) {
                int color = unmodifiedTriangleColor[t];

                VertexNormal n = vertexNormals[a];
                int lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorA[t] = adjustHSLLightness(color, lightness, 0);

                n = vertexNormals[b];
                lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorB[t] = adjustHSLLightness(color, lightness, 0);

                n = vertexNormals[c];
                lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorC[t] = adjustHSLLightness(color, lightness, 0);
            } else if ((triangleInfo[t] & 1) == 0) {
                int color = unmodifiedTriangleColor[t];
                int info = triangleInfo[t];

                VertexNormal n = vertexNormals[a];
                int lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorA[t] = adjustHSLLightness(color, lightness, info);

                n = vertexNormals[b];
                lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorB[t] = adjustHSLLightness(color, lightness, info);

                n = vertexNormals[c];
                lightness = minIntensity + (x * n.x + y * n.y + z * n.z) / (intensity * n.magnitude);
                colorC[t] = adjustHSLLightness(color, lightness, info);
            }
        }

        vertexNormals = null;
        unmodifiedVertexNormals = null;
        vertexLabel = null;
        triangleSkin = null;
        if (triangleInfo != null) {
            for (int t = 0; t < triangleCount; t++) {
                if ((triangleInfo[t] & 2) == 2) {
                    return;
                }
            }
        }
        unmodifiedTriangleColor = null;
    }

    public static int adjustHSLLightness(int hsl, int lightness, int type) {
        if ((type & 2) == 2) {
            if (lightness < 0)
                lightness = 0;
            else if (lightness > 127)
                lightness = 127;
            lightness = 127 - lightness;
            return lightness;
        }

        lightness = lightness * (hsl & 0x7f) >> 7;
        if (lightness < 2)
            lightness = 2;
        else if (lightness > 126)
            lightness = 126;
        return (hsl & 0xff80) + lightness;
    }

    public void draw(int pitch, int yaw, int roll, int cameraPitch, int cameraX, int cameraY, int cameraZ) {
        final int centerX = Draw3D.centerX;
        final int centerY = Draw3D.centerY;

        int pitchsin = sin[pitch];
        int pitchcos = cos[pitch];

        int yawsin = sin[yaw];
        int yawcos = cos[yaw];

        int rollsin = sin[roll];
        int rollcos = cos[roll];

        int cpitchsin = sin[cameraPitch];
        int cpitchcos = cos[cameraPitch];

        int depth = cameraY * cpitchsin + cameraZ * cpitchcos >> 16;
        
        for (int v = 0; v < vertexCount; v++) {
            int x = vertexX[v];
            int y = vertexY[v];
            int z = vertexZ[v];

            if (roll != 0) {
                int z0 = y * rollsin + x * rollcos >> 16;
                y = y * rollcos - x * rollsin >> 16;
                x = z0;
            }

            if (pitch != 0) {
                int x0 = y * pitchcos - z * pitchsin >> 16;
                z = y * pitchsin + z * pitchcos >> 16;
                y = x0;
            }

            if (yaw != 0) {
                int y0 = z * yawsin + x * yawcos >> 16;
                z = z * yawcos - x * yawsin >> 16;
                x = y0;
            }

            x += cameraX;
            y += cameraY;
            z += cameraZ;

            int x0 = y * cpitchcos - z * cpitchsin >> 16;
            z = y * cpitchsin + z * cpitchcos >> 16;
            y = x0;

            vertexDepth[v] = z - depth;
            vertexScreenX[v] = centerX + (x << 9) / z;
            vertexScreenY[v] = centerY + (y << 9) / z;
            if (texturedCount > 0) {
                projectSceneX[v] = x;
                projectSceneY[v] = y;
                projectSceneZ[v] = z;
            }
        }

        try {
            draw(false, false, 0);
        } catch (Exception _ex) {
        }
    }

    public void draw(int yaw, int sinCameraPitch, int cosCameraPitch, int sinCameraYaw, int cosCameraYaw, int sceneX, int sceneY,
                     int sceneZ, int uid) {
        int a = sceneZ * cosCameraYaw - sceneX * sinCameraYaw >> 16;
        int b = sceneY * sinCameraPitch + a * cosCameraPitch >> 16;
        int c = lengthXZ * cosCameraPitch >> 16;
        int d = b + c;
        
        if (d <= 50 || b >= 3500) {
            return;
        }
        
        int e = sceneZ * sinCameraYaw + sceneX * cosCameraYaw >> 16;

        int minScreenX = e - lengthXZ << 9;
        if (minScreenX / d >= Draw2D.centerX) {
            return;
        }

        int maxScreenX = e + lengthXZ << 9;
        if (maxScreenX / d <= -Draw2D.centerX) {
            return;
        }
        
        int f = sceneY * cosCameraPitch - a * sinCameraPitch >> 16;
        int g = lengthXZ * sinCameraPitch >> 16;
        
        int maxScreenY = f + g << 9;
        if (maxScreenY / d <= -Draw2D.centerY) {
            return;
        }
        
        int h = g + (maxBoundY * cosCameraPitch >> 16);

        int minScreenY = f - h << 9;
        if (minScreenY / d >= Draw2D.centerY) {
            return;
        }

        int i = c + (maxBoundY * sinCameraPitch >> 16);
        boolean project = b - i <= 50;
        boolean hasInput = false;

        if (uid > 0 && allowInput) {
            int j = b - c;
            if (j <= 50) {
                j = 50;
            }
            
            if (e > 0) {
                minScreenX /= d;
                maxScreenX /= j;
            } else {
                maxScreenX /= d;
                minScreenX /= j;
            }
            
            if (f > 0) {
                minScreenY /= d;
                maxScreenY /= j;
            } else {
                maxScreenY /= d;
                minScreenY /= j;
            }
            
            int x = cursorX - Draw3D.centerX;
            int y = cursorY - Draw3D.centerY;

            if (x > minScreenX && x < maxScreenX && y > minScreenY && y < maxScreenY) {
                if (pickable) {
                    hoveredBitsets[resourceCount++] = uid;
                } else {
                    hasInput = true;
                }
            }
        }

        int cx = Draw3D.centerX;
        int cy = Draw3D.centerY;
        
        int yawsin = 0;
        int yawcos = 0;
        if (yaw != 0) {
            yawsin = sin[yaw];
            yawcos = cos[yaw];
        }
        
        for (int v = 0; v < vertexCount; v++) {
            int x = vertexX[v];
            int y = vertexY[v];
            int z = vertexZ[v];

            if (yaw != 0) {
                int w = z * yawsin + x * yawcos >> 16;
                z = z * yawcos - x * yawsin >> 16;
                x = w;
            }

            x += sceneX;
            y += sceneY;
            z += sceneZ;

            int w = z * sinCameraYaw + x * cosCameraYaw >> 16;
            z = z * cosCameraYaw - x * sinCameraYaw >> 16;
            x = w;

            w = y * cosCameraPitch - z * sinCameraPitch >> 16;
            z = y * sinCameraPitch + z * cosCameraPitch >> 16;
            y = w;

            vertexDepth[v] = z - b;

            if (z >= 50) {
                vertexScreenX[v] = cx + (x << 9) / z;
                vertexScreenY[v] = cy + (y << 9) / z;
            } else {
                vertexScreenX[v] = -5000;
                project = true;
            }

            if (project || texturedCount > 0) {
                projectSceneX[v] = x;
                projectSceneY[v] = y;
                projectSceneZ[v] = z;
            }
        }

        try {
            draw(project, hasInput, uid);
        } catch (Exception _ex) {
        }
    }

    public void draw(boolean projected, boolean hasInput, int bitset) {
        for (int d = 0; d < maxDepth; d++) {
            depthTriangleCount[d] = 0;
        }

        for (int t = 0; t < triangleCount; t++) {
            if (triangleInfo == null || triangleInfo[t] != -1) {
                int a = triangleVertexA[t];
                int b = triangleVertexB[t];
                int c = triangleVertexC[t];

                int x0 = vertexScreenX[a];
                int x1 = vertexScreenX[b];
                int x2 = vertexScreenX[c];

                if (projected && (x0 == -5000 || x1 == -5000 || x2 == -5000)) {
                    projectTriangle[t] = true;
                    int depth = (vertexDepth[a] + vertexDepth[b] + vertexDepth[c]) / 3 + minDepth;
                    depthTriangles[depth][depthTriangleCount[depth]++] = t;
                } else {
                    if (hasInput && pointWithinTriangle(cursorX, cursorY, vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], x0, x1, x2)) {
                        hoveredBitsets[resourceCount++] = bitset;
                        hasInput = false;
                    }

                    if ((x0 - x1) * (vertexScreenY[c] - vertexScreenY[b]) - (vertexScreenY[a] - vertexScreenY[b]) * (x2 - x1) > 0) {
                        projectTriangle[t] = false;
                        testTriangleX[t] = x0 < 0 || x1 < 0 || x2 < 0 || x0 > Draw2D.rightX || x1 > Draw2D.rightX || x2 > Draw2D.rightX;
                        int depth = (vertexDepth[a] + vertexDepth[b] + vertexDepth[c]) / 3 + minDepth;
                        depthTriangles[depth][depthTriangleCount[depth]++] = t;
                    }
                }
            }
        }

        if (trianglePriorities == null) {
            for (int d = maxDepth - 1; d >= 0; d--) {
                int n = depthTriangleCount[d];
                if (n > 0) {
                    int[] triangles = depthTriangles[d];
                    for (int t = 0; t < n; t++) {
                        drawTriangle(triangles[t]);
                    }
                }
            }

            return;
        }

        for (int p = 0; p < 12; p++) {
            priorityTriangleCounts[p] = 0;
            lowTrianglePriority[p] = 0;
        }

        for (int d = maxDepth - 1; d >= 0; d--) {
            int n = depthTriangleCount[d];
            if (n > 0) {
                int[] triangles = depthTriangles[d];
                for (int m = 0; m < n; m++) {
                    int t = triangles[m];
                    int trianglePriority = trianglePriorities[t];
                    int priorityTriangle = priorityTriangleCounts[trianglePriority]++;
                    priorityTriangles[trianglePriority][priorityTriangle] = t;

                    if (trianglePriority < 10) {
                        lowTrianglePriority[trianglePriority] += d;
                    } else if (trianglePriority == 10) {
                        normalTrianglePriority[priorityTriangle] = d;
                    } else {
                        highTrianglePriority[priorityTriangle] = d;
                    }
                }
            }
        }

        int minPriority = 0;
        if (priorityTriangleCounts[1] > 0 || priorityTriangleCounts[2] > 0) {
            minPriority = (lowTrianglePriority[1] + lowTrianglePriority[2]) / (priorityTriangleCounts[1] + priorityTriangleCounts[2]);
        }

        int halfPriority = 0;
        if (priorityTriangleCounts[3] > 0 || priorityTriangleCounts[4] > 0) {
            halfPriority = (lowTrianglePriority[3] + lowTrianglePriority[4]) / (priorityTriangleCounts[3] + priorityTriangleCounts[4]);
        }

        int maxPriority = 0;
        if (priorityTriangleCounts[6] > 0 || priorityTriangleCounts[8] > 0) {
            maxPriority = (lowTrianglePriority[6] + lowTrianglePriority[8]) / (priorityTriangleCounts[6] + priorityTriangleCounts[8]);
        }

        int t = 0;
        int priorityTriangleCount = priorityTriangleCounts[10];
        int[] triangles = priorityTriangles[10];
        int[] priorities = normalTrianglePriority;

        if (t == priorityTriangleCount) {
            t = 0;
            priorityTriangleCount = priorityTriangleCounts[11];
            triangles = priorityTriangles[11];
            priorities = highTrianglePriority;
        }

        int pri;
        if (t < priorityTriangleCount) {
            pri = priorities[t];
        } else {
            pri = -1000;
        }

        for (int p = 0; p < 10; p++) {
            while (p == 0 && pri > minPriority) {
                drawTriangle(triangles[t++]);

                if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
                    t = 0;
                    priorityTriangleCount = priorityTriangleCounts[11];
                    triangles = priorityTriangles[11];
                    priorities = highTrianglePriority;
                }

                if (t < priorityTriangleCount) {
                    pri = priorities[t];
                } else {
                    pri = -1000;
                }
            }

            while (p == 3 && pri > halfPriority) {
                drawTriangle(triangles[t++]);
                if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
                    t = 0;
                    priorityTriangleCount = priorityTriangleCounts[11];
                    triangles = priorityTriangles[11];
                    priorities = highTrianglePriority;
                }

                if (t < priorityTriangleCount) {
                    pri = priorities[t];
                } else {
                    pri = -1000;
                }
            }

            while (p == 5 && pri > maxPriority) {
                drawTriangle(triangles[t++]);
                if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
                    t = 0;
                    priorityTriangleCount = priorityTriangleCounts[11];
                    triangles = priorityTriangles[11];
                    priorities = highTrianglePriority;
                }

                if (t < priorityTriangleCount) {
                    pri = priorities[t];
                } else {
                    pri = -1000;
                }
            }

            int n = priorityTriangleCounts[p];
            int[] tris = priorityTriangles[p];

            for (int m = 0; m < n; m++) {
                drawTriangle(tris[m]);
            }
        }

        while (pri != -1000) {
            drawTriangle(triangles[t++]);
            if (t == priorityTriangleCount && triangles != priorityTriangles[11]) {
                t = 0;
                triangles = priorityTriangles[11];
                priorityTriangleCount = priorityTriangleCounts[11];
                priorities = highTrianglePriority;
            }

            if (t < priorityTriangleCount) {
                pri = priorities[t];
            } else {
                pri = -1000;
            }
        }
    }

    public void drawTriangle(int index) {
        if (projectTriangle[index]) {
            drawProjectedTriangle(index);
            return;
        }

        int a = triangleVertexA[index];
        int b = triangleVertexB[index];
        int c = triangleVertexC[index];

        Draw3D.testX = testTriangleX[index];

        if (triangleAlpha == null) {
            Draw3D.alpha = 0;
        } else {
            Draw3D.alpha = triangleAlpha[index];
        }

        int type;
        if (triangleInfo == null) {
            type = 0;
        } else {
            type = triangleInfo[index] & 3;
        }

        if (type == 0) {
            Draw3D.fillGouraudTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], colorA[index], colorB[index], colorC[index]);
        } else if (type == 1) {
            Draw3D.fillTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a], vertexScreenX[b], vertexScreenX[c], palette[colorA[index]]);
        } else if (type == 2) {
            int t = triangleInfo[index] >> 2;
            int tA = textureVertexA[t];
            int tB = textureVertexB[t];
            int tC = textureVertexC[t];
            Draw3D.fillTexturedTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a],
                vertexScreenX[b], vertexScreenX[c], colorA[index], colorB[index], colorC[index],
                projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB],
                projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
        } else if (type == 3) {
            int t = triangleInfo[index] >> 2;
            int tA = textureVertexA[t];
            int tB = textureVertexB[t];
            int tC = textureVertexC[t];
            Draw3D.fillTexturedTriangle(vertexScreenY[a], vertexScreenY[b], vertexScreenY[c], vertexScreenX[a],
                vertexScreenX[b], vertexScreenX[c], colorA[index], colorA[index], colorA[index],
                projectSceneX[tA], projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB],
                projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
        }
    }

    public void drawProjectedTriangle(int index) {
        int centerX = Draw3D.centerX;
        int centerY = Draw3D.centerY;
        int n = 0;

        int a = triangleVertexA[index];
        int b = triangleVertexB[index];
        int c = triangleVertexC[index];

        int zA = projectSceneZ[a];
        int zB = projectSceneZ[b];
        int zC = projectSceneZ[c];

        if (zA >= 50) {
            tmpX[n] = vertexScreenX[a];
            tmpY[n] = vertexScreenY[a];
            tmpZ[n++] = colorA[index];
        } else {
            int x = projectSceneX[a];
            int y = projectSceneY[a];
            int color = colorA[index];

            if (zC >= 50) {
                int mul = (50 - zA) * oneOverFixed1616[zC - zA];
                tmpX[n] = centerX + (x + ((projectSceneX[c] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[c] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorC[index] - color) * mul >> 16);
            }

            if (zB >= 50) {
                int mul = (50 - zA) * oneOverFixed1616[zB - zA];
                tmpX[n] = centerX + (x + ((projectSceneX[b] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[b] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorB[index] - color) * mul >> 16);
            }
        }

        if (zB >= 50) {
            tmpX[n] = vertexScreenX[b];
            tmpY[n] = vertexScreenY[b];
            tmpZ[n++] = colorB[index];
        } else {
            int x = projectSceneX[b];
            int y = projectSceneY[b];
            int color = colorB[index];

            if (zA >= 50) {
                int mul = (50 - zB) * oneOverFixed1616[zA - zB];
                tmpX[n] = centerX + (x + ((projectSceneX[a] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[a] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorA[index] - color) * mul >> 16);
            }

            if (zC >= 50) {
                int mul = (50 - zB) * oneOverFixed1616[zC - zB];
                tmpX[n] = centerX + (x + ((projectSceneX[c] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[c] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorC[index] - color) * mul >> 16);
            }
        }

        if (zC >= 50) {
            tmpX[n] = vertexScreenX[c];
            tmpY[n] = vertexScreenY[c];
            tmpZ[n++] = colorC[index];
        } else {
            int x = projectSceneX[c];
            int y = projectSceneY[c];
            int color = colorC[index];

            if (zB >= 50) {
                int mul = (50 - zC) * oneOverFixed1616[zB - zC];
                tmpX[n] = centerX + (x + ((projectSceneX[b] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[b] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorB[index] - color) * mul >> 16);
            }

            if (zA >= 50) {
                int mul = (50 - zC) * oneOverFixed1616[zA - zC];
                tmpX[n] = centerX + (x + ((projectSceneX[a] - x) * mul >> 16) << 9) / 50;
                tmpY[n] = centerY + (y + ((projectSceneY[a] - y) * mul >> 16) << 9) / 50;
                tmpZ[n++] = color + ((colorA[index] - color) * mul >> 16);
            }
        }

        int xA = tmpX[0];
        int xB = tmpX[1];
        int xC = tmpX[2];

        int yA = tmpY[0];
        int yB = tmpY[1];
        int yC = tmpY[2];

        if ((xA - xB) * (yC - yB) - (yA - yB) * (xC - xB) > 0) {
            Draw3D.testX = false;

            if (n == 3) {
                if (xA < 0 || xB < 0 || xC < 0 || xA > Draw2D.rightX || xB > Draw2D.rightX || xC > Draw2D.rightX) {
                    Draw3D.testX = true;
                }

                int type;
                if (triangleInfo == null) {
                    type = 0;
                } else {
                    type = triangleInfo[index] & 3;
                }

                if (type == 0) {
                    Draw3D.fillGouraudTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2]);
                } else if (type == 1) {
                    Draw3D.fillTriangle(yA, yB, yC, xA, xB, xC, palette[colorA[index]]);
                } else if (type == 2) {
                    int t = triangleInfo[index] >> 2;
                    int tA = textureVertexA[t];
                    int tB = textureVertexB[t];
                    int tC = textureVertexC[t];
                    Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1],
                        tmpZ[2], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC],
                        projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA],
                        projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
                } else if (type == 3) {
                    int t = triangleInfo[index] >> 2;
                    int tA = textureVertexA[t];
                    int tB = textureVertexB[t];
                    int tC = textureVertexC[t];
                    Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, colorA[index], colorA[index],
                        colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC],
                        projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA],
                        projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
                }
            }

            if (n == 4) {
                if (xA < 0 || xB < 0 || xC < 0 || xA > Draw2D.rightX || xB > Draw2D.rightX || xC > Draw2D.rightX || tmpX[3] < 0 || tmpX[3] > Draw2D.rightX) {
                    Draw3D.testX = true;
                }

                int type;
                if (triangleInfo == null) {
                    type = 0;
                } else {
                    type = triangleInfo[index] & 3;
                }

                if (type == 0) {
                    Draw3D.fillGouraudTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1], tmpZ[2]);
                    Draw3D.fillGouraudTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], tmpZ[0], tmpZ[2], tmpZ[3]);
                } else if (type == 1) {
                    int rgb = palette[colorA[index]];
                    Draw3D.fillTriangle(yA, yB, yC, xA, xB, xC, rgb);
                    Draw3D.fillTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3], rgb);
                } else if (type == 2) {
                    int t = triangleInfo[index] >> 2;
                    int tA = textureVertexA[t];
                    int tB = textureVertexB[t];
                    int tC = textureVertexC[t];
                    Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, tmpZ[0], tmpZ[1],
                        tmpZ[2], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC],
                        projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA],
                        projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
                    Draw3D.fillTexturedTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3],
                        tmpZ[0], tmpZ[2], tmpZ[3], projectSceneX[tA],
                        projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB],
                        projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC],
                        unmodifiedTriangleColor[index]);
                } else if (type == 3) {
                    int t = triangleInfo[index] >> 2;
                    int tA = textureVertexA[t];
                    int tB = textureVertexB[t];
                    int tC = textureVertexC[t];
                    Draw3D.fillTexturedTriangle(yA, yB, yC, xA, xB, xC, colorA[index], colorA[index],
                        colorA[index], projectSceneX[tA], projectSceneX[tB], projectSceneX[tC],
                        projectSceneY[tA], projectSceneY[tB], projectSceneY[tC], projectSceneZ[tA],
                        projectSceneZ[tB], projectSceneZ[tC], unmodifiedTriangleColor[index]);
                    Draw3D.fillTexturedTriangle(yA, yC, tmpY[3], xA, xC, tmpX[3],
                        colorA[index], colorA[index], colorA[index], projectSceneX[tA],
                        projectSceneX[tB], projectSceneX[tC], projectSceneY[tA], projectSceneY[tB],
                        projectSceneY[tC], projectSceneZ[tA], projectSceneZ[tB], projectSceneZ[tC],
                        unmodifiedTriangleColor[index]);
                }
            }
        }
    }

    public boolean pointWithinTriangle(int x, int y, int yA, int yB, int yC, int xA, int xB, int xC) {
        if (y < yA && y < yB && y < yC) {
            return false;
        }

        if (y > yA && y > yB && y > yC) {
            return false;
        }

        if (x < xA && x < xB && x < xC) {
            return false;
        }

        return x <= xA || x <= xB || x <= xC;
    }

    public int vertexCount;
    public int[] vertexX;
    public int[] vertexY;
    public int[] vertexZ;
    public int triangleCount;
    public int[] triangleVertexA;
    public int[] triangleVertexB;
    public int[] triangleVertexC;
    public int[] colorA;
    public int[] colorB;
    public int[] colorC;
    public int[] triangleInfo;
    public int[] trianglePriorities;
    public int[] triangleAlpha;
    public int[] unmodifiedTriangleColor;
    public int priority;
    public int texturedCount;
    public int[] textureVertexA;
    public int[] textureVertexB;
    public int[] textureVertexC;
    public int minBoundX;
    public int maxBoundX;
    public int maxBoundZ;
    public int minBoundZ;
    public int lengthXZ;
    public int maxBoundY;
    public int minBoundY;
    public int maxDepth;
    public int minDepth;
    public int anInt1251;
    public int[] vertexLabel;
    public int[] triangleSkin;
    public int[][] labelVertices;
    public int[][] skinTriangle;
    public boolean pickable;
    public VertexNormal[] vertexNormals;
    public VertexNormal[] unmodifiedVertexNormals;
    public static Metadata[] metadata;
    public static Buffer obhead;
    public static Buffer obface1;
    public static Buffer obface2;
    public static Buffer obface3;
    public static Buffer obface4;
    public static Buffer obface5;
    public static Buffer obpoint1;
    public static Buffer obpoint2;
    public static Buffer obpoint3;
    public static Buffer obpoint4;
    public static Buffer obpoint5;
    public static Buffer obvertex1;
    public static Buffer obvertex2;
    public static Buffer obaxis;
    public static boolean[] testTriangleX = new boolean[4096];
    public static boolean[] projectTriangle = new boolean[4096];
    public static int[] vertexScreenX = new int[4096];
    public static int[] vertexScreenY = new int[4096];
    public static int[] vertexDepth = new int[4096];
    public static int[] projectSceneX = new int[4096];
    public static int[] projectSceneY = new int[4096];
    public static int[] projectSceneZ = new int[4096];
    public static int[] depthTriangleCount = new int[1500];
    public static int[][] depthTriangles = new int[1500][512];
    public static int[] priorityTriangleCounts = new int[12];
    public static int[][] priorityTriangles = new int[12][2000];
    public static int[] normalTrianglePriority = new int[2000];
    public static int[] highTrianglePriority = new int[2000];
    public static int[] lowTrianglePriority = new int[12];
    public static int[] tmpX = new int[10];
    public static int[] tmpY = new int[10];
    public static int[] tmpZ = new int[10];
    public static int transformX;
    public static int transformY;
    public static int transformZ;
    public static boolean allowInput;
    public static int cursorX;
    public static int cursorY;
    public static int resourceCount;
    public static int[] hoveredBitsets = new int[1000];
    public static int[] sin;
    public static int[] cos;
    public static int[] palette;
    public static int[] oneOverFixed1616;

    public static class Metadata {
        public Metadata() {
        }

        public int vertexCount;
        public int triangleCount;
        public int texturedCount;

        public int vertexFlagDataOffset;

        public int vertexXDataOffset;
        public int vertexYDataOffset;
        public int vertexZDataOffset;

        public int vertexLabelDataOffset;
        public int vertexIndexDataOffset;

        public int triangleTypeDataOffset;
        public int triangleColorDataOffset;
        public int triangleInfoDataOffset;
        public int trianglePriorityDataOffset;
        public int triangleAlphaDataOffset;
        public int triangleSkinDataOffset;
        public int triangleTextureDataOffset;
    }
}
