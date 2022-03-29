package com.jagex.runescape.util;

public class Packet {
    // OP* - Option <Type> <#>
    // OP*T* - Option <Type> Target (#)
    public class Client {
        // Outbound Packets
        public static final int ANTICHEAT_OPPLAYER2 = 2;
        public static final int CLIENT_CHEAT = 4; // NXT naming
        public static final int ITEM_ACTION_5 = 6;
        public static final int ANTICHEAT_OPOBJ4 = 7;
        public static final int OPNPC2 = 8; // NXT naming
        public static final int MAGIC_ON_OBJECT = 9;
        public static final int FRIENDLIST_DEL = 11; // NXT naming
        public static final int ANTICHEAT_ITEM_ACTION_5 = 17;
        public static final int OPNPC3 = 27; // NXT naming
        public static final int ANTICHEAT_ITEM_OPTION_1 = 30;
        public static final int ITEM_ACTION_1 = 31;
        public static final int ITEM_ACTION_4 = 38;
        public static final int GROUND_ITEM_5 = 40;
        public static final int MAGIC_ON_ITEM = 48;
        public static final int IF_DESIGN = 52; // not NXT naming, but fits the paradigm - design screen data
        public static final int OPPLAYER2 = 53; // NXT naming
        public static final int ITEM_ACTION_2 = 59;
        public static final int ANTICHEAT_OPOBJ5 = 66;
        public static final int IDLE_TIMER = 70;
        public static final int ITEM_OPTION_2 = 71;
        public static final int ITEM_ON_OBJECT = 75;
        public static final int IGNORELIST_ADD = 79; // NXT naming
        public static final int TRACKING_DATA = 81;
        public static final int ANTICHEAT_UPDATE_LOC = 85;
        public static final int ANTICHEAT_OPNPC3 = 88;
        public static final int MOVEMENT_WORLD_ACTION = 93;
        public static final int OPOBJ3 = 96; // NXT naming
        public static final int OPOBJ4 = 97; // NXT naming
        public static final int OPNPC5 = 100; // NXT naming
        public static final int KEEPALIVE = 108;
        public static final int OPNPC4 = 113; // NXT naming
        public static final int OPOBJ5 = 116; // NXT naming
        public static final int FRIENDLIST_ADD = 118; // NXT naming
        public static final int ITEM_ON_ITEM = 130;
        public static final int ITEM_OPTION_3 = 133;
        public static final int MAGIC_ON_NPC = 134;
        public static final int MAGIC_ON_GROUND_ITEM = 138;
        public static final int GROUND_ITEM_1 = 140;
        public static final int ANTICHEAT_UPDATE_VIEWPORT = 146;
        public static final int MESSAGE_PRIVATE = 148; // NXT naming
        public static final int REQUEST_MAP = 150;
        public static final int INTERFACE_BUTTON = 155;
        public static final int ITEM_OPTION_4 = 157;
        public static final int MESSAGE_PUBLIC = 158; // NXT naming
        public static final int ITEM_MOVE = 159;
        public static final int OPPLAYER1 = 164; // NXT naming
        public static final int MOVE_MINIMAPCLICK = 165; // NXT naming
        public static final int IGNORELIST_DEL = 171; // NXT naming
        public static final int OPOBJ2 = 172; // NXT naming
        public static final int INTERFACE_FLASHING_TAB_CLICKED = 175;
        public static final int ANTICHEAT_OPNPC5 = 176;
        public static final int MAGIC_ON_PLAYER = 177;
        public static final int GROUND_ITEM_ACTION = 178;
        public static final int MOVE_GAMECLICK = 181; // NXT naming
        public static final int OPPLAYER3 = 185; // NXT naming
        public static final int CAMERA_MOVEMENT = 189;
        public static final int REPORT_ABUSE = 190;
        public static final int OPNPC1 = 194; // NXT naming
        public static final int ITEM_OPTION_1 = 195;
        public static final int PICKUP_GROUND_ITEM = 200;
        public static final int ITEM_ON_NPC = 202;
        public static final int OPPLAYER4 = 206; // NXT naming
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
        public static final int OPOBJ1 = 245; // NXT naming
        public static final int LIGHT_ITEM = 247;
        public static final int ITEM_ON_PLAYER = 248;
    }

    // Inbound Packets

    public class Server {
        public static final int NPC_INFO = 1; // presumed NXT naming
        public static final int IF_SETCOLOUR = 2; // NXT naming
        public static final int CAM_FORCEANGLE = 3; // NXT naming
        public static final int MESSAGE_GAME = 4; // NXT naming
        public static final int PLAYER_POSITION = 7;
        public static final int SYNTH_SOUND = 12; // NXT naming
        public static final int CAM_SHAKE = 13; // NXT naming

