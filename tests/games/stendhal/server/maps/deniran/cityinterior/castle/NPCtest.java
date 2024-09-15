package games.stendhal.server.maps.deniran.cityinterior.castle;

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

public class NPCtest extends ZonePlayerAndNPCTestImpl {

    private static SpeakerNPC npc;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        QuestHelper.setUpBeforeClass();
        setupZone("int_deniran_castle");
        StendhalRPZone zone = new StendhalRPZone("admin_test");
        new NPC().configureZone(zone, null);
        new DatabaseFactory().initializeDatabase();
    }

    @Override
    @Before
    public void setUp() throws Exception {

        setZoneForPlayer("int_deniran_castle");
        setNpcNames("housekeeper");
        super.setUp();
        addZoneConfigurator(new NPC(), "0_deniran_city");

        npc = getNPC("housekeeper");



    }

    @Test
    public void createdialogTest() {
        assertNotNull(npc);
        player.setLevel(1);
        final Engine en = npc.getEngine();
        assertTrue(en.step(player, "hi"));
        String reply1 = getReply(npc);
        assertEquals("Hello, and welcome to Deniran castle.", reply1);
        assertTrue(en.step(player, "hire"));
        String reply2 = getReply(npc);
        assertEquals("you true can hire that stall, but you need to reach level 60 and own a house. Btw, the fee for renting that stall is quite expensive..... 20000 coins 6 month....", reply2);

        en.step(player, "bye");


    }

}