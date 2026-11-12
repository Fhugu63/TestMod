package com.fhugu63.testmod.content.energy.motor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.energy_system.ElectricMotorBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BasicElectricalMotorBlockEntity extends ElectricMotorBlockEntity implements IEnergyBlockEntity {
    public static final int DEFAULT_SPEED = 32;
    public static final int MAX_SPEED = 256;

    // per tick
    public static final float requiresEnergy = 10;

    public ScrollValueBehaviour generatedSpeed;

    int nowSpeed = 0;

    public BasicElectricalMotorBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntitys.BASIC_ELECTRIC_MOTOR_BE.get(), pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        this.applyNewSpeed(16, 32);
        /*if (this.haveEnergyNetwork()) {
            EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(this.getEnergyNetworkId());
            energyNetwork.getEnergy(requiresEnergy);

            nowSpeed = DEFAULT_SPEED;
            this.applyNewSpeed(nowSpeed, nowSpeed);
        } else {
            this.applyNewSpeed(0, 0);
        }*/
    }

    @Override
    public float getGeneratedSpeed() {
        return nowSpeed;
    }


    public void onPlace() {
        super.onLoad();

        if (!this.level.isClientSide()) {
            Vec3 vec3BlockPos = new Vec3(
                    this.getBlockPos().getX(),
                    this.getBlockPos().getY(),
                    this.getBlockPos().getZ()
            );

            AABB aabb = AABB.ofSize(vec3BlockPos, 6, 6, 6);

            Stream<BlockPos> blockPosStream = BlockPos.betweenClosedStream(aabb);
            List<BlockPos> blockPosList = blockPosStream.toList();

            //Stream<BlockState> blockStates = level.getBlockStates(aabb);
            System.out.println(aabb);

            int x = (int) aabb.minX;
            int y = (int) aabb.minY;
            int z = (int) aabb.minZ;

            while (true) {
                BlockPos blockPos = new BlockPos(x, y, z);
                BlockEntity blockEntity = this.level.getBlockEntity(blockPos);

                if (blockEntity != null && blockEntity.getClass() == WireConnectorBlockEntity.class && !blockEntity.isRemoved() && ((WireConnectorBlockEntity) blockEntity).haveEnergyNetwork()) {
                    WireConnectorBlockEntity wireConnectorBlockEntity = (WireConnectorBlockEntity) blockEntity;
                    EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity.getEnergyNetworkId());

                    this.setEnergyNetworkId(energyNetwork.getId());

                    break;
                }
                if (aabb.maxX == x && aabb.maxY == y && aabb.maxZ == z) {
                    break;
                }

                if (aabb.maxX == x && aabb.maxZ == z) {
                    x = (int) aabb.minX;
                    z = (int) aabb.minZ;
                    y++;
                }

                if (aabb.maxX == x) {
                    x = (int) aabb.minX;
                    z++;
                }

                x++;

                System.out.println("x: " + x);
                System.out.println("y: " + y);
                System.out.println("z: " + z);
            }

            //System.out.println(ene);
        }
    }

    /*public void onPlace() {
        if (!this.level.isClientSide()) {
            Vec3 vec3BlockPos = new Vec3(
                    this.getBlockPos().getX(),
                    this.getBlockPos().getY(),
                    this.getBlockPos().getZ()
            );

            AABB aabb = AABB.ofSize(vec3BlockPos, 6, 6, 6);

            Stream<BlockPos> blockPosStream = BlockPos.betweenClosedStream(aabb);
            List<BlockPos> blockPosList = blockPosStream.toList();

            //Stream<BlockState> blockStates = level.getBlockStates(aabb);
            System.out.println(aabb);

            int x = (int) aabb.minX;
            int y = (int) aabb.minY;
            int z = (int) aabb.minZ;

            while (true) {
                BlockEntity blockEntity = this.level.getBlockEntity(new BlockPos(x, y, z));
                System.out.println(blockEntity);
                if (blockEntity != null && blockEntity.getClass() == WireConnectorBlockEntity.class) {
                    WireConnectorBlockEntity wireConnectorBlockEntity = (WireConnectorBlockEntity) blockEntity;
                    EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity.getEnergyNetworkId());

                    System.out.println(energyNetwork);
                    this.setEnergyNetworkId(energyNetwork.getId());

                    break;
                }
                if (aabb.maxX == x && aabb.maxY == y && aabb.maxZ == z) {
                    break;
                }

                if (aabb.maxX == x && aabb.maxZ == z) {
                    x = (int) aabb.minX;
                    z = (int) aabb.minZ;
                    y++;
                }

                if (aabb.maxX == x) {
                    x = (int) aabb.minX;
                    z++;
                }

                x++;

                System.out.println("x: " + x);
                System.out.println("y: " + y);
                System.out.println("z: " + z);
            }

            //System.out.println(this.getEnergyNetwork());
        }
    }*/
}
