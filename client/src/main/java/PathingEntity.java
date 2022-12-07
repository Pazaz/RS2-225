import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!x")
public class PathingEntity extends Entity {

	@OriginalMember(owner = "client!x", name = "g", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!x", name = "h", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!x", name = "i", descriptor = "I")
	public int animationDelay;

	@OriginalMember(owner = "client!x", name = "s", descriptor = "Ljava/lang/String;")
	public String spoken;

	@OriginalMember(owner = "client!x", name = "u", descriptor = "I")
	public int spokenColor;

	@OriginalMember(owner = "client!x", name = "v", descriptor = "I")
	public int spokenEffect;

	@OriginalMember(owner = "client!x", name = "w", descriptor = "I")
	public int damageTaken;

	@OriginalMember(owner = "client!x", name = "x", descriptor = "I")
	public int damageType;

	@OriginalMember(owner = "client!x", name = "z", descriptor = "I")
	public int currentHealth;

	@OriginalMember(owner = "client!x", name = "A", descriptor = "I")
	public int maxHealth;

	@OriginalMember(owner = "client!x", name = "C", descriptor = "I")
	public int focusX;

	@OriginalMember(owner = "client!x", name = "D", descriptor = "I")
	public int focusZ;

	@OriginalMember(owner = "client!x", name = "F", descriptor = "I")
	public int secondarySeqFrame;

	@OriginalMember(owner = "client!x", name = "G", descriptor = "I")
	public int secondarySeqCycle;

	@OriginalMember(owner = "client!x", name = "I", descriptor = "I")
	public int primarySeqFrame;

	@OriginalMember(owner = "client!x", name = "J", descriptor = "I")
	public int primarySeqCycle;

	@OriginalMember(owner = "client!x", name = "K", descriptor = "I")
	public int primarySeqDelay;

	@OriginalMember(owner = "client!x", name = "L", descriptor = "I")
	public int primarySeqPlays;

	@OriginalMember(owner = "client!x", name = "N", descriptor = "I")
	public int spotAnimFrame;

	@OriginalMember(owner = "client!x", name = "O", descriptor = "I")
	public int spotAnimCycle;

	@OriginalMember(owner = "client!x", name = "P", descriptor = "I")
	public int lastSpotAnimCycle;

	@OriginalMember(owner = "client!x", name = "Q", descriptor = "I")
	public int spotAnimOffsetY;

	@OriginalMember(owner = "client!x", name = "R", descriptor = "I")
	public int srcTileX;

	@OriginalMember(owner = "client!x", name = "S", descriptor = "I")
	public int dstTileX;

	@OriginalMember(owner = "client!x", name = "T", descriptor = "I")
	public int srcTileZ;

	@OriginalMember(owner = "client!x", name = "U", descriptor = "I")
	public int dstTileZ;

	@OriginalMember(owner = "client!x", name = "V", descriptor = "I")
	public int firstMoveCycle;

	@OriginalMember(owner = "client!x", name = "W", descriptor = "I")
	public int lastMoveCycle;

	@OriginalMember(owner = "client!x", name = "X", descriptor = "I")
	public int faceDirection;

	@OriginalMember(owner = "client!x", name = "Y", descriptor = "I")
	public int removeTimer;

	@OriginalMember(owner = "client!x", name = "Z", descriptor = "I")
	public int height;

	@OriginalMember(owner = "client!x", name = "ab", descriptor = "I")
	public int dstYaw;

	@OriginalMember(owner = "client!x", name = "bb", descriptor = "I")
	public int pathRemaining;

	@OriginalMember(owner = "client!x", name = "fb", descriptor = "I")
	public int int1; // TODO

	@OriginalMember(owner = "client!x", name = "j", descriptor = "Z")
	public boolean animationStretches = false;

	@OriginalMember(owner = "client!x", name = "k", descriptor = "I")
	public int index = 1;

	@OriginalMember(owner = "client!x", name = "l", descriptor = "I")
	public int standSeq = -1;

	@OriginalMember(owner = "client!x", name = "m", descriptor = "I")
	public int turnSeq = -1;

	@OriginalMember(owner = "client!x", name = "n", descriptor = "I")
	public int runSeq = -1;

	@OriginalMember(owner = "client!x", name = "o", descriptor = "I")
	public int walkSeq = -1;

