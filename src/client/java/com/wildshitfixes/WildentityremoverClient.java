package com.wildshitfixes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class WildentityremoverClient implements ClientModInitializer {

	KeyBinding.Category CLEANER =
			new KeyBinding.Category(Identifier.of("cleaner","cleaner"));

	@Override
	public void onInitializeClient() {

		KeyBinding toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.cleaner.toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				CLEANER
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			while (toggleKey.wasPressed()) {
				RenderState.hideAllEntities = !RenderState.hideAllEntities;
                assert client.player != null;
                client.player.sendMessage(
                        Text.of("Entity hiding: " +
                                (RenderState.hideAllEntities ? "OFF (FPS MODE)" : "ON (NORMAL)"))
				,false);

			}
		});
	}
}