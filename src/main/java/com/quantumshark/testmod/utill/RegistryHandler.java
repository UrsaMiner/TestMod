package com.quantumshark.testmod.utill;

import com.quantumshark.testmod.CrudeOilFluidBase;
import com.quantumshark.testmod.TestMod;
import com.quantumshark.testmod.armor.ModArmorMaterial;
import com.quantumshark.testmod.blocks.BlueCrystalBlock;
import com.quantumshark.testmod.blocks.BlueCrystalOre;
import com.quantumshark.testmod.blocks.CrudeOilBlock;
import com.quantumshark.testmod.blocks.GrinderBlock;
import com.quantumshark.testmod.blocks.WoodenShaftBlock;
import com.quantumshark.testmod.capability.IShaftPower;
import com.quantumshark.testmod.blocks.ThermalGlass;
import com.quantumshark.testmod.blocks.FluoriteBlock;
import com.quantumshark.testmod.blocks.FluoriteOre;
import com.quantumshark.testmod.container.GrinderContainer;
import com.quantumshark.testmod.items.BlockItemBase;
import com.quantumshark.testmod.items.BucketItemBase;
import com.quantumshark.testmod.items.ItemBase;
import com.quantumshark.testmod.tileentity.GrinderTileEntity;
import com.quantumshark.testmod.tileentity.WoodenShaftTileEntity;
import com.quantumshark.testmod.tools.ModItemTier;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TestMod.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TestMod.MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, TestMod.MOD_ID);	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(
			ForgeRegistries.CONTAINERS, TestMod.MOD_ID);	
	public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, TestMod.MOD_ID);
	
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		RecipeInit.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	// Tags
	public static final Tag<Fluid> CRUDE_OIL_TAG = new FluidTags.Wrapper(new ResourceLocation(TestMod.MOD_ID,"crude_oil"));
	
	// Fluids
	public static final RegistryObject<CrudeOilFluidBase.Flowing> CRUDE_OIL_FLUID_FLOWING = FLUIDS.register("crude_oil_flowing", () -> new CrudeOilFluidBase.Flowing());
	public static final RegistryObject<CrudeOilFluidBase.Source> CRUDE_OIL_FLUID = FLUIDS.register("crude_oil", () -> new CrudeOilFluidBase.Source());

	// Fluid buckets
	public static final RegistryObject<BucketItem> CRUDE_OIL_BUCKET = ITEMS.register("crude_oil_bucket", () -> new BucketItemBase(() -> CRUDE_OIL_FLUID.get(), new Properties().maxStackSize(1)));
	
	// Fluid blocks
	public static final RegistryObject<CrudeOilBlock> CRUDE_OIL_BLOCK = BLOCKS.register("crude_oil", CrudeOilBlock::new);
	
	// Blocks
	public static final RegistryObject<Block> BLASTBRICKS_BLOCK = BLOCKS.register("blast_bricks", BlueCrystalBlock::new);
	public static final RegistryObject<Block> BLUECRYSTALBLOCK_BLOCK = BLOCKS.register("blue_crystal_block", BlueCrystalBlock::new);
	public static final RegistryObject<Block> BLUECRYSTALORE_BLOCK = BLOCKS.register("blue_crystal_ore", BlueCrystalOre::new);
	public static final RegistryObject<Block> FLUORITEBLOCK_BLOCK = BLOCKS.register("fluorite_block", FluoriteBlock::new);
	public static final RegistryObject<Block> FLUORITEORE_BLOCK = BLOCKS.register("fluorite_ore", FluoriteOre::new);
	public static final RegistryObject<Block> THERMALGLASS_BLOCK = BLOCKS.register("thermal_glass", ThermalGlass::new);
	public static final RegistryObject<Block> MILLSTONEGRIT_BLOCK = BLOCKS.register("millstone_grit", ()->new Block(Block.Properties.create(Material.ROCK)
												.hardnessAndResistance(5f, 9f)
												.sound(SoundType.STONE)
												.harvestLevel(2)
												.harvestTool(ToolType.PICKAXE)));
	
	// Block Items
	public static final RegistryObject<Item> BLASTBRICKS_BLOCK_ITEM = ITEMS.register("blast_bricks", () -> new BlockItemBase(BLASTBRICKS_BLOCK.get()));
	public static final RegistryObject<Item> BLUECRYSTALBLOCK_BLOCK_ITEM = ITEMS.register("blue_crystal_block", () -> new BlockItemBase(BLUECRYSTALBLOCK_BLOCK.get()));
	public static final RegistryObject<Item> BLUECRYSTALORE_BLOCK_ITEM = ITEMS.register("blue_crystal_ore", () -> new BlockItemBase(BLUECRYSTALORE_BLOCK.get()));
	public static final RegistryObject<Item> FLUORITEBLOCK_BLOCK_ITEM = ITEMS.register("fluorite_block", () -> new BlockItemBase(FLUORITEBLOCK_BLOCK.get()));
	public static final RegistryObject<Item> FLUORITEORE_BLOCK_ITEM = ITEMS.register("fluorite_ore", () -> new BlockItemBase(FLUORITEORE_BLOCK.get()));
	public static final RegistryObject<Item> THERMALGLASS_BLOCK_ITEM = ITEMS.register("thermal_glass", () -> new BlockItemBase(THERMALGLASS_BLOCK.get()));
	public static final RegistryObject<Item> MILLSTONEGRIT_BLOCK_ITEM = ITEMS.register("millstone_grit", () -> new BlockItemBase(MILLSTONEGRIT_BLOCK.get()));
	
	
	// Items
	public static final RegistryObject<Item> BLUECRYSTAL = ITEMS.register("blue_crystal", ItemBase::new);
	public static final RegistryObject<Item> FLUORITECRYSTAL = ITEMS.register("fluorite_crystal", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDANDESITE = ITEMS.register("pulverised_andesite", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDDIORITE = ITEMS.register("pulverised_diorite", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDGOLDORE = ITEMS.register("pulverised_gold_ore", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDGRANITE = ITEMS.register("pulverised_granite", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDIRONORE = ITEMS.register("pulverised_iron_ore", ItemBase::new);
	public static final RegistryObject<Item> PULVERISEDCHALCANTHITE = ITEMS.register("pulverised_chalcanthite", ItemBase::new);
	public static final RegistryObject<Item> IRONCHALCANTHITEBLEND = ITEMS.register("iron_chalcanthite_blend", ItemBase::new);
	public static final RegistryObject<Item> ANDESITEDIORITEBLEND = ITEMS.register("andesite_diorite_blend", ItemBase::new);
	public static final RegistryObject<Item> GRANITESANDBLEND = ITEMS.register("granite_sand_blend", ItemBase::new);
	public static final RegistryObject<Item> COPPERINGOT = ITEMS.register("copper_ingot", ItemBase::new);
	public static final RegistryObject<Item> BLASTBRICK = ITEMS.register("blast_brick", ItemBase::new);
	
	
	// Tools
	public static final RegistryObject<SwordItem> BLUECRYSTALSWORD = ITEMS.register("blue_crystal_sword", () -> new SwordItem(ModItemTier.BLUECRYSTAL, 3, -2.4F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<PickaxeItem> BLUECRYSTALPICKAXE = ITEMS.register("blue_crystal_pickaxe", () -> new PickaxeItem(ModItemTier.BLUECRYSTAL, 1, -2.8F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ShovelItem> BLUECRYSTALSHOVEL = ITEMS.register("blue_crystal_shovel", () -> new ShovelItem(ModItemTier.BLUECRYSTAL, 1.5F, -3F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<AxeItem> BLUECRYSTALAXE = ITEMS.register("blue_crystal_axe", () -> new AxeItem(ModItemTier.BLUECRYSTAL, 6.8F, -3.18F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<HoeItem> BLUECRYSTALHOE = ITEMS.register("blue_crystal_hoe", () -> new HoeItem(ModItemTier.BLUECRYSTAL, -1.8F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<SwordItem> FLUORITESWORD = ITEMS.register("fluorite_sword", () -> new SwordItem(ModItemTier.FLUORITE, 3, -2.4F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<PickaxeItem> FLUORITEPICKAXE = ITEMS.register("fluorite_pickaxe", () -> new PickaxeItem(ModItemTier.FLUORITE, 1, -2.8F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ShovelItem> FLUORITESHOVEL = ITEMS.register("fluorite_shovel", () -> new ShovelItem(ModItemTier.FLUORITE, 1.5F, -3F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<AxeItem> FLUORITEAXE = ITEMS.register("fluorite_axe", () -> new AxeItem(ModItemTier.FLUORITE, 6.3F, -3.13F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<HoeItem> FLUORITEHOE = ITEMS.register("fluorite_hoe", () -> new HoeItem(ModItemTier.FLUORITE, -1.3F, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<SwordItem> PHOTONBLADE = ITEMS.register("photon_blade", () -> new SwordItem(ModItemTier.PHOTON, 3, -2.4F, new Item.Properties().group(TestMod.TAB)));

	
	// Armor
	public static final RegistryObject<ArmorItem> BLUECRYSTALHELMET = ITEMS.register("blue_crystal_helmet", () -> new ArmorItem(ModArmorMaterial.BLUECRYSTAL, EquipmentSlotType.HEAD, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> BLUECRYSTALCHESTPLATE = ITEMS.register("blue_crystal_chestplate", () -> new ArmorItem(ModArmorMaterial.BLUECRYSTAL, EquipmentSlotType.CHEST, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> BLUECRYSTALLEGGINGS = ITEMS.register("blue_crystal_leggings", () -> new ArmorItem(ModArmorMaterial.BLUECRYSTAL, EquipmentSlotType.LEGS, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> BLUECRYSTALBOOTS = ITEMS.register("blue_crystal_boots", () -> new ArmorItem(ModArmorMaterial.BLUECRYSTAL, EquipmentSlotType.FEET, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> FLUORITEHELMET = ITEMS.register("fluorite_helmet", () -> new ArmorItem(ModArmorMaterial.FLUORITE, EquipmentSlotType.HEAD, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> FLUORITECHESTPLATE = ITEMS.register("fluorite_chestplate", () -> new ArmorItem(ModArmorMaterial.FLUORITE, EquipmentSlotType.CHEST, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> FLUORITELEGGINGS = ITEMS.register("fluorite_leggings", () -> new ArmorItem(ModArmorMaterial.FLUORITE, EquipmentSlotType.LEGS, new Item.Properties().group(TestMod.TAB)));
	public static final RegistryObject<ArmorItem> FLUORITEBOOTS = ITEMS.register("fluorite_boots", () -> new ArmorItem(ModArmorMaterial.FLUORITE, EquipmentSlotType.FEET, new Item.Properties().group(TestMod.TAB)));
	
	// Machine Blocks
	public static final RegistryObject<Block> GRINDER_BLOCK = BLOCKS.register("grinder", () -> new GrinderBlock(Block.Properties.from(Blocks.FURNACE)));
	public static final RegistryObject<Block> WOODEN_SHAFT_BLOCK = BLOCKS.register("wooden_shaft", ()-> new WoodenShaftBlock(Block.Properties.from(Blocks.OAK_PLANKS).notSolid()));
	
	// Machine Block Items
	public static final RegistryObject<Item> GRINDER_BLOCK_ITEM = ITEMS.register("grinder", () -> new BlockItemBase(GRINDER_BLOCK.get()));
	public static final RegistryObject<Item> WOODEN_SHAFT_BLOCK_ITEM = ITEMS.register("wooden_shaft", () -> new BlockItemBase(WOODEN_SHAFT_BLOCK.get()));

	// tile entity types
	public static final RegistryObject<TileEntityType<GrinderTileEntity>> GRINDER_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("grinder", () -> TileEntityType.Builder
					.create(GrinderTileEntity::new, GRINDER_BLOCK.get()).build(null));
	public static final RegistryObject<TileEntityType<WoodenShaftTileEntity>> WOODEN_SHAFT_TILE_ENTITY = TILE_ENTITY_TYPES
			.register("wooden_shaft", () -> TileEntityType.Builder
					.create(WoodenShaftTileEntity::new).build(null));
	
	// Containers
	public static final RegistryObject<ContainerType<GrinderContainer>> GRINDER_CONTAINER = CONTAINER_TYPES
			.register("grinder", () -> IForgeContainerType.create(GrinderContainer::new));	
	
	// Capabilities
	@CapabilityInject(IShaftPower.class)
	public static final Capability<IShaftPower> CAPABILITY_SHAFT_POWER = null;
}
