package games.stendhal.server.maps.quests;

import java.util.List;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.SetQuestAction;
import games.stendhal.server.entity.npc.action.SetQuestAndModifyKarmaAction;
import games.stendhal.server.entity.npc.condition.AndCondition;
import games.stendhal.server.entity.npc.condition.GreetingMatchesNameCondition;
import games.stendhal.server.entity.npc.condition.QuestActiveCondition;
import games.stendhal.server.entity.npc.condition.QuestCompletedCondition;
import games.stendhal.server.entity.npc.condition.QuestNotStartedCondition;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.Region;

public class GetPet extends AbstractQuest {
	private static final String QUEST_SLOT = "get_pet";
	
	@Override
	public String getSlotName() {
		return QUEST_SLOT;
	}
	
	private void step_1() {
		final SpeakerNPC npc = npcs.get("Byron Mcgalister");

		// player says hi before starting the quest
		npc.add(
			ConversationStates.IDLE,
			ConversationPhrases.GREETING_MESSAGES,
			new AndCondition(new GreetingMatchesNameCondition(npc.getName()),
					new QuestCompletedCondition(FishermansLicenseQuiz.QUEST_SLOT),
					new QuestNotStartedCondition(QUEST_SLOT)),
			ConversationStates.ATTENDING,
			"Hello! Would you like to help me get rid of the creature?",
			null);
		
		// player is willing to help
		npc.add(ConversationStates.QUEST_2_OFFERED,
			ConversationPhrases.YES_MESSAGES, null,
			ConversationStates.ATTENDING,
			"You have to lure out the creature with something shiny.",
			new SetQuestAction(QUEST_SLOT, ""));		

		
		// player is not willing to help
		npc.add(ConversationStates.QUEST_2_OFFERED,
			ConversationPhrases.NO_MESSAGES, null,
			ConversationStates.ATTENDING,
			"It's okay, I hope you would be able to help me some other time.",
			new SetQuestAndModifyKarmaAction(QUEST_SLOT, "rejected", -5.0));

}
	private void step_2() {
		final SpeakerNPC npc = npcs.get("Byron Mcgalister");

		// player returns while quest is still active
		npc.add(
			ConversationStates.IDLE,
			ConversationPhrases.GREETING_MESSAGES,
			new AndCondition(new GreetingMatchesNameCondition(npc.getName()),
					new QuestActiveCondition(QUEST_SLOT)),
			ConversationStates.ATTENDING,
			"Welcome back. I hope you were able to get rid of the creature from the house.",
			null);

		// player returns after finishing the quest
		npc.add(
			ConversationStates.IDLE,
			ConversationPhrases.GREETING_MESSAGES,
			new AndCondition(new GreetingMatchesNameCondition(npc.getName()),
					new QuestCompletedCondition(QUEST_SLOT)),
			ConversationStates.ATTENDING,
			"Welcome! Good to see you again! I hope you have finished the task. Good luck for other quests.",
			null);
	}
	
	public void rewardPlayer(final Player player) {
		final Item pet = SingletonRepository.getEntityManager().getItem("monkey");
		pet.setBoundTo(player.getName());
		player.equipOrPutOnGround(pet);
		player.addXP(100);
	}
	
	

	@Override
	public List<String> getHistory(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToWorld() {
		fillQuestInfo(
				"Get Pet from NPC's House",
				"Byran Mcgalister will grant the creature as a pet to those who can help him.",
				true);
		step_1();
		step_2();
		
	}

	@Override
	public String getName() {
		return "GetPet";
	}
	
	@Override
	public String getRegion() {
		return Region.DENIRAN;
	}
	
	@Override
	public String getNPCName() {
		return "Byron Mcgalister";
	}
	
}
	
	
