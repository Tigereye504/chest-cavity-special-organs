package net.tigereye.ccspecialorgans.listeners;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.ccspecialorgans.interfaces.PlayerEntityInterface;
import net.tigereye.ccspecialorgans.items.CCSOItems;
import net.tigereye.ccspecialorgans.items.CreeperAppendix;
import net.tigereye.ccspecialorgans.items.EnderKidney;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.ChestCavityOrgan;
import net.tigereye.chestcavity.items.VanillaOrgans;
import net.tigereye.chestcavity.listeners.ChestCavityListener;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

public class OrganTickListeners {
    public static final int TICKS_BETWEEN_EXPLOSIONS = 20;

    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickCreepiness);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickEnderness);
    }

    public static void TickCreepiness(PlayerEntity player, ChestCavityListener chestCavity){
        if(chestCavity.getOrganScore(CCSOItems.ORGANS_CREEPINESS) == 0){
            return;
        }
        if(((PlayerEntityInterface)player).getCCSOExplosionCooldown() > 0){
            ((PlayerEntityInterface)player).setCCSOExplosionCooldown(((PlayerEntityInterface)player).getCCSOExplosionCooldown()-1);
        }
        else if((player.getPose() == EntityPose.CROUCHING || player.isOnFire())
                && chestCavity.getOrganScore(CCSOItems.ORGANS_CREEPINESS) >= 1){
            ((PlayerEntityInterface)player).setCCSOExplosionCooldown(TICKS_BETWEEN_EXPLOSIONS);
            float explosion_yield = chestCavity.getOrganScore(CCSOItems.ORGANS_EXPLOSION_YIELD);
            destroyOrgansWithKey(player,CCSOItems.ORGANS_EXPLOSION_YIELD);
            CreeperAppendix.explode(player, explosion_yield);
        }
    }

    public static void TickEnderness(PlayerEntity player, ChestCavityListener chestCavity){
        if(chestCavity.getOrganScore(CCSOItems.ORGANS_ENDERNESS) == 0){
            return;
        }
        if(player.isTouchingWaterOrRain()){
            EnderKidney.teleportRandomly(player);
        }
    }

    public static void destroyOrgansWithKey(PlayerEntity player, Identifier organ){
        EnderChestInventory inv = ChestCavity.INVENTORYCOMPONENT.get(player).getInventory();
        for (int i = 0; i < inv.size(); i++)
        {
            ItemStack slot = inv.getStack(i);
            if (slot != null)
            {
                Item slotitem = slot.getItem();
                if(slotitem instanceof ChestCavityOrgan){
                    if(((ChestCavityOrgan) slotitem).getOrganQualityMap().containsKey(organ)){
                        inv.removeStack(i);
                    }
                }
                else if(VanillaOrgans.map.containsKey(slotitem)){
                    if(VanillaOrgans.map.get(slotitem).containsKey(organ)){
                        inv.removeStack(i);
                    }
                }
            }
        }
        inv.markDirty();
    }
}

