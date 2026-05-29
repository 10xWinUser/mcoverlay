package net.isetlp.mcreatorfe.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import net.isetlp.mcreatorfe.client.screens.OverLayOverlay;

@Mod.EventBusSubscriber
public class EnableOverlayMDCommand {

	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {

		event.getDispatcher().register(
			Commands.literal("enableoverlay")
				.executes(context -> {

					OverLayOverlay.enabled = true;

					context.getSource().sendSuccess(
						() -> Component.literal("Overlay Enabled"),
						false
					);

					return 1;
				})
		);
	}
}