package com.hetag.runessay.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.hetag.runessay.RunesSay;
import com.hetag.runessay.configuration.Manager;

public class Executor {
	private RunesSay plugin;

	public Executor(RunesSay plugin) {
		this.plugin = plugin;
		init();
	}

	public static String[] commandaliases = { "rs", "runessay", "rune", "runes" };
	public static List<String> help;

	private void init() {
		PluginCommand pc = this.plugin.getCommand("rs");
		new HelpCommand();
		new SayCommand();
		new ListCommand();
		new VersionCommand();
		
		help = Manager.getConfig().getStringList("Commands.HelpLines");

		CommandExecutor commandExecutor = new CommandExecutor() {
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if ((args.length == 0) && (Arrays.asList(Executor.commandaliases).contains(label.toLowerCase()))) {
					for (String line : Executor.help) {
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
					}
					return true;
				}
				List<String> sendingArgs = Arrays.asList(args).subList(1, args.length);
				for (RSCommand command : RSCommand.instances.values()) {
					if (Arrays.asList(command.getAliases()).contains(args[0].toLowerCase())) {
						command.execute(s, sendingArgs);
						return true;
					}
				}
				return true;
			}
		};
		pc.setExecutor(commandExecutor);
	}
}
