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
package games.stendhal.server.maps.deniran.cityinterior.castle;


import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.npc.ChatCondition;
//import games.stendhal.server.entity.npc.ConversationPhrases;

import games.stendhal.server.entity.npc.SpeakerNPC;



import games.stendhal.server.entity.npc.condition.AndCondition;
import games.stendhal.server.entity.npc.condition.NotCondition;
import games.stendhal.server.entity.npc.condition.PlayerHasItemWithHimCondition;
import games.stendhal.server.entity.player.Player;
import games.stendhal.common.parser.Sentence;


public class NPC implements ZoneConfigurator {
	
	protected static final String QUEST_SLOT = null;
	static boolean permission = false;
	final String rejectedMessage = "Only own a house and have 20000 coins so that you can hire it!";

	@Override
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {

		//money

//		final ChatCondition meetsPermission = new ChatCondition() {
//			@Override
//			public boolean fire(final Player player, final Sentence sentence, final Entity npc) {
//				return permission;
//			}
//		};

		
//		final ChatAction buySuccess = new ChatAction() {
//			@Override
//			public void fire(final Player player, final Sentence sentence, final EventRaiser raiser) {
//				
//				player.drop("money",20000);
//				NPC npc = new NPC();
//				npc.changeState();
//			}
//		};
		
		final ChatCondition meetsmoneyCondition = new ChatCondition() {
			@Override
			public boolean fire(final Player player, final Sentence sentence, final Entity npc) {
				return (player.isEquipped("money",20000));
			}
		};
		
		final ChatCondition meetsLevelCapCondition = new ChatCondition() {
			@Override
			public boolean fire(final Player player, final Sentence sentence, final Entity npc) {
				return (player.getLevel()>60);
			}
		};
		


		
		final SpeakerNPC npc = new SpeakerNPC("housekeeper") {
			

			
			
			@Override
			public void createDialog() {
				addGreeting("Hello, and welcome to Deniran castle.");
				addJob("I'm the castle housekeeper. If you want to hire anything in the city, let me know");
				addQuest("I don't have anything for you at the moment. But... There have been rumors of blordroughs digging caves under the city. I will probobly need your help in the future.");
				addGoodbye("Fare thee well, stranger!");
				addReply("request", "If you want to hire anything in the city, let me know");
				addReply("hire", "you true can hire that stall, but you need to reach level 60 and own a house. Btw, the fee for renting that stall is quite expensive..... 20000 coins 6 month....");
				add(ConversationStates.ATTENDING,
						"done",
						new AndCondition(
								new NotCondition(meetsmoneyCondition),
								new NotCondition(meetsLevelCapCondition),
								new NotCondition(new PlayerHasItemWithHimCondition("house_lince"))),
						ConversationStates.ATTENDING,
						"You need to meet all the condition house&money&level to hire this place.",
						null);
				add(ConversationStates.ATTENDING,
						"done",
						new AndCondition(
								meetsmoneyCondition,
								meetsLevelCapCondition,
								new PlayerHasItemWithHimCondition("house_lince")),
						ConversationStates.ATTENDING,
						"Now you own the stall",
						null);
				
				
				addReply("finish", "Congrats");
			}

			@Override
			protected void onGoodbye(RPEntity player) {
				setDirection(Direction.DOWN);
			}
		};
		
		
		npc.setPosition(12,9);
		npc.setEntityClass("welcomernpc");
		npc.setDescription("You see the housekeeper.");
		npc.setDirection(Direction.DOWN);
		zone.add(npc);

		
	}

//	protected void chageState() {
//		// TODO Auto-generated method stub
//		return null;
//	}




}
