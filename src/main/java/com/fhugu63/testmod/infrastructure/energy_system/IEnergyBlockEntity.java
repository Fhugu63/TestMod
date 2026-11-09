package com.fhugu63.testmod.infrastructure.energy_system;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public interface IEnergyBlockEntity {
    public boolean haveEnergyNetwork();

    public Long getEnergyNetworkId();

    public void setEnergyNetworkId(Long newEnergyNetworkId);

    default public void tick() {};

    default public void onPlace() {};

    default public void onDestroy() {};
}
