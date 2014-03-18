package net.cazzar.mods.openprinter.blocks

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntity
import net.cazzar.mods.openprinter.blocks.tile.{TileMiningPrinter, MiningPrinter, TilePrinter}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.entity.player.EntityPlayer
import net.cazzar.mods.openprinter.entity.fx.SpiralParticle
import net.minecraft.init.Blocks

class BlockPrinter extends BlockContainer(Material.rock) {
    var icons = Array[IIcon]()

    setBlockName("openprinter")


    override def onBlockActivated(world : World, x : Int, y: Int, z : Int, p_149727_5_ : EntityPlayer, p_149727_6_ : Int, p_149727_7_ : Float, p_149727_8_ : Float, p_149727_9_ : Float): Boolean = {
        new SpiralParticle(world, x, y, z, Blocks.stone).spawn(x + 2, y + 2, z + 2)

        true
    }
    override def createNewTileEntity(var1: World, var2: Int): TileEntity = new TileMiningPrinter

    override def registerBlockIcons(register: IIconRegister): Unit = {
        icons = Array(
            register.registerIcon("openprinter:printer_bottom"),
            register.registerIcon("openprinter:printer_front"),
            register.registerIcon("openprinter:printer_side"),
            register.registerIcon("openprinter:printer_top")
        )
    }
}
