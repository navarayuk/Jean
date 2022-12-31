package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.item.DragonSkullItemItem;
import uk.co.kukki.jean.block.DragonSkullBlock;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

public class RightClickHeadProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
			PlayerEntity entity = event.getPlayer();
			if (event.getHand() != entity.getActiveHand()) {
				return;
			}
			double i = event.getPos().getX();
			double j = event.getPos().getY();
			double k = event.getPos().getZ();
			IWorld world = event.getWorld();
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

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				JeanMod.LOGGER.warn("Failed to load dependency entity for procedure RightClickHead!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack putmeon = ItemStack.EMPTY;
		ItemStack jean = ItemStack.EMPTY;
		putmeon = (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).copy());
		if ((putmeon).getItem() == DragonSkullBlock.block.asItem()) {
			if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
					.getItem() == Blocks.AIR.asItem()) {
				if (!(EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, (putmeon)) != 0)
						&& (!(((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
								? ((ServerPlayerEntity) entity).getAdvancements()
										.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
												.getAdvancement(new ResourceLocation("jean:jean_cry")))
										.isDone()
								: false)
								|| (((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
										? ((ServerPlayerEntity) entity).getAdvancements()
												.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
														.getAdvancement(new ResourceLocation("jean:jean_forgives_you")))
												.isDone()
										: false))) {
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).shrink((int) 1);
					((putmeon)).setCount((int) 1);
					if (entity instanceof LivingEntity) {
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).inventory.armorInventory.set((int) 3, new ItemStack(DragonSkullBlock.block));
						else
							((LivingEntity) entity).setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(DragonSkullBlock.block));
						if (entity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) entity).inventory.markDirty();
					}
					if (entity instanceof ServerPlayerEntity) {
						Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
								.getAdvancement(new ResourceLocation("jean:wearing"));
						AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemaningCriteria().iterator();
							while (_iterator.hasNext()) {
								String _criterion = (String) _iterator.next();
								((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
							}
						}
					}
				}
			}
		} else if ((putmeon).getItem() == DragonSkullItemItem.block) {
			if ((EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, (putmeon)) != 0)) {
				if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.ARROW)) : false) {
					if (!(((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
							? ((ServerPlayerEntity) entity).getAdvancements()
									.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
											.getAdvancement(new ResourceLocation("jean:jean_cry")))
									.isDone()
							: false)
							|| (((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
									? ((ServerPlayerEntity) entity).getAdvancements()
											.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
													.getAdvancement(new ResourceLocation("jean:jean_forgives_you")))
											.isDone()
									: false)) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(Items.ARROW);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
						{
							Entity _shootFrom = entity;
							World projectileLevel = _shootFrom.world;
							if (!projectileLevel.isRemote()) {
								ProjectileEntity _entityToSpawn = new Object() {
									public ProjectileEntity getArrow(World world, Entity shooter, float damage, int knockback, byte piercing) {
										AbstractArrowEntity entityToSpawn = new ArrowEntity(EntityType.ARROW, world);
										entityToSpawn.setShooter(shooter);
										entityToSpawn.setDamage(damage);
										entityToSpawn.setKnockbackStrength(knockback);

										entityToSpawn.setPierceLevel(piercing);

										entityToSpawn.setIsCritical(true);
										entityToSpawn.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
										return entityToSpawn;
									}
								}.getArrow(projectileLevel, entity, (float) 6.7, 2, (byte) 3);
								_entityToSpawn.setPosition(_shootFrom.getPosX(), _shootFrom.getPosYEye() - 0.1, _shootFrom.getPosZ());
								_entityToSpawn.shoot(_shootFrom.getLookVec().x, _shootFrom.getLookVec().y, _shootFrom.getLookVec().z, (float) 2.7, 0);
								projectileLevel.addEntity(_entityToSpawn);
							}
						}
						if (entity instanceof ServerPlayerEntity) {
							Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
									.getAdvancement(new ResourceLocation("jean:taking_aim_jean"));
							AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemaningCriteria().iterator();
								while (_iterator.hasNext()) {
									String _criterion = (String) _iterator.next();
									((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
								}
							}
						}
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).getCooldownTracker().setCooldown(
									((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(),
									(int) 40);
					} else {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(Items.ARROW);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
						entity.setFire((int) 15);
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).attackEntityFrom(new DamageSource("jean.abused").setDamageBypassesArmor(), (float) 13);
						}
						if (entity instanceof PlayerEntity)
							((PlayerEntity) entity).getCooldownTracker().setCooldown(
									((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem(),
									(int) 40);
					}
				}
			}
		}
	}
}
