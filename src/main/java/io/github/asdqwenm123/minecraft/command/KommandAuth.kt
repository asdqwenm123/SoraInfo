package io.github.asdqwenm123.minecraft.command

import io.github.asdqwenm123.SoraInfo
import io.github.monun.kommand.KommandSource
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.dv8tion.jda.api.entities.Role
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.entities.UserSnowflake

class KommandAuth(
    plugin: SoraInfo
) {
    init {
        plugin.kommand {
            register("인증") {
                requires { isPlayer && !isLinked() }
                then("code" to string()) {
                    executes {
                        val code: String by it
                        if (SoraInfo.auth.contains(code)) {
                            SoraInfo.link.put((SoraInfo.auth.get(code) as User).id, player.uniqueId)
                            SoraInfo.auth.remove(code)

                            SoraInfo.jda.getGuildById("1268017119476584511")?.addRoleToMember(SoraInfo.jda.getUserById(SoraInfo.link.get(player.uniqueId) as String) as UserSnowflake,
                                SoraInfo.jda.getRoleById("1268017119476584512")!!)?.queue()
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