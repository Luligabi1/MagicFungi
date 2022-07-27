package me.luligabi.magicfungi.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class UtilisLaserParticle extends AnimatedParticle {

    public UtilisLaserParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.0125F);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale *= 0.75F;
        this.maxAge = 60 + random.nextInt(12);
        this.setTargetColor(0xFF55FF);
        this.setSpriteForAge(spriteProvider);
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            UtilisLaserParticle utilisLaserParticle = new UtilisLaserParticle(clientWorld, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
            utilisLaserParticle.setColor(0xFF55FF);

            return utilisLaserParticle;
        }
    }
}