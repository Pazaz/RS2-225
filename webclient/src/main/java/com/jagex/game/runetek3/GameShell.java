package com.jagex.game.runetek3;

import com.jagex.game.runetek3.graphics.Sprite;
import com.jagex.game.runetek3.graphics.TeaFrameBuffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.canvas.ImageData;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.TextRectangle;

@OriginalClass("client!a")
public class GameShell implements Runnable {

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

	@OriginalMember(owner = "client!a", name = "o", descriptor = "Lclient!qb;")
	protected TeaFrameBuffer frameBuffer;

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

	public HTMLCanvasElement canvas;
	public CanvasRenderingContext2D context;
	public ImageData imageData;

	private void setMousePosition(MouseEvent event) {
		TextRectangle boundingRect = canvas.getBoundingClientRect();
		double scaleX = (double) canvas.getWidth() / boundingRect.getWidth();
		double scaleY = (double) canvas.getHeight() / boundingRect.getHeight();

		this.mouseX = (int) ((event.getClientX() - boundingRect.getLeft()) * scaleX);
		this.mouseY = (int) ((event.getClientY() - boundingRect.getTop()) * scaleY);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(III)V")
	protected final void initApplication(@OriginalArg(0) int height, @OriginalArg(1) int width) {
		this.gameWidth = width;
		this.gameHeight = height;

		this.canvas = (HTMLCanvasElement) HTMLDocument.current().getElementById("game");
		this.canvas.setAttribute("tabindex", "-1");
		this.canvas.setWidth(gameWidth);
		this.canvas.setHeight(gameHeight);

		this.context = (CanvasRenderingContext2D) this.canvas.getContext("2d");
		imageData = context.createImageData(gameWidth, gameHeight);

		this.canvas.addEventListener("mousedown", new EventListener<MouseEvent>() {
			public void handleEvent(MouseEvent event) {
				setMousePosition(event);
				mousePressed(event.getButton() == 2 ? 2 : 1);
			}
		});

		this.canvas.addEventListener("mouseup", new EventListener<MouseEvent>() {
			public void handleEvent(MouseEvent event) {
				setMousePosition(event);
				mouseReleased(event.getButton() == 2 ? 2 : 1);
			}
		});

		this.canvas.addEventListener("mousemove", new EventListener<MouseEvent>() {
			public void handleEvent(MouseEvent event) {
				setMousePosition(event);
				mouseMoved();
			}
		});

		this.canvas.addEventListener("contextmenu", new EventListener<MouseEvent>() {
			public void handleEvent(MouseEvent event) {
				event.preventDefault();
			}
		});

		this.canvas.addEventListener("keydown", new EventListener<KeyboardEvent>() {
			public void handleEvent(KeyboardEvent event) {
				int code = event.getKeyCode();

				char charCode = event.getKey().length() == 1 ? event.getKey().charAt(0) : 65535;

				if (code == 8 || code == 13 || code == 10 || code == 9) {
					charCode = (char) code;
				}

				keyPressed(charCode, code);
			}
		});

		this.canvas.addEventListener("keyup", new EventListener<KeyboardEvent>() {
			public void handleEvent(KeyboardEvent event) {
				int code = event.getKeyCode();

				char charCode = event.getKey().length() == 1 ? event.getKey().charAt(0) : 65535;

				if (code == 8 || code == 13 || code == 10 || code == 9) {
					charCode = (char) code;
				}

				keyReleased(charCode, code);
			}
		});

		start();
		run();
	}

	@OriginalMember(owner = "client!a", name = "run", descriptor = "()V")
	@Override
	public void run() {
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
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(II)V")
	protected final void setLoopRate(@OriginalArg(1) int rate) {
		this.deltime = 1000 / rate;
	}

	@OriginalMember(owner = "client!a", name = "start", descriptor = "()V")
	public final void start() {
		if (this.state >= 0) {
			this.state = 0;
		}
	}

	public final void mousePressed(int button) {
		this.idleCycles = 0;
		this.clickX = this.mouseX;
		this.clickY = this.mouseY;

		if (button != 1) {
			this.mouseButton = 2;
			this.dragButton = 2;
		} else {
			this.mouseButton = 1;
			this.dragButton = 1;
		}

		if (InputTracking.enabled) {
			InputTracking.mousePressed(this.clickX, button, this.clickY);
		}
	}

	public final void mouseReleased(int button) {
		this.idleCycles = 0;
		this.dragButton = 0;

		if (InputTracking.enabled) {
			InputTracking.mouseReleased(button);
		}
	}

	public final void mouseMoved() {
		this.idleCycles = 0;

		if (InputTracking.enabled) {
			InputTracking.mouseMoved(this.mouseY, this.mouseX);
		}
	}

	public final void keyPressed(int ch, int code) {
		this.idleCycles = 0;

		if (ch < 30) {
			ch = 0;
		}

		if (code == 37) {
			ch = KEY_LEFT;
		} else if (code == 39) {
			ch = KEY_RIGHT;
		} else if (code == 38) {
			ch = KEY_UP;
		} else if (code == 40) {
			ch = KEY_DOWN;
		} else if (code == 17) {
			ch = KEY_CONTROL;
		} else if (code == 8) {
			ch = KEY_DELETE;
		} else if (code == 127) {
			ch = KEY_DELETE;
		} else if (code == 9) {
			ch = KEY_TAB;
		} else if (code == 10) {
			ch = KEY_ENTER;
		} else if (code == 36) {
			ch = KEY_HOME;
		} else if (code == 35) {
			ch = KEY_END;
		} else if (code == 33) {
			ch = 1002;
		} else if (code == 34) {
			ch = 1003;
		} else if (code >= 112 && code <= 123) {
			ch = (1008 + code) - 112;
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

	public final void keyReleased(int ch, int code) {
		this.idleCycles = 0;

		if (ch < 30) {
			ch = 0;
		}

		if (code == 37) {
			ch = KEY_LEFT;
		} else if (code == 39) {
			ch = KEY_RIGHT;
		} else if (code == 38) {
			ch = KEY_UP;
		} else if (code == 40) {
			ch = KEY_DOWN;
		} else if (code == 17) {
			ch = KEY_CONTROL;
		} else if (code == 8) {
			ch = KEY_DELETE;
		} else if (code == 127) {
			ch = KEY_DELETE;
		} else if (code == 9) {
			ch = KEY_TAB;
		} else if (code == 10) {
			ch = KEY_ENTER;
		} else if (code == 36) {
			ch = KEY_HOME;
		} else if (code == 35) {
			ch = KEY_END;
		} else if (code == 33) {
			ch = 1002;
		} else if (code == 34) {
			ch = 1003;
		} else if (code >= 112 && code <= 123) {
			ch = (1008 + code) - 112;
		}

		if (ch > 0 && ch < 128) {
			this.keyDown[ch] = 0;
		}

		if (InputTracking.enabled) {
			InputTracking.keyReleased(ch);
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

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	public void startThread(@OriginalArg(0) Runnable runnable, @OriginalArg(1) int priority) {
		@Pc(4) Thread thread = new Thread(runnable);
		thread.start();
		thread.setPriority(priority);
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	protected void showProgress(@OriginalArg(1) String str, @OriginalArg(2) int progress) {
		if (this.refresh) {
			this.refresh = false;
		}
	}
}
