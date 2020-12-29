package com.hetag.runessay;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hetag.runessay.commands.Executor;
import com.hetag.runessay.configuration.Manager;
import com.hetag.runessay.reflection.Protocol_1_13;
import com.hetag.runessay.reflection.Protocol_1_14;
import com.hetag.runessay.reflection.Protocol_1_15;
import com.hetag.runessay.reflection.Protocol_1_16;
import com.hetag.runessay.reflection.RSProtocol;
import com.hetag.runessay.runes.util.RunicLibrary;
public class RunesSay extends JavaPlugin {

	public static Logger log;
	public static RunesSay plugin;
	public static RSProtocol protocol;

	public void onEnable() {
		log = getLogger();
		plugin = this;
		log.info("-=-=-=-= RunesSay " + plugin.getDescription().getVersion() + " =-=-=-=-");
		checkProtocol();
		try {
			new Manager();
			log.info("Configurations succesfully registered!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			new Executor(this);
			log.info("Commands succesfully registered!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			new RunicLibrary();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Succesfully enabled RunesSay!");
		log.info("-=-=-=-= -=- =-=-=-=-");
	}

	public void onDisable() {
		try {
			Manager.defaultConfig.saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
			log.warning("An error occurred while trying to save the configurations files before shutting down.");
		}
		log.info("Disabling language: " + RunicLibrary.getLanguages());
		RunicLibrary.shutdown();
		log.info("Succesfully disabled RunesSay!");

	}
	
	public void checkProtocol() {
		String version = Bukkit.getServer().getClass().getPackage().getName();
		String formmatedVersion = version.substring(version.lastIndexOf(".") + 1);

		switch (formmatedVersion) {
		default:
			protocol = new Protocol_1_13();
			break;
		case "v1_13_R2":
		case "v1_13_R1":
			protocol = new Protocol_1_13();
			break;
		case "v1_14_R1":
			protocol = new Protocol_1_14();
			break;
		case "v1_15_R1":
			protocol = new Protocol_1_15();
			break;
		case "v1_16_R1":
			protocol = new Protocol_1_16();
			break;
		}
		if (protocol.equals(new Protocol_1_13())) {
			log.info("Using protocol for 1.13 versions compatibility!");
		} else if (protocol.equals(new Protocol_1_14())) {
			log.info("Using protocol for 1.14 versions compatibility!");
		} else if (protocol.equals(new Protocol_1_15())) {
			log.info("Using protocol for 1.15 versions compatibility!");
		} else if (protocol.equals(new Protocol_1_16())) {
			log.info("Using protocol for 1.16 versions compatibility!");
		}
	}

	public static RunesSay getInstance() {
		return plugin;
	}

	public static Object getProtocol() {
		return protocol;
	}
}
