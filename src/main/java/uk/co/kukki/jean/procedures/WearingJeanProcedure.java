package uk.co.kukki.jean.procedures;

import uk.co.kukki.jean.block.DragonSkullBlock;
import uk.co.kukki.jean.JeanMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class WearingJeanProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntitySetsAttackTarget(LivingSetAttackTargetEvent event) {
			LivingEntity entity = event.getTarget();
			LivingEntity sourceentity = event.getEntityLiving();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", sourceentity.getPosX());
			dependencies.put("y", sourceentity.getPosY());
			dependencies.put("z", sourceentity.getPosZ());
			dependencies.put("world", sourceentity.getEntityWorld());
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static ActionResultType executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				JeanMod.LOGGER.warn("Failed to load dependency world for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				JeanMod.LOGGER.warn("Failed to load dependency x for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				JeanMod.LOGGER.warn("Failed to load dependency y for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				JeanMod.LOGGER.warn("Failed to load dependency z for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				JeanMod.LOGGER.warn("Failed to load dependency entity for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				JeanMod.LOGGER.warn("Failed to load dependency sourceentity for procedure WearingJean!");
			return ActionResultType.PASS;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		ItemStack DropMe = ItemStack.EMPTY;
		if (sourceentity instanceof SkeletonEntity || sourceentity instanceof WitherSkeletonEntity) {
			if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
					.getItem() == DragonSkullBlock.block.asItem()) {
				if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == Items.BOW) {
					DropMe = (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.copy());
					(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY))
							.shrink((int) 1);
					((DropMe)).addEnchantment(Enchantments.LOYALTY, (int) 1);
					((DropMe)).setCount((int) 1);
					if (world instanceof World && !world.isRemote()) {
						ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (DropMe));
						entityToSpawn.setPickupDelay((int) 10);
						world.addEntity(entityToSpawn);
					}
					DropMe = (new ItemStack(Items.ARROW).copy());
					((DropMe)).setCount((int) (MathHelper.nextInt(new Random(), 1, 10)));
					if (world instanceof World && !world.isRemote()) {
						ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (DropMe));
						entityToSpawn.setPickupDelay((int) 10);
						world.addEntity(entityToSpawn);
					}
					if (entity instanceof PlayerEntity) {
						((PlayerEntity) entity).abilities.disableDamage = (true);
						((PlayerEntity) entity).sendPlayerAbilities();
					}
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private IWorld world;

						public void start(IWorld world, int waitTicks) {
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
							if (entity instanceof PlayerEntity) {
								((PlayerEntity) entity).abilities.disableDamage = (false);
								((PlayerEntity) entity).sendPlayerAbilities();
							}
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 20);
				}
				if (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() instanceof SwordItem) {
					DropMe = (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.copy());
					(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY))
							.shrink((int) 1);
					((DropMe)).setCount((int) 1);
					if (world instanceof World && !world.isRemote()) {
						ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (DropMe));
						entityToSpawn.setPickupDelay((int) 10);
						world.addEntity(entityToSpawn);
					}
					if (entity instanceof PlayerEntity) {
						((PlayerEntity) entity).abilities.disableDamage = (true);
						((PlayerEntity) entity).sendPlayerAbilities();
					}
					new Object() {
						private int ticks = 0;
						private float waitTicks;
						private IWorld world;

						public void start(IWorld world, int waitTicks) {
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
							if (entity instanceof PlayerEntity) {
								((PlayerEntity) entity).abilities.disableDamage = (false);
								((PlayerEntity) entity).sendPlayerAbilities();
							}
							MinecraftForge.EVENT_BUS.unregister(this);
						}
					}.start(world, (int) 20);
				}
			}
		}
		return ActionResultType.SUCCESS;
	}
}
