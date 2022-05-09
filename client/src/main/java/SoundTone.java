import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!zb")
public final class SoundTone {

	@OriginalMember(owner = "client!zb", name = "r", descriptor = "[I")
	private static int[] anIntArray239;

	@OriginalMember(owner = "client!zb", name = "s", descriptor = "[I")
	private static int[] anIntArray240;

	@OriginalMember(owner = "client!zb", name = "t", descriptor = "[I")
	private static int[] anIntArray241;

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "I")
	private static final int anInt943 = 8;

	@OriginalMember(owner = "client!zb", name = "u", descriptor = "[I")
	private static final int[] anIntArray242 = new int[5];

	@OriginalMember(owner = "client!zb", name = "v", descriptor = "[I")
	private static final int[] anIntArray243 = new int[5];

	@OriginalMember(owner = "client!zb", name = "w", descriptor = "[I")
	private static final int[] anIntArray244 = new int[5];

	@OriginalMember(owner = "client!zb", name = "x", descriptor = "[I")
	private static final int[] anIntArray245 = new int[5];

	@OriginalMember(owner = "client!zb", name = "y", descriptor = "[I")
	private static final int[] anIntArray246 = new int[5];

	@OriginalMember(owner = "client!zb", name = "c", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_1;

	@OriginalMember(owner = "client!zb", name = "d", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_2;

	@OriginalMember(owner = "client!zb", name = "e", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_3;

	@OriginalMember(owner = "client!zb", name = "f", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_4;

	@OriginalMember(owner = "client!zb", name = "g", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_5;

	@OriginalMember(owner = "client!zb", name = "h", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_6;

	@OriginalMember(owner = "client!zb", name = "i", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_7;

	@OriginalMember(owner = "client!zb", name = "j", descriptor = "Lclient!xb;")
	private SoundEnvelope aSoundEnvelope_8;

	@OriginalMember(owner = "client!zb", name = "n", descriptor = "I")
	private int anInt945;

	@OriginalMember(owner = "client!zb", name = "q", descriptor = "I")
	public int anInt948;

	@OriginalMember(owner = "client!zb", name = "b", descriptor = "I")
	private final int anInt944 = -15143;

	@OriginalMember(owner = "client!zb", name = "k", descriptor = "[I")
	private final int[] anIntArray236 = new int[5];

	@OriginalMember(owner = "client!zb", name = "l", descriptor = "[I")
	private final int[] anIntArray237 = new int[5];

	@OriginalMember(owner = "client!zb", name = "m", descriptor = "[I")
	private final int[] anIntArray238 = new int[5];

	@OriginalMember(owner = "client!zb", name = "o", descriptor = "I")
	private int anInt946 = 100;

	@OriginalMember(owner = "client!zb", name = "p", descriptor = "I")
	public int anInt947 = 500;

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "()V")
	public static void init() {
		anIntArray240 = new int[32768];
		for (@Pc(6) int local6 = 0; local6 < 32768; local6++) {
			if (Math.random() > 0.5D) {
				anIntArray240[local6] = 1;
			} else {
				anIntArray240[local6] = -1;
			}
		}
		anIntArray241 = new int[32768];
		for (@Pc(31) int local31 = 0; local31 < 32768; local31++) {
			anIntArray241[local31] = (int) (Math.sin((double) local31 / 5215.1903D) * 16384.0D);
		}
		anIntArray239 = new int[220500];
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(II)[I")
	public final int[] generate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		for (@Pc(3) int local3 = 0; local3 < arg0; local3++) {
			anIntArray239[local3] = 0;
		}
		if (arg1 < 10) {
			return anIntArray239;
		}
		@Pc(26) double local26 = (double) arg0 / ((double) arg1 + 0.0D);
		this.aSoundEnvelope_1.reset(anInt943);
		this.aSoundEnvelope_2.reset(anInt943);
		@Pc(36) int local36 = 0;
		@Pc(38) int local38 = 0;
		@Pc(40) int local40 = 0;
		if (this.aSoundEnvelope_3 != null) {
			this.aSoundEnvelope_3.reset(anInt943);
			this.aSoundEnvelope_4.reset(anInt943);
			local36 = (int) ((double) (this.aSoundEnvelope_3.anInt820 - this.aSoundEnvelope_3.anInt819) * 32.768D / local26);
			local38 = (int) ((double) this.aSoundEnvelope_3.anInt819 * 32.768D / local26);
		}
		@Pc(77) int local77 = 0;
		@Pc(79) int local79 = 0;
		@Pc(81) int local81 = 0;
		if (this.aSoundEnvelope_5 != null) {
			this.aSoundEnvelope_5.reset(anInt943);
			this.aSoundEnvelope_6.reset(anInt943);
			local77 = (int) ((double) (this.aSoundEnvelope_5.anInt820 - this.aSoundEnvelope_5.anInt819) * 32.768D / local26);
			local79 = (int) ((double) this.aSoundEnvelope_5.anInt819 * 32.768D / local26);
		}
		for (@Pc(118) int local118 = 0; local118 < 5; local118++) {
			if (this.anIntArray236[local118] != 0) {
				anIntArray242[local118] = 0;
				anIntArray243[local118] = (int) ((double) this.anIntArray238[local118] * local26);
				anIntArray244[local118] = (this.anIntArray236[local118] << 14) / 100;
				anIntArray245[local118] = (int) ((double) (this.aSoundEnvelope_1.anInt820 - this.aSoundEnvelope_1.anInt819) * 32.768D * Math.pow(1.0057929410678534D, (double) this.anIntArray237[local118]) / local26);
				anIntArray246[local118] = (int) ((double) this.aSoundEnvelope_1.anInt819 * 32.768D / local26);
			}
		}
		@Pc(201) int local201;
		@Pc(207) int local207;
		@Pc(222) int local222;
		for (@Pc(193) int local193 = 0; local193 < arg0; local193++) {
			local201 = this.aSoundEnvelope_1.evaluate(arg0);
			local207 = this.aSoundEnvelope_2.evaluate(arg0);
			@Pc(216) int local216;
			if (this.aSoundEnvelope_3 != null) {
				local216 = this.aSoundEnvelope_3.evaluate(arg0);
				local222 = this.aSoundEnvelope_4.evaluate(arg0);
				local201 += this.generate(local222, local40, this.aSoundEnvelope_3.anInt821) >> 1;
				local40 += (local216 * local36 >> 16) + local38;
			}
			if (this.aSoundEnvelope_5 != null) {
				local216 = this.aSoundEnvelope_5.evaluate(arg0);
				local222 = this.aSoundEnvelope_6.evaluate(arg0);
				local207 = local207 * ((this.generate(local222, local81, this.aSoundEnvelope_5.anInt821) >> 1) + 32768) >> 15;
				local81 += (local216 * local77 >> 16) + local79;
			}
			for (local216 = 0; local216 < 5; local216++) {
				if (this.anIntArray236[local216] != 0) {
					local222 = local193 + anIntArray243[local216];
					if (local222 < arg0) {
						anIntArray239[local222] += this.generate(local207 * anIntArray244[local216] >> 15, anIntArray242[local216], this.aSoundEnvelope_1.anInt821);
						anIntArray242[local216] += (local201 * anIntArray245[local216] >> 16) + anIntArray246[local216];
					}
				}
			}
		}
		if (this.aSoundEnvelope_7 != null) {
			this.aSoundEnvelope_7.reset(anInt943);
			this.aSoundEnvelope_8.reset(anInt943);
			local201 = 0;
			@Pc(369) boolean local369 = true;
			for (local222 = 0; local222 < arg0; local222++) {
				@Pc(379) int local379 = this.aSoundEnvelope_7.evaluate(arg0);
				@Pc(385) int local385 = this.aSoundEnvelope_8.evaluate(arg0);
				if (local369) {
					local207 = this.aSoundEnvelope_7.anInt819 + ((this.aSoundEnvelope_7.anInt820 - this.aSoundEnvelope_7.anInt819) * local379 >> 8);
				} else {
					local207 = this.aSoundEnvelope_7.anInt819 + ((this.aSoundEnvelope_7.anInt820 - this.aSoundEnvelope_7.anInt819) * local385 >> 8);
				}
				local201 += 256;
				if (local201 >= local207) {
					local201 = 0;
					local369 = !local369;
				}
				if (local369) {
					anIntArray239[local222] = 0;
				}
			}
		}
		if (this.anInt945 > 0 && this.anInt946 > 0) {
			local201 = (int) ((double) this.anInt945 * local26);
			for (local207 = local201; local207 < arg0; local207++) {
				anIntArray239[local207] += anIntArray239[local207 - local201] * this.anInt946 / 100;
			}
		}
		for (local201 = 0; local201 < arg0; local201++) {
			if (anIntArray239[local201] < -32768) {
				anIntArray239[local201] = -32768;
			}
			if (anIntArray239[local201] > 32767) {
				anIntArray239[local201] = 32767;
			}
		}
		return anIntArray239;
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(IIII)I")
	private int generate(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (this.anInt944 != -15143) {
			return 2;
		} else if (arg2 == 1) {
			return (arg1 & 0x7FFF) < 16384 ? arg0 : -arg0;
		} else if (arg2 == 2) {
			return anIntArray241[arg1 & 0x7FFF] * arg0 >> 14;
		} else if (arg2 == 3) {
			return ((arg1 & 0x7FFF) * arg0 >> 14) - arg0;
		} else if (arg2 == 4) {
			return anIntArray240[arg1 / 2607 & 0x7FFF] * arg0;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!zb", name = "a", descriptor = "(ZLclient!kb;)V")
	public final void read(@OriginalArg(1) Buffer arg0) {
		this.aSoundEnvelope_1 = new SoundEnvelope();
		this.aSoundEnvelope_1.readShape(arg0);
		this.aSoundEnvelope_2 = new SoundEnvelope();
		this.aSoundEnvelope_2.readShape(arg0);
		@Pc(24) int local24 = arg0.g1();
		if (local24 != 0) {
			arg0.anInt561--;
			this.aSoundEnvelope_3 = new SoundEnvelope();
			this.aSoundEnvelope_3.readShape(arg0);
			this.aSoundEnvelope_4 = new SoundEnvelope();
			this.aSoundEnvelope_4.readShape(arg0);
		}
		local24 = arg0.g1();
		if (local24 != 0) {
			arg0.anInt561--;
			this.aSoundEnvelope_5 = new SoundEnvelope();
			this.aSoundEnvelope_5.readShape(arg0);
			this.aSoundEnvelope_6 = new SoundEnvelope();
			this.aSoundEnvelope_6.readShape(arg0);
		}
		local24 = arg0.g1();
		if (local24 != 0) {
			arg0.anInt561--;
			this.aSoundEnvelope_7 = new SoundEnvelope();
			this.aSoundEnvelope_7.readShape(arg0);
			this.aSoundEnvelope_8 = new SoundEnvelope();
			this.aSoundEnvelope_8.readShape(arg0);
		}
		for (@Pc(122) int local122 = 0; local122 < 10; local122++) {
			@Pc(132) int local132 = arg0.gSmart1or2();
			if (local132 == 0) {
				break;
			}
			this.anIntArray236[local122] = local132;
			this.anIntArray237[local122] = arg0.gSmart1or2s();
			this.anIntArray238[local122] = arg0.gSmart1or2();
		}
		this.anInt945 = arg0.gSmart1or2();
		this.anInt946 = arg0.gSmart1or2();
		this.anInt947 = arg0.g2();
		this.anInt948 = arg0.g2();
	}
}
