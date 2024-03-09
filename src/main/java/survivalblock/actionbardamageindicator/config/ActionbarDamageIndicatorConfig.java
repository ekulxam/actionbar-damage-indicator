package survivalblock.actionbardamageindicator.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import survivalblock.actionbardamageindicator.ActionbarDamageIndicator;

@Config(name = ActionbarDamageIndicator.MODID)
public class ActionbarDamageIndicatorConfig implements ConfigData {
    public static int tracking_distance = 60;
    public static boolean show_health = false;
}
