package io.github.asdqwenm123.minecraft.command

import io.github.asdqwenm123.SoraInfo
import io.github.monun.kommand.KommandSource
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.UserSnowflake
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import java.awt.Color
import java.util.*

class KommandAuth(
    plugin: SoraInfo
) {
    init {
        plugin.kommand {
            register("인증") {
                requires { isPlayer && !isLinked() }
                then("code" to string()) {
                    executes { it ->
                        val code: String by it
                        if (SoraInfo.auth.contains(code)) {
                            SoraInfo.link.put((SoraInfo.auth.get(code) as User).id, player.uniqueId)
                            if (SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String) != null) {
                                SoraInfo.auth.remove(code)
                                SoraInfo.jda.getGuildById("1268017119476584511")?.addRoleToMember(
                                    SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String) as UserSnowflake,
                                    SoraInfo.jda.getRoleById("1268017119476584512")!!
                                )?.queue()

                                SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String)!!
                                    .openPrivateChannel().flatMap {
                                    it.sendMessageEmbeds(
                                        EmbedBuilder()
                                            .setTitle("마인크래프트와 연동되었습니다!")
                                            .setDescription(
                                                plugin.server.getPlayer(SoraInfo.link.get(it.user!!.id) as UUID)!!.name + "(${
                                                    SoraInfo.link.get(
                                                        it.user!!.id
                                                    ) as UUID
                                                })" + " <-> " + it.user!!.asTag + "(${it.user!!.id})"
                                            )
                                            .setColor(Color(0, 255, 255))
                                            .build()
                                    )
                                }.queue()
                                val auth = Component.text("AUTH").color(TextColor.color(0, 255, 255))
                                    .append(Component.text(" >> ").color(TextColor.color(170, 170, 170)))
                                val component = Component.text(player.name + "(${player.uniqueId})" + " <-> " + SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String)!!.asTag + "(${SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String)!!.id})").color(TextColor.color(255, 255, 255))
                                player.sendMessage(auth.append(component))
                            } else {
//                                println(SoraInfo.link.get(player.uniqueId) as String)
                                SoraInfo.link.remove(player.uniqueId)
                            }
                        }
                    }

                }
            }
        }
    }

    private fun KommandSource.isLinked(): Boolean {
        return SoraInfo.link.contains(player.uniqueId)
    }
}