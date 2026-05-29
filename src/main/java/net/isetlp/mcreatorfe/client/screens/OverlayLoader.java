package net.isetlp.mcreatorfe.client.screens;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class OverlayLoader {

	private static boolean loaded = false;

	@SubscribeEvent
	public static void onJoin(ClientPlayerNetworkEvent.LoggingIn event) {

		if (!loaded) {

			loaded = true;

			OverLayOverlay.loadText();
		}
	}
}