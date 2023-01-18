import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!sb")
public final class BZip2State {

	@OriginalMember(owner = "client!sb", name = "H", descriptor = "[I")
	public static int[] tt;

	@OriginalMember(owner = "client!sb", name = "j", descriptor = "[B")
	public byte[] stream;

	@OriginalMember(owner = "client!sb", name = "k", descriptor = "I")
	public int next_in;

	@OriginalMember(owner = "client!sb", name = "l", descriptor = "I")
	public int avail_in;

	@OriginalMember(owner = "client!sb", name = "m", descriptor = "I")
	public int total_in_lo32;

	@OriginalMember(owner = "client!sb", name = "n", descriptor = "I")
	public int total_in_hi32;

	@OriginalMember(owner = "client!sb", name = "o", descriptor = "[B")
	public byte[] decompressed;

	@OriginalMember(owner = "client!sb", name = "p", descriptor = "I")
	public int next_out;

	@OriginalMember(owner = "client!sb", name = "q", descriptor = "I")
	public int avail_out;

	@OriginalMember(owner = "client!sb", name = "r", descriptor = "I")
	public int total_out_lo32;

	@OriginalMember(owner = "client!sb", name = "s", descriptor = "I")
	public int total_out_hi32;

	@OriginalMember(owner = "client!sb", name = "t", descriptor = "B")
	public byte state_out_ch;

	@OriginalMember(owner = "client!sb", name = "u", descriptor = "I")
	public int state_out_len;

	@OriginalMember(owner = "client!sb", name = "v", descriptor = "Z")
	public boolean blockRandomized;

	@OriginalMember(owner = "client!sb", name = "w", descriptor = "I")
	public int bsBuff;

	@OriginalMember(owner = "client!sb", name = "x", descriptor = "I")
	public int bsLive;

	@OriginalMember(owner = "client!sb", name = "y", descriptor = "I")
	public int blockSize100k;

	@OriginalMember(owner = "client!sb", name = "z", descriptor = "I")
	public int currBlockNo;

	@OriginalMember(owner = "client!sb", name = "A", descriptor = "I")
	public int origPtr;

	@OriginalMember(owner = "client!sb", name = "B", descriptor = "I")
	public int tPos;

	@OriginalMember(owner = "client!sb", name = "C", descriptor = "I")
	public int k0;

	@OriginalMember(owner = "client!sb", name = "E", descriptor = "I")
	public int c_nblock_used;

	@OriginalMember(owner = "client!sb", name = "I", descriptor = "I")
	public int nInUse;

	@OriginalMember(owner = "client!sb", name = "V", descriptor = "I")
	public int save_nblock;

	@OriginalMember(owner = "client!sb", name = "a", descriptor = "I")
	public static final int MTFA_SIZE = 4096;

	@OriginalMember(owner = "client!sb", name = "b", descriptor = "I")
	public static final int MTFL_SIZE = 16;

	@OriginalMember(owner = "client!sb", name = "c", descriptor = "I")
	public static final int BZ_MAX_ALPHA_SIZE = 258;

	@OriginalMember(owner = "client!sb", name = "d", descriptor = "I")
	public static final int BZ_MAX_CODE_LEN = 23;

	@OriginalMember(owner = "client!sb", name = "e", descriptor = "I")
	public static final int anInt732 = 1; // TODO

	@OriginalMember(owner = "client!sb", name = "f", descriptor = "I")
	public static final int BZ_N_GROUPS = 6;

	@OriginalMember(owner = "client!sb", name = "g", descriptor = "I")
	public static final int BZ_G_SIZE = 50;

	@OriginalMember(owner = "client!sb", name = "h", descriptor = "I")
	public static final int anInt735 = 4; // TODO

	@OriginalMember(owner = "client!sb", name = "i", descriptor = "I")
	public static final int BZ_MAX_SELECTORS = (2 + (900000 / BZ_G_SIZE));

	public static final int BZ_RUNA = 0;
	public static final int BZ_RUNB = 1;

	@OriginalMember(owner = "client!sb", name = "D", descriptor = "[I")
	public final int[] unzftab = new int[256];

	@OriginalMember(owner = "client!sb", name = "F", descriptor = "[I")
	public final int[] cftab = new int[257];

	@OriginalMember(owner = "client!sb", name = "G", descriptor = "[I")
	private final int[] cftabCopy = new int[257];

	@OriginalMember(owner = "client!sb", name = "J", descriptor = "[Z")
	public final boolean[] inUse = new boolean[256];

	@OriginalMember(owner = "client!sb", name = "K", descriptor = "[Z")
	public final boolean[] inUse16 = new boolean[16];

	@OriginalMember(owner = "client!sb", name = "L", descriptor = "[B")
	public final byte[] seqToUnseq = new byte[256];

	@OriginalMember(owner = "client!sb", name = "M", descriptor = "[B")
	public final byte[] mtfa = new byte[MTFA_SIZE];

	@OriginalMember(owner = "client!sb", name = "N", descriptor = "[I")
	public final int[] mtfbase = new int[256 / MTFL_SIZE];

	@OriginalMember(owner = "client!sb", name = "O", descriptor = "[B")
	public final byte[] selector = new byte[BZ_MAX_SELECTORS];

	@OriginalMember(owner = "client!sb", name = "P", descriptor = "[B")
	public final byte[] selectorMtf = new byte[BZ_MAX_SELECTORS];

	@OriginalMember(owner = "client!sb", name = "Q", descriptor = "[[B")
	public final byte[][] len = new byte[BZ_N_GROUPS][BZ_MAX_ALPHA_SIZE];

	@OriginalMember(owner = "client!sb", name = "R", descriptor = "[[I")
	public final int[][] limit = new int[BZ_N_GROUPS][BZ_MAX_ALPHA_SIZE];

	@OriginalMember(owner = "client!sb", name = "S", descriptor = "[[I")
	public final int[][] base = new int[BZ_N_GROUPS][BZ_MAX_ALPHA_SIZE];

	@OriginalMember(owner = "client!sb", name = "T", descriptor = "[[I")
	public final int[][] perm = new int[BZ_N_GROUPS][BZ_MAX_ALPHA_SIZE];

	@OriginalMember(owner = "client!sb", name = "U", descriptor = "[I")
	public final int[] minLens = new int[BZ_N_GROUPS];
}
