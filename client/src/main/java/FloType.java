import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fc")
public final class FloType {

	@OriginalMember(owner = "client!fc", name = "c", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!fc", name = "d", descriptor = "[Lclient!fc;")
	public static FloType[] instances;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	private static final int flowObfuscator2 = 473;

	@OriginalMember(owner = "client!fc", name = "b", descriptor = "I")
	private static final int flowObfuscator1 = -546;

	@OriginalMember(owner = "client!fc", name = "e", descriptor = "I")
	public int rgb;

	@OriginalMember(owner = "client!fc", name = "i", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!fc", name = "j", descriptor = "I")
	public int hue;

	@OriginalMember(owner = "client!fc", name = "k", descriptor = "I")
	public int saturation;

	@OriginalMember(owner = "client!fc", name = "l", descriptor = "I")
	public int lightness;

	@OriginalMember(owner = "client!fc", name = "m", descriptor = "I")
	public int blendHue;

	@OriginalMember(owner = "client!fc", name = "n", descriptor = "I")
	public int hsl16;

	@OriginalMember(owner = "client!fc", name = "o", descriptor = "I")
	public int blendHueMultiplier;

	@OriginalMember(owner = "client!fc", name = "f", descriptor = "I")
	public int textureIndex = -1;

	@OriginalMember(owner = "client!fc", name = "g", descriptor = "Z")
	private boolean opcode3 = false;

	@OriginalMember(owner = "client!fc", name = "h", descriptor = "Z")
	public boolean occlude = true;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer buffer = new Buffer(363, archive.read("flo.dat", null));
		count = buffer.g2();
		if (instances == null) {
			instances = new FloType[count];
		}
		for (@Pc(23) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new FloType();
			}
			instances[i].decode(buffer);
		}
	}

	@OriginalMember(owner = "client!fc", name = "<init>", descriptor = "()V")
	private FloType() {
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		while (true) {
			@Pc(10) int opcode = buffer.g1();
			if (opcode == 0) {
				return;
			}
			if (opcode == 1) {
				this.rgb = buffer.g3();
				this.setColor(flowObfuscator1, this.rgb);
			} else if (opcode == 2) {
				this.textureIndex = buffer.g1();
			} else if (opcode == 3) {
				this.opcode3 = true;
			} else if (opcode == 5) {
				this.occlude = false;
			} else if (opcode == 6) {
				this.name = buffer.gjstr();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(II)V")
	private void setColor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(10) double local10 = (double) (arg1 >> 16 & 0xFF) / 256.0D;
		if (arg0 >= 0) {
			for (@Pc(14) int local14 = 1; local14 > 0; local14++) {
			}
		}
		@Pc(28) double local28 = (double) (arg1 >> 8 & 0xFF) / 256.0D;
		@Pc(35) double local35 = (double) (arg1 & 0xFF) / 256.0D;
		@Pc(37) double local37 = local10;
		if (local28 < local10) {
			local37 = local28;
		}
		if (local35 < local37) {
			local37 = local35;
		}
		@Pc(51) double local51 = local10;
		if (local28 > local10) {
			local51 = local28;
		}
		if (local35 > local51) {
			local51 = local35;
		}
		@Pc(65) double local65 = 0.0D;
		@Pc(67) double local67 = 0.0D;
		@Pc(73) double local73 = (local37 + local51) / 2.0D;
		if (local37 != local51) {
			if (local73 < 0.5D) {
				local67 = (local51 - local37) / (local51 + local37);
			}
			if (local73 >= 0.5D) {
				local67 = (local51 - local37) / (2.0D - local51 - local37);
			}
			if (local10 == local51) {
				local65 = (local28 - local35) / (local51 - local37);
			} else if (local28 == local51) {
				local65 = (local35 - local10) / (local51 - local37) + 2.0D;
			} else if (local35 == local51) {
				local65 = (local10 - local28) / (local51 - local37) + 4.0D;
			}
		}
		local65 /= 6.0D;
		this.hue = (int) (local65 * 256.0D);
		this.saturation = (int) (local67 * 256.0D);
		this.lightness = (int) (local73 * 256.0D);
		if (this.saturation < 0) {
			this.saturation = 0;
		} else if (this.saturation > 255) {
			this.saturation = 255;
		}
		if (this.lightness < 0) {
			this.lightness = 0;
		} else if (this.lightness > 255) {
			this.lightness = 255;
		}
		if (local73 > 0.5D) {
			this.hsl16 = (int) ((1.0D - local73) * local67 * 512.0D);
		} else {
			this.hsl16 = (int) (local73 * local67 * 512.0D);
		}
		if (this.hsl16 < 1) {
			this.hsl16 = 1;
		}
		this.blendHue = (int) (local65 * (double) this.hsl16);
		@Pc(248) int local248 = this.hue + (int) (Math.random() * 16.0D) - 8;
		if (local248 < 0) {
			local248 = 0;
		} else if (local248 > 255) {
			local248 = 255;
		}
		@Pc(269) int local269 = this.saturation + (int) (Math.random() * 48.0D) - 24;
		if (local269 < 0) {
			local269 = 0;
		} else if (local269 > 255) {
			local269 = 255;
		}
		@Pc(290) int local290 = this.lightness + (int) (Math.random() * 48.0D) - 24;
		if (local290 < 0) {
			local290 = 0;
		} else if (local290 > 255) {
			local290 = 255;
		}
		this.blendHueMultiplier = this.setHsl16(local248, local269, local290);
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(III)I")
	private int setHsl16(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (arg2 > 179) {
			arg1 /= 2;
		}
		if (arg2 > 192) {
			arg1 /= 2;
		}
		if (arg2 > 217) {
			arg1 /= 2;
		}
		if (arg2 > 243) {
			arg1 /= 2;
		}
		return (arg0 / 4 << 10) + (arg1 / 32 << 7) + arg2 / 2;
	}
}
