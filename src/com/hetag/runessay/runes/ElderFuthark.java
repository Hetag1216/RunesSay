package com.hetag.runessay.runes;

import com.hetag.runessay.runes.util.RunicLanguage;

public class ElderFuthark extends RunicLanguage {

	public ElderFuthark() {
		super("elderfuthark", LanguageType.core);
	}
	
	public static String[] getRunicList() {
		//								 a	 b	  c	   d	e	f	g	  h    i    j   k   l    m    n    o    p    q   r    s    t    u   v    w    x    y    z
		String[] runes = new String[] { "ᚨ", "ᛒ", "ᚲ", "ᛞ", "ᛖ", "ᚠ", "ᚷ", "ᚺ", "ᛁ", "ᛃ", "ᚲ", "ᛚ", "ᛗ", "ᚾ", "ᛟ", "ᛈ", "ᚦ", "ᚱ", "ᛋ", "ᛏ", "ᚢ", "ᚹ", "ᚹ", "ᛜ", "ᛇ", "ᛉ" };
		return runes;
	}

	/**
	 * Gets the letter assigned to the given rune.
	 * 
	 * @param rune
	 * @return the assigned letter.
	 */
	public static String getCorrespondentLetter(String rune) {
		switch (rune) {
		default:
			break;
		case "ᚨ":
			return "a";
		case "ᛒ":
			return "b";
		case "ᚲ":
			return "c, k";
		case "ᛞ":
			return "d";
		case "ᛖ":
			return "e";
		case "ᚠ":
			return "f";
		case "ᚷ":
			return "g";
		case "ᚺ":
			return "h";
		case "ᛁ":
			return "i";
		case "ᛃ":
			return "j";
		case "ᛚ":
			return "l";
		case "ᛗ":
			return "m";
		case "ᚾ":
			return "n";
		case "ᛟ":
			return "o";
		case "ᛈ":
			return "p";
		case "ᚦ":
			return "q";
		case "ᚱ":
			return "r";
		case "ᛋ":
			return "s";
		case "ᛏ":
			return "t";
		case "ᚢ":
			return "u";
		case "ᚹ":
			return "v, w";
			// extra
		case "ᛜ":
			return "x";
		case "ᛇ":
			return "y";
		case "ᛉ":
			return "z";
		}
		return rune.toString();
	}

	/**
	 * Translates the given sentence letter by letter to ElderFuthark runic.
	 * <p>
	 * Each given argument is turned to lower case!
	 * 
	 * @param letter
	 * @return the translated sentence.
	 */
	public static String translateToCorrespondentRune(String letter) {
		letter.toLowerCase();
		letter = letter.replace("a", "ᚨ");
		letter = letter.replace("b", "ᛒ");
		letter = letter.replace("c", "ᚲ");
		letter = letter.replace("d", "ᛞ");
		letter = letter.replace("e", "ᛖ");
		letter = letter.replace("f", "ᚠ");
		letter = letter.replace("g", "ᚷ");
		letter = letter.replace("h", "ᚺ");
		letter = letter.replace("i", "ᛁ");
		letter = letter.replace("j", "ᛃ");
		letter = letter.replace("k", "ᚲ");
		letter = letter.replace("l", "ᛚ");
		letter = letter.replace("m", "ᛗ");
		letter = letter.replace("n", "ᚾ");
		letter = letter.replace("o", "ᛟ");
		letter = letter.replace("p", "ᛈ");
		letter = letter.replace("q", "ᚦ");
		letter = letter.replace("r", "ᚱ");
		letter = letter.replace("s", "ᛋ");
		letter = letter.replace("t", "ᛏ");
		letter = letter.replace("u", "ᚢ");
		letter = letter.replace("v", "ᚹ");
		letter = letter.replace("w", "ᚹ");
		letter = letter.replace("x", "ᛜ");
		letter = letter.replace("y", "ᛇ");
		letter = letter.replace("z", "ᛉ");
		final String message = letter.toLowerCase();
		return message;
	}

