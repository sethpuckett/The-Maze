package com.game.themaze.level;

import com.game.themaze.graphics.TMImage;

public class WallSpriteSet {
	// Walls
	public int  L = TMImage.WALL_L;
	public int  U = TMImage.WALL_U;
	public int  R = TMImage.WALL_R;
	public int  D = TMImage.WALL_D;
	public int  LU = TMImage.WALL_LU;
	public int  LR = TMImage.WALL_LR;
	public int  LD = TMImage.WALL_LD;
	public int  UR = TMImage.WALL_UR;
	public int  UD = TMImage.WALL_UD;
	public int  RD = TMImage.WALL_RD;
	public int  LUR = TMImage.WALL_LUR;
	public int  LUD = TMImage.WALL_LUD;
	public int  LRD = TMImage.WALL_LRD;
	public int  URD = TMImage.WALL_URD;
	public int  LURD = TMImage.WALL_LURD;
	public int  Single = TMImage.WALL_SINGLE;
	
	// Spikes
	public int  SL = TMImage.SPIKES_L;
	public int  SU = TMImage.SPIKES_U;
	public int  SR = TMImage.SPIKES_R;
	public int  SD = TMImage.SPIKES_D;
	public int  SLU = TMImage.SPIKES_LU;
	public int  SLR = TMImage.SPIKES_LR;
	public int  SLD = TMImage.SPIKES_LD;
	public int  SUR = TMImage.SPIKES_UR;
	public int  SUD = TMImage.SPIKES_UD;
	public int  SRD = TMImage.SPIKES_RD;
	public int  SLUR = TMImage.SPIKES_LUR;
	public int  SLUD = TMImage.SPIKES_LUD;
	public int  SLRD = TMImage.SPIKES_LRD;
	public int  SURD = TMImage.SPIKES_URD;
	public int  SLURD = TMImage.SPIKES_LURD;
	public int  SSingle = TMImage.SPIKES_SINGLE;
	
	public void setSimpleImages(int horizontalWallImage, int verticalWallImage, int horizontalSpikeImage, int verticalSpikeImage) {
		L = horizontalWallImage;
		U = verticalWallImage;
		R = horizontalWallImage;
		D = verticalWallImage;
		LU = horizontalWallImage;
		LR = horizontalWallImage;
		LD = horizontalWallImage;
		UR = horizontalWallImage;
		UD = verticalWallImage;
		RD = horizontalWallImage;
		LUR = horizontalWallImage;
		LUD = horizontalWallImage;
		LRD = horizontalWallImage;
		URD = horizontalWallImage;
		LURD = horizontalWallImage;
		Single = horizontalWallImage;
		
		SL = horizontalSpikeImage;
		SU = verticalSpikeImage;
		SR = horizontalSpikeImage;
		SD = verticalSpikeImage;
		SLU = horizontalSpikeImage;
		SLR = horizontalSpikeImage;
		SLD = horizontalSpikeImage;
		SUR = horizontalSpikeImage;
		SUD = verticalSpikeImage;
		SRD = horizontalSpikeImage;
		SLUR = horizontalSpikeImage;
		SLUD = horizontalSpikeImage;
		SLRD = horizontalSpikeImage;
		SURD = horizontalSpikeImage;
		SLURD = horizontalSpikeImage;
		SSingle = horizontalSpikeImage;
	}
}
