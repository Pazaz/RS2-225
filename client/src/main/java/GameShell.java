import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!a")
public class GameShell extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

	@OriginalMember(owner = "client!a", name = "c", descriptor = "I")
	private int flowObfuscator2;

	@OriginalMember(owner = "client!a", name = "g", descriptor = "I")
	private int state;

	@OriginalMember(owner = "client!a", name = "k", descriptor = "I")
	private int fps;

	@OriginalMember(owner = "client!a", name = "l", descriptor = "I")
	protected int gameWidth;

	@OriginalMember(owner = "client!a", name = "m", descriptor = "I")
	protected int gameHeight;

	@OriginalMember(owner = "client!a", name = "n", descriptor = "Ljava/awt/Graphics;")
	protected Graphics graphic;

	@OriginalMember(owner = "client!a", name = "o", descriptor = "Lclient!qb;")
	protected DrawArea drawArea;

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
	private final boolean flowObfuscator3 = false;

	@OriginalMember(owner = "client!a", name = "b", descriptor = "Z")
	private final boolean flowObfuscator4 = false;

	@OriginalMember(owner = "client!a", name = "d", descriptor = "Z")
	private final boolean flowObfuscator5 = false;

	@OriginalMember(owner = "client!a", name = "e", descriptor = "B")
	private final byte flowObfuscator1 = 3;

	@OriginalMember(owner = "client!a", name = "f", descriptor = "I")
	private final int flowObfuscator6 = 27808;

	@OriginalMember(owner = "client!a", name = "h", descriptor = "I")
	private int deltime = 20;

	@OriginalMember(owner = "client!a", name = "i", descriptor = "I")
	protected int mindel = 1;

	@OriginalMember(owner = "client!a", name = "j", descriptor = "[J")
	private final long[] otim = new long[10];

	@OriginalMember(owner = "client!a", name = "p", descriptor = "[Lclient!hb;")
	private final Sprite[] spriteCache = new Sprite[6];

	@OriginalMember(owner = "client!a", name = "r", descriptor = "Z")
	private boolean refresh = true;

	@OriginalMember(owner = "client!a", name = "z", descriptor = "[I")
	protected final int[] keyDown = new int[128];

	@OriginalMember(owner = "client!a", name = "A", descriptor = "[I")
	private final int[] pressedKeys = new int[128];

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(III)V")
	protected final void initApplication() {
		this.gameWidth = 789;
		this.gameHeight = 532;
		this.frame = new GameFrame(this.gameHeight, 35731, this, this.gameWidth);
		this.graphic = this.getBaseComponent(this.flowObfuscator1).getGraphics();
		this.drawArea = new DrawArea(this.getBaseComponent(this.flowObfuscator1), this.gameWidth, 299, this.gameHeight);
		this.startThread(this, 1);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(IZI)V")
	protected final void initApplet() {
		this.gameWidth = 789;
		this.gameHeight = 532;
		this.graphic = this.getBaseComponent(this.flowObfuscator1).getGraphics();
		this.drawArea = new DrawArea(this.getBaseComponent(this.flowObfuscator1), this.gameWidth, 299, this.gameHeight);
		this.startThread(this, 1);
	}

	@OriginalMember(owner = "client!a", name = "run", descriptor = "()V")
	@Override
	public void run() {
		this.getBaseComponent(this.flowObfuscator1).addMouseListener(this);
		this.getBaseComponent(this.flowObfuscator1).addMouseMotionListener(this);
		this.getBaseComponent(this.flowObfuscator1).addKeyListener(this);
		this.getBaseComponent(this.flowObfuscator1).addFocusListener(this);
		if (this.frame != null) {
			this.frame.addWindowListener(this);
		}
		this.showProgress("Loading...", 0);
		this.load();
		@Pc(41) int opos = 0;
		@Pc(43) int ratio = 256;
		@Pc(45) int delta = 1;
		@Pc(47) int count = 0;
		for (@Pc(49) int i = 0; i < 10; i++) {
			this.otim[i] = System.currentTimeMillis();
		}
		@Pc(62) long ntime = System.currentTimeMillis();
		while (this.state >= 0) {
			if (this.state > 0) {
				this.state--;
				if (this.state == 0) {
					this.shutdown();
					return;
				}
			}
			@Pc(82) int lastRatio = ratio;
			@Pc(84) int lastDelta = delta;
			ratio = 300;
			delta = 1;
			ntime = System.currentTimeMillis();
			if (this.otim[opos] == 0L) {
				ratio = lastRatio;
				delta = lastDelta;
			} else if (ntime > this.otim[opos]) {
				ratio = (int) ((long) (this.deltime * 2560) / (ntime - this.otim[opos]));
			}
			if (ratio < 25) {
				ratio = 25;
			}
			if (ratio > 256) {
				ratio = 256;
				delta = (int) ((long) this.deltime - (ntime - this.otim[opos]) / 10L);
			}
			this.otim[opos] = ntime;
			opos = (opos + 1) % 10;
			if (delta > 1) {
				for (@Pc(164) int i = 0; i < 10; i++) {
					if (this.otim[i] != 0L) {
						this.otim[i] += delta;
					}
				}
			}
			if (delta < this.mindel) {
				delta = this.mindel;
			}
			try {
				Thread.sleep((long) delta);
			} catch (@Pc(198) InterruptedException ignored) {
			}
			while (count < 256) {
				this.update();
				this.mouseButton = 0;
				this.lastProcessedKey = this.unprocessedKeyCount;
				count += ratio;
			}
			count &= 0xFF;
			if (this.deltime > 0) {
				this.fps = ratio * 1000 / (this.deltime * 256);
			}
			this.draw();
		}
		if (this.state == -1) {
			this.shutdown();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(I)V")
	private void shutdown() {
		this.state = -2;
		this.unload();
		try {
			Thread.sleep(1000L);
		} catch (@Pc(21) Exception ignored) {
		}
		try {
			System.exit(0);
		} catch (@Pc(25) Throwable ignored) {
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(II)V")
	protected final void setLoopRate() {
		this.deltime = 1000;
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
			this.shutdown();
		}
	}

	@OriginalMember(owner = "client!a", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics g) {
		if (this.graphic == null) {
			this.graphic = g;
		}
		this.refresh = true;
		this.refresh();
	}

	@OriginalMember(owner = "client!a", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void paint(@OriginalArg(0) Graphics arg0) {
		if (this.graphic == null) {
			this.graphic = arg0;
		}
		this.refresh = true;
		this.refresh();
	}

	@OriginalMember(owner = "client!a", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mousePressed(@OriginalArg(0) MouseEvent e) {
		@Pc(2) int x = e.getX();
		@Pc(5) int y = e.getY();
		if (this.frame != null) {
			x -= 4;
			y -= 22;
		}
		this.idleCycles = 0;
		this.clickX = x;
		this.clickY = y;
		if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0) {
			this.mouseButton = 1;
			this.dragButton = 1;
		} else {
			this.mouseButton = 2;
			this.dragButton = 2;
		}
		if (InputTracking.enabled) {
			InputTracking.mousePressed(x, (e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0 ? 0 : 1, y);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseReleased(@OriginalArg(0) MouseEvent e) {
		this.idleCycles = 0;
		this.dragButton = 0;
		if (InputTracking.enabled) {
			InputTracking.mouseReleased((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0 ? 0 : 1);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseClicked", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseClicked(@OriginalArg(0) MouseEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "mouseEntered", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseEntered(@OriginalArg(0) MouseEvent e) {
		if (InputTracking.enabled) {
			InputTracking.mouseEntered();
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseExited", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseExited(@OriginalArg(0) MouseEvent e) {
		if (InputTracking.enabled) {
			InputTracking.mouseExited();
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseDragged", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseDragged(@OriginalArg(0) MouseEvent e) {
		@Pc(2) int x = e.getX();
		@Pc(5) int y = e.getY();
		if (this.frame != null) {
			x -= 4;
			y -= 22;
		}
		this.idleCycles = 0;
		this.mouseX = x;
		this.mouseY = y;
		if (InputTracking.enabled) {
			InputTracking.mouseMoved(y, x);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseMoved", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseMoved(@OriginalArg(0) MouseEvent e) {
		@Pc(2) int x = e.getX();
		@Pc(5) int y = e.getY();
		if (this.frame != null) {
			x -= 4;
			y -= 22;
		}
		this.idleCycles = 0;
		this.mouseX = x;
		this.mouseY = y;
		if (InputTracking.enabled) {
			InputTracking.mouseMoved(y, x);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyPressed", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyPressed(@OriginalArg(0) KeyEvent e) {
		this.idleCycles = 0;
		@Pc(7) int key = e.getKeyCode();
		@Pc(10) int c = e.getKeyChar();
		if (c < 30) {
			c = 0;
		}
		if (key == 37) {
			c = 1;
		}
		if (key == 39) {
			c = 2;
		}
		if (key == 38) {
			c = 3;
		}
		if (key == 40) {
			c = 4;
		}
		if (key == 17) {
			c = 5;
		}
		if (key == 8) {
			c = 8;
		}
		if (key == 127) {
			c = 8;
		}
		if (key == 9) {
			c = 9;
		}
		if (key == 10) {
			c = 10;
		}
		if (key >= 112 && key <= 123) {
			c = key + 1008 - 112;
		}
		if (key == 36) {
			c = 1000;
		}
		if (key == 35) {
			c = 1001;
		}
		if (key == 33) {
			c = 1002;
		}
		if (key == 34) {
			c = 1003;
		}
		if (c > 0 && c < 128) {
			this.keyDown[c] = 1;
		}
		if (c > 4) {
			this.pressedKeys[this.unprocessedKeyCount] = c;
			this.unprocessedKeyCount = this.unprocessedKeyCount + 1 & 0x7F;
		}
		if (InputTracking.enabled) {
			InputTracking.keyPressed(c);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyReleased", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyReleased(@OriginalArg(0) KeyEvent e) {
		this.idleCycles = 0;
		@Pc(5) int key = e.getKeyCode();
		@Pc(8) char c = e.getKeyChar();
		if (c < '\u001e') {
			c = '\u0000';
		}
		if (key == 37) {
			c = '\u0001';
		}
		if (key == 39) {
			c = '\u0002';
		}
		if (key == 38) {
			c = '\u0003';
		}
		if (key == 40) {
			c = '\u0004';
		}
		if (key == 17) {
			c = '\u0005';
		}
		if (key == 8) {
			c = '\b';
		}
		if (key == 127) {
			c = '\b';
		}
		if (key == 9) {
			c = '\t';
		}
		if (key == 10) {
			c = '\n';
		}
		if (c > '\u0000' && c < '\u0080') {
			this.keyDown[c] = 0;
		}
		if (InputTracking.enabled) {
			InputTracking.keyReleased(c);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyTyped", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyTyped(@OriginalArg(0) KeyEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent e) {
		this.refresh = true;
		this.refresh();
		if (InputTracking.enabled) {
			InputTracking.focusGained();
		}
	}

	@OriginalMember(owner = "client!a", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent e) {
		if (InputTracking.enabled) {
			InputTracking.focusLost();
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Z)I")
	protected final int pollKey() {
		@Pc(1) int key = -1;
		if (this.unprocessedKeyCount != this.lastProcessedKey) {
			key = this.pressedKeys[this.lastProcessedKey];
			this.lastProcessedKey = this.lastProcessedKey + 1 & 0x7F;
		}
		return key;
	}

	@OriginalMember(owner = "client!a", name = "windowActivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowActivated(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "windowClosed", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosed(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "windowClosing", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosing(@OriginalArg(0) WindowEvent e) {
		this.destroy();
	}

	@OriginalMember(owner = "client!a", name = "windowDeactivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeactivated(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "windowDeiconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeiconified(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "windowIconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowIconified(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "windowOpened", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowOpened(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "()V")
	protected void load() {
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(I)V")
	protected void update() {
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(B)V")
	protected void unload() {
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(Z)V")
	protected void draw() {
	}

	@OriginalMember(owner = "client!a", name = "c", descriptor = "(I)V")
	protected void refresh() {
	}

	@OriginalMember(owner = "client!a", name = "b", descriptor = "(B)Ljava/awt/Component;")
	protected Component getBaseComponent(@OriginalArg(0) byte obfuscator) {
		if (obfuscator != 3) {
			throw new NullPointerException();
		}
		return this.frame == null ? this : this.frame;
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	public void startThread(@OriginalArg(0) Runnable runnable, @OriginalArg(1) int priority) {
		@Pc(4) Thread thread = new Thread(runnable);
		thread.start();
		thread.setPriority(priority);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	protected void showProgress(@OriginalArg(1) String str, @OriginalArg(2) int progress) {
		while (this.graphic == null) {
			this.graphic = this.getBaseComponent(this.flowObfuscator1).getGraphics();
			try {
				this.getBaseComponent(this.flowObfuscator1).repaint();
			} catch (@Pc(22) Exception ignored) {
			}
			try {
				Thread.sleep(1000L);
			} catch (@Pc(26) Exception ignored) {
			}
		}
		@Pc(37) Font bold = new Font("Helvetica", 1, 13);
		@Pc(44) FontMetrics boldMetrics = this.getBaseComponent(this.flowObfuscator1).getFontMetrics(bold);
		@Pc(51) Font plain = new Font("Helvetica", 0, 13);
		this.getBaseComponent(this.flowObfuscator1).getFontMetrics(plain);
		if (this.refresh) {
			this.graphic.setColor(Color.black);
			this.graphic.fillRect(0, 0, this.gameWidth, this.gameHeight);
			this.refresh = false;
		}
		@Pc(84) Color color = new Color(140, 17, 17);
		@Pc(91) int y = this.gameHeight / 2 - 18;
		this.graphic.setColor(color);
		this.graphic.drawRect(this.gameWidth / 2 - 152, y, 304, 34);
		this.graphic.fillRect(this.gameWidth / 2 - 150, y + 2, progress * 3, 30);
		this.graphic.setColor(Color.black);
		this.graphic.fillRect(this.gameWidth / 2 + progress * 3 - 150, y + 2, 300 - progress * 3, 30);
		this.graphic.setFont(bold);
		this.graphic.setColor(Color.white);
		this.graphic.drawString(str, (this.gameWidth - boldMetrics.stringWidth(str)) / 2, y + 22);
	}
}
