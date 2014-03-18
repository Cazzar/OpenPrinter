package net.cazzar.mods.openprinter.blocks.tile

import li.cil.oc.api.network.{Arguments, Context, Callback}
import net.minecraft.tileentity.TileEntity
import net.minecraft.item.ItemStack
import net.minecraft.entity.item.EntityItem

trait MiningPrinter extends TileEntity {
    @Callback
    def breakBlock(ctx: Context, args: Arguments): Array[Object] = {
        val x = args.checkInteger(0)
        val y = args.checkInteger(1)
        val z = args.checkInteger(2)

        val items = getWorldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).getDrops(getWorldObj, x, y, z, getWorldObj.getBlockMetadata(x, y, z), 0).listIterator()
        while (items.hasNext) {
            getWorldObj.spawnEntityInWorld(new EntityItem(getWorldObj, xCoord + x, yCoord + y, zCoord + z, items.next()))
        }

        getWorldObj.setBlockToAir(xCoord + x, yCoord + y, zCoord + z)
        Array()
    }
}
