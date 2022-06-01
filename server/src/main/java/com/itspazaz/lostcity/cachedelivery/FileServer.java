package com.itspazaz.lostcity.cachedelivery;

import com.itspazaz.lostcity.Server;
import net.freeutils.httpserver.*;
import net.freeutils.httpserver.HTTPServer.*;

import java.io.IOException;

public class FileServer {
    HTTPServer http;
    VirtualHost host;

    public void start() throws IOException {
        http = new HTTPServer(Server.httpPort);
        host = http.getVirtualHost(null);

        // Cache archives
        host.addContext("/crc${rand}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/crc");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/title${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/title");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/config${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/config");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/interface${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/interface");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/media${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/media");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/models${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/models");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/textures${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/textures");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/wordenc${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/wordenc");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        host.addContext("/sounds${crc}", new ContextHandler() {
            public int serve(Request req, Response res) throws IOException {
                req.setPath("/sounds");
                res.getHeaders().add("Content-Type", "application/octet-stream");
                return HTTPServer.serveFile(Server.cacheDir.toFile(), "/", req, res);
            }
        });

        // Songs (midis)
        host.addContext("/songs", new FileContextHandler(Server.songDir.toFile()));

        host.setAllowGeneratedIndex(true);
        http.start();
        System.out.println("FileServer: Listening on 0.0.0.0:" + Server.httpPort);
    }

    public void stop() {
        http.stop();
    }
}
