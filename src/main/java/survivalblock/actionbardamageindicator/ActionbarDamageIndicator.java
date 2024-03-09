package survivalblock.actionbardamageindicator;

import com.google.gson.GsonBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import survivalblock.actionbardamageindicator.config.ActionbarDamageIndicatorConfig;
import survivalblock.actionbardamageindicator.util.RayTrace;

public class ActionbarDamageIndicator implements ModInitializer {
  public static final String MODID = "actionbardamageindicator";
  public static EntityHolder ENTITY_HOLDER = new EntityHolder();
  public static RayTrace RAYTRACE = new RayTrace();
  public static boolean IS_HOLDING_WEAPON = false;


  @Override
  public void onInitialize() {
    AutoConfig.register(ActionbarDamageIndicatorConfig.class, GsonConfigSerializer::new);
  }
}
