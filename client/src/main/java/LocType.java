import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ac")
public final class LocType {

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "I")
	private static int flowObfuscator3;

	@OriginalMember(owner = "client!ac", name = "b", descriptor = "I")
	private static int flowObfuscator1;

	@OriginalMember(owner = "client!ac", name = "c", descriptor = "Z")
	private static boolean flowObfuscator2;

	@OriginalMember(owner = "client!ac", name = "d", descriptor = "I")
	private static int count;

	@OriginalMember(owner = "client!ac", name = "e", descriptor = "[I")
	private static int[] offsets;

	@OriginalMember(owner = "client!ac", name = "f", descriptor = "Lclient!kb;")
	private static Buffer dat;

	@OriginalMember(owner = "client!ac", name = "g", descriptor = "[Lclient!ac;")
	private static LocType[] cache;

	@OriginalMember(owner = "client!ac", name = "h", descriptor = "I")
	private static int offset;

	@OriginalMember(owner = "client!ac", name = "P", descriptor = "Lclient!s;")
	public static Cache models = new Cache((byte) 0, 500);

	@OriginalMember(owner = "client!ac", name = "Q", descriptor = "Lclient!s;")
	public static Cache builtModels = new Cache((byte) 0, 30);

	@OriginalMember(owner = "client!ac", name = "j", descriptor = "[I")
	private int[] modelIndices;

	@OriginalMember(owner = "client!ac", name = "k", descriptor = "[I")
	private int[] modelTypes;

	@OriginalMember(owner = "client!ac", name = "l", descriptor = "Ljava/lang/String;")
	public String name;

	@OriginalMember(owner = "client!ac", name = "m", descriptor = "[B")
	public byte[] description;

	@OriginalMember(owner = "client!ac", name = "n", descriptor = "[I")
	private int[] oldColors;

	@OriginalMember(owner = "client!ac", name = "o", descriptor = "[I")
	private int[] newColors;

	@OriginalMember(owner = "client!ac", name = "p", descriptor = "I")
	public int sizeX;

	@OriginalMember(owner = "client!ac", name = "q", descriptor = "I")
	public int sizeZ;

	@OriginalMember(owner = "client!ac", name = "r", descriptor = "Z")
	public boolean hasCollision;

	@OriginalMember(owner = "client!ac", name = "s", descriptor = "Z")
	public boolean isSolid;

	@OriginalMember(owner = "client!ac", name = "t", descriptor = "Z")
	public boolean interactable;

	@OriginalMember(owner = "client!ac", name = "u", descriptor = "Z")
	private boolean adjustToTerrain;

	@OriginalMember(owner = "client!ac", name = "v", descriptor = "Z")
	private boolean flatShaded;

	@OriginalMember(owner = "client!ac", name = "w", descriptor = "Z")
	public boolean culls;

	@OriginalMember(owner = "client!ac", name = "x", descriptor = "I")
	public int animationIndex;

	@OriginalMember(owner = "client!ac", name = "y", descriptor = "I")
	public int thickness;

	@OriginalMember(owner = "client!ac", name = "z", descriptor = "B")
	private byte brightness;

	@OriginalMember(owner = "client!ac", name = "A", descriptor = "B")
	private byte specular;

	@OriginalMember(owner = "client!ac", name = "B", descriptor = "[Ljava/lang/String;")
	public String[] actions;

	@OriginalMember(owner = "client!ac", name = "C", descriptor = "Z")
	private boolean disposeAlpha;

	@OriginalMember(owner = "client!ac", name = "D", descriptor = "I")
	public int mapfunction;

	@OriginalMember(owner = "client!ac", name = "E", descriptor = "I")
	public int mapscene;

	@OriginalMember(owner = "client!ac", name = "F", descriptor = "Z")
	private boolean rotateCounterClockwise;

	@OriginalMember(owner = "client!ac", name = "G", descriptor = "Z")
	public boolean hasShadow;

	@OriginalMember(owner = "client!ac", name = "H", descriptor = "I")
	private int scaleX;

	@OriginalMember(owner = "client!ac", name = "I", descriptor = "I")
	private int scaleY;

	@OriginalMember(owner = "client!ac", name = "J", descriptor = "I")
	private int scaleZ;

	@OriginalMember(owner = "client!ac", name = "K", descriptor = "I")
	private int translateX;

	@OriginalMember(owner = "client!ac", name = "L", descriptor = "I")
	private int translateY;

	@OriginalMember(owner = "client!ac", name = "M", descriptor = "I")
	private int translateZ;

	@OriginalMember(owner = "client!ac", name = "N", descriptor = "I")
	public int interactionSideFlags;

	@OriginalMember(owner = "client!ac", name = "O", descriptor = "Z")
	public boolean aBoolean16;

	@OriginalMember(owner = "client!ac", name = "i", descriptor = "I")
	private int id = -1;

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Lclient!ub;)V")
	public static void decode(@OriginalArg(0) FileArchive archive) {
		dat = new Buffer(363, archive.read("loc.dat", null));
		@Pc(21) Buffer idx = new Buffer(363, archive.read("loc.idx", null));
		count = idx.g2();
		offsets = new int[count];
		@Pc(29) int offset = 2;
		for (@Pc(31) int i = 0; i < count; i++) {
			offsets[i] = offset;
			offset += idx.g2();
		}
		cache = new LocType[10];
		for (@Pc(51) int i = 0; i < 10; i++) {
			cache[i] = new LocType();
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Z)V")
	public static void unload() {
		models = null;
		builtModels = null;
		offsets = null;
		cache = null;
		dat = null;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(I)Lclient!ac;")
	public static LocType get(@OriginalArg(0) int id) {
		for (@Pc(1) int i = 0; i < 10; i++) {
			if (cache[i].id == id) {
				return cache[i];
			}
		}
		offset = (offset + 1) % 10;
		@Pc(27) LocType type = cache[offset];
		dat.offset = offsets[id];
		type.id = id;
		type.reset();
		type.decode(dat);
		return type;
	}

	@OriginalMember(owner = "client!ac", name = "<init>", descriptor = "()V")
	private LocType() {
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "()V")
	private void reset() {
		this.modelIndices = null;
		this.modelTypes = null;
		this.name = null;
		this.description = null;
		this.oldColors = null;
		this.newColors = null;
		this.sizeX = 1;
		this.sizeZ = 1;
		this.hasCollision = true;
		this.isSolid = true;
		this.interactable = false;
		this.adjustToTerrain = false;
		this.flatShaded = false;
		this.culls = false;
		this.animationIndex = -1;
		this.thickness = 16;
		this.brightness = 0;
		this.specular = 0;
		this.actions = null;
		this.disposeAlpha = false;
		this.mapfunction = -1;
		this.mapscene = -1;
		this.rotateCounterClockwise = false;
		this.hasShadow = true;
		this.scaleX = 128;
		this.scaleY = 128;
		this.scaleZ = 128;
		this.interactionSideFlags = 0;
		this.translateX = 0;
		this.translateY = 0;
		this.translateZ = 0;
		this.aBoolean16 = false;
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(ZLclient!kb;)V")
	private void decode(@OriginalArg(1) Buffer buffer) {
		@Pc(12) int interactive = -1;
		while (true) {
			while (true) {
				@Pc(15) int opcode = buffer.g1();
				if (opcode == 0) {
					if (this.modelTypes == null) {
						this.modelTypes = new int[0];
					}
					if (interactive == -1) {
						this.interactable = false;
						if (this.modelTypes.length > 0 && this.modelTypes[0] == 10) {
							this.interactable = true;
						}
						if (this.actions != null) {
							this.interactable = true;
							return;
						}
					}
					return;
				}
				@Pc(23) int count;
				@Pc(33) int i;
				if (opcode == 1) {
					count = buffer.g1();
					this.modelTypes = new int[count];
					this.modelIndices = new int[count];
					for (i = 0; i < count; i++) {
						this.modelIndices[i] = buffer.g2();
						this.modelTypes[i] = buffer.g1();
					}
				} else if (opcode == 2) {
					this.name = buffer.gjstr();
				} else if (opcode == 3) {
					this.description = buffer.gjstrBytes();
				} else if (opcode == 14) {
					this.sizeX = buffer.g1();
				} else if (opcode == 15) {
					this.sizeZ = buffer.g1();
				} else if (opcode == 17) {
					this.hasCollision = false;
				} else if (opcode == 18) {
					this.isSolid = false;
				} else if (opcode == 19) {
					interactive = buffer.g1();
					if (interactive == 1) {
						this.interactable = true;
					}
				} else if (opcode == 21) {
					this.adjustToTerrain = true;
				} else if (opcode == 22) {
					this.flatShaded = true;
				} else if (opcode == 23) {
					this.culls = true;
				} else if (opcode == 24) {
					this.animationIndex = buffer.g2();
					if (this.animationIndex == 65535) {
						this.animationIndex = -1;
					}
				} else if (opcode == 25) {
					this.disposeAlpha = true;
				} else if (opcode == 28) {
					this.thickness = buffer.g1();
				} else if (opcode == 29) {
					this.brightness = buffer.g1s();
				} else if (opcode == 39) {
					this.specular = buffer.g1s();
				} else if (opcode >= 30 && opcode < 39) {
					if (this.actions == null) {
						this.actions = new String[5];
					}
					this.actions[opcode - 30] = buffer.gjstr();
					if (this.actions[opcode - 30].equalsIgnoreCase("hidden")) {
						this.actions[opcode - 30] = null;
					}
				} else if (opcode == 40) {
					count = buffer.g1();
					this.oldColors = new int[count];
					this.newColors = new int[count];
					for (i = 0; i < count; i++) {
						this.oldColors[i] = buffer.g2();
						this.newColors[i] = buffer.g2();
					}
				} else if (opcode == 60) {
					this.mapfunction = buffer.g2();
				} else if (opcode == 62) {
					this.rotateCounterClockwise = true;
				} else if (opcode == 64) {
					this.hasShadow = false;
				} else if (opcode == 65) {
					this.scaleX = buffer.g2();
				} else if (opcode == 66) {
					this.scaleY = buffer.g2();
				} else if (opcode == 67) {
					this.scaleZ = buffer.g2();
				} else if (opcode == 68) {
					this.mapscene = buffer.g2();
				} else if (opcode == 69) {
					this.interactionSideFlags = buffer.g1();
				} else if (opcode == 70) {
					this.translateX = buffer.g2s();
				} else if (opcode == 71) {
					this.translateY = buffer.g2s();
				} else if (opcode == 72) {
					this.translateZ = buffer.g2s();
				} else if (opcode == 73) {
					this.aBoolean16 = true;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIIIIII)Lclient!eb;")
	public final Model getModel(@OriginalArg(0) int type, @OriginalArg(1) int orientation, @OriginalArg(2) int southwestY, @OriginalArg(3) int southeastY, @OriginalArg(4) int northwestY, @OriginalArg(5) int northeastY, @OriginalArg(6) int seqFrame) {
		@Pc(3) int modelType = -1;
		for (@Pc(5) int i = 0; i < this.modelTypes.length; i++) {
			if (this.modelTypes[i] == type) {
				modelType = i;
				break;
			}
		}
		if (modelType == -1) {
			return null;
		}
		@Pc(47) long key = (long) ((this.id << 6) + (modelType << 3) + orientation) + ((long) (seqFrame + 1) << 32);
		if (flowObfuscator2) {
			key = 0L;
		}
		@Pc(56) Model m = (Model) builtModels.get(key);
		@Pc(91) int modelIndex;
		@Pc(141) int n;
		if (m == null) {
			if (modelType >= this.modelIndices.length) {
				return null;
			}
			modelIndex = this.modelIndices[modelType];
			if (modelIndex == -1) {
				return null;
			}
			@Pc(188) boolean flipBackwards = this.rotateCounterClockwise ^ orientation > 3;
			if (flipBackwards) {
				modelIndex += 65536;
			}
			@Pc(200) Model m2 = (Model) models.get((long) modelIndex);
			if (m2 == null) {
				m2 = new Model(false, modelIndex & 0xFFFF);
				if (flipBackwards) {
					m2.flipBackwards();
				}
				models.put((long) modelIndex, m2);
			}
			@Pc(235) boolean rescale;
			if (this.scaleX == 128 && this.scaleY == 128 && this.scaleZ == 128) {
				rescale = false;
			} else {
				rescale = true;
			}
			@Pc(250) boolean move;
			if (this.translateX == 0 && this.translateY == 0 && this.translateZ == 0) {
				move = false;
			} else {
				move = true;
			}
			@Pc(284) Model m3 = new Model(m2, this.oldColors == null, !this.disposeAlpha, flowObfuscator3, orientation == 0 && seqFrame == -1 && !rescale && !move);
			if (seqFrame != -1) {
				m3.applyGroup();
				m3.applyFrame(seqFrame);
				m3.skinTriangle = null;
				m3.labelVertices = null;
			}
			while (orientation-- > 0) {
				m3.rotateCounterClockwise();
			}
			if (this.oldColors != null) {
				for (n = 0; n < this.oldColors.length; n++) {
					m3.recolor(this.oldColors[n], this.newColors[n]);
				}
			}
			if (rescale) {
				m3.scale(this.scaleZ, this.scaleY, this.scaleX);
			}
			if (move) {
				m3.translate(this.translateY, this.translateX, this.translateZ);
			}
			m3.applyLighting(this.brightness + 64, this.specular * 5 + 768, -50, -10, -50, !this.flatShaded);
			if (this.hasCollision) {
				m3.anInt372 = m3.maxBoundY;
			}
			builtModels.put(key, m3);
			if (this.adjustToTerrain || this.flatShaded) {
				m3 = new Model(m3, (byte) -31, this.adjustToTerrain, this.flatShaded);
			}
			if (this.adjustToTerrain) {
				n = (southwestY + southeastY + northwestY + northeastY) / 4;
				for (@Pc(417) int v = 0; v < m3.vertexCount; v++) {
					@Pc(424) int x = m3.vertexX[v];
					@Pc(429) int z = m3.vertexZ[v];
					@Pc(441) int averageY1 = southwestY + (southeastY - southwestY) * (x + 64) / 128;
					@Pc(453) int averageY2 = northeastY + (northwestY - northeastY) * (x + 64) / 128;
					@Pc(465) int average = averageY1 + (averageY2 - averageY1) * (z + 64) / 128;
					m3.vertexY[v] += average - n;
				}
				m3.calculateYBoundaries2(flowObfuscator1);
			}
			return m3;
		} else if (flowObfuscator2) {
			return m;
		} else {
			if (this.adjustToTerrain || this.flatShaded) {
				m = new Model(m, (byte) -31, this.adjustToTerrain, this.flatShaded);
			}
			if (this.adjustToTerrain) {
				modelIndex = (southwestY + southeastY + northwestY + northeastY) / 4;
				for (@Pc(93) int v = 0; v < m.vertexCount; v++) {
					@Pc(100) int x = m.vertexX[v];
					@Pc(105) int z = m.vertexZ[v];
					@Pc(117) int averageY1 = southwestY + (southeastY - southwestY) * (x + 64) / 128;
					@Pc(129) int averageY2 = northeastY + (northwestY - northeastY) * (x + 64) / 128;
					n = averageY1 + (averageY2 - averageY1) * (z + 64) / 128;
					m.vertexY[v] += n - modelIndex;
				}
				m.calculateYBoundaries2(flowObfuscator1);
			}
			return m;
		}
	}
}
