import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import sign.signlink;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;

@OriginalClass("client!a")
public class GameShell extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

	@OriginalMember(owner = "client!a", name = "c", descriptor = "I")
	private int anInt125;

	@OriginalMember(owner = "client!a", name = "g", descriptor = "I")
	private int state;

	@OriginalMember(owner = "client!a", name = "k", descriptor = "I")
	private int fps;

	@OriginalMember(owner = "client!a", name = "l", descriptor = "I")
	protected int gameWidth;

	@OriginalMember(owner = "client!a", name = "m", descriptor = "I")
	protected int gameHeight;

	@OriginalMember(owner = "client!a", name = "n", descriptor = "Ljava/awt/Graphics;")
	protected Graphics graphics;

	@OriginalMember(owner = "client!a", name = "o", descriptor = "Lclient!qb;")
	protected ImageProducerFrameBuffer frameBuffer;

	@OriginalMember(owner = "client!a", name = "q", descriptor = "Lclient!b;")
	protected GameFrame frame;

	@OriginalMember(owner = "client!a", name = "s", descriptor = "I")
	protected int idleCycles;

	@OriginalMember(owner = "client!a", name = "t", descriptor = "I")
	protected int dragButton;

	@OriginalMember(owner = "client!a", name = "u", descriptor = "I")
	protected int mouseX;

	@OriginalMember(owner = "client!a", name = "v", descriptor = "I")
	protected int mouseY;

	@OriginalMember(owner = "client!a", name = "w", descriptor = "I")
	protected int mouseButton;

	@OriginalMember(owner = "client!a", name = "x", descriptor = "I")
	protected int clickX;

	@OriginalMember(owner = "client!a", name = "y", descriptor = "I")
	protected int clickY;

	@OriginalMember(owner = "client!a", name = "B", descriptor = "I")
	private int lastProcessedKey;

	@OriginalMember(owner = "client!a", name = "C", descriptor = "I")
	private int unprocessedKeyCount;

	@OriginalMember(owner = "client!a", name = "a", descriptor = "Z")
	private boolean aBoolean33 = false;

	@OriginalMember(owner = "client!a", name = "b", descriptor = "Z")
	private boolean aBoolean34 = false;

	@OriginalMember(owner = "client!a", name = "d", descriptor = "Z")
	private boolean aBoolean35 = false;

	@OriginalMember(owner = "client!a", name = "e", descriptor = "B")
	private final byte aByte7 = 3;

	@OriginalMember(owner = "client!a", name = "f", descriptor = "I")
	private int anInt126 = 27808;

	@OriginalMember(owner = "client!a", name = "h", descriptor = "I")
	private int deltime = 20;

	@OriginalMember(owner = "client!a", name = "i", descriptor = "I")
	protected int mindel = 1;

	@OriginalMember(owner = "client!a", name = "j", descriptor = "[J")
	private final long[] otim = new long[10];

	@OriginalMember(owner = "client!a", name = "p", descriptor = "[Lclient!hb;")
	private final Sprite[] sprites = new Sprite[6];

	@OriginalMember(owner = "client!a", name = "r", descriptor = "Z")
	private boolean refresh = true;

	@OriginalMember(owner = "client!a", name = "z", descriptor = "[I")
	protected final int[] keyDown = new int[128];

	@OriginalMember(owner = "client!a", name = "A", descriptor = "[I")
	private final int[] pressedKeys = new int[128];

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(III)V")
	protected final void initApplication(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		try {
			if (arg2 != 0) {
				this.anInt125 = 411;
			}
			this.gameWidth = arg1;
			this.gameHeight = arg0;
			this.frame = new GameFrame(this.gameHeight, 35731, this, this.gameWidth);
			this.graphics = this.getBaseComponent(this.aByte7).getGraphics();
			this.frameBuffer = new ImageProducerFrameBuffer(this.getBaseComponent(this.aByte7), this.gameWidth, 299, this.gameHeight);
			this.startThread(this, 1);
		} catch (@Pc(48) RuntimeException local48) {
			signlink.reporterror("32828, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local48.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(IZI)V")
	protected final void initApplet(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2) {
		try {
			this.gameWidth = arg2;
			this.gameHeight = arg0;
			this.graphics = this.getBaseComponent(this.aByte7).getGraphics();
			this.frameBuffer = new ImageProducerFrameBuffer(this.getBaseComponent(this.aByte7), this.gameWidth, 299, this.gameHeight);
			if (arg1) {
				this.anInt125 = -370;
			}
			this.startThread(this, 1);
		} catch (@Pc(37) RuntimeException local37) {
			signlink.reporterror("68129, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local37.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "run", descriptor = "()V")
	@Override
	public void run() {
		this.getBaseComponent(this.aByte7).addMouseListener(this);
		this.getBaseComponent(this.aByte7).addMouseMotionListener(this);
		this.getBaseComponent(this.aByte7).addKeyListener(this);
		this.getBaseComponent(this.aByte7).addFocusListener(this);
		if (this.frame != null) {
			this.frame.addWindowListener(this);
		}
		this.showProgress(true, "Loading...", 0);
		this.load();
		@Pc(41) int local41 = 0;
		@Pc(43) int local43 = 256;
		@Pc(45) int local45 = 1;
		@Pc(47) int local47 = 0;
		for (@Pc(49) int local49 = 0; local49 < 10; local49++) {
			this.otim[local49] = System.currentTimeMillis();
		}
		@Pc(62) long local62 = System.currentTimeMillis();
		while (this.state >= 0) {
			if (this.state > 0) {
				this.state--;
				if (this.state == 0) {
					this.shutdown(-652);
					return;
				}
			}
			@Pc(82) int local82 = local43;
			@Pc(84) int local84 = local45;
			local43 = 300;
			local45 = 1;
			local62 = System.currentTimeMillis();
			if (this.otim[local41] == 0L) {
				local43 = local82;
				local45 = local84;
			} else if (local62 > this.otim[local41]) {
				local43 = (int) ((long) (this.deltime * 2560) / (local62 - this.otim[local41]));
			}
			if (local43 < 25) {
				local43 = 25;
			}
			if (local43 > 256) {
				local43 = 256;
				local45 = (int) ((long) this.deltime - (local62 - this.otim[local41]) / 10L);
			}
			this.otim[local41] = local62;
			local41 = (local41 + 1) % 10;
			if (local45 > 1) {
				for (@Pc(164) int local164 = 0; local164 < 10; local164++) {
					if (this.otim[local164] != 0L) {
						this.otim[local164] += local45;
					}
				}
			}
			if (local45 < this.mindel) {
				local45 = this.mindel;
			}
			try {
				Thread.sleep((long) local45);
			} catch (@Pc(198) InterruptedException local198) {
			}
			while (local47 < 256) {
				this.update(437);
				this.mouseButton = 0;
				this.lastProcessedKey = this.unprocessedKeyCount;
				local47 += local43;
			}
			local47 &= 0xFF;
			if (this.deltime > 0) {
				this.fps = local43 * 1000 / (this.deltime * 256);
			}
			this.draw(false);
		}
		if (this.state == -1) {
			this.shutdown(-652);
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(I)V")
	private void shutdown(@OriginalArg(0) int arg0) {
		try {
			while (arg0 >= 0) {
				this.aBoolean35 = !this.aBoolean35;
			}
			this.state = -2;
			this.unload((byte) -28);
			try {
				Thread.sleep(1000L);
			} catch (@Pc(21) Exception local21) {
			}
			try {
				System.exit(0);
			} catch (@Pc(25) Throwable local25) {
			}
		} catch (@Pc(27) RuntimeException local27) {
			signlink.reporterror("31182, " + arg0 + ", " + local27.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(II)V")
	protected final void setLoopRate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		try {
			this.deltime = 1000 / arg1;
			if (arg0 <= 0) {
				this.aBoolean33 = !this.aBoolean33;
			}
		} catch (@Pc(16) RuntimeException local16) {
			signlink.reporterror("9789, " + arg0 + ", " + arg1 + ", " + local16.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "start", descriptor = "()V")
	@Override
	public final void start() {
		if (this.state >= 0) {
			this.state = 0;
		}
	}

	@OriginalMember(owner = "client!a", name = "stop", descriptor = "()V")
	@Override
	public final void stop() {
		if (this.state >= 0) {
			this.state = 4000 / this.deltime;
		}
	}

	@OriginalMember(owner = "client!a", name = "destroy", descriptor = "()V")
	@Override
	public final void destroy() {
		this.state = -1;
		try {
			Thread.sleep(5000L);
		} catch (@Pc(6) Exception local6) {
		}
		if (this.state == -1) {
			this.shutdown(-652);
		}
	}

	@OriginalMember(owner = "client!a", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics arg0) {
		if (this.graphics == null) {
			this.graphics = arg0;
		}
		this.refresh = true;
		this.refresh(3);
	}

	@OriginalMember(owner = "client!a", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void paint(@OriginalArg(0) Graphics arg0) {
		if (this.graphics == null) {
			this.graphics = arg0;
		}
		this.refresh = true;
		this.refresh(3);
	}

	@OriginalMember(owner = "client!a", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mousePressed(@OriginalArg(0) MouseEvent arg0) {
		@Pc(2) int local2 = arg0.getX();
		@Pc(5) int local5 = arg0.getY();
		if (this.frame != null) {
			local2 -= 4;
			local5 -= 22;
		}
		this.idleCycles = 0;
		this.clickX = local2;
		this.clickY = local5;
		if (arg0.isMetaDown()) {
			this.mouseButton = 2;
			this.dragButton = 2;
		} else {
			this.mouseButton = 1;
			this.dragButton = 1;
		}
		if (InputTracking.aBoolean81) {
			InputTracking.mousePressed(local2, arg0.isMetaDown() ? 1 : 0, local5, (byte) 4);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseReleased(@OriginalArg(0) MouseEvent arg0) {
		this.idleCycles = 0;
		this.dragButton = 0;
		if (InputTracking.aBoolean81) {
			InputTracking.mouseReleased(arg0.isMetaDown() ? 1 : 0, 0);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseClicked", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseClicked(@OriginalArg(0) MouseEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "mouseEntered", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseEntered(@OriginalArg(0) MouseEvent arg0) {
		if (InputTracking.aBoolean81) {
			InputTracking.mouseEntered(-657);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseExited", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseExited(@OriginalArg(0) MouseEvent arg0) {
		if (InputTracking.aBoolean81) {
			InputTracking.mouseExited(false);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseDragged", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseDragged(@OriginalArg(0) MouseEvent arg0) {
		@Pc(2) int local2 = arg0.getX();
		@Pc(5) int local5 = arg0.getY();
		if (this.frame != null) {
			local2 -= 4;
			local5 -= 22;
		}
		this.idleCycles = 0;
		this.mouseX = local2;
		this.mouseY = local5;
		if (InputTracking.aBoolean81) {
			InputTracking.mouseMoved(local5, true, local2);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseMoved", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseMoved(@OriginalArg(0) MouseEvent arg0) {
		@Pc(2) int local2 = arg0.getX();
		@Pc(5) int local5 = arg0.getY();
		if (this.frame != null) {
			local2 -= 4;
			local5 -= 22;
		}
		this.idleCycles = 0;
		this.mouseX = local2;
		this.mouseY = local5;
		if (InputTracking.aBoolean81) {
			InputTracking.mouseMoved(local5, true, local2);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyPressed", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyPressed(@OriginalArg(0) KeyEvent arg0) {
		this.idleCycles = 0;
		@Pc(7) int local7 = arg0.getKeyCode();
		@Pc(10) int local10 = arg0.getKeyChar();
		if (local10 < 30) {
			local10 = 0;
		}
		if (local7 == 37) {
			local10 = 1;
		}
		if (local7 == 39) {
			local10 = 2;
		}
		if (local7 == 38) {
			local10 = 3;
		}
		if (local7 == 40) {
			local10 = 4;
		}
		if (local7 == 17) {
			local10 = 5;
		}
		if (local7 == 8) {
			local10 = 8;
		}
		if (local7 == 127) {
			local10 = 8;
		}
		if (local7 == 9) {
			local10 = 9;
		}
		if (local7 == 10) {
			local10 = 10;
		}
		if (local7 >= 112 && local7 <= 123) {
			local10 = local7 + 1008 - 112;
		}
		if (local7 == 36) {
			local10 = 1000;
		}
		if (local7 == 35) {
			local10 = 1001;
		}
		if (local7 == 33) {
			local10 = 1002;
		}
		if (local7 == 34) {
			local10 = 1003;
		}
		if (local10 > 0 && local10 < 128) {
			this.keyDown[local10] = 1;
		}
		if (local10 > 4) {
			this.pressedKeys[this.unprocessedKeyCount] = local10;
			this.unprocessedKeyCount = this.unprocessedKeyCount + 1 & 0x7F;
		}
		if (InputTracking.aBoolean81) {
			InputTracking.keyPressed(local10, true);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyReleased", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyReleased(@OriginalArg(0) KeyEvent arg0) {
		this.idleCycles = 0;
		@Pc(5) int local5 = arg0.getKeyCode();
		@Pc(8) char local8 = arg0.getKeyChar();
		if (local8 < '\u001e') {
			local8 = '\u0000';
		}
		if (local5 == 37) {
			local8 = '\u0001';
		}
		if (local5 == 39) {
			local8 = '\u0002';
		}
		if (local5 == 38) {
			local8 = '\u0003';
		}
		if (local5 == 40) {
			local8 = '\u0004';
		}
		if (local5 == 17) {
			local8 = '\u0005';
		}
		if (local5 == 8) {
			local8 = '\b';
		}
		if (local5 == 127) {
			local8 = '\b';
		}
		if (local5 == 9) {
			local8 = '\t';
		}
		if (local5 == 10) {
			local8 = '\n';
		}
		if (local8 > '\u0000' && local8 < '\u0080') {
			this.keyDown[local8] = 0;
		}
		if (InputTracking.aBoolean81) {
			InputTracking.keyReleased(local8, 1);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyTyped", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyTyped(@OriginalArg(0) KeyEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
		this.refresh = true;
		this.refresh(3);
		if (InputTracking.aBoolean81) {
			InputTracking.focusGained(-936);
		}
	}

	@OriginalMember(owner = "client!a", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent arg0) {
		if (InputTracking.aBoolean81) {
			InputTracking.focusLost(0);
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Z)I")
	protected final int pollKey(@OriginalArg(0) boolean arg0) {
		try {
			@Pc(1) int local1 = -1;
			if (arg0) {
				return 2;
			}
			if (this.unprocessedKeyCount != this.lastProcessedKey) {
				local1 = this.pressedKeys[this.lastProcessedKey];
				this.lastProcessedKey = this.lastProcessedKey + 1 & 0x7F;
			}
			return local1;
		} catch (@Pc(27) RuntimeException local27) {
			signlink.reporterror("3026, " + arg0 + ", " + local27.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "windowActivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowActivated(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "windowClosed", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosed(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "windowClosing", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosing(@OriginalArg(0) WindowEvent arg0) {
		this.destroy();
	}

	@OriginalMember(owner = "client!a", name = "windowDeactivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeactivated(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "windowDeiconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeiconified(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "windowIconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowIconified(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "windowOpened", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowOpened(@OriginalArg(0) WindowEvent arg0) {
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "()V")
	protected void load() {
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(I)V")
	protected void update(@OriginalArg(0) int arg0) {
		try {
			if (arg0 <= 0) {
				this.aBoolean34 = !this.aBoolean34;
			}
		} catch (@Pc(11) RuntimeException local11) {
			signlink.reporterror("39582, " + arg0 + ", " + local11.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(B)V")
	protected void unload(@OriginalArg(0) byte arg0) {
		try {
			if (arg0 != -28) {
				this.anInt126 = -407;
			}
		} catch (@Pc(7) RuntimeException local7) {
			signlink.reporterror("60526, " + arg0 + ", " + local7.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(Z)V")
	protected void draw(@OriginalArg(0) boolean arg0) {
		try {
			if (arg0) {
				this.aBoolean35 = !this.aBoolean35;
			}
		} catch (@Pc(11) RuntimeException local11) {
			signlink.reporterror("89494, " + arg0 + ", " + local11.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "c", descriptor = "(I)V")
	protected void refresh(@OriginalArg(0) int arg0) {
		try {
			if (arg0 < 3 || arg0 > 3) {
				this.aBoolean34 = !this.aBoolean34;
			}
		} catch (@Pc(15) RuntimeException local15) {
			signlink.reporterror("40825, " + arg0 + ", " + local15.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(B)Ljava/awt/Component;")
	protected Component getBaseComponent(@OriginalArg(0) byte arg0) {
		try {
			if (arg0 != 3) {
				throw new NullPointerException();
			}
			return this.frame == null ? this : this.frame;
		} catch (@Pc(15) RuntimeException local15) {
			signlink.reporterror("24145, " + arg0 + ", " + local15.toString());
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	public void startThread(@OriginalArg(0) Runnable arg0, @OriginalArg(1) int arg1) {
		@Pc(4) Thread local4 = new Thread(arg0);
		local4.start();
		local4.setPriority(arg1);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	protected void showProgress(@OriginalArg(0) boolean arg0, @OriginalArg(1) String arg1, @OriginalArg(2) int arg2) {
		try {
			while (this.graphics == null) {
				this.graphics = this.getBaseComponent(this.aByte7).getGraphics();
				try {
					this.getBaseComponent(this.aByte7).repaint();
				} catch (@Pc(22) Exception local22) {
				}
				try {
					Thread.sleep(1000L);
				} catch (@Pc(26) Exception local26) {
				}
			}
			@Pc(37) Font local37 = new Font("Helvetica", 1, 13);
			@Pc(44) FontMetrics local44 = this.getBaseComponent(this.aByte7).getFontMetrics(local37);
			@Pc(51) Font local51 = new Font("Helvetica", 0, 13);
			this.getBaseComponent(this.aByte7).getFontMetrics(local51);
			if (this.refresh) {
				this.graphics.setColor(Color.black);
				this.graphics.fillRect(0, 0, this.gameWidth, this.gameHeight);
				this.refresh = false;
			}
			@Pc(84) Color local84 = new Color(140, 17, 17);
			@Pc(91) int local91 = this.gameHeight / 2 - 18;
			this.graphics.setColor(local84);
			this.graphics.drawRect(this.gameWidth / 2 - 152, local91, 304, 34);
			this.graphics.fillRect(this.gameWidth / 2 - 150, local91 + 2, arg2 * 3, 30);
			this.graphics.setColor(Color.black);
			this.graphics.fillRect(this.gameWidth / 2 + arg2 * 3 - 150, local91 + 2, 300 - arg2 * 3, 30);
			this.graphics.setFont(local37);
			this.graphics.setColor(Color.white);
			if (arg0) {
				this.graphics.drawString(arg1, (this.gameWidth - local44.stringWidth(arg1)) / 2, local91 + 22);
			}
		} catch (@Pc(177) RuntimeException local177) {
			signlink.reporterror("55533, " + arg0 + ", " + arg1 + ", " + arg2 + ", " + local177.toString());
			throw new RuntimeException();
		}
	}
}
