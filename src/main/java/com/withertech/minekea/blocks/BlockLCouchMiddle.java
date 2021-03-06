package com.withertech.minekea.blocks;

import java.util.List;

import com.withertech.minekea.Minekea;
import com.withertech.minekea.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLCouchMiddle extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	private static final AxisAlignedBB BOUNDS = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (0 * 0.0625), (16 * 0.0625), (8 * 0.0625), (16 * 0.0625));
	private static final AxisAlignedBB LEG_1 = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (0 * 0.0625), (2 * 0.0625), (2 * 0.0625), (2 * 0.0625));
	private static final AxisAlignedBB LEG_2 = new AxisAlignedBB((14 * 0.0625), (0 * 0.0625), (0 * 0.0625), (16 * 0.0625), (2 * 0.0625), (2 * 0.0625));
	private static final AxisAlignedBB LEG_3 = new AxisAlignedBB((14 * 0.0625), (0 * 0.0625), (14 * 0.0625), (16 * 0.0625), (2 * 0.0625), (16 * 0.0625));
	private static final AxisAlignedBB LEG_4 = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (14 * 0.0625), (2 * 0.0625), (2 * 0.0625), (16 * 0.0625));
	private static final AxisAlignedBB SEAT = new AxisAlignedBB((0 * 0.0625), (2 * 0.0625), (0 * 0.0625), (16 * 0.0625), (8 * 0.0625), (16 * 0.0625));
	
	public BlockLCouchMiddle()
	{
		super(Material.CLOTH);
		setSoundType(SoundType.CLOTH);
		setHardness(0.8F);
		setUnlocalizedName(Minekea.MODID + ".blocklcouchwhitemiddle");
		this.setCreativeTab(CommonProxy.MinekeaDenTab);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState blockState)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState blockState)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return BOUNDS;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
	{
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, LEG_1);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, LEG_2);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, LEG_3);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, LEG_4);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, SEAT);
	}
}
