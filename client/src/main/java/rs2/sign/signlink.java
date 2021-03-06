package rs2.sign;

import java.applet.Applet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import rs2.sound.MidiPlayer;

import javax.sound.midi.MidiSystem;
import javax.sound.sampled.*;

@OriginalClass("client!sign/signlink")
public class signlink implements Runnable {

	@OriginalMember(owner = "client!sign/signlink", name = "clientversion", descriptor = "I")
	private static int clientversion;

	@OriginalMember(owner = "client!sign/signlink", name = "uid", descriptor = "I")
	public static int uid;

	@OriginalMember(owner = "client!sign/signlink", name = "mainapp", descriptor = "Ljava/applet/Applet;")
	public static Applet mainapp;

	@OriginalMember(owner = "client!sign/signlink", name = "sunjava", descriptor = "Z")
	public static boolean sunjava;

	@OriginalMember(owner = "client!sign/signlink", name = "active", descriptor = "Z")
	private static boolean active;

	@OriginalMember(owner = "client!sign/signlink", name = "threadliveid", descriptor = "I")
	private static int threadliveid;

	@OriginalMember(owner = "client!sign/signlink", name = "socketip", descriptor = "Ljava/net/InetAddress;")
	private static InetAddress socketip;

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
	public static int wavevol;

	@OriginalMember(owner = "client!sign/signlink", name = "socket", descriptor = "Ljava/net/Socket;")
	private static Socket socket = null;

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
	private static DataInputStream urlstream = null;

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

