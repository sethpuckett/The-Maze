package com.game.themaze.level.concrete;

import java.util.Random;

import com.game.loblib.entity.GameEntity;
import com.game.loblib.exception.UndefinedVertexException;
import com.game.loblib.messaging.Message;
import com.game.loblib.utility.Global;
import com.game.loblib.utility.Logger;
import com.game.loblib.utility.Manager;
import com.game.loblib.utility.android.FixedSizeArray;
import com.game.loblib.utility.area.Area;
import com.game.loblib.utility.area.Vertex;
import com.game.the_maze.R;
import com.game.themaze.behavior.DestinationMoveBehavior;
import com.game.themaze.entity.EntityHelper;
import com.game.themaze.gameitem.GameItemType;
import com.game.themaze.graphics.TMImage;
import com.game.themaze.level.Level;
import com.game.themaze.messaging.TMMessageType;
import com.game.themaze.utility.DoorOpenType;
import com.game.themaze.utility.DoorType;
import com.game.themaze.utility.TMManager;

// Pac-man maze
public class Level14 extends Level {
	
	protected FixedSizeArray<GameEntity> _spikes;
	protected FixedSizeArray<IntersectionNode> _previousNodes;
	protected FixedSizeArray<IntersectionNode> _currentNodes;
	protected FixedSizeArray<IntersectionNode> _nodes;
	
	public Level14() {
		_settings.ResourceId = R.raw.level_21;
		_settings.CellWidth = (int)(Global.Renderer.Width / 26f);
		_settings.BackgroundImage = TMImage.BACKGROUND_METAL;
		_settings.Item1 = GameItemType.RED_KEY;
		_settings.Item2 = GameItemType.BLUE_KEY;
		_settings.Item3 = GameItemType.YELLOW_KEY;
		_settings.EnableActionButton = true;
	}
	
