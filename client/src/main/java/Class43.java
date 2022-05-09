import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!yb")
public final class Class43 {

	@OriginalMember(owner = "client!yb", name = "h", descriptor = "I")
	private int anInt878;

	@OriginalMember(owner = "client!yb", name = "i", descriptor = "I")
	private int anInt879;

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "Z")
	private final boolean aBoolean158 = true;

	@OriginalMember(owner = "client!yb", name = "g", descriptor = "[Lclient!zb;")
	private final Class44[] aClass44Array1 = new Class44[10];

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZLclient!kb;)V")
	public final void method563(@OriginalArg(1) Class1_Sub3_Sub3 arg0) {
		for (@Pc(1) int local1 = 0; local1 < 10; local1++) {
			@Pc(6) int local6 = arg0.method391();
			if (local6 != 0) {
				arg0.anInt561--;
				this.aClass44Array1[local1] = new Class44();
				this.aClass44Array1[local1].method578(arg0);
			}
		}
		this.anInt878 = arg0.method393();
		this.anInt879 = arg0.method393();
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(B)I")
	public final int method564() {
		@Pc(3) int local3 = 9999999;
		for (@Pc(5) int local5 = 0; local5 < 10; local5++) {
			if (this.aClass44Array1[local5] != null && this.aClass44Array1[local5].anInt948 / 20 < local3) {
				local3 = this.aClass44Array1[local5].anInt948 / 20;
			}
		}
		if (this.anInt878 < this.anInt879 && this.anInt878 / 20 < local3) {
			local3 = this.anInt878 / 20;
		}
		if (local3 == 9999999 || local3 == 0) {
			return 0;
		}
		for (@Pc(67) int local67 = 0; local67 < 10; local67++) {
			if (this.aClass44Array1[local67] != null) {
				this.aClass44Array1[local67].anInt948 -= local3 * 20;
			}
		}
		if (this.anInt878 < this.anInt879) {
			this.anInt878 -= local3 * 20;
			this.anInt879 -= local3 * 20;
		}
		return local3;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(ZI)Lclient!kb;")
	public final Class1_Sub3_Sub3 method565(@OriginalArg(1) int arg0) {
		@Pc(3) int local3 = this.method566(arg0);
		Static32.aClass1_Sub3_Sub3_23.anInt561 = 0;
		Static32.aClass1_Sub3_Sub3_23.method385(1380533830);
		Static32.aClass1_Sub3_Sub3_23.method386(local3 + 36);
		Static32.aClass1_Sub3_Sub3_23.method385(1463899717);
		Static32.aClass1_Sub3_Sub3_23.method385(1718449184);
		Static32.aClass1_Sub3_Sub3_23.method386(16);
		Static32.aClass1_Sub3_Sub3_23.method383(this.aBoolean158, 1);
		Static32.aClass1_Sub3_Sub3_23.method383(this.aBoolean158, 1);
		Static32.aClass1_Sub3_Sub3_23.method386(22050);
		Static32.aClass1_Sub3_Sub3_23.method386(22050);
		Static32.aClass1_Sub3_Sub3_23.method383(this.aBoolean158, 1);
		Static32.aClass1_Sub3_Sub3_23.method383(this.aBoolean158, 8);
		Static32.aClass1_Sub3_Sub3_23.method385(1684108385);
		Static32.aClass1_Sub3_Sub3_23.method386(local3);
		Static32.aClass1_Sub3_Sub3_23.anInt561 += local3;
		return Static32.aClass1_Sub3_Sub3_23;
	}

	@OriginalMember(owner = "client!yb", name = "a", descriptor = "(I)I")
	private int method566(@OriginalArg(0) int arg0) {
		@Pc(3) int local3 = 0;
		for (@Pc(5) int local5 = 0; local5 < 10; local5++) {
			if (this.aClass44Array1[local5] != null && this.aClass44Array1[local5].anInt947 + this.aClass44Array1[local5].anInt948 > local3) {
				local3 = this.aClass44Array1[local5].anInt947 + this.aClass44Array1[local5].anInt948;
			}
		}
		if (local3 == 0) {
			return 0;
		}
		@Pc(51) int local51 = local3 * 22050 / 1000;
		@Pc(58) int local58 = this.anInt878 * 22050 / 1000;
		@Pc(65) int local65 = this.anInt879 * 22050 / 1000;
		if (local58 < 0 || local58 > local51 || local65 < 0 || local65 > local51 || local58 >= local65) {
			arg0 = 0;
		}
		@Pc(90) int local90 = local51 + (local65 - local58) * (arg0 - 1);
		for (@Pc(92) int local92 = 44; local92 < local90 + 44; local92++) {
			Static32.aByteArray15[local92] = -128;
		}
		@Pc(123) int local123;
		@Pc(133) int local133;
		@Pc(147) int local147;
		for (@Pc(106) int local106 = 0; local106 < 10; local106++) {
			if (this.aClass44Array1[local106] != null) {
				local123 = this.aClass44Array1[local106].anInt947 * 22050 / 1000;
				local133 = this.aClass44Array1[local106].anInt948 * 22050 / 1000;
				@Pc(145) int[] local145 = this.aClass44Array1[local106].method576(local123, this.aClass44Array1[local106].anInt947);
				for (local147 = 0; local147 < local123; local147++) {
					Static32.aByteArray15[local147 + local133 + 44] += (byte) (local145[local147] >> 8);
				}
			}
		}
		if (arg0 > 1) {
			local58 += 44;
			local65 += 44;
			local51 += 44;
			local90 += 44;
			local123 = local90 - local51;
			for (local133 = local51 - 1; local133 >= local65; local133--) {
				Static32.aByteArray15[local133 + local123] = Static32.aByteArray15[local133];
			}
			for (@Pc(205) int local205 = 1; local205 < arg0; local205++) {
				local123 = (local65 - local58) * local205;
				for (local147 = local58; local147 < local65; local147++) {
					Static32.aByteArray15[local147 + local123] = Static32.aByteArray15[local147];
				}
			}
			local90 -= 44;
		}
		return local90;
	}
}
