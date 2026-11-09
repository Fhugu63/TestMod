package com.fhugu63.testmod.infrastructure.energy_system;

import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Overwrite;

public class KineticGeneratorBE extends KineticBlockEntity implements IEnergyBlockEntity {
    Long energyNetworkId = null;

    public KineticGeneratorBE(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
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
    public void setEnergyNetworkId(Long id) {
        this.energyNetworkId = id;
    }

    public float generateFor1RPMPerTick() {
        return 1;
    }

    @Overwrite
    public void tick() {
        super.tick();

        if (this.speed != 0 && this.haveEnergyNetwork()) {
            EnergyNetwork energyNetwork = EnergyNetworksContainer.getEnergyNetwork(this.getEnergyNetworkId());
            energyNetwork.giveEnergy(this.generateFor1RPMPerTick() * this.speed);
        }
    }
}
