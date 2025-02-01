package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.data.codec.FossilCleaningManager;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.NotNull;


public class PaleontologyTableBlockEntity extends BlockEntityBase {
    public ItemStack stack = ItemStack.EMPTY;

    private static final int BRUSH_COOLDOWN_TICKS = 10;
    private static final int BRUSH_RESET_TICKS = 40;
    private static final int REQUIRED_BRUSHES_TO_BREAK = 10;
    private int brushCount;
    private long coolDownEndsAtTick;
    private long brushCountResetsAtTick;

    public PaleontologyTableBlockEntity(BlockPos pos, BlockState state) {
        super(MMBlockEntities.PALEO_TABLE_ENTITY.get(), pos, state);
    }

    public void onDestroyed(BlockState state, BlockPos pos) {
        if (!stack.isEmpty())
            Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
    }

    public InteractionResult onActivated(BlockState state, BlockPos pos, Player player, InteractionHand hand) {
        if (level != null && !level.isClientSide) {
            ItemStack itemInHand = player.getItemInHand(hand);
            boolean flag = itemInHand.is(MMTags.Items.NATURAL_FOSSILS);
            if (itemInHand.isEmpty() && !stack.isEmpty()) {
                ItemHandlerHelper.giveItemToPlayer(player, stack);
                stack = ItemStack.EMPTY;
                if (!level.isClientSide) sync();
                return InteractionResult.SUCCESS;
            } else if (!itemInHand.isEmpty() && stack.isEmpty() && flag) {
                stack = itemInHand.split(1);
                if (!level.isClientSide) sync();
                return InteractionResult.SUCCESS;
            } else if (!itemInHand.isEmpty() && !stack.isEmpty() && flag) {
                ItemStack oldstack = stack.copy();
                stack = itemInHand.split(1);
                ItemHandlerHelper.giveItemToPlayer(player, oldstack);
                if (!level.isClientSide) sync();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public void checkReset() {
        if (this.level != null) {
            if (this.brushCount != 0 && this.level.getGameTime() >= this.brushCountResetsAtTick) {
                this.brushCount = Math.max(0, this.brushCount - 2);

                this.brushCountResetsAtTick = this.level.getGameTime() + 4;
            }

            if (this.brushCount == 0) {
                this.brushCountResetsAtTick = 0;
                this.coolDownEndsAtTick = 0;
            } else {
                this.level.scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), (int)(this.brushCountResetsAtTick - this.level.getGameTime()));
            }

        }
    }

    public boolean brush(long pStartTick, Player pPlayer, Direction pHitDirection) {
        if (pHitDirection==Direction.UP){
            this.brushCountResetsAtTick = pStartTick + 40L;
            if (pStartTick >= this.coolDownEndsAtTick && this.level instanceof ServerLevel) {
                this.coolDownEndsAtTick = pStartTick + 10L;
                if (++this.brushCount >= 2) {
                    this.brushingCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    private void brushingCompleted() {
        if (this.level != null && this.level.getServer() != null) {
            this.dropContent();
            BlockState blockstate = this.getBlockState();
            this.level.levelEvent(3008, this.getBlockPos(), Block.getId(blockstate));
            this.brushCountResetsAtTick = 0;
            this.coolDownEndsAtTick = 0;
            this.brushCount=0;
        }
    }

    private void dropContent() {
        if (this.level != null && this.level.getServer() != null) {
            boolean hasRecipe = FossilCleaningManager.containsRecipe(this.stack.getItem());
            //System.out.println(hasRecipe);
            ItemStack result = ItemStack.EMPTY;
            if(hasRecipe) {
                // determine result item
                result = FossilCleaningManager.getRandomItemStack(this.stack.getItem(), level);
                System.out.println(result);
                // remove input
                stack = ItemStack.EMPTY;
                if (!level.isClientSide) sync();
            }

            if (!result.isEmpty()) {
                double d0 = (double) EntityType.ITEM.getWidth();
                double d1 = 1.0D - d0;
                double d2 = d0 / 2.0D;
                BlockPos blockpos = this.worldPosition.relative(Direction.UP, 1);
                double d3 = (double)blockpos.getX() + 0.5D * d1 + d2;
                double d4 = (double)blockpos.getY() + 0.5D + (double)(EntityType.ITEM.getHeight() / 2.0F);
                double d5 = (double)blockpos.getZ() + 0.5D * d1 + d2;
                ItemEntity itementity = new ItemEntity(this.level, d3, d4, d5, result);
                itementity.setDeltaMovement(Vec3.ZERO);
                this.level.addFreshEntity(itementity);
                ExperienceOrb.award((ServerLevel) this.level, new Vec3(d3, d4, d5), 1);
                this.stack = ItemStack.EMPTY;
            }

        }
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        stack = ItemStack.of(tag.getCompound("stack"));
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("stack", stack.save(new CompoundTag()));
    }

}
