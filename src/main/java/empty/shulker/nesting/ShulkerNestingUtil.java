package empty.shulker.nesting;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public final class ShulkerNestingUtil {
	private ShulkerNestingUtil() {
	}

	/**
	 * Matches all vanilla shulker box items (undyed + 16 colors), same set the
	 * original mod enumerated explicitly.
	 */
	public static boolean isShulkerBoxItem(ItemStack stack) {
		return Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock;
	}

	/**
	 * A shulker box item is "empty" if it has no container component, or if every
	 * stack inside the container component is empty.
	 */
	public static boolean isEmptyShulker(ItemStack shulkerStack) {
		ItemContainerContents container = shulkerStack.get(DataComponents.CONTAINER);
		if (container == null) {
			return true;
		}
		return container.allItemsCopyStream().allMatch(ItemStack::isEmpty);
	}
}
