package com.jagex.runetek3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URI;
import java.util.Map;

public class GameFrame extends Frame {

    public GameFrame(GameShell applet, String title) {
        this.applet = applet;

        setTitle("RuneScape 2 - " + title);
        setResizable(false);

        setLayout(new BorderLayout());

        try {
            Font arial = new Font("Arial", Font.PLAIN, 11);
            Map attributes = arial.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

            JLayeredPane layers = new JLayeredPane();
            layers.setPreferredSize(new Dimension(789, 25));

            ImageIcon backgroundImg = new ImageIcon(this.getClass().getResource("navbar-789.gif"));
            ImageIcon menuImg = new ImageIcon(this.getClass().getResource("navbar_mainmenu.gif"));
            ImageIcon companyImg = new ImageIcon(this.getClass().getResource("navbar_jagex.gif"));
            ImageIcon worldmapImg = new ImageIcon(this.getClass().getResource("navbar_worldmap.gif"));
            ImageIcon manualImg = new ImageIcon(this.getClass().getResource("navbar_manual.gif"));
            ImageIcon rulesImg = new ImageIcon(this.getClass().getResource("navbar_rules.gif"));

            // set up containers
            JLabel background = new JLabel(backgroundImg);
            background.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());

            JLabel company = new JLabel(companyImg);
            company.setBounds(5, 0, companyImg.getIconWidth(), companyImg.getIconHeight());

            JLabel mainMenu = new JLabel(menuImg);
            mainMenu.setBounds(126, 0, menuImg.getIconWidth(), menuImg.getIconHeight());
            mainMenu.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                }
            });
            JLabel mainMenuText = new JLabel();
            mainMenuText.setForeground(Color.WHITE);
            mainMenuText.setFont(arial.deriveFont(attributes));
            mainMenuText.setBounds(126 + menuImg.getIconWidth() + 4, 0, 75, 25);
            mainMenuText.setText("Main Menu");

            JLabel worldSelect = new JLabel(menuImg);
            worldSelect.setBounds(250, 0, menuImg.getIconWidth(), menuImg.getIconHeight());
            worldSelect.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                }
            });
            JLabel worldSelectText = new JLabel();
            worldSelectText.setForeground(Color.WHITE);
            worldSelectText.setFont(arial.deriveFont(attributes));
            worldSelectText.setBounds(250 + menuImg.getIconWidth() + 4, 0, 75, 25);
            worldSelectText.setText("World Select");

            JLabel worldmap = new JLabel(worldmapImg); // TODO: "World Map" label text
            worldmap.setBounds(387, 0, worldmapImg.getIconWidth(), worldmapImg.getIconHeight());
            worldmap.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                }
            });
            JLabel worldmapText = new JLabel();
            worldmapText.setForeground(Color.WHITE);
            worldmapText.setFont(arial.deriveFont(attributes));
            worldmapText.setBounds(387 + worldmapImg.getIconWidth() + 4, 0, 75, 25);
            worldmapText.setText("World Map");

            JLabel manual = new JLabel(manualImg); // TODO: "Manual" label text
            manual.setBounds(520, 0, manualImg.getIconWidth(), manualImg.getIconHeight());
            manual.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create("howtoplay.html"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            JLabel manualText = new JLabel();
            manualText.setForeground(Color.WHITE);
            manualText.setFont(arial.deriveFont(attributes));
            manualText.setBounds(520 + manualImg.getIconWidth() + 4, 0, 50, 25);
            manualText.setText("Manual");

            JLabel rules = new JLabel(rulesImg); // TODO: "Rules & Security" label text
            rules.setBounds(656, 0, rulesImg.getIconWidth(), rulesImg.getIconHeight());
            manual.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(URI.create("rules.html"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            JLabel rulesText = new JLabel();
            rulesText.setForeground(Color.WHITE);
            rulesText.setFont(arial.deriveFont(attributes));
            rulesText.setBounds(656 + rulesImg.getIconWidth() + 4, 0, 100, 25);
            rulesText.setText("Rules & Security");

            // layer images
            layers.add(background, 0);
            layers.add(company, 0);
            layers.add(mainMenu, 0);
            layers.add(mainMenuText, 0);
            layers.add(worldSelect, 0);
            layers.add(worldSelectText, 0);
            layers.add(worldmap, 0);
            layers.add(worldmapText, 0);
            layers.add(manual, 0);
            layers.add(manualText, 0);
            layers.add(rules, 0);
            layers.add(rulesText, 0);
            add(layers, BorderLayout.NORTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        add(applet, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        transferFocus();
        toFront();
    }

    public GameShell applet;
}
