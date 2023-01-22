package com.jagex.game.runetek3.scene;

import com.jagex.game.runetek3.config.LocType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ec")
public class CollisionMap {

	public static final int OPEN = 0x0;
	public static final int CLOSED = 0xFFFFFF;

	public static final int WALL_NORTHWEST = 0x1;
	public static final int WALL_NORTH = 0x2;
	public static final int WALL_NORTHEAST = 0x4;
	public static final int WALL_EAST = 0x8;
	public static final int WALL_SOUTHEAST = 0x10;
	public static final int WALL_SOUTH = 0x20;
	public static final int WALL_SOUTHWEST = 0x40;
	public static final int WALL_WEST = 0x80;

	public static final int OCCUPIED_TILE = 0x100;

	public static final int BLOCKED_NORTHWEST = 0x200;
	public static final int BLOCKED_NORTH = 0x400;
	public static final int BLOCKED_NORTHEAST = 0x800;
	public static final int BLOCKED_EAST = 0x1000;
	public static final int BLOCKED_SOUTHEAST = 0x2000;
	public static final int BLOCKED_SOUTH = 0x4000;
	public static final int BLOCKED_SOUTHWEST = 0x8000;
	public static final int BLOCKED_WEST = 0x10000;

	public static final int SOLID = 0x20000;
	public static final int FLAG_UNUSED1 = 0x80000;
	public static final int BLOCKED_TILE = 0x200000;

	public static final int NORTH_WEST = 0;
	public static final int NORTH_EAST = 1;
	public static final int SOUTH_EAST = 2;
	public static final int SOUTH_WEST = 3;

	@OriginalMember(owner = "client!ec", name = "f", descriptor = "I")
	private final int xOffset;

	@OriginalMember(owner = "client!ec", name = "g", descriptor = "I")
	private final int zOffset;

	@OriginalMember(owner = "client!ec", name = "h", descriptor = "I")
	private final int x;

	@OriginalMember(owner = "client!ec", name = "i", descriptor = "I")
	private final int z;

	@OriginalMember(owner = "client!ec", name = "j", descriptor = "[[I")
	public final int[][] flags;

