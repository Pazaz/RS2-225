package com.jagex.runescape;

import com.jagex.runescape.scene.PlayerEntity;
import com.jagex.runescape.util.Packet;
import com.jagex.runetek3.Applet;
import com.jagex.runetek3.cache.FileArchive;
import com.jagex.runetek3.formats.*;
import com.jagex.runetek3.graphics.Component;
import com.jagex.runetek3.graphics.Font;
import com.jagex.runetek3.graphics.*;
import com.jagex.runetek3.scene.*;
import com.jagex.runetek3.sound.SoundTrack;
import com.jagex.runetek3.util.*;
import net.burtleburtle.bob.rand.IsaacRandom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.zip.CRC32;

public class Game extends Applet {

    public static String serverAddress = "127.0.0.1";
    public static int serverHttpPort = 80;
    public static int serverGamePort = 43594;

    public static int skyColor = 0x000000;

    public static final int[][] APPEARANCE_COLORS = {
        {
            6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433,
            2983, 54193
        },
        {
            8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153,
            56621, 4783, 1341, 16578, 35003, 25239
        },
        {
            25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094,
            10153, 56621, 4783, 1341, 16578, 35003
        },
        {
            4626, 11146, 6439, 12, 4758, 10270
        },
        {
            4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
        }
    };
    public static final int[] BEARD_COLORS = {
        9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145,
        58654, 5027, 1457, 16565, 34991, 25486
    };

    // interface opcodes
    public static int OPCODE_RETURN = 0;
    public static int OPCODE_SKILL_LEVEL_REAL = 1;
    public static int OPCODE_SKILL_LEVEL = 2;
    public static int OPCODE_SKILL_LEVEL_EXPERIENCE = 3;
    public static int OPCODE_INVENTORY_ITEM_AMOUNT = 4;
    public static int OPCODE_VARIABLE = 5;
    public static int OPCODE_SKILL_EXPERIENCE = 6;
    public static int OPCODE_VARIABLE_MUL = 7;
    public static int OPCODE_COMBAT_LEVEL = 8;
    public static int OPCODE_TOTAL_LEVEL = 9;
    public static int OPCODE_INVENTORY_CAPACITY = 10;
    public static int OPCODE_ENERGY = 11;
    public static int OPCODE_WEIGHT = 12;

    // chat colors
    public static int COLOR_YELLOW = 0;
    public static int COLOR_RED = 1;
    public static int COLOR_GREEN = 2;
    public static int COLOR_CYAN = 3;
    public static int COLOR_PURPLE = 4;
    public static int COLOR_WHITE = 5;
    public static int COLOR_FLASH1 = 6;
    public static int COLOR_FLASH2 = 7;
    public static int COLOR_FLASH3 = 8;
    public static int COLOR_GLOW1 = 9;
    public static int COLOR_GLOW2 = 10;
    public static int COLOR_GLOW3 = 11;
    public int[] textColors = {
        0xffff00, 0xff0000, 0xff00, 0xffff, 0xff00ff, 0xffffff
    };

    // chat effects
    public static int EFFECT_WAVE = 1;
    public static int EFFECT_SCROLL = 2;

    public static Game instance;
    public static PlayerEntity self;
    public static int[] EXPERIENCE_TABLE;
    public static int nodeid = 10;
    public static int portoff = 0;
    public static boolean members = true;
    public static boolean lowMemory;
    public static boolean alreadyStarted;
    public static int clientClock;
    public static BigInteger exponent = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
    public static BigInteger modulus = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static String ASCII_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

    // anticheat counters
    public static int playerAction2Counter;
    public static int updatePlayersCounter;
    public static int itemAction5Counter;
    public static int updateGameCounter;
    public static int updateGameCounter2;
    public static int updateLocCounter;
    public static int itemOption4Counter;
    public static int objectAction4Counter;
    public static int npcAction5Counter;
    public static int drawViewportCounter;
    public static int itemOption1Counter;
    public static int objectAction5Counter;
    public static int npcAction3Counter;
    public static int itemAction4Counter;
    public static int sidebarClickedCounter;
    public int keepaliveCounter;

    static {
        EXPERIENCE_TABLE = new int[99];
        int xpAccum = 0;
        for (int level = 0; level < 99; level++) {
            int nextLevel = level + 1;
            int nextLevelXp = (int) ((double) nextLevel + 300D * Math.pow(2D, (double) nextLevel / 7D));
            xpAccum += nextLevelXp;
            EXPERIENCE_TABLE[level] = xpAccum / 4;
        }
    }

    public int netIdleCycles;
    public int idleTimeout;

    public boolean showSocialInput = false;
    public boolean characterDesignIsMale = true;

    public int lastSceneLevel = -1;
    public int mouseArea;
    public int updateCount;
    public int sceneCycle;
    public int sceneState;
    public int titleState;
    public int worldLocationState;
    public int tutorialIslandState;

    public Buffer loginBuffer = Buffer.reserve(1);
    public Buffer inBuffer = Buffer.reserve(1);
    public Buffer outBuffer = Buffer.reserve(1);
    public CRC32 crc32 = new CRC32();
    public BufferedStream stream;
    public IsaacRandom isaacState;
    public int packetLength;
    public int packetOpcode;
    public int lastPacketOpcode;
    public int secondMostRecentOpcode;
    public int thirdMostRecentOpcode;

    public int[] archiveChecksums = new int[10];
    public FileArchive titleArchive;

    public boolean errorLoading = false;
    public boolean errorStarted = false;
    public boolean errorHost = false;

    public int[] chatOffsets;
    public int[] sidebarOffsets;
    public int[] viewportOffsets;

    public int minimapOffsetCycle;
    public int minimapZoom;
    public int minimapZoomModifier = 1;
    public int[] minimapLineWidth = new int[151];
    public int clickedMinimap;
    public int minimapAnticheatAngle;
    public int minimapAngleModifier = 2;
    public int[] compassLeft = new int[33];
    public int[] minimapLeft = new int[151];
    public int[] compassLineWidth = new int[33];
    public int activeMapFunctionCount;
    public int[] activeMapFunctionX = new int[1000];
    public int[] activeMapFunctionZ = new int[1000];
    public int flagTileX;
    public int flagTileY;

    public int systemUpdateTimer;
    public int sceneDelta;
    public boolean ingame = false;

    public int crossX;
    public int crossY;
    public int crossCycle;
    public int crossType;

    public int hintTileX;
    public int hintTileZ;
    public int hintHeight;
    public int hintOffsetX;
    public int hintOffsetZ;
    public int hintType;
    public int hintNPC;
    public int hintPlayer;

    public int baseTileX;
    public int baseTileZ;
    public int mapLastBaseX;
    public int mapLastBaseZ;
    public byte[][] sceneMapLandData;
    public byte[][] sceneMapLocData;
    public byte[][][] levelRenderFlags;
    public int[][][] levelHeightMaps;
    public MapSquare mapSquare;
    public int[] sceneMapIndex;
    public byte[] tmpTexels = new byte[16384];
    public CollisionMap[] collisionMaps = new CollisionMap[4];
    public LinkedList spotanims = new LinkedList();
    public LinkedList spawnedLocations = new LinkedList();
    public LinkedList[][][] objects = new LinkedList[4][104][104];
    public LinkedList projectiles = new LinkedList();
    public LinkedList locs = new LinkedList();
    public LinkedList temporaryLocs = new LinkedList();
    public int[][] tileRenderCount = new int[104][104];
    public int centerSectorX;
    public int centerSectorY;
    public int localPosX;
    public int localPosZ;
    public final int[] objectGroups = {
        0, 0, 0, 0,
        1, 1, 1, 1,
        1, 2, 2, 2,
        2, 2, 2, 2,
        2, 2, 2, 2,
        2, 2, 3
    };

    public String socialInput = "";
    public String socialMessage = "";
    public String chatbackInput = "";
    public String reportInput = "";
    public int[] chatMessageType = new int[100];
    public String[] chatMessagePrefix = new String[100];
    public String[] chatMessage = new String[100];
    public int[] privateMessageIndex = new int[100];
    public boolean reportAbuseMuteToggle = false;
    public String chatbackMessage;
    public int chatScrollY = 78;
    public int chatEffects;
    public int chatScrollAmount;
    public int chatCount;

    public int deadEntityCount;
    public int[] deadEntityIndices = new int[1000];

    public Component component = new Component();
    public int flashingSidebarId = -1;
    public int sidebarInterfaceId = -1;
    public int menuX;
    public int menuY;
    public int menuWidth;
    public int menuHeight;
    public int optionCount;
    public int drawX = -1;
    public int drawY = -1;
    public int chatbackComponentId = -1;
    public int selectedObject;
    public int selectedObjSlot;
    public int selectedObjInterface;
    public int objInterface;
    public String selectedObjName;
    public int stickyChatbackComponentId = -1;
    public int selectedSpell;
    public int spellInterface;
    public int selectedFlags;
    public String selectedSpellPrefix;
    public int viewportInterfaceIndex = -1;
    public int selectedTab = 3;
    public int socialAction;
    public String[] options = new String[500];
    public int[] paramA = new int[500];
    public int[] paramB = new int[500];
    public int[] paramC = new int[500];
    public int[] actions = new int[500];
    public int[] defaultVariables = new int[2000];
    public int[] variables = new int[2000];
    public int sidebarHoveredInterfaceIndex;
    public int selectedCycle;
    public int selectedInterface;
    public int selectedItem;
    public int selectedArea;
    public boolean objGrabThreshold = false;
    public int hoveredSlot;
    public int hoveredSlotParentId;
    public int openInterfaceId = -1;
    public int hoveredInterfaceIndex;
    public boolean menuVisible = false;
    public boolean chatContinuingDialogue = false;
    public int objDragComponentId;
    public int objDragSlot;
    public int objDragArea;
    public int objGrabX;
    public int objGrabY;
    public int chatHoveredInterfaceIndex;
    public int[] tabComponentId = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1
    };
    public boolean chatbackInputType = false;
    public int viewportHoveredInterfaceIndex;
    public int objDragCycles;
    public int dragCycle;
    public long targetPlayerName;
    public int button;

    public long lastWaveStartTime;
    public int lastWaveId = -1;
    public int[] waveLoops = new int[50];
    public int[] waveId = new int[50];
    public int lastWaveLoops = -1;
    public int waveCount;
    public int[] waveDelay = new int[50];
    public int lastWaveLength;

    public boolean sidebarRedrawIcons = false;
    public boolean sidebarRedraw = false;
    public boolean redrawChatback = false;
    public boolean chatRedrawSettings = false;
    public boolean redrawTitleBackground = false;

    public int MAX_PLAYER_COUNT = 2048;
    public int LOCAL_PLAYER_INDEX = 2047;
    public PlayerEntity[] playerEntities = new PlayerEntity[MAX_PLAYER_COUNT];
    public boolean rights = false;
    public int playerCount;
    public int[] playerIndices = new int[MAX_PLAYER_COUNT];
    public int[] entityUpdateIndices = new int[MAX_PLAYER_COUNT];
    public Buffer[] playerBuffers = new Buffer[MAX_PLAYER_COUNT];
    public int selfPlayerId = -1;
    public int[] characterDesignColors = new int[5];
    public int energy;
    public int wildernessLevel;
    public int[] skillExperience = new int[50];
    public int[] skillLevel = new int[50];
    public int[] skillLevelReal = new int[50];
    public int unreadMessageCount;
    public int lastLoginIP;
    public int inMultizone;
    public int splitPrivateChat;
    public int privateMessageCount;
    public int currentLevel;
    public int daysSinceRecoveryChange;
    public int daysSinceLogin;
    public int[][] pathWaypoint = new int[104][104];
    public int[][] pathDistance = new int[104][104];
    public int[] waypointX = new int[4000];
    public int[] waypointY = new int[4000];
    public boolean characterDesignUpdate = false;
    public int[] characterDesigns = new int[7];
    public int chatTradeDuelSetting;
    public int chatPrivateSetting;
    public int chatPublicSetting;
    public int weightCarried;

    public NPCEntity[] npcEntities = new NPCEntity[8192];
    public int[] npcIndices = new int[8192];
    public int npcCount;

    public boolean midiActive = true;
    public boolean midiThreadActive = false;
    public boolean startMidiThread = false;
    public final Object midiSync = new Object();
    public String midiSyncName;
    public int midiSyncLen;
    public int midiSyncCrc;
    public String currentMidi;
    public int midiSize;
    public int midiCrc;
    public int nextMusicDelay;

    public int friendCount;
    public int ignoreCount;
    public String[] friendName = new String[100];
    public long[] friendName37 = new long[100];
    public int[] friendWorld = new int[100];
    public long[] ignoreName37 = new long[100];

    public int[] flameBuffer1;
    public int[] flameBuffer2;
    public int flameCycle1;
    public int flameCycle2;
    public boolean flameActive = false;
    public int[] flameGradient;
    public int[] flameGradientRed;
    public int[] flameGradientGreen;
    public int[] flameGradientViolet;
    public int[] flameShiftX = new int[256];
    public boolean flamesThreadActive = false;
    public boolean startFlamesThread = false;
    public int flameOffset;
    public int[] flameIntensity;
    public int[] flameIntensityBuffer;

    public int loginFocusedLine;
    public String loginMessage0 = "";
    public String loginMessage1 = "";
    public String username = "";
    public String password = "";

    public int overheadMessageCount = 50;
    public int[] chatScreenX = new int[overheadMessageCount];
    public int[] chatScreenY = new int[overheadMessageCount];
    public int[] chatHeight = new int[overheadMessageCount];
    public int[] chatPadding = new int[overheadMessageCount];
    public int[] chatColors = new int[overheadMessageCount];
    public int[] chatStyles = new int[overheadMessageCount];
    public int[] chatTimers = new int[overheadMessageCount];
    public String[] chatMessages = new String[overheadMessageCount];
    public String input = "";
    public boolean effectsEnabled = true;

    public int cameraX;
    public int cameraY;
    public int cameraZ;
    public int cameraPitch;
    public int cameraOrbitYaw;
    public int cameraAnticheatOffsetX;
    public int cameraOffsetXModifier = 2;
    public int cameraAnticheatOffsetZ;
    public int cameraOffsetZModifier = 2;
    public int cameraAnticheatAngle;
    public int cameraOffsetYawModifier = 1;
    public int[] cameraFrequency = new int[5];
    public int[] unknownCameraVariable = new int[5];
    public int[] cameraAmplitude = new int[5];
    public boolean cameraOriented = false;
    public int[] cameraJitter = new int[5];
    public int cameraMaxY;
    public int cameraOrbitX;
    public int cameraOrbitZ;
    public int cameraMovedWrite;
    public int cameraOrbitPitch = 128;
    public int cameraYaw;
    public int cameraYawModifier;
    public int cameraPitchModifier;
    public int cameraOffsetCycle;
    public boolean[] customCameraActive = new boolean[5];
    public int cameraLocalX;
    public int cameraLocalZ;
    public int cameraHeightOffset;
    public int cameraSpinSpeed;
    public int cameraSpinMultiplier;
    public int cutsceneLocalX;
    public int cutsceneLocalY;
    public int cutsceneHeightOffset;
    public int cutsceneSpinSpeed;
    public int cutsceneSpinMultiplier;

    public int SCROLLBAR_GRIP_LOWLIGHT = 0x332d25;
    public int SCROLLBAR_GRIP_HIGHLIGHT = 0x766654;
    public int SCROLLBAR_TRACK = 0x23201b;
    public int SCROLLBAR_GRIP_FOREGROUND = 0x4d4233;
    public int scrollGripInputPadding;
    public boolean scrollGripHeld = false;

    public Font fontPlain11;
    public Font fontPlain12;
    public Font fontBold12;
    public Font fontQuill8;
    public Sprite compass;
    public Sprite[] hitmarks = new Sprite[20];
    public Sprite[] activeMapFunctions = new Sprite[1000];
    public Sprite[] headicons = new Sprite[20];
    public Sprite sprite;
    public Sprite spriteActive;
    public Sprite imageFlamesLeft;
    public Sprite imageFlamesRight;
    public Sprite mapflag;
    public Sprite minimap;
    public Sprite mapdot1;
    public Sprite mapdot2;
    public Sprite mapdot3;
    public Sprite mapdot4;
    public Sprite[] cross = new Sprite[8];
    public Sprite[] mapfunction = new Sprite[50];
    public IndexedSprite[] imageRunes;
    public IndexedSprite[] sideicons = new IndexedSprite[13];
    public IndexedSprite scrollbar1;
    public IndexedSprite scrollbar2;
    public IndexedSprite redstone1;
    public IndexedSprite redstone2;
    public IndexedSprite redstone3;
    public IndexedSprite redstone1h;
    public IndexedSprite redstone2h;
    public IndexedSprite backbase1;
    public IndexedSprite backbase2;
    public IndexedSprite backhmid1;
    public IndexedSprite inback;
    public IndexedSprite mapback;
    public IndexedSprite chatback;
    public IndexedSprite[] mapscene = new IndexedSprite[50];
    public IndexedSprite redstone1v;
    public IndexedSprite redstone2v;
    public IndexedSprite redstone3v;
    public IndexedSprite redstone1hv;
    public IndexedSprite redstone2hv;
    public IndexedSprite imageTitlebox;
    public IndexedSprite imageTitlebutton;
    public DrawArea backleft1;
    public DrawArea backleft2;
    public DrawArea backright1;
    public DrawArea backright2;
    public DrawArea backtop1;
    public DrawArea backtop2;
    public DrawArea backvmid1;
    public DrawArea backvmid2;
    public DrawArea backvmid3;
    public DrawArea backhmid2;
    public DrawArea titleTop;
    public DrawArea titleBottom;
    public DrawArea titleCenter;
    public DrawArea titleLeft;
    public DrawArea titleRight;
    public DrawArea titleBottomLeft;
    public DrawArea titleBottomRight;
    public DrawArea titleLeftSpace;
    public DrawArea titleRightSpace;
    public DrawArea areaInvback;
    public DrawArea areaMapback;
    public DrawArea areaViewport;
    public DrawArea areaChatback;
    public DrawArea areaBackbase1;
    public DrawArea areaBackbase2;
    public DrawArea areaBackhmid1;

    public Game() {
    }

    public static void setLowMemory() {
        MapSquare.lowMemory = true;
        Draw3D.lowMemory = true;
        lowMemory = true;
        SceneManager.lowMemory = true;
        Signlink.lowMemory = true;
    }

    public static String formatNumber(int i) {
        String s = String.valueOf(i);
        for (int k = s.length() - 3; k > 0; k -= 3)
            s = s.substring(0, k) + "," + s.substring(k);

        if (s.length() > 8)
            s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        else if (s.length() > 4)
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        return " " + s;
    }

    public static String getLevelColorTag(int lvl1, int lvl2) {
        int diff = lvl1 - lvl2;
        if (diff < -9) {
            return "@red@";
        } else if (diff < -6) {
            return "@or3@";
        } else if (diff < -3) {
            return "@or2@";
        } else if (diff < 0) {
            return "@or1@";
        } else if (diff > 9) {
            return "@gre@";
        } else if (diff > 6) {
            return "@gr3@";
        } else if (diff > 3) {
            return "@gr2@";
        } else if (diff > 0) {
            return "@gr1@";
        } else {
            return "@yel@";
        }
    }

    public static String getIntString(int value) {
        if (value < 999999999) {
            return String.valueOf(value);
        } else {
            return "*";
        }
    }

    public static void setHighMemory() {
        MapSquare.lowMemory = false;
        Draw3D.lowMemory = false;
        lowMemory = false;
        SceneManager.lowMemory = false;
        Signlink.lowMemory = false;
    }

    public static String formatObjAmount(int amount) {
        if (amount < 100000) {
            return String.valueOf(amount);
        }

        if (amount < 10000000) {
            return amount / 1000 + "K";
        } else {
            return amount / 1000000 + "M";
        }
    }

    public static void resetParentComponentSeq(int id) {
        if (Component.instances.length < id) {
            return;
        }

        Component parent = Component.instances[id];
        if (parent == null || parent.children == null) {
            return;
        }
        for (int n = 0; n < parent.children.length; n++) {
            if (parent.children[n] == -1) {
                break;
            }

            Component child = Component.instances[parent.children[n]];
            if (child.type == 1) {
                resetParentComponentSeq(child.id);
            }
            child.seqFrame = 0;
            child.seqCycle = 0;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("RS2 user client - release #" + Signlink.clientversion);

            if (args.length == 4) {
                nodeid = Integer.parseInt(args[0]);
                portoff = Integer.parseInt(args[1]);

                if (args[2].equals("lowmem")) {
                    setLowMemory();
                } else if (args[2].equals("highmem")) {
                    setHighMemory();
                } else {
                    System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                    return;
                }

                if (args[3].equals("free")) {
                    members = false;
                } else if (args[3].equals("members")) {
                    members = true;
                } else {
                    System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
                    return;
                }
            } else if (args.length == 2) {
                nodeid = 10;
                portoff = 0;
                setHighMemory();
                members = true;
                serverAddress = args[0];
                serverHttpPort = Integer.parseInt(args[1]);
            } else {
                nodeid = 10;
                portoff = 0;
                setHighMemory();
                members = true;
            }

            Signlink.startpriv(InetAddress.getLocalHost());
            instance = new Game();
            instance.initFrame(532, 789, "Revision 225");

            try {
                java.awt.Font arial = new java.awt.Font("Arial", java.awt.Font.PLAIN, 11);
                Map attributes = arial.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

                JLayeredPane layers = new JLayeredPane();
                layers.setPreferredSize(new Dimension(789, 25));

                ImageIcon backgroundImg = new ImageIcon(Game.class.getResource("navbar.gif"));
                ImageIcon menuImg = new ImageIcon(Game.class.getResource("navbar_mainmenu.gif"));
                ImageIcon companyImg = new ImageIcon(Game.class.getResource("navbar_jagex.gif"));
                ImageIcon worldmapImg = new ImageIcon(Game.class.getResource("navbar_worldmap.gif"));
                ImageIcon manualImg = new ImageIcon(Game.class.getResource("navbar_manual.gif"));
                ImageIcon rulesImg = new ImageIcon(Game.class.getResource("navbar_rules.gif"));

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
                instance.frame.add(layers, BorderLayout.NORTH);
                instance.frame.pack();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setMidi(int crc, String name, int len) {
        if (name == null) {
            return;
        }

        synchronized (midiSync) {
            midiSyncName = name;
            midiSyncCrc = crc;
            midiSyncLen = len;
        }
    }

    public void drawViewport2d() {
        chatCount = 0;
        for (int i = -1; i < playerCount + npcCount; i++) {
            PathingEntity e;

            if (i == -1) {
                e = self;
            } else if (i < playerCount) {
                e = playerEntities[playerIndices[i]];
            } else {
                e = npcEntities[npcIndices[i - playerCount]];
            }

            if (e == null || !e.isValid(false)) {
                continue;
            }

            if (i < playerCount) {
                int l = 30;

                PlayerEntity playerEntity = (PlayerEntity) e;
                if (playerEntity.headicons != 0) {
                    setDrawPos(e.height + 15, e);
                    if (drawX > -1) {
                        for (int l1 = 0; l1 < 8; l1++) {
                            if ((playerEntity.headicons & 1 << l1) != 0) {
                                headicons[l1].draw(drawY - l, drawX - 12);
                                l -= 25;
                            }
                        }
                    }
                }

                if (i >= 0 && hintType == 10 && hintPlayer == playerIndices[i]) {
                    setDrawPos(e.height + 15, e);
                    if (drawX > -1) {
                        headicons[7].draw(drawY - l, drawX - 12);
                    }
                }
            } else if (hintType == 1 && hintNPC == npcIndices[i - playerCount] && clientClock % 20 < 10) {
                setDrawPos(e.height + 15, e);
                if (drawX > -1) {
                    headicons[2].draw(drawY - 28, drawX - 12);
                }
            }

            if (e.spoken != null && (i >= playerCount || chatPublicSetting == 0 || chatPublicSetting == 3 || chatPublicSetting == 1 && isFriend(((PlayerEntity) e).name))) {
                setDrawPos(e.height, e);

                if (drawX > -1 && chatCount < overheadMessageCount) {
                    chatPadding[chatCount] = fontBold12.stringWidth(e.spoken) / 2;
                    chatHeight[chatCount] = fontBold12.height;
                    chatScreenX[chatCount] = drawX;
                    chatScreenY[chatCount] = drawY;
                    chatColors[chatCount] = e.spokenColor;
                    chatStyles[chatCount] = e.spokenEffect;
                    chatTimers[chatCount] = e.textCycle;
                    chatMessages[chatCount++] = e.spoken;

                    if (chatEffects == 0 && e.spokenEffect == 1) {
                        chatHeight[chatCount] += 10;
                        chatScreenY[chatCount] += 5;
                    }

                    if (chatEffects == 0 && e.spokenEffect == 2) {
                        chatPadding[chatCount] = 60;
                    }
                }
            }

            if (e.lastCombatCycle > clientClock + 100) {
                setDrawPos(e.height + 15, e);
                if (drawX > -1) {
                    int w = (e.currentHealth * 30) / e.maxHealth;
                    if (w > 30) {
                        w = 30;
                    }
                    Draw2D.fillRect(drawY - 3, drawX - 15, 0xff00, w, 5);
                    Draw2D.fillRect(drawY - 3, (drawX - 15) + w, 0xff0000, 30 - w, 5);
                }
            }

            if (e.lastCombatCycle > clientClock + 330) {
                setDrawPos(e.height / 2, e);
                if (drawX > -1) {
                    hitmarks[e.damageType].draw(drawY - 12, drawX - 12);
                    fontPlain11.drawCentered(drawY + 4, 0, String.valueOf(e.damageTaken), drawX);
                    fontPlain11.drawCentered(drawY + 3, 0xffffff, String.valueOf(e.damageTaken), drawX - 1);
                }
            }
        }

        for (int k = 0; k < chatCount; k++) {
            int j1 = chatScreenX[k];
            int k1 = chatScreenY[k];
            int i2 = chatPadding[k];
            int j2 = chatHeight[k];
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int k2 = 0; k2 < k; k2++)
                    if (k1 + 2 > chatScreenY[k2] - chatHeight[k2] && k1 - j2 < chatScreenY[k2] + 2
                        && j1 - i2 < chatScreenX[k2] + chatPadding[k2]
                        && j1 + i2 > chatScreenX[k2] - chatPadding[k2]
                        && chatScreenY[k2] - chatHeight[k2] < k1) {
                        k1 = chatScreenY[k2] - chatHeight[k2];
                        flag = true;
                    }

            }
            drawX = chatScreenX[k];
            drawY = chatScreenY[k] = k1;
            String s = chatMessages[k];
            if (chatEffects == 0) {
                int color = 0xffff00;
                if (chatColors[k] < 6)
                    color = textColors[chatColors[k]];
                if (chatColors[k] == 6)
                    color = sceneCycle % 20 >= 10 ? 0xffff00 : 0xff0000;
                if (chatColors[k] == 7)
                    color = sceneCycle % 20 >= 10 ? 65535 : 255;
                if (chatColors[k] == 8)
                    color = sceneCycle % 20 >= 10 ? 0x80ff80 : 45056;
                if (chatColors[k] == 9) {
                    int i3 = 150 - chatTimers[k];
                    if (i3 < 50)
                        color = 0xff0000 + 1280 * i3;
                    else if (i3 < 100)
                        color = 0xffff00 - 0x50000 * (i3 - 50);
                    else if (i3 < 150)
                        color = 65280 + 5 * (i3 - 100);
                }
                if (chatColors[k] == 10) {
                    int j3 = 150 - chatTimers[k];
                    if (j3 < 50)
                        color = 0xff0000 + 5 * j3;
                    else if (j3 < 100)
                        color = 0xff00ff - 0x50000 * (j3 - 50);
                    else if (j3 < 150)
                        color = (255 + 0x50000 * (j3 - 100)) - 5 * (j3 - 100);
                }
                if (chatColors[k] == 11) {
                    int k3 = 150 - chatTimers[k];
                    if (k3 < 50)
                        color = 0xffffff - 0x50005 * k3;
                    else if (k3 < 100)
                        color = 65280 + 0x50005 * (k3 - 50);
                    else if (k3 < 150)
                        color = 0xffffff - 0x50000 * (k3 - 100);
                }
                if (chatStyles[k] == 0) {
                    fontBold12.drawCentered(drawY + 1, 0, s, drawX);
                    fontBold12.drawCentered(drawY, color, s, drawX);
                }
                if (chatStyles[k] == 1) {
                    fontBold12.drawCenteredWave(sceneCycle, drawX, drawY + 1, 0, s);
                    fontBold12.drawCenteredWave(sceneCycle, drawX, drawY, color, s);
                }
                if (chatStyles[k] == 2) {
                    int l3 = fontBold12.stringWidth(s);
                    int i4 = ((150 - chatTimers[k]) * (l3 + 100)) / 150;
                    Draw2D.setBounds(334, 0, drawX + 50, drawX - 50);
                    fontBold12.draw((drawX + 50) - i4, drawY + 1, 0, s);
                    fontBold12.draw((drawX + 50) - i4, drawY, color, s);
                    Draw2D.resetBounds();
                }
            } else {
                fontBold12.drawCentered(drawY + 1, 0, s, drawX);
                fontBold12.drawCentered(drawY, 0xffff00, s, drawX);
            }
        }
    }

    public void closeInterface() {
        outBuffer.p1isaac(Packet.Client.INTERFACE_CLOSED);
        if (sidebarInterfaceId != -1) {
            sidebarInterfaceId = -1;
            sidebarRedraw = true;
            chatContinuingDialogue = false;
            sidebarRedrawIcons = true;
        }
        if (chatbackComponentId != -1) {
            chatbackComponentId = -1;
            redrawChatback = true;
            chatContinuingDialogue = false;
        }
        viewportInterfaceIndex = -1;
    }

    public void midistop() {
        Signlink.midifade = false;
        Signlink.midi = "stop";
    }

    public void drawWildyLevel() {
        int j = (self.x >> 7) + baseTileX;
        int k = (self.z >> 7) + baseTileZ;
        if (j >= 2944 && j < 3392 && k >= 3520 && k < 6400)
            wildernessLevel = 1 + (k - 3520) / 8;
        else if (j >= 2944 && j < 3392 && k >= 9920 && k < 12800)
            wildernessLevel = 1 + (k - 9920) / 8;
        else
            wildernessLevel = 0;
        worldLocationState = 0;
        if (j >= 3328 && j < 3392 && k >= 3200 && k < 3264) {
            int l = j & 0x3f;
            int i1 = k & 0x3f;
            if (l >= 4 && l <= 29 && i1 >= 44 && i1 <= 58)
                worldLocationState = 1;
            if (l >= 36 && l <= 61 && i1 >= 44 && i1 <= 58)
                worldLocationState = 1;
            if (l >= 4 && l <= 29 && i1 >= 25 && i1 <= 39)
                worldLocationState = 1;
            if (l >= 36 && l <= 61 && i1 >= 25 && i1 <= 39)
                worldLocationState = 1;
            if (l >= 4 && l <= 29 && i1 >= 6 && i1 <= 20)
                worldLocationState = 1;
            if (l >= 36 && l <= 61 && i1 >= 6 && i1 <= 20)
                worldLocationState = 1;
        }
        if (worldLocationState == 0 && j >= 3328 && j <= 3393 && k >= 3203 && k <= 3325)
            worldLocationState = 2;
        tutorialIslandState = 0;
        if (j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136)
            tutorialIslandState = 1;
        if (j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535)
            tutorialIslandState = 1;
        if (tutorialIslandState == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062)
            tutorialIslandState = 0;
    }

    public void drawChat() {
        if (splitPrivateChat == 0)
            return;
        Font font = fontPlain12;
        int j = 0;
        if (systemUpdateTimer != 0)
            j = 1;
        for (int k = 0; k < 100; k++)
            if (chatMessage[k] != null) {
                int l = chatMessageType[k];
                if ((l == 3 || l == 7)
                    && (l == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[k]))) {
                    int i1 = 329 - j * 13;
                    font.draw(4, i1, 0,
                        "From " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    font.draw(4, i1 - 1, 65535,
                        "From " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 5 && chatPrivateSetting < 2) {
                    int j1 = 329 - j * 13;
                    font.draw(4, j1, 0, chatMessage[k]);
                    font.draw(4, j1 - 1, 65535, chatMessage[k]);
                    if (++j >= 5)
                        return;
                }
                if (l == 6 && chatPrivateSetting < 2) {
                    int k1 = 329 - j * 13;
                    font.draw(4, k1, 0,
                        "To " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    font.draw(4, k1 - 1, 65535,
                        "To " + chatMessagePrefix[k] + ": " + chatMessage[k]);
                    if (++j >= 5)
                        return;
                }
            }

    }

    public void updateNpcMasks(Buffer buffer) {
        for (int n = 0; n < updateCount; n++) {
            int nid = entityUpdateIndices[n];
            NPCEntity npc = npcEntities[nid];

            int mask = buffer.g1();

            // Play animation
            if ((mask & 2) == 2) {
                int j1 = buffer.g2();
                if (j1 == 65535) {
                    j1 = -1;
                }

                if (j1 == npc.primarySeq) {
                    npc.primarySeqPlays = 0;
                }

                int l1 = buffer.g1();
                if (j1 == -1 || npc.primarySeq == -1
                    || SeqType.instances[j1].priority > SeqType.instances[npc.primarySeq].priority
                    || SeqType.instances[npc.primarySeq].priority == 0) {
                    npc.primarySeq = j1;
                    npc.primarySeqFrame = 0;
                    npc.primarySeqCycle = 0;
                    npc.primarySeqDelay = l1;
                    npc.primarySeqPlays = 0;
                }
            }

            // Face entity
            if ((mask & 4) == 4) {
                npc.targetEntity = buffer.g2();
                if (npc.targetEntity == 65535) {
                    npc.targetEntity = -1;
                }
            }

            // Chat
            if ((mask & 8) == 8) {
                npc.spoken = buffer.gString();
                npc.textCycle = 100;
            }

            // Combat hit
            if ((mask & 0x10) == 16) {
                npc.damageTaken = buffer.g1();
                npc.damageType = buffer.g1();
                npc.lastCombatCycle = clientClock + 400;
                npc.currentHealth = buffer.g1();
                npc.maxHealth = buffer.g1();
            }

            // Change NPC
            if ((mask & 0x20) == 32) {
                npc.info = NPCType.get(buffer.g2());
                npc.runSeq = npc.info.walkSeq;
                npc.walkSeq = npc.info.turnAroundSeq;
                npc.turnAroundSeq = npc.info.turnRightSeq;
                npc.turnRightSeq = npc.info.turnLeftSeq;
                npc.standSeq = npc.info.standSeq;
            }

            // Play graphic
            if ((mask & 0x40) == 64) {
                npc.spotAnimIndex = buffer.g2();
                int k1 = buffer.g4();
                npc.spotAnimOffsetY = k1 >> 16;
                npc.lastSpotAnimCycle = clientClock + (k1 & 0xffff);
                npc.spotAnimFrame = 0;
                npc.spotAnimCycle = 0;
                if (npc.lastSpotAnimCycle > clientClock) {
                    npc.spotAnimFrame = -1;
                }

                if (npc.spotAnimIndex == 65535) {
                    npc.spotAnimIndex = -1;
                }
            }

            // Focus on tile
            if ((mask & 0x80) == 128) {
                npc.focusX = buffer.g2();
                npc.focusY = buffer.g2();
            }
        }
    }

    public void addIgnore(long l) {
        if (l == 0L)
            return;
        if (ignoreCount >= 100) {
            addMessage(0, "Your ignore list is full. Max of 100 hit", "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int i = 0; i < ignoreCount; i++)
            if (ignoreName37[i] == l) {
                addMessage(0, s + " is already on your ignore list", "");
                return;
            }

        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, "Please remove " + s + " from your friend list first", "");
                return;
            }

        ignoreName37[ignoreCount++] = l;
        sidebarRedraw = true;
        outBuffer.p1isaac(Packet.Client.IGNORELIST_ADD);
        outBuffer.p8(l);
    }

    public void readSecondaryPacket(Buffer b, int opcode) {
        if (opcode == Packet.Server.ADD_LOCATION || opcode == Packet.Server.LOC_DEL) {
            int j = b.g1();
            int k2 = localPosX + (j >> 4 & 7);
            int l4 = localPosZ + (j & 7);
            int i7 = b.g1();
            int j9 = i7 >> 2;
            int j11 = i7 & 3;
            int i13 = objectGroups[j9];
            int i14;
            if (opcode == 76)
                i14 = -1;
            else
                i14 = b.g2();
            if (k2 >= 0 && l4 >= 0 && k2 < 104 && l4 < 104) {
                SpawnedLoc loc = null;
                for (SpawnedLoc loc2 = (SpawnedLoc) spawnedLocations
                    .peekLast(); loc2 != null; loc2 = (SpawnedLoc) spawnedLocations
                    .getPrevious()) {
                    if (loc2.level != currentLevel || loc2.tileX != k2
                        || loc2.tileZ != l4 || loc2.classType != i13)
                        continue;
                    loc = loc2;
                    break;
                }

                if (loc == null) {
                    int i16 = 0;
                    int l16 = -1;
                    int j17 = 0;
                    int l17 = 0;
                    if (i13 == 0)
                        i16 = mapSquare.getWallBitset(currentLevel, k2, l4);
                    if (i13 == 1)
                        i16 = mapSquare.getWallDecorationBitset(currentLevel, l4, k2);
                    if (i13 == 2)
                        i16 = mapSquare.getLocationBitset(currentLevel, k2, l4);
                    if (i13 == 3)
                        i16 = mapSquare.getGroundDecorationBitset(currentLevel, k2, l4);
                    if (i16 != 0) {
                        int i18 = mapSquare.getInfo(currentLevel, k2, l4, i16);
                        l16 = i16 >> 14 & 0x7fff;
                        j17 = i18 & 0x1f;
                        l17 = i18 >> 6;
                    }
                    loc = new SpawnedLoc();
                    loc.level = currentLevel;
                    loc.classType = i13;
                    loc.tileX = k2;
                    loc.tileZ = l4;
                    loc.lastLocIndex = l16;
                    loc.lastType = j17;
                    loc.lastRotation = l17;
                    spawnedLocations.pushNext(loc);
                }
                loc.locIndex = i14;
                loc.type = j9;
                loc.rotation = j11;
                addLoc(j11, k2, l4, i13, i14, j9, currentLevel);
            }
            return;
        }
        if (opcode == Packet.Server.LOC_ANIM) {
            int k = b.g1();
            int l2 = localPosX + (k >> 4 & 7);
            int i5 = localPosZ + (k & 7);
            int j7 = b.g1();
            int k9 = j7 >> 2;
            int k11 = objectGroups[k9];
            int j13 = b.g2();
            if (l2 >= 0 && i5 >= 0 && l2 < 104 && i5 < 104) {
                int j14 = 0;
                if (k11 == 0)
                    j14 = mapSquare.getWallBitset(currentLevel, l2, i5);
                if (k11 == 1)
                    j14 = mapSquare.getWallDecorationBitset(currentLevel, i5, l2);
                if (k11 == 2)
                    j14 = mapSquare.getLocationBitset(currentLevel, l2, i5);
                if (k11 == 3)
                    j14 = mapSquare.getGroundDecorationBitset(currentLevel, l2, i5);
                if (j14 != 0) {
                    LocEntity locEntity = new LocEntity(false, j14 >> 14 & 0x7fff, currentLevel, k11,
                        SeqType.instances[j13], i5, l2);
                    locs.pushNext(locEntity);
                }
            }
            return;
        }
        if (opcode == Packet.Server.ADD_OBJECT_STACK) {
            int l = b.g1();
            int x = localPosX + (l >> 4 & 7);
            int z = localPosZ + (l & 7);
            int k7 = b.g2();
            int l9 = b.g2();
            if (x >= 0 && z >= 0 && x < 104 && z < 104) {
                ObjStackEntity objStackEntity = new ObjStackEntity();
                objStackEntity.model = k7;
                objStackEntity.amount = l9;
                if (objects[currentLevel][x][z] == null) {
                    objects[currentLevel][x][z] = new LinkedList();
                }
                objects[currentLevel][x][z].pushNext(objStackEntity);
                updateObjectStack(x, z);
            }
            return;
        }
        if (opcode == Packet.Server.REMOVE_OBJECT_STACK) {
            int i1 = b.g1();
            int x = localPosX + (i1 >> 4 & 7);
            int z = localPosZ + (i1 & 7);
            int l7 = b.g2();
            if (x >= 0 && z >= 0 && x < 104 && z < 104) {
                LinkedList linkedList = objects[currentLevel][x][z];
                if (linkedList != null) {
                    for (ObjStackEntity objStackEntity_1 = (ObjStackEntity) linkedList
                        .peekLast(); objStackEntity_1 != null; objStackEntity_1 = (ObjStackEntity) linkedList
                        .getPrevious()) {
                        if (objStackEntity_1.model != (l7 & 0x7fff))
                            continue;
                        objStackEntity_1.unlink();
                        break;
                    }

                    if (linkedList.peekLast() == null)
                        objects[currentLevel][x][z] = null;
                    updateObjectStack(x, z);
                }
            }
            return;
        }
        if (opcode == Packet.Server.ADD_PROJECTILE) {
            int j1 = b.g1();
            int k3 = localPosX + (j1 >> 4 & 7);
            int l5 = localPosZ + (j1 & 7);
            int i8 = k3 + b.g1s();
            int i10 = l5 + b.g1s();
            int l11 = b.g2s();
            int k13 = b.g2();
            int k14 = b.g1();
            int i15 = b.g1();
            int k15 = b.g2();
            int j16 = b.g2();
            int i17 = b.g1();
            int k17 = b.g1();
            if (k3 >= 0 && l5 >= 0 && k3 < 104 && l5 < 104 && i8 >= 0 && i10 >= 0 && i8 < 104 && i10 < 104) {
                k3 = k3 * 128 + 64;
                l5 = l5 * 128 + 64;
                i8 = i8 * 128 + 64;
                i10 = i10 * 128 + 64;
                ProjectileEntity projectileEntity = new ProjectileEntity(i15, i17, l5, j16 + clientClock, currentLevel, l11,
                    k15 + clientClock, k17, getLandY(currentLevel, k3, l5) - k14, k13, k3);
                projectileEntity.setTarget(getLandY(currentLevel, i8, i10) - i15, i10, i8, k15 + clientClock);
                projectiles.pushNext(projectileEntity);
            }
            return;
        }
        if (opcode == Packet.Server.SPOTANIM_SPECIFIC) {
            int k1 = b.g1();
            int l3 = localPosX + (k1 >> 4 & 7);
            int i6 = localPosZ + (k1 & 7);
            int j8 = b.g2();
            int j10 = b.g1();
            int i12 = b.g2();
            if (l3 >= 0 && i6 >= 0 && l3 < 104 && i6 < 104) {
                l3 = l3 * 128 + 64;
                i6 = i6 * 128 + 64;
                SpotAnimEntity spotAnimEntity = new SpotAnimEntity(l3, j8, i6, i12,
                    getLandY(currentLevel, l3, i6) - j10, currentLevel, clientClock);
                spotanims.pushNext(spotAnimEntity);
            }
            return;
        }
        if (opcode == Packet.Server.ADD_PRIVATE_OBJECT_STACK) {
            int l1 = b.g1();
            int x = localPosX + (l1 >> 4 & 7);
            int z = localPosZ + (l1 & 7);
            int k8 = b.g2();
            int k10 = b.g2();
            int j12 = b.g2();
            if (x >= 0 && z >= 0 && x < 104 && z < 104 && j12 != selfPlayerId) {
                ObjStackEntity obj = new ObjStackEntity();
                obj.model = k8;
                obj.amount = k10;
                if (objects[currentLevel][x][z] == null) {
                    objects[currentLevel][x][z] = new LinkedList();
                }
                objects[currentLevel][x][z].pushNext(obj);
                updateObjectStack(x, z);
            }
            return;
        }
        if (opcode == Packet.Server.ATTACH_TEMPORARY_LOCATION_TO_PLAYER) {
            int i2 = b.g1();
            int x = localPosX + (i2 >> 4 & 7);
            int z = localPosZ + (i2 & 7);
            int l8 = b.g1();
            int l10 = l8 >> 2;
            int k12 = l8 & 3;
            int l13 = objectGroups[l10];
            int l14 = b.g2();
            int j15 = b.g2();
            int l15 = b.g2();
            int k16 = b.g2();
            byte byte1 = b.g1s();
            byte byte2 = b.g1s();
            byte byte3 = b.g1s();
            byte byte4 = b.g1s();
            PlayerEntity e;
            if (k16 == selfPlayerId)
                e = self;
            else
                e = playerEntities[k16];
            if (e != null) {
                TemporaryLoc loc1 = new TemporaryLoc(currentLevel, k12, z, j15 + clientClock, l10, -1, x, l13);
                temporaryLocs.pushNext(loc1);
                TemporaryLoc loc2 = new TemporaryLoc(currentLevel, k12, z, l15 + clientClock, l10, l14, x, l13);
                temporaryLocs.pushNext(loc2);
                int j18 = levelHeightMaps[currentLevel][x][z];
                int k18 = levelHeightMaps[currentLevel][x + 1][z];
                int l18 = levelHeightMaps[currentLevel][x + 1][z + 1];
                int i19 = levelHeightMaps[currentLevel][x][z + 1];
                LocType locType = LocType.get(l14);
                e.locFirstCycle = j15 + clientClock;
                e.locLastCycle = l15 + clientClock;
                e.locModel = locType.getModel(l10, k12, j18, k18, l18, i19, -1);
                int j19 = locType.sizeX;
                int k19 = locType.sizeZ;
                if (k12 == 1 || k12 == 3) {
                    j19 = locType.sizeZ;
                    k19 = locType.sizeX;
                }
                e.locSceneX = x * 128 + j19 * 64;
                e.locSceneZ = z * 128 + k19 * 64;
                e.locSceneY = getLandY(currentLevel, e.locSceneX,
                    e.locSceneZ);
                if (byte1 > byte3) {
                    byte byte5 = byte1;
                    byte1 = byte3;
                    byte3 = byte5;
                }
                if (byte2 > byte4) {
                    byte byte6 = byte2;
                    byte2 = byte4;
                    byte4 = byte6;
                }
                e.locMinTileX = x + byte1;
                e.locMaxTileX = x + byte3;
                e.locMinTileZ = z + byte2;
                e.locMaxTileZ = z + byte4;
            }
        }
        if (opcode == Packet.Server.UPDATE_OBJECT_STACK) {
            int j2 = b.g1();
            int x = localPosX + (j2 >> 4 & 7);
            int z = localPosZ + (j2 & 7);
            int i9 = b.g2();
            int i11 = b.g2();
            int l12 = b.g2();
            if (x >= 0 && z >= 0 && x < 104 && z < 104) {
                LinkedList obj = objects[currentLevel][x][z];
                if (obj != null) {
                    for (ObjStackEntity objStack = (ObjStackEntity) obj
                        .peekLast(); objStack != null; objStack = (ObjStackEntity) obj
                        .getPrevious()) {
                        if (objStack.model != (i9 & 0x7fff) || objStack.amount != i11) {
                            continue;
                        }
                        objStack.amount = l12;
                        break;
                    }

                    updateObjectStack(x, z);
                }
            }
        }
    }

    public int getTopLevel() {
        int level = 3;
        int ctx = cameraX >> 7;
        int ctz = cameraZ >> 7;
        if ((ctx < 0) || (ctz < 0) || (ctx > 103) || (ctz > 103)) {
            return level;
        }
        if (cameraPitch < 310) {
            int l = self.x >> 7;
            int i1 = self.z >> 7;
            if ((levelRenderFlags[currentLevel][ctx][ctz] & 4) != 0) {
                level = currentLevel;
            }
            int j1;
            if (l > ctx) {
                j1 = l - ctx;
            } else {
                j1 = ctx - l;
            }
            int k1;
            if (i1 > ctz) {
                k1 = i1 - ctz;
            } else {
                k1 = ctz - i1;
            }
            if (j1 > k1) {
                int l1 = (k1 * 0x10000) / j1;
                int j2 = 0x8000;
                while (ctx != l) {
                    if (ctx < l) {
                        ctx++;
                    } else if (ctx > l) {
                        ctx--;
                    }
                    if ((levelRenderFlags[currentLevel][ctx][ctz] & 4) != 0) {
                        level = currentLevel;
                    }
                    j2 += l1;
                    if (j2 >= 0x10000) {
                        j2 -= 0x10000;
                        if (ctz < i1) {
                            ctz++;
                        } else if (ctz > i1) {
                            ctz--;
                        }
                        if ((levelRenderFlags[currentLevel][ctx][ctz] & 4) != 0) {
                            level = currentLevel;
                        }
                    }
                }
            } else {
                int i2 = (j1 * 0x10000) / k1;
                int k2 = 0x8000;
                while (ctz != i1) {
                    if (ctz < i1) {
                        ctz++;
                    } else if (ctz > i1) {
                        ctz--;
                    }
                    if ((levelRenderFlags[currentLevel][ctx][ctz] & 4) != 0) {
                        level = currentLevel;
                    }
                    k2 += i2;
                    if (k2 >= 0x10000) {
                        k2 -= 0x10000;
                        if (ctx < l) {
                            ctx++;
                        } else if (ctx > l) {
                            ctx--;
                        }
                        if ((levelRenderFlags[currentLevel][ctx][ctz] & 4) != 0) {
                            level = currentLevel;
                        }
                    }
                }
            }
        }
        if ((levelRenderFlags[currentLevel][self.x >> 7][self.z >> 7] & 4) != 0) {
            level = currentLevel;
        }
        return level;
    }

    public int getCameraPlaneCutscene() {
        int j = getLandY(currentLevel, cameraX, cameraZ);
        if (j - cameraY < 800 && (levelRenderFlags[currentLevel][cameraX >> 7][cameraZ >> 7] & 4) != 0)
            return currentLevel;
        else
            return 3;
    }

    public void drawViewport() {
        sceneCycle++;
        updateScenePlayers();
        updateSceneNpcs();
        updateSceneProjectiles();
        updateSceneSpotAnims();
        updateSceneSeqLocs();
        if (!cameraOriented) {
            int j = cameraOrbitPitch;
            if (cameraMaxY / 256 > j)
                j = cameraMaxY / 256;
            if (customCameraActive[4] && cameraAmplitude[4] + 128 > j)
                j = cameraAmplitude[4] + 128;
            int l = cameraYaw + cameraAnticheatAngle & 0x7ff;
            updateCameraOrbit(
                getLandY(currentLevel, self.x,
                    self.z) - 50,
                cameraOrbitX, l, j, cameraOrbitZ, 600 + j * 3);
            drawViewportCounter++;
            if (drawViewportCounter > 1802) {
                drawViewportCounter = 0;
                outBuffer.p1isaac(Packet.Client.ANTICHEAT_UPDATE_VIEWPORT);
                outBuffer.p1(0);
                int j1 = outBuffer.offset;
                outBuffer.p2(29711);
                outBuffer.p1(70);
                outBuffer.p1((int) (Math.random() * 256D));
                outBuffer.p1(242);
                outBuffer.p1(186);
                outBuffer.p1(39);
                outBuffer.p1(61);
                if ((int) (Math.random() * 2D) == 0)
                    outBuffer.p1(13);
                if ((int) (Math.random() * 2D) == 0)
                    outBuffer.p2(57856);
                outBuffer.p2((int) (Math.random() * 65536D));
                outBuffer.pSize(outBuffer.offset - j1);
            }
        }
        int k;
        if (!cameraOriented)
            k = getTopLevel();
        else
            k = getCameraPlaneCutscene();
        int i1 = cameraX;
        int k1 = cameraY;
        int l1 = cameraZ;
        int i2 = cameraPitch;
        int j2 = cameraOrbitYaw;
        for (int k2 = 0; k2 < 5; k2++)
            if (customCameraActive[k2]) {
                int l2 = (int) ((Math.random() * (double) (cameraJitter[k2] * 2 + 1) - (double) cameraJitter[k2])
                    + Math.sin((double) unknownCameraVariable[k2] * ((double) cameraFrequency[k2] / 100D))
                    * (double) cameraAmplitude[k2]);
                if (k2 == 0)
                    cameraX += l2;
                if (k2 == 1)
                    cameraY += l2;
                if (k2 == 2)
                    cameraZ += l2;
                if (k2 == 3)
                    cameraOrbitYaw = cameraOrbitYaw + l2 & 0x7ff;
                if (k2 == 4) {
                    cameraPitch += l2;
                    if (cameraPitch < 128)
                        cameraPitch = 128;
                    if (cameraPitch > 383)
                        cameraPitch = 383;
                }
            }

        int i3 = Draw3D.cycle;
        Model.allowInput = true;
        Model.resourceCount = 0;
        Model.cursorX = super.mouseX - 8;
        Model.cursorY = super.mouseY - 11;
        Draw2D.fillRect(0, 0, skyColor, super.gameWidth, super.gameHeight);
        mapSquare.draw(cameraOrbitYaw, cameraX, k, cameraPitch, cameraY, cameraZ);
        mapSquare.clearFrameLocs();
        drawViewport2d();
        drawTileHint();
        updateAnimatedTextures(i3);
        drawViewport3d();
        areaViewport.drawImage(11, super.graphics, 8);
        cameraX = i1;
        cameraY = k1;
        cameraZ = l1;
        cameraPitch = i2;
        cameraOrbitYaw = j2;
    }

    public void runMidi() {
        startMidiThread = false;

        while (midiThreadActive) {
            try {
                Thread.sleep(50L);
            } catch (Exception ignored) {
            }

            String name;
            int crc;
            int totalSize;
            synchronized (midiSync) {
                name = midiSyncName;
                crc = midiSyncCrc;
                totalSize = midiSyncLen;
                midiSyncName = null;
                midiSyncCrc = 0;
                midiSyncLen = 0;
            }

            if (name != null) {
                byte[] src = Signlink.cacheload(name + ".mid", false);

                if (src != null && crc != 12345678) {
                    crc32.reset();
                    crc32.update(src);
                    int other = (int) crc32.getValue();
                    if (other != crc) {
                        src = null;
                    }
                }

                if (src == null) {
                    try {
                        DataInputStream dis = openStream("songs/" + name + "_" + crc + ".mid");
                        src = new byte[totalSize];
                        int bytesRead;
                        for (int size = 0; size < totalSize; size += bytesRead) {
                            bytesRead = dis.read(src, size, totalSize - size);
                            if (bytesRead != -1) {
                                continue;
                            }
                            byte[] dest = new byte[size];
                            System.arraycopy(src, 0, dest, 0, size);
                            src = dest;
                            totalSize = size;
                            break;
                        }

                        dis.close();
                        Signlink.cachesave(name + ".mid", src, false);
                    } catch (Exception ignored) {
                    }
                }

                if (src == null) {
                    continue;
                }

                int length = (new Buffer(src)).g4();
                byte[] decompressed = new byte[length];
                BZip2InputStream.read(decompressed, length, src, totalSize, 4);
                midisave(decompressed, length, true);
            }
        }
    }

    public void drawFlames() {
        char c = '\u0100';
        if (flameCycle1 > 0) {
            for (int i = 0; i < 256; i++)
                if (flameCycle1 > 768)
                    flameGradient[i] = mix(flameGradientRed[i], 1024 - flameCycle1, flameGradientGreen[i]);
                else if (flameCycle1 > 256)
                    flameGradient[i] = flameGradientGreen[i];
                else
                    flameGradient[i] = mix(flameGradientGreen[i], 256 - flameCycle1, flameGradientRed[i]);
        } else if (flameCycle2 > 0) {
            for (int j = 0; j < 256; j++)
                if (flameCycle2 > 768)
                    flameGradient[j] = mix(flameGradientRed[j], 1024 - flameCycle2, flameGradientViolet[j]);
                else if (flameCycle2 > 256)
                    flameGradient[j] = flameGradientViolet[j];
                else
                    flameGradient[j] = mix(flameGradientViolet[j], 256 - flameCycle2, flameGradientRed[j]);
        } else {
            System.arraycopy(flameGradientRed, 0, flameGradient, 0, 256);
        }

        System.arraycopy(imageFlamesLeft.pixels, 0, titleLeft.pixels, 0, 33920);

        int i1 = 0;
        int j1 = 1152;
        for (int k1 = 1; k1 < c - 1; k1++) {
            int l1 = (flameShiftX[k1] * (c - k1)) / c;
            int j2 = 22 + l1;
            if (j2 < 0)
                j2 = 0;
            i1 += j2;
            for (int l2 = j2; l2 < 128; l2++) {
                int j3 = flameIntensity[i1++];
                if (j3 != 0) {
                    int l3 = j3;
                    int j4 = 256 - j3;
                    j3 = flameGradient[j3];
                    int l4 = titleLeft.pixels[j1];
                    titleLeft.pixels[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00)
                        + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            j1 += j2;
        }

        titleLeft.drawImage(0, super.graphics, 0);
        System.arraycopy(imageFlamesRight.pixels, 0, titleRight.pixels, 0, 33920);

        i1 = 0;
        j1 = 1176;
        for (int k2 = 1; k2 < c - 1; k2++) {
            int i3 = (flameShiftX[k2] * (c - k2)) / c;
            int k3 = 103 - i3;
            j1 += i3;
            for (int i4 = 0; i4 < k3; i4++) {
                int k4 = flameIntensity[i1++];
                if (k4 != 0) {
                    int i5 = k4;
                    int j5 = 256 - k4;
                    k4 = flameGradient[k4];
                    int k5 = titleRight.pixels[j1];
                    titleRight.pixels[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00)
                        + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
                } else {
                    j1++;
                }
            }

            i1 += 128 - k3;
            j1 += 128 - k3 - i3;
        }

        titleRight.drawImage(0, super.graphics, 661);
    }

    public void updateInterface(int i, int j, int k, Component component, int i1, int j1) {
        if (component.type != 0 || component.children == null || component.hidden)
            return;
        if (j < i1 || i < k || j >= i1 + component.width || i >= k + component.height)
            return;
        int k1 = component.children.length;
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = component.childX[l1] + i1;
            int j2 = (component.childY[l1] + k) - j1;
            Component inter = Component.instances[component.children[l1]];
            i2 += inter.x;
            j2 += inter.y;
            if ((inter.hoverParentIndex >= 0 || inter.hoverColor != 0) && j >= i2 && i >= j2
                && j < i2 + inter.width && i < j2 + inter.height)
                if (inter.hoverParentIndex >= 0)
                    hoveredInterfaceIndex = inter.hoverParentIndex;
                else
                    hoveredInterfaceIndex = inter.id;
            if (inter.type == 0) {
                updateInterface(i, j, j2, inter, i2, inter.scrollY);
                if (inter.scrollHeight > inter.height)
                    updateInterfaceScrollbar(j, i, inter.scrollHeight, inter.height, true, i2 + inter.width, j2,
                        inter);
            } else {
                if (inter.buttonType == 1 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    boolean flag = false;
                    if (inter.contentType != 0)
                        flag = updateInterfaceTooltip(inter);
                    if (!flag) {
                        options[optionCount] = inter.option;
                        actions[optionCount] = 951;
                        paramB[optionCount] = inter.id;
                        optionCount++;
                    }
                }
                if (inter.buttonType == 2 && selectedSpell == 0 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    String s = inter.optionCircumfix;
                    if (s.indexOf(" ") != -1)
                        s = s.substring(0, s.indexOf(" "));
                    options[optionCount] = s + " @gre@" + inter.optionSuffix;
                    actions[optionCount] = 930;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 3 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = "Close";
                    actions[optionCount] = 947;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 4 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 465;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 5 && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 960;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.buttonType == 6 && !chatContinuingDialogue && j >= i2 && i >= j2 && j < i2 + inter.width
                    && i < j2 + inter.height) {
                    options[optionCount] = inter.option;
                    actions[optionCount] = 44;
                    paramB[optionCount] = inter.id;
                    optionCount++;
                }
                if (inter.type == 2) {
                    int slot = 0;
                    for (int l2 = 0; l2 < inter.height; l2++) {
                        for (int i3 = 0; i3 < inter.width; i3++) {
                            int j3 = i2 + i3 * (32 + inter.inventoryMarginX);
                            int k3 = j2 + l2 * (32 + inter.inventoryMarginY);
                            if (slot < 20) {
                                j3 += inter.inventoryOffsetX[slot];
                                k3 += inter.inventoryOffsetY[slot];
                            }
                            if (j >= j3 && i >= k3 && j < j3 + 32 && i < k3 + 32) {
                                hoveredSlot = slot;
                                hoveredSlotParentId = inter.id;
                                if (inter.inventoryIndices[slot] > 0) {
                                    ObjType objType = ObjType.get(inter.inventoryIndices[slot] - 1);
                                    if (selectedObject == 1 && inter.inventoryHasOptions) {
                                        if (inter.id != selectedObjInterface || slot != selectedObjSlot) {
                                            options[optionCount] = "Use " + selectedObjName + " with @lre@"
                                                + objType.name;
                                            actions[optionCount] = 881;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = slot;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                    } else if (selectedSpell == 1 && inter.inventoryHasOptions) {
                                        if ((selectedFlags & 0x10) == 16) {
                                            options[optionCount] = selectedSpellPrefix + " @lre@" + objType.name;
                                            actions[optionCount] = 391;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = slot;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                    } else {
                                        if (inter.inventoryHasOptions) {
                                            for (int l3 = 4; l3 >= 3; l3--)
                                                if (objType.options != null
                                                    && objType.options[l3] != null) {
                                                    options[optionCount] = objType.options[l3] + " @lre@"
                                                        + objType.name;
                                                    if (l3 == 3)
                                                        actions[optionCount] = 478;
                                                    if (l3 == 4)
                                                        actions[optionCount] = 347;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = slot;
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                } else if (l3 == 4) {
                                                    options[optionCount] = "Drop @lre@" + objType.name;
                                                    actions[optionCount] = 347;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = slot;
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        if (inter.inventoryIsUsable) {
                                            options[optionCount] = "Use @lre@" + objType.name;
                                            actions[optionCount] = 188;
                                            paramC[optionCount] = objType.index;
                                            paramA[optionCount] = slot;
                                            paramB[optionCount] = inter.id;
                                            optionCount++;
                                        }
                                        if (inter.inventoryHasOptions && objType.options != null) {
                                            for (int i4 = 2; i4 >= 0; i4--)
                                                if (objType.options[i4] != null) {
                                                    options[optionCount] = objType.options[i4] + " @lre@"
                                                        + objType.name;
                                                    if (i4 == 0)
                                                        actions[optionCount] = 405;
                                                    if (i4 == 1)
                                                        actions[optionCount] = 38;
                                                    if (i4 == 2)
                                                        actions[optionCount] = 422;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = slot;
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        if (inter.inventoryOptions != null) {
                                            for (int j4 = 4; j4 >= 0; j4--)
                                                if (inter.inventoryOptions[j4] != null) {
                                                    options[optionCount] = inter.inventoryOptions[j4]
                                                        + " @lre@" + objType.name;
                                                    if (j4 == 0)
                                                        actions[optionCount] = 602;
                                                    if (j4 == 1)
                                                        actions[optionCount] = 596;
                                                    if (j4 == 2)
                                                        actions[optionCount] = 22;
                                                    if (j4 == 3)
                                                        actions[optionCount] = 892;
                                                    if (j4 == 4)
                                                        actions[optionCount] = 415;
                                                    paramC[optionCount] = objType.index;
                                                    paramA[optionCount] = slot;
                                                    paramB[optionCount] = inter.id;
                                                    optionCount++;
                                                }

                                        }
                                        options[optionCount] = "Examine @lre@" + objType.name;
                                        actions[optionCount] = 1773;
                                        paramC[optionCount] = objType.index;
                                        paramB[optionCount] = inter.inventoryAmount[slot];
                                        optionCount++;
                                    }
                                }
                            }
                            slot++;
                        }
                    }
                }
            }
        }
    }

    public void updateChatSettingInput() {
        if (super.mouseButton == 1) {
            if (super.clickX >= 8 && super.clickX <= 108 && super.clickY >= 490 && super.clickY <= 522) {
                chatPublicSetting = (chatPublicSetting + 1) % 4;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.p1isaac(Packet.Client.PRIVACY_OPTIONS);
                outBuffer.p1(chatPublicSetting);
                outBuffer.p1(chatPrivateSetting);
                outBuffer.p1(chatTradeDuelSetting);
            }
            if (super.clickX >= 137 && super.clickX <= 237 && super.clickY >= 490 && super.clickY <= 522) {
                chatPrivateSetting = (chatPrivateSetting + 1) % 3;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.p1isaac(Packet.Client.PRIVACY_OPTIONS);
                outBuffer.p1(chatPublicSetting);
                outBuffer.p1(chatPrivateSetting);
                outBuffer.p1(chatTradeDuelSetting);
            }
            if (super.clickX >= 275 && super.clickX <= 375 && super.clickY >= 490 && super.clickY <= 522) {
                chatTradeDuelSetting = (chatTradeDuelSetting + 1) % 3;
                chatRedrawSettings = true;
                redrawChatback = true;
                outBuffer.p1isaac(Packet.Client.PRIVACY_OPTIONS);
                outBuffer.p1(chatPublicSetting);
                outBuffer.p1(chatPrivateSetting);
                outBuffer.p1(chatTradeDuelSetting);
            }
            if (super.clickX >= 416 && super.clickX <= 516 && super.clickY >= 490 && super.clickY <= 522) {
                closeInterface();
                reportInput = "";
                reportAbuseMuteToggle = false;
                for (int j = 0; j < Component.instances.length; j++) {
                    if (Component.instances[j] != null && Component.instances[j].contentType == 600) {
                        openInterfaceId = viewportInterfaceIndex = Component.instances[j].parent;
                        return;
                    }
                }
            }
        }
    }

    public void updatePlayerTooltip(int i) {
        int l = 0;
        for (int i1 = 0; i1 < 100; i1++) {
            if (chatMessage[i1] == null)
                continue;
            int j1 = chatMessageType[i1];
            int k1 = (70 - l * 14) + chatScrollAmount + 4;
            if (k1 < -20)
                break;
            if (j1 == 0)
                l++;
            if ((j1 == 1 || j1 == 2)
                && (j1 == 1 || chatPublicSetting == 0 || chatPublicSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1 && !chatMessagePrefix[i1].equals(self.name)) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 3 || j1 == 7) && splitPrivateChat == 0
                && (j1 == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    if (rights) {
                        options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[i1];
                        actions[optionCount] = 34;
                        optionCount++;
                    }
                    options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 436;
                    optionCount++;
                    options[optionCount] = "Add friend @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 406;
                    optionCount++;
                }
                l++;
            }
            if (j1 == 4 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept trade @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 903;
                    optionCount++;
                }
                l++;
            }
            if ((j1 == 5 || j1 == 6) && splitPrivateChat == 0 && chatPrivateSetting < 2)
                l++;
            if (j1 == 8 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[i1]))) {
                if (i > k1 - 14 && i <= k1) {
                    options[optionCount] = "Accept duel @whi@" + chatMessagePrefix[i1];
                    actions[optionCount] = 363;
                    optionCount++;
                }
                l++;
            }
        }
    }

    public void updateScenePlayers() {
        if (self.x >> 7 == flagTileX && self.z >> 7 == flagTileY) {
            flagTileX = 0;
        }

        for (int j = -1; j < playerCount; j++) {
            PlayerEntity playerEntity;
            int k;
            if (j == -1) {
                playerEntity = self;
                k = LOCAL_PLAYER_INDEX << 14;
            } else {
                playerEntity = playerEntities[playerIndices[j]];
                k = playerIndices[j] << 14;
            }

            if (playerEntity == null || !playerEntity.isValid(false)) {
                continue;
            }

            playerEntity.lowMemory = (lowMemory && playerCount > 50 || playerCount > 200) && j != -1 && playerEntity.secondarySeq == playerEntity.standSeq;
            int x = playerEntity.x >> 7;
            int z = playerEntity.z >> 7;
            if (x < 0 || x >= 104 || z < 0 || z >= 104) {
                continue;
            }

            if (playerEntity.locModel != null && clientClock >= playerEntity.locFirstCycle && clientClock < playerEntity.locLastCycle) {
                playerEntity.lowMemory = false;
                playerEntity.y = getLandY(currentLevel, playerEntity.x, playerEntity.z);
                mapSquare.add(playerEntity.locMaxTileX, null,
                    playerEntity.z, playerEntity.y, k,
                    playerEntity.animationDelay, playerEntity.locMinTileZ,
                    playerEntity.locMinTileX, playerEntity, currentLevel,
                    playerEntity.locMaxTileZ, playerEntity.x);
                continue;
            }
            if ((playerEntity.x & 0x7f) == 64 && (playerEntity.z & 0x7f) == 64) {
                if (tileRenderCount[x][z] == sceneCycle) {
                    continue;
                }
                tileRenderCount[x][z] = sceneCycle;
            }
            playerEntity.y = getLandY(currentLevel, playerEntity.x, playerEntity.z);
            mapSquare.add(playerEntity.z, 60, playerEntity.animationDelay, playerEntity.x, k, playerEntity.animationStretches, null, playerEntity, playerEntity.y, currentLevel);
        }
    }

    public int getLandY(int plane, int sceneX, int sceneZ) {
        int stx = sceneX >> 7;
        int stz = sceneZ >> 7;
        if ((stx < 0) || (stz < 0) || (stx > 103) || (stz > 103)) {
            return 0;
        }
        int p = plane;
        if (p < 3 && (levelRenderFlags[1][stx][stz] & 2) == 2) {
            p++;
        }
        int ltx = sceneX & 0x7f;
        int ltz = sceneZ & 0x7f;
        int y00 = levelHeightMaps[p][stx][stz] * (128 - ltx) + levelHeightMaps[p][stx + 1][stz] * ltx >> 7;
        int y11 = levelHeightMaps[p][stx][stz + 1] * (128 - ltx) + levelHeightMaps[p][stx + 1][stz + 1] * ltx >> 7;
        return y00 * (128 - ltz) + y11 * ltz >> 7;
    }

    public void drawTooltip(NPCType npcType, int j, int k, int l) {
        if (optionCount >= 400)
            return;
        String s = npcType.name;
        if (npcType.level != 0)
            s = s + getLevelColorTag(self.combatLevel, npcType.level) + " (level-"
                + npcType.level + ")";
        if (selectedObject == 1) {
            options[optionCount] = "Use " + selectedObjName + " with @yel@" + s;
            actions[optionCount] = 900;
            paramC[optionCount] = l;
            paramA[optionCount] = k;
            paramB[optionCount] = j;
            optionCount++;
            return;
        }
        if (selectedSpell == 1) {
            if ((selectedFlags & 2) == 2) {
                options[optionCount] = selectedSpellPrefix + " @yel@" + s;
                actions[optionCount] = 265;
                paramC[optionCount] = l;
                paramA[optionCount] = k;
                paramB[optionCount] = j;
                optionCount++;
            }
        } else {
            if (npcType.options != null) {
                for (int i1 = 4; i1 >= 0; i1--)
                    if (npcType.options[i1] != null && !npcType.options[i1].equalsIgnoreCase("attack")) {
                        options[optionCount] = npcType.options[i1] + " @yel@" + s;
                        if (i1 == 0)
                            actions[optionCount] = 728;
                        if (i1 == 1)
                            actions[optionCount] = 542;
                        if (i1 == 2)
                            actions[optionCount] = 6;
                        if (i1 == 3)
                            actions[optionCount] = 963;
                        if (i1 == 4)
                            actions[optionCount] = 245;
                        paramC[optionCount] = l;
                        paramA[optionCount] = k;
                        paramB[optionCount] = j;
                        optionCount++;
                    }

            }
            if (npcType.options != null) {
                for (int j1 = 4; j1 >= 0; j1--)
                    if (npcType.options[j1] != null && npcType.options[j1].equalsIgnoreCase("attack")) {
                        char c = '\0';
                        if (npcType.level > self.combatLevel)
                            c = '\u07D0';
                        options[optionCount] = npcType.options[j1] + " @yel@" + s;
                        if (j1 == 0)
                            actions[optionCount] = 728 + c;
                        if (j1 == 1)
                            actions[optionCount] = 542 + c;
                        if (j1 == 2)
                            actions[optionCount] = 6 + c;
                        if (j1 == 3)
                            actions[optionCount] = 963 + c;
                        if (j1 == 4)
                            actions[optionCount] = 245 + c;
                        paramC[optionCount] = l;
                        paramA[optionCount] = k;
                        paramB[optionCount] = j;
                        optionCount++;
                    }

            }
            options[optionCount] = "Examine @yel@" + s;
            actions[optionCount] = 1607;
            paramC[optionCount] = l;
            paramA[optionCount] = k;
            paramB[optionCount] = j;
            optionCount++;
        }
    }

    public void updateKeyboardInput() {
        do {
            int key = pollKey();
            if (key == -1) {
                break;
            }

            if (viewportInterfaceIndex != -1 && viewportInterfaceIndex == openInterfaceId) {
                if (key == Applet.KEY_DELETE && reportInput.length() > 0) {
                    reportInput = reportInput.substring(0, reportInput.length() - 1);
                } else if ((key >= 'a' && key <= 'z' || key >= 'A' && key <= 'Z' || key >= '0' && key <= '9' || key == ' ') && reportInput.length() < 12) {
                    reportInput += (char) key;
                }
            } else if (showSocialInput) {
                if (key >= ' ' && key <= 'z' && socialInput.length() < 80) {
                    socialInput += (char) key;
                    redrawChatback = true;
                } else if (key == Applet.KEY_DELETE && socialInput.length() > 0) {
                    socialInput = socialInput.substring(0, socialInput.length() - 1);
                    redrawChatback = true;
                }

                if (key == Applet.KEY_RETURN || key == Applet.KEY_ENTER) {
                    showSocialInput = false;
                    redrawChatback = true;

                    if (socialAction == 1) {
                        long l = StringUtils.toBase37(socialInput);
                        addFriend(l);
                    } else if (socialAction == 2 && friendCount > 0) {
                        long l1 = StringUtils.toBase37(socialInput);
                        removeFriend(l1);
                    } else if (socialAction == 3 && socialInput.length() > 0) {
                        outBuffer.p1isaac(Packet.Client.MESSAGE_PRIVATE);
                        outBuffer.p1(0);
                        int k = outBuffer.offset;
                        outBuffer.p8(targetPlayerName);
                        TextEncoder.write(outBuffer, socialInput);
                        outBuffer.pSize(outBuffer.offset - k);
                        socialInput = StringUtils.toSentence(socialInput);
                        socialInput = WordPack.getFiltered(socialInput);
                        addMessage(6, socialInput, StringUtils.formatName(StringUtils.fromBase37(targetPlayerName)));
                        if (chatPrivateSetting == 2) {
                            chatPrivateSetting = 1;
                            chatRedrawSettings = true;
                            outBuffer.p1isaac(Packet.Client.PRIVACY_OPTIONS);
                            outBuffer.p1(chatPublicSetting);
                            outBuffer.p1(chatPrivateSetting);
                            outBuffer.p1(chatTradeDuelSetting);
                        }
                    } else if (socialAction == 4 && ignoreCount < 100) {
                        long l2 = StringUtils.toBase37(socialInput);
                        addIgnore(l2);
                    } else if (socialAction == 5 && ignoreCount > 0) {
                        long l3 = StringUtils.toBase37(socialInput);
                        removeIgnore(l3);
                    }
                }
            } else if (chatbackInputType) {
                if (key >= '0' && key <= '9' && chatbackInput.length() < 10) {
                    chatbackInput += (char) key;
                    redrawChatback = true;
                } else if (key == Applet.KEY_DELETE && chatbackInput.length() > 0) {
                    chatbackInput = chatbackInput.substring(0, chatbackInput.length() - 1);
                    redrawChatback = true;
                } else if (key == Applet.KEY_RETURN || key == Applet.KEY_ENTER) {
                    if (chatbackInput.length() > 0) {
                        int i1 = 0;
                        try {
                            i1 = Integer.parseInt(chatbackInput);
                        } catch (Exception ignored) {
                        }
                        outBuffer.p1isaac(Packet.Client.INTERFACE_ENTERED_AMOUNT);
                        outBuffer.p4(i1);
                    }

                    chatbackInputType = false;
                    redrawChatback = true;
                }
            } else if (chatbackComponentId == -1) {
                if (key >= ' ' && key <= 'z' && input.length() < 80) {
                    input += (char) key;
                    redrawChatback = true;
                } else if (key == Applet.KEY_DELETE && input.length() > 0) {
                    input = input.substring(0, input.length() - 1);
                    redrawChatback = true;
                } else if ((key == Applet.KEY_RETURN || key == Applet.KEY_ENTER) && input.length() > 0) {
                    if (input.equals("::clientdrop") && (super.frame != null || getHost().contains("192.168.1."))) {
                        reconnect();
                    } else if (input.startsWith("::")) {
                        outBuffer.p1isaac(Packet.Client.CLIENT_CHEAT);
                        outBuffer.p1(input.length() - 1);
                        outBuffer.pString(input.substring(2));
                    } else {
                        int color = COLOR_YELLOW;
                        if (input.startsWith("yellow:")) {
                            input = input.substring(7);
                        } else if (input.startsWith("red:")) {
                            color = COLOR_RED;
                            input = input.substring(4);
                        } else if (input.startsWith("green:")) {
                            color = COLOR_GREEN;
                            input = input.substring(6);
                        } else if (input.startsWith("cyan:")) {
                            color = COLOR_CYAN;
                            input = input.substring(5);
                        } else if (input.startsWith("purple:")) {
                            color = COLOR_PURPLE;
                            input = input.substring(7);
                        } else if (input.startsWith("white:")) {
                            color = COLOR_WHITE;
                            input = input.substring(6);
                        } else if (input.startsWith("flash1:")) {
                            color = COLOR_FLASH1;
                            input = input.substring(7);
                        } else if (input.startsWith("flash2:")) {
                            color = COLOR_FLASH2;
                            input = input.substring(7);
                        } else if (input.startsWith("flash3:")) {
                            color = COLOR_FLASH3;
                            input = input.substring(7);
                        } else if (input.startsWith("glow1:")) {
                            color = COLOR_GLOW1;
                            input = input.substring(6);
                        } else if (input.startsWith("glow2:")) {
                            color = COLOR_GLOW2;
                            input = input.substring(6);
                        } else if (input.startsWith("glow3:")) {
                            color = COLOR_GLOW3;
                            input = input.substring(6);
                        }

                        int effect = 0;
                        if (input.startsWith("wave:")) {
                            effect = EFFECT_WAVE;
                            input = input.substring(5);
                        } else if (input.startsWith("scroll:")) {
                            effect = EFFECT_SCROLL;
                            input = input.substring(7);
                        }

                        outBuffer.p1isaac(Packet.Client.MESSAGE_PUBLIC);
                        outBuffer.p1(0);
                        int i2 = outBuffer.offset;
                        outBuffer.p1(color);
                        outBuffer.p1(effect);
                        TextEncoder.write(outBuffer, input);
                        outBuffer.pSize(outBuffer.offset - i2);
                        input = StringUtils.toSentence(input);
                        input = WordPack.getFiltered(input);
                        self.spoken = input;
                        self.spokenColor = color;
                        self.spokenEffect = effect;
                        self.textCycle = 150;
                        addMessage(2, self.spoken, self.name);
                        if (chatPublicSetting == 2) {
                            chatPublicSetting = 3;
                            chatRedrawSettings = true;
                            outBuffer.p1isaac(Packet.Client.PRIVACY_OPTIONS);
                            outBuffer.p1(chatPublicSetting);
                            outBuffer.p1(chatPrivateSetting);
                            outBuffer.p1(chatTradeDuelSetting);
                        }
                    }

                    input = "";
                    redrawChatback = true;
                }
            }
        } while (true);
    }

    public void draw() {
        if (errorStarted || errorLoading || errorHost) {
            drawErrorScreen();
            return;
        }

        if (!ingame) {
            drawTitleScreen();
        } else {
            drawGame();
        }

        dragCycle = 0;
    }

    public void updateTitle() {
        if (titleState == 0) {
            int i = super.gameWidth / 2 - 80;
            int l = super.gameHeight / 2 + 20;
            l += 20;
            if (super.mouseButton == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20
                && super.clickY <= l + 20) {
                titleState = 3;
                loginFocusedLine = 0;
            }
            i = super.gameWidth / 2 + 80;
            if (super.mouseButton == 1 && super.clickX >= i - 75 && super.clickX <= i + 75 && super.clickY >= l - 20
                && super.clickY <= l + 20) {
                loginMessage0 = "";
                loginMessage1 = "Enter your username & password.";
                titleState = 2;
                loginFocusedLine = 0;
            }
        } else {
            if (titleState == 2) {
                int j = super.gameHeight / 2 - 40;
                j += 30;
                j += 25;
                if (super.mouseButton == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginFocusedLine = 0;
                j += 15;
                if (super.mouseButton == 1 && super.clickY >= j - 15 && super.clickY < j)
                    loginFocusedLine = 1;
                j += 15;
                int i1 = super.gameWidth / 2 - 80;
                int k1 = super.gameHeight / 2 + 50;
                k1 += 20;
                if (super.mouseButton == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75
                    && super.clickY >= k1 - 20 && super.clickY <= k1 + 20)
                    login(username, password, false);
                i1 = super.gameWidth / 2 + 80;
                if (super.mouseButton == 1 && super.clickX >= i1 - 75 && super.clickX <= i1 + 75
                    && super.clickY >= k1 - 20 && super.clickY <= k1 + 20) {
                    titleState = 0;
                    password = "";
                }

                do {
                    int key = pollKey();
                    if (key == -1)
                        break;
                    boolean flag = false;
                    for (int i2 = 0; i2 < ASCII_CHARSET.length(); i2++) {
                        if (key != ASCII_CHARSET.charAt(i2))
                            continue;
                        flag = true;
                        break;
                    }

                    if (loginFocusedLine == 0) {
                        if (key == Applet.KEY_DELETE && username.length() > 0)
                            username = username.substring(0, username.length() - 1);
                        if (key == Applet.KEY_TAB || key == Applet.KEY_ENTER || key == Applet.KEY_RETURN)
                            loginFocusedLine = 1;
                        if (flag)
                            username += (char) key;
                        if (username.length() > 12)
                            username = username.substring(0, 12);
                    } else if (loginFocusedLine == 1) {
                        if (key == Applet.KEY_DELETE && password.length() > 0)
                            password = password.substring(0, password.length() - 1);
                        if (key == Applet.KEY_TAB || key == Applet.KEY_ENTER || key == Applet.KEY_RETURN)
                            loginFocusedLine = 0;
                        if (flag)
                            password += (char) key;
                        if (password.length() > 20)
                            password = password.substring(0, 20);
                    }
                } while (true);
                return;
            }
            if (titleState == 3) {
                int k = super.gameWidth / 2;
                int j1 = super.gameHeight / 2 + 50;
                j1 += 20;
                if (super.mouseButton == 1 && super.clickX >= k - 75 && super.clickX <= k + 75 && super.clickY >= j1 - 20
                    && super.clickY <= j1 + 20)
                    titleState = 0;
            }
        }
    }

    public FileArchive loadArchive(String displayName, int expectedCrc, String fileName, int progress) {
        int nextSecs = 5;
        byte[] data = Signlink.cacheload(fileName, false);
        if (data != null && Signlink.clientversion == 225) {
            crc32.reset();
            crc32.update(data);
            int realCrc = (int) crc32.getValue();
            if (realCrc != expectedCrc) {
                data = null;
            }
        }

        if (data != null) {
            return new FileArchive(data);
        }

        while (data == null) {
            showProgress("Requesting " + displayName, progress);
            try {
                int lastPercent = 0;
                DataInputStream dis = openStream(fileName + expectedCrc);
                byte[] lenBuffer = new byte[6];
                dis.readFully(lenBuffer, 0, 6);
                Buffer b = new Buffer(lenBuffer);
                b.offset = 3;
                int i2 = b.g3() + 6;
                int j2 = 6;
                data = new byte[i2];
                System.arraycopy(lenBuffer, 0, data, 0, 6);

                while (j2 < i2) {
                    int remaining = i2 - j2;
                    if (remaining > 1000) {
                        remaining = 1000;
                    }
                    j2 += dis.read(data, j2, remaining);
                    int currentPercent = (j2 * 100) / i2;
                    if (currentPercent != lastPercent) {
                        showProgress("Loading " + displayName + " - " + currentPercent + "%", progress);
                    }
                    lastPercent = currentPercent;
                }
                dis.close();
            } catch (IOException _ex) {
                data = null;
                for (int secs = nextSecs; secs > 0; secs--) {
                    showProgress("Error loading - Will retry in " + secs + " secs.", progress);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception ignored) {
                    }
                }

                nextSecs *= 2;
                if (nextSecs > 60) {
                    nextSecs = 60;
                }
            }
        }

        Signlink.cachesave(fileName, data, false);
        return new FileArchive(data);
    }

    public void disposeTitleComponents() {
        flameActive = false;
        while (flamesThreadActive) {
            flameActive = false;
            try {
                Thread.sleep(50L);
            } catch (Exception ignored) {
            }
        }
        imageTitlebox = null;
        imageTitlebutton = null;
        imageRunes = null;
        flameGradient = null;
        flameGradientRed = null;
        flameGradientGreen = null;
        flameGradientViolet = null;
        flameBuffer1 = null;
        flameBuffer2 = null;
        flameIntensity = null;
        flameIntensityBuffer = null;
        imageFlamesLeft = null;
        imageFlamesRight = null;
    }

    public void updateCameraOrbit(int i, int j, int k, int l, int j1, int k1) {
        int l1 = 2048 - l & 0x7ff;
        int i2 = 2048 - k & 0x7ff;
        int j2 = 0;
        int k2 = 0;
        int l2 = k1;
        if (l1 != 0) {
            int i3 = Model.sin[l1];
            int k3 = Model.cos[l1];
            int i4 = k2 * k3 - l2 * i3 >> 16;
            l2 = k2 * i3 + l2 * k3 >> 16;
            k2 = i4;
        }
        if (i2 != 0) {
            int j3 = Model.sin[i2];
            int l3 = Model.cos[i2];
            int j4 = l2 * j3 + j2 * l3 >> 16;
            l2 = l2 * l3 - j2 * j3 >> 16;
            j2 = j4;
        }
        cameraX = j - j2;
        cameraY = i - k2;
        cameraZ = j1 - l2;
        cameraPitch = l;
        cameraOrbitYaw = k;
    }

    public void updateAnimatedTextures(int cycle) {
        if (lowMemory) {
            return;
        }

        if (Draw3D.textureCycles[17] >= cycle) {
            IndexedSprite i = Draw3D.textures[17];
            int j = i.width * i.height - 1;
            int l = i.width * sceneDelta * 2;
            byte[] pixels = i.pixels;
            byte[] texels = tmpTexels;
            for (int j1 = 0; j1 <= j; j1++)
                texels[j1] = pixels[j1 - l & j];

            i.pixels = texels;
            tmpTexels = pixels;
            Draw3D.updateTexture(17);
        }

        if (Draw3D.textureCycles[24] >= cycle) {
            IndexedSprite i = Draw3D.textures[24];
            int k = i.width * i.height - 1;
            int i1 = i.width * sceneDelta * 2;
            byte[] pixels = i.pixels;
            byte[] texels = tmpTexels;
            for (int k1 = 0; k1 <= k; k1++)
                texels[k1] = pixels[k1 - i1 & k];

            i.pixels = texels;
            tmpTexels = pixels;
            Draw3D.updateTexture(24);
        }
    }

    public void updateFlames() {
        char c = '\u0100';
        for (int i = 10; i < 117; i++) {
            int j = (int) (Math.random() * 100D);
            if (j < 50)
                flameIntensity[i + (c - 2 << 7)] = 255;
        }

        for (int k = 0; k < 100; k++) {
            int l = (int) (Math.random() * 124D) + 2;
            int j1 = (int) (Math.random() * 128D) + 128;
            int j2 = l + (j1 << 7);
            flameIntensity[j2] = 192;
        }

        for (int i1 = 1; i1 < c - 1; i1++) {
            for (int k1 = 1; k1 < 127; k1++) {
                int k2 = k1 + (i1 << 7);
                flameIntensityBuffer[k2] = (flameIntensity[k2 - 1] + flameIntensity[k2 + 1] + flameIntensity[k2 - 128]
                    + flameIntensity[k2 + 128]) / 4;
            }
        }

        flameOffset += 128;

        if (flameOffset > flameBuffer1.length) {
            flameOffset -= flameBuffer1.length;
            int l1 = (int) (Math.random() * 12D);
            updateFlameDissolve(imageRunes[l1]);
        }

        for (int i2 = 1; i2 < c - 1; i2++) {
            for (int l2 = 1; l2 < 127; l2++) {
                int j3 = l2 + (i2 << 7);
                int l3 = flameIntensityBuffer[j3 + 128] - flameBuffer1[j3 + flameOffset & flameBuffer1.length - 1] / 5;
                if (l3 < 0)
                    l3 = 0;
                flameIntensity[j3] = l3;
            }
        }

        System.arraycopy(flameShiftX, 1, flameShiftX, 0, c - 1);

        flameShiftX[c - 1] = (int) (Math.sin((double) clientClock / 14D) * 16D + Math.sin((double) clientClock / 15D) * 14D
            + Math.sin((double) clientClock / 16D) * 12D);

        if (flameCycle1 > 0)
            flameCycle1 -= 4;

        if (flameCycle2 > 0)
            flameCycle2 -= 4;

        if (flameCycle1 == 0 && flameCycle2 == 0) {
            int k3 = (int) (Math.random() * 2000D);
            if (k3 == 0)
                flameCycle1 = 1024;
            if (k3 == 1)
                flameCycle2 = 1024;
        }
    }

    public void drawMinimap() {
        areaMapback.bind();

        int i = cameraYaw + minimapAnticheatAngle & 0x7ff;
        int j = 48 + self.x / 32;
        int l1 = 464 - self.z / 32;

        minimap.drawRotatedMasked(i, 146, minimapLeft, 151, l1, 256 + minimapZoom, j, 21, 9, minimapLineWidth);
        compass.drawRotatedMasked(cameraYaw, 33, compassLeft, 33, 25, 256, 25, 0, 0, compassLineWidth);

        for (int j3 = 0; j3 < activeMapFunctionCount; j3++) {
            int k = (activeMapFunctionX[j3] * 4 + 2) - self.x / 32;
            int i2 = (activeMapFunctionZ[j3] * 4 + 2) - self.z / 32;
            drawOnMinimap(i2, activeMapFunctions[j3], k);
        }

        for (int x = 0; x < 104; x++) {
            for (int z = 0; z < 104; z++) {
                LinkedList stack = objects[currentLevel][x][z];
                if (stack != null) {
                    int l = (x * 4 + 2) - self.x / 32;
                    int j2 = (z * 4 + 2) - self.z / 32;
                    drawOnMinimap(j2, mapdot1, l);
                }
            }
        }

        for (int i4 = 0; i4 < npcCount; i4++) {
            NPCEntity npcEntity = npcEntities[npcIndices[i4]];
            if (npcEntity != null && npcEntity.isValid(false)
                && npcEntity.info.showOnMinimap) {
                int i1 = npcEntity.x / 32
                    - self.x / 32;
                int k2 = npcEntity.z / 32
                    - self.z / 32;
                drawOnMinimap(k2, mapdot2, i1);
            }
        }

        for (int j4 = 0; j4 < playerCount; j4++) {
            PlayerEntity playerEntity = playerEntities[playerIndices[j4]];
            if (playerEntity != null && playerEntity.isValid(false)) {
                int j1 = playerEntity.x / 32
                    - self.x / 32;
                int l2 = playerEntity.z / 32
                    - self.z / 32;
                boolean flag = false;
                long l4 = StringUtils.toBase37(playerEntity.name);
                for (int k4 = 0; k4 < friendCount; k4++) {
                    if (l4 != friendName37[k4] || friendWorld[k4] == 0)
                        continue;
                    flag = true;
                    break;
                }

                if (flag)
                    drawOnMinimap(l2, mapdot4, j1);
                else
                    drawOnMinimap(l2, mapdot3, j1);
            }
        }

        if (flagTileX != 0) {
            int k1 = (flagTileX * 4 + 2) - self.x / 32;
            int i3 = (flagTileY * 4 + 2) - self.z / 32;
            drawOnMinimap(i3, mapflag, k1);
        }
        Draw2D.fillRect(82, 93, 0xffffff, 3, 3);
        areaViewport.bind();
    }

    public java.awt.Component getBaseComponent() {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp;
        }

        return this;
    }

    public void updateTemporaryLocs() {
        if (sceneState == 2) {
            for (TemporaryLoc loc = (TemporaryLoc) temporaryLocs
                .peekLast(); loc != null; loc = (TemporaryLoc) temporaryLocs.getPrevious())
                if (clientClock >= loc.lastCycle) {
                    addLoc(loc.rotation, loc.tileX, loc.tileZ,
                        loc.classType, loc.locIndex, loc.type,
                        loc.level);
                    loc.unlink();
                }

            updateLocCounter++;
            if (updateLocCounter > 85) {
                updateLocCounter = 0;
                outBuffer.p1isaac(Packet.Client.ANTICHEAT_UPDATE_LOC);
            }
        }
    }

    public void createMinimap(int p) {
        int[] ai = minimap.pixels;
        int k = ai.length;
        for (int l = 0; l < k; l++)
            ai[l] = 0;

        for (int z = 1; z < 103; z++) {
            int j1 = 24628 + (103 - z) * 512 * 4;
            for (int x = 1; x < 103; x++) {
                if ((levelRenderFlags[p][x][z] & 0x18) == 0)
                    mapSquare.drawMinimapTile(ai, j1, 512, p, x, z);
                if (p < 3 && (levelRenderFlags[p + 1][x][z] & 8) != 0)
                    mapSquare.drawMinimapTile(ai, j1, 512, p + 1, x, z);
                j1 += 4;
            }
        }

        int k1 = ((238 + (int) (Math.random() * 20D)) - 10 << 16) + ((238 + (int) (Math.random() * 20D)) - 10 << 8)
            + ((238 + (int) (Math.random() * 20D)) - 10);
        int i2 = (238 + (int) (Math.random() * 20D)) - 10 << 16;
        minimap.prepare();
        for (int z = 1; z < 103; z++) {
            for (int x = 1; x < 103; x++) {
                if ((levelRenderFlags[p][x][z] & 0x18) == 0)
                    drawMinimapLoc(p, k1, x, i2, z);
                if (p < 3 && (levelRenderFlags[p + 1][x][z] & 8) != 0)
                    drawMinimapLoc(p + 1, k1, x, i2, z);
            }

        }

        areaViewport.bind();
        activeMapFunctionCount = 0;
        for (int x = 0; x < 104; x++) {
            for (int z = 0; z < 104; z++) {
                int j3 = mapSquare.getGroundDecorationBitset(currentLevel, x, z);
                if (j3 != 0) {
                    j3 = j3 >> 14 & 0x7fff;
                    int k3 = LocType.get(j3).mapfunction;
                    if (k3 >= 0) {
                        int tempX = x;
                        int tempZ = z;
                        if (k3 != 22 && k3 != 29 && k3 != 34 && k3 != 36 && k3 != 46 && k3 != 47 && k3 != 48) {
                            byte byte0 = 104;
                            byte byte1 = 104;
                            int[][] ai1 = collisionMaps[currentLevel].flags;
                            for (int j4 = 0; j4 < 10; j4++) {
                                int k4 = (int) (Math.random() * 4D);
                                if (k4 == 0 && tempX > 0 && tempX > x - 3 && (ai1[tempX - 1][tempZ] & 0x280108) == 0)
                                    tempX--;
                                if (k4 == 1 && tempX < byte0 - 1 && tempX < x + 3 && (ai1[tempX + 1][tempZ] & 0x280180) == 0)
                                    tempX++;
                                if (k4 == 2 && tempZ > 0 && tempZ > z - 3 && (ai1[tempX][tempZ - 1] & 0x280102) == 0)
                                    tempZ--;
                                if (k4 == 3 && tempZ < byte1 - 1 && tempZ < z + 3 && (ai1[tempX][tempZ + 1] & 0x280120) == 0)
                                    tempZ++;
                            }

                        }
                        activeMapFunctions[activeMapFunctionCount] = mapfunction[k3];
                        activeMapFunctionX[activeMapFunctionCount] = tempX;
                        activeMapFunctionZ[activeMapFunctionCount] = tempZ;
                        activeMapFunctionCount++;
                    }
                }
            }
        }
    }

    public void drawMinimapLoc(int j, int k, int l, int i1, int j1) {
        int k1 = mapSquare.getWallBitset(j, l, j1);
        if (k1 != 0) {
            int l1 = mapSquare.getInfo(j, l, j1, k1);
            int k2 = l1 >> 6 & 3;
            int i3 = l1 & 0x1f;
            int k3 = k;
            if (k1 > 0)
                k3 = i1;
            int[] ai = minimap.pixels;
            int k4 = 24624 + l * 4 + (103 - j1) * 512 * 4;
            int i5 = k1 >> 14 & 0x7fff;
            LocType locType_2 = LocType.get(i5);
            if (locType_2.mapscene != -1) {
                IndexedSprite s = mapscene[locType_2.mapscene];
                if (s != null) {
                    int i6 = (locType_2.sizeX * 4 - s.width) / 2;
                    int j6 = (locType_2.sizeZ * 4 - s.height) / 2;
                    s.draw(48 + (104 - j1 - locType_2.sizeZ) * 4 + j6, 48 + l * 4 + i6);
                }
            } else {
                if (i3 == 0 || i3 == 2)
                    if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 1) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 3) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
                if (i3 == 3)
                    if (k2 == 0)
                        ai[k4] = k3;
                    else if (k2 == 1)
                        ai[k4 + 3] = k3;
                    else if (k2 == 2)
                        ai[k4 + 3 + 1536] = k3;
                    else if (k2 == 3)
                        ai[k4 + 1536] = k3;
                if (i3 == 2)
                    if (k2 == 3) {
                        ai[k4] = k3;
                        ai[k4 + 512] = k3;
                        ai[k4 + 1024] = k3;
                        ai[k4 + 1536] = k3;
                    } else if (k2 == 0) {
                        ai[k4] = k3;
                        ai[k4 + 1] = k3;
                        ai[k4 + 2] = k3;
                        ai[k4 + 3] = k3;
                    } else if (k2 == 1) {
                        ai[k4 + 3] = k3;
                        ai[k4 + 3 + 512] = k3;
                        ai[k4 + 3 + 1024] = k3;
                        ai[k4 + 3 + 1536] = k3;
                    } else if (k2 == 2) {
                        ai[k4 + 1536] = k3;
                        ai[k4 + 1536 + 1] = k3;
                        ai[k4 + 1536 + 2] = k3;
                        ai[k4 + 1536 + 3] = k3;
                    }
            }
        }
        k1 = mapSquare.getLocationBitset(j, l, j1);
        if (k1 != 0) {
            int i2 = mapSquare.getInfo(j, l, j1, k1);
            int l2 = i2 >> 6 & 3;
            int j3 = i2 & 0x1f;
            int l3 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(l3);
            if (locType.mapscene != -1) {
                IndexedSprite indexedSprite_1 = mapscene[locType.mapscene];
                if (indexedSprite_1 != null) {
                    int j5 = (locType.sizeX * 4 - indexedSprite_1.width) / 2;
                    int k5 = (locType.sizeZ * 4 - indexedSprite_1.height) / 2;
                    indexedSprite_1.draw(48 + (104 - j1 - locType.sizeZ) * 4 + k5, 48 + l * 4 + j5
                    );
                }
            } else if (j3 == 9) {
                int l4 = 0xeeeeee;
                if (k1 > 0)
                    l4 = 0xee0000;
                int[] ai1 = minimap.pixels;
                int l5 = 24624 + l * 4 + (103 - j1) * 512 * 4;
                if (l2 == 0 || l2 == 2) {
                    ai1[l5 + 1536] = l4;
                    ai1[l5 + 1024 + 1] = l4;
                    ai1[l5 + 512 + 2] = l4;
                    ai1[l5 + 3] = l4;
                } else {
                    ai1[l5] = l4;
                    ai1[l5 + 512 + 1] = l4;
                    ai1[l5 + 1024 + 2] = l4;
                    ai1[l5 + 1536 + 3] = l4;
                }
            }
        }
        k1 = mapSquare.getGroundDecorationBitset(j, l, j1);
        if (k1 != 0) {
            int j2 = k1 >> 14 & 0x7fff;
            LocType locType = LocType.get(j2);
            if (locType.mapscene != -1) {
                IndexedSprite indexedSprite = mapscene[locType.mapscene];
                if (indexedSprite != null) {
                    int i4 = (locType.sizeX * 4 - indexedSprite.width) / 2;
                    int j4 = (locType.sizeZ * 4 - indexedSprite.height) / 2;
                    indexedSprite.draw(48 + (104 - j1 - locType.sizeZ) * 4 + j4, 48 + l * 4 + i4);
                }
            }
        }
    }

    public void updateNpcs(Buffer buffer, int size) {
        deadEntityCount = 0;
        updateCount = 0;

        updateNpcList(buffer);
        updateLocalNpcs(buffer, size);
        updateNpcMasks(buffer);

        for (int n = 0; n < deadEntityCount; n++) {
            int id = deadEntityIndices[n];
            if (npcEntities[id].removeTimer != clientClock) {
                npcEntities[id].info = null;
                npcEntities[id] = null;
            }
        }

        if (buffer.offset != size) {
            Signlink.reporterror(username + " size mismatch in getnpcpos - pos:" + buffer.offset + " psize:" + size);
            throw new RuntimeException("eek");
        }

        for (int pos = 0; pos < npcCount; pos++) {
            if (npcEntities[npcIndices[pos]] == null) {
                Signlink.reporterror(username + " null entry in npc list - pos:" + pos + " size:" + npcCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public void startThread(Runnable task, int priority) {
        if (Signlink.mainapp != null) {
            Signlink.startthread(task, priority);
        } else {
            super.startThread(task, priority);
        }
    }

    public void loadTitleForeground() {
        imageTitlebox = new IndexedSprite(titleArchive, "titlebox", 0);
        imageTitlebutton = new IndexedSprite(titleArchive, "titlebutton", 0);

        imageRunes = new IndexedSprite[12];
        for (int j = 0; j < 12; j++) {
            imageRunes[j] = new IndexedSprite(titleArchive, "runes", j);
        }

        imageFlamesLeft = new Sprite(128, 265);
        imageFlamesRight = new Sprite(128, 265);
        System.arraycopy(titleLeft.pixels, 0, imageFlamesLeft.pixels, 0, 33920);
        System.arraycopy(titleRight.pixels, 0, imageFlamesRight.pixels, 0, 33920);

        flameGradientRed = new int[256];
        for (int i1 = 0; i1 < 64; i1++) {
            flameGradientRed[i1] = i1 * 0x40000;
        }
        for (int j1 = 0; j1 < 64; j1++) {
            flameGradientRed[j1 + 64] = 0xff0000 + 1024 * j1;
        }
        for (int k1 = 0; k1 < 64; k1++) {
            flameGradientRed[k1 + 128] = 0xffff00 + 4 * k1;
        }
        for (int l1 = 0; l1 < 64; l1++) {
            flameGradientRed[l1 + 192] = 0xffffff;
        }

        flameGradientGreen = new int[256];
        for (int i2 = 0; i2 < 64; i2++) {
            flameGradientGreen[i2] = i2 * 1024;
        }
        for (int j2 = 0; j2 < 64; j2++) {
            flameGradientGreen[j2 + 64] = 65280 + 4 * j2;
        }
        for (int k2 = 0; k2 < 64; k2++) {
            flameGradientGreen[k2 + 128] = 65535 + 0x40000 * k2;
        }
        for (int l2 = 0; l2 < 64; l2++) {
            flameGradientGreen[l2 + 192] = 0xffffff;
        }

        flameGradientViolet = new int[256];
        for (int i3 = 0; i3 < 64; i3++) {
            flameGradientViolet[i3] = i3 * 4;
        }
        for (int j3 = 0; j3 < 64; j3++) {
            flameGradientViolet[j3 + 64] = 255 + 0x40000 * j3;
        }
        for (int k3 = 0; k3 < 64; k3++) {
            flameGradientViolet[k3 + 128] = 0xff00ff + 1024 * k3;
        }
        for (int l3 = 0; l3 < 64; l3++) {
            flameGradientViolet[l3 + 192] = 0xffffff;
        }

        flameGradient = new int[256];
        flameBuffer1 = new int[32768];
        flameBuffer2 = new int[32768];
        updateFlameDissolve(null);
        flameIntensity = new int[32768];
        flameIntensityBuffer = new int[32768];
        showProgress("Connecting to fileserver", 10);

        if (!flameActive) {
            startFlamesThread = true;
            flameActive = true;
            startThread(this, 2);
        }
    }

    public void updateOtherPlayers(Buffer buffer) {
        int players = buffer.gBit(8);

        if (players < playerCount) {
            for (int n = players; n < playerCount; n++) {
                deadEntityIndices[deadEntityCount++] = playerIndices[n];
            }
        }

        if (players > playerCount) {
            Signlink.reporterror(username + " Too many players");
            throw new RuntimeException("eek");
        }

        playerCount = 0;
        for (int n = 0; n < players; n++) {
            int index = playerIndices[n];
            PlayerEntity p = playerEntities[index];

            int keepPlayer = buffer.gBit(1);
            if (keepPlayer == 0) {
                playerIndices[playerCount++] = index;
                p.removeTimer = clientClock;
            } else {
                int type = buffer.gBit(2);
                if (type == 0) {
                    playerIndices[playerCount++] = index;
                    p.removeTimer = clientClock;
                    entityUpdateIndices[updateCount++] = index;
                } else if (type == 1) {
                    playerIndices[playerCount++] = index;
                    p.removeTimer = clientClock;
                    int j2 = buffer.gBit(3);
                    p.walk(false, j2);
                    int l2 = buffer.gBit(1);
                    if (l2 == 1) {
                        entityUpdateIndices[updateCount++] = index;
                    }
                } else if (type == 2) {
                    playerIndices[playerCount++] = index;
                    p.removeTimer = clientClock;
                    int k2 = buffer.gBit(3);
                    p.walk(true, k2);
                    int i3 = buffer.gBit(3);
                    p.walk(true, i3);
                    int j3 = buffer.gBit(1);
                    if (j3 == 1) {
                        entityUpdateIndices[updateCount++] = index;
                    }
                } else if (type == 3) {
                    deadEntityIndices[deadEntityCount++] = index;
                }
            }
        }
    }

    public void drawScrollbar(int j, int k, int l, int i1, int j1) {
        scrollbar1.draw(k, j);
        scrollbar2.draw((k + j1) - 16, j);
        Draw2D.fillRect(k + 16, j, SCROLLBAR_TRACK, 16, j1 - 32);
        int k1 = ((j1 - 32) * j1) / i1;
        if (k1 < 8)
            k1 = 8;
        int l1 = ((j1 - 32 - k1) * l) / (i1 - j1);
        Draw2D.fillRect(k + 16 + l1, j, SCROLLBAR_GRIP_FOREGROUND, 16, k1);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, k1, j);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, k1, j + 1);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 16 + l1, 16, j);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_HIGHLIGHT, k + 17 + l1, 16, j);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 16 + l1, k1, j + 15);
        Draw2D.drawVerticalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 17 + l1, k1 - 1, j + 14);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 15 + l1 + k1, 16, j);
        Draw2D.drawHorizontalLine(SCROLLBAR_GRIP_LOWLIGHT, k + 14 + l1 + k1, 15, j + 1);
    }

    public void resetCharacterDesign() {
        characterDesignUpdate = true;
        for (int i = 0; i < 7; i++) {
            characterDesigns[i] = -1;
            for (int j = 0; j < IDKType.count; j++) {
                if (IDKType.instances[j].validStyle || IDKType.instances[j].type != i + (characterDesignIsMale ? 0 : 7))
                    continue;
                characterDesigns[i] = j;
                break;
            }
        }
    }

    public void midisave(byte[] src, int len, boolean fade) {
        Signlink.midifade = fade;
        Signlink.midisave(src, len);
    }

    public void updateSceneNpcs() {
        for (int i = 0; i < npcCount; i++) {
            NPCEntity npcEntity = npcEntities[npcIndices[i]];
            int j = 0x20000000 + (npcIndices[i] << 14);
            if (npcEntity == null || !npcEntity.isValid(false))
                continue;
            int x = npcEntity.x >> 7;
            int z = npcEntity.z >> 7;
            if (x < 0 || x >= 104 || z < 0 || z >= 104)
                continue;
            if (npcEntity.index == 1
                && (npcEntity.x & 0x7f) == 64
                && (npcEntity.z & 0x7f) == 64) {
                if (tileRenderCount[x][z] == sceneCycle)
                    continue;
                tileRenderCount[x][z] = sceneCycle;
            }
            mapSquare.add(npcEntity.z,
                (npcEntity.index - 1) * 64 + 60,
                npcEntity.animationDelay,
                npcEntity.x, j,
                npcEntity.animationStretches, null, npcEntity,
                getLandY(currentLevel, npcEntity.x,
                    npcEntity.z),
                currentLevel);
        }
    }

    public void midivol(int vol, boolean active) {
        Signlink.midivol = vol;
        if (active) {
            Signlink.midi = "voladjust";
        }
    }

    public void drawTitleScreen() {
        prepareTitleScreen();
        titleCenter.bind();
        imageTitlebox.draw(0, 0);
        char c = '\u0168';
        char c1 = '\310';
        if (titleState == 0) {
            int j = c1 / 2 - 20;
            fontBold12.drawCentered(c / 2, 0xffff00, true, j, "Welcome to RuneScape");
            j += 30;
            int i1 = c / 2 - 80;
            int l1 = c1 / 2 + 20;
            imageTitlebutton.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "New user");
            i1 = c / 2 + 80;
            imageTitlebutton.draw(l1 - 20, i1 - 73);
            fontBold12.drawCentered(i1, 0xffffff, true, l1 + 5, "Existing User");
        }
        if (titleState == 2) {
            int k = c1 / 2 - 40;
            if (loginMessage0.length() > 0) {
                fontBold12.drawCentered(c / 2, 0xffff00, true, k - 15, loginMessage0);
                fontBold12.drawCentered(c / 2, 0xffff00, true, k, loginMessage1);
                k += 30;
            } else {
                fontBold12.drawCentered(c / 2, 0xffff00, true, k - 7, loginMessage1);
                k += 30;
            }
            fontBold12.draw(c / 2 - 90, k,
                "Username: " + username + ((loginFocusedLine == 0) & (clientClock % 40 < 20) ? "@yel@|" : ""), true,
                0xffffff);
            k += 15;
            fontBold12.draw(c / 2 - 88, k, "Password: " + StringUtils.toAsterisks(password)
                + ((loginFocusedLine == 1) & (clientClock % 40 < 20) ? "@yel@|" : ""), true, 0xffffff);
            k += 15;
            int j1 = c / 2 - 80;
            int i2 = c1 / 2 + 50;
            imageTitlebutton.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Login");
            j1 = c / 2 + 80;
            imageTitlebutton.draw(i2 - 20, j1 - 73);
            fontBold12.drawCentered(j1, 0xffffff, true, i2 + 5, "Cancel");
        }
        if (titleState == 3) {
            fontBold12.drawCentered(c / 2, 0xffff00, true, c1 / 2 - 60, "Create a free account");
            int l = c1 / 2 - 35;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "To create a new account you need to");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "go back to the main RuneScape webpage");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "and choose the red 'create account'");
            l += 15;
            fontBold12.drawCentered(c / 2, 0xffffff, true, l, "button at the top right of that page.");
            l += 15;
            int k1 = c / 2;
            int j2 = c1 / 2 + 50;
            imageTitlebutton.draw(j2 - 20, k1 - 73);
            fontBold12.drawCentered(k1, 0xffffff, true, j2 + 5, "Cancel");
        }
        titleCenter.drawImage(186, super.graphics, 214);
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            titleTop.drawImage(0, super.graphics, 128);
            titleBottom.drawImage(386, super.graphics, 214);
            titleBottomLeft.drawImage(265, super.graphics, 0);
            titleBottomRight.drawImage(265, super.graphics, 574);
            titleLeftSpace.drawImage(186, super.graphics, 128);
            titleRightSpace.drawImage(186, super.graphics, 574);
        }
    }

    public void prepareGameScreen() {
        if (areaChatback != null)
            return;
        disposeTitleComponents();
        super.drawArea = null;
        titleTop = null;
        titleBottom = null;
        titleCenter = null;
        titleLeft = null;
        titleRight = null;
        titleBottomLeft = null;
        titleBottomRight = null;
        titleLeftSpace = null;
        titleRightSpace = null;
        areaChatback = new DrawArea(getBaseComponent(), 479, 96);
        areaMapback = new DrawArea(getBaseComponent(), 168, 160);
        Draw2D.clear();
        mapback.draw(0, 0);
        areaInvback = new DrawArea(getBaseComponent(), 190, 261);
        areaViewport = new DrawArea(getBaseComponent(), 512, 334);
        Draw2D.clear();
        areaBackbase1 = new DrawArea(getBaseComponent(), 501, 61);
        areaBackbase2 = new DrawArea(getBaseComponent(), 288, 40);
        areaBackhmid1 = new DrawArea(getBaseComponent(), 269, 66);
        redrawTitleBackground = true;
    }

    public void updateNewPlayers(int size, Buffer buffer) {
        do {
            if (buffer.bitOffset + 10 >= size * 8) {
                break;
            }

            int pid = buffer.gBit(11);
            if (pid == 2047) {
                break;
            }

            if (playerEntities[pid] == null) {
                playerEntities[pid] = new PlayerEntity();
                if (playerBuffers[pid] != null) {
                    playerEntities[pid].read(playerBuffers[pid]);
                }
            }

            playerIndices[playerCount++] = pid;
            PlayerEntity p = playerEntities[pid];
            p.removeTimer = clientClock;

            int x = buffer.gBit(5);
            if (x > 15) {
                x -= 32;
            }

            int y = buffer.gBit(5);
            if (y > 15) {
                y -= 32;
            }

            int teleport = buffer.gBit(1);
            p.move(teleport == 1, self.pathTileX[0] + x, self.pathTileZ[0] + y);

            int hasUpdate = buffer.gBit(1);
            if (hasUpdate == 1) {
                entityUpdateIndices[updateCount++] = pid;
            }
        } while (true);

        buffer.accessBytes();
    }

    public void disconnect() {
        try {
            if (stream != null)
                stream.close();
        } catch (Exception ignored) {
        }
        stream = null;
        ingame = false;
        titleState = 0;
        password = "";
        InputTracking.setDisabled();
        clearCaches();
        mapSquare.reset();
        for (int j = 0; j < 4; j++)
            collisionMaps[j].reset();

        System.gc();
        midistop();
        currentMidi = null;
        nextMusicDelay = 0;
        if (!lowMemory && midiActive) {
            setMidi(12345678, "scape_main", 40000);
        }
    }

    public void drawInterface(int y, int x, Component inter, int scrollY) {
        if (inter.type != 0 || inter.children == null) {
            return;
        }
        if (inter.hidden && viewportHoveredInterfaceIndex != inter.id && sidebarHoveredInterfaceIndex != inter.id && chatHoveredInterfaceIndex != inter.id) {
            return;
        }

        int left = Draw2D.left;
        int top = Draw2D.top;
        int right = Draw2D.right;
        int bottom = Draw2D.bottom;
        Draw2D.setBounds(y + inter.height, y, x + inter.width, x);

        int count = inter.children.length;
        for (int n = 0; n < count; n++) {
            int offsetX = inter.childX[n] + x;
            int offsetY = (inter.childY[n] + y) - scrollY;
            Component child = Component.instances[inter.children[n]];
            offsetX += child.x;
            offsetY += child.y;
            if (child.contentType > 0) {
                updateComponentContent(child);
            }

            if (child.type == 0) {
                if (child.scrollY > child.scrollHeight - child.height) {
                    child.scrollY = child.scrollHeight - child.height;
                }
                if (child.scrollY < 0) {
                    child.scrollY = 0;
                }
                drawInterface(offsetY, offsetX, child, child.scrollY);
                if (child.scrollHeight > child.height) {
                    drawScrollbar(offsetX + child.width, offsetY, child.scrollY, child.scrollHeight, child.height);
                }
            } else if (child.type == 2) {
                int invItem = 0;
                for (int childY = 0; childY < child.height; childY++) {
                    for (int childX = 0; childX < child.width; childX++) {
                        int j5 = offsetX + childX * (32 + child.inventoryMarginX);
                        int i6 = offsetY + childY * (32 + child.inventoryMarginY);
                        if (invItem < 20) {
                            j5 += child.inventoryOffsetX[invItem];
                            i6 += child.inventoryOffsetY[invItem];
                        }
                        if (child.inventoryIndices[invItem] > 0) {
                            int k6 = 0;
                            int k8 = 0;
                            int i9 = child.inventoryIndices[invItem] - 1;
                            if (j5 >= -32 && j5 <= 512 && i6 >= -32 && i6 <= 334 || objDragArea != 0 && objDragSlot == invItem) {
                                Sprite itemIcon = ObjType.getSprite(i9, child.inventoryAmount[invItem]);
                                if (objDragArea != 0 && objDragSlot == invItem && objDragComponentId == child.id) {
                                    k6 = super.mouseX - objGrabX;
                                    k8 = super.mouseY - objGrabY;
                                    if (k6 < 5 && k6 > -5) {
                                        k6 = 0;
                                    }
                                    if (k8 < 5 && k8 > -5) {
                                        k8 = 0;
                                    }
                                    if (objDragCycles < 5) {
                                        k6 = 0;
                                        k8 = 0;
                                    }
                                    itemIcon.draw(128, j5 + k6, i6 + k8);
                                } else if (selectedArea != 0 && selectedItem == invItem && selectedInterface == child.id) {
                                    itemIcon.draw(128, j5, i6);
                                } else {
                                    itemIcon.draw(i6, j5);
                                }
                                if (itemIcon.cropW == 33 || child.inventoryAmount[invItem] != 1) {
                                    int k9 = child.inventoryAmount[invItem];
                                    fontPlain11.draw(j5 + 1 + k6, i6 + 10 + k8, 0, formatObjAmount(k9));
                                    fontPlain11.draw(j5 + k6, i6 + 9 + k8, 0xffff00, formatObjAmount(k9));
                                }
                            }
                        } else if (child.inventorySprite != null && invItem < 20) {
                            Sprite sprite = child.inventorySprite[invItem];
                            if (sprite != null) {
                                sprite.draw(i6, j5);
                            }
                        }
                        invItem++;
                    }
                }
            } else if (child.type == 3) {
                if (child.fill) {
                    Draw2D.fillRect(offsetY, offsetX, child.color, child.width, child.height);
                } else {
                    Draw2D.drawRect(offsetX, child.color, child.height, offsetY, child.width);
                }
            } else if (child.type == 4) {
                Font font = child.font;
                int color = child.color;
                String text = child.text;

                if ((chatHoveredInterfaceIndex == child.id || sidebarHoveredInterfaceIndex == child.id || viewportHoveredInterfaceIndex == child.id) && child.hoverColor != 0) {
                    color = child.hoverColor;
                }

                if (isInterfaceEnabled(child)) {
                    color = child.colorEnabled;
                    if (child.activeText.length() > 0) {
                        text = child.activeText;
                    }
                }

                if (child.buttonType == 6 && chatContinuingDialogue) {
                    text = "Please wait...";
                    color = child.color;
                }

                for (int textY = offsetY + font.height; text.length() > 0; textY += font.height) {
                    if (text.contains("%")) {
                        do {
                            int var1 = text.indexOf("%1");
                            if (var1 == -1)
                                break;
                            text = text.substring(0, var1) + getIntString(executeInterface(child, 0))
                                + text.substring(var1 + 2);
                        } while (true);
                        do {
                            int var2 = text.indexOf("%2");
                            if (var2 == -1) {
                                break;
                            }
                            text = text.substring(0, var2) + getIntString(executeInterface(child, 1)) + text.substring(var2 + 2);
                        } while (true);
                        do {
                            int var3 = text.indexOf("%3");
                            if (var3 == -1) {
                                break;
                            }
                            text = text.substring(0, var3) + getIntString(executeInterface(child, 2)) + text.substring(var3 + 2);
                        } while (true);
                        do {
                            int var4 = text.indexOf("%4");
                            if (var4 == -1) {
                                break;
                            }
                            text = text.substring(0, var4) + getIntString(executeInterface(child, 3)) + text.substring(var4 + 2);
                        } while (true);
                        do {
                            int var5 = text.indexOf("%5");
                            if (var5 == -1) {
                                break;
                            }
                            text = text.substring(0, var5) + getIntString(executeInterface(child, 4)) + text.substring(var5 + 2);
                        } while (true);
                    }
                    int newline = text.indexOf("\\n");
                    String str;
                    if (newline != -1) {
                        str = text.substring(0, newline);
                        text = text.substring(newline + 2);
                    } else {
                        str = text;
                        text = "";
                    }
                    if (child.center) {
                        font.drawCentered(offsetX + child.width / 2, color, child.shadow, textY, str);
                    } else {
                        font.draw(offsetX, textY, str, child.shadow, color);
                    }
                }
            } else if (child.type == 5) {
                Sprite image;
                if (isInterfaceEnabled(child)) {
                    image = child.activeImage;
                } else {
                    image = child.image;
                }
                if (image != null) {
                    image.draw(offsetY, offsetX);
                }
            } else if (child.type == 6) {
                int oldX = Draw3D.centerX;
                int oldY = Draw3D.centerY;
                Draw3D.centerX = offsetX + child.width / 2;
                Draw3D.centerY = offsetY + child.height / 2;
                int sin = Draw3D.sin[child.modelEyePitch] * child.modelZoom >> 16;
                int cos = Draw3D.cos[child.modelEyePitch] * child.modelZoom >> 16;
                boolean enabled = isInterfaceEnabled(child);
                int seqId;
                if (enabled) {
                    seqId = child.activeSeqId;
                } else {
                    seqId = child.seqId;
                }
                Model model;
                if (seqId == -1) {
                    model = child.getModel(-1, -1, enabled);
                } else {
                    SeqType seqType = SeqType.instances[seqId];
                    model = child.getModel(seqType.primaryFrames[child.seqFrame], seqType.secondaryFrames[child.seqFrame], enabled);
                }
                if (model != null) {
                    model.draw(0, child.modelYaw, 0, child.modelEyePitch, 0, sin, cos);
                }
                Draw3D.centerX = oldX;
                Draw3D.centerY = oldY;
            } else if (child.type == 7) {
                Font font = child.font;
                int invItem = 0;
                for (int childY = 0; childY < child.height; childY++) {
                    for (int childX = 0; childX < child.width; childX++) {
                        if (child.inventoryIndices[invItem] > 0) {
                            ObjType objType = ObjType.get(child.inventoryIndices[invItem] - 1);
                            String name = objType.name;
                            if (objType.stackable || child.inventoryAmount[invItem] != 1) {
                                name = name + " x" + formatNumber(child.inventoryAmount[invItem]);
                            }
                            int invX = offsetX + childX * (115 + child.inventoryMarginX);
                            int invY = offsetY + childY * (12 + child.inventoryMarginY);
                            if (child.center) {
                                font.drawCentered(invX + child.width / 2, child.color, child.shadow, invY, name);
                            } else {
                                font.draw(invX, invY, name, child.shadow, child.color);
                            }
                        }
                        invItem++;
                    }
                }
            }
        }

        Draw2D.setBounds(bottom, top, right, left);
    }

    public void updatePlayerMasks(Buffer buffer) {
        for (int j = 0; j < updateCount; j++) {
            int k = entityUpdateIndices[j];
            PlayerEntity player = playerEntities[k];
            int type = buffer.g1();
            if ((type & 0x80) == 0x80) {
                type += buffer.g1() << 8;
            }
            updatePlayerMask(k, type, buffer, player);
        }
    }

    public void updateVarp(int i) {
        int varpType = VarType.instances[i].type;
        if (varpType == 0)
            return;
        int varp = variables[i];
        if (varpType == 1) {
            if (varp == 1)
                Draw3D.setBrightness(0.9D);
            if (varp == 2)
                Draw3D.setBrightness(0.8D);
            if (varp == 3)
                Draw3D.setBrightness(0.7D);
            if (varp == 4)
                Draw3D.setBrightness(0.6D);
            ObjType.iconCache.clear();
            redrawTitleBackground = true;
        }
        if (varpType == 3) {
            boolean lastActive = midiActive;
            if (varp == 0) {
                midivol(256, midiActive);
                midiActive = true;
            }
            if (varp == 1) {
                midivol(192, midiActive);
                midiActive = true;
            }
            if (varp == 2) {
                midivol(96, midiActive);
                midiActive = true;
            }
            if (varp == 3) {
                midivol(32, midiActive);
                midiActive = true;
            }
            if (varp == 4) {
                midiActive = false;
            }
            if (midiActive != lastActive) {
                if (midiActive) {
                    setMidi(midiCrc, currentMidi, midiSize);
                } else {
                    midistop();
                }
                nextMusicDelay = 0;
            }
        }
        if (varpType == 4) {
            if (varp == 0) {
                effectsEnabled = true;
                wavevol(0);
            }
            if (varp == 1) {
                effectsEnabled = true;
                wavevol(-400);
            }
            if (varp == 2) {
                effectsEnabled = true;
                wavevol(-800);
            }
            if (varp == 3) {
                effectsEnabled = true;
                wavevol(-1200);
            }
            if (varp == 4)
                effectsEnabled = false;
        }
        if (varpType == 5)
            button = varp;
        if (varpType == 6)
            chatEffects = varp;
        if (varpType == 8) {
            splitPrivateChat = varp;
            redrawChatback = true;
        }
    }

    public void updateNpcEntity(boolean flag) {
        ingame &= flag;
        for (int i = 0; i < npcCount; i++) {
            int j = npcIndices[i];
            NPCEntity npcEntity = npcEntities[j];
            if (npcEntity != null) {
                updateEntity(npcEntity);
            }
        }
    }

    public void updateEntity(PathingEntity entity) {
        if (entity.x < 128 || entity.z < 128
            || entity.x >= 13184 || entity.z >= 13184) {
            entity.primarySeq = -1;
            entity.spotAnimIndex = -1;
            entity.firstMoveCycle = 0;
            entity.lastMoveCycle = 0;
            entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
            entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
            entity.pathRemaining = 0;
        }
        if (entity == self
            && (entity.x < 1536 || entity.z < 1536
            || entity.x >= 11776 || entity.z >= 11776)) {
            entity.primarySeq = -1;
            entity.spotAnimIndex = -1;
            entity.firstMoveCycle = 0;
            entity.lastMoveCycle = 0;
            entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
            entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
            entity.pathRemaining = 0;
        }
        if (entity.firstMoveCycle > clientClock)
            moveEntity1(entity);
        else if (entity.lastMoveCycle >= clientClock)
            moveEntity2(entity);
        else
            moveEntity3(entity);
        updateEntity2(entity);
        updateEntity3(entity);
    }

    public void moveEntity1(PathingEntity entity) {
        int j = entity.firstMoveCycle - clientClock;
        int k = entity.srcTileX * 128 + entity.index * 64;
        int l = entity.srcTileY * 128 + entity.index * 64;
        entity.x += (k - entity.x) / j;
        entity.z += (l - entity.z) / j;
        entity.anInt1431 = 0;
        if (entity.faceDirection == 0)
            entity.dstYaw = 1024;
        if (entity.faceDirection == 1)
            entity.dstYaw = 1536;
        if (entity.faceDirection == 2)
            entity.dstYaw = 0;
        if (entity.faceDirection == 3)
            entity.dstYaw = 512;
    }

    public void moveEntity2(PathingEntity entity) {
        if (entity.lastMoveCycle == clientClock || entity.primarySeq == -1
            || entity.primarySeqDelay != 0 || entity.primarySeqCycle
            + 1 > SeqType.instances[entity.primarySeq].frameDelay[entity.primarySeqFrame]) {
            int j = entity.lastMoveCycle - entity.firstMoveCycle;
            int k = clientClock - entity.firstMoveCycle;
            int l = entity.srcTileX * 128 + entity.index * 64;
            int i1 = entity.srcTileY * 128 + entity.index * 64;
            int j1 = entity.dstTileX * 128 + entity.index * 64;
            int k1 = entity.dstTileY * 128 + entity.index * 64;
            entity.x = (l * (j - k) + j1 * k) / j;
            entity.z = (i1 * (j - k) + k1 * k) / j;
        }
        entity.anInt1431 = 0;
        if (entity.faceDirection == 0)
            entity.dstYaw = 1024;
        if (entity.faceDirection == 1)
            entity.dstYaw = 1536;
        if (entity.faceDirection == 2)
            entity.dstYaw = 0;
        if (entity.faceDirection == 3)
            entity.dstYaw = 512;
        entity.animationDelay = entity.dstYaw;
    }

    public void moveEntity3(PathingEntity entity) {
        entity.secondarySeq = entity.standSeq;
        if (entity.pathRemaining == 0) {
            entity.anInt1431 = 0;
            return;
        }
        if (entity.primarySeq != -1 && entity.primarySeqDelay == 0) {
            SeqType seqType = SeqType.instances[entity.primarySeq];
            if (seqType.labelGroups == null) {
                entity.anInt1431++;
                return;
            }
        }
        int j = entity.x;
        int k = entity.z;
        int l = entity.pathTileX[entity.pathRemaining - 1] * 128
            + entity.index * 64;
        int i1 = entity.pathTileZ[entity.pathRemaining - 1] * 128
            + entity.index * 64;
        if (l - j > 256 || l - j < -256 || i1 - k > 256 || i1 - k < -256) {
            entity.x = l;
            entity.z = i1;
            return;
        }
        if (j < l) {
            if (k < i1)
                entity.dstYaw = 1280;
            else if (k > i1)
                entity.dstYaw = 1792;
            else
                entity.dstYaw = 1536;
        } else if (j > l) {
            if (k < i1)
                entity.dstYaw = 768;
            else if (k > i1)
                entity.dstYaw = 256;
            else
                entity.dstYaw = 512;
        } else if (k < i1)
            entity.dstYaw = 1024;
        else
            entity.dstYaw = 0;
        int j1 = entity.dstYaw - entity.animationDelay & 0x7ff;
        if (j1 > 1024)
            j1 -= 2048;
        int k1 = entity.walkSeq;
        if (j1 >= -256 && j1 <= 256)
            k1 = entity.runSeq;
        else if (j1 >= 256 && j1 < 768)
            k1 = entity.turnRightSeq;
        else if (j1 >= -768 && j1 <= -256)
            k1 = entity.turnAroundSeq;
        if (k1 == -1)
            k1 = entity.runSeq;
        entity.secondarySeq = k1;
        int l1 = 4;
        if (entity.animationDelay != entity.dstYaw && entity.targetEntity == -1)
            l1 = 2;
        if (entity.pathRemaining > 2)
            l1 = 6;
        if (entity.pathRemaining > 3)
            l1 = 8;
        if (entity.anInt1431 > 0 && entity.pathRemaining > 1) {
            l1 = 8;
            entity.anInt1431--;
        }
        if (entity.pathRunning[entity.pathRemaining - 1])
            l1 <<= 1;
        if (l1 >= 8 && entity.secondarySeq == entity.runSeq && entity.turnLeftSeq != -1)
            entity.secondarySeq = entity.turnLeftSeq;
        if (j < l) {
            entity.x += l1;
            if (entity.x > l)
                entity.x = l;
        } else if (j > l) {
            entity.x -= l1;
            if (entity.x < l)
                entity.x = l;
        }
        if (k < i1) {
            entity.z += l1;
            if (entity.z > i1)
                entity.z = i1;
        } else if (k > i1) {
            entity.z -= l1;
            if (entity.z < i1)
                entity.z = i1;
        }
        if (entity.x == l && entity.z == i1)
            entity.pathRemaining--;
    }

    public void updateEntity2(PathingEntity entity) {
        if (entity.targetEntity != -1 && entity.targetEntity < 32768) {
            NPCEntity npcEntity = npcEntities[entity.targetEntity];
            if (npcEntity != null) {
                int l = entity.x - npcEntity.x;
                int j1 = entity.z - npcEntity.z;
                if (l != 0 || j1 != 0)
                    entity.dstYaw = (int) (Math.atan2(l, j1) * 325.949D) & 0x7ff;
            }
        }
        if (entity.targetEntity >= 32768) {
            int i = entity.targetEntity - 32768;
            if (i == selfPlayerId)
                i = LOCAL_PLAYER_INDEX;
            PlayerEntity playerEntity = playerEntities[i];
            if (playerEntity != null) {
                int k1 = entity.x - playerEntity.x;
                int l1 = entity.z - playerEntity.z;
                if (k1 != 0 || l1 != 0)
                    entity.dstYaw = (int) (Math.atan2(k1, l1) * 325.949D) & 0x7ff;
            }
        }
        if ((entity.focusX != 0 || entity.focusY != 0) && (entity.pathRemaining == 0 || entity.anInt1431 > 0)) {
            int targetX = entity.x - (entity.focusX - baseTileX - baseTileX) * 64;
            int targetZ = entity.z - (entity.focusY - baseTileZ - baseTileZ) * 64;
            if (targetX != 0 || targetZ != 0) {
                entity.dstYaw = (int) (Math.atan2(targetX, targetZ) * 325.949D) & 0x7ff;
            }
            entity.focusX = 0;
            entity.focusY = 0;
        }
        int k = entity.dstYaw - entity.animationDelay & 0x7ff;
        if (k != 0) {
            if (k < 32 || k > 2016)
                entity.animationDelay = entity.dstYaw;
            else if (k > 1024)
                entity.animationDelay -= 32;
            else
                entity.animationDelay += 32;
            entity.animationDelay &= 0x7ff;
            if (entity.secondarySeq == entity.standSeq
                && entity.animationDelay != entity.dstYaw) {
                if (entity.turnSeq != -1) {
                    entity.secondarySeq = entity.turnSeq;
                    return;
                }
                entity.secondarySeq = entity.runSeq;
            }
        }
    }

    public void updateEntity3(PathingEntity entity) {
        try {
            entity.animationStretches = false;
            if (entity.secondarySeq != -1) {
                SeqType seqType = SeqType.instances[entity.secondarySeq];
                entity.anInt1406++;
                if (entity.secondarySeqFrame < seqType.frameCount
                    && entity.anInt1406 > seqType.frameDelay[entity.secondarySeqFrame]) {
                    entity.anInt1406 = 0;
                    entity.secondarySeqFrame++;
                }
                if (entity.secondarySeqFrame >= seqType.frameCount) {
                    entity.anInt1406 = 0;
                    entity.secondarySeqFrame = 0;
                }
            }
            if (entity.primarySeq != -1 && entity.primarySeqDelay == 0) {
                SeqType seqType = SeqType.instances[entity.primarySeq];
                for (entity.primarySeqCycle++; entity.primarySeqFrame < seqType.frameCount
                    && entity.primarySeqCycle > seqType.frameDelay[entity.primarySeqFrame]; entity.primarySeqFrame++)
                    entity.primarySeqCycle -= seqType.frameDelay[entity.primarySeqFrame];

                if (entity.primarySeqFrame >= seqType.frameCount) {
                    entity.primarySeqFrame -= seqType.delay;
                    entity.primarySeqPlays++;
                    if (entity.primarySeqPlays >= seqType.replays)
                        entity.primarySeq = -1;
                    if (entity.primarySeqFrame < 0 || entity.primarySeqFrame >= seqType.frameCount)
                        entity.primarySeq = -1;
                }
                entity.animationStretches = seqType.renderPadding;
            }
            if (entity.primarySeqDelay > 0)
                entity.primarySeqDelay--;
            if (entity.spotAnimIndex != -1 && clientClock >= entity.lastSpotAnimCycle) {
                if (entity.spotAnimFrame < 0)
                    entity.spotAnimFrame = 0;
                SeqType seqType = SpotAnimType.instances[entity.spotAnimIndex].seq;
                for (entity.spotAnimCycle++; entity.spotAnimFrame < seqType.frameCount && entity.spotAnimCycle > seqType.frameDelay[entity.spotAnimFrame]; entity.spotAnimFrame++) {
                    entity.spotAnimCycle -= seqType.frameDelay[entity.spotAnimFrame];
                }

                if (entity.spotAnimFrame >= seqType.frameCount && (entity.spotAnimFrame < 0 || entity.spotAnimFrame >= seqType.frameCount))
                    entity.spotAnimIndex = -1;
            }
        } catch (Exception ignored) {
        }
    }

    public void drawGame() {
        if (redrawTitleBackground) {
            redrawTitleBackground = false;
            backleft1.drawImage(11, super.graphics, 0);
            backleft2.drawImage(375, super.graphics, 0);
            backright1.drawImage(5, super.graphics, 729);
            backright2.drawImage(231, super.graphics, 752);
            backtop1.drawImage(0, super.graphics, 0);
            backtop2.drawImage(0, super.graphics, 561);
            backvmid1.drawImage(11, super.graphics, 520);
            backvmid2.drawImage(231, super.graphics, 520);
            backvmid3.drawImage(375, super.graphics, 501);
            backhmid2.drawImage(345, super.graphics, 0);
            sidebarRedraw = true;
            redrawChatback = true;
            sidebarRedrawIcons = true;
            chatRedrawSettings = true;

            if (sceneState != 2) {
                areaViewport.drawImage(11, super.graphics, 8);
                areaMapback.drawImage(5, super.graphics, 561);
            }
        }

        if (sceneState == 2) {
            drawViewport();
        }

        if (menuVisible && mouseArea == 1) {
            sidebarRedraw = true;
        }

        if (sidebarInterfaceId != -1) {
            boolean flag = animateInterface(sidebarInterfaceId, sceneDelta);
            if (flag) {
                sidebarRedraw = true;
            }
        }

        if (selectedArea == 2) {
            sidebarRedraw = true;
        }

        if (objDragArea == 2) {
            sidebarRedraw = true;
        }

        if (sidebarRedraw) {
            drawChat2();
            sidebarRedraw = false;
        }

        if (chatbackComponentId == -1) {
            component.scrollY = chatScrollY - chatScrollAmount - 77;
            if (super.mouseX > 453 && super.mouseX < 565 && super.mouseY > 350)
                updateInterfaceScrollbar(super.mouseX - 22, super.mouseY - 375, chatScrollY, 77, false, 463, 0, component);
            int j = chatScrollY - 77 - component.scrollY;
            if (j < 0)
                j = 0;
            if (j > chatScrollY - 77)
                j = chatScrollY - 77;
            if (chatScrollAmount != j) {
                chatScrollAmount = j;
                redrawChatback = true;
            }
        }

        if (chatbackComponentId != -1) {
            boolean flag1 = animateInterface(chatbackComponentId, sceneDelta);
            if (flag1) {
                redrawChatback = true;
            }
        }

        if (selectedArea == 3) {
            redrawChatback = true;
        }

        if (objDragArea == 3) {
            redrawChatback = true;
        }

        if (chatbackMessage != null) {
            redrawChatback = true;
        }

        if (menuVisible && mouseArea == 2) {
            redrawChatback = true;
        }

        if (redrawChatback) {
            drawChatback();
            redrawChatback = false;
        }

        if (sceneState == 2) {
            drawMinimap();
            areaMapback.drawImage(5, super.graphics, 561);
        }

        if (flashingSidebarId != -1) {
            sidebarRedrawIcons = true;
        }

        if (sidebarRedrawIcons) {
            if (flashingSidebarId != -1 && flashingSidebarId == selectedTab) {
                flashingSidebarId = -1;
                outBuffer.p1isaac(Packet.Client.INTERFACE_FLASHING_TAB_CLICKED);
                outBuffer.p1(selectedTab);
            }
            sidebarRedrawIcons = false;

            areaBackhmid1.bind();
            backhmid1.draw(0, 0);
            if (sidebarInterfaceId == -1) {
                if (tabComponentId[selectedTab] != -1) {
                    if (selectedTab == 0) {
                        redstone1.draw(30, 29);
                    } else if (selectedTab == 1) {
                        redstone2.draw(29, 59);
                    } else if (selectedTab == 2) {
                        redstone2.draw(29, 87);
                    } else if (selectedTab == 3) {
                        redstone3.draw(29, 115);
                    } else if (selectedTab == 4) {
                        redstone2h.draw(29, 156);
                    } else if (selectedTab == 5) {
                        redstone2h.draw(29, 184);
                    } else if (selectedTab == 6) {
                        redstone1h.draw(30, 212);
                    }
                }
                try {
                    if (tabComponentId[0] != -1 && (flashingSidebarId != 0 || clientClock % 20 < 10)) {
                        sideicons[0].draw(34, 35);
                    }
                    if (tabComponentId[1] != -1 && (flashingSidebarId != 1 || clientClock % 20 < 10)) {
                        sideicons[1].draw(32, 59);
                    }
                    if (tabComponentId[2] != -1 && (flashingSidebarId != 2 || clientClock % 20 < 10)) {
                        sideicons[2].draw(32, 86);
                    }
                    if (tabComponentId[3] != -1 && (flashingSidebarId != 3 || clientClock % 20 < 10)) {
                        sideicons[3].draw(33, 121);
                    }
                    if (tabComponentId[4] != -1 && (flashingSidebarId != 4 || clientClock % 20 < 10)) {
                        sideicons[4].draw(34, 157);
                    }
                    if (tabComponentId[5] != -1 && (flashingSidebarId != 5 || clientClock % 20 < 10)) {
                        sideicons[5].draw(32, 185);
                    }
                    if (tabComponentId[6] != -1 && (flashingSidebarId != 6 || clientClock % 20 < 10)) {
                        sideicons[6].draw(34, 212);
                    }
                } catch (Exception ignored) {
                }
            }
            areaBackhmid1.drawImage(165, super.graphics, 520);

            areaBackbase2.bind();
            backbase2.draw(0, 0);
            if (sidebarInterfaceId == -1) {
                if (tabComponentId[selectedTab] != -1) {
                    if (selectedTab == 7) {
                        redstone1v.draw(0, 49);
                    } else if (selectedTab == 8) {
                        redstone2v.draw(0, 81);
                    } else if (selectedTab == 9) {
                        redstone2v.draw(0, 108);
                    } else if (selectedTab == 10) {
                        redstone3v.draw(1, 136);
                    } else if (selectedTab == 11) {
                        redstone2hv.draw(0, 178);
                    } else if (selectedTab == 12) {
                        redstone2hv.draw(0, 205);
                    } else if (selectedTab == 13) {
                        redstone1hv.draw(0, 233);
                    }
                }

                try {
                    if (tabComponentId[8] != -1 && (flashingSidebarId != 8 || clientClock % 20 < 10)) {
                        sideicons[7].draw(2, 80);
                    }
                    if (tabComponentId[9] != -1 && (flashingSidebarId != 9 || clientClock % 20 < 10)) {
                        sideicons[8].draw(3, 107);
                    }
                    if (tabComponentId[10] != -1 && (flashingSidebarId != 10 || clientClock % 20 < 10)) {
                        sideicons[9].draw(4, 142);
                    }
                    if (tabComponentId[11] != -1 && (flashingSidebarId != 11 || clientClock % 20 < 10)) {
                        sideicons[10].draw(2, 179);
                    }
                    if (tabComponentId[12] != -1 && (flashingSidebarId != 12 || clientClock % 20 < 10)) {
                        sideicons[11].draw(2, 206);
                    }
                    if (tabComponentId[13] != -1 && (flashingSidebarId != 13 || clientClock % 20 < 10)) {
                        sideicons[12].draw(2, 230);
                    }
                } catch (Exception ignored) {
                }
            }
            areaBackbase2.drawImage(492, super.graphics, 501);
            areaViewport.bind();
        }

        if (chatRedrawSettings) {
            chatRedrawSettings = false;
            areaBackbase1.bind();
            backbase1.draw(0, 0);

            fontPlain12.drawCentered(57, 0xffffff, true, 33, "Public chat");
            if (chatPublicSetting == 0) {
                fontPlain12.drawCentered(57, 65280, true, 46, "On");
            } else if (chatPublicSetting == 1) {
                fontPlain12.drawCentered(57, 0xffff00, true, 46, "Friends");
            } else if (chatPublicSetting == 2) {
                fontPlain12.drawCentered(57, 0xff0000, true, 46, "Off");
            } else if (chatPublicSetting == 3) {
                fontPlain12.drawCentered(57, 65535, true, 46, "Hide");
            }

            fontPlain12.drawCentered(186, 0xffffff, true, 33, "Private chat");
            if (chatPrivateSetting == 0) {
                fontPlain12.drawCentered(186, 65280, true, 46, "On");
            } else if (chatPrivateSetting == 1) {
                fontPlain12.drawCentered(186, 0xffff00, true, 46, "Friends");
            } else if (chatPrivateSetting == 2) {
                fontPlain12.drawCentered(186, 0xff0000, true, 46, "Off");
            }

            fontPlain12.drawCentered(326, 0xffffff, true, 33, "Trade/duel");
            if (chatTradeDuelSetting == 0) {
                fontPlain12.drawCentered(326, 65280, true, 46, "On");
            } else if (chatTradeDuelSetting == 1) {
                fontPlain12.drawCentered(326, 0xffff00, true, 46, "Friends");
            } else if (chatTradeDuelSetting == 2) {
                fontPlain12.drawCentered(326, 0xff0000, true, 46, "Off");
            }

            fontPlain12.drawCentered(462, 0xffffff, true, 38, "Report abuse");
            areaBackbase1.drawImage(471, super.graphics, 0);
            areaViewport.bind();
        }

        sceneDelta = 0;
    }

    public boolean isFriend(int row) {
        if (row < 0) {
            return false;
        }

        int actionId = actions[row];
        if (actionId >= 2000) {
            actionId -= 2000;
        }
        return actionId == 406;
    }

    public void useMenuOption(int option) {
        if (option < 0) {
            return;
        }
        if (chatbackInputType) {
            chatbackInputType = false;
            redrawChatback = true;
        }
        int a = paramA[option];
        int b = paramB[option];
        int c = paramC[option];
        int action = actions[option];
        if (action >= 2000) {
            action -= 2000;
        }

        if (action == 903 || action == 363) {
            String s = options[option];
            int l1 = s.indexOf("@whi@");
            if (l1 != -1) {
                s = s.substring(l1 + 5).trim();
                String name = StringUtils.formatName(StringUtils.fromBase37(StringUtils.toBase37(s)));

                boolean foundPlayer = false;
                for (int n = 0; n < playerCount; n++) {
                    PlayerEntity player = playerEntities[playerIndices[n]];
                    if (player == null || player.name == null || !player.name.equalsIgnoreCase(name)) {
                        continue;
                    }
                    moveTo(self.pathTileX[0], 1, false, player.pathTileX[0], self.pathTileZ[0], 2, 1, player.pathTileZ[0], 0, 0, 0);
                    if (action == 903) {
                        outBuffer.p1isaac(Packet.Client.OPPLAYER4);
                    } else { // action == 363
                        outBuffer.p1isaac(Packet.Client.OPPLAYER1);
                    }
                    outBuffer.p2(playerIndices[n]);
                    foundPlayer = true;
                    break;
                }

                if (!foundPlayer) {
                    addMessage(0, "Unable to find " + name, "");
                }
            }
        } else if (action == 450 && interactWithLoc(Packet.Client.ITEM_ON_OBJECT, a, b, c)) {
            outBuffer.p2(objInterface);
            outBuffer.p2(selectedObjSlot);
            outBuffer.p2(selectedObjInterface);
        } else if (action == 405 || action == 38 || action == 422 || action == 478 || action == 347) {
            if (action == 478) {
                if ((a & 3) == 0) {
                    itemOption4Counter++;
                }
                if (itemOption4Counter >= 90) {
                    outBuffer.p1isaac(Packet.Client.ANTICHEAT_ITEM_OPTION_4);
                }
                outBuffer.p1isaac(Packet.Client.ITEM_OPTION_4);
            } else if (action == 347) {
                outBuffer.p1isaac(Packet.Client.ITEM_OPTION_5);
            } else if (action == 422) {
                outBuffer.p1isaac(Packet.Client.ITEM_OPTION_3);
            } else if (action == 405) {
                itemOption1Counter += c;
                if (itemOption1Counter >= 97) {
                    outBuffer.p1isaac(Packet.Client.ANTICHEAT_ITEM_OPTION_1);
                    outBuffer.p3(0xe42d58);
                }
                outBuffer.p1isaac(Packet.Client.ITEM_OPTION_1);
            } else { // action == 38
                outBuffer.p1isaac(Packet.Client.ITEM_OPTION_2);
            }
            outBuffer.p2(c);
            outBuffer.p2(a);
            outBuffer.p2(b);
            selectedCycle = 0;
            selectedInterface = b;
            selectedItem = a;
            selectedArea = 2;
            if (Component.instances[b].parent == viewportInterfaceIndex) {
                selectedArea = 1;
            }
            if (Component.instances[b].parent == chatbackComponentId) {
                selectedArea = 3;
            }
        }
        if (action == 728 || action == 542 || action == 6 || action == 963 || action == 245) {
            NPCEntity npc = npcEntities[c];
            if (npc != null) {
                moveTo(self.pathTileX[0], 1, false, npc.pathTileX[0], self.pathTileZ[0], 2, 1, npc.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                if (action == 542) {
                    outBuffer.p1isaac(Packet.Client.OPNPC2);
                } else if (action == 6) {
                    if ((c & 3) == 0) {
                        npcAction3Counter++;
                    }
                    if (npcAction3Counter >= 124) {
                        outBuffer.p1isaac(Packet.Client.OPNPCT1);
                        outBuffer.p4(0);
                    }
                    outBuffer.p1isaac(Packet.Client.OPNPC3);
                } else if (action == 963) {
                    outBuffer.p1isaac(Packet.Client.OPNPC4);
                } else if (action == 728) {
                    outBuffer.p1isaac(Packet.Client.OPNPC1);
                } else { // action == 245
                    if ((c & 3) == 0) {
                        npcAction5Counter++;
                    }
                    if (npcAction5Counter >= 85) {
                        outBuffer.p1isaac(Packet.Client.OPNPCT2);
                        outBuffer.p2(39596);
                    }
                    outBuffer.p1isaac(Packet.Client.OPNPC5);
                }
                outBuffer.p2(c);
            }
        } else if (action == 217) {
            boolean flag = moveTo(self.pathTileX[0], 0, false, a, self.pathTileZ[0], 2, 0, b, 0, 0, 0);
            if (!flag) {
                moveTo(self.pathTileX[0], 1, false, a, self.pathTileZ[0], 2, 1, b, 0, 0, 0);
            }
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
            outBuffer.p1isaac(Packet.Client.ITEM_ON_GROUND_ITEM);
            outBuffer.p2(a + baseTileX);
            outBuffer.p2(b + baseTileZ);
            outBuffer.p2(c);
            outBuffer.p2(objInterface);
            outBuffer.p2(selectedObjSlot);
            outBuffer.p2(selectedObjInterface);
        } else if (action == 1175) {
            int k1 = c >> 14 & 0x7fff;
            LocType loc = LocType.get(k1);
            String description;
            if (loc.description != null) {
                description = loc.description;
            } else {
                description = "It's a " + loc.name + ".";
            }
            addMessage(0, description, "");
        } else if (action == 285) {
            // OP_OBJ1 = 285
            interactWithLoc(Packet.Client.OPOBJ1, a, b, c);
        } else if (action == 881) {
            outBuffer.p1isaac(Packet.Client.ITEM_ON_ITEM);
            outBuffer.p2(c);
            outBuffer.p2(a);
            outBuffer.p2(b);
            outBuffer.p2(objInterface);
            outBuffer.p2(selectedObjSlot);
            outBuffer.p2(selectedObjInterface);
            selectedCycle = 0;
            selectedInterface = b;
            selectedItem = a;
            selectedArea = 2;
            if (Component.instances[b].parent == viewportInterfaceIndex) {
                selectedArea = 1;
            }
            if (Component.instances[b].parent == chatbackComponentId) {
                selectedArea = 3;
            }
        } else if (action == 391) {
            outBuffer.p1isaac(Packet.Client.MAGIC_ON_ITEM);
            outBuffer.p2(c);
            outBuffer.p2(a);
            outBuffer.p2(b);
            outBuffer.p2(spellInterface);
            selectedCycle = 0;
            selectedInterface = b;
            selectedItem = a;
            selectedArea = 2;
            if (Component.instances[b].parent == viewportInterfaceIndex) {
                selectedArea = 1;
            }
            if (Component.instances[b].parent == chatbackComponentId) {
                selectedArea = 3;
            }
        } else if (action == 660) {
            if (!menuVisible) {
                mapSquare.setClick(super.clickY - 11, super.clickX - 8);
            } else {
                mapSquare.setClick(b - 11, a - 8);
            }
        } else if (action == 188) {
            selectedObject = 1;
            selectedObjSlot = a;
            selectedObjInterface = b;
            objInterface = c;
            selectedObjName = ObjType.get(c).name;
            selectedSpell = 0;
            return;
        } else if (action == 44 && !chatContinuingDialogue) {
            outBuffer.p1isaac(Packet.Client.INTERFACE_CONTINUE);
            outBuffer.p2(b);
            chatContinuingDialogue = true;
        } else if (action == 1773) {
            ObjType obj = ObjType.get(c);
            String s4;
            if (b >= 0x186a0) {
                s4 = b + " x " + obj.name;
            } else if (obj.description != null) {
                s4 = obj.description;
            } else {
                s4 = "It's a " + obj.name + ".";
            }
            addMessage(0, s4, "");
        } else if (action == 900) {
            NPCEntity npc = npcEntities[c];
            if (npc != null) {
                moveTo(self.pathTileX[0], 1, false, npc.pathTileX[0], self.pathTileZ[0], 2, 1, npc.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                outBuffer.p1isaac(Packet.Client.ITEM_ON_NPC);
                outBuffer.p2(c);
                outBuffer.p2(objInterface);
                outBuffer.p2(selectedObjSlot);
                outBuffer.p2(selectedObjInterface);
            }
        } else if (action == 1373 || action == 1544 || action == 151 || action == 1101) {
            PlayerEntity player = playerEntities[c];
            if (player != null) {
                moveTo(self.pathTileX[0], 1, false, player.pathTileX[0], self.pathTileZ[0], 2, 1, player.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                if (action == 1101) {
                    outBuffer.p1isaac(Packet.Client.OPPLAYER1);
                } else if (action == 151) {
                    playerAction2Counter++;
                    if (playerAction2Counter >= 90) {
                        outBuffer.p1isaac(Packet.Client.OPPLAYERT);
                        outBuffer.p2(31114);
                    }
                    outBuffer.p1isaac(Packet.Client.OPPLAYER2);
                } else if (action == 1373) {
                    outBuffer.p1isaac(Packet.Client.OPPLAYER4);
                } else { // action == 1544
                    outBuffer.p1isaac(Packet.Client.OPPLAYER3);
                }
                outBuffer.p2(c);
            }
        } else if (action == 265) {
            NPCEntity npc = npcEntities[c];
            if (npc != null) {
                moveTo(self.pathTileX[0], 1, false,
                    npc.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    npc.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                outBuffer.p1isaac(Packet.Client.MAGIC_ON_NPC);
                outBuffer.p2(c);
                outBuffer.p2(spellInterface);
            }
        } else if (action == 679) {
            String s1 = options[option];
            int i2 = s1.indexOf("@whi@");
            if (i2 != -1) {
                long l3 = StringUtils.toBase37(s1.substring(i2 + 5).trim());
                int i4 = -1;
                for (int n = 0; n < friendCount; n++) {
                    if (friendName37[n] != l3) {
                        continue;
                    }
                    i4 = n;
                    break;
                }

                if (i4 != -1 && friendWorld[i4] > 0) {
                    redrawChatback = true;
                    chatbackInputType = false;
                    showSocialInput = true;
                    socialInput = "";
                    socialAction = 3;
                    targetPlayerName = friendName37[i4];
                    socialMessage = "Enter message to send to " + friendName[i4];
                }
            }
        } else if (action == 55) {
            interactWithLoc(Packet.Client.MAGIC_ON_OBJECT, a, b, c);
            outBuffer.p2(spellInterface);
        } else if (action == 224 || action == 993 || action == 99 || action == 746 || action == 877) {
            boolean flag1 = moveTo(self.pathTileX[0], 0, false, a, self.pathTileZ[0], 2, 0, b, 0, 0, 0);
            if (!flag1) {
                moveTo(self.pathTileX[0], 1, false, a, self.pathTileZ[0], 2, 1, b, 0, 0, 0);
            }

            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
            if (action == 224) {
                outBuffer.p1isaac(Packet.Client.GROUND_ITEM_1);
            } else if (action == 746) {
                outBuffer.p1isaac(Packet.Client.GROUND_ITEM_ACTION);
            } else if (action == 877) {
                outBuffer.p1isaac(Packet.Client.LIGHT_ITEM);
            } else if (action == 99) {
                outBuffer.p1isaac(Packet.Client.PICKUP_GROUND_ITEM);
            } else { // action == 993
                outBuffer.p1isaac(Packet.Client.GROUND_ITEM_5);
            }
            outBuffer.p2(a + baseTileX);
            outBuffer.p2(b + baseTileZ);
            outBuffer.p2(c);
        } else if (action == 1607) {
            NPCEntity npc = npcEntities[c];
            if (npc != null) {
                String examine;
                if (npc.info.description != null) {
                    examine = npc.info.description;
                } else {
                    examine = "It's a " + npc.info.name + ".";
                }
                addMessage(0, examine, "");
            }
        } else if (action == 504) {
            // OP_OBJ2 = 504
            interactWithLoc(Packet.Client.OPOBJ2, a, b, c);
        } else if (action == 930) {
            Component component = Component.instances[b];
            selectedSpell = 1;
            spellInterface = b;
            selectedFlags = component.optionFlags;
            selectedObject = 0;
            String s6 = component.optionCircumfix;
            if (s6.contains(" ")) {
                s6 = s6.substring(0, s6.indexOf(" "));
            }
            String s10 = component.optionCircumfix;
            if (s10.contains(" ")) {
                s10 = s10.substring(s10.indexOf(" ") + 1);
            }
            selectedSpellPrefix = s6 + " " + component.optionSuffix + " " + s10;
            if (selectedFlags == 16) {
                sidebarRedraw = true;
                selectedTab = 3;
                sidebarRedrawIcons = true;
            }
            return;
        } else if (action == 951) {
            Component inter = Component.instances[b];
            boolean flag3 = true;
            if (inter.contentType > 0) {
                flag3 = handleComponentAction(inter);
            }
            if (flag3) {
                outBuffer.p1isaac(Packet.Client.INTERFACE_BUTTON);
                outBuffer.p2(b);
            }
        } else if (action == 602 || action == 596 || action == 22 || action == 892 || action == 415) {
            if (action == 22) {
                outBuffer.p1isaac(Packet.Client.ITEM_ACTION_3);
            } else if (action == 415) {
                if ((b & 3) == 0) {
                    itemAction5Counter++;
                }
                if (itemAction5Counter >= 55) {
                    outBuffer.p1isaac(Packet.Client.ANTICHEAT_ITEM_ACTION_5);
                    outBuffer.p4(0);
                }
                outBuffer.p1isaac(Packet.Client.ITEM_ACTION_5);
            } else if (action == 602) {
                outBuffer.p1isaac(Packet.Client.ITEM_ACTION_1);
            } else if (action == 892) {
                if ((a & 3) == 0) {
                    itemAction4Counter++;
                }
                if (itemAction4Counter >= 130) {
                    outBuffer.p1isaac(Packet.Client.ANTICHEAT_ITEM_ACTION_4);
                    outBuffer.p1(177);
                }
                outBuffer.p1isaac(Packet.Client.ITEM_ACTION_4);
            } else { // action == 596
                outBuffer.p1isaac(Packet.Client.ITEM_ACTION_2);
            }
            outBuffer.p2(c);
            outBuffer.p2(a);
            outBuffer.p2(b);
            selectedCycle = 0;
            selectedInterface = b;
            selectedItem = a;
            selectedArea = 2;
            if (Component.instances[b].parent == viewportInterfaceIndex) {
                selectedArea = 1;
            }
            if (Component.instances[b].parent == chatbackComponentId) {
                selectedArea = 3;
            }
        } else if (action == 581) {
            // OP_OBJ4 = 581
            if ((c & 3) == 0) {
                objectAction4Counter++;
            }
            if (objectAction4Counter >= 99) {
                outBuffer.p1isaac(Packet.Client.OPOBJT1);
                outBuffer.p4(0);
            }
            interactWithLoc(Packet.Client.OPOBJ4, a, b, c);
        } else if (action == 965) {
            boolean flag2 = moveTo(self.pathTileX[0], 0, false, a, self.pathTileZ[0], 2, 0, b, 0, 0, 0);
            if (!flag2) {
                moveTo(self.pathTileX[0], 1, false, a, self.pathTileZ[0], 2, 1, b, 0, 0, 0);
            }
            crossX = super.clickX;
            crossY = super.clickY;
            crossType = 2;
            crossCycle = 0;
            outBuffer.p1isaac(Packet.Client.MAGIC_ON_GROUND_ITEM);
            outBuffer.p2(a + baseTileX);
            outBuffer.p2(b + baseTileZ);
            outBuffer.p2(c);
            outBuffer.p2(spellInterface);
        } else if (action == 1501) {
            // OP_OBJ5 = 1501
            objectAction5Counter += baseTileZ;
            if (objectAction5Counter >= 92) {
                outBuffer.p1isaac(Packet.Client.OPOBJT2);
                outBuffer.p4(0);
            }
            interactWithLoc(Packet.Client.OPOBJ5, a, b, c);
        } else if (action == 364) {
            // OP_OBJ3 = 364;
            interactWithLoc(Packet.Client.OPOBJ3, a, b, c);
        } else if (action == 1102) {
            ObjType obj = ObjType.get(c);
            String examine;
            if (obj.description != null) {
                examine = obj.description;
            } else {
                examine = "It's a " + obj.name + ".";
            }
            addMessage(0, examine, "");
        } else if (action == 960) {
            outBuffer.p1isaac(Packet.Client.INTERFACE_BUTTON);
            outBuffer.p2(b);
            Component inter = Component.instances[b];
            if (inter.script != null && inter.script[0][0] == 5) {
                int j2 = inter.script[0][1];
                if (variables[j2] != inter.scriptCompareValue[0]) {
                    variables[j2] = inter.scriptCompareValue[0];
                    updateVarp(j2);
                    sidebarRedraw = true;
                }
            }
        } else if (action == 34) {
            String s2 = options[option];
            int k2 = s2.indexOf("@whi@");
            if (k2 != -1) {
                closeInterface();
                reportInput = s2.substring(k2 + 5).trim();
                reportAbuseMuteToggle = false;
                for (int n = 0; n < Component.instances.length; n++) {
                    if (Component.instances[n] == null || Component.instances[n].contentType != 600) {
                        continue;
                    }
                    openInterfaceId = viewportInterfaceIndex = Component.instances[n].parent;
                    break;
                }
            }
        } else if (action == 947) {
            closeInterface();
        } else if (action == 367) {
            PlayerEntity player = playerEntities[c];
            if (player != null) {
                moveTo(self.pathTileX[0], 1, false,
                    player.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    player.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                outBuffer.p1isaac(Packet.Client.ITEM_ON_PLAYER);
                outBuffer.p2(c);
                outBuffer.p2(objInterface);
                outBuffer.p2(selectedObjSlot);
                outBuffer.p2(selectedObjInterface);
            }
        } else if (action == 465) {
            outBuffer.p1isaac(Packet.Client.INTERFACE_BUTTON);
            outBuffer.p2(b);
            Component inter = Component.instances[b];
            if (inter.script != null && inter.script[0][0] == 5) {
                int l2 = inter.script[0][1];
                variables[l2] = 1 - variables[l2];
                updateVarp(l2);
                sidebarRedraw = true;
            }
        } else if (action == 406 || action == 436 || action == 557 || action == 556) {
            String s3 = options[option];
            int i3 = s3.indexOf("@whi@");
            if (i3 != -1) {
                long l4 = StringUtils.toBase37(s3.substring(i3 + 5).trim());
                if (action == 406)
                    addFriend(l4);
                if (action == 436)
                    addIgnore(l4);
                if (action == 557)
                    removeFriend(l4);
                if (action == 556)
                    removeIgnore(l4);
            }
        } else if (action == 651) {
            PlayerEntity player = playerEntities[c];
            if (player != null) {
                moveTo(self.pathTileX[0], 1, false,
                    player.pathTileX[0],
                    self.pathTileZ[0], 2, 1,
                    player.pathTileZ[0], 0, 0, 0);
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 2;
                crossCycle = 0;
                outBuffer.p1isaac(Packet.Client.MAGIC_ON_PLAYER);
                outBuffer.p2(c);
                outBuffer.p2(spellInterface);
            }
        }
        selectedObject = 0;
        selectedSpell = 0;
    }

    public String getHost() {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp.getDocumentBase().getHost().toLowerCase();
        }

        if (super.frame != null) {
            return "runescape.com";
        } else {
            return super.getDocumentBase().getHost().toLowerCase();
        }
    }

    public void drawMenu() {
        int j = menuX;
        int k = menuY;
        int l = menuWidth;
        int i1 = menuHeight;
        int j1 = 0x5d5447;
        Draw2D.fillRect(k, j, j1, l, i1);
        Draw2D.fillRect(k + 1, j + 1, 0, l - 2, 16);
        Draw2D.drawRect(j + 1, 0, i1 - 19, k + 18, l - 2);
        fontBold12.draw(j + 3, k + 14, j1, "Choose Option");

        int k1 = super.mouseX;
        int l1 = super.mouseY;
        if (mouseArea == 0) {
            k1 -= 8;
            l1 -= 11;
        } else if (mouseArea == 1) {
            k1 -= 562;
            l1 -= 231;
        } else if (mouseArea == 2) {
            k1 -= 22;
            l1 -= 375;
        }

        for (int i2 = 0; i2 < optionCount; i2++) {
            int j2 = k + 31 + (optionCount - 1 - i2) * 15;
            int k2 = 0xffffff;
            if (k1 > j && k1 < j + l && l1 > j2 - 13 && l1 < j2 + 3)
                k2 = 0xffff00;
            fontBold12.draw(j + 3, j2, options[i2], true, k2);
        }
    }

    public void handleChatInput(int k) {
        if (splitPrivateChat == 0) {
            return;
        }

        int l = 0;
        if (systemUpdateTimer != 0) {
            l = 1;
        }

        for (int n = 0; n < 100; n++) {
            if (chatMessage[n] != null) {
                int j1 = chatMessageType[n];
                if ((j1 == 3 || j1 == 7) && (j1 == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[n]))) {
                    int k1 = 329 - l * 13;
                    if (super.mouseX > 8 && super.mouseX < 520 && k - 11 > k1 - 10 && k - 11 <= k1 + 3) {
                        if (rights) {
                            options[optionCount] = "Report abuse @whi@" + chatMessagePrefix[n];
                            actions[optionCount] = 2034;
                            optionCount++;
                        }

                        options[optionCount] = "Add ignore @whi@" + chatMessagePrefix[n];
                        actions[optionCount] = 2436;
                        optionCount++;

                        options[optionCount] = "Add friend @whi@" + chatMessagePrefix[n];
                        actions[optionCount] = 2406;
                        optionCount++;
                    }

                    if (++l >= 5) {
                        return;
                    }
                }

                if ((j1 == 5 || j1 == 6) && chatPrivateSetting < 2 && ++l >= 5) {
                    return;
                }
            }
        }
    }

    public void updateComponentContent(Component inter) {
        int type = inter.contentType;
        if (type >= 1 && type <= 100) {
            if (--type >= friendCount) {
                inter.text = "";
                inter.buttonType = 0;
            } else {
                inter.text = friendName[type];
                inter.buttonType = 1;
            }
            return;
        }

        if (type >= 101 && type <= 200) {
            if ((type -= 101) >= friendCount) {
                inter.text = "";
                inter.buttonType = 0;
                return;
            }
            if (friendWorld[type] == 0) {
                inter.text = "@red@Offline";
            } else if (friendWorld[type] == nodeid) {
                inter.text = "@gre@World-" + (friendWorld[type] - 9);
            } else {
                inter.text = "@yel@World-" + (friendWorld[type] - 9);
            }
            inter.buttonType = 1;
            return;
        }

        if (type == 203) {
            inter.scrollHeight = friendCount * 15 + 20;
            if (inter.scrollHeight <= inter.height) {
                inter.scrollHeight = inter.height + 1;
            }
            return;
        }

        if (type >= 401 && type <= 500) {
            if ((type -= 401) >= ignoreCount) {
                inter.text = "";
                inter.buttonType = 0;
            } else {
                inter.text = StringUtils.formatName(StringUtils.fromBase37(ignoreName37[type]));
                inter.buttonType = 1;
            }
            return;
        }

        if (type == 503) {
            inter.scrollHeight = ignoreCount * 15 + 20;
            if (inter.scrollHeight <= inter.height) {
                inter.scrollHeight = inter.height + 1;
            }
            return;
        }

        if (type == 327) {
            inter.modelEyePitch = 150;
            inter.modelYaw = (int) (Math.sin((double) clientClock / 40D) * 256D) & 0x7ff;
            if (characterDesignUpdate) {
                characterDesignUpdate = false;
                Model[] models = new Model[7];
                int k = 0;
                for (int l = 0; l < 7; l++) {
                    int i1 = characterDesigns[l];
                    if (i1 >= 0) {
                        models[k++] = IDKType.instances[i1].getModel();
                    }
                }

                Model m = new Model(models, k);
                for (int j1 = 0; j1 < 5; j1++) {
                    if (characterDesignColors[j1] != 0) {
                        m.recolor(APPEARANCE_COLORS[j1][0], APPEARANCE_COLORS[j1][characterDesignColors[j1]]);
                        if (j1 == 1) {
                            m.recolor(BEARD_COLORS[0], BEARD_COLORS[characterDesignColors[j1]]);
                        }
                    }
                }

                m.applyGroups();
                m.applyFrame(SeqType.instances[self.standSeq].primaryFrames[0]);
                m.applyLighting(64, 850, -30, -50, -30, true);
                inter.modelDisabled = m;
            }

            return;
        }

        if (type == 324) {
            if (sprite == null) {
                sprite = inter.image;
                spriteActive = inter.activeImage;
            }

            if (characterDesignIsMale) {
                inter.image = spriteActive;
            } else {
                inter.image = sprite;
            }
            return;
        }

        if (type == 325) {
            if (sprite == null) {
                sprite = inter.image;
                spriteActive = inter.activeImage;
            }

            if (characterDesignIsMale) {
                inter.image = sprite;
            } else {
                inter.image = spriteActive;
            }
            return;
        }

        if (type == 600) {
            inter.text = reportInput;
            if (clientClock % 20 < 10) {
                inter.text += "|";
            } else {
                inter.text += " ";
            }
            return;
        }

        if (type == 613) {
            if (rights) {
                if (reportAbuseMuteToggle) {
                    inter.color = 0xff0000;
                    inter.text = "Moderator option: Mute player for 48 hours: <ON>";
                } else {
                    inter.color = 0xffffff;
                    inter.text = "Moderator option: Mute player for 48 hours: <OFF>";
                }
            } else {
                inter.text = "";
            }
        }

        if (type == 650 || type == 655) {
            if (lastLoginIP != 0) {
                String s;
                if (daysSinceLogin == 0) {
                    s = "earlier today";
                } else if (daysSinceLogin == 1) {
                    s = "yesterday";
                } else {
                    s = daysSinceLogin + " days ago";
                }

                inter.text = "You last logged in " + s + " from: " + Signlink.dns;
            } else {
                inter.text = "";
            }
        }

        if (type == 651) {
            if (unreadMessageCount == 0) {
                inter.text = "0 unread messages";
                inter.color = 0xffff00;
            }

            if (unreadMessageCount == 1) {
                inter.text = "1 unread message";
                inter.color = 65280;
            }

            if (unreadMessageCount > 1) {
                inter.text = unreadMessageCount + " unread messages";
                inter.color = 65280;
            }
        }

        if (type == 652) {
            if (daysSinceRecoveryChange == 201) {
                inter.text = "";
            } else if (daysSinceRecoveryChange == 200) {
                inter.text = "You have not yet set any password recovery questions.";
            } else {
                String s1;
                if (daysSinceRecoveryChange == 0) {
                    s1 = "Earlier today";
                } else if (daysSinceRecoveryChange == 1) {
                    s1 = "Yesterday";
                } else {
                    s1 = daysSinceRecoveryChange + " days ago";
                }

                inter.text = s1 + " you changed your recovery questions";
            }
        }

        if (type == 653) {
            if (daysSinceRecoveryChange == 201) {
                inter.text = "";
            } else if (daysSinceRecoveryChange == 200) {
                inter.text = "We strongly recommend you do so now to secure your account.";
            } else {
                inter.text = "If you do not remember making this change then cancel it immediately";
            }
        }

        if (type == 654) {
            if (daysSinceRecoveryChange == 201) {
                inter.text = "";
                return;
            }

            if (daysSinceRecoveryChange == 200) {
                inter.text = "Do this from the 'account management' area on our front webpage";
                return;
            }

            inter.text = "Do this from the 'account management' area on our front webpage";
        }
    }

    public boolean wavesave(byte[] src, int len) {
        if (src == null) {
            return true;
        } else {
            return Signlink.wavesave(src, len);
        }
    }

    public boolean wavereplay() {
        return Signlink.wavereplay();
    }

    public void wavevol(int i) {
        Signlink.wavevol = i;
    }

    public void updateLocalNpcs(Buffer buffer, int size) {
        do {
            if (buffer.bitOffset + 21 >= size * 8) {
                break;
            }

            int nid = buffer.gBit(13);
            if (nid == 8191) {
                break;
            }

            if (npcEntities[nid] == null) {
                npcEntities[nid] = new NPCEntity();
            }

            NPCEntity npc = npcEntities[nid];
            npcIndices[npcCount++] = nid;
            npc.removeTimer = clientClock;
            try {
                npc.info = NPCType.get(buffer.gBit(11));
                npc.index = npc.info.size;
                npc.runSeq = npc.info.walkSeq;
                npc.walkSeq = npc.info.turnAroundSeq;
                npc.turnAroundSeq = npc.info.turnRightSeq;
                npc.turnRightSeq = npc.info.turnLeftSeq;
                npc.standSeq = npc.info.standSeq;
            } catch (Exception ignored) {
            }

            int x = buffer.gBit(5);
            if (x > 15) {
                x -= 32;
            }

            int z = buffer.gBit(5);
            if (z > 15) {
                z -= 32;
            }

            npc.move(false, self.pathTileX[0] + x, self.pathTileZ[0] + z);

            int hasUpdate = buffer.gBit(1);
            if (hasUpdate == 1) {
                entityUpdateIndices[updateCount++] = nid;
            }
        } while (true);

        buffer.accessBytes();
    }

    public boolean handleComponentAction(Component component) {
        int type = component.contentType;
        if (type == 201) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 1;
            socialMessage = "Enter name of friend to add to list";
        } else if (type == 202) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 2;
            socialMessage = "Enter name of friend to delete from list";
        } else if (type == 205) {
            idleTimeout = 250;
            return true;
        } else if (type == 501) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 4;
            socialMessage = "Enter name of player to add to list";
        } else if (type == 502) {
            redrawChatback = true;
            chatbackInputType = false;
            showSocialInput = true;
            socialInput = "";
            socialAction = 5;
            socialMessage = "Enter name of player to delete from list";
        } else if (type >= 300 && type <= 313) {
            int j = (type - 300) / 2;
            int i1 = type & 1;
            int l1 = characterDesigns[j];
            if (l1 != -1) {
                do {
                    if (i1 == 0 && --l1 < 0)
                        l1 = IDKType.count - 1;
                    if (i1 == 1 && ++l1 >= IDKType.count)
                        l1 = 0;
                } while (IDKType.instances[l1].validStyle
                    || IDKType.instances[l1].type != j + (characterDesignIsMale ? 0 : 7));
                characterDesigns[j] = l1;
                characterDesignUpdate = true;
            }
        } else if (type >= 314 && type <= 323) {
            int k = (type - 314) / 2;
            int j1 = type & 1;
            int i2 = characterDesignColors[k];
            if (j1 == 0 && --i2 < 0)
                i2 = APPEARANCE_COLORS[k].length - 1;
            if (j1 == 1 && ++i2 >= APPEARANCE_COLORS[k].length)
                i2 = 0;
            characterDesignColors[k] = i2;
            characterDesignUpdate = true;
        } else if (type == 324 && !characterDesignIsMale) {
            characterDesignIsMale = true;
            resetCharacterDesign();
        } else if (type == 325 && characterDesignIsMale) {
            characterDesignIsMale = false;
            resetCharacterDesign();
        } else if (type == 326) {
            outBuffer.p1isaac(Packet.Client.IF_DESIGN);
            outBuffer.p1(characterDesignIsMale ? 0 : 1);
            for (int l = 0; l < 7; l++) {
                outBuffer.p1(characterDesigns[l]);
            }
            for (int k1 = 0; k1 < 5; k1++) {
                outBuffer.p1(characterDesignColors[k1]);
            }
            return true;
        } else if (type == 613) {
            reportAbuseMuteToggle = !reportAbuseMuteToggle;
        } else if (type >= 601 && type <= 612) {
            closeInterface();
            if (reportInput.length() > 0) {
                outBuffer.p1isaac(Packet.Client.REPORT_ABUSE);
                outBuffer.p8(StringUtils.toBase37(reportInput));
                outBuffer.p1(type - 601);
                outBuffer.p1(reportAbuseMuteToggle ? 1 : 0);
            }
        }
        return false;
    }

    public void load() {
        if (Signlink.sunjava) {
            super.mindel = 5;
        }

        if (alreadyStarted) {
            errorStarted = true;
            return;
        }
        alreadyStarted = true;

        try {
            int nextSecs = 5;
            for (archiveChecksums[9] = 0; archiveChecksums[9] == 0; ) {
                showProgress("Connecting to fileserver", 10);
                try {
                    DataInputStream dis = openStream("crc" + (int) (Math.random() * 99999999D));
                    Buffer b = new Buffer(new byte[40]);
                    dis.readFully(b.data, 0, 40);
                    for (int k = 0; k < 10; k++) {
                        archiveChecksums[k] = b.g4();
                    }
                    dis.close();
                } catch (IOException _ex) {
                    for (int secs = nextSecs; secs > 0; secs--) {
                        showProgress("Error loading - Will retry in " + secs + " secs.", 10);
                        try {
                            Thread.sleep(1000L);
                        } catch (Exception ignored) {
                        }
                    }

                    nextSecs *= 2;
                    if (nextSecs > 60) {
                        nextSecs = 60;
                    }
                }
            }

            if (!lowMemory) {
                startMidiThread = true;
                midiThreadActive = true;
                startThread(this, 2);
                Signlink.setSoundfont(loadArchive("virtual instruments", archiveChecksums[9], "soundfont", 5));
                setMidi(0xbc614e, "scape_main", 40000);
            }

            this.titleArchive = loadArchive("title screen", archiveChecksums[1], "title", 10);

            fontPlain11 = new Font(this.titleArchive, "p11");
            fontPlain12 = new Font(this.titleArchive, "p12");
            fontBold12 = new Font(this.titleArchive, "b12");
            fontQuill8 = new Font(this.titleArchive, "q8");

            loadTitleBackground();
            loadTitleForeground();

            FileArchive configArchive = loadArchive("config", archiveChecksums[2], "config", 15);
            FileArchive interfaceArchive = loadArchive("interface", archiveChecksums[3], "interface", 20);
            FileArchive mediaArchive = loadArchive("2d graphics", archiveChecksums[4], "media", 30);
            FileArchive modelsArchive = loadArchive("3d graphics", archiveChecksums[5], "models", 40);
            FileArchive texturesArchive = loadArchive("textures", archiveChecksums[6], "textures", 60);
            FileArchive wordencArchive = loadArchive("chat system", archiveChecksums[7], "wordenc", 65);
            FileArchive soundsArchive = loadArchive("sound effects", archiveChecksums[8], "sounds", 70);

            levelRenderFlags = new byte[4][104][104];
            levelHeightMaps = new int[4][105][105];
            mapSquare = new MapSquare(levelHeightMaps, 104, 4, 104);
            for (int l = 0; l < 4; l++) {
                collisionMaps[l] = new CollisionMap(104, 104);
            }
            minimap = new Sprite(512, 512);

            showProgress("Unpacking media", 75);
            inback = new IndexedSprite(mediaArchive, "invback", 0);
            chatback = new IndexedSprite(mediaArchive, "chatback", 0);
            mapback = new IndexedSprite(mediaArchive, "mapback", 0);
            backbase1 = new IndexedSprite(mediaArchive, "backbase1", 0);
            backbase2 = new IndexedSprite(mediaArchive, "backbase2", 0);
            backhmid1 = new IndexedSprite(mediaArchive, "backhmid1", 0);

            try {
                for (int i1 = 0; i1 < 13; i1++) {
                    sideicons[i1] = new IndexedSprite(mediaArchive, "sideicons", i1);
                }
            } catch (Exception ignored) {
            }

            compass = new Sprite(mediaArchive, "compass", 0);
            try {
                for (int n = 0; n < 50; n++) {
                    // skip weird seaweed-like sprite on rivers
                    if (n == 22) {
                        continue;
                    }

                    mapscene[n] = new IndexedSprite(mediaArchive, "mapscene", n);
                }
            } catch (Exception ignored) {
            }

            try {
                for (int k1 = 0; k1 < 50; k1++) {
                    mapfunction[k1] = new Sprite(mediaArchive, "mapfunction", k1);
                }
            } catch (Exception ignored) {
            }

            try {
                for (int l1 = 0; l1 < 20; l1++) {
                    hitmarks[l1] = new Sprite(mediaArchive, "hitmarks", l1);
                }
            } catch (Exception ignored) {
            }

            try {
                for (int i2 = 0; i2 < 20; i2++) {
                    headicons[i2] = new Sprite(mediaArchive, "headicons", i2);
                }
            } catch (Exception ignored) {
            }

            try {
                mapflag = new Sprite(mediaArchive, "mapflag", 0);
            } catch (Exception ignored) {
            }

            for (int j2 = 0; j2 < 8; j2++) {
                cross[j2] = new Sprite(mediaArchive, "cross", j2);
            }

            mapdot1 = new Sprite(mediaArchive, "mapdots", 0);
            mapdot2 = new Sprite(mediaArchive, "mapdots", 1);
            mapdot3 = new Sprite(mediaArchive, "mapdots", 2);
            mapdot4 = new Sprite(mediaArchive, "mapdots", 3);
            scrollbar1 = new IndexedSprite(mediaArchive, "scrollbar", 0);
            scrollbar2 = new IndexedSprite(mediaArchive, "scrollbar", 1);
            redstone1 = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone2 = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone3 = new IndexedSprite(mediaArchive, "redstone3", 0);

            redstone1h = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1h.flipHorizontally();

            redstone2h = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2h.flipHorizontally();

            redstone1v = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1v.flipVertically();

            redstone2v = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2v.flipVertically();

            redstone3v = new IndexedSprite(mediaArchive, "redstone3", 0);
            redstone3v.flipVertically();

            redstone1hv = new IndexedSprite(mediaArchive, "redstone1", 0);
            redstone1hv.flipHorizontally();
            redstone1hv.flipVertically();

            redstone2hv = new IndexedSprite(mediaArchive, "redstone2", 0);
            redstone2hv.flipHorizontally();
            redstone2hv.flipVertically();

            Sprite temp = new Sprite(mediaArchive, "backleft1", 0);
            backleft1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backleft2", 0);
            backleft2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backright1", 0);
            backright1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backright2", 0);
            backright2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backtop1", 0);
            backtop1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backtop2", 0);
            backtop2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid1", 0);
            backvmid1 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid2", 0);
            backvmid2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backvmid3", 0);
            backvmid3 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            temp = new Sprite(mediaArchive, "backhmid2", 0);
            backhmid2 = new DrawArea(getBaseComponent(), temp.width, temp.height);
            temp.drawOpaque(0, 0);

            int k2 = (int) (Math.random() * 21D) - 10;
            int l2 = (int) (Math.random() * 21D) - 10;
            int i3 = (int) (Math.random() * 21D) - 10;
            int j3 = (int) (Math.random() * 41D) - 20;
            for (int k3 = 0; k3 < 50; k3++) {
                if (mapfunction[k3] != null) {
                    mapfunction[k3].translate(k2 + j3, l2 + j3, i3 + j3);
                }
                if (mapscene[k3] != null) {
                    mapscene[k3].translate(k2 + j3, l2 + j3, i3 + j3);
                }
            }

            showProgress("Unpacking textures", 80);
            Draw3D.unpackTextures(texturesArchive);
            Draw3D.setBrightness(0.8D);
            Draw3D.setupPools(20);
            showProgress("Unpacking models", 83);
            Model.load(modelsArchive);
            SeqBase.load(modelsArchive);
            SeqFrame.load(modelsArchive);
            showProgress("Unpacking config", 86);
            SeqType.load(configArchive);
            LocType.load(configArchive);
            FloorType.load(configArchive);
            ObjType.load(configArchive);
            NPCType.load(configArchive);
            IDKType.load(configArchive);
            SpotAnimType.load(configArchive);
            VarType.load(configArchive);
            ObjType.isMember = members;

            if (!lowMemory) {
                showProgress("Unpacking sounds", 90);
                byte[] src = soundsArchive.read("sounds.dat", null);
                Buffer sounds = new Buffer(src);
                SoundTrack.load(sounds);
            }

            showProgress("Unpacking interfaces", 92);
            Font[] fonts = {fontPlain11, fontPlain12, fontBold12, fontQuill8};
            Component.load(mediaArchive, fonts, interfaceArchive);
            showProgress("Preparing game engine", 97);
            for (int l3 = 0; l3 < 33; l3++) {
                int i4 = 999;
                int k4 = 0;
                for (int i5 = 0; i5 < 35; i5++) {
                    if (mapback.pixels[i5
                        + l3 * mapback.width] == 0) {
                        if (i4 == 999)
                            i4 = i5;
                        continue;
                    }
                    if (i4 == 999)
                        continue;
                    k4 = i5;
                    break;
                }

                compassLeft[l3] = i4;
                compassLineWidth[l3] = k4 - i4;
            }

            for (int j4 = 9; j4 < 160; j4++) {
                int l4 = 999;
                int j5 = 0;
                for (int l5 = 10; l5 < 168; l5++) {
                    if (mapback.pixels[l5 + j4 * mapback.width] == 0
                        && (l5 > 34 || j4 > 34)) {
                        if (l4 == 999)
                            l4 = l5;
                        continue;
                    }
                    if (l4 == 999)
                        continue;
                    j5 = l5;
                    break;
                }

                minimapLeft[j4 - 9] = l4 - 21;
                minimapLineWidth[j4 - 9] = j5 - l4;
            }

            Draw3D.prepareOffsets(96, 479);
            chatOffsets = Draw3D.offsets;

            Draw3D.prepareOffsets(261, 190);
            sidebarOffsets = Draw3D.offsets;

            Draw3D.prepareOffsets(334, 512);
            viewportOffsets = Draw3D.offsets;

            int[] ai = new int[9];
            for (int k5 = 0; k5 < 9; k5++) {
                int i6 = 128 + k5 * 32 + 15;
                int j6 = 600 + i6 * 3;
                int k6 = Draw3D.sin[i6];
                ai[k5] = j6 * k6 >> 16;
            }

            MapSquare.init(ai, 800, 512, 334, 500);
            WordPack.load(wordencArchive);
        } catch (Exception ex) {
            ex.printStackTrace();
            errorLoading = true;
        }
    }

    public void updateInput() {
        if (objDragArea != 0) {
            return;
        }

        options[0] = "Cancel";
        actions[0] = 1252;
        optionCount = 1;
        handleChatInput(super.mouseY);
        hoveredInterfaceIndex = 0;

        if (super.mouseX > 8 && super.mouseY > 11 && super.mouseX < 520 && super.mouseY < 345) {
            if (viewportInterfaceIndex != -1 && viewportInterfaceIndex < Component.instances.length) {
                updateInterface(super.mouseY, super.mouseX, 11, Component.instances[viewportInterfaceIndex], 8, 0);
            } else {
                updateViewport();
            }
        }

        if (hoveredInterfaceIndex != viewportHoveredInterfaceIndex) {
            viewportHoveredInterfaceIndex = hoveredInterfaceIndex;
        }

        hoveredInterfaceIndex = 0;
        if (super.mouseX > 562 && super.mouseY > 231 && super.mouseX < 752 && super.mouseY < 492) {
            if (sidebarInterfaceId != -1) {
                updateInterface(super.mouseY, super.mouseX, 231, Component.instances[sidebarInterfaceId], 562, 0);
            } else if (tabComponentId[selectedTab] != -1) {
                updateInterface(super.mouseY, super.mouseX, 231, Component.instances[tabComponentId[selectedTab]], 562, 0);
            }
        }

        if (hoveredInterfaceIndex != sidebarHoveredInterfaceIndex) {
            sidebarRedraw = true;
            sidebarHoveredInterfaceIndex = hoveredInterfaceIndex;
        }

        hoveredInterfaceIndex = 0;
        if (super.mouseX > 22 && super.mouseY > 375 && super.mouseX < 431 && super.mouseY < 471) {
            if (chatbackComponentId != -1) {
                updateInterface(super.mouseY, super.mouseX, 375, Component.instances[chatbackComponentId], 22, 0);
            } else {
                updatePlayerTooltip(super.mouseY - 375);
            }
        }

        if (chatbackComponentId != -1 && hoveredInterfaceIndex != chatHoveredInterfaceIndex) {
            redrawChatback = true;
            chatHoveredInterfaceIndex = hoveredInterfaceIndex;
        }

        for (boolean flag = false; !flag; ) {
            flag = true;
            for (int j = 0; j < optionCount - 1; j++) {
                if (actions[j] < 1000 && actions[j + 1] > 1000) {
                    String s = options[j];
                    options[j] = options[j + 1];
                    options[j + 1] = s;
                    int k = actions[j];
                    actions[j] = actions[j + 1];
                    actions[j + 1] = k;
                    k = paramA[j];
                    paramA[j] = paramA[j + 1];
                    paramA[j + 1] = k;
                    k = paramB[j];
                    paramB[j] = paramB[j + 1];
                    paramB[j + 1] = k;
                    k = paramC[j];
                    paramC[j] = paramC[j + 1];
                    paramC[j + 1] = k;
                    flag = false;
                }
            }
        }
    }

    public void clearCaches() {
        LocType.models.clear();
        LocType.builtModels.clear();
        NPCType.models.clear();
        ObjType.modelCache.clear();
        ObjType.iconCache.clear();
        PlayerEntity.models.clear();
        SpotAnimType.models.clear();
    }

    public void drawViewport3d() {
        drawChat();
        if (crossType == 1) {
            cross[crossCycle / 100].draw(crossY - 8 - 11, crossX - 8 - 8);
        }

        if (crossType == 2) {
            cross[4 + crossCycle / 100].draw(crossY - 8 - 11, crossX - 8 - 8);
        }

        if (viewportInterfaceIndex != -1 && viewportInterfaceIndex < Component.instances.length) {
            animateInterface(viewportInterfaceIndex, sceneDelta);
            drawInterface(0, 0, Component.instances[viewportInterfaceIndex], 0);
        }

        drawWildyLevel();
        if (!menuVisible) {
            updateInput();
            drawTooltip();
        } else if (mouseArea == 0) {
            drawMenu();
        }

        if (inMultizone == 1) {
            if (wildernessLevel > 0 || worldLocationState == 1) {
                headicons[1].draw(258, 472);
            } else {
                headicons[1].draw(296, 472);
            }
        }

        if (wildernessLevel > 0) {
            headicons[0].draw(296, 472);
            fontPlain12.drawCentered(329, 0xffff00, "Level: " + wildernessLevel, 484);
        }

        if (worldLocationState == 1) {
            headicons[6].draw(296, 472);
            fontPlain12.drawCentered(329, 0xffff00, "Arena", 484);
        }

        if (systemUpdateTimer != 0) {
            int k = systemUpdateTimer / 50;
            int l = k / 60;
            k %= 60;
            if (k < 10) {
                fontPlain12.draw(4, 329, 0xffff00, "System update in: " + l + ":0" + k);
                return;
            }
            fontPlain12.draw(4, 329, 0xffff00, "System update in: " + l + ":" + k);
        }
    }

    public void updateOrbitCamera() {
        int j = self.x + cameraAnticheatOffsetX;
        int k = self.z + cameraAnticheatOffsetZ;
        if (cameraOrbitX - j < -500 || cameraOrbitX - j > 500 || cameraOrbitZ - k < -500 || cameraOrbitZ - k > 500) {
            cameraOrbitX = j;
            cameraOrbitZ = k;
        }

        if (cameraOrbitX != j) {
            cameraOrbitX += (j - cameraOrbitX) / 16;
        }
        if (cameraOrbitZ != k) {
            cameraOrbitZ += (k - cameraOrbitZ) / 16;
        }

        if (super.keyDown[Applet.KEY_LEFT] == 1) {
            cameraYawModifier += (-24 - cameraYawModifier) / 2;
        } else if (super.keyDown[Applet.KEY_RIGHT] == 1) {
            cameraYawModifier += (24 - cameraYawModifier) / 2;
        } else {
            cameraYawModifier /= 2;
        }

        if (super.keyDown[Applet.KEY_UP] == 1) {
            cameraPitchModifier += (12 - cameraPitchModifier) / 2;
        } else if (super.keyDown[Applet.KEY_DOWN] == 1) {
            cameraPitchModifier += (-12 - cameraPitchModifier) / 2;
        } else {
            cameraPitchModifier /= 2;
        }

        cameraYaw = cameraYaw + cameraYawModifier / 2 & 0x7ff;
        cameraOrbitPitch += cameraPitchModifier / 2;
        if (cameraOrbitPitch < 128) {
            cameraOrbitPitch = 128;
        }
        if (cameraOrbitPitch > 383) {
            cameraOrbitPitch = 383;
        }

        int l = cameraOrbitX >> 7;
        int i1 = cameraOrbitZ >> 7;
        int j1 = getLandY(currentLevel, cameraOrbitX, cameraOrbitZ);
        int k1 = 0;
        if (l > 3 && i1 > 3 && l < 100 && i1 < 100) {
            for (int x = l - 4; x <= l + 4; x++) {
                for (int z = i1 - 4; z <= i1 + 4; z++) {
                    int p = currentLevel;
                    if (p < 3 && (levelRenderFlags[1][x][z] & 2) == 2)
                        p++;
                    int l2 = j1 - levelHeightMaps[p][x][z];
                    if (l2 > k1)
                        k1 = l2;
                }
            }
        }

        int i2 = k1 * 192;
        if (i2 > 0x17f00) {
            i2 = 0x17f00;
        }
        if (i2 < 32768) {
            i2 = 32768;
        }
        if (i2 > cameraMaxY) {
            cameraMaxY += (i2 - cameraMaxY) / 24;
        } else if (i2 < cameraMaxY) {
            cameraMaxY += (i2 - cameraMaxY) / 80;
        }
    }

    public void updateSceneProjectiles() {
        for (ProjectileEntity entity = (ProjectileEntity) projectiles.peekLast(); entity != null; entity = (ProjectileEntity) projectiles.getPrevious()) {
            if (entity.level != currentLevel || clientClock > entity.lastCycle) {
                entity.unlink();
            } else if (clientClock >= entity.firstCycle) {
                if (entity.targetIndex > 0) {
                    NPCEntity npc = npcEntities[entity.targetIndex - 1];
                    if (npc != null) {
                        entity.setTarget(getLandY(entity.level, npc.x, npc.z) - entity.baseZ, npc.z, npc.x, clientClock);
                    }
                }
                if (entity.targetIndex < 0) {
                    int i = -entity.targetIndex - 1;
                    PlayerEntity player;
                    if (i == selfPlayerId) {
                        player = self;
                    } else {
                        player = playerEntities[i];
                    }
                    if (player != null) {
                        entity.setTarget(getLandY(entity.level, player.x, player.z) - entity.baseZ, player.z, player.x, clientClock);
                    }
                }
                entity.update(sceneDelta);
                mapSquare.add((int) entity.y, 60, entity.yaw, (int) entity.x, -1, false, null, entity, (int) entity.z, currentLevel);
            }
        }
    }

    public void refresh() {
        redrawTitleBackground = true;
    }

    public void drawOnMinimap(int dy, Sprite image, int dx) {
        try {
            int l = cameraYaw + minimapAnticheatAngle & 0x7ff;
            int i1 = dx * dx + dy * dy;
            if (i1 > 6400) {
                return;
            }
            int j1 = Model.sin[l];
            int k1 = Model.cos[l];
            j1 = (j1 * 256) / (minimapZoom + 256);
            k1 = (k1 * 256) / (minimapZoom + 256);
            int l1 = dy * j1 + dx * k1 >> 16;
            int i2 = dy * k1 - dx * j1 >> 16;
            if (i1 > 2500) {
                image.drawMasked(mapback, 83 - i2 - image.cropH / 2, (94 + l1) - image.cropW / 2);
            } else {
                image.draw(83 - i2 - image.cropH / 2, (94 + l1) - image.cropW / 2);
            }
        } catch (Exception ignored) {
        }
    }

    public int mix(int i, int j, int k) {
        int i1 = 256 - j;
        return ((i & 0xff00ff) * i1 + (k & 0xff00ff) * j & 0xff00ff00) + ((i & 0xff00) * i1 + (k & 0xff00) * j & 0xff0000) >> 8;
    }

    public void setDrawPos(int i, PathingEntity entity) {
        projectToScreen(entity.z, entity.x, i);
    }

    public void projectToScreen(int i, int j, int l) {
        if (j < 128 || i < 128 || j > 13056 || i > 13056) {
            drawX = -1;
            drawY = -1;
            return;
        }
        int i1 = getLandY(currentLevel, j, i) - l;
        j -= cameraX;
        i1 -= cameraY;
        i -= cameraZ;
        int j1 = Model.sin[cameraPitch];
        int k1 = Model.cos[cameraPitch];
        int l1 = Model.sin[cameraOrbitYaw];
        int i2 = Model.cos[cameraOrbitYaw];
        int j2 = i * l1 + j * i2 >> 16;
        i = i * i2 - j * l1 >> 16;
        j = j2;
        j2 = i1 * k1 - i * j1 >> 16;
        i = i1 * j1 + i * k1 >> 16;
        i1 = j2;
        if (i >= 50) {
            drawX = Draw3D.centerX + (j << 9) / i;
            drawY = Draw3D.centerY + (i1 << 9) / i;
        } else {
            drawX = -1;
            drawY = -1;
        }
    }

    public boolean interactWithLoc(int i, int j, int k, int l) {
        int i1 = l >> 14 & 0x7fff;
        int j1 = mapSquare.getInfo(currentLevel, j, k, l);
        if (j1 == -1)
            return false;
        int k1 = j1 & 0x1f;
        int l1 = j1 >> 6 & 3;
        if (k1 == 10 || k1 == 11 || k1 == 22) {
            LocType locType = LocType.get(i1);
            int i2;
            int j2;
            if (l1 == 0 || l1 == 2) {
                i2 = locType.sizeX;
                j2 = locType.sizeZ;
            } else {
                i2 = locType.sizeZ;
                j2 = locType.sizeX;
            }
            int k2 = locType.interactionSideFlags;
            if (l1 != 0)
                k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
            moveTo(self.pathTileX[0], i2, false, j,
                self.pathTileZ[0], 2, j2, k, 0, 0, k2);
        } else {
            moveTo(self.pathTileX[0], 0, false, j,
                self.pathTileZ[0], 2, 0, k, l1, k1 + 1, 0);
        }
        crossX = super.clickX;
        crossY = super.clickY;
        crossType = 2;
        crossCycle = 0;
        outBuffer.p1isaac(i);
        outBuffer.p2(j + baseTileX);
        outBuffer.p2(k + baseTileZ);
        outBuffer.p2(i1);
        return true;
    }

    public void showContextMenu() {
        int j = fontBold12.stringWidth("Choose Option");
        for (int k = 0; k < optionCount; k++) {
            int l = fontBold12.stringWidth(options[k]);
            if (l > j)
                j = l;
        }

        j += 8;
        int i1 = 15 * optionCount + 21;
        if (super.clickX > 8 && super.clickY > 11 && super.clickX < 520 && super.clickY < 345) {
            int j1 = super.clickX - 8 - j / 2;
            if (j1 + j > 512)
                j1 = 512 - j;
            if (j1 < 0)
                j1 = 0;
            int i2 = super.clickY - 11;
            if (i2 + i1 > 334)
                i2 = 334 - i1;
            if (i2 < 0)
                i2 = 0;
            menuVisible = true;
            mouseArea = 0;
            menuX = j1;
            menuY = i2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
        if (super.clickX > 562 && super.clickY > 231 && super.clickX < 752 && super.clickY < 492) {
            int k1 = super.clickX - 562 - j / 2;
            if (k1 < 0)
                k1 = 0;
            else if (k1 + j > 190)
                k1 = 190 - j;
            int j2 = super.clickY - 231;
            if (j2 < 0)
                j2 = 0;
            else if (j2 + i1 > 261)
                j2 = 261 - i1;
            menuVisible = true;
            mouseArea = 1;
            menuX = k1;
            menuY = j2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
        if (super.clickX > 22 && super.clickY > 375 && super.clickX < 501 && super.clickY < 471) {
            int l1 = super.clickX - 22 - j / 2;
            if (l1 < 0)
                l1 = 0;
            else if (l1 + j > 479)
                l1 = 479 - j;
            int k2 = super.clickY - 375;
            if (k2 < 0)
                k2 = 0;
            else if (k2 + i1 > 96)
                k2 = 96 - i1;
            menuVisible = true;
            mouseArea = 2;
            menuX = l1;
            menuY = k2;
            menuWidth = j;
            menuHeight = 15 * optionCount + 22;
        }
    }

    public DataInputStream openStream(String s) throws IOException {
        if (Signlink.mainapp != null)
            return Signlink.openurl(s);
        else
            return new DataInputStream((new URL(getCodeBase(), s)).openStream());
    }

    public void prepareTitleScreen() {
        if (titleTop != null)
            return;
        super.drawArea = null;
        areaChatback = null;
        areaMapback = null;
        areaInvback = null;
        areaViewport = null;
        areaBackbase1 = null;
        areaBackbase2 = null;
        areaBackhmid1 = null;

        titleLeft = new DrawArea(getBaseComponent(), 128, 265);
        Draw2D.clear();

        titleRight = new DrawArea(getBaseComponent(), 128, 265);
        Draw2D.clear();

        titleTop = new DrawArea(getBaseComponent(), 533, 186);
        Draw2D.clear();

        titleBottom = new DrawArea(getBaseComponent(), 360, 146);
        Draw2D.clear();

        titleCenter = new DrawArea(getBaseComponent(), 360, 200);
        Draw2D.clear();

        titleBottomLeft = new DrawArea(getBaseComponent(), 214, 267);
        Draw2D.clear();

        titleBottomRight = new DrawArea(getBaseComponent(), 215, 267);
        Draw2D.clear();

        titleLeftSpace = new DrawArea(getBaseComponent(), 86, 79);
        Draw2D.clear();

        titleRightSpace = new DrawArea(getBaseComponent(), 87, 79);
        Draw2D.clear();

        if (titleArchive != null) {
            loadTitleBackground();
            loadTitleForeground();
        }

        redrawTitleBackground = true;
    }

    public void runFlames() {
        flamesThreadActive = true;

        try {
            long l = System.currentTimeMillis();
            int j = 0;
            int k = 20;

            while (flameActive) {
                updateFlames();
                updateFlames();
                drawFlames();
                if (++j > 10) {
                    long l1 = System.currentTimeMillis();
                    int i1 = (int) (l1 - l) / 10 - k;
                    k = 40 - i1;
                    if (k < 5)
                        k = 5;
                    j = 0;
                    l = l1;
                }

                try {
                    Thread.sleep(k);
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }

        flamesThreadActive = false;
    }

    public void run() {
        if (startFlamesThread) {
            runFlames();
            return;
        }

        if (startMidiThread) {
            runMidi();
        } else {
            super.run();
        }
    }

    public void updateInterfaceScrollbar(int i, int k, int l, int i1, boolean flag, int j1, int k1, Component component) {
        if (scrollGripHeld) {
            scrollGripInputPadding = 32;
        } else {
            scrollGripInputPadding = 0;
        }

        scrollGripHeld = false;
        if (i >= j1 && i < j1 + 16 && k >= k1 && k < k1 + 16) {
            component.scrollY -= dragCycle * 4;
            if (flag) {
                sidebarRedraw = true;
            }
        } else if (i >= j1 && i < j1 + 16 && k >= (k1 + i1) - 16 && k < k1 + i1) {
            component.scrollY += dragCycle * 4;
            if (flag) {
                sidebarRedraw = true;
            }
        } else if (i >= j1 - scrollGripInputPadding && i < j1 + 16 + scrollGripInputPadding && k >= k1 + 16 && k < (k1 + i1) - 16 && dragCycle > 0) {
            int l1 = ((i1 - 32) * i1) / l;
            if (l1 < 8)
                l1 = 8;
            int i2 = k - k1 - 16 - l1 / 2;
            int j2 = i1 - 32 - l1;
            component.scrollY = ((l - i1) * i2) / j2;
            if (flag) {
                sidebarRedraw = true;
            }
            scrollGripHeld = true;
        }
    }

    public void login(String username, String password, boolean reconnect) {
        Signlink.errorname = username;

        try {
            if (!reconnect) {
                loginMessage0 = "";
                loginMessage1 = "Connecting to server...";
                drawTitleScreen();
            }

            stream = new BufferedStream(this, opensocket(serverGamePort + portoff));
            stream.read(inBuffer.data, 0, 8);
            inBuffer.offset = 0;

            long serverSeed = inBuffer.g8();
            int[] isaacSeed = new int[4];
            isaacSeed[0] = (int) (Math.random() * 99999999D);
            isaacSeed[1] = (int) (Math.random() * 99999999D);
            isaacSeed[2] = (int) (serverSeed >> 32);
            isaacSeed[3] = (int) serverSeed;

            outBuffer.offset = 0;
            outBuffer.p1(10);
            outBuffer.p4(isaacSeed[0]);
            outBuffer.p4(isaacSeed[1]);
            outBuffer.p4(isaacSeed[2]);
            outBuffer.p4(isaacSeed[3]);
            outBuffer.p4(Signlink.uid);
            outBuffer.pString(username);
            outBuffer.pString(password);
            outBuffer.rsaEncrypt(modulus, exponent);

            loginBuffer.offset = 0;
            if (reconnect) {
                loginBuffer.p1(18);
            } else {
                loginBuffer.p1(16);
            }
            loginBuffer.p1(outBuffer.offset + 36 + 1 + 1);
            loginBuffer.p1(225);
            loginBuffer.p1(lowMemory ? 1 : 0);
            for (int i = 0; i < 9; i++) {
                loginBuffer.p4(archiveChecksums[i]);
            }
            loginBuffer.pArrayBuffer(outBuffer.data, outBuffer.offset, 0);

            outBuffer.isaacState = new IsaacRandom(isaacSeed);
            for (int j = 0; j < 4; j++) {
                isaacSeed[j] += 50;
            }
            isaacState = new IsaacRandom(isaacSeed);

            stream.write(loginBuffer.data, loginBuffer.offset, 0);

            int response = stream.read();
            if (response == 1) {
                try {
                    Thread.sleep(2000L);
                } catch (Exception ignored) {
                }
                login(username, password, reconnect);
                return;
            }

            if (response == 2 || response == 18) {
                rights = response == 18;
                InputTracking.setDisabled();
                ingame = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                packetOpcode = -1;
                lastPacketOpcode = -1;
                secondMostRecentOpcode = -1;
                thirdMostRecentOpcode = -1;
                packetLength = 0;
                netIdleCycles = 0;
                systemUpdateTimer = 0;
                idleTimeout = 0;
                hintType = 0;
                optionCount = 0;
                menuVisible = false;
                super.idleCycles = 0;
                for (int l = 0; l < 100; l++) {
                    chatMessage[l] = null;
                }
                selectedObject = 0;
                selectedSpell = 0;
                sceneState = 0;
                waveCount = 0;
                cameraAnticheatOffsetX = (int) (Math.random() * 100D) - 50;
                cameraAnticheatOffsetZ = (int) (Math.random() * 110D) - 55;
                cameraAnticheatAngle = (int) (Math.random() * 80D) - 40;
                minimapAnticheatAngle = (int) (Math.random() * 120D) - 60;
                minimapZoom = (int) (Math.random() * 30D) - 20;
                cameraYaw = (int) (Math.random() * 20D) - 10 & 0x7ff;
                lastSceneLevel = -1;
                flagTileX = 0;
                flagTileY = 0;
                playerCount = 0;
                npcCount = 0;
                for (int i1 = 0; i1 < MAX_PLAYER_COUNT; i1++) {
                    playerEntities[i1] = null;
                    playerBuffers[i1] = null;
                }
                for (int j1 = 0; j1 < 8192; j1++) {
                    npcEntities[j1] = null;
                }
                self = playerEntities[LOCAL_PLAYER_INDEX] = new PlayerEntity();
                projectiles.clear();
                spotanims.clear();
                temporaryLocs.clear();
                for (int p = 0; p < 4; p++) {
                    for (int x = 0; x < 104; x++) {
                        for (int z = 0; z < 104; z++) {
                            objects[p][x][z] = null;
                        }
                    }
                }
                spawnedLocations = new LinkedList();
                friendCount = 0;
                stickyChatbackComponentId = -1;
                chatbackComponentId = -1;
                viewportInterfaceIndex = -1;
                sidebarInterfaceId = -1;
                chatContinuingDialogue = false;
                selectedTab = 3;
                chatbackInputType = false;
                menuVisible = false;
                showSocialInput = false;
                chatbackMessage = null;
                inMultizone = 0;
                flashingSidebarId = -1;
                characterDesignIsMale = true;
                resetCharacterDesign();
                for (int i2 = 0; i2 < 5; i2++) {
                    characterDesignColors[i2] = 0;
                }
                objectAction4Counter = 0;
                npcAction3Counter = 0;
                itemOption1Counter = 0;
                npcAction5Counter = 0;
                itemOption4Counter = 0;
                objectAction5Counter = 0;
                itemAction5Counter = 0;
                playerAction2Counter = 0;
                itemAction4Counter = 0;
                prepareGameScreen();
                return;
            }
            if (response == 3) {
                loginMessage0 = "";
                loginMessage1 = "Invalid username or password.";
                return;
            }
            if (response == 4) {
                loginMessage0 = "Your account has been disabled.";
                loginMessage1 = "Please check your message-centre for details.";
                return;
            }
            if (response == 5) {
                loginMessage0 = "Your account is already logged in.";
                loginMessage1 = "Try again in 60 secs...";
                return;
            }
            if (response == 6) {
                loginMessage0 = "RuneScape has been updated!";
                loginMessage1 = "Please reload this page.";
                return;
            }
            if (response == 7) {
                loginMessage0 = "This world is full.";
                loginMessage1 = "Please use a different world.";
                return;
            }
            if (response == 8) {
                loginMessage0 = "Unable to connect.";
                loginMessage1 = "Login server offline.";
                return;
            }
            if (response == 9) {
                loginMessage0 = "Login limit exceeded.";
                loginMessage1 = "Too many connections from your address.";
                return;
            }
            if (response == 10) {
                loginMessage0 = "Unable to connect.";
                loginMessage1 = "Bad session id.";
                return;
            }
            if (response == 11) {
                loginMessage0 = "Login server rejected session.";
                loginMessage1 = "Please try again.";
                return;
            }
            if (response == 12) {
                loginMessage0 = "You need a members account to login to this world.";
                loginMessage1 = "Please subscribe, or use a different world.";
                return;
            }
            if (response == 13) {
                loginMessage0 = "Could not complete login.";
                loginMessage1 = "Please try using a different world.";
                return;
            }
            if (response == 14) {
                loginMessage0 = "The server is being updated.";
                loginMessage1 = "Please wait 1 minute and try again.";
                return;
            }
            if (response == 15) {
                ingame = true;
                outBuffer.offset = 0;
                inBuffer.offset = 0;
                packetOpcode = -1;
                lastPacketOpcode = -1;
                secondMostRecentOpcode = -1;
                thirdMostRecentOpcode = -1;
                packetLength = 0;
                netIdleCycles = 0;
                systemUpdateTimer = 0;
                optionCount = 0;
                menuVisible = false;
                return;
            }
            if (response == 16) {
                loginMessage0 = "Login attempts exceeded.";
                loginMessage1 = "Please wait 1 minute and try again.";
                return;
            }
            if (response == 17) {
                loginMessage0 = "You are standing in a members-only area.";
                loginMessage1 = "To play on this world move to a free area first";
            }
        } catch (IOException _ex) {
            loginMessage0 = "";
            loginMessage1 = "Error connecting to server.";
        }
    }

    public void addLoc(int i, int j, int k, int l, int i1, int j1,
                       int l1) {
        if (j >= 1 && k >= 1 && j <= 102 && k <= 102) {
            if (lowMemory && l1 != currentLevel)
                return;
            int i2 = 0;
            if (l == 0)
                i2 = mapSquare.getWallBitset(l1, j, k);
            if (l == 1)
                i2 = mapSquare.getWallDecorationBitset(l1, k, j);
            if (l == 2)
                i2 = mapSquare.getLocationBitset(l1, j, k);
            if (l == 3)
                i2 = mapSquare.getGroundDecorationBitset(l1, j, k);
            if (i2 != 0) {
                int i3 = mapSquare.getInfo(l1, j, k, i2);
                int j2 = i2 >> 14 & 0x7fff;
                int k2 = i3 & 0x1f;
                int l2 = i3 >> 6;
                if (l == 0) {
                    mapSquare.removeWall(j, l1, k);
                    LocType locType = LocType.get(j2);
                    if (locType.hasCollision)
                        collisionMaps[l1].removeWall(locType.isSolid, l2, j, k, k2);
                }
                if (l == 1)
                    mapSquare.removeWallDecoration(l1, k, j);
                if (l == 2) {
                    mapSquare.removeLocations(j, k, l1);
                    LocType locType = LocType.get(j2);
                    if (j + locType.sizeX > 103 || k + locType.sizeX > 103 || j + locType.sizeZ > 103
                        || k + locType.sizeZ > 103)
                        return;
                    if (locType.hasCollision)
                        collisionMaps[l1].removeLoc(k, j, l2, locType.sizeX, locType.isSolid,
                            locType.sizeZ);
                }
                if (l == 3) {
                    mapSquare.removeGroundDecoration(l1, j, k);
                    LocType locType_2 = LocType.get(j2);
                    if (locType_2.hasCollision && locType_2.interactable)
                        collisionMaps[l1].removeBlock(k, j);
                }
            }
            if (i1 >= 0) {
                int j3 = l1;
                if (j3 < 3 && (levelRenderFlags[1][j][k] & 2) == 2)
                    j3++;
                SceneManager.addLoc(j, locs, collisionMaps[l1], k, i, levelHeightMaps, l1, i1, j1,
                    mapSquare, j3);
            }
        }
    }

    public void addFriend(long l) {
        if (l == 0L)
            return;
        if (friendCount >= 100) {
            addMessage(0, "Your friends list is full. Max of 100 hit", "");
            return;
        }
        String s = StringUtils.formatName(StringUtils.fromBase37(l));
        for (int j = 0; j < friendCount; j++)
            if (friendName37[j] == l) {
                addMessage(0, s + " is already on your friend list", "");
                return;
            }

        for (int k = 0; k < ignoreCount; k++)
            if (ignoreName37[k] == l) {
                addMessage(0, "Please remove " + s + " from your ignore list first", "");
                return;
            }

        if (s.equals(self.name))
            return;
        friendName[friendCount] = s;
        friendName37[friendCount] = l;
        friendWorld[friendCount] = 0;
        friendCount++;
        sidebarRedraw = true;
        outBuffer.p1isaac(Packet.Client.FRIENDLIST_ADD);
        outBuffer.p8(l);
    }

    public void unload() {
        Signlink.reporterror = false;

        try {
            if (stream != null) {
                stream.close();
            }
        } catch (Exception ignored) {
        }

        stream = null;
        midistop();
        midiThreadActive = false;
        outBuffer = null;
        loginBuffer = null;
        inBuffer = null;
        sceneMapIndex = null;
        sceneMapLandData = null;
        sceneMapLocData = null;
        levelHeightMaps = null;
        levelRenderFlags = null;
        mapSquare = null;
        collisionMaps = null;
        pathWaypoint = null;
        pathDistance = null;
        waypointX = null;
        waypointY = null;
        tmpTexels = null;
        areaInvback = null;
        areaMapback = null;
        areaViewport = null;
        areaChatback = null;
        areaBackbase1 = null;
        areaBackbase2 = null;
        areaBackhmid1 = null;
        backleft1 = null;
        backleft2 = null;
        backright1 = null;
        backright2 = null;
        backtop1 = null;
        backtop2 = null;
        backvmid1 = null;
        backvmid2 = null;
        backvmid3 = null;
        backhmid2 = null;
        inback = null;
        mapback = null;
        chatback = null;
        backbase1 = null;
        backbase2 = null;
        backhmid1 = null;
        sideicons = null;
        redstone1 = null;
        redstone2 = null;
        redstone3 = null;
        redstone1h = null;
        redstone2h = null;
        redstone1v = null;
        redstone2v = null;
        redstone3v = null;
        redstone1hv = null;
        redstone2hv = null;
        compass = null;
        hitmarks = null;
        headicons = null;
        cross = null;
        mapdot1 = null;
        mapdot2 = null;
        mapdot3 = null;
        mapdot4 = null;
        mapscene = null;
        mapfunction = null;
        tileRenderCount = null;
        playerEntities = null;
        playerIndices = null;
        entityUpdateIndices = null;
        playerBuffers = null;
        deadEntityIndices = null;
        npcEntities = null;
        npcIndices = null;
        objects = null;
        spawnedLocations = null;
        temporaryLocs = null;
        projectiles = null;
        spotanims = null;
        locs = null;
        paramA = null;
        paramB = null;
        actions = null;
        paramC = null;
        options = null;
        variables = null;
        activeMapFunctionX = null;
        activeMapFunctionZ = null;
        activeMapFunctions = null;
        minimap = null;
        friendName = null;
        friendName37 = null;
        friendWorld = null;
        titleLeft = null;
        titleRight = null;
        titleTop = null;
        titleBottom = null;
        titleCenter = null;
        titleBottomLeft = null;
        titleBottomRight = null;
        titleLeftSpace = null;
        titleRightSpace = null;
        disposeTitleComponents();
        LocType.unload();
        NPCType.unload();
        ObjType.unload();
        FloorType.instances = null;
        IDKType.instances = null;
        Component.instances = null;
        SeqType.instances = null;
        SpotAnimType.instances = null;
        SpotAnimType.models = null;
        VarType.instances = null;
        super.drawArea = null;
        PlayerEntity.models = null;
        Draw3D.unload();
        MapSquare.unload();
        Model.unload();
        SeqBase.instances = null;
        SeqFrame.instances = null;
        System.gc();
    }

    public Socket opensocket(int port) throws IOException {
        if (Signlink.mainapp != null) {
            return Signlink.opensocket(port);
        } else {
            return new Socket(InetAddress.getByName(getCodeBase().getHost()), port);
        }
    }

    public void addPlayerOptions(int i, int j, PlayerEntity player, int k) {
        if (player == self) {
            return;
        }

        if (optionCount >= 400) {
            return;
        }

        String s = player.name
            + getLevelColorTag(self.combatLevel, player.combatLevel) + " (level-"
            + player.combatLevel + ")";
        if (selectedObject == 1) {
            options[optionCount] = "Use " + selectedObjName + " with @whi@" + s;
            actions[optionCount] = 367;
            paramC[optionCount] = j;
            paramA[optionCount] = k;
            paramB[optionCount] = i;
            optionCount++;
        } else if (selectedSpell == 1) {
            if ((selectedFlags & 8) == 8) {
                options[optionCount] = selectedSpellPrefix + " @whi@" + s;
                actions[optionCount] = 651;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
        } else {
            options[optionCount] = "Follow @whi@" + s;
            actions[optionCount] = 1544;
            paramC[optionCount] = j;
            paramA[optionCount] = k;
            paramB[optionCount] = i;
            optionCount++;
            if (tutorialIslandState == 0) {
                options[optionCount] = "Trade with @whi@" + s;
                actions[optionCount] = 1373;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (wildernessLevel > 0) {
                options[optionCount] = "Attack @whi@" + s;
                if (self.combatLevel >= player.combatLevel)
                    actions[optionCount] = 151;
                else
                    actions[optionCount] = 2151;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (worldLocationState == 1) {
                options[optionCount] = "Fight @whi@" + s;
                actions[optionCount] = 151;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
            if (worldLocationState == 2) {
                options[optionCount] = "Duel-with @whi@" + s;
                actions[optionCount] = 1101;
                paramC[optionCount] = j;
                paramA[optionCount] = k;
                paramB[optionCount] = i;
                optionCount++;
            }
        }

        for (int l = 0; l < optionCount; l++) {
            if (actions[l] == 660) {
                options[l] = "Walk here @whi@" + s;
                return;
            }
        }
    }

    public void updateGame() {
        if (systemUpdateTimer > 1) {
            systemUpdateTimer--;
        }

        if (idleTimeout > 0) {
            idleTimeout--;
        }

        // read 100 packets per client tick
        for (int i = 0; i < 100; i++) {
            if (!readPacket()) {
                break;
            }
        }

        if (!ingame) {
            return;
        }

        for (int j = 0; j < waveCount; j++) {
            if (waveDelay[j] <= 0) {
                boolean failed = false;
                try {
                    if (waveId[j] == lastWaveId && waveLoops[j] == lastWaveLoops) {
                        if (!wavereplay()) {
                            failed = true;
                        }
                    } else {
                        Buffer b = SoundTrack.generate(waveLoops[j], waveId[j]);
                        if (System.currentTimeMillis() + (long) (b.offset / 22) > lastWaveStartTime + (long) (lastWaveLength / 22)) {
                            lastWaveLength = b.offset;
                            lastWaveStartTime = System.currentTimeMillis();

                            if (wavesave(b.data, b.offset)) {
                                lastWaveId = waveId[j];
                                lastWaveLoops = waveLoops[j];
                            } else {
                                failed = true;
                            }
                        }
                    }
                } catch (Exception ignored) {
                }

                if (!failed || waveDelay[j] == -5) {
                    waveCount--;

                    for (int k = j; k < waveCount; k++) {
                        waveId[k] = waveId[k + 1];
                        waveLoops[k] = waveLoops[k + 1];
                        waveDelay[k] = waveDelay[k + 1];
                    }

                    j--;
                } else {
                    waveDelay[j] = -5;
                }
            } else {
                waveDelay[j]--;
            }
        }

        if (nextMusicDelay > 0) {
            nextMusicDelay -= 20;
            if (nextMusicDelay < 0) {
                nextMusicDelay = 0;
            }
            if (nextMusicDelay == 0 && midiActive && !lowMemory) {
                setMidi(midiCrc, currentMidi, midiSize);
            }
        }

        Buffer tracking = InputTracking.flushAndContinue();
        if (tracking != null) {
            outBuffer.p1isaac(Packet.Client.TRACKING_DATA);
            outBuffer.p2(tracking.offset);
            outBuffer.pArrayBuffer(tracking.data, tracking.offset, 0);
            tracking.release();
        }

        netIdleCycles++;
        if (netIdleCycles > 750) {
            reconnect();
        }

        updatePlayers(true);
        updateNpcEntity(true);
        updateEntityVoices();
        updateTemporaryLocs();
        if ((super.keyDown[Applet.KEY_LEFT] == 1 || super.keyDown[Applet.KEY_RIGHT] == 1 || super.keyDown[Applet.KEY_UP] == 1 || super.keyDown[Applet.KEY_DOWN] == 1) && cameraMovedWrite++ > 5) {
            cameraMovedWrite = 0;
            outBuffer.p1isaac(Packet.Client.CAMERA_MOVEMENT);
            outBuffer.p2(cameraOrbitPitch);
            outBuffer.p2(cameraYaw);
            outBuffer.p1(minimapAnticheatAngle);
            outBuffer.p1(minimapZoom);
        }
        sceneDelta++;

        if (crossType != 0) {
            crossCycle += 20;
            if (crossCycle >= 400) {
                crossType = 0;
            }
        }

        if (selectedArea != 0) {
            selectedCycle++;
            if (selectedCycle >= 15) {
                if (selectedArea == 2)
                    sidebarRedraw = true;
                if (selectedArea == 3)
                    redrawChatback = true;
                selectedArea = 0;
            }
        }

        if (objDragArea != 0) {
            objDragCycles++;
            if (super.mouseX > objGrabX + 5 || super.mouseX < objGrabX - 5 || super.mouseY > objGrabY + 5 || super.mouseY < objGrabY - 5)
                objGrabThreshold = true;
            if (super.dragButton == 0) {
                if (objDragArea == 2)
                    sidebarRedraw = true;
                if (objDragArea == 3)
                    redrawChatback = true;
                objDragArea = 0;
                if (objGrabThreshold && objDragCycles >= 5) {
                    hoveredSlotParentId = -1;
                    updateInput();
                    if (hoveredSlotParentId == objDragComponentId && hoveredSlot != objDragSlot) {
                        Component inter = Component.instances[objDragComponentId];
                        int j1 = inter.inventoryIndices[hoveredSlot];
                        inter.inventoryIndices[hoveredSlot] = inter.inventoryIndices[objDragSlot];
                        inter.inventoryIndices[objDragSlot] = j1;
                        j1 = inter.inventoryAmount[hoveredSlot];
                        inter.inventoryAmount[hoveredSlot] = inter.inventoryAmount[objDragSlot];
                        inter.inventoryAmount[objDragSlot] = j1;
                        outBuffer.p1isaac(Packet.Client.ITEM_MOVE);
                        outBuffer.p2(objDragComponentId);
                        outBuffer.p2(objDragSlot);
                        outBuffer.p2(hoveredSlot);
                    }
                } else if ((button == 1 || isFriend(optionCount - 1)) && optionCount > 2)
                    showContextMenu();
                else if (optionCount > 0)
                    useMenuOption(optionCount - 1);
                selectedCycle = 10;
                super.mouseButton = 0;
            }
        }

        updateGameCounter++;
        if (updateGameCounter > 127) {
            updateGameCounter = 0;
            outBuffer.p1isaac(Packet.Client.ANTICHEAT_UPDATE_GAME);
            outBuffer.p3(0x4c2b2c);
        }

        if (MapSquare.clickedTileX != -1) {
            int l = MapSquare.clickedTileX;
            int k1 = MapSquare.clickedTileZ;
            boolean canMove = moveTo(self.pathTileX[0], 0, true, l, self.pathTileZ[0], 0, 0, k1, 0, 0, 0);
            MapSquare.clickedTileX = -1;

            if (canMove) {
                crossX = super.clickX;
                crossY = super.clickY;
                crossType = 1;
                crossCycle = 0;
            }
        }

        if (super.mouseButton == 1 && chatbackMessage != null) {
            chatbackMessage = null;
            redrawChatback = true;
            super.mouseButton = 0;
        }

        updateMiniMenu();
        updateMinimapInput();
        updateSidebarTabInput();
        updateChatSettingInput();

        if (super.dragButton == 1 || super.mouseButton == 1) {
            dragCycle++;
        }

        if (sceneState == 2) {
            updateOrbitCamera();
        }

        if (sceneState == 2 && cameraOriented) {
            calculateCameraPos();
        }

        for (int i1 = 0; i1 < 5; i1++) {
            unknownCameraVariable[i1]++;
        }

        updateKeyboardInput();

        super.idleCycles++;
        if (super.idleCycles > 4500) {
            idleTimeout = 250;
            super.idleCycles -= 500;
            outBuffer.p1isaac(Packet.Client.IDLE_TIMER);
        }

        cameraOffsetCycle++;
        if (cameraOffsetCycle > 500) {
            cameraOffsetCycle = 0;
            int i = (int) (Math.random() * 8D);
            if ((i & 1) == 1)
                cameraAnticheatOffsetX += cameraOffsetXModifier;
            if ((i & 2) == 2)
                cameraAnticheatOffsetZ += cameraOffsetZModifier;
            if ((i & 4) == 4)
                cameraAnticheatAngle += cameraOffsetYawModifier;
        }

        if (cameraAnticheatOffsetX < -50) {
            cameraOffsetXModifier = 2;
        } else if (cameraAnticheatOffsetX > 50) {
            cameraOffsetXModifier = -2;
        }

        if (cameraAnticheatOffsetZ < -55) {
            cameraOffsetZModifier = 2;
        } else if (cameraAnticheatOffsetZ > 55) {
            cameraOffsetZModifier = -2;
        }

        if (cameraAnticheatAngle < -40) {
            cameraOffsetYawModifier = 1;
        } else if (cameraAnticheatAngle > 40) {
            cameraOffsetYawModifier = -1;
        }

        minimapOffsetCycle++;
        if (minimapOffsetCycle > 500) {
            minimapOffsetCycle = 0;
            int i2 = (int) (Math.random() * 8D);
            if ((i2 & 1) == 1) {
                minimapAnticheatAngle += minimapAngleModifier;
            }
            if ((i2 & 2) == 2) {
                minimapZoom += minimapZoomModifier;
            }
        }

        if (minimapAnticheatAngle < -60) {
            minimapAngleModifier = 2;
        } else if (minimapAnticheatAngle > 60) {
            minimapAngleModifier = -2;
        }

        if (minimapZoom < -20) {
            minimapZoomModifier = 1;
        } else if (minimapZoom > 10) {
            minimapZoomModifier = -1;
        }

        updateGameCounter2++;
        if (updateGameCounter2 > 110) {
            updateGameCounter2 = 0;
            outBuffer.p1isaac(Packet.Client.ANTICHEAT_UPDATE_GAME_2);
            outBuffer.p4(0);
        }

        keepaliveCounter++;
        if (keepaliveCounter > 50) {
            outBuffer.p1isaac(Packet.Client.KEEPALIVE);
        }

        try {
            if (stream != null && outBuffer.offset > 0) {
                stream.write(outBuffer.data, outBuffer.offset, 0);
                outBuffer.offset = 0;
                keepaliveCounter = 0;
            }
        } catch (IOException _ex) {
            reconnect();
        } catch (Exception exception1) {
            disconnect();
        }
    }

    public void drawTooltip() {
        if (optionCount < 2 && selectedObject == 0 && selectedSpell == 0) {
            return;
        }

        String s;

        if (selectedObject == 1 && optionCount < 2) {
            s = "Use " + selectedObjName + " with...";
        } else if (selectedSpell == 1 && optionCount < 2) {
            s = selectedSpellPrefix + "...";
        } else {
            s = options[optionCount - 1];
        }

        if (optionCount > 2) {
            s = s + "@whi@ / " + (optionCount - 2) + " more options";
        }

        fontBold12.drawTooltip(clientClock / 1000, true, 15, 0xffffff, s, 4);
    }

    public void updateSceneSpotAnims() {
        for (SpotAnimEntity entity = (SpotAnimEntity) spotanims
            .peekLast(); entity != null; entity = (SpotAnimEntity) spotanims
            .getPrevious())
            if (entity.level != currentLevel || entity.finished)
                entity.unlink();
            else if (clientClock >= entity.firstCycle) {
                entity.update(sceneDelta);
                if (entity.finished)
                    entity.unlink();
                else
                    mapSquare.add(entity.z, 60, 0, entity.x, -1,
                        false, null, entity, entity.y, entity.level);
            }

    }

    public URL getCodeBase() {
        if (Signlink.mainapp != null)
            return Signlink.mainapp.getCodeBase();
        try {
            if (super.frame != null) {
                return new URL("http://" + serverAddress + ":" + (serverHttpPort + portoff));
            }
        } catch (Exception ignored) {
        }
        return super.getCodeBase();
    }

    public boolean moveTo(int x, int j, boolean flag, int k, int z, int type, int k1, int l1, int i2, int j2, int k2) {
        byte byte0 = 104;
        byte byte1 = 104;
        for (int stepX = 0; stepX < byte0; stepX++) {
            for (int stepZ = 0; stepZ < byte1; stepZ++) {
                pathWaypoint[stepX][stepZ] = 0;
                pathDistance[stepX][stepZ] = 0x5f5e0ff;
            }
        }

        int stepX = x;
        int stepZ = z;
        pathWaypoint[x][z] = 99;
        pathDistance[x][z] = 0;

        int step = 0;
        int current = 0;
        waypointX[step] = x;
        waypointY[step++] = z;

        boolean reached = false;
        int j4 = waypointX.length;
        int[][] flags = collisionMaps[currentLevel].flags;

        while (current != step) {
            stepX = waypointX[current];
            stepZ = waypointY[current];
            current = (current + 1) % j4;
            if (stepX == k && stepZ == l1) {
                reached = true;
                break;
            }
            if (j2 != 0) {
                if ((j2 < 5 || j2 == 10) && collisionMaps[currentLevel].reachedWall(i2, l1, j2 - 1, stepZ, k, stepX)) {
                    reached = true;
                    break;
                }
                if (j2 < 10 && collisionMaps[currentLevel].reachedDecoration(i2, j2 - 1, stepX, k, stepZ, l1)) {
                    reached = true;
                    break;
                }
            }
            if (j != 0 && k1 != 0 && collisionMaps[currentLevel].reachedObject(stepZ, k1, stepX, k, k2, l1, j)) {
                reached = true;
                break;
            }
            int l4 = pathDistance[stepX][stepZ] + 1;
            if (stepX > 0 && pathWaypoint[stepX - 1][stepZ] == 0 && (flags[stepX - 1][stepZ] & 0x280108) == 0) {
                waypointX[step] = stepX - 1;
                waypointY[step] = stepZ;
                step = (step + 1) % j4;
                pathWaypoint[stepX - 1][stepZ] = 2;
                pathDistance[stepX - 1][stepZ] = l4;
            }
            if (stepX < byte0 - 1 && pathWaypoint[stepX + 1][stepZ] == 0 && (flags[stepX + 1][stepZ] & 0x280180) == 0) {
                waypointX[step] = stepX + 1;
                waypointY[step] = stepZ;
                step = (step + 1) % j4;
                pathWaypoint[stepX + 1][stepZ] = 8;
                pathDistance[stepX + 1][stepZ] = l4;
            }
            if (stepZ > 0 && pathWaypoint[stepX][stepZ - 1] == 0 && (flags[stepX][stepZ - 1] & 0x280102) == 0) {
                waypointX[step] = stepX;
                waypointY[step] = stepZ - 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX][stepZ - 1] = 1;
                pathDistance[stepX][stepZ - 1] = l4;
            }
            if (stepZ < byte1 - 1 && pathWaypoint[stepX][stepZ + 1] == 0 && (flags[stepX][stepZ + 1] & 0x280120) == 0) {
                waypointX[step] = stepX;
                waypointY[step] = stepZ + 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX][stepZ + 1] = 4;
                pathDistance[stepX][stepZ + 1] = l4;
            }
            if (stepX > 0 && stepZ > 0 && pathWaypoint[stepX - 1][stepZ - 1] == 0 && (flags[stepX - 1][stepZ - 1] & 0x28010e) == 0
                && (flags[stepX - 1][stepZ] & 0x280108) == 0 && (flags[stepX][stepZ - 1] & 0x280102) == 0) {
                waypointX[step] = stepX - 1;
                waypointY[step] = stepZ - 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX - 1][stepZ - 1] = 3;
                pathDistance[stepX - 1][stepZ - 1] = l4;
            }
            if (stepX < byte0 - 1 && stepZ > 0 && pathWaypoint[stepX + 1][stepZ - 1] == 0
                && (flags[stepX + 1][stepZ - 1] & 0x280183) == 0 && (flags[stepX + 1][stepZ] & 0x280180) == 0
                && (flags[stepX][stepZ - 1] & 0x280102) == 0) {
                waypointX[step] = stepX + 1;
                waypointY[step] = stepZ - 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX + 1][stepZ - 1] = 9;
                pathDistance[stepX + 1][stepZ - 1] = l4;
            }
            if (stepX > 0 && stepZ < byte1 - 1 && pathWaypoint[stepX - 1][stepZ + 1] == 0
                && (flags[stepX - 1][stepZ + 1] & 0x280138) == 0 && (flags[stepX - 1][stepZ] & 0x280108) == 0
                && (flags[stepX][stepZ + 1] & 0x280120) == 0) {
                waypointX[step] = stepX - 1;
                waypointY[step] = stepZ + 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX - 1][stepZ + 1] = 6;
                pathDistance[stepX - 1][stepZ + 1] = l4;
            }
            if (stepX < byte0 - 1 && stepZ < byte1 - 1 && pathWaypoint[stepX + 1][stepZ + 1] == 0
                && (flags[stepX + 1][stepZ + 1] & 0x2801e0) == 0 && (flags[stepX + 1][stepZ] & 0x280180) == 0
                && (flags[stepX][stepZ + 1] & 0x280120) == 0) {
                waypointX[step] = stepX + 1;
                waypointY[step] = stepZ + 1;
                step = (step + 1) % j4;
                pathWaypoint[stepX + 1][stepZ + 1] = 12;
                pathDistance[stepX + 1][stepZ + 1] = l4;
            }
        }

        clickedMinimap = 0;
        if (!reached) {
            if (flag) {
                int i5 = 100;
                for (int k5 = 1; k5 < 2; k5++) {
                    for (int i6 = k - k5; i6 <= k + k5; i6++) {
                        for (int k6 = l1 - k5; k6 <= l1 + k5; k6++) {
                            if (i6 >= 0 && k6 >= 0 && i6 < 104 && k6 < 104 && pathDistance[i6][k6] < i5) {
                                i5 = pathDistance[i6][k6];
                                stepX = i6;
                                stepZ = k6;
                                clickedMinimap = 1;
                                reached = true;
                            }
                        }
                    }

                    if (reached) {
                        break;
                    }
                }
            }
            if (!reached) {
                return false;
            }
        }

        current = 0;
        waypointX[current] = stepX;
        waypointY[current++] = stepZ;

        int l5;
        for (int j5 = l5 = pathWaypoint[stepX][stepZ]; stepX != x || stepZ != z; j5 = pathWaypoint[stepX][stepZ]) {
            if (j5 != l5) {
                l5 = j5;
                waypointX[current] = stepX;
                waypointY[current++] = stepZ;
            }
            if ((j5 & 2) != 0)
                stepX++;
            else if ((j5 & 8) != 0)
                stepX--;
            if ((j5 & 1) != 0)
                stepZ++;
            else if ((j5 & 4) != 0)
                stepZ--;
        }

        if (current > 0) {
            int steps = current;
            if (steps > 25) {
                steps = 25;
            }

            current--;
            int startX = waypointX[current];
            int startZ = waypointY[current];
            if (type == 0) {
                outBuffer.p1isaac(Packet.Client.MOVE_GAMECLICK);
                outBuffer.p1(steps * 2 + 3);
            } else if (type == 1) {
                outBuffer.p1isaac(Packet.Client.MOVE_MINIMAPCLICK);
                outBuffer.p1(steps * 2 + 3 + 14);
            } else if (type == 2) {
                outBuffer.p1isaac(Packet.Client.MOVEMENT_WORLD_ACTION);
                outBuffer.p1(steps * 2 + 3);
            }

            // control held down
            outBuffer.p1(super.keyDown[Applet.KEY_CONTROL] == 1 ? 1 : 0);

            // steps are relative to these starting values
            outBuffer.p2(startX + baseTileX);
            outBuffer.p2(startZ + baseTileZ);

            flagTileX = waypointX[0];
            flagTileY = waypointY[0];

            for (int n = 1; n < steps; n++) {
                current--;
                outBuffer.p1(waypointX[current] - startX);
                outBuffer.p1(waypointY[current] - startZ);
            }

            return true;
        }

        return type != 1;
    }

    public void updatePlayers(Buffer buffer, int size) {
        deadEntityCount = 0;
        updateCount = 0;

        updateLocalPlayer(buffer);
        updateOtherPlayers(buffer);
        updateNewPlayers(size, buffer);
        updatePlayerMasks(buffer);

        for (int n = 0; n < deadEntityCount; n++) {
            int l = deadEntityIndices[n];
            if (playerEntities[l].removeTimer != clientClock) {
                playerEntities[l] = null;
            }
        }

        if (buffer.offset != size) {
            Signlink.reporterror("Error packet size mismatch in getplayer pos:" + buffer.offset + " psize:" + size);
            throw new RuntimeException("eek");
        }

        for (int n = 0; n < playerCount; n++) {
            if (playerEntities[playerIndices[n]] == null) {
                Signlink.reporterror(username + " null entry in pl list - pos:" + n + " size:" + playerCount);
                throw new RuntimeException("eek");
            }
        }
    }

    public boolean animateInterface(int id, int sceneDelta) {
        boolean animated = false;

        if (Component.instances.length < id) {
            return false;
        }

        Component parent = Component.instances[id];
        if (parent.children == null) {
            return false;
        }

        for (int l = 0; l < parent.children.length; l++) {
            if (parent.children[l] == -1) {
                break;
            }

            Component child = Component.instances[parent.children[l]];
            if (child.type == 1) {
                animated |= animateInterface(child.id, sceneDelta);
            }
            if (child.type == 6 && (child.seqId != -1 || child.activeSeqId != -1)) {
                boolean enabled = isInterfaceEnabled(child);
                int seqId;
                if (enabled) {
                    seqId = child.activeSeqId;
                } else {
                    seqId = child.seqId;
                }
                if (seqId != -1) {
                    SeqType seqType = SeqType.instances[seqId];
                    for (child.seqCycle += sceneDelta; child.seqCycle > seqType.frameDelay[child.seqFrame]; ) {
                        child.seqCycle -= seqType.frameDelay[child.seqFrame] + 1;
                        child.seqFrame++;
                        if (child.seqFrame >= seqType.frameCount) {
                            child.seqFrame -= seqType.delay;
                            if (child.seqFrame < 0 || child.seqFrame >= seqType.frameCount) {
                                child.seqFrame = 0;
                            }
                        }
                        animated = true;
                    }
                }
            }
        }

        return animated;
    }

    public void addMessage(int type, String message, String prefix) {
        if (type == 0 && stickyChatbackComponentId != -1) {
            chatbackMessage = message;
            super.mouseButton = 0;
        }

        if (chatbackComponentId == -1) {
            redrawChatback = true;
        }

        for (int j = 99; j > 0; j--) {
            chatMessageType[j] = chatMessageType[j - 1];
            chatMessagePrefix[j] = chatMessagePrefix[j - 1];
            chatMessage[j] = chatMessage[j - 1];
        }

        chatMessageType[0] = type;
        chatMessagePrefix[0] = prefix;
        chatMessage[0] = message;
    }

    public void removeFriend(long l) {
        if (l == 0L)
            return;
        for (int k = 0; k < friendCount; k++)
            if (friendName37[k] == l) {
                friendCount--;
                sidebarRedraw = true;
                for (int i1 = k; i1 < friendCount; i1++) {
                    friendName[i1] = friendName[i1 + 1];
                    friendWorld[i1] = friendWorld[i1 + 1];
                    friendName37[i1] = friendName37[i1 + 1];
                }

                outBuffer.p1isaac(Packet.Client.FRIENDLIST_DEL);
                outBuffer.p8(l);
                return;
            }
    }

    public boolean isInterfaceEnabled(Component component) {
        if (component.scriptCompareType == null)
            return false;
        for (int j = 0; j < component.scriptCompareType.length; j++) {
            int k = executeInterface(component, j);
            int l = component.scriptCompareValue[j];
            if (component.scriptCompareType[j] == 2) {
                if (k >= l)
                    return false;
            } else if (component.scriptCompareType[j] == 3) {
                if (k <= l)
                    return false;
            } else if (component.scriptCompareType[j] == 4) {
                if (k == l)
                    return false;
            } else if (k != l)
                return false;
        }

        return true;
    }

    public void updateMinimapInput() {
        if (super.mouseButton == 1) {
            int i = super.clickX - 21 - 561;
            int j = super.clickY - 9 - 5;
            if (i >= 0 && j >= 0 && i < 146 && j < 151) {
                i -= 73;
                j -= 75;
                int k = cameraYaw + minimapAnticheatAngle & 0x7ff;
                int l = Draw3D.sin[k];
                int i1 = Draw3D.cos[k];
                l = l * (minimapZoom + 256) >> 8;
                i1 = i1 * (minimapZoom + 256) >> 8;
                int j1 = j * l + i * i1 >> 11;
                int k1 = j * i1 - i * l >> 11;
                int l1 = self.x + j1 >> 7;
                int i2 = self.z - k1 >> 7;
                boolean flag = moveTo(self.pathTileX[0], 0, true,
                    l1, self.pathTileZ[0], 1, 0, i2, 0, 0,
                    0);
                if (flag) {
                    outBuffer.p1(i);
                    outBuffer.p1(j);
                    outBuffer.p2(cameraYaw);
                    outBuffer.p1(57);
                    outBuffer.p1(minimapAnticheatAngle);
                    outBuffer.p1(minimapZoom);
                    outBuffer.p1(89);
                    outBuffer.p2(self.x);
                    outBuffer.p2(self.z);
                    outBuffer.p1(clickedMinimap);
                    outBuffer.p1(63);
                }
            }
        }
    }

    public void updateMiniMenu() {
        if (objDragArea != 0)
            return;
        int button = super.mouseButton;
        if (selectedSpell == 1 && super.clickX >= 520 && super.clickY >= 165 && super.clickX <= 788
            && super.clickY <= 230)
            button = 0;
        if (menuVisible) {
            if (button != 1) {
                int j = super.mouseX;
                int i1 = super.mouseY;
                if (mouseArea == 0) {
                    j -= 8;
                    i1 -= 11;
                }
                if (mouseArea == 1) {
                    j -= 562;
                    i1 -= 231;
                }
                if (mouseArea == 2) {
                    j -= 22;
                    i1 -= 375;
                }
                if (j < menuX - 10 || j > menuX + menuWidth + 10 || i1 < menuY - 10
                    || i1 > menuY + menuHeight + 10) {
                    menuVisible = false;
                    if (mouseArea == 1)
                        sidebarRedraw = true;
                    if (mouseArea == 2)
                        redrawChatback = true;
                }
            }
            if (button == 1) {
                int k = menuX;
                int j1 = menuY;
                int l1 = menuWidth;
                int j2 = super.clickX;
                int k2 = super.clickY;
                if (mouseArea == 0) {
                    j2 -= 8;
                    k2 -= 11;
                }
                if (mouseArea == 1) {
                    j2 -= 562;
                    k2 -= 231;
                }
                if (mouseArea == 2) {
                    j2 -= 22;
                    k2 -= 375;
                }
                int l2 = -1;
                for (int i3 = 0; i3 < optionCount; i3++) {
                    int j3 = j1 + 31 + (optionCount - 1 - i3) * 15;
                    if (j2 > k && j2 < k + l1 && k2 > j3 - 13 && k2 < j3 + 3)
                        l2 = i3;
                }

                if (l2 != -1)
                    useMenuOption(l2);
                menuVisible = false;
                if (mouseArea == 1)
                    sidebarRedraw = true;
                if (mouseArea == 2) {
                    redrawChatback = true;
                }
            }
        } else {
            if (button == 1 && optionCount > 0) {
                int l = actions[optionCount - 1];
                if (l == 602 || l == 596 || l == 22 || l == 892 || l == 415 || l == 405 || l == 38 || l == 422
                    || l == 478 || l == 347 || l == 188) {
                    int k1 = paramA[optionCount - 1];
                    int i2 = paramB[optionCount - 1];
                    Component component = Component.instances[i2];
                    if (component.inventoryDummy) {
                        objGrabThreshold = false;
                        objDragCycles = 0;
                        objDragComponentId = i2;
                        objDragSlot = k1;
                        objDragArea = 2;
                        objGrabX = super.clickX;
                        objGrabY = super.clickY;
                        if (Component.instances[i2].parent == viewportInterfaceIndex)
                            objDragArea = 1;
                        if (Component.instances[i2].parent == chatbackComponentId)
                            objDragArea = 3;
                        return;
                    }
                }
            }
            if (button == 1 && (this.button == 1 || isFriend(optionCount - 1)) && optionCount > 2)
                button = 2;
            if (button == 1 && optionCount > 0)
                useMenuOption(optionCount - 1);
            if (button == 2 && optionCount > 0)
                showContextMenu();
        }
    }

    public void calculateCameraPos() {
        int i = cameraLocalX * 128 + 64;
        int j = cameraLocalZ * 128 + 64;
        int k = getLandY(currentLevel, cameraLocalX, cameraLocalZ) - cameraHeightOffset;
        if (cameraX < i) {
            cameraX += cameraSpinSpeed + ((i - cameraX) * cameraSpinMultiplier) / 1000;
            if (cameraX > i) {
                cameraX = i;
            }
        }
        if (cameraX > i) {
            cameraX -= cameraSpinSpeed + ((cameraX - i) * cameraSpinMultiplier) / 1000;
            if (cameraX < i) {
                cameraX = i;
            }
        }
        if (cameraY < k) {
            cameraY += cameraSpinSpeed + ((k - cameraY) * cameraSpinMultiplier) / 1000;
            if (cameraY > k) {
                cameraY = k;
            }
        }
        if (cameraY > k) {
            cameraY -= cameraSpinSpeed + ((cameraY - k) * cameraSpinMultiplier) / 1000;
            if (cameraY < k) {
                cameraY = k;
            }
        }
        if (cameraZ < j) {
            cameraZ += cameraSpinSpeed + ((j - cameraZ) * cameraSpinMultiplier) / 1000;
            if (cameraZ > j) {
                cameraZ = j;
            }
        }
        if (cameraZ > j) {
            cameraZ -= cameraSpinSpeed + ((cameraZ - j) * cameraSpinMultiplier) / 1000;
            if (cameraZ < j) {
                cameraZ = j;
            }
        }
        i = cutsceneLocalX * 128 + 64;
        j = cutsceneLocalY * 128 + 64;
        k = getLandY(currentLevel, cutsceneLocalX, cutsceneLocalY) - cutsceneHeightOffset;
        int l = i - cameraX;
        int i1 = k - cameraY;
        int j1 = j - cameraZ;
        int k1 = (int) Math.sqrt(l * l + j1 * j1);
        int l1 = (int) (Math.atan2(i1, k1) * 325.949D) & 0x7ff;
        int i2 = (int) (Math.atan2(l, j1) * -325.949D) & 0x7ff;
        if (l1 < 128)
            l1 = 128;
        if (l1 > 383)
            l1 = 383;
        if (cameraPitch < l1) {
            cameraPitch += cutsceneSpinSpeed + ((l1 - cameraPitch) * cutsceneSpinMultiplier) / 1000;
            if (cameraPitch > l1)
                cameraPitch = l1;
        }
        if (cameraPitch > l1) {
            cameraPitch -= cutsceneSpinSpeed + ((cameraPitch - l1) * cutsceneSpinMultiplier) / 1000;
            if (cameraPitch < l1)
                cameraPitch = l1;
        }
        int j2 = i2 - cameraOrbitYaw;
        if (j2 > 1024)
            j2 -= 2048;
        if (j2 < -1024)
            j2 += 2048;
        if (j2 > 0) {
            cameraOrbitYaw += cutsceneSpinSpeed + (j2 * cutsceneSpinMultiplier) / 1000;
            cameraOrbitYaw &= 0x7ff;
        }
        if (j2 < 0) {
            cameraOrbitYaw -= cutsceneSpinSpeed + (-j2 * cutsceneSpinMultiplier) / 1000;
            cameraOrbitYaw &= 0x7ff;
        }
        int k2 = i2 - cameraOrbitYaw;
        if (k2 > 1024)
            k2 -= 2048;
        if (k2 < -1024)
            k2 += 2048;
        if (k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
            cameraOrbitYaw = i2;
    }

    public void updateSidebarTabInput() {
        if (super.mouseButton == 1) {
            if (super.clickX >= 549 && super.clickX <= 583 && super.clickY >= 195 && super.clickY < 231
                && tabComponentId[0] != -1) {
                sidebarRedraw = true;
                selectedTab = 0;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 579 && super.clickX <= 609 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[1] != -1) {
                sidebarRedraw = true;
                selectedTab = 1;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 607 && super.clickX <= 637 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[2] != -1) {
                sidebarRedraw = true;
                selectedTab = 2;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 635 && super.clickX <= 679 && super.clickY >= 194 && super.clickY < 229
                && tabComponentId[3] != -1) {
                sidebarRedraw = true;
                selectedTab = 3;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 676 && super.clickX <= 706 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[4] != -1) {
                sidebarRedraw = true;
                selectedTab = 4;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 704 && super.clickX <= 734 && super.clickY >= 194 && super.clickY < 231
                && tabComponentId[5] != -1) {
                sidebarRedraw = true;
                selectedTab = 5;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 732 && super.clickX <= 766 && super.clickY >= 195 && super.clickY < 231
                && tabComponentId[6] != -1) {
                sidebarRedraw = true;
                selectedTab = 6;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 550 && super.clickX <= 584 && super.clickY >= 492 && super.clickY < 528
                && tabComponentId[7] != -1) {
                sidebarRedraw = true;
                selectedTab = 7;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 582 && super.clickX <= 612 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[8] != -1) {
                sidebarRedraw = true;
                selectedTab = 8;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 609 && super.clickX <= 639 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[9] != -1) {
                sidebarRedraw = true;
                selectedTab = 9;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 637 && super.clickX <= 681 && super.clickY >= 493 && super.clickY < 528
                && tabComponentId[10] != -1) {
                sidebarRedraw = true;
                selectedTab = 10;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 679 && super.clickX <= 709 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[11] != -1) {
                sidebarRedraw = true;
                selectedTab = 11;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 706 && super.clickX <= 736 && super.clickY >= 492 && super.clickY < 529
                && tabComponentId[12] != -1) {
                sidebarRedraw = true;
                selectedTab = 12;
                sidebarRedrawIcons = true;
            }
            if (super.clickX >= 734 && super.clickX <= 768 && super.clickY >= 492 && super.clickY < 528
                && tabComponentId[13] != -1) {
                sidebarRedraw = true;
                selectedTab = 13;
                sidebarRedrawIcons = true;
            }

            sidebarClickedCounter++;
            if (sidebarClickedCounter > 150) {
                sidebarClickedCounter = 0;
                outBuffer.p1isaac(Packet.Client.ANTICHEAT_SIDEBAR_CLICKED);
                outBuffer.p1(43);
            }
        }
    }

    public boolean updateInterfaceTooltip(Component component) {
        int i = component.contentType;
        if (i >= 1 && i <= 200) {
            if (i >= 101)
                i -= 101;
            else
                i--;
            options[optionCount] = "Remove @whi@" + friendName[i];
            actions[optionCount] = 557;
            optionCount++;
            options[optionCount] = "Message @whi@" + friendName[i];
            actions[optionCount] = 679;
            optionCount++;
            return true;
        }
        if (i >= 401 && i <= 500) {
            options[optionCount] = "Remove @whi@" + component.text;
            actions[optionCount] = 556;
            optionCount++;
            return true;
        } else {
            return false;
        }
    }

    public void updateNpcList(Buffer buffer) {
        buffer.accessBits();

        int count = buffer.gBit(8);
        if (count < npcCount) {
            for (int n = count; n < npcCount; n++) {
                deadEntityIndices[deadEntityCount++] = npcIndices[n];
            }
        }

        if (count > npcCount) {
            Signlink.reporterror(username + " Too many npcs");
            throw new RuntimeException("eek");
        }

        npcCount = 0;
        for (int n = 0; n < count; n++) {
            int nid = npcIndices[n];
            NPCEntity npc = npcEntities[nid];

            int hasUpdate = buffer.gBit(1);
            if (hasUpdate == 0) {
                npcIndices[npcCount++] = nid;
                npc.removeTimer = clientClock;
            } else {
                int type = buffer.gBit(2);
                if (type == 0) {
                    npcIndices[npcCount++] = nid;
                    npc.removeTimer = clientClock;
                    entityUpdateIndices[updateCount++] = nid;
                } else if (type == 1) {
                    npcIndices[npcCount++] = nid;
                    npc.removeTimer = clientClock;
                    int dir1 = buffer.gBit(3);
                    npc.walk(false, dir1);
                    int maskUpdate = buffer.gBit(1);
                    if (maskUpdate == 1) {
                        entityUpdateIndices[updateCount++] = nid;
                    }
                } else if (type == 2) {
                    npcIndices[npcCount++] = nid;
                    npc.removeTimer = clientClock;
                    int dir1 = buffer.gBit(3);
                    npc.walk(true, dir1);
                    int dir2 = buffer.gBit(3);
                    npc.walk(true, dir2);
                    int makeUpdate = buffer.gBit(1);
                    if (makeUpdate == 1) {
                        entityUpdateIndices[updateCount++] = nid;
                    }
                } else if (type == 3) {
                    deadEntityIndices[deadEntityCount++] = nid;
                }
            }
        }
    }

    public String getParameter(String s) {
        if (Signlink.mainapp != null) {
            return Signlink.mainapp.getParameter(s);
        } else {
            return super.getParameter(s);
        }
    }

    public void reconnect() {
        if (idleTimeout > 0) {
            disconnect();
            return;
        }
        areaViewport.bind();
        fontPlain12.drawCentered(144, 0, "Connection lost", 257);
        fontPlain12.drawCentered(143, 0xffffff, "Connection lost", 256);
        fontPlain12.drawCentered(159, 0, "Please wait - attempting to reestablish", 257);
        fontPlain12.drawCentered(158, 0xffffff, "Please wait - attempting to reestablish", 256);
        areaViewport.drawImage(11, super.graphics, 8);
        flagTileX = 0;
        BufferedStream bufferedStream = this.stream;
        ingame = false;
        login(username, password, true);
        if (!ingame)
            disconnect();
        try {
            bufferedStream.close();
        } catch (Exception ignored) {
        }
    }

    public void updateFlameDissolve(IndexedSprite indexedSprite) {
        int j = 256;
        Arrays.fill(flameBuffer1, 0);

        for (int l = 0; l < 5000; l++) {
            int i1 = (int) (Math.random() * 128D * (double) j);
            flameBuffer1[i1] = (int) (Math.random() * 256D);
        }

        for (int j1 = 0; j1 < 20; j1++) {
            for (int k1 = 1; k1 < j - 1; k1++) {
                for (int i2 = 1; i2 < 127; i2++) {
                    int k2 = i2 + (k1 << 7);
                    flameBuffer2[k2] = (flameBuffer1[k2 - 1] + flameBuffer1[k2 + 1] + flameBuffer1[k2 - 128] + flameBuffer1[k2 + 128]) / 4;
                }
            }

            int[] ai = flameBuffer1;
            flameBuffer1 = flameBuffer2;
            flameBuffer2 = ai;
        }

        if (indexedSprite != null) {
            int l1 = 0;
            for (int j2 = 0; j2 < indexedSprite.height; j2++) {
                for (int l2 = 0; l2 < indexedSprite.width; l2++)
                    if (indexedSprite.pixels[l1++] != 0) {
                        int i3 = l2 + 16 + indexedSprite.clipX;
                        int j3 = j2 + 16 + indexedSprite.clipY;
                        int k3 = i3 + (j3 << 7);
                        flameBuffer1[k3] = 0;
                    }
            }
        }
    }

    public void updateObjectStack(int x, int z) {
        LinkedList stacks = objects[currentLevel][x][z];
        if (stacks == null) {
            mapSquare.removeObject(currentLevel, x, z);
            return;
        }
        int k = -99999999;

        ObjStackEntity obj = null;
        for (ObjStackEntity entity = (ObjStackEntity) stacks.peekLast(); entity != null; entity = (ObjStackEntity) stacks.getPrevious()) {
            ObjType objType = ObjType.get(entity.model);
            int i1 = objType.value;
            if (objType.stackable)
                i1 *= entity.amount + 1;
            if (i1 > k) {
                k = i1;
                obj = entity;
            }
        }

        stacks.pushLast(obj);
        int l = -1;
        int j1 = -1;
        int k1 = 0;
        int l1 = 0;
        for (ObjStackEntity entity = (ObjStackEntity) stacks.peekLast(); entity != null; entity = (ObjStackEntity) stacks.getPrevious()) {
            if (entity.model != obj.model && l == -1) {
                l = entity.model;
                k1 = entity.amount;
            }
            if (entity.model != obj.model && entity.model != l && j1 == -1) {
                j1 = entity.model;
                l1 = entity.amount;
            }
        }

        Model m1 = null;
        if (l != -1)
            m1 = ObjType.get(l).getModel(k1);
        Model m2 = null;
        if (j1 != -1)
            m2 = ObjType.get(j1).getModel(l1);
        int i2 = x + (z << 7) + 0x60000000;
        ObjType objType = ObjType.get(obj.model);
        mapSquare.addObject(objType.getModel(obj.amount), m1, getLandY(currentLevel, x * 128 + 64, z * 128 + 64), currentLevel, i2, z, x, m2);
    }

    public void createScene() {
        try {
            lastSceneLevel = -1;
            temporaryLocs.clear();
            locs.clear();
            spotanims.clear();
            projectiles.clear();
            Draw3D.clearPools();
            clearCaches();
            mapSquare.reset();
            for (int j = 0; j < 4; j++)
                collisionMaps[j].reset();

            System.gc();
            SceneManager sceneManager = new SceneManager(104, levelRenderFlags, 104, levelHeightMaps);
            byte[] dest = new byte[1000000];

            int mapCount = sceneMapLandData.length;
            SceneManager.lowMemory = MapSquare.lowMemory;
            for (int n = 0; n < mapCount; n++) {
                int x = sceneMapIndex[n] >> 8;
                int y = sceneMapIndex[n] & 0xff;
                if (x == 33 && y >= 71 && y <= 73) {
                    SceneManager.lowMemory = false;
                    break;
                }
            }

            if (SceneManager.lowMemory) {
                mapSquare.setup(currentLevel);
            } else {
                mapSquare.setup(0);
            }

            outBuffer.p1isaac(Packet.Client.KEEPALIVE);
            for (int n = 0; n < mapCount; n++) {
                int relX = (sceneMapIndex[n] >> 8) * 64 - baseTileX;
                int relZ = (sceneMapIndex[n] & 0xff) * 64 - baseTileZ;
                byte[] data = sceneMapLandData[n];
                if (data != null) {
                    int uncompressedLength = (new Buffer(data)).g4();
                    BZip2InputStream.read(dest, uncompressedLength, data, data.length - 4, 4);
                    sceneManager.readLandscape(dest, (centerSectorX - 6) * 8, relZ, relX, (centerSectorY - 6) * 8);
                } else if (centerSectorY < 800) {
                    sceneManager.clearLandscape(relX, relZ, 64, 64);
                }
            }

            outBuffer.p1isaac(Packet.Client.KEEPALIVE);
            for (int n = 0; n < mapCount; n++) {
                byte[] data = sceneMapLocData[n];
                if (data != null) {
                    int uncompressedLength = (new Buffer(data)).g4();
                    BZip2InputStream.read(dest, uncompressedLength, data, data.length - 4, 4);
                    int relX = (sceneMapIndex[n] >> 8) * 64 - baseTileX;
                    int relZ = (sceneMapIndex[n] & 0xff) * 64 - baseTileZ;
                    sceneManager.readLocs(dest, mapSquare, collisionMaps, locs, relZ, relX);
                }
            }

            outBuffer.p1isaac(Packet.Client.KEEPALIVE);
            sceneManager.buildLandscape(mapSquare, collisionMaps);
            areaViewport.bind();
            outBuffer.p1isaac(Packet.Client.KEEPALIVE);
            for (LocEntity locEntity = (LocEntity) locs.peekLast(); locEntity != null; locEntity = (LocEntity) locs.getPrevious()) {
                if ((levelRenderFlags[1][locEntity.tileX][locEntity.tileZ] & 2) == 2) {
                    locEntity.level--;
                    if (locEntity.level < 0) {
                        locEntity.unlink();
                    }
                }
            }

            for (int x = 0; x < 104; x++) {
                for (int y = 0; y < 104; y++) {
                    updateObjectStack(x, y);
                }
            }

            for (SpawnedLoc spawnedLoc = (SpawnedLoc) spawnedLocations.peekLast(); spawnedLoc != null; spawnedLoc = (SpawnedLoc) spawnedLocations.getPrevious()) {
                addLoc(spawnedLoc.rotation, spawnedLoc.tileX, spawnedLoc.tileZ, spawnedLoc.classType, spawnedLoc.locIndex, spawnedLoc.type, spawnedLoc.level);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        LocType.models.clear();
        System.gc();
        Draw3D.setupPools(20);
    }

    public void update() {
        if (errorStarted || errorLoading || errorHost) {
            return;
        }

        clientClock++;
        if (!ingame) {
            updateTitle();
        } else {
            updateGame();
        }
    }

    public void updateEntityVoices() {
        for (int i = -1; i < playerCount; i++) {
            int j;
            if (i == -1) {
                j = LOCAL_PLAYER_INDEX;
            } else {
                j = playerIndices[i];
            }
            PlayerEntity playerEntity = playerEntities[j];
            if (playerEntity != null && playerEntity.textCycle > 0) {
                playerEntity.textCycle--;
                if (playerEntity.textCycle == 0) {
                    playerEntity.spoken = null;
                }
            }
        }

        for (int k = 0; k < npcCount; k++) {
            int l = npcIndices[k];
            NPCEntity npcEntity = npcEntities[l];
            if (npcEntity != null && npcEntity.textCycle > 0) {
                npcEntity.textCycle--;
                if (npcEntity.textCycle == 0) {
                    npcEntity.spoken = null;
                }
            }
        }
    }

    public int executeInterface(Component inter, int script) {
        if (inter.script == null || script >= inter.script.length) {
            return -2;
        }
        try {
            int[] code = inter.script[script];
            int a = 0;
            int position = 0;

            do {
                int opcode;
                do {
                    opcode = code[position++];
                    if (opcode == OPCODE_RETURN) {
                        return a;
                    }

                    if (opcode == OPCODE_SKILL_LEVEL_REAL) {
                        a += skillLevelReal[code[position++]];
                    } else if (opcode == OPCODE_SKILL_LEVEL) {
                        a += skillLevel[code[position++]];
                    } else if (opcode == OPCODE_SKILL_LEVEL_EXPERIENCE) {
                        a += skillExperience[code[position++]];
                    } else if (opcode == OPCODE_INVENTORY_ITEM_AMOUNT) {
                        Component c = Component.instances[code[position++]];
                        int itemId = code[position++] + 1;
                        for (int n = 0; n < c.inventoryIndices.length; n++) {
                            if (c.inventoryIndices[n] == itemId) {
                                a += c.inventoryAmount[n];
                            }
                        }
                    } else if (opcode == OPCODE_VARIABLE) {
                        a += variables[code[position++]];
                    } else if (opcode == OPCODE_SKILL_EXPERIENCE) {
                        a += EXPERIENCE_TABLE[skillLevel[code[position++]] - 1];
                    } else if (opcode == OPCODE_VARIABLE_MUL) {
                        a += (variables[code[position++]] * 100) / 46875;
                    } else if (opcode == OPCODE_COMBAT_LEVEL) {
                        a += self.combatLevel;
                    } else if (opcode == OPCODE_TOTAL_LEVEL) {
                        for (int n = 0; n < 19; n++) {
                            if (n == 18) {
                                n = 20;
                            }
                            a += skillLevel[n];
                        }
                    } else if (opcode == OPCODE_INVENTORY_CAPACITY) {
                        Component c = Component.instances[code[position++]];
                        int itemId = code[position++] + 1;
                        for (int n = 0; n < c.inventoryIndices.length; n++) {
                            if (c.inventoryIndices[n] != itemId) {
                                continue;
                            }
                            a += 999999999;
                            break;
                        }
                    } else if (opcode == OPCODE_ENERGY) {
                        a += energy;
                    } else if (opcode == OPCODE_WEIGHT) {
                        a += weightCarried;
                    }
                } while (opcode != 13);
                int j1 = variables[code[position++]];
                int i2 = code[position++];
                a += (j1 & 1 << i2) == 0 ? 0 : 1;
            } while (true);
        } catch (Exception _ex) {
            return -1;
        }
    }

    public void drawErrorScreen() {
        Graphics g = getBaseComponent().getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 789, 532);

        setLoopRate(1);

        if (errorLoading) {
            flameActive = false;
            g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 16));
            g.setColor(Color.yellow);
            int i = 35;
            g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, i);
            i += 50;
            g.setColor(Color.white);
            g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, i);
            i += 30;
            g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, i);
            i += 30;
            g.drawString("3: Try using a different game-world", 30, i);
            i += 30;
            g.drawString("4: Try rebooting your computer", 30, i);
            i += 30;
            g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, i);
        }

        if (errorHost) {
            flameActive = false;
            g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("Error - unable to load game!", 50, 50);
            g.drawString("To play RuneScape make sure you play from", 50, 100);
            g.drawString("http://www.runescape.com", 50, 150);
        }

        if (errorStarted) {
            flameActive = false;
            g.setColor(Color.yellow);
            int j = 35;
            g.drawString("Error a copy of RuneScape already appears to be loaded", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.drawString("To fix this try the following (in order):", 30, j);
            j += 50;
            g.setColor(Color.white);
            g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
            g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, j);
            j += 30;
            g.drawString("2: Try rebooting your computer, and reloading", 30, j);
        }
    }

    public void loadTitleBackground() {
        byte[] src = titleArchive.read("title.dat", null);
        Sprite temp = new Sprite(src, this);
        titleLeft.bind();
        temp.drawOpaque(0, 0);

        titleRight.bind();
        temp.drawOpaque(-661, 0);

        titleTop.bind();
        temp.drawOpaque(-128, 0);

        titleBottom.bind();
        temp.drawOpaque(-214, -386);

        titleCenter.bind();
        temp.drawOpaque(-214, -186);

        titleBottomLeft.bind();
        temp.drawOpaque(0, -265);

        titleBottomRight.bind();
        temp.drawOpaque(-574, -265);

        titleLeftSpace.bind();
        temp.drawOpaque(-128, -186);

        titleRightSpace.bind();
        temp.drawOpaque(-574, -186);

        int[] tmp = new int[temp.width];
        for (int y = 0; y < temp.height; y++) {
            for (int x = 0; x < temp.width; x++) {
                tmp[x] = temp.pixels[(temp.width - x - 1) + temp.width * y];
            }
            System.arraycopy(tmp, 0, temp.pixels, temp.width * y, temp.width);
        }

        titleLeft.bind();
        temp.drawOpaque(394, 0);

        titleRight.bind();
        temp.drawOpaque(-267, 0);

        titleTop.bind();
        temp.drawOpaque(266, 0);

        titleBottom.bind();
        temp.drawOpaque(180, -386);

        titleCenter.bind();
        temp.drawOpaque(180, -186);

        titleBottomLeft.bind();
        temp.drawOpaque(394, -265);

        titleBottomRight.bind();
        temp.drawOpaque(-180, -265);

        titleLeftSpace.bind();
        temp.drawOpaque(212, -186);

        titleRightSpace.bind();
        temp.drawOpaque(-180, -186);

        temp = new Sprite(titleArchive, "logo", 0);
        titleTop.bind();
        temp.draw(18, super.gameWidth / 2 - temp.width / 2 - 128);

        System.gc();
    }

    public void updateSceneSeqLocs() {
        for (LocEntity e = (LocEntity) locs
            .peekLast(); e != null; e = (LocEntity) locs.getPrevious()) {
            boolean append = false;
            e.seqCycle += sceneDelta;
            if (e.seqFrame == -1) {
                e.seqFrame = 0;
                append = true;
            }
            while (e.seqCycle > e.seq.frameDelay[e.seqFrame]) {
                e.seqCycle -= e.seq.frameDelay[e.seqFrame] + 1;
                e.seqFrame++;
                append = true;
                if (e.seqFrame < e.seq.frameCount)
                    continue;
                e.seqFrame -= e.seq.delay;
                if (e.seqFrame >= 0 && e.seqFrame < e.seq.frameCount)
                    continue;
                e.unlink();
                append = false;
                break;
            }
            if (append) {
                int p = e.level;
                int x = e.tileX;
                int z = e.tileZ;
                int bitset = 0;
                if (e.classType == 0)
                    bitset = mapSquare.getWallBitset(p, x, z);
                if (e.classType == 1)
                    bitset = mapSquare.getWallDecorationBitset(p, z, x);
                if (e.classType == 2)
                    bitset = mapSquare.getLocationBitset(p, x, z);
                if (e.classType == 3)
                    bitset = mapSquare.getGroundDecorationBitset(p, x, z);
                if (bitset == 0 || (bitset >> 14 & 0x7fff) != e.locIndex) {
                    e.unlink();
                } else {
                    int j1 = levelHeightMaps[p][x][z];
                    int k1 = levelHeightMaps[p][x + 1][z];
                    int l1 = levelHeightMaps[p][x + 1][z + 1];
                    int i2 = levelHeightMaps[p][x][z + 1];
                    LocType locType = LocType.get(e.locIndex);
                    int j2 = -1;
                    if (e.seqFrame != -1)
                        j2 = e.seq.primaryFrames[e.seqFrame];
                    if (e.classType == 2) {
                        int k2 = mapSquare.getInfo(p, x, z, bitset);
                        int j3 = k2 & 0x1f;
                        int i4 = k2 >> 6;
                        if (j3 == 11)
                            j3 = 10;
                        Model m = locType.getModel(j3, i4, j1, k1, l1, i2, j2);
                        mapSquare.setLocModel(x, m, p, z);
                    } else if (e.classType == 1) {
                        Model m = locType.getModel(4, 0, j1, k1, l1, i2, j2);
                        mapSquare.setWallDecorationModel(z, x, m, p);
                    } else if (e.classType == 0) {
                        int l2 = mapSquare.getInfo(p, x, z, bitset);
                        int k3 = l2 & 0x1f;
                        int j4 = l2 >> 6;
                        if (k3 == 2) {
                            int k4 = j4 + 1 & 3;
                            Model m1 = locType.getModel(2, 4 + j4, j1, k1, l1, i2, j2);
                            Model m2 = locType.getModel(2, k4, j1, k1, l1, i2, j2);
                            mapSquare.setWallModels(m1, m2, z, x, p);
                        } else {
                            Model m = locType.getModel(k3, j4, j1, k1, l1, i2, j2);
                            mapSquare.setWallModel(m, z, x, p);
                        }
                    } else if (e.classType == 3) {
                        int i3 = mapSquare.getInfo(p, x, z, bitset);
                        int l3 = i3 >> 6;
                        Model m = locType.getModel(22, l3, j1, k1, l1, i2, j2);
                        mapSquare.setGroundDecorationModel(m, z, x, p);
                    }
                }
            }
        }

    }

    public void removeIgnore(long l) {
        if (l == 0L)
            return;
        for (int j = 0; j < ignoreCount; j++) {
            if (ignoreName37[j] == l) {
                ignoreCount--;
                sidebarRedraw = true;
                for (int k = j; k < ignoreCount; k++)
                    ignoreName37[k] = ignoreName37[k + 1];

                outBuffer.p1isaac(Packet.Client.IGNORELIST_DEL);
                outBuffer.p8(l);
                return;
            }
        }
    }

    public void updateViewport() {
        if (selectedObject == 0 && selectedSpell == 0) {
            options[optionCount] = "Walk here";
            actions[optionCount] = 660;
            paramA[optionCount] = super.mouseX;
            paramB[optionCount] = super.mouseY;
            optionCount++;
        }

        int i = -1;
        for (int j = 0; j < Model.resourceCount; j++) {
            int k = Model.hoveredBitsets[j];
            int x = k & 0x7f;
            int z = k >> 7 & 0x7f;
            int j1 = k >> 29 & 3;
            int k1 = k >> 14 & 0x7fff;
            if (k != i) {
                i = k;
                if (j1 == 2 && mapSquare.getInfo(currentLevel, x, z, k) >= 0) {
                    LocType locType = LocType.get(k1);
                    if (selectedObject == 1) {
                        options[optionCount] = "Use " + selectedObjName + " with @cya@" + locType.name;
                        actions[optionCount] = 450;
                        paramC[optionCount] = k;
                        paramA[optionCount] = x;
                        paramB[optionCount] = z;
                        optionCount++;
                    } else if (selectedSpell == 1) {
                        if ((selectedFlags & 4) == 4) {
                            options[optionCount] = selectedSpellPrefix + " @cya@" + locType.name;
                            actions[optionCount] = 55;
                            paramC[optionCount] = k;
                            paramA[optionCount] = x;
                            paramB[optionCount] = z;
                            optionCount++;
                        }
                    } else {
                        if (locType.actions != null) {
                            for (int l1 = 4; l1 >= 0; l1--)
                                if (locType.actions[l1] != null) {
                                    options[optionCount] = locType.actions[l1] + " @cya@"
                                        + locType.name;
                                    if (l1 == 0)
                                        actions[optionCount] = 285;
                                    if (l1 == 1)
                                        actions[optionCount] = 504;
                                    if (l1 == 2)
                                        actions[optionCount] = 364;
                                    if (l1 == 3)
                                        actions[optionCount] = 581;
                                    if (l1 == 4)
                                        actions[optionCount] = 1501;
                                    paramC[optionCount] = k;
                                    paramA[optionCount] = x;
                                    paramB[optionCount] = z;
                                    optionCount++;
                                }

                        }
                        options[optionCount] = "Examine @cya@" + locType.name;
                        actions[optionCount] = 1175;
                        paramC[optionCount] = k;
                        paramA[optionCount] = x;
                        paramB[optionCount] = z;
                        optionCount++;
                    }
                }
                if (j1 == 1) {
                    NPCEntity e = npcEntities[k1];
                    if (e.info.size == 1
                        && (e.x & 0x7f) == 64
                        && (e.z & 0x7f) == 64) {
                        for (int i2 = 0; i2 < npcCount; i2++) {
                            try {
                                NPCEntity npc = npcEntities[npcIndices[i2]];
                                if (npc != null && npc != e && npc.info.size == 1 && npc.x == e.x && npc.z == e.z) {
                                    drawTooltip(npc.info, z, x, npcIndices[i2]);
                                }
                            } catch (Exception ignored) {
                            }
                        }

                    }
                    drawTooltip(e.info, z, x, k1);
                }
                if (j1 == 0) {
                    PlayerEntity e = playerEntities[k1];
                    if ((e.x & 0x7f) == 64 && (e.z & 0x7f) == 64) {
                        for (int j2 = 0; j2 < npcCount; j2++) {
                            try {
                                NPCEntity npc = npcEntities[npcIndices[j2]];
                                if (npc != null && npc.info.size == 1 && npc.x == e.x && npc.z == e.z) {
                                    drawTooltip(npc.info, z, x, npcIndices[j2]);
                                }
                            } catch (Exception ignored) {
                            }
                        }

                        for (int k2 = 0; k2 < playerCount; k2++) {
                            PlayerEntity playerEntity_1 = playerEntities[playerIndices[k2]];
                            if (playerEntity_1 != null && playerEntity_1 != e && playerEntity_1.x == e.x && playerEntity_1.z == e.z) {
                                addPlayerOptions(z, playerIndices[k2], playerEntity_1, x);
                            }
                        }

                    }
                    addPlayerOptions(z, k1, e, x);
                }
                if (j1 == 3) {
                    LinkedList stack = objects[currentLevel][x][z];
                    if (stack != null) {
                        for (ObjStackEntity objStackEntity = (ObjStackEntity) stack
                            .peekFirst(); objStackEntity != null; objStackEntity = (ObjStackEntity) stack
                            .getNext()) {
                            ObjType objType = ObjType.get(objStackEntity.model);
                            if (selectedObject == 1) {
                                options[optionCount] = "Use " + selectedObjName + " with @lre@" + objType.name;
                                actions[optionCount] = 217;
                                paramC[optionCount] = objStackEntity.model;
                                paramA[optionCount] = x;
                                paramB[optionCount] = z;
                                optionCount++;
                            } else if (selectedSpell == 1) {
                                if ((selectedFlags & 1) == 1) {
                                    options[optionCount] = selectedSpellPrefix + " @lre@" + objType.name;
                                    actions[optionCount] = 965;
                                    paramC[optionCount] = objStackEntity.model;
                                    paramA[optionCount] = x;
                                    paramB[optionCount] = z;
                                    optionCount++;
                                }
                            } else {
                                for (int l2 = 4; l2 >= 0; l2--)
                                    if (objType.groundOptions != null && objType.groundOptions[l2] != null) {
                                        options[optionCount] = objType.groundOptions[l2] + " @lre@"
                                            + objType.name;
                                        if (l2 == 0)
                                            actions[optionCount] = 224;
                                        if (l2 == 1)
                                            actions[optionCount] = 993;
                                        if (l2 == 2)
                                            actions[optionCount] = 99;
                                        if (l2 == 3)
                                            actions[optionCount] = 746;
                                        if (l2 == 4)
                                            actions[optionCount] = 877;
                                        paramC[optionCount] = objStackEntity.model;
                                        paramA[optionCount] = x;
                                        paramB[optionCount] = z;
                                        optionCount++;
                                    } else if (l2 == 2) {
                                        options[optionCount] = "Take @lre@" + objType.name;
                                        actions[optionCount] = 99;
                                        paramC[optionCount] = objStackEntity.model;
                                        paramA[optionCount] = x;
                                        paramB[optionCount] = z;
                                        optionCount++;
                                    }

                                options[optionCount] = "Examine @lre@" + objType.name;
                                actions[optionCount] = 1102;
                                paramC[optionCount] = objStackEntity.model;
                                paramA[optionCount] = x;
                                paramB[optionCount] = z;
                                optionCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    public void updatePlayers(boolean flag) {
        ingame &= flag;
        for (int i = -1; i < playerCount; i++) {
            int j;
            if (i == -1)
                j = LOCAL_PLAYER_INDEX;
            else
                j = playerIndices[i];
            PlayerEntity playerEntity = playerEntities[j];
            if (playerEntity != null)
                updateEntity(playerEntity);
        }

        updatePlayersCounter++;
        if (updatePlayersCounter > 1406) {
            updatePlayersCounter = 0;
            outBuffer.p1isaac(Packet.Client.ANTICHEAT_UPDATE_PLAYERS);
            outBuffer.p1(0);
            int k = outBuffer.offset;
            outBuffer.p1(162);
            outBuffer.p1(22);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.p1(84);
            outBuffer.p2(31824);
            outBuffer.p2(13490);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.p1(123);
            if ((int) (Math.random() * 2D) == 0)
                outBuffer.p1(134);
            outBuffer.p1(100);
            outBuffer.p1(94);
            outBuffer.p2(35521);
            outBuffer.pSize(outBuffer.offset - k);
        }
    }

    public void drawTileHint() {
        if (hintType != 2) {
            return;
        }

        projectToScreen((hintTileZ - baseTileZ << 7) + hintOffsetZ, (hintTileX - baseTileX << 7) + hintOffsetX, hintHeight * 2);
        if (drawX > -1 && clientClock % 20 < 10) {
            headicons[2].draw(drawY - 28, drawX - 12);
        }
    }

    public void updateLocalPlayer(Buffer buffer) {
        buffer.accessBits();

        int hasUpdate = buffer.gBit(1);
        if (hasUpdate == 0) {
            return;
        }

        int type = buffer.gBit(2);
        if (type == 0) {
            entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
        } else if (type == 1) {
            int walkDir = buffer.gBit(3);
            self.walk(false, walkDir);
            int update = buffer.gBit(1);
            if (update == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (type == 2) {
            int walkDir = buffer.gBit(3);
            self.walk(true, walkDir);
            int runDir = buffer.gBit(3);
            self.walk(true, runDir);
            int update = buffer.gBit(1);
            if (update == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (type == 3) {
            currentLevel = buffer.gBit(2);
            int k1 = buffer.gBit(7);
            int j2 = buffer.gBit(7);
            int l2 = buffer.gBit(1);
            self.move(l2 == 1, k1, j2);
            int update = buffer.gBit(1);
            if (update == 1) {
                entityUpdateIndices[updateCount++] = LOCAL_PLAYER_INDEX;
            }
        }
    }

    public void drawChatback() {
        areaChatback.bind();
        Draw3D.offsets = chatOffsets;
        chatback.draw(0, 0);
        if (showSocialInput) {
            fontBold12.drawCentered(40, 0, socialMessage, 239);
            fontBold12.drawCentered(60, 128, socialInput + "*", 239);
        } else if (chatbackInputType) {
            fontBold12.drawCentered(40, 0, "Enter amount:", 239);
            fontBold12.drawCentered(60, 128, chatbackInput + "*", 239);
        } else if (chatbackMessage != null) {
            fontBold12.drawCentered(40, 0, chatbackMessage, 239);
            fontBold12.drawCentered(60, 128, "Click to continue", 239);
        } else if (chatbackComponentId != -1)
            drawInterface(0, 0, Component.instances[chatbackComponentId], 0);
        else if (stickyChatbackComponentId != -1) {
            drawInterface(0, 0, Component.instances[stickyChatbackComponentId], 0);
        } else {
            Font font = fontPlain12;
            int i = 0;
            Draw2D.setBounds(77, 0, 463, 0);
            for (int j = 0; j < 100; j++)
                if (chatMessage[j] != null) {
                    int k = chatMessageType[j];
                    int l = (70 - i * 14) + chatScrollAmount;
                    if (k == 0) {
                        if (l > 0 && l < 110)
                            font.draw(4, l, 0, chatMessage[j]);
                        i++;
                    }
                    if (k == 1) {
                        if (l > 0 && l < 110) {
                            font.draw(4, l, 0xffffff, chatMessagePrefix[j] + ":");
                            font.draw(
                                12 + font.stringWidth(chatMessagePrefix[j]), l, 255,
                                chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 2 && (chatPublicSetting == 0 || chatPublicSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110) {
                            font.draw(4, l, 0, chatMessagePrefix[j] + ":");
                            font.draw(
                                12 + font.stringWidth(chatMessagePrefix[j]), l, 255,
                                chatMessage[j]);
                        }
                        i++;
                    }
                    if ((k == 3 || k == 7) && splitPrivateChat == 0
                        && (k == 7 || chatPrivateSetting == 0 || chatPrivateSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110) {
                            font.draw(4, l, 0, "From " + chatMessagePrefix[j] + ":");
                            font.draw(
                                12 + font.stringWidth("From " + chatMessagePrefix[j]), l,
                                0x800000, chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 4 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110)
                            font.draw(4, l, 0x800080,
                                chatMessagePrefix[j] + " " + chatMessage[j]);
                        i++;
                    }
                    if (k == 5 && splitPrivateChat == 0 && chatPrivateSetting < 2) {
                        if (l > 0 && l < 110)
                            font.draw(4, l, 0x800000, chatMessage[j]);
                        i++;
                    }
                    if (k == 6 && splitPrivateChat == 0 && chatPrivateSetting < 2) {
                        if (l > 0 && l < 110) {
                            font.draw(4, l, 0, "To " + chatMessagePrefix[j] + ":");
                            font.draw(
                                12 + font.stringWidth("To " + chatMessagePrefix[j]), l,
                                0x800000, chatMessage[j]);
                        }
                        i++;
                    }
                    if (k == 8 && (chatTradeDuelSetting == 0 || chatTradeDuelSetting == 1 && isFriend(chatMessagePrefix[j]))) {
                        if (l > 0 && l < 110)
                            font.draw(4, l, 0xcbb789,
                                chatMessagePrefix[j] + " " + chatMessage[j]);
                        i++;
                    }
                }

            Draw2D.resetBounds();
            chatScrollY = i * 14 + 7;
            if (chatScrollY < 78)
                chatScrollY = 78;
            drawScrollbar(463, 0, chatScrollY - chatScrollAmount - 77, chatScrollY, 77);
            font.draw(4, 90, 0, StringUtils.formatName(username) + ":");
            font.draw(6 + font.stringWidth(username + ": "), 90,
                255, input + "*");
            Draw2D.drawHorizontalLine(0, 77, 479, 0);
        }
        if (menuVisible && mouseArea == 2)
            drawMenu();
        areaChatback.drawImage(375, super.graphics, 22);
        areaViewport.bind();
        Draw3D.offsets = viewportOffsets;
    }

    public boolean readPacket() {
        if (stream == null) {
            return false;
        }

        try {
            int available = stream.available();
            if (available == 0) {
                return false;
            }

            if (packetOpcode == -1) {
                stream.read(inBuffer.data, 0, 1);
                packetOpcode = inBuffer.data[0] & 0xff;
                if (isaacState != null) {
                    packetOpcode = packetOpcode - isaacState.nextInt() & 0xff;
                }
                packetLength = Packet.PACKET_LENGTH[packetOpcode];
                available--;
            }

            if (packetLength == -1) {
                if (available > 0) {
                    stream.read(inBuffer.data, 0, 1);
                    packetLength = inBuffer.data[0] & 0xff;
                    available--;
                } else {
                    return false;
                }
            }

            if (packetLength == -2) {
                if (available > 1) {
                    stream.read(inBuffer.data, 0, 2);
                    inBuffer.offset = 0;
                    packetLength = inBuffer.g2();
                    available -= 2;
                } else {
                    return false;
                }
            }

            if (available < packetLength) {
                return false;
            }

            inBuffer.offset = 0;
            stream.read(inBuffer.data, 0, packetLength);

            netIdleCycles = 0;
            thirdMostRecentOpcode = secondMostRecentOpcode;
            secondMostRecentOpcode = lastPacketOpcode;
            lastPacketOpcode = packetOpcode;

            if (packetOpcode == Packet.Server.CHANGE_SKY) {
                skyColor = inBuffer.g3();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.VARP_SMALL) {
                int variable = inBuffer.g2();
                byte state = inBuffer.g1s();
                defaultVariables[variable] = state;
                if (variables[variable] != state) {
                    variables[variable] = state;
                    updateVarp(variable);
                    sidebarRedraw = true;
                    if (stickyChatbackComponentId != -1) {
                        redrawChatback = true;
                    }
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_FRIENDLIST) {
                long name = inBuffer.g8();
                int world = inBuffer.g1();

                String formattedName = StringUtils.formatName(StringUtils.fromBase37(name));
                for (int n = 0; n < friendCount; n++) {
                    if (name != friendName37[n]) {
                        continue;
                    }

                    if (friendWorld[n] != world) {
                        friendWorld[n] = world;
                        sidebarRedraw = true;
                        if (world > 0) {
                            addMessage(5, formattedName + " has logged in.", "");
                        } else if (world == 0) {
                            addMessage(5, formattedName + " has logged out.", "");
                        }
                    }

                    formattedName = null;
                    break;
                }

                if (formattedName != null && friendCount < 100) {
                    friendName37[friendCount] = name;
                    friendName[friendCount] = formattedName;
                    friendWorld[friendCount] = world;
                    friendCount++;
                    sidebarRedraw = true;
                }

                for (boolean reordered = false; !reordered; ) {
                    reordered = true;
                    for (int n = 0; n < friendCount - 1; n++) {
                        if (friendWorld[n] != nodeid && friendWorld[n + 1] == nodeid || friendWorld[n] == 0 && friendWorld[n + 1] != 0) {
                            int otherWorld = friendWorld[n];
                            friendWorld[n] = friendWorld[n + 1];
                            friendWorld[n + 1] = otherWorld;

                            String otherName = friendName[n];
                            friendName[n] = friendName[n + 1];
                            friendName[n + 1] = otherName;

                            long name37 = friendName37[n];
                            friendName37[n] = friendName37[n + 1];
                            friendName37[n + 1] = name37;

                            sidebarRedraw = true;
                            reordered = false;
                        }
                    }
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_REBOOT_TIMER) {
                systemUpdateTimer = inBuffer.g2() * 30;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.DATA_LAND_DONE) {
                int x = inBuffer.g1();
                int y = inBuffer.g1();

                int region = -1;
                for (int n = 0; n < sceneMapIndex.length; n++) {
                    if (sceneMapIndex[n] == (x << 8) + y) {
                        region = n;
                    }
                }

                if (region != -1) {
                    Signlink.cachesave("m" + x + "_" + y, sceneMapLandData[region], false);
                    sceneState = 1;
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.NPC_INFO) {
                updateNpcs(inBuffer, packetLength);
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.LOAD_AREA) {
                int sectorX = inBuffer.g2();
                int sectorY = inBuffer.g2();

                if (centerSectorX == sectorX && centerSectorY == sectorY && sceneState != 0) {
                    packetOpcode = -1;
                    return true;
                }

                centerSectorX = sectorX;
                centerSectorY = sectorY;
                baseTileX = (centerSectorX - 6) * 8;
                baseTileZ = (centerSectorY - 6) * 8;

                sceneState = 1;

                areaViewport.bind();
                fontPlain12.drawCentered(151, 0, "Loading - please wait.", 257);
                fontPlain12.drawCentered(150, 0xffffff, "Loading - please wait.", 256);
                areaViewport.drawImage(11, super.graphics, 8);

                Signlink.looprate(5);
                int mapCount = (packetLength - 2) / 10;
                sceneMapLandData = new byte[mapCount][];
                sceneMapLocData = new byte[mapCount][];
                sceneMapIndex = new int[mapCount];

                outBuffer.p1isaac(Packet.Client.REQUEST_MAP);
                outBuffer.p1(0);
                int size = 0;
                for (int n = 0; n < mapCount; n++) {
                    int x = inBuffer.g1();
                    int y = inBuffer.g1();
                    int landCrc = inBuffer.g4();
                    int locCrc = inBuffer.g4();

                    sceneMapIndex[n] = (x << 8) + y;
                    if (landCrc != 0) {
                        byte[] data = Signlink.cacheload("m" + x + "_" + y, false);
                        if (data != null) {
                            crc32.reset();
                            crc32.update(data);
                            if ((int) crc32.getValue() != landCrc) {
                                data = null;
                            }
                        }

                        if (data != null) {
                            sceneMapLandData[n] = data;
                        } else {
                            sceneState = 0;
                            outBuffer.p1(0);
                            outBuffer.p1(x);
                            outBuffer.p1(y);
                            size += 3;
                        }
                    }

                    if (locCrc != 0) {
                        byte[] data = Signlink.cacheload("l" + x + "_" + y, false);
                        if (data != null) {
                            crc32.reset();
                            crc32.update(data);
                            if ((int) crc32.getValue() != locCrc) {
                                data = null;
                            }
                        }

                        if (data != null) {
                            sceneMapLocData[n] = data;
                        } else {
                            sceneState = 0;
                            outBuffer.p1(1);
                            outBuffer.p1(x);
                            outBuffer.p1(y);
                            size += 3;
                        }
                    }
                }

                outBuffer.pSize(size);
                Signlink.looprate(50);
                areaViewport.bind();
                if (sceneState == 0) {
                    fontPlain12.drawCentered(166, 0, "Map area updated since last visit, so load will take longer this time only", 257);
                    fontPlain12.drawCentered(165, 0xffffff, "Map area updated since last visit, so load will take longer this time only", 256);
                }
                areaViewport.drawImage(11, super.graphics, 8);
                int deltaX = baseTileX - mapLastBaseX;
                int deltaZ = baseTileZ - mapLastBaseZ;
                mapLastBaseX = baseTileX;
                mapLastBaseZ = baseTileZ;

                for (int n = 0; n < 8192; n++) {
                    NPCEntity e = npcEntities[n];
                    if (e != null) {
                        for (int m = 0; m < 10; m++) {
                            e.pathTileX[m] -= deltaX;
                            e.pathTileZ[m] -= deltaZ;
                        }

                        e.x -= deltaX * 128;
                        e.z -= deltaZ * 128;
                    }
                }

                for (int n = 0; n < MAX_PLAYER_COUNT; n++) {
                    PlayerEntity e = playerEntities[n];
                    if (e != null) {
                        for (int m = 0; m < 10; m++) {
                            e.pathTileX[m] -= deltaX;
                            e.pathTileZ[m] -= deltaZ;
                        }

                        e.x -= deltaX * 128;
                        e.z -= deltaZ * 128;
                    }
                }

                byte startTileX = 0;
                byte endTileX = 104;
                byte dirX = 1;
                if (deltaX < 0) {
                    startTileX = 103;
                    endTileX = -1;
                    dirX = -1;
                }

                byte startTileZ = 0;
                byte endTileZ = 104;
                byte dirZ = 1;
                if (deltaZ < 0) {
                    startTileZ = 103;
                    endTileZ = -1;
                    dirZ = -1;
                }

                for (int x = startTileX; x != endTileX; x += dirX) {
                    for (int z = startTileZ; z != endTileZ; z += dirZ) {
                        int dstX = x + deltaX;
                        int dstZ = z + deltaZ;

                        for (int plane = 0; plane < 4; plane++) {
                            if (dstX >= 0 && dstZ >= 0 && dstX < 104 && dstZ < 104) {
                                objects[plane][x][z] = objects[plane][dstX][dstZ];
                            } else {
                                objects[plane][x][z] = null;
                            }
                        }
                    }
                }

                for (SpawnedLoc loc = (SpawnedLoc) spawnedLocations.peekLast(); loc != null; loc = (SpawnedLoc) spawnedLocations.getPrevious()) {
                    loc.tileX -= deltaX;
                    loc.tileZ -= deltaZ;
                    if (loc.tileX < 0 || loc.tileZ < 0 || loc.tileX >= 104 || loc.tileZ >= 104) {
                        loc.unlink();
                    }
                }

                if (flagTileX != 0) {
                    flagTileX -= deltaX;
                    flagTileY -= deltaZ;
                }

                cameraOriented = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETPLAYERHEAD) {
                int id = inBuffer.g2();
                Component.instances[id].modelDisabled = self.getHeadModel();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.HINT_ARROW) {
                hintType = inBuffer.g1();

                if (hintType == 1) {
                    hintNPC = inBuffer.g2();
                } else if (hintType >= 2 && hintType <= 6) {
                    if (hintType == 2) {
                        hintOffsetX = 64;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 3) {
                        hintOffsetX = 0;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 4) {
                        hintOffsetX = 128;
                        hintOffsetZ = 64;
                    }
                    if (hintType == 5) {
                        hintOffsetX = 64;
                        hintOffsetZ = 0;
                    }
                    if (hintType == 6) {
                        hintOffsetX = 64;
                        hintOffsetZ = 128;
                    }
                    hintType = 2;
                    hintTileX = inBuffer.g2();
                    hintTileZ = inBuffer.g2();
                    hintHeight = inBuffer.g1();
                } else if (hintType == 10) {
                    hintPlayer = inBuffer.g2();
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.MIDI_SONG) {
                String name = inBuffer.gString();
                int crc = inBuffer.g4();
                int size = inBuffer.g4();
                if (!name.equals(currentMidi) && midiActive && !lowMemory) {
                    setMidi(crc, name, size);
                }
                currentMidi = name;
                midiCrc = crc;
                midiSize = size;
                nextMusicDelay = 0;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.LOGOUT) {
                disconnect();
                packetOpcode = -1;
                return false;
            } else if (packetOpcode == Packet.Server.DATA_LOC_DONE) {
                int x = inBuffer.g1();
                int y = inBuffer.g1();

                int region = -1;
                for (int n = 0; n < sceneMapIndex.length; n++) {
                    if (sceneMapIndex[n] == (x << 8) + y) {
                        region = n;
                    }
                }

                if (region != -1) {
                    Signlink.cachesave("l" + x + "_" + y, sceneMapLocData[region], false);
                    sceneState = 1;
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CLEAR_WALKING_QUEUE) {
                flagTileX = 0;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_UID192) {
                selfPlayerId = inBuffer.g2();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_OBJECT_STACK || packetOpcode == Packet.Server.ATTACH_TEMPORARY_LOCATION_TO_PLAYER ||
                packetOpcode == Packet.Server.ADD_PRIVATE_OBJECT_STACK || packetOpcode == Packet.Server.SPOTANIM_SPECIFIC ||
                packetOpcode == Packet.Server.ADD_PROJECTILE || packetOpcode == Packet.Server.REMOVE_OBJECT_STACK ||
                packetOpcode == Packet.Server.ADD_OBJECT_STACK || packetOpcode == Packet.Server.LOC_ANIM ||
                packetOpcode == Packet.Server.LOC_DEL || packetOpcode == Packet.Server.ADD_LOCATION) {
                readSecondaryPacket(inBuffer, packetOpcode);
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_OPENSUB) {
                int viewportIndex = inBuffer.g2();
                int sidebarId = inBuffer.g2();
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = viewportIndex;
                sidebarInterfaceId = sidebarId;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.VARP_LARGE) {
                int variable = inBuffer.g2();
                int value = inBuffer.g4();
                defaultVariables[variable] = value;
                if (variables[variable] != value) {
                    variables[variable] = value;
                    updateVarp(variable);
                    sidebarRedraw = true;
                    if (stickyChatbackComponentId != -1) {
                        redrawChatback = true;
                    }
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETANIM) {
                int id = inBuffer.g2();
                int anim = inBuffer.g2();
                Component.instances[id].seqId = anim;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_OPENSIDE) {
                int id = inBuffer.g2();
                int tab = inBuffer.g1();
                if (id == 65535) {
                    id = -1;
                }

                tabComponentId[tab] = id;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.DATA_LOC) {
                int x = inBuffer.g1();
                int y = inBuffer.g1();
                int offset = inBuffer.g2();
                int length = inBuffer.g2();

                int region = -1;
                for (int n = 0; n < sceneMapIndex.length; n++) {
                    if (sceneMapIndex[n] == (x << 8) + y) {
                        region = n;
                    }
                }

                if (region != -1) {
                    if (sceneMapLocData[region] == null || sceneMapLocData[region].length != length) {
                        sceneMapLocData[region] = new byte[length];
                    }
                    inBuffer.gArrayBuffer(packetLength - 6, offset, sceneMapLocData[region]);
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.FINISH_TRACKING) {
                Buffer buf = InputTracking.flushAndDisable();
                if (buf != null) {
                    outBuffer.p1isaac(Packet.Client.TRACKING_DATA);
                    outBuffer.p2(buf.offset);
                    outBuffer.pArrayBuffer(buf.data, buf.offset, 0);
                    buf.release();
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_INV_PARTIAL) {
                sidebarRedraw = true;
                int id = inBuffer.g2();
                Component inter = Component.instances[id];
                int slot = inBuffer.g1();
                for (int n = 0; n < slot; n++) {
                    inter.inventoryIndices[n] = inBuffer.g2();
                    int amount = inBuffer.g1();
                    if (amount == 255) {
                        amount = inBuffer.g4();
                    }
                    inter.inventoryAmount[n] = amount;
                }
                for (int n = slot; n < inter.inventoryIndices.length; n++) {
                    inter.inventoryIndices[n] = 0;
                    inter.inventoryAmount[n] = 0;
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.ENABLE_TRACKING) {
                InputTracking.setEnabled();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_IAMOUNT) {
                showSocialInput = false;
                chatbackInputType = true;
                chatbackInput = "";
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_ITEMS_CLEAR) {
                int id = inBuffer.g2();
                Component inter = Component.instances[id];
                for (int n = 0; n < inter.inventoryIndices.length; n++) {
                    inter.inventoryIndices[n] = -1;
                    inter.inventoryIndices[n] = 0;
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.LAST_LOGIN_INFO) {
                lastLoginIP = inBuffer.g4();
                daysSinceLogin = inBuffer.g2();
                daysSinceRecoveryChange = inBuffer.g1();
                unreadMessageCount = inBuffer.g2();
                if (lastLoginIP != 0 && viewportInterfaceIndex == -1) {
                    Signlink.dnslookup(StringUtils.fromIPv4(lastLoginIP));
                    closeInterface();
                    char c = 650;
                    if (daysSinceRecoveryChange != 201) {
                        c = 655;
                    }
                    reportInput = "";
                    reportAbuseMuteToggle = false;
                    for (int id = 0; id < Component.instances.length; id++) {
                        if (Component.instances[id] == null || Component.instances[id].contentType != c) {
                            continue;
                        }
                        viewportInterfaceIndex = Component.instances[id].parent;
                        break;
                    }
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_SIDEBAR_FLASH) {
                flashingSidebarId = inBuffer.g1();
                if (flashingSidebarId == selectedTab) {
                    if (flashingSidebarId == 3)
                        selectedTab = 1;
                    else
                        selectedTab = 3;
                    sidebarRedraw = true;
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.MIDI_JINGLE) {
                if (midiActive && !lowMemory) {
                    int delay = inBuffer.g2();
                    int length = inBuffer.g4();
                    int streamLength = packetLength - 6;
                    byte[] data = new byte[length];
                    BZip2InputStream.read(data, length, inBuffer.data, streamLength, inBuffer.offset);
                    midisave(data, length, false);
                    nextMusicDelay = delay;
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_MULTIZONE) {
                inMultizone = inBuffer.g1();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.SYNTH_SOUND) {
                int id = inBuffer.g2();
                int loop = inBuffer.g1();
                int delay = inBuffer.g2();
                if (effectsEnabled && !lowMemory && waveCount < 50) {
                    waveId[waveCount] = id;
                    waveLoops[waveCount] = loop;
                    waveDelay[waveCount] = delay + SoundTrack.delays[id];
                    waveCount++;
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETNPCHEAD) {
                int id = inBuffer.g2();
                int npcId = inBuffer.g2();
                NPCType npcType = NPCType.get(npcId);
                Component.instances[id].modelDisabled = npcType.getHeadModel();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.PLAYER_POSITION) {
                localPosX = inBuffer.g1();
                localPosZ = inBuffer.g1();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_MODEL_RECOLOR) {
                int index = inBuffer.g2();
                int from = inBuffer.g2();
                int to = inBuffer.g2();
                Component c = Component.instances[index];
                Model m = c.modelDisabled;
                if (m != null) {
                    m.recolor(from, to);
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CHAT_FILTER_SETTINGS) {
                chatPublicSetting = inBuffer.g1();
                chatPrivateSetting = inBuffer.g1();
                chatTradeDuelSetting = inBuffer.g1();
                chatRedrawSettings = true;
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_INVENTORY) {
                int id = inBuffer.g2();
                resetParentComponentSeq(id);
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                sidebarInterfaceId = id;
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_OPENBOTTOM) {
                int id = inBuffer.g2();
                resetParentComponentSeq(id);
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                chatbackComponentId = id;
                redrawChatback = true;
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETPOSITION) {
                int id = inBuffer.g2();
                int x = inBuffer.g2s();
                int y = inBuffer.g2s();
                Component inter = Component.instances[id];
                inter.x = x;
                inter.y = y;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CAM_FORCEANGLE) {
                cameraOriented = true;
                cameraLocalX = inBuffer.g1();
                cameraLocalZ = inBuffer.g1();
                cameraHeightOffset = inBuffer.g2();
                cameraSpinSpeed = inBuffer.g1();
                cameraSpinMultiplier = inBuffer.g1();
                if (cameraSpinMultiplier >= 100) {
                    cameraX = cameraLocalX * 128 + 64;
                    cameraZ = cameraLocalZ * 128 + 64;
                    cameraY = getLandY(currentLevel, cameraLocalX, cameraLocalZ) - cameraHeightOffset;
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.GROUND_ITEM_REMOVE_ALL) {
                localPosX = inBuffer.g1();
                localPosZ = inBuffer.g1();

                for (int x = localPosX; x < localPosX + 8; x++) {
                    for (int z = localPosZ; z < localPosZ + 8; z++) {
                        if (objects[currentLevel][x][z] != null) {
                            objects[currentLevel][x][z] = null;
                            updateObjectStack(x, z);
                        }
                    }
                }

                for (SpawnedLoc loc = (SpawnedLoc) spawnedLocations.peekLast(); loc != null; loc = (SpawnedLoc) spawnedLocations.getPrevious()) {
                    if (loc.tileX >= localPosX && loc.tileX < localPosX + 8 && loc.tileZ >= localPosZ && loc.tileZ < localPosZ + 8 && loc.level == currentLevel) {
                        addLoc(loc.lastRotation, loc.tileX, loc.tileZ, loc.classType, loc.lastLocIndex, loc.lastType, loc.level);
                        loc.unlink();
                    }
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.DATA_LAND) {
                int x = inBuffer.g1();
                int y = inBuffer.g1();
                int offset = inBuffer.g2();
                int length = inBuffer.g2();

                int region = -1;
                for (int n = 0; n < sceneMapIndex.length; n++) {
                    if (sceneMapIndex[n] == (x << 8) + y) {
                        region = n;
                    }
                }

                if (region != -1) {
                    if (sceneMapLandData[region] == null || sceneMapLandData[region].length != length) {
                        sceneMapLandData[region] = new byte[length];
                    }
                    inBuffer.gArrayBuffer(packetLength - 6, offset, sceneMapLandData[region]);
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.MESSAGE_PRIVATE_ECHO) {
                long name = inBuffer.g8();
                int messageId = inBuffer.g4();
                int forceMessage = inBuffer.g1();

                boolean ignoreMessage = false;
                for (int n = 0; n < 100; n++) {
                    if (privateMessageIndex[n] != messageId) {
                        continue;
                    }
                    ignoreMessage = true;
                    break;
                }

                if (forceMessage <= 1) {
                    for (int n = 0; n < ignoreCount; n++) {
                        if (ignoreName37[n] != name) {
                            continue;
                        }
                        ignoreMessage = true;
                        break;
                    }
                }

                if (!ignoreMessage && tutorialIslandState == 0)
                    try {
                        privateMessageIndex[privateMessageCount] = messageId;
                        privateMessageCount = (privateMessageCount + 1) % 100;
                        String message = TextEncoder.read(inBuffer, packetLength - 13);
                        message = WordPack.getFiltered(message);
                        if (forceMessage > 1) {
                            addMessage(7, message, StringUtils.formatName(StringUtils.fromBase37(name)));
                        } else {
                            addMessage(3, message, StringUtils.formatName(StringUtils.fromBase37(name)));
                        }
                    } catch (Exception ex) {
                        Signlink.reporterror("cde1");
                    }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.RESET_CLIENT_VARCACHE) {
                for (int n = 0; n < variables.length; n++)
                    if (variables[n] != defaultVariables[n]) {
                        variables[n] = defaultVariables[n];
                        updateVarp(n);
                        sidebarRedraw = true;
                    }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETMODEL) {
                int id = inBuffer.g2();
                int modelId = inBuffer.g2();
                Component.instances[id].modelDisabled = new Model(modelId);
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_DIALOGUE) {
                stickyChatbackComponentId = inBuffer.g2s();
                redrawChatback = true;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_RUNENERGY) {
                if (selectedTab == 12) {
                    sidebarRedraw = true;
                }
                energy = inBuffer.g1();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CAM_MOVETO) {
                cameraOriented = true;
                cutsceneLocalX = inBuffer.g1();
                cutsceneLocalY = inBuffer.g1();
                cutsceneHeightOffset = inBuffer.g2();
                cutsceneSpinSpeed = inBuffer.g1();
                cutsceneSpinMultiplier = inBuffer.g1();
                if (cutsceneSpinMultiplier >= 100) {
                    int l6 = cutsceneLocalX * 128 + 64;
                    int k14 = cutsceneLocalY * 128 + 64;
                    int l19 = getLandY(currentLevel, cutsceneLocalX, cutsceneLocalY) - cutsceneHeightOffset;
                    int k23 = l6 - cameraX;
                    int j26 = l19 - cameraY;
                    int j28 = k14 - cameraZ;
                    int i30 = (int) Math.sqrt(k23 * k23 + j28 * j28);
                    cameraPitch = (int) (Math.atan2(j26, i30) * 325.949D) & 0x7ff;
                    cameraOrbitYaw = (int) (Math.atan2(k23, j28) * -325.949D) & 0x7ff;
                    if (cameraPitch < 128) {
                        cameraPitch = 128;
                    } else if (cameraPitch > 383) {
                        cameraPitch = 383;
                    }
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_SIDEBAR_FOCUS) {
                selectedTab = inBuffer.g1();
                sidebarRedraw = true;
                sidebarRedrawIcons = true;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.MESSAGE_GAME) {
                String message = inBuffer.gString();
                if (message.endsWith(":tradereq:")) {
                    String otherPlayer = message.substring(0, message.indexOf(":"));
                    long otherPlayer37 = StringUtils.toBase37(otherPlayer);
                    boolean ignored = false;
                    for (int n = 0; n < ignoreCount; n++) {
                        if (ignoreName37[n] != otherPlayer37) {
                            continue;
                        }
                        ignored = true;
                        break;
                    }

                    if (!ignored && tutorialIslandState == 0) {
                        addMessage(4, "wishes to trade with you.", otherPlayer);
                    }
                } else if (message.endsWith(":duelreq:")) {
                    String otherPlayer = message.substring(0, message.indexOf(":"));
                    long otherPlayer37 = StringUtils.toBase37(otherPlayer);
                    boolean ignored = false;
                    for (int n = 0; n < ignoreCount; n++) {
                        if (ignoreName37[n] != otherPlayer37) {
                            continue;
                        }
                        ignored = true;
                        break;
                    }

                    if (!ignored && tutorialIslandState == 0) {
                        addMessage(8, "wishes to duel with you.", otherPlayer);
                    }
                } else {
                    addMessage(0, message, "");
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_ITEM_MODEL) {
                int id = inBuffer.g2();
                int objectId = inBuffer.g2();
                int modelZoomDivisor = inBuffer.g2();
                ObjType objType = ObjType.get(objectId);
                Component.instances[id].modelDisabled = objType.getModel(50);
                Component.instances[id].modelEyePitch = objType.iconCameraPitch;
                Component.instances[id].modelYaw = objType.iconYaw;
                Component.instances[id].modelZoom = (objType.iconZoom * 100) / modelZoomDivisor;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_OPENTOP) {
                int id = inBuffer.g2();
                resetParentComponentSeq(id);
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = id;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETCOLOUR) {
                try {
                    int id = inBuffer.g2();
                    int color = inBuffer.g2();
                    int r = color >> 10 & 0x1f;
                    int g = color >> 5 & 0x1f;
                    int b = color & 0x1f;
                    Component.instances[id].color = (r << 19) + (g << 11) + (b << 3);
                } catch (Exception ex) {
                    System.out.println("Bad interface from server");
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.RESET_ANIMS) {
                for (PlayerEntity playerEntity : playerEntities) {
                    if (playerEntity != null) {
                        playerEntity.primarySeq = -1;
                    }
                }

                for (NPCEntity npcEntity : npcEntities) {
                    if (npcEntity != null) {
                        npcEntity.primarySeq = -1;
                    }
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.INTERFACE_HOVER) {
                int id = inBuffer.g2();
                boolean state = inBuffer.g1() == 1;
                Component.instances[id].hidden = state;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_IGNORELIST) {
                ignoreCount = packetLength / 8;
                for (int n = 0; n < ignoreCount; n++) {
                    ignoreName37[n] = inBuffer.g8();
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CAM_RESET) {
                cameraOriented = false;
                for (int n = 0; n < 5; n++) {
                    customCameraActive[n] = false;
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_HIDE) {
                if (sidebarInterfaceId != -1) {
                    sidebarInterfaceId = -1;
                    sidebarRedraw = true;
                    sidebarRedrawIcons = true;
                }
                if (chatbackComponentId != -1) {
                    chatbackComponentId = -1;
                    redrawChatback = true;
                }
                if (chatbackInputType) {
                    chatbackInputType = false;
                    redrawChatback = true;
                }
                viewportInterfaceIndex = -1;
                chatContinuingDialogue = false;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.IF_SETTEXT) {
                try {
                    int id = inBuffer.g2();
                    Component.instances[id].text = inBuffer.gString();
                    if (Component.instances[id].parent == tabComponentId[selectedTab]) {
                        sidebarRedraw = true;
                    }
                } catch (Exception ex) {
                    System.out.println("Bad interface from server");
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_STAT) {
                sidebarRedraw = true;
                int skill = inBuffer.g1();
                int exp = inBuffer.g4();
                int level = inBuffer.g1();
                skillExperience[skill] = exp;
                skillLevelReal[skill] = level;
                skillLevel[skill] = 1;
                for (int lvl = 0; lvl < 98; lvl++) {
                    if (exp >= EXPERIENCE_TABLE[lvl]) {
                        skillLevel[skill] = lvl + 2;
                    }
                }

                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.BATCH_PACKETS) {
                localPosX = inBuffer.g1();
                localPosZ = inBuffer.g1();
                while (inBuffer.offset < packetLength) {
                    int opcode = inBuffer.g1();
                    readSecondaryPacket(inBuffer, opcode);
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_RUNWEIGHT) {
                if (selectedTab == 12) {
                    sidebarRedraw = true;
                }
                weightCarried = inBuffer.g2s();
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.CAM_SHAKE) {
                int cameraId = inBuffer.g1();
                int jitter = inBuffer.g1();
                int amplitude = inBuffer.g1();
                int frequency = inBuffer.g1();
                customCameraActive[cameraId] = true;
                cameraJitter[cameraId] = jitter;
                cameraAmplitude[cameraId] = amplitude;
                cameraFrequency[cameraId] = frequency;
                unknownCameraVariable[cameraId] = 0;
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.UPDATE_INV_FULL) {
                sidebarRedraw = true;
                int id = inBuffer.g2();
                Component inter = Component.instances[id];
                while (inBuffer.offset < packetLength) {
                    int slot = inBuffer.g1();
                    int item = inBuffer.g2();
                    int amount = inBuffer.g1();
                    if (amount == 255) {
                        amount = inBuffer.g4();
                    }
                    if (slot >= 0 && slot < inter.inventoryIndices.length) {
                        inter.inventoryIndices[slot] = item;
                        inter.inventoryAmount[slot] = amount;
                    }
                }
                packetOpcode = -1;
                return true;
            } else if (packetOpcode == Packet.Server.PLAYER_INFO) {
                updatePlayers(inBuffer, packetLength);
                if (sceneState == 1) {
                    sceneState = 2;
                    SceneManager.levelBuilt = currentLevel;
                    createScene();
                }
                if (lowMemory && sceneState == 2 && SceneManager.levelBuilt != currentLevel) {
                    areaViewport.bind();
                    fontPlain12.drawCentered(151, 0, "Loading - please wait.", 257);
                    fontPlain12.drawCentered(150, 0xffffff, "Loading - please wait.", 256);
                    areaViewport.drawImage(11, super.graphics, 8);
                    SceneManager.levelBuilt = currentLevel;
                    createScene();
                }
                if (currentLevel != lastSceneLevel && sceneState == 2) {
                    lastSceneLevel = currentLevel;
                    createMinimap(currentLevel);
                }
                packetOpcode = -1;
                return true;
            }

            Signlink.reporterror("T1 - " + packetOpcode + "," + packetLength + " - " + secondMostRecentOpcode + "," + thirdMostRecentOpcode);
            disconnect();
        } catch (IOException _ex) {
            reconnect();
        } catch (Exception exception) {
            exception.printStackTrace();
            String s2 = "T2 - " + packetOpcode + "," + secondMostRecentOpcode + "," + thirdMostRecentOpcode + " - " + packetLength + ","
                + (baseTileX + self.pathTileX[0]) + ","
                + (baseTileZ + self.pathTileZ[0]) + " - ";
            for (int i16 = 0; i16 < packetLength && i16 < 50; i16++) {
                s2 = s2 + inBuffer.data[i16] + ",";
            }

            Signlink.reporterror(s2);
            disconnect();
        }
        return true;
    }

    public void drawChat2() {
        areaInvback.bind();
        Draw3D.offsets = sidebarOffsets;
        inback.draw(0, 0);
        if (sidebarInterfaceId != -1)
            drawInterface(0, 0, Component.instances[sidebarInterfaceId], 0);
        else if (tabComponentId[selectedTab] != -1)
            drawInterface(0, 0, Component.instances[tabComponentId[selectedTab]], 0);
        if (menuVisible && mouseArea == 1)
            drawMenu();
        areaInvback.drawImage(231, super.graphics, 562);
        areaViewport.bind();
        Draw3D.offsets = viewportOffsets;
    }

    public boolean isFriend(String s) {
        if (s == null)
            return false;
        for (int j = 0; j < friendCount; j++)
            if (s.equalsIgnoreCase(friendName[j]))
                return true;

        return s.equalsIgnoreCase(self.name);
    }

    public void init() {
        nodeid = Integer.parseInt(getParameter("nodeid"));
        portoff = Integer.parseInt(getParameter("portoff"));
        String s = getParameter("lowmem");
        if (s != null && s.equals("1")) {
            setLowMemory();
        } else {
            setHighMemory();
        }
        String s1 = getParameter("free");
        members = s1 == null || !s1.equals("1");
        initApplet(532, 789);
    }

    public void updatePlayerMask(int i, int mask, Buffer b, PlayerEntity p) {
        // update appearance
        if ((mask & 1) == 0x1) {
            int k = b.g1();
            byte[] data = new byte[k];
            Buffer buffer = new Buffer(data);
            b.gArrayBuffer(k, 0, data);
            playerBuffers[i] = buffer;
            p.read(buffer);
        }

        // play animation
        if ((mask & 2) == 0x2) {
            int anim = b.g2();
            if (anim == 65535) {
                anim = -1;
            }
            if (anim == p.primarySeq) {
                p.primarySeqPlays = 0;
            }
            int delay = b.g1();
            if (anim == -1 || p.primarySeq == -1 || SeqType.instances[anim].priority > SeqType.instances[p.primarySeq].priority || SeqType.instances[p.primarySeq].priority == 0) {
                p.primarySeq = anim;
                p.primarySeqFrame = 0;
                p.primarySeqCycle = 0;
                p.primarySeqDelay = delay;
                p.primarySeqPlays = 0;
            }
        }

        // face entity
        if ((mask & 4) == 0x4) {
            p.targetEntity = b.g2();
            if (p.targetEntity == 65535) {
                p.targetEntity = -1;
            }
        }

        // forced chat
        if ((mask & 8) == 0x8) {
            p.spoken = b.gString();
            p.spokenColor = 0;
            p.spokenEffect = 0;
            p.textCycle = 150;
            addMessage(2, p.spoken, p.name);
        }

        // combat hit
        if ((mask & 0x10) == 0x10) {
            p.damageTaken = b.g1();
            p.damageType = b.g1();
            p.lastCombatCycle = clientClock + 400;
            p.currentHealth = b.g1();
            p.maxHealth = b.g1();
        }

        // face tile
        if ((mask & 0x20) == 0x20) {
            p.focusX = b.g2();
            p.focusY = b.g2();
        }

        // chat
        if ((mask & 0x40) == 0x40) {
            int info = b.g2();
            int type = b.g1();
            int length = b.g1();
            int offset = b.offset;
            if (p.name != null) {
                long name37 = StringUtils.toBase37(p.name);
                boolean ignore = false;
                if (type <= 1) {
                    for (int n = 0; n < ignoreCount; n++) {
                        if (ignoreName37[n] != name37) {
                            continue;
                        }
                        ignore = true;
                        break;
                    }
                }
                if (!ignore && tutorialIslandState == 0)
                    try {
                        String s = TextEncoder.read(b, length);
                        s = WordPack.getFiltered(s);
                        p.spoken = s;
                        p.spokenColor = info >> 8;
                        p.spokenEffect = info & 0xff;
                        p.textCycle = 150;
                        if (type > 1) {
                            addMessage(1, s, p.name);
                        } else {
                            addMessage(2, s, p.name);
                        }
                    } catch (Exception exception) {
                        Signlink.reporterror("cde2");
                    }
            }
            b.offset = offset + length;
        }

        // play graphic
        if ((mask & 0x100) == 0x100) {
            p.spotAnimIndex = b.g2();
            int delay = b.g2();
            int height = b.g2();
            p.spotAnimOffsetY = height >> 16;
            p.lastSpotAnimCycle = clientClock + (delay & 0xffff);
            p.spotAnimFrame = 0;
            p.spotAnimCycle = 0;
            if (p.lastSpotAnimCycle > clientClock) {
                p.spotAnimFrame = -1;
            }
            if (p.spotAnimIndex == 65535) {
                p.spotAnimIndex = -1;
            }
        }

        // forced movement
        if ((mask & 0x200) == 0x200) {
            p.srcTileX = b.g1();
            p.srcTileY = b.g1();
            p.dstTileX = b.g1();
            p.dstTileY = b.g1();
            p.firstMoveCycle = b.g2() + clientClock;
            p.lastMoveCycle = b.g2() + clientClock;
            p.faceDirection = b.g1();
            p.pathRemaining = 0;
            p.pathTileX[0] = p.dstTileX;
            p.pathTileZ[0] = p.dstTileY;
        }
    }

    public void showProgress(String str, int progress) {
        System.out.println(str);

        prepareTitleScreen();
        if (titleArchive == null) {
            super.showProgress(str, progress);
            return;
        }

        char c = '\u0168';
        char c1 = '\310';
        byte byte0 = 20;

        titleCenter.bind();
        fontBold12.drawCentered(c1 / 2 - 26 - byte0, 0xffffff, "RuneScape is loading - please wait...", c / 2);

        int j = c1 / 2 - 18 - byte0;
        Draw2D.drawRect(c / 2 - 152, 0x8c1111, 34, j, 304);
        Draw2D.drawRect(c / 2 - 151, 0, 32, j + 1, 302);
        Draw2D.fillRect(j + 2, c / 2 - 150, 0x8c1111, progress * 3, 30);
        Draw2D.fillRect(j + 2, (c / 2 - 150) + progress * 3, 0, 300 - progress * 3, 30);

        fontBold12.drawCentered((c1 / 2 + 5) - byte0, 0xffffff, str, c / 2);
        titleCenter.drawImage(186, super.graphics, 214);

        if (redrawTitleBackground) {
            redrawTitleBackground = false;

            if (!flameActive) {
                titleLeft.drawImage(0, super.graphics, 0);
                titleRight.drawImage(0, super.graphics, 661);
            }

            titleTop.drawImage(0, super.graphics, 128);
            titleBottom.drawImage(386, super.graphics, 214);
            titleBottomLeft.drawImage(265, super.graphics, 0);
            titleBottomRight.drawImage(265, super.graphics, 574);
            titleLeftSpace.drawImage(186, super.graphics, 128);
            titleRightSpace.drawImage(186, super.graphics, 574);
        }
    }
}
