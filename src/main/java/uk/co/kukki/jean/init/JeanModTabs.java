
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package uk.co.kukki.jean.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class JeanModTabs {
	public static CreativeModeTab TAB_JEAN;

	public static void load() {
		TAB_JEAN = new CreativeModeTab("tabjean") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(JeanModItems.DRAGONS_EYE.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
