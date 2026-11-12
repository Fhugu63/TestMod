package com.fhugu63.testmod.infrastructure;

import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class EnergyNetworksContainer {
    static HashMap<Long, EnergyNetwork> energyNetworks = new HashMap<>();

    public static EnergyNetwork createEnergyNetwork() {
        Random random = new Random();

        Long id = random.nextLong();
        while (energyNetworks.containsKey(id)) {
            id = random.nextLong();
        }
        EnergyNetwork energyNetwork = new EnergyNetwork(id);

        energyNetworks.put(id, energyNetwork);

        return energyNetwork;
    }

    public static void removeEnergyNetwork(Long id) {
        energyNetworks.remove(id);
    }

    public static EnergyNetwork getEnergyNetwork(Long id) {
        return energyNetworks.get(id);
    }

    public static Collection<EnergyNetwork> getEnergyNetworks() {
        return energyNetworks.values();
    }

    public static void putEnergyNetwork(Long id, EnergyNetwork energyNetwork) {
        energyNetworks.put(id, energyNetwork);
    }

    public static boolean containtEnergyNetwork(Long id) {
        return energyNetworks.containsKey(id);
    }

    public static class clientEnergyNetworksContainer {
        static HashMap<Long, EnergyNetwork.clientEnergyNetwork> clientEnergyNetworks = new HashMap<>();

        /*public static EnergyNetwork.clientEnergyNetwork createEnergyNetwork() {
            Random random = new Random();

            Long id = random.nextLong();
            while (clientEnergyNetworks.containsKey(id)) {
                id = random.nextLong();
            }
            EnergyNetwork.clientEnergyNetwork energyNetwork = new EnergyNetwork.clientEnergyNetwork(id);

            clientEnergyNetworks.put(id, clientEnergyNetworks);

            return clientEnergyNetworks;
        }*/

        public static void removeEnergyNetwork(Long id) {
            clientEnergyNetworks.remove(id);
        }

        public static EnergyNetwork.clientEnergyNetwork getEnergyNetwork(Long id) {
            return clientEnergyNetworks.get(id);
        }

        public static Collection<EnergyNetwork.clientEnergyNetwork> getEnergyNetworks() {
            return clientEnergyNetworks.values();
        }

        public static void putEnergyNetwork(Long id, EnergyNetwork.clientEnergyNetwork energyNetwork) {
            clientEnergyNetworks.put(id, energyNetwork);
        }

        public static boolean containtEnergyNetwork(Long id) {
            return clientEnergyNetworks.containsKey(id);
        }
    }

}
