package software.bernie.techarium.block.arboretum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import software.bernie.techarium.block.base.MachineBlock;
import software.bernie.techarium.tile.arboretum.ArboretumTile;
import software.bernie.techarium.util.BlockRegion;

import javax.annotation.Nonnull;

public class ArboretumMaster extends MachineBlock<ArboretumTile> {

    public ArboretumMaster() {
        super(Properties.of(Material.METAL).strength(3.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).noOcclusion().requiresCorrectToolForDrops(), ArboretumTile::new);
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tile = worldIn.getBlockEntity(pos);
        if(tile instanceof ArboretumTile) {
            ((ArboretumTile) tile).setOpening(true);
        }
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof ArboretumTile) {
            ArboretumTile tile = (ArboretumTile) world.getBlockEntity(pos);
            return Math.max(tile.getFluidInventory().getFluid().getFluid().defaultFluidState().createLegacyBlock().getLightValue(world, pos), super.getLightValue(state, world, pos));
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);
    }

    @Override
    public BlockRegion getBlockSize() {
        return new BlockRegion(1,2,1);
    }
}
