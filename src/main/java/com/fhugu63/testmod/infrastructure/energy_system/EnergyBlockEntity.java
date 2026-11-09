package com.fhugu63.testmod.infrastructure.energy_system;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.AllBlocks;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EnergyBlockEntity extends BlockEntity implements IEnergyBlockEntity {
    Long energyNetworkId = null;
    public float gettedEnergy = 0;

    public EnergyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public boolean haveEnergyNetwork() {
        return energyNetworkId != null;
    }

    @Override
    public Long getEnergyNetworkId() {
        //System.out.println(this.energyNetwork);
        return this.energyNetworkId;
    }

    @Override
    public void setEnergyNetworkId(Long newEnergyNetworkId) {
        this.energyNetworkId = newEnergyNetworkId;
    }

    @Override
    public void tick() {

    }

    @Override
    public void onPlace() {

    }

    @Override
    public void onDestroy() {

    }
}
