package net.cazzar.mods.openprinter.blocks

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntity
import net.cazzar.mods.openprinter.blocks.tile.TilePrinter

class BlockPrinter extends BlockContainer(Material.rock) {
    setBlockName("openprinter")

    override def createNewTileEntity(var1: World, var2: Int): TileEntity = new TilePrinter
}
