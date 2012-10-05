package vazkii.easyfps.client;

import java.io.File;
import java.util.TreeMap;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class EasyFPSConfig extends Configuration {

	public final String CATEGORY_COLORS = "colors";

	public TreeMap<String, Property> colorProps = new TreeMap();

	// Props Start
	// ============================================================================
	public static boolean averageFPSEnabled = true;
	public static boolean pingMeterEnabled = true;

	public static String hex_default = "FFFFFF";
	public static String hex_lessThan20 = "FF0400";
	public static String hex_20to30 = "FF5C05";
	public static String hex_30to60 = "FFF30F";
	public static String hex_60to120 = "43FF00";
	public static String hex_over120 = "00D4FF";

	// Props End
	// ==============================================================================

	public EasyFPSConfig(File file) {
		super(file);
		categories.put(CATEGORY_COLORS, colorProps);
		categories.remove(CATEGORY_BLOCK);
		categories.remove(CATEGORY_ITEM);

		load();

		Property propAverageFPSEnabled = getOrCreateBooleanProperty("averageFPSEnabled", CATEGORY_GENERAL, true);
		propAverageFPSEnabled.comment = "Set to true to enable the Average FPS Indicator.";
		averageFPSEnabled = propAverageFPSEnabled.getBoolean(true);

		Property propPingMeterEnabled = getOrCreateBooleanProperty("pingMeterEnabled", CATEGORY_GENERAL, true);
		propPingMeterEnabled.comment = "Set to true to enable the Ping Meter on SMP.";
		pingMeterEnabled = propPingMeterEnabled.getBoolean(true);

		Property propHexDefault = getOrCreateProperty("default", CATEGORY_COLORS, "FFFFFF");
		propHexDefault.comment = "The Hex code of the default FPS meter color.";
		hex_default = propHexDefault.value;

		Property propHexLessThan20 = getOrCreateProperty("fpsUnder20", CATEGORY_COLORS, "FF0400");
		propHexLessThan20.comment = "The Hex code of the FPS meter color when FPS is under 20.";
		hex_lessThan20 = propHexLessThan20.value;

		Property propHex20to30 = getOrCreateProperty("fps20to30", CATEGORY_COLORS, "FF5C05");
		propHex20to30.comment = "The Hex code of the FPS meter color when FPS is under 30 and over 20.";
		hex_20to30 = propHex20to30.value;

		Property propHex30to60 = getOrCreateProperty("fps30to60", CATEGORY_COLORS, "FFF30F");
		propHex30to60.comment = "The Hex code of the FPS meter color when FPS is under 60 and over 30.";
		hex_30to60 = propHex30to60.value;

		Property propHex60to120 = getOrCreateProperty("fps60to120", CATEGORY_COLORS, "43FF00");
		propHex60to120.comment = "The Hex code of the FPS meter color when FPS is under 120 and over 60.";
		hex_60to120 = propHex60to120.value;

		Property propHexOver120 = getOrCreateProperty("fpsOver120", CATEGORY_COLORS, "00D4FF");
		propHexOver120.comment = "The Hex code of the FPS meter color when FPS is over 120.";
		hex_over120 = propHexOver120.value;

		save();
	}

}
