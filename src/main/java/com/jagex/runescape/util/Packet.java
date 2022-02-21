package com.jagex.runescape.util;

public class Packet {

    // Outbound Packets
    public static final int ANTICHEAT_PLAYER_ACTION_2 = 2;
    public static final int CHAT_COMMAND = 4;
    public static final int ITEM_ACTION_5 = 6;
    public static final int ANTICHEAT_OBJECT_ACTION_4 = 7;
    public static final int NPC_ACTION_2 = 8;
    public static final int MAGIC_ON_OBJECT = 9;
    public static final int SOCIAL_FRIEND_REMOVE = 11;
    public static final int ANTICHEAT_ITEM_ACTION_5 = 17;
    public static final int NPC_ACTION_3 = 27;
    public static final int ANTICHEAT_ITEM_OPTION_1 = 30;
    public static final int ITEM_ACTION_1 = 31;
    public static final int ITEM_ACTION_4 = 38;
    public static final int GROUND_ITEM_5 = 40;
    public static final int MAGIC_ON_ITEM = 48;
    public static final int INTERFACE_DESIGN = 52;
    public static final int PLAYER_ACTION_2 = 53;
    public static final int ITEM_ACTION_2 = 59;
    public static final int ANTICHEAT_OBJECT_ACTION_5 = 66;
    public static final int IDLE_TIMER = 70;
    public static final int ITEM_OPTION_2 = 71;
    public static final int ITEM_ON_OBJECT = 75;
    public static final int SOCIAL_IGNORE_ADD = 79;
    public static final int TRACKING_DATA = 81;
    public static final int ANTICHEAT_UPDATE_LOC = 85;
    public static final int ANTICHEAT_NPC_ACTION_3 = 88;
    public static final int MOVEMENT_WORLD_ACTION = 93;
    public static final int OBJECT_ACTION_3 = 96;
    public static final int OBJECT_ACTION_4 = 97;
    public static final int NPC_ACTION_5 = 100;
    public static final int KEEPALIVE = 108;
    public static final int NPC_ACTION_4 = 113;
    public static final int OBJECT_ACTION_5 = 116;
    public static final int SOCIAL_FRIEND_ADD = 118;
    public static final int ITEM_ON_ITEM = 130;
    public static final int ITEM_OPTION_3 = 133;
    public static final int MAGIC_ON_NPC = 134;
    public static final int MAGIC_ON_GROUND_ITEM = 138;
    public static final int GROUND_ITEM_1 = 140;
    public static final int ANTICHEAT_UPDATE_VIEWPORT = 146;
    public static final int CHAT_PRIVATE = 148;
    public static final int REQUEST_MAP = 150;
    public static final int INTERFACE_BUTTON = 155;
    public static final int ITEM_OPTION_4 = 157;
    public static final int CHAT_PUBLIC = 158;
    public static final int ITEM_MOVE = 159;
    public static final int PLAYER_ACTION_1 = 164;
    public static final int MOVEMENT_MINIMAP = 165;
    public static final int SOCIAL_IGNORE_REMOVE = 171;
    public static final int OBJECT_ACTION_2 = 172;
    public static final int INTERFACE_FLASHING_TAB_CLICKED = 175;
    public static final int ANTICHEAT_NPC_ACTION_5 = 176;
    public static final int MAGIC_ON_PLAYER = 177;
    public static final int GROUND_ITEM_ACTION = 178;
    public static final int MOVEMENT_WORLD = 181;
    public static final int PLAYER_ACTION_3 = 185;
    public static final int CAMERA_MOVEMENT = 189;
    public static final int REPORT_ABUSE = 190;
    public static final int NPC_ACTION_1 = 194;
    public static final int ITEM_OPTION_1 = 195;
    public static final int PICKUP_GROUND_ITEM = 200;
    public static final int ITEM_ON_NPC = 202;
    public static final int PLAYER_ACTION_4 = 206;
    public static final int ITEM_OPTION_5 = 211;
    public static final int ITEM_ACTION_3 = 212;
    public static final int ANTICHEAT_UPDATE_GAME = 215;
    public static final int ANTICHEAT_UPDATE_PLAYERS = 219;
    public static final int ANTICHEAT_ITEM_OPTION_4 = 220;
    public static final int INTERFACE_CLOSED = 231;
    public static final int ANTICHEAT_SIDEBAR_CLICKED = 233;
    public static final int INTERFACE_CONTINUE = 235;
    public static final int ANTICHEAT_UPDATE_GAME_2 = 236;
    public static final int INTERFACE_ENTERED_AMOUNT = 237;
    public static final int ANTICHEAT_ITEM_ACTION_4 = 238;
    public static final int ITEM_ON_GROUND_ITEM = 239;
    public static final int PRIVACY_OPTIONS = 244;
    public static final int OBJECT_ACTION_1 = 245;
    public static final int LIGHT_ITEM = 247;
    public static final int ITEM_ON_PLAYER = 248;

