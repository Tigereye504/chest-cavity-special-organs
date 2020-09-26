package net.tigereye.ccspecialorgans.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.ccspecialorgans.interfaces.PlayerEntityInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityInterface {
	int CCSOExplosionCooldown = 0;
	public int getCCSOExplosionCooldown(){return CCSOExplosionCooldown;}
	public void setCCSOExplosionCooldown(int explosionCooldown){this.CCSOExplosionCooldown = explosionCooldown;}

}
