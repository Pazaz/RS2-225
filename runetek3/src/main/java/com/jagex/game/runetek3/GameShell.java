package com.jagex.game.runetek3;

import com.jagex.game.runetek3.graphics.ImageProducerFrameBuffer;
import com.jagex.game.runetek3.graphics.Sprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

@OriginalClass("client!a")
public class GameShell extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {

	public static int KEY_LEFT = 1;
	public static int KEY_RIGHT = 2;
	public static int KEY_UP = 3;
	public static int KEY_DOWN = 4;
	public static int KEY_CONTROL = 5;
	public static int KEY_DELETE = 8;
	public static int KEY_TAB = 9;
	public static int KEY_ENTER = 10; // \n
	public static int KEY_RETURN = 13; // \r
	public static int KEY_HOME = 1000;
	public static int KEY_END = 1001;

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
	protected final void initApplication(@OriginalArg(0) int height, @OriginalArg(1) int width) {
		this.gameWidth = width;
		this.gameHeight = height;
		this.setPreferredSize(new Dimension(this.gameWidth, this.gameHeight));

		this.frame = new GameFrame(this);
		this.graphics = this.getBaseComponent().getGraphics();
		this.frameBuffer = new ImageProducerFrameBuffer(this.getBaseComponent(), this.gameWidth, this.gameHeight);
		this.startThread(this, 1);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(IZI)V")
	protected final void initApplet(@OriginalArg(0) int height, @OriginalArg(2) int width) {
		this.gameWidth = width;
		this.gameHeight = height;
		this.graphics = this.getBaseComponent().getGraphics();
		this.frameBuffer = new ImageProducerFrameBuffer(this.getBaseComponent(), this.gameWidth, this.gameHeight);
		this.startThread(this, 1);
	}

	@OriginalMember(owner = "client!a", name = "run", descriptor = "()V")
	@Override
	public void run() {
		this.getBaseComponent().addMouseListener(this);
		this.getBaseComponent().addMouseMotionListener(this);
		this.getBaseComponent().addKeyListener(this);
		this.getBaseComponent().addFocusListener(this);
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

		@Pc(62) long ntime;
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
				ratio = (int) ((this.deltime * 2560L) / (ntime - this.otim[opos]));
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
				Thread.sleep(delta);
			} catch (@Pc(198) InterruptedException ex) {
			}

			for (; count < 256; count += ratio) {
				this.update();
				this.mouseButton = 0;
				this.lastProcessedKey = this.unprocessedKeyCount;
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
		} catch (@Pc(21) Exception ex) {
		}

		try {
			System.exit(0);
		} catch (@Pc(25) Throwable ex) {
		}
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(II)V")
	protected final void setLoopRate(@OriginalArg(1) int rate) {
		this.deltime = 1000 / rate;
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
		} catch (@Pc(6) Exception ex) {
		}

		if (this.state == -1) {
			this.shutdown();
		}
	}

	@OriginalMember(owner = "client!a", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics g) {
		if (this.graphics == null) {
			this.graphics = g;
		}

		this.refresh = true;
		this.refresh();
	}

