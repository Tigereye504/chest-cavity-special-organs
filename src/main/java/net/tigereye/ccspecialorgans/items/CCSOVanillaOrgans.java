package net.tigereye.ccspecialorgans.items;

import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.tigereye.chestcavity.items.VanillaOrgans;

import java.util.HashMap;
import java.util.Map;

public class CCSOVanillaOrgans {
    public static void init(){
        Map<Identifier,Float> gunpowder = new HashMap<>();
        gunpowder.put(CCSOItems.ORGANS_EXPLOSION_YIELD,3f);
        Map<Identifier,Float> tnt = new HashMap<>();
        tnt.put(CCSOItems.ORGANS_EXPLOSION_YIELD,16f);
        VanillaOrgans.map.put(Items.GUNPOWDER,gunpowder);
        VanillaOrgans.map.put(Items.TNT,tnt);

    }
}
