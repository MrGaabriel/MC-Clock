package me.mrgaabriel.mcclock.commands

import org.bukkit.command.*

abstract class AbstractCommand(label: String) : Command(label) {

    constructor(label: String, aliases: List<String>): this(label) {
        this.aliases = aliases
    }

    abstract override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean
}