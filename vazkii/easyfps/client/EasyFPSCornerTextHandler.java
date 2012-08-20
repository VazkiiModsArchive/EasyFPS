package vazkii.easyfps.client;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import vazkii.codebase.client.ClientUtils;
import vazkii.codebase.client.CornerTextEntry;
import vazkii.codebase.client.ICornerTextHandler;
import vazkii.codebase.client.handlers.ClientTickHandler;
import vazkii.codebase.common.CommonUtils;

public class EasyFPSCornerTextHandler implements ICornerTextHandler {

	int totalFPS = 0;

	@Override
	public List<CornerTextEntry> updateCornerText() {
		Minecraft mc = CommonUtils.getMc();

		if (mc.gameSettings.showDebugInfo || !mod_EasyFPS.fpsEnabled) return null;

		List<CornerTextEntry> entries = new LinkedList();

		String fps = ClientUtils.getFPS();
		int fpsNum = Integer.parseInt(fps);
		int color = getColor(fpsNum);

		totalFPS += fpsNum;

		entries.add(new CornerTextEntry("FPS: " + fps, color));

		if (EasyFPSConfig.averageFPSEnabled) entries.add(new CornerTextEntry("Avg: " + getAvgFps(totalFPS, ClientTickHandler.renderTicksElapsed), color));

		if (EasyFPSConfig.pingMeterEnabled && mc.theWorld != null && ClientUtils.isConnectedToServer()) entries.add(new CornerTextEntry("Ping: " + ClientUtils.getPing(), color));

		return entries;
	}

	private int getAvgFps(int totalFps, int ticks) {
		return (int) Math.round((double) totalFps / (double) ticks);
	}

	private int getColor(int fps) {
		String hex = EasyFPSConfig.hex_default;

		if (mod_EasyFPS.colorEnabled) hex = fps >= 120 ? EasyFPSConfig.hex_over120 : fps >= 60 ? EasyFPSConfig.hex_60to120 : fps >= 30 ? EasyFPSConfig.hex_30to60 : fps >= 20 ? EasyFPSConfig.hex_20to30 : EasyFPSConfig.hex_lessThan20;

		return CommonUtils.parseHexString(hex);
	}

}
