import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fb")
public class Draw2D extends CacheableNode {

	@OriginalMember(owner = "client!fb", name = "h", descriptor = "I")
	private static int anInt526;

	@OriginalMember(owner = "client!fb", name = "i", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!fb", name = "k", descriptor = "[I")
	public static int[] data;

	@OriginalMember(owner = "client!fb", name = "l", descriptor = "I")
	public static int width;

	@OriginalMember(owner = "client!fb", name = "m", descriptor = "I")
	public static int height;

	@OriginalMember(owner = "client!fb", name = "n", descriptor = "I")
	public static int top;

	@OriginalMember(owner = "client!fb", name = "o", descriptor = "I")
	public static int bottom;

	@OriginalMember(owner = "client!fb", name = "p", descriptor = "I")
	public static int left;

	@OriginalMember(owner = "client!fb", name = "q", descriptor = "I")
	public static int right;

	@OriginalMember(owner = "client!fb", name = "r", descriptor = "I")
	public static int safeX;

	@OriginalMember(owner = "client!fb", name = "s", descriptor = "I")
	public static int centerX;

	@OriginalMember(owner = "client!fb", name = "t", descriptor = "I")
	public static int centerY;

	@OriginalMember(owner = "client!fb", name = "j", descriptor = "Z")
	private static final boolean aBoolean123 = true;

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(I[III)V")
	public static void prepare(@OriginalArg(0) int width, @OriginalArg(1) int[] data, @OriginalArg(3) int height) {
		Draw2D.data = data;
		Draw2D.width = width;
		Draw2D.height = height;
		setBounds(height, 0, width, 0);
	}

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(I)V")
	public static void resetBounds() {
		left = 0;
		top = 0;
		right = width;
		bottom = height;
		safeX = right - 1;
		centerX = right / 2;
	}

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(IIIII)V")
	public static void setBounds(@OriginalArg(0) int y1, @OriginalArg(1) int y0, @OriginalArg(2) int x1, @OriginalArg(4) int x0) {
		if (x0 < 0) {
			x0 = 0;
		}
		if (y0 < 0) {
			y0 = 0;
		}
		if (x1 > width) {
			x1 = width;
		}
		if (y1 > height) {
			y1 = height;
		}
		left = x0;
		top = y0;
		right = x1;
		bottom = y1;
		safeX = right - 1;
		centerX = right / 2;
		centerY = bottom / 2;
	}

	@OriginalMember(owner = "client!fb", name = "b", descriptor = "(I)V")
	public static void clear(@OriginalArg(0) int obfuscator) {
		@Pc(7) int length = width * height;
		for (@Pc(9) int i = 0; i < length; i++) {
			data[i] = 0;
		}
	}

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(IIIBII)V")
	public static void fillRect(@OriginalArg(0) int y, @OriginalArg(1) int x, @OriginalArg(2) int rgb, @OriginalArg(4) int w, @OriginalArg(5) int h) {
		if (x < left) {
			w -= left - x;
			x = left;
		}
		if (y < top) {
			h -= top - y;
			y = top;
		}
		if (x + w > right) {
			w = right - x;
		}
		if (y + h > bottom) {
			h = bottom - y;
		}
		@Pc(50) int off = width - w;
		@Pc(56) int pixel = x + y * width;
		for (@Pc(59) int col = -h; col < 0; col++) {
			for (@Pc(64) int row = -w; row < 0; row++) {
				data[pixel++] = rgb;
			}
			pixel += off;
		}
	}

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(IIIIII)V")
	public static void drawRect(@OriginalArg(1) int x, @OriginalArg(2) int rgb, @OriginalArg(3) int h, @OriginalArg(4) int y, @OriginalArg(5) int w) {
		drawHorizontalLine(rgb, y, w, x);
		drawHorizontalLine(rgb, y + h - 1, w, x);
		drawVerticalLine(rgb, flowObfuscator1, y, h, x);
		drawVerticalLine(rgb, flowObfuscator1, y, h, x + w - 1);
	}

	@OriginalMember(owner = "client!fb", name = "b", descriptor = "(IIIII)V")
	public static void drawHorizontalLine(@OriginalArg(0) int rgb, @OriginalArg(2) int y, @OriginalArg(3) int len, @OriginalArg(4) int x) {
		if (y < top || y >= bottom) {
			return;
		}
		if (x < left) {
			len -= left - x;
			x = left;
		}
		if (x + len > right) {
			len = right - x;
		}
		@Pc(32) int off = x + y * width;
		for (@Pc(37) int w = 0; w < len; w++) {
			data[off + w] = rgb;
		}
	}

	@OriginalMember(owner = "client!fb", name = "c", descriptor = "(IIIII)V")
	public static void drawVerticalLine(@OriginalArg(0) int rgb, @OriginalArg(1) int obfuscator, @OriginalArg(2) int y, @OriginalArg(3) int len, @OriginalArg(4) int x) {
		if (x < left || x >= right) {
			return;
		}
		if (y < top) {
			len -= top - y;
			y = top;
		}
		if (y + len > bottom) {
			len = bottom - y;
		}
		@Pc(32) int off = x + y * width;
		if (obfuscator != 0) {
			flowObfuscator1 = 71;
		}
		for (@Pc(38) int v = 0; v < len; v++) {
			data[off + v * width] = rgb;
		}
	}

	@OriginalMember(owner = "client!fb", name = "<init>", descriptor = "()V")
	protected Draw2D() {
	}
}
