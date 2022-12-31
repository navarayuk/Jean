
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package uk.co.kukki.jean.init;

import uk.co.kukki.jean.item.JeanDragonsCryingEyeItem;
import uk.co.kukki.jean.item.ForgivenCarrotItem;
import uk.co.kukki.jean.item.DragonsEyeItem;
import uk.co.kukki.jean.item.DragonSkullItemItem;
import uk.co.kukki.jean.item.BoneArrowHeadItem;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class JeanModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JeanMod.MODID);
	public static final RegistryObject<Item> DRAGON_SKULL = block(JeanModBlocks.DRAGON_SKULL, JeanModTabs.TAB_JEAN);
	public static final RegistryObject<Item> WALL_DRAGON_SKULL = block(JeanModBlocks.WALL_DRAGON_SKULL, null);
	public static final RegistryObject<Item> BONE_ARROW_HEAD = REGISTRY.register("bone_arrow_head", () -> new BoneArrowHeadItem());
	public static final RegistryObject<Item> DRAGON_SKULL_ITEM = REGISTRY.register("dragon_skull_item", () -> new DragonSkullItemItem());
	public static final RegistryObject<Item> DRAGONS_EYE = REGISTRY.register("dragons_eye", () -> new DragonsEyeItem());
	public static final RegistryObject<Item> JEAN_DRAGONS_CRYING_EYE = REGISTRY.register("jean_dragons_crying_eye",
			() -> new JeanDragonsCryingEyeItem());
	public static final RegistryObject<Item> FORGIVEN_CARROT = REGISTRY.register("forgiven_carrot", () -> new ForgivenCarrotItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
