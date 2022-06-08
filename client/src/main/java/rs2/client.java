package rs2;

import java.awt.Color;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.time.Instant;
import java.util.Arrays;
import java.util.zip.CRC32;

import com.jagex.core.bzip2.BZip2InputStream;
import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import com.jagex.core.isaac.IsaacRandom;
import com.jagex.core.network.BufferedStream;
import com.jagex.core.stringutils.StringUtils;
import com.jagex.core.stringutils.TextEncoder;
import com.jagex.core.stringutils.WordPack;
import com.jagex.core.utils.LinkedList;
import com.jagex.game.runetek3.client.GameShell;
import com.jagex.game.runetek3.client.InputTracking;
import com.jagex.game.runetek3.config.*;
import com.jagex.game.runetek3.entity.*;
import com.jagex.game.runetek3.graphics.*;
import com.jagex.game.runetek3.graphics.model.Model;
import com.jagex.game.runetek3.graphics.seq.SeqBase;
import com.jagex.game.runetek3.graphics.seq.SeqFrame;
import com.jagex.game.runetek3.graphics.ui.Font;
import com.jagex.game.runetek3.graphics.ui.Component;
import com.jagex.game.runetek3.scenegraph.CollisionMap;
import com.jagex.game.runetek3.scenegraph.MapSquare;
import com.jagex.game.runetek3.scenegraph.Scene;
import com.jagex.game.runetek3.sound.SoundTrack;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import rs2.sign.signlink;
import com.itspazaz.lostcity.Server;

@OriginalClass("client!client")
public class client extends GameShell {

	public static boolean startServer = false;

	public static int httpPortOffset = 0;

	public static boolean drawDebug = true;

	// these mapfunction sprites are defined here because they aren't randomly offsetted on the minimap
	public static final int MAPFUNC_GEM_SHOP = 22;
	public static final int MAPFUNC_SILK_TRADER = 29;
	public static final int MAPFUNC_RARE_TREES = 34;
	public static final int MAPFUNC_FOOD_SHOP = 36;
	public static final int MAPFUNC_SILVER_SHOP = 46;
	public static final int MAPFUNC_FUR_TRADER = 47;
	public static final int MAPFUNC_SPICE_SHOP = 48;

	/// ----

	@OriginalMember(owner = "client!client", name = "E", descriptor = "I")
	private static int opHeld1Counter;

	@OriginalMember(owner = "client!client", name = "Ab", descriptor = "I")
	private static int opLoc4Counter;

	@OriginalMember(owner = "client!client", name = "Zb", descriptor = "I")
	private static int opNpc5Counter;

	@OriginalMember(owner = "client!client", name = "gc", descriptor = "I")
	private static int drawCounter;

	@OriginalMember(owner = "client!client", name = "ic", descriptor = "I")
	private static int opHeld4Counter;

	@OriginalMember(owner = "client!client", name = "hd", descriptor = "I")
	private static int opLoc5Counter;

	@OriginalMember(owner = "client!client", name = "rd", descriptor = "I")
	private static int opNpc3Counter;

	@OriginalMember(owner = "client!client", name = "Ad", descriptor = "I")
	private static int opHeld9Counter;

	@OriginalMember(owner = "client!client", name = "Gd", descriptor = "I")
	private static int sidebarInputCounter;

	@OriginalMember(owner = "client!client", name = "Ld", descriptor = "I")
	private static int gamePortOffset;

	@OriginalMember(owner = "client!client", name = "Nd", descriptor = "Z")
	private static boolean lowMemory;

	@OriginalMember(owner = "client!client", name = "Od", descriptor = "I")
	private static int opPlayer2Counter;

	@OriginalMember(owner = "client!client", name = "le", descriptor = "I")
	private static int updatePlayersCounter;

	@OriginalMember(owner = "client!client", name = "Je", descriptor = "I")
	private static int ifButton5Counter;

	@OriginalMember(owner = "client!client", name = "bf", descriptor = "I")
	public static int clientClock;

	@OriginalMember(owner = "client!client", name = "Tf", descriptor = "I")
	private static int updateCounter;

	@OriginalMember(owner = "client!client", name = "Hh", descriptor = "I")
	private static int update2Counter;

	@OriginalMember(owner = "client!client", name = "Th", descriptor = "Z")
	private static boolean alreadyStarted;

	@OriginalMember(owner = "client!client", name = "Zh", descriptor = "I")
	private static int updateLocCounter;

	@OriginalMember(owner = "client!client", name = "F", descriptor = "Ljava/lang/String;")
	private static final String ASCII_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

	@OriginalMember(owner = "client!client", name = "od", descriptor = "[I")
	private static final int[] EXPERIENCE_TABLE = new int[99];

	@OriginalMember(owner = "client!client", name = "Kd", descriptor = "I")
	private static int nodeId;

	@OriginalMember(owner = "client!client", name = "Md", descriptor = "Z")
	private static boolean members;

	@OriginalMember(owner = "client!client", name = "ue", descriptor = "Ljava/math/BigInteger;")
	private static final BigInteger RSA_EXPONENT;

	@OriginalMember(owner = "client!client", name = "fh", descriptor = "Ljava/math/BigInteger;")
	private static final BigInteger RSA_MODULUS;

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
	private IsaacRandom isaac;

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
	private int weightCarried;

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
	private BufferedImageFrameBuffer areaBackbase1;

