package net.cazzar.mods.openprinter.blocks.tile

import li.cil.oc.api.prefab.TileEntityEnvironment
import li.cil.oc.api.Network
import li.cil.oc.api.network.{Callback, Arguments, Context, Visibility}
import net.minecraft.command.CommandGive
import net.minecraft.block.Block

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

        worldObj.setBlock(xCoord + x, yCoord + y, zCoord + z, block)

        Array(Boolean.box(false))
    }
}
