import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kc")
public final class SpotAnimType {

	@OriginalMember(owner = "client!kc", name = "c", descriptor = "[Lclient!kc;")
	public static SpotAnimType[] instances;

	@OriginalMember(owner = "client!kc", name = "p", descriptor = "Lclient!s;")
	public static Cache models = new Cache(30);

	@OriginalMember(owner = "client!kc", name = "b", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!kc", name = "d", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!kc", name = "e", descriptor = "I")
	private int model;

	@OriginalMember(owner = "client!kc", name = "g", descriptor = "Lclient!jc;")
	public SeqType seq;

	@OriginalMember(owner = "client!kc", name = "m", descriptor = "I")
	public int orientation;

	@OriginalMember(owner = "client!kc", name = "n", descriptor = "I")
	public int ambient;

	@OriginalMember(owner = "client!kc", name = "o", descriptor = "I")
	public int contrast;

	@OriginalMember(owner = "client!kc", name = "f", descriptor = "I")
	private int anim = -1;

	@OriginalMember(owner = "client!kc", name = "h", descriptor = "Z")
	public boolean disposeAlpha = false;

	@OriginalMember(owner = "client!kc", name = "i", descriptor = "[I")
	private final int[] recol_s = new int[6];

	@OriginalMember(owner = "client!kc", name = "j", descriptor = "[I")
	private final int[] recol_d = new int[6];

	@OriginalMember(owner = "client!kc", name = "k", descriptor = "I")
	public int resizeh = 128;

	@OriginalMember(owner = "client!kc", name = "l", descriptor = "I")
	public int resizev = 128;

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		@Pc(13) Buffer local13 = new Buffer(archive.read("spotanim.dat", null));
		count = local13.g2();
		if (instances == null) {
			instances = new SpotAnimType[count];
		}
		for (@Pc(23) int local23 = 0; local23 < count; local23++) {
			if (instances[local23] == null) {
				instances[local23] = new SpotAnimType();
			}
			instances[local23].id = local23;
			instances[local23].decode(local13);
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer arg1) {
		@Pc(5) int local5;
		while (true) {
			local5 = arg1.g1();
			if (local5 == 0) {
				break;
			}

			if (local5 == 1) {
				this.model = arg1.g2();
			} else if (local5 == 2) {
				this.anim = arg1.g2();
				if (SeqType.instances != null) {
					this.seq = SeqType.instances[this.anim];
				}
			} else if (local5 == 3) {
				this.disposeAlpha = true;
			} else if (local5 == 4) {
				this.resizeh = arg1.g2();
			} else if (local5 == 5) {
				this.resizev = arg1.g2();
			} else if (local5 == 6) {
				this.orientation = arg1.g2();
			} else if (local5 == 7) {
				this.ambient = arg1.g1();
			} else if (local5 == 8) {
				this.contrast = arg1.g1();
			} else if (local5 >= 40 && local5 < 50) {
				this.recol_s[local5 - 40] = arg1.g2();
			} else if (local5 >= 50 && local5 < 60) {
				this.recol_d[local5 - 50] = arg1.g2();
			} else {
				System.out.println("Error unrecognised spotanim config code: " + local5);
			}
		}
	}

	@OriginalMember(owner = "client!kc", name = "a", descriptor = "()Lclient!eb;")
	public Model getModel() {
		@Pc(6) Model local6 = (Model) models.get(this.id);
		if (local6 != null) {
			return local6;
		}
		local6 = new Model(false, this.model);
		for (@Pc(19) int local19 = 0; local19 < 6; local19++) {
			if (this.recol_s[0] != 0) {
				local6.recolor(this.recol_s[local19], this.recol_d[local19]);
			}
		}
		models.put(this.id, local6);
		return local6;
	}
}
