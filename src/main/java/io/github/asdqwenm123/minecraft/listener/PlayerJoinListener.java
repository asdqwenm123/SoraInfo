package io.github.asdqwenm123.minecraft.listener;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.minecraft.Title;
import io.github.asdqwenm123.minecraft.scheduler.ScoreboardScheduler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private SoraInfo plugin;

    public PlayerJoinListener(SoraInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(null);

        //메시지 전송
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            new Title(plugin, p,
                    Component
                            .text("+ ")
                            .color(TextColor.color(0, 255, 0))
                            .append(
                                    Component.text(event.getPlayer().getName() + "님이 입장하셨습니다.")
                                            .color(TextColor.color(255, 255, 255))
                            )
            ).sendActionBar();
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
        }

        //스코어보드 스케쥴러
        new ScoreboardScheduler(plugin, event.getPlayer()).run();
    }
}
