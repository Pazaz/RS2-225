package com.jagex.runetek3.scene;

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
    public int xOffset;
    public int zOffset;
    public int wide;
    public int tall;
    public int[][] flags;

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

    public void setWall(int x, int z, int type, int orientation, boolean blocks) {
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

    public void setLoc(int initialX, int initialZ, int sizeX, int sizeZ, int orientation, boolean solid) {
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

    public void setBlocked(int x, int z) {
        x -= xOffset;
        z -= zOffset;
        flags[x][z] |= BLOCKED_TILE;
    }

    public void add(int i, int j, int k) {
        flags[i][j] |= k;
    }

    public void removeWall(int x, int z, int type, int orientation, boolean blocks) {
        x -= xOffset;
        z -= zOffset;

        if (type == 0) {
            if (orientation == 0) {
                remove(x, z, WALL_WEST);
                remove(x - 1, z, WALL_EAST);
            } else if (orientation == 1) {
                remove(x, z, WALL_NORTH);
                remove(x, z + 1, WALL_SOUTH);
            } else if (orientation == 2) {
                remove(x, z, WALL_EAST);
                remove(x + 1, z, WALL_WEST);
            } else if (orientation == 3) {
                remove(x, z, WALL_SOUTH);
                remove(x, z - 1, WALL_NORTH);
            }
        } else if (type == 1 || type == 3) {
            if (orientation == 0) {
                remove(x, z, WALL_NORTHWEST);
                remove(x - 1, z + 1, WALL_SOUTHEAST);
            } else if (orientation == 1) {
                remove(x, z, WALL_NORTHEAST);
                remove(x + 1, z + 1, WALL_SOUTHWEST);
            } else if (orientation == 2) {
                remove(x, z, WALL_SOUTHEAST);
                remove(x + 1, z - 1, WALL_NORTHWEST);
            } else if (orientation == 3) {
                remove(x, z, WALL_SOUTHWEST);
                remove(x - 1, z - 1, WALL_NORTHEAST);
            }
        } else if (type == 2) {
            if (orientation == 0) {
                remove(x, z, WALL_WEST | WALL_NORTH);
                remove(x - 1, z, WALL_EAST);
                remove(x, z + 1, WALL_SOUTH);
            } else if (orientation == 1) {
                remove(x, z, WALL_EAST | WALL_NORTH);
                remove(x, z + 1, WALL_SOUTH);
                remove(x + 1, z, WALL_WEST);
            } else if (orientation == 2) {
                remove(x, z, WALL_EAST | WALL_SOUTH);
                remove(x + 1, z, WALL_WEST);
                remove(x, z - 1, WALL_NORTH);
            } else if (orientation == 3) {
                remove(x, z, WALL_WEST | WALL_SOUTH);
                remove(x, z - 1, WALL_NORTH);
                remove(x - 1, z, WALL_EAST);
            }
        }

        if (blocks) {
            if (type == 0) {
                if (orientation == 0) {
                    remove(x, z, BLOCKED_WEST);
                    remove(x - 1, z, BLOCKED_EAST);
                } else if (orientation == 1) {
                    remove(x, z, BLOCKED_NORTH);
                    remove(x, z + 1, BLOCKED_SOUTH);
                } else if (orientation == 2) {
                    remove(x, z, BLOCKED_EAST);
                    remove(x + 1, z, BLOCKED_WEST);
                } else if (orientation == 3) {
                    remove(x, z, BLOCKED_SOUTH);
                    remove(x, z - 1, BLOCKED_NORTH);
                }
            } else if (type == 1 || type == 3) {
                if (orientation == 0) {
                    remove(x, z, BLOCKED_NORTHWEST);
                    remove(x - 1, z + 1, BLOCKED_SOUTHEAST);
                } else if (orientation == 1) {
                    remove(x, z, BLOCKED_NORTHEAST);
                    remove(x + 1, z + 1, BLOCKED_SOUTHWEST);
                } else if (orientation == 2) {
                    remove(x, z, BLOCKED_SOUTHEAST);
                    remove(x + 1, z - 1, BLOCKED_NORTHWEST);
                } else if (orientation == 3) {
                    remove(x, z, BLOCKED_SOUTHWEST);
                    remove(x - 1, z - 1, BLOCKED_NORTHEAST);
                }
            } else if (type == 2) {
                if (orientation == 0) {
                    remove(x, z, BLOCKED_WEST | BLOCKED_NORTH);
                    remove(x - 1, z, BLOCKED_EAST);
                    remove(x, z + 1, BLOCKED_SOUTH);
                } else if (orientation == 1) {
                    remove(x, z, BLOCKED_EAST | BLOCKED_NORTH);
                    remove(x, z + 1, BLOCKED_SOUTH);
                    remove(x + 1, z, BLOCKED_WEST);
                } else if (orientation == 2) {
                    remove(x, z, BLOCKED_EAST | BLOCKED_SOUTH);
                    remove(x + 1, z, BLOCKED_WEST);
                    remove(x, z - 1, BLOCKED_NORTH);
                } else if (orientation == 3) {
                    remove(x, z, BLOCKED_WEST | BLOCKED_SOUTH);
                    remove(x, z - 1, BLOCKED_NORTH);
                    remove(x - 1, z, BLOCKED_EAST);
                }
            }
        }
    }

    public void removeLoc(int startZ, int startX, int sizeX, int sizeZ, int orientation, boolean solid) {
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
                        remove(x, z, flag);
                    }
                }
            }
        }
    }

    public void remove(int x, int z, int flag) {
        flags[x][z] &= 0xFFFFFF - flag;
    }

    public void removeBlock(int x, int z) {
        x -= xOffset;
        z -= zOffset;
        flags[x][z] &= 0xFFFFFF - BLOCKED_TILE;
    }

    public boolean reachedWall(int initialX, int initialZ, int finalX, int finalZ, int direction, int type) {
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

    public boolean reachedDecoration(int initialX, int initialZ, int finalX, int finalZ, int orientation, int type) {
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
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0;
            } else if (orientation == 1) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & WALL_NORTH) == 0;
            } else if (orientation == 2) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_EAST) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0;
            } else if (orientation == 3) {
                if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & WALL_WEST) == 0) {
                    return true;
                } else
                    return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & WALL_SOUTH) == 0;
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

    public boolean reachedObject(int x, int z, int width, int depth, int finalX, int finalZ, int surroundings) {
        int maxX = (finalX + width) - 1;
        int maxZ = (finalZ + depth) - 1;

        if (x >= finalX && x <= maxX && z >= finalZ && z <= maxZ) {
            return true;
        } else if (x == finalX - 1 && z >= finalZ && z <= maxZ && (flags[x - xOffset][z - zOffset] & WALL_EAST) == 0 && (surroundings & 8) == 0) {
            return true;
        } else if (x == maxX + 1 && z >= finalZ && z <= maxZ && (flags[x - xOffset][z - zOffset] & WALL_WEST) == 0 && (surroundings & 2) == 0) {
            return true;
        } else if (z == finalZ - 1 && x >= finalX && x <= maxX && (flags[x - xOffset][z - zOffset] & WALL_NORTH) == 0 && (surroundings & 4) == 0) {
            return true;
        }

        return z == maxZ + 1 && x >= finalX && x <= maxX && (flags[x - xOffset][z - zOffset] & WALL_SOUTH) == 0 && (surroundings & 1) == 0;
    }
}
