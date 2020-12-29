package com.hetag.runessay.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hetag.runessay.configuration.Manager;
import com.hetag.runessay.runes.ElderFuthark;
import com.hetag.runessay.runes.util.RunicLibrary;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class ListCommand extends RSCommand {
	private ArrayList<String> runes = new ArrayList<String>();
	private int maxArgs;

	public ListCommand() {
		super("list", "/rs list <language> <page>", Manager.getConfig().getString("Commands.List.Description"), new String[] { "list" });
		maxArgs = Manager.getConfig().getInt("Commands.List.RunesPerPage");
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!this.hasPermission(sender) || !this.isPlayer(sender)) {
			return;
		}
		
		if (!runes.isEmpty()) runes.clear();
		
		final Player player = (Player) sender;
		if (args.size() == 0) {
			sendMessage(player, this.getProperUse(), true);
			sendMessage(player, languagesList().replace("%list%", RunicLibrary.getLanguages()), true);
			return;
		} else if (args.size() == 1) {
			String language = args.get(0).toLowerCase();
			if (RunicLibrary.isRunicLanguage(language)) {
				setRunes(language);
				for (String formatted : createPages(runes, 1, maxArgs)) {
					TextComponent toHover = new TextComponent();
					toHover.addExtra(this.hoverFormat() + RunicLibrary.getCorrespondentMeaning(language, formatted));
					TextComponent base = new TextComponent();
					base.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, formatted));
					base.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(toHover).create()));
					base.addExtra(this.runeFormat().replace("%rune%", formatted) + this.letterFormat().replace("%letter%", RunicLibrary.getCorrespondentLetters(language, formatted)));
					base.addExtra(this.runeNameFormat().replace("%rune_name%", RunicLibrary.getCorrespondentRuneName(language, formatted)));
					player.spigot().sendMessage(base);
				}
				return;
			} else {
				sendMessage(player, RunicLibrary.onInvalidLanguage().replace("%lang%", language), true);
				return;
			}
		} else if (args.size() == 2) {
			String language = args.get(0).toLowerCase();
			String arg = args.get(1);
			if (!this.isNumeric(arg)) {
				this.sendMessage(sender, this.notNumericMessage, true);
				return;
			}
			if (RunicLibrary.isRunicLanguage(language)) {
				setRunes(language);
				for (String formatted : createPages(runes, Integer.valueOf(arg), maxArgs)) {
					TextComponent toHover = new TextComponent();
					toHover.addExtra(this.hoverFormat() + RunicLibrary.getCorrespondentMeaning(language, formatted));
					TextComponent base = new TextComponent();
					base.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(toHover).create()));
					base.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, formatted));
					base.addExtra(this.runeFormat().replace("%rune%", formatted) + this.letterFormat().replace("%letter%", RunicLibrary.getCorrespondentLetters(language, formatted)));
					base.addExtra(this.runeNameFormat().replace("%rune_name%", RunicLibrary.getCorrespondentRuneName(language, formatted)));
					player.spigot().sendMessage(base);
				}
				return;
			} else {
				sendMessage(player, RunicLibrary.onInvalidLanguage().replace("%lang%", language), true);
				return;
			}
		}
	}
	
	public void setRunes(String language) {
		switch(language) {
		default:
			break;
		case "elderfuthark":
			for (String set_runes : ElderFuthark.getRunicList()) {
				runes.add(set_runes);
			}
		}
	}
	
	public List<String> createPages(List<String> entries, int page, int maxArgs) {
		List<String> strings = new ArrayList<String>();
		page = page < 1 ? 1 : page;
		strings.add(ChatColor.DARK_AQUA + "Runes Say " + ChatColor.DARK_GRAY + "- [" + ChatColor.GRAY + page + "/" + (int) Math.ceil((entries.size() + 0.0D) / maxArgs) + ChatColor.DARK_GRAY + "]");
		if (page * maxArgs - maxArgs >= entries.size()) {
			page = Math.round(entries.size() / maxArgs) + 1;
		}
		if (entries.size() > page * maxArgs - maxArgs) {
			for (int i = page * maxArgs - maxArgs; i < entries.size(); i++) {
				if (entries.get(i) != null) {
					strings.add(((String) entries.get(i)).toString());
				}
				if (i >= page * maxArgs - 1) {
					break;
				}
			}
		}
		
		return strings;
	}
	
	public static String languagesList() {
		return formatColors(Manager.getConfig().getString("Commands.List.LanguagesList"));
	}
	
	private String runeFormat() {
		return formatColors(Manager.getConfig().getString("Commands.List.Format.Rune"));
	}
	
	private String letterFormat() {
		return formatColors(Manager.getConfig().getString("Commands.List.Format.Letter"));
	}
	
	private String runeNameFormat() {
		return formatColors(Manager.getConfig().getString("Commands.List.Format.RuneName"));
	}
	
	private String hoverFormat() {
		return formatColors(Manager.getConfig().getString("Commands.List.Format.Hovering"));
	}
}
