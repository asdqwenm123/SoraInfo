package io.github.asdqwenm123.decentholograms;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.velocity.message.Message;

public class Scheduler {
    private SoraInfo plugin;

    public Scheduler(SoraInfo plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            new Message(plugin, null).playerCount(SoraInfo.CPVP);
            Hologram hologram = DHAPI.getHologram(SoraInfo.CPVP);
            HologramPage hologramPage = DHAPI.getHologramPage(hologram, 0);
            HologramLine hologramLine = DHAPI.getHologramLine(hologramPage, 1);
            hologramLine.setContent("<#FFFF00>플레이 중: " + SoraInfo.CPVPPlayerCount + "명");
        }, 20L,20L);

        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            new Message(plugin, null).playerCount(SoraInfo.WILD_SURVIVAL);
            Hologram hologram = DHAPI.getHologram(SoraInfo.WILD_SURVIVAL);
            HologramPage hologramPage = DHAPI.getHologramPage(hologram, 0);
            HologramLine hologramLine = DHAPI.getHologramLine(hologramPage, 1);
            hologramLine.setContent("<#FFFF00>플레이 중: " + SoraInfo.WILD_SURVIVALPlayerCount + "명");
        }, 20L,20L);
    }
}
