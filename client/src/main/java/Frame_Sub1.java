import java.awt.Frame;
import java.awt.Graphics;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!b")
public final class Frame_Sub1 extends Frame {

	@OriginalMember(owner = "client!b", name = "a", descriptor = "I")
	private final int anInt56 = 8;

	@OriginalMember(owner = "client!b", name = "b", descriptor = "Lclient!a;")
	private final Applet_Sub1 anApplet_Sub1_1;

	@OriginalMember(owner = "client!b", name = "<init>", descriptor = "(IILclient!a;I)V")
	public Frame_Sub1(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Applet_Sub1 arg2, @OriginalArg(3) int arg3) {
		this.anApplet_Sub1_1 = arg2;
		this.setTitle("Jagex");
		this.setResizable(false);
		this.show();
		this.toFront();
		this.resize(arg3 + 8, arg0 + 28);
	}

	@OriginalMember(owner = "client!b", name = "getGraphics", descriptor = "()Ljava/awt/Graphics;")
	@Override
	public final Graphics getGraphics() {
		@Pc(2) Graphics local2 = super.getGraphics();
		local2.translate(4, 24);
		return local2;
	}

	@OriginalMember(owner = "client!b", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics arg0) {
		this.anApplet_Sub1_1.update(arg0);
	}

	@OriginalMember(owner = "client!b", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void paint(@OriginalArg(0) Graphics arg0) {
		this.anApplet_Sub1_1.paint(arg0);
	}
}
