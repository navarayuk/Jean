package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.init.JeanModItems;
import uk.co.kukki.jean.init.JeanModBlocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import javax.annotation.Nullable;

import java.util.Iterator;

@Mod.EventBusSubscriber
public class RightClickHeadProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getPlayer().getUsedItemHand())
			return;
		execute(event, event.getPlayer());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		ItemStack putmeon = ItemStack.EMPTY;
		ItemStack jean = ItemStack.EMPTY;
		putmeon = ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
		if (putmeon.getItem() == JeanModBlocks.DRAGON_SKULL.get().asItem()) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
					.getItem() == Blocks.AIR.asItem()) {
				if (!(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, putmeon) != 0)
						&& (!(entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
								? _plr.getAdvancements()
										.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry")))
										.isDone()
								: false)
								|| (entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
										? _plr.getAdvancements()
												.getOrStartProgress(
														_plr.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_forgives_you")))
												.isDone()
										: false))) {
					((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).shrink(1);
					(putmeon).setCount(1);
					{
						Entity _entity = entity;
						if (_entity instanceof Player _player) {
							_player.getInventory().armor.set(3, new ItemStack(JeanModBlocks.DRAGON_SKULL.get()));
							_player.getInventory().setChanged();
						} else if (_entity instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.HEAD, new ItemStack(JeanModBlocks.DRAGON_SKULL.get()));
						}
					}
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:wearing"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemainingCriteria().iterator();
							while (_iterator.hasNext())
								_player.getAdvancements().award(_adv, (String) _iterator.next());
						}
					}
				}
			}
		} else if (putmeon.getItem() == JeanModItems.DRAGON_SKULL_ITEM.get()) {
			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, putmeon) != 0) {
				if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.ARROW)) : false) {
					if (!(entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
							? _plr.getAdvancements()
									.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_cry"))).isDone()
							: false)
							|| (entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
									? _plr.getAdvancements()
											.getOrStartProgress(
													_plr.server.getAdvancements().getAdvancement(new ResourceLocation("jean:jean_forgives_you")))
											.isDone()
									: false)) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(Items.ARROW);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
									_player.inventoryMenu.getCraftSlots());
						}
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
						{
							Entity _shootFrom = entity;
							Level projectileLevel = _shootFrom.level;
							if (!projectileLevel.isClientSide()) {
								Projectile _entityToSpawn = new Object() {
									public Projectile getArrow(Level level, Entity shooter, float damage, int knockback, byte piercing) {
										AbstractArrow entityToSpawn = new Arrow(EntityType.ARROW, level);
										entityToSpawn.setOwner(shooter);
										entityToSpawn.setBaseDamage(damage);
										entityToSpawn.setKnockback(knockback);
										entityToSpawn.setPierceLevel(piercing);
										entityToSpawn.setCritArrow(true);
										entityToSpawn.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
										return entityToSpawn;
									}
								}.getArrow(projectileLevel, entity, (float) 6.7, 2, (byte) 3);
								_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
								_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z,
										(float) 2.7, 0);
								projectileLevel.addFreshEntity(_entityToSpawn);
							}
						}
						if (entity instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("jean:taking_aim_jean"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						if (entity instanceof Player _player)
							_player.getCooldowns().addCooldown(
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 40);
					} else {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = new ItemStack(Items.ARROW);
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
									_player.inventoryMenu.getCraftSlots());
						}
						entity.setSecondsOnFire(15);
						if (entity instanceof LivingEntity _entity)
							_entity.hurt(new DamageSource("jean.abused").bypassArmor(), 13);
						if (entity instanceof Player _player)
							_player.getCooldowns().addCooldown(
									(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem(), 40);
					}
				}
			}
		}
	}
}