	@OriginalMember(owner = "client!a", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void paint(@OriginalArg(0) Graphics g) {
		if (this.graphics == null) {
			this.graphics = g;
		}

		this.refresh = true;
		this.refresh();
	}

	@OriginalMember(owner = "client!a", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mousePressed(@OriginalArg(0) MouseEvent e) {
		@Pc(2) int x = e.getX();
		@Pc(5) int y = e.getY();

		this.idleCycles = 0;
		this.clickX = x;
		this.clickY = y;

		if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
			this.mouseButton = 2;
			this.dragButton = 2;
		} else {
			this.mouseButton = 1;
			this.dragButton = 1;
		}

		if (InputTracking.enabled) {
			InputTracking.mousePressed(x, (e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0 ? 1 : 0, y);
		}
	}

	@OriginalMember(owner = "client!a", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseReleased(@OriginalArg(0) MouseEvent e) {
		this.idleCycles = 0;
		this.dragButton = 0;

		if (InputTracking.enabled) {
			InputTracking.mouseReleased(e.isMetaDown() ? 1 : 0);
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
		@Pc(7) int code = e.getKeyCode();
		@Pc(10) int ch = e.getKeyChar();

		if (ch < 30) {
			ch = 0;
		}

		if (code == KeyEvent.VK_LEFT) {
			ch = KEY_LEFT;
		} else if (code == KeyEvent.VK_RIGHT) {
			ch = KEY_RIGHT;
		} else if (code == KeyEvent.VK_UP) {
			ch = KEY_UP;
		} else if (code == KeyEvent.VK_DOWN) {
			ch = KEY_DOWN;
		} else if (code == KeyEvent.VK_CONTROL) {
			ch = KEY_CONTROL;
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			ch = KEY_DELETE;
		} else if (code == KeyEvent.VK_DELETE) {
			ch = KEY_DELETE;
		} else if (code == KeyEvent.VK_TAB) {
			ch = KEY_TAB;
		} else if (code == KeyEvent.VK_ENTER) {
			ch = KEY_ENTER;
		} else if (code == KeyEvent.VK_HOME) {
			ch = KEY_HOME;
		} else if (code == KeyEvent.VK_END) {
			ch = KEY_END;
		} else if (code == KeyEvent.VK_PAGE_UP) {
			ch = 1002;
		} else if (code == KeyEvent.VK_PAGE_DOWN) {
			ch = 1003;
		} else if (code >= KeyEvent.VK_F1 && code <= KeyEvent.VK_F12) {
			ch = (1008 + code) - KeyEvent.VK_F1;
		}

		if (ch > 0 && ch < 128) {
			this.keyDown[ch] = 1;
		}

		if (ch > 4) {
			this.pressedKeys[this.unprocessedKeyCount] = ch;
			this.unprocessedKeyCount = this.unprocessedKeyCount + 1 & 0x7F;
		}

		if (InputTracking.enabled) {
			InputTracking.keyPressed(ch);
		}
	}

	@OriginalMember(owner = "client!a", name = "keyReleased", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyReleased(@OriginalArg(0) KeyEvent e) {
		this.idleCycles = 0;
		@Pc(5) int code = e.getKeyCode();
		@Pc(8) int ch = e.getKeyChar();

		if (ch < 30) {
			ch = 0;
		}

		if (code == KeyEvent.VK_LEFT) {
			ch = 1;
		} else if (code == KeyEvent.VK_RIGHT) {
			ch = 2;
		} else if (code == KeyEvent.VK_UP) {
			ch = 3;
		} else if (code == KeyEvent.VK_DOWN) {
			ch = 4;
		} else if (code == KeyEvent.VK_CONTROL) {
			ch = 5;
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			ch = 8;
		} else if (code == KeyEvent.VK_DELETE) {
			ch = 8;
		} else if (code == KeyEvent.VK_TAB) {
			ch = 9;
		} else if (code == KeyEvent.VK_ENTER) {
			ch = 10;
		} else if (code == KeyEvent.VK_HOME) {
			ch = KEY_HOME;
		} else if (code == KeyEvent.VK_END) {
			ch = KEY_END;
		} else if (code == KeyEvent.VK_PAGE_UP) {
			ch = 1002;
		} else if (code == KeyEvent.VK_PAGE_DOWN) {
			ch = 1003;
		} else if (code >= KeyEvent.VK_F1 && code <= KeyEvent.VK_F12) {
			ch = (1008 + code) - KeyEvent.VK_F1;
		}

		if (ch > 0 && ch < 128) {
			this.keyDown[ch] = 0;
		}

		if (InputTracking.enabled) {
			InputTracking.keyReleased(ch);
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
	protected Component getBaseComponent() {
		return this;
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	public void startThread(@OriginalArg(0) Runnable runnable, @OriginalArg(1) int priority) {
		@Pc(4) Thread thread = new Thread(runnable);
		thread.start();
		thread.setPriority(priority);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	protected void showProgress(@OriginalArg(1) String str, @OriginalArg(2) int progress) {
		while (this.graphics == null) {
			this.graphics = this.getBaseComponent().getGraphics();

			try {
				this.getBaseComponent().repaint();
			} catch (@Pc(22) Exception ex) {
			}

			try {
				Thread.sleep(1000L);
			} catch (@Pc(26) Exception ex) {
			}
		}

		@Pc(37) Font helvetica = new Font("Helvetica", java.awt.Font.BOLD, 13);
		@Pc(44) FontMetrics metrics = this.getBaseComponent().getFontMetrics(helvetica);

		if (this.refresh) {
			this.graphics.setColor(Color.black);
			this.graphics.fillRect(0, 0, this.gameWidth, this.gameHeight);
			this.refresh = false;
		}

		@Pc(91) int y = this.gameHeight / 2 - 18;

		@Pc(84) Color color = new Color(140, 17, 17);
		this.graphics.setColor(color);
		this.graphics.drawRect(this.gameWidth / 2 - 152, y, 304, 34);
		this.graphics.fillRect(this.gameWidth / 2 - 150, y + 2, progress * 3, 30);

		this.graphics.setColor(Color.black);
		this.graphics.fillRect(this.gameWidth / 2 + progress * 3 - 150, y + 2, 300 - progress * 3, 30);

		this.graphics.setFont(helvetica);
		this.graphics.setColor(Color.white);
		this.graphics.drawString(str, (this.gameWidth - metrics.stringWidth(str)) / 2, y + 22);
	}
}
