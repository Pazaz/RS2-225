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
	public TileUnderlay(@OriginalArg(0) int swColor, @OriginalArg(1) int seColor, @OriginalArg(2) int neColor, @OriginalArg(3) int nwColor, @OriginalArg(4) int textureIndex, @OriginalArg(5) int rgb, @OriginalArg(6) boolean isFlat) {
		this.swColor = swColor;
		this.seColor = seColor;
		this.neColor = neColor;
		this.nwColor = nwColor;
		this.textureIndex = textureIndex;
		this.rgb = rgb;
		this.isFlat = isFlat;
	}
}
