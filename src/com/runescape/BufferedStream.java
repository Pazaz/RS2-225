package com.runescape;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BufferedStream
        implements Runnable {

    public BufferedStream(GameShell applet, Socket socket)
            throws IOException {
        closed = false;
        writing = false;
        exception = false;
        this.applet = applet;
        this.socket = socket;
        this.socket.setSoTimeout(30000);
        this.socket.setTcpNoDelay(true);
        in = this.socket.getInputStream();
        out = this.socket.getOutputStream();
    }

    public void close() {
        closed = true;

        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException _ex) {
            System.out.println("Error closing stream");
        }

        writing = false;

        synchronized (this) {
            notify();
        }

        buffer = null;
    }

    public int read()
            throws IOException {
        if (closed) {
            return 0;
        }

        return in.read();
    }

    public int available()
            throws IOException {
        if (closed) {
            return 0;
        }

        return in.available();
    }

    public void read(byte[] dest, int off, int len)
            throws IOException {
        if (closed) {
            return;
        }

        int read;
        for (; len > 0; len -= read) {
            read = in.read(dest, off, len);
            if (read <= 0)
                throw new IOException("EOF");
            off += read;
        }
    }

    public void write(byte[] src, int len, int off)
            throws IOException {
        if (closed) {
            return;
        }

        if (exception) {
            exception = false;
            throw new IOException("Error in writer thread");
        }

        if (buffer == null) {
            buffer = new byte[5000];
        }

        synchronized (this) {
            for (int i = 0; i < len; i++) {
                buffer[bufPos] = src[i + off];
                bufPos = (bufPos + 1) % 5000;
                if (bufPos == (bufLen + 4900) % 5000) {
                    throw new IOException("buffer overflow");
                }
            }

            if (!writing) {
                writing = true;
                applet.method12(this, 2);
            }
            notify();
        }
    }

    public void run() {
        while (writing) {
            int off, len;

            synchronized (this) {
                if (bufPos == bufLen) {
                    try {
                        wait();
                    } catch (InterruptedException _ex) {
                    }
                }

                if (!writing) {
                    return;
                }

                len = bufLen;
                if (bufPos >= bufLen) {
                    off = bufPos - bufLen;
                } else {
                    off = 5000 - bufLen;
                }
            }

            if (off > 0) {
                try {
                    out.write(buffer, len, off);
                } catch (IOException _ex) {
                    exception = true;
                }

                bufLen = (bufLen + off) % 5000;

                try {
                    if (bufPos == bufLen) {
                        out.flush();
                    }
                } catch (IOException _ex) {
                    exception = true;
                }
            }
        }
    }

    public InputStream in;
    public OutputStream out;
    public Socket socket;
    public boolean closed;
    public GameShell applet;
    public byte[] buffer;
    public int bufLen;
    public int bufPos;
    public boolean writing;
    public boolean exception;
}
