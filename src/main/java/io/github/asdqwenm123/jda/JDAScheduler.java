package io.github.asdqwenm123.jda;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.jda.listener.MessageReactionAddListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.awt.*;

public class JDAScheduler {
    private SoraInfo plugin;

    public JDAScheduler(SoraInfo plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            try {
                SoraInfo.jda = JDABuilder.createDefault(SoraInfo.TOKEN)
                        .enableIntents(
                                GatewayIntent.MESSAGE_CONTENT,
                                GatewayIntent.GUILD_MEMBERS,
                                GatewayIntent.GUILD_MESSAGES
                        )
                        .setMemberCachePolicy(
                                MemberCachePolicy.ALL
                        )
                        .addEventListeners(new MessageReactionAddListener())
                        .build().awaitReady();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("인증");
            eb.setDescription("밑의 이모지를 눌러 인증 후 역할을 받으세요!");
            eb.setColor(new Color(0, 255, 255));
            ((MessageChannel) SoraInfo.jda.getGuildChannelById("1268017122014138500")).sendMessageEmbeds(
                    eb.build()).queue(message -> message.addReaction(Emoji.fromUnicode("✅")).queue());
        });
    }

}
