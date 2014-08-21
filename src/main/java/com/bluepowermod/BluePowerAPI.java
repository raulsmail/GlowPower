package com.bluepowermod;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.bluepowermod.api.BPApi.IBPApi;
import com.bluepowermod.api.bluestone.IBluestoneApi;
import com.bluepowermod.api.compat.IMultipartCompat;
import com.bluepowermod.api.part.BPPart;
import com.bluepowermod.api.part.IPartRegistry;
import com.bluepowermod.api.recipe.IAlloyFurnaceRegistry;
import com.bluepowermod.api.tube.IPneumaticTube;
import com.bluepowermod.compat.CompatibilityUtils;
import com.bluepowermod.part.PartRegistry;
import com.bluepowermod.part.cable.bluestone.BluestoneApi;
import com.bluepowermod.part.tube.PneumaticTube;
import com.bluepowermod.recipe.AlloyFurnaceRegistry;
import com.bluepowermod.util.Dependencies;

public class BluePowerAPI implements IBPApi {
    
    @Override
    public IMultipartCompat getMultipartCompat() {
    
        return (IMultipartCompat) CompatibilityUtils.getModule(Dependencies.FMP);
    }
    
    @Override
    public IPartRegistry getPartRegistry() {
    
        return PartRegistry.getInstance();
    }
    
    @Override
    public IPneumaticTube getPneumaticTube(TileEntity te) {
    
        PneumaticTube tube = getMultipartCompat().getBPPart(te, PneumaticTube.class);
        return tube != null ? tube.getLogic() : null;
    }
    
    @Override
    public IAlloyFurnaceRegistry getAlloyFurnaceRegistry() {
    
        return AlloyFurnaceRegistry.getInstance();
    }
    
    @Override
    public IBluestoneApi getBluestoneApi() {
    
        return BluestoneApi.getInstance();
    }
    
    @Override
    public void loadSilkySettings(World world, int x, int y, int z, ItemStack stack) {
    
        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null) throw new IllegalStateException("This block doesn't have a tile entity?!");
        if (stack == null) throw new IllegalArgumentException("ItemStack is null!");
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("tileData")) {
                NBTTagCompound tileTag = tag.getCompoundTag("tileData");
                tileTag.setInteger("x", x);
                tileTag.setInteger("y", y);
                tileTag.setInteger("z", z);
                te.readFromNBT(tileTag);
            }
        }
    }
    
    @Override
    public void loadSilkySettings(BPPart part, ItemStack stack) {
    
        if (stack == null) throw new IllegalArgumentException("ItemStack is null!");
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("tileData")) {
                NBTTagCompound tileTag = tag.getCompoundTag("tileData");
                part.load(tileTag);
            }
        }
    }
    
}