    // Inbound Packets

    // format: 225 opcode = 317 opcode
    // (?) means it might be incorrectly matched and should be tested
    // this is just a helpful reference for me and will be removed at some point
    public static final int UPDATE_NPCS = 1;
    // 2 = 122
    public static final int INTERFACE_COLOR = 2;
    // 3 = 166
    public static final int CAMERA_SPIN = 3;
    public static final int SEND_MESSAGE = 4;
    // 7 = 85 (?)
    public static final int PLAYER_POSITION = 7;
    // 12 = 174
    public static final int PLAY_SOUND = 12;
    // 13 = 35
    public static final int CAMERA_SHAKE = 13;
    // 14 = 164
    public static final int INTERFACE_CHATBOX = 14;
    // 15 = 72
    public static final int INTERFACE_ITEMS_CLEAR = 15;
    // 19 = 78
    public static final int CLEAR_WALKING_QUEUE = 19;
    public static final int DATA_LOC_DONE = 20;
    // 21 = 214
    public static final int ADD_IGNORE = 21;
    // 22 = 110
    public static final int PLAYER_ENERGY = 22;
    // 25 = 254
    public static final int MOB_HINT = 25;
    // 26 = 171
    public static final int INTERFACE_HOVER = 26;
    // 28 = 248
    public static final int INTERFACE_CHILD = 28;
    // 32 = 206
    public static final int INTERFACE_PRIVACY = 32;
    // 41 = 196
    public static final int PRIVATE_MESSAGE = 41;
    // 43 = 114
    public static final int SYSTEM_UPDATE = 43;
    // 44 = 134
    public static final int GROUND_ITEM_ADD = 44;
    // 46 = 246 (?)
    public static final int INTERFACE_ITEM_MODEL = 46;
    public static final int PLAY_SONG = 54;
    // 68 = 240
    public static final int PLAYER_WEIGHT = 68;
    // 74 = 177
    public static final int CAMERA_CUTSCENE = 74;
    public static final int DATA_LAND_DONE = 80;
    // 84 = 106
    public static final int INTERFACE_SIDEBAR_FOCUS = 84;
    // 87 = 8 (?)
    public static final int INTERFACE_MODEL = 87;
    // 98 = 34 (?)
    public static final int INTERFACE_ITEM_SLOT = 98;
    public static final int INTERFACE_MODEL_RECOLOR = 103;
    // 126 = 24
    public static final int INTERFACE_SIDEBAR_FLASH = 126;
    // 129 = 219
    public static final int INTERFACE_CLEAR = 129;
    public static final int DATA_LAND = 132;
    public static final int FINISH_TRACKING = 133;
    // 135 = 64
    public static final int GROUND_ITEM_REMOVE_ALL = 135;
    // 136 = 1
    public static final int RESET_ANIMATIONS = 136;
    // 139 = 249
    public static final int PLAYER_INFO = 139;
    public static final int SHOW_WELCOME = 140;
    // 142 = 109
    public static final int LOGOUT = 142;
    // 146 = 200 (?) (could be swapped with 87)
    public static final int INTERFACE_ANIMATE = 146;
    // 150 = 36
    public static final int INTERFACE_SETTING_BYTE = 150;
    // 152 = 50
    public static final int ADD_FRIEND = 152;
    // 162 = 60
    public static final int BATCH_PACKETS = 162;
    // 167 = 71
    public static final int INTERFACE_SIDEBAR = 167;
    // 168 = 97
    public static final int INTERFACE_OPEN = 168;
    // 175 = 87
    public static final int INTERFACE_SETTING_DWORD = 175;
    public static final int UPDATE_PLAYERS = 184;
    // 185 = 218
    public static final int INTERFACE_DIALOGUE = 185;
    // 193 = 68
    public static final int INTERFACE_SETTINGS_RESET = 193;
    // 195 = 142
    public static final int INTERFACE_INVENTORY = 195;
    // 197 = 185
    public static final int INTERFACE_PLAYERHEAD = 197;
    // 201 = 126
    public static final int INTERFACE_TEXT = 201;
    // 204 = 75 (?)
    public static final int INTERFACE_NPCHEAD = 204;
    // 209 = 70
    public static final int INTERFACE_XY = 209;
    public static final int PLAY_JINGLE = 212;
    // 213 = 53 (?)
    public static final int INTERFACE_ITEM_ARRAY = 213;
    public static final int DATA_LOC = 220;
    public static final int ENABLE_TRACKING = 226;
    public static final int LOAD_AREA = 237;
    // 239 = 107
    public static final int CAMERA_RESET = 239;
    // 243 = 27
    public static final int INTERFACE_AMOUNT = 243;
    // 254 = 61 (?)
    public static final int DISPLAY_MULTI_ICON = 254;

