package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.maps.semos.library.HistorianGeographerNPC;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;


public class MeetZynnTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "testzone";

	private SpeakerNPC npc;
	private Engine en;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		setupZone(ZONE_NAME);
	}

	public MeetZynnTest() {
		setNpcNames("Zynn Iwuhos ");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new HistorianGeographerNPC(), ZONE_NAME);
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();

		npc = SingletonRepository.getNPCList().get("Zynn Iwuhos");
		en = npc.getEngine();

		quest = new MeetZynn();
		quest.addToWorld();
	}

	/**
	 * Tests for dialogues of npc.
	 */
	//TODO Leave this for later
	/*
	@Test
	public void testHiAndBye() {
		assertNotNull(npc);
		assertTrue(en.step(player, "hi Zynn Iwuhos"));
		assertTrue(npc.isTalking());
		assertEquals("Bonjour!", getReply(npc));
		assertTrue(en.step(player, "bye bye"));
		assertFalse(npc.isTalking());
		assertEquals("Au revoir!", getReply(npc));
	}*/

	/**
	 * Tests for the quest
	 */
	@Test
	public void testQuestCorrectlyIncreasing() {
		en.step(player, "hi");
		Integer xp = player.getXP();
		en.step(player, "geography");
		
		assertEquals(xp + 5, player.getXP());
		//TODO: test the rest of the Zynn quest
	}
	
	/**
	 * Tests for the quest
	 */
	@Test
	public void testQuestCorrectlyIncreasing2() {
		Integer xp = player.getXP();
		en.step(player, "history");
		
		assertEquals(xp + 5, player.getXP());
		//TODO: test the rest of the Zynn quest
	}
	
	/**
	 * Tests for the quest
	 */
	@Test
	public void testQuestCorrectlyIncreasing3() {
		Integer xp = player.getXP();
		en.step(player, "history");
		en.step(player, "history");
		assertEquals(xp + 10, player.getXP());
		//TODO: test the rest of the Zynn quest
	}
	
	/**
	 * Tests for the quest
	 */
	@Test
	public void testQuestCorrectlyIncreasing4() {
		Integer xp = player.getXP();
		en.step(player, "history");
		en.step(player, "geography");
		assertEquals(xp + 10, player.getXP());
		//TODO: test the rest of the Zynn quest
	}
	
	/**
	 * Tests for the quest
	 */
	@Test
	public void testQuestCorrectlyNotIncreasing() {
		Integer xp = player.getXP();
		en.step(player, "hi");
		
		assertEquals(xp + 0, player.getXP());
	}


}
