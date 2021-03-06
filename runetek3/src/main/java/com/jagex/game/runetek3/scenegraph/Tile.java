package com.jagex.game.runetek3.scenegraph;

import com.jagex.core.utils.Node;
import com.jagex.game.runetek3.entity.ObjEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!cb")
public class Tile extends Node {

	@OriginalMember(owner = "client!cb", name = "i", descriptor = "Lclient!o;")
	public TileUnderlay underlay;

	@OriginalMember(owner = "client!cb", name = "j", descriptor = "Lclient!i;")
	public TileOverlay overlay;

	@OriginalMember(owner = "client!cb", name = "k", descriptor = "Lclient!q;")
	public Wall wall;

	@OriginalMember(owner = "client!cb", name = "l", descriptor = "Lclient!h;")
	public WallDecoration wallDecoration;

	@OriginalMember(owner = "client!cb", name = "m", descriptor = "Lclient!j;")
	public GroundDecoration groundDecoration;

	@OriginalMember(owner = "client!cb", name = "n", descriptor = "Lclient!k;")
	public ObjEntity objEntity;

	@OriginalMember(owner = "client!cb", name = "o", descriptor = "I")
	public int locationCount;

	@OriginalMember(owner = "client!cb", name = "r", descriptor = "I")
	public int flags;

	@OriginalMember(owner = "client!cb", name = "s", descriptor = "I")
	public int physicalLevel;

	@OriginalMember(owner = "client!cb", name = "t", descriptor = "Z")
	public boolean draw;

	@OriginalMember(owner = "client!cb", name = "u", descriptor = "Z")
	public boolean isVisible;

	@OriginalMember(owner = "client!cb", name = "v", descriptor = "Z")
	public boolean drawLocs;

	@OriginalMember(owner = "client!cb", name = "w", descriptor = "I")
	public int wallCullDirection;

	@OriginalMember(owner = "client!cb", name = "x", descriptor = "I")
	public int wallUncullDirection;

	@OriginalMember(owner = "client!cb", name = "y", descriptor = "I")
	public int wallCullOppositeDirection;

	@OriginalMember(owner = "client!cb", name = "z", descriptor = "I")
	public int wallDrawFlags;

	@OriginalMember(owner = "client!cb", name = "A", descriptor = "Lclient!cb;")
	public Tile bridge;

	@OriginalMember(owner = "client!cb", name = "p", descriptor = "[Lclient!p;")
	public final Loc[] locs = new Loc[5];

	@OriginalMember(owner = "client!cb", name = "q", descriptor = "[I")
	public final int[] locFlags = new int[5];

	@OriginalMember(owner = "client!cb", name = "e", descriptor = "I")
	public int level;

	@OriginalMember(owner = "client!cb", name = "h", descriptor = "I")
	public final int renderLevel;

	@OriginalMember(owner = "client!cb", name = "f", descriptor = "I")
	public final int x;

	@OriginalMember(owner = "client!cb", name = "g", descriptor = "I")
	public final int z;

	@OriginalMember(owner = "client!cb", name = "<init>", descriptor = "(III)V")
	public Tile(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		this.renderLevel = this.level = arg0;
		this.x = arg1;
		this.z = arg2;
	}
}
