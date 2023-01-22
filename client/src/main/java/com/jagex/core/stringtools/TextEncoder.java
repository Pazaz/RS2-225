package com.jagex.core.stringtools;

import com.jagex.core.io.Buffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class TextEncoder {

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "[C")
	private static final char[] builder = new char[100];

	@OriginalMember(owner = "client!wb", name = "b", descriptor = "[C")
	private static final char[] CHARSET = new char[] { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '@', '#', '+', '=', '£', '$', '%', '"', '[', ']' };

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "(Lclient!kb;II)Ljava/lang/String;")
	public static String read(@OriginalArg(0) Buffer buf, @OriginalArg(2) int len) {
		@Pc(3) int pos = 0;
		@Pc(5) int last = -1;

		for (@Pc(11) int i = 0; i < len; i++) {
			@Pc(16) int c = buf.g1();

			int value = c >> 4 & 0xF;
			if (last != -1) {
				builder[pos++] = CHARSET[(last << 4) + value - 195];
				last = -1;
			} else if (value < 13) {
				builder[pos++] = CHARSET[value];
			} else {
				last = value;
			}

			value = c & 0xF;
			if (last != -1) {
				builder[pos++] = CHARSET[(last << 4) + value - 195];
				last = -1;
			} else if (value < 13) {
				builder[pos++] = CHARSET[value];
			} else {
				last = value;
			}
		}

		@Pc(100) boolean capitalize = true;
		for (int i = 0; i < pos; i++) {
			@Pc(108) char c = builder[i];

			if (capitalize && c >= 'a' && c <= 'z') {
				builder[i] = (char) (builder[i] - 32);
				capitalize = false;
			}

			if (c == '.' || c == '!') {
				capitalize = true;
			}
		}

		return new String(builder, 0, pos);
	}

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "(Lclient!kb;ZLjava/lang/String;)V")
	public static void write(@OriginalArg(0) Buffer buf, @OriginalArg(1) boolean flushEnd, @OriginalArg(2) String str) {
		if (str.length() > 80) {
			str = str.substring(0, 80);
		}
		str = str.toLowerCase();
		
		@Pc(15) int msb = -1;
		for (@Pc(17) int i = 0; i < str.length(); i++) {
			@Pc(23) char c = str.charAt(i);
			@Pc(25) int lsb = 0;
	
			for (@Pc(27) int j = 0; j < CHARSET.length; j++) {
				if (c == CHARSET[j]) {
					lsb = j;
					break;
				}
			}

			if (lsb > 12) {
				lsb += 195;
			}

			if (msb == -1) {
				if (lsb < 13) {
					msb = lsb;
				} else {
					buf.p1(lsb);
				}
			} else if (lsb < 13) {
				buf.p1((msb << 4) + lsb);
				msb = -1;
			} else {
				buf.p1((msb << 4) + (lsb >> 4));
				msb = lsb & 0xF;
			}
		}

		if (flushEnd && msb != -1) {
			buf.p1(msb << 4);
		}
	}
}
