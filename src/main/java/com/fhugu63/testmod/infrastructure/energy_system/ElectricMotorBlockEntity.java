package com.fhugu63.testmod.infrastructure.energy_system;

import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricMotorBlockEntity extends GeneratingKineticBlockEntity implements IEnergyBlockEntity {
    Long energyNetworkId = null;

    public ElectricMotorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getGeneratedSpeed() {
        return 32;
    }

    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
            updateGeneratedRotation();
    }

    @Override
    public boolean haveEnergyNetwork() {
        return this.energyNetworkId != null;
    }

    @Override
    public Long getEnergyNetworkId() {
        return this.energyNetworkId;
    }

    @Override
    public void setEnergyNetworkId(Long newEnergyNetworkId) {
        this.energyNetworkId = newEnergyNetworkId;
    }

    @Override
    public void onPlace() {

    }

    @Override
    public void onDestroy() {

    }
}
