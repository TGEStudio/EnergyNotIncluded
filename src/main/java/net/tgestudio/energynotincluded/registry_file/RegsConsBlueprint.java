package net.tgestudio.energynotincluded.registry_file;

import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.tgestudio.energynotincluded.block.construction.construct_base.ConsBlock;
import net.tgestudio.energynotincluded.block.construction.construct_base.ConsBlockBlueprint;
import net.tgestudio.energynotincluded.block.construction.construct_base.MasterBlock;

import java.util.Arrays;
import java.util.HashMap;

import static net.minecraft.world.level.block.Blocks.*;

public class RegsConsBlueprint {
    private static final Vec3i masterPos = new Vec3i(0, 0, 1);
    private static final HashMap<String, ConsBlockBlueprint> ConsBlueprintHashmap;

    static {
        ConsBlueprintHashmap = new HashMap<>();
        ConsBlueprintHashmap.put("TEST", new ConsBlockBlueprint(Arrays.asList
                (
                        new MasterBlock(new Vec3i(0, 1, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), OAK_FENCE.defaultBlockState(), new Vec3i(1, -1, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), REDSTONE_BLOCK.defaultBlockState(), new Vec3i(0, -1, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), OAK_FENCE.defaultBlockState(), new Vec3i(-1, -1, 1)),

                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BLOCK.defaultBlockState(), new Vec3i(-1, 0, 0)),

                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BARS.defaultBlockState(), new Vec3i(1, 0, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BLOCK.defaultBlockState(), new Vec3i(0, 0, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BLOCK.defaultBlockState(), new Vec3i(-1, 0, 1)),

                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BARS.defaultBlockState(), new Vec3i(1, 1, 1)),
                        new ConsBlock(new Vec3i(0, 1, 1), IRON_BARS.defaultBlockState(), new Vec3i(-1, 1, 1))
                )));
        ConsBlueprintHashmap.put("TEST2", new ConsBlockBlueprint(Arrays.asList
                (
                        new MasterBlock(new Vec3i(0, 0, 1)),
                        new ConsBlock(masterPos, GOLD_BLOCK.defaultBlockState(), new Vec3i(0, 1, 0)),
                        new ConsBlock(masterPos, EMERALD_BLOCK.defaultBlockState(), new Vec3i(0, 2, 0)),
                        new ConsBlock(masterPos, NETHERITE_BLOCK.defaultBlockState(), new Vec3i(0, 3, 0))
                )));
    }

    public static ConsBlockBlueprint get(String name) {
        return ConsBlueprintHashmap.get(name);
    }

    public static Vec3i rotate(BlockState blockChecking, Vec3i pPos) {
        return switch (blockChecking.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case EAST -> new Vec3i(-pPos.getZ(), pPos.getY(), pPos.getX());
            case WEST -> new Vec3i(pPos.getZ(), pPos.getY(), -pPos.getX());
            case SOUTH -> new Vec3i(-pPos.getX(), pPos.getY(), -pPos.getZ());
            case NORTH -> pPos;
            default -> pPos;
        };
    }
}
