package vazkii.easyfps.client;

import java.io.File;

import net.minecraft.src.NBTTagCompound;
import vazkii.codebase.client.CornerText;
import vazkii.codebase.common.CommonUtils;
import vazkii.codebase.common.EnumVazkiiMods;
import vazkii.codebase.common.IOUtils;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "easyfps_Vazkii", name = "EasyFPS", version = "by Vazkii. Version [2.0] for 1.3.2.") public class mod_EasyFPS {

	protected static boolean fpsEnabled = true;
	protected static boolean colorEnabled = true;

	@Init
	public void onInit(FMLInitializationEvent event) {
		if (!CommonUtils.getSide().isClient()) return;

		File configFile = IOUtils.getConfigFile(EnumVazkiiMods.EASYFPS);
		File cacheFile = IOUtils.getCacheFile(EnumVazkiiMods.EASYFPS);

		new EasyFPSConfig(configFile);

		NBTTagCompound comp = IOUtils.getTagCompoundInFile(cacheFile);
		fpsEnabled = comp.hasKey("fpsEnabled") ? comp.getBoolean("fpsEnabled") : true;
		colorEnabled = comp.hasKey("colorEnabled") ? comp.getBoolean("colorEnabled") : true;

		CornerText.registerCornerTextHandler(new EasyFPSCornerTextHandler());
		KeyBindingRegistry.registerKeyBinding(new EasyFPSKeyHandler());
	}

}
