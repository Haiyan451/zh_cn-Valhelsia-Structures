package com.stal111.valhelsia_structures.common.block.entity;

import com.stal111.valhelsia_structures.core.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nonnull;

/**
 * Explorers Tent Block Entity <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.common.block.entity.ExplorersTentBlockEntity
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2020-12-10
 */
public class ExplorersTentBlockEntity extends BlockEntity implements DyeableBlockEntity {

    private int color = 10511680;

    public ExplorersTentBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TENT.get(), pos, state);
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Color")) {
            this.setColor(tag.getInt("Color"));
        }
    }

    @Nonnull
    @Override
    public CompoundTag save(@Nonnull CompoundTag tag) {
        tag.putInt("Color", this.getColor());
        return super.save(tag);
    }
}