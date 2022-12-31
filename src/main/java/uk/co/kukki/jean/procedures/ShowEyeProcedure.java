package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.item.DragonsEyeItem;
import uk.co.kukki.jean.JeanMod;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.Map;

public class ShowEyeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				JeanMod.LOGGER.warn("Failed to load dependency world for procedure ShowEye!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				JeanMod.LOGGER.warn("Failed to load dependency entity for procedure ShowEye!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.isRemote()) {
			Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(DragonsEyeItem.block));
		}
		(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).shrink((int) 1);
	}
}
