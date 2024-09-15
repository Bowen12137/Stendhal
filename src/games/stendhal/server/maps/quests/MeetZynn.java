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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.IncreaseXPAction;
import games.stendhal.server.entity.npc.action.MultipleActions;
import games.stendhal.server.entity.npc.condition.LevelGreaterThanCondition;
import games.stendhal.server.entity.npc.condition.LevelLessThanCondition;
import games.stendhal.server.entity.player.Player;

/**
 * QUEST: Speak with Zynn PARTICIPANTS: - Zynn
 *
 * STEPS: <ul>
 * <li> Talk to Zynn to activate the quest
 * <li> Keep speaking with Zynn
 * </ul>
 *
 * REWARD: - 5 XP for each question asked
 * REPETITIONS: - As much as wanted.
 */
public class MeetZynn extends AbstractQuest {
	private static final String QUEST_SLOT = "meetzynn";
	
	@Override
	public String getSlotName() {
		return QUEST_SLOT;
	}
	
	private void prepareZynn() {

		final SpeakerNPC npc = npcs.get("Zynn Iwuhos");

		//this holds info for all possible available words which cause Zynn to give more info
		Map<String, String> possibleAnswers = new HashMap<String, String>();
		loadPossibleAnswers(possibleAnswers);
		
		npc.add(ConversationStates.ATTENDING, "bye",
			new LevelLessThanCondition(15),
			ConversationStates.IDLE,
			"Bye. Hey, if you're going to hang around the library, don't forget to be quiet; people could be studying!",
			null);

		npc.add(ConversationStates.ATTENDING, "bye",
			new LevelGreaterThanCondition(14),
			ConversationStates.IDLE,
			"Bye. Hey, you should consider getting a library card, you know.",
			null);
		
		//creating the actions that follow up certain action - in our case only XP increase
		final List<ChatAction> actions = new LinkedList<ChatAction>();
		//todo: possible issues - quest never finishes. The row below ends it. It may be added after bye, but I have to check this
		//actions.add(new SetQuestAction(QUEST_SLOT, "done"));
		
		//this is the reward; the player gets 5 XP no matter what
		actions.add(new IncreaseXPAction(5));

		//this covers all possible answers which share the same behavior - Zynn gives information and the player gets xp
		for (Map.Entry<String, String> entry : possibleAnswers.entrySet()) {
			npc.add(ConversationStates.ATTENDING,
					entry.getKey(),
					null,
					ConversationStates.ATTENDING,
					entry.getValue(),
					new MultipleActions(actions));
	    }
	}

	@Override
	public void addToWorld() {
		fillQuestInfo(
				"Meet Zynn Iwuhos",
				"Zynn Iwuhos, in the Semos library, is a great source of useful information.",
				false);
		prepareZynn();
	}

	@Override
	public String getName() {
		return "MeetZynn";
	}

	// no quest slots ever get set so making it visible seems silly
	// however, there is an entry for another quest slot in the games.stendhal.server.maps.semos.library.HistorianGeographerNPC file
	@Override
	public boolean isVisibleOnQuestStatus() {
		return false;
	}

	@Override
	public List<String> getHistory(final Player player) {
		return new ArrayList<String>();
		//this is empty because this quest doesn't have steps and is not visible in the journal
		//it starts when the player starts talking to Zynn and ends when the player stops talking
	}

	@Override
	public String getNPCName() {
		return "Zynn Iwuhos";
	}
	
