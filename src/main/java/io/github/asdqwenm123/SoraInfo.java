package io.github.asdqwenm123;

import com.google.gson.Gson;
import io.github.asdqwenm123.cenkor.CensorResult;
import io.github.asdqwenm123.cenkor.TextCensor;
import io.github.asdqwenm123.citizen.listener.NPCLeftClickListener;
import io.github.asdqwenm123.decentholograms.Scheduler;
import io.github.asdqwenm123.jda.JDAScheduler;
import io.github.asdqwenm123.minecraft.command.KommandAuth;
import io.github.asdqwenm123.minecraft.listener.PlayerChatListener;
import io.github.asdqwenm123.minecraft.listener.PlayerJoinListener;
import io.github.asdqwenm123.minecraft.listener.PlayerQuitListener;
import io.github.asdqwenm123.minecraft.listener.PlayerSpawnListener;
import io.github.asdqwenm123.velocity.listener.PluginMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;


public class SoraInfo extends JavaPlugin {
    public static final String TOKEN = "MTI2NDQzODg3NzIwMDk3Nzk4Mw.GZeYwE.E6Oh2ue8tzQcZT5W1IX8KY8dUWXv3M0URTL9yc";
    public static KeyKey<User, String> auth = new KeyKey<>();
    public static KeyKey<String, UUID> link = new KeyKey<>();

    public static final String BUNGEECORD = "BungeeCord";

    public static final String CPVP = "cpvp";
    public static final String WILD_SURVIVAL = "wild_survival";

    public static int CPVPPlayerCount = 0;
    public static int WILD_SURVIVALPlayerCount = 0;

    public static JDA jda;

    @Override
    public void onEnable() {
//        getDataFolder().mkdirs();

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new NPCLeftClickListener(this), this);

        pm.registerEvents(new PlayerSpawnListener(this), this);
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerQuitListener(this), this);
        pm.registerEvents(new PlayerChatListener(this), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, BUNGEECORD);
        getServer().getMessenger().registerIncomingPluginChannel(this, BUNGEECORD, new PluginMessageListener());

        new Scheduler(this).run();
        new JDAScheduler(this).run();
//        new cpvp_playersExpansion().register();

        new KommandAuth(this);

        //TODO 데이터 저장 불러오기(link)

        getLogger().info("start");
    }

    @Override
    public void onDisable() {
        System.out.println(new Gson().toJson(link));
        getLogger().info("stop");
    }
}
