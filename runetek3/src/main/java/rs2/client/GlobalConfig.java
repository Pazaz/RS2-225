package rs2.client;

import org.openrs2.deob.annotation.OriginalMember;

import java.math.BigInteger;

// Shared definitions so we can have the engine change certain behaviors without inheriting any client code
public class GlobalConfig {

	// smooths rotated minimaps/sprites
    public static boolean MINIMAP_BILINEAR_FILTERING = true;

	// the pixel offset was incremented before accessing and caused transparent edges to overlap. this flag post-increments instead
	public static boolean FIX_TRANSPARENCY_OVERFLOW = true;

	// the bounds were set to 512-1. this flag sets the bounds to 512
	public static boolean FULL_512PX_VIEWPORT = true;

	// in this revision item/spell highlighting wasn't a thing, that was added 1 month later in june 2004. this flag adds the later behavior in
	public static boolean SHOW_HIGHLIGHT_OUTLINE = true;

	// there were 2 different chatbox styles before this revision
	public static int CHATBOX_ERA = 3;

	// default server to connect to
	public static String SERVER_ADDRESS = "lostcity.runewiki.org";

	// default port to connect to
	public static int SERVER_WEB_PORT = 80;

	// http or https (for downloading and the websocket connection)
	public static String SERVER_WEB_SCHEMA = "http:";

	@OriginalMember(owner = "client!client", name = "ue", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

	@OriginalMember(owner = "client!client", name = "fh", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

}
