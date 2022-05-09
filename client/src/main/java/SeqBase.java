import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!f")
public final class SeqBase {

	@OriginalMember(owner = "client!f", name = "a", descriptor = "[Lclient!f;")
	public static SeqBase[] aSeqBaseArray1;

	@OriginalMember(owner = "client!f", name = "b", descriptor = "I")
	private int anInt385;

	@OriginalMember(owner = "client!f", name = "c", descriptor = "[I")
	public int[] anIntArray128;

	@OriginalMember(owner = "client!f", name = "d", descriptor = "[[I")
	public int[][] anIntArrayArray11;

	@OriginalMember(owner = "client!f", name = "a", descriptor = "(ZLclient!ub;)V")
	public static void decode(@OriginalArg(1) FileArchive arg0) {
		@Pc(11) Buffer local11 = new Buffer(363, arg0.read("base_head.dat", null));
		@Pc(21) Buffer local21 = new Buffer(363, arg0.read("base_type.dat", null));
		@Pc(31) Buffer local31 = new Buffer(363, arg0.read("base_label.dat", null));
		@Pc(34) int local34 = local11.g2();
		@Pc(37) int local37 = local11.g2();
		aSeqBaseArray1 = new SeqBase[local37 + 1];
		for (@Pc(50) int local50 = 0; local50 < local34; local50++) {
			@Pc(55) int local55 = local11.g2();
			@Pc(58) int local58 = local11.g1();
			@Pc(61) int[] local61 = new int[local58];
			@Pc(64) int[][] local64 = new int[local58][];
			for (@Pc(66) int local66 = 0; local66 < local58; local66++) {
				local61[local66] = local21.g1();
				@Pc(76) int local76 = local31.g1();
				local64[local66] = new int[local76];
				for (@Pc(83) int local83 = 0; local83 < local76; local83++) {
					local64[local66][local83] = local31.g1();
				}
			}
			aSeqBaseArray1[local55] = new SeqBase();
			aSeqBaseArray1[local55].anInt385 = local58;
			aSeqBaseArray1[local55].anIntArray128 = local61;
			aSeqBaseArray1[local55].anIntArrayArray11 = local64;
		}
	}

	@OriginalMember(owner = "client!f", name = "<init>", descriptor = "()V")
	private SeqBase() {
	}
}
