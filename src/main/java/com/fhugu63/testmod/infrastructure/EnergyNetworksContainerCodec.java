package com.fhugu63.testmod.infrastructure;

import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record EnergyNetworksContainerCodec() {
    static HashMap<Long, EnergyNetwork> energyNetworks = new HashMap<>();

    /*public static final Codec<List<BlockPos>> blockPosCodec = BlockPos.CODEC.listOf();

    //public static final Codec<Long> longCodec = Codec.unboundedMap(Codec.LONG)
    //);

    public static final Codec<EnergyNetwork> energyNetworkCodec = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    Codec.FLOAT.fieldOf("totalEnergy").forGetter(EnergyNetwork::getTotalEnergy),
                    Codec.FLOAT.fieldOf("maxContainEnergy").forGetter(EnergyNetwork::getMaxCanContainsEnergy),

                    Codec.FLOAT.fieldOf("r").forGetter(EnergyNetwork::getR),
                    Codec.FLOAT.fieldOf("g").forGetter(EnergyNetwork::getG),
                    Codec.FLOAT.fieldOf("b").forGetter(EnergyNetwork::getB),

                    Codec.LONG.fieldOf("energyNetworkId").forGetter(EnergyNetwork::getId),

                    BlockPos.CODEC.listOf().fieldOf("blockPosList").forGetter(EnergyNetwork::getEnergyBlocksPoses)
            ).apply(instance, EnergyNetwork::new) // Define how to create the object
    );

    public static final Codec<Map<Long, EnergyNetwork>> energyNetworkContainerCodec = Codec.unboundedMap(Codec.LONG, energyNetworkCodec);*/
}
