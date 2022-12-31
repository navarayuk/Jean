
package uk.co.kukki.jean.item;

import uk.co.kukki.jean.itemgroup.JeanItemGroup;
import uk.co.kukki.jean.JeanModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@JeanModElements.ModElement.Tag
public class BoneArrowHeadItem extends JeanModElements.ModElement {
	@ObjectHolder("jean:bone_arrow_head")
	public static final Item block = null;

	public BoneArrowHeadItem(JeanModElements instance) {
		super(instance, 15);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(JeanItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("bone_arrow_head");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 0.1F;
		}
	}
}
