package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.item.DragonSkullItemItem;
import uk.co.kukki.jean.block.DragonSkullBlock;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.HashMap;

public class ChangeBlockToItemProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				JeanMod.LOGGER.warn("Failed to load dependency world for procedure ChangeBlockToItem!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				JeanMod.LOGGER.warn("Failed to load dependency entity for procedure ChangeBlockToItem!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack blocks = ItemStack.EMPTY;
		double cccc = 0;
		cccc = 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					blocks = new ItemStack(DragonSkullItemItem.block);
					if (cccc < 100) {
						if (itemstackiterator.getItem() == DragonSkullBlock.block.asItem()
								&& (EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstackiterator) != 0)) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = itemstackiterator;
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) cccc,
										((PlayerEntity) entity).container.func_234641_j_());
							}
							blocks = new ItemStack(DragonSkullItemItem.block);
							((blocks)).addEnchantment(Enchantments.LOOTING,
									(int) (EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstackiterator)));
							{
								final ItemStack _setstack = (blocks);
								final int _sltid = (int) (cccc);
								_setstack.setCount((int) 1);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						if (itemstackiterator.getItem() == DragonSkullItemItem.block
								&& !(EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstackiterator) != 0)) {
							blocks = new ItemStack(DragonSkullBlock.block);
							{
								final ItemStack _setstack = (blocks);
								final int _sltid = (int) (cccc);
								_setstack.setCount((int) 1);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					cccc = (cccc + 1);
				}
			}
		}
	}
}
