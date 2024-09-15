/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//import java.util.LinkedList;
//import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.entity.item.token.Token;
import games.stendhal.server.entity.mapstuff.portal.Door;
import games.stendhal.server.entity.mapstuff.sign.Sign;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.maps.MockStendlRPWorld;
//import games.stendhal.server.maps.quests.ReverseArrow.Timer;
//import marauroa.common.game.IRPZone;
import marauroa.common.game.RPClass;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class ReverseArrowTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		QuestHelper.setUpBeforeClass();
		StendhalRPZone zone = new StendhalRPZone("int_ados_reverse_arrow");
		MockStendlRPWorld.get().addRPZone(zone);
		MockStendlRPWorld.get().addRPZone(new StendhalRPZone("0_semos_mountain_n2"));

		if (!RPClass.hasRPClass("door")) {
			Door.generateRPClass();
		}
		if (!RPClass.hasRPClass("sign")) {
			Sign.generateRPClass();
		}

	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
		NPCList.get().clear();
	}


	/**
	 * Tests for getSlotName.
	 */
	@Test
	public void testGetSlotName() {
		assertEquals("reverse_arrow", new ReverseArrow().getSlotName());
	}

	/**
	 * Tests for addToWorld.
	 */
	@Test
	public void testAddToWorld() {

		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
	}

	/**
	 * Tests for finish.
	 */
	@Test
	public void testFinish() {
		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
		arrowquest.player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(arrowquest.player);
		arrowquest.finish(false, null);
		assertNotNull(arrowquest.player);

		arrowquest.finish(true, null);
		assertNull(arrowquest.player);
	}
	
	/**
	 * Tests for checkBoard.
	 */
	
//	@Test
//	public void testCheckBoard() {
//		ReverseArrow arrowquest = new ReverseArrow();
//		arrowquest.addToWorld();
//		arrowquest.player = PlayerTestHelper.createPlayer("ana");
//		StendhalRPZone zone = new StendhalRPZone("int_ados_reverse_arrow");
//		arrowquest.start(arrowquest.player);
//		arrowquest.removeAllTokens();
//		arrowquest.addTokenToWorld(0,0);
//		arrowquest.addTokenToWorld(-1,1);
//		arrowquest.addTokenToWorld(1,1);
//		arrowquest.addTokenToWorld(-2,2);
//		arrowquest.addTokenToWorld(-1,2);
//		arrowquest.addTokenToWorld(0,2);
//		arrowquest.addTokenToWorld(1,2);
//		arrowquest.addTokenToWorld(2,2);
//		arrowquest.addTokenToWorld(0,3);
//		assertEquals(true, arrowquest.getCheckBoard()); //idk
//	}
		//arrowquest.tokens = new LinkedList<Token>();
		// * * 0 * *     * * 0 * *
		// * 1 2 3 *     * 1 * 2 *
		// 4 5 6 7 8	 3 4 5 6 7
		//				 * * 8 * *
		//arrowquest.ReverseArrowCheck.checkBoard()
		//arrowquest.ReverseArrowCheck checker = new arrowquest.ReverseArrowCheck();
		
		
		//Create player??? Start quest. Call start create tokens. Add to world. Set position. // Check
		//assert Function return val idk. Act + assert. Or make so errors when test doesnt work.

}

// addTokentoworld function. Call start on reverse arrow create tokens
// Implement the quest here. Set position of tokens. (Cue npc to) check work.
// Create instance of the classs.
// Init class.
//
// Create instance of class implement quest. Reverse arrow call start create tokens. addtokentoworld. Set position of tokens. Check work.
//
//
// Tests for
//
