package com.game.themaze.level;

import com.game.loblib.utility.Logger;
import com.game.themaze.level.concrete.Level01;
import com.game.themaze.level.concrete.Level02;
import com.game.themaze.level.concrete.Level03;
import com.game.themaze.level.concrete.Level04;
import com.game.themaze.level.concrete.Level05;
import com.game.themaze.level.concrete.Level06;
import com.game.themaze.level.concrete.Level07;
import com.game.themaze.level.concrete.Level08;
import com.game.themaze.level.concrete.Level09;
import com.game.themaze.level.concrete.Level10;
import com.game.themaze.level.concrete.Level11;
import com.game.themaze.level.concrete.Level12;
import com.game.themaze.level.concrete.Level13;
import com.game.themaze.level.concrete.Level14;
import com.game.themaze.level.concrete.Level15;
import com.game.themaze.level.concrete.Level16;
import com.game.themaze.level.concrete.Level17;
import com.game.themaze.level.concrete.Level18;
import com.game.themaze.level.concrete.Level19;
import com.game.themaze.level.concrete.Level20;
import com.game.themaze.level.concrete.Level21;
import com.game.themaze.level.concrete.Level22;
import com.game.themaze.level.concrete.Level23;
import com.game.themaze.level.concrete.Level24;
import com.game.themaze.level.concrete.Level25;
import com.game.themaze.level.concrete.Level26;
import com.game.themaze.level.concrete.Level27;
import com.game.themaze.level.concrete.Level28;
import com.game.themaze.level.concrete.Level30;
import com.game.themaze.level.concrete.Level31;
import com.game.themaze.level.concrete.Level32;
import com.game.themaze.level.concrete.Level33;
import com.game.themaze.level.concrete.Level34;

public class LevelFactory {
	protected final static StringBuffer _tag = new StringBuffer("LevelFactory");
	
	public static Level create(int level) {
		switch (level) {
		case 0: return new RandomLevel();
		case 1: return new Level01();
		case 2: return new Level02();
		case 3: return new Level03();
		case 4: return new Level04();
		case 5: return new Level05();
		case 6: return new Level06();
		case 7: return new Level07();
		case 8: return new Level08();
		case 9: return new Level09();
		case 10: return new Level10();
		case 11: return new Level11();
		case 12: return new Level12();
		case 13: return new Level13();
		case 14: return new Level14();
		case 15: return new Level15();
		case 16: return new Level16();
		case 17: return new Level17();
		case 18: return new Level18();
		case 19: return new Level19();
		case 20: return new Level20();
		case 21: return new Level21();
		case 22: return new Level22();
		case 23: return new Level23();
		case 24: return new Level24();
		case 25: return new Level25();
		case 26: return new Level26();
		case 27: return new Level27();
		case 28: return new Level28();
		case 29: return new Level27();
		case 30: return new Level30();
		case 31: return new Level31();
		case 32: return new Level32();
		case 33: return new Level33();
		case 34: return new Level34();
		default:
			Logger.e(_tag, "Invalid level");
			return null;
		}
	}
}
