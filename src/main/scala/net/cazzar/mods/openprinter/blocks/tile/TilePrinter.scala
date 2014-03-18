package net.cazzar.mods.openprinter.blocks.tile

import li.cil.oc.api.prefab.TileEntityEnvironment
import li.cil.oc.api.Network
import li.cil.oc.api.network._
import net.minecraft.command.CommandGive
import net.minecraft.block.Block
import net.minecraftforge.common.util.ForgeDirection._
import net.minecraft.inventory.IInventory
import net.minecraftforge.common.util.ForgeDirection
import net.minecraft.item.ItemBlock
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.init.Blocks

class TilePrinter extends TileEntityEnvironment {
    val costPerPlacement = 2

    private var isReady = true
    node = Network.newNode(this, Visibility.Network).withConnector().withComponent("printer").create()

    @Callback
    def isReady(ctx: Context, args: Arguments): Array[Object] = Array(Boolean.box(isReady))

    @Callback
    def placeBlock(ctx: Context, args: Arguments): Array[Object] = {
        val blockNotation = args.checkString(0)
        val block = Block.getBlockFromName(blockNotation)
        val x = args.checkInteger(1)
        val y = args.checkInteger(2)
        val z = args.checkInteger(3)

        if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).isReplaceable(worldObj, x, y, z)) return Array(Boolean.box(false))

        if (!searchForBlock(block)) return Array("Block \"" + blockNotation + "\" was not found!")

        worldObj.setBlock(xCoord + x, yCoord + y, zCoord + z, block)
        node.asInstanceOf[Connector].tryChangeBuffer(-costPerPlacement)
        Array(Boolean.box(true))
    }

    @Callback
    def placeBlockWithMeta(ctx: Context, args: Arguments): Array[Object] = {
        val blockNotation = args.checkString(0)
        val block = Block.getBlockFromName(blockNotation)
        val x = args.checkInteger(1)
        val y = args.checkInteger(2)
        val z = args.checkInteger(3)
        val meta = args.checkInteger(4)

        if (worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z).isReplaceable(worldObj, x, y, z)) return Array(Boolean.box(false))

        if (!searchForBlock(block)) return Array("Block \"" + blockNotation + "\" was not found!")

        worldObj.setBlock(xCoord + x, yCoord + y, zCoord + z, block, meta, 1 & 2)
        node.asInstanceOf[Connector].tryChangeBuffer(-costPerPlacement)

        Array(Boolean.box(false))
    }

    def searchForBlock(block: Block): Boolean = {
        val dirs = Array(DOWN, UP, NORTH, SOUTH, EAST, WEST)
        for (dir <- dirs) {
            val te = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ)
            te match {
                case inv: IInventory =>
                    for (i <- 0 to inv.getSizeInventory) {
                        val stack = inv.getStackInSlot(i)
                        if (stack != null && stack.getItem.isInstanceOf[ItemBlock]
                            && stack.getItem.asInstanceOf[ItemBlock].field_150939_a == block) {
                            if (stack.stackSize - 1  <= 0)
                                inv.setInventorySlotContents(i, null)
                            else
                                inv.setInventorySlotContents(i, inv.decrStackSize(i, stack.stackSize - 1))
                            return true
                        }
                    }
                case _ =>
            }
        }
        false
    }
}
