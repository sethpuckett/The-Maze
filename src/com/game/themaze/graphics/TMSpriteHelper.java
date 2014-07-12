package com.game.themaze.graphics;

import com.game.loblib.graphics.Sprite;
import com.game.loblib.graphics.SpriteHelper;
import com.game.loblib.graphics.SpriteType;
import com.game.loblib.utility.Global;
import com.game.the_maze.R;

// Helper class for loading specific images
public class TMSpriteHelper extends SpriteHelper {

	@Override
	public void setupSprite(Sprite s, int image) {
		switch (image) {
		case TMImage.SPLASH_LOGO:
			s.Texture.ResourceId = R.drawable.splash_logo;
			s.Frames = FrameHelper._256x256;
			break;
		case TMImage.KILLMONSTER_BG:
			s.Texture.ResourceId = R.drawable.killmonster_bg;
			s.Frames = FrameHelper.TitleBackground;
			s.Type = SpriteType.SINGLE_ANIMATION;
			s.FrameCount = 9;
			s.FrameRate = 10f;
			break;
		case TMImage.NEW_GAME_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_new_game_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.CONTINUE_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_continue_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.CHOOSE_LEVEL_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_choose_level_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.CREDITS_BUTTON:
			s.Texture.ResourceId = R.drawable.title_credits_button;
			s.Frames = FrameHelper.CreditsButton;
			break;
		case TMImage.KILL_MONSTER_BUTTON:
			s.Texture.ResourceId = R.drawable.killmonster_btn;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.PLAYER:
			s.Texture.ResourceId = R.drawable.levelcommon_android;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.CONTROL_BAR:
			s.Texture.ResourceId = R.drawable.levelcommon_control_bar;
			s.Frames = FrameHelper._256x64;
			break;
		case TMImage.CAMERA_BTN:
			s.Texture.ResourceId = R.drawable.levelcommon_camera_button;
			s.Frames = FrameHelper.ToggleButton;
			s.FrameCount = 2;
			break;
		case TMImage.LEVEL_1_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_1btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_2_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_2btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_3_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_3btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_4_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_4btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_5_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_5btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_6_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_6btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_7_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_7btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_8_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_8btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_9_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_9btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_10_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_10btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_11_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_11btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_12_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_12btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_13_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_13btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_14_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_14btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_15_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_15btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_16_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_16btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_17_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_17btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_18_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_18btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_19_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_19btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_20_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_20btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_21_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_21btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_22_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_22btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_23_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_23btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_24_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_24btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_25_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_25btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_26_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_26btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_27_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_27btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_28_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_28btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_29_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_29btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_30_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_30btn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_LOCK_BUTTON:
			s.Texture.ResourceId = R.drawable.levelselect_lockbtn;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.BACK_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_back_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.CREDITS:
			s.Texture.ResourceId = R.drawable.credits_credits;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.SOUND_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_sound_button;
			s.Frames = FrameHelper.ToggleButton;
			s.FrameCount = 2;
			break;
		case TMImage.BACKGROUND_DIRT_GRAY:
			s.Texture.ResourceId = R.drawable.background_dirt_gray;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_MARBLE_GOLD:
			s.Texture.ResourceId = R.drawable.background_goldmarble;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_MARBLE_GREEN:
			s.Texture.ResourceId = R.drawable.background_greenmarble;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_TILE_BLACK:
			s.Texture.ResourceId = R.drawable.background_blacktiles;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_TILE_RED:
			s.Texture.ResourceId = R.drawable.background_redtiles;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_ICE:
			s.Texture.ResourceId = R.drawable.background_ice;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_ICE2:
			s.Texture.ResourceId = R.drawable.background_ice2;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_METAL:
			s.Texture.ResourceId = R.drawable.background_metal;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_METAL2:
			s.Texture.ResourceId = R.drawable.background_metal2;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_COPPER:
			s.Texture.ResourceId = R.drawable.background_copper;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_DIRT_CRACKED:
			s.Texture.ResourceId = R.drawable.background_dirt_cracked;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_GLASS:
			s.Texture.ResourceId = R.drawable.background_glass;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_ORNATE:
			s.Texture.ResourceId = R.drawable.background_ornate;
			s.Frames = FrameHelper.BigBackground;
			break;
		case TMImage.BACKGROUND_ORNATE2:
			s.Texture.ResourceId = R.drawable.background_ornate2;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_PAPER:
			s.Texture.ResourceId = R.drawable.background_paper;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_SAND:
			s.Texture.ResourceId = R.drawable.background_sand;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_SAND2:
			s.Texture.ResourceId = R.drawable.background_sand2;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.BACKGROUND_TILE_REDWHITE:
			s.Texture.ResourceId = R.drawable.background_tile_redwhite;
			s.Frames = FrameHelper.Background;
			break;
		case TMImage.DEATH_BONES:
			s.Texture.ResourceId = R.drawable.bonetiles;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.WALL_HORIZONTAL:
			s.Texture.ResourceId = R.drawable.levelwall_lr;
			s.Frames = FrameHelper.WallHorizontal;
			break;
		case TMImage.WALL_VERTICAL:
			s.Texture.ResourceId = R.drawable.levelwall_ud;
			s.Frames = FrameHelper.WallVertical;
			break;
		case TMImage.JOURNAL_PAPER:
			s.Texture.ResourceId = R.drawable.journal_old_paper;
			s.Frames = FrameHelper.JournalPaper;
			break;
		case TMImage.SCROLLING_STONE_WALL:
			s.Texture.ResourceId = R.drawable.menu_dark_stone_h_tile;
			s.Frames = FrameHelper.MenuStoneTile;
			break;
		case TMImage.TRANSPARENT_FOG_LIGHT:
			s.Texture.ResourceId = R.drawable.level07_fog_light;
			s.Frames = FrameHelper._1024x1024;
			break;
		case TMImage.TRANSPARENT_FOG_DARK:
			s.Texture.ResourceId = R.drawable.level07_fog_dark;
			s.Frames = FrameHelper._1024x1024;
			break;
		case TMImage.TRANSPARENT_FOG_SOLID:
			s.Texture.ResourceId = R.drawable.level07_fog_solid;
			s.Frames = FrameHelper._1024x1024;
			break;
		case TMImage.BLACK_HOLES:
			s.Texture.ResourceId = R.drawable.level05_black_holes;
			s.Frames = FrameHelper._1024x1024;
			break;
		case TMImage.BLACK:
			s.Texture.ResourceId = R.drawable.common_black;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.WHITE:
			s.Texture.ResourceId = R.drawable.common_white;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.RED:
			s.Texture.ResourceId = R.drawable.common_red;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.YELLOW:
			s.Texture.ResourceId = R.drawable.common_yellow;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.ORANGE:
			s.Texture.ResourceId = R.drawable.common_orange;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.GREEN:
			s.Texture.ResourceId = R.drawable.common_green;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.BLUE:
			s.Texture.ResourceId = R.drawable.common_blue;
			s.Frames = FrameHelper.Color;
			break;
		case TMImage.STATUE_WHITE:
			s.Texture.ResourceId = R.drawable.level06_statue_white;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_RED:
			s.Texture.ResourceId = R.drawable.level06_statue_red;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_ORANGE:
			s.Texture.ResourceId = R.drawable.level06_statue_orange;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BLUE:
			s.Texture.ResourceId = R.drawable.level06_statue_blue;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_GREEN:
			s.Texture.ResourceId = R.drawable.level06_statue_green;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BACK_WHITE:
			s.Texture.ResourceId = R.drawable.level06_statue_back_white;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BACK_RED:
			s.Texture.ResourceId = R.drawable.level06_statue_back_red;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BACK_ORANGE:
			s.Texture.ResourceId = R.drawable.level06_statue_back_orange;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BACK_BLUE:
			s.Texture.ResourceId = R.drawable.level06_statue_back_blue;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.STATUE_BACK_GREEN:
			s.Texture.ResourceId = R.drawable.level06_statue_back_green;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SYMBOL_WHITE:
			s.Texture.ResourceId = R.drawable.level06_symbol_white;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SYMBOL_RED:
			s.Texture.ResourceId = R.drawable.level06_symbol_red;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SYMBOL_ORANGE:
			s.Texture.ResourceId = R.drawable.level06_symbol_orange;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SYMBOL_BLUE:
			s.Texture.ResourceId = R.drawable.level06_symbol_blue;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SYMBOL_GREEN:
			s.Texture.ResourceId = R.drawable.level06_symbol_green;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TITLE_LOGO:
			s.Texture.ResourceId = R.drawable.title_maze_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.RESUME_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_resume_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.RESTART_BUTTON:
			s.Texture.ResourceId = R.drawable.menu_restart_button;
			s.Frames = FrameHelper._256x128;
			break;
		case TMImage.JOURNAL_TEST:
			s.Texture.ResourceId = R.drawable.journal_test;
			s.Frames = FrameHelper._512x1024;
			break;
		case TMImage.JOURNAL_LEVEL_01:
			s.Texture.ResourceId = R.drawable.journal_test;
			s.Frames = FrameHelper._512x1024;
			break;
		case TMImage.JOURNAL_LEVEL_02:
			s.Texture.ResourceId = R.drawable.journal_test;
			s.Frames = FrameHelper._512x1024;
			break;
		case TMImage.JOURNAL_LEVEL_03:
			s.Texture.ResourceId = R.drawable.journal_test;
			s.Frames = FrameHelper._512x1024;
			break;
		case TMImage.JOURNAL_LEVEL_04:
			s.Texture.ResourceId = R.drawable.journal_test;
			s.Frames = FrameHelper._512x1024;
			break;
		case TMImage.TUTORIAL_POPUP_01:
			s.Texture.ResourceId = R.drawable.level01_tutorial_popup_01;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.TUTORIAL_POPUP_02:
			s.Texture.ResourceId = R.drawable.level02_tutorial_popup_02;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.TUTORIAL_POPUP_03:
			s.Texture.ResourceId = R.drawable.level02_tutorial_popup_03;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.ACTION_BTN:
			s.Texture.ResourceId = R.drawable.levelcommon_action_button;
			s.Frames = FrameHelper.ToggleButton;
			s.FrameCount = 2;
			s.Type = SpriteType.LOOP_ANIMATION;
			s.FrameRate = 3f;
			break;
		case TMImage.WALL_HORIZONTAL_2:
			s.Texture.ResourceId = R.drawable.levelwall_horizontal_02;
			s.Frames = FrameHelper.WallHorizontal;
			break;
		case TMImage.WALL_VERTICAL_2:
			s.Texture.ResourceId = R.drawable.levelwall_vertical_02;
			s.Frames = FrameHelper.WallVertical;
			break;
		case TMImage.WALL_HORIZONTAL_BLACK:
			s.Texture.ResourceId = R.drawable.levelwall_horizontal_black;
			s.Frames = FrameHelper.WallHorizontal;
			break;
		case TMImage.WALL_VERTICAL_BLACK:
			s.Texture.ResourceId = R.drawable.levelwall_vertical_black;
			s.Frames = FrameHelper.WallVertical;
			break;
		case TMImage.DOOR_LOCKED_HORIZONTAL_RED:
			s.Texture.ResourceId = R.drawable.door_red_horizontal;
			s.Frames = FrameHelper._128x64;;
			break;
		case TMImage.DOOR_LOCKED_VERTICAL_RED:
			s.Texture.ResourceId = R.drawable.door_red_vertical;
			s.Frames = FrameHelper._64x128;
			break;
		case TMImage.DOOR_LOCKED_HORIZONTAL_BLUE:
			s.Texture.ResourceId = R.drawable.door_blue_horizontal;
			s.Frames = FrameHelper._128x64;;
			break;
		case TMImage.DOOR_LOCKED_VERTICAL_BLUE:
			s.Texture.ResourceId = R.drawable.door_blue_vertical;
			s.Frames = FrameHelper._64x128;
			break;
		case TMImage.DOOR_LOCKED_HORIZONTAL_YELLOW:
			s.Texture.ResourceId = R.drawable.door_yellow_horizontal;
			s.Frames = FrameHelper._128x64;;
			break;
		case TMImage.DOOR_LOCKED_VERTICAL_YELLOW:
			s.Texture.ResourceId = R.drawable.door_yellow_vertical;
			s.Frames = FrameHelper._64x128;
			break;
		case TMImage.DOOR_LOCKED_HORIZONTAL_NEUTRAL:
			s.Texture.ResourceId = R.drawable.door_neutral_horizontal;
			s.Frames = FrameHelper._128x64;;
			break;
		case TMImage.DOOR_LOCKED_VERTICAL_NEUTRAL:
			s.Texture.ResourceId = R.drawable.door_neutral_vertical;
			s.Frames = FrameHelper._64x128;
			break;
		case TMImage.LEVEL_03_EMBLEM:
			s.Texture.ResourceId = R.drawable.level03_emblem;
			s.Frames = FrameHelper.Level03Emblem;
			s.FrameCount = 12;
			break;
		case TMImage.TRIGGER_BUTTON_LARGE:
			s.Texture.ResourceId = R.drawable.level03_button;
			s.Frames = FrameHelper._256x256;
			break;
		case TMImage.TRIGGER_BUTTON_SMALL:
			s.Texture.ResourceId = R.drawable.trigger_button_small;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_C:
			s.Texture.ResourceId = R.drawable.level04_rune_c;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_A:
			s.Texture.ResourceId = R.drawable.level04_rune_a;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_T:
			s.Texture.ResourceId = R.drawable.level04_rune_t;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_D:
			s.Texture.ResourceId = R.drawable.level04_rune_d;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_U:
			s.Texture.ResourceId = R.drawable.level04_rune_u;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_B:
			s.Texture.ResourceId = R.drawable.level04_rune_b;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_R:
			s.Texture.ResourceId = R.drawable.level04_rune_r;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_M:
			s.Texture.ResourceId = R.drawable.level04_rune_m;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LEVEL_04_RUNE_N:
			s.Texture.ResourceId = R.drawable.level04_rune_n;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.PLAYER_HUMAN:
			s.Texture.ResourceId = R.drawable.player;
			s.Frames = FrameHelper.Player;
			s.addAnimation(new int[] {0, 1, 0, 2});
			s.addAnimation(new int[] {3, 4, 3, 5});
			s.addAnimation(new int[] {6, 7, 6, 8});
			s.addAnimation(new int[] {9, 10, 9, 11});
			s.addAnimation(new int[] {12, 13, 12, 14});
			s.addAnimation(new int[] {15, 16, 15, 17});
			s.addAnimation(new int[] {18, 19, 18, 20});
			s.addAnimation(new int[] {21, 22, 21, 23});
			s.FrameCount = 24;
			s.FrameRate = 9f;
			s.Type = SpriteType.LOOP_ANIMATION;
			break;
		case TMImage.MONSTER:
			s.Texture.ResourceId = R.drawable.monster;
			s.Frames = FrameHelper.Monster;
			s.addAnimation(new int[] {1, 0, 1, 2});
			s.addAnimation(new int[] {4, 3, 4, 5});
			s.addAnimation(new int[] {7, 6, 7, 8});
			s.addAnimation(new int[] {10, 9, 10, 11});
			s.FrameCount = 12;
			s.FrameRate = 3f;
			s.Type = SpriteType.LOOP_ANIMATION;
			break;
		case TMImage.BUTTON_BARS:
			s.Texture.ResourceId = R.drawable.button_bars;
			s.Frames = FrameHelper.ButtonBars;
			break;
		case TMImage.WARNING_LEFT:
			s.Texture.ResourceId = R.drawable.warning_left;
			s.Frames = FrameHelper.WarningVertical;
			break;
		case TMImage.WARNING_RIGHT:
			s.Texture.ResourceId = R.drawable.warning_right;
			s.Frames = FrameHelper.WarningVertical;
			break;
		case TMImage.WARNING_UP:
			s.Texture.ResourceId = R.drawable.warning_up;
			s.Frames = FrameHelper.WarningHorizontal;
			break;
		case TMImage.WARNING_DOWN:
			s.Texture.ResourceId = R.drawable.warning_down;
			s.Frames = FrameHelper.WarningHorizontal;
			break;
		case TMImage.ARROW_DOWN:
			s.Texture.ResourceId = R.drawable.arrow_down;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.ARROW_UP:
			s.Texture.ResourceId = R.drawable.arrow_up;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.ARROW_LEFT:
			s.Texture.ResourceId = R.drawable.arrow_left;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.ARROW_RIGHT:
			s.Texture.ResourceId = R.drawable.arrow_right;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.DEATH_TEXT:
			s.Texture.ResourceId = R.drawable.death_screen;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.GOAL_INDICATOR:
			s.Texture.ResourceId = R.drawable.goal_indicator;
			s.Frames = FrameHelper.GoalIndicator;
			s.FrameCount = 4;
			break;
		case TMImage.SPIKES_L:
			s.Texture.ResourceId = R.drawable.spike_l;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_U:
			s.Texture.ResourceId = R.drawable.spike_u;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_R:
			s.Texture.ResourceId = R.drawable.spike_r;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_D:
			s.Texture.ResourceId = R.drawable.spike_d;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LU:
			s.Texture.ResourceId = R.drawable.spike_lu;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LR:
			s.Texture.ResourceId = R.drawable.spike_lr;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LD:
			s.Texture.ResourceId = R.drawable.spike_ld;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_UR:
			s.Texture.ResourceId = R.drawable.spike_ur;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_UD:
			s.Texture.ResourceId = R.drawable.spike_ud;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_RD:
			s.Texture.ResourceId = R.drawable.spike_rd;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LUR:
			s.Texture.ResourceId = R.drawable.spike_lur;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LUD:
			s.Texture.ResourceId = R.drawable.spike_lud;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LRD:
			s.Texture.ResourceId = R.drawable.spike_lrd;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_URD:
			s.Texture.ResourceId = R.drawable.spike_urd;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_LURD:
			s.Texture.ResourceId = R.drawable.spike_lurd;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKES_SINGLE:
			s.Texture.ResourceId = R.drawable.spike_single;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.WALL_L:
			s.Texture.ResourceId = R.drawable.levelwall_l;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_U:
			s.Texture.ResourceId = R.drawable.levelwall_u;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_R:
			s.Texture.ResourceId = R.drawable.levelwall_r;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_D:
			s.Texture.ResourceId = R.drawable.levelwall_d;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LU:
			s.Texture.ResourceId = R.drawable.levelwall_lu;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LR:
			s.Texture.ResourceId = R.drawable.levelwall_lr;
			s.Frames = FrameHelper.WallHorizontal;
			break;
		case TMImage.WALL_LD:
			s.Texture.ResourceId = R.drawable.levelwall_ld;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_UR:
			s.Texture.ResourceId = R.drawable.levelwall_ur;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_UD:
			s.Texture.ResourceId = R.drawable.levelwall_ud;
			s.Frames = FrameHelper.WallVertical;
			break;
		case TMImage.WALL_RD:
			s.Texture.ResourceId = R.drawable.levelwall_rd;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LUR:
			s.Texture.ResourceId = R.drawable.levelwall_lur;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LUD:
			s.Texture.ResourceId = R.drawable.levelwall_lud;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LRD:
			s.Texture.ResourceId = R.drawable.levelwall_lrd;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_URD:
			s.Texture.ResourceId = R.drawable.levelwall_urd;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_LURD:
			s.Texture.ResourceId = R.drawable.levelwall_lurd;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.WALL_SINGLE:
			s.Texture.ResourceId = R.drawable.levelwall_single;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.SPEED_BOOTS:
			s.Texture.ResourceId = R.drawable.speed_boots;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPEED_BOOTS_LEVEL:
			s.Texture.ResourceId = R.drawable.speed_boots_level;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_ZERO:
			s.Texture.ResourceId = R.drawable.number_0;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_ONE:
			s.Texture.ResourceId = R.drawable.number_1;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_TWO:
			s.Texture.ResourceId = R.drawable.number_2;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_THREE:
			s.Texture.ResourceId = R.drawable.number_3;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_FOUR:
			s.Texture.ResourceId = R.drawable.number_4;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_FIVE:
			s.Texture.ResourceId = R.drawable.number_5;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_SIX:
			s.Texture.ResourceId = R.drawable.number_6;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_SEVEN:
			s.Texture.ResourceId = R.drawable.number_7;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_EIGHT:
			s.Texture.ResourceId = R.drawable.number_8;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.TIMER_NINE:
			s.Texture.ResourceId = R.drawable.number_9;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.CLOCK:
			s.Texture.ResourceId = R.drawable.clock;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.GAME_ITEM_BORDER:
			s.Texture.ResourceId = R.drawable.game_item_border;
			s.Frames = FrameHelper.GameItemBorder;
			s.FrameCount = 2;
			break;
		case TMImage.DEATH_MESSAGE01:
			s.Texture.ResourceId = R.drawable.death_message_01;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE02:
			s.Texture.ResourceId = R.drawable.death_message_02;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE03:
			s.Texture.ResourceId = R.drawable.death_message_03;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE04:
			s.Texture.ResourceId = R.drawable.death_message_04;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE05:
			s.Texture.ResourceId = R.drawable.death_message_05;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE06:
			s.Texture.ResourceId = R.drawable.death_message_06;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE_RARE_01:
			s.Texture.ResourceId = R.drawable.death_message_rare_01;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE_RARE_02:
			s.Texture.ResourceId = R.drawable.death_message_rare_02;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE_RARE_03:
			s.Texture.ResourceId = R.drawable.death_message_rare_03;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.DEATH_MESSAGE_RARE_04:
			s.Texture.ResourceId = R.drawable.death_message_rare_04;
			s.Frames = FrameHelper._512x128;
			break;
		case TMImage.RED_KEY:
			s.Texture.ResourceId = R.drawable.key_red;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.BLUE_KEY:
			s.Texture.ResourceId = R.drawable.key_blue;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.YELLOW_KEY:
			s.Texture.ResourceId = R.drawable.key_yellow;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.LAMP:
			s.Texture.ResourceId = R.drawable.level06_statue_red;
			s.Frames = FrameHelper._64x64;
			break;
		case TMImage.POPUP_REDKEY:
			s.Texture.ResourceId = R.drawable.popup_redkey;
			s.Frames = FrameHelper._512x64;
			break;
		case TMImage.POPUP_BLUEKEY:
			s.Texture.ResourceId = R.drawable.popup_bluekey;
			s.Frames = FrameHelper._512x64;
			break;
		case TMImage.POPUP_YELLOWKEY:
			s.Texture.ResourceId = R.drawable.popup_yellowkey;
			s.Frames = FrameHelper._512x64;
			break;
		case TMImage.STONE_TILE:
			s.Texture.ResourceId = R.drawable.stone_tile;
			s.Frames = FrameHelper._512x512;
			break;
		case TMImage.STONE_TILE_LIT:
			s.Texture.ResourceId = R.drawable.stone_tile_lit;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.CHAPTER_0_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_0_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_1_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_1_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_2_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_2_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_3_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_3_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_4_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_4_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_5_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_5_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.CHAPTER_6_TITLE:
			s.Texture.ResourceId = R.drawable.chapter_6_title;
			s.Frames = FrameHelper._512x256;
			break;
		case TMImage.SPIKE_RED:
			s.Texture.ResourceId = R.drawable.spike_red;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKE_BLUE:
			s.Texture.ResourceId = R.drawable.spike_blue;
			s.Frames = FrameHelper._128x128;
			break;
		case TMImage.SPIKE_YELLOW:
			s.Texture.ResourceId = R.drawable.spike_yellow;
			s.Frames = FrameHelper._128x128;
			break;
		default:
			super.setupSprite(s, image);
			break;
		}
		Global.View.bindTexture(s.Texture);
	}
}