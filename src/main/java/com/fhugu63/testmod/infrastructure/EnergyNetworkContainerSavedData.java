package com.fhugu63.testmod.infrastructure;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

import java.io.DataOutput;

public class EnergyNetworkContainerSavedData extends SavedData {
    public static EnergyNetworkContainerSavedData create() {
        return new EnergyNetworkContainerSavedData();
    }

    // Load existing instance of saved data
    public static EnergyNetworkContainerSavedData load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        EnergyNetworkContainerSavedData data = EnergyNetworkContainerSavedData.create();
        // Load saved data
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        //tag.t;
        // Write data to tag
        return tag;
    }

    public void foo() {
        // Change data in saved data
        // Call set dirty if data changes
        this.setDirty();
    }

}