    // Secondary packets
    public static final int ATTACH_TEMPORARY_LOCATION_TO_PLAYER = 23;
    public static final int ADD_ANIMATED_LOCATION = 42;
    public static final int REMOVE_OBJECT_STACK = 49;
    public static final int ADD_PRIVATE_OBJECT_STACK = 50;
    public static final int ADD_LOCATION = 59;
    public static final int ADD_PROJECTILE = 69;
    public static final int REMOVE_LOCATION = 76;
    public static final int UPDATE_OBJECT_STACK = 151;
    public static final int ADD_SPOT_ANIMATION = 191;
    public static final int ADD_OBJECT_STACK = 223;

    // Custom packets
    public static final int CHANGE_SKY = 5;

    public static final int[] PACKET_ORDER = {
        95, 218, 67, 50, 253, 222, 194, 60, 101, 128,
        8, 251, 92, 111, 24, 33, 223, 66, 232, 59,
        227, 113, 153, 105, 126, 98, 167, 102, 177, 238,
        62, 190, 147, 23, 150, 151, 156, 144, 193, 155,
        81, 0, 198, 22, 137, 210, 179, 16, 168, 170,
        32, 181, 248, 141, 58, 87, 208, 106, 180, 191,
        221, 241, 40, 176, 196, 154, 65, 145, 230, 78,
        30, 161, 188, 41, 14, 129, 18, 199, 47, 247,
        225, 34, 51, 10, 159, 75, 12, 56, 61, 31,
        39, 91, 46, 242, 134, 5, 122, 123, 209, 228,
        104, 195, 21, 3, 11, 44, 107, 172, 6, 186,
        110, 215, 205, 103, 27, 185, 124, 77, 252, 117,
        86, 115, 127, 207, 52, 79, 43, 97, 219, 116,
        169, 7, 118, 162, 108, 36, 20, 233, 88, 135,
        80, 19, 42, 237, 57, 152, 71, 9, 250, 17,
        4, 119, 234, 130, 26, 200, 189, 163, 254, 245,
        197, 171, 220, 235, 140, 244, 184, 94, 211, 231,
        99, 246, 121, 212, 112, 204, 63, 148, 83, 178,
        1, 255, 131, 13, 183, 142, 236, 45, 55, 35,
        243, 136, 37, 85, 100, 160, 38, 224, 146, 174,
        82, 48, 109, 132, 125, 90, 143, 138, 240, 173,
        165, 164, 192, 175, 29, 74, 28, 114, 213, 73,
        64, 206, 76, 139, 96, 2, 229, 15, 93, 25,
        239, 202, 49, 70, 214, 201, 72, 203, 68, 89,
        69, 157, 216, 217, 249, 120, 226, 84, 149, 187,
        54, 53, 158, 166, 182, 133, 0
    };

    public static final int[] PACKET_LENGTH = {
        0, -2, 4, 6, -1, 3, 0, 2, 0, 0,
        0, 0, 5, 4, 2, 2, 0, 0, 0, 0,
        2, -2, 2, 14, 0, 6, 3, 0, 4, 0,
        0, 0, 3, 0, 0, 0, 0, 0, 0, 0,
        0, -1, 4, 2, 6, 0, 6, 0, 0, 3,
        7, 0, 0, 0, -1, 0, 0, 0, 0, 4,
        0, 0, 0, 0, 0, 0, 0, 0, 1, 15,
        0, 0, 0, 0, 6, 0, 2, 0, 0, 0,
        2, 0, 0, 0, 1, 0, 0, 4, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, -2, 0,
        0, 0, 0, 6, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
        0, 0, -2, 0, 0, 2, 0, 0, 0, 2,
        9, 0, 0, 0, 0, 0, 4, 0, 0, 0,
        3, 7, 9, 0, 0, 0, 0, 0, 0, 0,
        0, 0, -2, 0, 0, 0, 0, 3, 2, 0,
        0, 0, 0, 0, 0, 6, 0, 0, 0, 0,
        0, 0, 0, 0, -2, 2, 0, 0, 0, 0,
        0, 6, 0, 0, 0, 2, 0, 2, 0, 0,
        0, -2, 0, 0, 4, 0, 0, 0, 0, 6,
        0, 0, -2, -2, 0, 0, 0, 0, 0, 0,
        -2, 0, 0, 5, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, -2, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 1, 0, 0
    };
}
