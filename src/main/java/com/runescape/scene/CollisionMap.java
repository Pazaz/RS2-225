package com.runescape.scene;

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
	public static final int BLOCKED_TILE = 0x200000;

    public CollisionMap(int wide, int tall) {
        this.wide = wide;
        this.tall = tall;
        flags = new int[this.wide][this.tall];
        reset();
    }

    public void reset() {
        for (int x = 0; x < wide; x++) {
            for (int z = 0; z < tall; z++) {
                if (x == 0 || z == 0 || x == wide - 1 || z == tall - 1) {
                    flags[x][z] = CLOSED;
                } else {
                    flags[x][z] = OPEN;
                }
            }
        }
    }

    public void setWall(int orientation, int z, int x, boolean blocks, int type) {
        x -= xOffset;
        z -= zOffset;

        if (type == 0) {
            if (orientation == 0) {
                add(x, z, WALL_WEST);
                add(x - 1, z, WALL_EAST);
            } else if (orientation == 1) {
                add(x, z, WALL_NORTH);
                add(x, z + 1, WALL_SOUTH);
            } else if (orientation == 2) {
                add(x, z, WALL_EAST);
                add(x + 1, z, WALL_WEST);
            } else if (orientation == 3) {
                add(x, z, WALL_SOUTH);
                add(x, z - 1, WALL_NORTH);
            }
        } else if (type == 1 || type == 3) {
            if (orientation == 0) {
                add(x, z, WALL_NORTHWEST);
                add(x - 1, z + 1, WALL_SOUTHEAST);
            } else if (orientation == 1) {
                add(x, z, WALL_NORTHEAST);
                add(x + 1, z + 1, WALL_SOUTHWEST);
            } else if (orientation == 2) {
                add(x, z, WALL_SOUTHEAST);
                add(x + 1, z - 1, WALL_NORTHWEST);
            } else if (orientation == 3) {
                add(x, z, WALL_SOUTHWEST);
                add(x - 1, z - 1, WALL_NORTHEAST);
            }
        } else if (type == 2) {
            if (orientation == 0) {
                add(x, z, WALL_WEST | WALL_NORTH);
                add(x - 1, z, WALL_EAST);
                add(x, z + 1, WALL_SOUTH);
            } else if (orientation == 1) {
                add(x, z, WALL_EAST | WALL_NORTH);
                add(x, z + 1, WALL_SOUTH);
                add(x + 1, z, WALL_WEST);
            } else if (orientation == 2) {
                add(x, z, WALL_EAST | WALL_SOUTH);
                add(x + 1, z, WALL_WEST);
                add(x, z - 1, WALL_NORTH);
            } else if (orientation == 3) {
                add(x, z, WALL_WEST | WALL_SOUTH);
                add(x, z - 1, WALL_NORTH);
                add(x - 1, z, WALL_EAST);
            }
        }

        if (blocks) {
            if (type == 0) {
                if (orientation == 0) {
                    add(x, z, BLOCKED_WEST);
                    add(x - 1, z, BLOCKED_EAST);
                } else if (orientation == 1) {
                    add(x, z, BLOCKED_NORTH);
                    add(x, z + 1, BLOCKED_SOUTH);
                } else if (orientation == 2) {
                    add(x, z, BLOCKED_EAST);
                    add(x + 1, z, BLOCKED_WEST);
                } else if (orientation == 3) {
                    add(x, z, BLOCKED_SOUTH);
                    add(x, z - 1, BLOCKED_NORTH);
                }
            } else if (type == 1 || type == 3) {
                if (orientation == 0) {
                    add(x, z, BLOCKED_NORTHWEST);
                    add(x - 1, z + 1, BLOCKED_SOUTHEAST);
                } else if (orientation == 1) {
                    add(x, z, BLOCKED_NORTHEAST);
                    add(x + 1, z + 1, BLOCKED_SOUTHWEST);
                } else if (orientation == 2) {
                    add(x, z, BLOCKED_SOUTHEAST);
                    add(x + 1, z - 1, BLOCKED_NORTHWEST);
                } else if (orientation == 3) {
                    add(x, z, BLOCKED_SOUTHWEST);
                    add(x - 1, z - 1, BLOCKED_NORTHEAST);
                }
            } else if (type == 2) {
                if (orientation == 0) {
                    add(x, z, BLOCKED_WEST | BLOCKED_NORTH);
                    add(x - 1, z, BLOCKED_EAST);
                    add(x, z + 1, BLOCKED_SOUTH);
                } else if (orientation == 1) {
                    add(x, z, BLOCKED_EAST | BLOCKED_NORTH);
                    add(x, z + 1, BLOCKED_SOUTH);
                    add(x + 1, z, BLOCKED_WEST);
                } else if (orientation == 2) {
                    add(x, z, BLOCKED_EAST | BLOCKED_SOUTH);
                    add(x + 1, z, BLOCKED_WEST);
                    add(x, z - 1, BLOCKED_NORTH);
                } else if (orientation == 3) {
                    add(x, z, BLOCKED_WEST | BLOCKED_SOUTH);
                    add(x, z - 1, BLOCKED_NORTH);
                    add(x - 1, z, BLOCKED_EAST);
                }
            }
        }
    }

    public void setLoc(int orientation, int sizeZ, int sizeX, int initialX, int initialZ, boolean solid) {
        int flag = OCCUPIED_TILE;

        if (solid) {
            flag += SOLID;
        }

        initialX -= xOffset;
        initialZ -= zOffset;

        if (orientation == 1 || orientation == 3) {
            int z = sizeX;
            sizeX = sizeZ;
            sizeZ = z;
        }

        for (int x = initialX; x < initialX + sizeX; x++) {
            if (x >= 0 && x < wide) {
                for (int z = initialZ; z < initialZ + sizeZ; z++) {
                    if (z >= 0 && z < tall) {
                        add(x, z, flag);
                    }
                }
            }
        }
    }

    public void setBlocked(int z, int x) {
        x -= xOffset;
        z -= zOffset;
        flags[x][z] |= BLOCKED_TILE;
    }

    public void add(int i, int j, int k) {
        flags[i][j] |= k;
    }

    public void removeWall(boolean blocks, int orientation, int x, int z, int type) {
        x -= xOffset;
        z -= zOffset;

        if (type == 0) {
            if (orientation == 0) {
                remove(z, x, WALL_WEST);
                remove(z, x - 1, WALL_EAST);
            } else if (orientation == 1) {
                remove(z, x, WALL_NORTH);
                remove(z + 1, x, WALL_SOUTH);
            } else if (orientation == 2) {
                remove(z, x, WALL_EAST);
                remove(z, x + 1, WALL_WEST);
            } else if (orientation == 3) {
                remove(z, x, WALL_SOUTH);
                remove(z - 1, x, WALL_NORTH);
            }
        } else if (type == 1 || type == 3) {
            if (orientation == 0) {
                remove(z, x, WALL_NORTHWEST);
                remove(z + 1, x - 1, WALL_SOUTHEAST);
            } else if (orientation == 1) {
                remove(z, x, WALL_NORTHEAST);
                remove(z + 1, x + 1, WALL_SOUTHWEST);
            } else if (orientation == 2) {
                remove(z, x, WALL_SOUTHEAST);
                remove(z - 1, x + 1, WALL_NORTHWEST);
            } else if (orientation == 3) {
                remove(z, x, WALL_SOUTHWEST);
                remove(z - 1, x - 1, WALL_NORTHEAST);
            }
        } else if (type == 2) {
            if (orientation == 0) {
                remove(z, x, WALL_WEST | WALL_NORTH);
                remove(z, x - 1, WALL_EAST);
                remove(z + 1, x, WALL_SOUTH);
            } else if (orientation == 1) {
                remove(z, x, WALL_EAST | WALL_NORTH);
                remove(z + 1, x, WALL_SOUTH);
                remove(z, x + 1, WALL_WEST);
            } else if (orientation == 2) {
                remove(z, x, WALL_EAST | WALL_SOUTH);
                remove(z, x + 1, WALL_WEST);
                remove(z - 1, x, WALL_NORTH);
            } else if (orientation == 3) {
                remove(z, x, WALL_WEST | WALL_SOUTH);
                remove(z - 1, x, WALL_NORTH);
                remove(z, x - 1, WALL_EAST);
            }
        }

        if (blocks) {
            if (type == 0) {
                if (orientation == 0) {
                    remove(z, x, BLOCKED_WEST);
                    remove(z, x - 1, BLOCKED_EAST);
                } else if (orientation == 1) {
                    remove(z, x, BLOCKED_NORTH);
                    remove(z + 1, x, BLOCKED_SOUTH);
                } else if (orientation == 2) {
                    remove(z, x, BLOCKED_EAST);
                    remove(z, x + 1, BLOCKED_WEST);
                } else if (orientation == 3) {
                    remove(z, x, BLOCKED_SOUTH);
                    remove(z - 1, x, BLOCKED_NORTH);
                }
            } else if (type == 1 || type == 3) {
                if (orientation == 0) {
                    remove(z, x, BLOCKED_NORTHWEST);
                    remove(z + 1, x - 1, BLOCKED_SOUTHEAST);
                } else if (orientation == 1) {
                    remove(z, x, BLOCKED_NORTHEAST);
                    remove(z + 1, x + 1, BLOCKED_SOUTHWEST);
                } else if (orientation == 2) {
                    remove(z, x, BLOCKED_SOUTHEAST);
                    remove(z - 1, x + 1, BLOCKED_NORTHWEST);
                } else if (orientation == 3) {
                    remove(z, x, BLOCKED_SOUTHWEST);
                    remove(z - 1, x - 1, BLOCKED_NORTHEAST);
                }
            } else if (type == 2) {
                if (orientation == 0) {
                    remove(z, x, BLOCKED_WEST | BLOCKED_NORTH);
                    remove(z, x - 1, BLOCKED_EAST);
                    remove(z + 1, x, BLOCKED_SOUTH);
                } else if (orientation == 1) {
                    remove(z, x, BLOCKED_EAST | BLOCKED_NORTH);
                    remove(z + 1, x, BLOCKED_SOUTH);
                    remove(z, x + 1, BLOCKED_WEST);
                } else if (orientation == 2) {
                    remove(z, x, BLOCKED_EAST | BLOCKED_SOUTH);
                    remove(z, x + 1, BLOCKED_WEST);
                    remove(z - 1, x, BLOCKED_NORTH);
                } else if (orientation == 3) {
                    remove(z, x, BLOCKED_WEST | BLOCKED_SOUTH);
                    remove(z - 1, x, BLOCKED_NORTH);
                    remove(z, x - 1, BLOCKED_EAST);
                }
            }
        }
    }

    public void removeLoc(int startZ, int startX, int orientation, int sizeX, boolean solid, int sizeZ) {
        int flag = OCCUPIED_TILE;

        if (solid) {
            flag += SOLID;
        }

        startX -= xOffset;
        startZ -= zOffset;

        if (orientation == 1 || orientation == 3) {
            int w = sizeX;
            sizeX = sizeZ;
            sizeZ = w;
        }

        for (int x = startX; x < startX + sizeX; x++) {
            if (x >= 0 && x < wide) {
                for (int z = startZ; z < startZ + sizeZ; z++) {
                    if (z >= 0 && z < tall) {
                        remove(z, x, flag);
                    }
                }
            }
        }
    }

    public void remove(int z, int x, int flag) {
        flags[x][z] &= 0xFFFFFF - flag;
    }

    public void removeBlock(int z, int x) {
        x -= xOffset;
        z -= zOffset;
        flags[x][z] &= 0xFFFFFF - BLOCKED_TILE;
    }

    public boolean reachedWall(int direction, int finalZ, int type, int initialZ, int finalX, int initialX) {
        if (initialX == finalX && initialZ == finalZ) {
            return true;
        }

        initialX -= xOffset;
        initialZ -= zOffset;
        finalX -= xOffset;
        finalZ -= zOffset;

        if (type == 0) {
            if (direction == 0) {
                if (initialX == finalX - 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x280120) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 1) {
                if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else
                    return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0;
            } else if (direction == 2) {
                if (initialX == finalX + 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x280120) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 3) {
                if (initialX == finalX && initialZ == finalZ - 1) {
                    return true;
                } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else
                    return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0;
            }
        } else if (type == 2) {
            if (direction == 0) {
                if (initialX == finalX - 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 1) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 2) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x280120) == 0) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1;
            } else if (direction == 3) {
                if (initialX == finalX - 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x280120) == 0) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1;
            }
        } else if (type == 9) {
            if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0) {
                return true;
            } else if (initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0) {
                return true;
            } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                return true;
            }

            return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_WEST) == 0;
        }

        return false;
    }

    public boolean reachedDecoration(int orientation, int type, int initialX, int finalX, int initialZ, int finalZ) {
        if (initialX == finalX && initialZ == finalZ) {
            return true;
        }

        initialX -= xOffset;
        initialZ -= zOffset;
        finalX -= xOffset;
        finalZ -= zOffset;

        if (type == 6 || type == 7) {
            if (type == 7) {
                orientation = orientation + 2 & 3;
            }

            if (orientation == 0) {
                if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_WEST) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0;
            } else if (orientation == 1) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0;
            } else if (orientation == 2) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0;
            } else if (orientation == 3) {
                if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_WEST) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0;
            }
        } else if (type == 8) {
            if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0) {
                return true;
            } else if (initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0) {
                return true;
            } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                return true;
            }

            return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_WEST) == 0;
        }

        return false;
    }

    public boolean reachedObject(int y, int depth, int x, int finalX, int surroundings, int finalZ, int width) {
        int maxX = (finalX + width) - 1;
        int maxZ = (finalZ + depth) - 1;

        if (x >= finalX && x <= maxX && y >= finalZ && y <= maxZ) {
            return true;
        } else if (x == finalX - 1 && y >= finalZ && y <= maxZ && (flags[x - xOffset][y - zOffset] & WALL_EAST) == 0 && (surroundings & 8) == 0) {
            return true;
        } else if (x == maxX + 1 && y >= finalZ && y <= maxZ && (flags[x - xOffset][y - zOffset] & WALL_WEST) == 0 && (surroundings & 2) == 0) {
            return true;
        } else if (y == finalZ - 1 && x >= finalX && x <= maxX && (flags[x - xOffset][y - zOffset] & WALL_NORTH) == 0 && (surroundings & 4) == 0) {
            return true;
        }

        return y == maxZ + 1 && x >= finalX && x <= maxX && (flags[x - xOffset][y - zOffset] & WALL_SOUTH) == 0 && (surroundings & 1) == 0;
    }

    public int xOffset;
    public int zOffset;
    public int wide;
    public int tall;
    public int[][] flags;
}
