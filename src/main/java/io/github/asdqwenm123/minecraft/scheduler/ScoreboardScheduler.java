package io.github.asdqwenm123.minecraft.scheduler;

import io.github.asdqwenm123.SoraInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardScheduler {
    private SoraInfo plugin;
    private Player player;

    public ScoreboardScheduler(SoraInfo plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void run() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, bukkitTask -> {
            if (player.isOnline()) {
                player.setScoreboard(createScoreboard());
            } else {
                bukkitTask.cancel();
            }
        }, 0L, 20L);

    }

    private Scoreboard createScoreboard() {
        Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("lobby", Criteria.DUMMY, Component.text("|| SORA24.KR ||")
//                .decoration(TextDecoration.BOLD, true)
                .color(TextColor.color(0, 255, 255)));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("§3").setScore(7);
        objective.getScore("§e>> §6CPVP §f(§a1.19.4-1.20.2§f)").setScore(6);
        objective.getScore("§r§7- §a"+ SoraInfo.CPVPPlayerCount + "§f명 플레이 중").setScore(5);
        objective.getScore("§2").setScore(4);
        objective.getScore("§e>> §6생야생 §f(§a1.19.2-1.20.2§f)").setScore(3);
        objective.getScore("§7- §a"+ SoraInfo.WILD_SURVIVALPlayerCount + "§f명 플레이 중").setScore(2);
        objective.getScore("§1").setScore(1);
        objective.getScore("§8SORA24.KR").setScore(0);

        return scoreboard;
    }
}
