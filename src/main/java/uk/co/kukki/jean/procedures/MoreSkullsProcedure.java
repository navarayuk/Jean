package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.item.DragonSkullItemItem;
import uk.co.kukki.jean.block.DragonSkullBlock;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class MoreSkullsProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityDeath(LivingDeathEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				JeanMod.LOGGER.warn("Failed to load dependency world for procedure MoreSkulls!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				JeanMod.LOGGER.warn("Failed to load dependency x for procedure MoreSkulls!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				JeanMod.LOGGER.warn("Failed to load dependency y for procedure MoreSkulls!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				JeanMod.LOGGER.warn("Failed to load dependency z for procedure MoreSkulls!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				JeanMod.LOGGER.warn("Failed to load dependency entity for procedure MoreSkulls!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				JeanMod.LOGGER.warn("Failed to load dependency sourceentity for procedure MoreSkulls!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		ItemStack gift = ItemStack.EMPTY;
		if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
				.getItem() == DragonSkullBlock.block.asItem()) {
			if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == DragonSkullItemItem.block) {
				if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING,
						((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0)) {
					if (Math.random() < 0.31) {
						if (entity instanceof WitherSkeletonEntity) {
							gift = (new ItemStack(Items.WITHER_SKELETON_SKULL).copy());
							((gift)).setDisplayName(new StringTextComponent("Gift From Jean, Wither skull"));
							if (world instanceof World && !world.isRemote()) {
								ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (gift));
								entityToSpawn.setPickupDelay((int) 10);
								world.addEntity(entityToSpawn);
							}
						}
						if (entity instanceof SkeletonEntity) {
							gift = (new ItemStack(Items.SKELETON_SKULL).copy());
							((gift)).setDisplayName(new StringTextComponent("Gift From Jean, Skeleton skull"));
							if (world instanceof World && !world.isRemote()) {
								ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (gift));
								entityToSpawn.setPickupDelay((int) 10);
								world.addEntity(entityToSpawn);
							}
						}
						if (entity instanceof CreeperEntity) {
							gift = (new ItemStack(Items.CREEPER_HEAD).copy());
							((gift)).setDisplayName(new StringTextComponent("Gift From Jean, Creeper skull"));
							if (world instanceof World && !world.isRemote()) {
								ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (gift));
								entityToSpawn.setPickupDelay((int) 10);
								world.addEntity(entityToSpawn);
							}
						}
						if (entity instanceof ZombieEntity) {
							gift = (new ItemStack(Items.ZOMBIE_HEAD).copy());
							((gift)).setDisplayName(new StringTextComponent("Gift From Jean, Zombie skull"));
							if (world instanceof World && !world.isRemote()) {
								ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (gift));
								entityToSpawn.setPickupDelay((int) 10);
								world.addEntity(entityToSpawn);
							}
						}
					}
					if (entity instanceof DonkeyEntity) {
						if (entity instanceof ServerPlayerEntity) {
							Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemaningCriteria().iterator();
								while (_iterator.hasNext()) {
									String _criterion = (String) _iterator.next();
									((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
								}
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(
									((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.LOOTING)) {
								_enchantments.remove(Enchantments.LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemMainhand()
												: ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity) {
							((LivingEntity) sourceentity).attackEntityFrom(new DamageSource("jean.lovekiller").setDamageBypassesArmor(),
									(float) 9000);
						}
					}
					if (entity instanceof MuleEntity) {
						if (entity instanceof ServerPlayerEntity) {
							Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemaningCriteria().iterator();
								while (_iterator.hasNext()) {
									String _criterion = (String) _iterator.next();
									((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
								}
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(
									((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.LOOTING)) {
								_enchantments.remove(Enchantments.LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemMainhand()
												: ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity) {
							((LivingEntity) sourceentity).attackEntityFrom(new DamageSource("jean.lovekiller").setDamageBypassesArmor(),
									(float) 9000);
						}
					}
				}
			}
		} else {
			if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == DragonSkullItemItem.block) {
				if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING,
						((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0)) {
					if (entity instanceof DonkeyEntity) {
						if (sourceentity instanceof ServerPlayerEntity) {
							Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) sourceentity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = ((ServerPlayerEntity) sourceentity).getAdvancements().getProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemaningCriteria().iterator();
								while (_iterator.hasNext()) {
									String _criterion = (String) _iterator.next();
									((ServerPlayerEntity) sourceentity).getAdvancements().grantCriterion(_adv, _criterion);
								}
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(
									((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.LOOTING)) {
								_enchantments.remove(Enchantments.LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemMainhand()
												: ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity) {
							((LivingEntity) sourceentity).attackEntityFrom(new DamageSource("jean.lovekiller").setDamageBypassesArmor(),
									(float) 9000);
						}
					}
					if (entity instanceof MuleEntity) {
						if (sourceentity instanceof ServerPlayerEntity) {
							Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) sourceentity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("jean:jean_cry"));
							AdvancementProgress _ap = ((ServerPlayerEntity) sourceentity).getAdvancements().getProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemaningCriteria().iterator();
								while (_iterator.hasNext()) {
									String _criterion = (String) _iterator.next();
									((ServerPlayerEntity) sourceentity).getAdvancements().grantCriterion(_adv, _criterion);
								}
							}
						}
						{
							Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(
									((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY));
							if (_enchantments.containsKey(Enchantments.LOOTING)) {
								_enchantments.remove(Enchantments.LOOTING);
								EnchantmentHelper.setEnchantments(_enchantments,
										((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemMainhand()
												: ItemStack.EMPTY));
							}
						}
						if (sourceentity instanceof LivingEntity) {
							((LivingEntity) sourceentity).attackEntityFrom(new DamageSource("jean.lovekiller").setDamageBypassesArmor(),
									(float) 9000);
						}
					}
				}
			}
		}
	}
}
