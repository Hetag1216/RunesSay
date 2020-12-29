package com.hetag.runessay.runes.util;

import com.hetag.runessay.runes.util.RunicLanguage.LanguageType;

public interface RunicInterface {
	
	/**
	 * Gets the name of the runic language.
	 * @return name
	 */
	public String getName();
	
	/**
	 * Gets the permission needed to use the language.
	 * @return permission
	 */
	public String getPermission();
	
	public LanguageType getType();
	
	//public String[] getRunicList();
	
	//public String getRunes();
	
	/**
	 * Returns the correspondent letter for the given rune.
	 * @param rune
	 * @return the letter correspondent to the rune
	 */
	//public String getCorrespondentLetter(Runes rune);
	
	/**
	 * Returns the correspondent rune for the given letter.
	 * @param letter
	 * @return the rune correspondent to the letter
	 */
	//public String getCorrespondentRune(String letter);
	
	/**
	 * Translates the given sentence letter by letter to the desired runic language.
	 * @param letter
	 * @return the translated sentence.
	 */
	//public String translateToCorrespondentRune(String letter);
}
