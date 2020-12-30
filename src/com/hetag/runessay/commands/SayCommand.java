package com.hetag.runessay.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hetag.runessay.configuration.Manager;
import com.hetag.runessay.runes.util.RunicLibrary;

public class SayCommand extends RSCommand {

	public SayCommand() {
		super("say", "/rs say <language> <message>", formatColors(Manager.getConfig().getString("Commands.Say.Description")), new String[] { "say", "s" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!this.hasPermission(sender) || !this.isPlayer(sender)) {
			return;
		}
		if (args.size() < 1) {
			sendMessage(sender, this.getProperUse(), true);
		} else if (args.size() >= 1) {
			String lang = args.get(0);
			lang = lang.toLowerCase();
			if (!RunicLibrary.isRunicLanguage(lang)) {
				sendMessage(sender, RunicLibrary.onInvalidLanguage().replace("%lang%", lang), true);
				sendMessage(sender, ListCommand.languagesList().replace("%list%", RunicLibrary.getLanguages()), true);
				return;
			}
			final Player player = (Player) sender;
			if (!RunicLibrary.hasLanguagePermission(player, lang)) {
				sendMessage(player, this.noPermissionMessage, true);
				return;
			}
			String message = new String();
			for (String part : args.subList(1, args.size())) {
				if (message != "") {
					message = message + " ";
				}
				message = message + part;
			}
			message.toLowerCase();
			final String translation = RunicLibrary.translate(lang, message);
			player.chat(translation);
		}
	}
}