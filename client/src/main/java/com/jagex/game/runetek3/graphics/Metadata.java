package com.jagex.game.runetek3.graphics;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!l")
public class Metadata {

	@OriginalMember(owner = "client!l", name = "a", descriptor = "I")
	public int vertexCount;

	@OriginalMember(owner = "client!l", name = "b", descriptor = "I")
	public int triangleCount;

	@OriginalMember(owner = "client!l", name = "c", descriptor = "I")
	public int texturedCount;

	@OriginalMember(owner = "client!l", name = "d", descriptor = "I")
	public int vertexFlagDataOffset;

	@OriginalMember(owner = "client!l", name = "e", descriptor = "I")
	public int vertexXDataOffset;

	@OriginalMember(owner = "client!l", name = "f", descriptor = "I")
	public int vertexYDataOffset;

	@OriginalMember(owner = "client!l", name = "g", descriptor = "I")
	public int vertexZDataOffset;

	@OriginalMember(owner = "client!l", name = "h", descriptor = "I")
	public int vertexLabelDataOffset;

	@OriginalMember(owner = "client!l", name = "i", descriptor = "I")
	public int vertexIndexDataOffset;

	@OriginalMember(owner = "client!l", name = "j", descriptor = "I")
	public int triangleTypeDataOffset;

	@OriginalMember(owner = "client!l", name = "k", descriptor = "I")
	public int triangleColorDataOffset;

	@OriginalMember(owner = "client!l", name = "l", descriptor = "I")
	public int triangleInfoDataOffset;

	@OriginalMember(owner = "client!l", name = "m", descriptor = "I")
	public int trianglePriorityDataOffset;

	@OriginalMember(owner = "client!l", name = "n", descriptor = "I")
	public int triangleAlphaDataOffset;

	@OriginalMember(owner = "client!l", name = "o", descriptor = "I")
	public int triangleSkinDataOffset;

	@OriginalMember(owner = "client!l", name = "p", descriptor = "I")
	public int triangleTextureDataOffset;
}
