package io.github.asdqwenm123;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.asdqwenm123.cenkor.CensorResult;
import io.github.asdqwenm123.cenkor.TextCensor;
import io.github.asdqwenm123.citizen.listener.NPCLeftClickListener;
import io.github.asdqwenm123.decentholograms.Scheduler;
import io.github.asdqwenm123.jda.JDAScheduler;
import io.github.asdqwenm123.minecraft.command.KommandAuth;
import io.github.asdqwenm123.minecraft.listener.*;
import io.github.asdqwenm123.velocity.listener.PluginMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
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


    private File linkFile = new File(getDataFolder(), "link.json");

    @Override
    public void onEnable() {
        init();
        load();

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new NPCLeftClickListener(this), this);

        pm.registerEvents(new PlayerSpawnListener(this), this);
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerQuitListener(this), this);
        pm.registerEvents(new PlayerChatListener(this), this);
        pm.registerEvents(new PlayerMoveListener(), this);

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
//        System.out.println(new Gson().toJson(link));
        save();

        getLogger().info("stop");
    }
    private void init() {
        getDataFolder().mkdirs();
        if (!linkFile.exists()) {
            try {
                linkFile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(linkFile));
                bw.write("{}");
                bw.flush();
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void save() {
        try {
            FileWriter fileWriter = new FileWriter(linkFile);
            String json = new GsonBuilder().setPrettyPrinting().create().toJson(link);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(linkFile));
            Type type = new TypeToken<KeyKey<String, UUID>>(){}.getType();
            Gson gson = new Gson();

            KeyKey<String, UUID> tmp = gson.fromJson(br, type);
            if (tmp != null) {
                link.putAll(tmp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
