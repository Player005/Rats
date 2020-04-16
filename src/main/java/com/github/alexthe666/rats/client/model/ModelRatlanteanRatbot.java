package com.github.alexthe666.rats.client.model;

import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.github.alexthe666.rats.server.entity.EntityRatlanteanRatbot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRatlanteanRatbot<T extends EntityRatlanteanRatbot> extends AdvancedEntityModel<T>{
    public AdvancedModelBox body1;
    public AdvancedModelBox body2;
    public AdvancedModelBox tail1;
    public AdvancedModelBox leftThigh;
    public AdvancedModelBox rightThigh;
    public AdvancedModelBox neck;
    public AdvancedModelBox leftArm;
    public AdvancedModelBox rightArm;
    public AdvancedModelBox head;
    public AdvancedModelBox snout;
    public AdvancedModelBox leftEar;
    public AdvancedModelBox rightEar;
    public AdvancedModelBox leftEye;
    public AdvancedModelBox rightEye;
    public AdvancedModelBox nose;
    public AdvancedModelBox wisker1;
    public AdvancedModelBox wisker2;
    public AdvancedModelBox leftHand;
    public AdvancedModelBox rightHand;
    public AdvancedModelBox tail2;
    public AdvancedModelBox leftFoot;
    public AdvancedModelBox rightFoot;
    private AdvancedModelBox gear;
    private ModelAnimator animator;

    public ModelRatlanteanRatbot(float scale) {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.neck = new AdvancedModelBox(this, 32, 17);
        this.neck.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.neck.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, scale);
        this.nose = new AdvancedModelBox(this, 32, 26);
        this.nose.setRotationPoint(0.0F, -0.6F, -2.1F);
        this.nose.addBox(-0.5F, -0.3F, -1.0F, 1, 1, 2, 0.0F);
        this.rightEye = new AdvancedModelBox(this, 37, 20);
        this.rightEye.setRotationPoint(-2.0F, -0.5F, -1.6F);
        this.rightEye.addBox(-0.5F, -0.7F, -0.5F, 1, 1, 1, scale);
        this.leftFoot = new AdvancedModelBox(this, 22, 15);
        this.leftFoot.mirror = true;
        this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.5F);
        this.leftFoot.addBox(-0.5F, 0.0F, -3.5F, 1, 1, 4, scale);
        this.wisker2 = new AdvancedModelBox(this, 35, 0);
        this.wisker2.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.wisker2.addBox(-2.5F, -1.5F, 0.0F, 2, 3, 0, scale);
        this.wisker1 = new AdvancedModelBox(this, 40, 0);
        this.wisker1.setRotationPoint(1.0F, 0.0F, -1.0F);
        this.wisker1.addBox(0.5F, -1.5F, 0.0F, 2, 3, 0, scale);
        this.tail2 = new AdvancedModelBox(this, 0, 11);
        this.tail2.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, scale);
        this.leftEye = new AdvancedModelBox(this, 37, 20);
        this.leftEye.setRotationPoint(2.0F, -0.5F, -1.6F);
        this.leftEye.addBox(-0.5F, -0.7F, -0.5F, 1, 1, 1, scale);
        this.leftArm = new AdvancedModelBox(this, 32, 9);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(2.0F, 1.5F, -3.0F);
        this.leftArm.addBox(0.0F, 0.0F, -1.0F, 1, 2, 2, scale);
        this.body2 = new AdvancedModelBox(this, 22, 0);
        this.body2.setRotationPoint(0.0F, -0.4F, 0.5F);
        this.body2.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, scale);
        this.rightThigh = new AdvancedModelBox(this, 22, 9);
        this.rightThigh.setRotationPoint(-2.5F, 0.0F, 5.0F);
        this.rightThigh.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3, scale);
        this.head = new AdvancedModelBox(this, 22, 20);
        this.head.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.head.addBox(-2.0F, -1.5F, -2.5F, 4, 3, 3, scale);
        this.setRotateAngle(head, 0.18656417346763232F, 0.0F, 0.0F);
        this.body1 = new AdvancedModelBox(this, 0, 0);
        this.body1.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body1.addBox(-2.5F, -3.0F, 0.0F, 5, 5, 6, scale);
        this.rightFoot = new AdvancedModelBox(this, 22, 15);
        this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.5F);
        this.rightFoot.addBox(-0.5F, 0.0F, -3.5F, 1, 1, 4, scale);
        this.tail1 = new AdvancedModelBox(this, 0, 11);
        this.tail1.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, scale);
        this.rightHand = new AdvancedModelBox(this, 32, 13);
        this.rightHand.setRotationPoint(-0.4F, 2.0F, -0.3F);
        this.rightHand.addBox(-0.5F, 0.0F, -1.5F, 1, 1, 2, scale);
        this.leftEar = new AdvancedModelBox(this, 33, 20);
        this.leftEar.mirror = true;
        this.leftEar.setRotationPoint(1.5F, -0.5F, 0.0F);
        this.leftEar.addBox(0.0F, -2.0F, 0.0F, 2, 2, 0, scale);
        this.setRotateAngle(leftEar, 0.0F, -0.7853981633974483F, 0.0F);
        this.rightEar = new AdvancedModelBox(this, 33, 20);
        this.rightEar.setRotationPoint(-1.5F, -0.5F, 0.0F);
        this.rightEar.addBox(-2.0F, -2.0F, 0.0F, 2, 2, 0, scale);
        this.setRotateAngle(rightEar, 0.0F, 0.7853981633974483F, 0.0F);
        this.rightArm = new AdvancedModelBox(this, 32, 9);
        this.rightArm.setRotationPoint(-2.0F, 1.5F, -3.0F);
        this.rightArm.addBox(-1.0F, 0.0F, -1.0F, 1, 2, 2, scale);
        this.leftThigh = new AdvancedModelBox(this, 22, 9);
        this.leftThigh.mirror = true;
        this.leftThigh.setRotationPoint(2.5F, 0.0F, 5.0F);
        this.leftThigh.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3, scale);
        this.snout = new AdvancedModelBox(this, 22, 26);
        this.snout.setRotationPoint(0.0F, 0.5F, -2.5F);
        this.snout.addBox(-1.5F, -1.0F, -2.0F, 3, 2, 2, scale);
        this.leftHand = new AdvancedModelBox(this, 32, 13);
        this.leftHand.mirror = true;
        this.leftHand.setRotationPoint(0.4F, 2.0F, -0.3F);
        this.leftHand.addBox(-0.5F, 0.0F, -1.5F, 1, 1, 2, scale);
        this.gear = new AdvancedModelBox(this, 40, -3);
        this.gear.setRotationPoint(0.0F, -3.0F, 2.5F);
        this.gear.addBox(0.0F, -5.0F, -4.0F, 0, 5, 8, 0.0F);
        this.body2.addChild(this.neck);
        this.snout.addChild(this.nose);
        this.head.addChild(this.rightEye);
        this.leftThigh.addChild(this.leftFoot);
        this.snout.addChild(this.wisker1);
        this.snout.addChild(this.wisker2);
        this.tail1.addChild(this.tail2);
        this.head.addChild(this.leftEye);
        this.body2.addChild(this.leftArm);
        this.body1.addChild(this.body2);
        this.body1.addChild(this.rightThigh);
        this.neck.addChild(this.head);
        this.rightThigh.addChild(this.rightFoot);
        this.body1.addChild(this.tail1);
        this.rightArm.addChild(this.rightHand);
        this.head.addChild(this.leftEar);
        this.head.addChild(this.rightEar);
        this.body2.addChild(this.rightArm);
        this.body1.addChild(this.leftThigh);
        this.body1.addChild(this.gear);
        this.head.addChild(this.snout);
        this.leftArm.addChild(this.leftHand);
        this.animator = ModelAnimator.create();
        this.updateDefaultPose();
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.body1.render(f5);
    }


    public void renderNoWiskers(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
        this.wisker1.showModel = false;
        this.wisker2.showModel = false;
        this.body1.render(f5);
        this.wisker1.showModel = true;
        this.wisker2.showModel = true;
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.resetToDefaultPose();
        EntityRatlanteanRatbot rat = (EntityRatlanteanRatbot) entity;
        setRotationAngles(f, f1, f2, f3, f4, f5, (EntityRatlanteanRatbot) entity);
        animator.update(entity);//ANIMATION_IDLE_SCRATCH
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityRatlanteanRatbot rat) {
        float speedWalk = 0.9F;
        float degreeWalk = 0.25F;
        float speedRun = 1F;
        float degreeRun = 0.4F;
        float speedIdle = 0.75F;
        float degreeIdle = 0.15F;
        float speedDance = 0.75F;
        float degreeDance = 0.4F;
        this.walk(this.body1, speedWalk, degreeWalk * 0.25F, false, 0, 0, f, f1);
        this.walk(this.body2, speedWalk, degreeWalk * 0.25F, false, 1, 0.1F, f, f1);
        this.walk(this.rightThigh, speedWalk, degreeWalk * 4F, false, 1, 0, f, f1);
        this.walk(this.rightFoot, speedWalk, degreeWalk * 2F, false, 3.5F, -0.1F, f, f1);
        this.walk(this.leftThigh, speedWalk, degreeWalk * 4F, true, 1, 0, f, f1);
        this.walk(this.leftFoot, speedWalk, degreeWalk * 2F, true, 3.5F, 0.1F, f, f1);
        this.walk(this.rightArm, speedWalk, degreeWalk * 4F, true, 1, 0, f, f1);
        this.walk(this.rightHand, speedWalk, degreeWalk * 2F, true, 3F, 0.1F, f, f1);
        this.walk(this.leftArm, speedWalk, degreeWalk * 4F, false, 1, 0, f, f1);
        this.walk(this.leftHand, speedWalk, degreeWalk * 2F, false, 3F, -0.1F, f, f1);
        this.walk(this.tail1, speedWalk, degreeWalk, false, -1, -0.15F, f, f1);
        this.walk(this.tail2, speedWalk, degreeWalk * 0.5F, true, 0, -0.15F, f, f1);
        this.walk(this.neck, speedWalk, degreeWalk * 0.25F, false, 2, 0, f, f1);
        this.gear.rotateAngleY += MathHelper.lerp(Minecraft.getInstance().getRenderPartialTicks(), rat.prevWalkTick, rat.walkTick) * 0.15F;
    }

    public void setRotateAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }

    public void progressRotation(AdvancedModelBox model, float progress, float rotX, float rotY, float rotZ, float divisor) {
        model.rotateAngleX += progress * (rotX - model.defaultRotationX) / divisor;
        model.rotateAngleY += progress * (rotY - model.defaultRotationY) / divisor;
        model.rotateAngleZ += progress * (rotZ - model.defaultRotationZ) / divisor;
    }

    public void progressPosition(AdvancedModelBox model, float progress, float x, float y, float z, float divisor) {
        model.rotationPointX += progress * (x - model.defaultPositionX) / divisor;
        model.rotationPointY += progress * (y - model.defaultPositionY) / divisor;
        model.rotationPointZ += progress * (z - model.defaultPositionZ) / divisor;
    }

    private void scratchPosition(EntityRat rat) {
        if (rat.holdProgress == 0) {
            rotateFrom(neck, 38, 0, 0);
            rotateFrom(head, 58, 0, 0);
            rotateFrom(rightHand, 25, 10, -30);
            rotateFrom(leftHand, 25, -10, 30);
        }
    }

    private void rotateFrom(AdvancedModelBox renderer, float degX, float degY, float degZ) {
        animator.rotate(renderer, (float) Math.toRadians(degX) - renderer.defaultRotationX, (float) Math.toRadians(degY) - renderer.defaultRotationY, (float) Math.toRadians(degZ) - renderer.defaultRotationZ);
    }

}
