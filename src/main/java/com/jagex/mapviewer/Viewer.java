package com.jagex.mapviewer;

import com.jagex.mapviewer.graphics.DrawText;
import com.jagex.mapviewer.util.Signature;
import com.jagex.runetek3.GameShell;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.graphics.Draw2D;
import com.jagex.runetek3.graphics.IndexedFontFull;
import com.jagex.runetek3.graphics.IndexedSprite;
import com.jagex.runetek3.graphics.Sprite;
import com.jagex.runetek3.util.Buffer;
import com.jagex.runetek3.util.Signlink;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;

public class Viewer extends GameShell {

    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        viewer.initFrame(503, 633);
    }

    @Override
    public void init() {
        initApplet(503, 633);
    }

    public void load() {
        FileArchive worldmap = loadData();
        showProgress("Please wait... Rendering Map", 100);

        Buffer buffer = new Buffer(worldmap.read("size.dat", null));
        centerX = buffer.readWord();
        centerY = buffer.readWord();
        originX = buffer.readWord();
        originY = buffer.readWord();

        offsetX = 3200 - centerX;
        offsetY = (centerY + originY) - 3200;

        imageOverviewHeight = 180;
        imageOverviewWidth = (originX * imageOverviewHeight) / originY;
        overviewX = super.gameWidth - imageOverviewWidth - 5;
        overviewY = super.gameHeight - imageOverviewHeight - 20;

        buffer = new Buffer(worldmap.read("labels.dat", null));
        labelCount = buffer.readWord();
        for (int n = 0; n < labelCount; n++) {
            labelText[n] = buffer.readString();
            labelX[n] = buffer.readWord();
            labelY[n] = buffer.readWord();
            labelType[n] = buffer.readByte();
        }

        buffer = new Buffer(worldmap.read("floorcol.dat", null));
        int floorcolCount = buffer.readWord();
        floormapsUnderlay = new int[floorcolCount + 1];
        floormapsOverlay = new int[floorcolCount + 1];
        for (int n = 0; n < floorcolCount; n++) {
            floormapsUnderlay[n + 1] = buffer.readDWord();
            floormapsOverlay[n + 1] = buffer.readDWord();
        }

        byte[] underlay = worldmap.read("underlay.dat", null);
        byte[][] underlays = new byte[originX][originY];
        decodeUnderlay(underlay, underlays);

        byte[] overlay = worldmap.read("overlay.dat", null);
        overlayColors = new int[originX][originY];
        overlayTypes = new byte[originX][originY];
        decodeOverlay(overlay, overlayColors, overlayTypes);

        byte[] loc = worldmap.read("loc.dat", null);
        locWalls = new byte[originX][originY];
        locScenes = new byte[originX][originY];
        locIcons = new byte[originX][originY];
        decodeLoc(loc, locWalls, locScenes, locIcons);

        try {
            for (int n = 0; n < 100; n++) {
                mapscenes[n] = new IndexedSprite(worldmap, "mapscene", n);
            }
        } catch (Exception exception) {
        }

        try {
            for (int n = 0; n < 100; n++) {
                mapfunctions[n] = new Sprite(worldmap, "mapfunction", n);
            }
        } catch (Exception exception1) {
        }

        b12 = new IndexedFontFull(worldmap, "b12_full", false);
        f11 = new DrawText(11, true, this);
        f12 = new DrawText(12, true, this);
        f14 = new DrawText(14, true, this);
        f17 = new DrawText(17, true, this);
        f19 = new DrawText(19, true, this);
        f22 = new DrawText(22, true, this);
        f26 = new DrawText(26, true, this);
        f30 = new DrawText(30, true, this);

        floormapColors = new int[originX][originY];
        averageUnderlayColors(underlays, floormapColors);

        imageOverview = new Sprite(imageOverviewWidth, imageOverviewHeight);
        imageOverview.prepare();
        drawMap(0, 0, originX, originY, 0, 0, imageOverviewWidth, imageOverviewHeight);
        Draw2D.drawRect(0, 0, imageOverviewHeight, 0, imageOverviewWidth);
        Draw2D.drawRect(1, colorInactiveBorderTL, imageOverviewHeight - 2, 1, imageOverviewWidth - 2);
        super.drawArea.bind();
    }

    private void decodeLoc(byte[] data, byte[][] walls, byte[][] mapscene, byte[][] icons) {
        for (int k = 0; k < data.length; ) {
            int i1 = (data[k++] & 0xff) * 64 - centerX;
            int j1 = (data[k++] & 0xff) * 64 - centerY;
            if (i1 > 0 && j1 > 0 && i1 + 64 < originX && j1 + 64 < originY) {
                int k1 = 0;
                while (k1 < 64) {
                    byte[] wall = walls[k1 + i1];
                    byte[] scene = mapscene[k1 + i1];
                    byte[] icon = icons[k1 + i1];
                    int j2 = originY - j1 - 1;
                    for (int k2 = -64; k2 < 0; k2++) {
                        do {
                            int l2 = data[k++] & 0xff;
                            if (l2 == 0)
                                break;
                            if (l2 < 29)
                                wall[j2] = (byte) l2;
                            else if (l2 < 160) {
                                scene[j2] = (byte) (l2 - 28);
                            } else {
                                icon[j2] = (byte) (l2 - 159);
                                mapIconsX[mapIconCount] = k1 + i1;
                                mapIconsY[mapIconCount] = j2;
                                mapIcons[mapIconCount] = l2 - 160;
                                mapIconCount++;
                            }
                        } while (true);
                        j2--;
                    }

                    k1++;
                }
            } else {
                int l1 = 0;
                while (l1 < 64) {
                    for (int i2 = -64; i2 < 0; i2++) {
                        byte byte0;
                        do
                            byte0 = data[k++];
                        while (byte0 != 0);
                    }

                    l1++;
                }
            }
        }
    }

    private void decodeUnderlay(byte[] data, byte[][] underlays) {
        for (int k = 0; k < data.length; ) {
            int i1 = (data[k++] & 0xff) * 64 - centerX;
            int j1 = (data[k++] & 0xff) * 64 - centerY;
            if (i1 > 0 && j1 > 0 && i1 + 64 < originX && j1 + 64 < originY) {
                for (int k1 = 0; k1 < 64; k1++) {
                    byte[] underlay = underlays[k1 + i1];
                    int l1 = originY - j1 - 1;
                    for (int i2 = -64; i2 < 0; i2++) {
                        underlay[l1--] = data[k++];
                    }
                }
            } else {
                k += 4096;
            }
        }
    }

    private void decodeOverlay(byte[] data, int[][] colors, byte[][] types) {
        for (int k = 0; k < data.length; ) {
            int i1 = (data[k++] & 0xff) * 64 - centerX;
            int j1 = (data[k++] & 0xff) * 64 - centerY;
            if (i1 > 0 && j1 > 0 && i1 + 64 < originX && j1 + 64 < originY) {
                int k1 = 0;
                while (k1 < 64) {
                    int[] ai1 = colors[k1 + i1];
                    byte[] abyte2 = types[k1 + i1];
                    int i2 = originY - j1 - 1;
                    for (int j2 = -64; j2 < 0; j2++) {
                        byte byte1 = data[k++];
                        if (byte1 != 0) {
                            abyte2[i2] = data[k++];
                            ai1[i2--] = floormapsOverlay[byte1];
                        } else {
                            ai1[i2--] = 0;
                        }
                    }

                    k1++;
                }
            } else {
                int l1 = -4096;
                while (l1 < 0) {
                    byte byte0 = data[k++];
                    if (byte0 != 0)
                        k++;
                    l1++;
                }
            }
        }

    }

    private void averageUnderlayColors(byte[][] underlays, int[][] underlayColors) {
        int k = originX;
        int i1 = originY;

        int[] colors = new int[i1];
        for (int y = 0; y < i1; y++) {
            colors[y] = 0;
        }

        for (int k1 = 5; k1 < k - 5; k1++) {
            byte[] tile1 = underlays[k1 + 5];
            byte[] tile2 = underlays[k1 - 5];
            for (int l1 = 0; l1 < i1; l1++) {
                colors[l1] += floormapsUnderlay[tile1[l1] & 0xff] - floormapsUnderlay[tile2[l1] & 0xff];
            }

            if (k1 <= 10 || k1 >= k - 10) {
                continue;
            }

            int h = 0;
            int s = 0;
            int l = 0;
            int[] samples = underlayColors[k1];
            for (int l2 = 5; l2 < i1 - 5; l2++) {
                int color1 = colors[l2 - 5];
                int color2 = colors[l2 + 5];
                h += (color2 >> 20) - (color1 >> 20);
                s += (color2 >> 10 & 0x3ff) - (color1 >> 10 & 0x3ff);
                l += (color2 & 0x3ff) - (color1 & 0x3ff);
                if (l > 0) {
                    samples[l2] = convertHSL((double) h / 8533D, (double) s / 8533D, (double) l / 8533D);
                }
            }
        }
    }

    private int convertHSL(double hue, double saturation, double lightness) {
        double r = lightness;
        double g = lightness;
        double b = lightness;

        if (saturation != 0.0D) {
            double q;
            if (lightness < 0.5D) {
                q = lightness * (1.0D + saturation);
            } else {
                q = (lightness + saturation) - lightness * saturation;
            }

            double p = 2D * lightness - q;
            double t = hue + 0.33333333333333331D;
            if (t > 1.0D) {
                t--;
            }

            double d10 = hue - 0.33333333333333331D;
            if (d10 < 0.0D) {
                d10++;
            }

            if (6D * t < 1.0D) {
                r = p + (q - p) * 6D * t;
            } else if (2D * t < 1.0D) {
                r = q;
            } else if (3D * t < 2D) {
                r = p + (q - p) * (0.66666666666666663D - t) * 6D;
            } else {
                r = p;
            }

            if (6D * hue < 1.0D) {
                g = p + (q - p) * 6D * hue;
            } else if (2D * hue < 1.0D) {
                g = q;
            } else if (3D * hue < 2D) {
                g = p + (q - p) * (0.66666666666666663D - hue) * 6D;
            } else {
                g = p;
            }

            if (6D * d10 < 1.0D) {
                b = p + (q - p) * 6D * d10;
            } else if (2D * d10 < 1.0D) {
                b = q;
            } else if (3D * d10 < 2D) {
                b = p + (q - p) * (0.66666666666666663D - d10) * 6D;
            } else {
                b = p;
            }
        }

        int intR = (int) (r * 256D);
        int intG = (int) (g * 256D);
        int intB = (int) (b * 256D);
        return (intR << 16) + (intG << 8) + intB;
    }

    public void unload() {
        try {
            floormapsUnderlay = null;
            floormapsOverlay = null;
            floormapColors = null;
            overlayColors = null;
            overlayTypes = null;
            locWalls = null;
            locIcons = null;
            locScenes = null;
            mapscenes = null;
            mapfunctions = null;
            b12 = null;
            visibleMapIconsX = null;
            visibleMapIconsY = null;
            visibleMapIcons = null;
            mapIconsX = null;
            mapIconsY = null;
            mapIcons = null;
            imageOverview = null;
            labelText = null;
            labelX = null;
            labelY = null;
            labelType = null;
            keyNames = null;
            System.gc();
            return;
        } catch (Throwable throwable) {
            return;
        }
    }

    public void update() {
        if (super.keyDown[1] == 1) {
            offsetX = (int) ((double) offsetX - 16D / zoomX);
            shouldDraw = true;
        }
        if (super.keyDown[2] == 1) {
            offsetX = (int) ((double) offsetX + 16D / zoomX);
            shouldDraw = true;
        }
        if (super.keyDown[3] == 1) {
            offsetY = (int) ((double) offsetY - 16D / zoomX);
            shouldDraw = true;
        }
        if (super.keyDown[4] == 1) {
            offsetY = (int) ((double) offsetY + 16D / zoomX);
            shouldDraw = true;
        }
        int k = 1;
        do {
            if (k <= 0)
                break;
            k = pollKey();
            if (k == 49) {
                zoomY = 3D;
                shouldDraw = true;
            }
            if (k == 50) {
                zoomY = 4D;
                shouldDraw = true;
            }
            if (k == 51) {
                zoomY = 6D;
                shouldDraw = true;
            }
            if (k == 52) {
                zoomY = 8D;
                shouldDraw = true;
            }
            if (k == 107 || k == 75) {
                showKey = !showKey;
                shouldDraw = true;
            }
            if (k == 111 || k == 79) {
                showOverview = !showOverview;
                shouldDraw = true;
            }
            if (super.frame != null && k == 101) {
                System.out.println("Starting export...");
                Sprite g1 = new Sprite(originX * 2, originY * 2);
                g1.prepare();
                drawMap(0, 0, originX, originY, 0, 0, originX * 2, originY * 2);
                super.drawArea.bind();
                int l1 = g1.pixels.length;
                byte[] abyte0 = new byte[l1 * 3];
                int k2 = 0;
                for (int l2 = 0; l2 < l1; l2++) {
                    int i3 = g1.pixels[l2];
                    abyte0[k2++] = (byte) (i3 >> 16);
                    abyte0[k2++] = (byte) (i3 >> 8);
                    abyte0[k2++] = (byte) i3;
                }

                System.out.println("Saving to disk");
                try {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((new StringBuilder()).append("map-").append(originX * 2).append("-").append(originY * 2).append("-rgb.raw").toString()));
                    stream.write(abyte0);
                    stream.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println((new StringBuilder()).append("Done export: ").append(originX * 2).append(",").append(originY * 2));
            }
        } while (true);

        if (super.mouseButton == 1) {
            mouseClickX = super.clickX;
            mouseClickY = super.clickY;
            lastOffsetX = offsetX;
            lastOffsetY = offsetY;
            if (super.clickX > 170 && super.clickX < 220 && super.clickY > 471 && super.clickY < 503) {
                zoomY = 3D;
                mouseClickX = -1;
            }
            if (super.clickX > 230 && super.clickX < 280 && super.clickY > 471 && super.clickY < 503) {
                zoomY = 4D;
                mouseClickX = -1;
            }
            if (super.clickX > 290 && super.clickX < 340 && super.clickY > 471 && super.clickY < 503) {
                zoomY = 6D;
                mouseClickX = -1;
            }
            if (super.clickX > 350 && super.clickX < 400 && super.clickY > 471 && super.clickY < 503) {
                zoomY = 8D;
                mouseClickX = -1;
            }
            if (super.clickX > keyX && super.clickY > keyY + keyOffset && super.clickX < keyX + keyWidth && super.clickY < 503) {
                showKey = !showKey;
                mouseClickX = -1;
            }
            if (super.clickX > overviewX && super.clickY > overviewY + imageOverviewHeight && super.clickX < overviewX + imageOverviewWidth && super.clickY < 503) {
                showOverview = !showOverview;
                mouseClickX = -1;
            }
            if (showKey) {
                if (super.clickX > keyX && super.clickY > keyY && super.clickX < keyX + keyWidth && super.clickY < keyY + keyOffset)
                    mouseClickX = -1;
                if (super.clickX > keyX && super.clickY > keyY && super.clickX < keyX + keyWidth && super.clickY < keyY + 18 && keyPage > 0)
                    keyPage -= 25;
                if (super.clickX > keyX && super.clickY > (keyY + keyOffset) - 18 && super.clickX < keyX + keyWidth && super.clickY < keyY + keyOffset && keyPage < 50)
                    keyPage += 25;
            }
            shouldDraw = true;
        }

        if (showKey) {
            currentKeyHover = -1;
            if (super.mouseX > keyX && super.mouseX < keyX + keyWidth) {
                k = keyY + 21 + 5;
                for (int i1 = 0; i1 < 25; i1++) {
                    if (i1 + lastKeyPage < keyNames.length && keyNames[i1 + lastKeyPage].equals("???"))
                        continue;
                    if (super.mouseY >= k && super.mouseY < k + 17) {
                        currentKeyHover = i1 + lastKeyPage;
                        if (super.mouseButton == 1) {
                            currentKey = i1 + lastKeyPage;
                            flashTimer = 50;
                        }
                    }
                    k += 17;
                }
            }

            if (currentKeyHover != lastKeyHover) {
                lastKeyHover = currentKeyHover;
                shouldDraw = true;
            }
        }

        if ((super.dragButton == 1 || super.mouseButton == 1) && showOverview) {
            k = super.clickX;
            int j1 = super.clickY;
            if (super.dragButton == 1) {
                k = super.mouseX;
                j1 = super.mouseY;
            }
            if (k > overviewX && j1 > overviewY && k < overviewX + imageOverviewWidth && j1 < overviewY + imageOverviewHeight) {
                offsetX = ((k - overviewX) * originX) / imageOverviewWidth;
                offsetY = ((j1 - overviewY) * originY) / imageOverviewHeight;
                mouseClickX = -1;
                shouldDraw = true;
            }
        }

        if (super.dragButton == 1 && mouseClickX != -1) {
            offsetX = lastOffsetX + (int) (((double) (mouseClickX - super.mouseX) * 2D) / zoomY);
            offsetY = lastOffsetY + (int) (((double) (mouseClickY - super.mouseY) * 2D) / zoomY);
            shouldDraw = true;
        }

        if (zoomX < zoomY) {
            shouldDraw = true;
            zoomX += zoomX / 30D;
            if (zoomX > zoomY)
                zoomX = zoomY;
        }

        if (zoomX > zoomY) {
            shouldDraw = true;
            zoomX -= zoomX / 30D;
            if (zoomX < zoomY)
                zoomX = zoomY;
        }

        if (lastKeyPage < keyPage) {
            shouldDraw = true;
            lastKeyPage++;
        }

        if (lastKeyPage > keyPage) {
            shouldDraw = true;
            lastKeyPage--;
        }

        if (flashTimer > 0) {
            shouldDraw = true;
            flashTimer--;
        }

        k = offsetX - (int) (635D / zoomX);
        int k1 = offsetY - (int) (503D / zoomX);
        int i2 = offsetX + (int) (635D / zoomX);
        int j2 = offsetY + (int) (503D / zoomX);
        if (k < 48)
            offsetX = 48 + (int) (635D / zoomX);
        if (k1 < 48)
            offsetY = 48 + (int) (503D / zoomX);
        if (i2 > originX - 48)
            offsetX = originX - 48 - (int) (635D / zoomX);
        if (j2 > originY - 48)
            offsetY = originY - 48 - (int) (503D / zoomX);
    }

    public void draw() {
        if (shouldDraw) {
            shouldDraw = false;
            drawTimer = 0;

            Draw2D.clear();
            int k = offsetX - (int) (635D / zoomX);
            int i1 = offsetY - (int) (503D / zoomX);
            int j1 = offsetX + (int) (635D / zoomX);
            int k1 = offsetY + (int) (503D / zoomX);
            drawMap(k, i1, j1, k1, 0, 0, 635, 503);

            if (showOverview) {
                imageOverview.drawOpaque(overviewX, overviewY);
                Draw2D.fillRect(overviewX + (imageOverviewWidth * k) / originX, overviewY + (imageOverviewHeight * i1) / originY, ((j1 - k) * imageOverviewWidth) / originX, ((k1 - i1) * imageOverviewHeight) / originY, 0xff0000, 128);
                Draw2D.drawRect(overviewX + (imageOverviewWidth * k) / originX, 0xff0000, ((k1 - i1) * imageOverviewHeight) / originY, overviewY + (imageOverviewHeight * i1) / originY, ((j1 - k) * imageOverviewWidth) / originX);
                if (flashTimer > 0 && flashTimer % 10 < 5) {
                    for (int l1 = 0; l1 < mapIconCount; l1++) {
                        if (mapIcons[l1] == currentKey) {
                            int j2 = overviewX + (imageOverviewWidth * mapIconsX[l1]) / originX;
                            int l2 = overviewY + (imageOverviewHeight * mapIconsY[l1]) / originY;
                            Draw2D.fillCircle(j2, l2, 2, 0xffff00, 256);
                        }
                    }
                }
            }

            if (showKey) {
                drawString(keyX, keyY, keyWidth, 18, 0x999999, 0x777777, 0x555555, "Prev page");
                drawString(keyX, keyY + 18, keyWidth, keyOffset - 36, 0x999999, 0x777777, 0x555555, "");
                drawString(keyX, (keyY + keyOffset) - 18, keyWidth, 18, 0x999999, 0x777777, 0x555555, "Next page");
                int i2 = keyY + 3 + 18;
                for (int k2 = 0; k2 < 25; k2++) {
                    if (k2 + lastKeyPage < mapfunctions.length && k2 + lastKeyPage < keyNames.length) {
                        if (keyNames[k2 + lastKeyPage].equals("???"))
                            continue;
                        mapfunctions[k2 + lastKeyPage].draw(i2, keyX + 3);
                        b12.drawString(keyNames[k2 + lastKeyPage], keyX + 21, i2 + 14, 0);
                        int i3 = 0xffffff;
                        if (currentKeyHover == k2 + lastKeyPage)
                            i3 = 0xbbaaaa;
                        if (flashTimer > 0 && flashTimer % 10 < 5 && currentKey == k2 + lastKeyPage)
                            i3 = 0xffff00;
                        b12.drawString(keyNames[k2 + lastKeyPage], keyX + 20, i2 + 13, i3);
                    }
                    i2 += 17;
                }
            }

            drawString(overviewX, overviewY + imageOverviewHeight, imageOverviewWidth, 18, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "Overview");
            drawString(keyX, keyY + keyOffset, keyWidth, 18, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "Key");

            if (zoomY == 3D)
                drawString(170, 471, 50, 30, colorActiveBorderTL, colorActive, colorActiveBorderBR, "37%");
            else
                drawString(170, 471, 50, 30, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "37%");

            if (zoomY == 4D)
                drawString(230, 471, 50, 30, colorActiveBorderTL, colorActive, colorActiveBorderBR, "50%");
            else
                drawString(230, 471, 50, 30, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "50%");

            if (zoomY == 6D)
                drawString(290, 471, 50, 30, colorActiveBorderTL, colorActive, colorActiveBorderBR, "75%");
            else
                drawString(290, 471, 50, 30, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "75%");

            if (zoomY == 8D)
                drawString(350, 471, 50, 30, colorActiveBorderTL, colorActive, colorActiveBorderBR, "100%");
            else
                drawString(350, 471, 50, 30, colorInactiveBorderTL, colorInactive, colorInactiveBorderBR, "100%");
        }

        drawTimer--;
        if (drawTimer <= 0) {
            super.drawArea.drawImage(0, super.graphics, 0);
            drawTimer = 50;
        }
    }

    public void refresh() {
        drawTimer = 0;
    }

    private void drawString(int k, int i1, int j1, int k1, int l1, int i2, int j2,
                            String s) {
        Draw2D.drawRect(k, 0, k1, i1, j1);
        k++;
        i1++;
        j1 -= 2;
        k1 -= 2;
        Draw2D.fillRect(i1, k, i2, j1, k1);
        Draw2D.drawHorizontalLine(l1, i1, j1, k);
        Draw2D.drawVerticalLine(l1, i1, k1, k);
        Draw2D.drawHorizontalLine(j2, (i1 + k1) - 1, j1, k);
        Draw2D.drawVerticalLine(j2, i1, k1, (k + j1) - 1);
        b12.drawStringCenter(s, k + j1 / 2 + 1, i1 + k1 / 2 + 1 + 4, 0);
        b12.drawStringCenter(s, k + j1 / 2, i1 + k1 / 2 + 4, 0xffffff);
    }

    private void drawMap(int k, int i1, int j1, int k1, int l1, int i2, int j2,
                         int k2) {
        int l2 = j1 - k;
        int i3 = k1 - i1;
        int j3 = (j2 - l1 << 16) / l2;
        int k3 = (k2 - i2 << 16) / i3;
        for (int l3 = 0; l3 < l2; l3++) {
            int j4 = j3 * l3 >> 16;
            int l5 = j3 * (l3 + 1) >> 16;
            int l6 = l5 - j4;
            if (l6 <= 0)
                continue;
            j4 += l1;
            l5 += l1;
            int[] ai = floormapColors[l3 + k];
            int[] ai1 = overlayColors[l3 + k];
            byte[] abyte1 = overlayTypes[l3 + k];
            for (int k9 = 0; k9 < i3; k9++) {
                int j10 = k3 * k9 >> 16;
                int i11 = k3 * (k9 + 1) >> 16;
                int k11 = i11 - j10;
                if (k11 <= 0)
                    continue;
                j10 += i2;
                i11 += i2;
                int j12 = ai1[k9 + i1];
                if (j12 == 0) {
                    Draw2D.fillRect(j10, j4, ai[k9 + i1], l5 - j4, i11 - j10);
                    continue;
                }
                byte byte0 = abyte1[k9 + i1];
                int k13 = byte0 & 0xfc;
                if (k13 == 0 || l6 <= 1 || k11 <= 1)
                    Draw2D.fillRect(j10, j4, j12, l6, k11);
                else
                    drawSmoothEdges(Draw2D.dest, j10 * Draw2D.width + j4, ai[k9 + i1], j12, l6, k11, k13 >> 2, byte0 & 3);
            }

        }

        if (j1 - k > j2 - l1)
            return;
        int i4 = 0;
        for (int k4 = 0; k4 < l2; k4++) {
            int i6 = j3 * k4 >> 16;
            int i7 = j3 * (k4 + 1) >> 16;
            int l7 = i7 - i6;
            if (l7 <= 0)
                continue;
            byte[] abyte0 = locWalls[k4 + k];
            byte[] mapscene = locScenes[k4 + k];
            byte[] abyte3 = locIcons[k4 + k];
            for (int k10 = 0; k10 < i3; k10++) {
                int j11 = k3 * k10 >> 16;
                int l11 = k3 * (k10 + 1) >> 16;
                int k12 = l11 - j11;
                if (k12 <= 0)
                    continue;
                int wall = abyte0[k10 + i1] & 0xff;
                if (wall != 0) {
                    int l13;
                    if (l7 == 1)
                        l13 = i6;
                    else
                        l13 = i7 - 1;
                    int j14;
                    if (k12 == 1)
                        j14 = j11;
                    else
                        j14 = l11 - 1;
                    int l14 = 0xcccccc;
                    if (wall >= 5 && wall <= 8 || wall >= 13 && wall <= 16 || wall >= 21 && wall <= 24 || wall == 27 || wall == 28) {
                        l14 = 0xcc0000;
                        wall -= 4;
                    }
                    if (wall == 1)
                        Draw2D.drawVerticalLine(l14, j11, k12, i6);
                    else if (wall == 2)
                        Draw2D.drawHorizontalLine(l14, j11, l7, i6);
                    else if (wall == 3)
                        Draw2D.drawVerticalLine(l14, j11, k12, l13);
                    else if (wall == 4)
                        Draw2D.drawHorizontalLine(l14, j14, l7, i6);
                    else if (wall == 9) {
                        Draw2D.drawVerticalLine(0xffffff, j11, k12, i6);
                        Draw2D.drawHorizontalLine(l14, j11, l7, i6);
                    } else if (wall == 10) {
                        Draw2D.drawVerticalLine(0xffffff, j11, k12, l13);
                        Draw2D.drawHorizontalLine(l14, j11, l7, i6);
                    } else if (wall == 11) {
                        Draw2D.drawVerticalLine(0xffffff, j11, k12, l13);
                        Draw2D.drawHorizontalLine(l14, j14, l7, i6);
                    } else if (wall == 12) {
                        Draw2D.drawVerticalLine(0xffffff, j11, k12, i6);
                        Draw2D.drawHorizontalLine(l14, j14, l7, i6);
                    } else if (wall == 17)
                        Draw2D.drawHorizontalLine(l14, j11, 1, i6);
                    else if (wall == 18)
                        Draw2D.drawHorizontalLine(l14, j11, 1, l13);
                    else if (wall == 19)
                        Draw2D.drawHorizontalLine(l14, j14, 1, l13);
                    else if (wall == 20)
                        Draw2D.drawHorizontalLine(l14, j14, 1, i6);
                    else if (wall == 25) {
                        for (int i15 = 0; i15 < k12; i15++)
                            Draw2D.drawHorizontalLine(l14, j14 - i15, 1, i6 + i15);

                    } else if (wall == 26) {
                        for (int j15 = 0; j15 < k12; j15++)
                            Draw2D.drawHorizontalLine(l14, j11 + j15, 1, i6 + j15);
                    }
                }

                int scene = mapscene[k10 + i1] & 0xff;
                if (scene != 0)
                    mapscenes[scene - 1].clip(i6 - l7 / 2, j11 - k12 / 2, l7 * 2, k12 * 2);

                int icon = abyte3[k10 + i1] & 0xff;
                if (icon != 0) {
                    visibleMapIcons[i4] = icon - 1;
                    visibleMapIconsX[i4] = i6 + l7 / 2;
                    visibleMapIconsY[i4] = j11 + k12 / 2;
                    i4++;
                }
            }

        }

        for (int l4 = 0; l4 < i4; l4++) {
            if (mapfunctions[visibleMapIcons[l4]] != null) {
                mapfunctions[visibleMapIcons[l4]].draw(visibleMapIconsY[l4] - 7, visibleMapIconsX[l4] - 7);
            }
        }

        if (flashTimer > 0) {
            for (int i5 = 0; i5 < i4; i5++) {
                if (visibleMapIcons[i5] != currentKey) {
                    continue;
                }

                mapfunctions[visibleMapIcons[i5]].draw(visibleMapIconsY[i5] - 7, visibleMapIconsX[i5] - 7);
                if (flashTimer % 10 < 5) {
                    Draw2D.fillCircle(visibleMapIconsX[i5], visibleMapIconsY[i5], 15, 0xffff00, 128);
                    Draw2D.fillCircle(visibleMapIconsX[i5], visibleMapIconsY[i5], 7, 0xffffff, 256);
                }
            }
        }

        if (zoomX == zoomY && shouldDrawLabels) {
            for (int j5 = 0; j5 < labelCount; j5++) {
                int j6 = labelX[j5];
                int j7 = labelY[j5];
                j6 -= centerX;
                j7 = (centerY + originY) - j7;
                int i8 = l1 + ((j2 - l1) * (j6 - k)) / (j1 - k);
                int k8 = i2 + ((k2 - i2) * (j7 - i1)) / (k1 - i1);
                int i9 = labelType[j5];
                int l9 = 0xffffff;
                DrawText f1 = null;
                if (i9 == 0) {
                    if (zoomX == 3D)
                        f1 = this.f11;
                    if (zoomX == 4D)
                        f1 = f12;
                    if (zoomX == 6D)
                        f1 = f14;
                    if (zoomX == 8D)
                        f1 = f17;
                } else if (i9 == 1) {
                    if (zoomX == 3D)
                        f1 = f14;
                    if (zoomX == 4D)
                        f1 = f17;
                    if (zoomX == 6D)
                        f1 = f19;
                    if (zoomX == 8D)
                        f1 = f22;
                } else if (i9 == 2) {
                    l9 = 0xffaa00;
                    if (zoomX == 3D)
                        f1 = f19;
                    if (zoomX == 4D)
                        f1 = f22;
                    if (zoomX == 6D)
                        f1 = f26;
                    if (zoomX == 8D)
                        f1 = f30;
                }

                if (f1 == null) {
                    continue;
                }

                String s = labelText[j5];
                int i12 = 1;
                for (int l12 = 0; l12 < s.length(); l12++) {
                    if (s.charAt(l12) == '/') {
                        i12++;
                    }
                }

                k8 -= (f1.aib() * (i12 - 1)) / 2;
                k8 += f1.aig() / 2;

                do {
                    int i13 = s.indexOf("/");
                    if (i13 == -1) {
                        f1.ahn(s, i8, k8, l9, true);
                        break;
                    }
                    String s1 = s.substring(0, i13);
                    f1.ahn(s1, i8, k8, l9, true);
                    k8 += f1.aib();
                    s = s.substring(i13 + 1);
                } while (true);
            }
        }
        if (shouldDrawDebug) {
            for (int k5 = centerX / 64; k5 < (centerX + originX) / 64; k5++) {
                for (int k6 = centerY / 64; k6 < (centerY + originY) / 64; k6++) {
                    int k7 = k5 * 64;
                    int j8 = k6 * 64;
                    k7 -= centerX;
                    j8 = (centerY + originY) - j8;
                    int l8 = l1 + ((j2 - l1) * (k7 - k)) / (j1 - k);
                    int j9 = i2 + ((k2 - i2) * (j8 - 64 - i1)) / (k1 - i1);
                    int i10 = l1 + ((j2 - l1) * ((k7 + 64) - k)) / (j1 - k);
                    int l10 = i2 + ((k2 - i2) * (j8 - i1)) / (k1 - i1);
                    Draw2D.drawRect(l8, 0xffffff, l10 - j9, j9, i10 - l8);
                    b12.drawStringRight((new StringBuilder()).append(k5).append("_").append(k6).toString(), i10 - 5, l10 - 5, 0xffffff);
                    if (k5 == 33 && k6 >= 71 && k6 <= 73) {
                        b12.drawStringCenter("u_pass", (i10 + l8) / 2, (l10 + j9) / 2, 0xff0000);
                    } else if (k5 >= 32 && k5 <= 34 && k6 >= 70 && k6 <= 74) {
                        b12.drawStringCenter("u_pass", (i10 + l8) / 2, (l10 + j9) / 2, 0xffff00);
                    }
                }
            }
        }
    }

    private void drawSmoothEdges(int[] ai, int k, int i1, int j1, int k1, int l1, int i2,
                                 int j2) {
        int k2 = Draw2D.width - k1;
        if (i2 == 9) {
            i2 = 1;
            j2 = j2 + 1 & 3;
        }
        if (i2 == 10) {
            i2 = 1;
            j2 = j2 + 3 & 3;
        }
        if (i2 == 11) {
            i2 = 8;
            j2 = j2 + 3 & 3;
        }
        if (i2 == 1) {
            if (j2 == 0) {
                for (int l2 = 0; l2 < l1; l2++) {
                    for (int l10 = 0; l10 < k1; l10++)
                        if (l10 <= l2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i3 = l1 - 1; i3 >= 0; i3--) {
                    for (int i11 = 0; i11 < k1; i11++)
                        if (i11 <= i3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j3 = 0; j3 < l1; j3++) {
                    for (int j11 = 0; j11 < k1; j11++)
                        if (j11 >= j3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k3 = l1 - 1; k3 >= 0; k3--) {
                    for (int k11 = 0; k11 < k1; k11++)
                        if (k11 >= k3)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else {
                return;
            }
        }
        if (i2 == 2) {
            if (j2 == 0) {
                for (int l3 = l1 - 1; l3 >= 0; l3--) {
                    for (int l11 = 0; l11 < k1; l11++)
                        if (l11 <= l3 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i4 = 0; i4 < l1; i4++) {
                    for (int i12 = 0; i12 < k1; i12++)
                        if (i12 >= i4 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j4 = 0; j4 < l1; j4++) {
                    for (int j12 = k1 - 1; j12 >= 0; j12--)
                        if (j12 <= j4 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k4 = l1 - 1; k4 >= 0; k4--) {
                    for (int k12 = k1 - 1; k12 >= 0; k12--)
                        if (k12 >= k4 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else {
                return;
            }
        }
        if (i2 == 3) {
            if (j2 == 0) {
                for (int l4 = l1 - 1; l4 >= 0; l4--) {
                    for (int l12 = k1 - 1; l12 >= 0; l12--)
                        if (l12 <= l4 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i5 = l1 - 1; i5 >= 0; i5--) {
                    for (int i13 = 0; i13 < k1; i13++)
                        if (i13 >= i5 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j5 = 0; j5 < l1; j5++) {
                    for (int j13 = 0; j13 < k1; j13++)
                        if (j13 <= j5 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k5 = 0; k5 < l1; k5++) {
                    for (int k13 = k1 - 1; k13 >= 0; k13--)
                        if (k13 >= k5 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else {
                return;
            }
        }
        if (i2 == 4) {
            if (j2 == 0) {
                for (int l5 = l1 - 1; l5 >= 0; l5--) {
                    for (int l13 = 0; l13 < k1; l13++)
                        if (l13 >= l5 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i6 = 0; i6 < l1; i6++) {
                    for (int i14 = 0; i14 < k1; i14++)
                        if (i14 <= i6 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j6 = 0; j6 < l1; j6++) {
                    for (int j14 = k1 - 1; j14 >= 0; j14--)
                        if (j14 >= j6 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k6 = l1 - 1; k6 >= 0; k6--) {
                    for (int k14 = k1 - 1; k14 >= 0; k14--)
                        if (k14 <= k6 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else {
                return;
            }
        }
        if (i2 == 5) {
            if (j2 == 0) {
                for (int l6 = l1 - 1; l6 >= 0; l6--) {
                    for (int l14 = k1 - 1; l14 >= 0; l14--)
                        if (l14 >= l6 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i7 = l1 - 1; i7 >= 0; i7--) {
                    for (int i15 = 0; i15 < k1; i15++)
                        if (i15 <= i7 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j7 = 0; j7 < l1; j7++) {
                    for (int j15 = 0; j15 < k1; j15++)
                        if (j15 >= j7 >> 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k7 = 0; k7 < l1; k7++) {
                    for (int k15 = k1 - 1; k15 >= 0; k15--)
                        if (k15 <= k7 << 1)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            } else {
                return;
            }
        }
        if (i2 == 6) {
            if (j2 == 0) {
                for (int l7 = 0; l7 < l1; l7++) {
                    for (int l15 = 0; l15 < k1; l15++)
                        if (l15 <= k1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i8 = 0; i8 < l1; i8++) {
                    for (int i16 = 0; i16 < k1; i16++)
                        if (i8 <= l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j8 = 0; j8 < l1; j8++) {
                    for (int j16 = 0; j16 < k1; j16++)
                        if (j16 >= k1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k8 = 0; k8 < l1; k8++) {
                    for (int k16 = 0; k16 < k1; k16++)
                        if (k8 >= l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
        }
        if (i2 == 7) {
            if (j2 == 0) {
                for (int l8 = 0; l8 < l1; l8++) {
                    for (int l16 = 0; l16 < k1; l16++)
                        if (l16 <= l8 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i9 = l1 - 1; i9 >= 0; i9--) {
                    for (int i17 = 0; i17 < k1; i17++)
                        if (i17 <= i9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j9 = l1 - 1; j9 >= 0; j9--) {
                    for (int j17 = k1 - 1; j17 >= 0; j17--)
                        if (j17 <= j9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k9 = 0; k9 < l1; k9++) {
                    for (int k17 = k1 - 1; k17 >= 0; k17--)
                        if (k17 <= k9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
        }
        if (i2 == 8) {
            if (j2 == 0) {
                for (int l9 = 0; l9 < l1; l9++) {
                    for (int l17 = 0; l17 < k1; l17++)
                        if (l17 >= l9 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 1) {
                for (int i10 = l1 - 1; i10 >= 0; i10--) {
                    for (int i18 = 0; i18 < k1; i18++)
                        if (i18 >= i10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 2) {
                for (int j10 = l1 - 1; j10 >= 0; j10--) {
                    for (int j18 = k1 - 1; j18 >= 0; j18--)
                        if (j18 >= j10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

                return;
            }
            if (j2 == 3) {
                for (int k10 = 0; k10 < l1; k10++) {
                    for (int k18 = k1 - 1; k18 >= 0; k18--)
                        if (k18 >= k10 - l1 / 2)
                            ai[k++] = j1;
                        else
                            ai[k++] = i1;

                    k += k2;
                }

            }
        }
    }

    private FileArchive loadData() {
        byte[] abyte0 = null;
        String s = null;
        try {
            s = Signlink.findcachedir();
            abyte0 = loadFile((new StringBuilder()).append(s).append("/worldmap.dat").toString());
            if (!compareChecksum(abyte0))
                abyte0 = null;
            if (abyte0 != null)
                return new FileArchive(abyte0);
        } catch (Throwable throwable) {
        }
        abyte0 = getLatestData();
        if (s != null && abyte0 != null)
            try {
                saveFile((new StringBuilder()).append(s).append("/worldmap.dat").toString(), abyte0);
            } catch (Throwable throwable1) {
            }
        return new FileArchive(abyte0);
    }

    private byte[] getLatestData() {
        System.out.println("Updating");
        showProgress("Requesting map", 0);
        try {
            String s = "";
            for (int k = 0; k < 10; k++)
                s = (new StringBuilder()).append(s).append(Signature.sha[k]).toString();

            DataInputStream datainputstream;
            if (super.frame != null)
                datainputstream = new DataInputStream(new FileInputStream("worldmap.jag"));
            else
                datainputstream = new DataInputStream((new URL(getCodeBase(), (new StringBuilder()).append("worldmap").append(s).append(".jag").toString())).openStream());
            int i1 = 0;
            int j1 = 0;
            int k1 = Signature.length;
            byte[] abyte0 = new byte[k1];
            while (j1 < k1) {
                int l1 = k1 - j1;
                if (l1 > 1000)
                    l1 = 1000;
                int i2 = datainputstream.read(abyte0, j1, l1);
                if (i2 < 0)
                    throw new IOException("EOF");
                j1 += i2;
                int j2 = (j1 * 100) / k1;
                if (j2 != i1)
                    showProgress((new StringBuilder()).append("Loading map - ").append(j2).append("%").toString(), j2);
                i1 = j2;
            }
            datainputstream.close();
            return abyte0;
        } catch (IOException ioexception) {
            System.out.println("Error loading");
            ioexception.printStackTrace();
            return null;
        }
    }

    private byte[] loadFile(String name)
        throws IOException {
        File file = new File(name);
        if (!file.exists()) {
            return null;
        } else {
            int k = (int) file.length();
            byte[] abyte0 = new byte[k];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(name)));
            datainputstream.readFully(abyte0, 0, k);
            datainputstream.close();
            return abyte0;
        }
    }

    private void saveFile(String name, byte[] data)
        throws IOException {
        FileOutputStream fileoutputstream = new FileOutputStream(name);
        fileoutputstream.write(data, 0, data.length);
        fileoutputstream.close();
    }

    private boolean compareChecksum(byte[] data)
        throws Exception {
        if (data == null)
            return false;
        MessageDigest messagedigest = MessageDigest.getInstance("SHA");
        messagedigest.reset();
        messagedigest.update(data);
        byte[] abyte1 = messagedigest.digest();
        for (int k = 0; k < 20; k++)
            if (abyte1[k] != Signature.sha[k])
                return false;

        return true;
    }

    public Viewer() {
    }

    private static boolean shouldDrawDebug;
    private final int colorInactiveBorderTL = 0x887755;
    private final int colorInactive = 0x776644;
    private final int colorInactiveBorderBR = 0x665533;
    private final int colorActiveBorderTL = 0xaa0000;
    private final int colorActive = 0x990000;
    private final int colorActiveBorderBR = 0x880000;
    private boolean shouldDraw = true;
    private int drawTimer;
    private static int centerX;
    private static int centerY;
    private static int originX;
    private static int originY;
    private int[] floormapsUnderlay;
    private int[] floormapsOverlay;
    private int[][] floormapColors;
    private int[][] overlayColors;
    private byte[][] overlayTypes;
    private byte[][] locWalls;
    private byte[][] locIcons;
    private byte[][] locScenes;
    private IndexedSprite[] mapscenes = new IndexedSprite[100];
    private Sprite[] mapfunctions = new Sprite[100];
    private IndexedFontFull b12;
    private DrawText f11;
    private DrawText f12;
    private DrawText f14;
    private DrawText f17;
    private DrawText f19;
    private DrawText f22;
    private DrawText f26;
    private DrawText f30;
    private int[] visibleMapIconsX = new int[2000];
    private int[] visibleMapIconsY = new int[2000];
    private int[] visibleMapIcons = new int[2000];
    private int mapIconCount;
    private int[] mapIconsX = new int[2000];
    private int[] mapIconsY = new int[2000];
    private int[] mapIcons = new int[2000];
    private final int keyX = 5;
    private final int keyY = 13;
    private final int keyWidth = 140;
    private final int keyOffset = 470;
    private int lastKeyPage;
    private int keyPage;
    private boolean showKey = false;
    private int currentKeyHover = -1;
    private int lastKeyHover = -1;
    private int currentKey = -1;
    private int flashTimer;
    private int imageOverviewHeight;
    private int imageOverviewWidth;
    private int overviewX;
    private int overviewY;
    private boolean showOverview = false;
    private Sprite imageOverview;
    private int mouseClickX;
    private int mouseClickY;
    private int lastOffsetX;
    private int lastOffsetY;
    private static final boolean shouldDrawLabels = true;
    private int labelCount;
    private final int maxLabels = 1000;
    private String[] labelText = new String[maxLabels];
    private int[] labelX = new int[maxLabels];
    private int[] labelY = new int[maxLabels];
    private int[] labelType = new int[maxLabels];
    private double zoomX = 4D;
    private double zoomY = 4D;
    private static int offsetX;
    private static int offsetY;
    private String[] keyNames = {
        "General Store", "Sword Shop", "Magic Shop", "Axe Shop", "Helmet Shop", "Bank", "Quest Start", "Amulet Shop", "Mining Site", "Furnace",
        "Anvil", "Combat Training", "Dungeon", "Staff Shop", "Platebody Shop", "Platelegs Shop", "Scimitar Shop", "Archery Shop", "Shield Shop", "Altar",
        "Herbalist", "Jewelery", "Gem Shop", "Crafting Shop", "Candle Shop", "Fishing Shop", "Fishing Spot", "Clothes Shop", "Apothecary", "Silk Trader",
        "Kebab Seller", "Pub/Bar", "Mace Shop", "Tannery", "Rare Trees", "Spinning Wheel", "Food Shop", "Cookery Shop", "Mini-Game", "Water Source",
        "Cooking Range", "Skirt Shop", "Potters Wheel", "Windmill", "Mining Shop", "Chainmail Shop", "Silver Shop", "Fur Trader", "Spice Shop", "Agility Training",
        "Vegetable Store", "Slayer Master", "Hair Dressers", "Farming patch", "Makeover Mage", "Guide", "Transportation", "???", "Farming shop", "Loom",
        "Brewery"
    };

}
