package net.tigereye.ccspecialorgans.listeners;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tigereye.ccspecialorgans.items.CCSOItems;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;

import java.util.Map;
import java.util.UUID;

public class OrganUpdateListeners {

    private static final UUID rabbitHeartID = UUID.fromString("996015c9-006f-4679-b201-a8978f89cf12");

    public static void register(){
        OrganUpdateCallback.EVENT.register(OrganUpdateListeners::UpdateRabbitHeart);
    }

    private static void UpdateRabbitHeart(PlayerEntity player, Map<Identifier, Float> oldScores, Map<Identifier, Float> newScores) {
        if(oldScores.getOrDefault(CCSOItems.ORGANS_RABBIT_HEART,0f) != newScores.getOrDefault(CCSOItems.ORGANS_RABBIT_HEART,0f)){
            EntityAttributeInstance att = player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            EntityAttributeModifier mod = new EntityAttributeModifier(rabbitHeartID, "ChestCavityRabbitHeartMovementSpeed",
                    newScores.getOrDefault(CCSOItems.ORGANS_RABBIT_HEART, 0f)*.1, EntityAttributeModifier.Operation.MULTIPLY_BASE);
            ReplaceAttributeModifier(att, mod);
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        att.removeModifier(mod);
        att.addPersistentModifier(mod);
    }
}
