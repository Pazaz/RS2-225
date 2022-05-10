import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ec")
public final class CollisionMap {

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "I")
	private int flowObfuscator1;

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "Z")
	private final boolean flowObfuscator2 = false;

	@OriginalMember(owner = "client!ec", name = "c", descriptor = "B")
	private final byte flowObfuscator3 = 12;

	@OriginalMember(owner = "client!ec", name = "d", descriptor = "I")
	private final int flowObfuscator4 = 27808;

	@OriginalMember(owner = "client!ec", name = "e", descriptor = "Z")
	private final boolean flowObfuscator5 = true;

	@OriginalMember(owner = "client!ec", name = "f", descriptor = "I")
	private final int flowObfuscator6 = 0;

	@OriginalMember(owner = "client!ec", name = "g", descriptor = "I")
	private final int flowObfuscator7 = 0;

	@OriginalMember(owner = "client!ec", name = "h", descriptor = "I")
	private final int x;

	@OriginalMember(owner = "client!ec", name = "i", descriptor = "I")
	private final int z;

	@OriginalMember(owner = "client!ec", name = "j", descriptor = "[[I")
	public final int[][] flags;

	@OriginalMember(owner = "client!ec", name = "<init>", descriptor = "(III)V")
	public CollisionMap(@OriginalArg(0) int x, @OriginalArg(1) int obfuscator, @OriginalArg(2) int z) {
		this.x = x;
		this.z = z;
		this.flags = new int[this.x][this.z];
		this.reset();
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(B)V")
	public final void reset() {
		for (@Pc(3) int x = 0; x < this.x; x++) {
			for (@Pc(7) int z = 0; z < this.z; z++) {
				if (x == 0 || z == 0 || x == this.x - 1 || z == this.z - 1) {
					this.flags[x][z] = 16777215;
				} else {
					this.flags[x][z] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIZI)V")
	public final void setWall(@OriginalArg(1) int orientation, @OriginalArg(2) int zOffset, @OriginalArg(3) int xOffset, @OriginalArg(4) boolean blocks, @OriginalArg(5) int type) {
		@Pc(4) int x = xOffset - this.flowObfuscator6;
		@Pc(19) int z = zOffset - this.flowObfuscator7;
		if (type == 0) {
			if (orientation == 0) {
				this.add(x, z, 128);
				this.add(x - 1, z, 8);
			}
			if (orientation == 1) {
				this.add(x, z, 2);
				this.add(x, z + 1, 32);
			}
			if (orientation == 2) {
				this.add(x, z, 8);
				this.add(x + 1, z, 128);
			}
			if (orientation == 3) {
				this.add(x, z, 32);
				this.add(x, z - 1, 2);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.add(x, z, 1);
				this.add(x - 1, z + 1, 16);
			}
			if (orientation == 1) {
				this.add(x, z, 4);
				this.add(x + 1, z + 1, 64);
			}
			if (orientation == 2) {
				this.add(x, z, 16);
				this.add(x + 1, z - 1, 1);
			}
			if (orientation == 3) {
				this.add(x, z, 64);
				this.add(x - 1, z - 1, 4);
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				this.add(x, z, 130);
				this.add(x - 1, z, 8);
				this.add(x, z + 1, 32);
			}
			if (orientation == 1) {
				this.add(x, z, 10);
				this.add(x, z + 1, 32);
				this.add(x + 1, z, 128);
			}
			if (orientation == 2) {
				this.add(x, z, 40);
				this.add(x + 1, z, 128);
				this.add(x, z - 1, 2);
			}
			if (orientation == 3) {
				this.add(x, z, 160);
				this.add(x, z - 1, 2);
				this.add(x - 1, z, 8);
			}
		}
		if (!blocks) {
			return;
		}
		if (type == 0) {
			if (orientation == 0) {
				this.add(x, z, 65536);
				this.add(x - 1, z, 4096);
			}
			if (orientation == 1) {
				this.add(x, z, 1024);
				this.add(x, z + 1, 16384);
			}
			if (orientation == 2) {
				this.add(x, z, 4096);
				this.add(x + 1, z, 65536);
			}
			if (orientation == 3) {
				this.add(x, z, 16384);
				this.add(x, z - 1, 1024);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.add(x, z, 512);
				this.add(x - 1, z + 1, 8192);
			}
			if (orientation == 1) {
				this.add(x, z, 2048);
				this.add(x + 1, z + 1, 32768);
			}
			if (orientation == 2) {
				this.add(x, z, 8192);
				this.add(x + 1, z - 1, 512);
			}
			if (orientation == 3) {
				this.add(x, z, 32768);
				this.add(x - 1, z - 1, 2048);
			}
		}
		if (type != 2) {
			return;
		}
		if (orientation == 0) {
			this.add(x, z, 66560);
			this.add(x - 1, z, 4096);
			this.add(x, z + 1, 16384);
		}
		if (orientation == 1) {
			this.add(x, z, 5120);
			this.add(x, z + 1, 16384);
			this.add(x + 1, z, 65536);
		}
		if (orientation == 2) {
			this.add(x, z, 20480);
			this.add(x + 1, z, 65536);
			this.add(x, z - 1, 1024);
		}
		if (orientation == 3) {
			this.add(x, z, 81920);
			this.add(x, z - 1, 1024);
			this.add(x - 1, z, 4096);
			return;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIZ)V")
	public final void setLoc(@OriginalArg(0) int orientation, @OriginalArg(1) int sizeZ, @OriginalArg(2) int sizeX, @OriginalArg(3) int xOffset, @OriginalArg(5) int zOffset, @OriginalArg(6) boolean solid) {
		@Pc(3) int flag = 256;
		if (solid) {
			flag = 131328;
		}
		@Pc(14) int initialX = xOffset - this.flowObfuscator6;
		@Pc(19) int initialZ = zOffset - this.flowObfuscator7;
		@Pc(27) int temp;
		if (orientation == 1 || orientation == 3) {
			temp = sizeX;
			sizeX = sizeZ;
			sizeZ = temp;
		}
		for (temp = initialX; temp < initialX + sizeX; temp++) {
			if (temp >= 0 && temp < this.x) {
				for (@Pc(43) int y = initialZ; y < initialZ + sizeZ; y++) {
					if (y >= 0 && y < this.z) {
						this.add(temp, y, flag);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(BII)V")
	public final void setBlocoked(@OriginalArg(1) int zOffset, @OriginalArg(2) int xOffset) {
		if (this.flowObfuscator3 == 12) {
			@Pc(9) int x = xOffset - this.flowObfuscator6;
			@Pc(14) int z = zOffset - this.flowObfuscator7;
			this.flags[x][z] |= 0x200000;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(III)V")
	private void add(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(2) int flag) {
		this.flags[x][z] |= flag;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIII)V")
	public final void removeWall(@OriginalArg(0) boolean blocks, @OriginalArg(1) int orientation, @OriginalArg(2) int xOffset, @OriginalArg(3) int zOffset, @OriginalArg(5) int type) {
		@Pc(8) int x = xOffset - this.flowObfuscator6;
		@Pc(13) int z = zOffset - this.flowObfuscator7;
		if (type == 0) {
			if (orientation == 0) {
				this.remove(z, x, 128);
				this.remove(z, x - 1, 8);
			}
			if (orientation == 1) {
				this.remove(z, x, 2);
				this.remove(z + 1, x, 32);
			}
			if (orientation == 2) {
				this.remove(z, x, 8);
				this.remove(z, x + 1, 128);
			}
			if (orientation == 3) {
				this.remove(z, x, 32);
				this.remove(z - 1, x, 2);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.remove(z, x, 1);
				this.remove(z + 1, x - 1, 16);
			}
			if (orientation == 1) {
				this.remove(z, x, 4);
				this.remove(z + 1, x + 1, 64);
			}
			if (orientation == 2) {
				this.remove(z, x, 16);
				this.remove(z - 1, x + 1, 1);
			}
			if (orientation == 3) {
				this.remove(z, x, 64);
				this.remove(z - 1, x - 1, 4);
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				this.remove(z, x, 130);
				this.remove(z, x - 1, 8);
				this.remove(z + 1, x, 32);
			}
			if (orientation == 1) {
				this.remove(z, x, 10);
				this.remove(z + 1, x, 32);
				this.remove(z, x + 1, 128);
			}
			if (orientation == 2) {
				this.remove(z, x, 40);
				this.remove(z, x + 1, 128);
				this.remove(z - 1, x, 2);
			}
			if (orientation == 3) {
				this.remove(z, x, 160);
				this.remove(z - 1, x, 2);
				this.remove(z, x - 1, 8);
			}
		}
		if (!blocks) {
			return;
		}
		if (type == 0) {
			if (orientation == 0) {
				this.remove(z, x, 65536);
				this.remove(z, x - 1, 4096);
			}
			if (orientation == 1) {
				this.remove(z, x, 1024);
				this.remove(z + 1, x, 16384);
			}
			if (orientation == 2) {
				this.remove(z, x, 4096);
				this.remove(z, x + 1, 65536);
			}
			if (orientation == 3) {
				this.remove(z, x, 16384);
				this.remove(z - 1, x, 1024);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.remove(z, x, 512);
				this.remove(z + 1, x - 1, 8192);
			}
			if (orientation == 1) {
				this.remove(z, x, 2048);
				this.remove(z + 1, x + 1, 32768);
			}
			if (orientation == 2) {
				this.remove(z, x, 8192);
				this.remove(z - 1, x + 1, 512);
			}
			if (orientation == 3) {
				this.remove(z, x, 32768);
				this.remove(z - 1, x - 1, 2048);
			}
		}
		if (type != 2) {
			return;
		}
		if (orientation == 0) {
			this.remove(z, x, 66560);
			this.remove(z, x - 1, 4096);
			this.remove(z + 1, x, 16384);
		}
		if (orientation == 1) {
			this.remove(z, x, 5120);
			this.remove(z + 1, x, 16384);
			this.remove(z, x + 1, 65536);
		}
		if (orientation == 2) {
			this.remove(z, x, 20480);
			this.remove(z, x + 1, 65536);
			this.remove(z - 1, x, 1024);
		}
		if (orientation == 3) {
			this.remove(z, x, 81920);
			this.remove(z - 1, x, 1024);
			this.remove(z, x - 1, 4096);
			return;
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIZZI)V")
	public final void removeLoc(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset, @OriginalArg(2) int orientation, @OriginalArg(3) int sizeX, @OriginalArg(5) boolean solid, @OriginalArg(6) int sizeZ) {
		@Pc(3) int flag = 256;
		if (solid) {
			flag = 131328;
		}
		@Pc(14) int startX = xOffset - this.flowObfuscator6;
		@Pc(19) int startZ = zOffset - this.flowObfuscator7;
		@Pc(32) int temp;
		if (orientation == 1 || orientation == 3) {
			temp = sizeX;
			sizeX = sizeZ;
			sizeZ = temp;
		}
		for (temp = startX; temp < startX + sizeX; temp++) {
			if (temp >= 0 && temp < this.x) {
				for (@Pc(48) int z = startZ; z < startZ + sizeZ; z++) {
					if (z >= 0 && z < this.z) {
						this.remove(z, temp, flag);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IBII)V")
	private void remove(@OriginalArg(0) int z, @OriginalArg(2) int x, @OriginalArg(3) int flag) {
		this.flags[x][z] &= 16777215 - flag;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(III)V")
	public final void removeBlock(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset) {
		@Pc(7) int x = xOffset - this.flowObfuscator6;
		@Pc(12) int z = zOffset - this.flowObfuscator7;
		this.flags[x][z] &= 0xDFFFFF;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIII)Z")
	public final boolean reachedWall(@OriginalArg(1) int orientation, @OriginalArg(2) int finalZOffset, @OriginalArg(3) int type, @OriginalArg(4) int initialZOffset, @OriginalArg(5) int finalXOffset, @OriginalArg(6) int initialXOffset) {
		if (initialXOffset == finalXOffset && initialZOffset == finalZOffset) {
			return true;
		}
		@Pc(12) int initialX = initialXOffset - this.flowObfuscator6;
		@Pc(17) int initialZ = initialZOffset - this.flowObfuscator7;
		@Pc(29) int finalX = finalXOffset - this.flowObfuscator6;
		@Pc(34) int finalZ = finalZOffset - this.flowObfuscator7;
		if (type == 0) {
			if (orientation == 0) {
				if (initialX == finalX - 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x280120) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x280102) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (initialX == finalX && initialZ == finalZ + 1) {
					return true;
				}
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280108) == 0) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280180) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (initialX == finalX + 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x280120) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x280102) == 0) {
					return true;
				}
			} else if (orientation == 3) {
				if (initialX == finalX && initialZ == finalZ - 1) {
					return true;
				}
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280108) == 0) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280180) == 0) {
					return true;
				}
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				if (initialX == finalX - 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280180) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x280102) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280108) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x280102) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280108) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x280120) == 0) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1) {
					return true;
				}
			} else if (orientation == 3) {
				if (initialX == finalX - 1 && initialZ == finalZ) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x280120) == 0) {
					return true;
				}
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x280180) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1) {
					return true;
				}
			}
		}
		if (type == 9) {
			if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x20) == 0) {
				return true;
			}
			if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x2) == 0) {
				return true;
			}
			if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x8) == 0) {
				return true;
			}
			if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x80) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(IIIIIII)Z")
	public final boolean reachedDecoration(@OriginalArg(0) int orientation, @OriginalArg(1) int type, @OriginalArg(2) int obfuscator, @OriginalArg(3) int initialXOffset, @OriginalArg(4) int finalXOffset, @OriginalArg(5) int initialZOffset, @OriginalArg(6) int finalZOffset) {
		if (obfuscator != this.flowObfuscator4) {
			this.flowObfuscator1 = -218;
		}
		if (initialXOffset == finalXOffset && initialZOffset == finalZOffset) {
			return true;
		}
		@Pc(19) int initialX = initialXOffset - this.flowObfuscator6;
		@Pc(24) int initialZ = initialZOffset - this.flowObfuscator7;
		@Pc(29) int finalX = finalXOffset - this.flowObfuscator6;
		@Pc(34) int finalZ = finalZOffset - this.flowObfuscator7;
		if (type == 6 || type == 7) {
			if (type == 7) {
				orientation = orientation + 2 & 0x3;
			}
			if (orientation == 0) {
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x80) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x2) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x8) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x2) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x8) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x20) == 0) {
					return true;
				}
			} else if (orientation == 3) {
				if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x80) == 0) {
					return true;
				}
				if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x20) == 0) {
					return true;
				}
			}
		}
		if (type == 8) {
			if (initialX == finalX && initialZ == finalZ + 1 && (this.flags[initialX][initialZ] & 0x20) == 0) {
				return true;
			}
			if (initialX == finalX && initialZ == finalZ - 1 && (this.flags[initialX][initialZ] & 0x2) == 0) {
				return true;
			}
			if (initialX == finalX - 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x8) == 0) {
				return true;
			}
			if (initialX == finalX + 1 && initialZ == finalZ && (this.flags[initialX][initialZ] & 0x80) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIII)Z")
	public final boolean reachedObject(@OriginalArg(0) int z, @OriginalArg(1) int height, @OriginalArg(2) int x, @OriginalArg(3) int finalX, @OriginalArg(4) int surroundings, @OriginalArg(5) int finalZ, @OriginalArg(6) int width) {
		@Pc(5) int maxX = finalX + width - 1;
		@Pc(11) int maxZ = finalZ + height - 1;
		if (x >= finalX && x <= maxX && z >= finalZ && z <= maxZ) {
			return true;
		} else if (x == finalX - 1 && z >= finalZ && z <= maxZ && (this.flags[x - this.flowObfuscator6][z - this.flowObfuscator7] & 0x8) == 0 && (surroundings & 0x8) == 0) {
			return true;
		} else if (x == maxX + 1 && z >= finalZ && z <= maxZ && (this.flags[x - this.flowObfuscator6][z - this.flowObfuscator7] & 0x80) == 0 && (surroundings & 0x2) == 0) {
			return true;
		} else if (z == finalZ - 1 && x >= finalX && x <= maxX && (this.flags[x - this.flowObfuscator6][z - this.flowObfuscator7] & 0x2) == 0 && (surroundings & 0x4) == 0) {
			return true;
		} else {
			return z == maxZ + 1 && x >= finalX && x <= maxX && (this.flags[x - this.flowObfuscator6][z - this.flowObfuscator7] & 0x20) == 0 && (surroundings & 0x1) == 0;
		}
	}
}
