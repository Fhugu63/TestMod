package com.fhugu63.testmod.content.energy.kinetic_generator;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.KineticGeneratorBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class KineticGeneratorBlockEntity extends KineticGeneratorBE {
    public KineticGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntitys.KINETIC_GENERATOR_BE.get(), pos, state);
    }
}
