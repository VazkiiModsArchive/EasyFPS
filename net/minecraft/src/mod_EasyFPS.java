package net.minecraft.src;

import java.util.List;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.src.vazkii.updatemanager.IUMAdvanced;
import net.minecraft.src.vazkii.updatemanager.IUpdateManager;
import net.minecraft.src.vazkii.updatemanager.ModType;
import net.minecraft.src.vazkii.updatemanager.UMCore;

import org.lwjgl.input.Keyboard;

public class mod_EasyFPS extends BaseMod implements IUpdateManager, IUMAdvanced{
	
	private Minecraft mc = ModLoader.getMinecraftInstance();
	
	public static KeyBinding key = new KeyBinding("FPS",Keyboard.KEY_F4);
	
	private boolean fpsEnabled = true;
	private boolean coloredIndicator = false;
	
	private int fpsTotal = 0;
	private int ticks = 0;
	private int avgFps;
	
	@MLProp public static boolean disabledOnStartup = false;
	@MLProp public static boolean colorModeOnStartup = true;
	@MLProp public static boolean averageFPSEnabled = true;
	@MLProp public static boolean pingMeterEnabled = true;
	
	@MLProp public static String hex_default = "FFFFFF";
	@MLProp public static String hex_lessThan20 = "FF0400";
	@MLProp public static String hex_20to30 = "FF5C05";
	@MLProp public static String hex_30to60 = "FFF30F";
	@MLProp public static String hex_60to120 = "43FF00";
	@MLProp public static String hex_over120 = "00D4FF";
	

	
    public mod_EasyFPS()
    {
    	UMCore.addMod(this);
    	fpsEnabled = !disabledOnStartup;
    	coloredIndicator = colorModeOnStartup;
        ModLoader.setInGameHook(this, true, false);
		ModLoader.registerKey(this, key, true);
    }

    public String getVersion()
    {
        return "by Vazkii. Version [1.6.1] for 1.2.5";
    }
 
    public void load()
    {
    	//BaseMod Abstract Method
    }

    public boolean onTickInGame(float f, Minecraft minecraft)
    { 
    	if(!mc.gameSettings.showDebugInfo && fpsEnabled){

        String debug = mc.debug;
        String s = "[( f]+";
        String[] s1 = debug.split(s);
        
        String fps;
        int fps1 = Integer.parseInt(s1[0]);

        StringBuilder sb = new StringBuilder();
        sb.append("FPS: ");
        sb.append(Integer.parseInt(s1[0]));
        
        if(fps1 != 0 && averageFPSEnabled)
        sb.append(" (Avg: ").append(doAvgCounter(ticks, fps1)).append(")");
        
        if(fps1 !=0 && mc.theWorld.isRemote && pingMeterEnabled)
        sb.append(" || Ping: ").append(getPlayerPing()).append("ms.");
        
        if(fps1 != 0)
        	fps = sb.toString();
        else fps = "CRASH?";
        
        int hex;
        
        if(fps1 != 0)
        hex = getHexColor(fps1);
        else
        hex = Integer.parseInt(new StringBuilder().append(hex_default).toString(), 16);
        
        mc.fontRenderer.drawStringWithShadow(fps, 2, 2, hex);
        }
        return true;
    }
    
	public void keyboardEvent(KeyBinding event) {
		if (event.keyCode == key.keyCode && event.isPressed()){
			if (mc.thePlayer.isSneaking() && fpsEnabled)
			coloredIndicator = !coloredIndicator;
			else
			fpsEnabled = !fpsEnabled;
		}	
	}
	
	private int getHexColor(int fps){
		if(coloredIndicator){
		if(fps >= 120)
			return Integer.parseInt(new StringBuilder().append(hex_over120).toString(), 16);
		else if(fps >= 60 && fps < 120)
			return Integer.parseInt(new StringBuilder().append(hex_60to120).toString(), 16);
		else if(fps >= 30 && fps < 60)
			return Integer.parseInt(new StringBuilder().append(hex_30to60).toString(), 16);
		else if(fps >= 20 && fps < 30)
			return Integer.parseInt(new StringBuilder().append(hex_20to30).toString(), 16);
		else if(fps < 20 && fps != 0);
			return Integer.parseInt(new StringBuilder().append(hex_lessThan20).toString(), 16);
		}else return Integer.parseInt(new StringBuilder().append(hex_default).toString(), 16);
	}
	
	private int doAvgCounter(int t, int f){
		if(fpsTotal >= 2147473647){ //The number here is the highest integer minus 10000, it's in here to prevent crashes.
			ticks = 0;
			fpsTotal = 0;
		}else{
			ticks++;
			fpsTotal = fpsTotal + f;
		}
		
		return (fpsTotal/ticks);
	}
	
	private int getPlayerPing(){	
		String username = mc.thePlayer.username;
        String nameToCheck = "nil"; 
        NetClientHandler player = ((EntityClientPlayerMP)mc.thePlayer).sendQueue;
        List list = player.playerNames;
        Iterator iterator = list.iterator();
        GuiPlayerInfo info = null;
        int time = -1337;
        
        while(iterator.hasNext()){
        	time = -1337;
        	info = (GuiPlayerInfo)iterator.next();
        	 nameToCheck = info.name;
        	 
        	if(nameToCheck.matches(username))
        	time = info.responseTime;	
        	
            if(time != -1337)
            return time;
        }return -1;
	}

	public String getModName() {
		return "EasyFPS";
	}

	public String getChangelogURL() {
		return "https://dl.dropbox.com/u/34938401/Mods/On%20Topic/Mods/EasyFPS/Changelog.txt";
	}

	public String getUpdateURL() {
		return "https://dl.dropbox.com/u/34938401/Mods/On%20Topic/Mods/EasyFPS/Version.txt";
	}

	public String getModURL() {
		return "http://www.minecraftforum.net/topic/528166-123-mlforge-vazkiis-mods-ebonapi-last-updated-12512/";
	}

	public ModType getModType() {
		return ModType.UNDEFINED;
	}
    
}