	@Override
	public void init() {
		super.init();
		int cellWidth = TMManager.Level.getCellWidth();
		
		_spikes = new FixedSizeArray<GameEntity>(10);
		_spikes.add(EntityHelper.singleSpike(42, 94));
		_spikes.add(EntityHelper.singleSpike(54, 94));
		_spikes.add(EntityHelper.singleSpike(18, 70));
		_spikes.add(EntityHelper.singleSpike(78, 70));
		_spikes.add(EntityHelper.singleSpike(24, 49));
		_spikes.add(EntityHelper.singleSpike(72, 49));
		_spikes.add(EntityHelper.singleSpike(6, 37));
		_spikes.add(EntityHelper.singleSpike(90, 37));
		_spikes.add(EntityHelper.singleSpike(42, 46));
		_spikes.add(EntityHelper.singleSpike(54, 46));
		
		int spikeCount = _spikes.getCount();
		for (int i = 0; i < spikeCount; i++) {
			_spikes.get(i).addBehavior(new DestinationMoveBehavior(false));
			_spikes.get(i).Attributes.Speed = (float)cellWidth / 9f;
		}
		_entities.addAll(_spikes);
		
		_nodes = new FixedSizeArray<Level14.IntersectionNode>(89);
		_nodes.add(new IntersectionNode(42, 94));
		_nodes.add(new IntersectionNode(54, 94));
		_nodes.add(new IntersectionNode(33, 82));
		_nodes.add(new IntersectionNode(42, 82));
		_nodes.add(new IntersectionNode(54, 82));
		_nodes.add(new IntersectionNode(63, 82));
		_nodes.add(new IntersectionNode(42, 76));
		_nodes.add(new IntersectionNode(54, 76));
		_nodes.add(new IntersectionNode(18, 70));
		_nodes.add(new IntersectionNode(27, 70));
		_nodes.add(new IntersectionNode(33, 70));
		_nodes.add(new IntersectionNode(42, 70));
		_nodes.add(new IntersectionNode(54, 70));
		_nodes.add(new IntersectionNode(63, 70));
		_nodes.add(new IntersectionNode(69, 70));
		_nodes.add(new IntersectionNode(78, 70));
		_nodes.add(new IntersectionNode(27, 61));
		_nodes.add(new IntersectionNode(33, 61));
		_nodes.add(new IntersectionNode(42, 61));
		_nodes.add(new IntersectionNode(54, 61));
		_nodes.add(new IntersectionNode(63, 61));
		_nodes.add(new IntersectionNode(69, 61));
		_nodes.add(new IntersectionNode(18, 55));
		_nodes.add(new IntersectionNode(24, 55));
		_nodes.add(new IntersectionNode(33, 55));
		_nodes.add(new IntersectionNode(42, 55));
		_nodes.add(new IntersectionNode(48, 55));
		_nodes.add(new IntersectionNode(54, 55));
		_nodes.add(new IntersectionNode(63, 55));
		_nodes.add(new IntersectionNode(72, 55));
		_nodes.add(new IntersectionNode(78, 55));
		_nodes.add(new IntersectionNode(24, 49));
		_nodes.add(new IntersectionNode(33, 49));
		_nodes.add(new IntersectionNode(63, 49));
		_nodes.add(new IntersectionNode(72, 49));
		_nodes.add(new IntersectionNode(42, 46));
		_nodes.add(new IntersectionNode(48, 46));
		_nodes.add(new IntersectionNode(54, 46));
		_nodes.add(new IntersectionNode(18, 43));
		_nodes.add(new IntersectionNode(33, 43));
		_nodes.add(new IntersectionNode(63, 43));
		_nodes.add(new IntersectionNode(78, 43));
		_nodes.add(new IntersectionNode(42, 40));
		_nodes.add(new IntersectionNode(54, 40));
		_nodes.add(new IntersectionNode(06, 37));
		_nodes.add(new IntersectionNode(12, 37));
		_nodes.add(new IntersectionNode(18, 37));
		_nodes.add(new IntersectionNode(24, 37));
		_nodes.add(new IntersectionNode(33, 37));
		_nodes.add(new IntersectionNode(63, 37));
		_nodes.add(new IntersectionNode(72, 37));
		_nodes.add(new IntersectionNode(78, 37));
		_nodes.add(new IntersectionNode(84, 37));
		_nodes.add(new IntersectionNode(90, 37));
		_nodes.add(new IntersectionNode(42, 34));
		_nodes.add(new IntersectionNode(54, 34));
		_nodes.add(new IntersectionNode(12, 31));
		_nodes.add(new IntersectionNode(24, 31));
		_nodes.add(new IntersectionNode(33, 31));
		_nodes.add(new IntersectionNode(63, 31));
		_nodes.add(new IntersectionNode(72, 31));
		_nodes.add(new IntersectionNode(84, 31));
		_nodes.add(new IntersectionNode(39, 28));
		_nodes.add(new IntersectionNode(48, 28));
		_nodes.add(new IntersectionNode(57, 28));
		_nodes.add(new IntersectionNode(6, 25));
		_nodes.add(new IntersectionNode(15, 25));
		_nodes.add(new IntersectionNode(27, 25));
		_nodes.add(new IntersectionNode(33, 25));
		_nodes.add(new IntersectionNode(63, 25));
		_nodes.add(new IntersectionNode(69, 25));
		_nodes.add(new IntersectionNode(81, 25));
		_nodes.add(new IntersectionNode(90, 25));
		_nodes.add(new IntersectionNode(39, 22));
		_nodes.add(new IntersectionNode(48, 22));
		_nodes.add(new IntersectionNode(57, 22));
		_nodes.add(new IntersectionNode(06, 19));
		_nodes.add(new IntersectionNode(15, 19));
		_nodes.add(new IntersectionNode(27, 19));
		_nodes.add(new IntersectionNode(33, 19));
		_nodes.add(new IntersectionNode(63, 19));
		_nodes.add(new IntersectionNode(69, 19));
		_nodes.add(new IntersectionNode(81, 19));
		_nodes.add(new IntersectionNode(90, 19));
		_nodes.add(new IntersectionNode(33, 16));
		_nodes.add(new IntersectionNode(39, 16));
		_nodes.add(new IntersectionNode(48, 16));
		_nodes.add(new IntersectionNode(57, 16));
		_nodes.add(new IntersectionNode(63, 16));
		_nodes.get(0).Connections.add(_nodes.get(1));
		_nodes.get(0).Connections.add(_nodes.get(3));
		_nodes.get(1).Connections.add(_nodes.get(0));
		_nodes.get(1).Connections.add(_nodes.get(4));
		_nodes.get(2).Connections.add(_nodes.get(3));
		_nodes.get(2).Connections.add(_nodes.get(10));
		_nodes.get(3).Connections.add(_nodes.get(0));
		_nodes.get(3).Connections.add(_nodes.get(2));
		_nodes.get(3).Connections.add(_nodes.get(6));
		_nodes.get(4).Connections.add(_nodes.get(1));
		_nodes.get(4).Connections.add(_nodes.get(5));
		_nodes.get(4).Connections.add(_nodes.get(7));
		_nodes.get(5).Connections.add(_nodes.get(4));
		_nodes.get(5).Connections.add(_nodes.get(13));
		_nodes.get(6).Connections.add(_nodes.get(3));
		_nodes.get(6).Connections.add(_nodes.get(7));
		_nodes.get(6).Connections.add(_nodes.get(11));
		_nodes.get(7).Connections.add(_nodes.get(4));
		_nodes.get(7).Connections.add(_nodes.get(6));
		_nodes.get(7).Connections.add(_nodes.get(12));
		_nodes.get(8).Connections.add(_nodes.get(9));
		_nodes.get(8).Connections.add(_nodes.get(22));
		_nodes.get(9).Connections.add(_nodes.get(8));
		_nodes.get(9).Connections.add(_nodes.get(16));
		_nodes.get(10).Connections.add(_nodes.get(2));
		_nodes.get(10).Connections.add(_nodes.get(11));
		_nodes.get(11).Connections.add(_nodes.get(6));
		_nodes.get(11).Connections.add(_nodes.get(10));
		_nodes.get(11).Connections.add(_nodes.get(12));
		_nodes.get(11).Connections.add(_nodes.get(18));
		_nodes.get(12).Connections.add(_nodes.get(7));
		_nodes.get(12).Connections.add(_nodes.get(11));
		_nodes.get(12).Connections.add(_nodes.get(13));
		_nodes.get(12).Connections.add(_nodes.get(19));
		_nodes.get(13).Connections.add(_nodes.get(5));
		_nodes.get(13).Connections.add(_nodes.get(12));
		_nodes.get(14).Connections.add(_nodes.get(15));
		_nodes.get(14).Connections.add(_nodes.get(21));
		_nodes.get(15).Connections.add(_nodes.get(14));
		_nodes.get(15).Connections.add(_nodes.get(30));
		_nodes.get(16).Connections.add(_nodes.get(9));
		_nodes.get(16).Connections.add(_nodes.get(17));
		_nodes.get(17).Connections.add(_nodes.get(16));
		_nodes.get(17).Connections.add(_nodes.get(18));
		_nodes.get(17).Connections.add(_nodes.get(24));
		_nodes.get(18).Connections.add(_nodes.get(11));
		_nodes.get(18).Connections.add(_nodes.get(17));
		_nodes.get(18).Connections.add(_nodes.get(25));
		_nodes.get(19).Connections.add(_nodes.get(12));
		_nodes.get(19).Connections.add(_nodes.get(20));
		_nodes.get(19).Connections.add(_nodes.get(27));
		_nodes.get(20).Connections.add(_nodes.get(19));
		_nodes.get(20).Connections.add(_nodes.get(21));
		_nodes.get(20).Connections.add(_nodes.get(28));
		_nodes.get(21).Connections.add(_nodes.get(14));
		_nodes.get(21).Connections.add(_nodes.get(20));
		_nodes.get(22).Connections.add(_nodes.get(8));
		_nodes.get(22).Connections.add(_nodes.get(23));
		_nodes.get(22).Connections.add(_nodes.get(38));
		_nodes.get(23).Connections.add(_nodes.get(22));
		_nodes.get(23).Connections.add(_nodes.get(24));
		_nodes.get(23).Connections.add(_nodes.get(31));
		_nodes.get(24).Connections.add(_nodes.get(17));
		_nodes.get(24).Connections.add(_nodes.get(23));
		_nodes.get(24).Connections.add(_nodes.get(25));
		_nodes.get(25).Connections.add(_nodes.get(18));
		_nodes.get(25).Connections.add(_nodes.get(24));
		_nodes.get(25).Connections.add(_nodes.get(26));
		_nodes.get(26).Connections.add(_nodes.get(25));
		_nodes.get(26).Connections.add(_nodes.get(27));
		_nodes.get(26).Connections.add(_nodes.get(36));
		_nodes.get(27).Connections.add(_nodes.get(19));
		_nodes.get(27).Connections.add(_nodes.get(26));
		_nodes.get(27).Connections.add(_nodes.get(28));
		_nodes.get(28).Connections.add(_nodes.get(20));
		_nodes.get(28).Connections.add(_nodes.get(27));
		_nodes.get(28).Connections.add(_nodes.get(29));
		_nodes.get(29).Connections.add(_nodes.get(34));
		_nodes.get(29).Connections.add(_nodes.get(28));
		_nodes.get(29).Connections.add(_nodes.get(30));
		_nodes.get(30).Connections.add(_nodes.get(15));
		_nodes.get(30).Connections.add(_nodes.get(29));
		_nodes.get(30).Connections.add(_nodes.get(41));
		_nodes.get(31).Connections.add(_nodes.get(23));
		_nodes.get(31).Connections.add(_nodes.get(32));
		_nodes.get(32).Connections.add(_nodes.get(31));
		_nodes.get(32).Connections.add(_nodes.get(39));
		_nodes.get(33).Connections.add(_nodes.get(34));
		_nodes.get(33).Connections.add(_nodes.get(40));
		_nodes.get(34).Connections.add(_nodes.get(29));
		_nodes.get(34).Connections.add(_nodes.get(33));
		_nodes.get(35).Connections.add(_nodes.get(36));
		_nodes.get(35).Connections.add(_nodes.get(42));
		_nodes.get(36).Connections.add(_nodes.get(26));
		_nodes.get(36).Connections.add(_nodes.get(35));
		_nodes.get(36).Connections.add(_nodes.get(37));
		_nodes.get(36).Connections.add(_nodes.get(37));
		_nodes.get(37).Connections.add(_nodes.get(36));
		_nodes.get(37).Connections.add(_nodes.get(43));
		_nodes.get(38).Connections.add(_nodes.get(22));
		_nodes.get(38).Connections.add(_nodes.get(39));
		_nodes.get(38).Connections.add(_nodes.get(46));
		_nodes.get(39).Connections.add(_nodes.get(32));
		_nodes.get(39).Connections.add(_nodes.get(38));
		_nodes.get(39).Connections.add(_nodes.get(48));
		_nodes.get(40).Connections.add(_nodes.get(33));
		_nodes.get(40).Connections.add(_nodes.get(41));
		_nodes.get(40).Connections.add(_nodes.get(49));
		_nodes.get(41).Connections.add(_nodes.get(30));
		_nodes.get(41).Connections.add(_nodes.get(51));
		_nodes.get(42).Connections.add(_nodes.get(35));
		_nodes.get(42).Connections.add(_nodes.get(43));
		_nodes.get(42).Connections.add(_nodes.get(54));
		_nodes.get(43).Connections.add(_nodes.get(37));
		_nodes.get(43).Connections.add(_nodes.get(42));
		_nodes.get(43).Connections.add(_nodes.get(55));
		_nodes.get(44).Connections.add(_nodes.get(45));
		_nodes.get(44).Connections.add(_nodes.get(65));
		_nodes.get(45).Connections.add(_nodes.get(44));
		_nodes.get(45).Connections.add(_nodes.get(46));
		_nodes.get(45).Connections.add(_nodes.get(56));
		_nodes.get(46).Connections.add(_nodes.get(45));
		_nodes.get(46).Connections.add(_nodes.get(38));
		_nodes.get(47).Connections.add(_nodes.get(48));
		_nodes.get(47).Connections.add(_nodes.get(57));
		_nodes.get(48).Connections.add(_nodes.get(39));
		_nodes.get(48).Connections.add(_nodes.get(47));
		_nodes.get(49).Connections.add(_nodes.get(40));
		_nodes.get(49).Connections.add(_nodes.get(50));
		_nodes.get(50).Connections.add(_nodes.get(49));
		_nodes.get(50).Connections.add(_nodes.get(60));
		_nodes.get(51).Connections.add(_nodes.get(41));
		_nodes.get(51).Connections.add(_nodes.get(52));
		_nodes.get(52).Connections.add(_nodes.get(51));
		_nodes.get(52).Connections.add(_nodes.get(53));
		_nodes.get(52).Connections.add(_nodes.get(61));
		_nodes.get(53).Connections.add(_nodes.get(52));
		_nodes.get(53).Connections.add(_nodes.get(72));
		_nodes.get(54).Connections.add(_nodes.get(42));
		_nodes.get(54).Connections.add(_nodes.get(55));
		_nodes.get(55).Connections.add(_nodes.get(43));
		_nodes.get(55).Connections.add(_nodes.get(54));
		_nodes.get(56).Connections.add(_nodes.get(45));
		_nodes.get(56).Connections.add(_nodes.get(57));
		_nodes.get(57).Connections.add(_nodes.get(47));
		_nodes.get(57).Connections.add(_nodes.get(56));
		_nodes.get(57).Connections.add(_nodes.get(58));
		_nodes.get(58).Connections.add(_nodes.get(57));
		_nodes.get(58).Connections.add(_nodes.get(68));
		_nodes.get(59).Connections.add(_nodes.get(60));
		_nodes.get(59).Connections.add(_nodes.get(69));
		_nodes.get(60).Connections.add(_nodes.get(50));
		_nodes.get(60).Connections.add(_nodes.get(59));
		_nodes.get(60).Connections.add(_nodes.get(61));
		_nodes.get(61).Connections.add(_nodes.get(52));
		_nodes.get(61).Connections.add(_nodes.get(60));
		_nodes.get(62).Connections.add(_nodes.get(63));
		_nodes.get(62).Connections.add(_nodes.get(73));
		_nodes.get(63).Connections.add(_nodes.get(62));
		_nodes.get(63).Connections.add(_nodes.get(64));
		_nodes.get(63).Connections.add(_nodes.get(74));
		_nodes.get(64).Connections.add(_nodes.get(63));
		_nodes.get(64).Connections.add(_nodes.get(75));
		_nodes.get(65).Connections.add(_nodes.get(44));
		_nodes.get(65).Connections.add(_nodes.get(66));
		_nodes.get(65).Connections.add(_nodes.get(76));
		_nodes.get(66).Connections.add(_nodes.get(65));
		_nodes.get(66).Connections.add(_nodes.get(77));
		_nodes.get(67).Connections.add(_nodes.get(68));
		_nodes.get(67).Connections.add(_nodes.get(78));
		_nodes.get(68).Connections.add(_nodes.get(67));
		_nodes.get(68).Connections.add(_nodes.get(79));
		_nodes.get(69).Connections.add(_nodes.get(70));
		_nodes.get(69).Connections.add(_nodes.get(80));
		_nodes.get(70).Connections.add(_nodes.get(69));
		_nodes.get(70).Connections.add(_nodes.get(81));
		_nodes.get(71).Connections.add(_nodes.get(72));
		_nodes.get(71).Connections.add(_nodes.get(82));
		_nodes.get(72).Connections.add(_nodes.get(71));
		_nodes.get(72).Connections.add(_nodes.get(83));
		_nodes.get(73).Connections.add(_nodes.get(62));
		_nodes.get(73).Connections.add(_nodes.get(74));
		_nodes.get(73).Connections.add(_nodes.get(85));
		_nodes.get(74).Connections.add(_nodes.get(63));
		_nodes.get(74).Connections.add(_nodes.get(73));
		_nodes.get(74).Connections.add(_nodes.get(75));
		_nodes.get(75).Connections.add(_nodes.get(64));
		_nodes.get(75).Connections.add(_nodes.get(74));
		_nodes.get(75).Connections.add(_nodes.get(87));
		_nodes.get(76).Connections.add(_nodes.get(65));
		_nodes.get(76).Connections.add(_nodes.get(77));
		_nodes.get(77).Connections.add(_nodes.get(66));
		_nodes.get(77).Connections.add(_nodes.get(76));
		_nodes.get(77).Connections.add(_nodes.get(78));
		_nodes.get(78).Connections.add(_nodes.get(67));
		_nodes.get(78).Connections.add(_nodes.get(67));
		_nodes.get(78).Connections.add(_nodes.get(77));
		_nodes.get(78).Connections.add(_nodes.get(79));
		_nodes.get(79).Connections.add(_nodes.get(68));
		_nodes.get(79).Connections.add(_nodes.get(78));
		_nodes.get(79).Connections.add(_nodes.get(84));
		_nodes.get(80).Connections.add(_nodes.get(69));
		_nodes.get(80).Connections.add(_nodes.get(81));
		_nodes.get(80).Connections.add(_nodes.get(88));
		_nodes.get(81).Connections.add(_nodes.get(70));
		_nodes.get(81).Connections.add(_nodes.get(80));
		_nodes.get(81).Connections.add(_nodes.get(82));
		_nodes.get(82).Connections.add(_nodes.get(71));
		_nodes.get(82).Connections.add(_nodes.get(81));
		_nodes.get(82).Connections.add(_nodes.get(83));
		_nodes.get(83).Connections.add(_nodes.get(72));
		_nodes.get(83).Connections.add(_nodes.get(82));
		_nodes.get(84).Connections.add(_nodes.get(79));
		_nodes.get(84).Connections.add(_nodes.get(85));
		_nodes.get(85).Connections.add(_nodes.get(73));
		_nodes.get(85).Connections.add(_nodes.get(84));
		_nodes.get(85).Connections.add(_nodes.get(86));
		_nodes.get(86).Connections.add(_nodes.get(85));
		_nodes.get(86).Connections.add(_nodes.get(87));
		_nodes.get(87).Connections.add(_nodes.get(75));
		_nodes.get(87).Connections.add(_nodes.get(86));
		_nodes.get(87).Connections.add(_nodes.get(88));
		_nodes.get(88).Connections.add(_nodes.get(80));
		_nodes.get(88).Connections.add(_nodes.get(87));		
		
		_currentNodes = new FixedSizeArray<Level14.IntersectionNode>(10);
		_currentNodes.add(_nodes.get(3));
		_currentNodes.add(_nodes.get(4));
		_currentNodes.add(_nodes.get(9));
		_currentNodes.add(_nodes.get(14));
		_currentNodes.add(_nodes.get(32));
		_currentNodes.add(_nodes.get(33));
		_currentNodes.add(_nodes.get(45));
		_currentNodes.add(_nodes.get(52));
		_currentNodes.add(_nodes.get(35));
		_currentNodes.add(_nodes.get(37));
		
		_previousNodes = new FixedSizeArray<Level14.IntersectionNode>(10);
		_previousNodes.add(_nodes.get(3));
		_previousNodes.add(_nodes.get(4));
		_previousNodes.add(_nodes.get(9));
		_previousNodes.add(_nodes.get(14));
		_previousNodes.add(_nodes.get(32));
		_previousNodes.add(_nodes.get(33));
		_previousNodes.add(_nodes.get(45));
		_previousNodes.add(_nodes.get(52));
		_previousNodes.add(_nodes.get(35));
		_previousNodes.add(_nodes.get(37));
		
		for (int i = 0; i < spikeCount; i++)
			Area.sync(_spikes.get(i).Attributes.Destination, _currentNodes.get(i).Position);
		
		_entities.add(EntityHelper.gameItem(19, 71, GameItemType.RED_KEY, false));
		_entities.add(EntityHelper.gameItem(79, 71, GameItemType.BLUE_KEY, false));
		_entities.add(EntityHelper.gameItem(49, 35, GameItemType.YELLOW_KEY, false));
		
		int redId = makeDoor(48, 90, 45, 90, true, DoorType.RED, DoorOpenType.SINGLE, false);
		int blueId = makeDoor(48, 87, 50, 87, true, DoorType.BLUE, DoorOpenType.SINGLE, false);
		int yellowId = makeDoor(48, 84, 45, 84, true, DoorType.YELLOW, DoorOpenType.SINGLE, false);
		
		makeDoorTrigger(redId, 48, 91, 2, 2);
		makeDoorTrigger(blueId, 48, 88, 2, 2);
		makeDoorTrigger(yellowId, 48, 85, 2, 2);
		
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED);
	}
	
	@Override
	public void unpause() {
		super.unpause();
		
		Manager.Message.subscribe(this, TMMessageType.DESTINATION_REACHED);
	}
	
	@Override
	public void handleMessage(Message message) {
		super.handleMessage(message);
		
		if (message.Type == TMMessageType.DESTINATION_REACHED) {
			GameEntity entity = message.getData();
			int index = 0;
			if ((index = _spikes.find(entity, false)) != -1)
				updateDestination(index);
		}
		
	}

	protected void updateDestination(int index) {
		IntersectionNode node = _currentNodes.get(index);
		_currentNodes.set(index, getRandomNode(index, _previousNodes.get(index)));
		_previousNodes.set(index, node);
		Area.sync(_spikes.get(index).Attributes.Destination, _currentNodes.get(index).Position);
	}
	
	protected IntersectionNode getClosestNode(int index) {
		float cellWidth = TMManager.Level.getCellWidth();
		IntersectionNode node = null;
		int minIndex = -1;
		float minDistance = Float.MAX_VALUE;
		Vertex playerLocation = Manager.Vertex.allocate();
		int conCount =_currentNodes.get(index).Connections.getCount();
		for (int i = 0; i < conCount; i++) {
			try {
				// check all connecting nodes; remember shortest distance
				Area.sync(playerLocation, _player.Attributes.Area.Position);
				playerLocation.Y -= cellWidth - 1;
				float curDistance = Vertex.distanceSquared(_currentNodes.get(index).Connections.get(i).Position, playerLocation);
				if (curDistance < minDistance)
				{
					minIndex = i;
					minDistance = curDistance;
				}
				// if player is on same row or column and adjacent to spike, go directly towards
				if ((isHorizontal(_currentNodes.get(index).Position, _player.Attributes.Area.Position) &&
					isHorizontal(_currentNodes.get(index).Position, _currentNodes.get(index).Connections.get(i).Position) &&
					isPlayerCloser(_currentNodes.get(index).Position, _currentNodes.get(index).Connections.get(i))) ||
					(isVertical(_currentNodes.get(index).Position, _player.Attributes.Area.Position) &&
					isVertical(_currentNodes.get(index).Position, _currentNodes.get(index).Connections.get(i).Position) &&
					isPlayerCloser(_currentNodes.get(index).Position, _currentNodes.get(index).Connections.get(i)))) {
					minIndex = i;
					minDistance = -1;
				}
			} catch (UndefinedVertexException e) {
				Logger.e(_tag, "undefined vertex");
			}
		}
		Manager.Vertex.release(playerLocation);
		
		if (minIndex != -1)
			node = _currentNodes.get(index).Connections.get(minIndex);
		
		return node;
	}
	
	protected IntersectionNode getSecondNode(int index) {
		IntersectionNode node = null;
		int secondIndex = -1;
		float secondDistance = Float.MAX_VALUE;
		int conCount =_currentNodes.get(index).Connections.getCount();
		for (int i = 0; i < conCount; i++) {
			try {
				// check all connecting nodes; remember second shortest distance
				float curDistance = Vertex.distanceSquared(_currentNodes.get(index).Connections.get(i).Position, _player.Attributes.Area.Position);
				if (i == 0 ||
					(i == 1 && curDistance > secondDistance) ||
					(i == 2 && curDistance < secondDistance))
				
				{
					secondIndex = i;
					secondDistance = curDistance;
				}
			} catch (UndefinedVertexException e) {
				Logger.e(_tag, "undefined vertex");
			}
		}
		
		if (secondIndex != -1)
			node = _currentNodes.get(index).Connections.get(secondIndex);
		
		return node;
	}
	
	protected IntersectionNode getRandomNode(int index, IntersectionNode previousNode) {
		int count = _currentNodes.get(index).Connections.getCount();
		Random rand = new Random();
		IntersectionNode node = null;
		while ((node = _currentNodes.get(index).Connections.get(rand.nextInt(count))) == previousNode)
		{}
		
		return node;
	}
	
	// return true if the vertex is on the same row as the spike
	protected boolean isHorizontal(Vertex v1, Vertex v2) {
		float cellWidth = TMManager.Level.getCellWidth();
		return
			v2.Y - v1.Y >= 0 &&
			v2.Y - v1.Y < cellWidth * 2f;
	}
	
	// return true if the vertex is on the same column as the spike
	protected boolean isVertical(Vertex v1, Vertex v2) {
		float cellWidth = TMManager.Level.getCellWidth();
		return
			v1.X - v2.X >= 0 &&
			v1.X - v2.X < cellWidth * 2f;
	}
	
	//return true if player is closer to node
	protected boolean isPlayerCloser(Vertex v1, IntersectionNode node) {
		boolean result = false;
		try {
			result =
				Vertex.distanceSquared(_player.Attributes.Area.Position, node.Position) <
				Vertex.distanceSquared(v1, node.Position);
		} catch (UndefinedVertexException e) {
			Logger.e(_tag, "undefined vertex");
		}
		return result;
	}
	
	protected class IntersectionNode {
		public IntersectionNode(int x, int y) {
			Position = new Vertex(xGridPosition(x), yGridPosition(y));
			Connections = new FixedSizeArray<Level14.IntersectionNode>(4);
		}
		
		public Vertex Position;
		public FixedSizeArray<IntersectionNode> Connections;
	}
}
