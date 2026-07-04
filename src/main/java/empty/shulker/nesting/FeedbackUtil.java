package empty.shulker.nesting;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class FeedbackUtil {
	private static final Map<UUID, Long> LAST = new ConcurrentHashMap<>();
	private static final long COOLDOWN_MS = 750L;

	private FeedbackUtil() {
	}

	public static boolean shouldSend(UUID uuid) {
		long now = System.currentTimeMillis();
		Long last = LAST.put(uuid, now);
		return last == null || now - last >= COOLDOWN_MS;
	}
}
