package io.github.asdqwenm123.minecraft.listener;

import io.github.asdqwenm123.SoraInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!SoraInfo.link.contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            TextComponent auth = Component.text("AUTH").color(TextColor.color(0, 255, 255))
                    .append(Component.text(" >> ").color(TextColor.color(170, 170, 170)));
            TextComponent component = Component.text()
                    .append(
                            Component.text("여기")
                                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://discord.sora24.kr"))
                                .color(TextColor.color(0, 255, 0))
                    )
                    .append(
                        Component.text("를 눌러 디스코드에 입장해서 인증하세요!")
                                .color(TextColor.color(255, 255, 255))
                    ).build();
            event.getPlayer().sendMessage(auth.append(component));
        }
    }
}
