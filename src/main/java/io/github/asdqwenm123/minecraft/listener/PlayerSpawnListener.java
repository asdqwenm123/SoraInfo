package io.github.asdqwenm123.minecraft.listener;

import io.github.asdqwenm123.SoraInfo;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;


public class PlayerSpawnListener implements Listener {
    private SoraInfo plugin;

    public PlayerSpawnListener(SoraInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(new Location(event.getRespawnLocation().getWorld(), 130.5, 56.0, 292.5, -90.0f, 0.0f));
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnLocationEvent event) {
        event.setSpawnLocation(new Location(event.getSpawnLocation().getWorld(), 130.5, 56.0, 292.5, -90.0f, 0.0f));

    }
}
