package com.fhugu63.testmod.infrastructure.energy_system;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.jcraft.jorbis.Block;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnergyNetwork {



    float totalEnergy = 0;
    float maxCanContainsEnergy = 100000000;

    Long id = null;

    public float r = TestMod.random.nextFloat(1);
    public float g = TestMod.random.nextFloat(1);
    public float b = TestMod.random.nextFloat(1);

    List<BlockPos> energyBlocksPoses = new ArrayList<>();
    ArrayList<IEnergyBlockEntity> energyBlockEntitiesInNetwork = new ArrayList<>();

    public List<BlockPos> getEnergyBlocksPoses() {
        return energyBlocksPoses;
    }

    public EnergyNetwork(Long id) {
        this.id = id;
    }

    public EnergyNetwork(float r, float g, float b, Long id, List<BlockPos> blockPosesList) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.id = id;

        this.energyBlocksPoses = blockPosesList;


    }

    public EnergyNetwork(float totalEnergy, float maxCanContainsEnergy, float r, float g, float b, Long id, List<BlockPos> blockPosesList) {
        this.totalEnergy = totalEnergy;
        this.maxCanContainsEnergy = maxCanContainsEnergy;

        this.r = r;
        this.g = g;
        this.b = b;

        this.id = id;

        this.energyBlocksPoses = blockPosesList;


    }

    public float giveEnergy(float howMuchEnergy) {
        if (howMuchEnergy + totalEnergy > maxCanContainsEnergy) {
            return totalEnergy - howMuchEnergy;
        } else {
            totalEnergy += howMuchEnergy;
        }

        return 0;
    }

    public float getEnergy(float howMuchEnergy) {
        if (totalEnergy - howMuchEnergy < 0) {
            return 0;
        } else {
            totalEnergy -= howMuchEnergy;
        }

        return howMuchEnergy;
    }

    public void changeEnergyNetworkIdInConnectedBlocks(Long id, Level level) {
        energyBlocksPoses.forEach(blockPos -> {
            energyBlockEntitiesInNetwork.add((IEnergyBlockEntity) level.getBlockEntity(blockPos));
        });

        for (IEnergyBlockEntity energyBlockEntity : energyBlockEntitiesInNetwork) {
            EnergyNetwork newEnergyNetwork = EnergyNetworksContainer.getEnergyNetwork(id);

            energyBlockEntity.setEnergyNetworkId(id);

            newEnergyNetwork.energyBlockEntitiesInNetwork.add(energyBlockEntity);
        }
    }

    public void addBlockEntityToNetwork(IEnergyBlockEntity energyBlockEntity) {
        energyBlockEntitiesInNetwork.add(energyBlockEntity);
    }

    public void removeBlockEntityFromNetwork(IEnergyBlockEntity energyBlockEntity) {
        energyBlockEntitiesInNetwork.add(energyBlockEntity);
    }

    public float getTotalEnergy() {
        return this.totalEnergy;
    }

    public float getMaxCanContainsEnergy() {
        return this.maxCanContainsEnergy;
    }

    public int getCountOfConnectedBlocks() {
        return energyBlockEntitiesInNetwork.size();
    }

    public Long getId() {
        return id;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public static class clientEnergyNetwork {
        float totalEnergy;

        float r;
        float g;
        float b;

        Long id;

        public clientEnergyNetwork(float totalEnergy, float r, float g, float b, Long id) {
            this.totalEnergy = totalEnergy;

            this.r = r;
            this.g = g;
            this.b = b;

            this.id = id;
        }

        public float getTotalEnergy() {
            return totalEnergy;
        }

        public float getR() {
            return r;
        }

        public float getG() {
            return g;
        }

        public float getB() {
            return b;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long newId) {
            this.id = newId;
        }
    }
}
