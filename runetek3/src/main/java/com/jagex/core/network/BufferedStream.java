package com.jagex.core.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.jagex.game.runetek3.client.GameShell;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!d")
public class BufferedStream implements Runnable {

	@OriginalMember(owner = "client!d", name = "g", descriptor = "[B")
	private byte[] buffer;

	@OriginalMember(owner = "client!d", name = "h", descriptor = "I")
	private int length;

	@OriginalMember(owner = "client!d", name = "i", descriptor = "I")
	private int offset;

	@OriginalMember(owner = "client!d", name = "e", descriptor = "Z")
	private boolean closed = false;

	@OriginalMember(owner = "client!d", name = "j", descriptor = "Z")
	private boolean writing = false;

	@OriginalMember(owner = "client!d", name = "k", descriptor = "Z")
	private boolean exception = false;

	@OriginalMember(owner = "client!d", name = "f", descriptor = "Lclient!a;")
	private final GameShell shell;

	@OriginalMember(owner = "client!d", name = "d", descriptor = "Ljava/net/Socket;")
	private final Socket socket;

	@OriginalMember(owner = "client!d", name = "b", descriptor = "Ljava/io/InputStream;")
	private final InputStream in;

	@OriginalMember(owner = "client!d", name = "c", descriptor = "Ljava/io/OutputStream;")
	private final OutputStream out;

	@OriginalMember(owner = "client!d", name = "<init>", descriptor = "(Lclient!a;BLjava/net/Socket;)V")
	public BufferedStream(@OriginalArg(0) GameShell shell, @OriginalArg(2) Socket socket) throws IOException {
		this.shell = shell;
		this.socket = socket;
		this.socket.setSoTimeout(30000);
		this.socket.setTcpNoDelay(true);
		this.in = this.socket.getInputStream();
		this.out = this.socket.getOutputStream();
	}

	@OriginalMember(owner = "client!d", name = "a", descriptor = "()V")
	public final void close() {
		this.closed = true;
		try {
			if (this.in != null) {
				this.in.close();
			}
			if (this.out != null) {
				this.out.close();
			}
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (@Pc(22) IOException ignored) {
			System.out.println("Error closing stream");
		}
		this.writing = false;
		synchronized (this) {
			this.notify();
		}
		this.buffer = null;
	}

	@OriginalMember(owner = "client!d", name = "b", descriptor = "()I")
	public final int read() throws IOException {
		return this.closed ? 0 : this.in.read();
	}

	@OriginalMember(owner = "client!d", name = "c", descriptor = "()I")
	public final int available() throws IOException {
		return this.closed ? 0 : this.in.available();
	}

	@OriginalMember(owner = "client!d", name = "a", descriptor = "([BII)V")
	public void read(@OriginalArg(0) byte[] dest, @OriginalArg(1) int off, @OriginalArg(2) int len) throws IOException {
		if (this.closed) {
			return;
		}
		while (len > 0) {
			@Pc(11) int read = this.in.read(dest, off, len);
			if (read <= 0) {
				throw new IOException("EOF");
			}
			off += read;
			len -= read;
		}
	}

	@OriginalMember(owner = "client!d", name = "a", descriptor = "([BIZI)V")
	public void write(@OriginalArg(0) byte[] src, @OriginalArg(1) int len) throws IOException {
		if (this.closed) {
			return;
		}
		if (this.exception) {
			this.exception = false;
			throw new IOException("Error in writer thread");
		}
		if (this.buffer == null) {
			this.buffer = new byte[5000];
		}
		synchronized (this) {
			for (@Pc(31) int i = 0; i < len; i++) {
				this.buffer[this.offset] = src[i + 0];
				this.offset = (this.offset + 1) % 5000;
				if (this.offset == (this.length + 4900) % 5000) {
					throw new IOException("buffer overflow");
				}
			}
			if (!this.writing) {
				this.writing = true;
				this.shell.startThread(this, 2);
			}
			this.notify();
		}
	}

	@OriginalMember(owner = "client!d", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (this.writing) {
			@Pc(38) int off;
			@Pc(27) int len;
			synchronized (this) {
				if (this.offset == this.length) {
					try {
						this.wait();
					} catch (@Pc(16) InterruptedException ignored) {
					}
				}
				if (!this.writing) {
					return;
				}
				len = this.length;
				if (this.offset >= this.length) {
					off = this.offset - this.length;
				} else {
					off = 5000 - this.length;
				}
			}
			if (off > 0) {
				try {
					this.out.write(this.buffer, len, off);
				} catch (@Pc(62) IOException ignored) {
					this.exception = true;
				}
				this.length = (this.length + off) % 5000;
				try {
					if (this.offset == this.length) {
						this.out.flush();
					}
				} catch (@Pc(83) IOException ignored) {
					this.exception = true;
				}
			}
		}
	}

	@OriginalMember(owner = "client!d", name = "a", descriptor = "Z")
	private final boolean flowObfuscator1 = false;

}
