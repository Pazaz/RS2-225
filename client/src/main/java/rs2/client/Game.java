package rs2.client;

import com.jagex.core.bzip2.BZip2InputStream;
import com.jagex.core.crypto.IsaacRandom;
import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.stringtools.StringUtils;
import com.jagex.core.stringtools.TextEncoder;
import com.jagex.core.stringtools.WordPack;
import com.jagex.core.util.LinkedList;
import com.jagex.core.io.BufferedStream;
import com.jagex.game.runetek3.GameShell;
import com.jagex.game.runetek3.InputTracking;
import com.jagex.game.runetek3.animation.SeqBase;
import com.jagex.game.runetek3.animation.SeqFrame;
import com.jagex.game.runetek3.audio.SoundTrack;
import com.jagex.game.runetek3.config.*;
import com.jagex.game.runetek3.graphics.Component;
import com.jagex.game.runetek3.graphics.Font;
import com.jagex.game.runetek3.graphics.*;
import com.jagex.game.runetek3.scene.*;
import com.jagex.game.runetek3.scene.entities.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import rs2.shared.network.ServerProt;
import rs2.shared.network.ZoneProt;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

@OriginalClass("client!client")
public class Game extends GameShell {

	@OriginalMember(owner = "client!client", name = "F", descriptor = "Ljava/lang/String;")
	public static final String ASCII_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

	@OriginalMember(owner = "client!client", name = "od", descriptor = "[I")
	private static final int[] EXPERIENCE_TABLE = new int[99];

	@OriginalMember(owner = "client!client", name = "E", descriptor = "I")
	public static int opHeld1Counter;

	@OriginalMember(owner = "client!client", name = "Ab", descriptor = "I")
	public static int opLoc4Counter;

	@OriginalMember(owner = "client!client", name = "Zb", descriptor = "I")
	public static int opNpc5Counter;

	@OriginalMember(owner = "client!client", name = "gc", descriptor = "I")
	public static int drawCounter;

	@OriginalMember(owner = "client!client", name = "ic", descriptor = "I")
	public static int opHeld4Counter;

	@OriginalMember(owner = "client!client", name = "hd", descriptor = "I")
	public static int opLoc5Counter;

	@OriginalMember(owner = "client!client", name = "rd", descriptor = "I")
	public static int opNpc3Counter;

	@OriginalMember(owner = "client!client", name = "Ad", descriptor = "I")
	public static int opHeld9Counter;

	@OriginalMember(owner = "client!client", name = "Gd", descriptor = "I")
	public static int sidebarInputCounter;

	@OriginalMember(owner = "client!client", name = "Ld", descriptor = "I")
	public static int gamePortOffset;

	@OriginalMember(owner = "client!client", name = "Nd", descriptor = "Z")
	public static boolean lowMemory;

	@OriginalMember(owner = "client!client", name = "Od", descriptor = "I")
	public static int opPlayer2Counter;

	@OriginalMember(owner = "client!client", name = "le", descriptor = "I")
	public static int updatePlayersCounter;

	@OriginalMember(owner = "client!client", name = "Je", descriptor = "I")
	public static int ifButton5Counter;

	@OriginalMember(owner = "client!client", name = "bf", descriptor = "I")
	public static int clientClock;

	@OriginalMember(owner = "client!client", name = "Tf", descriptor = "I")
	public static int updateCounter;

	@OriginalMember(owner = "client!client", name = "Hh", descriptor = "I")
	public static int update2Counter;

	@OriginalMember(owner = "client!client", name = "Th", descriptor = "Z")
	public static boolean alreadyStarted;

	@OriginalMember(owner = "client!client", name = "Zh", descriptor = "I")
	public static int updateLocCounter;

	@OriginalMember(owner = "client!client", name = "Kd", descriptor = "I")
	public static int nodeId = 10;

	@OriginalMember(owner = "client!client", name = "Md", descriptor = "Z")
	public static boolean members = true;

	@OriginalMember(owner = "client!client", name = "G", descriptor = "I")
	private int midiSyncLen;

	@OriginalMember(owner = "client!client", name = "I", descriptor = "I")
	private int cameraLocalX;

	@OriginalMember(owner = "client!client", name = "J", descriptor = "I")
	private int cameraLocalZ;

	@OriginalMember(owner = "client!client", name = "K", descriptor = "I")
	private int cameraHeightOffset;

	@OriginalMember(owner = "client!client", name = "L", descriptor = "I")
	private int cameraSpinSpeed;

	@OriginalMember(owner = "client!client", name = "M", descriptor = "I")
	private int cameraSpinMultiplier;

	@OriginalMember(owner = "client!client", name = "P", descriptor = "[I")
	private int[] chatOffsets;

	@OriginalMember(owner = "client!client", name = "Q", descriptor = "[I")
	private int[] sidebarOffsets;

	@OriginalMember(owner = "client!client", name = "R", descriptor = "[I")
	private int[] viewportOffsets;

	@OriginalMember(owner = "client!client", name = "S", descriptor = "I")
	private int crossX;

	@OriginalMember(owner = "client!client", name = "T", descriptor = "I")
	private int crossY;

	@OriginalMember(owner = "client!client", name = "U", descriptor = "I")
	private int crossCycle;

	@OriginalMember(owner = "client!client", name = "V", descriptor = "I")
	private int crossType;

	@OriginalMember(owner = "client!client", name = "Y", descriptor = "I")
	private int nextMusicDelay;

	@OriginalMember(owner = "client!client", name = "Z", descriptor = "I")
	private int hintTileX;

	@OriginalMember(owner = "client!client", name = "ab", descriptor = "I")
	private int hintTileZ;

	@OriginalMember(owner = "client!client", name = "bb", descriptor = "I")
	private int hintHeight;

	@OriginalMember(owner = "client!client", name = "cb", descriptor = "I")
	private int hintOffsetX;

	@OriginalMember(owner = "client!client", name = "db", descriptor = "I")
	private int hintOffsetZ;

	@OriginalMember(owner = "client!client", name = "eb", descriptor = "I")
	private int minimapOffsetCycle;

	@OriginalMember(owner = "client!client", name = "hb", descriptor = "Lclient!tb;")
	private IsaacRandom decryptor;

	@OriginalMember(owner = "client!client", name = "jb", descriptor = "I")
	private int chatPrivateSetting;

	@OriginalMember(owner = "client!client", name = "ob", descriptor = "I")
	private int socialAction;

	@OriginalMember(owner = "client!client", name = "pb", descriptor = "I")
	private int baseTileX;

	@OriginalMember(owner = "client!client", name = "qb", descriptor = "I")
	private int baseTileZ;

	@OriginalMember(owner = "client!client", name = "rb", descriptor = "I")
	private int mapLastBaseX;

	@OriginalMember(owner = "client!client", name = "sb", descriptor = "I")
	private int mapLastBaseZ;

	@OriginalMember(owner = "client!client", name = "xb", descriptor = "I")
	private int runWeight;

	@OriginalMember(owner = "client!client", name = "yb", descriptor = "[[B")
	private byte[][] sceneMapLandData;

	@OriginalMember(owner = "client!client", name = "Fb", descriptor = "J")
	private long lastWaveStartTime;

	@OriginalMember(owner = "client!client", name = "Hb", descriptor = "I")
	private int packetLength;

	@OriginalMember(owner = "client!client", name = "Ib", descriptor = "I")
	private int packetOpcode;

	@OriginalMember(owner = "client!client", name = "Jb", descriptor = "I")
	private int netIdleCycles;

	@OriginalMember(owner = "client!client", name = "Kb", descriptor = "I")
	private int keepaliveCounter;

	@OriginalMember(owner = "client!client", name = "Lb", descriptor = "I")
	private int idleTimeout;

	@OriginalMember(owner = "client!client", name = "Nb", descriptor = "I")
	private int cameraOffsetCycle;

	@OriginalMember(owner = "client!client", name = "Vb", descriptor = "I")
	private int ignoreCount;

	@OriginalMember(owner = "client!client", name = "Wb", descriptor = "[[[I")
	private int[][][] levelHeightMaps;

	@OriginalMember(owner = "client!client", name = "cc", descriptor = "I")
	private int chatEffects;

	@OriginalMember(owner = "client!client", name = "dc", descriptor = "I")
	private int hintNpc;

	@OriginalMember(owner = "client!client", name = "ec", descriptor = "I")
	private int tutorialIslandState;

	@OriginalMember(owner = "client!client", name = "mc", descriptor = "I")
	private int button;

	@OriginalMember(owner = "client!client", name = "rc", descriptor = "I")
	private int lastWaveLength;

	@OriginalMember(owner = "client!client", name = "tc", descriptor = "I")
	private int cameraYaw;

	@OriginalMember(owner = "client!client", name = "uc", descriptor = "I")
	private int cameraYawModifier;

	@OriginalMember(owner = "client!client", name = "vc", descriptor = "I")
	private int cameraPitchModifier;

	@OriginalMember(owner = "client!client", name = "zc", descriptor = "I")
	private int playerCount;

	@OriginalMember(owner = "client!client", name = "Bc", descriptor = "I")
	private int updateCount;

	@OriginalMember(owner = "client!client", name = "Ec", descriptor = "I")
	private int lastPacketOpcode;

	@OriginalMember(owner = "client!client", name = "Fc", descriptor = "I")
	private int secondMostRecentOpcode;

	@OriginalMember(owner = "client!client", name = "Gc", descriptor = "I")
	private int thirdMostRecentOpcode;

	@OriginalMember(owner = "client!client", name = "Hc", descriptor = "Lclient!r;")
	private MapSquare mapSquare;

	@OriginalMember(owner = "client!client", name = "Jc", descriptor = "I")
	private int splitPrivateChat;

	@OriginalMember(owner = "client!client", name = "Nc", descriptor = "I")
	private int sceneCycle;

	@OriginalMember(owner = "client!client", name = "Oc", descriptor = "I")
	private int centerSectorX;

	@OriginalMember(owner = "client!client", name = "Pc", descriptor = "I")
	private int centerSectorZ;

	@OriginalMember(owner = "client!client", name = "Qc", descriptor = "[[[B")
	private byte[][][] levelRenderFlags;

	@OriginalMember(owner = "client!client", name = "Rc", descriptor = "[I")
	private int[] flameBuffer1;

	@OriginalMember(owner = "client!client", name = "Sc", descriptor = "[I")
	private int[] flameBuffer2;

	@OriginalMember(owner = "client!client", name = "Uc", descriptor = "I")
	private int objDragComponentId;

	@OriginalMember(owner = "client!client", name = "Vc", descriptor = "I")
	private int objDragSlot;

	@OriginalMember(owner = "client!client", name = "Wc", descriptor = "I")
	private int objDragArea;

	@OriginalMember(owner = "client!client", name = "Xc", descriptor = "I")
	private int objGrabX;

	@OriginalMember(owner = "client!client", name = "Yc", descriptor = "I")
	private int objGrabY;

	@OriginalMember(owner = "client!client", name = "cd", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaBackbase1;

	@OriginalMember(owner = "client!client", name = "dd", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaBackbase2;

	@OriginalMember(owner = "client!client", name = "ed", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaBackhmid1;

	@OriginalMember(owner = "client!client", name = "fd", descriptor = "I")
	private int privateMessageCount;

	@OriginalMember(owner = "client!client", name = "jd", descriptor = "I")
	private int chatHoveredInterfaceIndex;

	@OriginalMember(owner = "client!client", name = "md", descriptor = "I")
	private int localPosX;

	@OriginalMember(owner = "client!client", name = "nd", descriptor = "I")
	private int localPosZ;

	@OriginalMember(owner = "client!client", name = "sd", descriptor = "I")
	private int hoveredInterfaceIndex;

	@OriginalMember(owner = "client!client", name = "xd", descriptor = "I")
	private int daysSinceLogin;

	@OriginalMember(owner = "client!client", name = "yd", descriptor = "I")
	private int flameCycle1;

	@OriginalMember(owner = "client!client", name = "zd", descriptor = "I")
	private int flameCycle2;

	@OriginalMember(owner = "client!client", name = "Ed", descriptor = "I")
	private int currentPlane;

	@OriginalMember(owner = "client!client", name = "Jd", descriptor = "I")
	private int chatTradeDuelSetting;

	@OriginalMember(owner = "client!client", name = "Pd", descriptor = "Lclient!ib;")
	private IndexedSprite redstone1;

	@OriginalMember(owner = "client!client", name = "Qd", descriptor = "Lclient!ib;")
	private IndexedSprite redstone2;

	@OriginalMember(owner = "client!client", name = "Rd", descriptor = "Lclient!ib;")
	private IndexedSprite redstone3;

	@OriginalMember(owner = "client!client", name = "Sd", descriptor = "Lclient!ib;")
	private IndexedSprite redstone1h;

	@OriginalMember(owner = "client!client", name = "Td", descriptor = "Lclient!ib;")
	private IndexedSprite redstone2h;

	@OriginalMember(owner = "client!client", name = "Yd", descriptor = "J")
	private long socialName37;

	@OriginalMember(owner = "client!client", name = "Zd", descriptor = "I")
	private int daysSinceRecoveryChange;

	@OriginalMember(owner = "client!client", name = "be", descriptor = "[I")
	private int[] flameGradient;

	@OriginalMember(owner = "client!client", name = "ce", descriptor = "[I")
	private int[] flameGradientRed;

	@OriginalMember(owner = "client!client", name = "de", descriptor = "[I")
	private int[] flameGradientGreen;

	@OriginalMember(owner = "client!client", name = "ee", descriptor = "[I")
	private int[] flameGradientBlue;

	@OriginalMember(owner = "client!client", name = "ge", descriptor = "Lclient!ib;")
	private IndexedSprite backbase1;

	@OriginalMember(owner = "client!client", name = "he", descriptor = "Lclient!ib;")
	private IndexedSprite backbase2;

	@OriginalMember(owner = "client!client", name = "ie", descriptor = "Lclient!ib;")
	private IndexedSprite backhmid1;

	@OriginalMember(owner = "client!client", name = "je", descriptor = "I")
	private int hintType;

	@OriginalMember(owner = "client!client", name = "me", descriptor = "I")
	private int cameraOrbitX;

	@OriginalMember(owner = "client!client", name = "ne", descriptor = "I")
	private int cameraOrbitZ;

	@OriginalMember(owner = "client!client", name = "oe", descriptor = "I")
	private int cameraMovedWrite;

	@OriginalMember(owner = "client!client", name = "pe", descriptor = "I")
	private int activeMapFunctionCount;

	@OriginalMember(owner = "client!client", name = "we", descriptor = "I")
	private int objDragCycles;

	@OriginalMember(owner = "client!client", name = "xe", descriptor = "[I")
	private int[] sceneMapIndex;

	@OriginalMember(owner = "client!client", name = "Ae", descriptor = "I")
	private int npcCount;

	@OriginalMember(owner = "client!client", name = "Ce", descriptor = "I")
	private int minimapZoom;

	@OriginalMember(owner = "client!client", name = "Ee", descriptor = "I")
	private int cameraMaxY;

	@OriginalMember(owner = "client!client", name = "Fe", descriptor = "I")
	private int worldLocationState;

	@OriginalMember(owner = "client!client", name = "Ge", descriptor = "I")
	private int dragCycle;

	@OriginalMember(owner = "client!client", name = "Ie", descriptor = "Ljava/lang/String;")
	private String chatbackMessage;

	@OriginalMember(owner = "client!client", name = "Le", descriptor = "I")
	private int deadEntityCount;

	@OriginalMember(owner = "client!client", name = "Ne", descriptor = "I")
	private int sidebarHoveredInterfaceIndex;

	@OriginalMember(owner = "client!client", name = "Qe", descriptor = "I")
	private int selectedCycle;

	@OriginalMember(owner = "client!client", name = "Re", descriptor = "I")
	private int selectedInterface;

	@OriginalMember(owner = "client!client", name = "Se", descriptor = "I")
	private int selectedItem;

	@OriginalMember(owner = "client!client", name = "Te", descriptor = "I")
	private int selectedArea;

	@OriginalMember(owner = "client!client", name = "Ue", descriptor = "I")
	private int cutsceneLocalX;

	@OriginalMember(owner = "client!client", name = "Ve", descriptor = "I")
	private int cutsceneLocalY;

	@OriginalMember(owner = "client!client", name = "We", descriptor = "I")
	private int cutsceneHeightOffset;

	@OriginalMember(owner = "client!client", name = "Xe", descriptor = "I")
	private int cutsceneSpinSpeed;

	@OriginalMember(owner = "client!client", name = "Ye", descriptor = "I")
	private int cutsceneSpinMultiplier;

	@OriginalMember(owner = "client!client", name = "df", descriptor = "I")
	private int systemUpdateTimer;

	@OriginalMember(owner = "client!client", name = "hf", descriptor = "Lclient!hb;")
	private Sprite sprite;

	@OriginalMember(owner = "client!client", name = "jf", descriptor = "Lclient!hb;")
	private Sprite spriteActive;

	@OriginalMember(owner = "client!client", name = "kf", descriptor = "I")
	private int midiSyncCrc;

	@OriginalMember(owner = "client!client", name = "of", descriptor = "Lclient!z;")
	private PlayerEntity self;

	@OriginalMember(owner = "client!client", name = "qf", descriptor = "I")
	private int sceneDelta;

	@OriginalMember(owner = "client!client", name = "tf", descriptor = "I")
	private int loginFocusedLine;

	@OriginalMember(owner = "client!client", name = "uf", descriptor = "[Lclient!ib;")
	private IndexedSprite[] runes;

	@OriginalMember(owner = "client!client", name = "xf", descriptor = "I")
	private int chatPublicSetting;

	@OriginalMember(owner = "client!client", name = "yf", descriptor = "I")
	private int chatScrollAmount;

	@OriginalMember(owner = "client!client", name = "zf", descriptor = "Lclient!hb;")
	private Sprite imageFlamesLeft;

	@OriginalMember(owner = "client!client", name = "Af", descriptor = "Lclient!hb;")
	private Sprite imageFlamesRight;

	@OriginalMember(owner = "client!client", name = "Cf", descriptor = "Lclient!ib;")
	private IndexedSprite invback;

	@OriginalMember(owner = "client!client", name = "Df", descriptor = "Lclient!ib;")
	private IndexedSprite mapback;

	@OriginalMember(owner = "client!client", name = "Ef", descriptor = "Lclient!ib;")
	private IndexedSprite chatback;

	@OriginalMember(owner = "client!client", name = "Ff", descriptor = "I")
	private int inMultizone;

	@OriginalMember(owner = "client!client", name = "Gf", descriptor = "Lclient!jb;")
	private Font plain11;

	@OriginalMember(owner = "client!client", name = "Hf", descriptor = "Lclient!jb;")
	private Font plain12;

	@OriginalMember(owner = "client!client", name = "If", descriptor = "Lclient!jb;")
	private Font bold12;

	@OriginalMember(owner = "client!client", name = "Jf", descriptor = "Lclient!jb;")
	private Font quill8;

	@OriginalMember(owner = "client!client", name = "Kf", descriptor = "I")
	private int clickedMinimap;

	@OriginalMember(owner = "client!client", name = "Mf", descriptor = "[I")
	private int[] flameIntensity;

	@OriginalMember(owner = "client!client", name = "Nf", descriptor = "[I")
	private int[] flameIntensityBuffer;

	@OriginalMember(owner = "client!client", name = "Sf", descriptor = "Lclient!hb;")
	private Sprite mapflags;

	@OriginalMember(owner = "client!client", name = "Uf", descriptor = "Lclient!d;")
	private BufferedStream stream;

	@OriginalMember(owner = "client!client", name = "Vf", descriptor = "[[B")
	private byte[][] sceneMapLocData;

	@OriginalMember(owner = "client!client", name = "Xf", descriptor = "I")
	private int selectedObject;

	@OriginalMember(owner = "client!client", name = "Yf", descriptor = "I")
	private int selectedObjSlot;

	@OriginalMember(owner = "client!client", name = "Zf", descriptor = "I")
	private int selectedObjInterface;

	@OriginalMember(owner = "client!client", name = "ag", descriptor = "I")
	private int objInterface;

	@OriginalMember(owner = "client!client", name = "bg", descriptor = "Ljava/lang/String;")
	private String selectedObjName;

	@OriginalMember(owner = "client!client", name = "cg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backleft1;

	@OriginalMember(owner = "client!client", name = "dg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backleft2;

	@OriginalMember(owner = "client!client", name = "eg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backright1;

	@OriginalMember(owner = "client!client", name = "fg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backright2;

	@OriginalMember(owner = "client!client", name = "gg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backtop1;

	@OriginalMember(owner = "client!client", name = "hg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backtop2;

	@OriginalMember(owner = "client!client", name = "ig", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backvmid1;

	@OriginalMember(owner = "client!client", name = "jg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backvmid2;

	@OriginalMember(owner = "client!client", name = "kg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backvmid3;

	@OriginalMember(owner = "client!client", name = "lg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer backhmid2;

	@OriginalMember(owner = "client!client", name = "ng", descriptor = "I")
	private int waveCount;

	@OriginalMember(owner = "client!client", name = "ug", descriptor = "I")
	private int selectedSpell;

	@OriginalMember(owner = "client!client", name = "vg", descriptor = "I")
	private int spellInterface;

	@OriginalMember(owner = "client!client", name = "wg", descriptor = "I")
	private int selectedFlags;

	@OriginalMember(owner = "client!client", name = "xg", descriptor = "Ljava/lang/String;")
	private String selectedSpellPrefix;

	@OriginalMember(owner = "client!client", name = "yg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleTop;

	@OriginalMember(owner = "client!client", name = "zg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleBottom;

	@OriginalMember(owner = "client!client", name = "Ag", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleCenter;

	@OriginalMember(owner = "client!client", name = "Bg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleLeft;

	@OriginalMember(owner = "client!client", name = "Cg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleRight;

	@OriginalMember(owner = "client!client", name = "Dg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleBottomLeft;

	@OriginalMember(owner = "client!client", name = "Eg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleBottomRight;

	@OriginalMember(owner = "client!client", name = "Fg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleLeftSpace;

	@OriginalMember(owner = "client!client", name = "Gg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer titleRightSpace;

	@OriginalMember(owner = "client!client", name = "Jg", descriptor = "Lclient!ib;")
	private IndexedSprite redstone1v;

	@OriginalMember(owner = "client!client", name = "Kg", descriptor = "Lclient!ib;")
	private IndexedSprite redstone2v;

	@OriginalMember(owner = "client!client", name = "Lg", descriptor = "Lclient!ib;")
	private IndexedSprite redstone3v;

	@OriginalMember(owner = "client!client", name = "Mg", descriptor = "Lclient!ib;")
	private IndexedSprite redstone1vh;

	@OriginalMember(owner = "client!client", name = "Ng", descriptor = "Lclient!ib;")
	private IndexedSprite redstone2vh;

	@OriginalMember(owner = "client!client", name = "Pg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaInvback;

	@OriginalMember(owner = "client!client", name = "Qg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaMapback;

	@OriginalMember(owner = "client!client", name = "Rg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaViewport;

	@OriginalMember(owner = "client!client", name = "Sg", descriptor = "Lclient!qb;")
	private ImageProducerFrameBuffer areaChatback;

	@OriginalMember(owner = "client!client", name = "Ug", descriptor = "I")
	private int flagTileX;

	@OriginalMember(owner = "client!client", name = "Vg", descriptor = "I")
	private int flagTileY;

	@OriginalMember(owner = "client!client", name = "Wg", descriptor = "Lclient!hb;")
	private Sprite minimap;

	@OriginalMember(owner = "client!client", name = "Xg", descriptor = "I")
	private int unreadMessageCount;

	@OriginalMember(owner = "client!client", name = "ah", descriptor = "Lclient!hb;")
	private Sprite mapdot0;

	@OriginalMember(owner = "client!client", name = "bh", descriptor = "Lclient!hb;")
	private Sprite mapdot1;

	@OriginalMember(owner = "client!client", name = "ch", descriptor = "Lclient!hb;")
	private Sprite mapdot2;

	@OriginalMember(owner = "client!client", name = "dh", descriptor = "Lclient!hb;")
	private Sprite mapdot3;

	@OriginalMember(owner = "client!client", name = "eh", descriptor = "I")
	private int lastLoginIp;

	@OriginalMember(owner = "client!client", name = "gh", descriptor = "I")
	private int viewportHoveredInterfaceIndex;

	@OriginalMember(owner = "client!client", name = "hh", descriptor = "Ljava/lang/String;")
	private String midiSyncName;

	@OriginalMember(owner = "client!client", name = "ph", descriptor = "I")
	private int runEnergy;

	@OriginalMember(owner = "client!client", name = "rh", descriptor = "I")
	private int optionCount;

	@OriginalMember(owner = "client!client", name = "th", descriptor = "I")
	private int hintPlayer;

	@OriginalMember(owner = "client!client", name = "vh", descriptor = "I")
	private int sceneState;

	@OriginalMember(owner = "client!client", name = "yh", descriptor = "Lclient!ib;")
	private IndexedSprite scrollbar1;

	@OriginalMember(owner = "client!client", name = "zh", descriptor = "Lclient!ib;")
	private IndexedSprite scrollbar2;

	@OriginalMember(owner = "client!client", name = "Ch", descriptor = "I")
	private int minimapAnticheatAngle;

	@OriginalMember(owner = "client!client", name = "Eh", descriptor = "I")
	private int hoveredSlot;

	@OriginalMember(owner = "client!client", name = "Fh", descriptor = "I")
	private int hoveredSlotParentId;

	@OriginalMember(owner = "client!client", name = "Gh", descriptor = "I")
	private int friendCount;

	@OriginalMember(owner = "client!client", name = "Ih", descriptor = "I")
	private int chatCount;

	@OriginalMember(owner = "client!client", name = "Sh", descriptor = "I")
	private int wildernessLevel;

	@OriginalMember(owner = "client!client", name = "Uh", descriptor = "Lclient!ib;")
	private IndexedSprite titlebox;

	@OriginalMember(owner = "client!client", name = "Vh", descriptor = "Lclient!ib;")
	private IndexedSprite titlebutton;

	@OriginalMember(owner = "client!client", name = "ai", descriptor = "I")
	private int titleState;

	@OriginalMember(owner = "client!client", name = "bi", descriptor = "I")
	private int midiCrc;

	@OriginalMember(owner = "client!client", name = "ci", descriptor = "I")
	private int cameraX;

	@OriginalMember(owner = "client!client", name = "di", descriptor = "I")
	private int cameraY;

	@OriginalMember(owner = "client!client", name = "ei", descriptor = "I")
	private int cameraZ;

	@OriginalMember(owner = "client!client", name = "fi", descriptor = "I")
	private int cameraPitch;

	@OriginalMember(owner = "client!client", name = "gi", descriptor = "I")
	private int cameraOrbitYaw;

	@OriginalMember(owner = "client!client", name = "ki", descriptor = "Ljava/lang/String;")
	private String currentMidi;

	@OriginalMember(owner = "client!client", name = "qi", descriptor = "I")
	private int cameraAnticheatOffsetX;

	@OriginalMember(owner = "client!client", name = "vi", descriptor = "I")
	private int cameraAnticheatOffsetZ;

	@OriginalMember(owner = "client!client", name = "zi", descriptor = "I")
	private int cameraAnticheatAngle;

	@OriginalMember(owner = "client!client", name = "Bi", descriptor = "Lclient!ub;")
	private FileArchive titleArchive;

	@OriginalMember(owner = "client!client", name = "Ki", descriptor = "Lclient!hb;")
	private Sprite compass;

	@OriginalMember(owner = "client!client", name = "Li", descriptor = "J")
	private long serverSeed;

	@OriginalMember(owner = "client!client", name = "Ni", descriptor = "I")
	private int mouseArea;

	@OriginalMember(owner = "client!client", name = "Oi", descriptor = "I")
	private int menuX;

	@OriginalMember(owner = "client!client", name = "Pi", descriptor = "I")
	private int menuY;

	@OriginalMember(owner = "client!client", name = "Qi", descriptor = "I")
	private int menuWidth;

	@OriginalMember(owner = "client!client", name = "Ri", descriptor = "I")
	private int menuHeight;

	@OriginalMember(owner = "client!client", name = "Ti", descriptor = "I")
	private int scrollGripInputPadding;

	@OriginalMember(owner = "client!client", name = "Ui", descriptor = "I")
	private int midiSize;

	@OriginalMember(owner = "client!client", name = "Vi", descriptor = "I")
	private int flameOffset;

	@OriginalMember(owner = "client!client", name = "O", descriptor = "I")
	private int selfPlayerId = -1;

	@OriginalMember(owner = "client!client", name = "W", descriptor = "[I")
	private final int[] characterDesignColors = new int[5];

	@OriginalMember(owner = "client!client", name = "X", descriptor = "Lclient!kb;")
	private Buffer loginBuffer = Buffer.reserve(1);

	@OriginalMember(owner = "client!client", name = "fb", descriptor = "Z")
	private boolean redrawTitleBackground = false;

	@OriginalMember(owner = "client!client", name = "gb", descriptor = "Lclient!ob;")
	private LinkedList locList = new LinkedList();

	@OriginalMember(owner = "client!client", name = "ib", descriptor = "[Z")
	private final boolean[] customCameraActive = new boolean[5];

	@OriginalMember(owner = "client!client", name = "lb", descriptor = "I")
	private int selectedTab = 3;

	@OriginalMember(owner = "client!client", name = "mb", descriptor = "[[I")
	private int[][] bfsCost = new int[104][104];

	@OriginalMember(owner = "client!client", name = "tb", descriptor = "Ljava/lang/String;")
	private String socialInput = "";

	@OriginalMember(owner = "client!client", name = "vb", descriptor = "Lclient!ob;")
	private LinkedList temporaryLocs = new LinkedList();

	@OriginalMember(owner = "client!client", name = "wb", descriptor = "[J")
	private final long[] ignoreName37 = new long[100];

	@OriginalMember(owner = "client!client", name = "Bb", descriptor = "[I")
	private int[] friendWorld = new int[100];

	@OriginalMember(owner = "client!client", name = "Cb", descriptor = "I")
	private int lastSceneLevel = -1;

	@OriginalMember(owner = "client!client", name = "Db", descriptor = "Ljava/lang/String;")
	private String socialMessage = "";

	@OriginalMember(owner = "client!client", name = "Eb", descriptor = "[Lclient!hb;")
	private Sprite[] hitmarks = new Sprite[20];

	@OriginalMember(owner = "client!client", name = "Mb", descriptor = "Ljava/lang/String;")
	private String chatbackInput = "";

	@OriginalMember(owner = "client!client", name = "Ob", descriptor = "I")
	private int lastWaveId = -1;

	@OriginalMember(owner = "client!client", name = "Qb", descriptor = "Z")
	private boolean characterDesignUpdate = false;

	@OriginalMember(owner = "client!client", name = "Rb", descriptor = "[I")
	private final int[] characterDesigns = new int[7];

	@OriginalMember(owner = "client!client", name = "Tb", descriptor = "[Lclient!hb;")
	private Sprite[] activeMapFunctions = new Sprite[1000];

	@OriginalMember(owner = "client!client", name = "Ub", descriptor = "I")
	private int chatScrollY = 78;

	@OriginalMember(owner = "client!client", name = "Xb", descriptor = "Lclient!kb;")
	private Buffer inBuffer = Buffer.reserve(1);

	@OriginalMember(owner = "client!client", name = "ac", descriptor = "Lclient!kb;")
	private Buffer outBuffer = Buffer.reserve(1);

	@OriginalMember(owner = "client!client", name = "bc", descriptor = "Z")
	private boolean startMidiThread = false;

	@OriginalMember(owner = "client!client", name = "jc", descriptor = "[I")
	private final int[] skillLevelReal = new int[50];

	@OriginalMember(owner = "client!client", name = "kc", descriptor = "Lclient!hc;")
	private final Component component = new Component();

	@OriginalMember(owner = "client!client", name = "lc", descriptor = "[I")
	private final int[] waveLoops = new int[50];

	@OriginalMember(owner = "client!client", name = "nc", descriptor = "[I")
	private final int[] archiveChecksums = new int[9];

	@OriginalMember(owner = "client!client", name = "oc", descriptor = "Z")
	private boolean midiThreadActive = true;

	@OriginalMember(owner = "client!client", name = "qc", descriptor = "[Lclient!ib;")
	private IndexedSprite[] sideicons = new IndexedSprite[13];

	@OriginalMember(owner = "client!client", name = "sc", descriptor = "I")
	private int cameraOrbitPitch = 128;

	@OriginalMember(owner = "client!client", name = "wc", descriptor = "I")
	private final int MAX_PLAYER_COUNT = 2048;

	@OriginalMember(owner = "client!client", name = "xc", descriptor = "I")
	private final int LOCAL_PLAYER_INDEX = 2047;

	@OriginalMember(owner = "client!client", name = "yc", descriptor = "[Lclient!z;")
	private PlayerEntity[] playerEntities = new PlayerEntity[this.MAX_PLAYER_COUNT];

	@OriginalMember(owner = "client!client", name = "Ac", descriptor = "[I")
	private int[] playerIndices = new int[this.MAX_PLAYER_COUNT];

	@OriginalMember(owner = "client!client", name = "Cc", descriptor = "[I")
	private int[] entityUpdateIndices = new int[this.MAX_PLAYER_COUNT];

	@OriginalMember(owner = "client!client", name = "Dc", descriptor = "[Lclient!kb;")
	private Buffer[] playerBuffers = new Buffer[this.MAX_PLAYER_COUNT];

	@OriginalMember(owner = "client!client", name = "Ic", descriptor = "Lclient!ob;")
	private LinkedList projectiles = new LinkedList();

	@OriginalMember(owner = "client!client", name = "Kc", descriptor = "[Ljava/lang/String;")
	private String[] options = new String[500];

	@OriginalMember(owner = "client!client", name = "Lc", descriptor = "Z")
	private boolean midiActive = true;

	@OriginalMember(owner = "client!client", name = "Mc", descriptor = "Z")
	private boolean characterDesignIsMale = true;

	@OriginalMember(owner = "client!client", name = "ad", descriptor = "[I")
	private final int[] flameShiftX = new int[256];

	@OriginalMember(owner = "client!client", name = "gd", descriptor = "[I")
	private final int[] compassLeft = new int[33];

	@OriginalMember(owner = "client!client", name = "id", descriptor = "[I")
	private final int[] waveDelay = new int[50];

	@OriginalMember(owner = "client!client", name = "ld", descriptor = "[I")
	private final int[] tabComponentId = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!client", name = "pd", descriptor = "Z")
	private boolean errorLoading = false;

	@OriginalMember(owner = "client!client", name = "td", descriptor = "Z")
	private boolean showSocialInput = false;

	@OriginalMember(owner = "client!client", name = "wd", descriptor = "Z")
	private boolean chatContinuingDialogue = false;

	@OriginalMember(owner = "client!client", name = "Cd", descriptor = "[I")
	private final int[] privateMessageIndex = new int[100];

	@OriginalMember(owner = "client!client", name = "Dd", descriptor = "Z")
	private boolean menuVisible = false;

	@OriginalMember(owner = "client!client", name = "Fd", descriptor = "Z")
	private boolean reportAbuseMuteToggle = false;

	@OriginalMember(owner = "client!client", name = "Id", descriptor = "Lclient!ob;")
	private LinkedList spawnedLocations = new LinkedList();

	@OriginalMember(owner = "client!client", name = "Ud", descriptor = "[I")
	private final int[] chatMessageType = new int[100];

	@OriginalMember(owner = "client!client", name = "Vd", descriptor = "[Ljava/lang/String;")
	private final String[] chatMessagePrefix = new String[100];

	@OriginalMember(owner = "client!client", name = "Wd", descriptor = "[Ljava/lang/String;")
	private final String[] chatMessage = new String[100];

	@OriginalMember(owner = "client!client", name = "ae", descriptor = "Z")
	private boolean flameActive = false;

	@OriginalMember(owner = "client!client", name = "fe", descriptor = "I")
	private int openInterfaceId = -1;

	@OriginalMember(owner = "client!client", name = "qe", descriptor = "[I")
	private int[] activeMapFunctionX = new int[1000];

	@OriginalMember(owner = "client!client", name = "re", descriptor = "[I")
	private int[] activeMapFunctionZ = new int[1000];

	@OriginalMember(owner = "client!client", name = "se", descriptor = "[[I")
	private int[][] tileRenderCount = new int[104][104];

	@OriginalMember(owner = "client!client", name = "te", descriptor = "Z")
	private boolean redrawChat = false;

	@OriginalMember(owner = "client!client", name = "ve", descriptor = "Z")
	private boolean errorHost = false;

	@OriginalMember(owner = "client!client", name = "ye", descriptor = "[I")
	private final int[] skillLevel = new int[50];

	@OriginalMember(owner = "client!client", name = "ze", descriptor = "[Lclient!y;")
	private NpcEntity[] npcEntities = new NpcEntity[8192];

	@OriginalMember(owner = "client!client", name = "Be", descriptor = "[I")
	private int[] npcIndices = new int[8192];

	@OriginalMember(owner = "client!client", name = "De", descriptor = "I")
	private int minimapZoomModifier = 1;

	@OriginalMember(owner = "client!client", name = "Ke", descriptor = "[I")
	private int[] variables = new int[2000];

	@OriginalMember(owner = "client!client", name = "Me", descriptor = "[I")
	private int[] deadEntityIndices = new int[1000];

	@OriginalMember(owner = "client!client", name = "Pe", descriptor = "[J")
	private long[] friendName37 = new long[100];

	@OriginalMember(owner = "client!client", name = "Ze", descriptor = "[I")
	private final int[] minimapLineWidth = new int[151];

	@OriginalMember(owner = "client!client", name = "af", descriptor = "[Lclient!ec;")
	private CollisionMap[] collisionMaps = new CollisionMap[4];

	@OriginalMember(owner = "client!client", name = "cf", descriptor = "[Lclient!hb;")
	private Sprite[] headicons = new Sprite[20];

	@OriginalMember(owner = "client!client", name = "ff", descriptor = "[I")
	private final int[] cameraJitter = new int[5];

	@OriginalMember(owner = "client!client", name = "gf", descriptor = "Z")
	private boolean objGrabThreshold = false;

	@OriginalMember(owner = "client!client", name = "lf", descriptor = "Z")
	private boolean redrawSidebar = false;

	@OriginalMember(owner = "client!client", name = "mf", descriptor = "Z")
	private boolean redrawChatback = false;

	@OriginalMember(owner = "client!client", name = "nf", descriptor = "[I")
	private final int[] cameraAmplitude = new int[5];

	@OriginalMember(owner = "client!client", name = "pf", descriptor = "Z")
	private boolean cameraOriented = false;

	@OriginalMember(owner = "client!client", name = "rf", descriptor = "Ljava/lang/String;")
	private String reportInput = "";

	@OriginalMember(owner = "client!client", name = "sf", descriptor = "I")
	private int viewportInterfaceIndex = -1;

	@OriginalMember(owner = "client!client", name = "vf", descriptor = "Z")
	private boolean ingame = false;

	@OriginalMember(owner = "client!client", name = "wf", descriptor = "Z")
	private boolean startFlamesThread = false;

	@OriginalMember(owner = "client!client", name = "Bf", descriptor = "I")
	private final int SCROLLBAR_GRIP_LOWLIGHT = 0x332d25;

	@OriginalMember(owner = "client!client", name = "Of", descriptor = "I")
	private final int SCROLLBAR_GRIP_HIGHLIGHT = 0x766654;

	@OriginalMember(owner = "client!client", name = "Pf", descriptor = "[I")
	private int[] bfsStepX = new int[4000];

	@OriginalMember(owner = "client!client", name = "Qf", descriptor = "[I")
	private int[] bfsStepZ = new int[4000];

	@OriginalMember(owner = "client!client", name = "Wf", descriptor = "I")
	private int chatbackComponentId = -1;

	@OriginalMember(owner = "client!client", name = "og", descriptor = "I")
	private int drawX = -1;

	@OriginalMember(owner = "client!client", name = "pg", descriptor = "I")
	private int drawY = -1;

	@OriginalMember(owner = "client!client", name = "qg", descriptor = "I")
	private int stickyChatbackComponentId = -1;

	@OriginalMember(owner = "client!client", name = "sg", descriptor = "Z")
	private boolean modRights = false;

	@OriginalMember(owner = "client!client", name = "tg", descriptor = "[I")
	private final int[] unknownCameraVariable = new int[5];

	@OriginalMember(owner = "client!client", name = "Hg", descriptor = "[Lclient!ib;")
	private IndexedSprite[] mapscene = new IndexedSprite[50];

	@OriginalMember(owner = "client!client", name = "Og", descriptor = "[I")
	private final int[] textColors = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };

	@OriginalMember(owner = "client!client", name = "Tg", descriptor = "I")
	private final int SCROLLBAR_TRACK = 0x23201b;

	@OriginalMember(owner = "client!client", name = "Yg", descriptor = "Z")
	private boolean checkInputType = false;

	@OriginalMember(owner = "client!client", name = "Zg", descriptor = "Lclient!ob;")
	private LinkedList spotanims = new LinkedList();

	@OriginalMember(owner = "client!client", name = "ih", descriptor = "I")
	private int lastWaveLoops = -1;

	@OriginalMember(owner = "client!client", name = "jh", descriptor = "Ljava/lang/String;")
	private String username = "";

	@OriginalMember(owner = "client!client", name = "kh", descriptor = "Ljava/lang/String;")
	private String password = "";

	@OriginalMember(owner = "client!client", name = "mh", descriptor = "[B")
	private byte[] tmpTexels = new byte[16384];

	@OriginalMember(owner = "client!client", name = "nh", descriptor = "Z")
	private boolean errorStarted = false;

	@OriginalMember(owner = "client!client", name = "sh", descriptor = "[I")
	private final int[] defaultVariables = new int[2000];

	@OriginalMember(owner = "client!client", name = "wh", descriptor = "[I")
	private final int[] skillExperience = new int[50];

	@OriginalMember(owner = "client!client", name = "xh", descriptor = "Z")
	private boolean sidebarRedrawIcons = false;

	@OriginalMember(owner = "client!client", name = "Ah", descriptor = "Ljava/lang/String;")
	private String loginMessage1 = "";

	@OriginalMember(owner = "client!client", name = "Bh", descriptor = "Ljava/lang/String;")
	private String loginMessage2 = "";

	@OriginalMember(owner = "client!client", name = "Dh", descriptor = "I")
	private int minimapAngleModifier = 2;

	@OriginalMember(owner = "client!client", name = "Jh", descriptor = "I")
	private final int overheadMessageCount = 50;

	@OriginalMember(owner = "client!client", name = "Kh", descriptor = "[I")
	private final int[] chatScreenX = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Lh", descriptor = "[I")
	private final int[] chatScreenY = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Mh", descriptor = "[I")
	private final int[] chatHeight = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Nh", descriptor = "[I")
	private final int[] chatPadding = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Oh", descriptor = "[I")
	private final int[] chatColors = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Ph", descriptor = "[I")
	private final int[] chatStyles = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Qh", descriptor = "[I")
	private final int[] chatTimers = new int[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Rh", descriptor = "[Ljava/lang/String;")
	private final String[] chatMessages = new String[this.overheadMessageCount];

	@OriginalMember(owner = "client!client", name = "Yh", descriptor = "[I")
	private final int[] objectGroups = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3 };

	@OriginalMember(owner = "client!client", name = "ii", descriptor = "[I")
	private final int[] compassLineWidth = new int[33];

	@OriginalMember(owner = "client!client", name = "ji", descriptor = "[[I")
	private int[][] bfsDirection = new int[104][104];

	@OriginalMember(owner = "client!client", name = "li", descriptor = "[Lclient!hb;")
	private Sprite[] cross = new Sprite[8];

	@OriginalMember(owner = "client!client", name = "mi", descriptor = "Z")
	private boolean flamesThreadActive = false;

	@OriginalMember(owner = "client!client", name = "oi", descriptor = "Ljava/lang/Object;")
	private final Object midiSync = new Object();

	@OriginalMember(owner = "client!client", name = "pi", descriptor = "[I")
	private final int[] waveId = new int[50];

	@OriginalMember(owner = "client!client", name = "ri", descriptor = "I")
	private int cameraOffsetXModifier = 2;

	@OriginalMember(owner = "client!client", name = "si", descriptor = "[Ljava/lang/String;")
	private String[] friendName = new String[100];

	@OriginalMember(owner = "client!client", name = "ti", descriptor = "I")
	private int flashingSidebarId = -1;

	@OriginalMember(owner = "client!client", name = "ui", descriptor = "I")
	private int sidebarInterfaceId = -1;

	@OriginalMember(owner = "client!client", name = "wi", descriptor = "I")
	private int cameraOffsetZModifier = 2;

	@OriginalMember(owner = "client!client", name = "yi", descriptor = "[I")
	private final int[] minimapLeft = new int[151];

	@OriginalMember(owner = "client!client", name = "Ai", descriptor = "I")
	private int cameraOffsetYawModifier = 1;

	@OriginalMember(owner = "client!client", name = "Ci", descriptor = "Ljava/lang/String;")
	private String input = "";

	@OriginalMember(owner = "client!client", name = "Di", descriptor = "[Lclient!hb;")
	private Sprite[] mapfunction = new Sprite[50];

	@OriginalMember(owner = "client!client", name = "Ei", descriptor = "[I")
	private int[] optionParamA = new int[500];

	@OriginalMember(owner = "client!client", name = "Fi", descriptor = "[I")
	private int[] optionParamB = new int[500];

	@OriginalMember(owner = "client!client", name = "Gi", descriptor = "[I")
	private int[] optionType = new int[500];

	@OriginalMember(owner = "client!client", name = "Hi", descriptor = "[I")
	private int[] optionParamC = new int[500];

	@OriginalMember(owner = "client!client", name = "Ji", descriptor = "Z")
	private boolean scrollGripHeld = false;

	@OriginalMember(owner = "client!client", name = "Si", descriptor = "Z")
	private boolean effectsEnabled = true;

	@OriginalMember(owner = "client!client", name = "Wi", descriptor = "[[[Lclient!ob;")
	private LinkedList[][][] objects = new LinkedList[4][104][104];

	@OriginalMember(owner = "client!client", name = "Xi", descriptor = "I")
	private final int SCROLLBAR_GRIP_FOREGROUND = 0x4d4233;

	@OriginalMember(owner = "client!client", name = "Yi", descriptor = "[I")
	private final int[] cameraFrequency = new int[5];

	static {
		@Pc(6) int local6 = 0;
		for (@Pc(8) int local8 = 0; local8 < 99; local8++) {
			@Pc(13) int local13 = local8 + 1;
			@Pc(26) int local26 = (int) ((double) local13 + Math.pow(2.0D, (double) local13 / 7.0D) * 300.0D);
			local6 += local26;
			EXPERIENCE_TABLE[local8] = local6 / 4;
		}
	}

	@OriginalMember(owner = "client!client", name = "main", descriptor = "([Ljava/lang/String;)V")
	public static void main(@OriginalArg(0) String[] arg0) {
		try {
			System.out.println("RS2 user client - release #" + 225);

			if (arg0.length == 4) {
				nodeId = Integer.parseInt(arg0[0]);
				gamePortOffset = Integer.parseInt(arg0[1]);
				if (arg0[2].equals("lowmem")) {
					setLowMemory();
				} else if (arg0[2].equals("highmem")) {
					setHighMemory();
				} else {
					System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
					return;
				}

				if (arg0[3].equals("free")) {
					members = false;
				} else if (arg0[3].equals("members")) {
					members = true;
				} else {
					System.out.println("Usage: node-id, port-offset, [lowmem/highmem], [free/members]");
					return;
				}
			} else {
				nodeId = 10;
				gamePortOffset = 0;
				setHighMemory();
				members = true;
			}

			SignedLink.startpriv(InetAddress.getByName(GlobalConfig.SERVER_ADDRESS));
			@Pc(82) Game game = new Game();
			game.initApplication(532, 789);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(Z)V")
	public static void setLowMemory() {
		MapSquare.lowMemory = true;
		Draw3D.lowMemory = true;
		lowMemory = true;
		Scene.lowMemory = true;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(II)Ljava/lang/String;")
	public static String formatNumber(@OriginalArg(0) int arg0) {
		@Pc(4) String local4 = String.valueOf(arg0);
		for (@Pc(9) int local9 = local4.length() - 3; local9 > 0; local9 -= 3) {
			local4 = local4.substring(0, local9) + "," + local4.substring(local9);
		}
		if (local4.length() > 8) {
			local4 = "@gre@" + local4.substring(0, local4.length() - 8) + " million @whi@(" + local4 + ")";
		} else if (local4.length() > 4) {
			local4 = "@cya@" + local4.substring(0, local4.length() - 4) + "K @whi@(" + local4 + ")";
		}
		return " " + local4;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IZI)Ljava/lang/String;")
	public static String getLevelColorTag(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2) {
		@Pc(9) int local9 = arg0 - arg2;
		if (local9 < -9) {
			return "@red@";
		} else if (local9 < -6) {
			return "@or3@";
		} else if (local9 < -3) {
			return "@or2@";
		} else if (local9 < 0) {
			return "@or1@";
		} else if (local9 > 9) {
			return "@gre@";
		} else if (local9 > 6) {
			return "@gr3@";
		} else if (local9 > 3) {
			return "@gr2@";
		} else if (local9 > 0) {
			return "@gr1@";
		} else {
			return "@yel@";
		}
	}

	@OriginalMember(owner = "client!client", name = "B", descriptor = "(I)V")
	public static void setHighMemory() {
		MapSquare.lowMemory = false;
		Draw3D.lowMemory = false;
		lowMemory = false;
		Scene.lowMemory = false;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZI)Ljava/lang/String;")
	public static String formatObjAmount(@OriginalArg(1) int arg1) {
		if (arg1 < 100000) {
			return String.valueOf(arg1);
		} else if (arg1 < 10000000) {
			return arg1 / 1000 + "K";
		} else {
			return arg1 / 1000000 + "M";
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZILjava/lang/String;I)V")
	private void setMidi(@OriginalArg(1) int arg1, @OriginalArg(2) String arg2, @OriginalArg(3) int arg3) {
		if (arg2 != null) {
			synchronized (this.midiSync) {
				this.midiSyncName = arg2;
				this.midiSyncCrc = arg1;
				this.midiSyncLen = arg3;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(I)V")
	private void drawViewport2d() {
		this.chatCount = 0;
		@Pc(63) int local63;
		@Pc(84) int local84;
		for (@Pc(15) int local15 = -1; local15 < this.playerCount + this.npcCount; local15++) {
			@Pc(23) PathingEntity local23;
			if (local15 == -1) {
				local23 = this.self;
			} else if (local15 < this.playerCount) {
				local23 = this.playerEntities[this.playerIndices[local15]];
			} else {
				local23 = this.npcEntities[this.npcIndices[local15 - this.playerCount]];
			}
			if (local23 != null && local23.isVisible()) {
				if (local15 < this.playerCount) {
					local63 = 30;
					@Pc(66) PlayerEntity local66 = (PlayerEntity) local23;
					if (local66.headicons != 0) {
						this.setDrawPos(local23.height + 15, local23);
						if (this.drawX > -1) {
							for (local84 = 0; local84 < 8; local84++) {
								if ((local66.headicons & 0x1 << local84) != 0) {
									this.headicons[local84].draw(this.drawY - local63, this.drawX - 12);
									local63 -= 25;
								}
							}
						}
					}
					if (local15 >= 0 && this.hintType == 10 && this.hintPlayer == this.playerIndices[local15]) {
						this.setDrawPos(local23.height + 15, local23);
						if (this.drawX > -1) {
							this.headicons[7].draw(this.drawY - local63, this.drawX - 12);
						}
					}
				} else if (this.hintType == 1 && this.hintNpc == this.npcIndices[local15 - this.playerCount] && clientClock % 20 < 10) {
					this.setDrawPos(local23.height + 15, local23);
					if (this.drawX > -1) {
						this.headicons[2].draw(this.drawY - 28, this.drawX - 12);
					}
				}
				if (local23.spoken != null && (local15 >= this.playerCount || this.chatPublicSetting == 0 || this.chatPublicSetting == 3 || this.chatPublicSetting == 1 && this.isFriend(((PlayerEntity) local23).name))) {
					this.setDrawPos(local23.height, local23);
					if (this.drawX > -1 && this.chatCount < this.overheadMessageCount) {
						this.chatPadding[this.chatCount] = this.bold12.stringWidth(local23.spoken) / 2;
						this.chatHeight[this.chatCount] = this.bold12.fontHeight;
						this.chatScreenX[this.chatCount] = this.drawX;
						this.chatScreenY[this.chatCount] = this.drawY;
						this.chatColors[this.chatCount] = local23.spokenColor;
						this.chatStyles[this.chatCount] = local23.spokenEffect;
						this.chatTimers[this.chatCount] = local23.chatTimer;
						this.chatMessages[this.chatCount++] = local23.spoken;
						if (this.chatEffects == 0 && local23.spokenEffect == 1) {
							this.chatHeight[this.chatCount] += 10;
							this.chatScreenY[this.chatCount] += 5;
						}
						if (this.chatEffects == 0 && local23.spokenEffect == 2) {
							this.chatPadding[this.chatCount] = 60;
						}
					}
				}
				if (local23.combatCycle > clientClock + 100) {
					this.setDrawPos(local23.height + 15, local23);
					if (this.drawX > -1) {
						local63 = local23.currentHealth * 30 / local23.maxHealth;
						if (local63 > 30) {
							local63 = 30;
						}
						Draw2D.fillRect(this.drawY - 3, this.drawX - 15, 65280, local63, 5);
						Draw2D.fillRect(this.drawY - 3, this.drawX - 15 + local63, 16711680, 30 - local63, 5);
					}
				}
				if (local23.combatCycle > clientClock + 330) {
					this.setDrawPos(local23.height / 2, local23);
					if (this.drawX > -1) {
						this.hitmarks[local23.damageType].draw(this.drawY - 12, this.drawX - 12);
						this.plain11.drawCentered(this.drawY + 4, 0, String.valueOf(local23.damageTaken), this.drawX);
						this.plain11.drawCentered(this.drawY + 3, 16777215, String.valueOf(local23.damageTaken), this.drawX - 1);
					}
				}
			}
		}
		for (@Pc(483) int local483 = 0; local483 < this.chatCount; local483++) {
			local63 = this.chatScreenX[local483];
			@Pc(495) int local495 = this.chatScreenY[local483];
			local84 = this.chatPadding[local483];
			@Pc(505) int local505 = this.chatHeight[local483];
			@Pc(507) boolean local507 = true;
			while (local507) {
				local507 = false;
				for (@Pc(513) int local513 = 0; local513 < local483; local513++) {
					if (local495 + 2 > this.chatScreenY[local513] - this.chatHeight[local513] && local495 - local505 < this.chatScreenY[local513] + 2 && local63 - local84 < this.chatScreenX[local513] + this.chatPadding[local513] && local63 + local84 > this.chatScreenX[local513] - this.chatPadding[local513] && this.chatScreenY[local513] - this.chatHeight[local513] < local495) {
						local495 = this.chatScreenY[local513] - this.chatHeight[local513];
						local507 = true;
					}
				}
			}
			this.drawX = this.chatScreenX[local483];
			this.drawY = this.chatScreenY[local483] = local495;
			@Pc(612) String local612 = this.chatMessages[local483];
			if (this.chatEffects == 0) {
				@Pc(617) int local617 = 16776960;
				if (this.chatColors[local483] < 6) {
					local617 = this.textColors[this.chatColors[local483]];
				}
				if (this.chatColors[local483] == 6) {
					local617 = this.sceneCycle % 20 < 10 ? 16711680 : 16776960;
				}
				if (this.chatColors[local483] == 7) {
					local617 = this.sceneCycle % 20 < 10 ? 255 : 65535;
				}
				if (this.chatColors[local483] == 8) {
					local617 = this.sceneCycle % 20 < 10 ? 45056 : 8454016;
				}
				@Pc(692) int local692;
				if (this.chatColors[local483] == 9) {
					local692 = 150 - this.chatTimers[local483];
					if (local692 < 50) {
						local617 = local692 * 1280 + 16711680;
					} else if (local692 < 100) {
						local617 = 16776960 - (local692 - 50) * 327680;
					} else if (local692 < 150) {
						local617 = (local692 - 100) * 5 + 65280;
					}
				}
				if (this.chatColors[local483] == 10) {
					local692 = 150 - this.chatTimers[local483];
					if (local692 < 50) {
						local617 = local692 * 5 + 16711680;
					} else if (local692 < 100) {
						local617 = 16711935 - (local692 - 50) * 327680;
					} else if (local692 < 150) {
						local617 = (local692 - 100) * 327680 + 255 - (local692 - 100) * 5;
					}
				}
				if (this.chatColors[local483] == 11) {
					local692 = 150 - this.chatTimers[local483];
					if (local692 < 50) {
						local617 = 16777215 - local692 * 327685;
					} else if (local692 < 100) {
						local617 = (local692 - 50) * 327685 + 65280;
					} else if (local692 < 150) {
						local617 = 16777215 - (local692 - 100) * 327680;
					}
				}
				if (this.chatStyles[local483] == 0) {
					this.bold12.drawCentered(this.drawY + 1, 0, local612, this.drawX);
					this.bold12.drawCentered(this.drawY, local617, local612, this.drawX);
				}
				if (this.chatStyles[local483] == 1) {
					this.bold12.drawCenteredWave(this.sceneCycle, this.drawX, this.drawY + 1, 0, local612);
					this.bold12.drawCenteredWave(this.sceneCycle, this.drawX, this.drawY, local617, local612);
				}
				if (this.chatStyles[local483] == 2) {
					local692 = this.bold12.stringWidth(local612);
					@Pc(913) int local913 = (150 - this.chatTimers[local483]) * (local692 + 100) / 150;
					Draw2D.setBounds(334, 0, this.drawX + 50, this.drawX - 50);
					this.bold12.draw(this.drawX + 50 - local913, this.drawY + 1, 0, local612);
					this.bold12.draw(this.drawX + 50 - local913, this.drawY, local617, local612);
					Draw2D.resetBounds();
				}
			} else {
				this.bold12.drawCentered(this.drawY + 1, 0, local612, this.drawX);
				this.bold12.drawCentered(this.drawY, 16776960, local612, this.drawX);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(B)V")
	private void closeInterface() {
		this.outBuffer.p1isaac(231);
		if (this.sidebarInterfaceId != -1) {
			this.sidebarInterfaceId = -1;
			this.redrawSidebar = true;
			this.chatContinuingDialogue = false;
			this.sidebarRedrawIcons = true;
		}
		if (this.chatbackComponentId != -1) {
			this.chatbackComponentId = -1;
			this.redrawChatback = true;
			this.chatContinuingDialogue = false;
		}
		this.viewportInterfaceIndex = -1;
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(I)V")
	private void midistop() {
		SignedLink.midifade = false;
		SignedLink.midi = "stop";
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(I)V")
	private void drawWildyLevel() {
		@Pc(10) int local10 = (this.self.x >> 7) + this.baseTileX;
		@Pc(19) int local19 = (this.self.z >> 7) + this.baseTileZ;
		if (local10 >= 2944 && local10 < 3392 && local19 >= 3520 && local19 < 6400) {
			this.wildernessLevel = (local19 - 3520) / 8 + 1;
		} else if (local10 >= 2944 && local10 < 3392 && local19 >= 9920 && local19 < 12800) {
			this.wildernessLevel = (local19 - 9920) / 8 + 1;
		} else {
			this.wildernessLevel = 0;
		}
		this.worldLocationState = 0;
		if (local10 >= 3328 && local10 < 3392 && local19 >= 3200 && local19 < 3264) {
			@Pc(98) int local98 = local10 & 0x3F;
			@Pc(102) int local102 = local19 & 0x3F;
			if (local98 >= 4 && local98 <= 29 && local102 >= 44 && local102 <= 58) {
				this.worldLocationState = 1;
			}
			if (local98 >= 36 && local98 <= 61 && local102 >= 44 && local102 <= 58) {
				this.worldLocationState = 1;
			}
			if (local98 >= 4 && local98 <= 29 && local102 >= 25 && local102 <= 39) {
				this.worldLocationState = 1;
			}
			if (local98 >= 36 && local98 <= 61 && local102 >= 25 && local102 <= 39) {
				this.worldLocationState = 1;
			}
			if (local98 >= 4 && local98 <= 29 && local102 >= 6 && local102 <= 20) {
				this.worldLocationState = 1;
			}
			if (local98 >= 36 && local98 <= 61 && local102 >= 6 && local102 <= 20) {
				this.worldLocationState = 1;
			}
		}
		if (this.worldLocationState == 0 && local10 >= 3328 && local10 <= 3393 && local19 >= 3203 && local19 <= 3325) {
			this.worldLocationState = 2;
		}
		this.tutorialIslandState = 0;
		if (local10 >= 3053 && local10 <= 3156 && local19 >= 3056 && local19 <= 3136) {
			this.tutorialIslandState = 1;
		}
		if (local10 >= 3072 && local10 <= 3118 && local19 >= 9492 && local19 <= 9535) {
			this.tutorialIslandState = 1;
		}
		if (this.tutorialIslandState == 1 && local10 >= 3139 && local10 <= 3199 && local19 >= 3008 && local19 <= 3062) {
			this.tutorialIslandState = 0;
		}
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(I)V")
	private void drawChat() {
		if (this.splitPrivateChat != 0) {
			@Pc(9) Font local9 = this.plain12;
			@Pc(11) int local11 = 0;
			if (this.systemUpdateTimer != 0) {
				local11 = 1;
			}
			for (@Pc(18) int local18 = 0; local18 < 100; local18++) {
				if (this.chatMessage[local18] != null) {
					@Pc(30) int local30 = this.chatMessageType[local18];
					@Pc(60) int local60;
					if ((local30 == 3 || local30 == 7) && (local30 == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[local18]))) {
						local60 = 329 - local11 * 13;
						local9.draw(4, local60, 0, "From " + this.chatMessagePrefix[local18] + ": " + this.chatMessage[local18]);
						local9.draw(4, local60 - 1, 65535, "From " + this.chatMessagePrefix[local18] + ": " + this.chatMessage[local18]);
						local11++;
						if (local11 >= 5) {
							return;
						}
					}
					if (local30 == 5 && this.chatPrivateSetting < 2) {
						local60 = 329 - local11 * 13;
						local9.draw(4, local60, 0, this.chatMessage[local18]);
						local9.draw(4, local60 - 1, 65535, this.chatMessage[local18]);
						local11++;
						if (local11 >= 5) {
							return;
						}
					}
					if (local30 == 6 && this.chatPrivateSetting < 2) {
						local60 = 329 - local11 * 13;
						local9.draw(4, local60, 0, "To " + this.chatMessagePrefix[local18] + ": " + this.chatMessage[local18]);
						local9.draw(4, local60 - 1, 65535, "To " + this.chatMessagePrefix[local18] + ": " + this.chatMessage[local18]);
						local11++;
						if (local11 >= 5) {
							return;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!kb;II)V")
	private void readNpcUpdates(@OriginalArg(0) Buffer arg0, @OriginalArg(1) int arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.updateCount; local1++) {
			@Pc(8) int local8 = this.entityUpdateIndices[local1];
			@Pc(13) NpcEntity local13 = this.npcEntities[local8];
			@Pc(16) int local16 = arg0.g1();
			@Pc(24) int local24;
			if ((local16 & 0x2) == 2) {
				local24 = arg0.g2();
				if (local24 == 65535) {
					local24 = -1;
				}
				if (local24 == local13.primarySeq) {
					local13.primarySeqPlays = 0;
				}
				@Pc(39) int local39 = arg0.g1();
				if (local24 == -1 || local13.primarySeq == -1 || SeqType.instances[local24].priority > SeqType.instances[local13.primarySeq].priority || SeqType.instances[local13.primarySeq].priority == 0) {
					local13.primarySeq = local24;
					local13.primarySeqFrame = 0;
					local13.primarySeqCycle = 0;
					local13.primarySeqDelay = local39;
					local13.primarySeqPlays = 0;
				}
			}
			if ((local16 & 0x4) == 4) {
				local13.targetEntity = arg0.g2();
				if (local13.targetEntity == 65535) {
					local13.targetEntity = -1;
				}
			}
			if ((local16 & 0x8) == 8) {
				local13.spoken = arg0.gstr();
				local13.chatTimer = 100;
			}
			if ((local16 & 0x10) == 16) {
				local13.damageTaken = arg0.g1();
				local13.damageType = arg0.g1();
				local13.combatCycle = clientClock + 400;
				local13.currentHealth = arg0.g1();
				local13.maxHealth = arg0.g1();
			}
			if ((local16 & 0x20) == 32) {
				local13.type = NpcType.get(arg0.g2());
				local13.runSeq = local13.type.walkanim;
				local13.walkSeq = local13.type.walkanim_b;
				local13.turnAroundSeq = local13.type.walkanim_r;
				local13.turnRightSeq = local13.type.walkanim_l;
				local13.standSeq = local13.type.readyanim;
			}
			if ((local16 & 0x40) == 64) {
				local13.spotAnimIndex = arg0.g2();
				local24 = arg0.g4();
				local13.spotAnimOffsetY = local24 >> 16;
				local13.lastSpotAnimCycle = clientClock + (local24 & 0xFFFF);
				local13.spotAnimFrame = 0;
				local13.spotAnimCycle = 0;
				if (local13.lastSpotAnimCycle > clientClock) {
					local13.spotAnimFrame = -1;
				}
				if (local13.spotAnimIndex == 65535) {
					local13.spotAnimIndex = -1;
				}
			}
			if ((local16 & 0x80) == 128) {
				local13.focusX = arg0.g2();
				local13.focusZ = arg0.g2();
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(JB)V")
	private void addIgnore(@OriginalArg(0) long arg0) {
		if (arg0 != 0L) {
			if (this.ignoreCount >= 100) {
				this.addMessage(0, "Your ignore list is full. Max of 100 hit", "");
			} else {
				@Pc(23) String local23 = StringUtils.formatName(StringUtils.fromBase37(arg0));
				for (@Pc(25) int local25 = 0; local25 < this.ignoreCount; local25++) {
					if (this.ignoreName37[local25] == arg0) {
						this.addMessage(0, local23 + " is already on your ignore list", "");
						return;
					}
				}
				for (@Pc(55) int local55 = 0; local55 < this.friendCount; local55++) {
					if (this.friendName37[local55] == arg0) {
						this.addMessage(0, "Please remove " + local23 + " from your friend list first", "");
						return;
					}
				}
				this.ignoreName37[this.ignoreCount++] = arg0;
				this.redrawSidebar = true;
				this.outBuffer.p1isaac(79);
				this.outBuffer.p8(arg0);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(BLclient!kb;I)V")
	private void readZonePacket(@OriginalArg(1) Buffer arg1, @OriginalArg(2) int opcode) {
		@Pc(15) int local15;
		@Pc(24) int local24;
		@Pc(31) int local31;
		@Pc(34) int local34;
		@Pc(38) int local38;
		@Pc(42) int local42;
		@Pc(47) int local47;
		@Pc(52) int local52;
		@Pc(108) int local108;
		@Pc(110) int local110;
		@Pc(112) int local112;
		if (opcode == ZoneProt.LOC_ADD || opcode == ZoneProt.LOC_DEL) {
			local15 = arg1.g1();
			local24 = this.localPosX + (local15 >> 4 & 0x7);
			local31 = this.localPosZ + (local15 & 0x7);
			local34 = arg1.g1();
			local38 = local34 >> 2;
			local42 = local34 & 0x3;
			local47 = this.objectGroups[local38];
			if (opcode == ZoneProt.LOC_DEL) {
				local52 = -1;
			} else {
				local52 = arg1.g2();
			}
			if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
				@Pc(69) SpawnedLoc local69 = null;
				for (@Pc(74) SpawnedLoc local74 = (SpawnedLoc) this.spawnedLocations.peekPrevious(); local74 != null; local74 = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					if (local74.plane == this.currentPlane && local74.x == local24 && local74.z == local31 && local74.classType == local47) {
						local69 = local74;
						break;
					}
				}
				if (local69 == null) {
					local108 = 0;
					local110 = -1;
					local112 = 0;
					@Pc(114) int local114 = 0;
					if (local47 == 0) {
						local108 = this.mapSquare.getWallBitset(this.currentPlane, local24, local31);
					}
					if (local47 == 1) {
						local108 = this.mapSquare.getWallDecorationBitset(this.currentPlane, local31, local24);
					}
					if (local47 == 2) {
						local108 = this.mapSquare.getLocationBitset(this.currentPlane, local24, local31);
					}
					if (local47 == 3) {
						local108 = this.mapSquare.getGroundDecorationBitset(this.currentPlane, local24, local31);
					}
					if (local108 != 0) {
						@Pc(169) int local169 = this.mapSquare.getInfo(this.currentPlane, local24, local31, local108);
						local110 = local108 >> 14 & 0x7FFF;
						local112 = local169 & 0x1F;
						local114 = local169 >> 6;
					}
					local69 = new SpawnedLoc();
					local69.plane = this.currentPlane;
					local69.classType = local47;
					local69.x = local24;
					local69.z = local31;
					local69.lastLocIndex = local110;
					local69.lastType = local112;
					local69.lastOrientation = local114;
					this.spawnedLocations.pushNext(local69);
				}
				local69.locIndex = local52;
				local69.type = local38;
				local69.orientation = local42;
				this.addLoc(local42, local24, local31, local47, local52, local38, this.currentPlane);
			}
		} else if (opcode == ZoneProt.LOC_ANIM) {
			local15 = arg1.g1();
			local24 = this.localPosX + (local15 >> 4 & 0x7);
			local31 = this.localPosZ + (local15 & 0x7);
			local34 = arg1.g1();
			local38 = local34 >> 2;
			local42 = this.objectGroups[local38];
			local47 = arg1.g2();
			if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
				local52 = 0;
				if (local42 == 0) {
					local52 = this.mapSquare.getWallBitset(this.currentPlane, local24, local31);
				}
				if (local42 == 1) {
					local52 = this.mapSquare.getWallDecorationBitset(this.currentPlane, local31, local24);
				}
				if (local42 == 2) {
					local52 = this.mapSquare.getLocationBitset(this.currentPlane, local24, local31);
				}
				if (local42 == 3) {
					local52 = this.mapSquare.getGroundDecorationBitset(this.currentPlane, local24, local31);
				}
				if (local52 != 0) {
					@Pc(348) LocEntity local348 = new LocEntity(false, local52 >> 14 & 0x7FFF, this.currentPlane, local42, SeqType.instances[local47], local31, local24);
					this.locList.pushNext(local348);
				}
			}
		} else {
			@Pc(395) ObjEntity local395;
			if (opcode == ZoneProt.OBJ_REVEAL) {
				local15 = arg1.g1();
				local24 = this.localPosX + (local15 >> 4 & 0x7);
				local31 = this.localPosZ + (local15 & 0x7);
				local34 = arg1.g2();
				local38 = arg1.g2();
				if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
					local395 = new ObjEntity();
					local395.id = local34;
					local395.count = local38;
					if (this.objects[this.currentPlane][local24][local31] == null) {
						this.objects[this.currentPlane][local24][local31] = new LinkedList();
					}
					this.objects[this.currentPlane][local24][local31].pushNext(local395);
					this.updateObjectStack(local24, local31);
				}
			} else if (opcode == ZoneProt.OBJ_DEL) {
				local15 = arg1.g1();
				local24 = this.localPosX + (local15 >> 4 & 0x7);
				local31 = this.localPosZ + (local15 & 0x7);
				local34 = arg1.g2();
				if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
					@Pc(485) LinkedList local485 = this.objects[this.currentPlane][local24][local31];
					if (local485 != null) {
						for (local395 = (ObjEntity) local485.peekPrevious(); local395 != null; local395 = (ObjEntity) local485.getPrevious()) {
							if (local395.id == (local34 & 0x7FFF)) {
								local395.unlink();
								break;
							}
						}
						if (local485.peekPrevious() == null) {
							this.objects[this.currentPlane][local24][local31] = null;
						}
						this.updateObjectStack(local24, local31);
					}
				}
			} else {
				@Pc(572) int local572;
				@Pc(575) int local575;
				if (opcode == ZoneProt.MAP_PROJANIM) {
					local15 = arg1.g1();
					local24 = this.localPosX + (local15 >> 4 & 0x7);
					local31 = this.localPosZ + (local15 & 0x7);
					local34 = local24 + arg1.g1b();
					local38 = local31 + arg1.g1b();
					local42 = arg1.g2b();
					local47 = arg1.g2();
					local52 = arg1.g1();
					local572 = arg1.g1();
					local575 = arg1.g2();
					local108 = arg1.g2();
					local110 = arg1.g1();
					local112 = arg1.g1();
					if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104 && local34 >= 0 && local38 >= 0 && local34 < 104 && local38 < 104) {
						local24 = local24 * 128 + 64;
						local31 = local31 * 128 + 64;
						local34 = local34 * 128 + 64;
						local38 = local38 * 128 + 64;
						@Pc(657) ProjectileEntity local657 = new ProjectileEntity(local572, local110, local31, local108 + clientClock, this.currentPlane, local42, local575 + clientClock, local112, this.getLandY(this.currentPlane, local24, local31) - local52, local47, local24);
						local657.setTarget(this.getLandY(this.currentPlane, local34, local38) - local572, local38, local34, local575 + clientClock);
						this.projectiles.pushNext(local657);
					}
				} else if (opcode == ZoneProt.SPOTANIM_SPECIFIC) {
					local15 = arg1.g1();
					local24 = this.localPosX + (local15 >> 4 & 0x7);
					local31 = this.localPosZ + (local15 & 0x7);
					local34 = arg1.g2();
					local38 = arg1.g1();
					local42 = arg1.g2();
					if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
						local24 = local24 * 128 + 64;
						local31 = local31 * 128 + 64;
						@Pc(753) SpotAnimEntity local753 = new SpotAnimEntity(local24, local34, local31, local42, this.getLandY(this.currentPlane, local24, local31) - local38, this.currentPlane, clientClock);
						this.spotanims.pushNext(local753);
					}
				} else if (opcode == ZoneProt.OBJ_ADD) {
					local15 = arg1.g1();
					local24 = this.localPosX + (local15 >> 4 & 0x7);
					local31 = this.localPosZ + (local15 & 0x7);
					local34 = arg1.g2();
					local38 = arg1.g2();
					local42 = arg1.g2();
					if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104 && local42 != this.selfPlayerId) {
						@Pc(807) ObjEntity local807 = new ObjEntity();
						local807.id = local34;
						local807.count = local38;
						if (this.objects[this.currentPlane][local24][local31] == null) {
							this.objects[this.currentPlane][local24][local31] = new LinkedList();
						}
						this.objects[this.currentPlane][local24][local31].pushNext(local807);
						this.updateObjectStack(local24, local31);
					}
				} else {
					if (opcode == ZoneProt.LOC_ADD_CHANGE) {
						local15 = arg1.g1();
						local24 = this.localPosX + (local15 >> 4 & 0x7);
						local31 = this.localPosZ + (local15 & 0x7);
						local34 = arg1.g1();
						local38 = local34 >> 2;
						local42 = local34 & 0x3;
						local47 = this.objectGroups[local38];
						local52 = arg1.g2();
						local572 = arg1.g2();
						local575 = arg1.g2();
						local108 = arg1.g2();
						@Pc(905) byte local905 = arg1.g1b();
						@Pc(908) byte local908 = arg1.g1b();
						@Pc(911) byte local911 = arg1.g1b();
						@Pc(914) byte local914 = arg1.g1b();
						@Pc(921) PlayerEntity local921;
						if (local108 == this.selfPlayerId) {
							local921 = this.self;
						} else {
							local921 = this.playerEntities[local108];
						}
						if (local921 != null) {
							@Pc(946) TemporaryLoc local946 = new TemporaryLoc(this.currentPlane, local42, local31, local572 + clientClock, local38, -1, local24, local47);
							this.temporaryLocs.pushNext(local946);
							@Pc(966) TemporaryLoc local966 = new TemporaryLoc(this.currentPlane, local42, local31, local575 + clientClock, local38, local52, local24, local47);
							this.temporaryLocs.pushNext(local966);
							@Pc(980) int local980 = this.levelHeightMaps[this.currentPlane][local24][local31];
							@Pc(992) int local992 = this.levelHeightMaps[this.currentPlane][local24 + 1][local31];
							@Pc(1006) int local1006 = this.levelHeightMaps[this.currentPlane][local24 + 1][local31 + 1];
							@Pc(1018) int local1018 = this.levelHeightMaps[this.currentPlane][local24][local31 + 1];
							@Pc(1021) LocType local1021 = LocType.get(local52);
							local921.firstCycle = local572 + clientClock;
							local921.lastCycle = local575 + clientClock;
							local921.model = local1021.getModel(local38, local42, local980, local992, local1006, local1018, -1);
							@Pc(1045) int local1045 = local1021.sizeX;
							@Pc(1048) int local1048 = local1021.sizeZ;
							if (local42 == 1 || local42 == 3) {
								local1045 = local1021.sizeZ;
								local1048 = local1021.sizeX;
							}
							local921.sceneX = local24 * 128 + local1045 * 64;
							local921.sceneZ = local31 * 128 + local1048 * 64;
							local921.sceneY = this.getLandY(this.currentPlane, local921.sceneX, local921.sceneZ);
							@Pc(1094) byte local1094;
							if (local905 > local911) {
								local1094 = local905;
								local905 = local911;
								local911 = local1094;
							}
							if (local908 > local914) {
								local1094 = local908;
								local908 = local914;
								local914 = local1094;
							}
							local921.minTileX = local24 + local905;
							local921.maxTileX = local24 + local911;
							local921.minTileZ = local31 + local908;
							local921.maxTileZ = local31 + local914;
						}
					}
					if (opcode == ZoneProt.OBJ_COUNT) {
						local15 = arg1.g1();
						local24 = this.localPosX + (local15 >> 4 & 0x7);
						local31 = this.localPosZ + (local15 & 0x7);
						local34 = arg1.g2();
						local38 = arg1.g2();
						local42 = arg1.g2();
						if (local24 >= 0 && local31 >= 0 && local24 < 104 && local31 < 104) {
							@Pc(1178) LinkedList local1178 = this.objects[this.currentPlane][local24][local31];
							if (local1178 != null) {
								for (@Pc(1184) ObjEntity local1184 = (ObjEntity) local1178.peekPrevious(); local1184 != null; local1184 = (ObjEntity) local1178.getPrevious()) {
									if (local1184.id == (local34 & 0x7FFF) && local1184.count == local38) {
										local1184.count = local42;
										break;
									}
								}
								this.updateObjectStack(local24, local31);
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(B)I")
	private int getTopLevel() {
		@Pc(11) int local11 = 3;
		if (this.cameraPitch < 310) {
			@Pc(20) int local20 = this.cameraX >> 7;
			@Pc(25) int local25 = this.cameraZ >> 7;
			@Pc(31) int local31 = this.self.x >> 7;
			@Pc(37) int local37 = this.self.z >> 7;
			if ((this.levelRenderFlags[this.currentPlane][local20][local25] & 0x4) != 0) {
				local11 = this.currentPlane;
			}
			@Pc(59) int local59;
			if (local31 > local20) {
				local59 = local31 - local20;
			} else {
				local59 = local20 - local31;
			}
			@Pc(72) int local72;
			if (local37 > local25) {
				local72 = local37 - local25;
			} else {
				local72 = local25 - local37;
			}
			@Pc(87) int local87;
			@Pc(89) int local89;
			if (local59 > local72) {
				local87 = local72 * 65536 / local59;
				local89 = 32768;
				while (local20 != local31) {
					if (local20 < local31) {
						local20++;
					} else if (local20 > local31) {
						local20--;
					}
					if ((this.levelRenderFlags[this.currentPlane][local20][local25] & 0x4) != 0) {
						local11 = this.currentPlane;
					}
					local89 += local87;
					if (local89 >= 65536) {
						local89 -= 65536;
						if (local25 < local37) {
							local25++;
						} else if (local25 > local37) {
							local25--;
						}
						if ((this.levelRenderFlags[this.currentPlane][local20][local25] & 0x4) != 0) {
							local11 = this.currentPlane;
						}
					}
				}
			} else {
				local87 = local59 * 65536 / local72;
				local89 = 32768;
				while (local25 != local37) {
					if (local25 < local37) {
						local25++;
					} else if (local25 > local37) {
						local25--;
					}
					if ((this.levelRenderFlags[this.currentPlane][local20][local25] & 0x4) != 0) {
						local11 = this.currentPlane;
					}
					local89 += local87;
					if (local89 >= 65536) {
						local89 -= 65536;
						if (local20 < local31) {
							local20++;
						} else if (local20 > local31) {
							local20--;
						}
						if ((this.levelRenderFlags[this.currentPlane][local20][local25] & 0x4) != 0) {
							local11 = this.currentPlane;
						}
					}
				}
			}
		}
		if ((this.levelRenderFlags[this.currentPlane][this.self.x >> 7][this.self.z >> 7] & 0x4) != 0) {
			local11 = this.currentPlane;
		}
		return local11;
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(I)I")
	private int getCameraPlaneCutscene() {
		@Pc(9) int local9 = this.getLandY(this.currentPlane, this.cameraX, this.cameraZ);
		return local9 - this.cameraY >= 800 || (this.levelRenderFlags[this.currentPlane][this.cameraX >> 7][this.cameraZ >> 7] & 0x4) == 0 ? 3 : this.currentPlane;
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(I)V")
	private void drawViewport() {
		this.sceneCycle++;
		this.updateScenePlayers();
		this.updateSceneNpcs();
		this.updateSceneProjectiles();
		this.updateSceneSpotAnims();
		this.updateSceneSeqLocs();
		@Pc(34) int local34;
		@Pc(73) int local73;
		@Pc(122) int local122;
		if (!this.cameraOriented) {
			local34 = this.cameraOrbitPitch;
			if (this.cameraMaxY / 256 > local34) {
				local34 = this.cameraMaxY / 256;
			}
			if (this.customCameraActive[4] && this.cameraAmplitude[4] + 128 > local34) {
				local34 = this.cameraAmplitude[4] + 128;
			}
			local73 = this.cameraYaw + this.cameraAnticheatAngle & 0x7FF;
			this.updateCameraOrbit(this.getLandY(this.currentPlane, this.self.x, this.self.z) - 50, this.cameraOrbitX, local73, local34, this.cameraOrbitZ, local34 * 3 + 600);
			drawCounter++;
			if (drawCounter > 1802) {
				drawCounter = 0;
				this.outBuffer.p1isaac(146);
				this.outBuffer.p1(0);
				local122 = this.outBuffer.pos;
				this.outBuffer.p2(29711);
				this.outBuffer.p1(70);
				this.outBuffer.p1((int) (Math.random() * 256.0D));
				this.outBuffer.p1(242);
				this.outBuffer.p1(186);
				this.outBuffer.p1(39);
				this.outBuffer.p1(61);
				if ((int) (Math.random() * 2.0D) == 0) {
					this.outBuffer.p1(13);
				}
				if ((int) (Math.random() * 2.0D) == 0) {
					this.outBuffer.p2(57856);
				}
				this.outBuffer.p2((int) (Math.random() * 65536.0D));
				this.outBuffer.psize1(this.outBuffer.pos - local122);
			}
		}
		if (this.cameraOriented) {
			local34 = this.getCameraPlaneCutscene();
		} else {
			local34 = this.getTopLevel();
		}
		local73 = this.cameraX;
		local122 = this.cameraY;
		@Pc(209) int local209 = this.cameraZ;
		@Pc(212) int local212 = this.cameraPitch;
		@Pc(215) int local215 = this.cameraOrbitYaw;
		@Pc(264) int local264;
		for (@Pc(217) int local217 = 0; local217 < 5; local217++) {
			if (this.customCameraActive[local217]) {
				local264 = (int) (Math.random() * (double) (this.cameraJitter[local217] * 2 + 1) - (double) this.cameraJitter[local217] + Math.sin((double) this.unknownCameraVariable[local217] * ((double) this.cameraFrequency[local217] / 100.0D)) * (double) this.cameraAmplitude[local217]);
				if (local217 == 0) {
					this.cameraX += local264;
				}
				if (local217 == 1) {
					this.cameraY += local264;
				}
				if (local217 == 2) {
					this.cameraZ += local264;
				}
				if (local217 == 3) {
					this.cameraOrbitYaw = this.cameraOrbitYaw + local264 & 0x7FF;
				}
				if (local217 == 4) {
					this.cameraPitch += local264;
					if (this.cameraPitch < 128) {
						this.cameraPitch = 128;
					}
					if (this.cameraPitch > 383) {
						this.cameraPitch = 383;
					}
				}
			}
		}
		local264 = Draw3D.cycle;
		Model.allowInput = true;
		Model.resourceCount = 0;
		Model.cursorX = super.mouseX - 8;
		Model.cursorY = super.mouseY - 11;
		Draw2D.clear();
		this.mapSquare.draw(this.cameraOrbitYaw, this.cameraX, local34, this.cameraPitch, this.cameraY, this.cameraZ, 0, clientClock);
		this.mapSquare.clearFrameLocs();
		this.drawViewport2d();
		this.drawTileHint();
		this.updateAnimatedTextures(local264);
		this.drawViewport3d();
		this.areaViewport.drawAt(11, super.graphics, 8);
		this.cameraX = local73;
		this.cameraY = local122;
		this.cameraZ = local209;
		this.cameraPitch = local212;
		this.cameraOrbitYaw = local215;
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(Z)V")
	private void runMidi() {
		this.startMidiThread = false;
		while (this.midiThreadActive) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(11) Exception local11) {
			}
			@Pc(19) String local19;
			@Pc(22) int local22;
			@Pc(25) int local25;
			synchronized (this.midiSync) {
				local19 = this.midiSyncName;
				local22 = this.midiSyncCrc;
				local25 = this.midiSyncLen;
				this.midiSyncName = null;
				this.midiSyncCrc = 0;
				this.midiSyncLen = 0;
			}
			if (local19 != null) {
				@Pc(52) byte[] local52 = SignedLink.cacheload(local19 + ".mid");
				@Pc(69) int local69;
				if (local52 != null && local22 != 12345678) {
					local69 = Buffer.getcrc(local52);
					if (local69 != local22) {
						local52 = null;
					}
				}
				if (local52 == null) {
					try {
						@Pc(91) DataInputStream local91 = this.openStream(local19 + "_" + local22 + ".mid");
						local52 = new byte[local25];
						@Pc(106) int local106;
						for (@Pc(96) int local96 = 0; local96 < local25; local96 += local106) {
							local106 = local91.read(local52, local96, local25 - local96);
							if (local106 == -1) {
								@Pc(112) byte[] local112 = new byte[local96];
								System.arraycopy(local52, 0, local112, 0, local96);
								local52 = local112;
								local25 = local96;
								break;
							}
						}
						local91.close();
						SignedLink.cachesave(local19 + ".mid", local52);
					} catch (@Pc(153) Exception local153) {
					}
				}
				if (local52 == null) {
					return;
				}
				local69 = (new Buffer(local52)).g4();
				@Pc(167) byte[] local167 = new byte[local69];
				BZip2InputStream.read(local167, local69, local52, local25, 4);
				this.midisave(local167, local69, true);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(Z)V")
	private void drawFlames() {
		@Pc(3) short local3 = 256;
		@Pc(8) int local8;
		if (this.flameCycle1 > 0) {
			for (local8 = 0; local8 < 256; local8++) {
				if (this.flameCycle1 > 768) {
					this.flameGradient[local8] = this.mix(this.flameGradientRed[local8], 1024 - this.flameCycle1, this.flameGradientGreen[local8]);
				} else if (this.flameCycle1 > 256) {
					this.flameGradient[local8] = this.flameGradientGreen[local8];
				} else {
					this.flameGradient[local8] = this.mix(this.flameGradientGreen[local8], 256 - this.flameCycle1, this.flameGradientRed[local8]);
				}
			}
		} else if (this.flameCycle2 > 0) {
			for (local8 = 0; local8 < 256; local8++) {
				if (this.flameCycle2 > 768) {
					this.flameGradient[local8] = this.mix(this.flameGradientRed[local8], 1024 - this.flameCycle2, this.flameGradientBlue[local8]);
				} else if (this.flameCycle2 > 256) {
					this.flameGradient[local8] = this.flameGradientBlue[local8];
				} else {
					this.flameGradient[local8] = this.mix(this.flameGradientBlue[local8], 256 - this.flameCycle2, this.flameGradientRed[local8]);
				}
			}
		} else {
			for (local8 = 0; local8 < 256; local8++) {
				this.flameGradient[local8] = this.flameGradientRed[local8];
			}
		}
		for (local8 = 0; local8 < 33920; local8++) {
			this.titleLeft.pixels[local8] = this.imageFlamesLeft.pixels[local8];
		}
		@Pc(181) int local181 = 0;
		@Pc(183) int local183 = 1152;
		@Pc(198) int local198;
		@Pc(202) int local202;
		@Pc(212) int local212;
		@Pc(220) int local220;
		@Pc(224) int local224;
		@Pc(228) int local228;
		@Pc(239) int local239;
		for (@Pc(185) int local185 = 1; local185 < local3 - 1; local185++) {
			local198 = this.flameShiftX[local185] * (local3 - local185) / local3;
			local202 = local198 + 22;
			if (local202 < 0) {
				local202 = 0;
			}
			local181 += local202;
			for (local212 = local202; local212 < 128; local212++) {
				local220 = this.flameIntensity[local181++];
				if (local220 == 0) {
					local183++;
				} else {
					local224 = local220;
					local228 = 256 - local220;
					local220 = this.flameGradient[local220];
					local239 = this.titleLeft.pixels[local183];
					this.titleLeft.pixels[local183++] = ((local220 & 0xFF00FF) * local224 + (local239 & 0xFF00FF) * local228 & 0xFF00FF00) + ((local220 & 0xFF00) * local224 + (local239 & 0xFF00) * local228 & 0xFF0000) >> 8;
				}
			}
			local183 += local202;
		}
		this.titleLeft.drawAt(0, super.graphics, 0);
		for (local198 = 0; local198 < 33920; local198++) {
			this.titleRight.pixels[local198] = this.imageFlamesRight.pixels[local198];
		}
		local181 = 0;
		local183 = 1176;
		for (local202 = 1; local202 < local3 - 1; local202++) {
			local212 = this.flameShiftX[local202] * (local3 - local202) / local3;
			local220 = 103 - local212;
			local183 += local212;
			for (local224 = 0; local224 < local220; local224++) {
				local228 = this.flameIntensity[local181++];
				if (local228 == 0) {
					local183++;
				} else {
					local239 = local228;
					@Pc(362) int local362 = 256 - local228;
					local228 = this.flameGradient[local228];
					@Pc(373) int local373 = this.titleRight.pixels[local183];
					this.titleRight.pixels[local183++] = ((local228 & 0xFF00FF) * local239 + (local373 & 0xFF00FF) * local362 & 0xFF00FF00) + ((local228 & 0xFF00) * local239 + (local373 & 0xFF00) * local362 & 0xFF0000) >> 8;
				}
			}
			local181 += 128 - local220;
			local183 += 128 - local220 - local212;
		}
		this.titleRight.drawAt(0, super.graphics, 661);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIILclient!hc;III)V")
	private void updateInterface(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Component arg3, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		if (arg3.type == 0 && arg3.children != null && !arg3.hide && (arg1 >= arg5 && arg0 >= arg2 && arg1 <= arg5 + arg3.width && arg0 <= arg2 + arg3.height)) {
			@Pc(34) int local34 = arg3.children.length;
			for (@Pc(44) int local44 = 0; local44 < local34; local44++) {
				@Pc(53) int local53 = arg3.childX[local44] + arg5;
				@Pc(62) int local62 = arg3.childY[local44] + arg2 - arg6;
				@Pc(69) Component local69 = Component.instances[arg3.children[local44]];
				@Pc(74) int local74 = local53 + local69.x;
				@Pc(79) int local79 = local62 + local69.y;
				if ((local69.overlayer >= 0 || local69.overcolour != 0) && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
					if (local69.overlayer >= 0) {
						this.hoveredInterfaceIndex = local69.overlayer;
					} else {
						this.hoveredInterfaceIndex = local69.id;
					}
				}
				if (local69.type == 0) {
					this.updateInterface(arg0, arg1, local79, local69, local74, local69.scrollY);
					if (local69.scrollHeight > local69.height) {
						this.updateInterfaceScrollbar(arg1, arg0, local69.scrollHeight, local69.height, true, local74 + local69.width, local79, local69);
					}
				} else {
					if (local69.buttontype == 1 && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						@Pc(176) boolean local176 = false;
						if (local69.clientcode != 0) {
							local176 = this.updateInterfaceTooltip(local69);
						}
						if (!local176) {
							this.options[this.optionCount] = local69.option;
							this.optionType[this.optionCount] = 951;
							this.optionParamB[this.optionCount] = local69.id;
							this.optionCount++;
						}
					}
					if (local69.buttontype == 2 && this.selectedSpell == 0 && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						@Pc(240) String local240 = local69.optionCircumfix;
						if (local240.indexOf(" ") != -1) {
							local240 = local240.substring(0, local240.indexOf(" "));
						}
						this.options[this.optionCount] = local240 + " @gre@" + local69.optionSuffix;
						this.optionType[this.optionCount] = 930;
						this.optionParamB[this.optionCount] = local69.id;
						this.optionCount++;
					}
					if (local69.buttontype == 3 && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						this.options[this.optionCount] = "Close";
						this.optionType[this.optionCount] = 947;
						this.optionParamB[this.optionCount] = local69.id;
						this.optionCount++;
					}
					if (local69.buttontype == 4 && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						this.options[this.optionCount] = local69.option;
						this.optionType[this.optionCount] = 465;
						this.optionParamB[this.optionCount] = local69.id;
						this.optionCount++;
					}
					if (local69.buttontype == 5 && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						this.options[this.optionCount] = local69.option;
						this.optionType[this.optionCount] = 960;
						this.optionParamB[this.optionCount] = local69.id;
						this.optionCount++;
					}
					if (local69.buttontype == 6 && !this.chatContinuingDialogue && arg1 >= local74 && arg0 >= local79 && arg1 < local74 + local69.width && arg0 < local79 + local69.height) {
						this.options[this.optionCount] = local69.option;
						this.optionType[this.optionCount] = 44;
						this.optionParamB[this.optionCount] = local69.id;
						this.optionCount++;
					}
					if (local69.type == 2) {
						@Pc(488) int local488 = 0;
						for (@Pc(490) int local490 = 0; local490 < local69.height; local490++) {
							for (@Pc(494) int local494 = 0; local494 < local69.width; local494++) {
								@Pc(505) int local505 = local74 + local494 * (local69.inventoryMarginX + 32);
								@Pc(514) int local514 = local79 + local490 * (local69.inventoryMarginY + 32);
								if (local488 < 20) {
									local505 += local69.inventoryOffsetX[local488];
									local514 += local69.inventoryOffsetY[local488];
								}
								if (arg1 >= local505 && arg0 >= local514 && arg1 < local505 + 32 && arg0 < local514 + 32) {
									this.hoveredSlot = local488;
									this.hoveredSlotParentId = local69.id;
									if (local69.inventoryIndices[local488] > 0) {
										@Pc(567) ObjType local567 = ObjType.get(local69.inventoryIndices[local488] - 1);
										if (this.selectedObject == 1 && local69.inventoryHasOptions) {
											if (local69.id != this.selectedObjInterface || local488 != this.selectedObjSlot) {
												this.options[this.optionCount] = "Use " + this.selectedObjName + " with @lre@" + local567.name;
												this.optionType[this.optionCount] = 881;
												this.optionParamC[this.optionCount] = local567.id;
												this.optionParamA[this.optionCount] = local488;
												this.optionParamB[this.optionCount] = local69.id;
												this.optionCount++;
											}
										} else if (this.selectedSpell != 1 || !local69.inventoryHasOptions) {
											@Pc(704) int local704;
											if (local69.inventoryHasOptions) {
												for (local704 = 4; local704 >= 3; local704--) {
													if (local567.iops != null && local567.iops[local704] != null) {
														this.options[this.optionCount] = local567.iops[local704] + " @lre@" + local567.name;
														if (local704 == 3) {
															this.optionType[this.optionCount] = 478;
														}
														if (local704 == 4) {
															this.optionType[this.optionCount] = 347;
														}
														this.optionParamC[this.optionCount] = local567.id;
														this.optionParamA[this.optionCount] = local488;
														this.optionParamB[this.optionCount] = local69.id;
														this.optionCount++;
													} else if (local704 == 4) {
														this.options[this.optionCount] = "Drop @lre@" + local567.name;
														this.optionType[this.optionCount] = 347;
														this.optionParamC[this.optionCount] = local567.id;
														this.optionParamA[this.optionCount] = local488;
														this.optionParamB[this.optionCount] = local69.id;
														this.optionCount++;
													}
												}
											}
											if (local69.inventoryIsUsable) {
												this.options[this.optionCount] = "Use @lre@" + local567.name;
												this.optionType[this.optionCount] = 188;
												this.optionParamC[this.optionCount] = local567.id;
												this.optionParamA[this.optionCount] = local488;
												this.optionParamB[this.optionCount] = local69.id;
												this.optionCount++;
											}
											if (local69.inventoryHasOptions && local567.iops != null) {
												for (local704 = 2; local704 >= 0; local704--) {
													if (local567.iops[local704] != null) {
														this.options[this.optionCount] = local567.iops[local704] + " @lre@" + local567.name;
														if (local704 == 0) {
															this.optionType[this.optionCount] = 405;
														}
														if (local704 == 1) {
															this.optionType[this.optionCount] = 38;
														}
														if (local704 == 2) {
															this.optionType[this.optionCount] = 422;
														}
														this.optionParamC[this.optionCount] = local567.id;
														this.optionParamA[this.optionCount] = local488;
														this.optionParamB[this.optionCount] = local69.id;
														this.optionCount++;
													}
												}
											}
											if (local69.inventoryOptions != null) {
												for (local704 = 4; local704 >= 0; local704--) {
													if (local69.inventoryOptions[local704] != null) {
														this.options[this.optionCount] = local69.inventoryOptions[local704] + " @lre@" + local567.name;
														if (local704 == 0) {
															this.optionType[this.optionCount] = 602;
														}
														if (local704 == 1) {
															this.optionType[this.optionCount] = 596;
														}
														if (local704 == 2) {
															this.optionType[this.optionCount] = 22;
														}
														if (local704 == 3) {
															this.optionType[this.optionCount] = 892;
														}
														if (local704 == 4) {
															this.optionType[this.optionCount] = 415;
														}
														this.optionParamC[this.optionCount] = local567.id;
														this.optionParamA[this.optionCount] = local488;
														this.optionParamB[this.optionCount] = local69.id;
														this.optionCount++;
													}
												}
											}
											this.options[this.optionCount] = "Examine @lre@" + local567.name;
											this.optionType[this.optionCount] = 1773;
											this.optionParamC[this.optionCount] = local567.id;
											this.optionParamB[this.optionCount] = local69.inventoryAmount[local488];
											this.optionCount++;
										} else if ((this.selectedFlags & 0x10) == 16) {
											this.options[this.optionCount] = this.selectedSpellPrefix + " @lre@" + local567.name;
											this.optionType[this.optionCount] = 391;
											this.optionParamC[this.optionCount] = local567.id;
											this.optionParamA[this.optionCount] = local488;
											this.optionParamB[this.optionCount] = local69.id;
											this.optionCount++;
										}
									}
								}
								local488++;
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(I)V")
	private void updateChatSettingInput() {
		if (super.mouseButton == 1) {
			if (super.clickX >= 8 && super.clickX <= 108 && super.clickY >= 490 && super.clickY <= 522) {
				this.chatPublicSetting = (this.chatPublicSetting + 1) % 4;
				this.redrawChat = true;
				this.redrawChatback = true;
				this.outBuffer.p1isaac(244);
				this.outBuffer.p1(this.chatPublicSetting);
				this.outBuffer.p1(this.chatPrivateSetting);
				this.outBuffer.p1(this.chatTradeDuelSetting);
			}
			if (super.clickX >= 137 && super.clickX <= 237 && super.clickY >= 490 && super.clickY <= 522) {
				this.chatPrivateSetting = (this.chatPrivateSetting + 1) % 3;
				this.redrawChat = true;
				this.redrawChatback = true;
				this.outBuffer.p1isaac(244);
				this.outBuffer.p1(this.chatPublicSetting);
				this.outBuffer.p1(this.chatPrivateSetting);
				this.outBuffer.p1(this.chatTradeDuelSetting);
			}
			if (super.clickX >= 275 && super.clickX <= 375 && super.clickY >= 490 && super.clickY <= 522) {
				this.chatTradeDuelSetting = (this.chatTradeDuelSetting + 1) % 3;
				this.redrawChat = true;
				this.redrawChatback = true;
				this.outBuffer.p1isaac(244);
				this.outBuffer.p1(this.chatPublicSetting);
				this.outBuffer.p1(this.chatPrivateSetting);
				this.outBuffer.p1(this.chatTradeDuelSetting);
			}
			if (super.clickX >= 416 && super.clickX <= 516 && super.clickY >= 490 && super.clickY <= 522) {
				this.closeInterface();
				this.reportInput = "";
				this.reportAbuseMuteToggle = false;
				for (@Pc(186) int local186 = 0; local186 < Component.instances.length; local186++) {
					if (Component.instances[local186] != null && Component.instances[local186].clientcode == 600) {
						this.openInterfaceId = this.viewportInterfaceIndex = Component.instances[local186].parent;
						return;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(III)V")
	private void updatePlayerTooltip(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2) {
		@Pc(1) int local1 = 0;
		for (@Pc(3) int local3 = 0; local3 < 100; local3++) {
			if (this.chatMessage[local3] != null) {
				@Pc(15) int local15 = this.chatMessageType[local3];
				@Pc(26) int local26 = this.chatScrollAmount + 70 + 4 - local1 * 14;
				if (local26 < -20) {
					break;
				}
				if (local15 == 0) {
					local1++;
				}
				if ((local15 == 1 || local15 == 2) && (local15 == 1 || this.chatPublicSetting == 0 || this.chatPublicSetting == 1 && this.isFriend(this.chatMessagePrefix[local3]))) {
					if (arg0 > local26 - 14 && arg0 <= local26 && !this.chatMessagePrefix[local3].equals(this.self.name)) {
						if (this.modRights) {
							this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[local3];
							this.optionType[this.optionCount] = 34;
							this.optionCount++;
						}
						this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 436;
						this.optionCount++;
						this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 406;
						this.optionCount++;
					}
					local1++;
				}
				if ((local15 == 3 || local15 == 7) && this.splitPrivateChat == 0 && (local15 == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[local3]))) {
					if (arg0 > local26 - 14 && arg0 <= local26) {
						if (this.modRights) {
							this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[local3];
							this.optionType[this.optionCount] = 34;
							this.optionCount++;
						}
						this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 436;
						this.optionCount++;
						this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 406;
						this.optionCount++;
					}
					local1++;
				}
				if (local15 == 4 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[local3]))) {
					if (arg0 > local26 - 14 && arg0 <= local26) {
						this.options[this.optionCount] = "Accept trade @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 903;
						this.optionCount++;
					}
					local1++;
				}
				if ((local15 == 5 || local15 == 6) && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
					local1++;
				}
				if (local15 == 8 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[local3]))) {
					if (arg0 > local26 - 14 && arg0 <= local26) {
						this.options[this.optionCount] = "Accept duel @whi@" + this.chatMessagePrefix[local3];
						this.optionType[this.optionCount] = 363;
						this.optionCount++;
					}
					local1++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(I)V")
	private void updateScenePlayers() {
		if (this.self.x >> 7 == this.flagTileX && this.self.z >> 7 == this.flagTileY) {
			this.flagTileX = 0;
		}
		for (@Pc(22) int local22 = -1; local22 < this.playerCount; local22++) {
			@Pc(30) PlayerEntity local30;
			@Pc(35) int local35;
			if (local22 == -1) {
				local30 = this.self;
				local35 = this.LOCAL_PLAYER_INDEX << 14;
			} else {
				local30 = this.playerEntities[this.playerIndices[local22]];
				local35 = this.playerIndices[local22] << 14;
			}
			if (local30 != null && local30.isVisible()) {
				local30.lowMemory = false;
				if ((lowMemory && this.playerCount > 50 || this.playerCount > 200) && local22 != -1 && local30.secondarySeq == local30.standSeq) {
					local30.lowMemory = true;
				}
				@Pc(87) int local87 = local30.x >> 7;
				@Pc(92) int local92 = local30.z >> 7;
				if (local87 >= 0 && local87 < 104 && local92 >= 0 && local92 < 104) {
					if (local30.model == null || clientClock < local30.firstCycle || clientClock >= local30.lastCycle) {
						if ((local30.x & 0x7F) == 64 && (local30.z & 0x7F) == 64) {
							if (this.tileRenderCount[local87][local92] == this.sceneCycle) {
								continue;
							}
							this.tileRenderCount[local87][local92] = this.sceneCycle;
						}
						local30.plane = this.getLandY(this.currentPlane, local30.x, local30.z);
						this.mapSquare.add(local30.z, 60, local30.animationDelay, local30.x, local35, local30.needsForwardDrawPadding, null, local30, local30.plane, this.currentPlane);
					} else {
						local30.lowMemory = false;
						local30.plane = this.getLandY(this.currentPlane, local30.x, local30.z);
						this.mapSquare.add(local30.maxTileX, null, local30.z, local30.plane, local35, local30.animationDelay, local30.minTileZ, local30.minTileX, local30, this.currentPlane, local30.maxTileZ, local30.x);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIBI)I")
	private int getLandY(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		@Pc(11) int local11 = arg1 >> 7;
		@Pc(15) int local15 = arg3 >> 7;
		@Pc(17) int local17 = arg0;
		if (arg0 < 3 && (this.levelRenderFlags[1][local11][local15] & 0x2) == 2) {
			local17 = arg0 + 1;
		}
		@Pc(37) int local37 = arg1 & 0x7F;
		@Pc(41) int local41 = arg3 & 0x7F;
		@Pc(69) int local69 = this.levelHeightMaps[local17][local11][local15] * (128 - local37) + this.levelHeightMaps[local17][local11 + 1][local15] * local37 >> 7;
		@Pc(101) int local101 = this.levelHeightMaps[local17][local11][local15 + 1] * (128 - local37) + this.levelHeightMaps[local17][local11 + 1][local15 + 1] * local37 >> 7;
		return local69 * (128 - local41) + local101 * local41 >> 7;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!bc;IIII)V")
	private void drawTooltip(@OriginalArg(0) NpcType arg0, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (this.optionCount < 400) {
			@Pc(16) String local16 = arg0.name;
			if (arg0.vislevel != 0) {
				local16 = local16 + getLevelColorTag(this.self.combatLevel, arg0.vislevel) + " (level-" + arg0.vislevel + ")";
			}
			if (this.selectedObject == 1) {
				this.options[this.optionCount] = "Use " + this.selectedObjName + " with @yel@" + local16;
				this.optionType[this.optionCount] = 900;
				this.optionParamC[this.optionCount] = arg4;
				this.optionParamA[this.optionCount] = arg3;
				this.optionParamB[this.optionCount] = arg2;
				this.optionCount++;
			} else if (this.selectedSpell != 1) {
				@Pc(155) int local155;
				if (arg0.ops != null) {
					for (local155 = 4; local155 >= 0; local155--) {
						if (arg0.ops[local155] != null && !arg0.ops[local155].equalsIgnoreCase("attack")) {
							this.options[this.optionCount] = arg0.ops[local155] + " @yel@" + local16;
							if (local155 == 0) {
								this.optionType[this.optionCount] = 728;
							}
							if (local155 == 1) {
								this.optionType[this.optionCount] = 542;
							}
							if (local155 == 2) {
								this.optionType[this.optionCount] = 6;
							}
							if (local155 == 3) {
								this.optionType[this.optionCount] = 963;
							}
							if (local155 == 4) {
								this.optionType[this.optionCount] = 245;
							}
							this.optionParamC[this.optionCount] = arg4;
							this.optionParamA[this.optionCount] = arg3;
							this.optionParamB[this.optionCount] = arg2;
							this.optionCount++;
						}
					}
				}
				if (arg0.ops != null) {
					for (local155 = 4; local155 >= 0; local155--) {
						if (arg0.ops[local155] != null && arg0.ops[local155].equalsIgnoreCase("attack")) {
							@Pc(279) short local279 = 0;
							if (arg0.vislevel > this.self.combatLevel) {
								local279 = 2000;
							}
							this.options[this.optionCount] = arg0.ops[local155] + " @yel@" + local16;
							if (local155 == 0) {
								this.optionType[this.optionCount] = local279 + 728;
							}
							if (local155 == 1) {
								this.optionType[this.optionCount] = local279 + 542;
							}
							if (local155 == 2) {
								this.optionType[this.optionCount] = local279 + 6;
							}
							if (local155 == 3) {
								this.optionType[this.optionCount] = local279 + 963;
							}
							if (local155 == 4) {
								this.optionType[this.optionCount] = local279 + 245;
							}
							this.optionParamC[this.optionCount] = arg4;
							this.optionParamA[this.optionCount] = arg3;
							this.optionParamB[this.optionCount] = arg2;
							this.optionCount++;
						}
					}
				}
				this.options[this.optionCount] = "Examine @yel@" + local16;
				this.optionType[this.optionCount] = 1607;
				this.optionParamC[this.optionCount] = arg4;
				this.optionParamA[this.optionCount] = arg3;
				this.optionParamB[this.optionCount] = arg2;
				this.optionCount++;
			} else if ((this.selectedFlags & 0x2) == 2) {
				this.options[this.optionCount] = this.selectedSpellPrefix + " @yel@" + local16;
				this.optionType[this.optionCount] = 265;
				this.optionParamC[this.optionCount] = arg4;
				this.optionParamA[this.optionCount] = arg3;
				this.optionParamB[this.optionCount] = arg2;
				this.optionCount++;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "l", descriptor = "(I)V")
	private void updateKeyboardInput() {
		while (true) {
			@Pc(13) int local13;
			do {
				while (true) {
					local13 = this.pollKey();
					if (local13 == -1) {
						return;
					}
					if (this.viewportInterfaceIndex != -1 && this.viewportInterfaceIndex == this.openInterfaceId) {
						if (local13 == 8 && this.reportInput.length() > 0) {
							this.reportInput = this.reportInput.substring(0, this.reportInput.length() - 1);
						}
						break;
					}
					@Pc(193) int local193;
					if (this.showSocialInput) {
						if (local13 >= 32 && local13 <= 122 && this.socialInput.length() < 80) {
							this.socialInput = this.socialInput + (char) local13;
							this.redrawChatback = true;
						}
						if (local13 == 8 && this.socialInput.length() > 0) {
							this.socialInput = this.socialInput.substring(0, this.socialInput.length() - 1);
							this.redrawChatback = true;
						}
						if (local13 == 13 || local13 == 10) {
							this.showSocialInput = false;
							this.redrawChatback = true;
							@Pc(153) long local153;
							if (this.socialAction == 1) {
								local153 = StringUtils.toBase37(this.socialInput);
								this.addFriend(local153);
							}
							if (this.socialAction == 2 && this.friendCount > 0) {
								local153 = StringUtils.toBase37(this.socialInput);
								this.removeFriend(local153);
							}
							if (this.socialAction == 3 && this.socialInput.length() > 0) {
								this.outBuffer.p1isaac(148);
								this.outBuffer.p1(0);
								local193 = this.outBuffer.pos;
								this.outBuffer.p8(this.socialName37);
								TextEncoder.write(this.outBuffer, true, this.socialInput);
								this.outBuffer.psize1(this.outBuffer.pos - local193);
								this.socialInput = StringUtils.toSentenceCase(this.socialInput);
								this.socialInput = WordPack.getFiltered(this.socialInput);
								this.addMessage(6, this.socialInput, StringUtils.formatName(StringUtils.fromBase37(this.socialName37)));
								if (this.chatPrivateSetting == 2) {
									this.chatPrivateSetting = 1;
									this.redrawChat = true;
									this.outBuffer.p1isaac(244);
									this.outBuffer.p1(this.chatPublicSetting);
									this.outBuffer.p1(this.chatPrivateSetting);
									this.outBuffer.p1(this.chatTradeDuelSetting);
								}
							}
							if (this.socialAction == 4 && this.ignoreCount < 100) {
								local153 = StringUtils.toBase37(this.socialInput);
								this.addIgnore(local153);
							}
							if (this.socialAction == 5 && this.ignoreCount > 0) {
								local153 = StringUtils.toBase37(this.socialInput);
								this.removeIgnore(local153);
							}
						}
					} else if (this.checkInputType) {
						if (local13 >= 48 && local13 <= 57 && this.chatbackInput.length() < 10) {
							this.chatbackInput = this.chatbackInput + (char) local13;
							this.redrawChatback = true;
						}
						if (local13 == 8 && this.chatbackInput.length() > 0) {
							this.chatbackInput = this.chatbackInput.substring(0, this.chatbackInput.length() - 1);
							this.redrawChatback = true;
						}
						if (local13 == 13 || local13 == 10) {
							if (this.chatbackInput.length() > 0) {
								local193 = 0;
								try {
									local193 = Integer.parseInt(this.chatbackInput);
								} catch (@Pc(369) Exception local369) {
								}
								this.outBuffer.p1isaac(237);
								this.outBuffer.p4(local193);
							}
							this.checkInputType = false;
							this.redrawChatback = true;
						}
					} else if (this.chatbackComponentId == -1) {
						if (local13 >= 32 && local13 <= 122 && this.input.length() < 80) {
							this.input = this.input + (char) local13;
							this.redrawChatback = true;
						}
						if (local13 == 8 && this.input.length() > 0) {
							this.input = this.input.substring(0, this.input.length() - 1);
							this.redrawChatback = true;
						}
						if ((local13 == 13 || local13 == 10) && this.input.length() > 0) {
							if (this.input.equals("::clientdrop") && (super.frame != null || this.getHost().indexOf("192.168.1.") != -1)) {
								this.reconnect();
							} else if (this.input.equals("::outline")) {
								GlobalConfig.SHOW_HIGHLIGHT_OUTLINE = !GlobalConfig.SHOW_HIGHLIGHT_OUTLINE;
								this.redrawSidebar = true;
							} else if (this.input.equals("::bilinear")) {
								GlobalConfig.MINIMAP_BILINEAR_FILTERING = !GlobalConfig.MINIMAP_BILINEAR_FILTERING;
							} else if (this.input.equals("::viewport")) {
								GlobalConfig.FULL_512PX_VIEWPORT = !GlobalConfig.FULL_512PX_VIEWPORT;
							} else if (this.input.equals("::transparency")) {
								GlobalConfig.FIX_TRANSPARENCY_OVERFLOW = !GlobalConfig.FIX_TRANSPARENCY_OVERFLOW;
							} else if (input.equals("::chat 1")) {
								GlobalConfig.CHATBOX_ERA = 1;
								this.redrawChat = true;
							} else if (input.equals("::chat 2")) {
								GlobalConfig.CHATBOX_ERA = 2;
								this.redrawChat = true;
							} else if (input.equals("::chat 3")) {
								GlobalConfig.CHATBOX_ERA = 3;
								this.redrawChat = true;
							} else if (this.input.equals("::authentic")) {
								GlobalConfig.SHOW_HIGHLIGHT_OUTLINE = false;
								GlobalConfig.MINIMAP_BILINEAR_FILTERING = false;
								GlobalConfig.FULL_512PX_VIEWPORT = false;
								GlobalConfig.FIX_TRANSPARENCY_OVERFLOW = false;
								GlobalConfig.CHATBOX_ERA = 3;
								this.redrawSidebar = true;
								this.redrawChat = true;
							} else if (this.input.equals("::qol")) {
								GlobalConfig.SHOW_HIGHLIGHT_OUTLINE = true;
								GlobalConfig.MINIMAP_BILINEAR_FILTERING = true;
								GlobalConfig.FULL_512PX_VIEWPORT = true;
								GlobalConfig.FIX_TRANSPARENCY_OVERFLOW = true;
								GlobalConfig.CHATBOX_ERA = 3;
								this.redrawSidebar = true;
								this.redrawChat = true;
							} else if (this.input.startsWith("::")) {
								this.outBuffer.p1isaac(4);
								this.outBuffer.p1(this.input.length() - 1);
								this.outBuffer.pjstr(this.input.substring(2));
							} else {
								@Pc(496) byte local496 = 0;
								if (this.input.startsWith("yellow:")) {
									local496 = 0;
									this.input = this.input.substring(7);
								}
								if (this.input.startsWith("red:")) {
									local496 = 1;
									this.input = this.input.substring(4);
								}
								if (this.input.startsWith("green:")) {
									local496 = 2;
									this.input = this.input.substring(6);
								}
								if (this.input.startsWith("cyan:")) {
									local496 = 3;
									this.input = this.input.substring(5);
								}
								if (this.input.startsWith("purple:")) {
									local496 = 4;
									this.input = this.input.substring(7);
								}
								if (this.input.startsWith("white:")) {
									local496 = 5;
									this.input = this.input.substring(6);
								}
								if (this.input.startsWith("flash1:")) {
									local496 = 6;
									this.input = this.input.substring(7);
								}
								if (this.input.startsWith("flash2:")) {
									local496 = 7;
									this.input = this.input.substring(7);
								}
								if (this.input.startsWith("flash3:")) {
									local496 = 8;
									this.input = this.input.substring(7);
								}
								if (this.input.startsWith("glow1:")) {
									local496 = 9;
									this.input = this.input.substring(6);
								}
								if (this.input.startsWith("glow2:")) {
									local496 = 10;
									this.input = this.input.substring(6);
								}
								if (this.input.startsWith("glow3:")) {
									local496 = 11;
									this.input = this.input.substring(6);
								}
								@Pc(654) byte local654 = 0;
								if (this.input.startsWith("wave:")) {
									local654 = 1;
									this.input = this.input.substring(5);
								}
								if (this.input.startsWith("scroll:")) {
									local654 = 2;
									this.input = this.input.substring(7);
								}
								this.outBuffer.p1isaac(158);
								this.outBuffer.p1(0);
								@Pc(693) int local693 = this.outBuffer.pos;
								this.outBuffer.p1(local496);
								this.outBuffer.p1(local654);
								TextEncoder.write(this.outBuffer, true, this.input);
								this.outBuffer.psize1(this.outBuffer.pos - local693);
								this.input = StringUtils.toSentenceCase(this.input);
								this.input = WordPack.getFiltered(this.input);
								this.self.spoken = this.input;
								this.self.spokenColor = local496;
								this.self.spokenEffect = local654;
								this.self.chatTimer = 150;
								this.addMessage(2, this.self.spoken, this.self.name);
								if (this.chatPublicSetting == 2) {
									this.chatPublicSetting = 3;
									this.redrawChat = true;
									this.outBuffer.p1isaac(244);
									this.outBuffer.p1(this.chatPublicSetting);
									this.outBuffer.p1(this.chatPrivateSetting);
									this.outBuffer.p1(this.chatTradeDuelSetting);
								}
							}
							this.input = "";
							this.redrawChatback = true;
						}
					}
				}
			} while ((local13 < 97 || local13 > 122) && (local13 < 65 || local13 > 90) && (local13 < 48 || local13 > 57) && local13 != 32);
			if (this.reportInput.length() < 12) {
				this.reportInput = this.reportInput + (char) local13;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Z)V")
	@Override
	protected void draw() {
		if (this.errorStarted || this.errorLoading || this.errorHost) {
			this.drawErrorScreen();
		} else {
			if (this.ingame) {
				this.drawGame();
			} else {
				this.drawTitleScreen();
			}
			this.dragCycle = 0;
		}
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(B)V")
	private void updateTitle() {
		@Pc(17) int local17;
		@Pc(24) int local24;
		if (this.titleState == 0) {
			local17 = super.gameWidth / 2 - 80;
			local24 = super.gameHeight / 2 + 20;
			local24 += 20;
			if (super.mouseButton == 1 && super.clickX >= local17 - 75 && super.clickX <= local17 + 75 && super.clickY >= local24 - 20 && super.clickY <= local24 + 20) {
				this.titleState = 3;
				this.loginFocusedLine = 0;
			}
			local17 = super.gameWidth / 2 + 80;
			if (super.mouseButton == 1 && super.clickX >= local17 - 75 && super.clickX <= local17 + 75 && super.clickY >= local24 - 20 && super.clickY <= local24 + 20) {
				this.loginMessage1 = "";
				this.loginMessage2 = "Enter your username & password.";
				this.titleState = 2;
				this.loginFocusedLine = 0;
			}
		} else if (this.titleState == 2) {
			local17 = super.gameHeight / 2 - 40;
			local17 += 30;
			local17 += 25;
			if (super.mouseButton == 1 && super.clickY >= local17 - 15 && super.clickY < local17) {
				this.loginFocusedLine = 0;
			}
			local17 += 15;
			if (super.mouseButton == 1 && super.clickY >= local17 - 15 && super.clickY < local17) {
				this.loginFocusedLine = 1;
			}
			local17 += 15;
			local24 = super.gameWidth / 2 - 80;
			@Pc(170) int local170 = super.gameHeight / 2 + 50;
			@Pc(171) int local171 = local170 + 20;
			if (super.mouseButton == 1 && super.clickX >= local24 - 75 && super.clickX <= local24 + 75 && super.clickY >= local171 - 20 && super.clickY <= local171 + 20) {
				this.login(this.username, this.password, false);
			}
			local24 = super.gameWidth / 2 + 80;
			if (super.mouseButton == 1 && super.clickX >= local24 - 75 && super.clickX <= local24 + 75 && super.clickY >= local171 - 20 && super.clickY <= local171 + 20) {
				this.titleState = 0;
				this.username = "";
				this.password = "";
			}
			while (true) {
				@Pc(254) int local254 = this.pollKey();
				if (local254 == -1) {
					return;
				}
				@Pc(259) boolean local259 = false;
				for (@Pc(261) int local261 = 0; local261 < ASCII_CHARSET.length(); local261++) {
					if (local254 == ASCII_CHARSET.charAt(local261)) {
						local259 = true;
						break;
					}
				}
				if (this.loginFocusedLine == 0) {
					if (local254 == 8 && this.username.length() > 0) {
						this.username = this.username.substring(0, this.username.length() - 1);
					}
					if (local254 == 9 || local254 == 10 || local254 == 13) {
						this.loginFocusedLine = 1;
					}
					if (local259) {
						this.username = this.username + (char) local254;
					}
					if (this.username.length() > 12) {
						this.username = this.username.substring(0, 12);
					}
				} else if (this.loginFocusedLine == 1) {
					if (local254 == 8 && this.password.length() > 0) {
						this.password = this.password.substring(0, this.password.length() - 1);
					}
					if (local254 == 9 || local254 == 10 || local254 == 13) {
						this.loginFocusedLine = 0;
					}
					if (local259) {
						this.password = this.password + (char) local254;
					}
					if (this.password.length() > 20) {
						this.password = this.password.substring(0, 20);
					}
				}
			}
		} else if (this.titleState == 3) {
			local17 = super.gameWidth / 2;
			local24 = super.gameHeight / 2 + 50;
			@Pc(418) int local418 = local24 + 20;
			if (super.mouseButton == 1 && super.clickX >= local17 - 75 && super.clickX <= local17 + 75 && super.clickY >= local418 - 20 && super.clickY <= local418 + 20) {
				this.titleState = 0;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;ILjava/lang/String;II)Lclient!ub;")
	private FileArchive loadArchive(@OriginalArg(0) String arg0, @OriginalArg(1) int arg1, @OriginalArg(2) String arg2, @OriginalArg(3) int arg3) {
		@Pc(3) int local3 = 5;
		@Pc(6) byte[] local6 = SignedLink.cacheload(arg2);
		@Pc(20) int local20;
		if (local6 != null) {
			local20 = Buffer.getcrc(local6);
			if (local20 != arg1) {
				local6 = null;
			}
		}
		if (local6 != null) {
			return new FileArchive(local6);
		}
		while (local6 == null) {
			this.showProgress("Requesting " + arg0, arg3);
			try {
				local20 = 0;
				@Pc(60) DataInputStream local60 = this.openStream(arg2 + arg1);
				@Pc(63) byte[] local63 = new byte[6];
				local60.readFully(local63, 0, 6);
				@Pc(74) Buffer local74 = new Buffer(local63);
				local74.pos = 3;
				@Pc(82) int local82 = local74.g3() + 6;
				@Pc(84) int local84 = 6;
				local6 = new byte[local82];
				System.arraycopy(local63, 0, local6, 0, 6);
				while (local84 < local82) {
					@Pc(107) int local107 = local82 - local84;
					if (local107 > 1000) {
						local107 = 1000;
					}
					local84 += local60.read(local6, local84, local107);
					@Pc(126) int local126 = local84 * 100 / local82;
					if (local126 != local20) {
						this.showProgress("Loading " + arg0 + " - " + local126 + "%", arg3);
					}
					local20 = local126;
				}
				local60.close();
			} catch (@Pc(155) IOException local155) {
				local6 = null;
				for (local20 = local3; local20 > 0; local20--) {
					this.showProgress("Error loading - Will retry in " + local20 + " secs.", arg3);
					try {
						Thread.sleep(1000L);
					} catch (@Pc(178) Exception local178) {
					}
				}
				local3 *= 2;
				if (local3 > 60) {
					local3 = 60;
				}
			}
		}
		SignedLink.cachesave(arg2, local6);
		return new FileArchive(local6);
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(Z)V")
	private void disposeTitleComponents() {
		this.flameActive = false;
		while (this.flamesThreadActive) {
			this.flameActive = false;
			try {
				Thread.sleep(50L);
			} catch (@Pc(13) Exception local13) {
			}
		}
		this.titlebox = null;
		this.titlebutton = null;
		this.runes = null;
		this.flameGradient = null;
		this.flameGradientRed = null;
		this.flameGradientGreen = null;
		this.flameGradientBlue = null;
		this.flameBuffer1 = null;
		this.flameBuffer2 = null;
		this.flameIntensity = null;
		this.flameIntensityBuffer = null;
		this.imageFlamesLeft = null;
		this.imageFlamesRight = null;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIIII)V")
	private void updateCameraOrbit(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(5) int local5 = 2048 - arg3 & 0x7FF;
		@Pc(11) int local11 = 2048 - arg2 & 0x7FF;
		@Pc(13) int local13 = 0;
		@Pc(15) int local15 = 0;
		@Pc(17) int local17 = arg6;
		@Pc(23) int local23;
		@Pc(27) int local27;
		@Pc(37) int local37;
		if (local5 != 0) {
			local23 = Model.sin[local5];
			local27 = Model.cos[local5];
			local37 = local15 * local27 - arg6 * local23 >> 16;
			local17 = local15 * local23 + arg6 * local27 >> 16;
			local15 = local37;
		}
		if (local11 != 0) {
			local23 = Model.sin[local11];
			local27 = Model.cos[local11];
			local37 = local17 * local23 + local13 * local27 >> 16;
			local17 = local17 * local27 - local13 * local23 >> 16;
			local13 = local37;
		}
		this.cameraX = arg1 - local13;
		this.cameraY = arg0 - local15;
		this.cameraZ = arg5 - local17;
		this.cameraPitch = arg3;
		this.cameraOrbitYaw = arg2;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IZ)V")
	private void updateAnimatedTextures(@OriginalArg(0) int arg0) {
		if (lowMemory) {
			return;
		}

		@Pc(17) IndexedSprite local17;
		@Pc(25) int local25;
		@Pc(33) int local33;
		@Pc(36) byte[] local36;
		@Pc(39) byte[] local39;
		@Pc(41) int local41;
		if (Draw3D.textureCycles[17] >= arg0) {
			local17 = Draw3D.textures[17];
			local25 = local17.spriteWidth * local17.spriteHeight - 1;
			local33 = local17.spriteWidth * this.sceneDelta * 2;
			local36 = local17.pixels;
			local39 = this.tmpTexels;
			for (local41 = 0; local41 <= local25; local41++) {
				local39[local41] = local36[local41 - local33 & local25];
			}
			local17.pixels = local39;
			this.tmpTexels = local36;
			Draw3D.pushTexture(17);
		} else if (Draw3D.textureCycles[24] >= arg0) {
			local17 = Draw3D.textures[24];
			local25 = local17.spriteWidth * local17.spriteHeight - 1;
			local33 = local17.spriteWidth * this.sceneDelta * 2;
			local36 = local17.pixels;
			local39 = this.tmpTexels;
			for (local41 = 0; local41 <= local25; local41++) {
				local39[local41] = local36[local41 - local33 & local25];
			}
			local17.pixels = local39;
			this.tmpTexels = local36;
			Draw3D.pushTexture(24);
		}
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(Z)V")
	private void updateFlames() {
		@Pc(3) short local3 = 256;
		@Pc(12) int local12;
		for (@Pc(5) int local5 = 10; local5 < 117; local5++) {
			local12 = (int) (Math.random() * 100.0D);
			if (local12 < 50) {
				this.flameIntensity[local5 + (local3 - 2 << 7)] = 255;
			}
		}
		@Pc(41) int local41;
		@Pc(48) int local48;
		@Pc(54) int local54;
		for (local12 = 0; local12 < 100; local12++) {
			local41 = (int) (Math.random() * 124.0D) + 2;
			local48 = (int) (Math.random() * 128.0D) + 128;
			local54 = local41 + (local48 << 7);
			this.flameIntensity[local54] = 192;
		}
		for (local41 = 1; local41 < local3 - 1; local41++) {
			for (local48 = 1; local48 < 127; local48++) {
				local54 = local48 + (local41 << 7);
				this.flameIntensityBuffer[local54] = (this.flameIntensity[local54 - 1] + this.flameIntensity[local54 + 1] + this.flameIntensity[local54 - 128] + this.flameIntensity[local54 + 128]) / 4;
			}
		}
		this.flameOffset += 128;
		if (this.flameOffset > this.flameBuffer1.length) {
			this.flameOffset -= this.flameBuffer1.length;
			local48 = (int) (Math.random() * 12.0D);
			this.updateFlameDissolve(this.runes[local48]);
		}
		@Pc(173) int local173;
		for (local48 = 1; local48 < local3 - 1; local48++) {
			for (local54 = 1; local54 < 127; local54++) {
				local173 = local54 + (local48 << 7);
				@Pc(196) int local196 = this.flameIntensityBuffer[local173 + 128] - this.flameBuffer1[local173 + this.flameOffset & this.flameBuffer1.length - 1] / 5;
				if (local196 < 0) {
					local196 = 0;
				}
				this.flameIntensity[local173] = local196;
			}
		}
		for (local54 = 0; local54 < local3 - 1; local54++) {
			this.flameShiftX[local54] = this.flameShiftX[local54 + 1];
		}
		this.flameShiftX[local3 - 1] = (int) (Math.sin((double) clientClock / 14.0D) * 16.0D + Math.sin((double) clientClock / 15.0D) * 14.0D + Math.sin((double) clientClock / 16.0D) * 12.0D);
		if (this.flameCycle1 > 0) {
			this.flameCycle1 -= 4;
		}
		if (this.flameCycle2 > 0) {
			this.flameCycle2 -= 4;
		}
		if (this.flameCycle1 == 0 && this.flameCycle2 == 0) {
			local173 = (int) (Math.random() * 2000.0D);
			if (local173 == 0) {
				this.flameCycle1 = 1024;
			} else if (local173 == 1) {
				this.flameCycle2 = 1024;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(B)V")
	private void drawMinimap() {
		this.areaMapback.makeTarget();
		@Pc(18) int local18 = this.cameraYaw + this.minimapAnticheatAngle & 0x7FF;
		@Pc(26) int local26 = this.self.x / 32 + 48;
		@Pc(34) int local34 = 464 - this.self.z / 32;
		this.minimap.drawRotatedMasked(local18, 146, this.minimapLeft, 151, local34, this.minimapZoom + 256, local26, 21, 9, this.minimapLineWidth);
		this.compass.drawRotatedMasked(this.cameraYaw, 33, this.compassLeft, 33, 25, 256, 25, 0, 0, this.compassLineWidth);
		for (@Pc(72) int local72 = 0; local72 < this.activeMapFunctionCount; local72++) {
			local26 = this.activeMapFunctionX[local72] * 4 + 2 - this.self.x / 32;
			local34 = this.activeMapFunctionZ[local72] * 4 + 2 - this.self.z / 32;
			this.drawOnMinimap(local34, this.activeMapFunctions[local72], local26);
		}
		@Pc(124) int local124;
		for (@Pc(120) int local120 = 0; local120 < 104; local120++) {
			for (local124 = 0; local124 < 104; local124++) {
				@Pc(136) LinkedList local136 = this.objects[this.currentPlane][local120][local124];
				if (local136 != null) {
					local26 = local120 * 4 + 2 - this.self.x / 32;
					local34 = local124 * 4 + 2 - this.self.z / 32;
					this.drawOnMinimap(local34, this.mapdot0, local26);
				}
			}
		}
		for (local124 = 0; local124 < this.npcCount; local124++) {
			@Pc(189) NpcEntity local189 = this.npcEntities[this.npcIndices[local124]];
			if (local189 != null && local189.isVisible() && local189.type.visonmap) {
				local26 = local189.x / 32 - this.self.x / 32;
				local34 = local189.z / 32 - this.self.z / 32;
				this.drawOnMinimap(local34, this.mapdot1, local26);
			}
		}
		for (@Pc(235) int local235 = 0; local235 < this.playerCount; local235++) {
			@Pc(245) PlayerEntity local245 = this.playerEntities[this.playerIndices[local235]];
			if (local245 != null && local245.isVisible()) {
				local26 = local245.x / 32 - this.self.x / 32;
				local34 = local245.z / 32 - this.self.z / 32;
				@Pc(275) boolean local275 = false;
				@Pc(279) long local279 = StringUtils.toBase37(local245.name);
				for (@Pc(281) int local281 = 0; local281 < this.friendCount; local281++) {
					if (local279 == this.friendName37[local281] && this.friendWorld[local281] != 0) {
						local275 = true;
						break;
					}
				}
				if (local275) {
					this.drawOnMinimap(local34, this.mapdot3, local26);
				} else {
					this.drawOnMinimap(local34, this.mapdot2, local26);
				}
			}
		}
		if (this.flagTileX != 0) {
			local26 = this.flagTileX * 4 + 2 - this.self.x / 32;
			local34 = this.flagTileY * 4 + 2 - this.self.z / 32;
			this.drawOnMinimap(local34, this.mapflags, local26);
		}
		Draw2D.fillRect(82, 93, 16777215, 3, 3);
		this.areaViewport.makeTarget();
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(B)Ljava/awt/Component;")
	@Override
	protected java.awt.Component getBaseComponent() {
		if (SignedLink.mainapp == null) {
			return this;
		} else {
			return SignedLink.mainapp;
		}
	}

	@OriginalMember(owner = "client!client", name = "m", descriptor = "(I)V")
	private void updateTemporaryLocs() {
		if (this.sceneState == 2) {
			for (@Pc(12) TemporaryLoc local12 = (TemporaryLoc) this.temporaryLocs.peekPrevious(); local12 != null; local12 = (TemporaryLoc) this.temporaryLocs.getPrevious()) {
				if (clientClock >= local12.lastCycle) {
					this.addLoc(local12.orientation, local12.x, local12.z, local12.classType, local12.locIndex, local12.type, local12.plane);
					local12.unlink();
				}
			}
			updateLocCounter++;
			if (updateLocCounter > 85) {
				updateLocCounter = 0;
				this.outBuffer.p1isaac(85);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(II)V")
	private void createMinimap(@OriginalArg(0) int arg0) {
		@Pc(5) int[] local5 = this.minimap.pixels;
		@Pc(11) int local11 = local5.length;
		for (@Pc(13) int local13 = 0; local13 < local11; local13++) {
			local5[local13] = 0;
		}
		@Pc(37) int local37;
		@Pc(39) int local39;
		for (@Pc(25) int local25 = 1; local25 < 103; local25++) {
			local37 = (103 - local25) * 512 * 4 + 24628;
			for (local39 = 1; local39 < 103; local39++) {
				if ((this.levelRenderFlags[arg0][local39][local25] & 0x18) == 0) {
					this.mapSquare.drawMinimapTile(local5, local37, 512, arg0, local39, local25);
				}
				if (arg0 < 3 && (this.levelRenderFlags[arg0 + 1][local39][local25] & 0x8) != 0) {
					this.mapSquare.drawMinimapTile(local5, local37, 512, arg0 + 1, local39, local25);
				}
				local37 += 4;
			}
		}
		local37 = ((int) (Math.random() * 20.0D) + 238 - 10 << 16) + ((int) (Math.random() * 20.0D) + 238 - 10 << 8) + (int) (Math.random() * 20.0D) + 238 - 10;
		local39 = (int) (Math.random() * 20.0D) + 238 - 10 << 16;
		this.minimap.prepare();
		@Pc(149) int local149;
		for (@Pc(145) int local145 = 1; local145 < 103; local145++) {
			for (local149 = 1; local149 < 103; local149++) {
				if ((this.levelRenderFlags[arg0][local149][local145] & 0x18) == 0) {
					this.drawMinimapLoc(arg0, local37, local149, local39, local145);
				}
				if (arg0 < 3 && (this.levelRenderFlags[arg0 + 1][local149][local145] & 0x8) != 0) {
					this.drawMinimapLoc(arg0 + 1, local37, local149, local39, local145);
				}
			}
		}
		this.areaViewport.makeTarget();
		this.activeMapFunctionCount = 0;
		for (local149 = 0; local149 < 104; local149++) {
			for (@Pc(217) int local217 = 0; local217 < 104; local217++) {
				@Pc(227) int local227 = this.mapSquare.getGroundDecorationBitset(this.currentPlane, local149, local217);
				if (local227 != 0) {
					local227 = local227 >> 14 & 0x7FFF;
					@Pc(239) int local239 = LocType.get(local227).mapfunction;
					if (local239 >= 0) {
						@Pc(243) int local243 = local149;
						@Pc(245) int local245 = local217;
						if (local239 != 22 && local239 != 29 && local239 != 34 && local239 != 36 && local239 != 46 && local239 != 47 && local239 != 48) {
							@Pc(268) byte local268 = 104;
							@Pc(270) byte local270 = 104;
							@Pc(277) int[][] local277 = this.collisionMaps[this.currentPlane].flags;
							for (@Pc(279) int local279 = 0; local279 < 10; local279++) {
								@Pc(286) int local286 = (int) (Math.random() * 4.0D);
								if (local286 == 0 && local243 > 0 && local243 > local149 - 3 && (local277[local243 - 1][local245] & 0x280108) == 0) {
									local243--;
								}
								if (local286 == 1 && local243 < local268 - 1 && local243 < local149 + 3 && (local277[local243 + 1][local245] & 0x280180) == 0) {
									local243++;
								}
								if (local286 == 2 && local245 > 0 && local245 > local217 - 3 && (local277[local243][local245 - 1] & 0x280102) == 0) {
									local245--;
								}
								if (local286 == 3 && local245 < local270 - 1 && local245 < local217 + 3 && (local277[local243][local245 + 1] & 0x280120) == 0) {
									local245++;
								}
							}
						}
						this.activeMapFunctions[this.activeMapFunctionCount] = this.mapfunction[local239];
						this.activeMapFunctionX[this.activeMapFunctionCount] = local243;
						this.activeMapFunctionZ[this.activeMapFunctionCount] = local245;
						this.activeMapFunctionCount++;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIII)V")
	private void drawMinimapLoc(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(8) int local8 = this.mapSquare.getWallBitset(arg1, arg3, arg5);
		@Pc(18) int local18;
		@Pc(24) int local24;
		@Pc(28) int local28;
		@Pc(30) int local30;
		@Pc(52) int local52;
		@Pc(58) int local58;
		if (local8 != 0) {
			local18 = this.mapSquare.getInfo(arg1, arg3, arg5, local8);
			local24 = local18 >> 6 & 0x3;
			local28 = local18 & 0x1F;
			local30 = arg2;
			if (local8 > 0) {
				local30 = arg4;
			}
			@Pc(38) int[] local38 = this.minimap.pixels;
			local52 = arg3 * 4 + (103 - arg5) * 512 * 4 + 24624;
			local58 = local8 >> 14 & 0x7FFF;
			@Pc(61) LocType local61 = LocType.get(local58);
			if (local61.mapscene == -1) {
				if (local28 == 0 || local28 == 2) {
					if (local24 == 0) {
						local38[local52] = local30;
						local38[local52 + 512] = local30;
						local38[local52 + 1024] = local30;
						local38[local52 + 1536] = local30;
					} else if (local24 == 1) {
						local38[local52] = local30;
						local38[local52 + 1] = local30;
						local38[local52 + 2] = local30;
						local38[local52 + 3] = local30;
					} else if (local24 == 2) {
						local38[local52 + 3] = local30;
						local38[local52 + 3 + 512] = local30;
						local38[local52 + 3 + 1024] = local30;
						local38[local52 + 3 + 1536] = local30;
					} else if (local24 == 3) {
						local38[local52 + 1536] = local30;
						local38[local52 + 1536 + 1] = local30;
						local38[local52 + 1536 + 2] = local30;
						local38[local52 + 1536 + 3] = local30;
					}
				}
				if (local28 == 3) {
					if (local24 == 0) {
						local38[local52] = local30;
					} else if (local24 == 1) {
						local38[local52 + 3] = local30;
					} else if (local24 == 2) {
						local38[local52 + 3 + 1536] = local30;
					} else if (local24 == 3) {
						local38[local52 + 1536] = local30;
					}
				}
				if (local28 == 2) {
					if (local24 == 3) {
						local38[local52] = local30;
						local38[local52 + 512] = local30;
						local38[local52 + 1024] = local30;
						local38[local52 + 1536] = local30;
					} else if (local24 == 0) {
						local38[local52] = local30;
						local38[local52 + 1] = local30;
						local38[local52 + 2] = local30;
						local38[local52 + 3] = local30;
					} else if (local24 == 1) {
						local38[local52 + 3] = local30;
						local38[local52 + 3 + 512] = local30;
						local38[local52 + 3 + 1024] = local30;
						local38[local52 + 3 + 1536] = local30;
					} else if (local24 == 2) {
						local38[local52 + 1536] = local30;
						local38[local52 + 1536 + 1] = local30;
						local38[local52 + 1536 + 2] = local30;
						local38[local52 + 1536 + 3] = local30;
					}
				}
			} else {
				@Pc(71) IndexedSprite local71 = this.mapscene[local61.mapscene];
				if (local71 != null) {
					@Pc(83) int local83 = (local61.sizeX * 4 - local71.spriteWidth) / 2;
					@Pc(93) int local93 = (local61.sizeZ * 4 - local71.spriteHeight) / 2;
					local71.draw((104 - arg5 - local61.sizeZ) * 4 + local93 + 48, arg3 * 4 + 48 + local83);
				}
			}
		}
		local8 = this.mapSquare.getLocationBitset(arg1, arg3, arg5);
		if (local8 != 0) {
			local18 = this.mapSquare.getInfo(arg1, arg3, arg5, local8);
			local24 = local18 >> 6 & 0x3;
			local28 = local18 & 0x1F;
			local30 = local8 >> 14 & 0x7FFF;
			@Pc(451) LocType local451 = LocType.get(local30);
			@Pc(483) int local483;
			if (local451.mapscene != -1) {
				@Pc(461) IndexedSprite local461 = this.mapscene[local451.mapscene];
				if (local461 != null) {
					local58 = (local451.sizeX * 4 - local461.spriteWidth) / 2;
					local483 = (local451.sizeZ * 4 - local461.spriteHeight) / 2;
					local461.draw((104 - arg5 - local451.sizeZ) * 4 + local483 + 48, arg3 * 4 + 48 + local58);
				}
			} else if (local28 == 9) {
				local52 = 15658734;
				if (local8 > 0) {
					local52 = 15597568;
				}
				@Pc(520) int[] local520 = this.minimap.pixels;
				local483 = arg3 * 4 + (103 - arg5) * 512 * 4 + 24624;
				if (local24 == 0 || local24 == 2) {
					local520[local483 + 1536] = local52;
					local520[local483 + 1024 + 1] = local52;
					local520[local483 + 512 + 2] = local52;
					local520[local483 + 3] = local52;
				} else {
					local520[local483] = local52;
					local520[local483 + 512 + 1] = local52;
					local520[local483 + 1024 + 2] = local52;
					local520[local483 + 1536 + 3] = local52;
				}
			}
		}
		local8 = this.mapSquare.getGroundDecorationBitset(arg1, arg3, arg5);
		if (local8 != 0) {
			local18 = local8 >> 14 & 0x7FFF;
			@Pc(615) LocType local615 = LocType.get(local18);
			if (local615.mapscene != -1) {
				@Pc(625) IndexedSprite local625 = this.mapscene[local615.mapscene];
				if (local625 != null) {
					local30 = (local615.sizeX * 4 - local625.spriteWidth) / 2;
					@Pc(647) int local647 = (local615.sizeZ * 4 - local625.spriteHeight) / 2;
					local625.draw((104 - arg5 - local615.sizeZ) * 4 + local647 + 48, arg3 * 4 + 48 + local30);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!kb;IZ)V")
	private void readNpcInfo(@OriginalArg(0) Buffer buf, @OriginalArg(1) int size) {
		this.deadEntityCount = 0;
		this.updateCount = 0;
		this.readNpcs(buf, size);
		this.readNewNpcs(buf, size);
		this.readNpcUpdates(buf, size);

		@Pc(36) int index;
		for (@Pc(29) int i = 0; i < this.deadEntityCount; i++) {
			index = this.deadEntityIndices[i];
			if (this.npcEntities[index].removeTimer != clientClock) {
				this.npcEntities[index].type = null;
				this.npcEntities[index] = null;
			}
		}

		if (buf.pos != size) {
			SignedLink.reporterror(this.username + " size mismatch in getnpcpos - pos:" + buf.pos + " psize:" + size);
			throw new RuntimeException("eek");
		}

		for (index = 0; index < this.npcCount; index++) {
			if (this.npcEntities[this.npcIndices[index]] == null) {
				SignedLink.reporterror(this.username + " null entry in npc list - pos:" + index + " size:" + this.npcCount);
				throw new RuntimeException("eek");
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	@Override
	public void startThread(@OriginalArg(0) Runnable runnable, @OriginalArg(1) int priority) {
		if (SignedLink.mainapp == null) {
			super.startThread(runnable, priority);
		} else {
			SignedLink.startthread(runnable, priority);
		}
	}

	@OriginalMember(owner = "client!client", name = "n", descriptor = "(I)V")
	private void loadTitleForeground() {
		this.titlebox = new IndexedSprite(this.titleArchive, "titlebox", 0);
		this.titlebutton = new IndexedSprite(this.titleArchive, "titlebutton", 0);
		this.runes = new IndexedSprite[12];
		for (@Pc(32) int local32 = 0; local32 < 12; local32++) {
			this.runes[local32] = new IndexedSprite(this.titleArchive, "runes", local32);
		}
		this.imageFlamesLeft = new Sprite(128, 265);
		this.imageFlamesRight = new Sprite(128, 265);
		System.arraycopy(this.titleLeft.pixels, 0, this.imageFlamesLeft.pixels, 0, 33920);
		System.arraycopy(this.titleRight.pixels, 0, this.imageFlamesRight.pixels, 0, 33920);
		this.flameGradientRed = new int[256];
		for (@Pc(105) int local105 = 0; local105 < 64; local105++) {
			this.flameGradientRed[local105] = local105 * 262144;
		}
		for (@Pc(120) int local120 = 0; local120 < 64; local120++) {
			this.flameGradientRed[local120 + 64] = local120 * 1024 + 16711680;
		}
		for (@Pc(139) int local139 = 0; local139 < 64; local139++) {
			this.flameGradientRed[local139 + 128] = local139 * 4 + 16776960;
		}
		for (@Pc(158) int local158 = 0; local158 < 64; local158++) {
			this.flameGradientRed[local158 + 192] = 16777215;
		}
		this.flameGradientGreen = new int[256];
		for (@Pc(177) int local177 = 0; local177 < 64; local177++) {
			this.flameGradientGreen[local177] = local177 * 1024;
		}
		for (@Pc(192) int local192 = 0; local192 < 64; local192++) {
			this.flameGradientGreen[local192 + 64] = local192 * 4 + 65280;
		}
		for (@Pc(211) int local211 = 0; local211 < 64; local211++) {
			this.flameGradientGreen[local211 + 128] = local211 * 262144 + 65535;
		}
		for (@Pc(230) int local230 = 0; local230 < 64; local230++) {
			this.flameGradientGreen[local230 + 192] = 16777215;
		}
		this.flameGradientBlue = new int[256];
		for (@Pc(249) int local249 = 0; local249 < 64; local249++) {
			this.flameGradientBlue[local249] = local249 * 4;
		}
		for (@Pc(264) int local264 = 0; local264 < 64; local264++) {
			this.flameGradientBlue[local264 + 64] = local264 * 262144 + 255;
		}
		for (@Pc(283) int local283 = 0; local283 < 64; local283++) {
			this.flameGradientBlue[local283 + 128] = local283 * 1024 + 16711935;
		}
		for (@Pc(302) int local302 = 0; local302 < 64; local302++) {
			this.flameGradientBlue[local302 + 192] = 16777215;
		}
		this.flameGradient = new int[256];
		this.flameBuffer1 = new int[32768];
		this.flameBuffer2 = new int[32768];
		this.updateFlameDissolve(null);
		this.flameIntensity = new int[32768];
		this.flameIntensityBuffer = new int[32768];
		this.showProgress("Connecting to fileserver", 10);
		if (!this.flameActive) {
			this.startFlamesThread = true;
			this.flameActive = true;
			this.startThread(this, 2);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!kb;I)V")
	private void readPlayers(@OriginalArg(1) int arg0, @OriginalArg(1) Buffer arg1) {
		@Pc(6) int local6 = arg1.gBit(8);
		@Pc(10) int local10;
		if (local6 < this.playerCount) {
			for (local10 = local6; local10 < this.playerCount; local10++) {
				this.deadEntityIndices[this.deadEntityCount++] = this.playerIndices[local10];
			}
		}
		if (local6 > this.playerCount) {
			SignedLink.reporterror(this.username + " Too many players");
			throw new RuntimeException("eek");
		}
		this.playerCount = 0;
		for (local10 = 0; local10 < local6; local10++) {
			@Pc(73) int local73 = this.playerIndices[local10];
			@Pc(78) PlayerEntity local78 = this.playerEntities[local73];
			@Pc(83) int local83 = arg1.gBit(1);
			if (local83 == 0) {
				this.playerIndices[this.playerCount++] = local73;
				local78.removeTimer = clientClock;
			} else {
				@Pc(106) int local106 = arg1.gBit(2);
				if (local106 == 0) {
					this.playerIndices[this.playerCount++] = local73;
					local78.removeTimer = clientClock;
					this.entityUpdateIndices[this.updateCount++] = local73;
				} else {
					@Pc(157) int local157;
					@Pc(167) int local167;
					if (local106 == 1) {
						this.playerIndices[this.playerCount++] = local73;
						local78.removeTimer = clientClock;
						local157 = arg1.gBit(3);
						local78.walk(false, local157);
						local167 = arg1.gBit(1);
						if (local167 == 1) {
							this.entityUpdateIndices[this.updateCount++] = local73;
						}
					} else if (local106 == 2) {
						this.playerIndices[this.playerCount++] = local73;
						local78.removeTimer = clientClock;
						local157 = arg1.gBit(3);
						local78.walk(true, local157);
						local167 = arg1.gBit(3);
						local78.walk(true, local167);
						@Pc(225) int local225 = arg1.gBit(1);
						if (local225 == 1) {
							this.entityUpdateIndices[this.updateCount++] = local73;
						}
					} else if (local106 == 3) {
						this.deadEntityIndices[this.deadEntityCount++] = local73;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IIIIII)V")
	private void drawScrollbar(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		this.scrollbar1.draw(arg2, arg1);
		this.scrollbar2.draw(arg2 + arg5 - 16, arg1);
		Draw2D.fillRect(arg2 + 16, arg1, this.SCROLLBAR_TRACK, 16, arg5 - 32);
		@Pc(35) int local35 = (arg5 - 32) * arg5 / arg4;
		if (local35 < 8) {
			local35 = 8;
		}
		@Pc(52) int local52 = (arg5 - local35 - 32) * arg3 / (arg4 - arg5);
		Draw2D.fillRect(arg2 + local52 + 16, arg1, this.SCROLLBAR_GRIP_FOREGROUND, 16, local35);
		Draw2D.drawVerticalLine(this.SCROLLBAR_GRIP_HIGHLIGHT, arg2 + local52 + 16, local35, arg1);
		Draw2D.drawVerticalLine(this.SCROLLBAR_GRIP_HIGHLIGHT, arg2 + local52 + 16, local35, arg1 + 1);
		Draw2D.drawHorizontalLine(this.SCROLLBAR_GRIP_HIGHLIGHT, arg2 + local52 + 16, 16, arg1);
		Draw2D.drawHorizontalLine(this.SCROLLBAR_GRIP_HIGHLIGHT, arg2 + local52 + 17, 16, arg1);
		Draw2D.drawVerticalLine(this.SCROLLBAR_GRIP_LOWLIGHT, arg2 + local52 + 16, local35, arg1 + 15);
		Draw2D.drawVerticalLine(this.SCROLLBAR_GRIP_LOWLIGHT, arg2 + local52 + 17, local35 - 1, arg1 + 14);
		Draw2D.drawHorizontalLine(this.SCROLLBAR_GRIP_LOWLIGHT, arg2 + local52 + local35 + 15, 16, arg1);
		Draw2D.drawHorizontalLine(this.SCROLLBAR_GRIP_LOWLIGHT, arg2 + local52 + local35 + 14, 15, arg1 + 1);
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(B)V")
	private void resetCharacterDesign() {
		this.characterDesignUpdate = true;
		for (@Pc(12) int local12 = 0; local12 < 7; local12++) {
			this.characterDesigns[local12] = -1;
			for (@Pc(21) int local21 = 0; local21 < IdkType.count; local21++) {
				if (!IdkType.instances[local21].disable && IdkType.instances[local21].type == local12 + (this.characterDesignIsMale ? 0 : 7)) {
					this.characterDesigns[local12] = local21;
					break;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([BIIZ)V")
	private void midisave(@OriginalArg(0) byte[] arg0, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3) {
		SignedLink.midifade = arg3;
		SignedLink.midisave(arg0, arg2);
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(Z)V")
	private void updateSceneNpcs() {
		for (@Pc(1) int local1 = 0; local1 < this.npcCount; local1++) {
			@Pc(11) NpcEntity local11 = this.npcEntities[this.npcIndices[local1]];
			@Pc(20) int local20 = (this.npcIndices[local1] << 14) + 536870912;
			if (local11 != null && local11.isVisible()) {
				@Pc(31) int local31 = local11.x >> 7;
				@Pc(36) int local36 = local11.z >> 7;
				if (local31 >= 0 && local31 < 104 && local36 >= 0 && local36 < 104) {
					if (local11.index == 1 && (local11.x & 0x7F) == 64 && (local11.z & 0x7F) == 64) {
						if (this.tileRenderCount[local31][local36] == this.sceneCycle) {
							continue;
						}
						this.tileRenderCount[local31][local36] = this.sceneCycle;
					}
					this.mapSquare.add(local11.z, (local11.index - 1) * 64 + 60, local11.animationDelay, local11.x, local20, local11.needsForwardDrawPadding, null, local11, this.getLandY(this.currentPlane, local11.x, local11.z), this.currentPlane);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIZ)V")
	private void midivol(@OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2) {
		SignedLink.midivol = arg1;
		if (arg2) {
			SignedLink.midi = "voladjust";
		}
	}

	@OriginalMember(owner = "client!client", name = "o", descriptor = "(I)V")
	private void drawTitleScreen() {
		this.prepareTitleScreen();
		this.titleCenter.makeTarget();
		this.titlebox.draw(0, 0);
		@Pc(21) short local21 = 360;
		@Pc(23) short local23 = 200;
		@Pc(32) int local32;
		@Pc(50) int local50;
		@Pc(56) int local56;
		if (this.titleState == 0) {
			local32 = local23 / 2 - 20;
			this.bold12.drawCentered(local21 / 2, 16776960, true, local32, "Welcome to RuneScape");
			@Pc(44) int local44 = local32 + 30;
			local50 = local21 / 2 - 80;
			local56 = local23 / 2 + 20;
			this.titlebutton.draw(local56 - 20, local50 - 73);
			this.bold12.drawCentered(local50, 16777215, true, local56 + 5, "New user");
			@Pc(83) int local83 = local21 / 2 + 80;
			this.titlebutton.draw(local56 - 20, local83 - 73);
			this.bold12.drawCentered(local83, 16777215, true, local56 + 5, "Existing User");
		}
		if (this.titleState == 2) {
			local32 = local23 / 2 - 40;
			if (this.loginMessage1.length() > 0) {
				this.bold12.drawCentered(local21 / 2, 16776960, true, local32 - 15, this.loginMessage1);
				this.bold12.drawCentered(local21 / 2, 16776960, true, local32, this.loginMessage2);
			} else {
				this.bold12.drawCentered(local21 / 2, 16776960, true, local32 - 7, this.loginMessage2);
			}
			local32 += 30;
			this.bold12.draw(local21 / 2 - 90, local32, "Username: " + this.username + (this.loginFocusedLine == 0 & clientClock % 40 < 20 ? "@yel@|" : ""), true, 16777215);
			local32 += 15;
			this.bold12.draw(local21 / 2 - 88, local32, "Password: " + StringUtils.censor(this.password) + (this.loginFocusedLine == 1 & clientClock % 40 < 20 ? "@yel@|" : ""), true, 16777215);
			local32 += 15;
			local50 = local21 / 2 - 80;
			local56 = local23 / 2 + 50;
			this.titlebutton.draw(local56 - 20, local50 - 73);
			this.bold12.drawCentered(local50, 16777215, true, local56 + 5, "Login");
			local50 = local21 / 2 + 80;
			this.titlebutton.draw(local56 - 20, local50 - 73);
			this.bold12.drawCentered(local50, 16777215, true, local56 + 5, "Cancel");
		}
		if (this.titleState == 3) {
			this.bold12.drawCentered(local21 / 2, 16776960, true, local23 / 2 - 60, "Create a free account");
			local32 = local23 / 2 - 35;
			this.bold12.drawCentered(local21 / 2, 16777215, true, local32, "To create a new account you need to");
			local32 += 15;
			this.bold12.drawCentered(local21 / 2, 16777215, true, local32, "go back to the main RuneScape webpage");
			local32 += 15;
			this.bold12.drawCentered(local21 / 2, 16777215, true, local32, "and choose the red 'create account'");
			local32 += 15;
			this.bold12.drawCentered(local21 / 2, 16777215, true, local32, "button at the top right of that page.");
			local32 += 15;
			local50 = local21 / 2;
			local56 = local23 / 2 + 50;
			this.titlebutton.draw(local56 - 20, local50 - 73);
			this.bold12.drawCentered(local50, 16777215, true, local56 + 5, "Cancel");
		}
		this.titleCenter.drawAt(186, super.graphics, 214);
		if (this.redrawTitleBackground) {
			this.redrawTitleBackground = false;
			this.titleTop.drawAt(0, super.graphics, 128);
			this.titleBottom.drawAt(386, super.graphics, 214);
			this.titleBottomLeft.drawAt(265, super.graphics, 0);
			this.titleBottomRight.drawAt(265, super.graphics, 574);
			this.titleLeftSpace.drawAt(186, super.graphics, 128);
			this.titleRightSpace.drawAt(186, super.graphics, 574);
		}
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(I)V")
	private void prepareGameScreen() {
		if (this.areaChatback == null) {
			this.disposeTitleComponents();
			super.frameBuffer = null;
			this.titleTop = null;
			this.titleBottom = null;
			this.titleCenter = null;
			this.titleLeft = null;
			this.titleRight = null;
			this.titleBottomLeft = null;
			this.titleBottomRight = null;
			this.titleLeftSpace = null;
			this.titleRightSpace = null;
			this.areaChatback = new ImageProducerFrameBuffer(this.getBaseComponent(), 479, 96);
			this.areaMapback = new ImageProducerFrameBuffer(this.getBaseComponent(), 168, 160);
			Draw2D.clear();
			this.mapback.draw(0, 0);
			this.areaInvback = new ImageProducerFrameBuffer(this.getBaseComponent(), 190, 261);
			this.areaViewport = new ImageProducerFrameBuffer(this.getBaseComponent(), 512, 334);
			Draw2D.clear();
			this.areaBackbase1 = new ImageProducerFrameBuffer(this.getBaseComponent(), 501, 61);
			this.areaBackbase2 = new ImageProducerFrameBuffer(this.getBaseComponent(), 288, 40);
			this.areaBackhmid1 = new ImageProducerFrameBuffer(this.getBaseComponent(), 269, 66);
			this.redrawTitleBackground = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IILclient!kb;)V")
	private void readNewPlayers(@OriginalArg(1) int arg1, @OriginalArg(2) Buffer arg2) {
		@Pc(9) int local9;
		while (arg2.bitOffset + 10 < arg1 * 8) {
			local9 = arg2.gBit(11);
			if (local9 == 2047) {
				break;
			}
			if (this.playerEntities[local9] == null) {
				this.playerEntities[local9] = new PlayerEntity();
				if (this.playerBuffers[local9] != null) {
					this.playerEntities[local9].decode(this.playerBuffers[local9]);
				}
			}
			this.playerIndices[this.playerCount++] = local9;
			@Pc(73) PlayerEntity local73 = this.playerEntities[local9];
			local73.removeTimer = clientClock;
			@Pc(81) int local81 = arg2.gBit(5);
			if (local81 > 15) {
				local81 -= 32;
			}
			@Pc(90) int local90 = arg2.gBit(5);
			if (local90 > 15) {
				local90 -= 32;
			}
			@Pc(99) int local99 = arg2.gBit(1);
			local73.move(local99 == 1, this.self.pathTileX[0] + local81, this.self.pathTileZ[0] + local90);
			@Pc(127) int local127 = arg2.gBit(1);
			if (local127 == 1) {
				this.entityUpdateIndices[this.updateCount++] = local9;
			}
		}
		arg2.accessBytes();
	}

	@OriginalMember(owner = "client!client", name = "q", descriptor = "(I)V")
	private void disconnect() {
		try {
			if (this.stream != null) {
				this.stream.close();
			}
		} catch (@Pc(9) Exception local9) {
		}
		this.stream = null;
		this.ingame = false;
		this.titleState = 0;
		this.username = "";
		this.password = "";
		InputTracking.setDisabled();
		this.clearCaches();
		this.mapSquare.reset();
		for (@Pc(41) int local41 = 0; local41 < 4; local41++) {
			this.collisionMaps[local41].reset();
		}
		System.gc();
		this.midistop();
		this.currentMidi = null;
		this.nextMusicDelay = 0;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIILclient!hc;I)V")
	private void drawInterface(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Component arg3, @OriginalArg(4) int arg4) {
		if (arg3.type == 0 && arg3.children != null && (!arg3.hide || this.viewportHoveredInterfaceIndex == arg3.id || this.sidebarHoveredInterfaceIndex == arg3.id || this.chatHoveredInterfaceIndex == arg3.id)) {
			@Pc(29) int local29 = Draw2D.left;
			@Pc(31) int local31 = Draw2D.top;
			@Pc(33) int local33 = Draw2D.right;
			@Pc(35) int local35 = Draw2D.bottom;
			Draw2D.setBounds(arg0 + arg3.height, arg0, arg1 + arg3.width, arg1);
			@Pc(57) int local57 = arg3.children.length;
			for (@Pc(59) int local59 = 0; local59 < local57; local59++) {
				@Pc(68) int local68 = arg3.childX[local59] + arg1;
				@Pc(77) int local77 = arg3.childY[local59] + arg0 - arg4;
				@Pc(84) Component iface = Component.instances[arg3.children[local59]];
				@Pc(89) int local89 = local68 + iface.x;
				@Pc(94) int local94 = local77 + iface.y;
				if (iface.clientcode > 0) {
					this.updateComponentContent(iface);
				}
				if (iface.type == 0) {
					if (iface.scrollY > iface.scrollHeight - iface.height) {
						iface.scrollY = iface.scrollHeight - iface.height;
					}
					if (iface.scrollY < 0) {
						iface.scrollY = 0;
					}
					this.drawInterface(local94, local89, iface, iface.scrollY);
					if (iface.scrollHeight > iface.height) {
						this.drawScrollbar(local89 + iface.width, local94, iface.scrollY, iface.scrollHeight, iface.height);
					}
				} else if (iface.type != 1) {
					@Pc(167) int local167;
					@Pc(171) int local171;
					@Pc(182) int slotX;
					@Pc(217) int dy;
					@Pc(224) int objId;
					@Pc(165) int slot;
					@Pc(191) int slotY;
					@Pc(215) int dx;

					if (iface.type == 2) {
						slot = 0;
						for (local167 = 0; local167 < iface.height; local167++) {
							for (local171 = 0; local171 < iface.width; local171++) {
								slotX = local89 + local171 * (iface.inventoryMarginX + 32);
								slotY = local94 + local167 * (iface.inventoryMarginY + 32);

								if (slot < 20) {
									slotX += iface.inventoryOffsetX[slot];
									slotY += iface.inventoryOffsetY[slot];
								}

								if (iface.inventoryIndices[slot] > 0) {
									dx = 0;
									dy = 0;
									objId = iface.inventoryIndices[slot] - 1;

									if (slotX >= -32 && slotX <= 512 && slotY >= -32 && slotY <= 334 || this.objDragArea != 0 && this.objDragSlot == slot) {
										int outlineColor = 0;

										if (GlobalConfig.SHOW_HIGHLIGHT_OUTLINE) {
											if (this.selectedObject == 1 && slot == this.selectedObjSlot && iface.id == this.selectedObjInterface) {
												outlineColor = 0xFFFFFF;
											}
										}

										@Pc(251) Sprite icon = ObjType.getSprite(objId, iface.inventoryAmount[slot], outlineColor);
										if (icon != null) {
											if (this.objDragArea != 0 && this.objDragSlot == slot && this.objDragComponentId == iface.id) {
												dx = super.mouseX - this.objGrabX;
												dy = super.mouseY - this.objGrabY;

												if (dx < 5 && dx > -5) {
													dx = 0;
												}

												if (dy < 5 && dy > -5) {
													dy = 0;
												}

												if (this.objDragCycles < 5) {
													dx = 0;
													dy = 0;
												}

												icon.drawAlpha(128, slotX + dx, slotY + dy);
											} else if (this.selectedArea != 0 && this.selectedItem == slot && this.selectedInterface == iface.id) {
												icon.drawAlpha(128, slotX, slotY);
											} else {
												icon.draw(slotY, slotX);
											}

											if (icon.clipWidth == 33 || iface.inventoryAmount[slot] != 1) {
												@Pc(351) int count = iface.inventoryAmount[slot];
												this.plain11.draw(slotX + dx + 1, slotY + 10 + dy, 0, formatObjAmount(count));
												this.plain11.draw(slotX + dx, slotY + 9 + dy, 16776960, formatObjAmount(count));
											}
										}
									}
								} else if (iface.inventorySprite != null && slot < 20) {
									@Pc(398) Sprite local398 = iface.inventorySprite[slot];
									if (local398 != null) {
										local398.draw(slotY, slotX);
									}
								}
								slot++;
							}
						}
					} else if (iface.type != 3) {
						@Pc(456) Font local456;
						if (iface.type == 4) {
							local456 = iface.font;
							local167 = iface.colour;
							@Pc(462) String local462 = iface.text;
							if ((this.chatHoveredInterfaceIndex == iface.id || this.sidebarHoveredInterfaceIndex == iface.id || this.viewportHoveredInterfaceIndex == iface.id) && iface.overcolour != 0) {
								local167 = iface.overcolour;
							}
							if (this.isInterfaceEnabled(iface)) {
								local167 = iface.activecolour;
								if (iface.activetext.length() > 0) {
									local462 = iface.activetext;
								}
							}
							if (iface.buttontype == 6 && this.chatContinuingDialogue) {
								local462 = "Please wait...";
								local167 = iface.colour;
							}
							slotY = local94 + local456.fontHeight;
							while (local462.length() > 0) {
								if (local462.indexOf("%") != -1) {
									label264:
									while (true) {
										dx = local462.indexOf("%1");
										if (dx == -1) {
											while (true) {
												dx = local462.indexOf("%2");
												if (dx == -1) {
													while (true) {
														dx = local462.indexOf("%3");
														if (dx == -1) {
															while (true) {
																dx = local462.indexOf("%4");
																if (dx == -1) {
																	while (true) {
																		dx = local462.indexOf("%5");
																		if (dx == -1) {
																			break label264;
																		}
																		local462 = local462.substring(0, dx) + this.getIntString(this.executeInterface(iface, 4)) + local462.substring(dx + 2);
																	}
																}
																local462 = local462.substring(0, dx) + this.getIntString(this.executeInterface(iface, 3)) + local462.substring(dx + 2);
															}
														}
														local462 = local462.substring(0, dx) + this.getIntString(this.executeInterface(iface, 2)) + local462.substring(dx + 2);
													}
												}
												local462 = local462.substring(0, dx) + this.getIntString(this.executeInterface(iface, 1)) + local462.substring(dx + 2);
											}
										}
										local462 = local462.substring(0, dx) + this.getIntString(this.executeInterface(iface, 0)) + local462.substring(dx + 2);
									}
								}
								dx = local462.indexOf("\\n");
								@Pc(704) String local704;
								if (dx == -1) {
									local704 = local462;
									local462 = "";
								} else {
									local704 = local462.substring(0, dx);
									local462 = local462.substring(dx + 2);
								}
								if (iface.halign) {
									local456.drawCentered(local89 + iface.width / 2, local167, iface.shadowed, slotY, local704);
								} else {
									local456.draw(local89, slotY, local704, iface.shadowed, local167);
								}
								slotY += local456.fontHeight;
							}
						} else if (iface.type == 5) {
							@Pc(766) Sprite local766;
							if (this.isInterfaceEnabled(iface)) {
								local766 = iface.activegraphic;
							} else {
								local766 = iface.graphic;
							}
							if (local766 != null) {
								local766.draw(local94, local89);
							}
						} else if (iface.type == 6) {
							slot = Draw3D.centerX3D;
							local167 = Draw3D.centerY3D;
							Draw3D.centerX3D = local89 + iface.width / 2;
							Draw3D.centerY3D = local94 + iface.height / 2;
							local171 = Draw3D.sin[iface.xan] * iface.zoom >> 16;
							slotX = Draw3D.cos[iface.xan] * iface.zoom >> 16;
							@Pc(827) boolean local827 = this.isInterfaceEnabled(iface);
							if (local827) {
								dx = iface.activeanim;
							} else {
								dx = iface.anim;
							}
							@Pc(846) Model local846;
							if (dx == -1) {
								local846 = iface.getModel(-1, -1, local827);
							} else {
								@Pc(852) SeqType local852 = SeqType.instances[dx];
								local846 = iface.getModel(local852.primaryFrames[iface.seqFrame], local852.secondaryFrames[iface.seqFrame], local827);
							}
							if (local846 != null) {
								local846.drawSimple(0, iface.yan, 0, iface.xan, 0, local171, slotX);
							}
							Draw3D.centerX3D = slot;
							Draw3D.centerY3D = local167;
						} else if (iface.type == 7) {
							local456 = iface.font;
							local167 = 0;
							for (local171 = 0; local171 < iface.height; local171++) {
								for (slotX = 0; slotX < iface.width; slotX++) {
									if (iface.inventoryIndices[local167] > 0) {
										@Pc(915) ObjType local915 = ObjType.get(iface.inventoryIndices[local167] - 1);
										@Pc(918) String local918 = local915.name;
										if (local915.stackable || iface.inventoryAmount[local167] != 1) {
											local918 = local918 + " x" + formatNumber(iface.inventoryAmount[local167]);
										}
										dy = local89 + slotX * (iface.inventoryMarginX + 115);
										objId = local94 + local171 * (iface.inventoryMarginY + 12);
										if (iface.halign) {
											local456.drawCentered(dy + iface.width / 2, iface.colour, iface.shadowed, objId, local918);
										} else {
											local456.draw(dy, objId, local918, iface.shadowed, iface.colour);
										}
									}
									local167++;
								}
							}
						}
					} else if (iface.fill) {
						Draw2D.fillRect(local94, local89, iface.colour, iface.width, iface.height);
					} else {
						Draw2D.drawRect(local89, iface.colour, iface.height, local94, iface.width);
					}
				}
			}
			Draw2D.setBounds(local35, local31, local33, local29);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZILclient!kb;)V")
	private void readPlayerUpdates(@OriginalArg(2) Buffer arg2, @OriginalArg(1) int arg1) {
		for (@Pc(1) int local1 = 0; local1 < this.updateCount; local1++) {
			@Pc(8) int local8 = this.entityUpdateIndices[local1];
			@Pc(13) PlayerEntity local13 = this.playerEntities[local8];
			@Pc(16) int local16 = arg2.g1();
			if ((local16 & 0x80) == 128) {
				local16 += arg2.g1() << 8;
			}
			this.updatePlayerMask(local8, local16, arg2, local13);
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(II)V")
	private void updateVarp(@OriginalArg(0) int arg0) {
		@Pc(8) int local8 = VarType.instances[arg0].clientcode;
		if (local8 != 0) {
			@Pc(16) int local16 = this.variables[arg0];
			if (local8 == 1) {
				if (local16 == 1) {
					Draw3D.setBrightness(0.9D);
				}
				if (local16 == 2) {
					Draw3D.setBrightness(0.8D);
				}
				if (local16 == 3) {
					Draw3D.setBrightness(0.7D);
				}
				if (local16 == 4) {
					Draw3D.setBrightness(0.6D);
				}
				ObjType.icons.clear();
				this.redrawTitleBackground = true;
			}
			if (local8 == 3) {
				@Pc(54) boolean local54 = this.midiActive;
				if (local16 == 0) {
					this.midivol(256, this.midiActive);
					this.midiActive = true;
				}
				if (local16 == 1) {
					this.midivol(192, this.midiActive);
					this.midiActive = true;
				}
				if (local16 == 2) {
					this.midivol(96, this.midiActive);
					this.midiActive = true;
				}
				if (local16 == 3) {
					this.midivol(32, this.midiActive);
					this.midiActive = true;
				}
				if (local16 == 4) {
					this.midiActive = false;
				}
				if (this.midiActive != local54) {
					if (this.midiActive) {
						this.setMidi(this.midiCrc, this.currentMidi, this.midiSize);
					} else {
						this.midistop();
					}
					this.nextMusicDelay = 0;
				}
			}
			if (local8 == 4) {
				if (local16 == 0) {
					this.effectsEnabled = true;
					this.wavevol(0);
				}
				if (local16 == 1) {
					this.effectsEnabled = true;
					this.wavevol(-400);
				}
				if (local16 == 2) {
					this.effectsEnabled = true;
					this.wavevol(-800);
				}
				if (local16 == 3) {
					this.effectsEnabled = true;
					this.wavevol(-1200);
				}
				if (local16 == 4) {
					this.effectsEnabled = false;
				}
			}
			if (local8 == 5) {
				this.button = local16;
			}
			if (local8 == 6) {
				this.chatEffects = local16;
			}
			if (local8 == 8) {
				this.splitPrivateChat = local16;
				this.redrawChatback = true;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(Z)V")
	private void updateNpcs() {
		for (@Pc(7) int i = 0; i < this.npcCount; i++) {
			@Pc(14) int index = this.npcIndices[i];
			@Pc(19) NpcEntity npc = this.npcEntities[index];

			if (npc != null) {
				this.updateEntity(npc, npc.type.size);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;BI)V")
	private void updateEntity(@OriginalArg(0) PathingEntity entity, @OriginalArg(2) int arg2) {
		if (entity.x < 128 || entity.z < 128 || entity.x >= 13184 || entity.z >= 13184) {
			entity.primarySeq = -1;
			entity.spotAnimIndex = -1;
			entity.firstMoveCycle = 0;
			entity.lastMoveCycle = 0;
			entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
			entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
			entity.pathLength = 0;
		}
		if (entity == this.self && (entity.x < 1536 || entity.z < 1536 || entity.x >= 11776 || entity.z >= 11776)) {
			entity.primarySeq = -1;
			entity.spotAnimIndex = -1;
			entity.firstMoveCycle = 0;
			entity.lastMoveCycle = 0;
			entity.x = entity.pathTileX[0] * 128 + entity.index * 64;
			entity.z = entity.pathTileZ[0] * 128 + entity.index * 64;
			entity.pathLength = 0;
		}
		if (entity.firstMoveCycle > clientClock) {
			this.updateForceMovement(entity);
		} else if (entity.lastMoveCycle >= clientClock) {
			this.startForceMovement(entity);
		} else {
			this.updateMovement(entity);
		}
		this.updateFacingDirection(entity);
		this.updateSequences(entity);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;I)V")
	private void updateForceMovement(@OriginalArg(0) PathingEntity arg0) {
		@Pc(4) int local4 = arg0.firstMoveCycle - clientClock;
		@Pc(14) int local14 = arg0.srcTileX * 128 + arg0.index * 64;
		@Pc(24) int local24 = arg0.srcTileZ * 128 + arg0.index * 64;
		arg0.x += (local14 - arg0.x) / local4;
		arg0.z += (local24 - arg0.z) / local4;
		arg0.seqTrigger = 0;
		if (arg0.faceDirection == 0) {
			arg0.dstYaw = 1024;
		}
		if (arg0.faceDirection == 1) {
			arg0.dstYaw = 1536;
		}
		if (arg0.faceDirection == 2) {
			arg0.dstYaw = 0;
		}
		if (arg0.faceDirection == 3) {
			arg0.dstYaw = 512;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!x;I)V")
	private void startForceMovement(@OriginalArg(0) PathingEntity entity) {
		if (entity.lastMoveCycle == clientClock || entity.primarySeq == -1 || entity.primarySeqDelay != 0 || entity.primarySeqCycle + 1 > SeqType.instances[entity.primarySeq].frameDelay[entity.primarySeqFrame]) {
			@Pc(35) int duration = entity.lastMoveCycle - entity.firstMoveCycle;
			@Pc(40) int delta = clientClock - entity.firstMoveCycle;
			@Pc(50) int dx0 = entity.srcTileX * 128 + entity.index * 64;
			@Pc(60) int dz0 = entity.srcTileZ * 128 + entity.index * 64;
			@Pc(70) int dx1 = entity.dstTileX * 128 + entity.index * 64;
			@Pc(80) int dz1 = entity.dstTileZ * 128 + entity.index * 64;
			entity.x = (dx0 * (duration - delta) + dx1 * delta) / duration;
			entity.z = (dz0 * (duration - delta) + dz1 * delta) / duration;
		}
		entity.seqTrigger = 0;
		if (entity.faceDirection == 0) {
			entity.dstYaw = 1024;
		}
		if (entity.faceDirection == 1) {
			entity.dstYaw = 1536;
		}
		if (entity.faceDirection == 2) {
			entity.dstYaw = 0;
		}
		if (entity.faceDirection == 3) {
			entity.dstYaw = 512;
		}
		entity.animationDelay = entity.dstYaw;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!x;)V")
	private void updateMovement(@OriginalArg(1) PathingEntity entity) {
		entity.secondarySeq = entity.standSeq;
		if (entity.pathLength == 0) {
			entity.seqTrigger = 0;
		} else {
			if (entity.primarySeq != -1 && entity.primarySeqDelay == 0) {
				@Pc(28) SeqType local28 = SeqType.instances[entity.primarySeq];
				if (local28.labelGroups == null) {
					entity.seqTrigger++;
					return;
				}
			}
			@Pc(41) int x = entity.x;
			@Pc(44) int z = entity.z;
			@Pc(59) int dstX = entity.pathTileX[entity.pathLength - 1] * 128 + entity.index * 64;
			@Pc(74) int dstZ = entity.pathTileZ[entity.pathLength - 1] * 128 + entity.index * 64;
			if (dstX - x <= 256 && dstX - x >= -256 && dstZ - z <= 256 && dstZ - z >= -256) {
				if (x < dstX) {
					if (z < dstZ) {
						entity.dstYaw = 1280;
					} else if (z > dstZ) {
						entity.dstYaw = 1792;
					} else {
						entity.dstYaw = 1536;
					}
				} else if (x > dstX) {
					if (z < dstZ) {
						entity.dstYaw = 768;
					} else if (z > dstZ) {
						entity.dstYaw = 256;
					} else {
						entity.dstYaw = 512;
					}
				} else if (z < dstZ) {
					entity.dstYaw = 1024;
				} else {
					entity.dstYaw = 0;
				}
				@Pc(168) int local168 = entity.dstYaw - entity.animationDelay & 0x7FF;
				if (local168 > 1024) {
					local168 -= 2048;
				}
				@Pc(175) int local175 = entity.walkSeq;
				if (local168 >= -256 && local168 <= 256) {
					local175 = entity.runSeq;
				} else if (local168 >= 256 && local168 < 768) {
					local175 = entity.turnRightSeq;
				} else if (local168 >= -768 && local168 <= -256) {
					local175 = entity.turnAroundSeq;
				}
				if (local175 == -1) {
					local175 = entity.runSeq;
				}
				entity.secondarySeq = local175;
				@Pc(217) int moveSpeed = 4;
				if (entity.animationDelay != entity.dstYaw && entity.targetEntity == -1) {
					moveSpeed = 2;
				}
				if (entity.pathLength > 2) {
					moveSpeed = 6;
				}
				if (entity.pathLength > 3) {
					moveSpeed = 8;
				}
				if (entity.seqTrigger > 0 && entity.pathLength > 1) {
					moveSpeed = 8;
					entity.seqTrigger--;
				}
				if (entity.pathRunning[entity.pathLength - 1]) {
					moveSpeed <<= 0x1;
				}
				if (moveSpeed >= 8 && entity.secondarySeq == entity.runSeq && entity.turnLeftSeq != -1) {
					entity.secondarySeq = entity.turnLeftSeq;
				}
				if (x < dstX) {
					entity.x += moveSpeed;
					if (entity.x > dstX) {
						entity.x = dstX;
					}
				} else if (x > dstX) {
					entity.x -= moveSpeed;
					if (entity.x < dstX) {
						entity.x = dstX;
					}
				}
				if (z < dstZ) {
					entity.z += moveSpeed;
					if (entity.z > dstZ) {
						entity.z = dstZ;
					}
				} else if (z > dstZ) {
					entity.z -= moveSpeed;
					if (entity.z < dstZ) {
						entity.z = dstZ;
					}
				}
				if (entity.x == dstX && entity.z == dstZ) {
					entity.pathLength--;
				}
			} else {
				entity.x = dstX;
				entity.z = dstZ;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;B)V")
	private void updateFacingDirection(@OriginalArg(0) PathingEntity entity) {
		@Pc(30) int local30;
		@Pc(36) int local36;
		if (entity.targetEntity != -1 && entity.targetEntity < 32768) {
			@Pc(22) NpcEntity npc = this.npcEntities[entity.targetEntity];
			if (npc != null) {
				local30 = entity.x - npc.x;
				local36 = entity.z - npc.z;
				if (local30 != 0 || local36 != 0) {
					entity.dstYaw = (int) (Math.atan2(local30, local36) * 325.949D) & 0x7FF;
				}
			}
		}
		@Pc(61) int local61;
		if (entity.targetEntity >= 32768) {
			local61 = entity.targetEntity - 32768;
			if (local61 == this.selfPlayerId) {
				local61 = this.LOCAL_PLAYER_INDEX;
			}
			@Pc(73) PlayerEntity local73 = this.playerEntities[local61];
			if (local73 != null) {
				local36 = entity.x - local73.x;
				@Pc(87) int local87 = entity.z - local73.z;
				if (local36 != 0 || local87 != 0) {
					entity.dstYaw = (int) (Math.atan2(local36, local87) * 325.949D) & 0x7FF;
				}
			}
		}
		if ((entity.focusX != 0 || entity.focusZ != 0) && (entity.pathLength == 0 || entity.seqTrigger > 0)) {
			local61 = entity.x - (entity.focusX - this.baseTileX - this.baseTileX) * 64;
			local30 = entity.z - (entity.focusZ - this.baseTileZ - this.baseTileZ) * 64;
			if (local61 != 0 || local30 != 0) {
				entity.dstYaw = (int) (Math.atan2(local61, local30) * 325.949D) & 0x7FF;
			}
			entity.focusX = 0;
			entity.focusZ = 0;
		}
		local61 = entity.dstYaw - entity.animationDelay & 0x7FF;
		if (local61 != 0) {
			if (local61 < 32 || local61 > 2016) {
				entity.animationDelay = entity.dstYaw;
			} else if (local61 > 1024) {
				entity.animationDelay -= 32;
			} else {
				entity.animationDelay += 32;
			}
			entity.animationDelay &= 0x7FF;
			if (entity.secondarySeq == entity.standSeq && entity.animationDelay != entity.dstYaw) {
				if (entity.turnSeq != -1) {
					entity.secondarySeq = entity.turnSeq;
					return;
				}
				entity.secondarySeq = entity.runSeq;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!x;)V")
	private void updateSequences(@OriginalArg(1) PathingEntity arg1) {
		arg1.needsForwardDrawPadding = false;
		@Pc(16) SeqType local16;
		if (arg1.secondarySeq != -1) {
			local16 = SeqType.instances[arg1.secondarySeq];
			arg1.secondarySeqCycle++;
			if (arg1.secondarySeqFrame < local16.framecount && arg1.secondarySeqCycle > local16.frameDelay[arg1.secondarySeqFrame]) {
				arg1.secondarySeqCycle = 0;
				arg1.secondarySeqFrame++;
			}
			if (arg1.secondarySeqFrame >= local16.framecount) {
				arg1.secondarySeqCycle = 0;
				arg1.secondarySeqFrame = 0;
			}
		}
		if (arg1.primarySeq != -1 && arg1.primarySeqDelay == 0) {
			local16 = SeqType.instances[arg1.primarySeq];
			arg1.primarySeqCycle++;
			while (arg1.primarySeqFrame < local16.framecount && arg1.primarySeqCycle > local16.frameDelay[arg1.primarySeqFrame]) {
				arg1.primarySeqCycle -= local16.frameDelay[arg1.primarySeqFrame];
				arg1.primarySeqFrame++;
			}
			if (arg1.primarySeqFrame >= local16.framecount) {
				arg1.primarySeqFrame -= local16.replayoff;
				arg1.primarySeqPlays++;
				if (arg1.primarySeqPlays >= local16.replaycount) {
					arg1.primarySeq = -1;
				}
				if (arg1.primarySeqFrame < 0 || arg1.primarySeqFrame >= local16.framecount) {
					arg1.primarySeq = -1;
				}
			}
			arg1.needsForwardDrawPadding = local16.stretches;
		}
		if (arg1.primarySeqDelay > 0) {
			arg1.primarySeqDelay--;
		}
		if (arg1.spotAnimIndex != -1 && clientClock >= arg1.lastSpotAnimCycle) {
			if (arg1.spotAnimFrame < 0) {
				arg1.spotAnimFrame = 0;
			}
			local16 = SpotAnimType.instances[arg1.spotAnimIndex].seq;
			arg1.spotAnimCycle++;
			while (arg1.spotAnimFrame < local16.framecount && arg1.spotAnimCycle > local16.frameDelay[arg1.spotAnimFrame]) {
				arg1.spotAnimCycle -= local16.frameDelay[arg1.spotAnimFrame];
				arg1.spotAnimFrame++;
			}
			if (arg1.spotAnimFrame >= local16.framecount) {
				if (arg1.spotAnimFrame < 0 || arg1.spotAnimFrame >= local16.framecount) {
					arg1.spotAnimIndex = -1;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "r", descriptor = "(I)V")
	private void drawGame() {
		if (this.redrawTitleBackground) {
			this.redrawTitleBackground = false;
			this.backleft1.drawAt(11, super.graphics, 0);
			this.backleft2.drawAt(375, super.graphics, 0);
			this.backright1.drawAt(5, super.graphics, 729);
			this.backright2.drawAt(231, super.graphics, 752);
			this.backtop1.drawAt(0, super.graphics, 0);
			this.backtop2.drawAt(0, super.graphics, 561);
			this.backvmid1.drawAt(11, super.graphics, 520);
			this.backvmid2.drawAt(231, super.graphics, 520);
			this.backvmid3.drawAt(375, super.graphics, 501);
			this.backhmid2.drawAt(345, super.graphics, 0);
			this.redrawSidebar = true;
			this.redrawChatback = true;
			this.sidebarRedrawIcons = true;
			this.redrawChat = true;
			if (this.sceneState != 2) {
				this.areaViewport.drawAt(11, super.graphics, 8);
				this.areaMapback.drawAt(5, super.graphics, 561);
			}
		}
		if (this.sceneState == 2) {
			this.drawViewport();
		}
		if (this.menuVisible && this.mouseArea == 1) {
			this.redrawSidebar = true;
		}
		@Pc(152) boolean local152;
		if (this.sidebarInterfaceId != -1) {
			local152 = this.animateInterface(this.sidebarInterfaceId, this.sceneDelta);
			if (local152) {
				this.redrawSidebar = true;
			}
		}
		if (this.selectedArea == 2) {
			this.redrawSidebar = true;
		}
		if (this.objDragArea == 2) {
			this.redrawSidebar = true;
		}
		if (this.redrawSidebar) {
			this.drawInventory();
			this.redrawSidebar = false;
		}
		if (this.chatbackComponentId == -1) {
			this.component.scrollY = this.chatScrollY - this.chatScrollAmount - 77;
			if (super.mouseX > 453 && super.mouseX < 565 && super.mouseY > 350) {
				this.updateInterfaceScrollbar(super.mouseX - 22, super.mouseY - 375, this.chatScrollY, 77, false, 463, 0, this.component);
			}
			@Pc(234) int local234 = this.chatScrollY - this.component.scrollY - 77;
			if (local234 < 0) {
				local234 = 0;
			}
			if (local234 > this.chatScrollY - 77) {
				local234 = this.chatScrollY - 77;
			}
			if (this.chatScrollAmount != local234) {
				this.chatScrollAmount = local234;
				this.redrawChatback = true;
			}
		}
		if (this.chatbackComponentId != -1) {
			local152 = this.animateInterface(this.chatbackComponentId, this.sceneDelta);
			if (local152) {
				this.redrawChatback = true;
			}
		}
		if (this.selectedArea == 3) {
			this.redrawChatback = true;
		}
		if (this.objDragArea == 3) {
			this.redrawChatback = true;
		}
		if (this.chatbackMessage != null) {
			this.redrawChatback = true;
		}
		if (this.menuVisible && this.mouseArea == 2) {
			this.redrawChatback = true;
		}
		if (this.redrawChatback) {
			this.drawChatback();
			this.redrawChatback = false;
		}
		if (this.sceneState == 2) {
			this.drawMinimap();
			this.areaMapback.drawAt(5, super.graphics, 561);
		}
		if (this.flashingSidebarId != -1) {
			this.sidebarRedrawIcons = true;
		}
		if (this.sidebarRedrawIcons) {
			if (this.flashingSidebarId != -1 && this.flashingSidebarId == this.selectedTab) {
				this.flashingSidebarId = -1;
				this.outBuffer.p1isaac(175);
				this.outBuffer.p1(this.selectedTab);
			}
			this.sidebarRedrawIcons = false;
			this.areaBackhmid1.makeTarget();
			this.backhmid1.draw(0, 0);
			if (this.sidebarInterfaceId == -1) {
				if (this.tabComponentId[this.selectedTab] != -1) {
					if (this.selectedTab == 0) {
						this.redstone1.draw(30, 29);
					}
					if (this.selectedTab == 1) {
						this.redstone2.draw(29, 59);
					}
					if (this.selectedTab == 2) {
						this.redstone2.draw(29, 87);
					}
					if (this.selectedTab == 3) {
						this.redstone3.draw(29, 115);
					}
					if (this.selectedTab == 4) {
						this.redstone2h.draw(29, 156);
					}
					if (this.selectedTab == 5) {
						this.redstone2h.draw(29, 184);
					}
					if (this.selectedTab == 6) {
						this.redstone1h.draw(30, 212);
					}
				}
				if (this.tabComponentId[0] != -1 && (this.flashingSidebarId != 0 || clientClock % 20 < 10)) {
					this.sideicons[0].draw(34, 35);
				}
				if (this.tabComponentId[1] != -1 && (this.flashingSidebarId != 1 || clientClock % 20 < 10)) {
					this.sideicons[1].draw(32, 59);
				}
				if (this.tabComponentId[2] != -1 && (this.flashingSidebarId != 2 || clientClock % 20 < 10)) {
					this.sideicons[2].draw(32, 86);
				}
				if (this.tabComponentId[3] != -1 && (this.flashingSidebarId != 3 || clientClock % 20 < 10)) {
					this.sideicons[3].draw(33, 121);
				}
				if (this.tabComponentId[4] != -1 && (this.flashingSidebarId != 4 || clientClock % 20 < 10)) {
					this.sideicons[4].draw(34, 157);
				}
				if (this.tabComponentId[5] != -1 && (this.flashingSidebarId != 5 || clientClock % 20 < 10)) {
					this.sideicons[5].draw(32, 185);
				}
				if (this.tabComponentId[6] != -1 && (this.flashingSidebarId != 6 || clientClock % 20 < 10)) {
					this.sideicons[6].draw(34, 212);
				}
			}
			this.areaBackhmid1.drawAt(165, super.graphics, 520);
			this.areaBackbase2.makeTarget();
			this.backbase2.draw(0, 0);
			if (this.sidebarInterfaceId == -1) {
				if (this.tabComponentId[this.selectedTab] != -1) {
					if (this.selectedTab == 7) {
						this.redstone1v.draw(0, 49);
					}
					if (this.selectedTab == 8) {
						this.redstone2v.draw(0, 81);
					}
					if (this.selectedTab == 9) {
						this.redstone2v.draw(0, 108);
					}
					if (this.selectedTab == 10) {
						this.redstone3v.draw(1, 136);
					}
					if (this.selectedTab == 11) {
						this.redstone2vh.draw(0, 178);
					}
					if (this.selectedTab == 12) {
						this.redstone2vh.draw(0, 205);
					}
					if (this.selectedTab == 13) {
						this.redstone1vh.draw(0, 233);
					}
				}
				if (this.tabComponentId[8] != -1 && (this.flashingSidebarId != 8 || clientClock % 20 < 10)) {
					this.sideicons[7].draw(2, 80);
				}
				if (this.tabComponentId[9] != -1 && (this.flashingSidebarId != 9 || clientClock % 20 < 10)) {
					this.sideicons[8].draw(3, 107);
				}
				if (this.tabComponentId[10] != -1 && (this.flashingSidebarId != 10 || clientClock % 20 < 10)) {
					this.sideicons[9].draw(4, 142);
				}
				if (this.tabComponentId[11] != -1 && (this.flashingSidebarId != 11 || clientClock % 20 < 10)) {
					this.sideicons[10].draw(2, 179);
				}
				if (this.tabComponentId[12] != -1 && (this.flashingSidebarId != 12 || clientClock % 20 < 10)) {
					this.sideicons[11].draw(2, 206);
				}
				if (this.tabComponentId[13] != -1 && (this.flashingSidebarId != 13 || clientClock % 20 < 10)) {
					this.sideicons[12].draw(2, 230);
				}
			}
			this.areaBackbase2.drawAt(492, super.graphics, 501);
			this.areaViewport.makeTarget();
		}
		if (this.redrawChat) {
			this.redrawChat = false;
			this.areaBackbase1.makeTarget();
			this.backbase1.draw(0, 0);
			this.plain12.drawCentered(57, 16777215, true, 33, "Public chat");
			if (this.chatPublicSetting == 0) {
				this.plain12.drawCentered(57, 65280, true, 46, "On");
			}
			if (this.chatPublicSetting == 1) {
				this.plain12.drawCentered(57, 16776960, true, 46, "Friends");
			}
			if (this.chatPublicSetting == 2) {
				this.plain12.drawCentered(57, 16711680, true, 46, "Off");
			}
			if (this.chatPublicSetting == 3) {
				this.plain12.drawCentered(57, 65535, true, 46, "Hide");
			}
			this.plain12.drawCentered(186, 16777215, true, 33, "Private chat");
			if (this.chatPrivateSetting == 0) {
				this.plain12.drawCentered(186, 65280, true, 46, "On");
			}
			if (this.chatPrivateSetting == 1) {
				this.plain12.drawCentered(186, 16776960, true, 46, "Friends");
			}
			if (this.chatPrivateSetting == 2) {
				this.plain12.drawCentered(186, 16711680, true, 46, "Off");
			}
			this.plain12.drawCentered(326, 16777215, true, 33, "Trade/duel");
			if (this.chatTradeDuelSetting == 0) {
				this.plain12.drawCentered(326, 65280, true, 46, "On");
			}
			if (this.chatTradeDuelSetting == 1) {
				this.plain12.drawCentered(326, 16776960, true, 46, "Friends");
			}
			if (this.chatTradeDuelSetting == 2) {
				this.plain12.drawCentered(326, 16711680, true, 46, "Off");
			}
			this.plain12.drawCentered(462, 16777215, true, 38, "Report abuse");
			this.areaBackbase1.drawAt(471, super.graphics, 0);
			this.areaViewport.makeTarget();
		}
		this.sceneDelta = 0;
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(II)Z")
	private boolean isFriend(@OriginalArg(1) int arg1) {
		if (arg1 < 0) {
			return false;
		}
		@Pc(8) int local8 = this.optionType[arg1];
		if (local8 >= 2000) {
			local8 -= 2000;
		}
		return local8 == 406;
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(II)V")
	private void useMenuOption(@OriginalArg(1) int arg1) {
		if (arg1 >= 0) {
			if (this.checkInputType) {
				this.checkInputType = false;
				this.redrawChatback = true;
			}
			@Pc(18) int local18 = this.optionParamA[arg1];
			@Pc(23) int local23 = this.optionParamB[arg1];
			@Pc(28) int local28 = this.optionType[arg1];
			@Pc(33) int local33 = this.optionParamC[arg1];
			if (local28 >= 2000) {
				local28 -= 2000;
			}
			@Pc(48) String local48;
			@Pc(52) int local52;
			@Pc(69) String local69;
			@Pc(73) int local73;
			if (local28 == 903 || local28 == 363) {
				local48 = this.options[arg1];
				local52 = local48.indexOf("@whi@");
				if (local52 != -1) {
					local48 = local48.substring(local52 + 5).trim();
					local69 = StringUtils.formatName(StringUtils.fromBase37(StringUtils.toBase37(local48)));
					@Pc(71) boolean local71 = false;
					for (local73 = 0; local73 < this.playerCount; local73++) {
						@Pc(83) PlayerEntity local83 = this.playerEntities[this.playerIndices[local73]];
						if (local83 != null && local83.name != null && local83.name.equalsIgnoreCase(local69)) {
							this.tryMove(this.self.pathTileX[0], 1, false, local83.pathTileX[0], this.self.pathTileZ[0], 2, 1, local83.pathTileZ[0], 0, 0, 0);
							if (local28 == 903) {
								this.outBuffer.p1isaac(206);
							}
							if (local28 == 363) {
								this.outBuffer.p1isaac(164);
							}
							this.outBuffer.p2(this.playerIndices[local73]);
							local71 = true;
							break;
						}
					}
					if (!local71) {
						this.addMessage(0, "Unable to find " + local69, "");
					}
				}
			}
			if (local28 == 450 && this.interactWithLoc(75, local18, local23, local33)) {
				this.outBuffer.p2(this.objInterface);
				this.outBuffer.p2(this.selectedObjSlot);
				this.outBuffer.p2(this.selectedObjInterface);
			}
			if (local28 == 405 || local28 == 38 || local28 == 422 || local28 == 478 || local28 == 347) {
				if (local28 == 478) {
					if ((local18 & 0x3) == 0) {
						opHeld1Counter++;
					}
					if (opHeld1Counter >= 90) {
						this.outBuffer.p1isaac(220);
					}
					this.outBuffer.p1isaac(157);
				}
				if (local28 == 347) {
					this.outBuffer.p1isaac(211);
				}
				if (local28 == 422) {
					this.outBuffer.p1isaac(133);
				}
				if (local28 == 405) {
					opHeld4Counter += local33;
					if (opHeld4Counter >= 97) {
						this.outBuffer.p1isaac(30);
						this.outBuffer.p3(14953816);
					}
					this.outBuffer.p1isaac(195);
				}
				if (local28 == 38) {
					this.outBuffer.p1isaac(71);
				}
				this.outBuffer.p2(local33);
				this.outBuffer.p2(local18);
				this.outBuffer.p2(local23);
				this.selectedCycle = 0;
				this.selectedInterface = local23;
				this.selectedItem = local18;
				this.selectedArea = 2;
				if (Component.instances[local23].parent == this.viewportInterfaceIndex) {
					this.selectedArea = 1;
				}
				if (Component.instances[local23].parent == this.chatbackComponentId) {
					this.selectedArea = 3;
				}
			}
			@Pc(345) NpcEntity local345;
			if (local28 == 728 || local28 == 542 || local28 == 6 || local28 == 963 || local28 == 245) {
				local345 = this.npcEntities[local33];
				if (local345 != null) {
					this.tryMove(this.self.pathTileX[0], 1, false, local345.pathTileX[0], this.self.pathTileZ[0], 2, 1, local345.pathTileZ[0], 0, 0, 0);
					this.crossX = super.clickX;
					this.crossY = super.clickY;
					this.crossType = 2;
					this.crossCycle = 0;
					if (local28 == 542) {
						this.outBuffer.p1isaac(8);
					}
					if (local28 == 6) {
						if ((local33 & 0x3) == 0) {
							opNpc3Counter++;
						}
						if (opNpc3Counter >= 124) {
							this.outBuffer.p1isaac(88);
							this.outBuffer.p4(0);
						}
						this.outBuffer.p1isaac(27);
					}
					if (local28 == 963) {
						this.outBuffer.p1isaac(113);
					}
					if (local28 == 728) {
						this.outBuffer.p1isaac(194);
					}
					if (local28 == 245) {
						if ((local33 & 0x3) == 0) {
							opNpc5Counter++;
						}
						if (opNpc5Counter >= 85) {
							this.outBuffer.p1isaac(176);
							this.outBuffer.p2(39596);
						}
						this.outBuffer.p1isaac(100);
					}
					this.outBuffer.p2(local33);
				}
			}
			@Pc(500) boolean local500;
			if (local28 == 217) {
				local500 = this.tryMove(this.self.pathTileX[0], 0, false, local18, this.self.pathTileZ[0], 2, 0, local23, 0, 0, 0);
				if (!local500) {
					this.tryMove(this.self.pathTileX[0], 1, false, local18, this.self.pathTileZ[0], 2, 1, local23, 0, 0, 0);
				}
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				this.outBuffer.p1isaac(239);
				this.outBuffer.p2(local18 + this.baseTileX);
				this.outBuffer.p2(local23 + this.baseTileZ);
				this.outBuffer.p2(local33);
				this.outBuffer.p2(this.objInterface);
				this.outBuffer.p2(this.selectedObjSlot);
				this.outBuffer.p2(this.selectedObjInterface);
			}
			if (local28 == 1175) {
				@Pc(586) int local586 = local33 >> 14 & 0x7FFF;
				@Pc(589) LocType local589 = LocType.get(local586);
				if (local589.desc == null) {
					local69 = "It's a " + local589.name + ".";
				} else {
					local69 = new String(local589.desc);
				}
				this.addMessage(0, local69, "");
			}
			if (local28 == 285) {
				this.interactWithLoc(245, local18, local23, local33);
			}
			if (local28 == 881) {
				this.outBuffer.p1isaac(130);
				this.outBuffer.p2(local33);
				this.outBuffer.p2(local18);
				this.outBuffer.p2(local23);
				this.outBuffer.p2(this.objInterface);
				this.outBuffer.p2(this.selectedObjSlot);
				this.outBuffer.p2(this.selectedObjInterface);
				this.selectedCycle = 0;
				this.selectedInterface = local23;
				this.selectedItem = local18;
				this.selectedArea = 2;
				if (Component.instances[local23].parent == this.viewportInterfaceIndex) {
					this.selectedArea = 1;
				}
				if (Component.instances[local23].parent == this.chatbackComponentId) {
					this.selectedArea = 3;
				}
			}
			if (local28 == 391) {
				this.outBuffer.p1isaac(48);
				this.outBuffer.p2(local33);
				this.outBuffer.p2(local18);
				this.outBuffer.p2(local23);
				this.outBuffer.p2(this.spellInterface);
				this.selectedCycle = 0;
				this.selectedInterface = local23;
				this.selectedItem = local18;
				this.selectedArea = 2;
				if (Component.instances[local23].parent == this.viewportInterfaceIndex) {
					this.selectedArea = 1;
				}
				if (Component.instances[local23].parent == this.chatbackComponentId) {
					this.selectedArea = 3;
				}
			}
			if (local28 == 660) {
				if (this.menuVisible) {
					this.mapSquare.setClick(local23 - 11, local18 - 8);
				} else {
					this.mapSquare.setClick(super.clickY - 11, super.clickX - 8);
				}
			}
			if (local28 == 188) {
				this.selectedObject = 1;
				this.selectedObjSlot = local18;
				this.selectedObjInterface = local23;
				this.objInterface = local33;
				this.selectedObjName = ObjType.get(local33).name;
				this.selectedSpell = 0;
			} else {
				if (local28 == 44 && !this.chatContinuingDialogue) {
					this.outBuffer.p1isaac(235);
					this.outBuffer.p2(local23);
					this.chatContinuingDialogue = true;
				}
				@Pc(830) ObjType local830;
				@Pc(845) String local845;
				if (local28 == 1773) {
					local830 = ObjType.get(local33);
					if (local23 >= 100000) {
						local845 = local23 + " x " + local830.name;
					} else if (local830.desc == null) {
						local845 = "It's a " + local830.name + ".";
					} else {
						local845 = new String(local830.desc);
					}
					this.addMessage(0, local845, "");
				}
				if (local28 == 900) {
					local345 = this.npcEntities[local33];
					if (local345 != null) {
						this.tryMove(this.self.pathTileX[0], 1, false, local345.pathTileX[0], this.self.pathTileZ[0], 2, 1, local345.pathTileZ[0], 0, 0, 0);
						this.crossX = super.clickX;
						this.crossY = super.clickY;
						this.crossType = 2;
						this.crossCycle = 0;
						this.outBuffer.p1isaac(202);
						this.outBuffer.p2(local33);
						this.outBuffer.p2(this.objInterface);
						this.outBuffer.p2(this.selectedObjSlot);
						this.outBuffer.p2(this.selectedObjInterface);
					}
				}
				@Pc(969) PlayerEntity local969;
				if (local28 == 1373 || local28 == 1544 || local28 == 151 || local28 == 1101) {
					local969 = this.playerEntities[local33];
					if (local969 != null) {
						this.tryMove(this.self.pathTileX[0], 1, false, local969.pathTileX[0], this.self.pathTileZ[0], 2, 1, local969.pathTileZ[0], 0, 0, 0);
						this.crossX = super.clickX;
						this.crossY = super.clickY;
						this.crossType = 2;
						this.crossCycle = 0;
						if (local28 == 1101) {
							this.outBuffer.p1isaac(164);
						}
						if (local28 == 151) {
							opPlayer2Counter++;
							if (opPlayer2Counter >= 90) {
								this.outBuffer.p1isaac(2);
								this.outBuffer.p2(31114);
							}
							this.outBuffer.p1isaac(53);
						}
						if (local28 == 1373) {
							this.outBuffer.p1isaac(206);
						}
						if (local28 == 1544) {
							this.outBuffer.p1isaac(185);
						}
						this.outBuffer.p2(local33);
					}
				}
				if (local28 == 265) {
					local345 = this.npcEntities[local33];
					if (local345 != null) {
						this.tryMove(this.self.pathTileX[0], 1, false, local345.pathTileX[0], this.self.pathTileZ[0], 2, 1, local345.pathTileZ[0], 0, 0, 0);
						this.crossX = super.clickX;
						this.crossY = super.clickY;
						this.crossType = 2;
						this.crossCycle = 0;
						this.outBuffer.p1isaac(134);
						this.outBuffer.p2(local33);
						this.outBuffer.p2(this.spellInterface);
					}
				}
				@Pc(1156) long local1156;
				if (local28 == 679) {
					local48 = this.options[arg1];
					local52 = local48.indexOf("@whi@");
					if (local52 != -1) {
						local1156 = StringUtils.toBase37(local48.substring(local52 + 5).trim());
						local73 = -1;
						for (@Pc(1160) int local1160 = 0; local1160 < this.friendCount; local1160++) {
							if (this.friendName37[local1160] == local1156) {
								local73 = local1160;
								break;
							}
						}
						if (local73 != -1 && this.friendWorld[local73] > 0) {
							this.redrawChatback = true;
							this.checkInputType = false;
							this.showSocialInput = true;
							this.socialInput = "";
							this.socialAction = 3;
							this.socialName37 = this.friendName37[local73];
							this.socialMessage = "Enter message to send to " + this.friendName[local73];
						}
					}
				}
				if (local28 == 55 && this.interactWithLoc(9, local18, local23, local33)) {
					this.outBuffer.p2(this.spellInterface);
				}
				if (local28 == 224 || local28 == 993 || local28 == 99 || local28 == 746 || local28 == 877) {
					local500 = this.tryMove(this.self.pathTileX[0], 0, false, local18, this.self.pathTileZ[0], 2, 0, local23, 0, 0, 0);
					if (!local500) {
						this.tryMove(this.self.pathTileX[0], 1, false, local18, this.self.pathTileZ[0], 2, 1, local23, 0, 0, 0);
					}
					this.crossX = super.clickX;
					this.crossY = super.clickY;
					this.crossType = 2;
					this.crossCycle = 0;
					if (local28 == 224) {
						this.outBuffer.p1isaac(140);
					}
					if (local28 == 746) {
						this.outBuffer.p1isaac(178);
					}
					if (local28 == 877) {
						this.outBuffer.p1isaac(247);
					}
					if (local28 == 99) {
						this.outBuffer.p1isaac(200);
					}
					if (local28 == 993) {
						this.outBuffer.p1isaac(40);
					}
					this.outBuffer.p2(local18 + this.baseTileX);
					this.outBuffer.p2(local23 + this.baseTileZ);
					this.outBuffer.p2(local33);
				}
				if (local28 == 1607) {
					local345 = this.npcEntities[local33];
					if (local345 != null) {
						if (local345.type.desc == null) {
							local845 = "It's a " + local345.type.name + ".";
						} else {
							local845 = new String(local345.type.desc);
						}
						this.addMessage(0, local845, "");
					}
				}
				if (local28 == 504) {
					this.interactWithLoc(172, local18, local23, local33);
				}
				@Pc(1429) Component local1429;
				if (local28 == 930) {
					local1429 = Component.instances[local23];
					this.selectedSpell = 1;
					this.spellInterface = local23;
					this.selectedFlags = local1429.optionFlags;
					this.selectedObject = 0;
					local845 = local1429.optionCircumfix;
					if (local845.indexOf(" ") != -1) {
						local845 = local845.substring(0, local845.indexOf(" "));
					}
					local69 = local1429.optionCircumfix;
					if (local69.indexOf(" ") != -1) {
						local69 = local69.substring(local69.indexOf(" ") + 1);
					}
					this.selectedSpellPrefix = local845 + " " + local1429.optionSuffix + " " + local69;
					if (this.selectedFlags == 16) {
						this.redrawSidebar = true;
						this.selectedTab = 3;
						this.sidebarRedrawIcons = true;
					}
				} else {
					if (local28 == 951) {
						local1429 = Component.instances[local23];
						@Pc(1513) boolean local1513 = true;
						if (local1429.clientcode > 0) {
							local1513 = this.handleComponentAction(local1429);
						}
						if (local1513) {
							this.outBuffer.p1isaac(155);
							this.outBuffer.p2(local23);
						}
					}
					if (local28 == 602 || local28 == 596 || local28 == 22 || local28 == 892 || local28 == 415) {
						if (local28 == 22) {
							this.outBuffer.p1isaac(212);
						}
						if (local28 == 415) {
							if ((local23 & 0x3) == 0) {
								ifButton5Counter++;
							}
							if (ifButton5Counter >= 55) {
								this.outBuffer.p1isaac(17);
								this.outBuffer.p4(0);
							}
							this.outBuffer.p1isaac(6);
						}
						if (local28 == 602) {
							this.outBuffer.p1isaac(31);
						}
						if (local28 == 892) {
							if ((local18 & 0x3) == 0) {
								opHeld9Counter++;
							}
							if (opHeld9Counter >= 130) {
								this.outBuffer.p1isaac(238);
								this.outBuffer.p1(177);
							}
							this.outBuffer.p1isaac(38);
						}
						if (local28 == 596) {
							this.outBuffer.p1isaac(59);
						}
						this.outBuffer.p2(local33);
						this.outBuffer.p2(local18);
						this.outBuffer.p2(local23);
						this.selectedCycle = 0;
						this.selectedInterface = local23;
						this.selectedItem = local18;
						this.selectedArea = 2;
						if (Component.instances[local23].parent == this.viewportInterfaceIndex) {
							this.selectedArea = 1;
						}
						if (Component.instances[local23].parent == this.chatbackComponentId) {
							this.selectedArea = 3;
						}
					}
					if (local28 == 581) {
						if ((local33 & 0x3) == 0) {
							opLoc4Counter++;
						}
						if (opLoc4Counter >= 99) {
							this.outBuffer.p1isaac(7);
							this.outBuffer.p4(0);
						}
						this.interactWithLoc(97, local18, local23, local33);
					}
					if (local28 == 965) {
						local500 = this.tryMove(this.self.pathTileX[0], 0, false, local18, this.self.pathTileZ[0], 2, 0, local23, 0, 0, 0);
						if (!local500) {
							this.tryMove(this.self.pathTileX[0], 1, false, local18, this.self.pathTileZ[0], 2, 1, local23, 0, 0, 0);
						}
						this.crossX = super.clickX;
						this.crossY = super.clickY;
						this.crossType = 2;
						this.crossCycle = 0;
						this.outBuffer.p1isaac(138);
						this.outBuffer.p2(local18 + this.baseTileX);
						this.outBuffer.p2(local23 + this.baseTileZ);
						this.outBuffer.p2(local33);
						this.outBuffer.p2(this.spellInterface);
					}
					if (local28 == 1501) {
						opLoc5Counter += this.baseTileZ;
						if (opLoc5Counter >= 92) {
							this.outBuffer.p1isaac(66);
							this.outBuffer.p4(0);
						}
						this.interactWithLoc(116, local18, local23, local33);
					}
					if (local28 == 364) {
						this.interactWithLoc(96, local18, local23, local33);
					}
					if (local28 == 1102) {
						local830 = ObjType.get(local33);
						if (local830.desc == null) {
							local845 = "It's a " + local830.name + ".";
						} else {
							local845 = new String(local830.desc);
						}
						this.addMessage(0, local845, "");
					}
					if (local28 == 960) {
						this.outBuffer.p1isaac(155);
						this.outBuffer.p2(local23);
						local1429 = Component.instances[local23];
						if (local1429.script != null && local1429.script[0][0] == 5) {
							local52 = local1429.script[0][1];
							if (this.variables[local52] != local1429.scriptCompareValue[0]) {
								this.variables[local52] = local1429.scriptCompareValue[0];
								this.updateVarp(local52);
								this.redrawSidebar = true;
							}
						}
					}
					if (local28 == 34) {
						local48 = this.options[arg1];
						local52 = local48.indexOf("@whi@");
						if (local52 != -1) {
							this.closeInterface();
							this.reportInput = local48.substring(local52 + 5).trim();
							this.reportAbuseMuteToggle = false;
							for (@Pc(1957) int local1957 = 0; local1957 < Component.instances.length; local1957++) {
								if (Component.instances[local1957] != null && Component.instances[local1957].clientcode == 600) {
									this.openInterfaceId = this.viewportInterfaceIndex = Component.instances[local1957].parent;
									break;
								}
							}
						}
					}
					if (local28 == 947) {
						this.closeInterface();
					}
					if (local28 == 367) {
						local969 = this.playerEntities[local33];
						if (local969 != null) {
							this.tryMove(this.self.pathTileX[0], 1, false, local969.pathTileX[0], this.self.pathTileZ[0], 2, 1, local969.pathTileZ[0], 0, 0, 0);
							this.crossX = super.clickX;
							this.crossY = super.clickY;
							this.crossType = 2;
							this.crossCycle = 0;
							this.outBuffer.p1isaac(248);
							this.outBuffer.p2(local33);
							this.outBuffer.p2(this.objInterface);
							this.outBuffer.p2(this.selectedObjSlot);
							this.outBuffer.p2(this.selectedObjInterface);
						}
					}
					if (local28 == 465) {
						this.outBuffer.p1isaac(155);
						this.outBuffer.p2(local23);
						local1429 = Component.instances[local23];
						if (local1429.script != null && local1429.script[0][0] == 5) {
							local52 = local1429.script[0][1];
							this.variables[local52] = 1 - this.variables[local52];
							this.updateVarp(local52);
							this.redrawSidebar = true;
						}
					}
					if (local28 == 406 || local28 == 436 || local28 == 557 || local28 == 556) {
						local48 = this.options[arg1];
						local52 = local48.indexOf("@whi@");
						if (local52 != -1) {
							local1156 = StringUtils.toBase37(local48.substring(local52 + 5).trim());
							if (local28 == 406) {
								this.addFriend(local1156);
							}
							if (local28 == 436) {
								this.addIgnore(local1156);
							}
							if (local28 == 557) {
								this.removeFriend(local1156);
							}
							if (local28 == 556) {
								this.removeIgnore(local1156);
							}
						}
					}
					if (local28 == 651) {
						local969 = this.playerEntities[local33];
						if (local969 != null) {
							this.tryMove(this.self.pathTileX[0], 1, false, local969.pathTileX[0], this.self.pathTileZ[0], 2, 1, local969.pathTileZ[0], 0, 0, 0);
							this.crossX = super.clickX;
							this.crossY = super.clickY;
							this.crossType = 2;
							this.crossCycle = 0;
							this.outBuffer.p1isaac(177);
							this.outBuffer.p2(local33);
							this.outBuffer.p2(this.spellInterface);
						}
					}
					this.selectedObject = 0;
					this.selectedSpell = 0;
					this.redrawSidebar = true; // added to fix sidebar not updating when drawing item outlines
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "s", descriptor = "(I)Ljava/lang/String;")
	private String getHost() {
		if (SignedLink.mainapp == null) {
			return super.frame == null ? super.getDocumentBase().getHost().toLowerCase() : "runescape.com";
		} else {
			return SignedLink.mainapp.getDocumentBase().getHost().toLowerCase();
		}
	}

	@OriginalMember(owner = "client!client", name = "t", descriptor = "(I)V")
	private void drawMenu() {
		@Pc(2) int local2 = this.menuX;
		@Pc(5) int local5 = this.menuY;
		@Pc(8) int local8 = this.menuWidth;
		@Pc(11) int local11 = this.menuHeight;
		@Pc(13) int local13 = 6116423;
		Draw2D.fillRect(local5, local2, local13, local8, local11);
		Draw2D.fillRect(local5 + 1, local2 + 1, 0, local8 - 2, 16);
		Draw2D.drawRect(local2 + 1, 0, local11 - 19, local5 + 18, local8 - 2);
		this.bold12.draw(local2 + 3, local5 + 14, local13, "Choose Option");
		@Pc(63) int local63 = super.mouseX;
		@Pc(66) int local66 = super.mouseY;
		if (this.mouseArea == 0) {
			local63 -= 8;
			local66 -= 11;
		}
		if (this.mouseArea == 1) {
			local63 -= 562;
			local66 -= 231;
		}
		if (this.mouseArea == 2) {
			local63 -= 22;
			local66 -= 375;
		}
		for (@Pc(85) int local85 = 0; local85 < this.optionCount; local85++) {
			@Pc(100) int local100 = local5 + (this.optionCount - 1 - local85) * 15 + 31;
			@Pc(102) int local102 = 16777215;
			if (local63 > local2 && local63 < local2 + local8 && local66 > local100 - 13 && local66 < local100 + 3) {
				local102 = 16776960;
			}
			this.bold12.draw(local2 + 3, local100, this.options[local85], true, local102);
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(III)V")
	private void handleChatInput(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2) {
		if (this.splitPrivateChat != 0) {
			@Pc(5) int local5 = 0;
			if (this.systemUpdateTimer != 0) {
				local5 = 1;
			}
			for (@Pc(12) int local12 = 0; local12 < 100; local12++) {
				if (this.chatMessage[local12] != null) {
					@Pc(24) int local24 = this.chatMessageType[local12];
					if ((local24 == 3 || local24 == 7) && (local24 == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[local12]))) {
						@Pc(54) int local54 = 329 - local5 * 13;
						if (super.mouseX > 8 && super.mouseX < 520 && arg2 - 11 > local54 - 10 && arg2 - 11 <= local54 + 3) {
							if (this.modRights) {
								this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[local12];
								this.optionType[this.optionCount] = 2034;
								this.optionCount++;
							}
							this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[local12];
							this.optionType[this.optionCount] = 2436;
							this.optionCount++;
							this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[local12];
							this.optionType[this.optionCount] = 2406;
							this.optionCount++;
						}
						local5++;
						if (local5 >= 5) {
							return;
						}
					}
					if ((local24 == 5 || local24 == 6) && this.chatPrivateSetting < 2) {
						local5++;
						if (local5 >= 5) {
							return;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!hc;)V")
	private void updateComponentContent(@OriginalArg(1) Component arg1) {
		@Pc(4) int local4 = arg1.clientcode;
		if (local4 >= 1 && local4 <= 100) {
			local4--;
			if (local4 >= this.friendCount) {
				arg1.text = "";
				arg1.buttontype = 0;
			} else {
				arg1.text = this.friendName[local4];
				arg1.buttontype = 1;
			}
		} else if (local4 >= 101 && local4 <= 200) {
			local4 -= 101;
			if (local4 >= this.friendCount) {
				arg1.text = "";
				arg1.buttontype = 0;
			} else {
				if (this.friendWorld[local4] == 0) {
					arg1.text = "@red@Offline";
				} else if (this.friendWorld[local4] == nodeId) {
					arg1.text = "@gre@World-" + (this.friendWorld[local4] - 9);
				} else {
					arg1.text = "@yel@World-" + (this.friendWorld[local4] - 9);
				}
				arg1.buttontype = 1;
			}
		} else if (local4 == 203) {
			arg1.scrollHeight = this.friendCount * 15 + 20;
			if (arg1.scrollHeight <= arg1.height) {
				arg1.scrollHeight = arg1.height + 1;
			}
		} else if (local4 >= 401 && local4 <= 500) {
			local4 -= 401;
			if (local4 >= this.ignoreCount) {
				arg1.text = "";
				arg1.buttontype = 0;
			} else {
				arg1.text = StringUtils.formatName(StringUtils.fromBase37(this.ignoreName37[local4]));
				arg1.buttontype = 1;
			}
		} else if (local4 == 503) {
			arg1.scrollHeight = this.ignoreCount * 15 + 20;
			if (arg1.scrollHeight <= arg1.height) {
				arg1.scrollHeight = arg1.height + 1;
			}
		} else if (local4 == 327) {
			arg1.xan = 150;
			arg1.yan = (int) (Math.sin((double) clientClock / 40.0D) * 256.0D) & 0x7FF;
			if (this.characterDesignUpdate) {
				this.characterDesignUpdate = false;
				@Pc(209) Model[] local209 = new Model[7];
				@Pc(211) int local211 = 0;
				for (@Pc(213) int local213 = 0; local213 < 7; local213++) {
					@Pc(220) int local220 = this.characterDesigns[local213];
					if (local220 >= 0) {
						local209[local211++] = IdkType.instances[local220].getModel();
					}
				}
				@Pc(241) Model local241 = new Model(local209, local211);
				for (@Pc(243) int local243 = 0; local243 < 5; local243++) {
					if (this.characterDesignColors[local243] != 0) {
						local241.recolor(PlayerEntity.APPEARANCE_COLORS[local243][0], PlayerEntity.APPEARANCE_COLORS[local243][this.characterDesignColors[local243]]);
						if (local243 == 1) {
							local241.recolor(PlayerEntity.BEARD_COLORS[0], PlayerEntity.BEARD_COLORS[this.characterDesignColors[local243]]);
						}
					}
				}
				local241.createLabelReferences();
				local241.applyTransform(SeqType.instances[this.self.standSeq].primaryFrames[0]);
				local241.calculateNormals(64, 850, -30, -50, -30, true);
				arg1.model = local241;
			}
		} else if (local4 == 324) {
			if (this.sprite == null) {
				this.sprite = arg1.graphic;
				this.spriteActive = arg1.activegraphic;
			}
			if (this.characterDesignIsMale) {
				arg1.graphic = this.spriteActive;
			} else {
				arg1.graphic = this.sprite;
			}
		} else if (local4 == 325) {
			if (this.sprite == null) {
				this.sprite = arg1.graphic;
				this.spriteActive = arg1.activegraphic;
			}
			if (this.characterDesignIsMale) {
				arg1.graphic = this.sprite;
			} else {
				arg1.graphic = this.spriteActive;
			}
		} else if (local4 == 600) {
			arg1.text = this.reportInput;
			if (clientClock % 20 < 10) {
				arg1.text = arg1.text + "|";
			} else {
				arg1.text = arg1.text + " ";
			}
		} else {
			if (local4 == 613) {
				if (!this.modRights) {
					arg1.text = "";
				} else if (this.reportAbuseMuteToggle) {
					arg1.colour = 16711680;
					arg1.text = "Moderator option: Mute player for 48 hours: <ON>";
				} else {
					arg1.colour = 16777215;
					arg1.text = "Moderator option: Mute player for 48 hours: <OFF>";
				}
			}
			@Pc(441) String local441;
			if (local4 == 650 || local4 == 655) {
				if (this.lastLoginIp == 0) {
					arg1.text = "";
				} else {
					if (this.daysSinceLogin == 0) {
						local441 = "earlier today";
					} else if (this.daysSinceLogin == 1) {
						local441 = "yesterday";
					} else {
						local441 = this.daysSinceLogin + " days ago";
					}
					arg1.text = "You last logged in " + local441 + " from: " + SignedLink.dns;
				}
			}
			if (local4 == 651) {
				if (this.unreadMessageCount == 0) {
					arg1.text = "0 unread messages";
					arg1.colour = 16776960;
				}
				if (this.unreadMessageCount == 1) {
					arg1.text = "1 unread message";
					arg1.colour = 65280;
				}
				if (this.unreadMessageCount > 1) {
					arg1.text = this.unreadMessageCount + " unread messages";
					arg1.colour = 65280;
				}
			}
			if (local4 == 652) {
				if (this.daysSinceRecoveryChange == 201) {
					arg1.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					arg1.text = "You have not yet set any password recovery questions.";
				} else {
					if (this.daysSinceRecoveryChange == 0) {
						local441 = "Earlier today";
					} else if (this.daysSinceRecoveryChange == 1) {
						local441 = "Yesterday";
					} else {
						local441 = this.daysSinceRecoveryChange + " days ago";
					}
					arg1.text = local441 + " you changed your recovery questions";
				}
			}
			if (local4 == 653) {
				if (this.daysSinceRecoveryChange == 201) {
					arg1.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					arg1.text = "We strongly recommend you do so now to secure your account.";
				} else {
					arg1.text = "If you do not remember making this change then cancel it immediately";
				}
			}
			if (local4 == 654) {
				if (this.daysSinceRecoveryChange == 201) {
					arg1.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					arg1.text = "Do this from the 'account management' area on our front webpage";
				} else {
					arg1.text = "Do this from the 'account management' area on our front webpage";
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([BII)Z")
	private boolean wavesave(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1) {
		return arg0 == null ? true : SignedLink.wavesave(arg0, arg1);
	}

	@OriginalMember(owner = "client!client", name = "u", descriptor = "(I)Z")
	private boolean wavereplay() {
		return SignedLink.wavereplay();
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(II)V")
	private void wavevol(@OriginalArg(0) int arg0) {
		SignedLink.wavevol = arg0;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!kb;I)V")
	private void readNewNpcs(@OriginalArg(1) Buffer arg1, @OriginalArg(2) int arg2) {
		while (arg1.bitOffset + 21 < arg2 * 8) {
			@Pc(16) int local16 = arg1.gBit(13);
			if (local16 == 8191) {
				break;
			}
			if (this.npcEntities[local16] == null) {
				this.npcEntities[local16] = new NpcEntity();
			}
			@Pc(36) NpcEntity local36 = this.npcEntities[local16];
			this.npcIndices[this.npcCount++] = local16;
			local36.removeTimer = clientClock;
			local36.type = NpcType.get(arg1.gBit(11));
			local36.index = local36.type.size;
			local36.runSeq = local36.type.walkanim;
			local36.walkSeq = local36.type.walkanim_b;
			local36.turnAroundSeq = local36.type.walkanim_r;
			local36.turnRightSeq = local36.type.walkanim_l;
			local36.standSeq = local36.type.readyanim;
			@Pc(92) int local92 = arg1.gBit(5);
			if (local92 > 15) {
				local92 -= 32;
			}
			@Pc(101) int local101 = arg1.gBit(5);
			if (local101 > 15) {
				local101 -= 32;
			}
			local36.move(false, this.self.pathTileX[0] + local92, this.self.pathTileZ[0] + local101);
			@Pc(128) int local128 = arg1.gBit(1);
			if (local128 == 1) {
				this.entityUpdateIndices[this.updateCount++] = local16;
			}
		}
		arg1.accessBytes();
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!hc;)Z")
	private boolean handleComponentAction(@OriginalArg(1) Component arg1) {
		@Pc(4) int local4 = arg1.clientcode;
		if (local4 == 201) {
			this.redrawChatback = true;
			this.checkInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 1;
			this.socialMessage = "Enter name of friend to add to list";
		}
		if (local4 == 202) {
			this.redrawChatback = true;
			this.checkInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 2;
			this.socialMessage = "Enter name of friend to delete from list";
		}
		if (local4 == 205) {
			this.idleTimeout = 250;
			return true;
		}
		if (local4 == 501) {
			this.redrawChatback = true;
			this.checkInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 4;
			this.socialMessage = "Enter name of player to add to list";
		}
		if (local4 == 502) {
			this.redrawChatback = true;
			this.checkInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 5;
			this.socialMessage = "Enter name of player to delete from list";
		}
		@Pc(112) int local112;
		@Pc(116) int local116;
		@Pc(121) int local121;
		if (local4 >= 300 && local4 <= 313) {
			local112 = (local4 - 300) / 2;
			local116 = local4 & 0x1;
			local121 = this.characterDesigns[local112];
			if (local121 != -1) {
				while (true) {
					if (local116 == 0) {
						local121--;
						if (local121 < 0) {
							local121 = IdkType.count - 1;
						}
					}
					if (local116 == 1) {
						local121++;
						if (local121 >= IdkType.count) {
							local121 = 0;
						}
					}
					if (!IdkType.instances[local121].disable && IdkType.instances[local121].type == local112 + (this.characterDesignIsMale ? 0 : 7)) {
						this.characterDesigns[local112] = local121;
						this.characterDesignUpdate = true;
						break;
					}
				}
			}
		}
		if (local4 >= 314 && local4 <= 323) {
			local112 = (local4 - 314) / 2;
			local116 = local4 & 0x1;
			local121 = this.characterDesignColors[local112];
			if (local116 == 0) {
				local121--;
				if (local121 < 0) {
					local121 = PlayerEntity.APPEARANCE_COLORS[local112].length - 1;
				}
			}
			if (local116 == 1) {
				local121++;
				if (local121 >= PlayerEntity.APPEARANCE_COLORS[local112].length) {
					local121 = 0;
				}
			}
			this.characterDesignColors[local112] = local121;
			this.characterDesignUpdate = true;
		}
		if (local4 == 324 && !this.characterDesignIsMale) {
			this.characterDesignIsMale = true;
			this.resetCharacterDesign();
		}
		if (local4 == 325 && this.characterDesignIsMale) {
			this.characterDesignIsMale = false;
			this.resetCharacterDesign();
		}
		if (local4 == 326) {
			this.outBuffer.p1isaac(52);
			this.outBuffer.p1(this.characterDesignIsMale ? 0 : 1);
			for (local112 = 0; local112 < 7; local112++) {
				this.outBuffer.p1(this.characterDesigns[local112]);
			}
			for (local116 = 0; local116 < 5; local116++) {
				this.outBuffer.p1(this.characterDesignColors[local116]);
			}
			return true;
		}
		if (local4 == 613) {
			this.reportAbuseMuteToggle = !this.reportAbuseMuteToggle;
		}
		if (local4 >= 601 && local4 <= 612) {
			this.closeInterface();
			if (this.reportInput.length() > 0) {
				this.outBuffer.p1isaac(190);
				this.outBuffer.p8(StringUtils.toBase37(this.reportInput));
				this.outBuffer.p1(local4 - 601);
				this.outBuffer.p1(this.reportAbuseMuteToggle ? 1 : 0);
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "()V")
	@Override
	protected void load() {
		if (SignedLink.sunjava) {
			super.mindel = 5;
		}
		if (!lowMemory) {
			this.startMidiThread = true;
			this.midiThreadActive = true;
			this.startThread(this, 2);
			this.setMidi(12345678, "scape_main", 40000);
		}
		if (alreadyStarted) {
			this.errorStarted = true;
			return;
		}
		alreadyStarted = true;
		@Pc(34) boolean local34 = false;
		@Pc(38) String local38 = this.getHost();
		if (local38.endsWith("jagex.com")) {
			local34 = true;
		}
		if (local38.endsWith("runescape.com")) {
			local34 = true;
		}
		if (local38.endsWith("192.168.1.2")) {
			local34 = true;
		}
		if (local38.endsWith("192.168.1.249")) {
			local34 = true;
		}
		if (local38.endsWith("192.168.1.252")) {
			local34 = true;
		}
		if (local38.endsWith("192.168.1.253")) {
			local34 = true;
		}
		if (local38.endsWith("192.168.1.254")) {
			local34 = true;
		}
		if (local38.endsWith("127.0.0.1")) {
			local34 = true;
		}
		if (!local34) {
			this.errorHost = true;
			return;
		}
		try {
			@Pc(94) int local94 = 5;
			this.archiveChecksums[8] = 0;
			while (this.archiveChecksums[8] == 0) {
				this.showProgress("Connecting to fileserver", 10);
				try {
					@Pc(119) DataInputStream local119 = this.openStream("crc" + (int) (Math.random() * 9.9999999E7D));
					@Pc(126) Buffer local126 = new Buffer(new byte[36]);
					local119.readFully(local126.data, 0, 36);
					for (@Pc(134) int local134 = 0; local134 < 9; local134++) {
						this.archiveChecksums[local134] = local126.g4();
					}
					local119.close();
				} catch (@Pc(150) IOException local150) {
					for (@Pc(152) int local152 = local94; local152 > 0; local152--) {
						this.showProgress("Error loading - Will retry in " + local152 + " secs.", 10);
						try {
							Thread.sleep(1000L);
						} catch (@Pc(171) Exception local171) {
						}
					}
					local94 *= 2;
					if (local94 > 60) {
						local94 = 60;
					}
				}
			}
			this.titleArchive = this.loadArchive("title screen", this.archiveChecksums[1], "title", 10);
			this.plain11 = new Font(this.titleArchive, "p11");
			this.plain12 = new Font(this.titleArchive, "p12");
			this.bold12 = new Font(this.titleArchive, "b12");
			this.quill8 = new Font(this.titleArchive, "q8");
			this.loadTitleBackground();
			this.loadTitleForeground();
			@Pc(255) FileArchive configArchive = this.loadArchive("config", this.archiveChecksums[2], "config", 15);
			@Pc(266) FileArchive interfaceArchive = this.loadArchive("interface", this.archiveChecksums[3], "interface", 20);
			@Pc(277) FileArchive mediaArchive = this.loadArchive("2d graphics", this.archiveChecksums[4], "media", 30);
			@Pc(288) FileArchive modelsArchive = this.loadArchive("3d graphics", this.archiveChecksums[5], "models", 40);
			@Pc(299) FileArchive texturesArchive = this.loadArchive("textures", this.archiveChecksums[6], "textures", 60);
			@Pc(310) FileArchive wordencArchive = this.loadArchive("chat system", this.archiveChecksums[7], "wordenc", 65);
			@Pc(321) FileArchive soundsArchive = this.loadArchive("sound effects", this.archiveChecksums[8], "sounds", 70);
			this.levelRenderFlags = new byte[4][104][104];
			this.levelHeightMaps = new int[4][105][105];
			this.mapSquare = new MapSquare(this.levelHeightMaps, 104, 4, 104);
			for (@Pc(346) int local346 = 0; local346 < 4; local346++) {
				this.collisionMaps[local346] = new CollisionMap(104, 104);
			}
			this.minimap = new Sprite(512, 512);
			this.showProgress("Unpacking media", 75);
			this.invback = new IndexedSprite(mediaArchive, "invback", 0);
			this.chatback = new IndexedSprite(mediaArchive, "chatback", 0);
			this.mapback = new IndexedSprite(mediaArchive, "mapback", 0);
			this.backbase1 = new IndexedSprite(mediaArchive, "backbase1", 0);
			this.backbase2 = new IndexedSprite(mediaArchive, "backbase2", 0);
			this.backhmid1 = new IndexedSprite(mediaArchive, "backhmid1", 0);
			for (@Pc(424) int local424 = 0; local424 < 13; local424++) {
				this.sideicons[local424] = new IndexedSprite(mediaArchive, "sideicons", local424);
			}
			this.compass = new Sprite(mediaArchive, "compass", 0);
			@Pc(450) int local450;
			try {
				for (local450 = 0; local450 < 50; local450++) {
					if (local450 == 22) {
						continue;
					}
					this.mapscene[local450] = new IndexedSprite(mediaArchive, "mapscene", local450);
				}
			} catch (@Pc(468) Exception local468) {
			}
			try {
				for (local450 = 0; local450 < 50; local450++) {
					this.mapfunction[local450] = new Sprite(mediaArchive, "mapfunction", local450);
				}
			} catch (@Pc(488) Exception local488) {
			}
			try {
				for (local450 = 0; local450 < 20; local450++) {
					this.hitmarks[local450] = new Sprite(mediaArchive, "hitmarks", local450);
				}
			} catch (@Pc(508) Exception local508) {
			}
			try {
				for (local450 = 0; local450 < 20; local450++) {
					this.headicons[local450] = new Sprite(mediaArchive, "headicons", local450);
				}
			} catch (@Pc(528) Exception local528) {
			}
			this.mapflags = new Sprite(mediaArchive, "mapflag", 0);
			for (local450 = 0; local450 < 8; local450++) {
				this.cross[local450] = new Sprite(mediaArchive, "cross", local450);
			}
			this.mapdot0 = new Sprite(mediaArchive, "mapdots", 0);
			this.mapdot1 = new Sprite(mediaArchive, "mapdots", 1);
			this.mapdot2 = new Sprite(mediaArchive, "mapdots", 2);
			this.mapdot3 = new Sprite(mediaArchive, "mapdots", 3);
			this.scrollbar1 = new IndexedSprite(mediaArchive, "scrollbar", 0);
			this.scrollbar2 = new IndexedSprite(mediaArchive, "scrollbar", 1);
			this.redstone1 = new IndexedSprite(mediaArchive, "redstone1", 0);
			this.redstone2 = new IndexedSprite(mediaArchive, "redstone2", 0);
			this.redstone3 = new IndexedSprite(mediaArchive, "redstone3", 0);
			this.redstone1h = new IndexedSprite(mediaArchive, "redstone1", 0);
			this.redstone1h.flipHorizontally();
			this.redstone2h = new IndexedSprite(mediaArchive, "redstone2", 0);
			this.redstone2h.flipHorizontally();
			this.redstone1v = new IndexedSprite(mediaArchive, "redstone1", 0);
			this.redstone1v.flipVertically();
			this.redstone2v = new IndexedSprite(mediaArchive, "redstone2", 0);
			this.redstone2v.flipVertically();
			this.redstone3v = new IndexedSprite(mediaArchive, "redstone3", 0);
			this.redstone3v.flipVertically();
			this.redstone1vh = new IndexedSprite(mediaArchive, "redstone1", 0);
			this.redstone1vh.flipHorizontally();
			this.redstone1vh.flipVertically();
			this.redstone2vh = new IndexedSprite(mediaArchive, "redstone2", 0);
			this.redstone2vh.flipHorizontally();
			this.redstone2vh.flipVertically();
			@Pc(725) Sprite local725 = new Sprite(mediaArchive, "backleft1", 0);
			this.backleft1 = new ImageProducerFrameBuffer(this.getBaseComponent(), local725.spriteWidth, local725.spriteHeight);
			local725.drawOpaque(0, 0);
			@Pc(750) Sprite local750 = new Sprite(mediaArchive, "backleft2", 0);
			this.backleft2 = new ImageProducerFrameBuffer(this.getBaseComponent(), local750.spriteWidth, local750.spriteHeight);
			local750.drawOpaque(0, 0);
			@Pc(775) Sprite local775 = new Sprite(mediaArchive, "backright1", 0);
			this.backright1 = new ImageProducerFrameBuffer(this.getBaseComponent(), local775.spriteWidth, local775.spriteHeight);
			local775.drawOpaque(0, 0);
			@Pc(800) Sprite local800 = new Sprite(mediaArchive, "backright2", 0);
			this.backright2 = new ImageProducerFrameBuffer(this.getBaseComponent(), local800.spriteWidth, local800.spriteHeight);
			local800.drawOpaque(0, 0);
			@Pc(825) Sprite local825 = new Sprite(mediaArchive, "backtop1", 0);
			this.backtop1 = new ImageProducerFrameBuffer(this.getBaseComponent(), local825.spriteWidth, local825.spriteHeight);
			local825.drawOpaque(0, 0);
			@Pc(850) Sprite local850 = new Sprite(mediaArchive, "backtop2", 0);
			this.backtop2 = new ImageProducerFrameBuffer(this.getBaseComponent(), local850.spriteWidth, local850.spriteHeight);
			local850.drawOpaque(0, 0);
			@Pc(875) Sprite local875 = new Sprite(mediaArchive, "backvmid1", 0);
			this.backvmid1 = new ImageProducerFrameBuffer(this.getBaseComponent(), local875.spriteWidth, local875.spriteHeight);
			local875.drawOpaque(0, 0);
			@Pc(900) Sprite local900 = new Sprite(mediaArchive, "backvmid2", 0);
			this.backvmid2 = new ImageProducerFrameBuffer(this.getBaseComponent(), local900.spriteWidth, local900.spriteHeight);
			local900.drawOpaque(0, 0);
			@Pc(925) Sprite local925 = new Sprite(mediaArchive, "backvmid3", 0);
			this.backvmid3 = new ImageProducerFrameBuffer(this.getBaseComponent(), local925.spriteWidth, local925.spriteHeight);
			local925.drawOpaque(0, 0);
			@Pc(950) Sprite local950 = new Sprite(mediaArchive, "backhmid2", 0);
			this.backhmid2 = new ImageProducerFrameBuffer(this.getBaseComponent(), local950.spriteWidth, local950.spriteHeight);
			local950.drawOpaque(0, 0);
			@Pc(975) int local975 = (int) (Math.random() * 21.0D) - 10;
			@Pc(982) int local982 = (int) (Math.random() * 21.0D) - 10;
			@Pc(989) int local989 = (int) (Math.random() * 21.0D) - 10;
			@Pc(996) int local996 = (int) (Math.random() * 41.0D) - 20;
			for (@Pc(998) int local998 = 0; local998 < 50; local998++) {
				if (this.mapfunction[local998] != null) {
					this.mapfunction[local998].translate(local975 + local996, local982 + local996, local989 + local996);
				}
				if (this.mapscene[local998] != null) {
					this.mapscene[local998].translate(local975 + local996, local982 + local996, local989 + local996);
				}
			}
			this.showProgress("Unpacking textures", 80);
			Draw3D.unpackTextures(texturesArchive);
			Draw3D.setBrightness(0.8D);
			Draw3D.initPool(20);
			this.showProgress("Unpacking models", 83);
			Model.unpack(modelsArchive);
			SeqBase.unpack(modelsArchive);
			SeqFrame.unpack(modelsArchive);
			this.showProgress("Unpacking config", 86);
			SeqType.unpack(configArchive);
			LocType.unpack(configArchive);
			FloType.unpack(configArchive);
			ObjType.unpack(configArchive);
			NpcType.unpack(configArchive);
			IdkType.unpack(configArchive);
			SpotAnimType.unpack(configArchive);
			VarType.unpack(configArchive);
			ObjType.isMember = members;
			if (!lowMemory) {
				this.showProgress("Unpacking sounds", 90);
				@Pc(1113) byte[] local1113 = soundsArchive.read("sounds.dat", null);
				@Pc(1119) Buffer local1119 = new Buffer(local1113);
				SoundTrack.unpack(local1119);
			}
			this.showProgress("Unpacking interfaces", 92);
			@Pc(1150) Font[] local1150 = new Font[] { this.plain11, this.plain12, this.bold12, this.quill8 };
			Component.unpack(mediaArchive, local1150, interfaceArchive);
			this.showProgress("Preparing game engine", 97);
			@Pc(1166) int local1166;
			@Pc(1168) int local1168;
			@Pc(1170) int local1170;
			for (@Pc(1162) int local1162 = 0; local1162 < 33; local1162++) {
				local1166 = 999;
				local1168 = 0;
				for (local1170 = 0; local1170 < 35; local1170++) {
					if (this.mapback.pixels[local1170 + local1162 * this.mapback.spriteWidth] == 0) {
						if (local1166 == 999) {
							local1166 = local1170;
						}
					} else if (local1166 != 999) {
						local1168 = local1170;
						break;
					}
				}
				this.compassLeft[local1162] = local1166;
				this.compassLineWidth[local1162] = local1168 - local1166;
			}
			@Pc(1228) int local1228;
			for (local1166 = 9; local1166 < 160; local1166++) {
				local1168 = 999;
				local1170 = 0;
				for (local1228 = 10; local1228 < 168; local1228++) {
					if (this.mapback.pixels[local1228 + local1166 * this.mapback.spriteWidth] == 0 && (local1228 > 34 || local1166 > 34)) {
						if (local1168 == 999) {
							local1168 = local1228;
						}
					} else if (local1168 != 999) {
						local1170 = local1228;
						break;
					}
				}
				this.minimapLeft[local1166 - 9] = local1168 - 21;
				this.minimapLineWidth[local1166 - 9] = local1170 - local1168;
			}
			Draw3D.init3D(96, 479);
			this.chatOffsets = Draw3D.lineOffsets;
			Draw3D.init3D(261, 190);
			this.sidebarOffsets = Draw3D.lineOffsets;
			Draw3D.init3D(334, 512);
			this.viewportOffsets = Draw3D.lineOffsets;
			@Pc(1312) int[] local1312 = new int[9];
			for (local1170 = 0; local1170 < 9; local1170++) {
				local1228 = local1170 * 32 + 128 + 15;
				@Pc(1330) int local1330 = local1228 * 3 + 600;
				@Pc(1334) int local1334 = Draw3D.sin[local1228];
				local1312[local1170] = local1330 * local1334 >> 16;
			}
			MapSquare.init(local1312, 800, 512, 334, 500);
			WordPack.decode(wordencArchive);
		} catch (@Pc(1357) Exception ex) {
			ex.printStackTrace();
			this.errorLoading = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "v", descriptor = "(I)V")
	private void updateInput() {
		if (this.objDragArea == 0) {
			this.options[0] = "Cancel";
			this.optionType[0] = 1252;
			this.optionCount = 1;
			this.handleChatInput(super.mouseX, super.mouseY);
			this.hoveredInterfaceIndex = 0;
			if (super.mouseX > 8 && super.mouseY > 11 && super.mouseX < 520 && super.mouseY < 345) {
				if (this.viewportInterfaceIndex == -1) {
					this.updateViewport();
				} else {
					this.updateInterface(super.mouseY, super.mouseX, 11, Component.instances[this.viewportInterfaceIndex], 8, 0);
				}
			}
			if (this.hoveredInterfaceIndex != this.viewportHoveredInterfaceIndex) {
				this.viewportHoveredInterfaceIndex = this.hoveredInterfaceIndex;
			}
			this.hoveredInterfaceIndex = 0;
			if (super.mouseX > 562 && super.mouseY > 231 && super.mouseX < 752 && super.mouseY < 492) {
				if (this.sidebarInterfaceId != -1) {
					this.updateInterface(super.mouseY, super.mouseX, 231, Component.instances[this.sidebarInterfaceId], 562, 0);
				} else if (this.tabComponentId[this.selectedTab] != -1) {
					this.updateInterface(super.mouseY, super.mouseX, 231, Component.instances[this.tabComponentId[this.selectedTab]], 562, 0);
				}
			}
			if (this.hoveredInterfaceIndex != this.sidebarHoveredInterfaceIndex) {
				this.redrawSidebar = true;
				this.sidebarHoveredInterfaceIndex = this.hoveredInterfaceIndex;
			}
			this.hoveredInterfaceIndex = 0;
			if (super.mouseX > 22 && super.mouseY > 375 && super.mouseX < 431 && super.mouseY < 471) {
				if (this.chatbackComponentId == -1) {
					this.updatePlayerTooltip(super.mouseY - 375, super.mouseX - 22);
				} else {
					this.updateInterface(super.mouseY, super.mouseX, 375, Component.instances[this.chatbackComponentId], 22, 0);
				}
			}
			if (this.chatbackComponentId != -1 && this.hoveredInterfaceIndex != this.chatHoveredInterfaceIndex) {
				this.redrawChatback = true;
				this.chatHoveredInterfaceIndex = this.hoveredInterfaceIndex;
			}
			@Pc(223) boolean local223 = false;
			while (!local223) {
				local223 = true;
				for (@Pc(229) int local229 = 0; local229 < this.optionCount - 1; local229++) {
					if (this.optionType[local229] < 1000 && this.optionType[local229 + 1] > 1000) {
						@Pc(250) String local250 = this.options[local229];
						this.options[local229] = this.options[local229 + 1];
						this.options[local229 + 1] = local250;
						@Pc(272) int local272 = this.optionType[local229];
						this.optionType[local229] = this.optionType[local229 + 1];
						this.optionType[local229 + 1] = local272;
						@Pc(294) int local294 = this.optionParamA[local229];
						this.optionParamA[local229] = this.optionParamA[local229 + 1];
						this.optionParamA[local229 + 1] = local294;
						@Pc(316) int local316 = this.optionParamB[local229];
						this.optionParamB[local229] = this.optionParamB[local229 + 1];
						this.optionParamB[local229 + 1] = local316;
						@Pc(338) int local338 = this.optionParamC[local229];
						this.optionParamC[local229] = this.optionParamC[local229 + 1];
						this.optionParamC[local229 + 1] = local338;
						local223 = false;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(B)V")
	private void clearCaches() {
		LocType.modelCache.clear();
		LocType.modelCacheBuilt.clear();
		NpcType.builtModels.clear();
		ObjType.models.clear();
		ObjType.icons.clear();
		PlayerEntity.models.clear();
		SpotAnimType.models.clear();
	}

	@OriginalMember(owner = "client!client", name = "w", descriptor = "(I)V")
	private void drawViewport3d() {
		@Pc(7) int local7;
		this.drawChat();
		if (this.crossType == 1) {
			this.cross[this.crossCycle / 100].draw(this.crossY - 8 - 11, this.crossX - 8 - 8);
		}
		if (this.crossType == 2) {
			this.cross[this.crossCycle / 100 + 4].draw(this.crossY - 8 - 11, this.crossX - 8 - 8);
		}
		if (this.viewportInterfaceIndex != -1) {
			this.animateInterface(this.viewportInterfaceIndex, this.sceneDelta);
			this.drawInterface(0, 0, Component.instances[this.viewportInterfaceIndex], 0);
		}
		this.drawWildyLevel();
		if (!this.menuVisible) {
			this.updateInput();
			this.drawTooltip();
		} else if (this.mouseArea == 0) {
			this.drawMenu();
		}
		if (this.inMultizone == 1) {
			if (this.wildernessLevel > 0 || this.worldLocationState == 1) {
				this.headicons[1].draw(258, 472);
			} else {
				this.headicons[1].draw(296, 472);
			}
		}
		if (this.wildernessLevel > 0) {
			this.headicons[0].draw(296, 472);
			this.plain12.drawCentered(329, 16776960, "Level: " + this.wildernessLevel, 484);
		}
		if (this.worldLocationState == 1) {
			this.headicons[6].draw(296, 472);
			this.plain12.drawCentered(329, 16776960, "Arena", 484);
		}
		if (this.systemUpdateTimer != 0) {
			local7 = this.systemUpdateTimer / 50;
			@Pc(196) int local196 = local7 / 60;
			local7 %= 60;
			if (local7 < 10) {
				this.plain12.draw(4, 329, 16776960, "System update in: " + local196 + ":0" + local7);
			} else {
				this.plain12.draw(4, 329, 16776960, "System update in: " + local196 + ":" + local7);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "x", descriptor = "(I)V")
	private void updateOrbitCamera() {
		@Pc(8) int local8 = this.self.x + this.cameraAnticheatOffsetX;
		@Pc(15) int local15 = this.self.z + this.cameraAnticheatOffsetZ;
		if (this.cameraOrbitX - local8 < -500 || this.cameraOrbitX - local8 > 500 || this.cameraOrbitZ - local15 < -500 || this.cameraOrbitZ - local15 > 500) {
			this.cameraOrbitX = local8;
			this.cameraOrbitZ = local15;
		}
		if (this.cameraOrbitX != local8) {
			this.cameraOrbitX += (local8 - this.cameraOrbitX) / 16;
		}
		if (this.cameraOrbitZ != local15) {
			this.cameraOrbitZ += (local15 - this.cameraOrbitZ) / 16;
		}
		if (super.keyDown[1] == 1) {
			this.cameraYawModifier += (-this.cameraYawModifier - 24) / 2;
		} else if (super.keyDown[2] == 1) {
			this.cameraYawModifier += (24 - this.cameraYawModifier) / 2;
		} else {
			this.cameraYawModifier /= 2;
		}
		if (super.keyDown[3] == 1) {
			this.cameraPitchModifier += (12 - this.cameraPitchModifier) / 2;
		} else if (super.keyDown[4] == 1) {
			this.cameraPitchModifier += (-this.cameraPitchModifier - 12) / 2;
		} else {
			this.cameraPitchModifier /= 2;
		}
		this.cameraYaw = this.cameraYaw + this.cameraYawModifier / 2 & 0x7FF;
		this.cameraOrbitPitch += this.cameraPitchModifier / 2;
		if (this.cameraOrbitPitch < 128) {
			this.cameraOrbitPitch = 128;
		}
		if (this.cameraOrbitPitch > 383) {
			this.cameraOrbitPitch = 383;
		}
		@Pc(208) int local208 = this.cameraOrbitX >> 7;
		@Pc(213) int local213 = this.cameraOrbitZ >> 7;
		@Pc(223) int local223 = this.getLandY(this.currentPlane, this.cameraOrbitX, this.cameraOrbitZ);
		@Pc(225) int local225 = 0;
		@Pc(241) int local241;
		if (local208 > 3 && local213 > 3 && local208 < 100 && local213 < 100) {
			for (local241 = local208 - 4; local241 <= local208 + 4; local241++) {
				for (@Pc(247) int local247 = local213 - 4; local247 <= local213 + 4; local247++) {
					@Pc(252) int local252 = this.currentPlane;
					if (local252 < 3 && (this.levelRenderFlags[1][local241][local247] & 0x2) == 2) {
						local252++;
					}
					@Pc(279) int local279 = local223 - this.levelHeightMaps[local252][local241][local247];
					if (local279 > local225) {
						local225 = local279;
					}
				}
			}
		}
		local241 = local225 * 192;
		if (local241 > 98048) {
			local241 = 98048;
		}
		if (local241 < 32768) {
			local241 = 32768;
		}
		if (local241 > this.cameraMaxY) {
			this.cameraMaxY += (local241 - this.cameraMaxY) / 24;
		} else if (local241 < this.cameraMaxY) {
			this.cameraMaxY += (local241 - this.cameraMaxY) / 80;
		}
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(B)V")
	private void updateSceneProjectiles() {
		for (@Pc(12) ProjectileEntity local12 = (ProjectileEntity) this.projectiles.peekPrevious(); local12 != null; local12 = (ProjectileEntity) this.projectiles.getPrevious()) {
			if (local12.level != this.currentPlane || clientClock > local12.lastCycle) {
				local12.unlink();
			} else if (clientClock >= local12.startCycle) {
				if (local12.target > 0) {
					@Pc(42) NpcEntity local42 = this.npcEntities[local12.target - 1];
					if (local42 != null) {
						local12.setTarget(this.getLandY(local12.level, local42.x, local42.z) - local12.offsetY, local42.z, local42.x, clientClock);
					}
				}
				if (local12.target < 0) {
					@Pc(73) int local73 = -local12.target - 1;
					@Pc(80) PlayerEntity local80;
					if (local73 == this.selfPlayerId) {
						local80 = this.self;
					} else {
						local80 = this.playerEntities[local73];
					}
					if (local80 != null) {
						local12.setTarget(this.getLandY(local12.level, local80.x, local80.z) - local12.offsetY, local80.z, local80.x, clientClock);
					}
				}
				local12.update(this.sceneDelta);
				this.mapSquare.add((int) local12.y, 60, local12.yaw, (int) local12.x, -1, false, null, local12, (int) local12.z, this.currentPlane);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(I)V")
	@Override
	protected void refresh() {
		this.redrawTitleBackground = true;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IILclient!hb;I)V")
	private void drawOnMinimap(@OriginalArg(0) int arg0, @OriginalArg(2) Sprite arg2, @OriginalArg(3) int arg3) {
		@Pc(7) int local7 = this.cameraYaw + this.minimapAnticheatAngle & 0x7FF;
		@Pc(15) int local15 = arg3 * arg3 + arg0 * arg0;
		if (local15 <= 6400) {
			@Pc(34) int local34 = Model.sin[local7];
			@Pc(38) int local38 = Model.cos[local7];
			@Pc(47) int local47 = local34 * 256 / (this.minimapZoom + 256);
			@Pc(56) int local56 = local38 * 256 / (this.minimapZoom + 256);
			@Pc(66) int local66 = arg0 * local47 + arg3 * local56 >> 16;
			@Pc(76) int local76 = arg0 * local56 - arg3 * local47 >> 16;
			if (local15 > 2500) {
				arg2.drawMasked(this.mapback, 83 - local76 - arg2.clipHeight / 2, local66 + 94 - arg2.clipWidth / 2);
			} else {
				arg2.draw(83 - local76 - arg2.clipHeight / 2, local66 + 94 - arg2.clipWidth / 2);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIII)I")
	private int mix(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(3) int local3 = 256 - arg1;
		return ((arg0 & 0xFF00FF) * local3 + (arg2 & 0xFF00FF) * arg1 & 0xFF00FF00) + ((arg0 & 0xFF00) * local3 + (arg2 & 0xFF00) * arg1 & 0xFF0000) >> 8;
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(II)Ljava/lang/String;")
	private String getIntString(@OriginalArg(0) int arg0) {
		return arg0 < 999999999 ? String.valueOf(arg0) : "*";
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IZLclient!x;)V")
	private void setDrawPos(@OriginalArg(0) int arg0, @OriginalArg(2) PathingEntity arg2) {
		this.projectToScreen(arg2.z, arg2.x, arg0);
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IIII)V")
	private void projectToScreen(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg3) {
		if (arg1 >= 128 && arg0 >= 128 && arg1 <= 13056 && arg0 <= 13056) {
			@Pc(28) int local28 = this.getLandY(this.currentPlane, arg1, arg0) - arg3;
			@Pc(33) int local33 = arg1 - this.cameraX;
			@Pc(38) int local38 = local28 - this.cameraY;
			@Pc(43) int local43 = arg0 - this.cameraZ;
			@Pc(48) int local48 = Model.sin[this.cameraPitch];
			@Pc(53) int local53 = Model.cos[this.cameraPitch];
			@Pc(58) int local58 = Model.sin[this.cameraOrbitYaw];
			@Pc(63) int local63 = Model.cos[this.cameraOrbitYaw];
			@Pc(73) int local73 = local43 * local58 + local33 * local63 >> 16;
			@Pc(83) int local83 = local43 * local63 - local33 * local58 >> 16;
			local33 = local73;
			local73 = local38 * local53 - local83 * local48 >> 16;
			local43 = local38 * local48 + local83 * local53 >> 16;
			if (local43 >= 50) {
				this.drawX = Draw3D.centerX3D + (local33 << 9) / local43;
				this.drawY = Draw3D.centerY3D + (local73 << 9) / local43;
			} else {
				this.drawX = -1;
				this.drawY = -1;
			}
		} else {
			this.drawX = -1;
			this.drawY = -1;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIZ)Z")
	private boolean interactWithLoc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(7) int local7 = arg3 >> 14 & 0x7FFF;
		@Pc(16) int local16 = this.mapSquare.getInfo(this.currentPlane, arg1, arg2, arg3);
		if (local16 == -1) {
			return false;
		}
		@Pc(25) int local25 = local16 & 0x1F;
		@Pc(31) int local31 = local16 >> 6 & 0x3;
		if (local25 == 10 || local25 == 11 || local25 == 22) {
			@Pc(43) LocType local43 = LocType.get(local7);
			@Pc(51) int local51;
			@Pc(54) int local54;
			if (local31 == 0 || local31 == 2) {
				local51 = local43.sizeX;
				local54 = local43.sizeZ;
			} else {
				local51 = local43.sizeZ;
				local54 = local43.sizeX;
			}
			@Pc(65) int local65 = local43.blocksides;
			if (local31 != 0) {
				local65 = (local65 << local31 & 0xF) + (local65 >> 4 - local31);
			}
			this.tryMove(this.self.pathTileX[0], local51, false, arg1, this.self.pathTileZ[0], 2, local54, arg2, 0, 0, local65);
		} else {
			this.tryMove(this.self.pathTileX[0], 0, false, arg1, this.self.pathTileZ[0], 2, 0, arg2, local31, local25 + 1, 0);
		}
		this.crossX = super.clickX;
		this.crossY = super.clickY;
		this.crossType = 2;
		this.crossCycle = 0;
		this.outBuffer.p1isaac(arg0);
		this.outBuffer.p2(arg1 + this.baseTileX);
		this.outBuffer.p2(arg2 + this.baseTileZ);
		this.outBuffer.p2(local7);
		return true;
	}

	@OriginalMember(owner = "client!client", name = "y", descriptor = "(I)V")
	private void showContextMenu() {
		@Pc(7) int local7 = this.bold12.stringWidth("Choose Option");
		@Pc(20) int local20;
		for (@Pc(9) int local9 = 0; local9 < this.optionCount; local9++) {
			local20 = this.bold12.stringWidth(this.options[local9]);
			if (local20 > local7) {
				local7 = local20;
			}
		}
		local7 += 8;
		local20 = this.optionCount * 15 + 21;
		@Pc(66) int local66;
		@Pc(84) int local84;
		if (super.clickX > 8 && super.clickY > 11 && super.clickX < 520 && super.clickY < 345) {
			local66 = super.clickX - local7 / 2 - 8;
			if (local66 + local7 > 512) {
				local66 = 512 - local7;
			}
			if (local66 < 0) {
				local66 = 0;
			}
			local84 = super.clickY - 11;
			if (local84 + local20 > 334) {
				local84 = 334 - local20;
			}
			if (local84 < 0) {
				local84 = 0;
			}
			this.menuVisible = true;
			this.mouseArea = 0;
			this.menuX = local66;
			this.menuY = local84;
			this.menuWidth = local7;
			this.menuHeight = this.optionCount * 15 + 22;
		}
		if (super.clickX > 562 && super.clickY > 231 && super.clickX < 752 && super.clickY < 492) {
			local66 = super.clickX - local7 / 2 - 562;
			if (local66 < 0) {
				local66 = 0;
			} else if (local66 + local7 > 190) {
				local66 = 190 - local7;
			}
			local84 = super.clickY - 231;
			if (local84 < 0) {
				local84 = 0;
			} else if (local84 + local20 > 261) {
				local84 = 261 - local20;
			}
			this.menuVisible = true;
			this.mouseArea = 1;
			this.menuX = local66;
			this.menuY = local84;
			this.menuWidth = local7;
			this.menuHeight = this.optionCount * 15 + 22;
		}
		if (super.clickX > 22 && super.clickY > 375 && super.clickX < 501 && super.clickY < 471) {
			local66 = super.clickX - local7 / 2 - 22;
			if (local66 < 0) {
				local66 = 0;
			} else if (local66 + local7 > 479) {
				local66 = 479 - local7;
			}
			local84 = super.clickY - 375;
			if (local84 < 0) {
				local84 = 0;
			} else if (local84 + local20 > 96) {
				local84 = 96 - local20;
			}
			this.menuVisible = true;
			this.mouseArea = 2;
			this.menuX = local66;
			this.menuY = local84;
			this.menuWidth = local7;
			this.menuHeight = this.optionCount * 15 + 22;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;)Ljava/io/DataInputStream;")
	private DataInputStream openStream(@OriginalArg(0) String arg0) throws IOException {
		return SignedLink.mainapp == null ? new DataInputStream((new URL(this.getCodeBase(), arg0)).openStream()) : SignedLink.openurl(arg0);
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(B)V")
	private void prepareTitleScreen() {
		if (this.titleTop == null) {
			super.frameBuffer = null;
			this.areaChatback = null;
			this.areaMapback = null;
			this.areaInvback = null;
			this.areaViewport = null;
			this.areaBackbase1 = null;
			this.areaBackbase2 = null;
			this.areaBackhmid1 = null;
			this.titleLeft = new ImageProducerFrameBuffer(this.getBaseComponent(), 128, 265);
			Draw2D.clear();
			this.titleRight = new ImageProducerFrameBuffer(this.getBaseComponent(), 128, 265);
			Draw2D.clear();
			this.titleTop = new ImageProducerFrameBuffer(this.getBaseComponent(), 533, 186);
			Draw2D.clear();
			this.titleBottom = new ImageProducerFrameBuffer(this.getBaseComponent(), 360, 146);
			Draw2D.clear();
			this.titleCenter = new ImageProducerFrameBuffer(this.getBaseComponent(), 360, 200);
			Draw2D.clear();
			this.titleBottomLeft = new ImageProducerFrameBuffer(this.getBaseComponent(), 214, 267);
			Draw2D.clear();
			this.titleBottomRight = new ImageProducerFrameBuffer(this.getBaseComponent(), 215, 267);
			Draw2D.clear();
			this.titleLeftSpace = new ImageProducerFrameBuffer(this.getBaseComponent(), 86, 79);
			Draw2D.clear();
			this.titleRightSpace = new ImageProducerFrameBuffer(this.getBaseComponent(), 87, 79);
			Draw2D.clear();
			if (this.titleArchive != null) {
				this.loadTitleBackground();
				this.loadTitleForeground();
			}
			this.redrawTitleBackground = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "z", descriptor = "(I)V")
	private void runFlames() {
		this.flamesThreadActive = true;
		try {
			@Pc(4) long local4 = System.currentTimeMillis();
			@Pc(6) int local6 = 0;
			@Pc(8) int local8 = 20;
			while (this.flameActive) {
				this.updateFlames();
				this.updateFlames();
				this.drawFlames();
				local6++;
				if (local6 > 10) {
					@Pc(25) long local25 = System.currentTimeMillis();
					@Pc(34) int local34 = (int) (local25 - local4) / 10 - local8;
					local8 = 40 - local34;
					if (local8 < 5) {
						local8 = 5;
					}
					local6 = 0;
					local4 = local25;
				}
				try {
					Thread.sleep(local8);
				} catch (@Pc(52) Exception local52) {
				}
			}
		} catch (@Pc(58) Exception local58) {
		}
		this.flamesThreadActive = false;
	}

	@OriginalMember(owner = "client!client", name = "run", descriptor = "()V")
	@Override
	public void run() {
		if (this.startFlamesThread) {
			this.runFlames();
		} else if (this.startMidiThread) {
			this.runMidi();
		} else {
			super.run();
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIIZIILclient!hc;)V")
	private void updateInterfaceScrollbar(@OriginalArg(0) int arg0, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) Component arg8) {
		if (this.scrollGripHeld) {
			this.scrollGripInputPadding = 32;
		} else {
			this.scrollGripInputPadding = 0;
		}
		this.scrollGripHeld = false;
		if (arg0 >= arg6 && arg0 < arg6 + 16 && arg2 >= arg7 && arg2 < arg7 + 16) {
			arg8.scrollY -= this.dragCycle * 4;
			if (arg5) {
				this.redrawSidebar = true;
			}
		} else if (arg0 >= arg6 && arg0 < arg6 + 16 && arg2 >= arg7 + arg4 - 16 && arg2 < arg7 + arg4) {
			arg8.scrollY += this.dragCycle * 4;
			if (arg5) {
				this.redrawSidebar = true;
			}
		} else if (arg0 >= arg6 - this.scrollGripInputPadding && arg0 < arg6 + this.scrollGripInputPadding + 16 && arg2 >= arg7 + 16 && arg2 < arg7 + arg4 - 16 && this.dragCycle > 0) {
			@Pc(122) int local122 = (arg4 - 32) * arg4 / arg3;
			if (local122 < 8) {
				local122 = 8;
			}
			@Pc(137) int local137 = arg2 - arg7 - local122 / 2 - 16;
			@Pc(143) int local143 = arg4 - local122 - 32;
			arg8.scrollY = (arg3 - arg4) * local137 / local143;
			if (arg5) {
				this.redrawSidebar = true;
			}
			this.scrollGripHeld = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;Z)V")
	private void login(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(2) boolean arg2) {
		SignedLink.errorname = arg0;
		try {
			if (!arg2) {
				this.loginMessage1 = "";
				this.loginMessage2 = "Connecting to server...";
				this.drawTitleScreen();
			}
			this.stream = new BufferedStream(this, this.opensocket(gamePortOffset + 43594));
			this.stream.read(this.inBuffer.data, 0, 8);
			this.inBuffer.pos = 0;
			this.serverSeed = this.inBuffer.g8();
			@Pc(47) int[] local47 = new int[] { (int) (Math.random() * 9.9999999E7D), (int) (Math.random() * 9.9999999E7D), (int) (this.serverSeed >> 32), (int) this.serverSeed };
			this.outBuffer.pos = 0;
			this.outBuffer.p1(10);
			this.outBuffer.p4(local47[0]);
			this.outBuffer.p4(local47[1]);
			this.outBuffer.p4(local47[2]);
			this.outBuffer.p4(local47[3]);
			this.outBuffer.p4(SignedLink.uid);
			this.outBuffer.pjstr(arg0);
			this.outBuffer.pjstr(arg1);
			this.outBuffer.rsaenc(GlobalConfig.RSA_MODULUS, GlobalConfig.RSA_EXPONENT);
			this.loginBuffer.pos = 0;
			if (arg2) {
				this.loginBuffer.p1(18);
			} else {
				this.loginBuffer.p1(16);
			}
			this.loginBuffer.p1(this.outBuffer.pos + 36 + 1 + 1);
			this.loginBuffer.p1(225);
			this.loginBuffer.p1(lowMemory ? 1 : 0);
			for (@Pc(168) int local168 = 0; local168 < 9; local168++) {
				this.loginBuffer.p4(this.archiveChecksums[local168]);
			}
			this.loginBuffer.pdata(this.outBuffer.data, this.outBuffer.pos, 0);
			this.outBuffer.isaac = new IsaacRandom(local47);
			for (@Pc(202) int local202 = 0; local202 < 4; local202++) {
				local47[local202] += 50;
			}
			this.decryptor = new IsaacRandom(local47);
			this.stream.write(this.loginBuffer.data, this.loginBuffer.pos, 0);
			@Pc(237) int local237 = this.stream.read();
			if (local237 == 1) {
				try {
					Thread.sleep(2000L);
				} catch (@Pc(244) Exception local244) {
				}
				this.login(arg0, arg1, arg2);
				return;
			}
			if (local237 == 2 || local237 == 18) {
				if (local237 == 18) {
					this.modRights = true;
				} else {
					this.modRights = false;
				}
				InputTracking.setDisabled();
				this.ingame = true;
				this.outBuffer.pos = 0;
				this.inBuffer.pos = 0;
				this.packetOpcode = -1;
				this.lastPacketOpcode = -1;
				this.secondMostRecentOpcode = -1;
				this.thirdMostRecentOpcode = -1;
				this.packetLength = 0;
				this.netIdleCycles = 0;
				this.systemUpdateTimer = 0;
				this.idleTimeout = 0;
				this.hintType = 0;
				this.optionCount = 0;
				this.menuVisible = false;
				super.idleCycles = 0;
				for (@Pc(318) int local318 = 0; local318 < 100; local318++) {
					this.chatMessage[local318] = null;
				}
				this.selectedObject = 0;
				this.selectedSpell = 0;
				this.sceneState = 0;
				this.waveCount = 0;
				this.cameraAnticheatOffsetX = (int) (Math.random() * 100.0D) - 50;
				this.cameraAnticheatOffsetZ = (int) (Math.random() * 110.0D) - 55;
				this.cameraAnticheatAngle = (int) (Math.random() * 80.0D) - 40;
				this.minimapAnticheatAngle = (int) (Math.random() * 120.0D) - 60;
				this.minimapZoom = (int) (Math.random() * 30.0D) - 20;
				this.cameraYaw = (int) (Math.random() * 20.0D) - 10 & 0x7FF;
				this.lastSceneLevel = -1;
				this.flagTileX = 0;
				this.flagTileY = 0;
				this.playerCount = 0;
				this.npcCount = 0;
				for (@Pc(408) int local408 = 0; local408 < this.MAX_PLAYER_COUNT; local408++) {
					this.playerEntities[local408] = null;
					this.playerBuffers[local408] = null;
				}
				for (@Pc(427) int local427 = 0; local427 < 8192; local427++) {
					this.npcEntities[local427] = null;
				}
				this.self = this.playerEntities[this.LOCAL_PLAYER_INDEX] = new PlayerEntity();
				this.projectiles.clear();
				this.spotanims.clear();
				this.temporaryLocs.clear();
				@Pc(464) int local464;
				for (@Pc(460) int local460 = 0; local460 < 4; local460++) {
					for (local464 = 0; local464 < 104; local464++) {
						for (@Pc(468) int local468 = 0; local468 < 104; local468++) {
							this.objects[local460][local464][local468] = null;
						}
					}
				}
				this.spawnedLocations = new LinkedList();
				this.friendCount = 0;
				this.stickyChatbackComponentId = -1;
				this.chatbackComponentId = -1;
				this.viewportInterfaceIndex = -1;
				this.sidebarInterfaceId = -1;
				this.chatContinuingDialogue = false;
				this.selectedTab = 3;
				this.checkInputType = false;
				this.menuVisible = false;
				this.showSocialInput = false;
				this.chatbackMessage = null;
				this.inMultizone = 0;
				this.flashingSidebarId = -1;
				this.characterDesignIsMale = true;
				this.resetCharacterDesign();
				for (local464 = 0; local464 < 5; local464++) {
					this.characterDesignColors[local464] = 0;
				}
				opLoc4Counter = 0;
				opNpc3Counter = 0;
				opHeld4Counter = 0;
				opNpc5Counter = 0;
				opHeld1Counter = 0;
				opLoc5Counter = 0;
				ifButton5Counter = 0;
				opPlayer2Counter = 0;
				opHeld9Counter = 0;
				this.prepareGameScreen();
				return;
			}
			if (local237 == 3) {
				this.loginMessage1 = "";
				this.loginMessage2 = "Invalid username or password.";
				return;
			}
			if (local237 == 4) {
				this.loginMessage1 = "Your account has been disabled.";
				this.loginMessage2 = "Please check your message-centre for details.";
				return;
			}
			if (local237 == 5) {
				this.loginMessage1 = "Your account is already logged in.";
				this.loginMessage2 = "Try again in 60 secs...";
				return;
			}
			if (local237 == 6) {
				this.loginMessage1 = "RuneScape has been updated!";
				this.loginMessage2 = "Please reload this page.";
				return;
			}
			if (local237 == 7) {
				this.loginMessage1 = "This world is full.";
				this.loginMessage2 = "Please use a different world.";
				return;
			}
			if (local237 == 8) {
				this.loginMessage1 = "Unable to connect.";
				this.loginMessage2 = "Login server offline.";
				return;
			}
			if (local237 == 9) {
				this.loginMessage1 = "Login limit exceeded.";
				this.loginMessage2 = "Too many connections from your address.";
				return;
			}
			if (local237 == 10) {
				this.loginMessage1 = "Unable to connect.";
				this.loginMessage2 = "Bad session id.";
				return;
			}
			if (local237 == 11) {
				// this.loginMessage1 = "Login server rejected session.";
				this.loginMessage2 = "Please try again.";
				return;
			}
			if (local237 == 12) {
				this.loginMessage1 = "You need a members account to login to this world.";
				this.loginMessage2 = "Please subscribe, or use a different world.";
				return;
			}
			if (local237 == 13) {
				this.loginMessage1 = "Could not complete login.";
				this.loginMessage2 = "Please try using a different world.";
				return;
			}
			if (local237 == 14) {
				this.loginMessage1 = "The server is being updated.";
				this.loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if (local237 == 15) {
				this.ingame = true;
				this.outBuffer.pos = 0;
				this.inBuffer.pos = 0;
				this.packetOpcode = -1;
				this.lastPacketOpcode = -1;
				this.secondMostRecentOpcode = -1;
				this.thirdMostRecentOpcode = -1;
				this.packetLength = 0;
				this.netIdleCycles = 0;
				this.systemUpdateTimer = 0;
				this.optionCount = 0;
				this.menuVisible = false;
				return;
			}
			if (local237 == 16) {
				this.loginMessage1 = "Login attempts exceeded.";
				this.loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if (local237 == 17) {
				this.loginMessage1 = "You are standing in a members-only area.";
				this.loginMessage2 = "To play on this world move to a free area first";
			}
		} catch (@Pc(762) IOException local762) {
			this.loginMessage1 = "";
			this.loginMessage2 = "Error connecting to server.";
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIIIII)V")
	private void addLoc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg7) {
		if (arg1 >= 1 && arg2 >= 1 && arg1 <= 102 && arg2 <= 102) {
			if (lowMemory && arg7 != this.currentPlane) {
				return;
			}
			@Pc(25) int local25 = 0;
			@Pc(27) boolean local27 = true;
			@Pc(29) boolean local29 = false;
			@Pc(31) boolean local31 = false;
			if (arg3 == 0) {
				local25 = this.mapSquare.getWallBitset(arg7, arg1, arg2);
			}
			if (arg3 == 1) {
				local25 = this.mapSquare.getWallDecorationBitset(arg7, arg2, arg1);
			}
			if (arg3 == 2) {
				local25 = this.mapSquare.getLocationBitset(arg7, arg1, arg2);
			}
			if (arg3 == 3) {
				local25 = this.mapSquare.getGroundDecorationBitset(arg7, arg1, arg2);
			}
			@Pc(81) int local81;
			if (local25 != 0) {
				local81 = this.mapSquare.getInfo(arg7, arg1, arg2, local25);
				@Pc(87) int local87 = local25 >> 14 & 0x7FFF;
				@Pc(91) int local91 = local81 & 0x1F;
				@Pc(95) int local95 = local81 >> 6;
				@Pc(107) LocType local107;
				if (arg3 == 0) {
					this.mapSquare.removeWall(arg1, arg7, arg2);
					local107 = LocType.get(local87);
					if (local107.blockwalk) {
						this.collisionMaps[arg7].removeWall(local107.blockrange, local95, arg1, arg2, local91);
					}
				}
				if (arg3 == 1) {
					this.mapSquare.removeWallDecoration(arg7, arg2, arg1);
				}
				if (arg3 == 2) {
					this.mapSquare.removeLocation(arg1, arg2, arg7);
					local107 = LocType.get(local87);
					if (arg1 + local107.sizeX > 103 || arg2 + local107.sizeX > 103 || arg1 + local107.sizeZ > 103 || arg2 + local107.sizeZ > 103) {
						return;
					}
					if (local107.blockwalk) {
						this.collisionMaps[arg7].removeLoc(arg2, arg1, local95, local107.sizeX, local107.blockrange, local107.sizeZ);
					}
				}
				if (arg3 == 3) {
					this.mapSquare.removeGroundDecoration(arg7, arg1, arg2);
					local107 = LocType.get(local87);
					if (local107.blockwalk && local107.interactable) {
						this.collisionMaps[arg7].removeBlock(arg2, arg1);
					}
				}
			}
			if (arg4 >= 0) {
				local81 = arg7;
				if (arg7 < 3 && (this.levelRenderFlags[1][arg1][arg2] & 0x2) == 2) {
					local81 = arg7 + 1;
				}
				Scene.addLoc(arg1, this.locList, this.collisionMaps[arg7], arg2, arg0, this.levelHeightMaps, arg7, arg4, arg5, this.mapSquare, local81);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(JI)V")
	private void addFriend(@OriginalArg(0) long arg0) {
		if (this.friendCount >= 100) {
			this.addMessage(0, "Your friends list is full. Max of 100 hit", "");
		} else {
			@Pc(23) String local23 = StringUtils.formatName(StringUtils.fromBase37(arg0));
			for (@Pc(25) int local25 = 0; local25 < this.friendCount; local25++) {
				if (this.friendName37[local25] == arg0) {
					this.addMessage(0, local23 + " is already on your friend list", "");
					return;
				}
			}
			for (@Pc(55) int local55 = 0; local55 < this.ignoreCount; local55++) {
				if (this.ignoreName37[local55] == arg0) {
					this.addMessage(0, "Please remove " + local23 + " from your ignore list first", "");
					return;
				}
			}
			if (!local23.equals(this.self.name)) {
				this.friendName[this.friendCount] = local23;
				this.friendName37[this.friendCount] = arg0;
				this.friendWorld[this.friendCount] = 0;
				this.friendCount++;
				this.redrawSidebar = true;
				this.outBuffer.p1isaac(118);
				this.outBuffer.p8(arg0);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(B)V")
	@Override
	protected void unload() {
		SignedLink.reporterror = false;
		try {
			if (this.stream != null) {
				this.stream.close();
			}
		} catch (@Pc(11) Exception local11) {
		}
		this.stream = null;
		this.midistop();
		this.midiThreadActive = false;
		this.outBuffer = null;
		this.loginBuffer = null;
		this.inBuffer = null;
		this.sceneMapIndex = null;
		this.sceneMapLandData = null;
		this.sceneMapLocData = null;
		this.levelHeightMaps = null;
		this.levelRenderFlags = null;
		this.mapSquare = null;
		this.collisionMaps = null;
		this.bfsDirection = null;
		this.bfsCost = null;
		this.bfsStepX = null;
		this.bfsStepZ = null;
		this.tmpTexels = null;
		this.areaInvback = null;
		this.areaMapback = null;
		this.areaViewport = null;
		this.areaChatback = null;
		this.areaBackbase1 = null;
		this.areaBackbase2 = null;
		this.areaBackhmid1 = null;
		this.backleft1 = null;
		this.backleft2 = null;
		this.backright1 = null;
		this.backright2 = null;
		this.backtop1 = null;
		this.backtop2 = null;
		this.backvmid1 = null;
		this.backvmid2 = null;
		this.backvmid3 = null;
		this.backhmid2 = null;
		this.invback = null;
		this.mapback = null;
		this.chatback = null;
		this.backbase1 = null;
		this.backbase2 = null;
		this.backhmid1 = null;
		this.sideicons = null;
		this.redstone1 = null;
		this.redstone2 = null;
		this.redstone3 = null;
		this.redstone1h = null;
		this.redstone2h = null;
		this.redstone1v = null;
		this.redstone2v = null;
		this.redstone3v = null;
		this.redstone1vh = null;
		this.redstone2vh = null;
		this.compass = null;
		this.hitmarks = null;
		this.headicons = null;
		this.cross = null;
		this.mapdot0 = null;
		this.mapdot1 = null;
		this.mapdot2 = null;
		this.mapdot3 = null;
		this.mapscene = null;
		this.mapfunction = null;
		this.tileRenderCount = null;
		this.playerEntities = null;
		this.playerIndices = null;
		this.entityUpdateIndices = null;
		this.playerBuffers = null;
		this.deadEntityIndices = null;
		this.npcEntities = null;
		this.npcIndices = null;
		this.objects = null;
		this.spawnedLocations = null;
		this.temporaryLocs = null;
		this.projectiles = null;
		this.spotanims = null;
		this.locList = null;
		this.optionParamA = null;
		this.optionParamB = null;
		this.optionType = null;
		this.optionParamC = null;
		this.options = null;
		this.variables = null;
		this.activeMapFunctionX = null;
		this.activeMapFunctionZ = null;
		this.activeMapFunctions = null;
		this.minimap = null;
		this.friendName = null;
		this.friendName37 = null;
		this.friendWorld = null;
		this.titleLeft = null;
		this.titleRight = null;
		this.titleTop = null;
		this.titleBottom = null;
		this.titleCenter = null;
		this.titleBottomLeft = null;
		this.titleBottomRight = null;
		this.titleLeftSpace = null;
		this.titleRightSpace = null;
		this.disposeTitleComponents();
		LocType.unload();
		NpcType.unload();
		ObjType.unload();
		FloType.instances = null;
		IdkType.instances = null;
		Component.instances = null;
		SeqType.instances = null;
		SpotAnimType.instances = null;
		SpotAnimType.models = null;
		VarType.instances = null;
		super.frameBuffer = null;
		PlayerEntity.models = null;
		Draw3D.unload();
		MapSquare.unload();
		Model.unload();
		SeqBase.instances = null;
		SeqFrame.instances = null;
		System.gc();
	}

	@OriginalMember(owner = "client!client", name = "A", descriptor = "(I)Ljava/net/Socket;")
	private Socket opensocket(@OriginalArg(0) int arg0) throws IOException {
		return SignedLink.mainapp == null ? new Socket(InetAddress.getByName(this.getCodeBase().getHost()), arg0) : SignedLink.opensocket(arg0);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZIILclient!z;I)V")
	private void addPlayerOptions(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) PlayerEntity arg3, @OriginalArg(4) int arg4) {
		if (arg3 != this.self && this.optionCount < 400) {
			@Pc(41) String local41 = arg3.name + getLevelColorTag(this.self.combatLevel, arg3.combatLevel) + " (level-" + arg3.combatLevel + ")";
			if (this.selectedObject == 1) {
				this.options[this.optionCount] = "Use " + this.selectedObjName + " with @whi@" + local41;
				this.optionType[this.optionCount] = 367;
				this.optionParamC[this.optionCount] = arg2;
				this.optionParamA[this.optionCount] = arg4;
				this.optionParamB[this.optionCount] = arg1;
				this.optionCount++;
			} else if (this.selectedSpell != 1) {
				this.options[this.optionCount] = "Follow @whi@" + local41;
				this.optionType[this.optionCount] = 1544;
				this.optionParamC[this.optionCount] = arg2;
				this.optionParamA[this.optionCount] = arg4;
				this.optionParamB[this.optionCount] = arg1;
				this.optionCount++;
				if (this.tutorialIslandState == 0) {
					this.options[this.optionCount] = "Trade with @whi@" + local41;
					this.optionType[this.optionCount] = 1373;
					this.optionParamC[this.optionCount] = arg2;
					this.optionParamA[this.optionCount] = arg4;
					this.optionParamB[this.optionCount] = arg1;
					this.optionCount++;
				}
				if (this.wildernessLevel > 0) {
					this.options[this.optionCount] = "Attack @whi@" + local41;
					if (this.self.combatLevel >= arg3.combatLevel) {
						this.optionType[this.optionCount] = 151;
					} else {
						this.optionType[this.optionCount] = 2151;
					}
					this.optionParamC[this.optionCount] = arg2;
					this.optionParamA[this.optionCount] = arg4;
					this.optionParamB[this.optionCount] = arg1;
					this.optionCount++;
				}
				if (this.worldLocationState == 1) {
					this.options[this.optionCount] = "Fight @whi@" + local41;
					this.optionType[this.optionCount] = 151;
					this.optionParamC[this.optionCount] = arg2;
					this.optionParamA[this.optionCount] = arg4;
					this.optionParamB[this.optionCount] = arg1;
					this.optionCount++;
				}
				if (this.worldLocationState == 2) {
					this.options[this.optionCount] = "Duel-with @whi@" + local41;
					this.optionType[this.optionCount] = 1101;
					this.optionParamC[this.optionCount] = arg2;
					this.optionParamA[this.optionCount] = arg4;
					this.optionParamB[this.optionCount] = arg1;
					this.optionCount++;
				}
			} else if ((this.selectedFlags & 0x8) == 8) {
				this.options[this.optionCount] = this.selectedSpellPrefix + " @whi@" + local41;
				this.optionType[this.optionCount] = 651;
				this.optionParamC[this.optionCount] = arg2;
				this.optionParamA[this.optionCount] = arg4;
				this.optionParamB[this.optionCount] = arg1;
				this.optionCount++;
			}
			for (@Pc(392) int local392 = 0; local392 < this.optionCount; local392++) {
				if (this.optionType[local392] == 660) {
					this.options[local392] = "Walk here @whi@" + local41;
					return;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(Z)V")
	private void updateGame() {
		if (this.systemUpdateTimer > 1) {
			this.systemUpdateTimer--;
		}
		if (this.idleTimeout > 0) {
			this.idleTimeout--;
		}
		for (@Pc(22) int local22 = 0; local22 < 5 && this.readPacket(); local22++) {
		}
		if (this.ingame) {
			@Pc(155) int local155;
			@Pc(207) int local207;
			for (@Pc(46) int local46 = 0; local46 < this.waveCount; local46++) {
				if (this.waveDelay[local46] <= 0) {
					@Pc(55) boolean local55 = false;
					try {
						if (this.waveId[local46] != this.lastWaveId || this.waveLoops[local46] != this.lastWaveLoops) {
							@Pc(89) Buffer local89 = SoundTrack.generate(this.waveLoops[local46], this.waveId[local46]);
							if (System.currentTimeMillis() + (long) (local89.pos / 22) > this.lastWaveStartTime + (long) (this.lastWaveLength / 22)) {
								this.lastWaveLength = local89.pos;
								this.lastWaveStartTime = System.currentTimeMillis();
								if (this.wavesave(local89.data, local89.pos)) {
									this.lastWaveId = this.waveId[local46];
									this.lastWaveLoops = this.waveLoops[local46];
								} else {
									local55 = true;
								}
							}
						} else if (!this.wavereplay()) {
							local55 = true;
						}
					} catch (@Pc(139) Exception local139) {
					}
					if (local55 && this.waveDelay[local46] != -5) {
						this.waveDelay[local46] = -5;
					} else {
						this.waveCount--;
						for (local155 = local46; local155 < this.waveCount; local155++) {
							this.waveId[local155] = this.waveId[local155 + 1];
							this.waveLoops[local155] = this.waveLoops[local155 + 1];
							this.waveDelay[local155] = this.waveDelay[local155 + 1];
						}
						local46--;
					}
				} else {
					local207 = this.waveDelay[local46]--;
				}
			}
			if (this.nextMusicDelay > 0) {
				this.nextMusicDelay -= 20;
				if (this.nextMusicDelay < 0) {
					this.nextMusicDelay = 0;
				}
				if (this.nextMusicDelay == 0 && this.midiActive && !lowMemory) {
					this.setMidi(this.midiCrc, this.currentMidi, this.midiSize);
				}
			}
			@Pc(250) Buffer local250 = InputTracking.flush();
			if (local250 != null) {
				this.outBuffer.p1isaac(81);
				this.outBuffer.p2(local250.pos);
				this.outBuffer.pdata(local250.data, local250.pos, 0);
				local250.release();
			}
			this.netIdleCycles++;
			if (this.netIdleCycles > 750) {
				this.reconnect();
			}
			this.updatePlayers();
			this.updateNpcs();
			this.updateEntityVoices();
			this.updateTemporaryLocs();
			if ((super.keyDown[1] == 1 || super.keyDown[2] == 1 || super.keyDown[3] == 1 || super.keyDown[4] == 1) && this.cameraMovedWrite++ > 5) {
				this.cameraMovedWrite = 0;
				this.outBuffer.p1isaac(189);
				this.outBuffer.p2(this.cameraOrbitPitch);
				this.outBuffer.p2(this.cameraYaw);
				this.outBuffer.p1(this.minimapAnticheatAngle);
				this.outBuffer.p1(this.minimapZoom);
			}
			this.sceneDelta++;
			if (this.crossType != 0) {
				this.crossCycle += 20;
				if (this.crossCycle >= 400) {
					this.crossType = 0;
				}
			}
			if (this.selectedArea != 0) {
				this.selectedCycle++;
				if (this.selectedCycle >= 15) {
					if (this.selectedArea == 2) {
						this.redrawSidebar = true;
					}
					if (this.selectedArea == 3) {
						this.redrawChatback = true;
					}
					this.selectedArea = 0;
				}
			}
			@Pc(508) int local508;
			if (this.objDragArea != 0) {
				this.objDragCycles++;
				if (super.mouseX > this.objGrabX + 5 || super.mouseX < this.objGrabX - 5 || super.mouseY > this.objGrabY + 5 || super.mouseY < this.objGrabY - 5) {
					this.objGrabThreshold = true;
				}
				if (super.dragButton == 0) {
					if (this.objDragArea == 2) {
						this.redrawSidebar = true;
					}
					if (this.objDragArea == 3) {
						this.redrawChatback = true;
					}
					this.objDragArea = 0;
					if (this.objGrabThreshold && this.objDragCycles >= 5) {
						this.hoveredSlotParentId = -1;
						this.updateInput();
						if (this.hoveredSlotParentId == this.objDragComponentId && this.hoveredSlot != this.objDragSlot) {
							@Pc(502) Component local502 = Component.instances[this.objDragComponentId];
							local508 = local502.inventoryIndices[this.hoveredSlot];
							local502.inventoryIndices[this.hoveredSlot] = local502.inventoryIndices[this.objDragSlot];
							local502.inventoryIndices[this.objDragSlot] = local508;
							@Pc(530) int local530 = local502.inventoryAmount[this.hoveredSlot];
							local502.inventoryAmount[this.hoveredSlot] = local502.inventoryAmount[this.objDragSlot];
							local502.inventoryAmount[this.objDragSlot] = local530;
							this.outBuffer.p1isaac(159);
							this.outBuffer.p2(this.objDragComponentId);
							this.outBuffer.p2(this.objDragSlot);
							this.outBuffer.p2(this.hoveredSlot);
						}
					} else if ((this.button == 1 || this.isFriend(this.optionCount - 1)) && this.optionCount > 2) {
						this.showContextMenu();
					} else if (this.optionCount > 0) {
						this.useMenuOption(this.optionCount - 1);
					}
					this.selectedCycle = 10;
					super.mouseButton = 0;
				}
			}
			updateCounter++;
			if (updateCounter > 127) {
				updateCounter = 0;
				this.outBuffer.p1isaac(215);
				this.outBuffer.p3(4991788);
			}
			if (MapSquare.clickedTileX != -1) {
				local155 = MapSquare.clickedTileX;
				local508 = MapSquare.clickedTileZ;
				@Pc(653) boolean local653 = this.tryMove(this.self.pathTileX[0], 0, true, local155, this.self.pathTileZ[0], 0, 0, local508, 0, 0, 0);
				MapSquare.clickedTileX = -1;
				if (local653) {
					this.crossX = super.clickX;
					this.crossY = super.clickY;
					this.crossType = 1;
					this.crossCycle = 0;
				}
			}
			if (super.mouseButton == 1 && this.chatbackMessage != null) {
				this.chatbackMessage = null;
				this.redrawChatback = true;
				super.mouseButton = 0;
			}
			this.updateMiniMenu();
			this.updateMinimapInput();
			this.updateSidebarTabInput();
			this.updateChatSettingInput();
			if (super.dragButton == 1 || super.mouseButton == 1) {
				this.dragCycle++;
			}
			if (this.sceneState == 2) {
				this.updateOrbitCamera();
			}
			if (this.sceneState == 2 && this.cameraOriented) {
				this.calculateCameraPos();
			}
			for (local155 = 0; local155 < 5; local155++) {
				local207 = this.unknownCameraVariable[local155]++;
			}
			this.updateKeyboardInput();
			super.idleCycles++;
			if (super.idleCycles > 4500) {
				this.idleTimeout = 250;
				super.idleCycles -= 500;
				this.outBuffer.p1isaac(70);
			}
			this.cameraOffsetCycle++;
			if (this.cameraOffsetCycle > 500) {
				this.cameraOffsetCycle = 0;
				local508 = (int) (Math.random() * 8.0D);
				if ((local508 & 0x1) == 1) {
					this.cameraAnticheatOffsetX += this.cameraOffsetXModifier;
				}
				if ((local508 & 0x2) == 2) {
					this.cameraAnticheatOffsetZ += this.cameraOffsetZModifier;
				}
				if ((local508 & 0x4) == 4) {
					this.cameraAnticheatAngle += this.cameraOffsetYawModifier;
				}
			}
			if (this.cameraAnticheatOffsetX < -50) {
				this.cameraOffsetXModifier = 2;
			}
			if (this.cameraAnticheatOffsetX > 50) {
				this.cameraOffsetXModifier = -2;
			}
			if (this.cameraAnticheatOffsetZ < -55) {
				this.cameraOffsetZModifier = 2;
			}
			if (this.cameraAnticheatOffsetZ > 55) {
				this.cameraOffsetZModifier = -2;
			}
			if (this.cameraAnticheatAngle < -40) {
				this.cameraOffsetYawModifier = 1;
			}
			if (this.cameraAnticheatAngle > 40) {
				this.cameraOffsetYawModifier = -1;
			}
			this.minimapOffsetCycle++;
			if (this.minimapOffsetCycle > 500) {
				this.minimapOffsetCycle = 0;
				local508 = (int) (Math.random() * 8.0D);
				if ((local508 & 0x1) == 1) {
					this.minimapAnticheatAngle += this.minimapAngleModifier;
				}
				if ((local508 & 0x2) == 2) {
					this.minimapZoom += this.minimapZoomModifier;
				}
			}
			if (this.minimapAnticheatAngle < -60) {
				this.minimapAngleModifier = 2;
			}
			if (this.minimapAnticheatAngle > 60) {
				this.minimapAngleModifier = -2;
			}
			if (this.minimapZoom < -20) {
				this.minimapZoomModifier = 1;
			}
			if (this.minimapZoom > 10) {
				this.minimapZoomModifier = -1;
			}
			update2Counter++;
			if (update2Counter > 110) {
				update2Counter = 0;
				this.outBuffer.p1isaac(236);
				this.outBuffer.p4(0);
			}
			this.keepaliveCounter++;
			if (this.keepaliveCounter > 50) {
				this.outBuffer.p1isaac(108);
			}
			try {
				if (this.stream != null && this.outBuffer.pos > 0) {
					this.stream.write(this.outBuffer.data, this.outBuffer.pos, 0);
					this.outBuffer.pos = 0;
					this.keepaliveCounter = 0;
				}
			} catch (@Pc(1001) IOException local1001) {
				this.reconnect();
			} catch (@Pc(1006) Exception local1006) {
				this.disconnect();
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(Z)V")
	private void drawTooltip() {
		if (this.optionCount >= 2 || this.selectedObject != 0 || this.selectedSpell != 0) {
			@Pc(31) String local31;
			if (this.selectedObject == 1 && this.optionCount < 2) {
				local31 = "Use " + this.selectedObjName + " with...";
			} else if (this.selectedSpell == 1 && this.optionCount < 2) {
				local31 = this.selectedSpellPrefix + "...";
			} else {
				local31 = this.options[this.optionCount - 1];
			}
			if (this.optionCount > 2) {
				local31 = local31 + "@whi@ / " + (this.optionCount - 2) + " more options";
			}
			this.bold12.drawTooltip(clientClock / 1000, true, 15, 16777215, local31, 4);
		}
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(B)V")
	private void updateSceneSpotAnims() {
		for (@Pc(13) SpotAnimEntity local13 = (SpotAnimEntity) this.spotanims.peekPrevious(); local13 != null; local13 = (SpotAnimEntity) this.spotanims.getPrevious()) {
			if (local13.plane != this.currentPlane || local13.finished) {
				local13.unlink();
			} else if (clientClock >= local13.startCycle) {
				local13.update(this.sceneDelta);
				if (local13.finished) {
					local13.unlink();
				} else {
					this.mapSquare.add(local13.z, 60, 0, local13.x, -1, false, null, local13, local13.height, local13.plane);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "getCodeBase", descriptor = "()Ljava/net/URL;")
	@Override
	public URL getCodeBase() {
		if (SignedLink.mainapp != null) {
			return SignedLink.mainapp.getCodeBase();
		}
		try {
			if (super.frame != null) {
				return new URL("http://" + GlobalConfig.SERVER_ADDRESS + ":" + (gamePortOffset + 80));
			}
		} catch (@Pc(21) Exception local21) {
		}
		return super.getCodeBase();
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIZIIIIIIIII)Z")
	private boolean tryMove(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11) {
		@Pc(3) byte local3 = 104;
		@Pc(5) byte local5 = 104;
		@Pc(11) int local11;
		for (@Pc(7) int local7 = 0; local7 < local3; local7++) {
			for (local11 = 0; local11 < local5; local11++) {
				this.bfsDirection[local7][local11] = 0;
				this.bfsCost[local7][local11] = 99999999;
			}
		}
		local11 = arg0;
		@Pc(39) int local39 = arg4;
		this.bfsDirection[arg0][arg4] = 99;
		this.bfsCost[arg0][arg4] = 0;
		@Pc(55) byte local55 = 0;
		@Pc(57) int local57 = 0;
		this.bfsStepX[local55] = arg0;
		@Pc(66) int local66 = local55 + 1;
		this.bfsStepZ[local55] = arg4;
		@Pc(70) boolean local70 = false;
		@Pc(74) int local74 = this.bfsStepX.length;
		@Pc(81) int[][] local81 = this.collisionMaps[this.currentPlane].flags;
		@Pc(193) int local193;
		while (local57 != local66) {
			local11 = this.bfsStepX[local57];
			local39 = this.bfsStepZ[local57];
			local57 = (local57 + 1) % local74;
			if (local11 == arg3 && local39 == arg8) {
				local70 = true;
				break;
			}
			if (arg10 != 0) {
				if ((arg10 < 5 || arg10 == 10) && this.collisionMaps[this.currentPlane].reachedWall(arg9, arg8, arg10 - 1, local39, arg3, local11)) {
					local70 = true;
					break;
				}
				if (arg10 < 10 && this.collisionMaps[this.currentPlane].reachedDecoration(arg9, arg10 - 1, local11, arg3, local39, arg8)) {
					local70 = true;
					break;
				}
			}
			if (arg1 != 0 && arg7 != 0 && this.collisionMaps[this.currentPlane].reachedObject(local39, arg7, local11, arg3, arg11, arg8, arg1)) {
				local70 = true;
				break;
			}
			local193 = this.bfsCost[local11][local39] + 1;
			if (local11 > 0 && this.bfsDirection[local11 - 1][local39] == 0 && (local81[local11 - 1][local39] & 0x280108) == 0) {
				this.bfsStepX[local66] = local11 - 1;
				this.bfsStepZ[local66] = local39;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 - 1][local39] = 2;
				this.bfsCost[local11 - 1][local39] = local193;
			}
			if (local11 < local3 - 1 && this.bfsDirection[local11 + 1][local39] == 0 && (local81[local11 + 1][local39] & 0x280180) == 0) {
				this.bfsStepX[local66] = local11 + 1;
				this.bfsStepZ[local66] = local39;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 + 1][local39] = 8;
				this.bfsCost[local11 + 1][local39] = local193;
			}
			if (local39 > 0 && this.bfsDirection[local11][local39 - 1] == 0 && (local81[local11][local39 - 1] & 0x280102) == 0) {
				this.bfsStepX[local66] = local11;
				this.bfsStepZ[local66] = local39 - 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11][local39 - 1] = 1;
				this.bfsCost[local11][local39 - 1] = local193;
			}
			if (local39 < local5 - 1 && this.bfsDirection[local11][local39 + 1] == 0 && (local81[local11][local39 + 1] & 0x280120) == 0) {
				this.bfsStepX[local66] = local11;
				this.bfsStepZ[local66] = local39 + 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11][local39 + 1] = 4;
				this.bfsCost[local11][local39 + 1] = local193;
			}
			if (local11 > 0 && local39 > 0 && this.bfsDirection[local11 - 1][local39 - 1] == 0 && (local81[local11 - 1][local39 - 1] & 0x28010E) == 0 && (local81[local11 - 1][local39] & 0x280108) == 0 && (local81[local11][local39 - 1] & 0x280102) == 0) {
				this.bfsStepX[local66] = local11 - 1;
				this.bfsStepZ[local66] = local39 - 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 - 1][local39 - 1] = 3;
				this.bfsCost[local11 - 1][local39 - 1] = local193;
			}
			if (local11 < local3 - 1 && local39 > 0 && this.bfsDirection[local11 + 1][local39 - 1] == 0 && (local81[local11 + 1][local39 - 1] & 0x280183) == 0 && (local81[local11 + 1][local39] & 0x280180) == 0 && (local81[local11][local39 - 1] & 0x280102) == 0) {
				this.bfsStepX[local66] = local11 + 1;
				this.bfsStepZ[local66] = local39 - 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 + 1][local39 - 1] = 9;
				this.bfsCost[local11 + 1][local39 - 1] = local193;
			}
			if (local11 > 0 && local39 < local5 - 1 && this.bfsDirection[local11 - 1][local39 + 1] == 0 && (local81[local11 - 1][local39 + 1] & 0x280138) == 0 && (local81[local11 - 1][local39] & 0x280108) == 0 && (local81[local11][local39 + 1] & 0x280120) == 0) {
				this.bfsStepX[local66] = local11 - 1;
				this.bfsStepZ[local66] = local39 + 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 - 1][local39 + 1] = 6;
				this.bfsCost[local11 - 1][local39 + 1] = local193;
			}
			if (local11 < local3 - 1 && local39 < local5 - 1 && this.bfsDirection[local11 + 1][local39 + 1] == 0 && (local81[local11 + 1][local39 + 1] & 0x2801E0) == 0 && (local81[local11 + 1][local39] & 0x280180) == 0 && (local81[local11][local39 + 1] & 0x280120) == 0) {
				this.bfsStepX[local66] = local11 + 1;
				this.bfsStepZ[local66] = local39 + 1;
				local66 = (local66 + 1) % local74;
				this.bfsDirection[local11 + 1][local39 + 1] = 12;
				this.bfsCost[local11 + 1][local39 + 1] = local193;
			}
		}
		this.clickedMinimap = 0;
		@Pc(809) int local809;
		@Pc(815) int local815;
		@Pc(821) int local821;
		if (!local70) {
			if (arg2) {
				local193 = 100;
				for (local809 = 1; local809 < 2; local809++) {
					for (local815 = arg3 - local809; local815 <= arg3 + local809; local815++) {
						for (local821 = arg8 - local809; local821 <= arg8 + local809; local821++) {
							if (local815 >= 0 && local821 >= 0 && local815 < 104 && local821 < 104 && this.bfsCost[local815][local821] < local193) {
								local193 = this.bfsCost[local815][local821];
								local11 = local815;
								local39 = local821;
								this.clickedMinimap = 1;
								local70 = true;
							}
						}
					}
					if (local70) {
						break;
					}
				}
			}
			if (!local70) {
				return false;
			}
		}
		@Pc(882) byte local882 = 0;
		this.bfsStepX[local882] = local11;
		local57 = local882 + 1;
		this.bfsStepZ[local882] = local39;
		local193 = local809 = this.bfsDirection[local11][local39];
		while (local11 != arg0 || local39 != arg4) {
			if (local193 != local809) {
				local809 = local193;
				this.bfsStepX[local57] = local11;
				this.bfsStepZ[local57++] = local39;
			}
			if ((local193 & 0x2) != 0) {
				local11++;
			} else if ((local193 & 0x8) != 0) {
				local11--;
			}
			if ((local193 & 0x1) != 0) {
				local39++;
			} else if ((local193 & 0x4) != 0) {
				local39--;
			}
			local193 = this.bfsDirection[local11][local39];
		}
		if (local57 > 0) {
			local74 = local57;
			if (local57 > 25) {
				local74 = 25;
			}
			local57--;
			local815 = this.bfsStepX[local57];
			local821 = this.bfsStepZ[local57];
			if (arg6 == 0) {
				this.outBuffer.p1isaac(181);
				this.outBuffer.p1(local74 + local74 + 3);
			}
			if (arg6 == 1) {
				this.outBuffer.p1isaac(165);
				this.outBuffer.p1(local74 + local74 + 3 + 14);
			}
			if (arg6 == 2) {
				this.outBuffer.p1isaac(93);
				this.outBuffer.p1(local74 + local74 + 3);
			}
			if (super.keyDown[5] == 1) {
				this.outBuffer.p1(1);
			} else {
				this.outBuffer.p1(0);
			}
			this.outBuffer.p2(local815 + this.baseTileX);
			this.outBuffer.p2(local821 + this.baseTileZ);
			this.flagTileX = this.bfsStepX[0];
			this.flagTileY = this.bfsStepZ[0];
			for (@Pc(1077) int local1077 = 1; local1077 < local74; local1077++) {
				local57--;
				this.outBuffer.p1(this.bfsStepX[local57] - local815);
				this.outBuffer.p1(this.bfsStepZ[local57] - local821);
			}
			return true;
		} else if (arg6 == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!kb;II)V")
	private void readPlayerInfo(@OriginalArg(0) Buffer buf, @OriginalArg(1) int size) {
		this.deadEntityCount = 0;
		this.updateCount = 0;

		this.readLocalPlayer(size, buf);
		this.readPlayers(size, buf);
		this.readNewPlayers(size, buf);
		this.readPlayerUpdates(buf, size);

		@Pc(36) int index;
		for (@Pc(29) int i = 0; i < this.deadEntityCount; i++) {
			index = this.deadEntityIndices[i];
			if (this.playerEntities[index].removeTimer != clientClock) {
				this.playerEntities[index] = null;
			}
		}

		if (buf.pos != size) {
			SignedLink.reporterror("Error packet size mismatch in getplayer pos:" + buf.pos + " psize:" + size);
			throw new RuntimeException("eek");
		}
		for (index = 0; index < this.playerCount; index++) {
			if (this.playerEntities[this.playerIndices[index]] == null) {
				SignedLink.reporterror(this.username + " null entry in pl list - pos:" + index + " size:" + this.playerCount);
				throw new RuntimeException("eek");
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(III)Z")
	private boolean animateInterface(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) boolean local3 = false;
		@Pc(7) Component local7 = Component.instances[arg0];
		for (@Pc(9) int local9 = 0; local9 < local7.children.length && local7.children[local9] != -1; local9++) {
			@Pc(24) Component local24 = Component.instances[local7.children[local9]];
			if (local24.type == 1) {
				local3 |= this.animateInterface(local24.id, arg1);
			}
			if (local24.type == 6 && (local24.anim != -1 || local24.activeanim != -1)) {
				@Pc(54) boolean local54 = this.isInterfaceEnabled(local24);
				@Pc(59) int local59;
				if (local54) {
					local59 = local24.activeanim;
				} else {
					local59 = local24.anim;
				}
				if (local59 != -1) {
					@Pc(71) SeqType local71 = SeqType.instances[local59];
					local24.seqCycle += arg1;
					while (local24.seqCycle > local71.frameDelay[local24.seqFrame]) {
						local24.seqCycle -= local71.frameDelay[local24.seqFrame] + 1;
						local24.seqFrame++;
						if (local24.seqFrame >= local71.framecount) {
							local24.seqFrame -= local71.replayoff;
							if (local24.seqFrame < 0 || local24.seqFrame >= local71.framecount) {
								local24.seqFrame = 0;
							}
						}
						local3 = true;
					}
				}
			}
		}
		return local3;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILjava/lang/String;BLjava/lang/String;)V")
	private void addMessage(@OriginalArg(0) int arg0, @OriginalArg(1) String arg1, @OriginalArg(3) String arg3) {
		if (arg0 == 0 && this.stickyChatbackComponentId != -1) {
			this.chatbackMessage = arg1;
			super.mouseButton = 0;
		}
		if (this.chatbackComponentId == -1) {
			this.redrawChatback = true;
		}
		for (@Pc(20) int local20 = 99; local20 > 0; local20--) {
			this.chatMessageType[local20] = this.chatMessageType[local20 - 1];
			this.chatMessagePrefix[local20] = this.chatMessagePrefix[local20 - 1];
			this.chatMessage[local20] = this.chatMessage[local20 - 1];
		}
		this.chatMessageType[0] = arg0;
		this.chatMessagePrefix[0] = arg3;
		this.chatMessage[0] = arg1;
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(II)V")
	private void resetParentComponentSeq(@OriginalArg(1) int arg1) {
		@Pc(3) Component local3 = Component.instances[arg1];
		for (@Pc(5) int local5 = 0; local5 < local3.children.length && local3.children[local5] != -1; local5++) {
			@Pc(20) Component local20 = Component.instances[local3.children[local5]];
			if (local20.type == 1) {
				this.resetParentComponentSeq(local20.id);
			}
			local20.seqFrame = 0;
			local20.seqCycle = 0;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IJ)V")
	private void removeFriend(@OriginalArg(1) long arg1) {
		@Pc(6) int local6;
		if (arg1 != 0L) {
			for (local6 = 0; local6 < this.friendCount; local6++) {
				if (this.friendName37[local6] == arg1) {
					this.friendCount--;
					this.redrawSidebar = true;
					for (@Pc(38) int local38 = local6; local38 < this.friendCount; local38++) {
						this.friendName[local38] = this.friendName[local38 + 1];
						this.friendWorld[local38] = this.friendWorld[local38 + 1];
						this.friendName37[local38] = this.friendName37[local38 + 1];
					}
					this.outBuffer.p1isaac(11);
					this.outBuffer.p8(arg1);
					return;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!hc;I)Z")
	private boolean isInterfaceEnabled(@OriginalArg(0) Component arg0) {
		if (arg0.scriptCompareType == null) {
			return false;
		}
		for (@Pc(6) int local6 = 0; local6 < arg0.scriptCompareType.length; local6++) {
			@Pc(14) int local14 = this.executeInterface(arg0, local6);
			@Pc(19) int local19 = arg0.scriptCompareValue[local6];
			if (arg0.scriptCompareType[local6] == 2) {
				if (local14 >= local19) {
					return false;
				}
			} else if (arg0.scriptCompareType[local6] == 3) {
				if (local14 <= local19) {
					return false;
				}
			} else if (arg0.scriptCompareType[local6] == 4) {
				if (local14 == local19) {
					return false;
				}
			} else if (local14 != local19) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!client", name = "l", descriptor = "(B)V")
	private void updateMinimapInput() {
		if (super.mouseButton == 1) {
			@Pc(17) int local17 = super.clickX - 21 - 561;
			@Pc(24) int local24 = super.clickY - 9 - 5;
			if (local17 >= 0 && local24 >= 0 && local17 < 146 && local24 < 151) {
				local17 -= 73;
				local24 -= 75;
				@Pc(44) int local44 = this.cameraYaw + this.minimapAnticheatAngle & 0x7FF;
				@Pc(48) int local48 = Draw3D.sin[local44];
				@Pc(52) int local52 = Draw3D.cos[local44];
				@Pc(61) int local61 = local48 * (this.minimapZoom + 256) >> 8;
				@Pc(70) int local70 = local52 * (this.minimapZoom + 256) >> 8;
				@Pc(80) int local80 = local24 * local61 + local17 * local70 >> 11;
				@Pc(90) int local90 = local24 * local70 - local17 * local61 >> 11;
				@Pc(98) int local98 = this.self.x + local80 >> 7;
				@Pc(106) int local106 = this.self.z - local90 >> 7;
				@Pc(129) boolean local129 = this.tryMove(this.self.pathTileX[0], 0, true, local98, this.self.pathTileZ[0], 1, 0, local106, 0, 0, 0);
				if (local129) {
					this.outBuffer.p1(local17);
					this.outBuffer.p1(local24);
					this.outBuffer.p2(this.cameraYaw);
					this.outBuffer.p1(57);
					this.outBuffer.p1(this.minimapAnticheatAngle);
					this.outBuffer.p1(this.minimapZoom);
					this.outBuffer.p1(89);
					this.outBuffer.p2(this.self.x);
					this.outBuffer.p2(this.self.z);
					this.outBuffer.p1(this.clickedMinimap);
					this.outBuffer.p1(63);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "m", descriptor = "(B)V")
	private void updateMiniMenu() {
		if (this.objDragArea == 0) {
			@Pc(14) int local14 = super.mouseButton;
			if (this.selectedSpell == 1 && super.clickX >= 520 && super.clickY >= 165 && super.clickX <= 788 && super.clickY <= 230) {
				local14 = 0;
			}
			@Pc(45) int local45;
			@Pc(48) int local48;
			@Pc(124) int local124;
			if (this.menuVisible) {
				if (local14 != 1) {
					local45 = super.mouseX;
					local48 = super.mouseY;
					if (this.mouseArea == 0) {
						local45 -= 8;
						local48 -= 11;
					}
					if (this.mouseArea == 1) {
						local45 -= 562;
						local48 -= 231;
					}
					if (this.mouseArea == 2) {
						local45 -= 22;
						local48 -= 375;
					}
					if (local45 < this.menuX - 10 || local45 > this.menuX + this.menuWidth + 10 || local48 < this.menuY - 10 || local48 > this.menuY + this.menuHeight + 10) {
						this.menuVisible = false;
						if (this.mouseArea == 1) {
							this.redrawSidebar = true;
						}
						if (this.mouseArea == 2) {
							this.redrawChatback = true;
						}
					}
				}
				if (local14 == 1) {
					local45 = this.menuX;
					local48 = this.menuY;
					local124 = this.menuWidth;
					@Pc(127) int local127 = super.clickX;
					@Pc(130) int local130 = super.clickY;
					if (this.mouseArea == 0) {
						local127 -= 8;
						local130 -= 11;
					}
					if (this.mouseArea == 1) {
						local127 -= 562;
						local130 -= 231;
					}
					if (this.mouseArea == 2) {
						local127 -= 22;
						local130 -= 375;
					}
					@Pc(149) int local149 = -1;
					for (@Pc(151) int local151 = 0; local151 < this.optionCount; local151++) {
						@Pc(166) int local166 = local48 + (this.optionCount - 1 - local151) * 15 + 31;
						if (local127 > local45 && local127 < local45 + local124 && local130 > local166 - 13 && local130 < local166 + 3) {
							local149 = local151;
						}
					}
					if (local149 != -1) {
						this.useMenuOption(local149);
					}
					this.menuVisible = false;
					if (this.mouseArea == 1) {
						this.redrawSidebar = true;
					}
					if (this.mouseArea == 2) {
						this.redrawChatback = true;
					}
				}
			} else {
				if (local14 == 1 && this.optionCount > 0) {
					local45 = this.optionType[this.optionCount - 1];
					if (local45 == 602 || local45 == 596 || local45 == 22 || local45 == 892 || local45 == 415 || local45 == 405 || local45 == 38 || local45 == 422 || local45 == 478 || local45 == 347 || local45 == 188) {
						local48 = this.optionParamA[this.optionCount - 1];
						local124 = this.optionParamB[this.optionCount - 1];
						@Pc(283) Component local283 = Component.instances[local124];
						if (local283.inventoryDummy) {
							this.objGrabThreshold = false;
							this.objDragCycles = 0;
							this.objDragComponentId = local124;
							this.objDragSlot = local48;
							this.objDragArea = 2;
							this.objGrabX = super.clickX;
							this.objGrabY = super.clickY;
							if (Component.instances[local124].parent == this.viewportInterfaceIndex) {
								this.objDragArea = 1;
							}
							if (Component.instances[local124].parent == this.chatbackComponentId) {
								this.objDragArea = 3;
							}
							return;
						}
					}
				}
				if (local14 == 1 && (this.button == 1 || this.isFriend(this.optionCount - 1)) && this.optionCount > 2) {
					local14 = 2;
				}
				if (local14 == 1 && this.optionCount > 0) {
					this.useMenuOption(this.optionCount - 1);
				}
				if (local14 != 2 || this.optionCount <= 0) {
					return;
				}
				this.showContextMenu();
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "n", descriptor = "(B)V")
	private void calculateCameraPos() {
		@Pc(6) int local6 = this.cameraLocalX * 128 + 64;
		@Pc(13) int local13 = this.cameraLocalZ * 128 + 64;
		@Pc(26) int local26 = this.getLandY(this.currentPlane, this.cameraLocalX, this.cameraLocalZ) - this.cameraHeightOffset;
		if (this.cameraX < local6) {
			this.cameraX += this.cameraSpinSpeed + (local6 - this.cameraX) * this.cameraSpinMultiplier / 1000;
			if (this.cameraX > local6) {
				this.cameraX = local6;
			}
		}
		if (this.cameraX > local6) {
			this.cameraX -= this.cameraSpinSpeed + (this.cameraX - local6) * this.cameraSpinMultiplier / 1000;
			if (this.cameraX < local6) {
				this.cameraX = local6;
			}
		}
		if (this.cameraY < local26) {
			this.cameraY += this.cameraSpinSpeed + (local26 - this.cameraY) * this.cameraSpinMultiplier / 1000;
			if (this.cameraY > local26) {
				this.cameraY = local26;
			}
		}
		if (this.cameraY > local26) {
			this.cameraY -= this.cameraSpinSpeed + (this.cameraY - local26) * this.cameraSpinMultiplier / 1000;
			if (this.cameraY < local26) {
				this.cameraY = local26;
			}
		}
		if (this.cameraZ < local13) {
			this.cameraZ += this.cameraSpinSpeed + (local13 - this.cameraZ) * this.cameraSpinMultiplier / 1000;
			if (this.cameraZ > local13) {
				this.cameraZ = local13;
			}
		}
		if (this.cameraZ > local13) {
			this.cameraZ -= this.cameraSpinSpeed + (this.cameraZ - local13) * this.cameraSpinMultiplier / 1000;
			if (this.cameraZ < local13) {
				this.cameraZ = local13;
			}
		}
		local6 = this.cutsceneLocalX * 128 + 64;
		local13 = this.cutsceneLocalY * 128 + 64;
		local26 = this.getLandY(this.currentPlane, this.cutsceneLocalX, this.cutsceneLocalY) - this.cutsceneHeightOffset;
		@Pc(226) int local226 = local6 - this.cameraX;
		@Pc(231) int local231 = local26 - this.cameraY;
		@Pc(236) int local236 = local13 - this.cameraZ;
		@Pc(247) int local247 = (int) Math.sqrt(local226 * local226 + local236 * local236);
		@Pc(258) int local258 = (int) (Math.atan2(local231, local247) * 325.949D) & 0x7FF;
		@Pc(269) int local269 = (int) (Math.atan2(local226, local236) * -325.949D) & 0x7FF;
		if (local258 < 128) {
			local258 = 128;
		}
		if (local258 > 383) {
			local258 = 383;
		}
		if (this.cameraPitch < local258) {
			this.cameraPitch += this.cutsceneSpinSpeed + (local258 - this.cameraPitch) * this.cutsceneSpinMultiplier / 1000;
			if (this.cameraPitch > local258) {
				this.cameraPitch = local258;
			}
		}
		if (this.cameraPitch > local258) {
			this.cameraPitch -= this.cutsceneSpinSpeed + (this.cameraPitch - local258) * this.cutsceneSpinMultiplier / 1000;
			if (this.cameraPitch < local258) {
				this.cameraPitch = local258;
			}
		}
		@Pc(344) int local344 = local269 - this.cameraOrbitYaw;
		if (local344 > 1024) {
			local344 -= 2048;
		}
		if (local344 < -1024) {
			local344 += 2048;
		}
		if (local344 > 0) {
			this.cameraOrbitYaw += this.cutsceneSpinSpeed + local344 * this.cutsceneSpinMultiplier / 1000;
			this.cameraOrbitYaw &= 0x7FF;
		}
		if (local344 < 0) {
			this.cameraOrbitYaw -= this.cutsceneSpinSpeed + -local344 * this.cutsceneSpinMultiplier / 1000;
			this.cameraOrbitYaw &= 0x7FF;
		}
		@Pc(402) int local402 = local269 - this.cameraOrbitYaw;
		if (local402 > 1024) {
			local402 -= 2048;
		}
		if (local402 < -1024) {
			local402 += 2048;
		}
		if (local402 < 0 && local344 > 0 || local402 > 0 && local344 < 0) {
			this.cameraOrbitYaw = local269;
		}
	}

	@OriginalMember(owner = "client!client", name = "C", descriptor = "(I)V")
	private void updateSidebarTabInput() {
		if (super.mouseButton == 1) {
			if (super.clickX >= 549 && super.clickX <= 583 && super.clickY >= 195 && super.clickY < 231 && this.tabComponentId[0] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 0;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 579 && super.clickX <= 609 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[1] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 1;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 607 && super.clickX <= 637 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[2] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 2;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 635 && super.clickX <= 679 && super.clickY >= 194 && super.clickY < 229 && this.tabComponentId[3] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 3;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 676 && super.clickX <= 706 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[4] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 4;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 704 && super.clickX <= 734 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[5] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 5;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 732 && super.clickX <= 766 && super.clickY >= 195 && super.clickY < 231 && this.tabComponentId[6] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 6;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 550 && super.clickX <= 584 && super.clickY >= 492 && super.clickY < 528 && this.tabComponentId[7] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 7;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 582 && super.clickX <= 612 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[8] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 8;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 609 && super.clickX <= 639 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[9] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 9;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 637 && super.clickX <= 681 && super.clickY >= 493 && super.clickY < 528 && this.tabComponentId[10] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 10;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 679 && super.clickX <= 709 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[11] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 11;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 706 && super.clickX <= 736 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[12] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 12;
				this.sidebarRedrawIcons = true;
			}
			if (super.clickX >= 734 && super.clickX <= 768 && super.clickY >= 492 && super.clickY < 528 && this.tabComponentId[13] != -1) {
				this.redrawSidebar = true;
				this.selectedTab = 13;
				this.sidebarRedrawIcons = true;
			}
			sidebarInputCounter++;
			if (sidebarInputCounter > 150) {
				sidebarInputCounter = 0;
				this.outBuffer.p1isaac(233);
				this.outBuffer.p1(43);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!hc;B)Z")
	private boolean updateInterfaceTooltip(@OriginalArg(0) Component arg0) {
		@Pc(2) int local2 = arg0.clientcode;
		if (local2 >= 1 && local2 <= 200) {
			if (local2 >= 101) {
				local2 -= 101;
			} else {
				local2--;
			}
			this.options[this.optionCount] = "Remove @whi@" + this.friendName[local2];
			this.optionType[this.optionCount] = 557;
			this.optionCount++;
			this.options[this.optionCount] = "Message @whi@" + this.friendName[local2];
			this.optionType[this.optionCount] = 679;
			this.optionCount++;
			return true;
		} else if (local2 >= 401 && local2 <= 500) {
			this.options[this.optionCount] = "Remove @whi@" + arg0.text;
			this.optionType[this.optionCount] = 556;
			this.optionCount++;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(ILclient!kb;I)V")
	private void readNpcs(@OriginalArg(1) Buffer arg1, @OriginalArg(2) int arg2) {
		arg1.accessBits();
		@Pc(14) int local14 = arg1.gBit(8);
		@Pc(20) int local20;
		if (local14 < this.npcCount) {
			for (local20 = local14; local20 < this.npcCount; local20++) {
				this.deadEntityIndices[this.deadEntityCount++] = this.npcIndices[local20];
			}
		}
		if (local14 > this.npcCount) {
			SignedLink.reporterror(this.username + " Too many npcs");
			throw new RuntimeException("eek");
		}
		this.npcCount = 0;
		for (local20 = 0; local20 < local14; local20++) {
			@Pc(72) int local72 = this.npcIndices[local20];
			@Pc(77) NpcEntity local77 = this.npcEntities[local72];
			@Pc(82) int local82 = arg1.gBit(1);
			if (local82 == 0) {
				this.npcIndices[this.npcCount++] = local72;
				local77.removeTimer = clientClock;
			} else {
				@Pc(105) int local105 = arg1.gBit(2);
				if (local105 == 0) {
					this.npcIndices[this.npcCount++] = local72;
					local77.removeTimer = clientClock;
					this.entityUpdateIndices[this.updateCount++] = local72;
				} else {
					@Pc(156) int local156;
					@Pc(166) int local166;
					if (local105 == 1) {
						this.npcIndices[this.npcCount++] = local72;
						local77.removeTimer = clientClock;
						local156 = arg1.gBit(3);
						local77.walk(false, local156);
						local166 = arg1.gBit(1);
						if (local166 == 1) {
							this.entityUpdateIndices[this.updateCount++] = local72;
						}
					} else if (local105 == 2) {
						this.npcIndices[this.npcCount++] = local72;
						local77.removeTimer = clientClock;
						local156 = arg1.gBit(3);
						local77.walk(true, local156);
						local166 = arg1.gBit(3);
						local77.walk(true, local166);
						@Pc(224) int local224 = arg1.gBit(1);
						if (local224 == 1) {
							this.entityUpdateIndices[this.updateCount++] = local72;
						}
					} else if (local105 == 3) {
						this.deadEntityIndices[this.deadEntityCount++] = local72;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "getParameter", descriptor = "(Ljava/lang/String;)Ljava/lang/String;")
	@Override
	public String getParameter(@OriginalArg(0) String arg0) {
		return SignedLink.mainapp == null ? super.getParameter(arg0) : SignedLink.mainapp.getParameter(arg0);
	}

	@OriginalMember(owner = "client!client", name = "l", descriptor = "(Z)V")
	private void reconnect() {
		if (this.idleTimeout > 0) {
			this.disconnect();
			return;
		}

		this.areaViewport.makeTarget();
		this.plain12.drawCentered(144, 0, "Connection lost", 257);
		this.plain12.drawCentered(143, 16777215, "Connection lost", 256);
		this.plain12.drawCentered(159, 0, "Please wait - attempting to reestablish", 257);
		this.plain12.drawCentered(158, 16777215, "Please wait - attempting to reestablish", 256);
		this.areaViewport.drawAt(11, super.graphics, 8);
		this.flagTileX = 0;
		@Pc(60) BufferedStream local60 = this.stream;
		this.ingame = false;
		this.login(this.username, this.password, true);
		if (!this.ingame) {
			this.disconnect();
		}
		try {
			local60.close();
		} catch (@Pc(80) Exception local80) {
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!ib;)V")
	private void updateFlameDissolve(@OriginalArg(1) IndexedSprite arg1) {
		@Pc(3) short local3 = 256;
		for (@Pc(5) int local5 = 0; local5 < this.flameBuffer1.length; local5++) {
			this.flameBuffer1[local5] = 0;
		}
		@Pc(30) int local30;
		for (@Pc(20) int local20 = 0; local20 < 5000; local20++) {
			local30 = (int) (Math.random() * 128.0D * (double) local3);
			this.flameBuffer1[local30] = (int) (Math.random() * 256.0D);
		}
		@Pc(48) int local48;
		@Pc(52) int local52;
		@Pc(60) int local60;
		for (local30 = 0; local30 < 20; local30++) {
			for (local48 = 1; local48 < local3 - 1; local48++) {
				for (local52 = 1; local52 < 127; local52++) {
					local60 = local52 + (local48 << 7);
					this.flameBuffer2[local60] = (this.flameBuffer1[local60 - 1] + this.flameBuffer1[local60 + 1] + this.flameBuffer1[local60 - 128] + this.flameBuffer1[local60 + 128]) / 4;
				}
			}
			@Pc(106) int[] local106 = this.flameBuffer1;
			this.flameBuffer1 = this.flameBuffer2;
			this.flameBuffer2 = local106;
		}
		if (arg1 != null) {
			local48 = 0;
			for (local52 = 0; local52 < arg1.spriteHeight; local52++) {
				for (local60 = 0; local60 < arg1.spriteWidth; local60++) {
					if (arg1.pixels[local48++] != 0) {
						@Pc(152) int local152 = local60 + arg1.clipX + 16;
						@Pc(159) int local159 = local52 + arg1.clipY + 16;
						@Pc(165) int local165 = local152 + (local159 << 7);
						this.flameBuffer1[local165] = 0;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(II)V")
	private void updateObjectStack(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(9) LinkedList local9 = this.objects[this.currentPlane][arg0][arg1];
		if (local9 == null) {
			this.mapSquare.removeObject(this.currentPlane, arg0, arg1);
			return;
		}
		@Pc(21) int local21 = -99999999;
		@Pc(23) ObjEntity local23 = null;
		@Pc(27) ObjEntity local27;
		@Pc(35) int local35;
		for (local27 = (ObjEntity) local9.peekPrevious(); local27 != null; local27 = (ObjEntity) local9.getPrevious()) {
			@Pc(32) ObjType local32 = ObjType.get(local27.id);
			local35 = local32.cost;
			if (local32.stackable) {
				local35 *= local27.count + 1;
			}
			if (local35 > local21) {
				local21 = local35;
				local23 = local27;
			}
		}
		local9.pushPrevious(local23);
		@Pc(65) int local65 = -1;
		local35 = -1;
		@Pc(69) int local69 = 0;
		@Pc(71) int local71 = 0;
		for (local27 = (ObjEntity) local9.peekPrevious(); local27 != null; local27 = (ObjEntity) local9.getPrevious()) {
			if (local27.id != local23.id && local65 == -1) {
				local65 = local27.id;
				local69 = local27.count;
			}
			if (local27.id != local23.id && local27.id != local65 && local35 == -1) {
				local35 = local27.id;
				local71 = local27.count;
			}
		}
		@Pc(118) Model local118 = null;
		if (local65 != -1) {
			local118 = ObjType.get(local65).getModel(local69);
		}
		@Pc(128) Model local128 = null;
		if (local35 != -1) {
			local128 = ObjType.get(local35).getModel(local71);
		}
		@Pc(144) int local144 = arg0 + (arg1 << 7) + 0x60000000;
		@Pc(148) ObjType local148 = ObjType.get(local23.id);
		this.mapSquare.addObject(local148.getModel(local23.count), local118, this.getLandY(this.currentPlane, arg0 * 128 + 64, arg1 * 128 + 64), this.currentPlane, local144, arg1, arg0, local128);
	}

	@OriginalMember(owner = "client!client", name = "D", descriptor = "(I)V")
	private void createScene() {
		try {
			this.lastSceneLevel = -1;
			this.temporaryLocs.clear();
			this.locList.clear();
			this.spotanims.clear();
			this.projectiles.clear();
			Draw3D.clearTexels();
			this.clearCaches();
			this.mapSquare.reset();
			for (@Pc(28) int local28 = 0; local28 < 4; local28++) {
				this.collisionMaps[local28].reset();
			}
			System.gc();
			@Pc(53) Scene local53 = new Scene(104, this.levelRenderFlags, 104, this.levelHeightMaps);
			@Pc(56) byte[] local56 = new byte[100000];
			@Pc(60) int local60 = this.sceneMapLandData.length;
			Scene.lowMemory = MapSquare.lowMemory;
			@Pc(73) int local73;
			@Pc(80) int local80;
			for (@Pc(64) int local64 = 0; local64 < local60; local64++) {
				local73 = this.sceneMapIndex[local64] >> 8;
				local80 = this.sceneMapIndex[local64] & 0xFF;
				if (local73 == 33 && local80 >= 71 && local80 <= 73) {
					Scene.lowMemory = false;
				}
			}
			if (Scene.lowMemory) {
				this.mapSquare.setup(this.currentPlane);
			} else {
				this.mapSquare.setup(0);
			}
			this.outBuffer.p1isaac(108);
			@Pc(157) int local157;
			for (local73 = 0; local73 < local60; local73++) {
				local80 = (this.sceneMapIndex[local73] >> 8) * 64 - this.baseTileX;
				@Pc(143) int local143 = (this.sceneMapIndex[local73] & 0xFF) * 64 - this.baseTileZ;
				@Pc(148) byte[] local148 = this.sceneMapLandData[local73];
				if (local148 != null) {
					local157 = (new Buffer(local148)).g4();
					BZip2InputStream.read(local56, local157, local148, local148.length - 4, 4);
					local53.readLandscape(local56, (this.centerSectorX - 6) * 8, local143, local80, (this.centerSectorZ - 6) * 8);
				} else if (this.centerSectorZ < 800) {
					local53.clearLandscape(local80, local143, 64, 64);
				}
			}
			this.outBuffer.p1isaac(108);
			@Pc(225) int local225;
			for (local80 = 0; local80 < local60; local80++) {
				@Pc(216) byte[] local216 = this.sceneMapLocData[local80];
				if (local216 != null) {
					local225 = (new Buffer(local216)).g4();
					BZip2InputStream.read(local56, local225, local216, local216.length - 4, 4);
					local157 = (this.sceneMapIndex[local80] >> 8) * 64 - this.baseTileX;
					@Pc(259) int local259 = (this.sceneMapIndex[local80] & 0xFF) * 64 - this.baseTileZ;
					local53.readLocs(local56, this.mapSquare, this.collisionMaps, this.locList, true, local259, local157);
				}
			}
			this.outBuffer.p1isaac(108);
			local53.buildLandscape(this.mapSquare, this.collisionMaps);
			this.areaViewport.makeTarget();
			this.outBuffer.p1isaac(108);
			for (@Pc(301) LocEntity local301 = (LocEntity) this.locList.peekPrevious(); local301 != null; local301 = (LocEntity) this.locList.getPrevious()) {
				if ((this.levelRenderFlags[1][local301.x][local301.z] & 0x2) == 2) {
					local301.y--;
					if (local301.y < 0) {
						local301.unlink();
					}
				}
			}
			for (local225 = 0; local225 < 104; local225++) {
				for (local157 = 0; local157 < 104; local157++) {
					this.updateObjectStack(local225, local157);
				}
			}
			for (@Pc(361) SpawnedLoc local361 = (SpawnedLoc) this.spawnedLocations.peekPrevious(); local361 != null; local361 = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
				this.addLoc(local361.orientation, local361.x, local361.z, local361.classType, local361.locIndex, local361.type, local361.plane);
			}
		} catch (@Pc(390) Exception local390) {
		}
		LocType.modelCache.clear();
		System.gc();
		Draw3D.initPool(20);
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(I)V")
	@Override
	protected void update() {
		if (!this.errorStarted && !this.errorLoading && !this.errorHost) {
			clientClock++;
			if (this.ingame) {
				this.updateGame();
			} else {
				this.updateTitle();
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "o", descriptor = "(B)V")
	private void updateEntityVoices() {
		@Pc(16) int local16;
		for (@Pc(8) int local8 = -1; local8 < this.playerCount; local8++) {
			if (local8 == -1) {
				local16 = this.LOCAL_PLAYER_INDEX;
			} else {
				local16 = this.playerIndices[local8];
			}
			@Pc(28) PlayerEntity local28 = this.playerEntities[local16];
			if (local28 != null && local28.chatTimer > 0) {
				local28.chatTimer--;
				if (local28.chatTimer == 0) {
					local28.spoken = null;
				}
			}
		}
		for (local16 = 0; local16 < this.npcCount; local16++) {
			@Pc(59) int local59 = this.npcIndices[local16];
			@Pc(64) NpcEntity local64 = this.npcEntities[local59];
			if (local64 != null && local64.chatTimer > 0) {
				local64.chatTimer--;
				if (local64.chatTimer == 0) {
					local64.spoken = null;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!hc;ZI)I")
	private int executeInterface(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg2) {
		if (arg0.script == null || arg2 >= arg0.script.length) {
			return -2;
		}
		try {
			@Pc(26) int[] local26 = arg0.script[arg2];
			@Pc(28) int local28 = 0;
			@Pc(30) int local30 = 0;
			while (true) {
				@Pc(35) int local35 = local26[local30++];
				if (local35 == 0) {
					return local28;
				}
				if (local35 == 1) {
					local28 += this.skillLevelReal[local26[local30++]];
				}
				if (local35 == 2) {
					local28 += this.skillLevel[local26[local30++]];
				}
				if (local35 == 3) {
					local28 += this.skillExperience[local26[local30++]];
				}
				@Pc(88) Component local88;
				@Pc(95) int local95;
				@Pc(97) int local97;
				if (local35 == 4) {
					local88 = Component.instances[local26[local30++]];
					local95 = local26[local30++] + 1;
					for (local97 = 0; local97 < local88.inventoryIndices.length; local97++) {
						if (local88.inventoryIndices[local97] == local95) {
							local28 += local88.inventoryAmount[local97];
						}
					}
				}
				if (local35 == 5) {
					local28 += this.variables[local26[local30++]];
				}
				if (local35 == 6) {
					local28 += EXPERIENCE_TABLE[this.skillLevel[local26[local30++]] - 1];
				}
				if (local35 == 7) {
					local28 += this.variables[local26[local30++]] * 100 / 46875;
				}
				if (local35 == 8) {
					local28 += this.self.combatLevel;
				}
				@Pc(179) int local179;
				if (local35 == 9) {
					for (local179 = 0; local179 < 19; local179++) {
						if (local179 == 18) {
							local179 = 20;
						}
						local28 += this.skillLevel[local179];
					}
				}
				if (local35 == 10) {
					local88 = Component.instances[local26[local30++]];
					local95 = local26[local30++] + 1;
					for (local97 = 0; local97 < local88.inventoryIndices.length; local97++) {
						if (local88.inventoryIndices[local97] == local95) {
							local28 += 999999999;
							break;
						}
					}
				}
				if (local35 == 11) {
					local28 += this.runEnergy;
				}
				if (local35 == 12) {
					local28 += this.runWeight;
				}
				if (local35 == 13) {
					local179 = this.variables[local26[local30++]];
					local95 = local26[local30++];
					local28 += (local179 & 0x1 << local95) == 0 ? 0 : 1;
				}
			}
		} catch (@Pc(282) Exception local282) {
			return -1;
		}
	}

	@OriginalMember(owner = "client!client", name = "m", descriptor = "(Z)V")
	private void drawErrorScreen() {
		@Pc(4) Graphics local4 = this.getBaseComponent().getGraphics();
		local4.setColor(Color.black);
		local4.fillRect(0, 0, 789, 532);
		this.setLoopRate(1);
		@Pc(40) byte local40;
		@Pc(46) int local46;
		if (this.errorLoading) {
			this.flameActive = false;
			local4.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 16));
			local4.setColor(Color.yellow);
			local40 = 35;
			local4.drawString("Sorry, an error has occured whilst loading RuneScape", 30, local40);
			local46 = local40 + 50;
			local4.setColor(Color.white);
			local4.drawString("To fix this try the following (in order):", 30, local46);
			@Pc(55) int local55 = local46 + 50;
			local4.setColor(Color.white);
			local4.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
			local4.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, local55);
			@Pc(72) int local72 = local55 + 30;
			local4.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, local72);
			@Pc(78) int local78 = local72 + 30;
			local4.drawString("3: Try using a different game-world", 30, local78);
			@Pc(84) int local84 = local78 + 30;
			local4.drawString("4: Try rebooting your computer", 30, local84);
			@Pc(90) int local90 = local84 + 30;
			local4.drawString("5: Try selecting a different version of Java from the play-game menu", 30, local90);
		}
		if (this.errorHost) {
			this.flameActive = false;
			local4.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 20));
			local4.setColor(Color.white);
			local4.drawString("Error - unable to load game!", 50, 50);
			local4.drawString("To play RuneScape make sure you play from", 50, 100);
			local4.drawString("http://www.runescape.com", 50, 150);
		}
		if (this.errorStarted) {
			this.flameActive = false;
			local4.setColor(Color.yellow);
			local40 = 35;
			local4.drawString("Error a copy of RuneScape already appears to be loaded", 30, local40);
			local46 = local40 + 50;
			local4.setColor(Color.white);
			local4.drawString("To fix this try the following (in order):", 30, local46);
			local46 += 50;
			local4.setColor(Color.white);
			local4.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
			local4.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, local46);
			local46 += 30;
			local4.drawString("2: Try rebooting your computer, and reloading", 30, local46);
			local46 += 30;
		}
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(B)V")
	private void loadTitleBackground() {
		@Pc(8) byte[] local8 = this.titleArchive.read("title.dat", null);
		@Pc(14) Sprite local14 = new Sprite(local8, this);
		this.titleLeft.makeTarget();
		local14.drawOpaque(0, 0);
		this.titleRight.makeTarget();
		local14.drawOpaque(-661, 0);
		this.titleTop.makeTarget();
		local14.drawOpaque(-128, 0);
		this.titleBottom.makeTarget();
		local14.drawOpaque(-214, -386);
		this.titleCenter.makeTarget();
		local14.drawOpaque(-214, -186);
		this.titleBottomLeft.makeTarget();
		local14.drawOpaque(0, -265);
		this.titleBottomRight.makeTarget();
		local14.drawOpaque(-574, -265);
		this.titleLeftSpace.makeTarget();
		local14.drawOpaque(-128, -186);
		this.titleRightSpace.makeTarget();
		local14.drawOpaque(-574, -186);
		@Pc(110) int[] local110 = new int[local14.spriteWidth];
		for (@Pc(112) int local112 = 0; local112 < local14.spriteHeight; local112++) {
			for (@Pc(116) int local116 = 0; local116 < local14.spriteWidth; local116++) {
				local110[local116] = local14.pixels[local14.spriteWidth + local14.spriteWidth * local112 - local116 - 1];
			}
			System.arraycopy(local110, 0, local14.pixels, local14.spriteWidth * local112, local14.spriteWidth);
		}
		this.titleLeft.makeTarget();
		local14.drawOpaque(394, 0);
		this.titleRight.makeTarget();
		local14.drawOpaque(-267, 0);
		this.titleTop.makeTarget();
		local14.drawOpaque(266, 0);
		this.titleBottom.makeTarget();
		local14.drawOpaque(180, -386);
		this.titleCenter.makeTarget();
		local14.drawOpaque(180, -186);
		this.titleBottomLeft.makeTarget();
		local14.drawOpaque(394, -265);
		this.titleBottomRight.makeTarget();
		local14.drawOpaque(-180, -265);
		this.titleLeftSpace.makeTarget();
		local14.drawOpaque(212, -186);
		this.titleRightSpace.makeTarget();
		local14.drawOpaque(-180, -186);
		local14 = new Sprite(this.titleArchive, "logo", 0);
		this.titleTop.makeTarget();
		local14.draw(18, super.gameWidth / 2 - local14.spriteWidth / 2 - 128);
		local14 = null;
		System.gc();
	}

	@OriginalMember(owner = "client!client", name = "E", descriptor = "(I)V")
	private void updateSceneSeqLocs() {
		for (@Pc(10) LocEntity local10 = (LocEntity) this.locList.peekPrevious(); local10 != null; local10 = (LocEntity) this.locList.getPrevious()) {
			@Pc(14) boolean local14 = false;
			local10.seqDelay += this.sceneDelta;
			if (local10.seqFrame == -1) {
				local10.seqFrame = 0;
				local14 = true;
			}
			label70:
			{
				do {
					do {
						if (local10.seqDelay <= local10.seq.frameDelay[local10.seqFrame]) {
							break label70;
						}
						local10.seqDelay -= local10.seq.frameDelay[local10.seqFrame] + 1;
						local10.seqFrame++;
						local14 = true;
					} while (local10.seqFrame < local10.seq.framecount);
					local10.seqFrame -= local10.seq.replayoff;
				} while (local10.seqFrame >= 0 && local10.seqFrame < local10.seq.framecount);
				local10.unlink();
				local14 = false;
			}
			if (local14) {
				@Pc(96) int local96 = local10.y;
				@Pc(99) int local99 = local10.x;
				@Pc(102) int local102 = local10.z;
				@Pc(104) int local104 = 0;
				if (local10.kind == 0) {
					local104 = this.mapSquare.getWallBitset(local96, local99, local102);
				}
				if (local10.kind == 1) {
					local104 = this.mapSquare.getWallDecorationBitset(local96, local102, local99);
				}
				if (local10.kind == 2) {
					local104 = this.mapSquare.getLocationBitset(local96, local99, local102);
				}
				if (local10.kind == 3) {
					local104 = this.mapSquare.getGroundDecorationBitset(local96, local99, local102);
				}
				if (local104 != 0 && (local104 >> 14 & 0x7FFF) == local10.id) {
					@Pc(171) int local171 = this.levelHeightMaps[local96][local99][local102];
					@Pc(182) int local182 = this.levelHeightMaps[local96][local99 + 1][local102];
					@Pc(195) int local195 = this.levelHeightMaps[local96][local99 + 1][local102 + 1];
					@Pc(206) int local206 = this.levelHeightMaps[local96][local99][local102 + 1];
					@Pc(210) LocType local210 = LocType.get(local10.id);
					@Pc(212) int local212 = -1;
					if (local10.seqFrame != -1) {
						local212 = local10.seq.primaryFrames[local10.seqFrame];
					}
					@Pc(235) int local235;
					@Pc(239) int local239;
					@Pc(243) int local243;
					@Pc(258) Model local258;
					if (local10.kind == 2) {
						local235 = this.mapSquare.getInfo(local96, local99, local102, local104);
						local239 = local235 & 0x1F;
						local243 = local235 >> 6;
						if (local239 == 11) {
							local239 = 10;
						}
						local258 = local210.getModel(local239, local243, local171, local182, local195, local206, local212);
						this.mapSquare.setLocModel(local99, local258, local96, local102);
					} else if (local10.kind == 1) {
						@Pc(282) Model local282 = local210.getModel(4, 0, local171, local182, local195, local206, local212);
						this.mapSquare.setWallDecorationModel(local102, local99, local282, local96);
					} else if (local10.kind == 0) {
						local235 = this.mapSquare.getInfo(local96, local99, local102, local104);
						local239 = local235 & 0x1F;
						local243 = local235 >> 6;
						if (local239 == 2) {
							@Pc(320) int local320 = local243 + 1 & 0x3;
							@Pc(332) Model local332 = local210.getModel(2, local243 + 4, local171, local182, local195, local206, local212);
							@Pc(342) Model local342 = local210.getModel(2, local320, local171, local182, local195, local206, local212);
							this.mapSquare.setWallModels(local332, local342, local102, local99, local96);
						} else {
							local258 = local210.getModel(local239, local243, local171, local182, local195, local206, local212);
							this.mapSquare.setWallModel(local258, local102, local99, local96);
						}
					} else if (local10.kind == 3) {
						local235 = this.mapSquare.getInfo(local96, local99, local102, local104);
						local239 = local235 >> 6;
						@Pc(400) Model local400 = local210.getModel(22, local239, local171, local182, local195, local206, local212);
						this.mapSquare.setGroundDecorationModel(local400, local102, local99, local96);
					}
				} else {
					local10.unlink();
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IJ)V")
	private void removeIgnore(@OriginalArg(1) long arg1) {
		if (arg1 != 0L) {
			for (@Pc(14) int local14 = 0; local14 < this.ignoreCount; local14++) {
				if (this.ignoreName37[local14] == arg1) {
					this.ignoreCount--;
					this.redrawSidebar = true;
					for (@Pc(34) int local34 = local14; local34 < this.ignoreCount; local34++) {
						this.ignoreName37[local34] = this.ignoreName37[local34 + 1];
					}
					this.outBuffer.p1isaac(171);
					this.outBuffer.p8(arg1);
					return;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "q", descriptor = "(B)V")
	private void updateViewport() {
		if (this.selectedObject == 0 && this.selectedSpell == 0) {
			this.options[this.optionCount] = "Walk here";
			this.optionType[this.optionCount] = 660;
			this.optionParamA[this.optionCount] = super.mouseX;
			this.optionParamB[this.optionCount] = super.mouseY;
			this.optionCount++;
		}
		@Pc(41) int local41 = -1;
		for (@Pc(52) int local52 = 0; local52 < Model.resourceCount; local52++) {
			@Pc(58) int local58 = Model.pickedBitsets[local52];
			@Pc(62) int local62 = local58 & 0x7F;
			@Pc(68) int local68 = local58 >> 7 & 0x7F;
			@Pc(74) int local74 = local58 >> 29 & 0x3;
			@Pc(80) int local80 = local58 >> 14 & 0x7FFF;
			if (local58 != local41) {
				local41 = local58;
				@Pc(218) int local218;
				if (local74 == 2 && this.mapSquare.getInfo(this.currentPlane, local62, local68, local58) >= 0) {
					@Pc(100) LocType local100 = LocType.get(local80);
					if (this.selectedObject == 1) {
						this.options[this.optionCount] = "Use " + this.selectedObjName + " with @cya@" + local100.name;
						this.optionType[this.optionCount] = 450;
						this.optionParamC[this.optionCount] = local58;
						this.optionParamA[this.optionCount] = local62;
						this.optionParamB[this.optionCount] = local68;
						this.optionCount++;
					} else if (this.selectedSpell != 1) {
						if (local100.ops != null) {
							for (local218 = 4; local218 >= 0; local218--) {
								if (local100.ops[local218] != null) {
									this.options[this.optionCount] = local100.ops[local218] + " @cya@" + local100.name;
									if (local218 == 0) {
										this.optionType[this.optionCount] = 285;
									}
									if (local218 == 1) {
										this.optionType[this.optionCount] = 504;
									}
									if (local218 == 2) {
										this.optionType[this.optionCount] = 364;
									}
									if (local218 == 3) {
										this.optionType[this.optionCount] = 581;
									}
									if (local218 == 4) {
										this.optionType[this.optionCount] = 1501;
									}
									this.optionParamC[this.optionCount] = local58;
									this.optionParamA[this.optionCount] = local62;
									this.optionParamB[this.optionCount] = local68;
									this.optionCount++;
								}
							}
						}
						this.options[this.optionCount] = "Examine @cya@" + local100.name;
						this.optionType[this.optionCount] = 1175;
						this.optionParamC[this.optionCount] = local58;
						this.optionParamA[this.optionCount] = local62;
						this.optionParamB[this.optionCount] = local68;
						this.optionCount++;
					} else if ((this.selectedFlags & 0x4) == 4) {
						this.options[this.optionCount] = this.selectedSpellPrefix + " @cya@" + local100.name;
						this.optionType[this.optionCount] = 55;
						this.optionParamC[this.optionCount] = local58;
						this.optionParamA[this.optionCount] = local62;
						this.optionParamB[this.optionCount] = local68;
						this.optionCount++;
					}
				}
				@Pc(395) NpcEntity local395;
				if (local74 == 1) {
					@Pc(366) NpcEntity local366 = this.npcEntities[local80];
					if (local366.type.size == 1 && (local366.x & 0x7F) == 64 && (local366.z & 0x7F) == 64) {
						for (local218 = 0; local218 < this.npcCount; local218++) {
							local395 = this.npcEntities[this.npcIndices[local218]];
							if (local395 != null && local395 != local366 && local395.type.size == 1 && local395.x == local366.x && local395.z == local366.z) {
								this.drawTooltip(local395.type, local68, local62, this.npcIndices[local218]);
							}
						}
					}
					this.drawTooltip(local366.type, local68, local62, local80);
				}
				if (local74 == 0) {
					@Pc(446) PlayerEntity local446 = this.playerEntities[local80];
					if ((local446.x & 0x7F) == 64 && (local446.z & 0x7F) == 64) {
						for (local218 = 0; local218 < this.npcCount; local218++) {
							local395 = this.npcEntities[this.npcIndices[local218]];
							if (local395 != null && local395.type.size == 1 && local395.x == local446.x && local395.z == local446.z) {
								this.drawTooltip(local395.type, local68, local62, this.npcIndices[local218]);
							}
						}
						for (@Pc(505) int local505 = 0; local505 < this.playerCount; local505++) {
							@Pc(515) PlayerEntity local515 = this.playerEntities[this.playerIndices[local505]];
							if (local515 != null && local515 != local446 && local515.x == local446.x && local515.z == local446.z) {
								this.addPlayerOptions(local68, this.playerIndices[local505], local515, local62);
							}
						}
					}
					this.addPlayerOptions(local68, local80, local446, local62);
				}
				if (local74 == 3) {
					@Pc(565) LinkedList local565 = this.objects[this.currentPlane][local62][local68];
					if (local565 != null) {
						for (@Pc(572) ObjEntity local572 = (ObjEntity) local565.peekNext(); local572 != null; local572 = (ObjEntity) local565.getNext()) {
							@Pc(578) ObjType local578 = ObjType.get(local572.id);
							if (this.selectedObject == 1) {
								this.options[this.optionCount] = "Use " + this.selectedObjName + " with @lre@" + local578.name;
								this.optionType[this.optionCount] = 217;
								this.optionParamC[this.optionCount] = local572.id;
								this.optionParamA[this.optionCount] = local62;
								this.optionParamB[this.optionCount] = local68;
								this.optionCount++;
							} else if (this.selectedSpell != 1) {
								for (@Pc(695) int local695 = 4; local695 >= 0; local695--) {
									if (local578.ops != null && local578.ops[local695] != null) {
										this.options[this.optionCount] = local578.ops[local695] + " @lre@" + local578.name;
										if (local695 == 0) {
											this.optionType[this.optionCount] = 224;
										}
										if (local695 == 1) {
											this.optionType[this.optionCount] = 993;
										}
										if (local695 == 2) {
											this.optionType[this.optionCount] = 99;
										}
										if (local695 == 3) {
											this.optionType[this.optionCount] = 746;
										}
										if (local695 == 4) {
											this.optionType[this.optionCount] = 877;
										}
										this.optionParamC[this.optionCount] = local572.id;
										this.optionParamA[this.optionCount] = local62;
										this.optionParamB[this.optionCount] = local68;
										this.optionCount++;
									} else if (local695 == 2) {
										this.options[this.optionCount] = "Take @lre@" + local578.name;
										this.optionType[this.optionCount] = 99;
										this.optionParamC[this.optionCount] = local572.id;
										this.optionParamA[this.optionCount] = local62;
										this.optionParamB[this.optionCount] = local68;
										this.optionCount++;
									}
								}
								this.options[this.optionCount] = "Examine @lre@" + local578.name;
								this.optionType[this.optionCount] = 1102;
								this.optionParamC[this.optionCount] = local572.id;
								this.optionParamA[this.optionCount] = local62;
								this.optionParamB[this.optionCount] = local68;
								this.optionCount++;
							} else if ((this.selectedFlags & 0x1) == 1) {
								this.options[this.optionCount] = this.selectedSpellPrefix + " @lre@" + local578.name;
								this.optionType[this.optionCount] = 965;
								this.optionParamC[this.optionCount] = local572.id;
								this.optionParamA[this.optionCount] = local62;
								this.optionParamB[this.optionCount] = local68;
								this.optionCount++;
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "n", descriptor = "(Z)V")
	private void updatePlayers() {
		@Pc(17) int local17;
		for (@Pc(9) int local9 = -1; local9 < this.playerCount; local9++) {
			if (local9 == -1) {
				local17 = this.LOCAL_PLAYER_INDEX;
			} else {
				local17 = this.playerIndices[local9];
			}
			@Pc(29) PlayerEntity local29 = this.playerEntities[local17];
			if (local29 != null) {
				this.updateEntity(local29, 1);
			}
		}
		updatePlayersCounter++;
		if (updatePlayersCounter > 1406) {
			updatePlayersCounter = 0;
			this.outBuffer.p1isaac(219);
			this.outBuffer.p1(0);
			local17 = this.outBuffer.pos;
			this.outBuffer.p1(162);
			this.outBuffer.p1(22);
			if ((int) (Math.random() * 2.0D) == 0) {
				this.outBuffer.p1(84);
			}
			this.outBuffer.p2(31824);
			this.outBuffer.p2(13490);
			if ((int) (Math.random() * 2.0D) == 0) {
				this.outBuffer.p1(123);
			}
			if ((int) (Math.random() * 2.0D) == 0) {
				this.outBuffer.p1(134);
			}
			this.outBuffer.p1(100);
			this.outBuffer.p1(94);
			this.outBuffer.p2(35521);
			this.outBuffer.psize1(this.outBuffer.pos - local17);
		}
	}

	@OriginalMember(owner = "client!client", name = "r", descriptor = "(B)V")
	private void drawTileHint() {
		if (this.hintType == 2) {
			this.projectToScreen((this.hintTileZ - this.baseTileZ << 7) + this.hintOffsetZ, (this.hintTileX - this.baseTileX << 7) + this.hintOffsetX, this.hintHeight * 2);
			if (this.drawX > -1 && clientClock % 20 < 10) {
				this.headicons[2].draw(this.drawY - 28, this.drawX - 12);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IILclient!kb;)V")
	private void readLocalPlayer(@OriginalArg(1) int arg1, @OriginalArg(2) Buffer arg2) {
		arg2.accessBits();
		@Pc(7) int local7 = arg2.gBit(1);
		if (local7 != 0) {
			@Pc(21) int local21 = arg2.gBit(2);
			if (local21 == 0) {
				this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
			} else {
				@Pc(44) int local44;
				@Pc(55) int local55;
				if (local21 == 1) {
					local44 = arg2.gBit(3);
					this.self.walk(false, local44);
					local55 = arg2.gBit(1);
					if (local55 == 1) {
						this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
					}
				} else {
					@Pc(101) int local101;
					if (local21 == 2) {
						local44 = arg2.gBit(3);
						this.self.walk(true, local44);
						local55 = arg2.gBit(3);
						this.self.walk(true, local55);
						local101 = arg2.gBit(1);
						if (local101 == 1) {
							this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
						}
					} else if (local21 == 3) {
						this.currentPlane = arg2.gBit(2);
						local44 = arg2.gBit(7);
						local55 = arg2.gBit(7);
						local101 = arg2.gBit(1);
						this.self.move(local101 == 1, local44, local55);
						@Pc(158) int local158 = arg2.gBit(1);
						if (local158 == 1) {
							this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "o", descriptor = "(Z)V")
	private void drawChatback() {
		this.areaChatback.makeTarget();
		Draw3D.lineOffsets = this.chatOffsets;
		this.chatback.draw(0, 0);
		if (this.showSocialInput) {
			this.bold12.drawCentered(40, 0, this.socialMessage, 239);
			this.bold12.drawCentered(60, 128, this.socialInput + "*", 239);
		} else if (this.checkInputType) {
			this.bold12.drawCentered(40, 0, "Enter amount:", 239);
			this.bold12.drawCentered(60, 128, this.chatbackInput + "*", 239);
		} else if (this.chatbackMessage != null) {
			this.bold12.drawCentered(40, 0, this.chatbackMessage, 239);
			this.bold12.drawCentered(60, 128, "Click to continue", 239);
		} else if (this.chatbackComponentId != -1) {
			this.drawInterface(0, 0, Component.instances[this.chatbackComponentId], 0);
		} else if (this.stickyChatbackComponentId == -1) {
			@Pc(135) Font font = this.plain12;
			if (GlobalConfig.CHATBOX_ERA == 1) {
				font = this.quill8;
			}

			@Pc(137) int i = 0;
			Draw2D.setBounds(77, 0, 463, 0);
			for (@Pc(145) int line = 0; line < 100; line++) {
				if (this.chatMessage[line] != null) {
					@Pc(157) int type = this.chatMessageType[line];
					@Pc(166) int local166 = this.chatScrollAmount + 70 - i * 14;
					if (type == 0) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 0, this.chatMessage[line]);
						}
						i++;
					} else if (type == 1) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 16777215, this.chatMessagePrefix[line] + ":");
							font.draw(font.stringWidth(this.chatMessagePrefix[line]) + 12, local166, 255, this.chatMessage[line]);
						}
						i++;
					} else if (type == 2 && (this.chatPublicSetting == 0 || this.chatPublicSetting == 1 && this.isFriend(this.chatMessagePrefix[line]))) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 0, this.chatMessagePrefix[line] + ":");
							font.draw(font.stringWidth(this.chatMessagePrefix[line]) + 12, local166, 255, this.chatMessage[line]);
						}
						i++;
					} else if ((type == 3 || type == 7) && this.splitPrivateChat == 0 && (type == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[line]))) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 0, "From " + this.chatMessagePrefix[line] + ":");
							font.draw(font.stringWidth("From " + this.chatMessagePrefix[line]) + 12, local166, 8388608, this.chatMessage[line]);
						}
						i++;
					} else if (type == 4 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[line]))) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 8388736, this.chatMessagePrefix[line] + " " + this.chatMessage[line]);
						}
						i++;
					} else if (type == 5 && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 8388608, this.chatMessage[line]);
						}
						i++;
					} else if (type == 6 && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 0, "To " + this.chatMessagePrefix[line] + ":");
							font.draw(font.stringWidth("To " + this.chatMessagePrefix[line]) + 12, local166, 8388608, this.chatMessage[line]);
						}
						i++;
					} else if (type == 8 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[line]))) {
						if (local166 > 0 && local166 < 110) {
							font.draw(4, local166, 13350793, this.chatMessagePrefix[line] + " " + this.chatMessage[line]);
						}
						i++;
					}
				}
			}

			Draw2D.resetBounds();
			this.chatScrollY = i * 14 + 7;
			if (this.chatScrollY < 78) {
				this.chatScrollY = 78;
			}

			this.drawScrollbar(463, 0, this.chatScrollY - this.chatScrollAmount - 77, this.chatScrollY, 77);

			if (GlobalConfig.CHATBOX_ERA < 3) {
				// 186-??? draw: (no username)
				font.draw(3, 90, 0, this.input + "*");
			} else {
				// 225 draw: (username, blue chat message)
				font.draw(4, 90, 0, StringUtils.formatName(this.username) + ":");
				font.draw(font.stringWidth(this.username + ": ") + 6, 90, 255, this.input + "*");
			}

			Draw2D.drawHorizontalLine(0, 77, 479, 0);
		} else {
			this.drawInterface(0, 0, Component.instances[this.stickyChatbackComponentId], 0);
		}
		if (this.menuVisible && this.mouseArea == 2) {
			this.drawMenu();
		}
		this.areaChatback.drawAt(375, super.graphics, 22);
		this.areaViewport.makeTarget();
		Draw3D.lineOffsets = this.viewportOffsets;
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(Z)Z")
	private boolean readPacket() {
		if (this.stream == null) {
			return false;
		}
		@Pc(1264) String local1264;
		@Pc(462) int local462;
		try {
			@Pc(15) int local15 = this.stream.available();
			if (local15 == 0) {
				return false;
			}
			if (this.packetOpcode == -1) {
				this.stream.read(this.inBuffer.data, 0, 1);
				this.packetOpcode = this.inBuffer.data[0] & 0xFF;
				if (this.decryptor != null) {
					this.packetOpcode = this.packetOpcode - this.decryptor.nextInt() & 0xFF;
				}
				this.packetLength = ServerProt.PACKET_LENGTHS[this.packetOpcode];
				local15--;
			}
			if (this.packetLength == -1) {
				if (local15 <= 0) {
					return false;
				}
				this.stream.read(this.inBuffer.data, 0, 1);
				this.packetLength = this.inBuffer.data[0] & 0xFF;
				local15--;
			}
			if (this.packetLength == -2) {
				if (local15 <= 1) {
					return false;
				}
				this.stream.read(this.inBuffer.data, 0, 2);
				this.inBuffer.pos = 0;
				this.packetLength = this.inBuffer.g2();
				local15 -= 2;
			}
			if (local15 < this.packetLength) {
				return false;
			}
			this.inBuffer.pos = 0;
			this.stream.read(this.inBuffer.data, 0, this.packetLength);
			this.netIdleCycles = 0;
			this.thirdMostRecentOpcode = this.secondMostRecentOpcode;
			this.secondMostRecentOpcode = this.lastPacketOpcode;
			this.lastPacketOpcode = this.packetOpcode;
			@Pc(159) int local159;
			if (this.packetOpcode == ServerProt.VARP_SMALL) {
				local159 = this.inBuffer.g2();
				@Pc(163) byte local163 = this.inBuffer.g1b();
				this.defaultVariables[local159] = local163;
				if (this.variables[local159] != local163) {
					this.variables[local159] = local163;
					this.updateVarp(local159);
					this.redrawSidebar = true;
					if (this.stickyChatbackComponentId != -1) {
						this.redrawChatback = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			@Pc(211) int local211;
			@Pc(219) int local219;
			@Pc(321) int local321;
			@Pc(354) int local354;
			@Pc(207) long local207;
			if (this.packetOpcode == ServerProt.UPDATE_FRIENDLIST) {
				local207 = this.inBuffer.g8();
				local211 = this.inBuffer.g1();
				@Pc(217) String local217 = StringUtils.formatName(StringUtils.fromBase37(local207));
				for (local219 = 0; local219 < this.friendCount; local219++) {
					if (local207 == this.friendName37[local219]) {
						if (this.friendWorld[local219] != local211) {
							this.friendWorld[local219] = local211;
							this.redrawSidebar = true;
							if (local211 > 0) {
								this.addMessage(5, local217 + " has logged in.", "");
							}
							if (local211 == 0) {
								this.addMessage(5, local217 + " has logged out.", "");
							}
						}
						local217 = null;
						break;
					}
				}
				if (local217 != null && this.friendCount < 100) {
					this.friendName37[this.friendCount] = local207;
					this.friendName[this.friendCount] = local217;
					this.friendWorld[this.friendCount] = local211;
					this.friendCount++;
					this.redrawSidebar = true;
				}
				@Pc(315) boolean local315 = false;
				while (!local315) {
					local315 = true;
					for (local321 = 0; local321 < this.friendCount - 1; local321++) {
						if (this.friendWorld[local321] != nodeId && this.friendWorld[local321 + 1] == nodeId || this.friendWorld[local321] == 0 && this.friendWorld[local321 + 1] != 0) {
							local354 = this.friendWorld[local321];
							this.friendWorld[local321] = this.friendWorld[local321 + 1];
							this.friendWorld[local321 + 1] = local354;
							@Pc(376) String local376 = this.friendName[local321];
							this.friendName[local321] = this.friendName[local321 + 1];
							this.friendName[local321 + 1] = local376;
							@Pc(398) long local398 = this.friendName37[local321];
							this.friendName37[local321] = this.friendName37[local321 + 1];
							this.friendName37[local321 + 1] = local398;
							this.redrawSidebar = true;
							local315 = false;
						}
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_REBOOT_TIMER) {
				this.systemUpdateTimer = this.inBuffer.g2() * 30;
				this.packetOpcode = -1;
				return true;
			}
			@Pc(466) int local466;
			if (this.packetOpcode == ServerProt.DATA_LAND_DONE) {
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g1();
				local211 = -1;
				for (local466 = 0; local466 < this.sceneMapIndex.length; local466++) {
					if (this.sceneMapIndex[local466] == (local159 << 8) + local462) {
						local211 = local466;
					}
				}
				if (local211 != -1) {
					SignedLink.cachesave("m" + local159 + "_" + local462, this.sceneMapLandData[local211]);
					this.sceneState = 1;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.NPC_INFO) {
				this.readNpcInfo(this.inBuffer, this.packetLength);
				this.packetOpcode = -1;
				return true;
			}
			@Pc(650) int local650;
			if (this.packetOpcode == ServerProt.LOAD_AREA) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				if (this.centerSectorX == local159 && this.centerSectorZ == local462 && this.sceneState != 0) {
					this.packetOpcode = -1;
					return true;
				}
				this.centerSectorX = local159;
				this.centerSectorZ = local462;
				this.baseTileX = (this.centerSectorX - 6) * 8;
				this.baseTileZ = (this.centerSectorZ - 6) * 8;
				this.sceneState = 1;
				this.areaViewport.makeTarget();
				this.plain12.drawCentered(151, 0, "Loading - please wait.", 257);
				this.plain12.drawCentered(150, 16777215, "Loading - please wait.", 256);
				this.areaViewport.drawAt(11, super.graphics, 8);
				SignedLink.looprate(5);
				local211 = (this.packetLength - 2) / 10;
				this.sceneMapLandData = new byte[local211][];
				this.sceneMapLocData = new byte[local211][];
				this.sceneMapIndex = new int[local211];
				this.outBuffer.p1isaac(150);
				this.outBuffer.p1(0);
				local466 = 0;
				@Pc(662) int local662;
				for (local219 = 0; local219 < local211; local219++) {
					local650 = this.inBuffer.g1();
					local321 = this.inBuffer.g1();
					local354 = this.inBuffer.g4();
					local662 = this.inBuffer.g4();
					this.sceneMapIndex[local219] = (local650 << 8) + local321;
					@Pc(686) byte[] local686;
					if (local354 != 0) {
						local686 = SignedLink.cacheload("m" + local650 + "_" + local321);
						if (local686 != null) {
							if (Buffer.getcrc(local686) != local354) {
								local686 = null;
							}
						}
						if (local686 == null) {
							this.sceneState = 0;
							this.outBuffer.p1(0);
							this.outBuffer.p1(local650);
							this.outBuffer.p1(local321);
							local466 += 3;
						} else {
							this.sceneMapLandData[local219] = local686;
						}
					}
					if (local662 != 0) {
						local686 = SignedLink.cacheload("l" + local650 + "_" + local321);
						if (local686 != null) {
							if (Buffer.getcrc(local686) != local662) {
								local686 = null;
							}
						}
						if (local686 == null) {
							this.sceneState = 0;
							this.outBuffer.p1(1);
							this.outBuffer.p1(local650);
							this.outBuffer.p1(local321);
							local466 += 3;
						} else {
							this.sceneMapLocData[local219] = local686;
						}
					}
				}
				this.outBuffer.psize1(local466);
				SignedLink.looprate(50);
				this.areaViewport.makeTarget();
				if (this.sceneState == 0) {
					this.plain12.drawCentered(166, 0, "Map area updated since last visit, so load will take longer this time only", 257);
					this.plain12.drawCentered(165, 16777215, "Map area updated since last visit, so load will take longer this time only", 256);
				}
				this.areaViewport.drawAt(11, super.graphics, 8);
				local650 = this.baseTileX - this.mapLastBaseX;
				local321 = this.baseTileZ - this.mapLastBaseZ;
				this.mapLastBaseX = this.baseTileX;
				this.mapLastBaseZ = this.baseTileZ;
				for (local354 = 0; local354 < 8192; local354++) {
					@Pc(856) NpcEntity local856 = this.npcEntities[local354];
					if (local856 != null) {
						for (@Pc(860) int local860 = 0; local860 < 10; local860++) {
							local856.pathTileX[local860] -= local650;
							local856.pathTileZ[local860] -= local321;
						}
						local856.x -= local650 * 128;
						local856.z -= local321 * 128;
					}
				}
				for (local662 = 0; local662 < this.MAX_PLAYER_COUNT; local662++) {
					@Pc(911) PlayerEntity local911 = this.playerEntities[local662];
					if (local911 != null) {
						for (@Pc(915) int local915 = 0; local915 < 10; local915++) {
							local911.pathTileX[local915] -= local650;
							local911.pathTileZ[local915] -= local321;
						}
						local911.x -= local650 * 128;
						local911.z -= local321 * 128;
					}
				}
				@Pc(960) byte local960 = 0;
				@Pc(962) byte local962 = 104;
				@Pc(964) byte local964 = 1;
				if (local650 < 0) {
					local960 = 103;
					local962 = -1;
					local964 = -1;
				}
				@Pc(974) byte local974 = 0;
				@Pc(976) byte local976 = 104;
				@Pc(978) byte local978 = 1;
				if (local321 < 0) {
					local974 = 103;
					local976 = -1;
					local978 = -1;
				}
				for (@Pc(988) int local988 = local960; local988 != local962; local988 += local964) {
					for (@Pc(992) int local992 = local974; local992 != local976; local992 += local978) {
						@Pc(998) int local998 = local988 + local650;
						@Pc(1002) int local1002 = local992 + local321;
						for (@Pc(1004) int local1004 = 0; local1004 < 4; local1004++) {
							if (local998 >= 0 && local1002 >= 0 && local998 < 104 && local1002 < 104) {
								this.objects[local1004][local988][local992] = this.objects[local1004][local998][local1002];
							} else {
								this.objects[local1004][local988][local992] = null;
							}
						}
					}
				}
				for (@Pc(1066) SpawnedLoc local1066 = (SpawnedLoc) this.spawnedLocations.peekPrevious(); local1066 != null; local1066 = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					local1066.x -= local650;
					local1066.z -= local321;
					if (local1066.x < 0 || local1066.z < 0 || local1066.x >= 104 || local1066.z >= 104) {
						local1066.unlink();
					}
				}
				if (this.flagTileX != 0) {
					this.flagTileX -= local650;
					this.flagTileY -= local321;
				}
				this.cameraOriented = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETPLAYERHEAD) {
				local159 = this.inBuffer.g2();
				Component.instances[local159].model = this.self.getHeadModel();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.HINT_ARROW) {
				this.hintType = this.inBuffer.g1();
				if (this.hintType == 1) {
					this.hintNpc = this.inBuffer.g2();
				}
				if (this.hintType >= 2 && this.hintType <= 6) {
					if (this.hintType == 2) {
						this.hintOffsetX = 64;
						this.hintOffsetZ = 64;
					}
					if (this.hintType == 3) {
						this.hintOffsetX = 0;
						this.hintOffsetZ = 64;
					}
					if (this.hintType == 4) {
						this.hintOffsetX = 128;
						this.hintOffsetZ = 64;
					}
					if (this.hintType == 5) {
						this.hintOffsetX = 64;
						this.hintOffsetZ = 0;
					}
					if (this.hintType == 6) {
						this.hintOffsetX = 64;
						this.hintOffsetZ = 128;
					}
					this.hintType = 2;
					this.hintTileX = this.inBuffer.g2();
					this.hintTileZ = this.inBuffer.g2();
					this.hintHeight = this.inBuffer.g1();
				}
				if (this.hintType == 10) {
					this.hintPlayer = this.inBuffer.g2();
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.MIDI_SONG) {
				local1264 = this.inBuffer.gstr();
				local462 = this.inBuffer.g4();
				local211 = this.inBuffer.g4();
				if (!local1264.equals(this.currentMidi) && this.midiActive && !lowMemory) {
					this.setMidi(local462, local1264, local211);
				}
				this.currentMidi = local1264;
				this.midiCrc = local462;
				this.midiSize = local211;
				this.nextMusicDelay = 0;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.LOGOUT) {
				this.disconnect();
				this.packetOpcode = -1;
				return false;
			}
			if (this.packetOpcode == ServerProt.DATA_LOC_DONE) {
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g1();
				local211 = -1;
				for (local466 = 0; local466 < this.sceneMapIndex.length; local466++) {
					if (this.sceneMapIndex[local466] == (local159 << 8) + local462) {
						local211 = local466;
					}
				}
				if (local211 != -1) {
					SignedLink.cachesave("l" + local159 + "_" + local462, this.sceneMapLocData[local211]);
					this.sceneState = 1;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CLEAR_WALKING_QUEUE) {
				this.flagTileX = 0;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_UID192) {
				this.selfPlayerId = this.inBuffer.g2();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ZoneProt.OBJ_COUNT || this.packetOpcode == ZoneProt.LOC_ADD_CHANGE || this.packetOpcode == ZoneProt.OBJ_ADD || this.packetOpcode == ZoneProt.SPOTANIM_SPECIFIC || this.packetOpcode == ZoneProt.MAP_PROJANIM || this.packetOpcode == ZoneProt.OBJ_DEL || this.packetOpcode == ZoneProt.OBJ_REVEAL || this.packetOpcode == ZoneProt.LOC_ANIM || this.packetOpcode == ZoneProt.LOC_DEL || this.packetOpcode == ZoneProt.LOC_ADD) {
				this.readZonePacket(this.inBuffer, this.packetOpcode);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSUB) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.checkInputType) {
					this.checkInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = local159;
				this.sidebarInterfaceId = local462;
				this.redrawSidebar = true;
				this.sidebarRedrawIcons = true;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.VARP_LARGE) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g4();
				this.defaultVariables[local159] = local462;
				if (this.variables[local159] != local462) {
					this.variables[local159] = local462;
					this.updateVarp(local159);
					this.redrawSidebar = true;
					if (this.stickyChatbackComponentId != -1) {
						this.redrawChatback = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETANIM) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				Component.instances[local159].anim = local462;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTAB) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g1();
				if (local159 == 65535) {
					local159 = -1;
				}
				this.tabComponentId[local462] = local159;
				this.redrawSidebar = true;
				this.sidebarRedrawIcons = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.DATA_LOC) {
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g1();
				local211 = this.inBuffer.g2();
				local466 = this.inBuffer.g2();
				local219 = -1;
				for (local650 = 0; local650 < this.sceneMapIndex.length; local650++) {
					if (this.sceneMapIndex[local650] == (local159 << 8) + local462) {
						local219 = local650;
					}
				}
				if (local219 != -1) {
					if (this.sceneMapLocData[local219] == null || this.sceneMapLocData[local219].length != local466) {
						this.sceneMapLocData[local219] = new byte[local466];
					}
					this.inBuffer.gdata(this.packetLength - 6, local211, this.sceneMapLocData[local219]);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.FINISH_TRACKING) {
				@Pc(1701) Buffer local1701 = InputTracking.stop();
				if (local1701 != null) {
					this.outBuffer.p1isaac(81);
					this.outBuffer.p2(local1701.pos);
					this.outBuffer.pdata(local1701.data, local1701.pos, 0);
					local1701.release();
				}
				this.packetOpcode = -1;
				return true;
			}
			@Pc(1745) Component local1745;
			if (this.packetOpcode == ServerProt.UPDATE_INV_FULL) {
				this.redrawSidebar = true;
				local159 = this.inBuffer.g2();
				local1745 = Component.instances[local159];
				local211 = this.inBuffer.g1();
				for (local466 = 0; local466 < local211; local466++) {
					local1745.inventoryIndices[local466] = this.inBuffer.g2();
					local219 = this.inBuffer.g1();
					if (local219 == 255) {
						local219 = this.inBuffer.g4();
					}
					local1745.inventoryAmount[local466] = local219;
				}
				for (local219 = local211; local219 < local1745.inventoryIndices.length; local219++) {
					local1745.inventoryIndices[local219] = 0;
					local1745.inventoryAmount[local219] = 0;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.ENABLE_TRACKING) {
				InputTracking.setEnabled();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_IAMOUNT) {
				this.showSocialInput = false;
				this.checkInputType = true;
				this.chatbackInput = "";
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_INV_CLEAR) {
				local159 = this.inBuffer.g2();
				local1745 = Component.instances[local159];
				for (local211 = 0; local211 < local1745.inventoryIndices.length; local211++) {
					local1745.inventoryIndices[local211] = -1;
					local1745.inventoryIndices[local211] = 0;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.LAST_LOGIN_INFO) {
				this.lastLoginIp = this.inBuffer.g4();
				this.daysSinceLogin = this.inBuffer.g2();
				this.daysSinceRecoveryChange = this.inBuffer.g1();
				this.unreadMessageCount = this.inBuffer.g2();
				if (this.lastLoginIp != 0 && this.viewportInterfaceIndex == -1) {
					SignedLink.dnslookup(StringUtils.fromIPv4(this.lastLoginIp));
					this.closeInterface();
					@Pc(1915) short local1915 = 650;
					if (this.daysSinceRecoveryChange != 201) {
						local1915 = 655;
					}
					this.reportInput = "";
					this.reportAbuseMuteToggle = false;
					for (local462 = 0; local462 < Component.instances.length; local462++) {
						if (Component.instances[local462] != null && Component.instances[local462].clientcode == local1915) {
							this.viewportInterfaceIndex = Component.instances[local462].parent;
							break;
						}
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTAB_FLASH) {
				this.flashingSidebarId = this.inBuffer.g1();
				if (this.flashingSidebarId == this.selectedTab) {
					if (this.flashingSidebarId == 3) {
						this.selectedTab = 1;
					} else {
						this.selectedTab = 3;
					}
					this.redrawSidebar = true;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.MIDI_JINGLE) {
				if (this.midiActive && !lowMemory) {
					local159 = this.inBuffer.g2();
					local462 = this.inBuffer.g4();
					local211 = this.packetLength - 6;
					@Pc(2018) byte[] local2018 = new byte[local462];
					BZip2InputStream.read(local2018, local462, this.inBuffer.data, local211, this.inBuffer.pos);
					this.midisave(local2018, local462, false);
					this.nextMusicDelay = local159;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_MULTIZONE) {
				this.inMultizone = this.inBuffer.g1();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.SYNTH_SOUND) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g1();
				local211 = this.inBuffer.g2();
				if (this.effectsEnabled && !lowMemory && this.waveCount < 50) {
					this.waveId[this.waveCount] = local159;
					this.waveLoops[this.waveCount] = local462;
					this.waveDelay[this.waveCount] = local211 + SoundTrack.delays[local159];
					this.waveCount++;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETNPCHEAD) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				@Pc(2130) NpcType local2130 = NpcType.get(local462);
				Component.instances[local159].model = local2130.getHeadModel();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_ZONE_PARTIAL_FOLLOWS) {
				this.localPosX = this.inBuffer.g1();
				this.localPosZ = this.inBuffer.g1();
				this.packetOpcode = -1;
				return true;
			}
			@Pc(2181) Component local2181;
			if (this.packetOpcode == ServerProt.IF_SETMODEL_COLOUR) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				local211 = this.inBuffer.g2();
				local2181 = Component.instances[local159];
				@Pc(2184) Model local2184 = local2181.model;
				if (local2184 != null) {
					local2184.recolor(local462, local211);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CHAT_FILTER_SETTINGS) {
				this.chatPublicSetting = this.inBuffer.g1();
				this.chatPrivateSetting = this.inBuffer.g1();
				this.chatTradeDuelSetting = this.inBuffer.g1();
				this.redrawChat = true;
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSIDEBAR) {
				local159 = this.inBuffer.g2();
				this.resetParentComponentSeq(local159);
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.checkInputType) {
					this.checkInputType = false;
					this.redrawChatback = true;
				}
				this.sidebarInterfaceId = local159;
				this.redrawSidebar = true;
				this.sidebarRedrawIcons = true;
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENBOTTOM) {
				local159 = this.inBuffer.g2();
				this.resetParentComponentSeq(local159);
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.redrawSidebar = true;
					this.sidebarRedrawIcons = true;
				}
				this.chatbackComponentId = local159;
				this.redrawChatback = true;
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETPOSITION) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2b();
				local211 = this.inBuffer.g2b();
				local2181 = Component.instances[local159];
				local2181.x = local462;
				local2181.y = local211;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_FORCEANGLE) {
				this.cameraOriented = true;
				this.cameraLocalX = this.inBuffer.g1();
				this.cameraLocalZ = this.inBuffer.g1();
				this.cameraHeightOffset = this.inBuffer.g2();
				this.cameraSpinSpeed = this.inBuffer.g1();
				this.cameraSpinMultiplier = this.inBuffer.g1();
				if (this.cameraSpinMultiplier >= 100) {
					this.cameraX = this.cameraLocalX * 128 + 64;
					this.cameraZ = this.cameraLocalZ * 128 + 64;
					this.cameraY = this.getLandY(this.currentPlane, this.cameraLocalX, this.cameraLocalZ) - this.cameraHeightOffset;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_ZONE_FULL_FOLLOWS) {
				this.localPosX = this.inBuffer.g1();
				this.localPosZ = this.inBuffer.g1();
				for (local159 = this.localPosX; local159 < this.localPosX + 8; local159++) {
					for (local462 = this.localPosZ; local462 < this.localPosZ + 8; local462++) {
						if (this.objects[this.currentPlane][local159][local462] != null) {
							this.objects[this.currentPlane][local159][local462] = null;
							this.updateObjectStack(local159, local462);
						}
					}
				}
				for (@Pc(2487) SpawnedLoc local2487 = (SpawnedLoc) this.spawnedLocations.peekPrevious(); local2487 != null; local2487 = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					if (local2487.x >= this.localPosX && local2487.x < this.localPosX + 8 && local2487.z >= this.localPosZ && local2487.z < this.localPosZ + 8 && local2487.plane == this.currentPlane) {
						this.addLoc(local2487.lastOrientation, local2487.x, local2487.z, local2487.classType, local2487.lastLocIndex, local2487.lastType, local2487.plane);
						local2487.unlink();
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.DATA_LAND) {
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g1();
				local211 = this.inBuffer.g2();
				local466 = this.inBuffer.g2();
				local219 = -1;
				for (local650 = 0; local650 < this.sceneMapIndex.length; local650++) {
					if (this.sceneMapIndex[local650] == (local159 << 8) + local462) {
						local219 = local650;
					}
				}
				if (local219 != -1) {
					if (this.sceneMapLandData[local219] == null || this.sceneMapLandData[local219].length != local466) {
						this.sceneMapLandData[local219] = new byte[local466];
					}
					this.inBuffer.gdata(this.packetLength - 6, local211, this.sceneMapLandData[local219]);
				}
				this.packetOpcode = -1;
				return true;
			}
			@Pc(2652) boolean local2652;
			if (this.packetOpcode == ServerProt.MESSAGE_PRIVATE) {
				local207 = this.inBuffer.g8();
				local211 = this.inBuffer.g4();
				local466 = this.inBuffer.g1();
				local2652 = false;
				for (local650 = 0; local650 < 100; local650++) {
					if (this.privateMessageIndex[local650] == local211) {
						local2652 = true;
						break;
					}
				}
				if (local466 <= 1) {
					for (local321 = 0; local321 < this.ignoreCount; local321++) {
						if (this.ignoreName37[local321] == local207) {
							local2652 = true;
							break;
						}
					}
				}
				if (!local2652 && this.tutorialIslandState == 0) {
					try {
						this.privateMessageIndex[this.privateMessageCount] = local211;
						this.privateMessageCount = (this.privateMessageCount + 1) % 100;
						@Pc(2721) String local2721 = TextEncoder.read(this.inBuffer, this.packetLength - 13);
						@Pc(2725) String local2725 = WordPack.getFiltered(local2721);
						if (local466 > 1) {
							this.addMessage(7, local2725, StringUtils.formatName(StringUtils.fromBase37(local207)));
						} else {
							this.addMessage(3, local2725, StringUtils.formatName(StringUtils.fromBase37(local207)));
						}
					} catch (@Pc(2752) Exception local2752) {
						SignedLink.reporterror("cde1");
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.RESET_CLIENT_VARCACHE) {
				for (local159 = 0; local159 < this.variables.length; local159++) {
					if (this.variables[local159] != this.defaultVariables[local159]) {
						this.variables[local159] = this.defaultVariables[local159];
						this.updateVarp(local159);
						this.redrawSidebar = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETMODEL) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				Component.instances[local159].model = new Model(local462);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSTICKY) {
				local159 = this.inBuffer.g2b();
				this.stickyChatbackComponentId = local159;
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_RUNENERGY) {
				if (this.selectedTab == 12) {
					this.redrawSidebar = true;
				}
				this.runEnergy = this.inBuffer.g1();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_MOVETO) {
				this.cameraOriented = true;
				this.cutsceneLocalX = this.inBuffer.g1();
				this.cutsceneLocalY = this.inBuffer.g1();
				this.cutsceneHeightOffset = this.inBuffer.g2();
				this.cutsceneSpinSpeed = this.inBuffer.g1();
				this.cutsceneSpinMultiplier = this.inBuffer.g1();
				if (this.cutsceneSpinMultiplier >= 100) {
					local159 = this.cutsceneLocalX * 128 + 64;
					local462 = this.cutsceneLocalY * 128 + 64;
					local211 = this.getLandY(this.currentPlane, this.cutsceneLocalX, this.cutsceneLocalY) - this.cutsceneHeightOffset;
					local466 = local159 - this.cameraX;
					local219 = local211 - this.cameraY;
					local650 = local462 - this.cameraZ;
					local321 = (int) Math.sqrt(local466 * local466 + local650 * local650);
					this.cameraPitch = (int) (Math.atan2(local219, local321) * 325.949D) & 0x7FF;
					this.cameraOrbitYaw = (int) (Math.atan2(local466, local650) * -325.949D) & 0x7FF;
					if (this.cameraPitch < 128) {
						this.cameraPitch = 128;
					}
					if (this.cameraPitch > 383) {
						this.cameraPitch = 383;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTAB_ACTIVE) {
				this.selectedTab = this.inBuffer.g1();
				this.redrawSidebar = true;
				this.sidebarRedrawIcons = true;
				this.packetOpcode = -1;
				return true;
			}
			@Pc(3040) String local3040;
			if (this.packetOpcode == ServerProt.MESSAGE_GAME) {
				local1264 = this.inBuffer.gstr();
				@Pc(3043) long local3043;
				if (local1264.endsWith(":tradereq:")) {
					local3040 = local1264.substring(0, local1264.indexOf(":"));
					local3043 = StringUtils.toBase37(local3040);
					local2652 = false;
					for (local650 = 0; local650 < this.ignoreCount; local650++) {
						if (this.ignoreName37[local650] == local3043) {
							local2652 = true;
							break;
						}
					}
					if (!local2652 && this.tutorialIslandState == 0) {
						this.addMessage(4, "wishes to trade with you.", local3040);
					}
				} else if (local1264.endsWith(":duelreq:")) {
					local3040 = local1264.substring(0, local1264.indexOf(":"));
					local3043 = StringUtils.toBase37(local3040);
					local2652 = false;
					for (local650 = 0; local650 < this.ignoreCount; local650++) {
						if (this.ignoreName37[local650] == local3043) {
							local2652 = true;
							break;
						}
					}
					if (!local2652 && this.tutorialIslandState == 0) {
						this.addMessage(8, "wishes to duel with you.", local3040);
					}
				} else {
					this.addMessage(0, local1264, "");
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETOBJECT) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				local211 = this.inBuffer.g2();
				@Pc(3157) ObjType local3157 = ObjType.get(local462);
				Component.instances[local159].model = local3157.getModel(50);
				Component.instances[local159].xan = local3157.xan2d;
				Component.instances[local159].yan = local3157.yan2d;
				Component.instances[local159].zoom = local3157.zoom2d * 100 / local211;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENTOP) {
				local159 = this.inBuffer.g2();
				this.resetParentComponentSeq(local159);
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.redrawSidebar = true;
					this.sidebarRedrawIcons = true;
				}
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.checkInputType) {
					this.checkInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = local159;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETCOLOUR) {
				local159 = this.inBuffer.g2();
				local462 = this.inBuffer.g2();
				local211 = local462 >> 10 & 0x1F;
				local466 = local462 >> 5 & 0x1F;
				local219 = local462 & 0x1F;
				Component.instances[local159].colour = (local211 << 19) + (local466 << 11) + (local219 << 3);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.RESET_ANIMS) {
				for (local159 = 0; local159 < this.playerEntities.length; local159++) {
					if (this.playerEntities[local159] != null) {
						this.playerEntities[local159].primarySeq = -1;
					}
				}
				for (local462 = 0; local462 < this.npcEntities.length; local462++) {
					if (this.npcEntities[local462] != null) {
						this.npcEntities[local462].primarySeq = -1;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETHIDE) {
				local159 = this.inBuffer.g2();
				@Pc(3362) boolean local3362 = this.inBuffer.g1() == 1;
				Component.instances[local159].hide = local3362;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_IGNORELIST) {
				this.ignoreCount = this.packetLength / 8;
				for (local159 = 0; local159 < this.ignoreCount; local159++) {
					this.ignoreName37[local159] = this.inBuffer.g8();
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_RESET) {
				this.cameraOriented = false;
				for (local159 = 0; local159 < 5; local159++) {
					this.customCameraActive[local159] = false;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_CLOSESUB) {
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.redrawSidebar = true;
					this.sidebarRedrawIcons = true;
				}
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.checkInputType) {
					this.checkInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTEXT) {
				local159 = this.inBuffer.g2();
				local3040 = this.inBuffer.gstr();
				Component.instances[local159].text = local3040;
				if (Component.instances[local159].parent == this.tabComponentId[this.selectedTab]) {
					this.redrawSidebar = true;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_STAT) {
				this.redrawSidebar = true;
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g4();
				local211 = this.inBuffer.g1();
				this.skillExperience[local159] = local462;
				this.skillLevelReal[local159] = local211;
				this.skillLevel[local159] = 1;
				for (local466 = 0; local466 < 98; local466++) {
					if (local462 >= EXPERIENCE_TABLE[local466]) {
						this.skillLevel[local159] = local466 + 2;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_ZONE_PARTIAL_ENCLOSED) {
				this.localPosX = this.inBuffer.g1();
				this.localPosZ = this.inBuffer.g1();
				while (this.inBuffer.pos < this.packetLength) {
					local159 = this.inBuffer.g1();
					this.readZonePacket(this.inBuffer, local159);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_RUNWEIGHT) {
				if (this.selectedTab == 12) {
					this.redrawSidebar = true;
				}
				this.runWeight = this.inBuffer.g2b();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_SHAKE) {
				local159 = this.inBuffer.g1();
				local462 = this.inBuffer.g1();
				local211 = this.inBuffer.g1();
				local466 = this.inBuffer.g1();
				this.customCameraActive[local159] = true;
				this.cameraJitter[local159] = local462;
				this.cameraAmplitude[local159] = local211;
				this.cameraFrequency[local159] = local466;
				this.unknownCameraVariable[local159] = 0;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_INV_PARTIAL) {
				this.redrawSidebar = true;
				local159 = this.inBuffer.g2();
				local1745 = Component.instances[local159];
				while (this.inBuffer.pos < this.packetLength) {
					local211 = this.inBuffer.g1();
					local466 = this.inBuffer.g2();
					local219 = this.inBuffer.g1();
					if (local219 == 255) {
						local219 = this.inBuffer.g4();
					}
					if (local211 >= 0 && local211 < local1745.inventoryIndices.length) {
						local1745.inventoryIndices[local211] = local466;
						local1745.inventoryAmount[local211] = local219;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.PLAYER_INFO) {
				this.readPlayerInfo(this.inBuffer, this.packetLength);
				if (this.sceneState == 1) {
					this.sceneState = 2;
					Scene.levelBuilt = this.currentPlane;
					this.createScene();
				}
				if (lowMemory && this.sceneState == 2 && Scene.levelBuilt != this.currentPlane) {
					this.areaViewport.makeTarget();
					this.plain12.drawCentered(151, 0, "Loading - please wait.", 257);
					this.plain12.drawCentered(150, 16777215, "Loading - please wait.", 256);
					this.areaViewport.drawAt(11, super.graphics, 8);
					Scene.levelBuilt = this.currentPlane;
					this.createScene();
				}
				if (this.currentPlane != this.lastSceneLevel && this.sceneState == 2) {
					this.lastSceneLevel = this.currentPlane;
					this.createMinimap(this.currentPlane);
				}
				this.packetOpcode = -1;
				return true;
			}
			SignedLink.reporterror("T1 - " + this.packetOpcode + "," + this.packetLength + " - " + this.secondMostRecentOpcode + "," + this.thirdMostRecentOpcode);
			this.disconnect();
		} catch (@Pc(3862) IOException local3862) {
			this.reconnect();
		} catch (@Pc(3867) Exception local3867) {
			local1264 = "T2 - " + this.packetOpcode + "," + this.secondMostRecentOpcode + "," + this.thirdMostRecentOpcode + " - " + this.packetLength + "," + (this.baseTileX + this.self.pathTileX[0]) + "," + (this.baseTileZ + this.self.pathTileZ[0]) + " - ";
			for (local462 = 0; local462 < this.packetLength && local462 < 50; local462++) {
				local1264 = local1264 + this.inBuffer.data[local462] + ",";
			}
			SignedLink.reporterror(local1264);
			this.disconnect();
		}
		return true;
	}

	@OriginalMember(owner = "client!client", name = "s", descriptor = "(B)V")
	private void drawInventory() {
		this.areaInvback.makeTarget();
		Draw3D.lineOffsets = this.sidebarOffsets;
		this.invback.draw(0, 0);
		if (this.sidebarInterfaceId != -1) {
			this.drawInterface(0, 0, Component.instances[this.sidebarInterfaceId], 0);
		} else if (this.tabComponentId[this.selectedTab] != -1) {
			this.drawInterface(0, 0, Component.instances[this.tabComponentId[this.selectedTab]], 0);
		}
		if (this.menuVisible && this.mouseArea == 1) {
			this.drawMenu();
		}
		this.areaInvback.drawAt(231, super.graphics, 562);
		this.areaViewport.makeTarget();
		Draw3D.lineOffsets = this.viewportOffsets;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILjava/lang/String;)Z")
	private boolean isFriend(@OriginalArg(1) String arg1) {
		if (arg1 == null) {
			return false;
		}
		for (@Pc(15) int local15 = 0; local15 < this.friendCount; local15++) {
			if (arg1.equalsIgnoreCase(this.friendName[local15])) {
				return true;
			}
		}
		if (arg1.equalsIgnoreCase(this.self.name)) {
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!client", name = "init", descriptor = "()V")
	@Override
	public void init() {
		nodeId = Integer.parseInt(this.getParameter("nodeid"));
		gamePortOffset = Integer.parseInt(this.getParameter("portoff"));
		@Pc(15) String local15 = this.getParameter("lowmem");
		if (local15 != null && local15.equals("1")) {
			setLowMemory();
		} else {
			setHighMemory();
		}
		@Pc(31) String local31 = this.getParameter("free");
		if (local31 != null && local31.equals("1")) {
			members = false;
		} else {
			members = true;
		}
		this.initApplet(532, 789);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZIILclient!kb;Lclient!z;)V")
	private void updatePlayerMask(@OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Buffer arg3, @OriginalArg(4) PlayerEntity arg4) {
		@Pc(19) int local19;
		if ((arg2 & 0x1) == 1) {
			local19 = arg3.g1();
			@Pc(22) byte[] local22 = new byte[local19];
			@Pc(28) Buffer local28 = new Buffer(local22);
			arg3.gdata(local19, 0, local22);
			this.playerBuffers[arg1] = local28;
			arg4.decode(local28);
		}
		@Pc(66) int local66;
		if ((arg2 & 0x2) == 2) {
			local19 = arg3.g2();
			if (local19 == 65535) {
				local19 = -1;
			}
			if (local19 == arg4.primarySeq) {
				arg4.primarySeqPlays = 0;
			}
			local66 = arg3.g1();
			if (local19 == -1 || arg4.primarySeq == -1 || SeqType.instances[local19].priority > SeqType.instances[arg4.primarySeq].priority || SeqType.instances[arg4.primarySeq].priority == 0) {
				arg4.primarySeq = local19;
				arg4.primarySeqFrame = 0;
				arg4.primarySeqCycle = 0;
				arg4.primarySeqDelay = local66;
				arg4.primarySeqPlays = 0;
			}
		}
		if ((arg2 & 0x4) == 4) {
			arg4.targetEntity = arg3.g2();
			if (arg4.targetEntity == 65535) {
				arg4.targetEntity = -1;
			}
		}
		if ((arg2 & 0x8) == 8) {
			arg4.spoken = arg3.gstr();
			arg4.spokenColor = 0;
			arg4.spokenEffect = 0;
			arg4.chatTimer = 150;
			this.addMessage(2, arg4.spoken, arg4.name);
		}
		if ((arg2 & 0x10) == 16) {
			arg4.damageTaken = arg3.g1();
			arg4.damageType = arg3.g1();
			arg4.combatCycle = clientClock + 400;
			arg4.currentHealth = arg3.g1();
			arg4.maxHealth = arg3.g1();
		}
		if ((arg2 & 0x20) == 32) {
			arg4.focusX = arg3.g2();
			arg4.focusZ = arg3.g2();
		}
		if ((arg2 & 0x40) == 64) {
			local19 = arg3.g2();
			local66 = arg3.g1();
			@Pc(199) int local199 = arg3.g1();
			@Pc(202) int local202 = arg3.pos;
			if (arg4.name != null) {
				@Pc(209) long local209 = StringUtils.toBase37(arg4.name);
				@Pc(211) boolean local211 = false;
				if (local66 <= 1) {
					for (@Pc(216) int local216 = 0; local216 < this.ignoreCount; local216++) {
						if (this.ignoreName37[local216] == local209) {
							local211 = true;
							break;
						}
					}
				}
				if (!local211 && this.tutorialIslandState == 0) {
					try {
						@Pc(244) String local244 = TextEncoder.read(arg3, local199);
						@Pc(248) String local248 = WordPack.getFiltered(local244);
						arg4.spoken = local248;
						arg4.spokenColor = local19 >> 8;
						arg4.spokenEffect = local19 & 0xFF;
						arg4.chatTimer = 150;
						if (local66 > 1) {
							this.addMessage(1, local248, arg4.name);
						} else {
							this.addMessage(2, local248, arg4.name);
						}
					} catch (@Pc(285) Exception local285) {
						SignedLink.reporterror("cde2");
					}
				}
			}
			arg3.pos = local202 + local199;
		}
		if ((arg2 & 0x100) == 256) {
			arg4.spotAnimIndex = arg3.g2();
			local19 = arg3.g4();
			arg4.spotAnimOffsetY = local19 >> 16;
			arg4.lastSpotAnimCycle = clientClock + (local19 & 0xFFFF);
			arg4.spotAnimFrame = 0;
			arg4.spotAnimCycle = 0;
			if (arg4.lastSpotAnimCycle > clientClock) {
				arg4.spotAnimFrame = -1;
			}
			if (arg4.spotAnimIndex == 65535) {
				arg4.spotAnimIndex = -1;
			}
		}
		if ((arg2 & 0x200) == 512) {
			arg4.srcTileX = arg3.g1();
			arg4.srcTileZ = arg3.g1();
			arg4.dstTileX = arg3.g1();
			arg4.dstTileZ = arg3.g1();
			arg4.firstMoveCycle = arg3.g2() + clientClock;
			arg4.lastMoveCycle = arg3.g2() + clientClock;
			arg4.faceDirection = arg3.g1();
			arg4.pathLength = 0;
			arg4.pathTileX[0] = arg4.dstTileX;
			arg4.pathTileZ[0] = arg4.dstTileZ;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	@Override
	protected void showProgress(@OriginalArg(1) String str, @OriginalArg(2) int progress) {
		this.prepareTitleScreen();
		if (this.titleArchive == null) {
			super.showProgress(str, progress);
		} else {
			this.titleCenter.makeTarget();
			@Pc(17) short local17 = 360;
			@Pc(19) short local19 = 200;
			@Pc(21) byte local21 = 20;
			this.bold12.drawCentered(local19 / 2 - local21 - 26, 16777215, "RuneScape is loading - please wait...", local17 / 2);
			@Pc(51) int local51 = local19 / 2 - local21 - 18;
			Draw2D.drawRect(local17 / 2 - 152, 9179409, 34, local51, 304);
			Draw2D.drawRect(local17 / 2 - 151, 0, 32, local51 + 1, 302);
			Draw2D.fillRect(local51 + 2, local17 / 2 - 150, 9179409, progress * 3, 30);
			Draw2D.fillRect(local51 + 2, local17 / 2 - 150 + progress * 3, 0, 300 - progress * 3, 30);
			this.bold12.drawCentered(local19 / 2 + 5 - local21, 16777215, str, local17 / 2);
			this.titleCenter.drawAt(186, super.graphics, 214);
			if (this.redrawTitleBackground) {
				this.redrawTitleBackground = false;
				if (!this.flameActive) {
					this.titleLeft.drawAt(0, super.graphics, 0);
					this.titleRight.drawAt(0, super.graphics, 661);
				}
				this.titleTop.drawAt(0, super.graphics, 128);
				this.titleBottom.drawAt(386, super.graphics, 214);
				this.titleBottomLeft.drawAt(265, super.graphics, 0);
				this.titleBottomRight.drawAt(265, super.graphics, 574);
				this.titleLeftSpace.drawAt(186, super.graphics, 128);
				this.titleRightSpace.drawAt(186, super.graphics, 574);
			}
		}
	}
}
