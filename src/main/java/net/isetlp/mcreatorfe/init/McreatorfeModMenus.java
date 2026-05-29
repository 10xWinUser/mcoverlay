/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.isetlp.mcreatorfe.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.isetlp.mcreatorfe.world.inventory.GUIOverlayChangerMenu;
import net.isetlp.mcreatorfe.network.MenuStateUpdateMessage;
import net.isetlp.mcreatorfe.McreatorfeMod;

import java.util.Map;

public class McreatorfeModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, McreatorfeMod.MODID);
	public static final RegistryObject<MenuType<GUIOverlayChangerMenu>> GUI_OVERLAY_CHANGER = REGISTRY.register("gui_overlay_changer", () -> IForgeMenuType.create(GUIOverlayChangerMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				McreatorfeMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof McreatorfeModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				McreatorfeMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}