package com.fhugu63.testmod.content.energy.motor;

import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BasicElectriMotorRenderer implements BlockEntityRenderer<BasicElectricalMotorBlockEntity> {
    public BasicElectriMotorRenderer(BlockEntityRendererProvider.Context context) {

    }

    /*@Override
    protected SuperByteBuffer getRotatedModel(BasicElectricalMotorBlockEntity be, BlockState state) {
        return CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, state);
    }*/

    @Override
    public void render(BasicElectricalMotorBlockEntity basicElectricalMotorBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();

        PoseStack.Pose pose = poseStack.last();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.lightning());
        EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(basicElectricalMotorBlockEntity.getEnergyNetworkId());

        /*vertexConsumer.addVertex(pose, 0f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);
        vertexConsumer.addVertex(pose, 1f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 0f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;

        vertexConsumer.addVertex(pose, 1f, 1f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 1f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 1f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;
        vertexConsumer.addVertex(pose, 1f, 0f, 0f).setColor(1f, 1f, 1f, 1f).setNormal(pose, 0f, 0f, 0f);;*/

        if (basicElectricalMotorBlockEntity.haveEnergyNetwork()) {
            vertexConsumer.addVertex(pose, 0f, 0f, 1f)
                    .setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 0f, 1f, 1f).setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 1f, 1f, 1f).setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);
            vertexConsumer.addVertex(pose, 1f, 0f, 1f).setColor(energyNetwork.r, energyNetwork.g, energyNetwork.b, 1)
                    .setNormal(pose, 0f, 0f, 0f).setUv(0, 0);


        }



        poseStack.popPose();
    }
}
