package rs2.client;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.math.BigInteger;

// Shared definitions so we can have the engine change certain behaviors without inheriting any client code
public class GlobalConfig {

	// smooths rotated minimaps/sprites
    public static boolean MINIMAP_BILINEAR_FILTERING = true;

	// the pixel offset was incremented before accessing and caused transparent edges to overlap, this flag post-increments
	public static boolean FIX_TRANSPARENCY_OVERFLOW = true;

	// the bounds were set to 512-1, this flag sets the bounds to 512
	public static boolean FULL_512PX_VIEWPORT = true;

	// public static final String SERVER_ADDRESS = "localhost";
	public static final String SERVER_ADDRESS = "lostcity.runewiki.org";

	@OriginalMember(owner = "client!client", name = "ue", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

	@OriginalMember(owner = "client!client", name = "fh", descriptor = "Ljava/math/BigInteger;")
	public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

}
