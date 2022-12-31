
package uk.co.kukki.jean.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class DragonSkullItemItem extends Item {
	public DragonSkullItemItem() {
		super(new Item.Properties().tab(null).stacksTo(64).rarity(Rarity.COMMON));
	}
}
