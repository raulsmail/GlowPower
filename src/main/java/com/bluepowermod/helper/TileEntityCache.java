package com.bluepowermod.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author MineMaarten
 */

public class TileEntityCache {
    
    private TileEntity  te;
    private final World world;
    private final int   x, y, z;
    
    public TileEntityCache(World world, int x, int y, int z) {
    
        if (world == null) throw new NullPointerException("World can't be null!");
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        update();
    }
    
    public void update() {
    
        te = world.getTileEntity(x, y, z);
    }
    
    public TileEntity getTileEntity() {
    
        return te;
    }
    
    public static TileEntityCache[] getDefaultCache(World world, int x, int y, int z) {
    
        TileEntityCache[] cache = new TileEntityCache[6];
        for (int i = 0; i < 6; i++) {
            ForgeDirection d = ForgeDirection.getOrientation(i);
            cache[i] = new TileEntityCache(world, x + d.offsetX, y + d.offsetY, z + d.offsetZ);
        }
        return cache;
    }
    
}
