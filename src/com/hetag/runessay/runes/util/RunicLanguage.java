package com.hetag.runessay.runes.util;

import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

public abstract class RunicLanguage implements RunicInterface {
	public String name;
	public LanguageType type;
	public static HashMap<RunicLanguage, String> languages = new HashMap<RunicLanguage, String>();
	
	public enum LanguageType {
		core;
	}

	public RunicLanguage(String name, LanguageType type) {
		this.name = name;
		this.type = type;
		if (!isLanguageEnabled(name))
		languages.put(this, name);
	}
	
	public String getName() {
		return ChatColor.AQUA + this.name;
	}
	
	public String getPermission() {
		return "runessay.language." + this.name;
	}
	
	public LanguageType getType() {
		return type;
	}
	
	public boolean isLanguageEnabled(String language) {
		if (languages.containsValue(language)) {
			return true;
		}
		return false;
	}
	
	public HashMap<RunicLanguage, String> instances() {
		return languages;
	}

}
