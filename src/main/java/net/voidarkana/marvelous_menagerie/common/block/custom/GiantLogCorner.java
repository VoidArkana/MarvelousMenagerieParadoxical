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

public class GiantLogCorner extends FlammableWoodLogBlock {

    public static final EnumProperty<CornerType> TYPE = EnumProperty.create("type", CornerType.class);

    public GiantLogCorner(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Direction.Axis axis = pContext.getClickedFace().getAxis();

        return this.defaultBlockState().setValue(AXIS, axis).setValue(TYPE, this.getSideType(axis, pContext.getClickLocation(), blockpos));
    }

    public CornerType getSideType(Direction.Axis axis, Vec3 clickedLocation, BlockPos thisPos){
        float y = (float) clickedLocation.y() - thisPos.getY();
        float x = (float) clickedLocation.x() - thisPos.getX();
        float z = (float) clickedLocation.z() - thisPos.getZ();
        boolean flagY = y > 0.5;
        boolean flagZ = z > 0.5;
        boolean flagX = x > 0.5;
        switch (axis){
            case X:
                if (flagY && flagZ){
                    return CornerType.TOP_LEFT;
                }else if (!flagY && !flagZ){
                    return CornerType.BOTTOM_RIGHT;
                }else if (flagY){
                    return CornerType.TOP_RIGHT;
                }else {
                    return CornerType.BOTTOM_LEFT;
                }
            case Z:
                if (flagY && flagX){
                    return CornerType.TOP_RIGHT;
                }else if (!flagY && !flagX){
                    return CornerType.BOTTOM_LEFT;
                }else if (flagY){
                    return CornerType.TOP_LEFT;
                }else {
                    return CornerType.BOTTOM_RIGHT;
                }
            default:
                if (flagZ && flagX){
                    return CornerType.BOTTOM_RIGHT;
                }else if (!flagZ && !flagX){
                    return CornerType.TOP_LEFT;
                }else if (flagZ){
                    return CornerType.BOTTOM_LEFT;
                }else {
                    return CornerType.TOP_RIGHT;
                }
        }
    }

    public enum CornerType implements StringRepresentable {
        TOP_LEFT("top_left"),
        BOTTOM_LEFT("bottom_left"),
        TOP_RIGHT("top_right"),
        BOTTOM_RIGHT("bottom_right");

        private final String name;

        private CornerType(String pName) {
            this.name = pName;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }

        public CornerType getClockwise(){
            switch (this){
                case TOP_LEFT -> {
                    return TOP_RIGHT;
                }
                case TOP_RIGHT -> {
                    return BOTTOM_RIGHT;
                }
                case BOTTOM_RIGHT -> {
                    return BOTTOM_LEFT;
                }
                default -> {
                    return TOP_LEFT;
                }
            }
        }

        public CornerType getCounterClockwise(){
            switch (this){
                case TOP_LEFT -> {
                    return BOTTOM_LEFT;
                }
                case TOP_RIGHT -> {
                    return TOP_LEFT;
                }
                case BOTTOM_RIGHT -> {
                    return TOP_RIGHT;
                }
                default -> {
                    return BOTTOM_RIGHT;
                }
            }
        }

        public CornerType getOpposite(){
            switch (this){
                case TOP_LEFT -> {
                    return BOTTOM_RIGHT;
                }
                case TOP_RIGHT -> {
                    return BOTTOM_LEFT;
                }
                case BOTTOM_RIGHT -> {
                    return TOP_LEFT;
                }
                default -> {
                    return TOP_RIGHT;
                }
            }
        }


        public CornerType getHorizontalMirror(){
            switch (this){
                case TOP_LEFT -> {
                    return TOP_RIGHT;
                }
                case TOP_RIGHT -> {
                    return TOP_LEFT;
                }
                case BOTTOM_RIGHT -> {
                    return BOTTOM_LEFT;
                }
                default -> {
                    return BOTTOM_RIGHT;
                }
            }
        }

        public CornerType getVerticalMirror(){
            switch (this){
                case TOP_LEFT -> {
                    return BOTTOM_LEFT;
                }
                case TOP_RIGHT -> {
                    return BOTTOM_RIGHT;
                }
                case BOTTOM_RIGHT -> {
                    return TOP_RIGHT;
                }
                default -> {
                    return TOP_LEFT;
                }
            }
        }
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return rotatePillar(pState, pRot);
    }

    public static BlockState rotatePillar(BlockState pState, Rotation pRotation) {
        return switch (pRotation) {
            case COUNTERCLOCKWISE_90 -> switch (pState.getValue(AXIS)) {
                case X -> pState.setValue(AXIS, Direction.Axis.Z);
                case Z -> pState.setValue(AXIS, Direction.Axis.X);
                default -> pState.setValue(TYPE, pState.getValue(TYPE).getCounterClockwise());
            };
            case CLOCKWISE_90 -> switch (pState.getValue(AXIS)) {
                case X -> pState.setValue(AXIS, Direction.Axis.Z);
                case Z -> pState.setValue(AXIS, Direction.Axis.X);
                default -> pState.setValue(TYPE, pState.getValue(TYPE).getClockwise());
            };
            case CLOCKWISE_180 -> pState.setValue(TYPE, pState.getValue(TYPE).getOpposite());
            default -> pState;
        };
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        Direction.Axis direction = pState.getValue(AXIS);
        switch (pMirror) {
            case LEFT_RIGHT:
                if (direction == Direction.Axis.X) {
                    return pState.setValue(TYPE, pState.getValue(TYPE).getHorizontalMirror());
                }
                if (direction == Direction.Axis.Y) {
                    return pState.setValue(TYPE, pState.getValue(TYPE).getVerticalMirror());
                }
                break;
            case FRONT_BACK:
                if (direction == Direction.Axis.Z) {
                    return pState.setValue(TYPE, pState.getValue(TYPE).getHorizontalMirror());
                }
                if (direction == Direction.Axis.Y) {
                    return pState.setValue(TYPE, pState.getValue(TYPE).getHorizontalMirror());
                }
                break;
        }
        return super.mirror(pState, pMirror);
    }

    public @org.jetbrains.annotations.Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(MMBlocks.GIANT_ARAUCARIOXYLON_LOG_CORNER.get())){
                return MMBlocks.STRIPPED_GIANT_ARAUCARIOXYLON_LOG_CORNER.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)).setValue(TYPE, state.getValue(TYPE));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, TYPE);
    }
}
