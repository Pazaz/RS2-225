import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!g")
public final class SeqFrame {

	@OriginalMember(owner = "client!g", name = "a", descriptor = "[Lclient!g;")
	public static SeqFrame[] instances;

	@OriginalMember(owner = "client!g", name = "b", descriptor = "I")
	public int delay;

	@OriginalMember(owner = "client!g", name = "c", descriptor = "Lclient!f;")
	public SeqBase transform;

	@OriginalMember(owner = "client!g", name = "d", descriptor = "I")
	public int groupCount;

	@OriginalMember(owner = "client!g", name = "e", descriptor = "[I")
	public int[] groups;

	@OriginalMember(owner = "client!g", name = "f", descriptor = "[I")
	public int[] x;

	@OriginalMember(owner = "client!g", name = "g", descriptor = "[I")
	public int[] y;

	@OriginalMember(owner = "client!g", name = "h", descriptor = "[I")
	public int[] z;

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(ZLclient!ub;)V")
	public static void decode(@OriginalArg(1) FileArchive archive) {
		@Pc(17) Buffer head = new Buffer(363, archive.read("frame_head.dat", null));
		@Pc(27) Buffer tran1 = new Buffer(363, archive.read("frame_tran1.dat", null));
		@Pc(37) Buffer tran2 = new Buffer(363, archive.read("frame_tran2.dat", null));
		@Pc(47) Buffer del = new Buffer(363, archive.read("frame_del.dat", null));
		@Pc(50) int frameCount = head.g2();
		@Pc(53) int totalFrames = head.g2();
		instances = new SeqFrame[totalFrames + 1];
		@Pc(61) int[] labels = new int[500];
		@Pc(64) int[] x = new int[500];
		@Pc(67) int[] y = new int[500];
		@Pc(70) int[] z = new int[500];
		for (@Pc(72) int i = 0; i < frameCount; i++) {
			@Pc(77) int frameId = head.g2();
			@Pc(85) SeqFrame frame = instances[frameId] = new SeqFrame();
			frame.delay = del.g1();
			@Pc(92) int baseId = head.g2();
			@Pc(96) SeqBase base = SeqBase.instances[baseId];
			frame.transform = base;
			@Pc(102) int groupCount = head.g1();
			@Pc(104) int lastGroup = -1;
			@Pc(106) int count = 0;
			@Pc(113) int flags;
			for (@Pc(108) int j = 0; j < groupCount; j++) {
				flags = tran1.g1();
				if (flags > 0) {
					if (base.types[j] != 0) {
						for (@Pc(124) int group = j - 1; group > lastGroup; group--) {
							if (base.types[group] == 0) {
								labels[count] = group;
								x[count] = 0;
								y[count] = 0;
								z[count] = 0;
								count++;
								break;
							}
						}
					}
					labels[count] = j;
					@Pc(160) short defaultValue = 0;
					if (base.types[labels[count]] == 3) {
						defaultValue = 128;
					}
					if ((flags & 0x1) == 0) {
						x[count] = defaultValue;
					} else {
						x[count] = tran2.gSmart1or2s();
					}
					if ((flags & 0x2) == 0) {
						y[count] = defaultValue;
					} else {
						y[count] = tran2.gSmart1or2s();
					}
					if ((flags & 0x4) == 0) {
						z[count] = defaultValue;
					} else {
						z[count] = tran2.gSmart1or2s();
					}
					lastGroup = j;
					count++;
				}
			}
			frame.groupCount = count;
			frame.groups = new int[count];
			frame.x = new int[count];
			frame.y = new int[count];
			frame.z = new int[count];
			for (flags = 0; flags < count; flags++) {
				frame.groups[flags] = labels[flags];
				frame.x[flags] = x[flags];
				frame.y[flags] = y[flags];
				frame.z[flags] = z[flags];
			}
		}
	}

	@OriginalMember(owner = "client!g", name = "<init>", descriptor = "()V")
	private SeqFrame() {
	}
}
