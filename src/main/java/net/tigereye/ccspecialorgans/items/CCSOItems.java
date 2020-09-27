package net.tigereye.ccspecialorgans.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.ccspecialorgans.CCSpecialOrgans;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.items.CCItems;
import net.tigereye.chestcavity.items.OrganBase;


public class CCSOItems {

    public static final Identifier ORGANS_CREEPINESS = new Identifier(CCSpecialOrgans.MODID, "creepiness");
    public static final Identifier ORGANS_EXPLOSION_YIELD = new Identifier(CCSpecialOrgans.MODID, "explosion_yield");
    public static final Identifier ORGANS_RABBIT_HEART = new Identifier(CCSpecialOrgans.MODID,"rabbit_heart");
    public static final Identifier ORGANS_ENDERNESS = new Identifier(CCSpecialOrgans.MODID,"enderness");


    public static final Item CREEPER_APPENDIX = new CreeperAppendix()
            .setOrganQuality(CCItems.ORGANS_APPENDIX,.75f)
            .setOrganQuality(ORGANS_CREEPINESS,1);
    public static final Item RABBIT_HEART = new OrganBase()
            .setOrganQuality(CCItems.ORGANS_HEART, .25f)
            .setOrganQuality(ORGANS_RABBIT_HEART,1);
    public static final Item ENDER_KIDNEY = new EnderKidney()
            .setOrganQuality(CCItems.ORGANS_KIDNEY, .75f)
            .setOrganQuality(ORGANS_ENDERNESS, 1);

    public static void register(){
        registerItem("creeper_appendix", CREEPER_APPENDIX);
        registerItem("rabbit_heart", RABBIT_HEART);
        registerItem("ender_kidney", ENDER_KIDNEY);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, CCSpecialOrgans.MODID + ":" + name, item);
    }
}
