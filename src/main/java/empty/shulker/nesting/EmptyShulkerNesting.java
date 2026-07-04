package empty.shulker.nesting;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyShulkerNesting implements ModInitializer {
	public static final String MOD_ID = "empty-shulker-nesting";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Empty Shulker Nesting loaded (26.2).");
	}
}
