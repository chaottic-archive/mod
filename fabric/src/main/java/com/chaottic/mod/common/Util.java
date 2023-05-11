package com.chaottic.mod.common;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Util {

    private Util() {}

    public static VoxelShape rotateShape(VoxelShape shape, Quaternionf quaternionf) {
        var matrix4f = new Matrix4f();

        var outMin = new Vector3f();
        var outMax = new Vector3f();

        var list = shape.toAabbs().stream().map(aabb -> {

            matrix4f
                    .identity()
                    .translate(0.5F, 0.0F, 0.5F)
                    .rotate(quaternionf)
                    .translate(-0.5F, 0.0F, -0.5F);

            var min = new Vector3f((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ);
            var max = new Vector3f((float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);

            matrix4f.transformAab(min, max, outMin, outMax);

            return Shapes.create(new AABB(new Vec3(outMin), new Vec3(outMax)));
        }).toList();

        var joined = Shapes.empty();

        for (var rotated : list) {
            joined = Shapes.join(joined, rotated, BooleanOp.OR);
        }

        return joined;
    }
}
