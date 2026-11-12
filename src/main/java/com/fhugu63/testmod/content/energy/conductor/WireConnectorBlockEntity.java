package com.fhugu63.testmod.content.energy.conductor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class WireConnectorBlockEntity extends EnergyBlockEntity implements IEnergyBlockEntity {
    String energyNetworkIdName = "networkId";
    Long energyNetworkId = null;
    List<WireConnectorBlockEntity> connectedWireConnectors = new ArrayList<>();


    ArrayList<Player> whoUniteConnectors = new ArrayList<>();

    public WireConnectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(AllBlockEntitys.WIRE_CONNECTOR_BLOCK_ENTITY.get(), pos, blockState);
    }

    public List<WireConnectorBlockEntity> getConnectedWireConnectors() {
        return this.connectedWireConnectors;
    }

    public void connectWireConnector(WireConnectorBlockEntity connectedTo) {
        this.getConnectedWireConnectors().add(connectedTo);

        if (!this.getBlockState().getValue(WireConnectorBlock.booleanPropertyConnected)) {
            BlockState newBlockState = this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, true);

            level.setBlock(this.getBlockPos(), newBlockState, Block.UPDATE_ALL);

            System.out.println(getConnectedWireConnectors());
        }
    }

    public void disConnectWireConnector(WireConnectorBlockEntity energyBlockEntity) {
        this.getConnectedWireConnectors().remove(energyBlockEntity);

        if (this.getConnectedWireConnectors().isEmpty()) {
            level.setBlock(this.getBlockPos(), this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, false), Block.UPDATE_ALL);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WireConnectorBlockEntity blockEntity) {

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        //this.setEnergyNetworkId(tag.getLong(energyNetworkIdName));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (this.haveEnergyNetwork()) {
            //tag.putLong(energyNetworkIdName, this.getEnergyNetworkId());
        }
    }

    public BlockPos playerCickOn(Player player, BlockPos blockPos) {
        if (this.whoUniteConnectors.contains(player)) {
            whoUniteConnectors.remove(player);

            WireConnectorBlockEntity wireConnectorBlockEntity = (WireConnectorBlockEntity) this.level.getBlockEntity(blockPos);

            wireConnectorBlockEntity.connectWireConnector(this);
            this.connectWireConnector(wireConnectorBlockEntity);

            if (!this.haveEnergyNetwork() && !wireConnectorBlockEntity.haveEnergyNetwork()) {
                EnergyNetwork energyNetwork = EnergyNetworksContainer.createEnergyNetwork();

                this.setEnergyNetworkId(energyNetwork.getId());
                wireConnectorBlockEntity.setEnergyNetworkId(energyNetwork.getId());

                energyNetwork.addBlockEntityToNetwork(this);
                energyNetwork.addBlockEntityToNetwork(wireConnectorBlockEntity);

                TestMod.LOGGER.info("Connected!");

                return this.getBlockPos();
            } else if (this.haveEnergyNetwork()) {
                wireConnectorBlockEntity.setEnergyNetworkId(this.getEnergyNetworkId());

                EnergyNetworksContainer.getEnergyNetwork(this.getEnergyNetworkId()).addBlockEntityToNetwork(wireConnectorBlockEntity);
            } else {
                this.setEnergyNetworkId(wireConnectorBlockEntity.getEnergyNetworkId());

                EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity.getEnergyNetworkId()).addBlockEntityToNetwork(this);
            }
        } else {
            whoUniteConnectors.add(player);
        }

        TestMod.LOGGER.info("Called!");

        return null;
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
    public void tick() {

    }

    @Override
    public void onPlace() {

    }

    @Override
    public void onDestroy() {

    }
}
