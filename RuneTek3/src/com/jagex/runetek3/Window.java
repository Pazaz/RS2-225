package com.jagex.runetek3;

import java.awt.*;

public class Window extends Frame {

    public Window(Applet applet, String title) {
        this.applet = applet;

        setTitle("RuneScape 2 - " + title);
        setResizable(false);

        setLayout(new BorderLayout());

        add(applet, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        transferFocus();
        toFront();
    }

    public Applet applet;
}
