package com.jagex.core.stringutils;

import com.jagex.core.io.Buffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class TextEncoder {

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "[C")
	private static final char[] builder = new char[100];

	@OriginalMember(owner = "client!wb", name = "b", descriptor = "[C")
	private static final char[] CHAR_LOOKUP = new char[] { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '@', '#', '+', '=', '£', '$', '%', '"', '[', ']' };

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "(Lclient!kb;II)Ljava/lang/String;")
	public static String read(@OriginalArg(0) Buffer arg0, @OriginalArg(2) int arg1) {
		@Pc(3) int local3 = 0;
		@Pc(5) int local5 = -1;
		@Pc(22) int local22;
		for (@Pc(11) int local11 = 0; local11 < arg1; local11++) {
			@Pc(16) int local16 = arg0.g1();
			local22 = local16 >> 4 & 0xF;
			if (local5 != -1) {
				builder[local3++] = CHAR_LOOKUP[(local5 << 4) + local22 - 195];
				local5 = -1;
			} else if (local22 < 13) {
				builder[local3++] = CHAR_LOOKUP[local22];
			} else {
				local5 = local22;
			}
			local22 = local16 & 0xF;
			if (local5 != -1) {
				builder[local3++] = CHAR_LOOKUP[(local5 << 4) + local22 - 195];
				local5 = -1;
			} else if (local22 < 13) {
				builder[local3++] = CHAR_LOOKUP[local22];
			} else {
				local5 = local22;
			}
		}
		@Pc(100) boolean local100 = true;
		for (local22 = 0; local22 < local3; local22++) {
			@Pc(108) char local108 = builder[local22];
			if (local100 && local108 >= 'a' && local108 <= 'z') {
				builder[local22] = (char) (builder[local22] - 32);
				local100 = false;
			}
			if (local108 == '.' || local108 == '!') {
				local100 = true;
			}
		}
		return new String(builder, 0, local3);
	}

	@OriginalMember(owner = "client!wb", name = "a", descriptor = "(Lclient!kb;ZLjava/lang/String;)V")
	public static void write(@OriginalArg(0) Buffer arg0, @OriginalArg(2) String arg1) {
		if (arg1.length() > 80) {
			arg1 = arg1.substring(0, 80);
		}
		arg1 = arg1.toLowerCase();
		@Pc(15) int local15 = -1;
		for (@Pc(17) int local17 = 0; local17 < arg1.length(); local17++) {
			@Pc(23) char local23 = arg1.charAt(local17);
			@Pc(25) int local25 = 0;
			for (@Pc(27) int local27 = 0; local27 < CHAR_LOOKUP.length; local27++) {
				if (local23 == CHAR_LOOKUP[local27]) {
					local25 = local27;
					break;
				}
			}
			if (local25 > 12) {
				local25 += 195;
			}
			if (local15 == -1) {
				if (local25 < 13) {
					local15 = local25;
				} else {
					arg0.p1(local25);
				}
			} else if (local25 < 13) {
				arg0.p1((local15 << 4) + local25);
				local15 = -1;
			} else {
				arg0.p1((local15 << 4) + (local25 >> 4));
				local15 = local25 & 0xF;
			}
		}
		if (local15 != -1) {
			arg0.p1(local15 << 4);
		}
	}
}
