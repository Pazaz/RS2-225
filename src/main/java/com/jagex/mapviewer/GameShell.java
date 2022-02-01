package com.jagex.mapviewer;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameShell extends Applet
    implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener
{

    public void stop()
    {
        if(aig >= 0)
            aig = 4000 / aih;
    }

    public void afb()
    {
    }

    public void afc(int i, String s)
    {
        while(graphics == null)
        {
            graphics = agf().getGraphics();
            try
            {
                agf().repaint();
            }
            catch(Exception exception) { }
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception exception1) { }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = agf().getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        FontMetrics fontmetrics1 = agf().getFontMetrics(font1);
        if(aje)
        {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, aim, ain);
            aje = false;
        }
        Color color = new Color(140, 17, 17);
        int j = ain / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(aim / 2 - 152, j, 304, 34);
        graphics.fillRect(aim / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect((aim / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (aim - fontmetrics.stringWidth(s)) / 2, j + 22);
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        ajg = 0;
        ajh = 0;
    }

    public void keyPressed(KeyEvent keyevent)
    {
        ajg = 0;
        int i = keyevent.getKeyCode();
        int j = keyevent.getKeyChar();
        if(j < 30)
            j = 0;
        if(i == 37)
            j = 1;
        if(i == 39)
            j = 2;
        if(i == 38)
            j = 3;
        if(i == 40)
            j = 4;
        if(i == 17)
            j = 5;
        if(i == 8)
            j = 8;
        if(i == 127)
            j = 8;
        if(i == 9)
            j = 9;
        if(i == 10)
            j = 10;
        if(i >= 112 && i <= 123)
            j = (1008 + i) - 112;
        if(i == 36)
            j = 1000;
        if(i == 35)
            j = 1001;
        if(i == 33)
            j = 1002;
        if(i == 34)
            j = 1003;
        if(j > 0 && j < 128)
            ake[j] = 1;
        if(j > 4)
        {
            akf[akh] = j;
            akh = akh + 1 & 0x7f;
        }
    }

    public void aff(Runnable runnable, int i)
    {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(i);
    }

    public void windowClosing(WindowEvent windowevent)
    {
        destroy();
    }

    public void afh()
    {
        aig = -2;
        ahb();
        if(frame != null)
        {
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception exception) { }
            try
            {
                System.exit(0);
            }
            catch(Throwable throwable) { }
        }
    }

