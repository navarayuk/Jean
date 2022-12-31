
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package uk.co.kukki.jean.init;

import uk.co.kukki.jean.block.WallDragonSkullBlock;
import uk.co.kukki.jean.block.DragonSkullBlock;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

public class JeanModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, JeanMod.MODID);
	public static final RegistryObject<Block> DRAGON_SKULL = REGISTRY.register("dragon_skull", () -> new DragonSkullBlock());
	public static final RegistryObject<Block> WALL_DRAGON_SKULL = REGISTRY.register("wall_dragon_skull", () -> new WallDragonSkullBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			DragonSkullBlock.registerRenderLayer();
			WallDragonSkullBlock.registerRenderLayer();
		}
	}
}
