import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!xb")
public final class SoundEnvelope {

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "I")
	private int anInt818;

	@OriginalMember(owner = "client!xb", name = "b", descriptor = "[I")
	private int[] anIntArray227;

	@OriginalMember(owner = "client!xb", name = "c", descriptor = "[I")
	private int[] anIntArray228;

	@OriginalMember(owner = "client!xb", name = "d", descriptor = "I")
	public int anInt819;

	@OriginalMember(owner = "client!xb", name = "e", descriptor = "I")
	public int anInt820;

	@OriginalMember(owner = "client!xb", name = "f", descriptor = "I")
	public int anInt821;

	@OriginalMember(owner = "client!xb", name = "g", descriptor = "I")
	private int anInt822;

	@OriginalMember(owner = "client!xb", name = "h", descriptor = "I")
	private int anInt823;

	@OriginalMember(owner = "client!xb", name = "i", descriptor = "I")
	private int anInt824;

	@OriginalMember(owner = "client!xb", name = "j", descriptor = "I")
	private int anInt825;

	@OriginalMember(owner = "client!xb", name = "k", descriptor = "I")
	private int anInt826;

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(ZLclient!kb;)V")
	public final void method552(@OriginalArg(1) Buffer arg0) {
		this.anInt821 = arg0.method391();
		this.anInt819 = arg0.method396();
		this.anInt820 = arg0.method396();
		this.anInt818 = arg0.method391();
		this.anIntArray227 = new int[this.anInt818];
		this.anIntArray228 = new int[this.anInt818];
		for (@Pc(38) int local38 = 0; local38 < this.anInt818; local38++) {
			this.anIntArray227[local38] = arg0.method393();
			this.anIntArray228[local38] = arg0.method393();
		}
	}

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(I)V")
	public final void method553(@OriginalArg(0) int arg0) {
		this.anInt822 = 0;
		this.anInt823 = 0;
		this.anInt824 = 0;
		this.anInt825 = 0;
		if (arg0 >= 8 && arg0 <= 8) {
			this.anInt826 = 0;
		}
	}

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(ZI)I")
	public final int method554(@OriginalArg(1) int arg0) {
		if (this.anInt826 >= this.anInt822) {
			this.anInt825 = this.anIntArray228[this.anInt823++] << 15;
			if (this.anInt823 >= this.anInt818) {
				this.anInt823 = this.anInt818 - 1;
			}
			this.anInt822 = (int) ((double) this.anIntArray227[this.anInt823] / 65536.0D * (double) arg0);
			if (this.anInt822 > this.anInt826) {
				this.anInt824 = ((this.anIntArray228[this.anInt823] << 15) - this.anInt825) / (this.anInt822 - this.anInt826);
			}
		}
		this.anInt825 += this.anInt824;
		this.anInt826++;
		return this.anInt825 - this.anInt824 >> 15;
	}
}
