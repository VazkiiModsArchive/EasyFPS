package vazkii.easyfps.client;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import vazkii.codebase.common.VazkiiUpdateHandler;
import cpw.mods.fml.common.Mod;

public class EasyFPSUpdateHandler extends VazkiiUpdateHandler {

	public EasyFPSUpdateHandler(Mod m) {
		super(m);
	}

	@Override
	public String getModName() {
		return "EasyFPS";
	}

	@Override
	public String getUMVersion() {
		return EasyFPSReference.VERSION;
	}

	@Override
	public String getUpdateURL() {
		return EasyFPSReference.UPDATE_URL;
	}

	@Override
	public String getChangelogURL() {
		return EasyFPSReference.CHANGELOG_URL;
	}
	
	@Override
	public ItemStack getIconStack(){
		return new ItemStack(Item.bed);
	}

}
