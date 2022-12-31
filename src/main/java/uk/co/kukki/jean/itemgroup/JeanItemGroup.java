
package uk.co.kukki.jean.itemgroup;

import uk.co.kukki.jean.item.DragonsEyeItem;
import uk.co.kukki.jean.JeanModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@JeanModElements.ModElement.Tag
public class JeanItemGroup extends JeanModElements.ModElement {
	public JeanItemGroup(JeanModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabjean") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(DragonsEyeItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
