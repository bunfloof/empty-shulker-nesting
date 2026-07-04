package empty.shulker.nesting.mixin;

import empty.shulker.nesting.ShulkerNestingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlockEntity.class)
public class ShulkerBoxBlockEntityMixin {
	// Vanilla blocks ALL shulker box items here (hoppers etc.); allow empty ones through.
	@Inject(method = "canPlaceItemThroughFace", at = @At("RETURN"), cancellable = true)
	private void emptyShulkerNesting$allowEmptyShulkersOnly(int slot, ItemStack stack, Direction dir, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ()) {
			return;
		}
		if (ShulkerNestingUtil.isShulkerBoxItem(stack) && ShulkerNestingUtil.isEmptyShulker(stack)) {
			cir.setReturnValue(true);
		}
	}
}
