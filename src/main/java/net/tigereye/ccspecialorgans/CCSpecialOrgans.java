package net.tigereye.ccspecialorgans;

import net.fabricmc.api.ModInitializer;
import net.tigereye.ccspecialorgans.items.CCSOItems;
import net.tigereye.ccspecialorgans.items.CCSOVanillaOrgans;
import net.tigereye.ccspecialorgans.listeners.LootRegister;
import net.tigereye.ccspecialorgans.listeners.OrganTickListeners;
import net.tigereye.ccspecialorgans.listeners.OrganUpdateListeners;

public class CCSpecialOrgans implements ModInitializer {
	public static final String MODID = "ccspecialorgans";

	@Override
	public void onInitialize() {
		CCSOItems.register();
		CCSOVanillaOrgans.init();
		LootRegister.register();
		OrganTickListeners.register();
		OrganUpdateListeners.register();
	}
}
