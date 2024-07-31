package io.github.asdqwenm123.velocity.message;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.velocity.packet.ByteSerializer;
import io.github.asdqwenm123.velocity.packet.PacketSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Message {
    private SoraInfo plugin;
    private Player player;

    public Message(SoraInfo plugin, @Nullable Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void connect(String server) {
        player.sendPluginMessage(plugin, SoraInfo.BUNGEECORD, new ByteSerializer("Connect", server).toArray());
    }

    public void playerCount(String server) {
        if (player == null) {
            if (!plugin.getServer().getOnlinePlayers().isEmpty()) {
                ((Player) plugin.getServer().getOnlinePlayers().toArray()[0])
                        /*plugin.getServer()*/.sendPluginMessage(plugin, SoraInfo.BUNGEECORD, new ByteSerializer("PlayerCount", server).toArray());
            }
        } else {
            player.sendPluginMessage(plugin, SoraInfo.BUNGEECORD, new ByteSerializer("PlayerCount", server).toArray());
        }
    }
}
