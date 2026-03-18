package net.justmili.leftforgotten.debug;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.ChatEvent;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFDebug {
    public static void hook() {
        ChatEvent.RECEIVED.register(LFDebug::onChat);
    }

    private static EventResult onChat(Player player, Component component) {
        if (!component.getString().equals("dev.justmili.debug.sounds")) return EventResult.pass();
        logSounds();
        return EventResult.pass();
    }

    private static void logSounds() {
        Map<String, RegistrySupplier<SoundEvent>> sounds = new LinkedHashMap<>();
        sounds.put("HURT", LFSounds.HURT);
        sounds.put("MUSIC_13", LFSounds.MUSIC_13);
        sounds.put("MUSIC_BOO", LFSounds.MUSIC_BOO);
        sounds.put("MUSIC_CALM1", LFSounds.MUSIC_CALM1);
        sounds.put("MUSIC_CALM2", LFSounds.MUSIC_CALM2);
        sounds.put("MUSIC_CALM3", LFSounds.MUSIC_CALM3);
        sounds.put("MUSIC_HAL1", LFSounds.MUSIC_HAL1);
        sounds.put("MUSIC_HAL2", LFSounds.MUSIC_HAL2);
        sounds.put("MUSIC_HAL3", LFSounds.MUSIC_HAL3);
        sounds.put("MUSIC_HAL4", LFSounds.MUSIC_HAL4);
        sounds.put("MUSIC_NUANCE1", LFSounds.MUSIC_NUANCE1);
        sounds.put("MUSIC_NUANCE2", LFSounds.MUSIC_NUANCE2);
        sounds.put("MUSIC_PIANO1", LFSounds.MUSIC_PIANO1);
        sounds.put("MUSIC_PIANO2", LFSounds.MUSIC_PIANO2);
        sounds.put("MUSIC_PIANO3", LFSounds.MUSIC_PIANO3);
        sounds.put("MUSIC_DROOPY_LIKES_YOUR_FACE", LFSounds.MUSIC_DROOPY_LIKES_YOUR_FACE);

        for (Map.Entry<String, RegistrySupplier<SoundEvent>> entry : sounds.entrySet()) {
            String name = entry.getKey();
            RegistrySupplier<SoundEvent> s = entry.getValue();
            LeftForgotten.LOGGER.info("=== {} ===", name);
            LeftForgotten.LOGGER.info("  get()               : {}", s.get());
            LeftForgotten.LOGGER.info("  getOrNull()         : {}", s.getOrNull());
            LeftForgotten.LOGGER.info("  getId()             : {}", s.getId());
            LeftForgotten.LOGGER.info("  getRegistryId()     : {}", s.getRegistryId());
            LeftForgotten.LOGGER.info("  getRegistryKey()    : {}", s.getRegistryKey());
            LeftForgotten.LOGGER.info("  getRegistrar()      : {}", s.getRegistrar());
            LeftForgotten.LOGGER.info("  getRegistrarManager(): {}", s.getRegistrarManager());
            LeftForgotten.LOGGER.info("  getLocation()      : {}", s.get().getLocation());
            LeftForgotten.LOGGER.info("  getClass()          : {}", s.getClass());
            LeftForgotten.LOGGER.info("  isPresent()         : {}", s.isPresent());
        }
    }
}