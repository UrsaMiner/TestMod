package com.quantumshark.testmod.items;

import com.quantumshark.testmod.TestMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

	public ItemBase() {
		super(new Item.Properties().group(TestMod.TAB));
	}
}
