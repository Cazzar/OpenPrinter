package net.cazzar.mods.openprinter.blocks

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntity
import net.cazzar.mods.openprinter.blocks.tile.{MiningPrinter, TilePrinter}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon

class BlockPrinter extends BlockContainer(Material.rock) {
    var icons = Array[IIcon]()

    setBlockName("openprinter")

    override def createNewTileEntity(var1: World, var2: Int): TileEntity = new TilePrinter with MiningPrinter

    override def registerBlockIcons(register: IIconRegister): Unit = {
        icons = Array(
            register.registerIcon("openprinter:printer_bottom"),
            register.registerIcon("openprinter:printer_front"),
            register.registerIcon("openprinter:printer_side"),
            register.registerIcon("openprinter:printer_top")
        )
    }
}
