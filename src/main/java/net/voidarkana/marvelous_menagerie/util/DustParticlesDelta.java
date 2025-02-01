package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public record DustParticlesDelta(double xd, double yd, double zd) {
    private static final double ALONG_SIDE_DELTA = 1.0D;
    private static final double OUT_FROM_SIDE_DELTA = 0.1D;

    public static DustParticlesDelta fromDirection(Vec3 pPos, Direction pDirection) {
        double d0 = 0.0D;
        DustParticlesDelta brushitem$dustparticlesdelta;
        switch (pDirection) {
            case DOWN:
            case UP:
                brushitem$dustparticlesdelta = new DustParticlesDelta(pPos.z(), 0.0D, -pPos.x());
                break;
            case NORTH:
                brushitem$dustparticlesdelta = new DustParticlesDelta(1.0D, 0.0D, -0.1D);
                break;
            case SOUTH:
                brushitem$dustparticlesdelta = new DustParticlesDelta(-1.0D, 0.0D, 0.1D);
                break;
            case WEST:
                brushitem$dustparticlesdelta = new DustParticlesDelta(-0.1D, 0.0D, -1.0D);
                break;
            case EAST:
                brushitem$dustparticlesdelta = new DustParticlesDelta(0.1D, 0.0D, 1.0D);
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return brushitem$dustparticlesdelta;
    }
}
