package rs2.webclient;

import com.jagex.core.io.BufferedWebStream;
import com.jagex.core.io.DatabaseStore;
import com.jagex.core.io.FileDownloadStream;

import rs2.client.GlobalConfig;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import org.teavm.jso.JSBody;

import java.io.IOException;
import java.util.Objects;

@OriginalClass("client!sign/signlink")
public class SignedLink implements Runnable {

	@OriginalMember(owner = "client!sign/signlink", name = "clientversion", descriptor = "I")
	private static final int clientversion = 225;

	@OriginalMember(owner = "client!sign/signlink", name = "uid", descriptor = "I")
	public static int uid;

	@OriginalMember(owner = "client!sign/signlink", name = "sunjava", descriptor = "Z")
	public static boolean sunjava;

	@OriginalMember(owner = "client!sign/signlink", name = "active", descriptor = "Z")
	private static boolean active;

	@OriginalMember(owner = "client!sign/signlink", name = "threadliveid", descriptor = "I")
	private static int threadliveid;

	@OriginalMember(owner = "client!sign/signlink", name = "socketip", descriptor = "Ljava/net/InetAddress;")
	private static String socketip;

	@OriginalMember(owner = "client!sign/signlink", name = "socketreq", descriptor = "I")
	private static int socketreq;

	@OriginalMember(owner = "client!sign/signlink", name = "savelen", descriptor = "I")
	private static int savelen;

	@OriginalMember(owner = "client!sign/signlink", name = "midiplay", descriptor = "Z")
	private static boolean midiplay;

	@OriginalMember(owner = "client!sign/signlink", name = "midipos", descriptor = "I")
	private static int midipos;

	@OriginalMember(owner = "client!sign/signlink", name = "midivol", descriptor = "I")
	public static int midivol = 192;

	@OriginalMember(owner = "client!sign/signlink", name = "midifade", descriptor = "I")
	public static boolean midifade;

	@OriginalMember(owner = "client!sign/signlink", name = "waveplay", descriptor = "Z")
	private static boolean waveplay;

	@OriginalMember(owner = "client!sign/signlink", name = "wavepos", descriptor = "I")
	private static int wavepos;

	@OriginalMember(owner = "client!sign/signlink", name = "wavevol", descriptor = "I")
	public static int wavevol = 192;

	@OriginalMember(owner = "client!sign/signlink", name = "socket", descriptor = "Ljava/net/Socket;")
	private static BufferedWebStream socket = null;

	@OriginalMember(owner = "client!sign/signlink", name = "threadreqpri", descriptor = "I")
	private static int threadreqpri = 1;

	@OriginalMember(owner = "client!sign/signlink", name = "threadreq", descriptor = "Ljava/lang/Runnable;")
	private static Runnable threadreq = null;

	@OriginalMember(owner = "client!sign/signlink", name = "dnsreq", descriptor = "Ljava/lang/String;")
	private static String dnsreq = null;

	@OriginalMember(owner = "client!sign/signlink", name = "dns", descriptor = "Ljava/lang/String;")
	public static String dns = null;

	@OriginalMember(owner = "client!sign/signlink", name = "loadreq", descriptor = "Ljava/lang/String;")
	private static String loadreq = null;

	@OriginalMember(owner = "client!sign/signlink", name = "loadbuf", descriptor = "[B")
	private static byte[] loadbuf = null;

	@OriginalMember(owner = "client!sign/signlink", name = "savereq", descriptor = "Ljava/lang/String;")
	private static String savereq = null;

	@OriginalMember(owner = "client!sign/signlink", name = "savebuf", descriptor = "[B")
	private static byte[] savebuf = null;

	@OriginalMember(owner = "client!sign/signlink", name = "urlreq", descriptor = "Ljava/lang/String;")
	private static String urlreq = null;

	@OriginalMember(owner = "client!sign/signlink", name = "urlstream", descriptor = "Ljava/io/DataInputStream;")
	private static FileDownloadStream urlstream = null;

	@OriginalMember(owner = "client!sign/signlink", name = "looprate", descriptor = "I")
	private static int looprate = 50;

	@OriginalMember(owner = "client!sign/signlink", name = "midi", descriptor = "Ljava/lang/String;")
	public static String midi = "none";

	@OriginalMember(owner = "client!sign/signlink", name = "wave", descriptor = "Ljava/lang/String;")
	private static String wave = "none";

	@OriginalMember(owner = "client!sign/signlink", name = "reporterror", descriptor = "Z")
	public static boolean reporterror = true;

