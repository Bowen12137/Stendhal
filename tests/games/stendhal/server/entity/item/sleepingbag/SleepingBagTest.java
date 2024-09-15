package games.stendhal.server.entity.item.sleepingbag;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import utilities.RPClass.ItemTestHelper;

public class SleepingBagTest {
 
 @BeforeClass
 public static void setUpBeforeClass() throws Exception {
  MockStendlRPWorld.get();
  Log4J.init();
 }

 @AfterClass
 public static void tearDownAfterClass() throws Exception {
  MockStendlRPWorld.reset();
 }
 
 /*
  * Test if the item exits or not. 
  */
 @Test
 public void testcreateItem() throws Exception {
  Item sleepingbag = SingletonRepository.getEntityManager().getItem("sleepingbag");
  ItemTestHelper.createItem();
  final Item item = ItemTestHelper.createItem("sleepingbag");
  assertEquals(sleepingbag.getName(), item.getName());
 }
  public static class MockSleepingBag extends Item {
  public MockSleepingBag(final String name, final String clazz, final String subclass,
   final Map<String, String> attributes) {
    super("", clazz, subclass, attributes);
  }
 }
}