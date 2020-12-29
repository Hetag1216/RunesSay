package com.hetag.runessay.commands;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hetag.runessay.configuration.Manager;

public abstract class RSCommand implements SubCommand {
	protected String noPermissionMessage;
	protected String mustBePlayerMessage;
	protected String notNumericMessage;
	private final String name;
	private final String prefix;
	private final String properUse;
	private final String description;
	private final String[] aliases;
	public static Map<String, RSCommand> instances = new HashMap<>();

	public RSCommand(String name, String properUse, String description, String[] aliases) {
		this.name = name;
		this.properUse = properUse;
		this.description = description;
		this.aliases = aliases;
		this.noPermissionMessage = ChatColor.translateAlternateColorCodes('&', Manager.getConfig().getString("Settings.Language.NoPermission"));
		this.mustBePlayerMessage = ChatColor.translateAlternateColorCodes('&', Manager.getConfig().getString("Settings.Language.MustBePlayer"));
		this.notNumericMessage = ChatColor.translateAlternateColorCodes('&', Manager.getConfig().getString("Settings.Language.NotNumericValue"));
		this.prefix = ChatColor.translateAlternateColorCodes('&', Manager.getConfig().getString("Settings.Language.ChatPrefix"));
		instances.put(name, this);
	}

	public String getName() {
		return ChatColor.GREEN + this.name;
	}

	public String getProperUse() {
		return this.properUse;
	}

	public String getDescription() {
		return this.description;
	}

	public String[] getAliases() {
		return this.aliases;
	}

	public void help(CommandSender sender, boolean description) {
		sendMessage(sender, this.getProperUse(), true);
		if (description) {
			sender.sendMessage(ChatColor.GRAY + this.description);
		}
	}
	public static String formatColors(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	protected void sendMessage(CommandSender sender, String message, boolean prefix) {
		if (prefix) {
			sender.sendMessage(formatColors(this.prefix + message));
		} else {
			sender.sendMessage(formatColors(message));
		}
		return;
	}

	protected boolean hasPermission(CommandSender sender) {
		if (sender.hasPermission("runessay.command." + this.name)) {
			return true;
		}
		sender.sendMessage(prefix + this.noPermissionMessage);
		return false;
	}

	protected boolean hasPermission(CommandSender sender, String extra) {
		if (sender.hasPermission("runessay.command." + this.name + "." + extra)) {
			return true;
		}
		sender.sendMessage(prefix + this.noPermissionMessage);
		return false;
	}

	protected boolean correctLength(CommandSender sender, int size, int min, int max) {
		if ((size < min) || (size > max)) {
			help(sender, false);
			return false;
		}
		return true;
	}

	protected boolean isPlayer(CommandSender sender) {
		if ((sender instanceof Player)) {
			return true;
		}
		sender.sendMessage(prefix + this.mustBePlayerMessage);
		return false;
	}

	protected boolean isNumeric(String id) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(id, pos);
		return id.length() == pos.getIndex();
	}

	/**
	 * Gets the command name of the specified command.
	 * 
	 * @param cmd
	 * @return
	 */
	protected String getCommand(String cmd) {
		return ChatColor.DARK_GRAY + "\n/" + ChatColor.DARK_AQUA + "rs " + ChatColor.GREEN + cmd;
	}

	/**
	 * Gets the permission of the specified command.
	 * 
	 * @param pex
	 * @return
	 */
	protected String getPermission(String pex) {
		String permission = ChatColor.DARK_AQUA + "runessay.command.";
		String perm = "- " + permission + ChatColor.AQUA + pex;
		return perm;
	}

	protected List<String> getPage(List<String> entries, String title, int page, boolean sort) {
		List<String> strings = new ArrayList<>();
		if (sort) {
			Collections.sort(entries);
		}
		if (page < 1) {
			page = 1;
		}
		if (page * 8 - 8 >= entries.size()) {
			page = Math.round(entries.size() / 8) + 1;
			if (page < 1) {
				page = 1;
			}
		}
		strings.add(ChatColor.DARK_AQUA + "Runes Say " + ChatColor.DARK_GRAY + "- [" + ChatColor.GRAY + page + "/" + (int) Math.ceil((entries.size() + 0.0D) / 8.0D) + ChatColor.DARK_GRAY + "]");
		strings.add(title);
		if (entries.size() > page * 8 - 8) {
			for (int i = page * 8 - 8; i < entries.size(); i++) {
				if (entries.get(i) != null) {
					strings.add(((String) entries.get(i)).toString());
				}
				if (i >= page * 8 - 1) {
					break;
				}
			}
		}
		return strings;
	}
}
