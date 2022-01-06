package com.runescape.scene;

public class CollisionMap {

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
                    flags[x][z] = 0xffffff;
                } else {
                    flags[x][z] = 0;
                }
            }
        }
    }

    public void setWall(int orientation, int z, int x, boolean blocks, int type) {
        x -= xOffset;
        z -= zOffset;

        if (type == 0) {
            if (orientation == 0) {
                add(x, z, 128);
                add(x - 1, z, 8);
            } else if (orientation == 1) {
                add(x, z, 2);
                add(x, z + 1, 32);
            } else if (orientation == 2) {
                add(x, z, 8);
                add(x + 1, z, 128);
            } else if (orientation == 3) {
                add(x, z, 32);
                add(x, z - 1, 2);
            }
        } else if (type == 1 || type == 3) {
            if (orientation == 0) {
                add(x, z, 1);
                add(x - 1, z + 1, 16);
            } else if (orientation == 1) {
                add(x, z, 4);
                add(x + 1, z + 1, 64);
            } else if (orientation == 2) {
                add(x, z, 16);
                add(x + 1, z - 1, 1);
            } else if (orientation == 3) {
                add(x, z, 64);
                add(x - 1, z - 1, 4);
            }
        } else if (type == 2) {
            if (orientation == 0) {
                add(x, z, 130);
                add(x - 1, z, 8);
                add(x, z + 1, 32);
            } else if (orientation == 1) {
                add(x, z, 10);
                add(x, z + 1, 32);
                add(x + 1, z, 128);
            } else if (orientation == 2) {
                add(x, z, 40);
                add(x + 1, z, 128);
                add(x, z - 1, 2);
            } else if (orientation == 3) {
                add(x, z, 160);
                add(x, z - 1, 2);
                add(x - 1, z, 8);
            }
        }

        if (blocks) {
            if (type == 0) {
                if (orientation == 0) {
                    add(x, z, 0x10000);
                    add(x - 1, z, 4096);
                } else if (orientation == 1) {
                    add(x, z, 1024);
                    add(x, z + 1, 16384);
                } else if (orientation == 2) {
                    add(x, z, 4096);
                    add(x + 1, z, 0x10000);
                } else if (orientation == 3) {
                    add(x, z, 16384);
                    add(x, z - 1, 1024);
                }
            } else if (type == 1 || type == 3) {
                if (orientation == 0) {
                    add(x, z, 512);
                    add(x - 1, z + 1, 8192);
                } else if (orientation == 1) {
                    add(x, z, 2048);
                    add(x + 1, z + 1, 32768);
                } else if (orientation == 2) {
                    add(x, z, 8192);
                    add(x + 1, z - 1, 512);
                } else if (orientation == 3) {
                    add(x, z, 32768);
                    add(x - 1, z - 1, 2048);
                }
            } else if (type == 2) {
                if (orientation == 0) {
                    add(x, z, 0x10400);
                    add(x - 1, z, 4096);
                    add(x, z + 1, 16384);
                } else if (orientation == 1) {
                    add(x, z, 5120);
                    add(x, z + 1, 16384);
                    add(x + 1, z, 0x10000);
                } else if (orientation == 2) {
                    add(x, z, 20480);
                    add(x + 1, z, 0x10000);
                    add(x, z - 1, 1024);
                } else if (orientation == 3) {
                    add(x, z, 0x14000);
                    add(x, z - 1, 1024);
                    add(x - 1, z, 4096);
                }
            }
        }
    }

    public void setLoc(int orientation, int sizeZ, int sizeX, int initialX, int initialZ, boolean solid) {
        int flag = 256;
        if (solid) {
            flag += 0x20000;
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
        flags[x][z] |= 0x200000;
    }

    public void add(int i, int j, int k) {
        flags[i][j] |= k;
    }

    public void removeWall(boolean blocks, int orientation, int x, int z, int type) {
        x -= xOffset;
        z -= zOffset;
        
        if (type == 0) {
            if (orientation == 0) {
                remove(z, x, 128);
                remove(z, x - 1, 8);
            } else if (orientation == 1) {
                remove(z, x, 2);
                remove(z + 1, x, 32);
            } else if (orientation == 2) {
                remove(z, x, 8);
                remove(z, x + 1, 128);
            } else if (orientation == 3) {
                remove(z, x, 32);
                remove(z - 1, x, 2);
            }
        } else if (type == 1 || type == 3) {
            if (orientation == 0) {
                remove(z, x, 1);
                remove(z + 1, x - 1, 16);
            } else if (orientation == 1) {
                remove(z, x, 4);
                remove(z + 1, x + 1, 64);
            } else if (orientation == 2) {
                remove(z, x, 16);
                remove(z - 1, x + 1, 1);
            } else if (orientation == 3) {
                remove(z, x, 64);
                remove(z - 1, x - 1, 4);
            }
        } else if (type == 2) {
            if (orientation == 0) {
                remove(z, x, 130);
                remove(z, x - 1, 8);
                remove(z + 1, x, 32);
            } else if (orientation == 1) {
                remove(z, x, 10);
                remove(z + 1, x, 32);
                remove(z, x + 1, 128);
            } else if (orientation == 2) {
                remove(z, x, 40);
                remove(z, x + 1, 128);
                remove(z - 1, x, 2);
            } else if (orientation == 3) {
                remove(z, x, 160);
                remove(z - 1, x, 2);
                remove(z, x - 1, 8);
            }
        }

        if (blocks) {
            if (type == 0) {
                if (orientation == 0) {
                    remove(z, x, 0x10000);
                    remove(z, x - 1, 4096);
                } else if (orientation == 1) {
                    remove(z, x, 1024);
                    remove(z + 1, x, 16384);
                } else if (orientation == 2) {
                    remove(z, x, 4096);
                    remove(z, x + 1, 0x10000);
                } else if (orientation == 3) {
                    remove(z, x, 16384);
                    remove(z - 1, x, 1024);
                }
            } else if (type == 1 || type == 3) {
                if (orientation == 0) {
                    remove(z, x, 512);
                    remove(z + 1, x - 1, 8192);
                } else if (orientation == 1) {
                    remove(z, x, 2048);
                    remove(z + 1, x + 1, 32768);
                } else if (orientation == 2) {
                    remove(z, x, 8192);
                    remove(z - 1, x + 1, 512);
                } else if (orientation == 3) {
                    remove(z, x, 32768);
                    remove(z - 1, x - 1, 2048);
                }
            } else if (type == 2) {
                if (orientation == 0) {
                    remove(z, x, 0x10400);
                    remove(z, x - 1, 4096);
                    remove(z + 1, x, 16384);
                } else if (orientation == 1) {
                    remove(z, x, 5120);
                    remove(z + 1, x, 16384);
                    remove(z, x + 1, 0x10000);
                } else if (orientation == 2) {
                    remove(z, x, 20480);
                    remove(z, x + 1, 0x10000);
                    remove(z - 1, x, 1024);
                } else if (orientation == 3) {
                    remove(z, x, 0x14000);
                    remove(z - 1, x, 1024);
                    remove(z, x - 1, 4096);
                }
            }
        }
    }

    public void removeLoc(int startZ, int startX, int orientation, int sizeX, boolean solid, int sizeZ) {
        int flag = 256;

        if (solid) {
            flag += 0x20000;
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
        flags[x][z] &= 0xffffff - flag;
    }

    public void removeBlock(int z, int x) {
        x -= xOffset;
        z -= zOffset;
        flags[x][z] &= 0xdfffff;
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
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 1) {
                if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0;
            } else if (direction == 2) {
                if (initialX == finalX + 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x280120) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 3) {
                if (initialX == finalX && initialZ == finalZ - 1) {
                    return true;
                } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0;
            }
        } else if (type == 2) {
            if (direction == 0) {
                if (initialX == finalX - 1 && initialZ == finalZ) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280180) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
            } else if (direction == 1) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x280108) == 0) {
                    return true;
                } else if (initialX == finalX && initialZ == finalZ + 1) {
                    return true;
                } else if (initialX == finalX + 1 && initialZ == finalZ) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 0x280102) == 0;
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
            if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x20) == 0) {
                return true;
            } else if (initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 2) == 0) {
                return true;
            } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 8) == 0) {
                return true;
            }

            return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x80) == 0;
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
                if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x80) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 2) == 0;
            } else if (orientation == 1) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 8) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 2) == 0;
            } else if (orientation == 2) {
                if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 8) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x20) == 0;
            } else if (orientation == 3) {
                if (initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x80) == 0) {
                    return true;
                } else return initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x20) == 0;
            }
        } else if (type == 8) {
            if (initialX == finalX && initialZ == finalZ + 1 && (flags[initialX][initialZ] & 0x20) == 0) {
                return true;
            } else if (initialX == finalX && initialZ == finalZ - 1 && (flags[initialX][initialZ] & 2) == 0) {
                return true;
            } else if (initialX == finalX - 1 && initialZ == finalZ && (flags[initialX][initialZ] & 8) == 0) {
                return true;
            }

            return initialX == finalX + 1 && initialZ == finalZ && (flags[initialX][initialZ] & 0x80) == 0;
        }

        return false;
    }

    public boolean reachedObject(int y, int depth, int x, int finalX, int surroundings, int finalZ, int width) {
        int maxX = (finalX + width) - 1;
        int maxZ = (finalZ + depth) - 1;

        if (x >= finalX && x <= maxX && y >= finalZ && y <= maxZ) {
            return true;
        } else if (x == finalX - 1 && y >= finalZ && y <= maxZ && (flags[x - xOffset][y - zOffset] & 8) == 0 && (surroundings & 8) == 0) {
            return true;
        } else if (x == maxX + 1 && y >= finalZ && y <= maxZ && (flags[x - xOffset][y - zOffset] & 0x80) == 0 && (surroundings & 2) == 0) {
            return true;
        } else if (y == finalZ - 1 && x >= finalX && x <= maxX && (flags[x - xOffset][y - zOffset] & 2) == 0 && (surroundings & 4) == 0) {
            return true;
        }

        return y == maxZ + 1 && x >= finalX && x <= maxX && (flags[x - xOffset][y - zOffset] & 0x20) == 0 && (surroundings & 1) == 0;
    }

    public int xOffset;
    public int zOffset;
    public int wide;
    public int tall;
    public int[][] flags;
}
