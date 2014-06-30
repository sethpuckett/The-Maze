package com.game.themaze.level.concrete;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.the_maze.R;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.utility.TMManager;

// Obstacle course
public class Level07 extends Level {

	protected FixedSizeArray<GameEntity> _spikeAnchors;
	
	public Level07() {
		_settings.ResourceId = R.raw.level_09;
		_settings.CellWidth = (int)(Global.Renderer.Width / 15f);
		_settings.BackgroundImage = TMImage.BACKGROUND_ICE;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();
		
		_spikeAnchors = new FixedSizeArray<GameEntity>(45);
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(12, 21, (float)cellWidth / 15f));
		addAnchorDestination(_spikeAnchors.get(0), 12, 21);
		addAnchorDestination(_spikeAnchors.get(0), 12, 5);
		_entities.add(_spikeAnchors.get(0));
		_entities.add(EntityHelper.anchorWall(_spikeAnchors.get(0), 3, true, -1, 0));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(0), TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 3, true, -1, 1));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(0), TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 3, true, -1, -2));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(12, 32, (float)cellWidth / 15f));
		addAnchorDestination(_spikeAnchors.get(1), 12, 32);
		addAnchorDestination(_spikeAnchors.get(1), 12, 16);
		_entities.add(_spikeAnchors.get(1));
		_entities.add(EntityHelper.anchorWall(_spikeAnchors.get(1), 3, true, -1, 0));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(1), TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 3, true, -1, 1));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(1), TMImage.SPIKES_R, TMImage.SPIKES_L, TMImage.SPIKES_LR, 3, true, -1, -2));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(8, 48, (float)cellWidth / 5f));
		addAnchorDestination(_spikeAnchors.get(2), 8, 48);
		addAnchorDestination(_spikeAnchors.get(2), 15, 48);
		addAnchorDestination(_spikeAnchors.get(2), 15, 41);
		addAnchorDestination(_spikeAnchors.get(2), 8, 41);
		_entities.add(_spikeAnchors.get(2));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(2), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(15, 41, (float)cellWidth / 5f));
		addAnchorDestination(_spikeAnchors.get(3), 15, 41);
		addAnchorDestination(_spikeAnchors.get(3), 8, 41);
		addAnchorDestination(_spikeAnchors.get(3), 8, 48);
		addAnchorDestination(_spikeAnchors.get(3), 15, 48);
		_entities.add(_spikeAnchors.get(3));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(3), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(10, 43, (float)cellWidth / 12f));
		addAnchorDestination(_spikeAnchors.get(4), 10, 43);
		addAnchorDestination(_spikeAnchors.get(4), 13, 43);
		addAnchorDestination(_spikeAnchors.get(4), 13, 46);
		addAnchorDestination(_spikeAnchors.get(4), 10, 46);
		_entities.add(_spikeAnchors.get(4));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(4), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(13, 46, (float)cellWidth / 12f));
		addAnchorDestination(_spikeAnchors.get(5), 13, 46);
		addAnchorDestination(_spikeAnchors.get(5), 10, 46);
		addAnchorDestination(_spikeAnchors.get(5), 10, 43);
		addAnchorDestination(_spikeAnchors.get(5), 13, 43);
		_entities.add(_spikeAnchors.get(5));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(5), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, true, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(33, 33, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(6), 33, 33);
		addAnchorDestination(_spikeAnchors.get(6), 33, 26);
		_entities.add(_spikeAnchors.get(6));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(6), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(35, 26, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(7), 35, 26);
		addAnchorDestination(_spikeAnchors.get(7), 35, 33);
		_entities.add(_spikeAnchors.get(7));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(7), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(24, 39, (float)cellWidth / 11f));
		addAnchorDestination(_spikeAnchors.get(8), 24, 39);
		addAnchorDestination(_spikeAnchors.get(8), 36, 39);
		_entities.add(_spikeAnchors.get(8));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(8), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(36, 43, (float)cellWidth / 11f));
		addAnchorDestination(_spikeAnchors.get(9), 36, 43);
		addAnchorDestination(_spikeAnchors.get(9), 24, 43);
		_entities.add(_spikeAnchors.get(9));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(9), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(24, 47, (float)cellWidth / 11f));
		addAnchorDestination(_spikeAnchors.get(10), 24, 47);
		addAnchorDestination(_spikeAnchors.get(10), 36, 47);
		_entities.add(_spikeAnchors.get(10));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(10), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(36, 51, (float)cellWidth / 11f));
		addAnchorDestination(_spikeAnchors.get(11), 36, 51);
		addAnchorDestination(_spikeAnchors.get(11), 24, 51);
		_entities.add(_spikeAnchors.get(11));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(11), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(24, 55, (float)cellWidth / 11f));
		addAnchorDestination(_spikeAnchors.get(12), 24, 55);
		addAnchorDestination(_spikeAnchors.get(12), 36, 55);
		_entities.add(_spikeAnchors.get(12));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(12), TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, TMImage.SPIKES_SINGLE, 2, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(60, 64, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(13), 60, 53);
		addAnchorDestination(_spikeAnchors.get(13), 60, 66);
		_entities.add(_spikeAnchors.get(13));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(13), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(60, 55, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(14), 60, 66);
		addAnchorDestination(_spikeAnchors.get(14), 60, 53);
		_entities.add(_spikeAnchors.get(14));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(14), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(62, 66, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(15), 62, 66);
		addAnchorDestination(_spikeAnchors.get(15), 62, 53);
		_entities.add(_spikeAnchors.get(15));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(15), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(62, 53, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(16), 62, 53);
		addAnchorDestination(_spikeAnchors.get(16), 62, 66);
		_entities.add(_spikeAnchors.get(16));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(16), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(64, 64, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(17), 64, 66);
		addAnchorDestination(_spikeAnchors.get(17), 64, 53);
		_entities.add(_spikeAnchors.get(17));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(17), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(64, 55, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(18), 64, 53);
		addAnchorDestination(_spikeAnchors.get(18), 64, 66);
		_entities.add(_spikeAnchors.get(18));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(18), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(66, 62, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(19), 66, 66);
		addAnchorDestination(_spikeAnchors.get(19), 66, 53);
		_entities.add(_spikeAnchors.get(19));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(19), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(66, 57, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(20), 66, 53);
		addAnchorDestination(_spikeAnchors.get(20), 66, 66);
		_entities.add(_spikeAnchors.get(20));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(20), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(68, 60, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(21), 68, 66);
		addAnchorDestination(_spikeAnchors.get(21), 68, 53);
		_entities.add(_spikeAnchors.get(21));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(21), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(68, 59, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(22), 68, 53);
		addAnchorDestination(_spikeAnchors.get(22), 68, 66);
		_entities.add(_spikeAnchors.get(22));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(22), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(70, 58, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(23), 70, 66);
		addAnchorDestination(_spikeAnchors.get(23), 70, 53);
		_entities.add(_spikeAnchors.get(23));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(23), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(70, 61, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(24), 70, 53);
		addAnchorDestination(_spikeAnchors.get(24), 70, 66);
		_entities.add(_spikeAnchors.get(24));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(24), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(72, 56, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(25), 72, 66);
		addAnchorDestination(_spikeAnchors.get(25), 72, 53);
		_entities.add(_spikeAnchors.get(25));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(25), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(72, 63, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(26), 72, 53);
		addAnchorDestination(_spikeAnchors.get(26), 72, 66);
		_entities.add(_spikeAnchors.get(26));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(26), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(74, 54, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(27), 74, 66);
		addAnchorDestination(_spikeAnchors.get(27), 74, 53);
		_entities.add(_spikeAnchors.get(27));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(27), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(74, 65, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(28), 74, 53);
		addAnchorDestination(_spikeAnchors.get(28), 74, 66);
		_entities.add(_spikeAnchors.get(28));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(28), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(76, 54, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(29), 76, 53);
		addAnchorDestination(_spikeAnchors.get(29), 76, 66);
		_entities.add(_spikeAnchors.get(29));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(29), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(76, 65, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(30), 76, 66);
		addAnchorDestination(_spikeAnchors.get(30), 76, 53);
		_entities.add(_spikeAnchors.get(30));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(30), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(78, 56, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(31), 78, 53);
		addAnchorDestination(_spikeAnchors.get(31), 78, 66);
		_entities.add(_spikeAnchors.get(31));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(31), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(78, 63, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(32), 78, 66);
		addAnchorDestination(_spikeAnchors.get(32), 78, 53);
		_entities.add(_spikeAnchors.get(32));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(32), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(80, 58, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(33), 80, 53);
		addAnchorDestination(_spikeAnchors.get(33), 80, 66);
		_entities.add(_spikeAnchors.get(33));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(33), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(80, 61, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(34), 80, 66);
		addAnchorDestination(_spikeAnchors.get(34), 80, 53);
		_entities.add(_spikeAnchors.get(34));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(34), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(82, 60, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(35), 82, 53);
		addAnchorDestination(_spikeAnchors.get(35), 82, 66);
		_entities.add(_spikeAnchors.get(35));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(35), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(82, 59, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(36), 82, 66);
		addAnchorDestination(_spikeAnchors.get(36), 82, 53);
		_entities.add(_spikeAnchors.get(36));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(36), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(84, 62, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(37), 84, 53);
		addAnchorDestination(_spikeAnchors.get(37), 84, 66);
		_entities.add(_spikeAnchors.get(37));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(37), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(84, 57, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(38), 84, 66);
		addAnchorDestination(_spikeAnchors.get(38), 84, 53);
		_entities.add(_spikeAnchors.get(38));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(38), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(86, 64, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(39), 86, 53);
		addAnchorDestination(_spikeAnchors.get(39), 86, 66);
		_entities.add(_spikeAnchors.get(39));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(39), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(86, 55, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(40), 86, 66);
		addAnchorDestination(_spikeAnchors.get(40), 86, 53);
		_entities.add(_spikeAnchors.get(40));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(40), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(88, 66, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(41), 88, 53);
		addAnchorDestination(_spikeAnchors.get(41), 88, 66);
		_entities.add(_spikeAnchors.get(41));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(41), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(88, 53, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(42), 88, 66);
		addAnchorDestination(_spikeAnchors.get(42), 88, 53);
		_entities.add(_spikeAnchors.get(42));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(42), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(90, 64, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(43), 90, 66);
		addAnchorDestination(_spikeAnchors.get(43), 90, 53);
		_entities.add(_spikeAnchors.get(43));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(43), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
		
		_spikeAnchors.add(EntityHelper.patrolAnchor(90, 55, (float)cellWidth / 13f));
		addAnchorDestination(_spikeAnchors.get(44), 90, 53);
		addAnchorDestination(_spikeAnchors.get(44), 90, 66);
		_entities.add(_spikeAnchors.get(44));
		_entities.add(EntityHelper.anchorSpikes(_spikeAnchors.get(44), TMImage.SPIKES_U, TMImage.SPIKES_D, TMImage.SPIKES_UD, 3, false, 0, 0));
	}

}