    public void afi(Graphics g1)
    {
        if(graphics == null)
            graphics = g1;
        aje = true;
        afb();
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        ajg = 0;
        aji = -1;
        ajj = -1;
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void aga()
    {
    }

    public void start()
    {
        if(aig >= 0)
            aig = 0;
    }

    public void agc(int i, int j)
    {
        aim = i;
        ain = j;
        frame = new GameFrame(this, aim, ain);
        graphics = agf().getGraphics();
        drawArea = new DrawArea(aim, ain, agf());
        aff(this, 1);
    }

    public int agd()
    {
        int i = -1;
        if(akh != akg)
        {
            i = akf[akg];
            akg = akg + 1 & 0x7f;
        }
        return i;
    }

    public void age()
    {
    }

    public Component agf()
    {
        if(frame != null)
            return frame;
        else
            return this;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(frame != null)
        {
            i -= 4;
            j -= 22;
        }
        ajg = 0;
        ajl = i;
        ajm = j;
        ajn = System.currentTimeMillis();
        if(mouseevent.isMetaDown())
        {
            ajk = 2;
            ajh = 2;
        } else
        {
            ajk = 1;
            ajh = 1;
        }
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(frame != null)
        {
            i -= 4;
            j -= 22;
        }
        ajg = 0;
        aji = i;
        ajj = j;
    }

    public void agj(int i, int j)
    {
        aim = i;
        ain = j;
        graphics = agf().getGraphics();
        drawArea = new DrawArea(aim, ain, agf());
        aff(this, 1);
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(frame != null)
        {
            i -= 4;
            j -= 22;
        }
        ajg = 0;
        aji = i;
        ajj = j;
    }

    public void keyTyped(KeyEvent keyevent)
    {
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void agn(Graphics g1)
    {
        if(graphics == null)
            graphics = g1;
        aje = true;
        afb();
    }

    public void destroy()
    {
        aig = -1;
        try
        {
            Thread.sleep(5000L);
        }
        catch(Exception exception) { }
        if(aig == -1)
            afh();
    }

    public void ahb()
    {
    }

    public GameShell()
    {
        aig = 0;
        aih = 20;
        aii = 1;
        aij = new long[10];
        aik = 0;
        ail = false;
        aje = true;
        ajf = true;
        ajg = 0;
        ajh = 0;
        aji = 0;
        ajj = 0;
        ajk = 0;
        ajl = 0;
        ajm = 0;
        ajn = 0L;
        aka = 0;
        akb = 0;
        akc = 0;
        akd = 0L;
        ake = new int[128];
        akf = new int[128];
        akg = 0;
        akh = 0;
    }

    public void ahd()
    {
    }

    public void focusLost(FocusEvent focusevent)
    {
        ajf = false;
        for(int i = 0; i < 128; i++)
            ake[i] = 0;

    }

    public void keyReleased(KeyEvent keyevent)
    {
        ajg = 0;
        int i = keyevent.getKeyCode();
        char c = keyevent.getKeyChar();
        if(c < '\036')
            c = '\0';
        if(i == 37)
            c = '\001';
        if(i == 39)
            c = '\002';
        if(i == 38)
            c = '\003';
        if(i == 40)
            c = '\004';
        if(i == 17)
            c = '\005';
        if(i == 8)
            c = '\b';
        if(i == 127)
            c = '\b';
        if(i == 9)
            c = '\t';
        if(i == 10)
            c = '\n';
        if(c > 0 && c < '\200')
            ake[c] = 0;
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void run()
    {
        agf().addMouseListener(this);
        agf().addMouseMotionListener(this);
        agf().addKeyListener(this);
        agf().addFocusListener(this);
        if(frame != null)
            frame.addWindowListener(this);
        afc(0, "Loading...");
        aga();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        int j1 = 0;
        for(int k1 = 0; k1 < 10; k1++)
            aij[k1] = System.currentTimeMillis();

        long l1 = System.currentTimeMillis();
        do
        {
            if(aig < 0)
                break;
            if(aig > 0)
            {
                aig--;
                if(aig == 0)
                {
                    afh();
                    return;
                }
            }
            int i2 = j;
            int j2 = k;
            j = 300;
            k = 1;
            long l2 = System.currentTimeMillis();
            if(aij[i] == 0L)
            {
                j = i2;
                k = j2;
            } else
            if(l2 > aij[i])
                j = (int)((long)(2560 * aih) / (l2 - aij[i]));
            if(j < 25)
                j = 25;
            if(j > 256)
            {
                j = 256;
                k = (int)((long)aih - (l2 - aij[i]) / 10L);
            }
            if(k > aih)
                k = aih;
            aij[i] = l2;
            i = (i + 1) % 10;
            if(k > 1)
            {
                for(int k2 = 0; k2 < 10; k2++)
                    if(aij[k2] != 0L)
                        aij[k2] += k;

            }
            if(k < aii)
                k = aii;
            try
            {
                Thread.sleep(k);
            }
            catch(InterruptedException interruptedexception)
            {
                j1++;
            }
            for(; i1 < 256; i1 += j)
            {
                aka = ajk;
                akb = ajl;
                akc = ajm;
                akd = ajn;
                ajk = 0;
                ahd();
                akg = akh;
            }

            i1 &= 0xff;
            if(aih > 0)
                aik = (1000 * j) / (aih * 256);
            age();
            if(ail)
            {
                System.out.println((new StringBuilder()).append("ntime:").append(l2).toString());
                for(int i3 = 0; i3 < 10; i3++)
                {
                    int j3 = ((i - i3 - 1) + 20) % 10;
                    System.out.println((new StringBuilder()).append("otim").append(j3).append(":").append(aij[j3]).toString());
                }

                System.out.println((new StringBuilder()).append("fps:").append(aik).append(" ratio:").append(j).append(" count:").append(i1).toString());
                System.out.println((new StringBuilder()).append("del:").append(k).append(" deltime:").append(aih).append(" mindel:").append(aii).toString());
                System.out.println((new StringBuilder()).append("intex:").append(j1).append(" opos:").append(i).toString());
                ail = false;
                j1 = 0;
            }
        } while(true);
        if(aig == -1)
            afh();
    }

    public void focusGained(FocusEvent focusevent)
    {
        ajf = true;
        aje = true;
        afb();
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    private int aig;
    private int aih;
    public int aii;
    private long aij[];
    public int aik;
    public boolean ail;
    public int aim;
    public int ain;
    public Graphics graphics;
    public DrawArea drawArea;
    public GameFrame frame;
    public boolean aje;
    public boolean ajf;
    public int ajg;
    public int ajh;
    public int aji;
    public int ajj;
    public int ajk;
    public int ajl;
    public int ajm;
    public long ajn;
    public int aka;
    public int akb;
    public int akc;
    public long akd;
    public int ake[];
    private int akf[];
    private int akg;
    private int akh;
    public static boolean aki;
}
