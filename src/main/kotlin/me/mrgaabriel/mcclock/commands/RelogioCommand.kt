package me.mrgaabriel.mcclock.commands

import com.github.kevinsawicki.http.*
import com.github.salomonbrys.kotson.*
import com.google.gson.*
import org.bukkit.command.*
import org.bukkit.entity.*
import java.time.*
import java.time.format.*

class RelogioCommand : AbstractCommand("relogio", listOf("horas", "clock", "date")) {

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val ip = sender.address.hostString

            val request = HttpRequest.get("https://timezoneapi.io/api/ip?ip=$ip")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0")

            if (request.code() != 200) {
                sender.sendMessage("§c§lDeu ruim!")
                return false
            }

            val body = request.body()
            val json = JsonParser().parse(body).obj

            val meta = json["meta"].obj
            if (meta["code"].string != "200") {
                val message = json["error"].nullString
                if (message == null) {
                    sender.sendMessage("§c§lDeu ruim! $message")
                    return false
                }

                if (message == "IP address not valid..") {
                    sender.sendMessage("§eAviso: o seu IP é inválido! Por isso, não conseguimos pegar a hora de onde você mora.")
                    sender.sendMessage("§cHora local do servidor: ${OffsetDateTime.now().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))}")
                    return false
                }

                return false
            }

            val data = json["data"].obj
            val city = data["city"].string

            val datetime = data["datetime"].obj

            val date_time = datetime["date_time"].string

            sender.sendMessage("§aHorário em §l$city")
            sender.sendMessage("§a$date_time")
        } else {
            sender.sendMessage("COMANDO PARA JOGADORES")
        }

        return false
    }
}