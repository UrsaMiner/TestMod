package com.quantumshark.testmod.tileentity;

import com.quantumshark.testmod.TestMod;
import com.quantumshark.testmod.blocks.GrinderBlock;
import com.quantumshark.testmod.capability.ShaftPowerDefImpl;
import com.quantumshark.testmod.container.GrinderContainer;
import com.quantumshark.testmod.recipes.MachineRecipeBase;
import com.quantumshark.testmod.recipes.RecipeTemplateOneInOneOut;
import com.quantumshark.testmod.utill.RecipeInit;
import com.quantumshark.testmod.utill.RegistryHandler;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

public class GrinderTileEntity extends MachineTileEntityBase<RecipeTemplateOneInOneOut> {
	private ShaftPowerDefImpl shaft;

	public int currentSmeltTime;
	public final int maxSmeltTime = 100;

	public GrinderTileEntity() {
		super(RegistryHandler.GRINDER_TILE_ENTITY.get());
		shaft = new ShaftPowerDefImpl();
	}
	
	@Override
	public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
		return new GrinderContainer(windowID, playerInv, this);
	}
	
	@Override
	public void tick() {
		boolean dirty = false;
		boolean isRunning = false;

		if (this.world != null && !this.world.isRemote) {
			if (this.world.isBlockPowered(this.getPos())) {
				// reset progress if you remove the input item. 
				MachineRecipeBase<RecipeTemplateOneInOneOut> recipe = this.findMatchingRecipe();
				if(recipe == null)
				{
					this.currentSmeltTime = 0;
				}
				else
				{
					if(processRecipe(recipe, true))
					{
						if (this.currentSmeltTime < this.maxSmeltTime) {
							isRunning = true;
							this.currentSmeltTime++;
						} else {
							this.currentSmeltTime = 0;
							
							processRecipe(recipe, false);
							
							dirty = true;
						}
					}
				}
			}
			BlockState oldBlockState = getBlockState();
			boolean wasRunning = oldBlockState.get(GrinderBlock.LIT);
			if(isRunning != wasRunning)
			{
				this.world.setBlockState(this.getPos(),
					this.getBlockState().with(GrinderBlock.LIT, isRunning));
				dirty = true;
			}
		}

		if (dirty) {
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
					Constants.BlockFlags.BLOCK_UPDATE);
		}
	}
	
	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + TestMod.MOD_ID + ".grinder");
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);

		this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt("CurrentSmeltTime", this.currentSmeltTime);

		return compound;
	}
	
	@Override
	protected IRecipeType<MachineRecipeBase<RecipeTemplateOneInOneOut>> getRecipeType() {
		 return RecipeInit.GRINDER_RECIPE_TYPE;
	}
	
	// the recipe template (combination of inputs and secondary outputs if any)
	@Override
	public RecipeTemplateOneInOneOut getRecipeTemplate() {
		return RecipeTemplateOneInOneOut.INST;
	}	

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == RegistryHandler.CAPABILITY_SHAFT_POWER)
		{
			if(side == Direction.UP)
			{
				return RegistryHandler.CAPABILITY_SHAFT_POWER.orEmpty(cap, LazyOptional.of(() -> this.shaft));
			}
			else return null;
		}
		return super.getCapability(cap, side);
	}
}
