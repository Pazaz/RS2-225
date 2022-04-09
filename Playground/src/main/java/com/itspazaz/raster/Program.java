package com.itspazaz.raster;

import com.jagex.runetek3.Applet;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.graphics.Font;
import com.jagex.runetek3.util.Signlink;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Program extends Applet {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    static final int COLOR_BACKGROUND = 0x7F555555;
    static final int COLOR_TEXT = 0xFFFF00;

    Font p12 = null;
    Model model = null;
    int[] depth = new int[WIDTH * HEIGHT];

    public static void main(String[] args) throws UnknownHostException {
        Signlink.startpriv(InetAddress.getLocalHost());

        Program program = new Program();
        program.initFrame(HEIGHT, WIDTH, "Rasterizer");
    }

    @Override
    public void load() {
        drawArea.bind();

        showProgress("Loading title", 10);

        FileArchive title = new FileArchive(Signlink.cacheload("title", false));
        p12 = new Font(title, "p12");

        showProgress("Loading textures", 25);
        FileArchive textures = new FileArchive(Signlink.cacheload("textures", false));
        Draw3D.unpackTextures(textures);
        Draw3D.setBrightness(0.8D);
        Draw3D.setupPools(20);

        showProgress("Loading models", 50);
        FileArchive models = new FileArchive(Signlink.cacheload("models", false));
        Model.load(models);
        SeqSkeleton.load(models);
        SeqFrame.load(models);

        model = new Model(0);
        model.scale(256, 256, 256);
        model.applyLighting(64, 768, -50, -10, -50, true);
    }

    @Override
    public void unload() {
    }

    @Override
    public void update() {
    }

    public class Color3 {
        float r, g, b;

        public Color3(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public Color3(int r, int g, int b) {
            this.r = r / 255.0f;
            this.g = g / 255.0f;
            this.b = b / 255.0f;
        }

        public Color3(int rgb) {
            this.r = ((rgb >> 16) & 0xFF) / 255.0f;
            this.g = ((rgb >> 8) & 0xFF) / 255.0f;
            this.b = (rgb & 0xFF) / 255.0f;
        }

        public int toInt32() {
            int r = (int)(this.r * 255.0f);
            int g = (int)(this.g * 255.0f);
            int b = (int)(this.b * 255.0f);
            return (r << 16) | (g << 8) | b;
        }

        public Color3 add(Color3 c) {
            return new Color3(r + c.r, g + c.g, b + c.b);
        }

        public Color3 sub(Color3 c) {
            return new Color3(r - c.r, g - c.g, b - c.b);
        }

        public Color3 mul(float f) {
            return new Color3(r * f, g * f, b * f);
        }
    }

    public class Edge {
        Color3 color1, color2;
        int x1, y1, z1;
        int x2, y2, z2;
        int p1, p2;

        public Edge(Color3 color1, int x1, int y1, int z1, int p1, Color3 color2, int x2, int y2, int z2, int p2) {
            if (y1 < y2) {
                this.color1 = color1;
                this.x1 = x1;
                this.y1 = y1;
                this.z1 = z1;
                this.p1 = p1;
                this.color2 = color2;
                this.x2 = x2;
                this.y2 = y2;
                this.z2 = z2;
                this.p2 = p2;
            } else {
                this.color1 = color2;
                this.x1 = x2;
                this.y1 = y2;
                this.z1 = z2;
                this.p1 = p2;
                this.color2 = color1;
                this.x2 = x1;
                this.y2 = y1;
                this.z2 = z1;
                this.p2 = p1;
            }
        }
    }

    public class Span {
        Color3 color1, color2;
        int x1, x2;

        public Span(Color3 color1, int x1, Color3 color2, int x2) {
            if (x1 < x2) {
                this.color1 = color1;
                this.x1 = x1;
                this.color2 = color2;
                this.x2 = x2;
            } else {
                this.color1 = color2;
                this.x1 = x2;
                this.color2 = color1;
                this.x2 = x1;
            }
        }
    }

    void DrawSpan(Span span, int y, int z, int p) {
        int xdiff = span.x2 - span.x1;
        if (xdiff == 0) {
            return;
        }

        Color3 colordiff = span.color2.sub(span.color1);

        float factor = 0.0f;
        float factorStep = 1.0f / (float)xdiff;

        // draw each pixel in the span
        for(int x = span.x1; x < span.x2; x++) {
            int info = this.depth[x + y * WIDTH];
            int depth = info & 0xFFFF;
            int priority = info >> 16 & 0xFFFF;
            if (z < depth || p > priority) {
                this.depth[x + y * WIDTH] = (p << 16) | z;
                // Draw2D.set(x, y, span.color1.add(colordiff.mul(factor)).toInt32());
            }
            factor += factorStep;
        }
    }

    void DrawSpansBetweenEdges(Edge e1, Edge e2) {
        float e1ydiff = (float)(e1.y2 - e1.y1);
        if (e1ydiff == 0.0f) {
            return;
        }

        // calculate difference between the y coordinates
        // of the second edge and return if 0
        float e2ydiff = (float)(e2.y2 - e2.y1);
        if (e2ydiff == 0.0f) {
            return;
        }

        float e1zdiff = (float)(e1.z2 - e1.z1);
        if (e1zdiff == 0.0f) {
            return;
        }

        float e2zdiff = (float)(e2.z2 - e2.z1);
        if (e2zdiff == 0.0f) {
            return;
        }

        // calculate differences between the x coordinates
        // and colors of the points of the edges
        float e1xdiff = (float)(e1.x2 - e1.x1);
        float e2xdiff = (float)(e2.x2 - e2.x1);
        Color3 e1colordiff = e1.color2.sub(e1.color1);
        Color3 e2colordiff = e2.color2.sub(e2.color1);

        // calculate factors to use for interpolation
        // with the edges and the step values to increase
        // them by after drawing each span
        float factor1 = (float)(e2.y1 - e1.y1) / e1ydiff;
        float factorStep1 = 1.0f / e1ydiff;
        float factor2 = 0.0f;
        float factorStep2 = 1.0f / e2ydiff;

        // loop through the lines between the edges and draw spans
        for(int y = e2.y1; y < e2.y2; y++) {
            // create and draw span
            Span span = new Span(e1.color1.add(e1colordiff.mul(factor1)), e1.x1 + (int)(e1xdiff * factor1),
                e2.color1.add(e2colordiff.mul(factor2)), e2.x1 + (int)(e2xdiff * factor2));
            int z = Math.min(Math.min(e1.z1, e1.z2), Math.min(e2.z1, e2.z2));
            int p = Math.max(Math.max(e1.p1, e1.p2), Math.max(e2.p1, e2.p2));
            DrawSpan(span, y, z, p);

            // increase factors
            factor1 += factorStep1;
            factor2 += factorStep2;
        }
    }

    void DrawTriangle(Color3 colorA, int vertAx, int vertAy, int vertAz, int priorityA,
                      Color3 colorB, int vertBx, int vertBy, int vertBz, int priorityB,
                      Color3 colorC, int vertCx, int vertCy, int vertCz, int priorityC) {
        // create edges for the triangle
        Edge[] edges = new Edge[3];
        // point 1 to 2
        edges[0] = new Edge(colorA, vertAx, vertAy, vertAz, priorityA, colorB, vertBx, vertBy, vertBz, priorityB);
        // point 2 to 3
        edges[1] = new Edge(colorB, vertBx, vertBy, vertBz, priorityB, colorC, vertCx, vertCy, vertCz, priorityC);
        // point 3 to 1
        edges[2] = new Edge(colorC, vertCx, vertCy, vertCz, priorityC, colorA, vertAx, vertAy, vertAz, priorityA);

        int maxLength = 0;
        int longEdge = 0;

        // find edge with the greatest length in the y axis
        for(int i = 0; i < 3; i++) {
            int length = edges[i].y2 - edges[i].y1;
            if(length > maxLength) {
                maxLength = length;
                longEdge = i;
            }
        }

        int shortEdge1 = (longEdge + 1) % 3;
        int shortEdge2 = (longEdge + 2) % 3;

        // draw spans between edges; the long edge can be drawn
        // with the shorter edges to draw the full triangle
        DrawSpansBetweenEdges(edges[longEdge], edges[shortEdge1]);
        DrawSpansBetweenEdges(edges[longEdge], edges[shortEdge2]);
    }

    static final double M_PI_F = 3.14159265358979323846d;
    double r = 0.0d;
    long lastTicks = 0;

    @Override
    public void draw() {
        Draw2D.fillRect(0, 0, COLOR_BACKGROUND, Draw2D.width, Draw2D.height);
        Arrays.fill(depth, -1);

//        double size = 110.0f;
//        int x1 = (int)((WIDTH / 2.0f) + Math.cos(r - M_PI_F / 6.0f) * size);
//        int y1 = (int)((HEIGHT / 2.0f) + Math.sin(r - M_PI_F / 6.0f) * size);
//        int x2 = (int)((WIDTH / 2.0f) + Math.cos(r + M_PI_F / 2.0f) * size);
//        int y2 = (int)((HEIGHT / 2.0f) + Math.sin(r + M_PI_F / 2.0f) * size);
//        int x3 = (int)((WIDTH / 2.0f) + Math.cos(r + M_PI_F + M_PI_F / 6.0f) * size);
//        int y3 = (int)((HEIGHT / 2.0f) + Math.sin(r + M_PI_F + M_PI_F / 6.0f) * size);
//
//        DrawTriangle(new Color3(0xFF0000), x1, y1, new Color3(0x00FF00), x2, y2, new Color3(0x0000FF), x3, y3);
//
//        long ticks = System.currentTimeMillis();
//        long ticksDiff = ticks - lastTicks;
//        if (ticksDiff != 0) {
//            double time = ticksDiff / 1000.0d;
//            r += (M_PI_F / 2.0f) * time;
//            lastTicks = ticks;
//        }

        for (int t = 0; t < model.triangleCount; ++t) {
            int tA = model.triangleVertexA[t];
            int vAx = model.vertexX[tA] + 256;
            int vAy = model.vertexY[tA] + 256;
            int vAz = model.vertexZ[tA] + 256;
            int pA = model.trianglePriorities[t];

            int tB = model.triangleVertexB[t];
            int vBx = model.vertexX[tB] + 256;
            int vBy = model.vertexY[tB] + 256;
            int vBz = model.vertexZ[tB] + 256;
            int pB = model.trianglePriorities[t];

            int tC = model.triangleVertexC[t];
            int vCx = model.vertexX[tC] + 256;
            int vCy = model.vertexY[tC] + 256;
            int vCz = model.vertexZ[tC] + 256;
            int pC = model.trianglePriorities[t];

            DrawTriangle(new Color3(Draw3D.palette[model.colorA[t]]), vAx, vAy, vAz, pA,
                new Color3(Draw3D.palette[model.colorB[t]]), vBx, vBy, vBz, pB,
                new Color3(Draw3D.palette[model.colorC[t]]), vCx, vCy, vCz, pC);
        }

        int y = p12.height;
        p12.draw(3, y, COLOR_TEXT, "FPS: " + fps, true);
        drawArea.drawImage(0, graphics, 0);
    }

    @Override
    public void refresh() {
    }
}

/*Draw2D.fillTriangle(
    tA, tB, tC,
    vAx, vAy, vAz,
    vBx, vBy, vBz,
    vCx, vCy, vCz,
    Draw3D.palette[model.colorA[t]], Draw3D.palette[model.colorB[t]], Draw3D.palette[model.colorC[t]]
);*/