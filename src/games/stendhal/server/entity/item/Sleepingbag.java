package games.stendhal.server.entity.item;

import java.util.Map;

public class Sleepingbag  extends ConsumableItem {
 public Sleepingbag(final String name, final String clazz, final String subclass,
   final Map<String, String> attributes) {
  super(name, clazz, subclass, attributes);
 }

 /**
  * copy constructor.
  *
  * @param item
  *            item to copy
  */
 public Sleepingbag(final Sleepingbag item) {
  super(item);
 }
 
 
}