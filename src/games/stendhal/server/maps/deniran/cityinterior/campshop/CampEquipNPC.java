/***************************************************************************
 *                      (C) Copyright 2019 - Stendhal                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.deniran.cityinterior.campshop;

import java.util.LinkedHashMap;
import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;
public class CampEquipNPC implements ZoneConfigurator {

	@Override
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("Tia") {
			@Override
			public void createDialog() {
				addGreeting("Hello, and welcome to my shop.");
				addJob("I sell camping equipment, like sleeping bag");
								addGoodbye("goodbye");
			    
			}

			@Override
			protected void onGoodbye(RPEntity player) {
				setDirection(Direction.DOWN);
			}
		};
		final Map<String, Integer> pricesSell = new LinkedHashMap<String, Integer>() {{
			put("sleepingbag", 77);
		}};
		new SellerAdder().addSeller(npc, new SellerBehaviour(pricesSell), false);
		npc.setPosition(9,5);
		npc.setEntityClass("madscientistnpc");
		npc.setDescription("You see Tia. Who sell camping equipment for traveller");
		npc.setDirection(Direction.DOWN);
		zone.add(npc);

		buildShops(npc);
	}

	private void buildShops(final SpeakerNPC npc) {
		new SellerAdder().addSeller(npc, new SellerBehaviour(ShopList.get().get("denirankingsell")));
	}
}
