package com.jagex.game.runetek3.config;

import com.jagex.core.io.Buffer;
import com.jagex.core.io.FileArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fc")
public class FloType {

	@OriginalMember(owner = "client!fc", name = "c", descriptor = "I")
	public static int count;

	@OriginalMember(owner = "client!fc", name = "d", descriptor = "[Lclient!fc;")
	public static FloType[] instances;

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
	public int chroma;

	@OriginalMember(owner = "client!fc", name = "n", descriptor = "I")
	public int luminance;

	@OriginalMember(owner = "client!fc", name = "o", descriptor = "I")
	public int blendHueMultiplier;

	@OriginalMember(owner = "client!fc", name = "f", descriptor = "I")
	public int texture = -1;

	@OriginalMember(owner = "client!fc", name = "g", descriptor = "Z")
	private boolean opcode3 = false;

	@OriginalMember(owner = "client!fc", name = "h", descriptor = "Z")
	public boolean occlude = true;

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!ub;I)V")
	public static void unpack(@OriginalArg(0) FileArchive archive) {
		@Pc(9) Buffer dat = new Buffer(archive.read("flo.dat", null));
		count = dat.g2();
		if (instances == null) {
			instances = new FloType[count];
		}

		for (@Pc(23) int i = 0; i < count; i++) {
			if (instances[i] == null) {
				instances[i] = new FloType();
			}

			instances[i].decode(dat);
		}
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(ZLclient!kb;)V")
	public void decode(@OriginalArg(1) Buffer dat) {
		while (true) {
			@Pc(10) int opcode = dat.g1();
			if (opcode == 0) {
				break;
			}

			if (opcode == 1) {
				this.rgb = dat.g3();
				this.setColor(this.rgb);
			} else if (opcode == 2) {
				this.texture = dat.g1();
			} else if (opcode == 3) {
				this.opcode3 = true;
			} else if (opcode == 5) {
				this.occlude = false;
			} else if (opcode == 6) {
				this.name = dat.gstr();
			} else {
				System.out.println("Error unrecognised config code: " + opcode);
			}
		}
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(II)V")
	private void setColor(@OriginalArg(1) int color) {
		@Pc(10) double r = (double) (color >> 16 & 0xFF) / 256.0D;
		@Pc(28) double g = (double) (color >> 8 & 0xFF) / 256.0D;
		@Pc(35) double b = (double) (color & 0xFF) / 256.0D;

		@Pc(37) double min = Math.min(Math.min(r, g), b);
		@Pc(51) double max = Math.max(Math.max(r, g), b);

		@Pc(65) double h = 0.0D;
		@Pc(67) double s = 0.0D;
		@Pc(73) double l = (min + max) / 2.0D;

		if (min != max) {
			if (l < 0.5D) {
				s = (max - min) / (max + min);
			} else if (l >= 0.5D) {
				s = (max - min) / (2.0D - max - min);
			}

			if (r == max) {
				h = (g - b) / (max - min);
			} else if (g == max) {
				h = (b - r) / (max - min) + 2.0D;
			} else if (b == max) {
				h = (r - g) / (max - min) + 4.0D;
			}
		}

		h /= 6.0D;

		this.hue = (int) (h * 256.0D);
		this.saturation = (int) (s * 256.0D);
		this.lightness = (int) (l * 256.0D);

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

		if (l > 0.5D) {
			this.luminance = (int) ((1.0D - l) * s * 512.0D);
		} else {
			this.luminance = (int) (l * s * 512.0D);
		}

		if (this.luminance < 1) {
			this.luminance = 1;
		}

		this.chroma = (int) (h * (double) this.luminance);

		@Pc(248) int randHue = this.hue + (int) (Math.random() * 16.0D) - 8;
		if (randHue < 0) {
			randHue = 0;
		} else if (randHue > 255) {
			randHue = 255;
		}

		@Pc(269) int randSaturation = this.saturation + (int) (Math.random() * 48.0D) - 24;
		if (randSaturation < 0) {
			randSaturation = 0;
		} else if (randSaturation > 255) {
			randSaturation = 255;
		}

		@Pc(290) int randLightness = this.lightness + (int) (Math.random() * 48.0D) - 24;
		if (randLightness < 0) {
			randLightness = 0;
		} else if (randLightness > 255) {
			randLightness = 255;
		}

		this.blendHueMultiplier = this.setHsl16(randHue, randSaturation, randLightness);
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(III)I")
	private int setHsl16(@OriginalArg(0) int h, @OriginalArg(1) int s, @OriginalArg(2) int l) {
		if (l > 179) {
			s /= 2;
		}

		if (l > 192) {
			s /= 2;
		}

		if (l > 217) {
			s /= 2;
		}

		if (l > 243) {
			s /= 2;
		}

		return (h / 4 << 10) + (s / 32 << 7) + l / 2;
	}
}
