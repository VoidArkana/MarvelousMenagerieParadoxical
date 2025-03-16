package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.FossilBlockEntity;

public class FossilBlock extends BaseEntityBlock {

    private String lootTable;

    public FossilBlock(Properties pProperties, String pLootTable) {
        super(pProperties);
        this.lootTable = pLootTable;
    }

    public FossilBlock(Properties pProperties){
        this(pProperties, "");
    }

    public String getFossilLootTable(){
        return this.lootTable;
    }

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new FossilBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    //private static final Component CONTAINER_TITLE = Component.translatable("marvelous_menagerie.container.fossil_minigame");

//    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
//        return new SimpleMenuProvider((i, inv, player) -> {
//            return new FossilMinigameMenu(i, inv, ContainerLevelAccess.create(level, pos));
//        }, CONTAINER_TITLE);
//    }

}
