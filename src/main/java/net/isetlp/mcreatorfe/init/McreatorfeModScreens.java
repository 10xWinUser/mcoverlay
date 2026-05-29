/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.isetlp.mcreatorfe.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.isetlp.mcreatorfe.client.gui.GUIOverlayChangerScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class McreatorfeModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(McreatorfeModMenus.GUI_OVERLAY_CHANGER.get(), GUIOverlayChangerScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}