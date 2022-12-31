
package uk.co.kukki.jean.item;

import uk.co.kukki.jean.procedures.ShowEyeProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

public class DragonsEyeItem extends Item {
	public DragonsEyeItem() {
		super(new Item.Properties().tab(null).stacksTo(64).rarity(Rarity.EPIC));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			ShowEyeProcedure.execute(world, entity);
	}
}
