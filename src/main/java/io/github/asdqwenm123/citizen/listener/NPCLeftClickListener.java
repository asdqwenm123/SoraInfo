package io.github.asdqwenm123.citizen.listener;

import io.github.asdqwenm123.SoraInfo;
import io.github.asdqwenm123.citizen.NPCId;
import io.github.asdqwenm123.velocity.message.Message;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCLeftClickListener implements Listener {
    private SoraInfo plugin;

    public NPCLeftClickListener(SoraInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNPCLeftClick(NPCLeftClickEvent event) {
        NPC npc = event.getNPC();
        if (npc.getId() == NPCId.CPVP) {
            new Message(plugin, event.getClicker()).connect(SoraInfo.CPVP);
        } else if (npc.getId() == NPCId.WILD_SURVIVAL) {
            new Message(plugin, event.getClicker()).connect(SoraInfo.WILD_SURVIVAL);
        }
    }
}
