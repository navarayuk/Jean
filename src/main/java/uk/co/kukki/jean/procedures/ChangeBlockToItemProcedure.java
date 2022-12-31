package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.init.JeanModItems;
import uk.co.kukki.jean.init.JeanModBlocks;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@Mod.EventBusSubscriber
public class ChangeBlockToItemProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ItemStack blocks = ItemStack.EMPTY;
		double cccc = 0;
		cccc = 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					blocks = new ItemStack(JeanModItems.DRAGON_SKULL_ITEM.get());
					if (cccc < 100) {
						if (itemstackiterator.getItem() == JeanModBlocks.DRAGON_SKULL.get().asItem()
								&& EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, itemstackiterator) != 0) {
							if (entity instanceof Player _player) {
								ItemStack _stktoremove = itemstackiterator;
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) cccc,
										_player.inventoryMenu.getCraftSlots());
							}
							blocks = new ItemStack(JeanModItems.DRAGON_SKULL_ITEM.get());
							(blocks).enchant(Enchantments.MOB_LOOTING,
									EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, itemstackiterator));
							{
								final int _slotid = (int) cccc;
								final ItemStack _setstack = blocks;
								_setstack.setCount(1);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable _modHandler)
										_modHandler.setStackInSlot(_slotid, _setstack);
								});
							}
						}
						if (itemstackiterator.getItem() == JeanModItems.DRAGON_SKULL_ITEM.get()
								&& !(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, itemstackiterator) != 0)) {
							blocks = new ItemStack(JeanModBlocks.DRAGON_SKULL.get());
							{
								final int _slotid = (int) cccc;
								final ItemStack _setstack = blocks;
								_setstack.setCount(1);
								entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable _modHandler)
										_modHandler.setStackInSlot(_slotid, _setstack);
								});
							}
						}
					}
					cccc = cccc + 1;
				}
			}
		}
	}
}
