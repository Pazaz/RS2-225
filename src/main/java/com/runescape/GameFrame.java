package com.runescape;

import java.awt.*;

public class GameFrame extends Frame {

    public GameFrame(GameShell applet) {
        this.applet = applet;

        setTitle("RuneScape 2 - Revision 225");
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
