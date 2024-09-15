package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.Map;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.ConsumableItem;
import games.stendhal.server.entity.status.PoisonAttacker;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;

public class TeleportScrollTest {

	//Instantiating 2 zones and a player
	static StendhalRPZone zone1 = new StendhalRPZone("zone1");
	static StendhalRPZone zone2 = new StendhalRPZone("0_semos_city", 50, 50);
	static Player player = PlayerTestHelper.createPlayer("player");
	
	@BeforeClass
	public static void setUpBeforeClass() {
		//Add the 2 zones to a world and the player to one zone
		MockStendlRPWorld.get().addRPZone(zone1);
		MockStendlRPWorld.get().addRPZone(zone2);
		zone1.add(player);
	}

	@Test
	public void teleportTestWhenPoisoned() {	

		//Initiate an attacker
		final String poisontype = "greater poison";
		final ConsumableItem poison = (ConsumableItem) SingletonRepository.getEntityManager().getItem(poisontype);
		final PoisonAttacker poisoner = new PoisonAttacker(100, poison);
		
		//Poison the player
		poisoner.onAttackAttempt(player, SingletonRepository.getEntityManager().getCreature("snake"));
		
		//Initiate coordinates and attributes for the marked scroll
		Map<String, String> coordinates = new HashMap<String, String>();
		coordinates.put("id", "1");
		coordinates.put("infostring", "0_semos_city, 20, 40");
		
		//Instantiate a marked scroll and add it to the initial zone
		MarkedScroll scroll = new MarkedScroll("marked scroll", "MarkedScroll","", coordinates);
		zone1.add(scroll);
		
		//Player not allowed to teleport
		assertFalse(scroll.useTeleportScroll(player));
	}
}

