package net.tigereye.ccspecialorgans.listeners;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.ccspecialorgans.items.CCSOItems;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.modifydropsapi.api.LivingEntityDropLootCallback_AddDrops;
import net.tigereye.modifydropsapi.api.LivingEntityDropLootCallback_ModifyDrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootRegister {
    public static void register(){
        LivingEntityDropLootCallback_AddDrops.EVENT.register(LootRegister::DropSpecialOrgans);
        LivingEntityDropLootCallback_ModifyDrops.EVENT.register(LootRegister::AnimalHeartsToRabbitHearts);
    }

    private static List<ItemStack> DropSpecialOrgans(LivingEntity entity, DamageSource source, boolean causedByPlayer) {
        List<ItemStack> loot = new ArrayList<>();
        if (source.getAttacker() instanceof PlayerEntity) {
            Random random = new Random();
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            if (entity instanceof CreeperEntity) {
                if (random.nextFloat() < .025 + (.01f * EnchantmentHelper.getLooting(player))) {
                    loot.add(new ItemStack(CCSOItems.CREEPER_APPENDIX));
                }
            }
            if (entity instanceof EndermanEntity) {
                if (random.nextFloat() < .025 + (.01f * EnchantmentHelper.getLooting(player))) {
                    loot.add(new ItemStack(CCSOItems.ENDER_KIDNEY));
                }
            }
        }
        return loot;
    }

    private static List<ItemStack> AnimalHeartsToRabbitHearts(LivingEntity entity, DamageSource source, boolean causedByPlayer, List<ItemStack> loot) {
        if(entity instanceof RabbitEntity) {
            for (int i = 0; i < loot.size();i++){
                if(loot.get(i).getItem() == CCItems.ANIMAL_HEART){
                    ItemStack lootEntry = new ItemStack(CCSOItems.RABBIT_HEART);
                    loot.set(i,lootEntry);
                }
            }
        }
        return loot;
    }
}
