import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!o")
public final class TileUnderlay {

	@OriginalMember(owner = "client!o", name = "f", descriptor = "Z")
	public boolean isFlat = true;

	@OriginalMember(owner = "client!o", name = "a", descriptor = "I")
	public final int swColor;

	@OriginalMember(owner = "client!o", name = "b", descriptor = "I")
	public final int seColor;

	@OriginalMember(owner = "client!o", name = "c", descriptor = "I")
	public final int neColor;

	@OriginalMember(owner = "client!o", name = "d", descriptor = "I")
	public final int nwColor;

	@OriginalMember(owner = "client!o", name = "e", descriptor = "I")
	public final int textureIndex;

	@OriginalMember(owner = "client!o", name = "g", descriptor = "I")
	public final int rgb;

	@OriginalMember(owner = "client!o", name = "<init>", descriptor = "(IIIIIIZ)V")
	public TileUnderlay(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) boolean arg6) {
		this.swColor = arg0;
		this.seColor = arg1;
		this.neColor = arg2;
		this.nwColor = arg3;
		this.textureIndex = arg4;
		this.rgb = arg5;
		this.isFlat = arg6;
	}
}
