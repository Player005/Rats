package com.github.alexthe666.rats.server.items.upgrades;

import com.github.alexthe666.rats.client.model.entity.RatModel;
import com.github.alexthe666.rats.registry.RatsSoundRegistry;
import com.github.alexthe666.rats.server.block.entity.RatCraftingTableBlockEntity;
import com.github.alexthe666.rats.server.entity.rat.TamedRat;
import com.github.alexthe666.rats.server.items.upgrades.interfaces.HoldsItemUpgrade;
import com.github.alexthe666.rats.server.items.upgrades.interfaces.TickRatUpgrade;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CraftingRatUpgradeItem extends BaseRatUpgradeItem implements HoldsItemUpgrade, TickRatUpgrade {

	public CraftingRatUpgradeItem(Properties properties) {
		super(properties, 0, 2);
	}

	@Override
	public boolean playIdleAnimation(TamedRat rat) {
		return false;
	}

	@Override
	public void renderHeldItem(EntityRendererProvider.Context context, TamedRat rat, RatModel<?> model, PoseStack stack, MultiBufferSource buffer, int light, float ageInTicks) {
		stack.pushPose();
		this.translateToHand(model, true, stack);
		stack.mulPose(Axis.ZP.rotationDegrees(180F));
		stack.translate(0.0F, -0.075F, -0.1F);
		stack.scale(0.65F, 0.65F, 0.65F);
		context.getItemRenderer().renderStatic(new ItemStack(Items.STONE_AXE), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, light, OverlayTexture.NO_OVERLAY, stack, buffer, null, rat.getId());
		stack.popPose();
		stack.pushPose();
		this.translateToHand(model, false, stack);
		stack.mulPose(Axis.ZP.rotationDegrees(180F));
		stack.translate(0.0F, -0.075F, -0.1F);
		stack.scale(0.65F, 0.65F, 0.65F);
		context.getItemRenderer().renderStatic(new ItemStack(Items.STONE_PICKAXE), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, light, OverlayTexture.NO_OVERLAY, stack, buffer, null, rat.getId());
		stack.popPose();
	}

	@Override
	public void tick(TamedRat rat) {
		if (rat.getRespawnCountdown() <= 0 && rat.isTame()) {
			BlockEntity te = rat.level().getBlockEntity(rat.blockPosition().below());
			if (te instanceof RatCraftingTableBlockEntity table && !rat.level().isClientSide()) {
				double d2 = rat.getRandom().nextGaussian() * 0.02D;
				double d0 = rat.getRandom().nextGaussian() * 0.02D;
				double d1 = rat.getRandom().nextGaussian() * 0.02D;
				if (table.getCookTime() > 0) {
					rat.crafting = true;
					rat.level().broadcastEntityEvent(rat, (byte) 85);
					if (table.getRecipeUsed() != null) {
						ItemStack stack = table.getRecipeUsed().getResultItem(rat.level().registryAccess());
						if (stack.isEmpty()) {
							((ServerLevel) rat.level()).sendParticles(ParticleTypes.SMOKE, rat.getX() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), rat.getY() + (double) (rat.getRandom().nextFloat() * rat.getBbHeight()), rat.getZ() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), 1, d0, d1, d2, 0);
						} else {
							((ServerLevel) rat.level()).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), rat.getX() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), rat.getY(), rat.getZ() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), 1, d0, d1, d2, 0);
							((ServerLevel) rat.level()).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), rat.getX() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), rat.getY(), rat.getZ() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), 1, d0, d1, d2, 0);
						}
					}
					if (table.prevCookTime % 20 == 0) {
						rat.playSound(RatsSoundRegistry.RAT_CRAFT.get(), 0.6F, 0.75F + rat.getRandom().nextFloat());
					}
				} else {
					rat.crafting = false;
					rat.level().broadcastEntityEvent(rat, (byte) 86);
				}
				if (table.prevCookTime == 199) {
					for (int i = 0; i < 4; i++) {
						((ServerLevel) rat.level()).sendParticles(ParticleTypes.HAPPY_VILLAGER, rat.getX() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), rat.getY() + (double) (rat.getRandom().nextFloat() * rat.getBbHeight()), rat.getZ() + (double) (rat.getRandom().nextFloat() * rat.getBbWidth() * 2.0F) - (double) rat.getBbWidth(), 1, d0, d1, d2, 0);
					}
					rat.playSound(SoundEvents.ITEM_PICKUP, 1, 1);
				}
			}
		}
	}
}
