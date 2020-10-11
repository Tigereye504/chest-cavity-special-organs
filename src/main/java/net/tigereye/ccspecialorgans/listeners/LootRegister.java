package net.tigereye.ccspecialorgans.listeners;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.tigereye.ccspecialorgans.items.CCSOItems;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackModifyLoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootRegister {
    public static void register(){
        GenerateEntityLootCallbackAddLoot.EVENT.register(LootRegister::DropSpecialOrgans);
        GenerateEntityLootCallbackModifyLoot.EVENT.register(LootRegister::AnimalHeartsToRabbitHearts);
    }

    private static List<ItemStack> DropSpecialOrgans(LootContextType type, LootContext lootContext){
        List<ItemStack> loot = new ArrayList<>();
        if (lootContext.hasParameter(LootContextParameters.LAST_DAMAGE_PLAYER)) {
            int lootingLevel;
            Random random;
            Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
            if(lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof LivingEntity){
                lootingLevel = EnchantmentHelper.getLooting((LivingEntity) lootContext.get(LootContextParameters.KILLER_ENTITY));
                random = lootContext.getRandom();
            }
            else{
                lootingLevel = 0;
                random = new Random();
            }
            if (entity instanceof CreeperEntity) {
                if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                    loot.add(new ItemStack(CCSOItems.CREEPER_APPENDIX));
                }
            }
            if (entity instanceof EndermanEntity) {
                if(random.nextFloat() < ChestCavity.config.ORGAN_BUNDLE_DROP_RATE + (ChestCavity.config.ORGAN_BUNDLE_LOOTING_BOOST*lootingLevel)) {
                    loot.add(new ItemStack(CCSOItems.ENDER_KIDNEY));
                }
            }
        }
        return loot;
    }

    private static List<ItemStack> AnimalHeartsToRabbitHearts(LootContextType type, LootContext lootContext, List<ItemStack> loot) {
        Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
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
