package com.fhugu63.testmod.infrastructure.energy_system;

import java.util.ArrayList;

public class EnergyNetwork {
    float totalEnergy = 0;
    float maxCanContainsEnergy = 100000000;

    ArrayList<EnergyBlockEntity> energyBlockEntitiesInNetwork = new ArrayList<EnergyBlockEntity>();

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

    public void changeEnergyNetworkInConnectedBlocks(EnergyNetwork newEnergyNetwork) {
        for (EnergyBlockEntity energyBlockEntity : energyBlockEntitiesInNetwork) {
            energyBlockEntity.setEnergyNetwork(newEnergyNetwork);

            newEnergyNetwork.energyBlockEntitiesInNetwork.add(energyBlockEntity);
        }
    }

    public void addBlockEntityToNetwork(EnergyBlockEntity energyBlockEntity) {
        energyBlockEntitiesInNetwork.add(energyBlockEntity);
    }

    public void removeBlockEntityFromNetwork(EnergyBlockEntity energyBlockEntity) {
        energyBlockEntitiesInNetwork.add(energyBlockEntity);
    }

    public float getTotalEnergy() {
        return totalEnergy;
    }

    public int getCountOfConnectedBlocks() {
        return energyBlockEntitiesInNetwork.size();
    }
}
