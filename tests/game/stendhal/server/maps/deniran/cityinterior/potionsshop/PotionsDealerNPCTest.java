package game.stendhal.server.maps.deniran.cityinterior.potionsshop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.sign.ShopSign;
import games.stendhal.server.maps.deniran.cityinterior.potionsshop.PotionsDealerNPC;
import utilities.QuestHelper;

public class PotionsDealerNPCTest{
 @BeforeClass
 public static void setUpBeforeClass() throws Exception{
  QuestHelper.setUpBeforeClass();
 }
 
 @Test
 public void test() {
  StendhalRPZone zone = new StendhalRPZone("Wanda");
  StendhalRPWorld world = StendhalRPWorld.get();
  world.addRPZone(zone);
  ZoneConfigurator conf = new PotionsDealerNPC();
  conf.configureZone(zone,null);
  
  ShopSign buyerEntity = (ShopSign) zone.getEntityAt(5, 6);
  ShopSign sellerEntity = (ShopSign) zone.getEntityAt(10, 6);
    
  assertFalse(buyerEntity == null);
  assertFalse(sellerEntity == null);
    
  String shopNameB = buyerEntity.getShopName();
  String shopNameS = sellerEntity.getShopName();

  assertTrue(shopNameB == "deniranequipbuy");
  assertTrue(shopNameS == "deniranequipsell");
 };
}