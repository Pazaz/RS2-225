import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class InputTracking {

	@OriginalMember(owner = "client!e", name = "c", descriptor = "Z")
	private static boolean aBoolean79;

	@OriginalMember(owner = "client!e", name = "d", descriptor = "Z")
	private static boolean aBoolean80;

	@OriginalMember(owner = "client!e", name = "e", descriptor = "Z")
	public static boolean aBoolean81;

	@OriginalMember(owner = "client!e", name = "h", descriptor = "J")
	private static long aLong11;

	@OriginalMember(owner = "client!e", name = "i", descriptor = "I")
	private static int anInt352;

	@OriginalMember(owner = "client!e", name = "j", descriptor = "J")
	private static long aLong12;

	@OriginalMember(owner = "client!e", name = "k", descriptor = "I")
	private static int anInt353;

	@OriginalMember(owner = "client!e", name = "l", descriptor = "I")
	private static int anInt354;

	@OriginalMember(owner = "client!e", name = "a", descriptor = "B")
	private static final byte aByte18 = 65;

	@OriginalMember(owner = "client!e", name = "b", descriptor = "I")
	private static final int anInt351 = 78;

	@OriginalMember(owner = "client!e", name = "f", descriptor = "Lclient!kb;")
	private static Buffer aBuffer_7 = null;

	@OriginalMember(owner = "client!e", name = "g", descriptor = "Lclient!kb;")
	private static Buffer aBuffer_8 = null;

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(I)V")
	public static synchronized void setEnabled() {
		aBuffer_7 = Buffer.reserve();
		aBuffer_8 = null;
		aLong11 = System.currentTimeMillis();
		aBoolean81 = true;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(B)V")
	public static synchronized void setDisabled() {
		aBoolean81 = false;
		aBuffer_7 = null;
		if (aByte18 == 65) {
			aBuffer_8 = null;
		}
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(I)Lclient!kb;")
	public static synchronized Buffer flush() {
		@Pc(1) Buffer local1 = null;
		if (aBuffer_8 != null && aBoolean81) {
			local1 = aBuffer_8;
		}
		aBuffer_8 = null;
		return local1;
	}

	@OriginalMember(owner = "client!e", name = "c", descriptor = "(I)Lclient!kb;")
	public static synchronized Buffer stop() {
		@Pc(9) Buffer local9 = null;
		if (aBuffer_7 != null && aBuffer_7.anInt561 > 0 && aBoolean81) {
			local9 = aBuffer_7;
		}
		setDisabled();
		return local9;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(II)V")
	private static synchronized void resize(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (arg0 <= 0) {
			aBoolean79 = !aBoolean79;
		}
		if (aBuffer_7.anInt561 + arg1 >= 500) {
			@Pc(15) Buffer local15 = aBuffer_7;
			aBuffer_7 = Buffer.reserve();
			aBuffer_8 = local15;
		}
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIB)V")
	public static synchronized void mousePressed(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (!aBoolean81 || (arg0 < 0 || arg0 >= 789 || arg2 < 0 || arg2 >= 532)) {
			return;
		}
		anInt352++;
		@Pc(19) long local19 = System.currentTimeMillis();
		@Pc(25) long local25 = (local19 - aLong11) / 10L;
		if (local25 > 250L) {
			local25 = 250L;
		}
		aLong11 = local19;
		resize(anInt351, 5);
		if (arg1 == 1) {
			aBuffer_7.p1(1);
		} else {
			aBuffer_7.p1(2);
		}
		aBuffer_7.p1((int) local25);
		aBuffer_7.p3(arg0 + (arg2 << 10));
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(II)V")
	public static synchronized void mouseReleased(@OriginalArg(0) int arg0) {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(8) long local8 = System.currentTimeMillis();
		@Pc(14) long local14 = (local8 - aLong11) / 10L;
		if (local14 > 250L) {
			local14 = 250L;
		}
		aLong11 = local8;
		resize(anInt351, 2);
		if (arg0 == 1) {
			aBuffer_7.p1(3);
		} else {
			aBuffer_7.p1(4);
		}
		aBuffer_7.p1((int) local14);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IZI)V")
	public static synchronized void mouseMoved(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		if (!aBoolean81 || (arg1 < 0 || arg1 >= 789 || arg0 < 0 || arg0 >= 532)) {
			return;
		}
		@Pc(17) long local17 = System.currentTimeMillis();
		if (local17 - aLong12 < 50L) {
			return;
		}
		aLong12 = local17;
		anInt352++;
		@Pc(39) long local39 = (local17 - aLong11) / 10L;
		if (local39 > 250L) {
			local39 = 250L;
		}
		aLong11 = local17;
		if (arg1 - anInt353 < 8 && arg1 - anInt353 >= -8 && arg0 - anInt354 < 8 && arg0 - anInt354 >= -8) {
			resize(anInt351, 3);
			aBuffer_7.p1(5);
			aBuffer_7.p1((int) local39);
			aBuffer_7.p1(arg1 + (arg0 - anInt354 + 8 << 4) + 8 - anInt353);
		} else if (arg1 - anInt353 < 128 && arg1 - anInt353 >= -128 && arg0 - anInt354 < 128 && arg0 - anInt354 >= -128) {
			resize(anInt351, 4);
			aBuffer_7.p1(6);
			aBuffer_7.p1((int) local39);
			aBuffer_7.p1(arg1 + 128 - anInt353);
			aBuffer_7.p1(arg0 + 128 - anInt354);
		} else {
			resize(anInt351, 5);
			aBuffer_7.p1(7);
			aBuffer_7.p1((int) local39);
			aBuffer_7.p3(arg1 + (arg0 << 10));
		}
		anInt353 = arg1;
		anInt354 = arg0;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IZ)V")
	public static synchronized void keyPressed(@OriginalArg(0) int arg0) {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(8) long local8 = System.currentTimeMillis();
		@Pc(14) long local14 = (local8 - aLong11) / 10L;
		if (local14 > 250L) {
			local14 = 250L;
		}
		aLong11 = local8;
		if (arg0 == 1000) {
			arg0 = 11;
		}
		if (arg0 == 1001) {
			arg0 = 12;
		}
		if (arg0 == 1002) {
			arg0 = 14;
		}
		if (arg0 == 1003) {
			arg0 = 15;
		}
		if (arg0 >= 1008) {
			arg0 -= 992;
		}
		resize(anInt351, 3);
		aBuffer_7.p1(8);
		aBuffer_7.p1((int) local14);
		aBuffer_7.p1(arg0);
	}

	@OriginalMember(owner = "client!e", name = "c", descriptor = "(II)V")
	public static synchronized void keyReleased(@OriginalArg(0) int arg0) {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(8) long local8 = System.currentTimeMillis();
		@Pc(14) long local14 = (local8 - aLong11) / 10L;
		if (local14 > 250L) {
			local14 = 250L;
		}
		aLong11 = local8;
		if (arg0 == 1000) {
			arg0 = 11;
		}
		if (arg0 == 1001) {
			arg0 = 12;
		}
		if (arg0 == 1002) {
			arg0 = 14;
		}
		if (arg0 == 1003) {
			arg0 = 15;
		}
		if (arg0 >= 1008) {
			arg0 -= 992;
		}
		resize(anInt351, 3);
		aBuffer_7.p1(9);
		aBuffer_7.p1((int) local14);
		aBuffer_7.p1(arg0);
	}

	@OriginalMember(owner = "client!e", name = "d", descriptor = "(I)V")
	public static synchronized void focusGained() {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(11) long local11 = System.currentTimeMillis();
		@Pc(17) long local17 = (local11 - aLong11) / 10L;
		if (local17 > 250L) {
			local17 = 250L;
		}
		aLong11 = local11;
		resize(anInt351, 2);
		aBuffer_7.p1(10);
		aBuffer_7.p1((int) local17);
	}

	@OriginalMember(owner = "client!e", name = "e", descriptor = "(I)V")
	public static synchronized void focusLost() {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(8) long local8 = System.currentTimeMillis();
		@Pc(14) long local14 = (local8 - aLong11) / 10L;
		if (local14 > 250L) {
			local14 = 250L;
		}
		aLong11 = local8;
		resize(anInt351, 2);
		aBuffer_7.p1(11);
		aBuffer_7.p1((int) local14);
	}

	@OriginalMember(owner = "client!e", name = "f", descriptor = "(I)V")
	public static synchronized void mouseEntered() {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(8) long local8 = System.currentTimeMillis();
		@Pc(14) long local14 = (local8 - aLong11) / 10L;
		if (local14 > 250L) {
			local14 = 250L;
		}
		aLong11 = local8;
		resize(anInt351, 2);
		aBuffer_7.p1(12);
		aBuffer_7.p1((int) local14);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(Z)V")
	public static synchronized void mouseExited() {
		if (!aBoolean81) {
			return;
		}
		anInt352++;
		@Pc(11) long local11 = System.currentTimeMillis();
		@Pc(17) long local17 = (local11 - aLong11) / 10L;
		if (local17 > 250L) {
			local17 = 250L;
		}
		aLong11 = local11;
		resize(anInt351, 2);
		aBuffer_7.p1(13);
		aBuffer_7.p1((int) local17);
	}
}
