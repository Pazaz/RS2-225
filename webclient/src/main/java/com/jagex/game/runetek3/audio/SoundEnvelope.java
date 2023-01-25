package com.jagex.game.runetek3.audio;

import com.jagex.core.io.Buffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!xb")
public class SoundEnvelope {

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "I")
	private int length;

	@OriginalMember(owner = "client!xb", name = "b", descriptor = "[I")
	private int[] shapeDelta;

	@OriginalMember(owner = "client!xb", name = "c", descriptor = "[I")
	private int[] shapePeak;

	@OriginalMember(owner = "client!xb", name = "d", descriptor = "I")
	public int start;

	@OriginalMember(owner = "client!xb", name = "e", descriptor = "I")
	public int end;

	@OriginalMember(owner = "client!xb", name = "f", descriptor = "I")
	public int form;

	@OriginalMember(owner = "client!xb", name = "g", descriptor = "I")
	private int threshold;

	@OriginalMember(owner = "client!xb", name = "h", descriptor = "I")
	private int position;

	@OriginalMember(owner = "client!xb", name = "i", descriptor = "I")
	private int delta;

	@OriginalMember(owner = "client!xb", name = "j", descriptor = "I")
	private int amplitude;

	@OriginalMember(owner = "client!xb", name = "k", descriptor = "I")
	private int ticks;

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(ZLclient!kb;)V")
	public void readShape(@OriginalArg(1) Buffer buf) {
		this.form = buf.g1();
		this.start = buf.g4();
		this.end = buf.g4();
		this.length = buf.g1();
		this.shapeDelta = new int[this.length];
		this.shapePeak = new int[this.length];

		for (int i = 0; i < this.length; i++) {
			this.shapeDelta[i] = buf.g2();
			this.shapePeak[i] = buf.g2();
		}
	}

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(I)V")
	public void reset() {
		this.threshold = 0;
		this.position = 0;
		this.delta = 0;
		this.amplitude = 0;
		this.ticks = 0;
	}

	@OriginalMember(owner = "client!xb", name = "a", descriptor = "(ZI)I")
	public int evaluate(@OriginalArg(1) int delta) {
		if (this.ticks >= this.threshold) {
			this.amplitude = this.shapePeak[this.position++] << 15;
			if (this.position >= this.length) {
				this.position = this.length - 1;
			}

			this.threshold = (int) ((double) this.shapeDelta[this.position] / 65536.0D * (double) delta);
			if (this.threshold > this.ticks) {
				this.delta = ((this.shapePeak[this.position] << 15) - this.amplitude) / (this.threshold - this.ticks);
			}
		}

		this.amplitude += this.delta;
		this.ticks++;
		return this.amplitude - this.delta >> 15;
	}
}