	//this loads all possible responses to all available answers
	//it is separated from the rest because it will be easier to be read and understood
	private void loadPossibleAnswers(Map<String, String> map) {
		map.put("history", "At present, there are two significant powers on Faiumoni; the Deniran Empire, and the dark legions of Blordrough. Blordrough has recently conquered the south of the island, seizing several steel mines and a large gold mine. At present, Deniran still controls the central and northern parts of Faiumoni, including several gold and mithril mines.");
		map.put("news", "The Deniran Empire is currently seeking adventurers to sign on as mercenaries with their army to retake southern Faiumoni from the forces of Blordrough. Unfortunately Blordrough is still holding out against everything the Empire can throw at him.");
		map.put("geography", "Let's talk about the different #places you can visit on Faiumoni! I can also help you #get and #use a map, or give you advice on using the psychic #SPS system.");
		map.put("places", "The most important locations on #Faiumoni are #Or'ril Castle, #Semos, #Ados, #Nalwor, and of course #Deniran City.");
		map.put("Faiumoni", "Faiumoni is the island on which you stand! You've probably already noticed the mountains to the north. There is also a large desert in the middle of the island, and of course the river which bisects it, just below #Or'ril Castle to the south.");
		map.put("Semos", "Semos is our town where you are right now. We're on the north side of Faiumoni, with a population of about 40-50.");
		map.put("Ados", "Ados is an important coastal city to the east of us here in #Semos, where merchants bring trade from #Deniran. It's widely considered to be one of the Empire's most important shipping routes.");
		map.put("Or'ril", "Or'ril Castle is one of a series of such castles built to defend the imperial road between #Ados and #Deniran. When in use, it housed a fighting force of around 60 swordsmen, plus ancillary staff. Now that most of the Empire's army has been sent south to fend off the invaders, the castle has been abandoned by the military. As far as I'm aware, it should be empty; I hear some rumours, though...");
		map.put("Nalwor", "Nalwor is an ancient elven city, built deep inside the forest to the southeast of us long before humans ever arrived on this island. Elves don't like mixing with other races, but we're given to understand that Nalwor was built to help defend their capital city of Teruykeh against an evil force.");
		map.put("Deniran", "The Empire's capital city of Deniran lies in the heart of Faiumoni, and is the main base of operations for the Deniran army. Most of the Empire's main trade routes with other countries originate in this city, then extending north through #Ados, and south to Sikhw. Unfortunately, the southern routes were been destroyed when Blordrough and his armies conquered Sikhw, some time ago now.");
		map.put("use", "Once you #get a map, there are three scales on which you need to understand it. Firstly, there are the map #levels, then you need to familiarize yourself with the #naming conventions for the different zones within those levels, and lastly you should learn how we describe a person's #positioning within a zone. We'll have you navigating around in no time!");
		map.put("levels", "Maps are split into levels according to the height of that particular area above or below the surface. Areas on the surface are at level 0. The level number is the first thing in a map's name. For instance, #Semos itself is at ground level, so it is level 0; its map is called \\\"0_semos_city\\\". The first level of the dungeon beneath us is at level -1, so its map is called \\\"-1_semos_dungeon\\\". You should note, though, that a map of a building's interior will usually have the level at the end of the name instead, with \\\"int\\\" (for \\\"interior\\\") at the start. For instance, the upstairs floor of the tavern would be mapped out as \\\"int_semos_tavern_1\\\".");
		map.put("naming", "Each map is usually split up into \\\"sets\\\" of zones, with a central feature that is used as a reference point. The zones surrounding this central zone are named by the direction in which they lie from it. For instance, from the central zone \\\"0_semos_city\\\", you can travel west to the old village at \\\"0_semos_village_w\\\", or you could travel two zones north and one west to the mountains at \\\"0_semos_mountain_n2_w\\\".");
		map.put("positioning", "Positioning within a zone is simply described in terms of co-ordinates. We conventionally take the top-left corner (that is, the northwest corner) to be the origin point with co-ordinates (0, 0). The first co-ordinate ('x') increases as you move to the right (that is, east) within the zone, and the second co-ordinate ('y') increases as you move down (that is, south).");
		map.put("get", "You can get a map of Stendhal at #https://stendhalgame.org/world/atlas.html if you want one. Careful you don't spoil any surprises for yourself, though!");
		map.put("SPS", "SPS stands for Stendhal Positioning System; you can ask #Io in the Temple about the exact details of how it works. Essentially, it allows you to ascertain the exact current location of yourself or your friends at any time.");
		map.put("Io", "Her full name is \\\"Io Flotto\\\". She spends most of her time in the Temple, um, floating. She may seem weird, but her \\\"intuition\\\" works far better than any mere compass, as I can vouch.");
	}
}
