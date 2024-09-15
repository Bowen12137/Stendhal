package games.stendhal.server.maps.deniran.cityinterior.furnitureshop;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

public class FurnitureSellerNPCTest extends ZonePlayerAndNPCTestImpl {

	private final static String npcName = "Furniture seller";
	// change this; most probably he shouldn't be here
	private static final String ZONE_NAME = "int_deniran_weapons_shop";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	//
	public FurnitureSellerNPCTest() {
		setNpcNames("Steve Jobs");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new FurnitureSellerNPC(), ZONE_NAME);
	}

	// test hi and bye
	// this should probably work out of the box
	@Test
	public void testVoiceLines() {
		final SpeakerNPC npc = getNPC("Steve Jobs");
		assertNotNull(npc);
		final Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Hello! Wecome to the furniture shop. NOTE: THIS NPC IS STILL IN DEVELOPMENT!", getReply(npc));

		assertTrue(en.step(player, "job"));
		assertEquals("I am the local furniture dealer", getReply(npc));

		assertTrue(en.step(player, "offer"));
		assertEquals("Check out the blackboards for my prices. NOTE: MORE ITEMS ARE EXPECTED SOON!", getReply(npc));

		assertTrue(en.step(player, "bye"));
		assertEquals("Come back again, customer!", getReply(npc));
	}

	// check if the items we are selling are presented in the global shop info
	// more exactly if the furniture shop in deniran is selling them
	@Test
	public void testFurnitureShopList() {
		// all existing shops
		final ShopList shops = ShopList.get();
		
		// get the furniture shop
		Map<String, Integer> furnitureShopList = shops.get("deniranfurnituresell");
		
		// check if each item is presented in the furniture shop
		// this has to be updated when more items are added
		assertTrue(furnitureShopList.containsKey("stool"));
		assertTrue(furnitureShopList.containsKey("torch"));
		assertTrue(furnitureShopList.containsKey("chess"));
		assertTrue(furnitureShopList.containsKey("basin"));

	}
}
