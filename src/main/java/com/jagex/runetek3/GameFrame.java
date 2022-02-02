package com.jagex.runetek3;

import java.awt.*;

public class GameFrame extends Frame {

    public GameFrame(GameShell applet, String title) {
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

    public GameShell applet;
}
