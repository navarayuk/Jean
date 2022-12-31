package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.init.JeanModItems;
import uk.co.kukki.jean.init.JeanModBlocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

import java.util.Map;
import java.util.Iterator;

@Mod.EventBusSubscriber
public class MoreSkullsProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(),
					event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		ItemStack gift = ItemStack.EMPTY;
		if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
				.getItem() == JeanModBlocks.DRAGON_SKULL.get().asItem()) {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == JeanModItems.DRAGON_SKULL_ITEM.get()) {
				if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING,
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
					if (Math.random() < 0.21) {
						if (entity instanceof WitherSkeleton) {
							gift = (new ItemStack(Items.WITHER_SKELETON_SKULL).copy());
							(gift).setHoverName(new TextComponent("Gift From Jean, Wither skull"));
							if (world instanceof Level _level && !_level.isClientSide()) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, gift);
								entityToSpawn.setPickUpDelay(10);
								_level.addFreshEntity(entityToSpawn);
							}
						}
						if (entity instanceof Skeleton) {
							gift = (new ItemStack(Items.SKELETON_SKULL).copy());
							(gift).setHoverName(new TextComponent("Gift From Jean, Skeleton skull"));
							if (world instanceof Level _level && !_level.isClientSide()) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, gift);
								entityToSpawn.setPickUpDelay(10);
								_level.addFreshEntity(entityToSpawn);
							}
						}
						if (entity instanceof Creeper) {
							gift = (new ItemStack(Items.CREEPER_HEAD).copy());
							(gift).setHoverName(new TextComponent("Gift From Jean, Creeper skull"));
							if (world instanceof Level _level && !_level.isClientSide()) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, gift);
								entityToSpawn.setPickUpDelay(10);
								_level.addFreshEntity(entityToSpawn);
							}
						}
						if (entity instanceof Zombie) {
							gift = (new ItemStack(Blocks.ZOMBIE_HEAD).copy());
							(gift).setHoverName(new TextComponent("Gift From Jean, Zombie skull"));
							if (world instanceof Level _level && !_level.isClientSide()) {
								ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, gift);
								entityToSpawn.setPickUpDelay(10);
								_level.addFreshEntity(entityToSpawn);
							}
						}
					}
					if (entity instanceof Donkey) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper
									.getEnchantments((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.MOB_LOOTING)) {
								_enchantments.remove(Enchantments.MOB_LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity _entity)
							_entity.hurt(new DamageSource("jean.lovekiller").bypassArmor(), 9000);
					}
					if (entity instanceof Mule) {
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper
									.getEnchantments((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.MOB_LOOTING)) {
								_enchantments.remove(Enchantments.MOB_LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity _entity)
							_entity.hurt(new DamageSource("jean.lovekiller").bypassArmor(), 9000);
					}
				}
			}
		} else {
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == JeanModItems.DRAGON_SKULL_ITEM.get()) {
				if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING,
						(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0) {
					if (entity instanceof Donkey) {
						if (sourceentity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper
									.getEnchantments((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.MOB_LOOTING)) {
								_enchantments.remove(Enchantments.MOB_LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity _entity)
							_entity.hurt(new DamageSource("jean.lovekiller").bypassArmor(), 9000);
					}
					if (entity instanceof Mule) {
						if (sourceentity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper
									.getEnchantments((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.MOB_LOOTING)) {
								_enchantments.remove(Enchantments.MOB_LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										(sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity _entity)
							_entity.hurt(new DamageSource("jean.lovekiller").bypassArmor(), 9000);
					}
				}
			}
		}
	}
}
