package io.github.asdqwenm123.velocity.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import io.github.asdqwenm123.SoraInfo;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals(SoraInfo.BUNGEECORD)) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            if (server.equals(SoraInfo.CPVP)) {
                SoraInfo.CPVPPlayerCount = in.readInt();
            } else if (server.equals(SoraInfo.WILD_SURVIVAL)) {
                SoraInfo.WILD_SURVIVALPlayerCount = in.readInt();
            }
        }
    }
}
