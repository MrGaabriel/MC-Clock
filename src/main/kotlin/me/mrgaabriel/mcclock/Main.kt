package me.mrgaabriel.mcclock

import me.mrgaabriel.mcclock.commands.*
import org.bukkit.command.*
import org.bukkit.plugin.java.*

class Main : JavaPlugin() {

    val ascii = """
  __  __  ____    ____ _            _
 |  \/  |/ ___|  / ___| | ___   ___| | __
 | |\/| | |     | |   | |/ _ \ / __| |/ /
 | |  | | |___  | |___| | (_) | (__|   <
 |_|  |_|\____|  \____|_|\___/ \___|_|\_\

    """.trimIndent()

    override fun onEnable() {
        println("\n$ascii")
        println("What time is it!?")
        println("")
        println("")
        println("O plugin MC Clock está sendo iniciado")

        loadCommands()
    }

    override fun onDisable() {
        println("O plugin MC Clock está sendo desativado")
    }

    fun loadCommands() {
        val clazz = server::class.java
        val field = clazz.getDeclaredField("commandMap")
        field.isAccessible = true

        val commandMap = field.get(server) as CommandMap

        commandMap.register("clock", RelogioCommand())
    }
}