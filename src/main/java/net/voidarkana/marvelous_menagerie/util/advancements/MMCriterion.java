package net.voidarkana.marvelous_menagerie.util.advancements;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID)
public class MMCriterion {

    public static final MMCriteriaTriggers EXCAVATE_FOSSIL_PERFECT = CriteriaTriggers.register(new MMCriteriaTriggers("excavate_fossil_perfect"));
    public static final MMCriteriaTriggers USE_PALEO_TABLE = CriteriaTriggers.register(new MMCriteriaTriggers("use_paleo_table"));
    public static final MMCriteriaTriggers SUCCESSFUL_ALTAR = CriteriaTriggers.register(new MMCriteriaTriggers("successful_altar"));
    public static final MMCriteriaTriggers SUCCESSFUL_SUMMON = CriteriaTriggers.register(new MMCriteriaTriggers("successful_summon"));
    public static final MMCriteriaTriggers FAIL_SUMMON = CriteriaTriggers.register(new MMCriteriaTriggers("fail_summon"));
    public static final MMCriteriaTriggers BEHOLDER_GRAB = CriteriaTriggers.register(new MMCriteriaTriggers("beholder_grab"));
    public static final MMCriteriaTriggers THYLA_ALERT = CriteriaTriggers.register(new MMCriteriaTriggers("thylacine_alert"));

}
