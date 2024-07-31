package io.github.asdqwenm123.minecraft.listener;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.minecraft.Title;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private SoraInfo plugin;

    public PlayerQuitListener(SoraInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(null);
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            new Title(plugin, p,
                    Component
                            .text("- ")
                            .color(TextColor.color(255, 0, 0))
                            .append(
                                    Component.text(event.getPlayer().getName() + "님이 퇴장하셨습니다.")
                                            .color(TextColor.color(255, 255, 255))
                            )
            ).sendActionBar();
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 0.5f, 1f);
        }
    }
}
