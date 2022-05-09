import java.math.BigInteger;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static6 {

	@OriginalMember(owner = "client!client", name = "E", descriptor = "I")
	public static int anInt143;

	@OriginalMember(owner = "client!client", name = "Ab", descriptor = "I")
	public static int anInt175;

	@OriginalMember(owner = "client!client", name = "Zb", descriptor = "I")
	public static int anInt188;

	@OriginalMember(owner = "client!client", name = "gc", descriptor = "I")
	public static int anInt193;

	@OriginalMember(owner = "client!client", name = "ic", descriptor = "I")
	public static int anInt195;

	@OriginalMember(owner = "client!client", name = "hd", descriptor = "I")
	public static int anInt220;

	@OriginalMember(owner = "client!client", name = "rd", descriptor = "I")
	public static int anInt224;

	@OriginalMember(owner = "client!client", name = "Ad", descriptor = "I")
	public static int anInt229;

	@OriginalMember(owner = "client!client", name = "Gd", descriptor = "I")
	public static int anInt232;

	@OriginalMember(owner = "client!client", name = "Ld", descriptor = "I")
	public static int anInt236;

	@OriginalMember(owner = "client!client", name = "Nd", descriptor = "Z")
	public static boolean aBoolean53;

	@OriginalMember(owner = "client!client", name = "Od", descriptor = "I")
	public static int anInt237;

	@OriginalMember(owner = "client!client", name = "le", descriptor = "I")
	public static int anInt242;

	@OriginalMember(owner = "client!client", name = "Je", descriptor = "I")
	public static int anInt254;

	@OriginalMember(owner = "client!client", name = "bf", descriptor = "I")
	public static int anInt266;

	@OriginalMember(owner = "client!client", name = "Tf", descriptor = "I")
	public static int anInt279;

	@OriginalMember(owner = "client!client", name = "Hh", descriptor = "I")
	public static int anInt313;

	@OriginalMember(owner = "client!client", name = "Th", descriptor = "Z")
	public static boolean aBoolean69;

	@OriginalMember(owner = "client!client", name = "Zh", descriptor = "I")
	public static int anInt319;

	@OriginalMember(owner = "client!client", name = "F", descriptor = "Ljava/lang/String;")
	public static final String aString4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";

	@OriginalMember(owner = "client!client", name = "ud", descriptor = "Z")
	private static final boolean aBoolean48 = true;

	@OriginalMember(owner = "client!client", name = "vd", descriptor = "B")
	public static final byte aByte13 = 15;

	@OriginalMember(owner = "client!client", name = "Kd", descriptor = "I")
	public static int anInt235 = 10;

	@OriginalMember(owner = "client!client", name = "Md", descriptor = "Z")
	public static boolean aBoolean52 = true;

	@OriginalMember(owner = "client!client", name = "Xd", descriptor = "I")
	public static final int anInt238 = 629;

	@OriginalMember(owner = "client!client", name = "ue", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger aBigInteger1 = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

	@OriginalMember(owner = "client!client", name = "Oe", descriptor = "[[I")
	public static final int[][] anIntArrayArray4 = new int[][] { { 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983, 54193 }, { 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003, 25239 }, { 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 56621, 4783, 1341, 16578, 35003 }, { 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };

	@OriginalMember(owner = "client!client", name = "ef", descriptor = "I")
	public static final int anInt268 = 3;

	@OriginalMember(owner = "client!client", name = "fh", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger aBigInteger2 = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

	@OriginalMember(owner = "client!client", name = "qh", descriptor = "[I")
	public static final int[] anIntArray70 = new int[] { 9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 58654, 5027, 1457, 16565, 34991, 25486 };

	@OriginalMember(owner = "client!client", name = "hi", descriptor = "B")
	public static final byte aByte16 = 3;

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(Z)V")
	public static void method88() {
		Static25.aBoolean143 = true;
		Static15.aBoolean96 = true;
		aBoolean53 = true;
		Static4.aBoolean23 = true;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(II)Ljava/lang/String;")
	public static String method101(@OriginalArg(0) int arg0) {
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
	public static String method133(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(9) int local9 = arg0 - arg1;
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
	public static void method167() {
		Static25.aBoolean143 = false;
		Static15.aBoolean96 = false;
		aBoolean53 = false;
		Static4.aBoolean23 = false;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZI)Ljava/lang/String;")
	public static String method169(@OriginalArg(1) int arg0) {
		if (arg0 < 100000) {
			return String.valueOf(arg0);
		} else if (arg0 < 10000000) {
			return arg0 / 1000 + "K";
		} else {
			return arg0 / 1000000 + "M";
		}
	}
}
