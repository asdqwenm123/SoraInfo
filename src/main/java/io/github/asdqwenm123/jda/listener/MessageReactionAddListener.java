package io.github.asdqwenm123.jda.listener;

import io.github.asdqwenm123.SoraInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MessageReactionAddListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (event.getUser().isBot()) return;

        if (event.getGuildChannel().getId().equals("1268017122014138500")) {
            event.getReaction().removeReaction(event.getUser()).queue();
            if (!SoraInfo.link.contains(event.getUser().getId())) {
                String code;
                if (SoraInfo.auth.contains(event.getUser())) {
                    code = (String) SoraInfo.auth.get(event.getUser());
                } else {
                    do {
                        code = RandomStringUtils.randomAlphanumeric(8);
                    } while (SoraInfo.auth.contains(code));
                }
                SoraInfo.auth.put(event.getUser(), code);

                String finalCode = code;
                event.getUser().openPrivateChannel().flatMap(channel ->
                        channel.sendMessageEmbeds(
                                new EmbedBuilder()
                                        .setTitle("마인크래프트와 연동하세요")
                                        .setDescription("인증 코드: " + finalCode + "\n/인증 " + finalCode)
                                        .setColor(new Color(0, 255, 255))
                                        .build()
                        )
                ).queue();
            }
        }
    }
}
