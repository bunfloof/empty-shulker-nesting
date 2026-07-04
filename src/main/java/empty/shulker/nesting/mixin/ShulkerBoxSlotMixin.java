package empty.shulker.nesting.mixin;

import empty.shulker.nesting.ShulkerNestingUtil;
import net.minecraft.world.inventory.ShulkerBoxSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxSlot.class)
public class ShulkerBoxSlotMixin {
	// Vanilla blocks ALL shulker box items in the shulker GUI slots; allow empty ones through.
	@Inject(method = "mayPlace", at = @At("RETURN"), cancellable = true)
	private void emptyShulkerNesting$allowEmptyShulkersInUI(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValueZ()) {
			return;
		}
		if (ShulkerNestingUtil.isShulkerBoxItem(stack) && ShulkerNestingUtil.isEmptyShulker(stack)) {
			cir.setReturnValue(true);
		}
	}
}
