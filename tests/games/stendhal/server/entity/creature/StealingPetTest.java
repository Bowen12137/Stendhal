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
package games.stendhal.server.entity.creature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPObject;
import utilities.PlayerTestHelper;
import utilities.RPClass.stealingPetTestHelper;

public class StealingPetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stealingPetTestHelper.generateRPClasses();
		MockStendlRPWorld.get();
	}

	List<String> foods = Arrays.asList("banana", "coconut", "grapes");

	/**
	 * Tests for stealingPet.
	 */
	@Test
	public void testStealingPet() {
		final MonkeyPet drako = new MonkeyPet();
		assertThat(drako.getFoodNames(), is(foods));
	}

	/**
	 * Tests for StealingPetPlayer.
	 */
	@Test
	public void testStealingPetPlayer() {

		final StendhalRPZone zone = new StendhalRPZone("zone");
		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);
		final MonkeyPet drako = new MonkeyPet(bob);

		assertThat(drako.getFoodNames(), is(foods));
	}

	/**
	 * Tests for StealingPetRPObjectPlayer.
	 */
	@Test
	public void testStealingPetRPObjectPlayer() {
		RPObject template = new RPObject();
		template.put("hp", 30);
		final MonkeyPet drako = new MonkeyPet(template, PlayerTestHelper.createPlayer("bob"));
		assertThat(drako.getFoodNames(), is(foods));
	}

}
