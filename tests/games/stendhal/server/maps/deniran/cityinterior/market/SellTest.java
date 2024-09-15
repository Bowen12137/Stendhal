package games.stendhal.server.maps.deniran.cityinterior.market;

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

public class SellTest extends ZonePlayerAndNPCTestImpl {

    private static SpeakerNPC npc;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        QuestHelper.setUpBeforeClass();
        setupZone("0_deniran_city");
        StendhalRPZone zone = new StendhalRPZone("admin_test");
        new sellerNPC().configureZone(zone, null);
        new DatabaseFactory().initializeDatabase();
    }

    @Override
    @Before
    public void setUp() throws Exception {

        setZoneForPlayer("0_deniran_city");
        setNpcNames("bowen");
        super.setUp();
        addZoneConfigurator(new sellerNPC(), "0_deniran_city");

        npc = getNPC("bowen");



    }

    @Test
    public void createdialogTest() {
        assertNotNull(npc);
        player.setLevel(1);
        final Engine en = npc.getEngine();
        assertTrue(en.step(player, "hi"));
        String reply1 = getReply(npc);
        assertEquals("If you're looking for trading, you have come to the right place.", reply1);
        assertTrue(en.step(player, "help"));
        String reply2 = getReply(npc);
        assertEquals("If you want to trading.", reply2);

        en.step(player, "bye");


    }

}