	@OriginalMember(owner = "client!sign/signlink", name = "startpriv", descriptor = "(Ljava/net/InetAddress;)V")
	public static void startpriv(@OriginalArg(0) InetAddress ip) {
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
		@Pc(33) Thread thread = new Thread(new signlink());
		thread.setDaemon(true);
		thread.start();
		while (!active) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(43) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "findcachedir", descriptor = "()Ljava/lang/String;")
	private static String findcachedir() {
		@Pc(50) String[] local50 = new String[] { "c:/windows/", "c:/winnt/", "d:/windows/", "d:/winnt/", "e:/windows/", "e:/winnt/", "f:/windows/", "f:/winnt/", "c:/", "~/", "/tmp/", "" };
		@Pc(52) String local52 = ".file_store_32";
		for (@Pc(54) int local54 = 0; local54 < local50.length; local54++) {
			try {
				@Pc(59) String local59 = local50[local54];
				@Pc(67) File local67;
				if (local59.length() > 0) {
					local67 = new File(local59);
					if (!local67.exists()) {
						continue;
					}
				}
				local67 = new File(local59 + local52);
				if (local67.exists() || local67.mkdir()) {
					return local59 + local52 + "/";
				}
			} catch (@Pc(102) Exception ignored) {
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "getuid", descriptor = "(Ljava/lang/String;)I")
	private static int getuid(@OriginalArg(0) String cachedir) {
		try {
			@Pc(11) File file = new File(cachedir + "uid.dat");
			if (!file.exists() || file.length() < 4L) {
				@Pc(34) DataOutputStream dis = new DataOutputStream(new FileOutputStream(cachedir + "uid.dat"));
				dis.writeInt((int) (Math.random() * 9.9999999E7D));
				dis.close();
			}
		} catch (@Pc(44) Exception ignored) {
		}

		try {
			@Pc(59) DataInputStream dis = new DataInputStream(new FileInputStream(cachedir + "uid.dat"));
			@Pc(62) int uid = dis.readInt();
			dis.close();
			return uid + 1;
		} catch (@Pc(69) Exception local69) {
			return 0;
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "gethash", descriptor = "(Ljava/lang/String;)J")
	private static long gethash(@OriginalArg(0) String name) {
		@Pc(2) String str = name.trim();
		@Pc(4) long hash = 0L;
		for (@Pc(6) int i = 0; i < str.length() && i < 12; i++) {
			@Pc(11) char c = str.charAt(i);
			hash *= 37L;
			if (c >= 'A' && c <= 'Z') {
				hash += c + 1 - 65;
			} else if (c >= 'a' && c <= 'z') {
				hash += c + 1 - 97;
			} else if (c >= '0' && c <= '9') {
				hash += c + 27 - 48;
			}
		}
		return hash;
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

		loadreq = String.valueOf(gethash(name));
		while (loadreq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(12) Exception ignored) {
			}
		}

		return loadbuf;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "cachesave", descriptor = "(Ljava/lang/String;[B)V")
	public static synchronized void cachesave(@OriginalArg(0) String name, @OriginalArg(1) byte[] buf) {
		if (!active || buf.length > 2000000) {
			return;
		}
		while (savereq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(11) Exception ignored) {
			}
		}

		savelen = buf.length;
		savebuf = buf;
		savereq = String.valueOf(gethash(name));
		while (savereq != null) {
			try {
				Thread.sleep(1L);
			} catch (@Pc(28) Exception ignored) {
			}
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "opensocket", descriptor = "(I)Ljava/net/Socket;")
	public static synchronized Socket opensocket(@OriginalArg(0) int req) throws IOException {
		socketreq = req;
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
	public static synchronized DataInputStream openurl(@OriginalArg(0) String req) throws IOException {
		urlreq = req;
		while (urlreq != null) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(6) Exception ignored) {
			}
		}

		if (urlstream == null) {
			throw new IOException("could not open: " + req);
		}

		return urlstream;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "dnslookup", descriptor = "(Ljava/lang/String;)V")
	public static synchronized void dnslookup(@OriginalArg(0) String req) {
		dns = req;
		dnsreq = req;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "startthread", descriptor = "(Ljava/lang/Runnable;I)V")
	public static synchronized void startthread(@OriginalArg(0) Runnable req, @OriginalArg(1) int reqpri) {
		threadreqpri = reqpri;
		threadreq = req;
	}

	@OriginalMember(owner = "client!sign/signlink", name = "wavesave", descriptor = "([BI)Z")
	public static synchronized boolean wavesave(@OriginalArg(0) byte[] buf, @OriginalArg(1) int len) {
		if (len > 2000000) {
			return false;
		} else if (savereq == null) {
			wavepos = (wavepos + 1) % 5;
			savelen = len;
			savebuf = buf;
			waveplay = true;
			savereq = "sound" + wavepos + ".wav";
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "wavereplay", descriptor = "()Z")
	public static synchronized boolean wavereplay() {
		if (savereq == null) {
			savebuf = null;
			waveplay = true;
			savereq = "sound" + wavepos + ".wav";
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "midisave", descriptor = "([BI)V")
	public static synchronized void midisave(@OriginalArg(0) byte[] buf, @OriginalArg(1) int len) {
		if (len > 2000000 || savereq != null) {
			return;
		}

		midipos = (midipos + 1) % 5;
		savelen = len;
		savebuf = buf;
		midiplay = true;
		savereq = "jingle" + midipos + ".mid";
	}

	@OriginalMember(owner = "client!sign/signlink", name = "reporterror", descriptor = "(Ljava/lang/String;)V")
	public static void reporterror(@OriginalArg(0) String error) {
		if (!reporterror || !active) {
			return;
		}

		System.out.println("Error: " + error);
		try {
			@Pc(19) String safeuri1 = error.replace('@', '_');
			@Pc(24) String safeuri2 = safeuri1.replace('&', '_');
			@Pc(29) String safeuri = safeuri2.replace('#', '_');
			@Pc(46) DataInputStream dis = openurl("reporterror" + 225 + ".cgi?error=" + errorname + " " + safeuri);
			dis.readLine();
			dis.close();
		} catch (@Pc(53) IOException ignored) {
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "<init>", descriptor = "()V")
	private signlink() {
	}

	private MidiPlayer midiPlayer;
	public boolean midiFadingIn = false;
	public boolean midiFadingOut = false;
	public int midiFadeVol = 0;
	private final Position curPosition = Position.NORMAL;

	enum Position {
		LEFT, RIGHT, NORMAL
	}

	public void playMidi(String music) {
		if (midiFadingOut) {
			return;
		} else if (!midiFadingIn && midifade && midiPlayer.running()) {
			midiFadingOut = true;
			midiFadeVol = midivol;
			return;
		}

		try {
			if (midifade && midiFadingIn) {
				midiFadingOut = false;
				midiFadeVol = 0;
				midiPlayer.play(MidiSystem.getSequence(new File(music)), midifade, midiFadeVol);
			} else {
				midiPlayer.play(MidiSystem.getSequence(new File(music)), midifade, midivol);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// adapted from play_members.html's JS loop
	private void audioLoop() {
		if (midiFadingIn) {
			midiFadeVol += 8;
			if (midiFadeVol > midivol) {
				midiFadeVol = midivol;
			}
			midiPlayer.setVolume(0, midiFadeVol);
			if (midiFadeVol == midivol) {
				midiFadingIn = false;
			}
		} else if (midiFadingOut) {
			midiFadeVol -= 8;
			if (midiFadeVol < 0) {
				midiFadeVol = 0;
			}
			midiPlayer.setVolume(0, midiFadeVol);
			if (midiFadeVol == 0) {
				midiFadingOut = false;
				midiFadingIn = true;
			}
		}

		if (!Objects.equals(midi, "none")) {
			if (Objects.equals(midi, "stop")) {
				midiPlayer.stop();
			} else if (Objects.equals(midi, "voladjust")) {
				midiPlayer.setVolume(0, midivol);
			} else {
				playMidi(midi);
			}

			if (!midiFadingOut) {
				midi = "none";
			}
		}

		if (!Objects.equals(wave, "none")) {
			AudioInputStream audioInputStream;

			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(wave));
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}

			AudioFormat format = audioInputStream.getFormat();
			SourceDataLine auline;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			if (auline.isControlSupported(FloatControl.Type.PAN)) {
				FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
				if (curPosition == Position.RIGHT) {
					pan.setValue(1.0f);
				} else if (curPosition == Position.LEFT) {
					pan.setValue(-1.0f);
				}
			}

			auline.start();
			int nBytesRead = 0;
			int EXTERNAL_BUFFER_SIZE = 524288;
			byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

			try {
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0) {
						auline.write(abData, 0, nBytesRead);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				auline.drain();
				auline.close();
			}

			wave = "none";
		}
	}

	@OriginalMember(owner = "client!sign/signlink", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		active = true;
		@Pc(3) String cachedir = findcachedir();
		uid = getuid(cachedir);

		try {
			midiPlayer = new MidiPlayer();
		} catch (Exception ex) {
		}

		try {
			midiPlayer.setSoundfont(Files.readAllBytes(Paths.get(signlink.class.getResource("SCC1_Florestan.sf2").toURI())));
		} catch (Exception ex) {
			System.err.println("Could not load soundfont, continuing anyways");
		}

		@Pc(8) int liveid = threadliveid;
		while (threadliveid == liveid) {
			audioLoop();

			if (socketreq != 0) {
				try {
					socket = new Socket(socketip, socketreq);
				} catch (@Pc(19) Exception ignored) {
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
				try {
					dns = InetAddress.getByName(dnsreq).getHostName();
				} catch (@Pc(50) Exception ignored) {
					dns = "unknown";
				}
				dnsreq = null;
			} else if (loadreq != null) {
				loadbuf = null;
				try {
					@Pc(71) File file = new File(cachedir + loadreq);
					if (file.exists()) {
						@Pc(78) int length = (int) file.length();
						loadbuf = new byte[length];
						@Pc(96) DataInputStream dis = new DataInputStream(new FileInputStream(cachedir + loadreq));
						dis.readFully(loadbuf, 0, length);
						dis.close();
					}
				} catch (@Pc(105) Exception ignored) {
				}
				loadreq = null;
			} else if (savereq != null) {
				if (savebuf != null) {
					try {
						@Pc(124) FileOutputStream fos = new FileOutputStream(cachedir + savereq);
						fos.write(savebuf, 0, savelen);
						fos.close();
					} catch (@Pc(133) Exception ignored) {
					}
				}
				if (waveplay) {
					wave = cachedir + savereq;
					waveplay = false;
				}
				if (midiplay) {
					midi = cachedir + savereq;
					midiplay = false;
				}
				savereq = null;
			} else if (urlreq != null) {
				try {
					urlstream = new DataInputStream((new URL(mainapp.getCodeBase(), urlreq)).openStream());
				} catch (@Pc(178) Exception local178) {
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
