import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jc")
public final class SeqType {

	@OriginalMember(owner = "client!jc", name = "c", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!jc", name = "d", descriptor = "[Lclient!jc;")
	public static SeqType[] instances;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "Z")
	private static final boolean flowObfuscator1 = true;

	@OriginalMember(owner = "client!jc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!jc", name = "e", descriptor = "I")
	public int frameCount;

	@OriginalMember(owner = "client!jc", name = "f", descriptor = "[I")
	public int[] primaryFrames;

	@OriginalMember(owner = "client!jc", name = "g", descriptor = "[I")
	public int[] secondaryFrames;

	@OriginalMember(owner = "client!jc", name = "h", descriptor = "[I")
	public int[] frameDelay;

	@OriginalMember(owner = "client!jc", name = "j", descriptor = "[I")
	public int[] labelGroups;

	@OriginalMember(owner = "client!jc", name = "i", descriptor = "I")
	public int delay = -1;

	@OriginalMember(owner = "client!jc", name = "k", descriptor = "Z")
	public boolean renderPadding = false;

	@OriginalMember(owner = "client!jc", name = "l", descriptor = "I")
	public int priority = 5;

	@OriginalMember(owner = "client!jc", name = "m", descriptor = "I")
	public int shieldOverride = -1;

	@OriginalMember(owner = "client!jc", name = "n", descriptor = "I")
	public int weaponOverride = -1;

	@OriginalMember(owner = "client!jc", name = "o", descriptor = "I")
	public int replays = 99;

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(363, archive.read("seq.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new SeqType[count];
		}
		for (@Pc(27) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new SeqType();
			}
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!jc", name = "<init>", descriptor = "()V")
	private SeqType() {
	}

	@OriginalMember(owner = "client!jc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(13) int opcode = buffer.g1();
			if (opcode == 0) {
				if (this.frameCount == 0) {
					this.frameCount = 1;
					this.primaryFrames = new int[1];
					this.primaryFrames[0] = -1;
					this.secondaryFrames = new int[1];
					this.secondaryFrames[0] = -1;
					this.frameDelay = new int[1];
					this.frameDelay[0] = -1;
					return;
				}
				return;
			}
			@Pc(40) int i;
			if (opcode == 1) {
				this.frameCount = buffer.g1();
				this.primaryFrames = new int[this.frameCount];
				this.secondaryFrames = new int[this.frameCount];
				this.frameDelay = new int[this.frameCount];
				for (i = 0; i < this.frameCount; i++) {
					this.primaryFrames[i] = buffer.g2();
					this.secondaryFrames[i] = buffer.g2();
					if (this.secondaryFrames[i] == 65535) {
						this.secondaryFrames[i] = -1;
					}
					this.frameDelay[i] = buffer.g2();
					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = SeqFrame.instances[this.primaryFrames[i]].delay;
					}
					if (this.frameDelay[i] == 0) {
						this.frameDelay[i] = 1;
					}
				}
			} else if (opcode == 2) {
				this.delay = buffer.g2();
			} else if (opcode == 3) {
				i = buffer.g1();
				this.labelGroups = new int[i + 1];
				for (@Pc(127) int j = 0; j < i; j++) {
					this.labelGroups[j] = buffer.g1();
				}
				this.labelGroups[i] = 9999999;
			} else if (opcode == 4) {
				this.renderPadding = true;
			} else if (opcode == 5) {
				this.priority = buffer.g1();
			} else if (opcode == 6) {
				this.shieldOverride = buffer.g2();
			} else if (opcode == 7) {
				this.weaponOverride = buffer.g2();
			} else if (opcode == 8) {
				this.replays = buffer.g1();
			} else {
				System.out.println("Error unrecognised seq config code: " + opcode);
			}
		}
	}
}
