package com.fhugu63.testmod.infrastructure.codec_record;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainerCodec;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record EnergyNetworkRecord(float totalEnergy, float r, float g, float b, BlockPos blockPosToChange, Long id)
        implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<EnergyNetworkRecord> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "energy_network"));

    //public static final Codec<EnergyNetwork> energyNetworkCodec = RecordCodecBuilder.f
    public static final StreamCodec<ByteBuf, EnergyNetworkRecord> STREAM_CODEC = StreamCodec.composite(
            // totalEnergy, maxContaintEnergy, r, g, b
            ByteBufCodecs.FLOAT,
            EnergyNetworkRecord::totalEnergy,

            ByteBufCodecs.FLOAT,
            EnergyNetworkRecord::r,
            ByteBufCodecs.FLOAT,
            EnergyNetworkRecord::g,
            ByteBufCodecs.FLOAT,
            EnergyNetworkRecord::b,

            BlockPos.STREAM_CODEC,
            EnergyNetworkRecord::blockPosToChange,

            ByteBufCodecs.VAR_LONG,
            EnergyNetworkRecord::id,

            EnergyNetworkRecord::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
