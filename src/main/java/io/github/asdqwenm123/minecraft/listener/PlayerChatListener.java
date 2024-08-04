package io.github.asdqwenm123.minecraft.listener;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.cenkor.CensorResult;
import io.github.asdqwenm123.cenkor.TextCensor;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChatListener implements Listener {
    private SoraInfo plugin;

    public PlayerChatListener(SoraInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        TextCensor textCensor = new TextCensor(plugin);
        CensorResult censorResult = textCensor.censorText(PlainTextComponentSerializer.plainText().serialize(event.originalMessage()));
        if (censorResult.isFiltered()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Component.text("금지된 단어를 사용하셨습니다.").color(TextColor.color(255, 255, 0)));
        }
    }
}
