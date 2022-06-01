package com.itspazaz.lostcity.utils;

public class Position {
    public static int region(int pos) {
        return pos >> 3;
    }

    public static int chunk(int pos) {
        return (pos >> 3) - 6;
    }

    public static int file(int pos) {
        return pos >> 6;
    }

    public static int local(int pos) {
        return pos - (chunk(pos) << 3);
    }

    public static int face(int srcX, int srcZ, int dstX, int dstZ) {
        if (srcX == dstX) {
            if (srcZ > dstZ) {
                return Direction.SOUTH;
            } else if (srcZ < dstZ) {
                return Direction.NORTH;
            }
        } else if (srcX > dstX) {
            if (srcZ > dstZ) {
                return Direction.SOUTH_WEST;
            } else if (srcZ < dstZ) {
                return Direction.NORTH_WEST;
            } else {
                return Direction.WEST;
            }
        } else {
            if (srcZ > dstZ) {
                return Direction.SOUTH_EAST;
            } else if (srcZ < dstZ) {
                return Direction.NORTH_EAST;
            } else {
                return Direction.EAST;
            }
        }

        return -1;
    }

    public static int moveX(int pos, int dir) {
        if (dir == Direction.EAST || dir == Direction.SOUTH_EAST || dir == Direction.NORTH_EAST) {
            return pos + 1;
        } else if (dir == Direction.WEST || dir == Direction.SOUTH_WEST || dir == Direction.NORTH_WEST) {
            return pos - 1;
        }
        return pos;
    }

    public static int moveZ(int pos, int dir) {
        if (dir == Direction.NORTH || dir == Direction.NORTH_WEST || dir == Direction.NORTH_EAST) {
            return pos + 1;
        } else if (dir == Direction.SOUTH || dir == Direction.SOUTH_WEST || dir == Direction.SOUTH_EAST) {
            return pos - 1;
        }
        return pos;
    }

}
