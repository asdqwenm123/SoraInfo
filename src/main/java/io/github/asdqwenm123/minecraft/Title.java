package io.github.asdqwenm123.minecraft;

import io.github.asdqwenm123.SoraInfo;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class Title {
    private SoraInfo plugin;
    private Player player;
    private Component component;
    private long fadeIn;
    private long duration;
    private long fadeOut;

    public Title(SoraInfo plugin, Player player, Component component, long fadeIn, long duration, long fadeOut) {
        this.plugin = plugin;
        this.player = player;
        this.component = component;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
    }

    public Title(SoraInfo plugin, Player player, Component component) {
        this(plugin, player, component, 10, 70, 20);
    }

    public void sendActionBar() {
        player.sendActionBar(component);
    }
    public void sendActionBar(long delay) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            player.sendActionBar(component);
        }, delay);
    }
}
