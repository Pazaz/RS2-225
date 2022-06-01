package com.jagex.game.runetek3.scenegraph;

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

	@OriginalMember(owner = "client!ec", name = "h", descriptor = "I")
	private final int x;

	@OriginalMember(owner = "client!ec", name = "i", descriptor = "I")
	private final int z;

	@OriginalMember(owner = "client!ec", name = "j", descriptor = "[[I")
	public final int[][] flags;

	@OriginalMember(owner = "client!ec", name = "<init>", descriptor = "(III)V")
	public CollisionMap(@OriginalArg(0) int x, @OriginalArg(2) int z) {
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
					this.flags[x][z] = CLOSED;
				} else {
					this.flags[x][z] = OPEN;
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIZI)V")
	public void setWall(@OriginalArg(1) int orientation, @OriginalArg(2) int zOffset, @OriginalArg(3) int xOffset, @OriginalArg(4) boolean blocks, @OriginalArg(5) int type) {
		@Pc(4) int x = xOffset;
		@Pc(19) int z = zOffset;
		if (type == 0) {
			if (orientation == 0) {
				this.add(x, z, WALL_WEST);
				this.add(x - 1, z, WALL_EAST);
			}
			if (orientation == 1) {
				this.add(x, z, WALL_NORTH);
				this.add(x, z + 1, WALL_SOUTH);
			}
			if (orientation == 2) {
				this.add(x, z, WALL_EAST);
				this.add(x + 1, z, WALL_WEST);
			}
			if (orientation == 3) {
				this.add(x, z, WALL_SOUTH);
				this.add(x, z - 1, WALL_NORTH);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.add(x, z, WALL_NORTHWEST);
				this.add(x - 1, z + 1, WALL_SOUTHEAST);
			}
			if (orientation == 1) {
				this.add(x, z, WALL_NORTHEAST);
				this.add(x + 1, z + 1, WALL_SOUTHWEST);
			}
			if (orientation == 2) {
				this.add(x, z, WALL_SOUTHEAST);
				this.add(x + 1, z - 1, WALL_NORTHWEST);
			}
			if (orientation == 3) {
				this.add(x, z, WALL_SOUTHWEST);
				this.add(x - 1, z - 1, WALL_NORTHEAST);
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				this.add(x, z, WALL_WEST | WALL_NORTH);
				this.add(x - 1, z, WALL_EAST);
				this.add(x, z + 1, WALL_SOUTH);
			}
			if (orientation == 1) {
				this.add(x, z, WALL_EAST | WALL_NORTH);
				this.add(x, z + 1, WALL_SOUTH);
				this.add(x + 1, z, WALL_WEST);
			}
			if (orientation == 2) {
				this.add(x, z, WALL_SOUTH | WALL_EAST);
				this.add(x + 1, z, WALL_WEST);
				this.add(x, z - 1, WALL_NORTH);
			}
			if (orientation == 3) {
				this.add(x, z, WALL_WEST | WALL_SOUTH);
				this.add(x, z - 1, WALL_NORTH);
				this.add(x - 1, z, WALL_EAST);
			}
		}
		if (!blocks) {
			return;
		}
		if (type == 0) {
			if (orientation == 0) {
				this.add(x, z, BLOCKED_WEST);
				this.add(x - 1, z, BLOCKED_EAST);
			}
			if (orientation == 1) {
				this.add(x, z, BLOCKED_NORTH);
				this.add(x, z + 1, BLOCKED_SOUTH);
			}
			if (orientation == 2) {
				this.add(x, z, BLOCKED_EAST);
				this.add(x + 1, z, BLOCKED_WEST);
			}
			if (orientation == 3) {
				this.add(x, z, BLOCKED_SOUTH);
				this.add(x, z - 1, BLOCKED_NORTH);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.add(x, z, BLOCKED_NORTHWEST);
				this.add(x - 1, z + 1, BLOCKED_SOUTHEAST);
			}
			if (orientation == 1) {
				this.add(x, z, BLOCKED_NORTHEAST);
				this.add(x + 1, z + 1, BLOCKED_SOUTHWEST);
			}
			if (orientation == 2) {
				this.add(x, z, BLOCKED_SOUTHEAST);
				this.add(x + 1, z - 1, BLOCKED_NORTHWEST);
			}
			if (orientation == 3) {
				this.add(x, z, BLOCKED_SOUTHWEST);
				this.add(x - 1, z - 1, BLOCKED_NORTHEAST);
			}
		}
		if (type != 2) {
			return;
		}
		if (orientation == 0) {
			this.add(x, z, BLOCKED_WEST | BLOCKED_NORTH);
			this.add(x - 1, z, BLOCKED_EAST);
			this.add(x, z + 1, BLOCKED_SOUTH);
		}
		if (orientation == 1) {
			this.add(x, z, BLOCKED_EAST | BLOCKED_NORTH);
			this.add(x, z + 1, BLOCKED_SOUTH);
			this.add(x + 1, z, BLOCKED_WEST);
		}
		if (orientation == 2) {
			this.add(x, z, BLOCKED_SOUTH | BLOCKED_EAST);
			this.add(x + 1, z, BLOCKED_WEST);
			this.add(x, z - 1, BLOCKED_NORTH);
		}
		if (orientation == 3) {
			this.add(x, z, BLOCKED_WEST | BLOCKED_SOUTH);
			this.add(x, z - 1, BLOCKED_NORTH);
			this.add(x - 1, z, BLOCKED_EAST);
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIZ)V")
	public void setLoc(@OriginalArg(0) int orientation, @OriginalArg(1) int sizeZ, @OriginalArg(2) int sizeX, @OriginalArg(3) int xOffset, @OriginalArg(5) int zOffset, @OriginalArg(6) boolean solid) {
		@Pc(3) int flag = OCCUPIED_TILE;
		if (solid) {
			flag += SOLID;
		}
		@Pc(14) int initialX = xOffset;
		@Pc(19) int initialZ = zOffset;
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
	public void setBlocked(@OriginalArg(1) int zOffset, @OriginalArg(2) int xOffset) {
		@Pc(9) int x = xOffset;
		@Pc(14) int z = zOffset;
		this.flags[x][z] |= BLOCKED_TILE;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(III)V")
	private void add(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(2) int flag) {
		this.flags[x][z] |= flag;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(ZIIIII)V")
	public void removeWall(@OriginalArg(0) boolean blocks, @OriginalArg(1) int orientation, @OriginalArg(2) int xOffset, @OriginalArg(3) int zOffset, @OriginalArg(5) int type) {
		@Pc(8) int x = xOffset;
		@Pc(13) int z = zOffset;
		if (type == 0) {
			if (orientation == 0) {
				this.remove(x, z, WALL_WEST);
				this.remove(x - 1, z, WALL_EAST);
			}
			if (orientation == 1) {
				this.remove(x, z, WALL_NORTH);
				this.remove(x, z + 1, WALL_SOUTH);
			}
			if (orientation == 2) {
				this.remove(x, z, WALL_EAST);
				this.remove(x + 1, z, WALL_WEST);
			}
			if (orientation == 3) {
				this.remove(x, z, WALL_SOUTH);
				this.remove(x, z - 1, WALL_NORTH);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.remove(x, z, WALL_NORTHWEST);
				this.remove(x - 1, z + 1, WALL_SOUTHEAST);
			}
			if (orientation == 1) {
				this.remove(x, z, WALL_NORTHEAST);
				this.remove(x + 1, z + 1, WALL_SOUTHWEST);
			}
			if (orientation == 2) {
				this.remove(x, z, WALL_SOUTHEAST);
				this.remove(x + 1, z - 1, WALL_NORTHWEST);
			}
			if (orientation == 3) {
				this.remove(x, z, WALL_SOUTHWEST);
				this.remove(x - 1, z - 1, WALL_NORTHEAST);
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				this.remove(x, z, WALL_WEST | WALL_NORTH);
				this.remove(x - 1, z, WALL_EAST);
				this.remove(x, z + 1, WALL_SOUTH);
			}
			if (orientation == 1) {
				this.remove(x, z, WALL_EAST | WALL_NORTH);
				this.remove(x, z + 1, WALL_SOUTH);
				this.remove(x + 1, z, WALL_WEST);
			}
			if (orientation == 2) {
				this.remove(x, z, WALL_SOUTH | WALL_EAST);
				this.remove(x + 1, z, WALL_WEST);
				this.remove(x, z - 1, WALL_NORTH);
			}
			if (orientation == 3) {
				this.remove(x, z, WALL_WEST | WALL_SOUTH);
				this.remove(x, z - 1, WALL_NORTH);
				this.remove(x - 1, z, WALL_EAST);
			}
		}
		if (!blocks) {
			return;
		}
		if (type == 0) {
			if (orientation == 0) {
				this.remove(x, z, BLOCKED_WEST);
				this.remove(x - 1, z, BLOCKED_EAST);
			}
			if (orientation == 1) {
				this.remove(x, z, BLOCKED_NORTH);
				this.remove(x, z + 1, BLOCKED_SOUTH);
			}
			if (orientation == 2) {
				this.remove(x, z, BLOCKED_EAST);
				this.remove(x + 1, z, BLOCKED_WEST);
			}
			if (orientation == 3) {
				this.remove(x, z, BLOCKED_SOUTH);
				this.remove(x, z - 1, BLOCKED_NORTH);
			}
		}
		if (type == 1 || type == 3) {
			if (orientation == 0) {
				this.remove(x, z, BLOCKED_NORTHWEST);
				this.remove(x - 1, z + 1, BLOCKED_SOUTHEAST);
			}
			if (orientation == 1) {
				this.remove(x, z, BLOCKED_NORTHEAST);
				this.remove(x + 1, z + 1, BLOCKED_SOUTHWEST);
			}
			if (orientation == 2) {
				this.remove(x, z, BLOCKED_SOUTHEAST);
				this.remove(x + 1, z - 1, BLOCKED_NORTHWEST);
			}
			if (orientation == 3) {
				this.remove(x, z, BLOCKED_SOUTHWEST);
				this.remove(x - 1, z - 1, BLOCKED_NORTHEAST);
			}
		}
		if (type != 2) {
			return;
		}
		if (orientation == 0) {
			this.remove(x, z, BLOCKED_WEST | BLOCKED_NORTH);
			this.remove(x - 1, z, BLOCKED_EAST);
			this.remove(x, z + 1, BLOCKED_SOUTH);
		}
		if (orientation == 1) {
			this.remove(x, z, BLOCKED_EAST | BLOCKED_NORTH);
			this.remove(x, z + 1, BLOCKED_SOUTH);
			this.remove(x + 1, z, BLOCKED_WEST);
		}
		if (orientation == 2) {
			this.remove(x, z, BLOCKED_SOUTH | BLOCKED_EAST);
			this.remove(x + 1, z, BLOCKED_WEST);
			this.remove(x, z - 1, BLOCKED_NORTH);
		}
		if (orientation == 3) {
			this.remove(x, z, BLOCKED_WEST | BLOCKED_SOUTH);
			this.remove(x, z - 1, BLOCKED_NORTH);
			this.remove(x - 1, z, BLOCKED_EAST);
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIZZI)V")
	public void removeLoc(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset, @OriginalArg(2) int orientation, @OriginalArg(3) int sizeX, @OriginalArg(5) boolean solid, @OriginalArg(6) int sizeZ) {
		@Pc(3) int flag = OCCUPIED_TILE;
		if (solid) {
			flag = SOLID;
		}
		@Pc(14) int startX = xOffset;
		@Pc(19) int startZ = zOffset;
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
						this.remove(temp, z, flag);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IBII)V")
	private void remove(@OriginalArg(2) int x, @OriginalArg(0) int z, @OriginalArg(3) int flag) {
		this.flags[x][z] &= 0xFFFFFF - flag;
	}

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(III)V")
	public void removeBlock(@OriginalArg(0) int zOffset, @OriginalArg(1) int xOffset) {
		@Pc(7) int x = xOffset;
		@Pc(12) int z = zOffset;
		this.flags[x][z] &= 0xFFFFFF - BLOCKED_TILE;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIII)Z")
	public boolean reachedWall(@OriginalArg(1) int orientation, @OriginalArg(2) int finalZOffset, @OriginalArg(3) int type, @OriginalArg(4) int initialZOffset, @OriginalArg(5) int finalXOffset, @OriginalArg(6) int initialXOffset) {
		if (initialXOffset == finalXOffset && initialZOffset == finalZOffset) {
			return true;
		}
		@Pc(12) int x0 = initialXOffset;
		@Pc(17) int z0 = initialZOffset;
		@Pc(29) int x1 = finalXOffset;
		@Pc(34) int z1 = finalZOffset;
		if (type == 0) {
			if (orientation == 0) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (x0 == x1 && z0 == z1 + 1) {
					return true;
				}
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (x0 == x1 + 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == 3) {
				if (x0 == x1 && z0 == z1 - 1) {
					return true;
				}
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
			}
		}
		if (type == 2) {
			if (orientation == 0) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_NORTH)) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_EAST)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1) {
					return true;
				}
			} else if (orientation == 3) {
				if (x0 == x1 - 1 && z0 == z1) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_SOUTH)) == 0) {
					return true;
				}
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & (BLOCKED_TILE | FLAG_UNUSED1 | OCCUPIED_TILE | WALL_WEST)) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1) {
					return true;
				}
			}
		}
		if (type == 9) {
			if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
				return true;
			}
			if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
				return true;
			}
			if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
				return true;
			}
			if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
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
		@Pc(19) int x0 = initialXOffset;
		@Pc(24) int z0 = initialZOffset;
		@Pc(29) int x1 = finalXOffset;
		@Pc(34) int z1 = finalZOffset;
		if (type == 6 || type == 7) {
			if (type == 7) {
				orientation = orientation + 2 & 0x3;
			}
			if (orientation == 0) {
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
					return true;
				}
			} else if (orientation == 1) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
					return true;
				}
			} else if (orientation == 2) {
				if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
					return true;
				}
			} else if (orientation == 3) {
				if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
					return true;
				}
				if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
					return true;
				}
			}
		}
		if (type == 8) {
			if (x0 == x1 && z0 == z1 + 1 && (this.flags[x0][z0] & WALL_SOUTH) == 0) {
				return true;
			}
			if (x0 == x1 && z0 == z1 - 1 && (this.flags[x0][z0] & WALL_NORTH) == 0) {
				return true;
			}
			if (x0 == x1 - 1 && z0 == z1 && (this.flags[x0][z0] & WALL_EAST) == 0) {
				return true;
			}
			if (x0 == x1 + 1 && z0 == z1 && (this.flags[x0][z0] & WALL_WEST) == 0) {
				return true;
			}
		}
		return false;
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(IIIIIIII)Z")
	public boolean reachedObject(@OriginalArg(0) int z, @OriginalArg(1) int height, @OriginalArg(2) int x, @OriginalArg(3) int finalX, @OriginalArg(4) int surroundings, @OriginalArg(5) int finalZ, @OriginalArg(6) int width) {
		@Pc(5) int maxX = finalX + width - 1;
		@Pc(11) int maxZ = finalZ + height - 1;
		if (x >= finalX && x <= maxX && z >= finalZ && z <= maxZ) {
			return true;
		} else if (x == finalX - 1 && z >= finalZ && z <= maxZ && (this.flags[x][z] & WALL_EAST) == 0 && (surroundings & 0x8) == 0) {
			return true;
		} else if (x == maxX + 1 && z >= finalZ && z <= maxZ && (this.flags[x][z] & WALL_WEST) == 0 && (surroundings & 0x2) == 0) {
			return true;
		} else if (z == finalZ - 1 && x >= finalX && x <= maxX && (this.flags[x][z] & WALL_NORTH) == 0 && (surroundings & 0x4) == 0) {
			return true;
		} else {
			return z == maxZ + 1 && x >= finalX && x <= maxX && (this.flags[x][z] & WALL_SOUTH) == 0 && (surroundings & 0x1) == 0;
		}
	}

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

}
