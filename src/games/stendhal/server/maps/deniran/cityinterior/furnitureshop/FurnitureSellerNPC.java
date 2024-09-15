package games.stendhal.server.maps.deniran.cityinterior.furnitureshop;

import java.util.LinkedHashMap;
import java.util.Map;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.sign.ShopSign;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;

public class FurnitureSellerNPC implements ZoneConfigurator {

    @Override
    public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
        // TODO Auto-generated method stub
        buildNPC(zone);
        buildSigns(zone);
    }
    
    private void buildNPC(final StendhalRPZone zone) {
    	// find better name
        final SpeakerNPC npc = new SpeakerNPC("Steve Jobs") {
        	
        	// change basic text responses
            @Override
            public void createDialog() {
                addGreeting("Hello! Wecome to the furniture shop. NOTE: THIS NPC IS STILL IN DEVELOPMENT!");
                addJob("I am the local furniture dealer");
                addOffer("Check out the blackboards for my prices. NOTE: MORE ITEMS ARE EXPECTED SOON!");
                addGoodbye("Come back again, customer!");
            }
        };
        
        // update list when more items are added
        // handle prices with some sort of global economy
        final Map<String, Integer> pricesSell = new LinkedHashMap<String, Integer>() {{
            put("stool", 30);
            put("torch", 5);
            put("chess", 100);
            put("basin", 200);
        }};
        
        // this makes the npc act as a seller
        new SellerAdder().addSeller(npc, new SellerBehaviour(pricesSell), false);
        final ShopList shops = ShopList.get();
        for (final String key: pricesSell.keySet()) {
            shops.add("deniranfurnituresell", key, pricesSell.get(key));
        }
        
        // TODO add buyer behaviour
        
        // parameters for npc
        npc.setPosition(9,11);
        // reuising from weapon dealer 
        // TODO create sprites for npc later
        npc.setEntityClass("wellroundedguynpc");
        npc.setDescription("You see the furniture seller.");
        
        
        zone.add(npc);
    }
    
    // this is for the sign where are the prices
    private void buildSigns(final StendhalRPZone zone) {
        final ShopSign buys = new ShopSign("deniranfurnituresell", "Furniture Shop (selling)", "WARNING: WORK IN PROGRESS> UNEXPECTED BEHAVIOUR MAY APPEAR.", false);
        buys.setEntityClass("blackboard");
        buys.setPosition(6, 9);
        zone.add(buys);
    }

}