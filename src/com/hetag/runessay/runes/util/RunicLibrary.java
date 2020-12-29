package com.hetag.runessay.runes.util;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.hetag.runessay.RunesSay;
import com.hetag.runessay.commands.RSCommand;
import com.hetag.runessay.configuration.Manager;
import com.hetag.runessay.runes.ElderFuthark;

public class RunicLibrary {

	public static HashMap<RunicLanguage, String> languages;
	private static String langPermission;

	public RunicLibrary() {
		caller();
		languages = RunicLanguage.languages;
		langPermission = RSCommand.formatColors(Manager.getConfig().getString("General.RunicLanguages.InsufficientPermissions"));
		RunesSay.log.info("Succesfully loaded languages: " + getLanguages());
	}

	public void caller() {
		new ElderFuthark();
	}

	public static String getLanguage(String language) {
		if (languages.containsValue(language)) {
			return language;
		}
		return "Language not found";
	}

	public static boolean hasLanguagePermission(Player player, String language) {
		if (player.hasPermission("runessay.language." + language)) {
			return true; 
		}
		player.sendMessage(RSCommand.formatColors(getPrefix() + langPermission));
		return false;
	}

	public static String getLanguages() {
		if (!languages.isEmpty()) {
			for (String str : languages.values()) {
				return str;
			}
		} else {
			return "No languages could be found.";
		}
		return null;
	}

	public static boolean isRunicLanguage(String language) {
		if (languages.containsValue(language)) {
			return true;
		}
		return false;
	}

	public static String translate(String language, String message) {
		switch (getLanguages()) {
		default:
			break;
		case "elderfuthark":
			return ElderFuthark.translateToCorrespondentRune(message);
		}
		return message;
	}

	public static String getCorrespondentLetters(String language, String rune) {
		switch (getLanguages()) {
		default:
			break;
		case "elderfuthark":
			return ElderFuthark.getCorrespondentLetter(rune);
		}
		return rune;
	}
	
	public static String getCorrespondentMeaning(String language, String rune) {
		switch(getLanguages()) {
		default:
			break;
		case "elderfuthark":
			return ElderFuthark.getRuneMeaning(rune);
		}
		return rune;
	}
	
	public static String getCorrespondentRuneName(String language, String rune) {
		switch(getLanguages()) {
		default:
			break;
		case "elderfuthark":
			return ElderFuthark.getRuneName(rune);
		}
		return rune;
	}

	public String[] alphabet() {
		String[] l = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		return l;
	}
	
	public static String onInvalidLanguage() {
		return Manager.getConfig().getString("General.RunicLanguages.OnInvalidLanguage");
	}
	
	public static String getPrefix() {
		return Manager.getConfig().getString("Settings.Language.ChatPrefix");
	}
	
	public static void shutdown() {
		if (languages.isEmpty()) languages.clear();
	}
}
