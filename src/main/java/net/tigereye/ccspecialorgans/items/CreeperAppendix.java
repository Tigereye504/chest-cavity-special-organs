package net.tigereye.ccspecialorgans.items;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.explosion.Explosion;
import net.tigereye.chestcavity.items.OrganBase;

import java.util.Collection;
import java.util.Iterator;

public class CreeperAppendix extends OrganBase {
    public static void explode(PlayerEntity player, float explosionYield) {
        if (!player.world.isClient) {
            Explosion.DestructionType destructionType = player.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            player.world.createExplosion(null, player.getX(), player.getY(), player.getZ(), (float)Math.sqrt(explosionYield), destructionType);
            spawnEffectsCloud(player);
        }

    }

    public static void spawnEffectsCloud(PlayerEntity player) {
        Collection<StatusEffectInstance> collection = player.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(player.world, player.getX(), player.getY(), player.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            Iterator var3 = collection.iterator();

            while(var3.hasNext()) {
                StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var3.next();
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }

            player.world.spawnEntity(areaEffectCloudEntity);
        }

    }
}