	@OriginalMember(owner = "client!sign/signlink", name = "errorname", descriptor = "Ljava/lang/String;")
	public static String errorname = "";

	// sounds
	enum Position {
		LEFT, RIGHT, NORMAL
	}

	private Position curPosition = Position.NORMAL;

	@OriginalMember(owner = "client!sign/signlink", name = "startpriv", descriptor = "(Ljava/net/InetAddress;)V")
	public static void startpriv(@OriginalArg(0) String ip) {
		threadliveid = (int) (Math.random() * 9.9999999E7D);

		if (active) {
			try {
				Thread.sleep(500L);
			} catch (@Pc(10) Exception ignored) {
			}
			active = false;
		}

		socketreq = 0;
		threadreq = null;
		dnsreq = null;
		loadreq = null;
		savereq = null;
		urlreq = null;
		socketip = ip;

		@Pc(33) Thread thread = new Thread(new SignedLink());
		thread.setDaemon(true);
		thread.start();
		while (!active) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(43) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "getuid", descriptor = "(Ljava/lang/String;)I")
	public static int getuid() {
		// TODO: read from DatabaseStore
		return (int)(Math.random() * Integer.MAX_VALUE);
	}

	@OriginalMember(owner = "client!sign/signlink", name = "gethash", descriptor = "(Ljava/lang/String;)J")
	public static long gethash(@OriginalArg(0) String str) {
		@Pc(2) String trimmed = str.trim();
		@Pc(4) long value = 0L;

		for (@Pc(6) int i = 0; i < trimmed.length() && i < 12; i++) {
			@Pc(11) char c = trimmed.charAt(i);
			value *= 37L;
			if (c >= 'A' && c <= 'Z') {
				value += c + 1 - 65;
			} else if (c >= 'a' && c <= 'z') {
				value += c + 1 - 97;
			} else if (c >= '0' && c <= '9') {
				value += c + 27 - 48;
			}
		}

		return value;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "looprate", descriptor = "(I)V")
	public static void looprate(@OriginalArg(0) int rate) {
		looprate = rate;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "cacheload", descriptor = "(Ljava/lang/String;)[B")
	public static synchronized byte[] cacheload(@OriginalArg(0) String name) {
		if (!active) {
			return null;
		}

		loadreq = String.valueOf(name); // gethash(name));
		while (loadreq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(12) Exception ignored) {
			}
		}
		return loadbuf;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "cachesave", descriptor = "(Ljava/lang/String;[B)V")
	public static synchronized void cachesave(@OriginalArg(0) String name, @OriginalArg(1) byte[] src) {
		if (!active || src.length > 2000000) {
			return;
		}

		while (savereq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(11) Exception ignored) {
			}
		}

		savelen = src.length;
		savebuf = src;
		savereq = String.valueOf(name); // gethash(name));
		while (savereq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(28) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "opensocket", descriptor = "(I)Ljava/net/Socket;")
	public static synchronized BufferedWebStream opensocket(@OriginalArg(0) int port) throws IOException {
		socketreq = port;

		while (socketreq != 0) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(6) Exception ignored) {
			}
		}

		if (socket == null) {
			throw new IOException("could not open socket");
		}

		return socket;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "openurl", descriptor = "(Ljava/lang/String;)Ljava/io/DataInputStream;")
	public static synchronized FileDownloadStream openurl(@OriginalArg(0) String url) throws IOException {
		urlreq = GlobalConfig.SERVER_WEB_SCHEMA + "//" + socketip + ":" + GlobalConfig.SERVER_WEB_PORT + "/" + url;

		while (urlreq != null) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(6) Exception ignored) {
			}
		}

		if (urlstream == null) {
			throw new IOException("could not open: " + url);
		}

		return urlstream;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "dnslookup", descriptor = "(Ljava/lang/String;)V")
	public static synchronized void dnslookup(@OriginalArg(0) String name) {
		dns = name;
		dnsreq = name;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "startthread", descriptor = "(Ljava/lang/Runnable;I)V")
	public static synchronized void startthread(@OriginalArg(0) Runnable func, @OriginalArg(1) int priority) {
		threadreqpri = priority;
		threadreq = func;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "wavesave", descriptor = "([BI)Z")
	public static synchronized boolean wavesave(@OriginalArg(0) byte[] src, @OriginalArg(1) int len) {
		if (len > 2000000) {
			return false;
		}

		if (savereq != null) {
			return false;
		}

		wavepos = (wavepos + 1) % 5;
		savelen = len;
		savebuf = src;
		waveplay = true;
		savereq = "sound" + wavepos + ".wav";
		return true;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "wavereplay", descriptor = "()Z")
	public static synchronized boolean wavereplay() {
		if (savereq != null) {
			return false;
		}

		savebuf = null;
		waveplay = true;
		savereq = "sound" + wavepos + ".wav";
		return true;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "midisave", descriptor = "([BI)V")
	public static synchronized void midisave(@OriginalArg(0) byte[] src, @OriginalArg(1) int len) {
		if (len > 2000000 || savereq != null) {
			return;
		}

		midipos = (midipos + 1) % 5;
		savelen = len;
		savebuf = src;
		midiplay = true;
		savereq = "jingle" + midipos + ".mid";
	}

	@OriginalMember(owner = "client!sign/signlink", name = "reporterror", descriptor = "(Ljava/lang/String;)V")
	public static void reporterror(@OriginalArg(0) String err) {
		if (!reporterror || !active) {
			return;
		}

		System.out.println("Error: " + err);
	}

	@JSBody(params = { "data", "vol" }, script = "playWave(data, vol)")
	public static native void jsPlayWave(byte[] data, int vol);

	@JSBody(params = { "vol" }, script = "setWaveVolume(vol)")
	public static native void jsSetWaveVolume(int vol);

	@JSBody(params = { "data", "vol", "fade" }, script = "playMidi(data, vol, fade)")
	public static native void jsPlayMidi(byte[] data, int vol, boolean fade);

	@JSBody(script = "stopMidi()")
	public static native void jsStopMidi();

	@JSBody(params = { "vol" }, script = "setMidiVolume(vol)")
	public static native void jsSetMidiVolume(int vol);

	// adapted from play_members.html's JS loop
	private void audioLoop() {
		try {
			if (!Objects.equals(midi, "none")) {
				if (Objects.equals(midi, "stop")) {
					SignedLink.jsStopMidi();
				} else if (Objects.equals(midi, "voladjust")) {
					SignedLink.jsSetMidiVolume(midivol);
				} else {
					byte[] data = DatabaseStore.getFile(midi);
					SignedLink.jsPlayMidi(data, midivol, midifade);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		midi = "none";

		try {
			if (!Objects.equals(wave, "none")) {
				byte[] data = DatabaseStore.getFile(wave);
				SignedLink.jsPlayWave(data, wavevol); // needs to stick around long enough for the JS to play it
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		wave = "none";
	}

	@OriginalMember(owner = "client!sign/signlink", name = "run", descriptor = "()V")
	@Override
	public void run() {
		DatabaseStore.initDb();

		active = true;
		uid = getuid();

		@Pc(8) int threadid = threadliveid;
		while (threadliveid == threadid) {
			audioLoop();

			if (socketreq != 0) {
				try {
					socket = new BufferedWebStream(socketip, socketreq);
					socket.connect();
				} catch (@Pc(19) Exception ex) {
					ex.printStackTrace();
					socket = null;
				}
				socketreq = 0;
			} else if (threadreq != null) {
				@Pc(31) Thread thread = new Thread(threadreq);
				thread.setDaemon(true);
				thread.start();
				thread.setPriority(threadreqpri);
				threadreq = null;
			} else if (dnsreq != null) {
				dns = dnsreq;
				dnsreq = null;
			} else if (loadreq != null) {
				loadbuf = null;
				try {
					loadbuf = DatabaseStore.getFile(loadreq);
					if (loadbuf != null && loadbuf.length == 0) {
						System.out.println("Failed to load " + loadreq);
						loadbuf = null;
					}
				} catch (@Pc(133) Exception ex) {
					ex.printStackTrace();
				}
				loadreq = null;
			} else if (savereq != null) {
				if (savebuf != null) {
					try {
						DatabaseStore.putFile(savereq, savebuf);
					} catch (@Pc(133) Exception ex) {
						ex.printStackTrace();
					}
				}

				if (waveplay) {
					wave = savereq;
					waveplay = false;
				}

				if (midiplay) {
					midi = savereq;
					midiplay = false;
				}

				savereq = null;
			} else if (urlreq != null) {
				try {
					urlstream = new FileDownloadStream(urlreq);
				} catch (@Pc(178) Exception ignored) {
					urlstream = null;
				}
				urlreq = null;
			}

			try {
				Thread.sleep(looprate);
			} catch (@Pc(187) Exception ignored) {
			}
		}
	}
}
