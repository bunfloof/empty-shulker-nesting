package empty.shulker.nesting.mixin;

import empty.shulker.nesting.FeedbackUtil;
import empty.shulker.nesting.ShulkerNestingUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public class ScreenHandlerFeedbackMixin {
	@Inject(method = "clicked", at = @At("HEAD"))
	private void emptyShulkerNesting$feedback(int slotIndex, int button, ContainerInput actionType, Player player, CallbackInfo ci) {
		if (!((Object) this instanceof ShulkerBoxMenu)) {
			return;
		}
		AbstractContainerMenu self = (AbstractContainerMenu) (Object) this;
		if (slotIndex < 0 || slotIndex >= self.slots.size()) {
			return;
		}
		Slot clicked = self.slots.get(slotIndex);
		if (actionType == ContainerInput.PICKUP) {
			ItemStack cursor = self.getCarried();
			if (isBlockedShulker(cursor)) {
				send(player);
			}
			return;
		}
		if (actionType == ContainerInput.QUICK_MOVE && isBlockedShulker(clicked.getItem())) {
			send(player);
		}
	}

	private static boolean isBlockedShulker(ItemStack stack) {
		return ShulkerNestingUtil.isShulkerBoxItem(stack) && !ShulkerNestingUtil.isEmptyShulker(stack);
	}

	private static void send(Player player) {
		if (!FeedbackUtil.shouldSend(player.getUUID())) {
			return;
		}
		player.sendOverlayMessage(Component.literal("Non-empty shulker boxes can't be put inside other shulker boxes."));
	}
}
