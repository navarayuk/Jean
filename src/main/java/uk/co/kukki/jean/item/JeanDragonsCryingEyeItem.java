
package uk.co.kukki.jean.item;

import uk.co.kukki.jean.JeanModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@JeanModElements.ModElement.Tag
public class JeanDragonsCryingEyeItem extends JeanModElements.ModElement {
	@ObjectHolder("jean:jean_dragons_crying_eye")
	public static final Item block = null;

	public JeanDragonsCryingEyeItem(JeanModElements instance) {
		super(instance, 23);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("jean_dragons_crying_eye");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
