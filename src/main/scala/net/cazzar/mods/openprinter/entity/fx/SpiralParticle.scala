package net.cazzar.mods.openprinter.entity.fx

import net.minecraft.client.particle.EntityFX
import net.minecraft.world.World
import net.minecraft.block.Block
import net.cazzar.corelib.util.ClientUtil

class SpiralParticle(world: World, x: Int, y: Int, z: Int, block: Block) extends EntityFX(world, x, y, z) {
    particleMaxAge = 200
    motionY = 0.1
    particleScale = 1

    val opp = rand.nextBoolean()


    override def getFXLayer: Int = 1

    override def onUpdate() = {
        super.onUpdate()

//        if (opp) {
//            this.motionX = ((Math.cos(this.particleAge * 0.5) * 0.5) / 5)
//            this.motionZ = ((Math.sin(this.particleAge * 0.5) * 0.5) / 5)
//        }
//        else {
            this.motionX = (Math.sin(this.particleAge * 0.5) * 0.5) / 5
            this.motionZ = (Math.cos(this.particleAge * 0.5) * 0.5) / 5
//        }
    }

    def spawn(x: Double, y: Double, z: Double) {
        val mc = ClientUtil.mc()

        if (mc == null) return
        if (mc.renderViewEntity == null && mc.effectRenderer == null) return

        val setting = mc.gameSettings.particleSetting
        if (setting == 2 || (setting == 1 && mc.theWorld.rand.nextInt(3) == 0))
            return

        if (mc.renderViewEntity == null) return
        val plPosX = mc.renderViewEntity.posX - x
        val plPosY = mc.renderViewEntity.posY - y
        val plPosZ = mc.renderViewEntity.posZ - z

        val maxOffset = 16.0D; //vanilla default

        if (plPosX * plPosX + plPosY * plPosY + plPosZ * plPosZ > maxOffset * maxOffset) {
            //yay, pythagoras!
            return
        }

        mc.effectRenderer.addEffect(this)
    }
}
