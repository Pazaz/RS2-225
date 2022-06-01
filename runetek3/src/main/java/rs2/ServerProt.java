package rs2;

import org.openrs2.deob.annotation.OriginalMember;

public class ServerProt {

    @OriginalMember(owner = "client!dc", name = "b", descriptor = "[I")
    public static final int[] PACKET_LENGTHS = new int[] { 0, -2, 4, 6, -1, 0, 0, 2, 0, 0, 0, 0, 5, 4, 2, 2, 0, 0, 0, 0, 2, -2, 2, 14, 0, 6, 3, 0, 4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, -1, 4, 2, 6, 0, 6, 0, 0, 3, 7, 0, 0, 0, -1, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 15, 0, 0, 0, 0, 6, 0, 2, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, -2, 0, 0, 2, 0, 0, 0, 2, 9, 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, -2, 2, 0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 0, 2, 0, 0, 0, -2, 0, 0, 4, 0, 0, 0, 0, 6, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, -2, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 };

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
    public static final int MESSAGE_PRIVATE = 41; // presumed NXT naming
    public static final int UPDATE_REBOOT_TIMER = 43; // NXT naming
    public static final int UPDATE_STAT = 44; // NXT naming
    public static final int INTERFACE_ITEM_MODEL = 46;
    public static final int MIDI_SONG = 54; // NXT naming
    public static final int UPDATE_RUNENERGY = 68; // NXT naming
    public static final int CAM_MOVETO = 74; // NXT naming
    public static final int DATA_LAND_DONE = 80;
    public static final int INTERFACE_SIDEBAR_FOCUS = 84;
    public static final int IF_SETMODEL = 87; // NXT naming
    public static final int UPDATE_INV_PARTIAL = 98; // NXT naming
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
    public static final int UPDATE_INV_FULL = 213; // NXT naming
    public static final int DATA_LOC = 220;
    public static final int ENABLE_TRACKING = 226;
    public static final int LOAD_AREA = 237;
    public static final int CAM_RESET = 239; // NXT naming
    public static final int IF_IAMOUNT = 243; // not NXT naming - prompt to input a number
    public static final int IF_MULTIZONE = 254; // not NXT naming - display multizone icon

    // Secondary packets
    public static final int LOC_TEMP_ADD = 23;
    public static final int LOC_ANIM = 42; // presumed NXT naming
    public static final int OBJSTACK_DEL = 49;
    public static final int OBJSTACK_PRIV_ADD = 50;
    public static final int LOC_ADD = 59;
    public static final int PROJ_ADD = 69;
    public static final int LOC_DEL = 76; // presumed NXT naming
    public static final int OBJSTACK_UPDATE = 151;
    public static final int SPOTANIM_SPECIFIC = 191; // presumed NXT naming
    public static final int OBJSTACK_ADD = 223;

}
