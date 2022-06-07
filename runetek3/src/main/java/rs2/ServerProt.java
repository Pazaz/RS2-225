package rs2;

import org.openrs2.deob.annotation.OriginalMember;

public class ServerProt {

    @OriginalMember(owner = "client!dc", name = "b", descriptor = "[I")
    public static final int[] PACKET_LENGTHS = new int[] { 0, -2, 4, 6, -1, 0, 0, 2, 0, 0, 0, 0, 5, 4, 2, 2, 0, 0, 0, 0, 2, -2, 2, 14, 0, 6, 3, 0, 4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, -1, 4, 2, 6, 0, 6, 0, 0, 3, 7, 0, 0, 0, -1, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 15, 0, 0, 0, 0, 6, 0, 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, -2, 0, 0, 2, 0, 0, 0, 2, 9, 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, -2, 2, 0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 0, 2, 0, 0, 0, -2, 0, 0, 4, 0, 0, 0, 0, 6, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, -2, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 };

    public static final int IF_SETCOLOUR = 2; // not sure if this is supposed to represent text color or model color
    public static final int IF_OPENBOTTOM = 14; // not an original name
    public static final int IF_OPENSUB = 28;
    public static final int IF_SETHIDE = 26;
    public static final int IF_SETOBJECT = 46;
    public static final int IF_SETTAB_ACTIVE = 84; // not an original name
    public static final int IF_SETMODEL = 87;
    public static final int IF_SETMODEL_COLOUR = 103; // not an original name
    public static final int IF_SETTAB_FLASH = 126; // not an original name
    public static final int IF_CLOSESUB = 129;
    public static final int IF_SETANIM = 146;
    public static final int IF_SETTAB = 167; // not an original name
    public static final int IF_OPENTOP = 168;
    public static final int IF_OPENSTICKY = 185; // not an original name
    public static final int IF_OPENSIDEBAR = 195;
    public static final int IF_SETPLAYERHEAD = 197;
    public static final int IF_SETTEXT = 201;
    public static final int IF_SETNPCHEAD = 204;
    public static final int IF_SETPOSITION = 209;
    public static final int IF_IAMOUNT = 243; // not an original name
    public static final int IF_MULTIZONE = 254; // not an original name

    public static final int UPDATE_INV_CLEAR = 15;
    public static final int UPDATE_INV_FULL = 98;
    public static final int UPDATE_INV_PARTIAL = 213;

    public static final int CAM_FORCEANGLE = 3;
    public static final int CAM_SHAKE = 13;
    public static final int CAM_MOVETO = 74;
    public static final int CAM_RESET = 239;

    public static final int NPC_INFO = 1;
    public static final int PLAYER_INFO = 184;

    public static final int CLEAR_WALKING_QUEUE = 19;
    public static final int UPDATE_RUNWEIGHT = 22;
    public static final int HINT_ARROW = 25;
    public static final int UPDATE_REBOOT_TIMER = 43;
    public static final int UPDATE_STAT = 44;
    public static final int UPDATE_RUNENERGY = 68;
    public static final int FINISH_TRACKING = 133; // not an original name. tell the client to flush input events
    public static final int RESET_ANIMS = 136;
    public static final int UPDATE_UID192 = 139;
    public static final int LAST_LOGIN_INFO = 140;
    public static final int LOGOUT = 142;
    public static final int ENABLE_TRACKING = 226; // not an original name. tell the client to track input events

    public static final int MESSAGE_GAME = 4;
    public static final int UPDATE_IGNORELIST = 21;
    public static final int CHAT_FILTER_SETTINGS = 32;
    public static final int MESSAGE_PRIVATE = 41;
    public static final int UPDATE_FRIENDLIST = 152;

    // none of these DATA_ packets are original names
    public static final int DATA_LOC_DONE = 20;
    public static final int DATA_LAND_DONE = 80;
    public static final int DATA_LAND = 132;
    public static final int DATA_LOC = 220;
    public static final int LOAD_AREA = 237;

    public static final int VARP_SMALL = 150;
    public static final int VARP_LARGE = 175;
    public static final int RESET_CLIENT_VARCACHE = 193;

    public static final int SYNTH_SOUND = 12;
    public static final int MIDI_SONG = 54;
    public static final int MIDI_JINGLE = 212;

    public static final int UPDATE_ZONE_PARTIAL_FOLLOWS = 7;
    public static final int UPDATE_ZONE_FULL_FOLLOWS = 135;
    public static final int UPDATE_ZONE_PARTIAL_ENCLOSED = 162;

    // Zone packets
    public static final int LOC_ADD_CHANGE = 23; // not confident about the naming. Affects "temporary" locs instead of LOC_ADD "spawned" locs
    public static final int LOC_ANIM = 42;
    public static final int OBJ_DEL = 49;
    public static final int OBJ_ADD = 50;
    public static final int LOC_ADD = 59;
    public static final int MAP_PROJANIM = 69;
    public static final int LOC_DEL = 76;
    public static final int OBJ_COUNT = 151;
    public static final int SPOTANIM_SPECIFIC = 191;
    public static final int OBJ_REVEAL = 223;

}
