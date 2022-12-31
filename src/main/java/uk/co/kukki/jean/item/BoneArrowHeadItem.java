
package uk.co.kukki.jean.item;

import uk.co.kukki.jean.init.JeanModTabs;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class BoneArrowHeadItem extends Item {
	public BoneArrowHeadItem() {
		super(new Item.Properties().tab(JeanModTabs.TAB_JEAN).stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
		return 0.1F;
	}
}
