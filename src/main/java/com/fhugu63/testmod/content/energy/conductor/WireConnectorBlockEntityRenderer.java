package com.fhugu63.testmod.content.energy.conductor;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class WireConnectorBlockEntityRenderer implements BlockEntityRenderer<WireConnectorBlockEntity> {
    RenderType renderType = RenderType.lineStrip();
    BlockEntityRendererProvider.Context context;

    public WireConnectorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(WireConnectorBlockEntity wireConnectorBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
        EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity.getEnergyNetworkId());

        Vec3 vec3 = new Vec3(
                wireConnectorBlockEntity.getBlockPos().getX()-0.5,
                wireConnectorBlockEntity.getBlockPos().getY()-0.5,
                wireConnectorBlockEntity.getBlockPos().getZ()-0.5
        );
        for (WireConnectorBlockEntity wireConnector : wireConnectorBlockEntity.getConnectedWireConnectors()) {
            Vec3 vec31 = new Vec3(
                    wireConnector.getBlockPos().getX(),
                    wireConnector.getBlockPos().getY(),
                    wireConnector.getBlockPos().getZ()
            );

            float rx = (float) (vec31.x - vec3.x);
            float ry = (float) (vec31.y - vec3.y);
            float rz = (float) (vec31.z - vec3.z);

            vertexConsumer.addVertex(pose, rx, ry, rz)
                    .setColor(-16777216).setNormal(pose, 0.5f, 1f, 0.5f);
            vertexConsumer.addVertex(pose, 0.5f, 0.5f, 0.5f)
                    .setColor(-16777216).setNormal(pose, 0.5f, 1f, 0.5f);
        }

        vertexConsumer = multiBufferSource.getBuffer(RenderType.lightning());

        /*vertexConsumer.addVertex(pose, 0f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);
        vertexConsumer.addVertex(pose, 1f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 0f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;

        vertexConsumer.addVertex(pose, 1f, 1f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 1f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;*/

        if (wireConnectorBlockEntity.haveEnergyNetwork() && energyNetwork != null) {
            vertexConsumer.addVertex(pose, 0f, 1f, 0f)
                    .setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 0f, 1f, 1f)
                    .setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 1f, 1f, 1f)
                    .setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 1f, 1f, 0f)
                    .setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
        }



        poseStack.popPose();
    }

    /*@Override
    public void render(WireConnectorBlockEntity wireConnectorBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        //Player player = entity.getPlayerOwner();
        if (true) {
            poseStack.pushPose();
            //PoseStack.Pose posestack$pose = poseStack.last();
            //VertexConsumer vertexconsumer = multiBufferSource.getBuffer(renderType);
            //vertex(vertexconsumer, posestack$pose, 15, 0.0F, 0, 0, 1);
            //vertex(vertexconsumer, posestack$pose, 15, 1.0F, 0, 1, 1);
            //vertex(vertexconsumer, posestack$pose, 15, 1.0F, 1, 1, 0);
            //vertex(vertexconsumer, posestack$pose, 15, 0.0F, 1, 0, 0);
            float f = 0;
            float f1 = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
            Vec3 vec3 = new Vec3(
                    wireConnectorBlockEntity.getBlockPos().getX()-0.5,
                    wireConnectorBlockEntity.getBlockPos().getY()-0.5,
                    wireConnectorBlockEntity.getBlockPos().getZ()-0.5
            );
            for (EnergyBlockEntity energyBlockEntity : wireConnectorBlockEntity.getConnectedWireConnectors()) {
                Vec3 vec31 = new Vec3(
                        energyBlockEntity.getBlockPos().getX(),
                        energyBlockEntity.getBlockPos().getY(),
                        energyBlockEntity.getBlockPos().getZ()
                );
                float f2 = (float) (vec31.x - vec3.x);
                float f3 = (float) (vec31.y - vec3.y);
                float f4 = (float) (vec31.z - vec3.z);

                float sqrt = Mth.sqrt(f2 * f2 + f3 * f3 + f4 * f4);

                VertexConsumer vertexconsumer1 = multiBufferSource.getBuffer(RenderType.lineStrip());
                PoseStack.Pose posestack$pose1 = poseStack.last();
                int g = 16;

                for (int j = 0; j <= 16; ++j) {
                    //stringVertex(f2, f3, f4, vertexconsumer1, posestack$pose1, fraction(j, 16), fraction(j + 1, 16));
                    //vertexconsumer1.addVertex()
                    //vertexconsumer1.addVertex(f2, f3, f4)
                    //        .setColor(-16777216).setNormal(f2/sqrt, f3/sqrt, f4/sqrt);
                    //vertexconsumer1.addVertex(posestack$pose1, f2+j, f3+j, f4+j)
                    //        .setColor(-16777216).setNormal(posestack$pose1, 0, 0, 0);
                }
                vertexconsumer1.addVertex(posestack$pose1, f2, f3, f4)
                        .setColor(-16777216).setNormal(posestack$pose1, 0.5f, 1f, 0.5f);
                vertexconsumer1.addVertex(posestack$pose1, 0.5f, 0.5f, 0.5f)
                        .setColor(-16777216).setNormal(posestack$pose1, 0.5f, 1f, 0.5f);

                System.out.println("x1: " + f2);
                System.out.println("y1: " + f3);
                System.out.println("z1: " + f4);

                System.out.println("x2: " + f2/sqrt);
                System.out.println("y2: " + f3/sqrt);
                System.out.println("z2: " + f4/sqrt);

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
        System.out.println("x1: " + f);
        System.out.println("y1: " + f1);
        System.out.println("z1: " + f2);
        System.out.println("x2: " + f3);
        System.out.println("y2: " + f4);
        System.out.println("z2: " + f5);
        consumer.addVertex(pose, f, f1, f2).setColor(-16777216).setNormal(pose, f3, f4, f5);
    }
}
