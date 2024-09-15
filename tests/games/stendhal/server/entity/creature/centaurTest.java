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

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.constants.Nature;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;

public class centaurTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		MockStendhalRPRuleProcessor.get();
	}

	/**
	 * Tests for solar centaur and fire.
	 */
	@Test
	public void solarCentaur() throws Exception {
		final Creature sol = SingletonRepository.getEntityManager().getCreature("solar centaur");
		assertTrue(sol.getSusceptibility(Nature.FIRE) < 1);
		assertTrue(sol.getSusceptibility(Nature.ICE) > 1);
	}

	/**
	 * Tests for glacial centaur and ice.
	 */
	@Test
	public void glacialCentaur() throws Exception {
		final Creature ice = SingletonRepository.getEntityManager().getCreature("glacier centaur");
		assertTrue(ice.getSusceptibility(Nature.FIRE) > 1);
		assertTrue(ice.getSusceptibility(Nature.ICE) < 1);
	}

}
