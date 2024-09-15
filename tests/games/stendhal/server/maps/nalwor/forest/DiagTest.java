package games.stendhal.server.maps.nalwor.forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import marauroa.server.game.db.DatabaseFactory;

public class DiagTest extends ZonePlayerAndNPCTestImpl {

	private static SpeakerNPC npc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone("0_nalwor_forest_n");
		StendhalRPZone zone = new StendhalRPZone("admin_test");
		new Dojo().configureZone(zone, null);
		new DatabaseFactory().initializeDatabase();
	}

	@Override
	@Before
	public void setUp() throws Exception {

		setZoneForPlayer("0_nalwor_forest_n");
		setNpcNames("Omura Sumitada");
		super.setUp();
		addZoneConfigurator(new Dojo(), "0_nalwor_forest_n");

		npc = getNPC("Omura Sumitada");



	}

	@Test
	public void createdialogTest() {
		assertNotNull(npc);
		player.setLevel(1);
		final Engine en = npc.getEngine();
		assertTrue(en.step(player, "hi"));
		String reply1 = getReply(npc);
		assertEquals("This is the assassins' dojo.", reply1);
		assertTrue(en.step(player, "fee"));
		String reply2 = getReply(npc);
		assertEquals("At your level of experience, your attack strength is too high to train here at this time.", reply2);

		en.step(player, "bye");

		player.setLevel(100);
		assertTrue(en.step(player, "hi"));
		String reply3 = getReply(npc);
		assertEquals("This is the assassins' dojo.", reply3);

		assertTrue(en.step(player, "fee"));
		String reply4 = getReply(npc);
		assertEquals("The fee to #train for your skill level is 625 money.", reply4);

		en.step(player, "bye");
	}

}