	@OriginalMember(owner = "client!x", name = "p", descriptor = "I")
	public int turnAroundSeq = -1;

	@OriginalMember(owner = "client!x", name = "q", descriptor = "I")
	public int turnRightSeq = -1;

	@OriginalMember(owner = "client!x", name = "r", descriptor = "I")
	public int turnLeftSeq = -1;

	@OriginalMember(owner = "client!x", name = "t", descriptor = "I")
	public int textCycle = 100;

	@OriginalMember(owner = "client!x", name = "y", descriptor = "I")
	public int lastCombatCycle = -1000;

	@OriginalMember(owner = "client!x", name = "B", descriptor = "I")
	public int targetEntity = -1;

	@OriginalMember(owner = "client!x", name = "E", descriptor = "I")
	public int secondarySeq = -1;

	@OriginalMember(owner = "client!x", name = "H", descriptor = "I")
	public int primarySeq = -1;

	@OriginalMember(owner = "client!x", name = "M", descriptor = "I")
	public int spotAnimIndex = -1;

	@OriginalMember(owner = "client!x", name = "cb", descriptor = "[I")
	public final int[] pathTileX = new int[10];

	@OriginalMember(owner = "client!x", name = "db", descriptor = "[I")
	public final int[] pathTileZ = new int[10];

	@OriginalMember(owner = "client!x", name = "eb", descriptor = "[Z")
	public final boolean[] pathRunning = new boolean[10];

	@OriginalMember(owner = "client!x", name = "a", descriptor = "(ZZII)V")
	public final void move(@OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (this.primarySeq != -1 && SeqType.instances[this.primarySeq].priority <= 1) {
			this.primarySeq = -1;
		}

		if (!arg1) {
			@Pc(22) int local22 = arg2 - this.pathTileX[0];
			@Pc(29) int local29 = arg3 - this.pathTileZ[0];
			if (local22 >= -8 && local22 <= 8 && local29 >= -8 && local29 <= 8) {
				if (this.pathRemaining < 9) {
					this.pathRemaining++;
				}
				for (@Pc(54) int local54 = this.pathRemaining; local54 > 0; local54--) {
					this.pathTileX[local54] = this.pathTileX[local54 - 1];
					this.pathTileZ[local54] = this.pathTileZ[local54 - 1];
					this.pathRunning[local54] = this.pathRunning[local54 - 1];
				}
				this.pathTileX[0] = arg2;
				this.pathTileZ[0] = arg3;
				this.pathRunning[0] = false;
				return;
			}
		}

		this.pathRemaining = 0;
		this.int1 = 0;
		this.pathTileX[0] = arg2;
		this.pathTileZ[0] = arg3;
		this.x = this.pathTileX[0] * 128 + this.index * 64;
		this.z = this.pathTileZ[0] * 128 + this.index * 64;
	}

	@OriginalMember(owner = "client!x", name = "a", descriptor = "(ZIB)V")
	public final void walk(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1) {
		@Pc(6) int local6 = this.pathTileX[0];
		@Pc(11) int local11 = this.pathTileZ[0];
		if (arg1 == 0) {
			local6--;
			local11++;
		}
		if (arg1 == 1) {
			local11++;
		}
		if (arg1 == 2) {
			local6++;
			local11++;
		}
		if (arg1 == 3) {
			local6--;
		}
		if (arg1 == 4) {
			local6++;
		}
		if (arg1 == 5) {
			local6--;
			local11--;
		}
		if (arg1 == 6) {
			local11--;
		}
		if (arg1 == 7) {
			local6++;
			local11--;
		}
		if (this.primarySeq != -1 && SeqType.instances[this.primarySeq].priority <= 1) {
			this.primarySeq = -1;
		}
		if (this.pathRemaining < 9) {
			this.pathRemaining++;
		}
		for (@Pc(83) int local83 = this.pathRemaining; local83 > 0; local83--) {
			this.pathTileX[local83] = this.pathTileX[local83 - 1];
			this.pathTileZ[local83] = this.pathTileZ[local83 - 1];
			this.pathRunning[local83] = this.pathRunning[local83 - 1];
		}
		this.pathTileX[0] = local6;
		this.pathTileZ[0] = local11;
		this.pathRunning[0] = arg0;
	}

	@OriginalMember(owner = "client!x", name = "b", descriptor = "(Z)Z")
	public boolean isValid() {
		return false;
	}
}
