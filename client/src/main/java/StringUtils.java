import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class StringUtils {

	@OriginalMember(owner = "client!vb", name = "f", descriptor = "[C")
	private static final char[] builder = new char[12];

	@OriginalMember(owner = "client!vb", name = "g", descriptor = "[C")
	private static final char[] BASE37_CHARSET = new char[] { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@OriginalMember(owner = "client!vb", name = "a", descriptor = "(Ljava/lang/String;)J")
	public static long toBase37(@OriginalArg(0) String str) {
		@Pc(3) long value = 0L;

		for (@Pc(5) int i = 0; i < str.length() && i < 12; i++) {
			@Pc(11) char c = str.charAt(i);
			value *= 37L;

			if (c >= 'A' && c <= 'Z') {
				value += c + 1 - 65;
			} else if (c >= 'a' && c <= 'z') {
				value += c + 1 - 97;
			} else if (c >= '0' && c <= '9') {
				value += c + 27 - 48;
			}
		}

		while (value % 37L == 0L && value != 0L) {
			value /= 37L;
		}

		return value;
	}

	@OriginalMember(owner = "client!vb", name = "a", descriptor = "(JZ)Ljava/lang/String;")
	public static String fromBase37(@OriginalArg(0) long value) {
		if (value <= 0L || value >= 6582952005840035281L) {
			return "invalid_name";
		} else if (value % 37L == 0L) {
			return "invalid_name";
		}

		@Pc(19) int len = 0;
		while (value != 0L) {
			@Pc(27) long oldValue = value;
			value /= 37L;
			builder[11 - len++] = BASE37_CHARSET[(int) (oldValue - value * 37L)];
		}

		return new String(builder, 12 - len, len);
	}

	@OriginalMember(owner = "client!vb", name = "a", descriptor = "(ILjava/lang/String;)J")
	public static long genHash(@OriginalArg(1) String str) {
		@Pc(8) String upper = str.toUpperCase();
		@Pc(10) long hash = 0L;
		for (@Pc(12) int i = 0; i < upper.length(); i++) {
			hash = hash * 61L + (long) upper.charAt(i) - 32L;
			hash = hash + (hash >> 56) & 0xFFFFFFFFFFFFFFL;
		}

		return hash;
	}

	@OriginalMember(owner = "client!vb", name = "a", descriptor = "(II)Ljava/lang/String;")
	public static String fromIPv4(@OriginalArg(1) int ip) {
		return (ip >> 24 & 0xFF) + "." + (ip >> 16 & 0xFF) + "." + (ip >> 8 & 0xFF) + "." + (ip & 0xFF);
	}

	@OriginalMember(owner = "client!vb", name = "b", descriptor = "(ILjava/lang/String;)Ljava/lang/String;")
	public static String formatName(@OriginalArg(1) String name) {
		if (name.length() == 0) {
			return name;
		}

		@Pc(11) char[] chars = name.toCharArray();
		for (@Pc(13) int i = 0; i < chars.length; i++) {
			if (chars[i] == '_') {
				chars[i] = ' ';
				if (i + 1 < chars.length && chars[i + 1] >= 'a' && chars[i + 1] <= 'z') {
					chars[i + 1] = (char) (chars[i + 1] + 'A' - 97);
				}
			}
		}

		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] = (char) (chars[0] + 'A' - 97);
		}

		return new String(chars);
	}

	@OriginalMember(owner = "client!vb", name = "a", descriptor = "(Ljava/lang/String;I)Ljava/lang/String;")
	public static String toSentenceCase(@OriginalArg(0) String str) {
		@Pc(2) String lower = str.toLowerCase();
		@Pc(9) char[] chars = lower.toCharArray();
		@Pc(12) int length = chars.length;
		@Pc(14) boolean capitalize = true;

		for (@Pc(16) int i = 0; i < length; i++) {
			@Pc(22) char c = chars[i];
			if (capitalize && c >= 'a' && c <= 'z') {
				chars[i] = (char) (chars[i] - 32);
				capitalize = false;
			}

			if (c == '.' || c == '!') {
				capitalize = true;
			}
		}

		return new String(chars);
	}

	@OriginalMember(owner = "client!vb", name = "c", descriptor = "(ILjava/lang/String;)Ljava/lang/String;")
	public static String censor(@OriginalArg(1) String str) {
		@Pc(1) String c = "";
		for (@Pc(10) int i = 0; i < str.length(); i++) {
			c = c + "*";
		}
		return c;
	}
}
