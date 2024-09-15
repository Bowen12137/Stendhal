/***************************************************************************
 *                   (C) Copyright 2003-2016 - Marauroa                    *
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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPClass;
import marauroa.common.game.RPObject;
import marauroa.common.game.SyntaxException;

/**
 * A monkey is a domestic animal that can be owned by a player.
 * <p>
 * It eats bananas, coconuts and grapes from the ground.
 * <p>
 * monkeys attack animals which attack them
 *
 *
 */
public class MonkeyPet extends Pet {

	/** the logger instance. */
	private static final Logger logger = Logger.getLogger(MonkeyPet.class);

	@Override
	void setUp() {
		HP = 200;
		incHP = 4;
		ATK = 10;
		DEF = 30;
		XP = 100;
		baseSpeed = 0.9;
		setAtk(ATK);
		setDef(DEF);
		setXP(XP);
		setBaseHP(HP);
		setHP(HP);
	}

	public static void generateRPClass() {
		try {
			final RPClass monkey_pet = new RPClass("monkey_pet");
			monkey_pet.isA("pet");
		} catch (final SyntaxException e) {
			logger.error("cannot generate RPClass", e);
		}
	}

	/**
	 * Creates a new wild baby dragon.
	 */
	public MonkeyPet() {
		this(null);
	}

	/**
	 * Creates a new monkey_pet that may be owned by a player.
	 * @param owner The player who should own the baby dragon
	 */
	public MonkeyPet(final Player owner) {
		super();
		setOwner(owner);
		setUp();
		setRPClass("monkey_pet");
		put("type", "monkey_pet");

		if (owner != null) {
			// add pet to zone and create RPID to be used in setPet()
			owner.getZone().add(this);
			owner.setPet(this);
		}

		update();
	}

	/**
	 * Creates a monkey_pet based on an existing pet RPObject, and assigns it
	 * to a player.
	 *
	 * @param object object containing the data for the monkey_pet
	 * @param owner
	 *            The player who should own the monkey_pet
	 */
	public MonkeyPet(final RPObject object, final Player owner) {
		super(object, owner);

		setRPClass("monkey_pet");
		put("type", "monkey_pet");

		update();
	}

	@Override
	protected
	List<String> getFoodNames() {
		return Arrays.asList("banana", "coconut", "grapes");
	}
	
	/**
	 * Determines what the pet shall do next.
	 */
	@Override
	public void logic() {
		// call super class to perform common tasks like attacking targets
		super.logic();
		//steal from creature
		if (owner.getAttackTarget() != null) {
			myTarget = owner.getAttackTarget();
			this.setTarget(myTarget);
			if (nextTo(myTarget)) {
				//chance to steal. 1% per level
				Random rand = new Random();
				int n = rand.nextInt(100);
				n += 1;
				if(n < this.getLevel()) {
					List<Item> items = this.dropItemInstances;
					Item i = items.get(rand.nextInt(items.size()));
					i.onPutOnGround(false);
				}
			}
		}
	}

}
