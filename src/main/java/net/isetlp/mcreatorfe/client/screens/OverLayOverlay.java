package net.isetlp.mcreatorfe.client.screens;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class OverLayOverlay {

	public static String overlayText = "Minecraft Forge v1.20.1";
	public static boolean enabled = true;

	private static boolean loaded = false;

	public static final String DEFAULT_TEXT = "Minecraft Forge v1.20.1";

	public static final Path FILE_PATH =
		Paths.get(Minecraft.getInstance().gameDirectory.toString(), "overlaytext.txt");

	// LOAD
	public static void loadText() {
		try {

			if (!Files.exists(FILE_PATH)) {
				overlayText = DEFAULT_TEXT;
				Files.writeString(FILE_PATH, DEFAULT_TEXT);
				return;
			}

			String text = Files.readString(FILE_PATH).trim();

			if (text.isEmpty()) {
				overlayText = DEFAULT_TEXT;
				Files.writeString(FILE_PATH, DEFAULT_TEXT);
			} else {
				overlayText = text;
			}

		} catch (IOException e) {
			e.printStackTrace();
			overlayText = DEFAULT_TEXT;
		}
	}

	// SAVE
	public static void saveText(String text) {
		try {

			if (text == null || text.trim().isEmpty()) {
				overlayText = DEFAULT_TEXT;
				Files.writeString(FILE_PATH, DEFAULT_TEXT);
			} else {
				overlayText = text;
				Files.writeString(FILE_PATH, text);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
public static void onRender(RenderGuiEvent.Post event) {

	if (!loaded) {
		loadText();
		loaded = true;
	}

	Minecraft mc = Minecraft.getInstance();

	if (!enabled)
	return;

if (mc.options.renderDebug)
	return;

if (mc.options.hideGui)
	return;
	event.getGuiGraphics().drawString(
		mc.font,
		Component.literal(overlayText),
		0,
		0,
		-1,
		true
	);
}
}