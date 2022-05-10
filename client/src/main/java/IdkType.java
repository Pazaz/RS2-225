import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gc")
public final class IdkType {

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!gc", name = "d", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!gc", name = "e", descriptor = "[Lclient!gc;")
	public static IdkType[] instances;

	@OriginalMember(owner = "client!gc", name = "b", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!gc", name = "g", descriptor = "[I")
	private int[] modelIndices;

	@OriginalMember(owner = "client!gc", name = "c", descriptor = "Z")
	private final boolean flowObfuscator3 = false;

	@OriginalMember(owner = "client!gc", name = "f", descriptor = "I")
	public int type = -1;

	@OriginalMember(owner = "client!gc", name = "h", descriptor = "[I")
	private final int[] oldColors = new int[6];

	@OriginalMember(owner = "client!gc", name = "i", descriptor = "[I")
	private final int[] newColors = new int[6];

	@OriginalMember(owner = "client!gc", name = "j", descriptor = "[I")
	private final int[] headModelIndices = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!gc", name = "k", descriptor = "Z")
	public boolean validStyle = false;

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(363, archive.read("idk.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new IdkType[count];
		}
		for (@Pc(19) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new IdkType();
			}
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!gc", name = "<init>", descriptor = "()V")
	private IdkType() {
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(8) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			if (opcode == 1) {
				this.type = buffer.g1();
			} else if (opcode == 2) {
				@Pc(26) int count = buffer.g1();
				this.modelIndices = new int[count];
				for (@Pc(32) int i = 0; i < count; i++) {
					this.modelIndices[i] = buffer.g2();
				}
			} else if (opcode == 3) {
				this.validStyle = true;
			} else if (opcode >= 40 && opcode < 50) {
				this.oldColors[opcode - 40] = buffer.g2();
			} else if (opcode >= 50 && opcode < 60) {
				this.newColors[opcode - 50] = buffer.g2();
			} else if (opcode >= 60 && opcode < 70) {
				this.headModelIndices[opcode - 60] = buffer.g2();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "()Lclient!eb;")
	public final Model getModel() {
		if (this.modelIndices == null) {
			return null;
		}
		@Pc(11) Model[] models = new Model[this.modelIndices.length];
		for (@Pc(13) int i = 0; i < this.modelIndices.length; i++) {
			models[i] = new Model(false, this.modelIndices[i]);
		}
		@Pc(40) Model body;
		if (models.length == 1) {
			body = models[0];
		} else {
			body = new Model(0, models, models.length);
		}
		for (@Pc(52) int i = 0; i < 6 && this.oldColors[i] != 0; i++) {
			body.recolor(this.oldColors[i], this.newColors[i]);
		}
		return body;
	}

	@OriginalMember(owner = "client!gc", name = "a", descriptor = "(Z)Lclient!eb;")
	public final Model getHeadModel() {
		@Pc(4) Model[] models = new Model[5];
		@Pc(6) int count = 0;
		for (@Pc(8) int i = 0; i < 5; i++) {
			if (this.headModelIndices[i] != -1) {
				models[count++] = new Model(false, this.headModelIndices[i]);
			}
		}
		@Pc(39) Model head = new Model(0, models, count);
		for (@Pc(41) int i = 0; i < 6 && this.oldColors[i] != 0; i++) {
			head.recolor(this.oldColors[i], this.newColors[i]);
		}
		return head;
	}
}