        // Open an interface in the chatbox area
        public static final int IF_OPENBOTTOM = 14; // not NXT naming

        public static final int INTERFACE_ITEMS_CLEAR = 15;
        public static final int CLEAR_WALKING_QUEUE = 19;
        public static final int DATA_LOC_DONE = 20;
        public static final int UPDATE_IGNORELIST = 21; // NXT naming
        public static final int UPDATE_RUNWEIGHT = 22; // NXT naming
        public static final int HINT_ARROW = 25; // NXT naming
        public static final int INTERFACE_HOVER = 26;
        public static final int IF_OPENSUB = 28; // presumed NXT naming
        public static final int CHAT_FILTER_SETTINGS = 32; // presumed NXT naming
        public static final int MESSAGE_PRIVATE_ECHO = 41; // presumed NXT naming
        public static final int UPDATE_REBOOT_TIMER = 43; // NXT naming
        public static final int UPDATE_STAT = 44; // NXT naming
        public static final int INTERFACE_ITEM_MODEL = 46;
        public static final int MIDI_SONG = 54; // NXT naming
        public static final int UPDATE_RUNENERGY = 68; // NXT naming
        public static final int CAM_MOVETO = 74; // NXT naming
        public static final int DATA_LAND_DONE = 80;
        public static final int INTERFACE_SIDEBAR_FOCUS = 84;
        public static final int IF_SETMODEL = 87; // NXT naming
        public static final int UPDATE_INV_FULL = 98; // NXT naming
        public static final int INTERFACE_MODEL_RECOLOR = 103;
        public static final int INTERFACE_SIDEBAR_FLASH = 126;
        public static final int IF_HIDE = 129; // presumed NXT naming
        public static final int DATA_LAND = 132;
        public static final int FINISH_TRACKING = 133;
        public static final int GROUND_ITEM_REMOVE_ALL = 135;
        public static final int RESET_ANIMS = 136; // NXT naming
        public static final int UPDATE_UID192 = 139; // presumed NXT naming
        public static final int LAST_LOGIN_INFO = 140; // NXT naming
        public static final int LOGOUT = 142; // NXT naming
        public static final int IF_SETANIM = 146; // NXT naming
        public static final int VARP_SMALL = 150; // presumed NXT naming
        public static final int UPDATE_FRIENDLIST = 152; // NXT naming
        public static final int BATCH_PACKETS = 162;

        // Open an interface in the sidebar area
        public static final int IF_OPENSIDE = 167; // not NXT naming

        // Open an interface in the viewport area
        public static final int IF_OPENTOP = 168; // presumed NXT naming

        public static final int VARP_LARGE = 175; // presumed NXT naming
        public static final int PLAYER_INFO = 184; // presumed NXT naming
        public static final int INTERFACE_DIALOGUE = 185;
        public static final int RESET_CLIENT_VARCACHE = 193; // NXT naming
        public static final int INTERFACE_INVENTORY = 195;
        public static final int IF_SETPLAYERHEAD = 197; // NXT naming
        public static final int IF_SETTEXT = 201; // NXT naming
        public static final int IF_SETNPCHEAD = 204; // NXT naming
        public static final int IF_SETPOSITION = 209; // NXT naming
        public static final int MIDI_JINGLE = 212; // NXT naming
        public static final int UPDATE_INV_PARTIAL = 213; // NXT naming
        public static final int DATA_LOC = 220;
        public static final int ENABLE_TRACKING = 226;
        public static final int LOAD_AREA = 237;
        public static final int CAM_RESET = 239; // NXT naming
        public static final int IF_IAMOUNT = 243; // not NXT naming - prompt to input a number
        public static final int IF_MULTIZONE = 254; // not NXT naming - display multizone icon

        // Secondary packets
        public static final int ATTACH_TEMPORARY_LOCATION_TO_PLAYER = 23;
        public static final int LOC_ANIM = 42; // presumed NXT naming
        public static final int REMOVE_OBJECT_STACK = 49;
        public static final int ADD_PRIVATE_OBJECT_STACK = 50;
        public static final int ADD_LOCATION = 59;
        public static final int ADD_PROJECTILE = 69;
        public static final int LOC_DEL = 76; // presumed NXT naming
        public static final int UPDATE_OBJECT_STACK = 151;
        public static final int SPOTANIM_SPECIFIC = 191; // presumed NXT naming
        public static final int ADD_OBJECT_STACK = 223;

        // Custom packets
        public static final int CHANGE_SKY = 5;
    }

    // Not used in the client. Probably part of Jagex's original obfuscation process.
    // This array contains the original packet order before the opcodes got scrambled.
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
