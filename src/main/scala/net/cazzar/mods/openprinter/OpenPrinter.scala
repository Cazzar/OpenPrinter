package net.cazzar.mods.openprinter

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.cazzar.mods.openprinter.blocks.BlockPrinter
import net.cazzar.mods.openprinter.blocks.tile.TilePrinter

@Mod(modid = "OpenPrinter", modLanguage = "scala", dependencies = "required-after:OpenComputers")
object OpenPrinter {

    @EventHandler
    def preInit(e: FMLPreInitializationEvent) {
        GameRegistry.registerBlock(new BlockPrinter(), "printer")
        GameRegistry.registerTileEntity(classOf[TilePrinter], "printer")
    }
}