	@OriginalMember(owner = "client!client", name = "dd", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer areaBackbase2;

	@OriginalMember(owner = "client!client", name = "ed", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer areaBackhmid1;

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
	private BufferedImageFrameBuffer backleft1;

	@OriginalMember(owner = "client!client", name = "dg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backleft2;

	@OriginalMember(owner = "client!client", name = "eg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backright1;

	@OriginalMember(owner = "client!client", name = "fg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backright2;

	@OriginalMember(owner = "client!client", name = "gg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backtop1;

	@OriginalMember(owner = "client!client", name = "hg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backtop2;

	@OriginalMember(owner = "client!client", name = "ig", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backvmid1;

	@OriginalMember(owner = "client!client", name = "jg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backvmid2;

	@OriginalMember(owner = "client!client", name = "kg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backvmid3;

	@OriginalMember(owner = "client!client", name = "lg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer backhmid2;

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
	private BufferedImageFrameBuffer titleTop;

	@OriginalMember(owner = "client!client", name = "zg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleBottom;

	@OriginalMember(owner = "client!client", name = "Ag", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleCenter;

	@OriginalMember(owner = "client!client", name = "Bg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleLeft;

	@OriginalMember(owner = "client!client", name = "Cg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleRight;

	@OriginalMember(owner = "client!client", name = "Dg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleBottomLeft;

	@OriginalMember(owner = "client!client", name = "Eg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleBottomRight;

	@OriginalMember(owner = "client!client", name = "Fg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleLeftSpace;

	@OriginalMember(owner = "client!client", name = "Gg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer titleRightSpace;

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
	private BufferedImageFrameBuffer areaInvback;

	@OriginalMember(owner = "client!client", name = "Qg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer areaMapback;

	@OriginalMember(owner = "client!client", name = "Rg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer areaViewport;

	@OriginalMember(owner = "client!client", name = "Sg", descriptor = "Lclient!qb;")
	private BufferedImageFrameBuffer areaChatback;

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
	private int energy;

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
	private FileArchive title;

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
	private Buffer loginBuffer = Buffer.reserve(0);

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
	private boolean chatRedrawSettings = false;

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
	private boolean sidebarRedraw = false;

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

	@OriginalMember(owner = "client!client", name = "Rf", descriptor = "Ljava/util/zip/CRC32;")
	private final CRC32 crc32 = new CRC32();

	@OriginalMember(owner = "client!client", name = "Wf", descriptor = "I")
	private int chatbackComponentId = -1;

	@OriginalMember(owner = "client!client", name = "og", descriptor = "I")
	private int drawX = -1;

	@OriginalMember(owner = "client!client", name = "pg", descriptor = "I")
	private int drawY = -1;

	@OriginalMember(owner = "client!client", name = "qg", descriptor = "I")
	private int stickyChatbackComponentId = -1;

	@OriginalMember(owner = "client!client", name = "sg", descriptor = "Z")
	private boolean rights = false;

	@OriginalMember(owner = "client!client", name = "tg", descriptor = "[I")
	private final int[] unknownCameraVariable = new int[5];

	@OriginalMember(owner = "client!client", name = "Hg", descriptor = "[Lclient!ib;")
	private IndexedSprite[] mapscene = new IndexedSprite[50];

	@OriginalMember(owner = "client!client", name = "Og", descriptor = "[I")
	private final int[] textColors = new int[] { 0xffff00, 0xff0000, 0xff00, 0xffff, 0xff00ff, 0xffffff };

	@OriginalMember(owner = "client!client", name = "Tg", descriptor = "I")
	private final int SCROLLBAR_TRACK = 2301979;

	@OriginalMember(owner = "client!client", name = "Yg", descriptor = "Z")
	private boolean chatbackInputType = false;

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
	private String loginMessage0 = "";

	@OriginalMember(owner = "client!client", name = "Bh", descriptor = "Ljava/lang/String;")
	private String loginMessage1 = "";

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
		@Pc(6) int xpAccum = 0;
		for (@Pc(8) int level = 0; level < 99; level++) {
			@Pc(13) int nextLevel = level + 1;
			@Pc(26) int nextLevelXp = (int) ((double) nextLevel + Math.pow(2.0D, (double) nextLevel / 7.0D) * 300.0D);
			xpAccum += nextLevelXp;
			EXPERIENCE_TABLE[level] = xpAccum / 4;
		}
		nodeId = 10;
		members = true;
		RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
		RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(Z)V")
	private static void setLowMemory() {
		MapSquare.lowMemory = true;
		Draw3D.lowMemory = true;
		lowMemory = true;
		Scene.lowMemory = true;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(II)Ljava/lang/String;")
	private static String formatNumber(@OriginalArg(0) int value) {
		@Pc(4) String s = String.valueOf(value);
		for (@Pc(9) int i = s.length() - 3; i > 0; i -= 3) {
			s = s.substring(0, i) + "," + s.substring(i);
		}

		if (s.length() > 8) {
			s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
		} else if (s.length() > 4) {
			s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
		}

		return " " + s;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IZI)Ljava/lang/String;")
	private static String getLevelColorTag(@OriginalArg(0) int lvl1, @OriginalArg(2) int lvl2) {
		@Pc(9) int diff = lvl1 - lvl2;
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

	@OriginalMember(owner = "client!client", name = "B", descriptor = "(I)V")
	private static void setHighMemory() {
		MapSquare.lowMemory = false;
		Draw3D.lowMemory = false;
		lowMemory = false;
		Scene.lowMemory = false;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZI)Ljava/lang/String;")
	private static String formatObjAmount(@OriginalArg(1) int amount) {
		if (amount < 100000) {
			return String.valueOf(amount);
		} else if (amount < 10000000) {
			return amount / 1000 + "K";
		} else {
			return amount / 1000000 + "M";
		}
	}

	@OriginalMember(owner = "client!client", name = "main", descriptor = "([Ljava/lang/String;)V")
	public static void main(@OriginalArg(0) String[] args) {
		try {
			System.out.println("RS2 user client - release #" + 225);

			if (args.length == 4) {
				nodeId = Integer.parseInt(args[0]);
				gamePortOffset = Integer.parseInt(args[1]);

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
			} else {
				nodeId = 10;
				gamePortOffset = 0;
				setHighMemory();
				members = true;

				if (args.length > 0 && args[0].equals("server")) {
					startServer = true;
				}
			}

			if (startServer) {
				httpPortOffset = 1000 + (int)(Math.random() * 30000);
				Server.httpPort = httpPortOffset + 80;
				Server.main(new String[] { "client" });
			}

			signlink.startpriv(InetAddress.getLocalHost());
			@Pc(82) client c = new client();
			c.initApplication();
		} catch (@Pc(89) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZILjava/lang/String;I)V")
	private void setMidi(@OriginalArg(1) int crc, @OriginalArg(2) String name, @OriginalArg(3) int len) {
		if (name == null) {
			return;
		}

		synchronized (this.midiSync) {
			this.midiSyncName = name;
			this.midiSyncCrc = crc;
			this.midiSyncLen = len;
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(I)V")
	private void drawViewport2d() {
		this.chatCount = 0;

		for (@Pc(15) int i = -1; i < this.playerCount + this.npcCount; i++) {
			@Pc(23) PathingEntity e;
			if (i == -1) {
				e = this.self;
			} else if (i < this.playerCount) {
				e = this.playerEntities[this.playerIndices[i]];
			} else {
				e = this.npcEntities[this.npcIndices[i - this.playerCount]];
			}

			if (e == null || !e.isValid()) {
				continue;
			}

			if (i < this.playerCount) {
				int y = 30;

				@Pc(66) PlayerEntity p = (PlayerEntity) e;
				if (p.headicons != 0) {
					this.setDrawPos(e.height + 15, e);
					if (this.drawX > -1) {
						for (int j = 0; j < 8; j++) {
							if ((p.headicons & 0x1 << j) != 0) {
								this.headicons[j].draw(this.drawX - 12, this.drawY - y);
								y -= 25;
							}
						}
					}
				}

				if (i >= 0 && this.hintType == 10 && this.hintPlayer == this.playerIndices[i]) {
					this.setDrawPos(e.height + 15, e);

					if (this.drawX > -1) {
						this.headicons[7].draw(this.drawX - 12, this.drawY - y);
					}
				}
			} else if (this.hintType == 1 && this.hintNpc == this.npcIndices[i - this.playerCount] && clientClock % 20 < 10) {
				this.setDrawPos(e.height + 15, e);

				if (this.drawX > -1) {
					this.headicons[2].draw(this.drawX - 12, this.drawY - 28);
				}
			}

			if (e.spoken != null && (i >= this.playerCount || this.chatPublicSetting == 0 || this.chatPublicSetting == 3 || this.chatPublicSetting == 1 && this.isFriend(((PlayerEntity) e).name))) {
				this.setDrawPos(e.height, e);

				if (this.drawX > -1 && this.chatCount < this.overheadMessageCount) {
					this.chatPadding[this.chatCount] = this.bold12.stringWidth(e.spoken) / 2;
					this.chatHeight[this.chatCount] = this.bold12.fontHeight;
					this.chatScreenX[this.chatCount] = this.drawX;
					this.chatScreenY[this.chatCount] = this.drawY;
					this.chatColors[this.chatCount] = e.spokenColor;
					this.chatStyles[this.chatCount] = e.spokenEffect;
					this.chatTimers[this.chatCount] = e.textCycle;
					this.chatMessages[this.chatCount++] = e.spoken;

					if (this.chatEffects == 0 && e.spokenEffect == 1) {
						this.chatHeight[this.chatCount] += 10;
						this.chatScreenY[this.chatCount] += 5;
					}

					if (this.chatEffects == 0 && e.spokenEffect == 2) {
						this.chatPadding[this.chatCount] = 60;
					}
				}
			}

			if (e.lastCombatCycle > clientClock + 100) {
				this.setDrawPos(e.height + 15, e);
				if (this.drawX > -1) {
					int y = e.currentHealth * 30 / e.maxHealth;
					if (y > 30) {
						y = 30;
					}

					Draw2D.fillRect(this.drawX - 15, this.drawY - 3, y, 5, 0xff00);
					Draw2D.fillRect(this.drawX - 15 + y, this.drawY - 3, 30 - y, 5, 0xff0000);
				}
			}

			if (e.lastCombatCycle > clientClock + 330) {
				this.setDrawPos(e.height / 2, e);

				if (this.drawX > -1) {
					this.hitmarks[e.damageType].draw(this.drawX - 12, this.drawY - 12);
					this.plain11.drawCentered(String.valueOf(e.damageTaken), this.drawX, this.drawY + 4, 0);
					this.plain11.drawCentered(String.valueOf(e.damageTaken), this.drawX - 1, this.drawY + 3, 0xffffff);
				}
			}
		}

		for (@Pc(483) int chat = 0; chat < this.chatCount; chat++) {
			int x = this.chatScreenX[chat];
			@Pc(495) int y = this.chatScreenY[chat];
			int padding = this.chatPadding[chat];
			@Pc(505) int height = this.chatHeight[chat];

			@Pc(507) boolean visible = true;
			while (visible) {
				visible = false;
				for (@Pc(513) int i = 0; i < chat; i++) {
					if (y + 2 > this.chatScreenY[i] - this.chatHeight[i] && y - height < this.chatScreenY[i] + 2 && x - padding < this.chatScreenX[i] + this.chatPadding[i] && x + padding > this.chatScreenX[i] - this.chatPadding[i] && this.chatScreenY[i] - this.chatHeight[i] < y) {
						y = this.chatScreenY[i] - this.chatHeight[i];
						visible = true;
					}
				}
			}

			this.drawX = this.chatScreenX[chat];
			this.drawY = this.chatScreenY[chat] = y;
			@Pc(612) String message = this.chatMessages[chat];
			if (this.chatEffects == 0) {
				@Pc(692) int timer;
				@Pc(617) int color = 0xffff00;

				if (this.chatColors[chat] < 6) {
					color = this.textColors[this.chatColors[chat]];
				} else if (this.chatColors[chat] == 6) {
					color = this.sceneCycle % 20 < 10 ? 0xff0000 : 0xffff00;
				} else if (this.chatColors[chat] == 7) {
					color = this.sceneCycle % 20 < 10 ? 0xff : 0xffff;
				} else if (this.chatColors[chat] == 8) {
					color = this.sceneCycle % 20 < 10 ? 0xb000 : 0x80ff80;
				} else if (this.chatColors[chat] == 9) {
					timer = 150 - this.chatTimers[chat];
					if (timer < 50) {
						color = timer * 1280 + 0xff0000;
					} else if (timer < 100) {
						color = 0xffff00 - (timer - 50) * 327680;
					} else if (timer < 150) {
						color = (timer - 100) * 5 + 0xff00;
					}
				} else if (this.chatColors[chat] == 10) {
					timer = 150 - this.chatTimers[chat];
					if (timer < 50) {
						color = timer * 5 + 0xff0000;
					} else if (timer < 100) {
						color = 0xff00ff - (timer - 50) * 327680;
					} else if (timer < 150) {
						color = (timer - 100) * 327680 + 255 - (timer - 100) * 5;
					}
				} else if (this.chatColors[chat] == 11) {
					timer = 150 - this.chatTimers[chat];
					if (timer < 50) {
						color = 0xffffff - timer * 327685;
					} else if (timer < 100) {
						color = (timer - 50) * 327685 + 0xff00;
					} else if (timer < 150) {
						color = 0xffffff - (timer - 100) * 327680;
					}
				}

				if (this.chatStyles[chat] == 0) {
					this.bold12.drawCentered(message, this.drawX, this.drawY + 1, 0);
					this.bold12.drawCentered(message, this.drawX, this.drawY, color);
				} else if (this.chatStyles[chat] == 1) {
					this.bold12.drawCenteredWave(message, this.sceneCycle, this.drawX, this.drawY + 1, 0);
					this.bold12.drawCenteredWave(message, this.sceneCycle, this.drawX, this.drawY, color);
				} else if (this.chatStyles[chat] == 2) {
					int width = this.bold12.stringWidth(message);
					@Pc(913) int xoff = (150 - this.chatTimers[chat]) * (width + 100) / 150;
					Draw2D.setBounds(this.drawX - 50, this.drawX + 50, 0, 334);
					this.bold12.draw(this.drawX + 50 - xoff, this.drawY + 1, 0, message);
					this.bold12.draw(this.drawX + 50 - xoff, this.drawY, color, message);
					Draw2D.resetBounds();
				}
			} else {
				this.bold12.drawCentered(message, this.drawX, this.drawY + 1, 0);
				this.bold12.drawCentered(message, this.drawX, this.drawY, 0xffff00);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(B)V")
	private void closeInterface() {
		this.outBuffer.p1isaac(ClientProt.CLOSE_MODAL);

		if (this.sidebarInterfaceId != -1) {
			this.sidebarInterfaceId = -1;
			this.sidebarRedraw = true;
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
		signlink.midifade = false;
		signlink.midi = "stop";
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(I)V")
	private void drawWildyLevel() {
		@Pc(10) int x = (this.self.x >> 7) + this.baseTileX;
		@Pc(19) int z = (this.self.z >> 7) + this.baseTileZ;

		if (x >= 2944 && x < 3392 && z >= 3520 && z < 6400) {
			this.wildernessLevel = (z - 3520) / 8 + 1;
		} else if (x >= 2944 && x < 3392 && z >= 9920 && z < 12800) {
			this.wildernessLevel = (z - 9920) / 8 + 1;
		} else {
			this.wildernessLevel = 0;
		}

		this.worldLocationState = 0;
		if (x >= 3328 && x < 3392 && z >= 3200 && z < 3264) {
			@Pc(98) int mx = x & 0x3F;
			@Pc(102) int mz = z & 0x3F;
			if (mx >= 4 && mx <= 29 && mz >= 44 && mz <= 58) {
				this.worldLocationState = 1;
			}
			if (mx >= 36 && mx <= 61 && mz >= 44 && mz <= 58) {
				this.worldLocationState = 1;
			}
			if (mx >= 4 && mx <= 29 && mz >= 25 && mz <= 39) {
				this.worldLocationState = 1;
			}
			if (mx >= 36 && mx <= 61 && mz >= 25 && mz <= 39) {
				this.worldLocationState = 1;
			}
			if (mx >= 4 && mx <= 29 && mz >= 6 && mz <= 20) {
				this.worldLocationState = 1;
			}
			if (mx >= 36 && mx <= 61 && mz >= 6 && mz <= 20) {
				this.worldLocationState = 1;
			}
		}
		if (this.worldLocationState == 0 && x >= 3328 && x <= 3393 && z >= 3203 && z <= 3325) {
			this.worldLocationState = 2;
		}

		this.tutorialIslandState = 0;
		if (x >= 3053 && x <= 3156 && z >= 3056 && z <= 3136) {
			this.tutorialIslandState = 1;
		}
		if (x >= 3072 && x <= 3118 && z >= 9492 && z <= 9535) {
			this.tutorialIslandState = 1;
		}
		if (this.tutorialIslandState == 1 && x >= 3139 && x <= 3199 && z >= 3008 && z <= 3062) {
			this.tutorialIslandState = 0;
		}
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(I)V")
	private void drawChat() {
		if (this.splitPrivateChat == 0) {
			return;
		}

		@Pc(9) Font font = this.plain12;
		@Pc(11) int line = 0;
		if (this.systemUpdateTimer != 0) {
			line = 1;
		}

		for (@Pc(18) int i = 0; i < 100; i++) {
			if (this.chatMessage[i] != null) {
				@Pc(30) int type = this.chatMessageType[i];
				@Pc(60) int y;

				if ((type == 3 || type == 7) && (type == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[i]))) {
					y = 329 - line * 13;
					font.draw(4, y, 0, "From " + this.chatMessagePrefix[i] + ": " + this.chatMessage[i]);
					font.draw(4, y - 1, 0xffff, "From " + this.chatMessagePrefix[i] + ": " + this.chatMessage[i]);
					line++;
					if (line >= 5) {
						return;
					}
				} else if (type == 5 && this.chatPrivateSetting < 2) {
					y = 329 - line * 13;
					font.draw(4, y, 0, this.chatMessage[i]);
					font.draw(4, y - 1, 0xffff, this.chatMessage[i]);
					line++;
					if (line >= 5) {
						return;
					}
				} else if (type == 6 && this.chatPrivateSetting < 2) {
					y = 329 - line * 13;
					font.draw(4, y, 0, "To " + this.chatMessagePrefix[i] + ": " + this.chatMessage[i]);
					font.draw(4, y - 1, 0xffff, "To " + this.chatMessagePrefix[i] + ": " + this.chatMessage[i]);
					line++;
					if (line >= 5) {
						return;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!kb;II)V")
	private void updateNpcMasks(@OriginalArg(0) Buffer b, @OriginalArg(1) int size) {
		for (@Pc(1) int n = 0; n < this.updateCount; n++) {
			@Pc(8) int nid = this.entityUpdateIndices[n];
			@Pc(13) NpcEntity npc = this.npcEntities[nid];
			@Pc(16) int mask = b.g1();

			@Pc(24) int local24;
			if ((mask & 0x2) == 2) {
				local24 = b.g2();
				if (local24 == 0xffff) {
					local24 = -1;
				}
				if (local24 == npc.primarySeq) {
					npc.primarySeqPlays = 0;
				}
				@Pc(39) int local39 = b.g1();
				if (local24 == -1 || npc.primarySeq == -1 || SeqType.instances[local24].priority > SeqType.instances[npc.primarySeq].priority || SeqType.instances[npc.primarySeq].priority == 0) {
					npc.primarySeq = local24;
					npc.primarySeqFrame = 0;
					npc.primarySeqCycle = 0;
					npc.primarySeqDelay = local39;
					npc.primarySeqPlays = 0;
				}
			}
			if ((mask & 0x4) == 4) {
				npc.targetEntity = b.g2();
				if (npc.targetEntity == 0xffff) {
					npc.targetEntity = -1;
				}
			}
			if ((mask & 0x8) == 8) {
				npc.spoken = b.gstr();
				npc.textCycle = 100;
			}
			if ((mask & 0x10) == 16) {
				npc.damageTaken = b.g1();
				npc.damageType = b.g1();
				npc.lastCombatCycle = clientClock + 400;
				npc.currentHealth = b.g1();
				npc.maxHealth = b.g1();
			}
			if ((mask & 0x20) == 32) {
				npc.info = NpcType.get(b.g2());
				npc.runSeq = npc.info.walkSeq;
				npc.walkSeq = npc.info.turnAroundSeq;
				npc.turnAroundSeq = npc.info.turnRightSeq;
				npc.turnRightSeq = npc.info.turnLeftSeq;
				npc.standSeq = npc.info.standSeq;
			}
			if ((mask & 0x40) == 64) {
				npc.spotAnimIndex = b.g2();
				local24 = b.g4();
				npc.spotAnimOffsetY = local24 >> 16;
				npc.lastSpotAnimCycle = clientClock + (local24 & 0xFFFF);
				npc.spotAnimFrame = 0;
				npc.spotAnimCycle = 0;
				if (npc.lastSpotAnimCycle > clientClock) {
					npc.spotAnimFrame = -1;
				}
				if (npc.spotAnimIndex == 0xffff) {
					npc.spotAnimIndex = -1;
				}
			}
			if ((mask & 0x80) == 128) {
				npc.focusX = b.g2();
				npc.focusZ = b.g2();
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(JB)V")
	private void addIgnore(@OriginalArg(0) long name37) {
		if (name37 == 0L) {
			return;
		}

		if (this.ignoreCount >= 100) {
			this.addMessage(0, "", "Your ignore list is full. Max of 100 hit");
			return;
		}

		@Pc(23) String name = StringUtils.formatName(StringUtils.fromBase37(name37));
		for (@Pc(25) int i = 0; i < this.ignoreCount; i++) {
			if (this.ignoreName37[i] == name37) {
				this.addMessage(0, "", name + " is already on your ignore list");
				return;
			}
		}

		for (@Pc(55) int i = 0; i < this.friendCount; i++) {
			if (this.friendName37[i] == name37) {
				this.addMessage(0, "", "Please remove " + name + " from your friend list first");
				return;
			}
		}

		this.ignoreName37[this.ignoreCount++] = name37;
		this.sidebarRedraw = true;
		this.outBuffer.p1isaac(ClientProt.IGNORELIST_ADD);
		this.outBuffer.p8(name37);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(BLclient!kb;I)V")
	private void readZonePacket(@OriginalArg(1) Buffer buffer, @OriginalArg(2) int opcode) {
		int tile = buffer.g1();
		int x = this.localPosX + (tile >> 4 & 0x7);
		int z = this.localPosZ + (tile & 0x7);

		if (opcode == ServerProt.LOC_ADD || opcode == ServerProt.LOC_DEL) {
			int locInfo = buffer.g1();
			int locType = locInfo >> 2;
			int locRotation = locInfo & 0x3;
			int locClass = this.objectGroups[locType];

			int locIndex;
			if (opcode == ServerProt.LOC_DEL) {
				locIndex = -1;
			} else {
				locIndex = buffer.g2();
			}

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				@Pc(69) SpawnedLoc loc = null;
				for (@Pc(74) SpawnedLoc l = (SpawnedLoc) this.spawnedLocations.peekPrevious(); l != null; l = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					if (l.level == this.currentPlane && l.tileX == x && l.tileZ == z && l.classType == locClass) {
						loc = l;
						break;
					}
				}

				if (loc == null) {
					int bitset = 0;
					int lastIndex = -1;
					int lastType = 0;
					@Pc(114) int lastRotation = 0;

					if (locClass == 0) {
						bitset = this.mapSquare.getWallBitset(this.currentPlane, x, z);
					}

					if (locClass == 1) {
						bitset = this.mapSquare.getWallDecorationBitset(this.currentPlane, z, x);
					}

					if (locClass == 2) {
						bitset = this.mapSquare.getLocationBitset(this.currentPlane, x, z);
					}

					if (locClass == 3) {
						bitset = this.mapSquare.getGroundDecorationBitset(this.currentPlane, x, z);
					}

					if (bitset != 0) {
						@Pc(169) int info = this.mapSquare.getInfo(this.currentPlane, x, z, bitset);
						lastIndex = bitset >> 14 & 0x7FFF;
						lastType = info & 0x1F;
						lastRotation = info >> 6;
					}

					loc = new SpawnedLoc();
					loc.level = this.currentPlane;
					loc.classType = locClass;
					loc.tileX = x;
					loc.tileZ = z;
					loc.lastLocIndex = lastIndex;
					loc.lastType = lastType;
					loc.lastRotation = lastRotation;
					this.spawnedLocations.pushNext(loc);
				}
				loc.locIndex = locIndex;
				loc.type = locType;
				loc.orientation = locRotation;
				this.addLoc(locRotation, x, z, locClass, locIndex, locType, this.currentPlane);
			}
		} else if (opcode == ServerProt.LOC_ANIM) {
			int locInfo = buffer.g1();
			int locType = locInfo >> 2;
			int locClass = this.objectGroups[locType];

			int animationIndex = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				int bitset = 0;
				if (locClass == 0) {
					bitset = this.mapSquare.getWallBitset(this.currentPlane, x, z);
				}

				if (locClass == 1) {
					bitset = this.mapSquare.getWallDecorationBitset(this.currentPlane, z, x);
				}

				if (locClass == 2) {
					bitset = this.mapSquare.getLocationBitset(this.currentPlane, x, z);
				}

				if (locClass == 3) {
					bitset = this.mapSquare.getGroundDecorationBitset(this.currentPlane, x, z);
				}

				if (bitset != 0) {
					@Pc(348) LocEntity loc = new LocEntity(false, bitset >> 14 & 0x7FFF, this.currentPlane, 0, locClass, SeqType.instances[animationIndex], z, x);
					this.locList.pushNext(loc);
				}
			}
		} else if (opcode == ServerProt.OBJ_REVEAL) {
			int objId = buffer.g2();
			int objAmount = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				ObjStackEntity objStack = new ObjStackEntity();
				objStack.id = objId;
				objStack.amount = objAmount;
				if (this.objects[this.currentPlane][x][z] == null) {
					this.objects[this.currentPlane][x][z] = new LinkedList();
				}
				this.objects[this.currentPlane][x][z].pushNext(objStack);
				this.updateObjectStack(x, z);
			}
		} else if (opcode == ServerProt.OBJ_DEL) {
			int objId = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				@Pc(485) LinkedList stacks = this.objects[this.currentPlane][x][z];
				if (stacks != null) {
					for (ObjStackEntity objStack = (ObjStackEntity) stacks.peekPrevious(); objStack != null; objStack = (ObjStackEntity) stacks.getPrevious()) {
						if (objStack.id == (objId & 0x7FFF)) {
							objStack.unlink();
							break;
						}
					}

					if (stacks.peekPrevious() == null) {
						this.objects[this.currentPlane][x][z] = null;
					}

					this.updateObjectStack(x, z);
				}
			}
		} else if (opcode == ServerProt.MAP_PROJANIM) {
			int dstX = x + buffer.g1b();
			int dstZ = z + buffer.g1b();
			int targetIndex = buffer.g2b();
			int spotanimIndex = buffer.g2();
			int offsetY = buffer.g1();
			int baseY = buffer.g1();
			int startCycle = buffer.g2();
			int endCycle = buffer.g2();
			int elevationPitch = buffer.g1();
			int arcScale = buffer.g1();

			if (x >= 0 && z >= 0 && x < 104 && z < 104 && dstX >= 0 && dstZ >= 0 && dstX < 104 && dstZ < 104) {
				x = x * 128 + 64;
				z = z * 128 + 64;
				dstX = dstX * 128 + 64;
				dstZ = dstZ * 128 + 64;
				@Pc(657) ProjectileEntity proj = new ProjectileEntity(baseY, elevationPitch, z, endCycle + clientClock, this.currentPlane, targetIndex, startCycle + clientClock, arcScale, 0, this.getLandY(this.currentPlane, x, z) - offsetY, spotanimIndex, x);
				proj.setTarget(this.getLandY(this.currentPlane, dstX, dstZ) - baseY, dstZ, dstX, startCycle + clientClock);
				this.projectiles.pushNext(proj);
			}
		} else if (opcode == ServerProt.SPOTANIM_SPECIFIC) {
			int spotanimIndex = buffer.g2();
			int height = buffer.g1();
			int duration = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				x = x * 128 + 64;
				z = z * 128 + 64;
				@Pc(753) SpotAnimEntity spotAnim = new SpotAnimEntity(x, spotanimIndex, z, duration, this.getLandY(this.currentPlane, x, z) - height, this.currentPlane, clientClock);
				this.spotanims.pushNext(spotAnim);
			}
		} else if (opcode == ServerProt.OBJ_ADD) {
			int objId = buffer.g2();
			int objAmount = buffer.g2();
			int playerId = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104 && playerId != this.selfPlayerId) {
				@Pc(807) ObjStackEntity objStack = new ObjStackEntity();
				objStack.id = objId;
				objStack.amount = objAmount;
				if (this.objects[this.currentPlane][x][z] == null) {
					this.objects[this.currentPlane][x][z] = new LinkedList();
				}
				this.objects[this.currentPlane][x][z].pushNext(objStack);
				this.updateObjectStack(x, z);
			}
		} else if (opcode == ServerProt.LOC_ADD_CHANGE) {
			int locInfo = buffer.g1();
			int locType = locInfo >> 2;
			int locRotation = locInfo & 0x3;
			int locClass = this.objectGroups[locType];

			int locId = buffer.g2();
			int firstCycle = buffer.g2();
			int lastCycle = buffer.g2();
			int playerId = buffer.g2();
			@Pc(905) byte minX = buffer.g1b();
			@Pc(908) byte minZ = buffer.g1b();
			@Pc(911) byte maxX = buffer.g1b();
			@Pc(914) byte maxZ = buffer.g1b();

			@Pc(921) PlayerEntity playerEntity;
			if (playerId == this.selfPlayerId) {
				playerEntity = this.self;
			} else {
				playerEntity = this.playerEntities[playerId];
			}

			if (playerEntity != null) {
				@Pc(946) TemporaryLoc loc1 = new TemporaryLoc(this.currentPlane, locRotation, z, firstCycle + clientClock, 2, locType, -1, x, locClass);
				this.temporaryLocs.pushNext(loc1);
				@Pc(966) TemporaryLoc loc2 = new TemporaryLoc(this.currentPlane, locRotation, z, lastCycle + clientClock, 2, locType, locId, x, locClass);
				this.temporaryLocs.pushNext(loc2);

				@Pc(980) int region = this.levelHeightMaps[this.currentPlane][x][z];
				@Pc(992) int regionEast = this.levelHeightMaps[this.currentPlane][x + 1][z];
				@Pc(1006) int regionNorthEast = this.levelHeightMaps[this.currentPlane][x + 1][z + 1];
				@Pc(1018) int regionNorth = this.levelHeightMaps[this.currentPlane][x][z + 1];

				@Pc(1021) LocType loc = LocType.get(locId);
				playerEntity.firstCycle = firstCycle + clientClock;
				playerEntity.lastCycle = lastCycle + clientClock;
				playerEntity.model = loc.getModel(locType, locRotation, region, regionEast, regionNorthEast, regionNorth, -1);

				@Pc(1045) int width = loc.width;
				@Pc(1048) int depth = loc.length;
				if (locRotation == 1 || locRotation == 3) {
					width = loc.length;
					depth = loc.width;
				}

				playerEntity.sceneX = x * 128 + width * 64;
				playerEntity.sceneZ = z * 128 + depth * 64;
				playerEntity.sceneY = this.getLandY(this.currentPlane, playerEntity.sceneX, playerEntity.sceneZ);

				@Pc(1094) byte temp;
				if (minX > maxX) {
					temp = minX;
					minX = maxX;
					maxX = temp;
				}
				if (minZ > maxZ) {
					temp = minZ;
					minZ = maxZ;
					maxZ = temp;
				}

				playerEntity.minTileX = x + minX;
				playerEntity.maxTileX = x + maxX;
				playerEntity.minTileZ = z + minZ;
				playerEntity.maxTileZ = z + maxZ;
			}
		} else if (opcode == ServerProt.OBJ_COUNT) {
			int objId = buffer.g2();
			int oldAmount = buffer.g2();
			int newAmount = buffer.g2();

			if (x >= 0 && z >= 0 && x < 104 && z < 104) {
				@Pc(1178) LinkedList stacks = this.objects[this.currentPlane][x][z];
				if (stacks != null) {
					for (@Pc(1184) ObjStackEntity objStack = (ObjStackEntity) stacks.peekPrevious(); objStack != null; objStack = (ObjStackEntity) stacks.getPrevious()) {
						if (objStack.id == (objId & 0x7FFF) && objStack.amount == oldAmount) {
							objStack.amount = newAmount;
							break;
						}
					}

					this.updateObjectStack(x, z);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(B)I")
	private int getTopLevel() {
		@Pc(11) int top = 3;

		if (this.cameraPitch < 310) {
			@Pc(20) int dstX = this.cameraX >> 7;
			@Pc(25) int dstZ = this.cameraZ >> 7;
			@Pc(31) int x = this.self.x >> 7;
			@Pc(37) int z = this.self.z >> 7;
			if ((this.levelRenderFlags[this.currentPlane][dstX][dstZ] & 0x4) != 0) {
				top = this.currentPlane;
			}

			@Pc(59) int dx;
			if (x > dstX) {
				dx = x - dstX;
			} else {
				dx = dstX - x;
			}

			@Pc(72) int dz;
			if (z > dstZ) {
				dz = z - dstZ;
			} else {
				dz = dstZ - z;
			}

			@Pc(87) int slope;
			@Pc(89) int error;
			if (dx > dz) {
				slope = dz * 65536 / dx;
				error = 32768;

				while (dstX != x) {
					if (dstX < x) {
						dstX++;
					} else if (dstX > x) {
						dstX--;
					}

					if ((this.levelRenderFlags[this.currentPlane][dstX][dstZ] & 0x4) != 0) {
						top = this.currentPlane;
					}

					error += slope;
					if (error >= 65536) {
						error -= 65536;

						if (dstZ < z) {
							dstZ++;
						} else if (dstZ > z) {
							dstZ--;
						}

						if ((this.levelRenderFlags[this.currentPlane][dstX][dstZ] & 0x4) != 0) {
							top = this.currentPlane;
						}
					}
				}
			} else {
				slope = dx * 65536 / dz;
				error = 32768;

				while (dstZ != z) {
					if (dstZ < z) {
						dstZ++;
					} else if (dstZ > z) {
						dstZ--;
					}

					if ((this.levelRenderFlags[this.currentPlane][dstX][dstZ] & 0x4) != 0) {
						top = this.currentPlane;
					}

					error += slope;
					if (error >= 65536) {
						error -= 65536;
						if (dstX < x) {
							dstX++;
						} else if (dstX > x) {
							dstX--;
						}

						if ((this.levelRenderFlags[this.currentPlane][dstX][dstZ] & 0x4) != 0) {
							top = this.currentPlane;
						}
					}
				}
			}
		}

		if ((this.levelRenderFlags[this.currentPlane][this.self.x >> 7][this.self.z >> 7] & 0x4) != 0) {
			top = this.currentPlane;
		}

		return top;
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(I)I")
	private int getCameraPlaneCutscene() {
		@Pc(9) int y = this.getLandY(this.currentPlane, this.cameraX, this.cameraZ);
		return y - this.cameraY >= 800 || (this.levelRenderFlags[this.currentPlane][this.cameraX >> 7][this.cameraZ >> 7] & 0x4) == 0 ? 3 : this.currentPlane;
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(I)V")
	private void drawViewport() {
		this.sceneCycle++;

		this.updateScenePlayers();
		this.updateSceneNpcs();
		this.updateSceneProjectiles();
		this.updateSceneSpotAnims();
		this.updateSceneSeqLocs();

		if (!this.cameraOriented) {
			int pitch = this.cameraOrbitPitch;
			if (this.cameraMaxY / 256 > pitch) {
				pitch = this.cameraMaxY / 256;
			}
			if (this.customCameraActive[4] && this.cameraAmplitude[4] + 128 > pitch) {
				pitch = this.cameraAmplitude[4] + 128;
			}
			int yaw = this.cameraYaw + this.cameraAnticheatAngle & 0x7FF;

			this.updateCameraOrbit(this.cameraOrbitX, this.getLandY(this.currentPlane, this.self.x, this.self.z) - 50, this.cameraOrbitZ, pitch, yaw, pitch * 3 + 600);

			drawCounter++;
			if (drawCounter > 1802) {
				drawCounter = 0;
				this.outBuffer.p1isaac(ClientProt.ANTICHEAT_DRAW);
				this.outBuffer.p1(0);
				int start = this.outBuffer.pos;
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
				this.outBuffer.psize1(this.outBuffer.pos - start);
			}
		}

		int oriented;
		if (this.cameraOriented) {
			oriented = this.getCameraPlaneCutscene();
		} else {
			oriented = this.getTopLevel();
		}

		int x = this.cameraX;
		int y = this.cameraY;
		@Pc(209) int z = this.cameraZ;
		@Pc(212) int pitch = this.cameraPitch;
		@Pc(215) int orbitYaw = this.cameraOrbitYaw;

		@Pc(264) int angle;
		for (@Pc(217) int cameraType = 0; cameraType < 5; cameraType++) {
			if (this.customCameraActive[cameraType]) {
				angle = (int) (Math.random() * (double) (this.cameraJitter[cameraType] * 2 + 1) - (double) this.cameraJitter[cameraType] + Math.sin((double) this.unknownCameraVariable[cameraType] * ((double) this.cameraFrequency[cameraType] / 100.0D)) * (double) this.cameraAmplitude[cameraType]);
				if (cameraType == 0) {
					this.cameraX += angle;
				} else if (cameraType == 1) {
					this.cameraY += angle;
				} else if (cameraType == 2) {
					this.cameraZ += angle;
				} else if (cameraType == 3) {
					this.cameraOrbitYaw = this.cameraOrbitYaw + angle & 0x7FF;
				} else if (cameraType == 4) {
					this.cameraPitch += angle;
					if (this.cameraPitch < 128) {
						this.cameraPitch = 128;
					}
					if (this.cameraPitch > 383) {
						this.cameraPitch = 383;
					}
				}
			}
		}

		angle = Draw3D.cycle;
		Model.allowInput = true;
		Model.resourceCount = 0;
		Model.cursorX = super.mouseX - 8;
		Model.cursorY = super.mouseY - 11;
		Draw2D.clear();
		this.mapSquare.draw(this.cameraOrbitYaw, this.cameraX, oriented, this.cameraPitch, this.cameraY, this.cameraZ, clientClock);
		this.mapSquare.clearFrameLocs();
		this.drawViewport2d();
		this.drawTileHint();
		this.updateAnimatedTextures(angle);
		this.drawViewport3d();
		this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);
		this.cameraX = x;
		this.cameraY = y;
		this.cameraZ = z;
		this.cameraPitch = pitch;
		this.cameraOrbitYaw = orbitYaw;
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(Z)V")
	private void runMidi() {
		this.startMidiThread = false;

		while (this.midiThreadActive) {
			try {
				Thread.sleep(50L);
			} catch (@Pc(11) Exception ignored) {
			}

			@Pc(19) String name;
			@Pc(22) int crc;
			@Pc(25) int len;
			synchronized (this.midiSync) {
				name = this.midiSyncName;
				crc = this.midiSyncCrc;
				len = this.midiSyncLen;
				this.midiSyncName = null;
				this.midiSyncCrc = 0;
				this.midiSyncLen = 0;
			}

			if (name != null) {
				@Pc(52) byte[] data = signlink.cacheload(name + ".mid");
				@Pc(69) int realCrc;
				if (data != null && crc != 12345678) {
					if (Buffer.crc32(data) != crc) {
						data = null;
					}
				}

				if (data == null) {
					try {
						@Pc(91) DataInputStream dis = this.openStream("songs/" + name /*+ "_" + local22*/ + ".mid");
						data = new byte[len];
						@Pc(106) int lastRead;
						for (@Pc(96) int off = 0; off < len; off += lastRead) {
							lastRead = dis.read(data, off, len - off);
							if (lastRead == -1) {
								@Pc(112) byte[] temp = new byte[off];
								System.arraycopy(data, 0, temp, 0, off);
								data = temp;
								len = off;
								break;
							}
						}
						dis.close();
						signlink.cachesave(name + ".mid", data);
					} catch (@Pc(153) Exception ex) {
						ex.printStackTrace();
					}
				}

				if (data == null) {
					return;
				}

				realCrc = (new Buffer(data)).g4();
				@Pc(167) byte[] realData = new byte[realCrc];
				BZip2InputStream.read(realData, realCrc, data, len, 4);
				this.midisave(realData, realCrc, true);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(Z)V")
	private void drawFlames() {
		if (this.flameCycle1 > 0) {
			for (int height = 0; height < 256; height++) {
				if (this.flameCycle1 > 768) {
					this.flameGradient[height] = this.mix(this.flameGradientRed[height], 1024 - this.flameCycle1, this.flameGradientGreen[height]);
				} else if (this.flameCycle1 > 256) {
					this.flameGradient[height] = this.flameGradientGreen[height];
				} else {
					this.flameGradient[height] = this.mix(this.flameGradientGreen[height], 256 - this.flameCycle1, this.flameGradientRed[height]);
				}
			}
		} else if (this.flameCycle2 > 0) {
			for (int height = 0; height < 256; height++) {
				if (this.flameCycle2 > 768) {
					this.flameGradient[height] = this.mix(this.flameGradientRed[height], 1024 - this.flameCycle2, this.flameGradientBlue[height]);
				} else if (this.flameCycle2 > 256) {
					this.flameGradient[height] = this.flameGradientBlue[height];
				} else {
					this.flameGradient[height] = this.mix(this.flameGradientBlue[height], 256 - this.flameCycle2, this.flameGradientRed[height]);
				}
			}
		} else {
			System.arraycopy(this.flameGradientRed, 0,this. flameGradient, 0, 256);
		}

		System.arraycopy(this.imageFlamesLeft.pixels, 0, this.titleLeft.pixels, 0, 128 * 265);

		@Pc(181) int srcOffset = 0;
		@Pc(183) int dstOffset = 1152;

		for (@Pc(185) int y = 1; y < 255; y++) {
			int offset = this.flameShiftX[y] * (256 - y) / 256;

			int step = offset + 22;
			if (step < 0) {
				step = 0;
			}

			srcOffset += step;

			for (int x = step; x < 128; x++) {
				int value = this.flameIntensity[srcOffset++];
				if (value == 0) {
					dstOffset++;
				} else {
					int alpha = value;
					int invAlpha = 256 - value;
					value = this.flameGradient[value];
					int background = this.titleLeft.pixels[dstOffset];
					this.titleLeft.pixels[dstOffset++] = ((value & 0xFF00FF) * alpha + (background & 0xFF00FF) * invAlpha & 0xFF00FF00) + ((value & 0xFF00) * alpha + (background & 0xFF00) * invAlpha & 0xFF0000) >> 8;
				}
			}

			dstOffset += step;
		}

		this.titleLeft.drawAt(0, 0, super.graphic);
		System.arraycopy(this.imageFlamesRight.pixels, 0, this.titleRight.pixels, 0, 128 * 265);

		srcOffset = 0;
		dstOffset = 1176;

		for (int y = 1; y < 255; y++) {
			int offset = this.flameShiftX[y] * (256 - y) / 256;
			int step = 103 - offset;

			dstOffset += offset;

			for (int i = 0; i < step; i++) {
				int value = this.flameIntensity[srcOffset++];

				if (value == 0) {
					dstOffset++;
				} else {
					int alpha = value;
					int invAlpha = 256 - value;
					value = this.flameGradient[value];
					int background = this.titleRight.pixels[dstOffset];
					this.titleRight.pixels[dstOffset++] = ((value & 0xFF00FF) * alpha + (background & 0xFF00FF) * invAlpha & 0xFF00FF00) + ((value & 0xFF00) * alpha + (background & 0xFF00) * invAlpha & 0xFF0000) >> 8;
				}
			}

			srcOffset += 128 - step;
			dstOffset += 128 - step - offset;
		}

		this.titleRight.drawAt(661, 0, super.graphic);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIILclient!hc;III)V")
	private void updateInterface(@OriginalArg(3) Component parent, @OriginalArg(1) int mx, @OriginalArg(0) int my, @OriginalArg(5) int px, @OriginalArg(2) int py, @OriginalArg(6) int scrollY) {
		if (parent.type != 0 || parent.children == null || parent.hidden) {
			return;
		}

		if (mx < px || my < py || mx > px + parent.width || my > py + parent.height) {
			return;
		}

		@Pc(34) int childCount = parent.children.length;

		for (@Pc(44) int childIndex = 0; childIndex < childCount; childIndex++) {
			@Pc(53) int cx = parent.childX[childIndex] + px;
			@Pc(62) int cy = parent.childY[childIndex] + py - scrollY;
			@Pc(69) Component child = Component.instances[parent.children[childIndex]];

			cx += child.x;
			cy += child.y;

			if ((child.hoverParentIndex >= 0 || child.hoverColor != 0) && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
				if (child.hoverParentIndex >= 0) {
					this.hoveredInterfaceIndex = child.hoverParentIndex;
				} else {
					this.hoveredInterfaceIndex = child.id;
				}
			}

			if (child.type == Component.TYPE_PARENT) {
				this.updateInterface(child, mx, my, cx, cy, child.scrollY);
				if (child.scrollHeight > child.height) {
					this.updateInterfaceScrollbar(mx, my, child.scrollHeight, child.height, true, cx + child.width, cy, child);
				}
			} else {
				if (child.buttonType == Component.BUTTON && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					@Pc(176) boolean flag = false;
					if (child.clientCode != 0) {
						flag = this.updateInterfaceTooltip(child);
					}
					if (!flag) {
						this.options[this.optionCount] = child.option;
						this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON;
						this.optionParamC[this.optionCount] = child.id;
						this.optionCount++;
					}
				}

				if (child.buttonType == Component.TARGET_BUTTON && this.selectedSpell == 0 && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					@Pc(240) String circumfix = child.optionCircumfix;
					if (circumfix.contains(" ")) {
						circumfix = circumfix.substring(0, circumfix.indexOf(" "));
					}
					this.options[this.optionCount] = circumfix + " @gre@" + child.optionSuffix;
					this.optionType[this.optionCount] = Cs1Actions.IF_TARGETBUTTON;
					this.optionParamC[this.optionCount] = child.id;
					this.optionCount++;
				}

				if (child.buttonType == Component.CLOSE_BUTTON && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					this.options[this.optionCount] = "Close";
					this.optionType[this.optionCount] = Cs1Actions.IF_CLOSEBUTTON;
					this.optionParamC[this.optionCount] = child.id;
					this.optionCount++;
				}

				if (child.buttonType == Component.TOGGLE_BUTTON && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					this.options[this.optionCount] = child.option;
					this.optionType[this.optionCount] = Cs1Actions.IF_TOGGLEBUTTON;
					this.optionParamC[this.optionCount] = child.id;
					this.optionCount++;
				}

				if (child.buttonType == Component.SELECT_BUTTON && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					this.options[this.optionCount] = child.option;
					this.optionType[this.optionCount] = Cs1Actions.IF_SELECTBUTTON;
					this.optionParamC[this.optionCount] = child.id;
					this.optionCount++;
				}

				if (child.buttonType == Component.PAUSE_BUTTON && !this.chatContinuingDialogue && mx >= cx && my >= cy && mx < cx + child.width && my < cy + child.height) {
					this.options[this.optionCount] = child.option;
					this.optionType[this.optionCount] = Cs1Actions.IF_PAUSEBUTTON;
					this.optionParamC[this.optionCount] = child.id;
					this.optionCount++;
				}

				if (child.type == Component.TYPE_INVENTORY) {
					@Pc(488) int slot = 0;
					for (@Pc(490) int slotY = 0; slotY < child.height; slotY++) {
						for (@Pc(494) int slotX = 0; slotX < child.width; slotX++) {
							@Pc(505) int x0 = cx + slotX * (child.inventoryMarginX + 32);
							@Pc(514) int y0 = cy + slotY * (child.inventoryMarginY + 32);
							if (slot < 20) {
								x0 += child.inventoryOffsetX[slot];
								y0 += child.inventoryOffsetY[slot];
							}
							if (mx >= x0 && my >= y0 && mx < x0 + 32 && my < y0 + 32) {
								this.hoveredSlot = slot;
								this.hoveredSlotParentId = child.id;

								if (child.inventoryIndices[slot] > 0) {
									@Pc(567) ObjType objType = ObjType.get(child.inventoryIndices[slot] - 1);
									if (this.selectedObject == 1 && child.inventoryHasOptions) {
										if (child.id != this.selectedObjInterface || slot != this.selectedObjSlot) {
											this.options[this.optionCount] = "Use " + this.selectedObjName + " with @lre@" + objType.name;
											this.optionType[this.optionCount] = Cs1Actions.OPHELDU;
											this.optionParamB[this.optionCount] = slot;
											this.optionParamC[this.optionCount] = child.id;
											this.optionParamA[this.optionCount] = objType.id;
											this.optionCount++;
										}
									} else if (this.selectedSpell != 1 || !child.inventoryHasOptions) {
										@Pc(704) int option;
										if (child.inventoryHasOptions) {
											for (option = 4; option >= 3; option--) {
												if (objType.iop != null && objType.iop[option] != null) {
													this.options[this.optionCount] = objType.iop[option] + " @lre@" + objType.name;
													if (option == 3) {
														this.optionType[this.optionCount] = Cs1Actions.OPHELD4;
													}
													if (option == 4) {
														this.optionType[this.optionCount] = Cs1Actions.OPHELD5;
													}
													this.optionParamB[this.optionCount] = slot;
													this.optionParamC[this.optionCount] = child.id;
													this.optionParamA[this.optionCount] = objType.id;
													this.optionCount++;
												} else if (option == 4) {
													this.options[this.optionCount] = "Drop @lre@" + objType.name;
													this.optionType[this.optionCount] = Cs1Actions.OPHELD5;
													this.optionParamB[this.optionCount] = slot;
													this.optionParamC[this.optionCount] = child.id;
													this.optionParamA[this.optionCount] = objType.id;
													this.optionCount++;
												}
											}
										}

										if (child.inventoryIsUsable) {
											this.options[this.optionCount] = "Use @lre@" + objType.name;
											this.optionType[this.optionCount] = Cs1Actions.IF_BUTTONT;
											this.optionParamB[this.optionCount] = slot;
											this.optionParamC[this.optionCount] = child.id;
											this.optionParamA[this.optionCount] = objType.id;
											this.optionCount++;
										}

										if (child.inventoryHasOptions && objType.iop != null) {
											for (option = 2; option >= 0; option--) {
												if (objType.iop[option] != null) {
													this.options[this.optionCount] = objType.iop[option] + " @lre@" + objType.name;
													if (option == 0) {
														this.optionType[this.optionCount] = Cs1Actions.OPHELD1;
													}
													if (option == 1) {
														this.optionType[this.optionCount] = Cs1Actions.OPHELD2;
													}
													if (option == 2) {
														this.optionType[this.optionCount] = Cs1Actions.OPHELD3;
													}
													this.optionParamB[this.optionCount] = slot;
													this.optionParamC[this.optionCount] = child.id;
													this.optionParamA[this.optionCount] = objType.id;
													this.optionCount++;
												}
											}
										}

										if (child.inventoryOptions != null) {
											for (option = 4; option >= 0; option--) {
												if (child.inventoryOptions[option] != null) {
													this.options[this.optionCount] = child.inventoryOptions[option] + " @lre@" + objType.name;
													if (option == 0) {
														this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON1;
													}
													if (option == 1) {
														this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON2;
													}
													if (option == 2) {
														this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON3;
													}
													if (option == 3) {
														this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON4;
													}
													if (option == 4) {
														this.optionType[this.optionCount] = Cs1Actions.IF_BUTTON5;
													}
													this.optionParamB[this.optionCount] = slot;
													this.optionParamC[this.optionCount] = child.id;
													this.optionParamA[this.optionCount] = objType.id;
													this.optionCount++;
												}
											}
										}
										this.options[this.optionCount] = "Examine @lre@" + objType.name;
										this.optionType[this.optionCount] = Cs1Actions.EXAMINE_OBJ;
										this.optionParamC[this.optionCount] = child.inventoryAmount[slot];
										this.optionParamA[this.optionCount] = objType.id;
										this.optionCount++;
									} else if ((this.selectedFlags & 0x10) == 16) {
										this.options[this.optionCount] = this.selectedSpellPrefix + " @lre@" + objType.name;
										this.optionType[this.optionCount] = Cs1Actions.OPHELDT;
										this.optionParamB[this.optionCount] = slot;
										this.optionParamC[this.optionCount] = child.id;
										this.optionParamA[this.optionCount] = objType.id;
										this.optionCount++;
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

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(I)V")
	private void updateChatSettingInput() {
		if (super.mouseButton != 1) {
			return;
		}

		if (super.clickX >= 8 && super.clickX <= 108 && super.clickY >= 490 && super.clickY <= 522) {
			this.chatPublicSetting = (this.chatPublicSetting + 1) % 4;
			this.chatRedrawSettings = true;
			this.redrawChatback = true;
			this.outBuffer.p1isaac(ClientProt.CHAT_SETMODE);
			this.outBuffer.p1(this.chatPublicSetting);
			this.outBuffer.p1(this.chatPrivateSetting);
			this.outBuffer.p1(this.chatTradeDuelSetting);
		}

		if (super.clickX >= 137 && super.clickX <= 237 && super.clickY >= 490 && super.clickY <= 522) {
			this.chatPrivateSetting = (this.chatPrivateSetting + 1) % 3;
			this.chatRedrawSettings = true;
			this.redrawChatback = true;
			this.outBuffer.p1isaac(ClientProt.CHAT_SETMODE);
			this.outBuffer.p1(this.chatPublicSetting);
			this.outBuffer.p1(this.chatPrivateSetting);
			this.outBuffer.p1(this.chatTradeDuelSetting);
		}

		if (super.clickX >= 275 && super.clickX <= 375 && super.clickY >= 490 && super.clickY <= 522) {
			this.chatTradeDuelSetting = (this.chatTradeDuelSetting + 1) % 3;
			this.chatRedrawSettings = true;
			this.redrawChatback = true;
			this.outBuffer.p1isaac(ClientProt.CHAT_SETMODE);
			this.outBuffer.p1(this.chatPublicSetting);
			this.outBuffer.p1(this.chatPrivateSetting);
			this.outBuffer.p1(this.chatTradeDuelSetting);
		}

		if (super.clickX < 416 || super.clickX > 516 || super.clickY < 490 || super.clickY > 522) {
			return;
		}

		this.closeInterface();
		this.reportInput = "";
		this.reportAbuseMuteToggle = false;

		for (@Pc(186) int opened = 0; opened < Component.instances.length; opened++) {
			if (Component.instances[opened] != null && Component.instances[opened].clientCode == 600) {
				this.openInterfaceId = this.viewportInterfaceIndex = Component.instances[opened].parent;
				return;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(III)V")
	private void updatePlayerTooltip(@OriginalArg(0) int y, @OriginalArg(2) int x) {
		@Pc(1) int menuSize = 0;

		for (@Pc(3) int i = 0; i < 100; i++) {
			if (this.chatMessage[i] != null) {
				@Pc(15) int type = this.chatMessageType[i];
				@Pc(26) int scroll = this.chatScrollAmount + 70 + 4 - menuSize * 14;
				if (scroll < -20) {
					break;
				}
				if (type == 0) {
					menuSize++;
				}
				if ((type == 1 || type == 2) && (type == 1 || this.chatPublicSetting == 0 || this.chatPublicSetting == 1 && this.isFriend(this.chatMessagePrefix[i]))) {
					if (y > scroll - 14 && y <= scroll && !this.chatMessagePrefix[i].equals(this.self.name)) {
						if (this.rights) {
							this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[i];
							this.optionType[this.optionCount] = Cs1Actions.REPORT_ABUSE;
							this.optionCount++;
						}
						this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.ADD_IGNORE;
						this.optionCount++;
						this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.ADD_FRIEND;
						this.optionCount++;
					}
					menuSize++;
				}
				if ((type == 3 || type == 7) && this.splitPrivateChat == 0 && (type == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[i]))) {
					if (y > scroll - 14 && y <= scroll) {
						if (this.rights) {
							this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[i];
							this.optionType[this.optionCount] = Cs1Actions.REPORT_ABUSE;
							this.optionCount++;
						}
						this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.ADD_IGNORE;
						this.optionCount++;
						this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.ADD_FRIEND;
						this.optionCount++;
					}
					menuSize++;
				}
				if (type == 4 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[i]))) {
					if (y > scroll - 14 && y <= scroll) {
						this.options[this.optionCount] = "Accept trade @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.TRADE_PLAYER;
						this.optionCount++;
					}
					menuSize++;
				}
				if ((type == 5 || type == 6) && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
					menuSize++;
				}
				if (type == 8 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[i]))) {
					if (y > scroll - 14 && y <= scroll) {
						this.options[this.optionCount] = "Accept duel @whi@" + this.chatMessagePrefix[i];
						this.optionType[this.optionCount] = Cs1Actions.DUEL_PLAYER;
						this.optionCount++;
					}
					menuSize++;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(I)V")
	private void updateScenePlayers() {
		if (this.self.x >> 7 == this.flagTileX && this.self.z >> 7 == this.flagTileY) {
			this.flagTileX = 0;
		}

		for (@Pc(22) int i = -1; i < this.playerCount; i++) {
			@Pc(30) PlayerEntity p;

			@Pc(35) int pid;
			if (i == -1) {
				p = this.self;
				pid = this.LOCAL_PLAYER_INDEX << 14;
			} else {
				p = this.playerEntities[this.playerIndices[i]];
				pid = this.playerIndices[i] << 14;
			}

			if (p == null || !p.isValid()) {
				continue;
			}

			p.lowMemory = false;
			if ((lowMemory && this.playerCount > 50 || this.playerCount > 200) && i != -1 && p.secondarySeq == p.standSeq) {
				p.lowMemory = true;
			}

			@Pc(87) int stx = p.x >> 7;
			@Pc(92) int stz = p.z >> 7;
			if (stx >= 0 && stx < 104 && stz >= 0 && stz < 104) {
				if (p.model == null || clientClock < p.firstCycle || clientClock >= p.lastCycle) {
					if ((p.x & 0x7F) == 64 && (p.z & 0x7F) == 64) {
						if (this.tileRenderCount[stx][stz] == this.sceneCycle) {
							continue;
						}
						this.tileRenderCount[stx][stz] = this.sceneCycle;
					}
					p.plane = this.getLandY(this.currentPlane, p.x, p.z);
					this.mapSquare.add(p.z, 60, p.animationDelay, p.x, pid, p.animationStretches, null, p, p.plane, this.currentPlane);
				} else {
					p.lowMemory = false;
					p.plane = this.getLandY(this.currentPlane, p.x, p.z);
					this.mapSquare.add(p.maxTileX, null, p.z, p.plane, pid, p.animationDelay, p.minTileZ, p.minTileX, p, this.currentPlane, p.maxTileZ, p.x);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIBI)I")
	private int getLandY(@OriginalArg(0) int level, @OriginalArg(1) int sceneX, @OriginalArg(3) int sceneZ) {
		@Pc(11) int tileX = sceneX >> 7;
		@Pc(15) int tileZ = sceneZ >> 7;

		if (level < 3 && (this.levelRenderFlags[1][tileX][tileZ] & 0x2) == 2) {
			level++;
		}

		@Pc(37) int tileLocalX = sceneX & 0x7F;
		@Pc(41) int tileLocalZ = sceneZ & 0x7F;

		@Pc(69) int southY = this.levelHeightMaps[level][tileX][tileZ] * (128 - tileLocalX) + this.levelHeightMaps[level][tileX + 1][tileZ] * tileLocalX >> 7;
		@Pc(101) int northY = this.levelHeightMaps[level][tileX][tileZ + 1] * (128 - tileLocalX) + this.levelHeightMaps[level][tileX + 1][tileZ + 1] * tileLocalX >> 7;
		return southY * (128 - tileLocalZ) + northY * tileLocalZ >> 7;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!bc;IIII)V")
	private void drawTooltip(@OriginalArg(0) NpcType npc, @OriginalArg(3) int a, @OriginalArg(2) int b, @OriginalArg(4) int c) {
		if (this.optionCount >= 400) {
			return;
		}

		@Pc(16) String name = npc.name;
		if (npc.level != 0) {
			name = name + getLevelColorTag(this.self.combatLevel, npc.level) + " (level-" + npc.level + ")";
		}

		if (this.selectedObject == 1) {
			this.options[this.optionCount] = "Use " + this.selectedObjName + " with @yel@" + name;
			this.optionType[this.optionCount] = Cs1Actions.OPNPCU;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;
		} else if (this.selectedSpell != 1) {
			@Pc(155) int option;
			if (npc.op != null) {
				for (option = 4; option >= 0; option--) {
					if (npc.op[option] != null && !npc.op[option].equalsIgnoreCase("attack")) {
						this.options[this.optionCount] = npc.op[option] + " @yel@" + name;
						if (option == 0) {
							this.optionType[this.optionCount] = Cs1Actions.OPNPC1;
						}
						if (option == 1) {
							this.optionType[this.optionCount] = Cs1Actions.OPNPC2;
						}
						if (option == 2) {
							this.optionType[this.optionCount] = Cs1Actions.OPNPC3;
						}
						if (option == 3) {
							this.optionType[this.optionCount] = Cs1Actions.OPNPC4;
						}
						if (option == 4) {
							this.optionType[this.optionCount] = Cs1Actions.OPNPC5;
						}
						this.optionParamB[this.optionCount] = a;
						this.optionParamC[this.optionCount] = b;
						this.optionParamA[this.optionCount] = c;
						this.optionCount++;
					}
				}
			}

			if (npc.op != null) {
				for (option = 4; option >= 0; option--) {
					if (npc.op[option] != null && npc.op[option].equalsIgnoreCase("attack")) {
						@Pc(279) short local279 = 0;
						if (npc.level > this.self.combatLevel) {
							local279 = 2000;
						}
						this.options[this.optionCount] = npc.op[option] + " @yel@" + name;
						if (option == 0) {
							this.optionType[this.optionCount] = local279 + Cs1Actions.OPNPC1;
						}
						if (option == 1) {
							this.optionType[this.optionCount] = local279 + Cs1Actions.OPNPC2;
						}
						if (option == 2) {
							this.optionType[this.optionCount] = local279 + Cs1Actions.OPNPC3;
						}
						if (option == 3) {
							this.optionType[this.optionCount] = local279 + Cs1Actions.OPNPC4;
						}
						if (option == 4) {
							this.optionType[this.optionCount] = local279 + Cs1Actions.OPNPC5;
						}
						this.optionParamB[this.optionCount] = a;
						this.optionParamC[this.optionCount] = b;
						this.optionParamA[this.optionCount] = c;
						this.optionCount++;
					}
				}
			}
			this.options[this.optionCount] = "Examine @yel@" + name;
			this.optionType[this.optionCount] = Cs1Actions.EXAMINE_NPC;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;
		} else if ((this.selectedFlags & 0x2) == 2) {
			this.options[this.optionCount] = this.selectedSpellPrefix + " @yel@" + name;
			this.optionType[this.optionCount] = Cs1Actions.OPNPCT;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;
		}
	}

	@OriginalMember(owner = "client!client", name = "l", descriptor = "(I)V")
	private void updateKeyboardInput() {
		while (true) {
			@Pc(13) int key = this.pollKey();
			if (key == -1) {
				return;
			}

			if (this.viewportInterfaceIndex != -1 && this.viewportInterfaceIndex == this.openInterfaceId) {
				if (key == 8 && this.reportInput.length() > 0) {
					this.reportInput = this.reportInput.substring(0, this.reportInput.length() - 1);
				}
				if ((key >= 97 && key <= 122 || key >= 65 && key <= 90 || key >= 48 && key <= 57 || key == 32) && this.reportInput.length() < 12) {
					this.reportInput = this.reportInput + (char) key;
				}
			} else {
				@Pc(193) int start;
				if (this.showSocialInput) {
					if (key >= 32 && key <= 122 && this.socialInput.length() < 80) {
						this.socialInput = this.socialInput + (char) key;
						this.redrawChatback = true;
					}
					if (key == 8 && this.socialInput.length() > 0) {
						this.socialInput = this.socialInput.substring(0, this.socialInput.length() - 1);
						this.redrawChatback = true;
					}
					if (key == 13 || key == 10) {
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
							this.outBuffer.p1isaac(ClientProt.MESSAGE_PRIVATE);
							this.outBuffer.p1(0);
							start = this.outBuffer.pos;
							this.outBuffer.p8(this.socialName37);
							TextEncoder.write(this.outBuffer, this.socialInput);
							this.outBuffer.psize1(this.outBuffer.pos - start);
							this.socialInput = StringUtils.toSentenceCase(this.socialInput);
							this.socialInput = WordPack.getFiltered(this.socialInput);
							this.addMessage(6, StringUtils.formatName(StringUtils.fromBase37(this.socialName37)), this.socialInput);
							if (this.chatPrivateSetting == 2) {
								this.chatPrivateSetting = 1;
								this.chatRedrawSettings = true;
								this.outBuffer.p1isaac(ClientProt.CHAT_SETMODE);
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
				} else if (this.chatbackInputType) {
					if (key >= 48 && key <= 57 && this.chatbackInput.length() < 10) {
						this.chatbackInput = this.chatbackInput + (char) key;
						this.redrawChatback = true;
					}
					if (key == 8 && this.chatbackInput.length() > 0) {
						this.chatbackInput = this.chatbackInput.substring(0, this.chatbackInput.length() - 1);
						this.redrawChatback = true;
					}
					if (key == 13 || key == 10) {
						if (this.chatbackInput.length() > 0) {
							start = 0;
							try {
								start = Integer.parseInt(this.chatbackInput);
							} catch (@Pc(369) Exception ignored) {
							}
							this.outBuffer.p1isaac(ClientProt.RESUME_P_COUNTDIALOG);
							this.outBuffer.p4(start);
						}
						this.chatbackInputType = false;
						this.redrawChatback = true;
					}
				} else if (this.chatbackComponentId == -1) {
					if (key >= 32 && key <= 122 && this.input.length() < 80) {
						this.input = this.input + (char) key;
						this.redrawChatback = true;
					}
					if (key == 8 && this.input.length() > 0) {
						this.input = this.input.substring(0, this.input.length() - 1);
						this.redrawChatback = true;
					}
					if ((key == 13 || key == 10) && this.input.length() > 0) {
						if (this.input.equals("::clientdrop") && (super.frame != null || this.getHost().contains("192.168.1."))) {
							this.reconnect();
						} else if (this.input.startsWith("::")) {
							String[] args = this.input.split("\\s+");
							if (this.input.startsWith("::tileh")) {
								if (args.length > 4) {
									for (int x = 0; x < Integer.parseInt(args[3]); ++x) {
										for (int z = 0; z < Integer.parseInt(args[4]); ++z) {
											this.levelHeightMaps[Integer.parseInt(args[1])][(self.x / 128) + x][(self.z / 128) + z] = Integer.parseInt(args[2]);
										}
									}
								} else if (args.length > 2) {
									this.addMessage(0, "", "tileh: " + (self.x / 128) + " " + (self.z / 128) + " " + Integer.parseInt(args[1]) + " is now " + this.levelHeightMaps[Integer.parseInt(args[1])][(self.x / 128)][(self.z / 128)]);
								}
							} else if (this.input.startsWith("::gtileh")) {
								if (args.length > 1) {
									this.addMessage(0, "", "gtileh: " + (self.x / 128) + " " + (self.z / 128) + " " + Integer.parseInt(args[1]) + " is " + this.levelHeightMaps[Integer.parseInt(args[1])][(self.x / 128)][(self.z / 128)]);
								}
							} else if (this.input.equalsIgnoreCase("::reloc")) {
								createScene(true);
							} else if (this.input.equalsIgnoreCase("::save")) {
								try {
									this.areaViewport.save("screenshots/viewport" + Instant.now().toEpochMilli() + ".png");
								} catch (Exception ex) {}
							} else if (this.input.equalsIgnoreCase("::debug")) {
								drawDebug = !drawDebug;
							}

							this.outBuffer.p1isaac(ClientProt.CLIENT_CHEAT);
							this.outBuffer.p1(this.input.length() - 1);
							this.outBuffer.pjstr(this.input.substring(2));
						} else {
							@Pc(496) byte color = 0;
							if (this.input.startsWith("yellow:")) {
								color = 0;
								this.input = this.input.substring(7);
							}
							if (this.input.startsWith("red:")) {
								color = 1;
								this.input = this.input.substring(4);
							}
							if (this.input.startsWith("green:")) {
								color = 2;
								this.input = this.input.substring(6);
							}
							if (this.input.startsWith("cyan:")) {
								color = 3;
								this.input = this.input.substring(5);
							}
							if (this.input.startsWith("purple:")) {
								color = 4;
								this.input = this.input.substring(7);
							}
							if (this.input.startsWith("white:")) {
								color = 5;
								this.input = this.input.substring(6);
							}
							if (this.input.startsWith("flash1:")) {
								color = 6;
								this.input = this.input.substring(7);
							}
							if (this.input.startsWith("flash2:")) {
								color = 7;
								this.input = this.input.substring(7);
							}
							if (this.input.startsWith("flash3:")) {
								color = 8;
								this.input = this.input.substring(7);
							}
							if (this.input.startsWith("glow1:")) {
								color = 9;
								this.input = this.input.substring(6);
							}
							if (this.input.startsWith("glow2:")) {
								color = 10;
								this.input = this.input.substring(6);
							}
							if (this.input.startsWith("glow3:")) {
								color = 11;
								this.input = this.input.substring(6);
							}
							@Pc(654) byte effect = 0;
							if (this.input.startsWith("wave:")) {
								effect = 1;
								this.input = this.input.substring(5);
							}
							if (this.input.startsWith("scroll:")) {
								effect = 2;
								this.input = this.input.substring(7);
							}
							this.outBuffer.p1isaac(ClientProt.MESSAGE_PUBLIC);
							this.outBuffer.p1(0);
							@Pc(693) int bufStart = this.outBuffer.pos;
							this.outBuffer.p1(color);
							this.outBuffer.p1(effect);
							TextEncoder.write(this.outBuffer, this.input);
							this.outBuffer.psize1(this.outBuffer.pos - bufStart);
							this.input = StringUtils.toSentenceCase(this.input);
							this.input = WordPack.getFiltered(this.input);
							this.self.spoken = this.input;
							this.self.spokenColor = color;
							this.self.spokenEffect = effect;
							this.self.textCycle = 150;
							this.addMessage(2, this.self.name, this.self.spoken);
							if (this.chatPublicSetting == 2) {
								this.chatPublicSetting = 3;
								this.chatRedrawSettings = true;
								this.outBuffer.p1isaac(ClientProt.CHAT_SETMODE);
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
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Z)V")
	@Override
	protected final void draw() {
		if (this.errorStarted || this.errorLoading || this.errorHost) {
			this.drawErrorScreen();
			return;
		}
		if (this.ingame) {
			this.drawGame();
		} else {
			this.drawTitleScreen();
		}
		this.dragCycle = 0;
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(B)V")
	private void updateTitle() {
		@Pc(17) int x;
		@Pc(24) int y;

		if (this.titleState == 0) {
			x = super.gameWidth / 2 - 80;
			y = super.gameHeight / 2 + 20;

			y += 20;
			if (super.mouseButton == 1 && super.clickX >= x - 75 && super.clickX <= x + 75 && super.clickY >= y - 20 && super.clickY <= y + 20) {
				this.titleState = 3;
				this.loginFocusedLine = 0;
			}

			x = super.gameWidth / 2 + 80;
			if (super.mouseButton == 1 && super.clickX >= x - 75 && super.clickX <= x + 75 && super.clickY >= y - 20 && super.clickY <= y + 20) {
				this.loginMessage0 = "";
				this.loginMessage1 = "Enter your username & password.";
				this.titleState = 2;
				this.loginFocusedLine = 0;
			}
		} else if (this.titleState == 2) {
			x = super.gameHeight / 2 - 40;

			x += 55;
			if (super.mouseButton == 1 && super.clickY >= x - 15 && super.clickY < x) {
				this.loginFocusedLine = 0;
			}

			x += 15;
			if (super.mouseButton == 1 && super.clickY >= x - 15 && super.clickY < x) {
				this.loginFocusedLine = 1;
			}

			y = super.gameWidth / 2 - 80;
			@Pc(170) int bottomY = super.gameHeight / 2 + 50 + 20;
			if (super.mouseButton == 1 && super.clickX >= y - 75 && super.clickX <= y + 75 && super.clickY >= bottomY - 20 && super.clickY <= bottomY + 20) {
				this.login(this.username, this.password, false);
			}

			y = super.gameWidth / 2 + 80;
			if (super.mouseButton == 1 && super.clickX >= y - 75 && super.clickX <= y + 75 && super.clickY >= bottomY - 20 && super.clickY <= bottomY + 20) {
				this.titleState = 0;
				this.username = "";
				this.password = "";
			}

			while (true) {
				@Pc(254) int c = this.pollKey();
				if (c == -1) {
					return;
				}

				@Pc(259) boolean isAscii = false;
				for (@Pc(261) int i = 0; i < ASCII_CHARSET.length(); i++) {
					if (c == ASCII_CHARSET.charAt(i)) {
						isAscii = true;
						break;
					}
				}
				if (this.loginFocusedLine == 0) {
					if (c == 8 && this.username.length() > 0) {
						this.username = this.username.substring(0, this.username.length() - 1);
					}
					if (c == 9 || c == 10 || c == 13) {
						this.loginFocusedLine = 1;
					}
					if (isAscii) {
						this.username = this.username + (char) c;
					}
					if (this.username.length() > 12) {
						this.username = this.username.substring(0, 12);
					}
				} else if (this.loginFocusedLine == 1) {
					if (c == 8 && this.password.length() > 0) {
						this.password = this.password.substring(0, this.password.length() - 1);
					}
					if (c == 9 || c == 10 || c == 13) {
						this.loginFocusedLine = 0;
					}
					if (isAscii) {
						this.password = this.password + (char) c;
					}
					if (this.password.length() > 20) {
						this.password = this.password.substring(0, 20);
					}
				}
			}
		} else if (this.titleState == 3) {
			x = super.gameWidth / 2;
			y = super.gameHeight / 2 + 50 + 20;
			if (super.mouseButton == 1 && super.clickX >= x - 75 && super.clickX <= x + 75 && super.clickY >= y - 20 && super.clickY <= y + 20) {
				this.titleState = 0;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;ILjava/lang/String;II)Lclient!ub;")
	private FileArchive loadArchive(@OriginalArg(0) String displayName, @OriginalArg(1) int expectedCrc, @OriginalArg(2) String archiveName, @OriginalArg(3) int progress) {
		@Pc(3) int nextSecs = 5;
		@Pc(6) byte[] data = signlink.cacheload(archiveName);

		@Pc(20) int temp;
		if (data != null) {
			if (Buffer.crc32(data) != expectedCrc) {
				data = null;
			}
		}
		if (data != null) {
			return new FileArchive(data);
		}

		while (data == null) {
			this.showProgress("Requesting " + displayName, progress);
			try {
				temp = 0;
				@Pc(60) DataInputStream local60 = this.openStream(archiveName + expectedCrc);
				@Pc(63) byte[] local63 = new byte[6];
				local60.readFully(local63, 0, 6);
				@Pc(74) Buffer local74 = new Buffer(local63);
				local74.pos = 3;
				@Pc(82) int local82 = local74.g3() + 6;
				@Pc(84) int local84 = 6;
				data = new byte[local82];
				System.arraycopy(local63, 0, data, 0, 6);
				while (local84 < local82) {
					@Pc(107) int local107 = local82 - local84;
					if (local107 > 1000) {
						local107 = 1000;
					}
					local84 += local60.read(data, local84, local107);
					@Pc(126) int local126 = local84 * 100 / local82;
					if (local126 != temp) {
						this.showProgress("Loading " + displayName + " - " + local126 + "%", progress);
					}
					temp = local126;
				}
				local60.close();
			} catch (@Pc(155) IOException local155) {
				data = null;
				for (temp = nextSecs; temp > 0; temp--) {
					this.showProgress("Error loading - Will retry in " + temp + " secs.", progress);
					try {
						Thread.sleep(1000L);
					} catch (@Pc(178) Exception local178) {
					}
				}
				nextSecs *= 2;
				if (nextSecs > 60) {
					nextSecs = 60;
				}
			}
		}
		signlink.cachesave(archiveName, data);
		return new FileArchive(data);
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
	private void updateCameraOrbit(@OriginalArg(1) int x, @OriginalArg(0) int y, @OriginalArg(5) int z, @OriginalArg(3) int cameraPitch, @OriginalArg(2) int cameraYaw, @OriginalArg(6) int distance) {
		@Pc(5) int pitch = 2048 - cameraPitch & 0x7FF;
		@Pc(11) int yaw = 2048 - cameraYaw & 0x7FF;

		@Pc(13) int offsetX = 0;
		@Pc(15) int offsetY = 0;
		@Pc(17) int offsetZ = distance;

		@Pc(23) int sin;
		@Pc(27) int cos;

		if (pitch != 0) {
			sin = Model.sin[pitch];
			cos = Model.cos[pitch];
			int temp = offsetY * cos - offsetZ * sin >> 16;
			offsetZ = offsetY * sin + offsetZ * cos >> 16;
			offsetY = temp;
		}

		if (yaw != 0) {
			sin = Model.sin[yaw];
			cos = Model.cos[yaw];
			int temp = offsetZ * sin + offsetX * cos >> 16;
			offsetZ = offsetZ * cos - offsetX * sin >> 16;
			offsetX = temp;
		}

		this.cameraX = x - offsetX;
		this.cameraY = y - offsetY;
		this.cameraZ = z - offsetZ;
		this.cameraPitch = cameraPitch;
		this.cameraOrbitYaw = cameraYaw;
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
			Draw3D.updateTexture(17);
		}
		if (Draw3D.textureCycles[24] < arg0) {
			return;
		}
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
		Draw3D.updateTexture(24);
		return;
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(Z)V")
	private void updateFlames() {
		@Pc(12) int local12;
		for (@Pc(5) int local5 = 10; local5 < 117; local5++) {
			local12 = (int) (Math.random() * 100.0D);
			if (local12 < 50) {
				this.flameIntensity[local5 + 32512] = 255;
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
		for (local41 = 1; local41 < 255; local41++) {
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
		for (local48 = 1; local48 < 255; local48++) {
			for (local54 = 1; local54 < 127; local54++) {
				local173 = local54 + (local48 << 7);
				@Pc(196) int local196 = this.flameIntensityBuffer[local173 + 128] - this.flameBuffer1[local173 + this.flameOffset & this.flameBuffer1.length - 1] / 5;
				if (local196 < 0) {
					local196 = 0;
				}
				this.flameIntensity[local173] = local196;
			}
		}
		for (local54 = 0; local54 < 255; local54++) {
			this.flameShiftX[local54] = this.flameShiftX[local54 + 1];
		}
		this.flameShiftX[255] = (int) (Math.sin((double) clientClock / 14.0D) * 16.0D + Math.sin((double) clientClock / 15.0D) * 14.0D + Math.sin((double) clientClock / 16.0D) * 12.0D);
		if (this.flameCycle1 > 0) {
			this.flameCycle1 -= 4;
		}
		if (this.flameCycle2 > 0) {
			this.flameCycle2 -= 4;
		}
		if (this.flameCycle1 != 0 || this.flameCycle2 != 0) {
			return;
		}
		local173 = (int) (Math.random() * 2000.0D);
		if (local173 == 0) {
			this.flameCycle1 = 1024;
		}
		if (local173 == 1) {
			this.flameCycle2 = 1024;
		}
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(B)V")
	private void drawMinimap() {
		this.areaMapback.makeTarget();
		@Pc(18) int local18 = this.cameraYaw + this.minimapAnticheatAngle & 0x7FF;
		@Pc(26) int local26 = this.self.x / 32 + 48;
		@Pc(34) int local34 = 464 - this.self.z / 32;
		this.minimap.drawRotatedMasked(21, 9, 146, 151, local26, local34, local18, this.minimapZoom + 256, this.minimapLeft, this.minimapLineWidth, true);
		this.compass.drawRotatedMasked(0, 0, 33, 33, 25, 25, this.cameraYaw, 256, this.compassLeft, this.compassLineWidth, true);
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
			if (local189 != null && local189.isValid() && local189.info.showOnMinimap) {
				local26 = local189.x / 32 - this.self.x / 32;
				local34 = local189.z / 32 - this.self.z / 32;
				this.drawOnMinimap(local34, this.mapdot1, local26);
			}
		}
		for (@Pc(235) int local235 = 0; local235 < this.playerCount; local235++) {
			@Pc(245) PlayerEntity local245 = this.playerEntities[this.playerIndices[local235]];
			if (local245 != null && local245.isValid()) {
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
		Draw2D.fillRect(93, 82, 3, 3, 0xffffff);
		this.areaViewport.makeTarget();
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(B)Ljava/awt/Component;")
	@Override
	protected final java.awt.Component getBaseComponent() {
		if (signlink.mainapp == null) {
			return this;
		} else {
			return signlink.mainapp;
		}
	}

	@OriginalMember(owner = "client!client", name = "m", descriptor = "(I)V")
	private void updateTemporaryLocs() {
		if (this.sceneState != 2) {
			return;
		}
		for (@Pc(12) TemporaryLoc local12 = (TemporaryLoc) this.temporaryLocs.peekPrevious(); local12 != null; local12 = (TemporaryLoc) this.temporaryLocs.getPrevious()) {
			if (clientClock >= local12.lastCycle) {
				this.addLoc(local12.orientation, local12.tileX, local12.tileZ, local12.classType, local12.locIndex, local12.type, local12.level);
				local12.unlink();
			}
		}

		updateLocCounter++;
		if (updateLocCounter > 85) {
			updateLocCounter = 0;
			this.outBuffer.p1isaac(ClientProt.ANTICHEAT_UPDATE_LOCS);
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(II)V")
	private void createMinimap(@OriginalArg(0) int plane) {
		@Pc(5) int[] pixels = this.minimap.pixels;
		Arrays.fill(pixels, 0);

		@Pc(37) int offset;
		for (@Pc(25) int z = 1; z < 103; z++) {
			offset = (103 - z) * 512 * 4 + (52 + (48 * 512));

			for (int x = 1; x < 103; x++) {
				if ((this.levelRenderFlags[plane][x][z] & 0x18) == 0) {
					this.mapSquare.drawMinimapTile(pixels, offset, plane, x, z);
				}
				if (plane < 3 && (this.levelRenderFlags[plane + 1][x][z] & 0x8) != 0) {
					this.mapSquare.drawMinimapTile(pixels, offset, plane + 1, x, z);
				}
				offset += 4;
			}
		}

		int rand1 = ((int) (Math.random() * 20.0D) + 238 - 10 << 16) + ((int) (Math.random() * 20.0D) + 238 - 10 << 8) + (int) (Math.random() * 20.0D) + 238 - 10;
		int rand2 = (int) (Math.random() * 20.0D) + 238 - 10 << 16;
		this.minimap.prepare();
		for (@Pc(145) int z = 1; z < 103; z++) {
			for (int x = 1; x < 103; x++) {
				if ((this.levelRenderFlags[plane][x][z] & 0x18) == 0) {
					this.drawMinimapLoc(plane, rand1, x, rand2, z);
				}
				if (plane < 3 && (this.levelRenderFlags[plane + 1][x][z] & 0x8) != 0) {
					this.drawMinimapLoc(plane + 1, rand1, x, rand2, z);
				}
			}
		}
		this.areaViewport.makeTarget();
		this.activeMapFunctionCount = 0;
		for (int z = 0; z < 104; z++) {
			for (@Pc(217) int x = 0; x < 104; x++) {
				@Pc(227) int bitset = this.mapSquare.getGroundDecorationBitset(this.currentPlane, z, x);
				if (bitset != 0) {
					bitset = bitset >> 14 & 0x7FFF;
					@Pc(239) int mapfunction = LocType.get(bitset).mapfunction;
					if (mapfunction >= 0) {
						@Pc(243) int stx = z;
						@Pc(245) int stz = x;
						if (mapfunction != MAPFUNC_GEM_SHOP && mapfunction != MAPFUNC_SILK_TRADER && mapfunction != MAPFUNC_RARE_TREES && mapfunction != MAPFUNC_FOOD_SHOP && mapfunction != MAPFUNC_SILVER_SHOP && mapfunction != MAPFUNC_FUR_TRADER && mapfunction != MAPFUNC_SPICE_SHOP) {
							@Pc(277) int[][] collision = this.collisionMaps[this.currentPlane].flags;
							for (@Pc(279) int local279 = 0; local279 < 10; local279++) {
								@Pc(286) int rand = (int) (Math.random() * 4.0D);
								if (rand == 0 && stx > 0 && stx > z - 3 && (collision[stx - 1][stz] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_EAST)) == 0) {
									stx--;
								}
								if (rand == 1 && stx < 103 && stx < z + 3 && (collision[stx + 1][stz] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST)) == 0) {
									stx++;
								}
								if (rand == 2 && stz > 0 && stz > x - 3 && (collision[stx][stz - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_NORTH)) == 0) {
									stz--;
								}
								if (rand == 3 && stz < 103 && stz < x + 3 && (collision[stx][stz + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_SOUTH)) == 0) {
									stz++;
								}
							}
						}
						this.activeMapFunctions[this.activeMapFunctionCount] = this.mapfunction[mapfunction];
						this.activeMapFunctionX[this.activeMapFunctionCount] = stx;
						this.activeMapFunctionZ[this.activeMapFunctionCount] = stz;
						this.activeMapFunctionCount++;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIII)V")
	private void drawMinimapLoc(@OriginalArg(1) int arg1, @OriginalArg(2) int wallColor, @OriginalArg(3) int x, @OriginalArg(4) int interactableColor, @OriginalArg(5) int y) {
		@Pc(8) int bitset = this.mapSquare.getWallBitset(arg1, x, y);
		@Pc(18) int info;
		@Pc(24) int rotation;
		@Pc(28) int type;
		@Pc(30) int rgb;
		@Pc(52) int off;
		@Pc(58) int locIndex;
		if (bitset != 0) {
			info = this.mapSquare.getInfo(arg1, x, y, bitset);
			rotation = info >> 6 & 0x3;
			type = info & 0x1F;
			rgb = wallColor;
			if (bitset > 0) {
				rgb = interactableColor;
			}
			@Pc(38) int[] dst = this.minimap.pixels;
			off = x * 4 + (103 - y) * 512 * 4 + (48 + (48 * 512));
			locIndex = bitset >> 14 & 0x7FFF;
			@Pc(61) LocType local61 = LocType.get(locIndex);
			if (local61.mapscene == -1) {
				if (type == 0 || type == 2) {
					if (rotation == 0) {
						dst[off] = rgb;
						dst[off + 512] = rgb;
						dst[off + 1024] = rgb;
						dst[off + 1536] = rgb;
					} else if (rotation == 1) {
						dst[off] = rgb;
						dst[off + 1] = rgb;
						dst[off + 2] = rgb;
						dst[off + 3] = rgb;
					} else if (rotation == 2) {
						dst[off + 3] = rgb;
						dst[off + 3 + 512] = rgb;
						dst[off + 3 + 1024] = rgb;
						dst[off + 3 + 1536] = rgb;
					} else if (rotation == 3) {
						dst[off + 1536] = rgb;
						dst[off + 1536 + 1] = rgb;
						dst[off + 1536 + 2] = rgb;
						dst[off + 1536 + 3] = rgb;
					}
				}
				if (type == 3) {
					if (rotation == 0) {
						dst[off] = rgb;
					} else if (rotation == 1) {
						dst[off + 3] = rgb;
					} else if (rotation == 2) {
						dst[off + 3 + 1536] = rgb;
					} else if (rotation == 3) {
						dst[off + 1536] = rgb;
					}
				}
				if (type == 2) {
					if (rotation == 3) {
						dst[off] = rgb;
						dst[off + 512] = rgb;
						dst[off + 1024] = rgb;
						dst[off + 1536] = rgb;
					} else if (rotation == 0) {
						dst[off] = rgb;
						dst[off + 1] = rgb;
						dst[off + 2] = rgb;
						dst[off + 3] = rgb;
					} else if (rotation == 1) {
						dst[off + 3] = rgb;
						dst[off + 3 + 512] = rgb;
						dst[off + 3 + 1024] = rgb;
						dst[off + 3 + 1536] = rgb;
					} else if (rotation == 2) {
						dst[off + 1536] = rgb;
						dst[off + 1536 + 1] = rgb;
						dst[off + 1536 + 2] = rgb;
						dst[off + 1536 + 3] = rgb;
					}
				}
			} else {
				@Pc(71) IndexedSprite local71 = this.mapscene[local61.mapscene];
				if (local71 != null) {
					@Pc(83) int local83 = (local61.width * 4 - local71.spriteWidth) / 2;
					@Pc(93) int local93 = (local61.length * 4 - local71.spriteHeight) / 2;
					local71.draw((104 - y - local61.length) * 4 + local93 + 48, x * 4 + 48 + local83);
				}
			}
		}
		bitset = this.mapSquare.getLocationBitset(arg1, x, y);
		if (bitset != 0) {
			info = this.mapSquare.getInfo(arg1, x, y, bitset);
			rotation = info >> 6 & 0x3;
			type = info & 0x1F;
			rgb = bitset >> 14 & 0x7FFF;
			@Pc(451) LocType local451 = LocType.get(rgb);
			@Pc(483) int local483;
			if (local451.mapscene != -1) {
				@Pc(461) IndexedSprite local461 = this.mapscene[local451.mapscene];
				if (local461 != null) {
					locIndex = (local451.width * 4 - local461.spriteWidth) / 2;
					local483 = (local451.length * 4 - local461.spriteHeight) / 2;
					local461.draw((104 - y - local451.length) * 4 + local483 + 48, x * 4 + 48 + locIndex);
				}
			} else if (type == 9) {
				off = 15658734;
				if (bitset > 0) {
					off = 15597568;
				}
				@Pc(520) int[] local520 = this.minimap.pixels;
				local483 = x * 4 + (103 - y) * 512 * 4 + (48 + (48 * 512));
				if (rotation == 0 || rotation == 2) {
					local520[local483 + 1536] = off;
					local520[local483 + 1024 + 1] = off;
					local520[local483 + 512 + 2] = off;
					local520[local483 + 3] = off;
				} else {
					local520[local483] = off;
					local520[local483 + 512 + 1] = off;
					local520[local483 + 1024 + 2] = off;
					local520[local483 + 1536 + 3] = off;
				}
			}
		}
		bitset = this.mapSquare.getGroundDecorationBitset(arg1, x, y);
		if (bitset == 0) {
			return;
		}
		info = bitset >> 14 & 0x7FFF;

		@Pc(615) LocType locType = LocType.get(info);
		if (locType.mapscene == -1) {
			return;
		}

		@Pc(625) IndexedSprite mapscene = this.mapscene[locType.mapscene];
		if (mapscene != null) {
			rgb = (locType.width * 4 - mapscene.spriteWidth) / 2;
			@Pc(647) int local647 = (locType.length * 4 - mapscene.spriteHeight) / 2;
			mapscene.draw((104 - y - locType.length) * 4 + local647 + 48, x * 4 + 48 + rgb);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!kb;IZ)V")
	private void updateNpcs(@OriginalArg(0) Buffer buffer, @OriginalArg(1) int size) {
		this.deadEntityCount = 0;
		this.updateCount = 0;
		this.updateNpcList(buffer, size);
		this.updateLocalNpcs(buffer, size);
		this.updateNpcMasks(buffer, size);

		@Pc(36) int entity;
		for (@Pc(29) int i = 0; i < this.deadEntityCount; i++) {
			entity = this.deadEntityIndices[i];
			if (this.npcEntities[entity].removeTimer != clientClock) {
				this.npcEntities[entity].info = null;
				this.npcEntities[entity] = null;
			}
		}

		if (buffer.pos != size) {
			signlink.reporterror(this.username + " size mismatch in getnpcpos - pos:" + buffer.pos + " psize:" + size);
			throw new RuntimeException("eek");
		}

		for (entity = 0; entity < this.npcCount; entity++) {
			if (this.npcEntities[this.npcIndices[entity]] == null) {
				signlink.reporterror(this.username + " null entry in npc list - pos:" + entity + " size:" + this.npcCount);
				throw new RuntimeException("eek");
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/Runnable;I)V")
	@Override
	public void startThread(@OriginalArg(0) Runnable runnable, @OriginalArg(1) int priority) {
		if (signlink.mainapp == null) {
			super.startThread(runnable, priority);
		} else {
			signlink.startthread(runnable, priority);
		}
	}

	@OriginalMember(owner = "client!client", name = "n", descriptor = "(I)V")
	private void loadTitleForeground() {
		this.titlebox = new IndexedSprite(this.title, "titlebox", 0);
		this.titlebutton = new IndexedSprite(this.title, "titlebutton", 0);
		this.runes = new IndexedSprite[12];
		for (@Pc(32) int i = 0; i < 12; i++) {
			this.runes[i] = new IndexedSprite(this.title, "runes", i);
		}
		this.imageFlamesLeft = new Sprite(128, 265);
		this.imageFlamesRight = new Sprite(128, 265);
		System.arraycopy(this.titleLeft.pixels, 0, this.imageFlamesLeft.pixels, 0, 128 * 265);
		System.arraycopy(this.titleRight.pixels, 0, this.imageFlamesRight.pixels, 0, 128 * 265);
		this.flameGradientRed = new int[256];
		for (@Pc(105) int local105 = 0; local105 < 64; local105++) {
			this.flameGradientRed[local105] = local105 * 262144;
		}
		for (@Pc(120) int local120 = 0; local120 < 64; local120++) {
			this.flameGradientRed[local120 + 64] = local120 * 1024 + 0xff0000;
		}
		for (@Pc(139) int local139 = 0; local139 < 64; local139++) {
			this.flameGradientRed[local139 + 128] = local139 * 4 + 0xffff00;
		}
		for (@Pc(158) int local158 = 0; local158 < 64; local158++) {
			this.flameGradientRed[local158 + 192] = 0xffffff;
		}
		this.flameGradientGreen = new int[256];
		for (@Pc(177) int local177 = 0; local177 < 64; local177++) {
			this.flameGradientGreen[local177] = local177 * 1024;
		}
		for (@Pc(192) int local192 = 0; local192 < 64; local192++) {
			this.flameGradientGreen[local192 + 64] = local192 * 4 + 0xff00;
		}
		for (@Pc(211) int local211 = 0; local211 < 64; local211++) {
			this.flameGradientGreen[local211 + 128] = local211 * 262144 + 0xffff;
		}
		for (@Pc(230) int local230 = 0; local230 < 64; local230++) {
			this.flameGradientGreen[local230 + 192] = 0xffffff;
		}
		this.flameGradientBlue = new int[256];
		for (@Pc(249) int local249 = 0; local249 < 64; local249++) {
			this.flameGradientBlue[local249] = local249 * 4;
		}
		for (@Pc(264) int local264 = 0; local264 < 64; local264++) {
			this.flameGradientBlue[local264 + 64] = local264 * 262144 + 0xff;
		}
		for (@Pc(283) int local283 = 0; local283 < 64; local283++) {
			this.flameGradientBlue[local283 + 128] = local283 * 1024 + 0xff00ff;
		}
		for (@Pc(302) int local302 = 0; local302 < 64; local302++) {
			this.flameGradientBlue[local302 + 192] = 0xffffff;
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
	private void updateOtherPlayers(@OriginalArg(0) int size, @OriginalArg(1) Buffer b) {
		@Pc(6) int count = b.gBit(8);

		if (count < this.playerCount) {
			for (@Pc(21) int i = count; i < this.playerCount; i++) {
				this.deadEntityIndices[this.deadEntityCount++] = this.playerIndices[i];
			}
		}

		if (count > this.playerCount) {
			signlink.reporterror(this.username + " Too many players");
			throw new RuntimeException("eek");
		}

		this.playerCount = 0;
		for (int i = 0; i < count; i++) {
			@Pc(73) int index = this.playerIndices[i];
			@Pc(78) PlayerEntity p = this.playerEntities[index];

			@Pc(83) int keepPlayer = b.gBit(1);
			if (keepPlayer == 0) {
				this.playerIndices[this.playerCount++] = index;
				p.removeTimer = clientClock;
				return;
			}

			@Pc(106) int type = b.gBit(2);
			if (type == 0) {
				this.playerIndices[this.playerCount++] = index;
				p.removeTimer = clientClock;
				this.entityUpdateIndices[this.updateCount++] = index;
			} else if (type == 1) {
				this.playerIndices[this.playerCount++] = index;
				p.removeTimer = clientClock;
				int walkDir = b.gBit(3);
				p.walk(false, walkDir);
				int hasMaskUpdate = b.gBit(1);
				if (hasMaskUpdate == 1) {
					this.entityUpdateIndices[this.updateCount++] = index;
				}
			} else if (type == 2) {
				this.playerIndices[this.playerCount++] = index;
				p.removeTimer = clientClock;
				int walkDir = b.gBit(3);
				p.walk(true, walkDir);
				int runDir = b.gBit(3);
				p.walk(true, runDir);
				@Pc(225) int hasMaskUpdate = b.gBit(1);
				if (hasMaskUpdate == 1) {
					this.entityUpdateIndices[this.updateCount++] = index;
				}
			} else if (type == 3) {
				this.deadEntityIndices[this.deadEntityCount++] = index;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IIIIII)V")
	private void drawScrollbar(@OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int scrollY, @OriginalArg(4) int scrollHeight, @OriginalArg(5) int height) {
		this.scrollbar1.draw(y, x);
		this.scrollbar2.draw(y + height - 16, x);
		Draw2D.fillRect(x, y + 16, 16, height - 32, this.SCROLLBAR_TRACK);
		@Pc(35) int gripSize = (height - 32) * height / scrollHeight;
		if (gripSize < 8) {
			gripSize = 8;
		}
		@Pc(52) int gripY = (height - gripSize - 32) * scrollY / (scrollHeight - height);
		Draw2D.fillRect(x, y + gripY + 16, 16, gripSize, this.SCROLLBAR_GRIP_FOREGROUND);
		Draw2D.drawVerticalLine(x, y + gripY + 16, gripSize, this.SCROLLBAR_GRIP_HIGHLIGHT);
		Draw2D.drawVerticalLine(x + 1, y + gripY + 16, gripSize, this.SCROLLBAR_GRIP_HIGHLIGHT);
		Draw2D.drawHorizontalLine(x, y + gripY + 16, 16, this.SCROLLBAR_GRIP_HIGHLIGHT);
		Draw2D.drawHorizontalLine(x, y + gripY + 17, 16, this.SCROLLBAR_GRIP_HIGHLIGHT);
		Draw2D.drawVerticalLine(x + 15, y + gripY + 16, gripSize, this.SCROLLBAR_GRIP_LOWLIGHT);
		Draw2D.drawVerticalLine(x + 14, y + gripY + 17, gripSize - 1, this.SCROLLBAR_GRIP_LOWLIGHT);
		Draw2D.drawHorizontalLine(x, y + gripY + gripSize + 15, 16, this.SCROLLBAR_GRIP_LOWLIGHT);
		Draw2D.drawHorizontalLine(x + 1, y + gripY + gripSize + 14, 15, this.SCROLLBAR_GRIP_LOWLIGHT);
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(B)V")
	private void resetCharacterDesign() {
		this.characterDesignUpdate = true;

		for (@Pc(12) int i = 0; i < 7; i++) {
			this.characterDesigns[i] = -1;

			for (@Pc(21) int j = 0; j < IdkType.count; j++) {
				if (!IdkType.instances[j].validStyle && IdkType.instances[j].type == i + (this.characterDesignIsMale ? 0 : 7)) {
					this.characterDesigns[i] = j;
					break;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([BIIZ)V")
	private void midisave(@OriginalArg(0) byte[] buf, @OriginalArg(2) int len, @OriginalArg(3) boolean fade) {
		signlink.midifade = fade;
		signlink.midisave(buf, len);
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(Z)V")
	private void updateSceneNpcs() {
		for (@Pc(1) int local1 = 0; local1 < this.npcCount; local1++) {
			@Pc(11) NpcEntity local11 = this.npcEntities[this.npcIndices[local1]];
			@Pc(20) int local20 = (this.npcIndices[local1] << 14) + 536870912;
			if (local11 != null && local11.isValid()) {
				@Pc(31) int local31 = local11.x >> 7;
				@Pc(36) int local36 = local11.z >> 7;
				if (local31 >= 0 && local31 < 104 && local36 >= 0 && local36 < 104) {
					if (local11.index == 1 && (local11.x & 0x7F) == 64 && (local11.z & 0x7F) == 64) {
						if (this.tileRenderCount[local31][local36] == this.sceneCycle) {
							continue;
						}
						this.tileRenderCount[local31][local36] = this.sceneCycle;
					}
					this.mapSquare.add(local11.z, (local11.index - 1) * 64 + 60, local11.animationDelay, local11.x, local20, local11.animationStretches, null, local11, this.getLandY(this.currentPlane, local11.x, local11.z), this.currentPlane);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIZ)V")
	private void midivol(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1) {
		signlink.midivol = arg0;
		if (arg1) {
			signlink.midi = "voladjust";
		}
	}

	@OriginalMember(owner = "client!client", name = "o", descriptor = "(I)V")
	private void drawTitleScreen() {
		this.prepareTitleScreen();
		this.titleCenter.makeTarget();
		this.titlebox.draw(0, 0);
		@Pc(32) byte startY;
		@Pc(44) int y;
		if (this.titleState == 0) {
			startY = 80;
			this.bold12.drawCentered("Welcome to RuneScape", 180, 80, 0xffff00, true);
			y = startY + 30;
			this.titlebutton.draw(100, 27);
			this.bold12.drawCentered("New user", 100, 125, 0xffffff, true);
			this.titlebutton.draw(100, 187);
			this.bold12.drawCentered("Existing User", 260, 125, 0xffffff, true);
		}
		if (this.titleState == 2) {
			startY = 60;
			if (this.loginMessage0.length() > 0) {
				this.bold12.drawCentered(this.loginMessage0, 180, 45, 0xffff00, true);
				this.bold12.drawCentered(this.loginMessage1, 180, 60, 0xffff00, true);
				y = startY + 30;
			} else {
				this.bold12.drawCentered(this.loginMessage1, 180, 53, 0xffff00, true);
				y = startY + 30;
			}
			this.bold12.draw("Username: " + this.username + (this.loginFocusedLine == 0 & clientClock % 40 < 20 ? "@yel@|" : ""), 90, 90, 0xffffff, true);
			y += 15;
			this.bold12.draw("Password: " + StringUtils.censor(this.password) + (this.loginFocusedLine == 1 & clientClock % 40 < 20 ? "@yel@|" : ""), 92, 105, 0xffffff, true);
			y += 15;
			this.titlebutton.draw(130, 27);
			this.bold12.drawCentered("Login", 100, 155, 0xffffff, true);
			this.titlebutton.draw(130, 187);
			this.bold12.drawCentered("Cancel", 260, 155, 0xffffff, true);
		}
		if (this.titleState == 3) {
			this.bold12.drawCentered("Create a free account", 180, 40, 0xffff00, true);
			startY = 65;
			this.bold12.drawCentered("To create a new account you need to", 180, 65, 0xffffff, true);
			y = startY + 15;
			this.bold12.drawCentered("go back to the main RuneScape webpage", 180, 80, 0xffffff, true);
			y += 15;
			this.bold12.drawCentered("and choose the red 'create account'", 180, 95, 0xffffff, true);
			y += 15;
			this.bold12.drawCentered("button at the top right of that page.", 180, 110, 0xffffff, true);
			y += 15;
			this.titlebutton.draw(130, 107);
			this.bold12.drawCentered("Cancel", 180, 155, 0xffffff, true);
		}
		this.titleCenter.drawAt(214, 186, super.graphic);
		if (!this.redrawTitleBackground) {
			return;
		}
		this.redrawTitleBackground = false;
		this.titleTop.drawAt(128, 0, super.graphic);
		this.titleBottom.drawAt(214, 386, super.graphic);
		this.titleBottomLeft.drawAt(0, 265, super.graphic);
		this.titleBottomRight.drawAt(574, 265, super.graphic);
		this.titleLeftSpace.drawAt(128, 186, super.graphic);
		this.titleRightSpace.drawAt(574, 186, super.graphic);
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(I)V")
	private void prepareGameScreen() {
		if (this.areaChatback != null) {
			return;
		}

		this.disposeTitleComponents();

		this.titleTop = null;
		this.titleBottom = null;
		this.titleCenter = null;
		this.titleLeft = null;
		this.titleRight = null;
		this.titleBottomLeft = null;
		this.titleBottomRight = null;
		this.titleLeftSpace = null;
		this.titleRightSpace = null;

		this.areaChatback = new BufferedImageFrameBuffer(479, 96);
		this.areaMapback = new BufferedImageFrameBuffer(168, 160);
		Draw2D.clear();
		this.mapback.draw(0, 0);

		this.areaInvback = new BufferedImageFrameBuffer(190, 261);
		this.areaViewport = new BufferedImageFrameBuffer(Draw3D.renderWidth, Draw3D.renderHeight);
		Draw2D.clear();

		this.areaBackbase1 = new BufferedImageFrameBuffer(501, 61);
		this.areaBackbase2 = new BufferedImageFrameBuffer(288, 40);
		this.areaBackhmid1 = new BufferedImageFrameBuffer(269, 66);
		this.redrawTitleBackground = true;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IILclient!kb;)V")
	private void updateNewPlayers(@OriginalArg(1) int size, @OriginalArg(2) Buffer b) {
		while (true) {
			if (b.bitOffset + 10 >= size * 8) {
				break;
			}

			@Pc(27) int pid = b.gBit(11);
			if (pid == 2047) {
				break;
			}

			if (this.playerEntities[pid] == null) {
				this.playerEntities[pid] = new PlayerEntity();
				if (this.playerBuffers[pid] != null) {
					this.playerEntities[pid].decode(this.playerBuffers[pid]);
				}
			}

			this.playerIndices[this.playerCount++] = pid;
			@Pc(73) PlayerEntity p = this.playerEntities[pid];
			p.removeTimer = clientClock;

			@Pc(81) int x = b.gBit(5);
			if (x > 15) {
				x -= 32;
			}

			@Pc(90) int z = b.gBit(5);
			if (z > 15) {
				z -= 32;
			}

			@Pc(99) int warp = b.gBit(1);
			p.move(warp == 1, this.self.pathTileX[0] + x, this.self.pathTileZ[0] + z);

			@Pc(127) int hasMaskUpdate = b.gBit(1);
			if (hasMaskUpdate == 1) {
				this.entityUpdateIndices[this.updateCount++] = pid;
			}
		}

		b.accessBytes();
	}

	@OriginalMember(owner = "client!client", name = "q", descriptor = "(I)V")
	private void disconnect() {
		try {
			if (this.stream != null) {
				this.stream.close();
			}
		} catch (@Pc(9) Exception ignored) {
		}
		this.stream = null;
		this.ingame = false;
		this.titleState = 0;
		this.username = "";
		this.password = "";
		InputTracking.setDisabled();
		this.clearCaches();
		this.mapSquare.reset();
		for (@Pc(41) int plane = 0; plane < 4; plane++) {
			this.collisionMaps[plane].reset();
		}
		System.gc();
		this.midistop();
		this.currentMidi = null;
		this.nextMusicDelay = 0;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIILclient!hc;I)V")
	private void drawInterface(@OriginalArg(0) int parentY, @OriginalArg(1) int parentX, @OriginalArg(3) Component parent, @OriginalArg(4) int offsetY) {
		if (parent.type != Component.TYPE_PARENT || parent.children == null) {
			return;
		}

		if (parent.hidden && this.viewportHoveredInterfaceIndex != parent.id && this.sidebarHoveredInterfaceIndex != parent.id && this.chatHoveredInterfaceIndex != parent.id) {
			return;
		}

		@Pc(29) int left = Draw2D.left;
		@Pc(31) int top = Draw2D.top;
		@Pc(33) int right = Draw2D.right;
		@Pc(35) int bottom = Draw2D.bottom;
		Draw2D.setBounds(parentX, parentX + parent.width, parentY, parentY + parent.height);

		@Pc(57) int children = parent.children.length;
		for (@Pc(59) int i = 0; i < children; i++) {
			@Pc(68) int x = parent.childX[i] + parentX;
			@Pc(77) int y = parent.childY[i] + parentY - offsetY;

			@Pc(84) Component child = Component.instances[parent.children[i]];
			@Pc(89) int childX = x + child.x;
			@Pc(94) int childY = y + child.y;
			if (child.clientCode > 0) {
				this.updateComponentContent(child);
			}

			if (child.type == Component.TYPE_PARENT) {
				if (child.scrollY > child.scrollHeight - child.height) {
					child.scrollY = child.scrollHeight - child.height;
				}

				if (child.scrollY < 0) {
					child.scrollY = 0;
				}

				this.drawInterface(childY, childX, child, child.scrollY);

				if (child.scrollHeight > child.height) {
					this.drawScrollbar(childX + child.width, childY, child.scrollY, child.scrollHeight, child.height);
				}
			} else if (child.type == Component.TYPE_INVENTORY) {
				int slot = 0;
				for (int row = 0; row < child.height; row++) {
					for (int column = 0; column < child.width; column++) {
						drawX = childX + column * (child.inventoryMarginX + 32);
						drawY = childY + row * (child.inventoryMarginY + 32);
						if (slot < 20) {
							drawX += child.inventoryOffsetX[slot];
							drawY += child.inventoryOffsetY[slot];
						}

						if (child.inventoryIndices[slot] > 0) {
							int dragX = 0;
							int dragY = 0;
							int index = child.inventoryIndices[slot] - 1;
							if (drawX >= -32 && drawX <= 512 && drawY >= -32 && drawY <= 334 || this.objDragArea != 0 && this.objDragSlot == slot) {
								@Pc(251) Sprite sprite = ObjType.getSprite(index, child.inventoryAmount[slot]);
								if (this.objDragArea != 0 && this.objDragSlot == slot && this.objDragComponentId == child.id) {
									dragX = super.mouseX - this.objGrabX;
									dragY = super.mouseY - this.objGrabY;
									if (dragX < 5 && dragX > -5) {
										dragX = 0;
									}

									if (dragY < 5 && dragY > -5) {
										dragY = 0;
									}

									if (this.objDragCycles < 5) {
										dragX = 0;
										dragY = 0;
									}

									sprite.drawAlpha(drawX + dragX, drawY + dragY);
								} else if (this.selectedArea != 0 && this.selectedItem == slot && this.selectedInterface == child.id) {
									sprite.drawAlpha(drawX, drawY);
								} else {
									sprite.draw(drawX, drawY);
								}

								if (sprite.cropW == 33 || child.inventoryAmount[slot] != 1) {
									@Pc(351) int amount = child.inventoryAmount[slot];
									this.plain11.draw(drawX + dragX + 1, drawY + 10 + dragY, 0, formatObjAmount(amount));
									this.plain11.draw(drawX + dragX, drawY + 9 + dragY, 0xffff00, formatObjAmount(amount));
								}
							}
						} else if (child.inventorySprite != null && slot < 20) {
							@Pc(398) Sprite sprite = child.inventorySprite[slot];
							if (sprite != null) {
								sprite.draw(drawX, drawY);
							}
						}

						slot++;
					}
				}
			} else if (child.type == Component.TYPE_RECT) {
				if (child.fill) {
					Draw2D.fillRect(childX, childY, child.width, child.height, child.color);
				} else {
					Draw2D.drawRect(childX, childY, child.width, child.height, child.color);
				}
			} else if (child.type == Component.TYPE_TEXT) {
				Font font = child.font;
				int color = child.color;

				@Pc(462) String text = child.text;
				if ((this.chatHoveredInterfaceIndex == child.id || this.sidebarHoveredInterfaceIndex == child.id || this.viewportHoveredInterfaceIndex == child.id) && child.hoverColor != 0) {
					color = child.hoverColor;
				}

				if (this.isInterfaceEnabled(child)) {
					color = child.colorEnabled;
					if (child.activeText.length() > 0) {
						text = child.activeText;
					}
				}

				if (child.buttonType == Component.PAUSE_BUTTON && this.chatContinuingDialogue) {
					text = "Please wait...";
					color = child.color;
				}

				drawY = childY + font.fontHeight;
				while (text.length() > 0) {
					if (text.contains("%")) {
						for (; ; ) {
							int pos = text.indexOf("%1");
							if (pos == -1)
								break;
							text = (text.substring(0, pos) + getIntString(executeInterface(child, 0)) + text.substring(pos + 2));
						}
						for (; ; ) {
							int pos = text.indexOf("%2");
							if (pos == -1)
								break;
							text = (text.substring(0, pos) + getIntString(executeInterface(child, 1)) + text.substring(pos + 2));
						}
						for (; ; ) {
							int pos = text.indexOf("%3");
							if (pos == -1) break;
							text = (text.substring(0, pos) + getIntString(executeInterface(child, 2)) + text.substring(pos + 2));
						}
						for (; ; ) {
							int pos = text.indexOf("%4");
							if (pos == -1) break;
							text = (text.substring(0, pos) + getIntString(executeInterface(child, 3)) + text.substring(pos + 2));
						}
						for (; ; ) {
							int pos = text.indexOf("%5");
							if (pos == -1) break;
							text = (text.substring(0, pos) + getIntString(executeInterface(child, 4)) + text.substring(pos + 2));
						}
					}

					int newline = text.indexOf("\\n");
					@Pc(704) String str;
					if (newline == -1) {
						str = text;
						text = "";
					} else {
						str = text.substring(0, newline);
						text = text.substring(newline + 2);
					}

					if (child.center) {
						font.drawCentered(str, childX + child.width / 2, drawY, color, child.shadow);
					} else {
						font.draw(str, childX, drawY, color, child.shadow);
					}

					drawY += font.fontHeight;
				}
			} else if (child.type == Component.TYPE_SPRITE) {
				@Pc(766) Sprite sprite;

				if (this.isInterfaceEnabled(child)) {
					sprite = child.activeSprite;
				} else {
					sprite = child.sprite;
				}

				if (sprite != null) {
					sprite.draw(childX, childY);
				}
			} else if (child.type == Component.TYPE_MODEL) {
				int centerX = Draw3D.centerX3D;
				int centerY = Draw3D.centerY3D;

				Draw3D.centerX3D = childX + child.width / 2;
				Draw3D.centerY3D = childY + child.height / 2;

				int camY = Draw3D.sin[child.modelEyePitch] * child.modelZoom >> 16;
				int camZ = Draw3D.cos[child.modelEyePitch] * child.modelZoom >> 16;

				@Pc(827) boolean useActiveSeq = this.isInterfaceEnabled(child);
				int seqId;
				if (useActiveSeq) {
					seqId = child.activeSeqId;
				} else {
					seqId = child.seqId;
				}

				@Pc(846) Model m;
				if (seqId == -1) {
					m = child.getModel(-1, -1, useActiveSeq);
				} else {
					@Pc(852) SeqType seq = SeqType.instances[seqId];
					m = child.getModel(seq.primaryFrames[child.seqFrame], seq.secondaryFrames[child.seqFrame], useActiveSeq);
				}

				if (m != null) {
					m.draw(child.modelYaw, 0, child.modelEyePitch, 0, camY, camZ);
				}

				Draw3D.centerX3D = centerX;
				Draw3D.centerY3D = centerY;
			} else if (child.type == Component.TYPE_INVENTORY_TEXT) {
				Font font = child.font;

				int slot = 0;
				for (int row = 0; row < child.height; row++) {
					for (int column = 0; column < child.width; column++) {
						if (child.inventoryIndices[slot] > 0) {
							@Pc(915) ObjType obj = ObjType.get(child.inventoryIndices[slot] - 1);
							@Pc(918) String name = obj.name;
							if (obj.stackable || child.inventoryAmount[slot] != 1) {
								name = name + " x" + formatNumber(child.inventoryAmount[slot]);
							}

							int dx = childX + column * (child.inventoryMarginX + 115);
							int dy = childY + row * (child.inventoryMarginY + 12);

							if (child.center) {
								font.drawCentered(name, dx + child.width / 2, dy, child.color, child.shadow);
							} else {
								font.draw(name, dx, dy, child.color, child.shadow);
							}
						}

						slot++;
					}
				}
			}
		}

		Draw2D.setBounds(left, right, top, bottom);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZILclient!kb;)V")
	private void updatePlayerMasks(@OriginalArg(1) int size, @OriginalArg(2) Buffer b) {
		for (@Pc(1) int i = 0; i < this.updateCount; i++) {
			@Pc(8) int entity = this.entityUpdateIndices[i];
			@Pc(13) PlayerEntity p = this.playerEntities[entity];

			@Pc(16) int type = b.g1();
			if ((type & 0x80) == 0x80) {
				type += b.g1() << 8;
			}

			this.updatePlayerMask(entity, type, b, p);
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(II)V")
	private void updateVarp(@OriginalArg(0) int varp) {
		@Pc(8) int type = VarpType.instances[varp].type;
		if (type == 0) {
			return;
		}

		@Pc(16) int v = this.variables[varp];
		if (type == 1) {
			if (v == 1) {
				Draw3D.setBrightness(0.9D);
			} else if (v == 2) {
				Draw3D.setBrightness(0.8D);
			} else if (v == 3) {
				Draw3D.setBrightness(0.7D);
			} else if (v == 4) {
				Draw3D.setBrightness(0.6D);
			}
			ObjType.icons.clear();
			this.redrawTitleBackground = true;
		} else if (type == 3) {
			@Pc(54) boolean currentlyActive = this.midiActive;

			if (v == 0) {
				this.midivol(256, this.midiActive);
				this.midiActive = true;
			} else if (v == 1) {
				this.midivol(192, this.midiActive);
				this.midiActive = true;
			} else if (v == 2) {
				this.midivol(96, this.midiActive);
				this.midiActive = true;
			} else if (v == 3) {
				this.midivol(32, this.midiActive);
				this.midiActive = true;
			} else if (v == 4) {
				this.midiActive = false;
			}

			if (this.midiActive != currentlyActive) {
				if (this.midiActive) {
					this.setMidi(this.midiCrc, this.currentMidi, this.midiSize);
				} else {
					this.midistop();
				}
				this.nextMusicDelay = 0;
			}
		} else if (type == 4) {
			if (v == 0) {
				this.effectsEnabled = true;
				this.wavevol(0);
			} else if (v == 1) {
				this.effectsEnabled = true;
				this.wavevol(-400);
			} else if (v == 2) {
				this.effectsEnabled = true;
				this.wavevol(-800);
			} else if (v == 3) {
				this.effectsEnabled = true;
				this.wavevol(-1200);
			} else if (v == 4) {
				this.effectsEnabled = false;
			}
		} else if (type == 5) {
			this.button = v;
		} else if (type == 6) {
			this.chatEffects = v;
		} else if (type == 8) {
			this.splitPrivateChat = v;
			this.redrawChatback = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(Z)V")
	private void updateNpcEntity() {
		for (@Pc(7) int local7 = 0; local7 < this.npcCount; local7++) {
			@Pc(14) int local14 = this.npcIndices[local7];
			@Pc(19) NpcEntity local19 = this.npcEntities[local14];
			if (local19 != null) {
				this.updateEntity(local19, local19.info.size);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;BI)V")
	private void updateEntity(@OriginalArg(0) PathingEntity e, @OriginalArg(2) int size) {
		if (e.x < 128 || e.z < 128 || e.x >= 13184 || e.z >= 13184) {
			e.primarySeq = -1;
			e.spotAnimIndex = -1;
			e.firstMoveCycle = 0;
			e.lastMoveCycle = 0;
			e.x = e.pathTileX[0] * 128 + e.index * 64;
			e.z = e.pathTileZ[0] * 128 + e.index * 64;
			e.pathRemaining = 0;
		}
		if (e == this.self && (e.x < 1536 || e.z < 1536 || e.x >= 11776 || e.z >= 11776)) {
			e.primarySeq = -1;
			e.spotAnimIndex = -1;
			e.firstMoveCycle = 0;
			e.lastMoveCycle = 0;
			e.x = e.pathTileX[0] * 128 + e.index * 64;
			e.z = e.pathTileZ[0] * 128 + e.index * 64;
			e.pathRemaining = 0;
		}
		if (e.firstMoveCycle > clientClock) {
			this.moveEntity1(e);
		} else if (e.lastMoveCycle >= clientClock) {
			this.moveEntity2(e);
		} else {
			this.moveEntity3(e);
		}
		this.updateEntity2(e);
		this.updateEntity3(e);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;I)V")
	private void moveEntity1(@OriginalArg(0) PathingEntity e) {
		@Pc(4) int local4 = e.firstMoveCycle - clientClock;
		@Pc(14) int local14 = e.srcTileX * 128 + e.index * 64;
		@Pc(24) int local24 = e.srcTileZ * 128 + e.index * 64;
		e.x += (local14 - e.x) / local4;
		e.z += (local24 - e.z) / local4;
		e.anInt928 = 0;
		if (e.faceDirection == 0) {
			e.dstYaw = 1024;
		} else if (e.faceDirection == 1) {
			e.dstYaw = 1536;
		} else if (e.faceDirection == 2) {
			e.dstYaw = 0;
		} else if (e.faceDirection == 3) {
			e.dstYaw = 512;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!x;I)V")
	private void moveEntity2(@OriginalArg(0) PathingEntity arg0) {
		if (arg0.lastMoveCycle == clientClock || arg0.primarySeq == -1 || arg0.primarySeqDelay != 0 || arg0.primarySeqCycle + 1 > SeqType.instances[arg0.primarySeq].frameDelay[arg0.primarySeqFrame]) {
			@Pc(35) int local35 = arg0.lastMoveCycle - arg0.firstMoveCycle;
			@Pc(40) int local40 = clientClock - arg0.firstMoveCycle;
			@Pc(50) int local50 = arg0.srcTileX * 128 + arg0.index * 64;
			@Pc(60) int local60 = arg0.srcTileZ * 128 + arg0.index * 64;
			@Pc(70) int local70 = arg0.dstTileX * 128 + arg0.index * 64;
			@Pc(80) int local80 = arg0.dstTileZ * 128 + arg0.index * 64;
			arg0.x = (local50 * (local35 - local40) + local70 * local40) / local35;
			arg0.z = (local60 * (local35 - local40) + local80 * local40) / local35;
		}
		arg0.anInt928 = 0;
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
		arg0.animationDelay = arg0.dstYaw;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!x;)V")
	private void moveEntity3(@OriginalArg(1) PathingEntity arg0) {
		arg0.secondarySeq = arg0.standSeq;
		if (arg0.pathRemaining == 0) {
			arg0.anInt928 = 0;
			return;
		}
		if (arg0.primarySeq != -1 && arg0.primarySeqDelay == 0) {
			@Pc(28) SeqType local28 = SeqType.instances[arg0.primarySeq];
			if (local28.labelGroups == null) {
				arg0.anInt928++;
				return;
			}
		}
		@Pc(41) int local41 = arg0.x;
		@Pc(44) int local44 = arg0.z;
		@Pc(59) int local59 = arg0.pathTileX[arg0.pathRemaining - 1] * 128 + arg0.index * 64;
		@Pc(74) int local74 = arg0.pathTileZ[arg0.pathRemaining - 1] * 128 + arg0.index * 64;
		if (local59 - local41 > 256 || local59 - local41 < -256 || local74 - local44 > 256 || local74 - local44 < -256) {
			arg0.x = local59;
			arg0.z = local74;
			return;
		}
		if (local41 < local59) {
			if (local44 < local74) {
				arg0.dstYaw = 1280;
			} else if (local44 > local74) {
				arg0.dstYaw = 1792;
			} else {
				arg0.dstYaw = 1536;
			}
		} else if (local41 > local59) {
			if (local44 < local74) {
				arg0.dstYaw = 768;
			} else if (local44 > local74) {
				arg0.dstYaw = 256;
			} else {
				arg0.dstYaw = 512;
			}
		} else if (local44 < local74) {
			arg0.dstYaw = 1024;
		} else {
			arg0.dstYaw = 0;
		}
		@Pc(168) int local168 = arg0.dstYaw - arg0.animationDelay & 0x7FF;
		if (local168 > 1024) {
			local168 -= 2048;
		}
		@Pc(175) int local175 = arg0.walkSeq;
		if (local168 >= -256 && local168 <= 256) {
			local175 = arg0.runSeq;
		} else if (local168 >= 256 && local168 < 768) {
			local175 = arg0.turnRightSeq;
		} else if (local168 >= -768 && local168 <= -256) {
			local175 = arg0.turnAroundSeq;
		}
		if (local175 == -1) {
			local175 = arg0.runSeq;
		}
		arg0.secondarySeq = local175;
		@Pc(217) int local217 = 4;
		if (arg0.animationDelay != arg0.dstYaw && arg0.targetEntity == -1) {
			local217 = 2;
		}
		if (arg0.pathRemaining > 2) {
			local217 = 6;
		}
		if (arg0.pathRemaining > 3) {
			local217 = 8;
		}
		if (arg0.anInt928 > 0 && arg0.pathRemaining > 1) {
			local217 = 8;
			arg0.anInt928--;
		}
		if (arg0.pathRunning[arg0.pathRemaining - 1]) {
			local217 <<= 0x1;
		}
		if (local217 >= 8 && arg0.secondarySeq == arg0.runSeq && arg0.turnLeftSeq != -1) {
			arg0.secondarySeq = arg0.turnLeftSeq;
		}
		if (local41 < local59) {
			arg0.x += local217;
			if (arg0.x > local59) {
				arg0.x = local59;
			}
		} else if (local41 > local59) {
			arg0.x -= local217;
			if (arg0.x < local59) {
				arg0.x = local59;
			}
		}
		if (local44 < local74) {
			arg0.z += local217;
			if (arg0.z > local74) {
				arg0.z = local74;
			}
		} else if (local44 > local74) {
			arg0.z -= local217;
			if (arg0.z < local74) {
				arg0.z = local74;
			}
		}
		if (arg0.x == local59 && arg0.z == local74) {
			arg0.pathRemaining--;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!x;B)V")
	private void updateEntity2(@OriginalArg(0) PathingEntity arg0) {
		@Pc(30) int local30;
		@Pc(36) int local36;
		if (arg0.targetEntity != -1 && arg0.targetEntity < 32768) {
			@Pc(22) NpcEntity local22 = this.npcEntities[arg0.targetEntity];
			if (local22 != null) {
				local30 = arg0.x - local22.x;
				local36 = arg0.z - local22.z;
				if (local30 != 0 || local36 != 0) {
					arg0.dstYaw = (int) (Math.atan2(local30, local36) * 325.949D) & 0x7FF;
				}
			}
		}
		@Pc(61) int local61;
		if (arg0.targetEntity >= 32768) {
			local61 = arg0.targetEntity - 32768;
			if (local61 == this.selfPlayerId) {
				local61 = this.LOCAL_PLAYER_INDEX;
			}
			@Pc(73) PlayerEntity local73 = this.playerEntities[local61];
			if (local73 != null) {
				local36 = arg0.x - local73.x;
				@Pc(87) int local87 = arg0.z - local73.z;
				if (local36 != 0 || local87 != 0) {
					arg0.dstYaw = (int) (Math.atan2(local36, local87) * 325.949D) & 0x7FF;
				}
			}
		}
		if ((arg0.focusX != 0 || arg0.focusZ != 0) && (arg0.pathRemaining == 0 || arg0.anInt928 > 0)) {
			local61 = arg0.x - (arg0.focusX - this.baseTileX - this.baseTileX) * 64;
			local30 = arg0.z - (arg0.focusZ - this.baseTileZ - this.baseTileZ) * 64;
			if (local61 != 0 || local30 != 0) {
				arg0.dstYaw = (int) (Math.atan2(local61, local30) * 325.949D) & 0x7FF;
			}
			arg0.focusX = 0;
			arg0.focusZ = 0;
		}
		local61 = arg0.dstYaw - arg0.animationDelay & 0x7FF;
		if (local61 == 0) {
			return;
		}
		if (local61 < 32 || local61 > 2016) {
			arg0.animationDelay = arg0.dstYaw;
		} else if (local61 > 1024) {
			arg0.animationDelay -= 32;
		} else {
			arg0.animationDelay += 32;
		}
		arg0.animationDelay &= 0x7FF;
		if (arg0.secondarySeq != arg0.standSeq || arg0.animationDelay == arg0.dstYaw) {
			return;
		}
		if (arg0.turnSeq != -1) {
			arg0.secondarySeq = arg0.turnSeq;
			return;
		}
		arg0.secondarySeq = arg0.runSeq;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!x;)V")
	private void updateEntity3(@OriginalArg(1) PathingEntity arg1) {
		arg1.animationStretches = false;
		@Pc(16) SeqType local16;
		if (arg1.secondarySeq != -1) {
			local16 = SeqType.instances[arg1.secondarySeq];
			arg1.secondarySeqCycle++;
			if (arg1.secondarySeqFrame < local16.frameCount && arg1.secondarySeqCycle > local16.frameDelay[arg1.secondarySeqFrame]) {
				arg1.secondarySeqCycle = 0;
				arg1.secondarySeqFrame++;
			}
			if (arg1.secondarySeqFrame >= local16.frameCount) {
				arg1.secondarySeqCycle = 0;
				arg1.secondarySeqFrame = 0;
			}
		}
		if (arg1.primarySeq != -1 && arg1.primarySeqDelay == 0) {
			local16 = SeqType.instances[arg1.primarySeq];
			arg1.primarySeqCycle++;
			while (arg1.primarySeqFrame < local16.frameCount && arg1.primarySeqCycle > local16.frameDelay[arg1.primarySeqFrame]) {
				arg1.primarySeqCycle -= local16.frameDelay[arg1.primarySeqFrame];
				arg1.primarySeqFrame++;
			}
			if (arg1.primarySeqFrame >= local16.frameCount) {
				arg1.primarySeqFrame -= local16.delay;
				arg1.primarySeqPlays++;
				if (arg1.primarySeqPlays >= local16.replays) {
					arg1.primarySeq = -1;
				}
				if (arg1.primarySeqFrame < 0 || arg1.primarySeqFrame >= local16.frameCount) {
					arg1.primarySeq = -1;
				}
			}
			arg1.animationStretches = local16.renderPadding;
		}
		if (arg1.primarySeqDelay > 0) {
			arg1.primarySeqDelay--;
		}
		if (arg1.spotAnimIndex == -1 || clientClock < arg1.lastSpotAnimCycle) {
			return;
		}
		if (arg1.spotAnimFrame < 0) {
			arg1.spotAnimFrame = 0;
		}
		local16 = SpotAnimType.instances[arg1.spotAnimIndex].seq;
		arg1.spotAnimCycle++;
		while (arg1.spotAnimFrame < local16.frameCount && arg1.spotAnimCycle > local16.frameDelay[arg1.spotAnimFrame]) {
			arg1.spotAnimCycle -= local16.frameDelay[arg1.spotAnimFrame];
			arg1.spotAnimFrame++;
		}
		if (arg1.spotAnimFrame >= local16.frameCount) {
			if (arg1.spotAnimFrame < 0 || arg1.spotAnimFrame >= local16.frameCount) {
				arg1.spotAnimIndex = -1;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "r", descriptor = "(I)V")
	private void drawGame() {
		if (this.redrawTitleBackground) {
			this.redrawTitleBackground = false;
			this.backleft1.drawAt(0, 11, super.graphic);
			this.backleft2.drawAt(0, 375, super.graphic);
			this.backright1.drawAt(729, 5, super.graphic);
			this.backright2.drawAt(752, 231, super.graphic);
			this.backtop1.drawAt(0, 0, super.graphic);
			this.backtop2.drawAt(561, 0, super.graphic);
			this.backvmid1.drawAt(520, 11, super.graphic);
			this.backvmid2.drawAt(520, 231, super.graphic);
			this.backvmid3.drawAt(501, 375, super.graphic);
			this.backhmid2.drawAt(0, 345, super.graphic);
			this.sidebarRedraw = true;
			this.redrawChatback = true;
			this.sidebarRedrawIcons = true;
			this.chatRedrawSettings = true;
			if (this.sceneState != 2) {
				this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);
				this.areaMapback.drawAt(561, 5, super.graphic);
			}
		}
		if (this.sceneState == 2) {
			this.drawViewport();
		}
		if (this.menuVisible && this.mouseArea == 1) {
			this.sidebarRedraw = true;
		}
		@Pc(152) boolean local152;
		if (this.sidebarInterfaceId != -1) {
			local152 = this.animateInterface(this.sidebarInterfaceId, this.sceneDelta);
			if (local152) {
				this.sidebarRedraw = true;
			}
		}
		if (this.selectedArea == 2) {
			this.sidebarRedraw = true;
		}
		if (this.objDragArea == 2) {
			this.sidebarRedraw = true;
		}
		if (this.sidebarRedraw) {
			this.drawInventory();
			this.sidebarRedraw = false;
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
			this.areaMapback.drawAt(561, 5, super.graphic);
		}
		if (this.flashingSidebarId != -1) {
			this.sidebarRedrawIcons = true;
		}
		if (this.sidebarRedrawIcons) {
			if (this.flashingSidebarId != -1 && this.flashingSidebarId == this.selectedTab) {
				this.flashingSidebarId = -1;
				this.outBuffer.p1isaac(ClientProt.IF_FLASHING_TAB);
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
			this.areaBackhmid1.drawAt(520, 165, super.graphic);
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
			this.areaBackbase2.drawAt(501, 492, super.graphic);
			this.areaViewport.makeTarget();
		}
		if (this.chatRedrawSettings) {
			this.chatRedrawSettings = false;
			this.areaBackbase1.makeTarget();
			this.backbase1.draw(0, 0);
			this.plain12.drawCentered("Public chat", 57, 33, 0xffffff, true);
			if (this.chatPublicSetting == 0) {
				this.plain12.drawCentered("On", 57, 46, 0xff00, true);
			}
			if (this.chatPublicSetting == 1) {
				this.plain12.drawCentered("Friends", 57, 46, 0xffff00, true);
			}
			if (this.chatPublicSetting == 2) {
				this.plain12.drawCentered("Off", 57, 46, 0xff0000, true);
			}
			if (this.chatPublicSetting == 3) {
				this.plain12.drawCentered("Hide", 57, 46, 0xffff, true);
			}
			this.plain12.drawCentered("Private chat", 186, 33, 0xffffff, true);
			if (this.chatPrivateSetting == 0) {
				this.plain12.drawCentered("On", 186, 46, 0xff00, true);
			}
			if (this.chatPrivateSetting == 1) {
				this.plain12.drawCentered("Friends", 186, 46, 0xffff00, true);
			}
			if (this.chatPrivateSetting == 2) {
				this.plain12.drawCentered("Off", 186, 46, 0xff0000, true);
			}
			this.plain12.drawCentered("Trade/duel", 326, 33, 0xffffff, true);
			if (this.chatTradeDuelSetting == 0) {
				this.plain12.drawCentered("On", 326, 46, 0xff00, true);
			}
			if (this.chatTradeDuelSetting == 1) {
				this.plain12.drawCentered("Friends", 326, 46, 0xffff00, true);
			}
			if (this.chatTradeDuelSetting == 2) {
				this.plain12.drawCentered("Off", 326, 46, 0xff0000, true);
			}
			this.plain12.drawCentered("Report abuse", 462, 38, 0xffffff, true);
			this.areaBackbase1.drawAt(0, 471, super.graphic);
			this.areaViewport.makeTarget();
		}
		this.sceneDelta = 0;
	}

	@OriginalMember(owner = "client!client", name = "e", descriptor = "(II)Z")
	private boolean isFriend(@OriginalArg(1) int arg0) {
		if (arg0 < 0) {
			return false;
		}
		@Pc(8) int local8 = this.optionType[arg0];
		if (local8 >= 2000) {
			local8 -= 2000;
		}
		return local8 == 406;
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(II)V")
	private void useMenuOption(@OriginalArg(1) int option) {
		if (option < 0) {
			return;
		}

		if (this.chatbackInputType) {
			this.chatbackInputType = false;
			this.redrawChatback = true;
		}

		@Pc(28) int action = this.optionType[option];
		@Pc(18) int a = this.optionParamB[option];
		@Pc(23) int b = this.optionParamC[option];
		@Pc(33) int c = this.optionParamA[option];

		if (action >= 2000) {
			action -= 2000;
		}

		@Pc(48) String local48;
		@Pc(52) int local52;

		@Pc(69) String local69;
		@Pc(73) int local73;

		if (action == Cs1Actions.TRADE_PLAYER || action == Cs1Actions.DUEL_PLAYER) {
			local48 = this.options[option];
			local52 = local48.indexOf("@whi@");
			if (local52 != -1) {
				local48 = local48.substring(local52 + 5).trim();
				local69 = StringUtils.formatName(StringUtils.fromBase37(StringUtils.toBase37(local48)));
				@Pc(71) boolean local71 = false;
				for (local73 = 0; local73 < this.playerCount; local73++) {
					@Pc(83) PlayerEntity local83 = this.playerEntities[this.playerIndices[local73]];
					if (local83 != null && local83.name != null && local83.name.equalsIgnoreCase(local69)) {
						this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local83.pathTileZ[0], local83.pathTileX[0], false, 1, 1, 0, 0, 0);
						if (action == Cs1Actions.TRADE_PLAYER) {
							this.outBuffer.p1isaac(ClientProt.OPPLAYER2);
						}
						if (action == Cs1Actions.DUEL_PLAYER) {
							this.outBuffer.p1isaac(ClientProt.OPPLAYER4);
						}
						this.outBuffer.p2(this.playerIndices[local73]);
						local71 = true;
						break;
					}
				}

				if (!local71) {
					this.addMessage(0, "", "Unable to find " + local69);
				}
			}
		}

		if (action == Cs1Actions.OPLOCU && this.interactWithLoc(ClientProt.OPLOCU, a, b, c)) {
			this.outBuffer.p2(this.objInterface);
			this.outBuffer.p2(this.selectedObjSlot);
			this.outBuffer.p2(this.selectedObjInterface);
		}

		if (action == Cs1Actions.OPHELD1 || action == Cs1Actions.OPHELD2 || action == Cs1Actions.OPHELD3 || action == Cs1Actions.OPHELD4 || action == Cs1Actions.OPHELD5) {
			if (action == Cs1Actions.OPHELD4) {
				if ((a & 0x3) == 0) {
					opHeld1Counter++;
				}
				if (opHeld1Counter >= 90) {
					this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPHELD4);
				}
				this.outBuffer.p1isaac(ClientProt.OPHELD4);
			} else if (action == Cs1Actions.OPHELD5) {
				this.outBuffer.p1isaac(ClientProt.OPHELD5);
			} else if (action == Cs1Actions.OPHELD3) {
				this.outBuffer.p1isaac(ClientProt.OPHELD3);
			} else if (action == Cs1Actions.OPHELD1) {
				opHeld4Counter += c;
				if (opHeld4Counter >= 97) {
					this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPHELD1);
					this.outBuffer.p3(14953816);
				}
				this.outBuffer.p1isaac(ClientProt.OPHELD1);
			} else if (action == Cs1Actions.OPHELD2) {
				this.outBuffer.p1isaac(ClientProt.OPHELD2);
			}

			this.outBuffer.p2(c);
			this.outBuffer.p2(a);
			this.outBuffer.p2(b);
			this.selectedCycle = 0;
			this.selectedInterface = b;
			this.selectedItem = a;
			this.selectedArea = 2;

			if (Component.instances[b].parent == this.viewportInterfaceIndex) {
				this.selectedArea = 1;
			}

			if (Component.instances[b].parent == this.chatbackComponentId) {
				this.selectedArea = 3;
			}
		}

		@Pc(345) NpcEntity local345;
		if (action == Cs1Actions.OPNPC1 || action == Cs1Actions.OPNPC2 || action == Cs1Actions.OPNPC3 || action == Cs1Actions.OPNPC4 || action == Cs1Actions.OPNPC5) {
			local345 = this.npcEntities[c];
			if (local345 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local345.pathTileZ[0], local345.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;

				if (action == Cs1Actions.OPNPC2) {
					this.outBuffer.p1isaac(ClientProt.OPNPC2);
				} else if (action == Cs1Actions.OPNPC3) {
					if ((c & 0x3) == 0) {
						opNpc3Counter++;
					}
					if (opNpc3Counter >= 124) {
						this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPNPC3);
						this.outBuffer.p4(0);
					}
					this.outBuffer.p1isaac(ClientProt.OPNPC3);
				} else if (action == Cs1Actions.OPNPC4) {
					this.outBuffer.p1isaac(ClientProt.OPNPC4);
				} else if (action == Cs1Actions.OPNPC1) {
					this.outBuffer.p1isaac(ClientProt.OPNPC1);
				} else if (action == Cs1Actions.OPNPC5) {
					if ((c & 0x3) == 0) {
						opNpc5Counter++;
					}
					if (opNpc5Counter >= 85) {
						this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPNPC5);
						this.outBuffer.p2(39596);
					}
					this.outBuffer.p1isaac(ClientProt.OPNPC5);
				}

				this.outBuffer.p2(c);
			}
		}

		@Pc(500) boolean local500;
		if (action == Cs1Actions.OPOBJU) {
			local500 = this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 0, 0, 0, 0, 0);
			if (!local500) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 1, 1, 0, 0, 0);
			}
			this.crossX = super.clickX;
			this.crossY = super.clickY;
			this.crossType = 2;
			this.crossCycle = 0;
			this.outBuffer.p1isaac(ClientProt.OPOBJU);
			this.outBuffer.p2(a + this.baseTileX);
			this.outBuffer.p2(b + this.baseTileZ);
			this.outBuffer.p2(c);
			this.outBuffer.p2(this.objInterface);
			this.outBuffer.p2(this.selectedObjSlot);
			this.outBuffer.p2(this.selectedObjInterface);
		}

		if (action == Cs1Actions.EXAMINE_LOC) {
			@Pc(586) int local586 = c >> 14 & 0x7FFF;
			@Pc(589) LocType local589 = LocType.get(local586);
			if (local589.desc == null) {
				local69 = "It's a " + local589.name + ".";
			} else {
				local69 = local589.desc;
			}
			this.addMessage(0, "", local69);
		}

		if (action == Cs1Actions.OPLOC1) {
			this.interactWithLoc(ClientProt.OPLOC1, a, b, c);
		}

		if (action == Cs1Actions.OPHELDU) {
			this.outBuffer.p1isaac(ClientProt.OPHELDU);
			this.outBuffer.p2(c);
			this.outBuffer.p2(a);
			this.outBuffer.p2(b);
			this.outBuffer.p2(this.objInterface);
			this.outBuffer.p2(this.selectedObjSlot);
			this.outBuffer.p2(this.selectedObjInterface);
			this.selectedCycle = 0;
			this.selectedInterface = b;
			this.selectedItem = a;
			this.selectedArea = 2;

			if (Component.instances[b].parent == this.viewportInterfaceIndex) {
				this.selectedArea = 1;
			}

			if (Component.instances[b].parent == this.chatbackComponentId) {
				this.selectedArea = 3;
			}
		}

		if (action == Cs1Actions.OPHELDT) {
			this.outBuffer.p1isaac(ClientProt.OPHELDT);
			this.outBuffer.p2(c);
			this.outBuffer.p2(a);
			this.outBuffer.p2(b);
			this.outBuffer.p2(this.spellInterface);
			this.selectedCycle = 0;
			this.selectedInterface = b;
			this.selectedItem = a;
			this.selectedArea = 2;

			if (Component.instances[b].parent == this.viewportInterfaceIndex) {
				this.selectedArea = 1;
			}

			if (Component.instances[b].parent == this.chatbackComponentId) {
				this.selectedArea = 3;
			}
		}

		if (action == Cs1Actions.WALK) {
			if (this.menuVisible) {
				this.mapSquare.setClick(b - 11, a - 8);
			} else {
				this.mapSquare.setClick(super.clickY - 11, super.clickX - 8);
			}
		}

		if (action == Cs1Actions.IF_BUTTONT) {
			this.selectedObject = 1;
			this.selectedObjSlot = a;
			this.selectedObjInterface = b;
			this.objInterface = c;
			this.selectedObjName = ObjType.get(c).name;
			this.selectedSpell = 0;
			return;
		}

		if (action == Cs1Actions.IF_PAUSEBUTTON && !this.chatContinuingDialogue) {
			this.outBuffer.p1isaac(ClientProt.RESUME_PAUSEBUTTON);
			this.outBuffer.p2(b);
			this.chatContinuingDialogue = true;
		}

		@Pc(830) ObjType local830;
		@Pc(845) String local845;
		if (action == Cs1Actions.EXAMINE_OBJ) {
			local830 = ObjType.get(c);
			if (b >= 100000) {
				local845 = b + " x " + local830.name;
			} else if (local830.description == null) {
				local845 = "It's a " + local830.name + ".";
			} else {
				local845 = new String(local830.description);
			}
			this.addMessage(0, "", local845);
		}

		if (action == Cs1Actions.OPNPCU) {
			local345 = this.npcEntities[c];
			if (local345 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local345.pathTileZ[0], local345.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				this.outBuffer.p1isaac(ClientProt.OPNPCU);
				this.outBuffer.p2(c);
				this.outBuffer.p2(this.objInterface);
				this.outBuffer.p2(this.selectedObjSlot);
				this.outBuffer.p2(this.selectedObjInterface);
			}
		}

		@Pc(969) PlayerEntity local969;
		if (action == Cs1Actions.OPPLAYER2 || action == Cs1Actions.OPPLAYER1 || action == Cs1Actions.OPPLAYER3 || action == Cs1Actions.OPPLAYER4) {
			local969 = this.playerEntities[c];
			if (local969 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local969.pathTileZ[0], local969.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				if (action == Cs1Actions.OPPLAYER4) {
					this.outBuffer.p1isaac(ClientProt.OPPLAYER4);
				}
				if (action == Cs1Actions.OPPLAYER3) {
					opPlayer2Counter++;
					if (opPlayer2Counter >= 90) {
						this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPPLAYER3);
						this.outBuffer.p2(31114);
					}
					this.outBuffer.p1isaac(ClientProt.OPPLAYER3);
				}
				if (action == Cs1Actions.OPPLAYER2) {
					this.outBuffer.p1isaac(ClientProt.OPPLAYER2);
				}
				if (action == Cs1Actions.OPPLAYER1) {
					this.outBuffer.p1isaac(ClientProt.OPPLAYER1);
				}
				this.outBuffer.p2(c);
			}
		}

		if (action == Cs1Actions.OPNPCT) {
			local345 = this.npcEntities[c];
			if (local345 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local345.pathTileZ[0], local345.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				this.outBuffer.p1isaac(ClientProt.OPNPCT);
				this.outBuffer.p2(c);
				this.outBuffer.p2(this.spellInterface);
			}
		}

		@Pc(1156) long local1156;
		if (action == Cs1Actions.MESSAGE_FRIEND) {
			local48 = this.options[option];
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
					this.chatbackInputType = false;
					this.showSocialInput = true;
					this.socialInput = "";
					this.socialAction = 3;
					this.socialName37 = this.friendName37[local73];
					this.socialMessage = "Enter message to send to " + this.friendName[local73];
				}
			}
		}

		if (action == Cs1Actions.OPLOCT && this.interactWithLoc(ClientProt.OPLOCT, a, b, c)) {
			this.outBuffer.p2(this.spellInterface);
		}

		if (action == Cs1Actions.OPOBJ1 || action == Cs1Actions.OPOBJ2 || action == Cs1Actions.OPOBJ3 || action == Cs1Actions.OPOBJ4 || action == Cs1Actions.OPOBJ5) {
			local500 = this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 0, 0, 0, 0, 0);
			if (!local500) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 1, 1, 0, 0, 0);
			}

			this.crossX = super.clickX;
			this.crossY = super.clickY;
			this.crossType = 2;
			this.crossCycle = 0;

			if (action == Cs1Actions.OPOBJ1) {
				this.outBuffer.p1isaac(ClientProt.OPOBJ1);
			}
			if (action == Cs1Actions.OPOBJ4) {
				this.outBuffer.p1isaac(ClientProt.OPOBJ4);
			}
			if (action == Cs1Actions.OPOBJ5) {
				this.outBuffer.p1isaac(ClientProt.OPOBJ5);
			}
			if (action == Cs1Actions.OPOBJ3) {
				this.outBuffer.p1isaac(ClientProt.OPOBJ3);
			}
			if (action == Cs1Actions.OPOBJ2) {
				this.outBuffer.p1isaac(ClientProt.OPOBJ2);
			}
			this.outBuffer.p2(a + this.baseTileX);
			this.outBuffer.p2(b + this.baseTileZ);
			this.outBuffer.p2(c);
		}

		if (action == Cs1Actions.EXAMINE_NPC) {
			local345 = this.npcEntities[c];
			if (local345 != null) {
				if (local345.info.description == null) {
					local845 = "It's a " + local345.info.name + ".";
				} else {
					local845 = new String(local345.info.description);
				}
				this.addMessage(0, "", local845);
			}
		}

		if (action == Cs1Actions.OPLOC2) {
			this.interactWithLoc(ClientProt.OPLOC2, a, b, c);
		}

		@Pc(1429) Component local1429;
		if (action == Cs1Actions.IF_TARGETBUTTON) {
			local1429 = Component.instances[b];
			this.selectedSpell = 1;
			this.spellInterface = b;
			this.selectedFlags = local1429.optionFlags;
			this.selectedObject = 0;

			local845 = local1429.optionCircumfix;
			if (local845.contains(" ")) {
				local845 = local845.substring(0, local845.indexOf(" "));
			}

			local69 = local1429.optionCircumfix;
			if (local69.contains(" ")) {
				local69 = local69.substring(local69.indexOf(" ") + 1);
			}

			this.selectedSpellPrefix = local845 + " " + local1429.optionSuffix + " " + local69;

			if (this.selectedFlags == 16) {
				this.sidebarRedraw = true;
				this.selectedTab = 3;
				this.sidebarRedrawIcons = true;
			}
			return;
		}

		if (action == Cs1Actions.IF_BUTTON) {
			local1429 = Component.instances[b];

			@Pc(1513) boolean local1513 = true;
			if (local1429.clientCode > 0) {
				local1513 = this.handleComponentAction(local1429);
			}

			if (local1513) {
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON);
				this.outBuffer.p2(b);
			}
		}

		if (action == Cs1Actions.IF_BUTTON1 || action == Cs1Actions.IF_BUTTON2 || action == Cs1Actions.IF_BUTTON3 || action == Cs1Actions.IF_BUTTON4 || action == Cs1Actions.IF_BUTTON5) {
			if (action == Cs1Actions.IF_BUTTON3) {
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON3);
			} else if (action == Cs1Actions.IF_BUTTON5) {
				if ((b & 0x3) == 0) {
					ifButton5Counter++;
				}
				if (ifButton5Counter >= 55) {
					this.outBuffer.p1isaac(ClientProt.ANTICHEAT_IF_BUTTON5);
					this.outBuffer.p4(0);
				}
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON5);
			} else if (action == Cs1Actions.IF_BUTTON1) {
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON1);
			} else if (action == Cs1Actions.IF_BUTTON4) {
				if ((a & 0x3) == 0) {
					opHeld9Counter++;
				}
				if (opHeld9Counter >= 130) {
					this.outBuffer.p1isaac(ClientProt.ANTICHEAT_IF_BUTTON4);
					this.outBuffer.p1(177);
				}
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON4);
			} else if (action == Cs1Actions.IF_BUTTON2) {
				this.outBuffer.p1isaac(ClientProt.IF_BUTTON2);
			}

			this.outBuffer.p2(c);
			this.outBuffer.p2(a);
			this.outBuffer.p2(b);
			this.selectedCycle = 0;
			this.selectedInterface = b;
			this.selectedItem = a;
			this.selectedArea = 2;

			if (Component.instances[b].parent == this.viewportInterfaceIndex) {
				this.selectedArea = 1;
			}

			if (Component.instances[b].parent == this.chatbackComponentId) {
				this.selectedArea = 3;
			}
		}

		if (action == Cs1Actions.OPLOC4) {
			if ((c & 0x3) == 0) {
				opLoc4Counter++;
			}
			if (opLoc4Counter >= 99) {
				this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPLOC4);
				this.outBuffer.p4(0);
			}
			this.interactWithLoc(ClientProt.OPLOC4, a, b, c);
		}

		if (action == Cs1Actions.OPOBJT) {
			local500 = this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 0, 0, 0, 0, 0);
			if (!local500) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], b, a, false, 1, 1, 0, 0, 0);
			}

			this.crossX = super.clickX;
			this.crossY = super.clickY;
			this.crossType = 2;
			this.crossCycle = 0;
			this.outBuffer.p1isaac(ClientProt.OPOBJT);
			this.outBuffer.p2(a + this.baseTileX);
			this.outBuffer.p2(b + this.baseTileZ);
			this.outBuffer.p2(c);
			this.outBuffer.p2(this.spellInterface);
		}

		if (action == Cs1Actions.OPLOC5) {
			opLoc5Counter += this.baseTileZ;
			if (opLoc5Counter >= 92) {
				this.outBuffer.p1isaac(ClientProt.ANTICHEAT_OPLOC5);
				this.outBuffer.p4(0);
			}
			this.interactWithLoc(ClientProt.OPLOC5, a, b, c);
		}

		if (action == Cs1Actions.OPLOC3) {
			this.interactWithLoc(ClientProt.OPLOC3, a, b, c);
		}

		if (action == Cs1Actions.EXAMINE_OBJSTACK) {
			local830 = ObjType.get(c);
			if (local830.description == null) {
				local845 = "It's a " + local830.name + ".";
			} else {
				local845 = new String(local830.description);
			}
			this.addMessage(0, "", local845);
		}

		if (action == Cs1Actions.IF_SELECTBUTTON) {
			this.outBuffer.p1isaac(ClientProt.IF_BUTTON);
			this.outBuffer.p2(b);
			local1429 = Component.instances[b];
			if (local1429.script != null && local1429.script[0][0] == 5) {
				local52 = local1429.script[0][1];
				if (this.variables[local52] != local1429.scriptCompareValue[0]) {
					this.variables[local52] = local1429.scriptCompareValue[0];
					this.updateVarp(local52);
					this.sidebarRedraw = true;
				}
			}
		}

		if (action == Cs1Actions.REPORT_ABUSE) {
			local48 = this.options[option];
			local52 = local48.indexOf("@whi@");
			if (local52 != -1) {
				this.closeInterface();
				this.reportInput = local48.substring(local52 + 5).trim();
				this.reportAbuseMuteToggle = false;
				for (@Pc(1957) int local1957 = 0; local1957 < Component.instances.length; local1957++) {
					if (Component.instances[local1957] != null && Component.instances[local1957].clientCode == 600) {
						this.openInterfaceId = this.viewportInterfaceIndex = Component.instances[local1957].parent;
						break;
					}
				}
			}
		}

		if (action == Cs1Actions.IF_CLOSEBUTTON) {
			this.closeInterface();
		}

		if (action == Cs1Actions.OPPLAYERU) {
			local969 = this.playerEntities[c];
			if (local969 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local969.pathTileZ[0], local969.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				this.outBuffer.p1isaac(ClientProt.OPPLAYERU);
				this.outBuffer.p2(c);
				this.outBuffer.p2(this.objInterface);
				this.outBuffer.p2(this.selectedObjSlot);
				this.outBuffer.p2(this.selectedObjInterface);
			}
		}

		if (action == Cs1Actions.IF_TOGGLEBUTTON) {
			this.outBuffer.p1isaac(ClientProt.IF_BUTTON);
			this.outBuffer.p2(b);
			local1429 = Component.instances[b];
			if (local1429.script != null && local1429.script[0][0] == 5) {
				local52 = local1429.script[0][1];
				this.variables[local52] = 1 - this.variables[local52];
				this.updateVarp(local52);
				this.sidebarRedraw = true;
			}
		}

		if (action == Cs1Actions.ADD_FRIEND || action == Cs1Actions.ADD_IGNORE || action == Cs1Actions.DEL_FRIEND || action == Cs1Actions.DEL_IGNORE) {
			local48 = this.options[option];
			local52 = local48.indexOf("@whi@");
			if (local52 != -1) {
				local1156 = StringUtils.toBase37(local48.substring(local52 + 5).trim());
				if (action == Cs1Actions.ADD_FRIEND) {
					this.addFriend(local1156);
				}
				if (action == Cs1Actions.ADD_IGNORE) {
					this.addIgnore(local1156);
				}
				if (action == Cs1Actions.DEL_FRIEND) {
					this.removeFriend(local1156);
				}
				if (action == Cs1Actions.DEL_IGNORE) {
					this.removeIgnore(local1156);
				}
			}
		}

		if (action == Cs1Actions.OPPLAYERT) {
			local969 = this.playerEntities[c];
			if (local969 != null) {
				this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], local969.pathTileZ[0], local969.pathTileX[0], false, 1, 1, 0, 0, 0);
				this.crossX = super.clickX;
				this.crossY = super.clickY;
				this.crossType = 2;
				this.crossCycle = 0;
				this.outBuffer.p1isaac(ClientProt.OPPLAYERT);
				this.outBuffer.p2(c);
				this.outBuffer.p2(this.spellInterface);
			}
		}

		this.selectedObject = 0;
		this.selectedSpell = 0;
	}

	@OriginalMember(owner = "client!client", name = "s", descriptor = "(I)Ljava/lang/String;")
	private String getHost() {
		if (signlink.mainapp == null) {
			return super.frame == null ? super.getDocumentBase().getHost().toLowerCase() : "runescape.com";
		} else {
			return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
		}
	}

	@OriginalMember(owner = "client!client", name = "t", descriptor = "(I)V")
	private void drawMenu() {
		@Pc(2) int x = this.menuX;
		@Pc(5) int y = this.menuY;
		@Pc(8) int w = this.menuWidth;
		@Pc(11) int h = this.menuHeight;

		Draw2D.fillRect(x, y, w, h, 0x5d5447);
		Draw2D.fillRect(x + 1, y + 1, w - 2, 16, 0);
		Draw2D.drawRect(x + 1, y + 18, w - 2, h - 19, 0);
		this.bold12.draw(x + 3, y + 14, 0x5d5447, "Choose Option");

		@Pc(63) int mx = super.mouseX;
		@Pc(66) int my = super.mouseY;
		if (this.mouseArea == 0) {
			mx -= 8;
			my -= 11;
		} else if (this.mouseArea == 1) {
			mx -= 562;
			my -= 231;
		} else if (this.mouseArea == 2) {
			mx -= 22;
			my -= 375;
		}

		for (@Pc(85) int i = 0; i < this.optionCount; i++) {
			@Pc(100) int optionY = y + (this.optionCount - 1 - i) * 15 + 31;
			@Pc(102) int rgb = 0xffffff;
			if (mx > x && mx < x + w && my > optionY - 13 && my < optionY + 3) {
				rgb = 0xffff00;
			}

			this.bold12.draw(this.options[i], x + 3, optionY, rgb, true);
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(III)V")
	private void handleChatInput(@OriginalArg(0) int x, @OriginalArg(2) int y) {
		if (this.splitPrivateChat == 0) {
			return;
		}

		@Pc(5) int local5 = 0;
		if (this.systemUpdateTimer != 0) {
			local5 = 1;
		}

		for (@Pc(12) int local12 = 0; local12 < 100; local12++) {
			if (this.chatMessage[local12] != null) {
				@Pc(24) int local24 = this.chatMessageType[local12];
				if ((local24 == 3 || local24 == 7) && (local24 == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[local12]))) {
					@Pc(54) int local54 = 329 - local5 * 13;
					if (super.mouseX > 8 && super.mouseX < 520 && y - 11 > local54 - 10 && y - 11 <= local54 + 3) {
						if (this.rights) {
							this.options[this.optionCount] = "Report abuse @whi@" + this.chatMessagePrefix[local12];
							this.optionType[this.optionCount] = 2000 + Cs1Actions.REPORT_ABUSE;
							this.optionCount++;
						}
						this.options[this.optionCount] = "Add ignore @whi@" + this.chatMessagePrefix[local12];
						this.optionType[this.optionCount] = 2000 + Cs1Actions.ADD_IGNORE;
						this.optionCount++;
						this.options[this.optionCount] = "Add friend @whi@" + this.chatMessagePrefix[local12];
						this.optionType[this.optionCount] = 2000 + Cs1Actions.ADD_FRIEND;
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

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!hc;)V")
	private void updateComponentContent(@OriginalArg(1) Component parent) {
		@Pc(4) int type = parent.clientCode;
		if (type >= 1 && type <= 100) {
			type--;
			if (type >= this.friendCount) {
				parent.text = "";
				parent.buttonType = Component.NO_BUTTON;
			} else {
				parent.text = this.friendName[type];
				parent.buttonType = Component.BUTTON;
			}
		} else if (type >= 101 && type <= 200) {
			type -= 101;
			if (type >= this.friendCount) {
				parent.text = "";
				parent.buttonType = Component.NO_BUTTON;
			} else {
				if (this.friendWorld[type] == 0) {
					parent.text = "@red@Offline";
				} else if (this.friendWorld[type] == nodeId) {
					parent.text = "@gre@World-" + (this.friendWorld[type] - 9);
				} else {
					parent.text = "@yel@World-" + (this.friendWorld[type] - 9);
				}
				parent.buttonType = Component.BUTTON;
			}
		} else if (type == 203) {
			parent.scrollHeight = this.friendCount * 15 + 20;
			if (parent.scrollHeight <= parent.height) {
				parent.scrollHeight = parent.height + 1;
			}
		} else if (type >= 401 && type <= 500) {
			type -= 401;
			if (type >= this.ignoreCount) {
				parent.text = "";
				parent.buttonType = Component.NO_BUTTON;
			} else {
				parent.text = StringUtils.formatName(StringUtils.fromBase37(this.ignoreName37[type]));
				parent.buttonType = Component.BUTTON;
			}
		} else if (type == 503) {
			parent.scrollHeight = this.ignoreCount * 15 + 20;
			if (parent.scrollHeight <= parent.height) {
				parent.scrollHeight = parent.height + 1;
			}
		} else if (type == 327) {
			parent.modelEyePitch = 150;
			parent.modelYaw = (int) (Math.sin((double) clientClock / 40.0D) * 256.0D) & 0x7FF;
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
				local241.applyGroup();
				local241.applyFrame(SeqType.instances[this.self.standSeq].primaryFrames[0]);
				local241.applyLighting(64, 850, -30, -50, -30, true);
				parent.modelDisabled = local241;
			}
		} else if (type == 324) {
			if (this.sprite == null) {
				this.sprite = parent.sprite;
				this.spriteActive = parent.activeSprite;
			}
			if (this.characterDesignIsMale) {
				parent.sprite = this.spriteActive;
			} else {
				parent.sprite = this.sprite;
			}
		} else if (type == 325) {
			if (this.sprite == null) {
				this.sprite = parent.sprite;
				this.spriteActive = parent.activeSprite;
			}
			if (this.characterDesignIsMale) {
				parent.sprite = this.sprite;
			} else {
				parent.sprite = this.spriteActive;
			}
		} else if (type == 600) {
			parent.text = this.reportInput;
			if (clientClock % 20 < 10) {
				parent.text = parent.text + "|";
			} else {
				parent.text = parent.text + " ";
			}
		} else {
			if (type == 613) {
				if (!this.rights) {
					parent.text = "";
				} else if (this.reportAbuseMuteToggle) {
					parent.color = 0xff0000;
					parent.text = "Moderator option: Mute player for 48 hours: <ON>";
				} else {
					parent.color = 0xffffff;
					parent.text = "Moderator option: Mute player for 48 hours: <OFF>";
				}
			}
			@Pc(441) String local441;
			if (type == 650 || type == 655) {
				if (this.lastLoginIp == 0) {
					parent.text = "";
				} else {
					if (this.daysSinceLogin == 0) {
						local441 = "earlier today";
					} else if (this.daysSinceLogin == 1) {
						local441 = "yesterday";
					} else {
						local441 = this.daysSinceLogin + " days ago";
					}
					parent.text = "You last logged in " + local441 + " from: " + signlink.dns;
				}
			}
			if (type == 651) {
				if (this.unreadMessageCount == 0) {
					parent.text = "0 unread messages";
					parent.color = 0xffff00;
				}
				if (this.unreadMessageCount == 1) {
					parent.text = "1 unread message";
					parent.color = 0xff00;
				}
				if (this.unreadMessageCount > 1) {
					parent.text = this.unreadMessageCount + " unread messages";
					parent.color = 0xff00;
				}
			}
			if (type == 652) {
				if (this.daysSinceRecoveryChange == 201) {
					parent.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					parent.text = "You have not yet set any password recovery questions.";
				} else {
					if (this.daysSinceRecoveryChange == 0) {
						local441 = "Earlier today";
					} else if (this.daysSinceRecoveryChange == 1) {
						local441 = "Yesterday";
					} else {
						local441 = this.daysSinceRecoveryChange + " days ago";
					}
					parent.text = local441 + " you changed your recovery questions";
				}
			}
			if (type == 653) {
				if (this.daysSinceRecoveryChange == 201) {
					parent.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					parent.text = "We strongly recommend you do so now to secure your account.";
				} else {
					parent.text = "If you do not remember making this change then cancel it immediately";
				}
			}
			if (type == 654) {
				if (this.daysSinceRecoveryChange == 201) {
					parent.text = "";
				} else if (this.daysSinceRecoveryChange == 200) {
					parent.text = "Do this from the 'account management' area on our front webpage";
				} else {
					parent.text = "Do this from the 'account management' area on our front webpage";
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([BII)Z")
	private boolean wavesave(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1) {
		return arg0 == null || signlink.wavesave(arg0, arg1);
	}

	@OriginalMember(owner = "client!client", name = "u", descriptor = "(I)Z")
	private boolean wavereplay() {
		return signlink.wavereplay();
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(II)V")
	private void wavevol(@OriginalArg(0) int volume) {
		signlink.wavevol = volume;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!kb;I)V")
	private void updateLocalNpcs(@OriginalArg(1) Buffer b, @OriginalArg(2) int size) {
		while (true) {
			if (b.bitOffset + 21 >= size * 8) {
				break;
			}

			@Pc(16) int nid = b.gBit(13);
			if (nid == 8191) {
				break;
			}

			if (this.npcEntities[nid] == null) {
				this.npcEntities[nid] = new NpcEntity();
			}

			@Pc(36) NpcEntity npc = this.npcEntities[nid];
			this.npcIndices[this.npcCount++] = nid;
			npc.removeTimer = clientClock;
			npc.info = NpcType.get(b.gBit(11));
			npc.index = npc.info.size;
			npc.runSeq = npc.info.walkSeq;
			npc.walkSeq = npc.info.turnAroundSeq;
			npc.turnAroundSeq = npc.info.turnRightSeq;
			npc.turnRightSeq = npc.info.turnLeftSeq;
			npc.standSeq = npc.info.standSeq;

			@Pc(92) int x = b.gBit(5);
			if (x > 15) {
				x -= 32;
			}

			@Pc(101) int z = b.gBit(5);
			if (z > 15) {
				z -= 32;
			}

			npc.move(false, this.self.pathTileX[0] + x, this.self.pathTileZ[0] + z);

			@Pc(128) int hasMaskUpdate = b.gBit(1);
			if (hasMaskUpdate == 1) {
				this.entityUpdateIndices[this.updateCount++] = nid;
			}
		}

		b.accessBytes();
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLclient!hc;)Z")
	private boolean handleComponentAction(@OriginalArg(1) Component parent) {
		@Pc(4) int type = parent.clientCode;
		if (type == 201) {
			this.redrawChatback = true;
			this.chatbackInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 1;
			this.socialMessage = "Enter name of friend to add to list";
		}
		if (type == 202) {
			this.redrawChatback = true;
			this.chatbackInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 2;
			this.socialMessage = "Enter name of friend to delete from list";
		}
		if (type == 205) {
			this.idleTimeout = 250;
			return true;
		}
		if (type == 501) {
			this.redrawChatback = true;
			this.chatbackInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 4;
			this.socialMessage = "Enter name of player to add to list";
		}
		if (type == 502) {
			this.redrawChatback = true;
			this.chatbackInputType = false;
			this.showSocialInput = true;
			this.socialInput = "";
			this.socialAction = 5;
			this.socialMessage = "Enter name of player to delete from list";
		}
		@Pc(112) int local112;
		@Pc(116) int local116;
		@Pc(121) int local121;
		if (type >= 300 && type <= 313) {
			local112 = (type - 300) / 2;
			local116 = type & 0x1;
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
					if (!IdkType.instances[local121].validStyle && IdkType.instances[local121].type == local112 + (this.characterDesignIsMale ? 0 : 7)) {
						this.characterDesigns[local112] = local121;
						this.characterDesignUpdate = true;
						break;
					}
				}
			}
		}
		if (type >= 314 && type <= 323) {
			local112 = (type - 314) / 2;
			local116 = type & 0x1;
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
		if (type == 324 && !this.characterDesignIsMale) {
			this.characterDesignIsMale = true;
			this.resetCharacterDesign();
		}
		if (type == 325 && this.characterDesignIsMale) {
			this.characterDesignIsMale = false;
			this.resetCharacterDesign();
		}
		if (type == 326) {
			this.outBuffer.p1isaac(ClientProt.IF_DESIGN);
			this.outBuffer.p1(this.characterDesignIsMale ? 0 : 1);
			for (local112 = 0; local112 < 7; local112++) {
				this.outBuffer.p1(this.characterDesigns[local112]);
			}
			for (local116 = 0; local116 < 5; local116++) {
				this.outBuffer.p1(this.characterDesignColors[local116]);
			}
			return true;
		}
		if (type == 613) {
			this.reportAbuseMuteToggle = !this.reportAbuseMuteToggle;
		}
		if (type >= 601 && type <= 612) {
			this.closeInterface();
			if (this.reportInput.length() > 0) {
				this.outBuffer.p1isaac(ClientProt.BUG_REPORT);
				this.outBuffer.p8(StringUtils.toBase37(this.reportInput));
				this.outBuffer.p1(type - 601);
				this.outBuffer.p1(this.reportAbuseMuteToggle ? 1 : 0);
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "()V")
	@Override
	protected final void load() {
		if (signlink.sunjava) {
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
		@Pc(34) boolean badHost = false;
		@Pc(38) String host = this.getHost();
		if (host.endsWith("jagex.com")) {
			badHost = true;
		}
		if (host.endsWith("runescape.com")) {
			badHost = true;
		}
		if (host.endsWith("192.168.1.2")) {
			badHost = true;
		}
		if (host.endsWith("192.168.1.249")) {
			badHost = true;
		}
		if (host.endsWith("192.168.1.252")) {
			badHost = true;
		}
		if (host.endsWith("192.168.1.253")) {
			badHost = true;
		}
		if (host.endsWith("192.168.1.254")) {
			badHost = true;
		}
		if (host.endsWith("127.0.0.1")) {
			badHost = true;
		}
		if (!badHost) {
			this.errorHost = true;
			return;
		}
		try {
			@Pc(94) int nextSecs = 5;
			this.archiveChecksums[8] = 0;
			while (this.archiveChecksums[8] == 0) {
				this.showProgress("Connecting to fileserver", 10);
				try {
					@Pc(119) DataInputStream dis = this.openStream("crc" + (int) (Math.random() * 9.9999999E7D));
					@Pc(126) Buffer buf = new Buffer(new byte[36]);
					dis.readFully(buf.data, 0, 36);
					for (@Pc(134) int i = 0; i < 9; i++) {
						this.archiveChecksums[i] = buf.g4();
					}
					dis.close();
				} catch (@Pc(150) IOException local150) {
					for (@Pc(152) int secs = nextSecs; secs > 0; secs--) {
						this.showProgress("Error loading - Will retry in " + secs + " secs.", 10);
						try {
							Thread.sleep(1000L);
						} catch (@Pc(171) Exception ignored) {
						}
					}
					nextSecs *= 2;
					if (nextSecs > 60) {
						nextSecs = 60;
					}
				}
			}
			this.title = this.loadArchive("title screen", this.archiveChecksums[1], "title", 10);
			this.plain11 = new Font(this.title, "p11");
			this.plain12 = new Font(this.title, "p12");
			this.bold12 = new Font(this.title, "b12");
			this.quill8 = new Font(this.title, "q8");
			this.loadTitleBackground();
			this.loadTitleForeground();
			@Pc(255) FileArchive config = this.loadArchive("config", this.archiveChecksums[2], "config", 15);
			@Pc(266) FileArchive interfaceArchive = this.loadArchive("interface", this.archiveChecksums[3], "interface", 20);
			@Pc(277) FileArchive media = this.loadArchive("2d graphics", this.archiveChecksums[4], "media", 30);
			@Pc(288) FileArchive models = this.loadArchive("3d graphics", this.archiveChecksums[5], "models", 40);
			@Pc(299) FileArchive textures = this.loadArchive("textures", this.archiveChecksums[6], "textures", 60);
			@Pc(310) FileArchive wordenc = this.loadArchive("chat system", this.archiveChecksums[7], "wordenc", 65);
			@Pc(321) FileArchive sounds = this.loadArchive("sound effects", this.archiveChecksums[8], "sounds", 70);
			this.levelRenderFlags = new byte[4][104][104];
			this.levelHeightMaps = new int[4][105][105];
			this.mapSquare = new MapSquare(this.levelHeightMaps, 104, 4, 104);
			for (@Pc(346) int plane = 0; plane < 4; plane++) {
				this.collisionMaps[plane] = new CollisionMap(104, 104);
			}
			this.minimap = new Sprite(512, 512);
			this.showProgress("Unpacking media", 75);
			this.invback = new IndexedSprite(media, "invback", 0);
			this.chatback = new IndexedSprite(media, "chatback", 0);
			this.mapback = new IndexedSprite(media, "mapback", 0);
			this.backbase1 = new IndexedSprite(media, "backbase1", 0);
			this.backbase2 = new IndexedSprite(media, "backbase2", 0);
			this.backhmid1 = new IndexedSprite(media, "backhmid1", 0);
			for (@Pc(424) int i = 0; i < 13; i++) {
				this.sideicons[i] = new IndexedSprite(media, "sideicons", i);
			}
			this.compass = new Sprite(media, "compass", 0);
			@Pc(450) int i;
			try {
				for (i = 0; i < 50; i++) {
					if (i == 22) {
						continue;
					}

					this.mapscene[i] = new IndexedSprite(media, "mapscene", i);
				}
			} catch (@Pc(468) Exception ignored) {
			}
			try {
				for (i = 0; i < 50; i++) {
					this.mapfunction[i] = new Sprite(media, "mapfunction", i);
				}
			} catch (@Pc(488) Exception ignored) {
			}
			try {
				for (i = 0; i < 20; i++) {
					this.hitmarks[i] = new Sprite(media, "hitmarks", i);
				}
			} catch (@Pc(508) Exception ignored) {
			}
			try {
				for (i = 0; i < 20; i++) {
					this.headicons[i] = new Sprite(media, "headicons", i);
				}
			} catch (@Pc(528) Exception ignored) {
			}
			this.mapflags = new Sprite(media, "mapflag", 0);
			for (i = 0; i < 8; i++) {
				this.cross[i] = new Sprite(media, "cross", i);
			}
			this.mapdot0 = new Sprite(media, "mapdots", 0);
			this.mapdot1 = new Sprite(media, "mapdots", 1);
			this.mapdot2 = new Sprite(media, "mapdots", 2);
			this.mapdot3 = new Sprite(media, "mapdots", 3);
			this.scrollbar1 = new IndexedSprite(media, "scrollbar", 0);
			this.scrollbar2 = new IndexedSprite(media, "scrollbar", 1);
			this.redstone1 = new IndexedSprite(media, "redstone1", 0);
			this.redstone2 = new IndexedSprite(media, "redstone2", 0);
			this.redstone3 = new IndexedSprite(media, "redstone3", 0);

			this.redstone1h = new IndexedSprite(media, "redstone1", 0);
			this.redstone1h.flipHorizontally();

			this.redstone2h = new IndexedSprite(media, "redstone2", 0);
			this.redstone2h.flipHorizontally();

			this.redstone1v = new IndexedSprite(media, "redstone1", 0);
			this.redstone1v.flipVertically();

			this.redstone2v = new IndexedSprite(media, "redstone2", 0);
			this.redstone2v.flipVertically();

			this.redstone3v = new IndexedSprite(media, "redstone3", 0);
			this.redstone3v.flipVertically();

			this.redstone1vh = new IndexedSprite(media, "redstone1", 0);
			this.redstone1vh.flipHorizontally();
			this.redstone1vh.flipVertically();

			this.redstone2vh = new IndexedSprite(media, "redstone2", 0);
			this.redstone2vh.flipHorizontally();
			this.redstone2vh.flipVertically();

			@Pc(725) Sprite bl1 = new Sprite(media, "backleft1", 0);
			this.backleft1 = new BufferedImageFrameBuffer(bl1.spriteWidth, bl1.spriteHeight);
			bl1.drawOpaque(0, 0);

			@Pc(750) Sprite bl2 = new Sprite(media, "backleft2", 0);
			this.backleft2 = new BufferedImageFrameBuffer(bl2.spriteWidth, bl2.spriteHeight);
			bl2.drawOpaque(0, 0);

			@Pc(775) Sprite br1 = new Sprite(media, "backright1", 0);
			this.backright1 = new BufferedImageFrameBuffer(br1.spriteWidth, br1.spriteHeight);
			br1.drawOpaque(0, 0);

			@Pc(800) Sprite br2 = new Sprite(media, "backright2", 0);
			this.backright2 = new BufferedImageFrameBuffer(br2.spriteWidth, br2.spriteHeight);
			br2.drawOpaque(0, 0);

			@Pc(825) Sprite bt1 = new Sprite(media, "backtop1", 0);
			this.backtop1 = new BufferedImageFrameBuffer(bt1.spriteWidth, bt1.spriteHeight);
			bt1.drawOpaque(0, 0);

			@Pc(850) Sprite bt2 = new Sprite(media, "backtop2", 0);
			this.backtop2 = new BufferedImageFrameBuffer(bt2.spriteWidth, bt2.spriteHeight);
			bt2.drawOpaque(0, 0);

			@Pc(875) Sprite bv1 = new Sprite(media, "backvmid1", 0);
			this.backvmid1 = new BufferedImageFrameBuffer(bv1.spriteWidth, bv1.spriteHeight);
			bv1.drawOpaque(0, 0);

			@Pc(900) Sprite bv2 = new Sprite(media, "backvmid2", 0);
			this.backvmid2 = new BufferedImageFrameBuffer(bv2.spriteWidth, bv2.spriteHeight);
			bv2.drawOpaque(0, 0);

			@Pc(925) Sprite bv3 = new Sprite(media, "backvmid3", 0);
			this.backvmid3 = new BufferedImageFrameBuffer(bv3.spriteWidth, bv3.spriteHeight);
			bv3.drawOpaque(0, 0);

			@Pc(950) Sprite bh2 = new Sprite(media, "backhmid2", 0);
			this.backhmid2 = new BufferedImageFrameBuffer(bh2.spriteWidth, bh2.spriteHeight);
			bh2.drawOpaque(0, 0);

			@Pc(975) int randX = (int) (Math.random() * 21.0D) - 10;
			@Pc(982) int randY = (int) (Math.random() * 21.0D) - 10;
			@Pc(989) int randZ = (int) (Math.random() * 21.0D) - 10;
			@Pc(996) int randAll = (int) (Math.random() * 41.0D) - 20;
			for (@Pc(998) int j = 0; j < 50; j++) {
				if (this.mapfunction[j] != null) {
					this.mapfunction[j].translate(randX + randAll, randY + randAll, randZ + randAll);
				}
				if (this.mapscene[j] != null) {
					this.mapscene[j].translate(randX + randAll, randY + randAll, randZ + randAll);
				}
			}

			this.showProgress("Unpacking textures", 80);
			Draw3D.unpackTextures(textures);
			Draw3D.setBrightness(0.8D);
			Draw3D.setupPools();

			this.showProgress("Unpacking models", 83);
			Model.decode(models);
			SeqBase.decode(models);
			SeqFrame.decode(models);

			this.showProgress("Unpacking config", 86);
			SeqType.decode(config);
			LocType.decode(config);
			FloType.decode(config);
			ObjType.decode(config);
			NpcType.decode(config);
			IdkType.decode(config);
			SpotAnimType.decode(config);
			VarpType.decode(config);
			ObjType.isMember = members;

			if (!lowMemory) {
				this.showProgress("Unpacking sounds", 90);
				@Pc(1113) byte[] local1113 = sounds.read("sounds.dat", null);
				@Pc(1119) Buffer local1119 = new Buffer(local1113);
				SoundTrack.load(local1119);
			}

			this.showProgress("Unpacking interfaces", 92);
			@Pc(1150) Font[] local1150 = new Font[] { this.plain11, this.plain12, this.bold12, this.quill8};
			Component.decode(media, local1150, interfaceArchive);

			this.showProgress("Preparing game engine", 97);

			for (@Pc(1162) int y = 0; y < 33; y++) {
				int left = 999;
				int right = 0;
				for (int x = 0; x < 35; x++) {
					if (this.mapback.pixels[x + y * this.mapback.spriteWidth] == 0) {
						if (left == 999) {
							left = x;
						}
					} else if (left != 999) {
						right = x;
						break;
					}
				}
				this.compassLeft[y] = left;
				this.compassLineWidth[y] = right - left;
			}

			for (int y = 9; y < 160; y++) {
				int left = 999;
				int right = 0;
				for (int x = 10; x < 168; x++) {
					if (this.mapback.pixels[x + y * this.mapback.spriteWidth] == 0 && (x > 34 || y > 34)) {
						if (left == 999) {
							left = x;
						}
					} else if (left != 999) {
						right = x;
						break;
					}
				}
				this.minimapLeft[y - 9] = left - 21;
				this.minimapLineWidth[y - 9] = right - left;
			}

			Draw3D.prepareOffsets(479, 96);
			this.chatOffsets = Draw3D.offsets;

			Draw3D.prepareOffsets(190, 261);
			this.sidebarOffsets = Draw3D.offsets;

			Draw3D.prepareOffsets(Draw3D.renderWidth, Draw3D.renderHeight);
			this.viewportOffsets = Draw3D.offsets;

			MapSquare.init(Draw3D.renderWidth, Draw3D.renderHeight, 500, 800);
			WordPack.decode(wordenc);

			if (startServer) {
				Server.ready = true;
			}
		} catch (@Pc(1357) Exception ex) {
			ex.printStackTrace();
			this.errorLoading = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "v", descriptor = "(I)V")
	private void updateInput() {
		if (this.objDragArea != 0) {
			return;
		}

		this.options[0] = "Cancel";
		this.optionType[0] = Cs1Actions.IF_CANCELBUTTON;
		this.optionCount = 1;

		this.handleChatInput(super.mouseX, super.mouseY);
		this.hoveredInterfaceIndex = 0;
		if (super.mouseX > 8 && super.mouseY > 11 && super.mouseX < 520 && super.mouseY < 345) {
			if (this.viewportInterfaceIndex == -1) {
				this.updateViewport();
			} else {
				this.updateInterface(Component.instances[this.viewportInterfaceIndex], super.mouseX, super.mouseY, 8, 11, 0);
			}
		}
		if (this.hoveredInterfaceIndex != this.viewportHoveredInterfaceIndex) {
			this.viewportHoveredInterfaceIndex = this.hoveredInterfaceIndex;
		}
		this.hoveredInterfaceIndex = 0;
		if (super.mouseX > 562 && super.mouseY > 231 && super.mouseX < 752 && super.mouseY < 492) {
			if (this.sidebarInterfaceId != -1) {
				this.updateInterface(Component.instances[this.sidebarInterfaceId], super.mouseX, super.mouseY, 562, 231, 0);
			} else if (this.tabComponentId[this.selectedTab] != -1) {
				this.updateInterface(Component.instances[this.tabComponentId[this.selectedTab]], super.mouseX, super.mouseY, 562, 231, 0);
			}
		}
		if (this.hoveredInterfaceIndex != this.sidebarHoveredInterfaceIndex) {
			this.sidebarRedraw = true;
			this.sidebarHoveredInterfaceIndex = this.hoveredInterfaceIndex;
		}
		this.hoveredInterfaceIndex = 0;
		if (super.mouseX > 22 && super.mouseY > 375 && super.mouseX < 431 && super.mouseY < 471) {
			if (this.chatbackComponentId == -1) {
				this.updatePlayerTooltip(super.mouseY - 375, super.mouseX - 22);
			} else {
				this.updateInterface(Component.instances[this.chatbackComponentId], super.mouseX, super.mouseY, 22, 375, 0);
			}
		}
		if (this.chatbackComponentId != -1 && this.hoveredInterfaceIndex != this.chatHoveredInterfaceIndex) {
			this.redrawChatback = true;
			this.chatHoveredInterfaceIndex = this.hoveredInterfaceIndex;
		}

		@Pc(223) boolean done = false;
		while (!done) {
			done = true;
			for (@Pc(229) int i = 0; i < this.optionCount - 1; i++) {
				if (this.optionType[i] < 1000 && this.optionType[i + 1] > 1000) {
					@Pc(250) String tmp0 = this.options[i];
					this.options[i] = this.options[i + 1];
					this.options[i + 1] = tmp0;
					@Pc(272) int tmp1 = this.optionType[i];
					this.optionType[i] = this.optionType[i + 1];
					this.optionType[i + 1] = tmp1;
					@Pc(294) int tmp2 = this.optionParamB[i];
					this.optionParamB[i] = this.optionParamB[i + 1];
					this.optionParamB[i + 1] = tmp2;
					@Pc(316) int tmp3 = this.optionParamC[i];
					this.optionParamC[i] = this.optionParamC[i + 1];
					this.optionParamC[i + 1] = tmp3;
					@Pc(338) int tmp4 = this.optionParamA[i];
					this.optionParamA[i] = this.optionParamA[i + 1];
					this.optionParamA[i + 1] = tmp4;
					done = false;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(B)V")
	private void clearCaches() {
		LocType.modelCache.clear();
		LocType.modelCacheBuilt.clear();
		NpcType.models.clear();
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
			this.cross[this.crossCycle / 100].draw(this.crossX - 8 - 8, this.crossY - 8 - 11);
		} else if (this.crossType == 2) {
			this.cross[this.crossCycle / 100 + 4].draw(this.crossX - 8 - 8, this.crossY - 8 - 11);
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
				this.headicons[1].draw(472, 258);
			} else {
				this.headicons[1].draw(472, 296);
			}
		}

		if (drawDebug) {
			this.plain12.draw(4, 329 - (plain12.fontHeight * 3), 0xFFFF00, "Region: " + ((baseTileX + self.x / 128) >> 6) + " " + ((baseTileZ + self.z / 128) >> 6));
			this.plain12.draw(4, 329 - (plain12.fontHeight * 2), 0xFFFF00, "Relative: " + (self.x / 128) + " " + (self.z / 128));
			this.plain12.draw(4, 329 - (plain12.fontHeight), 0xFFFF00, "Coord: " + (baseTileX + self.x / 128) + " " + (baseTileZ + self.z / 128) + " " + currentPlane);
			this.plain12.draw(4, 329, 0xFFFF00, "FPS: " + super.fps);
		}

		if (this.wildernessLevel > 0) {
			this.headicons[0].draw(472, 296);
			this.plain12.drawCentered("Level: " + this.wildernessLevel, 484, 329, 0xffff00);
		}

		if (this.worldLocationState == 1) {
			this.headicons[6].draw(472, 296);
			this.plain12.drawCentered("Arena", 484, 329, 0xffff00);
		}

		if (this.systemUpdateTimer > 0) {
			local7 = this.systemUpdateTimer / 50;
			@Pc(196) int local196 = local7 / 60;
			local7 %= 60;
			if (local7 < 10) {
				this.plain12.draw(4, 329, 0xffff00, "System update in: " + local196 + ":0" + local7);
			} else {
				this.plain12.draw(4, 329, 0xffff00, "System update in: " + local196 + ":" + local7);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "x", descriptor = "(I)V")
	private void updateOrbitCamera() {
		@Pc(8) int x = this.self.x + this.cameraAnticheatOffsetX;
		@Pc(15) int z = this.self.z + this.cameraAnticheatOffsetZ;

		if (this.cameraOrbitX - x < -500 || this.cameraOrbitX - x > 500 || this.cameraOrbitZ - z < -500 || this.cameraOrbitZ - z > 500) {
			this.cameraOrbitX = x;
			this.cameraOrbitZ = z;
		}

		if (this.cameraOrbitX != x) {
			this.cameraOrbitX += (x - this.cameraOrbitX) / 16;
		}

		if (this.cameraOrbitZ != z) {
			this.cameraOrbitZ += (z - this.cameraOrbitZ) / 16;
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

		@Pc(208) int tileX = this.cameraOrbitX >> 7;
		@Pc(213) int tileZ = this.cameraOrbitZ >> 7;
		@Pc(223) int landY = this.getLandY(this.currentPlane, this.cameraOrbitX, this.cameraOrbitZ);
		@Pc(225) int maxY = 0;

		if (tileX > 3 && tileZ > 3 && tileX < 100 && tileZ < 100) {
			for (int tx = tileX - 4; tx <= tileX + 4; tx++) {
				for (@Pc(247) int tz = tileZ - 4; tz <= tileZ + 4; tz++) {
					@Pc(252) int p = this.currentPlane;
					if (p < 3 && (this.levelRenderFlags[1][tx][tz] & 0x2) == 2) {
						p++;
					}

					@Pc(279) int y = landY - this.levelHeightMaps[p][tx][tz];
					if (y > maxY) {
						maxY = y;
					}
				}
			}
		}

		int y = maxY * 192;

		// 98048 - 32768 + 256 = 65536
		if (y > 98048) {
			y = 98048;
		}

		if (y < 32768) {
			y = 32768;
		}

		if (y > this.cameraMaxY) {
			this.cameraMaxY += (y - this.cameraMaxY) / 24;
		} else if (y < this.cameraMaxY) {
			this.cameraMaxY += (y - this.cameraMaxY) / 80;
		}
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(B)V")
	private void updateSceneProjectiles() {
		for (@Pc(12) ProjectileEntity local12 = (ProjectileEntity) this.projectiles.peekPrevious(); local12 != null; local12 = (ProjectileEntity) this.projectiles.getPrevious()) {
			if (local12.level != this.currentPlane || clientClock > local12.lastCycle) {
				local12.unlink();
			} else if (clientClock >= local12.firstCycle) {
				if (local12.targetIndex > 0) {
					@Pc(42) NpcEntity local42 = this.npcEntities[local12.targetIndex - 1];
					if (local42 != null) {
						local12.setTarget(this.getLandY(local12.level, local42.x, local42.z) - local12.baseZ, local42.z, local42.x, clientClock);
					}
				}
				if (local12.targetIndex < 0) {
					@Pc(73) int local73 = -local12.targetIndex - 1;
					@Pc(80) PlayerEntity local80;
					if (local73 == this.selfPlayerId) {
						local80 = this.self;
					} else {
						local80 = this.playerEntities[local73];
					}
					if (local80 != null) {
						local12.setTarget(this.getLandY(local12.level, local80.x, local80.z) - local12.baseZ, local80.z, local80.x, clientClock);
					}
				}
				local12.update(this.sceneDelta);
				this.mapSquare.add((int) local12.y, 60, local12.yaw, (int) local12.x, -1, false, null, local12, (int) local12.z, this.currentPlane);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(I)V")
	@Override
	protected final void refresh() {
		this.redrawTitleBackground = true;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IILclient!hb;I)V")
	private void drawOnMinimap(@OriginalArg(0) int y, @OriginalArg(2) Sprite s, @OriginalArg(3) int x) {
		@Pc(7) int theta = this.cameraYaw + this.minimapAnticheatAngle & 0x7FF;

		@Pc(15) int length = x * x + y * y;
		if (length > 6400) {
			return;
		}

		@Pc(47) int sin = Model.sin[theta] * 256 / (this.minimapZoom + 256);
		@Pc(56) int cos = Model.cos[theta] * 256 / (this.minimapZoom + 256);

		@Pc(66) int drawX = y * sin + x * cos >> 16;
		@Pc(76) int drawY = y * cos - x * sin >> 16;

		if (length > 2500) {
			s.drawMasked(drawX + 94 - s.cropW / 2, 83 - drawY - s.cropH / 2, this.mapback);
		} else {
			s.draw(drawX + 94 - s.cropW / 2, 83 - drawY - s.cropH / 2);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIII)I")
	private int mix(@OriginalArg(0) int src, @OriginalArg(1) int alpha, @OriginalArg(2) int dst) {
		@Pc(3) int invAlpha = 256 - alpha;
		return ((src & 0xFF00FF) * invAlpha + (dst & 0xFF00FF) * alpha & 0xFF00FF00) + ((src & 0xFF00) * invAlpha + (dst & 0xFF00) * alpha & 0xFF0000) >> 8;
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(II)Ljava/lang/String;")
	private String getIntString(@OriginalArg(0) int amount) {
		return amount < 999999999 ? String.valueOf(amount) : "*";
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IZLclient!x;)V")
	private void setDrawPos(@OriginalArg(0) int offset, @OriginalArg(2) PathingEntity e) {
		this.projectToScreen(e.z, e.x, offset);
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IIII)V")
	private void projectToScreen(@OriginalArg(0) int tileZ, @OriginalArg(1) int tileX, @OriginalArg(3) int height) {
		// region <= 204 && region >= 2
		if (tileX < 128 || tileZ < 128 || tileX > 13056 || tileZ > 13056) {
			this.drawX = -1;
			this.drawY = -1;
			return;
		}

		@Pc(28) int landY = this.getLandY(this.currentPlane, tileX, tileZ) - height;

		@Pc(33) int x = tileX - this.cameraX;
		@Pc(38) int y = landY - this.cameraY;
		@Pc(43) int z = tileZ - this.cameraZ;

		@Pc(48) int pitchsin = Model.sin[this.cameraPitch];
		@Pc(53) int pitchcos = Model.cos[this.cameraPitch];

		@Pc(58) int yawsin = Model.sin[this.cameraOrbitYaw];
		@Pc(63) int yawcos = Model.cos[this.cameraOrbitYaw];

		@Pc(73) int tmp1 = z * yawsin + x * yawcos >> 16;
		@Pc(83) int tmp2 = z * yawcos - x * yawsin >> 16;
		x = tmp1;

		tmp1 = y * pitchcos - tmp2 * pitchsin >> 16;
		z = y * pitchsin + tmp2 * pitchcos >> 16;
		y = tmp1;

		if (z >= Scene.NEAR_Z) {
			this.drawX = Draw3D.centerX3D + (x << 9) / z;
			this.drawY = Draw3D.centerY3D + (y << 9) / z;
		} else {
			this.drawX = -1;
			this.drawY = -1;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIZ)Z")
	private boolean interactWithLoc(@OriginalArg(0) int opcode, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int bitset) {
		@Pc(7) int locId = bitset >> 14 & 0x7FFF;
		@Pc(16) int info = this.mapSquare.getInfo(this.currentPlane, x, z, bitset);
		if (info == -1) {
			return false;
		}

		@Pc(25) int type = info & 0x1F;
		@Pc(31) int angle = info >> 6 & 0x3;
		if (type == 10 || type == 11 || type == 22) {
			@Pc(43) LocType loc = LocType.get(locId);
			@Pc(51) int width;
			@Pc(54) int depth;
			if (angle == 0 || angle == 2) {
				width = loc.width;
				depth = loc.length;
			} else {
				width = loc.length;
				depth = loc.width;
			}

			@Pc(65) int interactionFlags = loc.blocksides;
			if (angle != 0) {
				interactionFlags = (interactionFlags << angle & 0xF) + (interactionFlags >> 4 - angle);
			}
			this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], z, x, false, width, depth, 0, 0, interactionFlags);
		} else {
			this.tryMove(2, this.self.pathTileX[0], this.self.pathTileZ[0], z, x, false, 0, 0, angle, type + 1, 0);
		}

		this.crossX = super.clickX;
		this.crossY = super.clickY;
		this.crossType = 2;
		this.crossCycle = 0;
		this.outBuffer.p1isaac(opcode);
		this.outBuffer.p2(x + this.baseTileX);
		this.outBuffer.p2(z + this.baseTileZ);
		this.outBuffer.p2(locId);
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
		if (super.clickX <= 22 || super.clickY <= 375 || super.clickX >= 501 || super.clickY >= 471) {
			return;
		}
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

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;)Ljava/io/DataInputStream;")
	private DataInputStream openStream(@OriginalArg(0) String arg0) throws IOException {
		return signlink.mainapp == null ? new DataInputStream((new URL(this.getCodeBase(), arg0)).openStream()) : signlink.openurl(arg0);
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(B)V")
	private void prepareTitleScreen() {
		if (this.titleTop != null) {
			return;
		}

		this.areaChatback = null;
		this.areaMapback = null;
		this.areaInvback = null;
		this.areaViewport = null;
		this.areaBackbase1 = null;
		this.areaBackbase2 = null;
		this.areaBackhmid1 = null;

		this.titleLeft = new BufferedImageFrameBuffer(128, 265);
		Draw2D.clear();

		this.titleRight = new BufferedImageFrameBuffer(128, 265);
		Draw2D.clear();

		this.titleTop = new BufferedImageFrameBuffer(533, 186);
		Draw2D.clear();

		this.titleBottom = new BufferedImageFrameBuffer(360, 146);
		Draw2D.clear();

		this.titleCenter = new BufferedImageFrameBuffer(360, 200);
		Draw2D.clear();

		this.titleBottomLeft = new BufferedImageFrameBuffer(214, 267);
		Draw2D.clear();

		this.titleBottomRight = new BufferedImageFrameBuffer(215, 267);
		Draw2D.clear();

		this.titleLeftSpace = new BufferedImageFrameBuffer(86, 79);
		Draw2D.clear();

		this.titleRightSpace = new BufferedImageFrameBuffer(87, 79);
		Draw2D.clear();

		if (this.title != null) {
			this.loadTitleBackground();
			this.loadTitleForeground();
		}
		this.redrawTitleBackground = true;
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
	public final void run() {
		if (this.startFlamesThread) {
			this.runFlames();
		} else if (this.startMidiThread) {
			this.runMidi();
		} else {
			super.run();
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIIZIILclient!hc;)V")
	private void updateInterfaceScrollbar(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) Component arg7) {
		if (this.scrollGripHeld) {
			this.scrollGripInputPadding = 32;
		} else {
			this.scrollGripInputPadding = 0;
		}
		this.scrollGripHeld = false;
		if (arg0 >= arg5 && arg0 < arg5 + 16 && arg1 >= arg6 && arg1 < arg6 + 16) {
			arg7.scrollY -= this.dragCycle * 4;
			if (arg4) {
				this.sidebarRedraw = true;
			}
		} else if (arg0 >= arg5 && arg0 < arg5 + 16 && arg1 >= arg6 + arg3 - 16 && arg1 < arg6 + arg3) {
			arg7.scrollY += this.dragCycle * 4;
			if (arg4) {
				this.sidebarRedraw = true;
			}
		} else if (arg0 >= arg5 - this.scrollGripInputPadding && arg0 < arg5 + this.scrollGripInputPadding + 16 && arg1 >= arg6 + 16 && arg1 < arg6 + arg3 - 16 && this.dragCycle > 0) {
			@Pc(122) int local122 = (arg3 - 32) * arg3 / arg2;
			if (local122 < 8) {
				local122 = 8;
			}
			@Pc(137) int local137 = arg1 - arg6 - local122 / 2 - 16;
			@Pc(143) int local143 = arg3 - local122 - 32;
			arg7.scrollY = (arg2 - arg3) * local137 / local143;
			if (arg4) {
				this.sidebarRedraw = true;
			}
			this.scrollGripHeld = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;Z)V")
	private void login(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(2) boolean arg2) {
		signlink.errorname = arg0;
		try {
			if (!arg2) {
				this.loginMessage0 = "";
				this.loginMessage1 = "Connecting to server...";
				this.drawTitleScreen();
			}
			this.stream = new BufferedStream(this, this.opensocket(gamePortOffset + 43594));
			this.stream.read(this.inBuffer.data, 0, 8);
			this.inBuffer.pos = 0;
			this.serverSeed = this.inBuffer.g8();
			@Pc(47) int[] seed = new int[] { (int) (Math.random() * 9.9999999E7D), (int) (Math.random() * 9.9999999E7D), (int) (this.serverSeed >> 32), (int) this.serverSeed};
			this.outBuffer.pos = 0;
			this.outBuffer.p1(10);
			this.outBuffer.p4(seed[0]);
			this.outBuffer.p4(seed[1]);
			this.outBuffer.p4(seed[2]);
			this.outBuffer.p4(seed[3]);
			this.outBuffer.p4(signlink.uid);
			this.outBuffer.pjstr(arg0);
			this.outBuffer.pjstr(arg1);
			this.outBuffer.rsaenc(RSA_MODULUS, RSA_EXPONENT);
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
			this.loginBuffer.pdata(this.outBuffer.data, 0, this.outBuffer.pos);
			this.outBuffer.isaac = new IsaacRandom(seed);
			for (@Pc(202) int local202 = 0; local202 < 4; local202++) {
				seed[local202] += 50;
			}
			this.isaac = new IsaacRandom(seed);
			this.stream.write(this.loginBuffer.data, this.loginBuffer.pos);
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
					this.rights = true;
				} else {
					this.rights = false;
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
				for (@Pc(408) int pid = 0; pid < this.MAX_PLAYER_COUNT; pid++) {
					this.playerEntities[pid] = null;
					this.playerBuffers[pid] = null;
				}
				for (@Pc(427) int nid = 0; nid < 8192; nid++) {
					this.npcEntities[nid] = null;
				}
				this.self = this.playerEntities[this.LOCAL_PLAYER_INDEX] = new PlayerEntity();
				this.projectiles.clear();
				this.spotanims.clear();
				this.temporaryLocs.clear();
				@Pc(464) int x;
				for (@Pc(460) int plane = 0; plane < 4; plane++) {
					for (x = 0; x < 104; x++) {
						for (@Pc(468) int z = 0; z < 104; z++) {
							this.objects[plane][x][z] = null;
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
				this.chatbackInputType = false;
				this.menuVisible = false;
				this.showSocialInput = false;
				this.chatbackMessage = null;
				this.inMultizone = 0;
				this.flashingSidebarId = -1;
				this.characterDesignIsMale = true;
				this.resetCharacterDesign();
				for (x = 0; x < 5; x++) {
					this.characterDesignColors[x] = 0;
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
				this.loginMessage0 = "";
				this.loginMessage1 = "Invalid username or password.";
				return;
			}
			if (local237 == 4) {
				this.loginMessage0 = "Your account has been disabled.";
				this.loginMessage1 = "Please check your message-centre for details.";
				return;
			}
			if (local237 == 5) {
				this.loginMessage0 = "Your account is already logged in.";
				this.loginMessage1 = "Try again in 60 secs...";
				return;
			}
			if (local237 == 6) {
				this.loginMessage0 = "RuneScape has been updated!";
				this.loginMessage1 = "Please reload this page.";
				return;
			}
			if (local237 == 7) {
				this.loginMessage0 = "This world is full.";
				this.loginMessage1 = "Please use a different world.";
				return;
			}
			if (local237 == 8) {
				this.loginMessage0 = "Unable to connect.";
				this.loginMessage1 = "Login server offline.";
				return;
			}
			if (local237 == 9) {
				this.loginMessage0 = "Login limit exceeded.";
				this.loginMessage1 = "Too many connections from your address.";
				return;
			}
			if (local237 == 10) {
				this.loginMessage0 = "Unable to connect.";
				this.loginMessage1 = "Bad session id.";
				return;
			}
			if (local237 == 11) {
				this.loginMessage0 = "Login server rejected session.";
				this.loginMessage1 = "Please try again.";
				return;
			}
			if (local237 == 12) {
				this.loginMessage0 = "You need a members account to login to this world.";
				this.loginMessage1 = "Please subscribe, or use a different world.";
				return;
			}
			if (local237 == 13) {
				this.loginMessage0 = "Could not complete login.";
				this.loginMessage1 = "Please try using a different world.";
				return;
			}
			if (local237 == 14) {
				this.loginMessage0 = "The server is being updated.";
				this.loginMessage1 = "Please wait 1 minute and try again.";
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
				this.loginMessage0 = "Login attempts exceeded.";
				this.loginMessage1 = "Please wait 1 minute and try again.";
				return;
			}
			if (local237 == 17) {
				this.loginMessage0 = "You are standing in a members-only area.";
				this.loginMessage1 = "To play on this world move to a free area first";
			}
		} catch (@Pc(762) IOException local762) {
			this.loginMessage0 = "";
			this.loginMessage1 = "Error connecting to server.";
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIIIIIII)V")
	private void addLoc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int locShape, @OriginalArg(7) int plane) {
		if (arg1 < 1 || arg2 < 1 || arg1 > 102 || arg2 > 102) {
			return;
		}
		if (lowMemory && plane != this.currentPlane) {
			return;
		}
		@Pc(25) int local25 = 0;
		if (arg3 == 0) {
			local25 = this.mapSquare.getWallBitset(plane, arg1, arg2);
		}
		if (arg3 == 1) {
			local25 = this.mapSquare.getWallDecorationBitset(plane, arg2, arg1);
		}
		if (arg3 == 2) {
			local25 = this.mapSquare.getLocationBitset(plane, arg1, arg2);
		}
		if (arg3 == 3) {
			local25 = this.mapSquare.getGroundDecorationBitset(plane, arg1, arg2);
		}
		@Pc(81) int local81;
		if (local25 != 0) {
			local81 = this.mapSquare.getInfo(plane, arg1, arg2, local25);
			@Pc(87) int local87 = local25 >> 14 & 0x7FFF;
			@Pc(91) int local91 = local81 & 0x1F;
			@Pc(95) int local95 = local81 >> 6;
			@Pc(107) LocType local107;
			if (arg3 == 0) {
				this.mapSquare.removeWall(arg1, plane, arg2);
				local107 = LocType.get(local87);
				if (local107.blockwalk) {
					this.collisionMaps[plane].removeWall(local107.blockrange, local95, arg1, arg2, local91);
				}
			}
			if (arg3 == 1) {
				this.mapSquare.removeWallDecoration(plane, arg2, arg1);
			}
			if (arg3 == 2) {
				this.mapSquare.removeLocation(arg1, arg2, plane);
				local107 = LocType.get(local87);
				if (arg1 + local107.width > 103 || arg2 + local107.width > 103 || arg1 + local107.length > 103 || arg2 + local107.length > 103) {
					return;
				}
				if (local107.blockwalk) {
					this.collisionMaps[plane].removeLoc(arg2, arg1, local95, local107.width, local107.blockrange, local107.length);
				}
			}
			if (arg3 == 3) {
				this.mapSquare.removeGroundDecoration(plane, arg1, arg2);
				local107 = LocType.get(local87);
				if (local107.blockwalk && local107.interactable) {
					this.collisionMaps[plane].removeBlock(arg2, arg1);
				}
			}
		}
		if (arg4 < 0) {
			return;
		}
		local81 = plane;
		if (plane < 3 && (this.levelRenderFlags[1][arg1][arg2] & 0x2) == 2) {
			local81 = plane + 1;
		}
		Scene.addLoc(arg1, this.locList, this.collisionMaps[plane], arg2, arg0, this.levelHeightMaps, plane, arg4, locShape, this.mapSquare, local81);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(JI)V")
	private void addFriend(@OriginalArg(0) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		if (this.friendCount >= 100) {
			this.addMessage(0, "", "Your friends list is full. Max of 100 hit");
			return;
		}
		@Pc(23) String local23 = StringUtils.formatName(StringUtils.fromBase37(arg0));
		for (@Pc(25) int local25 = 0; local25 < this.friendCount; local25++) {
			if (this.friendName37[local25] == arg0) {
				this.addMessage(0, "", local23 + " is already on your friend list");
				return;
			}
		}
		for (@Pc(55) int local55 = 0; local55 < this.ignoreCount; local55++) {
			if (this.ignoreName37[local55] == arg0) {
				this.addMessage(0, "", "Please remove " + local23 + " from your ignore list first");
				return;
			}
		}
		if (local23.equals(this.self.name)) {
			return;
		}
		this.friendName[this.friendCount] = local23;
		this.friendName37[this.friendCount] = arg0;
		this.friendWorld[this.friendCount] = 0;
		this.friendCount++;
		this.sidebarRedraw = true;
		this.outBuffer.p1isaac(ClientProt.FRIENDLIST_ADD);
		this.outBuffer.p8(arg0);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(B)V")
	@Override
	protected final void unload() {
		signlink.reporterror = false;
		try {
			if (this.stream != null) {
				this.stream.close();
			}
		} catch (@Pc(11) Exception ignored) {
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
		this.optionParamB = null;
		this.optionParamC = null;
		this.optionType = null;
		this.optionParamA = null;
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
		VarpType.instances = null;
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
		return signlink.mainapp == null ? new Socket(InetAddress.getByName(this.getCodeBase().getHost()), arg0) : signlink.opensocket(arg0);
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZIILclient!z;I)V")
	private void addPlayerOptions(@OriginalArg(3) PlayerEntity other, @OriginalArg(4) int a, @OriginalArg(1) int b, @OriginalArg(2) int c) {
		if (other == this.self || this.optionCount >= 400) {
			return;
		}

		@Pc(41) String otherName = other.name + getLevelColorTag(this.self.combatLevel, other.combatLevel) + " (level-" + other.combatLevel + ")";
		if (this.selectedObject == 1) {
			this.options[this.optionCount] = "Use " + this.selectedObjName + " with @whi@" + otherName;
			this.optionType[this.optionCount] = Cs1Actions.OPPLAYERU;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;
		} else if (this.selectedSpell != 1) {
			this.options[this.optionCount] = "Follow @whi@" + otherName;
			this.optionType[this.optionCount] = Cs1Actions.OPPLAYER1;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;

			if (this.tutorialIslandState == 0) {
				this.options[this.optionCount] = "Trade with @whi@" + otherName;
				this.optionType[this.optionCount] = Cs1Actions.OPPLAYER2;
				this.optionParamB[this.optionCount] = a;
				this.optionParamC[this.optionCount] = b;
				this.optionParamA[this.optionCount] = c;
				this.optionCount++;
			}

			if (this.wildernessLevel > 0) {
				this.options[this.optionCount] = "Attack @whi@" + otherName;
				if (this.self.combatLevel >= other.combatLevel) {
					this.optionType[this.optionCount] = Cs1Actions.OPPLAYER3;
				} else {
					this.optionType[this.optionCount] = 2000 + Cs1Actions.OPPLAYER3;
				}
				this.optionParamB[this.optionCount] = a;
				this.optionParamC[this.optionCount] = b;
				this.optionParamA[this.optionCount] = c;
				this.optionCount++;
			}

			if (this.worldLocationState == 1) {
				this.options[this.optionCount] = "Fight @whi@" + otherName;
				this.optionType[this.optionCount] = Cs1Actions.OPPLAYER3;
				this.optionParamB[this.optionCount] = a;
				this.optionParamC[this.optionCount] = b;
				this.optionParamA[this.optionCount] = c;
				this.optionCount++;
			}

			if (this.worldLocationState == 2) {
				this.options[this.optionCount] = "Duel-with @whi@" + otherName;
				this.optionType[this.optionCount] = Cs1Actions.OPPLAYER4;
				this.optionParamB[this.optionCount] = a;
				this.optionParamC[this.optionCount] = b;
				this.optionParamA[this.optionCount] = c;
				this.optionCount++;
			}
		} else if ((this.selectedFlags & 0x8) == 8) {
			this.options[this.optionCount] = this.selectedSpellPrefix + " @whi@" + otherName;
			this.optionType[this.optionCount] = Cs1Actions.OPPLAYERT;
			this.optionParamB[this.optionCount] = a;
			this.optionParamC[this.optionCount] = b;
			this.optionParamA[this.optionCount] = c;
			this.optionCount++;
		}

		for (@Pc(392) int local392 = 0; local392 < this.optionCount; local392++) {
			if (this.optionType[local392] == Cs1Actions.WALK) {
				this.options[local392] = "Walk here @whi@" + otherName;
				return;
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
		if (!this.ingame) {
			return;
		}
		@Pc(207) int local207;
		@Pc(155) int local155;
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
			this.outBuffer.p1isaac(ClientProt.EVENT_TRACKING);
			this.outBuffer.p2(local250.pos);
			this.outBuffer.pdata(local250.data, 0, local250.pos);
			local250.release();
		}
		this.netIdleCycles++;
		if (this.netIdleCycles > 750) {
			this.reconnect();
		}
		this.updatePlayers();
		this.updateNpcEntity();
		this.updateEntityVoices();
		this.updateTemporaryLocs();
		if ((super.keyDown[1] == 1 || super.keyDown[2] == 1 || super.keyDown[3] == 1 || super.keyDown[4] == 1) && this.cameraMovedWrite++ > 5) {
			this.cameraMovedWrite = 0;
			this.outBuffer.p1isaac(ClientProt.EVENT_CAMERA_POSITION);
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
					this.sidebarRedraw = true;
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
					this.sidebarRedraw = true;
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
						this.outBuffer.p1isaac(ClientProt.IF_BUTTOND);
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
			this.outBuffer.p1isaac(ClientProt.ANTICHEAT_UPDATE);
			this.outBuffer.p3(4991788);
		}
		if (MapSquare.clickedTileX != -1) {
			local155 = MapSquare.clickedTileX;
			local508 = MapSquare.clickedTileZ;
			@Pc(653) boolean local653 = this.tryMove(0, this.self.pathTileX[0], this.self.pathTileZ[0], local508, local155, true, 0, 0, 0, 0, 0);
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
			this.outBuffer.p1isaac(ClientProt.IDLE_TIMER);
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
			this.outBuffer.p1isaac(ClientProt.ANTICHEAT_UPDATE2);
			this.outBuffer.p4(0);
		}
		this.keepaliveCounter++;
		if (this.keepaliveCounter > 50) {
			this.outBuffer.p1isaac(ClientProt.NO_TIMEOUT);
		}
		try {
			if (this.stream != null && this.outBuffer.pos > 0) {
				this.stream.write(this.outBuffer.data, this.outBuffer.pos);
				this.outBuffer.pos = 0;
				this.keepaliveCounter = 0;
			}
		} catch (@Pc(1001) IOException ignored) {
			this.reconnect();
		} catch (@Pc(1006) Exception ignored) {
			this.disconnect();
		}
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(Z)V")
	private void drawTooltip() {
		if (this.optionCount < 2 && this.selectedObject == 0 && this.selectedSpell == 0) {
			return;
		}
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
		this.bold12.drawTooltip(local31, 4, 0xffffff, clientClock / 1000);
	}

	@OriginalMember(owner = "client!client", name = "k", descriptor = "(B)V")
	private void updateSceneSpotAnims() {
		for (@Pc(13) SpotAnimEntity local13 = (SpotAnimEntity) this.spotanims.peekPrevious(); local13 != null; local13 = (SpotAnimEntity) this.spotanims.getPrevious()) {
			if (local13.level != this.currentPlane || local13.finished) {
				local13.unlink();
			} else if (clientClock >= local13.firstCycle) {
				local13.update(this.sceneDelta);
				if (local13.finished) {
					local13.unlink();
				} else {
					this.mapSquare.add(local13.z, 60, 0, local13.x, -1, false, null, local13, local13.y, local13.level);
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "getCodeBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getCodeBase() {
		if (signlink.mainapp != null) {
			return signlink.mainapp.getCodeBase();
		}
		try {
			if (super.frame != null) {
				return new URL("http://127.0.0.1:" + (httpPortOffset + 80));
			}
		} catch (@Pc(21) Exception ignored) {
		}
		return super.getCodeBase();
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IIZIIIIIIIII)Z")
	private boolean tryMove(@OriginalArg(6) int moveType, @OriginalArg(0) int srcX, @OriginalArg(4) int srcZ, @OriginalArg(8) int dz, @OriginalArg(3) int dx, @OriginalArg(2) boolean tryNearest, @OriginalArg(1) int locWidth, @OriginalArg(7) int locLength, @OriginalArg(9) int locAngle, @OriginalArg(10) int locType, @OriginalArg(11) int locInteractionFlags) {
		for (int x = 0; x < 104; x++) {
			for (int z = 0; z < 104; z++) {
				this.bfsDirection[x][z] = 0;
				this.bfsCost[x][z] = 99999999;
			}
		}

		@Pc(11) int x = srcX;
		@Pc(39) int z = srcZ;

		this.bfsDirection[srcX][srcZ] = 99;
		this.bfsCost[srcX][srcZ] = 0;

		@Pc(55) int steps = 0;
		@Pc(57) int length = 0;

		this.bfsStepX[steps] = srcX;
		this.bfsStepZ[steps] = srcZ;
		steps++;

		@Pc(70) boolean arrived = false;
		@Pc(74) int bufferSize = this.bfsStepX.length;
		@Pc(81) int[][] flags = this.collisionMaps[this.currentPlane].flags;

		@Pc(193) int min;
		while (length != steps) {
			x = this.bfsStepX[length];
			z = this.bfsStepZ[length];
			length = (length + 1) % bufferSize;

			if (x == dx && z == dz) {
				arrived = true;
				break;
			}

			if (locType != 0) {
				if ((locType < 5 || locType == 10) && this.collisionMaps[this.currentPlane].reachedWall(locAngle, dz, locType - 1, z, dx, x)) {
					arrived = true;
					break;
				}

				if (locType < 10 && this.collisionMaps[this.currentPlane].reachedDecoration(locAngle, locType - 1, x, dx, z, dz)) {
					arrived = true;
					break;
				}
			}

			if (locWidth != 0 && locLength != 0 && this.collisionMaps[this.currentPlane].reachedObject(z, locLength, x, dx, locInteractionFlags, dz, locWidth)) {
				arrived = true;
				break;
			}

			min = this.bfsCost[x][z] + 1;

			if (x > 0 && this.bfsDirection[x - 1][z] == 0 && (flags[x - 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_EAST)) == 0) {
				this.bfsStepX[steps] = x - 1;
				this.bfsStepZ[steps] = z;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x - 1][z] = 2;
				this.bfsCost[x - 1][z] = min;
			}

			if (x < 103 && this.bfsDirection[x + 1][z] == 0 &&
				(flags[x + 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST)) == 0) {
				this.bfsStepX[steps] = x + 1;
				this.bfsStepZ[steps] = z;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x + 1][z] = 8;
				this.bfsCost[x + 1][z] = min;
			}

			if (z > 0 && this.bfsDirection[x][z - 1] == 0 &&
				(flags[x][z - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_NORTH)) == 0) {
				this.bfsStepX[steps] = x;
				this.bfsStepZ[steps] = z - 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x][z - 1] = 1;
				this.bfsCost[x][z - 1] = min;
			}

			if (z < 103 && this.bfsDirection[x][z + 1] == 0 &&
				(flags[x][z + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_SOUTH)) == 0) {
				this.bfsStepX[steps] = x;
				this.bfsStepZ[steps] = z + 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x][z + 1] = 4;
				this.bfsCost[x][z + 1] = min;
			}

			if (x > 0 && z > 0 && this.bfsDirection[x - 1][z - 1] == 0 &&
				(flags[x - 1][z - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_EAST | CollisionMap.WALL_NORTHEAST | CollisionMap.WALL_NORTH)) == 0 && (flags[x - 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_EAST)) == 0 && (flags[x][z - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_NORTH)) == 0) {
				this.bfsStepX[steps] = x - 1;
				this.bfsStepZ[steps] = z - 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x - 1][z - 1] = 3;
				this.bfsCost[x - 1][z - 1] = min;
			}

			if (x < 103 && z > 0 && this.bfsDirection[x + 1][z - 1] == 0 &&
				(flags[x + 1][z - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST | CollisionMap.WALL_NORTH | CollisionMap.WALL_NORTHWEST)) == 0 &&
				(flags[x + 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST)) == 0 &&
				(flags[x][z - 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_NORTH)) == 0) {
				this.bfsStepX[steps] = x + 1;
				this.bfsStepZ[steps] = z - 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x + 1][z - 1] = 9;
				this.bfsCost[x + 1][z - 1] = min;
			}

			if (x > 0 && z < 103 && this.bfsDirection[x - 1][z + 1] == 0 &&
				(flags[x - 1][z + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_SOUTH | CollisionMap.WALL_SOUTHEAST | CollisionMap.WALL_EAST)) == 0 &&
				(flags[x - 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_EAST)) == 0 &&
				(flags[x][z + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_SOUTH)) == 0) {
				this.bfsStepX[steps] = x - 1;
				this.bfsStepZ[steps] = z + 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x - 1][z + 1] = 6;
				this.bfsCost[x - 1][z + 1] = min;
			}

			if (x < 103 && z < 103 && this.bfsDirection[x + 1][z + 1] == 0 &&
				(flags[x + 1][z + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST | CollisionMap.WALL_SOUTHWEST | CollisionMap.WALL_SOUTH)) == 0 &&
				(flags[x + 1][z] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_WEST)) == 0 &&
				(flags[x][z + 1] & (CollisionMap.BLOCKED_TILE | CollisionMap.FLAG_UNUSED1 | CollisionMap.OCCUPIED_TILE | CollisionMap.WALL_SOUTH)) == 0) {
				this.bfsStepX[steps] = x + 1;
				this.bfsStepZ[steps] = z + 1;
				steps = (steps + 1) % bufferSize;
				this.bfsDirection[x + 1][z + 1] = 12;
				this.bfsCost[x + 1][z + 1] = min;
			}
		}

		this.clickedMinimap = 0;

		@Pc(809) int padding;
		@Pc(815) int px;
		@Pc(821) int pz;
		if (!arrived) {
			if (tryNearest) {
				min = 100;
				for (padding = 1; padding < 2; padding++) {
					for (px = dx - padding; px <= dx + padding; px++) {
						for (pz = dz - padding; pz <= dz + padding; pz++) {
							if (px >= 0 && pz >= 0 && px < 104 && pz < 104 && this.bfsCost[px][pz] < min) {
								min = this.bfsCost[px][pz];
								x = px;
								z = pz;
								this.clickedMinimap = 1;
								arrived = true;
							}
						}
					}

					if (arrived) {
						break;
					}
				}
			}

			if (!arrived) {
				return false;
			}
		}

		length = 0;
		this.bfsStepX[length] = x;
		this.bfsStepZ[length] = z;
		length++;

		int dir = this.bfsDirection[x][z];
		int next = dir;

		while (x != srcX || z != srcZ) {
			if (dir != next) {
				next = dir;
				this.bfsStepX[length] = x;
				this.bfsStepZ[length++] = z;
			}

			if ((dir & 0x2) != 0) {
				x++;
			} else if ((dir & 0x8) != 0) {
				x--;
			}

			if ((dir & 0x1) != 0) {
				z++;
			} else if ((dir & 0x4) != 0) {
				z--;
			}

			dir = this.bfsDirection[x][z];
		}

		if (length > 0) {
			int count = length;
			if (length > 25) {
				count = 25;
			}
			length--;

			int startX = this.bfsStepX[length];
			int startZ = this.bfsStepZ[length];

			if (moveType == 0) {
				this.outBuffer.p1isaac(ClientProt.MOVE_GAMECLICK);
				this.outBuffer.p1(count + count + 3);
			} else if (moveType == 1) {
				this.outBuffer.p1isaac(ClientProt.MOVE_MINIMAPCLICK);
				this.outBuffer.p1(count + count + 3 + 14);
			} else if (moveType == 2) {
				this.outBuffer.p1isaac(ClientProt.MOVE_OPCLICK);
				this.outBuffer.p1(count + count + 3);
			}

			this.outBuffer.p1(super.keyDown[5]);

			this.outBuffer.p2(startX + this.baseTileX);
			this.outBuffer.p2(startZ + this.baseTileZ);

			this.flagTileX = this.bfsStepX[0];
			this.flagTileY = this.bfsStepZ[0];

			for (@Pc(1077) int i = 1; i < count; i++) {
				length--;
				this.outBuffer.p1(this.bfsStepX[length] - startX);
				this.outBuffer.p1(this.bfsStepZ[length] - startZ);
			}

			return true;
		} else if (moveType == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!kb;II)V")
	private void updatePlayers(@OriginalArg(0) Buffer buffer, @OriginalArg(1) int size) {
		this.deadEntityCount = 0;
		this.updateCount = 0;

		this.updateLocalPlayer(size, buffer);
		this.updateOtherPlayers(size, buffer);
		this.updateNewPlayers(size, buffer);
		this.updatePlayerMasks(size, buffer);

		@Pc(36) int entity;
		for (@Pc(29) int i = 0; i < this.deadEntityCount; i++) {
			entity = this.deadEntityIndices[i];
			if (this.playerEntities[entity].removeTimer != clientClock) {
				this.playerEntities[entity] = null;
			}
		}

		if (buffer.pos != size) {
			signlink.reporterror("Error packet size mismatch in getplayer pos:" + buffer.pos + " psize:" + size);
			throw new RuntimeException("eek");
		}

		for (entity = 0; entity < this.playerCount; entity++) {
			if (this.playerEntities[this.playerIndices[entity]] == null) {
				signlink.reporterror(this.username + " null entry in pl list - pos:" + entity + " size:" + this.playerCount);
				throw new RuntimeException("eek");
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(III)Z")
	private boolean animateInterface(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) boolean local3 = false;
		@Pc(7) Component parent = Component.instances[arg0];
		for (@Pc(9) int local9 = 0; local9 < parent.children.length && parent.children[local9] != -1; local9++) {
			@Pc(24) Component child = Component.instances[parent.children[local9]];
			if (child.type == Component.TYPE_UNUSED) {
				local3 |= this.animateInterface(child.id, arg1);
			}
			if (child.type == Component.TYPE_MODEL && (child.seqId != -1 || child.activeSeqId != -1)) {
				@Pc(54) boolean local54 = this.isInterfaceEnabled(child);
				@Pc(59) int local59;
				if (local54) {
					local59 = child.activeSeqId;
				} else {
					local59 = child.seqId;
				}
				if (local59 != -1) {
					@Pc(71) SeqType local71 = SeqType.instances[local59];
					child.seqCycle += arg1;
					while (child.seqCycle > local71.frameDelay[child.seqFrame]) {
						child.seqCycle -= local71.frameDelay[child.seqFrame] + 1;
						child.seqFrame++;
						if (child.seqFrame >= local71.frameCount) {
							child.seqFrame -= local71.delay;
							if (child.seqFrame < 0 || child.seqFrame >= local71.frameCount) {
								child.seqFrame = 0;
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
	private void addMessage(@OriginalArg(0) int arg0, @OriginalArg(3) String arg2, @OriginalArg(1) String arg1) {
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
		this.chatMessagePrefix[0] = arg2;
		this.chatMessage[0] = arg1;
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(II)V")
	private void resetParentComponentSeq(@OriginalArg(1) int arg0) {
		@Pc(3) Component parent = Component.instances[arg0];
		for (@Pc(5) int local5 = 0; local5 < parent.children.length && parent.children[local5] != -1; local5++) {
			@Pc(20) Component child = Component.instances[parent.children[local5]];
			if (child.type == Component.TYPE_UNUSED) {
				this.resetParentComponentSeq(child.id);
			}
			child.seqFrame = 0;
			child.seqCycle = 0;
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(IJ)V")
	private void removeFriend(@OriginalArg(1) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		for (@Pc(18) int local18 = 0; local18 < this.friendCount; local18++) {
			if (this.friendName37[local18] == arg0) {
				this.friendCount--;
				this.sidebarRedraw = true;
				for (@Pc(38) int local38 = local18; local38 < this.friendCount; local38++) {
					this.friendName[local38] = this.friendName[local38 + 1];
					this.friendWorld[local38] = this.friendWorld[local38 + 1];
					this.friendName37[local38] = this.friendName37[local38 + 1];
				}
				this.outBuffer.p1isaac(ClientProt.FRIENDLIST_DEL);
				this.outBuffer.p8(arg0);
				return;
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
		if (super.mouseButton != 1) {
			return;
		}
		@Pc(17) int local17 = super.clickX - 21 - 561;
		@Pc(24) int local24 = super.clickY - 9 - 5;
		if (local17 < 0 || local24 < 0 || local17 >= 146 || local24 >= 151) {
			return;
		}
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
		@Pc(129) boolean local129 = this.tryMove(1, this.self.pathTileX[0], this.self.pathTileZ[0], local106, local98, true, 0, 0, 0, 0, 0);
		if (!local129) {
			return;
		}
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

	@OriginalMember(owner = "client!client", name = "m", descriptor = "(B)V")
	private void updateMiniMenu() {
		if (this.objDragArea != 0) {
			return;
		}
		@Pc(14) int local14 = super.mouseButton;
		if (this.selectedSpell == 1 && super.clickX >= 520 && super.clickY >= 165 && super.clickX <= 788 && super.clickY <= 230) {
			local14 = 0;
		}
		@Pc(45) int local45;
		@Pc(48) int local48;
		@Pc(124) int local124;
		if (!this.menuVisible) {
			if (local14 == 1 && this.optionCount > 0) {
				local45 = this.optionType[this.optionCount - 1];
				if (local45 == 602 || local45 == 596 || local45 == 22 || local45 == 892 || local45 == 415 || local45 == 405 || local45 == 38 || local45 == 422 || local45 == 478 || local45 == 347 || local45 == 188) {
					local48 = this.optionParamB[this.optionCount - 1];
					local124 = this.optionParamC[this.optionCount - 1];
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
			return;
		}
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
					this.sidebarRedraw = true;
				}
				if (this.mouseArea == 2) {
					this.redrawChatback = true;
				}
			}
		}
		if (local14 != 1) {
			return;
		}
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
			this.sidebarRedraw = true;
		}
		if (this.mouseArea == 2) {
			this.redrawChatback = true;
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
		@Pc(247) int local247 = (int) Math.sqrt((double) (local226 * local226 + local236 * local236));
		@Pc(258) int local258 = (int) (Math.atan2((double) local231, (double) local247) * 325.949D) & 0x7FF;
		@Pc(269) int local269 = (int) (Math.atan2((double) local226, (double) local236) * -325.949D) & 0x7FF;
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
		if (super.mouseButton != 1) {
			return;
		}
		if (super.clickX >= 549 && super.clickX <= 583 && super.clickY >= 195 && super.clickY < 231 && this.tabComponentId[0] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 0;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 579 && super.clickX <= 609 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[1] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 1;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 607 && super.clickX <= 637 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[2] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 2;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 635 && super.clickX <= 679 && super.clickY >= 194 && super.clickY < 229 && this.tabComponentId[3] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 3;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 676 && super.clickX <= 706 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[4] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 4;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 704 && super.clickX <= 734 && super.clickY >= 194 && super.clickY < 231 && this.tabComponentId[5] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 5;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 732 && super.clickX <= 766 && super.clickY >= 195 && super.clickY < 231 && this.tabComponentId[6] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 6;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 550 && super.clickX <= 584 && super.clickY >= 492 && super.clickY < 528 && this.tabComponentId[7] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 7;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 582 && super.clickX <= 612 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[8] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 8;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 609 && super.clickX <= 639 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[9] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 9;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 637 && super.clickX <= 681 && super.clickY >= 493 && super.clickY < 528 && this.tabComponentId[10] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 10;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 679 && super.clickX <= 709 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[11] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 11;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 706 && super.clickX <= 736 && super.clickY >= 492 && super.clickY < 529 && this.tabComponentId[12] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 12;
			this.sidebarRedrawIcons = true;
		}
		if (super.clickX >= 734 && super.clickX <= 768 && super.clickY >= 492 && super.clickY < 528 && this.tabComponentId[13] != -1) {
			this.sidebarRedraw = true;
			this.selectedTab = 13;
			this.sidebarRedrawIcons = true;
		}

		sidebarInputCounter++;
		if (sidebarInputCounter > 150) {
			sidebarInputCounter = 0;
			this.outBuffer.p1isaac(ClientProt.ANTICHEAT_SIDEBAR_INPUT);
			this.outBuffer.p1(43);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!hc;B)Z")
	private boolean updateInterfaceTooltip(@OriginalArg(0) Component parent) {
		@Pc(2) int type = parent.clientCode;
		if (type >= 1 && type <= 200) {
			if (type >= 101) {
				type -= 101;
			} else {
				type--;
			}

			this.options[this.optionCount] = "Remove @whi@" + this.friendName[type];
			this.optionType[this.optionCount] = Cs1Actions.DEL_FRIEND;
			this.optionCount++;

			this.options[this.optionCount] = "Message @whi@" + this.friendName[type];
			this.optionType[this.optionCount] = Cs1Actions.MESSAGE_FRIEND;
			this.optionCount++;
			return true;
		} else if (type >= 401 && type <= 500) {
			this.options[this.optionCount] = "Remove @whi@" + parent.text;
			this.optionType[this.optionCount] = Cs1Actions.DEL_IGNORE;
			this.optionCount++;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(ILclient!kb;I)V")
	private void updateNpcList(@OriginalArg(1) Buffer b, @OriginalArg(2) int size) {
		b.accessBits();

		@Pc(14) int count = b.gBit(8);
		if (count < this.npcCount) {
			for (int n = count; n < this.npcCount; n++) {
				this.deadEntityIndices[this.deadEntityCount++] = this.npcIndices[n];
			}
		}

		if (count > this.npcCount) {
			signlink.reporterror(this.username + " Too many npcs");
			throw new RuntimeException("eek");
		}

		this.npcCount = 0;
		for (int n = 0; n < count; n++) {
			@Pc(72) int nid = this.npcIndices[n];
			@Pc(77) NpcEntity npc = this.npcEntities[nid];
			@Pc(82) int hasUpdate = b.gBit(1);
			if (hasUpdate == 0) {
				this.npcIndices[this.npcCount++] = nid;
				npc.removeTimer = clientClock;
			} else {
				@Pc(105) int type = b.gBit(2);
				if (type == 0) {
					this.npcIndices[this.npcCount++] = nid;
					npc.removeTimer = clientClock;
					this.entityUpdateIndices[this.updateCount++] = nid;
				} else {
					if (type == 1) {
						this.npcIndices[this.npcCount++] = nid;
						npc.removeTimer = clientClock;
						int walkDir = b.gBit(3);
						npc.walk(false, walkDir);
						int hasMaskUpdate = b.gBit(1);
						if (hasMaskUpdate == 1) {
							this.entityUpdateIndices[this.updateCount++] = nid;
						}
					} else if (type == 2) {
						this.npcIndices[this.npcCount++] = nid;
						npc.removeTimer = clientClock;
						int walkDir = b.gBit(3);
						npc.walk(true, walkDir);
						int runDir = b.gBit(3);
						npc.walk(true, runDir);
						@Pc(224) int hasMaskUpdate = b.gBit(1);
						if (hasMaskUpdate == 1) {
							this.entityUpdateIndices[this.updateCount++] = nid;
						}
					} else if (type == 3) {
						this.deadEntityIndices[this.deadEntityCount++] = nid;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "getParameter", descriptor = "(Ljava/lang/String;)Ljava/lang/String;")
	@Override
	public String getParameter(@OriginalArg(0) String arg0) {
		return signlink.mainapp == null ? super.getParameter(arg0) : signlink.mainapp.getParameter(arg0);
	}

	@OriginalMember(owner = "client!client", name = "l", descriptor = "(Z)V")
	private void reconnect() {
		if (this.idleTimeout > 0) {
			this.disconnect();
			return;
		}
		this.areaViewport.makeTarget();
		this.plain12.drawCentered("Connection lost", 257, 144, 0);
		this.plain12.drawCentered("Connection lost", 256, 143, 0xffffff);
		this.plain12.drawCentered("Please wait - attempting to reestablish", 257, 159, 0);
		this.plain12.drawCentered("Please wait - attempting to reestablish", 256, 158, 0xffffff);
		this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);
		this.flagTileX = 0;
		@Pc(60) BufferedStream stream = this.stream;
		this.ingame = false;
		this.login(this.username, this.password, true);
		if (!this.ingame) {
			this.disconnect();
		}
		try {
			stream.close();
		} catch (@Pc(80) Exception ignored) {
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILclient!ib;)V")
	private void updateFlameDissolve(@OriginalArg(1) IndexedSprite image) {
		Arrays.fill(this.flameBuffer1, 0);

		@Pc(30) int i;
		for (@Pc(20) int local20 = 0; local20 < 5000; local20++) {
			i = (int) (Math.random() * 128.0D * (double) 256);
			this.flameBuffer1[i] = (int) (Math.random() * 256.0D);
		}

		@Pc(48) int offset;
		@Pc(52) int y;
		@Pc(60) int x;
		for (i = 0; i < 20; i++) {
			for (offset = 1; offset < 255; offset++) {
				for (y = 1; y < 127; y++) {
					x = y + (offset << 7);
					this.flameBuffer2[x] = (this.flameBuffer1[x - 1] + this.flameBuffer1[x + 1] + this.flameBuffer1[x - 128] + this.flameBuffer1[x + 128]) / 4;
				}
			}

			@Pc(106) int[] tmp = this.flameBuffer1;
			this.flameBuffer1 = this.flameBuffer2;
			this.flameBuffer2 = tmp;
		}

		if (image == null) {
			return;
		}

		offset = 0;
		for (y = 0; y < image.spriteHeight; y++) {
			for (x = 0; x < image.spriteWidth; x++) {
				if (image.pixels[offset++] != 0) {
					@Pc(152) int dstX = x + image.clipX + 16;
					@Pc(159) int dstY = y + image.clipY + 16;
					@Pc(165) int dst = dstX + (dstY << 7);
					this.flameBuffer1[dst] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "j", descriptor = "(II)V")
	private void updateObjectStack(@OriginalArg(0) int x, @OriginalArg(1) int z) {
		@Pc(9) LinkedList objstacks = this.objects[this.currentPlane][x][z];
		if (objstacks == null) {
			this.mapSquare.removeObject(this.currentPlane, x, z);
			return;
		}

		@Pc(21) int local21 = -99999999;
		@Pc(23) ObjStackEntity local23 = null;
		@Pc(27) ObjStackEntity local27;
		@Pc(35) int local35;
		for (local27 = (ObjStackEntity) objstacks.peekPrevious(); local27 != null; local27 = (ObjStackEntity) objstacks.getPrevious()) {
			@Pc(32) ObjType local32 = ObjType.get(local27.id);
			local35 = local32.cost;
			if (local32.stackable) {
				local35 *= local27.amount + 1;
			}
			if (local35 > local21) {
				local21 = local35;
				local23 = local27;
			}
		}
		objstacks.pushPrevious(local23);
		@Pc(65) int local65 = -1;
		local35 = -1;
		@Pc(69) int local69 = 0;
		@Pc(71) int local71 = 0;
		for (local27 = (ObjStackEntity) objstacks.peekPrevious(); local27 != null; local27 = (ObjStackEntity) objstacks.getPrevious()) {
			if (local27.id != local23.id && local65 == -1) {
				local65 = local27.id;
				local69 = local27.amount;
			}
			if (local27.id != local23.id && local27.id != local65 && local35 == -1) {
				local35 = local27.id;
				local71 = local27.amount;
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
		@Pc(144) int local144 = x + (z << 7) + 1610612736;
		@Pc(148) ObjType local148 = ObjType.get(local23.id);
		this.mapSquare.addObject(local148.getModel(local23.amount), local118, this.getLandY(this.currentPlane, x * 128 + 64, z * 128 + 64), this.currentPlane, local144, z, x, local128);
	}

	@OriginalMember(owner = "client!client", name = "D", descriptor = "(I)V")
	private void createScene(boolean reuseHeightmap) {
		try {
			this.lastSceneLevel = -1;
			this.temporaryLocs.clear();
			this.locList.clear();
			this.spotanims.clear();
			this.projectiles.clear();
			Draw3D.clearPools();
			this.clearCaches();
			this.mapSquare.reset();
			for (@Pc(28) int plane = 0; plane < 4; plane++) {
				this.collisionMaps[plane].reset();
			}
			System.gc();

			@Pc(53) Scene scene = new Scene(104, this.levelRenderFlags, 104, this.levelHeightMaps);
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

			this.outBuffer.p1isaac(ClientProt.NO_TIMEOUT);
			@Pc(157) int local157;
			for (local73 = 0; local73 < local60; local73++) {
				local80 = (this.sceneMapIndex[local73] >> 8) * 64 - this.baseTileX;
				@Pc(143) int local143 = (this.sceneMapIndex[local73] & 0xFF) * 64 - this.baseTileZ;
				@Pc(148) byte[] local148 = this.sceneMapLandData[local73];
				if (local148 != null) {
					local157 = (new Buffer(local148)).g4();
					BZip2InputStream.read(local56, local157, local148, local148.length - 4, 4);
					scene.readLandscape(local56, (this.centerSectorX - 6) * 8, local143, local80, (this.centerSectorZ - 6) * 8, reuseHeightmap);
				} else if (this.centerSectorZ < 800) {
					scene.clearLandscape(local80, local143);
				}
			}

			this.outBuffer.p1isaac(ClientProt.NO_TIMEOUT);
			@Pc(225) int local225;
			for (local80 = 0; local80 < local60; local80++) {
				@Pc(216) byte[] local216 = this.sceneMapLocData[local80];
				if (local216 != null) {
					local225 = (new Buffer(local216)).g4();
					BZip2InputStream.read(local56, local225, local216, local216.length - 4, 4);
					local157 = (this.sceneMapIndex[local80] >> 8) * 64 - this.baseTileX;
					@Pc(259) int local259 = (this.sceneMapIndex[local80] & 0xFF) * 64 - this.baseTileZ;
					scene.readLocs(local56, this.mapSquare, this.collisionMaps, this.locList, local259, local157);
				}
			}

			this.outBuffer.p1isaac(ClientProt.NO_TIMEOUT);
			scene.buildLandscape(this.mapSquare, this.collisionMaps);
			this.areaViewport.makeTarget();

			this.outBuffer.p1isaac(ClientProt.NO_TIMEOUT);
			for (@Pc(301) LocEntity local301 = (LocEntity) this.locList.peekPrevious(); local301 != null; local301 = (LocEntity) this.locList.getPrevious()) {
				if ((this.levelRenderFlags[1][local301.x][local301.z] & 0x2) == 2) {
					local301.level--;
					if (local301.level < 0) {
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
				this.addLoc(local361.orientation, local361.tileX, local361.tileZ, local361.classType, local361.locIndex, local361.type, local361.level);
			}
		} catch (@Pc(390) Exception ignored) {
		}
		LocType.modelCache.clear();
		System.gc();
		Draw3D.setupPools();
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(I)V")
	@Override
	protected final void update() {
		if (this.errorStarted || this.errorLoading || this.errorHost) {
			return;
		}
		clientClock++;
		if (this.ingame) {
			this.updateGame();
		} else {
			this.updateTitle();
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
			if (local28 != null && local28.textCycle > 0) {
				local28.textCycle--;
				if (local28.textCycle == 0) {
					local28.spoken = null;
				}
			}
		}
		for (local16 = 0; local16 < this.npcCount; local16++) {
			@Pc(59) int local59 = this.npcIndices[local16];
			@Pc(64) NpcEntity local64 = this.npcEntities[local59];
			if (local64 != null && local64.textCycle > 0) {
				local64.textCycle--;
				if (local64.textCycle == 0) {
					local64.spoken = null;
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!hc;ZI)I")
	private int executeInterface(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg1) {
		if (arg0.script == null || arg1 >= arg0.script.length) {
			return -2;
		}
		try {
			@Pc(26) int[] local26 = arg0.script[arg1];
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
					local28 += this.energy;
				}
				if (local35 == 12) {
					local28 += this.weightCarried;
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
		@Pc(4) Graphics g = this.getBaseComponent().getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 789, 532);
		this.setLoopRate();
		@Pc(40) byte startY;
		@Pc(46) int y;
		if (this.errorLoading) {
			this.flameActive = false;
			g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 16));
			g.setColor(Color.yellow);
			startY = 35;
			g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, 35);
			y = startY + 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, 85);
			@Pc(55) int local55 = y + 50;
			g.setColor(Color.white);
			g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, 135);
			@Pc(72) int local72 = local55 + 30;
			g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, 165);
			@Pc(78) int local78 = local72 + 30;
			g.drawString("3: Try using a different game-world", 30, 195);
			@Pc(84) int local84 = local78 + 30;
			g.drawString("4: Try rebooting your computer", 30, 225);
			@Pc(90) int local90 = local84 + 30;
			g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, 255);
		}
		if (this.errorHost) {
			this.flameActive = false;
			g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 20));
			g.setColor(Color.white);
			g.drawString("Error - unable to load game!", 50, 50);
			g.drawString("To play RuneScape make sure you play from", 50, 100);
			g.drawString("http://www.runescape.com", 50, 150);
		}
		if (!this.errorStarted) {
			return;
		}
		this.flameActive = false;
		g.setColor(Color.yellow);
		startY = 35;
		g.drawString("Error a copy of RuneScape already appears to be loaded", 30, 35);
		y = startY + 50;
		g.setColor(Color.white);
		g.drawString("To fix this try the following (in order):", 30, 85);
		y += 50;
		g.setColor(Color.white);
		g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
		g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, 135);
		y += 30;
		g.drawString("2: Try rebooting your computer, and reloading", 30, 165);
		y += 30;
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(B)V")
	private void loadTitleBackground() {
		@Pc(8) byte[] background = this.title.read("title.dat", null);

		@Pc(14) Sprite sprite = new Sprite(background, this);
		this.titleLeft.makeTarget();
		sprite.drawOpaque(0, 0);

		this.titleRight.makeTarget();
		sprite.drawOpaque(-661, 0);

		this.titleTop.makeTarget();
		sprite.drawOpaque(-128, 0);

		this.titleBottom.makeTarget();
		sprite.drawOpaque(-214, -386);

		this.titleCenter.makeTarget();
		sprite.drawOpaque(-214, -186);

		this.titleBottomLeft.makeTarget();
		sprite.drawOpaque(0, -265);

		this.titleBottomRight.makeTarget();
		sprite.drawOpaque(-574, -265);

		this.titleLeftSpace.makeTarget();
		sprite.drawOpaque(-128, -186);

		this.titleRightSpace.makeTarget();
		sprite.drawOpaque(-574, -186);

		@Pc(110) int[] flipped = new int[sprite.spriteWidth];
		for (@Pc(112) int y = 0; y < sprite.spriteHeight; y++) {
			for (@Pc(116) int x = 0; x < sprite.spriteWidth; x++) {
				flipped[x] = sprite.pixels[sprite.spriteWidth + sprite.spriteWidth * y - x - 1];
			}
			System.arraycopy(flipped, 0, sprite.pixels, sprite.spriteWidth * y, sprite.spriteWidth);
		}

		this.titleLeft.makeTarget();
		sprite.drawOpaque(394, 0);

		this.titleRight.makeTarget();
		sprite.drawOpaque(-267, 0);

		this.titleTop.makeTarget();
		sprite.drawOpaque(266, 0);

		this.titleBottom.makeTarget();
		sprite.drawOpaque(180, -386);

		this.titleCenter.makeTarget();
		sprite.drawOpaque(180, -186);

		this.titleBottomLeft.makeTarget();
		sprite.drawOpaque(394, -265);

		this.titleBottomRight.makeTarget();
		sprite.drawOpaque(-180, -265);

		this.titleLeftSpace.makeTarget();
		sprite.drawOpaque(212, -186);

		this.titleRightSpace.makeTarget();
		sprite.drawOpaque(-180, -186);

		sprite = new Sprite(this.title, "logo", 0);
		this.titleTop.makeTarget();
		sprite.draw(super.gameWidth / 2 - sprite.spriteWidth / 2 - 128, 18);
		System.gc();
	}

	@OriginalMember(owner = "client!client", name = "E", descriptor = "(I)V")
	private void updateSceneSeqLocs() {
		for (@Pc(10) LocEntity e = (LocEntity) this.locList.peekPrevious(); e != null; e = (LocEntity) this.locList.getPrevious()) {
			@Pc(14) boolean local14 = false;
			e.seqDelay += this.sceneDelta;
			if (e.seqFrame == -1) {
				e.seqFrame = 0;
				local14 = true;
			}
			loop: {
				do {
					do {
						if (e.seqDelay <= e.seq.frameDelay[e.seqFrame]) {
							break loop;
						}
						e.seqDelay -= e.seq.frameDelay[e.seqFrame] + 1;
						e.seqFrame++;
						local14 = true;
					} while (e.seqFrame < e.seq.frameCount);
					e.seqFrame -= e.seq.delay;
				} while (e.seqFrame >= 0 && e.seqFrame < e.seq.frameCount);
				e.unlink();
				local14 = false;
			}
			if (local14) {
				@Pc(96) int plane = e.level;
				@Pc(99) int x = e.x;
				@Pc(102) int z = e.z;
				@Pc(104) int bitset = 0;
				if (e.classType == 0) {
					bitset = this.mapSquare.getWallBitset(plane, x, z);
				}
				if (e.classType == 1) {
					bitset = this.mapSquare.getWallDecorationBitset(plane, z, x);
				}
				if (e.classType == 2) {
					bitset = this.mapSquare.getLocationBitset(plane, x, z);
				}
				if (e.classType == 3) {
					bitset = this.mapSquare.getGroundDecorationBitset(plane, x, z);
				}
				if (bitset != 0 && (bitset >> 14 & 0x7FFF) == e.locIndex) {
					@Pc(171) int local171 = this.levelHeightMaps[plane][x][z];
					@Pc(182) int local182 = this.levelHeightMaps[plane][x + 1][z];
					@Pc(195) int local195 = this.levelHeightMaps[plane][x + 1][z + 1];
					@Pc(206) int local206 = this.levelHeightMaps[plane][x][z + 1];
					@Pc(210) LocType locType = LocType.get(e.locIndex);
					@Pc(212) int seq = -1;
					if (e.seqFrame != -1) {
						seq = e.seq.primaryFrames[e.seqFrame];
					}
					@Pc(235) int info;
					@Pc(239) int local239;
					@Pc(243) int local243;
					@Pc(258) Model local258;
					if (e.classType == 2) {
						info = this.mapSquare.getInfo(plane, x, z, bitset);
						local239 = info & 0x1F;
						local243 = info >> 6;
						if (local239 == 11) {
							local239 = 10;
						}
						local258 = locType.getModel(local239, local243, local171, local182, local195, local206, seq);
						this.mapSquare.setLocModel(x, local258, plane, z);
					} else if (e.classType == 1) {
						@Pc(282) Model local282 = locType.getModel(LocType.WALLDECOR_STRAIGHT_XOFFSET, 0, local171, local182, local195, local206, seq);
						this.mapSquare.setWallDecorationModel(z, x, local282, plane);
					} else if (e.classType == 0) {
						info = this.mapSquare.getInfo(plane, x, z, bitset);
						local239 = info & 0x1F;
						local243 = info >> 6;
						if (local239 == 2) {
							@Pc(320) int local320 = local243 + 1 & 0x3;
							@Pc(332) Model local332 = locType.getModel(LocType.WALL_L, local243 + 4, local171, local182, local195, local206, seq);
							@Pc(342) Model local342 = locType.getModel(LocType.WALL_L, local320, local171, local182, local195, local206, seq);
							this.mapSquare.setWallModels(local332, local342, z, x, plane);
						} else {
							local258 = locType.getModel(local239, local243, local171, local182, local195, local206, seq);
							this.mapSquare.setWallModel(local258, z, x, plane);
						}
					} else if (e.classType == 3) {
						info = this.mapSquare.getInfo(plane, x, z, bitset);
						local239 = info >> 6;
						@Pc(400) Model local400 = locType.getModel(LocType.GROUNDDECOR, local239, local171, local182, local195, local206, seq);
						this.mapSquare.setGroundDecorationModel(local400, z, x, plane);
					}
				} else {
					e.unlink();
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IJ)V")
	private void removeIgnore(@OriginalArg(1) long name37) {
		if (name37 == 0L) {
			return;
		}

		for (@Pc(14) int i = 0; i < this.ignoreCount; i++) {
			if (this.ignoreName37[i] == name37) {
				this.ignoreCount--;
				this.sidebarRedraw = true;
				for (@Pc(34) int j = i; j < this.ignoreCount; j++) {
					this.ignoreName37[j] = this.ignoreName37[j + 1];
				}
				this.outBuffer.p1isaac(ClientProt.IGNORELIST_DEL);
				this.outBuffer.p8(name37);
				return;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "q", descriptor = "(B)V")
	private void updateViewport() {
		if (this.selectedObject == 0 && this.selectedSpell == 0) {
			this.options[this.optionCount] = "Walk here";
			this.optionType[this.optionCount] = Cs1Actions.WALK;
			this.optionParamB[this.optionCount] = super.mouseX;
			this.optionParamC[this.optionCount] = super.mouseY;
			this.optionCount++;
		}

		@Pc(41) int lastBitset = -1;
		for (@Pc(52) int i = 0; i < Model.resourceCount; i++) {
			@Pc(58) int bitset = Model.hoveredBitsets[i];
			@Pc(62) int x = bitset & 0x7F;
			@Pc(68) int z = bitset >> 7 & 0x7F;
			@Pc(74) int type = bitset >> 29 & 0x3;
			@Pc(80) int id = bitset >> 14 & 0x7FFF;

			if (bitset != lastBitset) {
				lastBitset = bitset;

				@Pc(218) int option;
				if (type == 2 && this.mapSquare.getInfo(this.currentPlane, x, z, bitset) >= 0) {
					@Pc(100) LocType loc = LocType.get(id);
					if (this.selectedObject == 1) {
						this.options[this.optionCount] = "Use " + this.selectedObjName + " with @cya@" + loc.name;
						this.optionType[this.optionCount] = Cs1Actions.OPLOCU;
						this.optionParamB[this.optionCount] = x;
						this.optionParamC[this.optionCount] = z;
						this.optionParamA[this.optionCount] = bitset;
						this.optionCount++;
					} else if (this.selectedSpell != 1) {
						if (loc.ops != null) {
							for (option = 4; option >= 0; option--) {
								if (loc.ops[option] != null) {
									this.options[this.optionCount] = loc.ops[option] + " @cya@" + loc.name;
									if (option == 0) {
										this.optionType[this.optionCount] = Cs1Actions.OPLOC1;
									}
									if (option == 1) {
										this.optionType[this.optionCount] = Cs1Actions.OPLOC2;
									}
									if (option == 2) {
										this.optionType[this.optionCount] = Cs1Actions.OPLOC3;
									}
									if (option == 3) {
										this.optionType[this.optionCount] = Cs1Actions.OPLOC4;
									}
									if (option == 4) {
										this.optionType[this.optionCount] = Cs1Actions.OPLOC5;
									}
									this.optionParamB[this.optionCount] = x;
									this.optionParamC[this.optionCount] = z;
									this.optionParamA[this.optionCount] = bitset;
									this.optionCount++;
								}
							}
						}

						this.options[this.optionCount] = "Examine @cya@" + loc.name;
						this.optionType[this.optionCount] = Cs1Actions.EXAMINE_LOC;
						this.optionParamB[this.optionCount] = x;
						this.optionParamC[this.optionCount] = z;
						this.optionParamA[this.optionCount] = bitset;
						this.optionCount++;
					} else if ((this.selectedFlags & 0x4) == 4) {
						this.options[this.optionCount] = this.selectedSpellPrefix + " @cya@" + loc.name;
						this.optionType[this.optionCount] = Cs1Actions.OPLOCT;
						this.optionParamB[this.optionCount] = x;
						this.optionParamC[this.optionCount] = z;
						this.optionParamA[this.optionCount] = bitset;
						this.optionCount++;
					}
				}

				@Pc(395) NpcEntity local395;
				if (type == 1) {
					@Pc(366) NpcEntity npc = this.npcEntities[id];
					if (npc.info.size == 1 && (npc.x & 0x7F) == 64 && (npc.z & 0x7F) == 64) {
						for (option = 0; option < this.npcCount; option++) {
							local395 = this.npcEntities[this.npcIndices[option]];
							if (local395 != null && local395 != npc && local395.info.size == 1 && local395.x == npc.x && local395.z == npc.z) {
								this.drawTooltip(local395.info, x, z, this.npcIndices[option]);
							}
						}
					}
					this.drawTooltip(npc.info, x, z, id);
				}

				if (type == 0) {
					@Pc(446) PlayerEntity other = this.playerEntities[id];
					if ((other.x & 0x7F) == 64 && (other.z & 0x7F) == 64) {
						for (option = 0; option < this.npcCount; option++) {
							local395 = this.npcEntities[this.npcIndices[option]];
							if (local395 != null && local395.info.size == 1 && local395.x == other.x && local395.z == other.z) {
								this.drawTooltip(local395.info, x, z, this.npcIndices[option]);
							}
						}

						for (@Pc(505) int local505 = 0; local505 < this.playerCount; local505++) {
							@Pc(515) PlayerEntity local515 = this.playerEntities[this.playerIndices[local505]];
							if (local515 != null && local515 != other && local515.x == other.x && local515.z == other.z) {
								this.addPlayerOptions(local515, x, z, this.playerIndices[local505]);
							}
						}
					}

					this.addPlayerOptions(other, x, z, id);
				}

				if (type == 3) {
					@Pc(565) LinkedList objStacks = this.objects[this.currentPlane][x][z];
					if (objStacks != null) {
						for (@Pc(572) ObjStackEntity objStack = (ObjStackEntity) objStacks.peekNext(); objStack != null; objStack = (ObjStackEntity) objStacks.getNext()) {
							@Pc(578) ObjType obj = ObjType.get(objStack.id);
							if (this.selectedObject == 1) {
								this.options[this.optionCount] = "Use " + this.selectedObjName + " with @lre@" + obj.name;
								this.optionType[this.optionCount] = Cs1Actions.OPOBJU;
								this.optionParamB[this.optionCount] = x;
								this.optionParamC[this.optionCount] = z;
								this.optionParamA[this.optionCount] = objStack.id;
								this.optionCount++;
							} else if (this.selectedSpell != 1) {
								for (option = 4; option >= 0; option--) {
									if (obj.op != null && obj.op[option] != null) {
										this.options[this.optionCount] = obj.op[option] + " @lre@" + obj.name;
										if (option == 0) {
											this.optionType[this.optionCount] = Cs1Actions.OPOBJ1;
										}
										if (option == 1) {
											this.optionType[this.optionCount] = Cs1Actions.OPOBJ2;
										}
										if (option == 2) {
											this.optionType[this.optionCount] = Cs1Actions.OPOBJ3;
										}
										if (option == 3) {
											this.optionType[this.optionCount] = Cs1Actions.OPOBJ4;
										}
										if (option == 4) {
											this.optionType[this.optionCount] = Cs1Actions.OPOBJ5;
										}
										this.optionParamB[this.optionCount] = x;
										this.optionParamC[this.optionCount] = z;
										this.optionParamA[this.optionCount] = objStack.id;
										this.optionCount++;
									} else if (option == 2) {
										this.options[this.optionCount] = "Take @lre@" + obj.name;
										this.optionType[this.optionCount] = Cs1Actions.OPOBJ3;
										this.optionParamB[this.optionCount] = x;
										this.optionParamC[this.optionCount] = z;
										this.optionParamA[this.optionCount] = objStack.id;
										this.optionCount++;
									}
								}

								this.options[this.optionCount] = "Examine @lre@" + obj.name;
								this.optionType[this.optionCount] = Cs1Actions.EXAMINE_OBJSTACK;
								this.optionParamB[this.optionCount] = x;
								this.optionParamC[this.optionCount] = z;
								this.optionParamA[this.optionCount] = objStack.id;
								this.optionCount++;
							} else if ((this.selectedFlags & 0x1) == 1) {
								this.options[this.optionCount] = this.selectedSpellPrefix + " @lre@" + obj.name;
								this.optionType[this.optionCount] = Cs1Actions.OPOBJT;
								this.optionParamB[this.optionCount] = x;
								this.optionParamC[this.optionCount] = z;
								this.optionParamA[this.optionCount] = objStack.id;
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
		@Pc(17) int start;
		for (@Pc(9) int local9 = -1; local9 < this.playerCount; local9++) {
			if (local9 == -1) {
				start = this.LOCAL_PLAYER_INDEX;
			} else {
				start = this.playerIndices[local9];
			}
			@Pc(29) PlayerEntity local29 = this.playerEntities[start];
			if (local29 != null) {
				this.updateEntity(local29, 1);
			}
		}
		updatePlayersCounter++;
		if (updatePlayersCounter <= 1406) {
			return;
		}
		updatePlayersCounter = 0;
		this.outBuffer.p1isaac(ClientProt.ANTICHEAT_UPDATE_PLAYERS);
		this.outBuffer.p1(0);
		start = this.outBuffer.pos;
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
		this.outBuffer.psize1(this.outBuffer.pos - start);
	}

	@OriginalMember(owner = "client!client", name = "r", descriptor = "(B)V")
	private void drawTileHint() {
		if (this.hintType == 2) {
			this.projectToScreen((this.hintTileZ - this.baseTileZ << 7) + this.hintOffsetZ, (this.hintTileX - this.baseTileX << 7) + this.hintOffsetX, this.hintHeight * 2);
			if (this.drawX > -1 && clientClock % 20 < 10) {
				this.headicons[2].draw(this.drawX - 12, this.drawY - 28);
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(IILclient!kb;)V")
	private void updateLocalPlayer(@OriginalArg(1) int size, @OriginalArg(2) Buffer b) {
		b.accessBits();

		@Pc(7) int hasUpdate = b.gBit(1);
		if (hasUpdate == 0) {
			return;
		}

		@Pc(21) int type = b.gBit(2);
		if (type == 0) {
			this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
		} else if (type == 1) {
			int walkDir = b.gBit(3);
			this.self.walk(false, walkDir);
			int hasMaskUpdate = b.gBit(1);
			if (hasMaskUpdate == 1) {
				this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
			}
		} else if (type == 2) {
			int walkDir = b.gBit(3);
			this.self.walk(true, walkDir);
			int runDir = b.gBit(3);
			this.self.walk(true, runDir);
			int hasMaskUpdate = b.gBit(1);
			if (hasMaskUpdate == 1) {
				this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
			}
		} else if (type == 3) {
			this.currentPlane = b.gBit(2);
			int x = b.gBit(7);
			int z = b.gBit(7);
			int warp = b.gBit(1);
			this.self.move(warp == 1, x, z);
			@Pc(158) int hasMaskUpdate = b.gBit(1);
			if (hasMaskUpdate == 1) {
				this.entityUpdateIndices[this.updateCount++] = this.LOCAL_PLAYER_INDEX;
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "o", descriptor = "(Z)V")
	private void drawChatback() {
		this.areaChatback.makeTarget();
		Draw3D.offsets = this.chatOffsets;
		this.chatback.draw(0, 0);
		if (this.showSocialInput) {
			this.bold12.drawCentered(this.socialMessage, 239, 40, 0);
			this.bold12.drawCentered(this.socialInput + "*", 239, 60, 128);
		} else if (this.chatbackInputType) {
			this.bold12.drawCentered("Enter amount:", 239, 40, 0);
			this.bold12.drawCentered(this.chatbackInput + "*", 239, 60, 128);
		} else if (this.chatbackMessage != null) {
			this.bold12.drawCentered(this.chatbackMessage, 239, 40, 0);
			this.bold12.drawCentered("Click to continue", 239, 60, 128);
		} else if (this.chatbackComponentId != -1) {
			this.drawInterface(0, 0, Component.instances[this.chatbackComponentId], 0);
		} else if (this.stickyChatbackComponentId == -1) {
			@Pc(135) Font font = this.plain12;
			@Pc(137) int messageCount = 0;
			Draw2D.setBounds(0, 463, 0, 77);
			for (@Pc(145) int local145 = 0; local145 < 100; local145++) {
				if (this.chatMessage[local145] != null) {
					@Pc(157) int type = this.chatMessageType[local145];
					@Pc(166) int y = this.chatScrollAmount + 70 - messageCount * 14;
					if (type == 0) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 1) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0xffffff, this.chatMessagePrefix[local145] + ":");
							font.draw(font.stringWidth(this.chatMessagePrefix[local145]) + 12, y, 255, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 2 && (this.chatPublicSetting == 0 || this.chatPublicSetting == 1 && this.isFriend(this.chatMessagePrefix[local145]))) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0, this.chatMessagePrefix[local145] + ":");
							font.draw(font.stringWidth(this.chatMessagePrefix[local145]) + 12, y, 255, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if ((type == 3 || type == 7) && this.splitPrivateChat == 0 && (type == 7 || this.chatPrivateSetting == 0 || this.chatPrivateSetting == 1 && this.isFriend(this.chatMessagePrefix[local145]))) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0, "From " + this.chatMessagePrefix[local145] + ":");
							font.draw(font.stringWidth("From " + this.chatMessagePrefix[local145]) + 12, y, 0x800000, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 4 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[local145]))) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0x800080, this.chatMessagePrefix[local145] + " " + this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 5 && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0x800000, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 6 && this.splitPrivateChat == 0 && this.chatPrivateSetting < 2) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0, "To " + this.chatMessagePrefix[local145] + ":");
							font.draw(font.stringWidth("To " + this.chatMessagePrefix[local145]) + 12, y, 0x800000, this.chatMessage[local145]);
						}
						messageCount++;
					}
					if (type == 8 && (this.chatTradeDuelSetting == 0 || this.chatTradeDuelSetting == 1 && this.isFriend(this.chatMessagePrefix[local145]))) {
						if (y > 0 && y < 110) {
							font.draw(4, y, 0xcbb789, this.chatMessagePrefix[local145] + " " + this.chatMessage[local145]);
						}
						messageCount++;
					}
				}
			}
			Draw2D.resetBounds();
			this.chatScrollY = messageCount * 14 + 7;
			if (this.chatScrollY < 78) {
				this.chatScrollY = 78;
			}
			this.drawScrollbar(463, 0, this.chatScrollY - this.chatScrollAmount - 77, this.chatScrollY, 77);
			font.draw(4, 90, 0, StringUtils.formatName(this.username) + ":");
			font.draw(font.stringWidth(this.username + ": ") + 6, 90, 255, this.input + "*");
			Draw2D.drawHorizontalLine(0, 77, 479, 0);
		} else {
			this.drawInterface(0, 0, Component.instances[this.stickyChatbackComponentId], 0);
		}
		if (this.menuVisible && this.mouseArea == 2) {
			this.drawMenu();
		}
		this.areaChatback.drawAt(22, 375, super.graphic);
		this.areaViewport.makeTarget();
		Draw3D.offsets = this.viewportOffsets;
	}

	@OriginalMember(owner = "client!client", name = "p", descriptor = "(Z)Z")
	private boolean readPacket() {
		if (this.stream == null) {
			return false;
		}

		try {
			@Pc(15) int available = this.stream.available();
			if (available == 0) {
				return false;
			}

			if (this.packetOpcode == -1) {
				this.stream.read(this.inBuffer.data, 0, 1);
				this.packetOpcode = this.inBuffer.data[0] & 0xFF;
				if (this.isaac != null) {
					this.packetOpcode = this.packetOpcode - this.isaac.nextInt() & 0xFF;
				}
				this.packetLength = ServerProt.PACKET_LENGTHS[this.packetOpcode];
				available--;
			}

			if (this.packetLength == -1) {
				if (available <= 0) {
					return false;
				}

				this.stream.read(this.inBuffer.data, 0, 1);
				this.packetLength = this.inBuffer.data[0] & 0xFF;
				available--;
			}

			if (this.packetLength == -2) {
				if (available <= 1) {
					return false;
				}

				this.stream.read(this.inBuffer.data, 0, 2);
				this.inBuffer.pos = 0;
				this.packetLength = this.inBuffer.g2();
				available -= 2;
			}

			if (available < this.packetLength) {
				return false;
			}

			this.inBuffer.pos = 0;
			this.stream.read(this.inBuffer.data, 0, this.packetLength);

			this.netIdleCycles = 0;
			this.thirdMostRecentOpcode = this.secondMostRecentOpcode;
			this.secondMostRecentOpcode = this.lastPacketOpcode;
			this.lastPacketOpcode = this.packetOpcode;

			if (this.packetOpcode == ServerProt.VARP_SMALL) {
				int varp = this.inBuffer.g2();
				@Pc(163) byte state = this.inBuffer.g1b();
				this.defaultVariables[varp] = state;
				if (this.variables[varp] != state) {
					this.variables[varp] = state;
					this.updateVarp(varp);
					this.sidebarRedraw = true;
					if (this.stickyChatbackComponentId != -1) {
						this.redrawChatback = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_FRIENDLIST) {
				long name37 = this.inBuffer.g8();
				int world = this.inBuffer.g1();
				@Pc(217) String name = StringUtils.formatName(StringUtils.fromBase37(name37));
				for (int i = 0; i < this.friendCount; i++) {
					if (name37 == this.friendName37[i]) {
						if (this.friendWorld[i] != world) {
							this.friendWorld[i] = world;
							this.sidebarRedraw = true;
							if (world > 0) {
								this.addMessage(5, "", name + " has logged in.");
							}
							if (world == 0) {
								this.addMessage(5, "", name + " has logged out.");
							}
						}
						name = null;
						break;
					}
				}
				if (name != null && this.friendCount < 100) {
					this.friendName37[this.friendCount] = name37;
					this.friendName[this.friendCount] = name;
					this.friendWorld[this.friendCount] = world;
					this.friendCount++;
					this.sidebarRedraw = true;
				}
				@Pc(315) boolean sorted = false;
				while (!sorted) {
					sorted = true;
					for (int j = 0; j < this.friendCount - 1; j++) {
						if (this.friendWorld[j] != nodeId && this.friendWorld[j + 1] == nodeId || this.friendWorld[j] == 0 && this.friendWorld[j + 1] != 0) {
							int tempWorld = this.friendWorld[j];
							this.friendWorld[j] = this.friendWorld[j + 1];
							this.friendWorld[j + 1] = tempWorld;
							@Pc(376) String tempName = this.friendName[j];
							this.friendName[j] = this.friendName[j + 1];
							this.friendName[j + 1] = tempName;
							@Pc(398) long tempName37 = this.friendName37[j];
							this.friendName37[j] = this.friendName37[j + 1];
							this.friendName37[j + 1] = tempName37;
							this.sidebarRedraw = true;
							sorted = false;
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
			if (this.packetOpcode == ServerProt.DATA_LAND_DONE) {
				int x = this.inBuffer.g1();
				int z = this.inBuffer.g1();
				int region = -1;
				for (int k = 0; k < this.sceneMapIndex.length; k++) {
					if (this.sceneMapIndex[k] == (x << 8) + z) {
						region = k;
						break;
					}
				}
				if (region != -1) {
					signlink.cachesave("m" + x + "_" + z, this.sceneMapLandData[region]);
					this.sceneState = 1;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.NPC_INFO) {
				this.updateNpcs(this.inBuffer, this.packetLength);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.LOAD_AREA) {
				int sectorX = this.inBuffer.g2();
				int sectorZ = this.inBuffer.g2();
				if (this.centerSectorX == sectorX && this.centerSectorZ == sectorZ && this.sceneState != 0) {
					this.packetOpcode = -1;
					return true;
				}

				this.centerSectorX = sectorX;
				this.centerSectorZ = sectorZ;
				this.baseTileX = (this.centerSectorX - 6) * 8;
				this.baseTileZ = (this.centerSectorZ - 6) * 8;
				this.sceneState = 1;

				this.areaViewport.makeTarget();
				this.plain12.drawCentered("Loading - please wait.", 257, 151, 0);
				this.plain12.drawCentered("Loading - please wait.", 256, 150, 0xffffff);
				this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);
				signlink.looprate(5);

				int mapCount = (this.packetLength - 2) / 10;
				this.sceneMapLandData = new byte[mapCount][];
				this.sceneMapLocData = new byte[mapCount][];
				this.sceneMapIndex = new int[mapCount];

				this.outBuffer.p1isaac(ClientProt.MAP_REQUEST_AREAS);
				this.outBuffer.p1(0);
				int size = 0;
				for (int i = 0; i < mapCount; i++) {
					int x = this.inBuffer.g1();
					int z = this.inBuffer.g1();
					int landCrc = this.inBuffer.g4();
					int locCrc = this.inBuffer.g4();
					this.sceneMapIndex[i] = (x << 8) + z;
					@Pc(686) byte[] data;
					if (landCrc != 0) {
						data = signlink.cacheload("m" + x + "_" + z);
						if (data != null) {
							if (Buffer.crc32(data) != landCrc) {
								data = null;
							}
						}
						if (data == null) {
							this.sceneState = 0;
							this.outBuffer.p1(0);
							this.outBuffer.p1(x);
							this.outBuffer.p1(z);
							size += 3;
						} else {
							this.sceneMapLandData[i] = data;
						}
					}
					if (locCrc != 0) {
						data = signlink.cacheload("l" + x + "_" + z);
						if (data != null) {
							if (Buffer.crc32(data) != locCrc) {
								data = null;
							}
						}
						if (data == null) {
							this.sceneState = 0;
							this.outBuffer.p1(1);
							this.outBuffer.p1(x);
							this.outBuffer.p1(z);
							size += 3;
						} else {
							this.sceneMapLocData[i] = data;
						}
					}
				}
				this.outBuffer.psize1(size);

				signlink.looprate(50);
				this.areaViewport.makeTarget();
				if (this.sceneState == 0) {
					this.plain12.drawCentered("Map area updated since last visit, so load will take longer this time only", 257, 166, 0);
					this.plain12.drawCentered("Map area updated since last visit, so load will take longer this time only", 256, 165, 0xffffff);
				}
				this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);

				int deltaX = this.baseTileX - this.mapLastBaseX;
				int deltaZ = this.baseTileZ - this.mapLastBaseZ;
				this.mapLastBaseX = this.baseTileX;
				this.mapLastBaseZ = this.baseTileZ;
				for (int n = 0; n < 8192; n++) {
					@Pc(856) NpcEntity e = this.npcEntities[n];
					if (e != null) {
						for (@Pc(860) int m = 0; m < 10; m++) {
							e.pathTileX[m] -= deltaX;
							e.pathTileZ[m] -= deltaZ;
						}
						e.x -= deltaX * 128;
						e.z -= deltaZ * 128;
					}
				}
				for (int n = 0; n < this.MAX_PLAYER_COUNT; n++) {
					@Pc(911) PlayerEntity e = this.playerEntities[n];
					if (e != null) {
						for (@Pc(915) int m = 0; m < 10; m++) {
							e.pathTileX[m] -= deltaX;
							e.pathTileZ[m] -= deltaZ;
						}
						e.x -= deltaX * 128;
						e.z -= deltaZ * 128;
					}
				}

				@Pc(960) byte startTileX = 0;
				@Pc(962) byte endTileX = 104;
				@Pc(964) byte dirX = 1;
				if (deltaX < 0) {
					startTileX = 103;
					endTileX = -1;
					dirX = -1;
				}

				@Pc(974) byte startTileZ = 0;
				@Pc(976) byte endTileZ = 104;
				@Pc(978) byte dirZ = 1;
				if (deltaZ < 0) {
					startTileZ = 103;
					endTileZ = -1;
					dirZ = -1;
				}

				for (@Pc(988) int x = startTileX; x != endTileX; x += dirX) {
					for (@Pc(992) int z = startTileZ; z != endTileZ; z += dirZ) {
						@Pc(998) int dstX = x + deltaX;
						@Pc(1002) int dstZ = z + deltaZ;
						for (@Pc(1004) int plane = 0; plane < 4; plane++) {
							if (dstX >= 0 && dstZ >= 0 && dstX < 104 && dstZ < 104) {
								this.objects[plane][x][z] = this.objects[plane][dstX][dstZ];
							} else {
								this.objects[plane][x][z] = null;
							}
						}
					}
				}

				for (@Pc(1066) SpawnedLoc loc = (SpawnedLoc) this.spawnedLocations.peekPrevious(); loc != null; loc = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					loc.tileX -= deltaX;
					loc.tileZ -= deltaZ;
					if (loc.tileX < 0 || loc.tileZ < 0 || loc.tileX >= 104 || loc.tileZ >= 104) {
						loc.unlink();
					}
				}

				if (this.flagTileX != 0) {
					this.flagTileX -= deltaX;
					this.flagTileY -= deltaZ;
				}
				this.cameraOriented = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETPLAYERHEAD) {
				int component = this.inBuffer.g2();
				Component.instances[component].modelDisabled = this.self.getHeadModel();
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
				String name = this.inBuffer.gstr();
				int crc = this.inBuffer.g4();
				int length = this.inBuffer.g4();
				if (!name.equals(this.currentMidi) && this.midiActive && !lowMemory) {
					this.setMidi(crc, name, length);
				}
				this.currentMidi = name;
				this.midiCrc = crc;
				this.midiSize = length;
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
				int x = this.inBuffer.g1();
				int z = this.inBuffer.g1();
				int region = -1;
				for (int k = 0; k < this.sceneMapIndex.length; k++) {
					if (this.sceneMapIndex[k] == (x << 8) + z) {
						region = k;
					}
				}
				if (region != -1) {
					signlink.cachesave("l" + x + "_" + z, this.sceneMapLocData[region]);
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
			if (this.packetOpcode == ServerProt.OBJ_COUNT || this.packetOpcode == ServerProt.LOC_ADD_CHANGE || this.packetOpcode == ServerProt.OBJ_ADD || this.packetOpcode == ServerProt.SPOTANIM_SPECIFIC || this.packetOpcode == ServerProt.MAP_PROJANIM || this.packetOpcode == ServerProt.OBJ_DEL || this.packetOpcode == ServerProt.OBJ_REVEAL || this.packetOpcode == ServerProt.LOC_ANIM || this.packetOpcode == ServerProt.LOC_DEL || this.packetOpcode == ServerProt.LOC_ADD) {
				this.readZonePacket(this.inBuffer, this.packetOpcode);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSUB) {
				int viewportIndex = this.inBuffer.g2();
				int sidebarComponent = this.inBuffer.g2();
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.chatbackInputType) {
					this.chatbackInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = viewportIndex;
				this.sidebarInterfaceId = sidebarComponent;
				this.sidebarRedraw = true;
				this.sidebarRedrawIcons = true;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.VARP_LARGE) {
				int varp = this.inBuffer.g2();
				int state = this.inBuffer.g4();
				this.defaultVariables[varp] = state;
				if (this.variables[varp] != state) {
					this.variables[varp] = state;
					this.updateVarp(varp);
					this.sidebarRedraw = true;
					if (this.stickyChatbackComponentId != -1) {
						this.redrawChatback = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETANIM) {
				int component = this.inBuffer.g2();
				Component.instances[component].seqId = this.inBuffer.g2();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTAB) {
				int component = this.inBuffer.g2();
				int sidebar = this.inBuffer.g1();
				if (component == 0xffff) {
					component = -1;
				}
				this.tabComponentId[sidebar] = component;
				this.sidebarRedraw = true;
				this.sidebarRedrawIcons = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.DATA_LOC) {
				int x = this.inBuffer.g1();
				int z = this.inBuffer.g1();
				int offset = this.inBuffer.g2();
				int length = this.inBuffer.g2();
				int region = -1;
				for (int var5 = 0; var5 < this.sceneMapIndex.length; var5++) {
					if (this.sceneMapIndex[var5] == (x << 8) + z) {
						region = var5;
					}
				}
				if (region != -1) {
					if (this.sceneMapLocData[region] == null || this.sceneMapLocData[region].length != length) {
						this.sceneMapLocData[region] = new byte[length];
					}
					this.inBuffer.gdata(this.sceneMapLocData[region], offset, this.packetLength - 6);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.FINISH_TRACKING) {
				@Pc(1701) Buffer trackingBuffer = InputTracking.stop();
				if (trackingBuffer != null) {
					this.outBuffer.p1isaac(ClientProt.EVENT_TRACKING);
					this.outBuffer.p2(trackingBuffer.pos);
					this.outBuffer.pdata(trackingBuffer.data, 0, trackingBuffer.pos);
					trackingBuffer.release();
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_INV_FULL) {
				this.sidebarRedraw = true;
				int componentId = this.inBuffer.g2();
				Component component = Component.instances[componentId];
				int slots = this.inBuffer.g1();
				for (int k = 0; k < slots; k++) {
					component.inventoryIndices[k] = this.inBuffer.g2();
					int amount = this.inBuffer.g1();
					if (amount == 255) {
						amount = this.inBuffer.g4();
					}
					component.inventoryAmount[k] = amount;
				}
				for (int i = slots; i < component.inventoryIndices.length; i++) {
					component.inventoryIndices[i] = 0;
					component.inventoryAmount[i] = 0;
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
				this.chatbackInputType = true;
				this.chatbackInput = "";
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_INV_CLEAR) {
				int componentId = this.inBuffer.g2();
				Component compnent = Component.instances[componentId];
				for (int i = 0; i < compnent.inventoryIndices.length; i++) {
					compnent.inventoryIndices[i] = -1;
					compnent.inventoryIndices[i] = 0;
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
					signlink.dnslookup(StringUtils.fromIPv4(this.lastLoginIp));
					this.closeInterface();
					@Pc(1915) short clientCode = 650;
					if (this.daysSinceRecoveryChange != 201) {
						clientCode = 655;
					}
					this.reportInput = "";
					this.reportAbuseMuteToggle = false;
					for (int i = 0; i < Component.instances.length; i++) {
						if (Component.instances[i] != null && Component.instances[i].clientCode == clientCode) {
							this.viewportInterfaceIndex = Component.instances[i].parent;
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
					this.sidebarRedraw = true;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.MIDI_JINGLE) {
				if (this.midiActive && !lowMemory) {
					int delay = this.inBuffer.g2();
					int length = this.inBuffer.g4();
					int streamLength = this.packetLength - 6;
					@Pc(2018) byte[] data = new byte[length];
					BZip2InputStream.read(data, length, this.inBuffer.data, streamLength, this.inBuffer.pos);
					this.midisave(data, length, false);
					this.nextMusicDelay = delay;
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
				int synthId = this.inBuffer.g2();
				int loops = this.inBuffer.g1();
				int delay = this.inBuffer.g2();
				if (this.effectsEnabled && !lowMemory && this.waveCount < 50) {
					this.waveId[this.waveCount] = synthId;
					this.waveLoops[this.waveCount] = loops;
					this.waveDelay[this.waveCount] = delay + SoundTrack.delays[synthId];
					this.waveCount++;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETNPCHEAD) {
				int componentId = this.inBuffer.g2();
				int npcId = this.inBuffer.g2();
				@Pc(2130) NpcType npcType = NpcType.get(npcId);
				Component.instances[componentId].modelDisabled = npcType.getHeadModel();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_ZONE_PARTIAL_FOLLOWS) {
				this.localPosX = this.inBuffer.g1();
				this.localPosZ = this.inBuffer.g1();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETMODEL_COLOUR) {
				int componentId = this.inBuffer.g2();
				int recol_s = this.inBuffer.g2();
				int recol_d = this.inBuffer.g2();
				Component component = Component.instances[componentId];
				@Pc(2184) Model m = component.modelDisabled;
				if (m != null) {
					m.recolor(recol_s, recol_d);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CHAT_FILTER_SETTINGS) {
				this.chatPublicSetting = this.inBuffer.g1();
				this.chatPrivateSetting = this.inBuffer.g1();
				this.chatTradeDuelSetting = this.inBuffer.g1();
				this.chatRedrawSettings = true;
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSIDEBAR) {
				int componentId = this.inBuffer.g2();
				this.resetParentComponentSeq(componentId);
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.chatbackInputType) {
					this.chatbackInputType = false;
					this.redrawChatback = true;
				}
				this.sidebarInterfaceId = componentId;
				this.sidebarRedraw = true;
				this.sidebarRedrawIcons = true;
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENBOTTOM) {
				int componentId = this.inBuffer.g2();
				this.resetParentComponentSeq(componentId);
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.sidebarRedraw = true;
					this.sidebarRedrawIcons = true;
				}
				this.chatbackComponentId = componentId;
				this.redrawChatback = true;
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETPOSITION) {
				int componentId = this.inBuffer.g2();
				int x = this.inBuffer.g2b();
				int y = this.inBuffer.g2b();
				Component compnent = Component.instances[componentId];
				compnent.x = x;
				compnent.y = y;
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
				for (int x = this.localPosX; x < this.localPosX + 8; x++) {
					for (int z = this.localPosZ; z < this.localPosZ + 8; z++) {
						if (this.objects[this.currentPlane][x][z] != null) {
							this.objects[this.currentPlane][x][z] = null;
							this.updateObjectStack(x, z);
						}
					}
				}
				for (@Pc(2487) SpawnedLoc loc = (SpawnedLoc) this.spawnedLocations.peekPrevious(); loc != null; loc = (SpawnedLoc) this.spawnedLocations.getPrevious()) {
					if (loc.tileX >= this.localPosX && loc.tileX < this.localPosX + 8 && loc.tileZ >= this.localPosZ && loc.tileZ < this.localPosZ + 8 && loc.level == this.currentPlane) {
						this.addLoc(loc.lastRotation, loc.tileX, loc.tileZ, loc.classType, loc.lastLocIndex, loc.lastType, loc.level);
						loc.unlink();
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.DATA_LAND) {
				int x = this.inBuffer.g1();
				int z = this.inBuffer.g1();
				int offset = this.inBuffer.g2();
				int length = this.inBuffer.g2();
				int region = -1;
				for (int i = 0; i < this.sceneMapIndex.length; i++) {
					if (this.sceneMapIndex[i] == (x << 8) + z) {
						region = i;
					}
				}
				if (region != -1) {
					if (this.sceneMapLandData[region] == null || this.sceneMapLandData[region].length != length) {
						this.sceneMapLandData[region] = new byte[length];
					}
					this.inBuffer.gdata(this.sceneMapLandData[region], offset, this.packetLength - 6);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.MESSAGE_PRIVATE) {
				long name37 = this.inBuffer.g8();
				int messageId = this.inBuffer.g4();
				int otherPlayerRights = this.inBuffer.g1();
				boolean ignored = false;
				for (int i = 0; i < 100; i++) {
					if (this.privateMessageIndex[i] == messageId) {
						ignored = true;
						break;
					}
				}
				if (otherPlayerRights <= 1) {
					for (int i = 0; i < this.ignoreCount; i++) {
						if (this.ignoreName37[i] == name37) {
							ignored = true;
							break;
						}
					}
				}
				if (!ignored && this.tutorialIslandState == 0) {
					try {
						this.privateMessageIndex[this.privateMessageCount] = messageId;
						this.privateMessageCount = (this.privateMessageCount + 1) % 100;
						@Pc(2721) String encoded = TextEncoder.read(this.inBuffer, this.packetLength - 13);
						@Pc(2725) String filtered = WordPack.getFiltered(encoded);
						if (otherPlayerRights > 1) {
							this.addMessage(7, StringUtils.formatName(StringUtils.fromBase37(name37)), filtered);
						} else {
							this.addMessage(3, StringUtils.formatName(StringUtils.fromBase37(name37)), filtered);
						}
					} catch (@Pc(2752) Exception ex) {
						signlink.reporterror("cde1");
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.RESET_CLIENT_VARCACHE) {
				for (int i = 0; i < this.variables.length; i++) {
					if (this.variables[i] != this.defaultVariables[i]) {
						this.variables[i] = this.defaultVariables[i];
						this.updateVarp(i);
						this.sidebarRedraw = true;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETMODEL) {
				int componentId = this.inBuffer.g2();
				int modelId = this.inBuffer.g2();
				Component.instances[componentId].modelDisabled = new Model(modelId);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENSTICKY) {
				this.stickyChatbackComponentId = this.inBuffer.g2b();
				this.redrawChatback = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_RUNENERGY) {
				if (this.selectedTab == 12) {
					this.sidebarRedraw = true;
				}
				this.energy = this.inBuffer.g1();
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
					int var1 = this.cutsceneLocalX * 128 + 64;
					int var4 = this.cutsceneLocalY * 128 + 64;
					int var2 = this.getLandY(this.currentPlane, this.cutsceneLocalX, this.cutsceneLocalY) - this.cutsceneHeightOffset;
					int k = var1 - this.cameraX;
					int i = var2 - this.cameraY;
					int var5 = var4 - this.cameraZ;
					int j = (int) Math.sqrt(k * k + var5 * var5);
					this.cameraPitch = (int) (Math.atan2(i, j) * 325.949D) & 0x7FF;
					this.cameraOrbitYaw = (int) (Math.atan2(k, var5) * -325.949D) & 0x7FF;
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
				this.sidebarRedraw = true;
				this.sidebarRedrawIcons = true;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.MESSAGE_GAME) {
				String message = this.inBuffer.gstr();
				@Pc(3043) long otherPlayer37;
				if (message.endsWith(":tradereq:")) {
					String otherPlayer = message.substring(0, message.indexOf(":"));
					otherPlayer37 = StringUtils.toBase37(otherPlayer);
					boolean ignored = false;
					for (int i = 0; i < this.ignoreCount; i++) {
						if (this.ignoreName37[i] == otherPlayer37) {
							ignored = true;
							break;
						}
					}
					if (!ignored && this.tutorialIslandState == 0) {
						this.addMessage(4, otherPlayer, "wishes to trade with you.");
					}
				} else if (message.endsWith(":duelreq:")) {
					String otherPlayer = message.substring(0, message.indexOf(":"));
					otherPlayer37 = StringUtils.toBase37(otherPlayer);
					boolean ignored = false;
					for (int i = 0; i < this.ignoreCount; i++) {
						if (this.ignoreName37[i] == otherPlayer37) {
							ignored = true;
							break;
						}
					}
					if (!ignored && this.tutorialIslandState == 0) {
						this.addMessage(8, otherPlayer, "wishes to duel with you.");
					}
				} else {
					this.addMessage(0, "", message);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETOBJECT) {
				int componentId = this.inBuffer.g2();
				int objId = this.inBuffer.g2();
				int zoom = this.inBuffer.g2();
				@Pc(3157) ObjType objType = ObjType.get(objId);
				Component.instances[componentId].modelDisabled = objType.getModel(50);
				Component.instances[componentId].modelEyePitch = objType.xan2d;
				Component.instances[componentId].modelYaw = objType.yan2d;
				Component.instances[componentId].modelZoom = objType.zoom2d * 100 / zoom;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_OPENTOP) {
				int componentId = this.inBuffer.g2();
				this.resetParentComponentSeq(componentId);
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.sidebarRedraw = true;
					this.sidebarRedrawIcons = true;
				}
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.chatbackInputType) {
					this.chatbackInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = componentId;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETCOLOUR) {
				int componentId = this.inBuffer.g2();
				int color = this.inBuffer.g2();
				int r = color >> 10 & 0x1F;
				int g = color >> 5 & 0x1F;
				int b = color & 0x1F;
				Component.instances[componentId].color = (r << 19) + (g << 11) + (b << 3);
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.RESET_ANIMS) {
				for (int i = 0; i < this.playerEntities.length; i++) {
					if (this.playerEntities[i] != null) {
						this.playerEntities[i].primarySeq = -1;
					}
				}
				for (int i = 0; i < this.npcEntities.length; i++) {
					if (this.npcEntities[i] != null) {
						this.npcEntities[i].primarySeq = -1;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETHIDE) {
				int componentId = this.inBuffer.g2();
				@Pc(3362) boolean hidden = this.inBuffer.g1() == 1;
				Component.instances[componentId].hidden = hidden;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_IGNORELIST) {
				this.ignoreCount = this.packetLength / 8;
				for (int i = 0; i < this.ignoreCount; i++) {
					this.ignoreName37[i] = this.inBuffer.g8();
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_RESET) {
				this.cameraOriented = false;
				for (int i = 0; i < 5; i++) {
					this.customCameraActive[i] = false;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_CLOSESUB) {
				if (this.sidebarInterfaceId != -1) {
					this.sidebarInterfaceId = -1;
					this.sidebarRedraw = true;
					this.sidebarRedrawIcons = true;
				}
				if (this.chatbackComponentId != -1) {
					this.chatbackComponentId = -1;
					this.redrawChatback = true;
				}
				if (this.chatbackInputType) {
					this.chatbackInputType = false;
					this.redrawChatback = true;
				}
				this.viewportInterfaceIndex = -1;
				this.chatContinuingDialogue = false;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.IF_SETTEXT) {
				int componentId = this.inBuffer.g2();
				Component.instances[componentId].text = this.inBuffer.gstr();
				if (Component.instances[componentId].parent == this.tabComponentId[this.selectedTab]) {
					this.sidebarRedraw = true;
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_STAT) {
				this.sidebarRedraw = true;
				int skill = this.inBuffer.g1();
				int exp = this.inBuffer.g4();
				int level = this.inBuffer.g1();
				this.skillExperience[skill] = exp;
				this.skillLevelReal[skill] = level;
				this.skillLevel[skill] = 1;
				for (int lvl = 0; lvl < 98; lvl++) {
					if (exp >= EXPERIENCE_TABLE[lvl]) {
						this.skillLevel[skill] = lvl + 2;
						break;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_ZONE_PARTIAL_ENCLOSED) {
				this.localPosX = this.inBuffer.g1();
				this.localPosZ = this.inBuffer.g1();
				while (this.inBuffer.pos < this.packetLength) {
					int opcode = this.inBuffer.g1();
					this.readZonePacket(this.inBuffer, opcode);
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_RUNWEIGHT) {
				if (this.selectedTab == 12) {
					this.sidebarRedraw = true;
				}
				this.weightCarried = this.inBuffer.g2b();
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.CAM_SHAKE) {
				int cameraId = this.inBuffer.g1();
				int jitter = this.inBuffer.g1();
				int amplitude = this.inBuffer.g1();
				int frequency = this.inBuffer.g1();
				this.customCameraActive[cameraId] = true;
				this.cameraJitter[cameraId] = jitter;
				this.cameraAmplitude[cameraId] = amplitude;
				this.cameraFrequency[cameraId] = frequency;
				this.unknownCameraVariable[cameraId] = 0;
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.UPDATE_INV_PARTIAL) {
				this.sidebarRedraw = true;
				int componentId = this.inBuffer.g2();
				Component component = Component.instances[componentId];
				while (this.inBuffer.pos < this.packetLength) {
					int slot = this.inBuffer.g1();
					int item = this.inBuffer.g2();
					int amount = this.inBuffer.g1();
					if (amount == 255) {
						amount = this.inBuffer.g4();
					}
					if (slot >= 0 && slot < component.inventoryIndices.length) {
						component.inventoryIndices[slot] = item;
						component.inventoryAmount[slot] = amount;
					}
				}
				this.packetOpcode = -1;
				return true;
			}
			if (this.packetOpcode == ServerProt.PLAYER_INFO) {
				this.updatePlayers(this.inBuffer, this.packetLength);
				if (this.sceneState == 1) {
					this.sceneState = 2;
					Scene.levelBuilt = this.currentPlane;
					this.createScene(false);
				}
				if (lowMemory && this.sceneState == 2 && Scene.levelBuilt != this.currentPlane) {
					this.areaViewport.makeTarget();
					this.plain12.drawCentered("Loading - please wait.", 257, 151, 0);
					this.plain12.drawCentered("Loading - please wait.", 256, 150, 0xffffff);
					this.areaViewport.drawAt(8, 11, 512, 334, super.graphic);
					Scene.levelBuilt = this.currentPlane;
					this.createScene(false);
				}
				if (this.currentPlane != this.lastSceneLevel && this.sceneState == 2) {
					this.lastSceneLevel = this.currentPlane;
					this.createMinimap(this.currentPlane);
				}
				this.packetOpcode = -1;
				return true;
			}
			signlink.reporterror("T1 - " + this.packetOpcode + "," + this.packetLength + " - " + this.secondMostRecentOpcode + "," + this.thirdMostRecentOpcode);
			this.disconnect();
		} catch (@Pc(3862) IOException ignored) {
			this.reconnect();
		} catch (@Pc(3867) Exception ex) {
			String err = "T2 - " + this.packetOpcode + "," + this.secondMostRecentOpcode + "," + this.thirdMostRecentOpcode + " - " + this.packetLength + "," + (this.baseTileX + this.self.pathTileX[0]) + "," + (this.baseTileZ + this.self.pathTileZ[0]) + " - ";
			for (int i = 0; i < this.packetLength && i < 50; i++) {
				err = err + this.inBuffer.data[i] + ",";
			}
			signlink.reporterror(err);
			ex.printStackTrace();
			this.disconnect();
		}
		return true;
	}

	@OriginalMember(owner = "client!client", name = "s", descriptor = "(B)V")
	private void drawInventory() {
		this.areaInvback.makeTarget();
		Draw3D.offsets = this.sidebarOffsets;
		this.invback.draw(0, 0);
		if (this.sidebarInterfaceId != -1) {
			this.drawInterface(0, 0, Component.instances[this.sidebarInterfaceId], 0);
		} else if (this.tabComponentId[this.selectedTab] != -1) {
			this.drawInterface(0, 0, Component.instances[this.tabComponentId[this.selectedTab]], 0);
		}
		if (this.menuVisible && this.mouseArea == 1) {
			this.drawMenu();
		}
		this.areaInvback.drawAt(562, 231, super.graphic);
		this.areaViewport.makeTarget();
		Draw3D.offsets = this.viewportOffsets;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ILjava/lang/String;)Z")
	private boolean isFriend(@OriginalArg(1) String str) {
		if (str == null) {
			return false;
		}
		for (@Pc(15) int i = 0; i < this.friendCount; i++) {
			if (str.equalsIgnoreCase(this.friendName[i])) {
				return true;
			}
		}
		return str.equalsIgnoreCase(this.self.name);
	}

	@OriginalMember(owner = "client!client", name = "init", descriptor = "()V")
	@Override
	public final void init() {
		nodeId = Integer.parseInt(this.getParameter("nodeid"));
		gamePortOffset = Integer.parseInt(this.getParameter("portoff"));
		@Pc(15) String isLowMem = this.getParameter("lowmem");
		if (isLowMem != null && isLowMem.equals("1")) {
			setLowMemory();
		} else {
			setHighMemory();
		}
		@Pc(31) String isFree = this.getParameter("free");
		if (isFree != null && isFree.equals("1")) {
			members = false;
		} else {
			members = true;
		}
		this.initApplet();
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZIILclient!kb;Lclient!z;)V")
	private void updatePlayerMask(@OriginalArg(1) int pid, @OriginalArg(2) int mask, @OriginalArg(3) Buffer b, @OriginalArg(4) PlayerEntity player) {
		@Pc(19) int local19;
		if ((mask & 0x1) == 1) {
			local19 = b.g1();
			@Pc(22) byte[] local22 = new byte[local19];
			@Pc(28) Buffer local28 = new Buffer(local22);
			b.gdata(local22, 0, local19);
			this.playerBuffers[pid] = local28;
			player.decode(local28);
		}
		@Pc(66) int local66;
		if ((mask & 0x2) == 2) {
			local19 = b.g2();
			if (local19 == 0xffff) {
				local19 = -1;
			}
			if (local19 == player.primarySeq) {
				player.primarySeqPlays = 0;
			}
			local66 = b.g1();
			if (local19 == -1 || player.primarySeq == -1 || SeqType.instances[local19].priority > SeqType.instances[player.primarySeq].priority || SeqType.instances[player.primarySeq].priority == 0) {
				player.primarySeq = local19;
				player.primarySeqFrame = 0;
				player.primarySeqCycle = 0;
				player.primarySeqDelay = local66;
				player.primarySeqPlays = 0;
			}
		}
		if ((mask & 0x4) == 4) {
			player.targetEntity = b.g2();
			if (player.targetEntity == 0xffff) {
				player.targetEntity = -1;
			}
		}
		if ((mask & 0x8) == 8) {
			player.spoken = b.gstr();
			player.spokenColor = 0;
			player.spokenEffect = 0;
			player.textCycle = 150;
			this.addMessage(2, player.name, player.spoken);
		}
		if ((mask & 0x10) == 16) {
			player.damageTaken = b.g1();
			player.damageType = b.g1();
			player.lastCombatCycle = clientClock + 400;
			player.currentHealth = b.g1();
			player.maxHealth = b.g1();
		}
		if ((mask & 0x20) == 32) {
			player.focusX = b.g2();
			player.focusZ = b.g2();
		}
		if ((mask & 0x40) == 64) {
			local19 = b.g2();
			local66 = b.g1();
			@Pc(199) int local199 = b.g1();
			@Pc(202) int local202 = b.pos;
			if (player.name != null) {
				@Pc(209) long local209 = StringUtils.toBase37(player.name);
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
						@Pc(244) String local244 = TextEncoder.read(b, local199);
						@Pc(248) String local248 = WordPack.getFiltered(local244);
						player.spoken = local248;
						player.spokenColor = local19 >> 8;
						player.spokenEffect = local19 & 0xFF;
						player.textCycle = 150;
						if (local66 > 1) {
							this.addMessage(1, player.name, local248);
						} else {
							this.addMessage(2, player.name, local248);
						}
					} catch (@Pc(285) Exception local285) {
						signlink.reporterror("cde2");
					}
				}
			}
			b.pos = local202 + local199;
		}
		if ((mask & 0x100) == 256) {
			player.spotAnimIndex = b.g2();
			local19 = b.g4();
			player.spotAnimOffsetY = local19 >> 16;
			player.lastSpotAnimCycle = clientClock + (local19 & 0xFFFF);
			player.spotAnimFrame = 0;
			player.spotAnimCycle = 0;
			if (player.lastSpotAnimCycle > clientClock) {
				player.spotAnimFrame = -1;
			}
			if (player.spotAnimIndex == 0xffff) {
				player.spotAnimIndex = -1;
			}
		}
		if ((mask & 0x200) != 512) {
			return;
		}
		player.srcTileX = b.g1();
		player.srcTileZ = b.g1();
		player.dstTileX = b.g1();
		player.dstTileZ = b.g1();
		player.firstMoveCycle = b.g2() + clientClock;
		player.lastMoveCycle = b.g2() + clientClock;
		player.faceDirection = b.g1();
		player.pathRemaining = 0;
		player.pathTileX[0] = player.dstTileX;
		player.pathTileZ[0] = player.dstTileZ;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZLjava/lang/String;I)V")
	@Override
	protected final void showProgress(@OriginalArg(1) String str, @OriginalArg(2) int progress) {
		this.prepareTitleScreen();
		if (this.title == null) {
			super.showProgress(str, progress);
			return;
		}
		this.titleCenter.makeTarget();
		this.bold12.drawCentered("RuneScape is loading - please wait...", 180, 54, 0xffffff);
		Draw2D.drawRect(28, 62, 304, 34, 9179409);
		Draw2D.drawRect(29, 63, 302, 32, 0);
		Draw2D.fillRect(30, 64, progress * 3, 30, 9179409);
		Draw2D.fillRect(progress * 3 + 30, 64, 300 - progress * 3, 30, 0);
		this.bold12.drawCentered(str, 180, 85, 0xffffff);
		this.titleCenter.drawAt(214, 186, super.graphic);
		if (!this.redrawTitleBackground) {
			return;
		}
		this.redrawTitleBackground = false;
		if (!this.flameActive) {
			this.titleLeft.drawAt(0, 0, super.graphic);
			this.titleRight.drawAt(661, 0, super.graphic);
		}
		this.titleTop.drawAt(128, 0, super.graphic);
		this.titleBottom.drawAt(214, 386, super.graphic);
		this.titleBottomLeft.drawAt(0, 265, super.graphic);
		this.titleBottomRight.drawAt(574, 265, super.graphic);
		this.titleLeftSpace.drawAt(128, 186, super.graphic);
		this.titleRightSpace.drawAt(574, 186, super.graphic);
	}

	@OriginalMember(owner = "client!client", name = "ud", descriptor = "Z")
	private static final boolean flowObfuscator38 = false;

	@OriginalMember(owner = "client!client", name = "vd", descriptor = "B")
	private static final byte flowObfuscator6 = (byte) 0;

	@OriginalMember(owner = "client!client", name = "Xd", descriptor = "I")
	private static final int flowObfuscator39 = 0;

	@OriginalMember(owner = "client!client", name = "ef", descriptor = "I")
	private static final int flowObfuscator5 = 0;

	@OriginalMember(owner = "client!client", name = "hi", descriptor = "B")
	private static final byte flowObfuscator4 = (byte) 0;

	@OriginalMember(owner = "client!client", name = "H", descriptor = "I")
	private int flowObfuscator35;

	@OriginalMember(owner = "client!client", name = "Yb", descriptor = "I")
	private int flowObfuscator40;

	@OriginalMember(owner = "client!client", name = "rg", descriptor = "I")
	private int flowObfuscator42;

	@OriginalMember(owner = "client!client", name = "uh", descriptor = "I")
	private int flowObfuscator41;

	@OriginalMember(owner = "client!client", name = "N", descriptor = "I")
	private final int flowObfuscator3 = 24676;

	@OriginalMember(owner = "client!client", name = "kb", descriptor = "I")
	private int flowObfuscator10 = 9;

	@OriginalMember(owner = "client!client", name = "nb", descriptor = "I")
	private int flowObfuscator1 = 997;

	@OriginalMember(owner = "client!client", name = "ub", descriptor = "B")
	private final byte flowObfuscator2 = 106;

	@OriginalMember(owner = "client!client", name = "zb", descriptor = "I")
	private int flowObfuscator17 = 723;

	@OriginalMember(owner = "client!client", name = "Gb", descriptor = "I")
	private final int flowObfuscator43 = 332;

	@OriginalMember(owner = "client!client", name = "Pb", descriptor = "Z")
	private final boolean flowObfuscator44 = true;

	@OriginalMember(owner = "client!client", name = "Sb", descriptor = "B")
	private final byte flowObfuscator22 = 8;

	@OriginalMember(owner = "client!client", name = "fc", descriptor = "I")
	private final int flowObfuscator21 = 3;

	@OriginalMember(owner = "client!client", name = "hc", descriptor = "I")
	private int flowObfuscator20 = -655;

	@OriginalMember(owner = "client!client", name = "pc", descriptor = "I")
	private final int flowObfuscator27 = 4277;

	@OriginalMember(owner = "client!client", name = "Tc", descriptor = "B")
	private final byte flowObfuscator7 = 4;

	@OriginalMember(owner = "client!client", name = "Zc", descriptor = "Z")
	private final boolean flowObfuscator29 = false;

	@OriginalMember(owner = "client!client", name = "bd", descriptor = "B")
	private final byte flowObfuscator19 = 2;

	@OriginalMember(owner = "client!client", name = "kd", descriptor = "Z")
	private final boolean flowObfuscator30 = false;

	@OriginalMember(owner = "client!client", name = "qd", descriptor = "B")
	private final byte flowObfuscator15 = 99;

	@OriginalMember(owner = "client!client", name = "Bd", descriptor = "I")
	private final int flowObfuscator31 = 100;

	@OriginalMember(owner = "client!client", name = "Hd", descriptor = "I")
	private int flowObfuscator32 = -655;

	@OriginalMember(owner = "client!client", name = "ke", descriptor = "Z")
	private final boolean flowObfuscator33 = false;

	@OriginalMember(owner = "client!client", name = "He", descriptor = "B")
	private final byte flowObfuscator34 = -46;

	@OriginalMember(owner = "client!client", name = "Lf", descriptor = "Z")
	private final boolean flowObfuscator46 = false;

	@OriginalMember(owner = "client!client", name = "Ig", descriptor = "I")
	private int flowObfuscator23 = 27808;

	@OriginalMember(owner = "client!client", name = "lh", descriptor = "B")
	private final byte flowObufscator24 = 7;

	@OriginalMember(owner = "client!client", name = "oh", descriptor = "I")
	private final int flowObfuscator25 = 242;

	@OriginalMember(owner = "client!client", name = "Wh", descriptor = "I")
	private final int flowObfuscator12 = -986;

	@OriginalMember(owner = "client!client", name = "Xh", descriptor = "I")
	private final int flowObfuscator36 = -35388;

	@OriginalMember(owner = "client!client", name = "ni", descriptor = "B")
	private final byte flowObfuscator16 = 94;

	@OriginalMember(owner = "client!client", name = "xi", descriptor = "I")
	private final int flowObfuscator18 = 29286;

	@OriginalMember(owner = "client!client", name = "Ii", descriptor = "I")
	private final int flowObfuscator14 = 701;

	@OriginalMember(owner = "client!client", name = "Mi", descriptor = "Z")
	private boolean flowObfuscator8 = true;

	@OriginalMember(owner = "client!client", name = "Zi", descriptor = "I")
	private final int flowObfuscator37 = -676;

}