	@OriginalMember(owner = "client!ec", name = "<init>", descriptor = "(III)V")
	public CollisionMap(@OriginalArg(0) int x, @OriginalArg(2) int z) {
		this.xOffset = 0;
		this.zOffset = 0;
		this.x = x;
		this.z = z;
		this.flags = new int[this.x][this.z];
		this.reset();
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(B)V")
	public void reset() {
		@Pc(7) int x;
		for (@Pc(3) int z = 0; z < this.x; z++) {
			for (x = 0; x < this.z; x++) {
				if (z == 0 || x == 0 || z == this.x - 1 || x == this.z - 1) {
					this.flags[z][x] = CLOSED;
				} else {
					this.flags[z][x] = OPEN;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIZI)V")
	public void setWall(@OriginalArg(1) int orientation, @OriginalArg(2) int z, @OriginalArg(3) int x, @OriginalArg(4) boolean blocks, @OriginalArg(5) int type) {
		x -= this.xOffset;
		z -= this.zOffset;

		if (type == LocType.WALL_STRAIGHT) {
			if (orientation == NORTH_WEST) {
				this.add(x, z, WALL_WEST);
				this.add(x - 1, z, WALL_EAST);
			} else if (orientation == NORTH_EAST) {
				this.add(x, z, WALL_NORTH);
				this.add(x, z + 1, WALL_SOUTH);
			} else if (orientation == SOUTH_EAST) {
				this.add(x, z, WALL_EAST);
				this.add(x + 1, z, WALL_WEST);
			} else if (orientation == SOUTH_WEST) {
				this.add(x, z, WALL_SOUTH);
				this.add(x, z - 1, WALL_NORTH);
			}
		} else if (type == LocType.WALL_DIAGONALCORNER || type == LocType.WALL_SQUARECORNER) {
			if (orientation == NORTH_WEST) {
				this.add(x, z, WALL_NORTHWEST);
				this.add(x - 1, z + 1, WALL_SOUTHEAST);
			} else if (orientation == NORTH_EAST) {
				this.add(x, z, WALL_NORTHEAST);
				this.add(x + 1, z + 1, WALL_SOUTHWEST);
			} else if (orientation == SOUTH_EAST) {
				this.add(x, z, WALL_SOUTHEAST);
				this.add(x + 1, z - 1, WALL_NORTHWEST);
			} else if (orientation == SOUTH_WEST) {
				this.add(x, z, WALL_SOUTHWEST);
				this.add(x - 1, z - 1, WALL_NORTHEAST);
			}
		} else if (type == LocType.WALL_L) {
			if (orientation == NORTH_WEST) {
				this.add(x, z, WALL_WEST | WALL_NORTH);
				this.add(x - 1, z, WALL_EAST);
				this.add(x, z + 1, WALL_SOUTH);
			} else if (orientation == NORTH_EAST) {
				this.add(x, z, WALL_EAST | WALL_NORTH);
				this.add(x, z + 1, WALL_SOUTH);
				this.add(x + 1, z, WALL_WEST);
			} else if (orientation == SOUTH_EAST) {
				this.add(x, z, WALL_SOUTH | WALL_EAST);
				this.add(x + 1, z, WALL_WEST);
				this.add(x, z - 1, WALL_NORTH);
			} else if (orientation == SOUTH_WEST) {
				this.add(x, z, WALL_WEST | WALL_SOUTH);
				this.add(x, z - 1, WALL_NORTH);
				this.add(x - 1, z, WALL_EAST);
			}
		}

		if (blocks) {
			if (type == LocType.WALL_STRAIGHT) {
				if (orientation == NORTH_WEST) {
					this.add(x, z, BLOCKED_WEST);
					this.add(x - 1, z, BLOCKED_EAST);
				} else if (orientation == NORTH_EAST) {
					this.add(x, z, BLOCKED_NORTH);
					this.add(x, z + 1, BLOCKED_SOUTH);
				} else if (orientation == SOUTH_EAST) {
					this.add(x, z, BLOCKED_EAST);
					this.add(x + 1, z, BLOCKED_WEST);
				} else if (orientation == SOUTH_WEST) {
					this.add(x, z, BLOCKED_SOUTH);
					this.add(x, z - 1, BLOCKED_NORTH);
				}
			} else if (type == LocType.WALL_DIAGONALCORNER || type == LocType.WALL_SQUARECORNER) {
				if (orientation == NORTH_WEST) {
					this.add(x, z, BLOCKED_NORTHWEST);
					this.add(x - 1, z + 1, BLOCKED_SOUTHEAST);
				} else if (orientation == NORTH_EAST) {
					this.add(x, z, BLOCKED_NORTHEAST);
					this.add(x + 1, z + 1, BLOCKED_SOUTHWEST);
				} else if (orientation == SOUTH_EAST) {
					this.add(x, z, BLOCKED_SOUTHEAST);
					this.add(x + 1, z - 1, BLOCKED_NORTHWEST);
				} else if (orientation == SOUTH_WEST) {
					this.add(x, z, BLOCKED_SOUTHWEST);
					this.add(x - 1, z - 1, BLOCKED_NORTHEAST);
				}
			} else if (type == LocType.WALL_L) {
				if (orientation == NORTH_WEST) {
					this.add(x, z, BLOCKED_WEST | BLOCKED_NORTH);
					this.add(x - 1, z, BLOCKED_EAST);
					this.add(x, z + 1, BLOCKED_SOUTH);
				} else if (orientation == NORTH_EAST) {
					this.add(x, z, BLOCKED_EAST | BLOCKED_NORTH);
					this.add(x, z + 1, BLOCKED_SOUTH);
					this.add(x + 1, z, BLOCKED_WEST);
				} else if (orientation == SOUTH_EAST) {
					this.add(x, z, BLOCKED_SOUTH | BLOCKED_EAST);
					this.add(x + 1, z, BLOCKED_WEST);
					this.add(x, z - 1, BLOCKED_NORTH);
				} else if (orientation == SOUTH_WEST) {
					this.add(x, z, BLOCKED_WEST | BLOCKED_SOUTH);
					this.add(x, z - 1, BLOCKED_NORTH);
					this.add(x - 1, z, BLOCKED_EAST);
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIZ)V")
	public void setLoc(@OriginalArg(0) int orientation, @OriginalArg(1) int sizeZ, @OriginalArg(2) int sizeX, @OriginalArg(3) int xOffset, @OriginalArg(5) int zOffset, @OriginalArg(6) boolean solid) {
		@Pc(3) int flag = OCCUPIED_TILE;
		if (solid) {
			flag += SOLID;
		}

		@Pc(14) int startX = xOffset - this.xOffset;
		@Pc(19) int startZ = zOffset - this.zOffset;

		if (orientation == 1 || orientation == 3) {
			int temp = sizeX;
			sizeX = sizeZ;
			sizeZ = temp;
		}

		for (int x = startX; x < startX + sizeX; x++) {
			if (x >= 0 && x < this.x) {
				for (int z = startZ; z < startZ + sizeZ; z++) {
					if (z >= 0 && z < this.z) {
						this.add(x, z, flag);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(BII)V")
	public void setBlocked(@OriginalArg(1) int zOffset, @OriginalArg(2) int xOffset) {
		@Pc(9) int x = xOffset - this.xOffset;
		@Pc(14) int z = zOffset - this.zOffset;
		this.flags[x][z] |= BLOCKED_TILE;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(III)V")
	private void add(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(2) int flag) {
		this.flags[x][z] |= flag;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIII)V")
	public void removeWall(@OriginalArg(0) boolean blocks, @OriginalArg(1) int orientation, @OriginalArg(2) int x, @OriginalArg(3) int z, @OriginalArg(5) int type) {
		x -= this.xOffset;
		z -= this.zOffset;

		if (type == LocType.WALL_STRAIGHT) {
			if (orientation == NORTH_WEST) {
				this.remove(z, x, WALL_WEST);
				this.remove(z, x - 1, WALL_EAST);
			} else if (orientation == NORTH_EAST) {
				this.remove(z, x, WALL_NORTH);
				this.remove(z + 1, x, WALL_SOUTH);
			} else if (orientation == SOUTH_EAST) {
				this.remove(z, x, WALL_EAST);
				this.remove(z, x + 1, WALL_WEST);
			} else if (orientation == SOUTH_WEST) {
				this.remove(z, x, WALL_SOUTH);
				this.remove(z - 1, x, WALL_NORTH);
			}
		} else if (type == LocType.WALL_DIAGONALCORNER || type == LocType.WALL_SQUARECORNER) {
			if (orientation == NORTH_WEST) {
				this.remove(z, x, WALL_NORTHWEST);
				this.remove(z + 1, x - 1, WALL_SOUTHEAST);
			} else if (orientation == NORTH_EAST) {
				this.remove(z, x, WALL_NORTHEAST);
				this.remove(z + 1, x + 1, WALL_SOUTHWEST);
			} else if (orientation == SOUTH_EAST) {
				this.remove(z, x, WALL_SOUTHEAST);
				this.remove(z - 1, x + 1, WALL_NORTHWEST);
			} else if (orientation == SOUTH_WEST) {
				this.remove(z, x, WALL_SOUTHWEST);
				this.remove(z - 1, x - 1, WALL_NORTHEAST);
			}
		} else if (type == LocType.WALL_L) {
			if (orientation == NORTH_WEST) {
				this.remove(z, x, WALL_WEST | WALL_NORTH);
				this.remove(z, x - 1, WALL_EAST);
				this.remove(z + 1, x, WALL_SOUTH);
			} else if (orientation == NORTH_EAST) {
				this.remove(z, x, WALL_EAST | WALL_NORTH);
				this.remove(z + 1, x, WALL_SOUTH);
				this.remove(z, x + 1, WALL_WEST);
			} else if (orientation == SOUTH_EAST) {
				this.remove(z, x, WALL_SOUTH | WALL_EAST);
				this.remove(z, x + 1, WALL_WEST);
				this.remove(z - 1, x, WALL_NORTH);
			} else if (orientation == SOUTH_WEST) {
				this.remove(z, x, WALL_WEST | WALL_SOUTH);
				this.remove(z - 1, x, WALL_NORTH);
				this.remove(z, x - 1, WALL_EAST);
			}
		}

		if (blocks) {
			if (type == LocType.WALL_STRAIGHT) {
				if (orientation == NORTH_WEST) {
					this.remove(z, x, BLOCKED_WEST);
					this.remove(z, x - 1, BLOCKED_EAST);
				} else if (orientation == NORTH_EAST) {
					this.remove(z, x, BLOCKED_NORTH);
					this.remove(z + 1, x, BLOCKED_SOUTH);
				} else if (orientation == SOUTH_EAST) {
					this.remove(z, x, BLOCKED_EAST);
					this.remove(z, x + 1, BLOCKED_WEST);
				} else if (orientation == SOUTH_WEST) {
					this.remove(z, x, BLOCKED_SOUTH);
					this.remove(z - 1, x, BLOCKED_NORTH);
				}
			} else if (type == LocType.WALL_DIAGONALCORNER || type == LocType.WALL_SQUARECORNER) {
				if (orientation == NORTH_WEST) {
					this.remove(z, x, BLOCKED_NORTHWEST);
					this.remove(z + 1, x - 1, BLOCKED_SOUTHEAST);
				} else if (orientation == NORTH_EAST) {
					this.remove(z, x, BLOCKED_NORTHEAST);
					this.remove(z + 1, x + 1, BLOCKED_SOUTHWEST);
				} else if (orientation == SOUTH_EAST) {
					this.remove(z, x, BLOCKED_SOUTHEAST);
					this.remove(z - 1, x + 1, BLOCKED_NORTHWEST);
				} else if (orientation == SOUTH_WEST) {
					this.remove(z, x, BLOCKED_SOUTHWEST);
					this.remove(z - 1, x - 1, BLOCKED_NORTHEAST);
				}
			} else if (type == LocType.WALL_L) {
				if (orientation == NORTH_WEST) {
					this.remove(z, x, BLOCKED_WEST | BLOCKED_NORTH);
					this.remove(z, x - 1, BLOCKED_EAST);
					this.remove(z + 1, x, BLOCKED_SOUTH);
				} else if (orientation == NORTH_EAST) {
					this.remove(z, x, BLOCKED_EAST | BLOCKED_NORTH);
					this.remove(z + 1, x, BLOCKED_SOUTH);
					this.remove(z, x + 1, BLOCKED_WEST);
				} else if (orientation == SOUTH_EAST) {
					this.remove(z, x, BLOCKED_SOUTH | BLOCKED_EAST);
					this.remove(z, x + 1, BLOCKED_WEST);
					this.remove(z - 1, x, BLOCKED_NORTH);
				} else if (orientation == SOUTH_WEST) {
					this.remove(z, x, BLOCKED_WEST | BLOCKED_SOUTH);
					this.remove(z - 1, x, BLOCKED_NORTH);
					this.remove(z, x - 1, BLOCKED_EAST);
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIZZI)V")
	public void removeLoc(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset, @OriginalArg(2) int orientation, @OriginalArg(3) int sizeX, @OriginalArg(5) boolean solid, @OriginalArg(6) int sizeZ) {
		@Pc(3) int flag = OCCUPIED_TILE;
		if (solid) {
			flag += SOLID;
		}

		@Pc(14) int startX = xOffset - this.xOffset;
		@Pc(19) int startZ = zOffset - this.zOffset;

		if (orientation == 1 || orientation == 3) {
			int temp = sizeX;
			sizeX = sizeZ;
			sizeZ = temp;
		}

		for (int x = startX; x < startX + sizeX; x++) {
			if (x >= 0 && x < this.x) {
				for (@Pc(48) int z = startZ; z < startZ + sizeZ; z++) {
					if (z >= 0 && z < this.z) {
						this.remove(z, x, flag);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IBII)V")
	private void remove(@OriginalArg(0) int z, @OriginalArg(2) int x, @OriginalArg(3) int flag) {
		this.flags[x][z] &= CLOSED - flag;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(III)V")
	public void removeBlock(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset) {
		@Pc(7) int x = xOffset - this.xOffset;
		@Pc(12) int z = zOffset - this.zOffset;
		this.flags[x][z] &= CLOSED - BLOCKED_TILE;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIII)Z")
	public boolean reachedWall(@OriginalArg(1) int orientation, @OriginalArg(2) int finalZOffset, @OriginalArg(3) int type, @OriginalArg(4) int initialZOffset, @OriginalArg(5) int finalXOffset, @OriginalArg(6) int initialXOffset) {
		if (initialXOffset == finalXOffset && initialZOffset == finalZOffset) {
			return true;
		}

		@Pc(12) int x0 = initialXOffset - this.xOffset;
		@Pc(17) int z0 = initialZOffset - this.zOffset;
		@Pc(29) int x1 = finalXOffset - this.xOffset;
		@Pc(34) int z1 = finalZOffset - this.zOffset;

		if (type == LocType.WALL_STRAIGHT) {
			if (orientation == NORTH_WEST) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == NORTH_EAST) {
				if (x0 == x1 && z0 == z1 + 1) {
					return true;
				} else if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
			} else if (orientation == SOUTH_EAST) {
				if (x0 == x1 + 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == SOUTH_WEST) {
				if (x0 == x1 && z0 == z1 - 1) {
					return true;
				} else if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
			}
		} else if (type == LocType.WALL_L) {
			if (orientation == NORTH_WEST) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == NORTH_EAST) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == SOUTH_EAST) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1) {
					return true;
				}
			} else if (orientation == SOUTH_WEST) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1) {
					return true;
				}
			}
		} else if (type == LocType.WALL_DIAGONAL) {
			if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
				return true;
			} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
				return true;
			} else if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
				return true;
			} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
				return true;
			}
		}

		return false;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(IIIIIII)Z")
	public boolean reachedDecoration(@OriginalArg(0) int orientation, @OriginalArg(1) int type, @OriginalArg(3) int initialXOffset, @OriginalArg(4) int finalXOffset, @OriginalArg(5) int initialZOffset, @OriginalArg(6) int finalZOffset) {
		if (initialXOffset == finalXOffset && initialZOffset == finalZOffset) {
			return true;
		}

		@Pc(19) int x0 = initialXOffset - this.xOffset;
		@Pc(24) int z0 = initialZOffset - this.zOffset;
		@Pc(29) int x1 = finalXOffset - this.xOffset;
		@Pc(34) int z1 = finalZOffset - this.zOffset;

		if (type == LocType.WALLDECOR_DIAGONAL_XOFFSET || type == LocType.WALLDECOR_DIAGONAL_ZOFFSET) {
			if (type == LocType.WALLDECOR_DIAGONAL_ZOFFSET) {
				orientation = orientation + 2 & 0x3;
			}

			if (orientation == NORTH_WEST) {
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
					return true;
				}
			} else if (orientation == NORTH_EAST) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
					return true;
				}
			} else if (orientation == SOUTH_EAST) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
					return true;
				}
			} else if (orientation == SOUTH_WEST) {
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
					return true;
				} else if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
					return true;
				}
			}
		} else if (type == LocType.WALLDECOR_DIAGONAL_BOTH) {
			if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
				return true;
			} else if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
				return true;
			} else if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
				return true;
			} else if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIII)Z")
	public boolean reachedObject(@OriginalArg(0) int z, @OriginalArg(1) int depth, @OriginalArg(2) int x, @OriginalArg(3) int finalX, @OriginalArg(4) int surroundings, @OriginalArg(5) int finalZ, @OriginalArg(6) int width) {
		@Pc(5) int maxX = finalX + width - 1;
		@Pc(11) int maxZ = finalZ + depth - 1;

		if (x >= finalX && x <= maxX && z >= finalZ && z <= maxZ) {
			return true;
		} else if (x == finalX - 1 && z >= finalZ && z <= maxZ && (this.flags[x - this.xOffset][z - this.zOffset] & WALL_EAST) == 0 && (surroundings & 0x8) == 0) {
			return true;
		} else if (x == maxX + 1 && z >= finalZ && z <= maxZ && (this.flags[x - this.xOffset][z - this.zOffset] & WALL_WEST) == 0 && (surroundings & 0x2) == 0) {
			return true;
		} else if (z == finalZ - 1 && x >= finalX && x <= maxX && (this.flags[x - this.xOffset][z - this.zOffset] & WALL_NORTH) == 0 && (surroundings & 0x4) == 0) {
			return true;
		} else if (z == maxZ + 1 && x >= finalX && x <= maxX && (this.flags[x - this.xOffset][z - this.zOffset] & WALL_SOUTH) == 0 && (surroundings & 0x1) == 0) {
			return true;
		}

		return false;
	}
}
