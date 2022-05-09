import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!db")
public class CacheableNode extends Node {

	@OriginalMember(owner = "client!db", name = "e", descriptor = "Lclient!db;")
	public CacheableNode aCacheableNode_15;

	@OriginalMember(owner = "client!db", name = "f", descriptor = "Lclient!db;")
	public CacheableNode aCacheableNode_16;

	@OriginalMember(owner = "client!db", name = "b", descriptor = "()V")
	public final void method377() {
		if (this.aCacheableNode_16 != null) {
			this.aCacheableNode_16.aCacheableNode_15 = this.aCacheableNode_15;
			this.aCacheableNode_15.aCacheableNode_16 = this.aCacheableNode_16;
			this.aCacheableNode_15 = null;
			this.aCacheableNode_16 = null;
		}
	}
}
