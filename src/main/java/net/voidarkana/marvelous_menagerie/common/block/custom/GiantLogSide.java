package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolAction;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.block.custom.flammable.FlammableWoodLogBlock;

import javax.annotation.Nullable;

public class GiantLogSide extends FlammableWoodLogBlock {

    public static final EnumProperty<SideType> TYPE = EnumProperty.create("type", SideType.class);

    public GiantLogSide(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SideType.TOP));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Direction.Axis axis = pContext.getClickedFace().getAxis();

        return this.defaultBlockState().setValue(AXIS, axis).setValue(TYPE, this.getSideType(axis, pContext.getClickLocation(), blockpos));
    }

    public SideType getSideType(Direction.Axis axis, Vec3 clickedLocation, BlockPos thisPos){
        float y = (float) clickedLocation.y() - thisPos.getY();
        float x = (float) clickedLocation.x() - thisPos.getX();
        float z = (float) clickedLocation.z() - thisPos.getZ();
        switch (axis){
            case X:
                if (y > 0.5 && isWithinRange(z, y)){
                    return SideType.TOP;
                }else if (y < 0.5 && isWithinRange(z, y)){
                    return SideType.BOTTOM;
                }else {
                    if (z > 0.5){
                        return SideType.LEFT;
                    }else {
                        return SideType.RIGHT;
                    }
                }
            case Z:
                if (y > 0.5 && isWithinRange(x, y)){
                    return SideType.TOP;
                }else if (y < 0.5 && isWithinRange(x, y)){
                    return SideType.BOTTOM;
                }else {
                    if (x > 0.5){
                        return SideType.RIGHT;
                    }else {
                        return SideType.LEFT;
                    }
                }
            default:
                if (z > 0.5 && isWithinRange(x, z)){
                    return SideType.BOTTOM; //south
                }else if (z < 0.5 && isWithinRange(x, z)){
                    return SideType.TOP; //north
                }else {
                    if (x > 0.5){
                        return SideType.RIGHT;
                    }else {
                        return SideType.LEFT;
                    }
                }
        }
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return rotatePillar(pState, pRot);
    }

    public static BlockState rotatePillar(BlockState pState, Rotation pRotation) {
        switch (pRotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)pState.getValue(AXIS)) {
                    case X:
                        return pState.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                        return pState.setValue(AXIS, Direction.Axis.X);
                    default:
                        return pState;
                }
            case CLOCKWISE_180:
                return pState.setValue(TYPE, pState.getValue(TYPE).getOpposite());
            default:
                return pState;
        }
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        Direction.Axis direction = pState.getValue(AXIS);
        switch (pMirror) {
            case LEFT_RIGHT:
                if (direction == Direction.Axis.Z) {
                    return pState.rotate(Rotation.CLOCKWISE_180);
                }
                break;
            case FRONT_BACK:
                if (direction == Direction.Axis.X) {
                    return pState.rotate(Rotation.CLOCKWISE_180);
                }
        }
        return super.mirror(pState, pMirror);
    }

    public boolean isWithinRange(float x, float y){
        float xRange = Math.abs(y - 0.5f);
        return x >= 0.5-xRange && x <= 0.5+xRange;
    }

    public enum SideType implements StringRepresentable {
        TOP("top"),
        BOTTOM("bottom"),
        LEFT("left"),
        RIGHT("right");

        private final String name;

        private SideType(String pName) {
            this.name = pName;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }

        public SideType getOpposite(){
            switch (this){
                case LEFT -> {
                    return RIGHT;
                }
                case RIGHT -> {
                    return LEFT;
                }
                case TOP -> {
                    return BOTTOM;
                }
                default -> {
                    return TOP;
                }
            }
        }
    }

    public @org.jetbrains.annotations.Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_SIDE.get())){
                return MMBlocks.STRIPPED_GIANT_ARAUCARIOXYLON_LOG_SIDE.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)).setValue(TYPE, state.getValue(TYPE));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, TYPE);
    }
}
