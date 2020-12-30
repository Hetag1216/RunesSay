package com.hetag.runessay.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.hetag.runessay.RunesSay;
import com.hetag.runessay.configuration.Manager;

public class VersionCommand extends RSCommand {

	public VersionCommand() {
		super("version", "/rs version", formatColors(Manager.getConfig().getString("Commands.Version.Description")), new String[] { "version", "v" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, 0, 0, 1)) {
			return;
		}
		sendMessage(sender, "", true);
		sendMessage(sender, "&3Version: &b" + RunesSay.getInstance().getDescription().getVersion(), false);
		sendMessage(sender, "&3Author: &b" + RunesSay.getInstance().getDescription().getAuthors().toString().replace("[", "").toString().replace("]", ""), false);
		sendMessage(sender, "&3Compatible Minecraft Version(s): &b 1.13 - 1.14 - 1.15 - 1.16", false);
		sendMessage(sender, "&3Using Protocol: &b" + RunesSay.getProtocol().toString(), false);
		sendMessage(sender, "", false);
		sendMessage(sender, "&8« &bUseful links &8»", false);
		sendMessage(sender, "&aGithub &8» &bhttps://github.com/Hetag1216/RunesSay", false);
		sendMessage(sender, "&aCheck out my other resources at &8» &bhttps://www.spigotmc.org/members/_hetag1216_.243334/", false);
		sendMessage(sender, "&aDiscord &8» &bhttps://discord.gg/yqs9UJs", false);
	}

}