	/**
	 * Gets the rune assigned to the given letter.
	 * 
	 * @param letter
	 * @return the rune.
	 */
	public static String getCorrespondentRune(String letter) {
		letter.toLowerCase();
		switch (letter) {
		default:
			break;
		case "a":
			return "ᚨ";
		case "b":
			return "ᛒ";
		case "c":
		case "k":
			return "ᚲ";
		case "d":
			return "ᛞ";
		case "e":
			return "ᛖ";
		case "f":
			return "ᚠ";
		case "g":
			return "ᚷ";
		case "h":
			return "ᚺ";
		case "i":
			return "ᛁ";
		case "j":
			return "ᛃ";
		case "l":
			return "ᛚ";
		case "m":
			return "ᛗ";
		case "n":
			return "ᚾ";
		case "o":
			return "ᛟ";
		case "p":
			return "ᛈ";
		case "q":
			return "ᚦ";
		case "r":
			return "ᚱ";
		case "s":
			return "ᛋ";
		case "t":
			return "ᛏ";
		case "u":
			return "ᚢ";
		case "v":
		case "w":
			return "ᚹ";
		case "x":
			return "ᛜ";
		case "y":
			return "ᛇ";
		case "z":
			return "ᛉ";
		}
		return letter;
	}
	
	public static String getRuneMeaning(String rune) {
		rune.toLowerCase();
		switch(rune) {
		default:
			break;
		case "ᚨ":
			return "insight | communication | ispiration | true vision";
		case "ᛒ":
			return "growth | birth | fertility | new beginnings";
		case "ᚲ":
			return "torch | vision | revelation | creativity";
		case "ᛞ":
			return "awakening | breakthrough";
		case "ᛖ":
			return "change | harmony | loyality | trust";
		case "ᚠ":
			return "wealth | abudance | energy | creation";
		case "ᚷ":
			return "gift | sacrifice | exchanges";
		case "ᚺ":
			return "wrath of nature | chaos | uncontrolled forces";
		case "ᛁ":
			return "ice | challenge | frustation";
		case "ᛃ":
			return "peace | prosperity";
		case "ᛚ":
			return "water | fantasy | renewal";
		case "ᛗ":
			return "social order";
		case "ᚾ":
			return "need | endurance | survival";
		case "ᛟ":
			return "heritage | land of birth";
		case "ᛈ":
			return " mystery | secrets | the hidden | occult abilities";
		case "ᚦ":
			return "giant";
		case "ᚱ":
			return "journey | evolution";
		case "ᛋ":
			return "sun | success | realization";
		case "ᛏ":
			return "honor | justice | authority";
		case "ᚢ":
			return "physical strenght | untamed potential";
		case "ᚹ":
			return "joy | comfort | pleasure";
			// extra
		case "ᛜ":
			return "internal growth";
		case "ᛇ":
			return "strenght | reliability | trustworthiness";
		case "ᛉ":
			return "protection | shield";
		}
		return rune;
	}

	public static String getRuneName(String rune) {
		rune.toLowerCase();
		switch(rune) {
		default:
			break;
		case "ᚨ":
			return "ansuz";
		case "ᛒ":
			return "berkanan";
		case "ᚲ":
			return "kaun/kenaz";
		case "ᛞ":
			return "dagaz";
		case "ᛖ":
			return "ehwaz";
		case "ᚠ":
			return "fehu";
		case "ᚷ":
			return "gebō";
		case "ᚺ":
			return "hagalaz";
		case "ᛁ":
			return "īsaz";
		case "ᛃ":
			return "jēra";
		case "ᛚ":
			return "laguz";
		case "ᛗ":
			return "mannaz";
		case "ᚾ":
			return "naudiz";
		case "ᛟ":
			return "ōþila/ōþala";
		case "ᛈ":
			return "perþ";
		case "ᚦ":
			return "þurisaz";
		case "ᚱ":
			return "raidō";
		case "ᛋ":
			return "sōwilō";
		case "ᛏ":
			return "tīwaz";
		case "ᚢ":
			return "ūruz";
		case "ᚹ":
			return "wunjō";
			// extra
		case "ᛜ":
			return "ingwaz";
		case "ᛇ":
			return "eiwaz";
		case "ᛉ":
			return "algiz";
		}
		return rune;
	}
}