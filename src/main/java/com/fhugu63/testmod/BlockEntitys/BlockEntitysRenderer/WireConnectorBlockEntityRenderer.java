package com.fhugu63.testmod.BlockEntitys.BlockEntitysRenderer;

import com.fhugu63.testmod.BlockEntitys.WireConnectorBlockEntity;
import com.fhugu63.testmod.Energy.EnergyBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import org.joml.Vector3f;

public class WireConnectorBlockEntityRenderer implements BlockEntityRenderer<WireConnectorBlockEntity> {
    RenderType renderType = RenderType.entityCutout(ResourceLocation.withDefaultNamespace("textures/entity/fishing_hook.png"));
    BlockEntityRendererProvider.Context context;

    public WireConnectorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(WireConnectorBlockEntity wireConnectorBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        //Player player = entity.getPlayerOwner();
        
            poseStack.pushPose();
            PoseStack pose = poseStack.last();
            VertexConsumer vertexConsumer = = multiBufferSource.getBuffer(RenderType.lineStrip());
            
            Vec3 vec3 = new Vec3(
                    wireConnectorBlockEntity.getBlockPos().getX(),
                    wireConnectorBlockEntity.getBlockPos().getY() + 1,
                    wireConnectorBlockEntity.getBlockPos().getZ()
            );
            
            for (EnergyBlockEntity energyBlockEntity : wireConnectorBlockEntity.getConnectedWireConnectors()) {
                Vec3 vec31 = new Vec3(
                        energyBlockEntity.getBlockPos().getX(),
                        energyBlockEntity.getBlockPos().getY(),
                        energyBlockEntity.getBlockPos().getZ()
                );
                float f2 = (float) (vec3.x - vec31.x);
                float f3 = (float) (vec3.y - vec31.y);
                float f4 = (float) (vec3.z - vec31.z);
                
                vertexConsumer.addVertex(pose, 0.5f, 0.5f, 0.5f).setColor(-16777216).setNormal(0.5f, 0.5f, 0.5f);
                vertexConsumer.addVertex(pose, f2+0.5f, f3+0.5f, f4+0.5f).setColor(-16777216).setNormal(0.5f, 0.5f, 0.5f);
            }

            poseStack.popPose();
        

    }
    
    /*@Override
    public void render(WireConnectorBlockEntity wireConnectorBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        //Player player = entity.getPlayerOwner();
        if (true) {
            poseStack.pushPose();
            poseStack.pushPose();
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(this.context.getEntityRenderer().cameraOrientation());
            PoseStack.Pose posestack$pose = poseStack.last();
            VertexConsumer vertexconsumer = multiBufferSource.getBuffer(renderType);
            vertex(vertexconsumer, posestack$pose, 15, 0.0F, 0, 0, 1);
            vertex(vertexconsumer, posestack$pose, 15, 1.0F, 0, 1, 1);
            vertex(vertexconsumer, posestack$pose, 15, 1.0F, 1, 1, 0);
            vertex(vertexconsumer, posestack$pose, 15, 0.0F, 1, 0, 0);
            poseStack.popPose();
            float f = 0;
            float f1 = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
            Vec3 vec3 = new Vec3(
                    wireConnectorBlockEntity.getBlockPos().getX(),
                    wireConnectorBlockEntity.getBlockPos().getY() + 1,
                    wireConnectorBlockEntity.getBlockPos().getZ()
            );
            for (EnergyBlockEntity energyBlockEntity : wireConnectorBlockEntity.getConnectedWireConnectors()) {
                Vec3 vec31 = new Vec3(
                        energyBlockEntity.getBlockPos().getX(),
                        energyBlockEntity.getBlockPos().getY(),
                        energyBlockEntity.getBlockPos().getZ()
                );
                float f2 = (float) (vec3.x - vec31.x);
                float f3 = (float) (vec3.y - vec31.y);
                float f4 = (float) (vec3.z - vec31.z);
                VertexConsumer vertexconsumer1 = multiBufferSource.getBuffer(RenderType.lineStrip());
                PoseStack.Pose posestack$pose1 = poseStack.last();
                int g = 16;

                for (int j = 0; j <= 16; ++j) {
                    stringVertex(f2, f3, f4, vertexconsumer1, posestack$pose1, fraction(j, 16), fraction(j + 1, 16));
                }


                //super.render(wireConnectorBlockEntity, v, poseStack, poseStack, multiBufferSource, i, i1);
            }

            poseStack.popPose();
        }

    }*/

    private static float fraction(int numerator, int denominator) {
        return (float)numerator / (float)denominator;
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, int y, int u, int v) {
        consumer.addVertex(pose, x - 0.5F, (float)y - 0.5F, 0.0F).setColor(-1).setUv((float)u, (float)v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    private static void stringVertex(float x, float y, float z, VertexConsumer consumer, PoseStack.Pose pose, float stringFraction, float nextStringFraction) {
        float f = x * stringFraction;
        float f1 = y * (stringFraction * stringFraction + stringFraction) * 0.5F + 0.25F;
        float f2 = z * stringFraction;
        float f3 = x * nextStringFraction - f;
        float f4 = y * (nextStringFraction * nextStringFraction + nextStringFraction) * 0.5F + 0.25F - f1;
        float f5 = z * nextStringFraction - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 /= f6;
        f4 /= f6;
        f5 /= f6;
        consumer.addVertex(pose, f, f1, f2).setColor(-16777216).setNormal(pose, f3, f4, f5);
    }
}
