package com.hetag.runessay.configuration;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;

import com.hetag.runessay.commands.Executor;

public class Manager {
	public static Config defaultConfig;

	public void initConfigs() {
		defaultConfig = new Config(new File("config.yml"));
	}

	public Manager() {
		initConfigs();
		loadConfig(defaultConfig);
	}

	public void loadConfig(Config configurationFile) {
		FileConfiguration config = null;
		if (configurationFile == defaultConfig) {
			config = defaultConfig.getConfig();
			config.addDefault("Settings.Language.ChatPrefix", "&8[&bRunesSay&8]&b ");
			config.addDefault("Settings.Language.NoPermission", "You don't own sufficent permissions to run this command!");
			config.addDefault("Settings.Language.MustBePlayer", "You must be a player to use this command!");
			config.addDefault("Settings.Language.NotNumericValue", "The given value is not numeric! ");
			
			ArrayList<String> helpLines = new ArrayList<>();
			Executor.help = helpLines;
			config.addDefault("Commands.HelpLines", helpLines);
			helpLines.add("&8/&3rs &ahelp &7Display commands help.");	
			
			config.addDefault("General.RunicLanguages.OnInvalidLanguage", "&cThe typed argument: &4%lang%&c is not an available runic language!");
			config.addDefault("General.RunicLanguages.InsufficientPermissions", "&cYou don't own sufficient permissions to use this language!");
			
			config.addDefault("Commands.Help.Required", "Required");
			config.addDefault("Commands.Help.Optional", "Optional");
			config.addDefault("Commands.Help.InvalidTopic", "Please specify a valid topic.");
			config.addDefault("Commands.Help.ProperUsage", "Proper usage: &b&o");
			config.addDefault("Commands.Help.Description", "Shows information about a command.");
			
			config.addDefault("Commands.Say.Description", "Translates typed letters into runes!");
			
			config.addDefault("Commands.List.Description", "Lists all the available runic languages.\n If more arguments are added, a complete list of runes of the specified language will be shown.");
			config.addDefault("Commands.List.LanguagesList", "&bAvailable languages: &3%list%");
			config.addDefault("Commands.List.RunesPerPage", 8);
			config.addDefault("Commands.List.Format.Rune", "&3&l%rune% ");
			config.addDefault("Commands.List.Format.Letter", "&8(&7%letter%&8)");
			config.addDefault("Commands.List.Format.RuneName", "&8 - &3&l%rune_name%");
			config.addDefault("Commands.List.Format.Hovering", "&b&lMeaning: ");
			
			config.addDefault("Commands.Version.Description", "Shows information about the plugin's version.");
			
			defaultConfig.saveConfig();
		}
	}
	
	public static FileConfiguration getConfig() {
		return defaultConfig.getConfig();
	}
}