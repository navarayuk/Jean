package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.init.JeanModBlocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class WearingJeanProcedure {
	@SubscribeEvent
	public static void onEntitySetsAttackTarget(LivingSetAttackTargetEvent event) {
		execute(event, event.getEntityLiving().level, event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(),
				event.getTarget(), event.getEntityLiving());
	}

	public static InteractionResult execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		return execute(null, world, x, y, z, entity, sourceentity);
	}

	private static InteractionResult execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity,
			Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return InteractionResult.PASS;
		ItemStack DropMe = ItemStack.EMPTY;
		if (sourceentity instanceof Skeleton || sourceentity instanceof WitherSkeleton) {
			if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
					.getItem() == JeanModBlocks.DRAGON_SKULL.get().asItem()) {
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BOW) {
					DropMe = ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
					((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).shrink(1);
					(DropMe).enchant(Enchantments.LOYALTY, 1);
					(DropMe).setCount(1);
					if (world instanceof Level _level && !_level.isClientSide()) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, DropMe);
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
					DropMe = (new ItemStack(Items.ARROW).copy());
					(DropMe).setCount(Mth.nextInt(new Random(), 1, 10));
					if (world instanceof Level _level && !_level.isClientSide()) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, DropMe);
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
					if (entity instanceof Player _player) {
						_player.getAbilities().invulnerable = (true);
						_player.onUpdateAbilities();
					}
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private LevelAccessor world;

						public void start(LevelAccessor world, int waitTicks) {
							this.waitTicks = waitTicks;
							MinecraftForge.EVENT_BUS.register(this);
							this.world = world;
						}

						@SubscribeEvent
						public void tick(TickEvent.ServerTickEvent event) {
							if (event.phase == TickEvent.Phase.END) {
								this.ticks += 1;
								if (this.ticks >= this.waitTicks)
									run();
							}
						}

						private void run() {
							if (entity instanceof Player _player) {
								_player.getAbilities().invulnerable = (false);
								_player.onUpdateAbilities();
							}
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, 20);
				}
				if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof SwordItem) {
					DropMe = ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy());
					((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)).shrink(1);
					(DropMe).setCount(1);
					if (world instanceof Level _level && !_level.isClientSide()) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, DropMe);
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
					if (entity instanceof Player _player) {
						_player.getAbilities().invulnerable = (true);
						_player.onUpdateAbilities();
					}
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private LevelAccessor world;

						public void start(LevelAccessor world, int waitTicks) {
							this.waitTicks = waitTicks;
							MinecraftForge.EVENT_BUS.register(this);
							this.world = world;
						}

						@SubscribeEvent
						public void tick(TickEvent.ServerTickEvent event) {
							if (event.phase == TickEvent.Phase.END) {
								this.ticks += 1;
								if (this.ticks >= this.waitTicks)
									run();
							}
						}

						private void run() {
							if (entity instanceof Player _player) {
								_player.getAbilities().invulnerable = (false);
								_player.onUpdateAbilities();
							}
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, 20);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}
