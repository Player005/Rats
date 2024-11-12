package com.github.alexthe666.rats.client.render.entity;

import com.github.alexthe666.rats.RatsMod;
import com.github.alexthe666.rats.client.events.ForgeClientEvents;
import com.github.alexthe666.rats.client.events.ModClientEvents;
import com.github.alexthe666.rats.client.model.entity.AbstractRatModel;
import com.github.alexthe666.rats.client.model.entity.PinkieModel;
import com.github.alexthe666.rats.client.model.entity.RatModel;
import com.github.alexthe666.rats.client.render.entity.layer.TamedRatEyesLayer;
import com.github.alexthe666.rats.client.render.entity.layer.TamedRatOverlayLayer;
import com.github.alexthe666.rats.server.entity.rat.TamedRat;
import com.github.alexthe666.rats.server.items.upgrades.interfaces.ChangesTextureUpgrade;
import com.github.alexthe666.rats.server.misc.RatUpgradeUtils;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.joml.Matrix4f;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TamedRatRenderer extends AbstractRatRenderer<TamedRat, AbstractRatModel<TamedRat>> {

	private static final RatModel<TamedRat> RAT_MODEL = new RatModel<>();
	private static final PinkieModel<TamedRat> PINKIE_MODEL = new PinkieModel<>();
	private static final ResourceLocation PINKIE_TEXTURE = new ResourceLocation(RatsMod.MODID, "textures/entity/rat/baby.png");

	private static final ImmutableMap<String, ResourceLocation> SPECIAL_SKINS = ImmutableMap.<String, ResourceLocation>builder()
			.put("bugraak", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/bugraak.png"))
			.put("dino", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/dino.png"))
			.put("friar", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/friar.png"))
			.put("gizmo", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/gizmo.png"))
			.put("julian", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/julian.png"))
			.put("lil_cheese", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/lil_cheese.png"))
			.put("ratatla", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/ratatla.png"))
			.put("riddler", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/riddler.png"))
			.put("sharva", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/sharva.png"))
			.put("shizuka", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/shizuka.png"))
			.put("skrat", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/skrat.png"))
			.put("ultrakill", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/ultrakill.png"))
			.put("zura", new ResourceLocation(RatsMod.MODID, "textures/entity/rat/patreon_skins/zura.png"))
			.build();

	public TamedRatRenderer(EntityRendererProvider.Context context) {
		super(context, new RatModel<>());
		this.addLayer(new TamedRatOverlayLayer(this));
		this.addLayer(new TamedRatEyesLayer(this));
	}

	@Override
	protected boolean shouldShowName(TamedRat entity) {
		return ModClientEvents.shouldRenderNameplates() && super.shouldShowName(entity);
	}

	@Override
	public void render(TamedRat entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int light) {
		if (entity.isBaby()) {
			this.model = PINKIE_MODEL;
		} else {
			this.model = RAT_MODEL;
		}
		super.render(entity, entityYaw, partialTicks, stack, buffer, light);
//		if (ForgeClientEvents.isRatSelectedOnStaff(entity)) {
//			this.renderAdditionalInfo(entity, stack, buffer, light);
//		}
	}

	protected void renderAdditionalInfo(TamedRat entity, PoseStack stack, MultiBufferSource source, int light) {
		double d0 = this.entityRenderDispatcher.distanceToSqr(entity);
		if (ForgeHooksClient.isNameplateInRenderDistance(entity, d0)) {
			boolean flag = !entity.isDiscrete();
			float f = entity.getNameTagOffsetY();
			stack.pushPose();
			stack.translate(0.0F, f, 0.0F);
			stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
			stack.scale(-0.025F, -0.025F, 0.025F);
			Matrix4f matrix4f = stack.last().pose();
			float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
			int j = (int)(f1 * 255.0F) << 24;
			Font font = this.getFont();

			Component rf = Component.literal("RF: " + entity.getHeldRF());
			float f2 = (float)(-font.width(rf) / 2);
			font.drawInBatch(rf, f2, -20, 553648127, false, matrix4f, source, Font.DisplayMode.NORMAL, j, light);
			Component fluid = Component.literal("Fluid: " + entity.transportingFluid.getAmount());
			f2 = (float)(-font.width(rf) / 2);
			font.drawInBatch(fluid, f2, -10, 553648127, false, matrix4f, source, Font.DisplayMode.NORMAL, j, light);
			Component pickupSides = Component.literal("Deposit: " + entity.depositFacing.getName() + ", Pickup: " + entity.pickupFacing.getName());
			f2 = (float)(-font.width(rf) / 2);
			font.drawInBatch(pickupSides, f2, 0, 553648127, false, matrix4f, source, Font.DisplayMode.NORMAL, j, light);

			stack.popPose();
		}
	}

	@Override
	public ResourceLocation getTextureLocation(TamedRat entity) {
		if (entity.isBaby()) {
			return PINKIE_TEXTURE;
		} else {
			AtomicReference<String> upgradeTex = new AtomicReference<>(null);

			RatUpgradeUtils.forEachUpgrade(entity, item -> item instanceof ChangesTextureUpgrade, (stack, slot) -> {
				if (entity.isSlotVisible(slot)) {
					upgradeTex.set(((ChangesTextureUpgrade) stack.getItem()).getTexture().toString());
				}
			});

			if (upgradeTex.get() != null) {
				return new ResourceLocation(upgradeTex.get());
			}

			if (entity.hasCustomName()) {
				String name = entity.getCustomName().getString().toLowerCase(Locale.ROOT);
				if (SPECIAL_SKINS.containsKey(name)) {
					return Objects.requireNonNull(SPECIAL_SKINS.get(name));
				}
			}

			return super.getTextureLocation(entity);
		}
	}
}
