package survivalblock.actionbardamageindicator;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class IndicatorStates {

  private static final Map<Integer, ActionbarDamageIndicatorDisplay> STATES = new HashMap<>();
  private static int tickCount = 0;

  public static ActionbarDamageIndicatorDisplay getState(LivingEntity entity) {
    int id = entity.getId();
    ActionbarDamageIndicatorDisplay state = STATES.get(id);
    if (state == null) {
      state = new ActionbarDamageIndicatorDisplay(entity);
      STATES.put(id, state);
    }
    return state;
  }

  public static void tick() {
    for (ActionbarDamageIndicatorDisplay state : STATES.values()) {
      state.tick();
    }

    if (tickCount % 200 == 0) {
      cleanCache();
    }

    tickCount++;
  }

  private static void cleanCache() {
    STATES.entrySet().removeIf(IndicatorStates::stateExpired);
  }

  private static boolean stateExpired(Map.Entry<Integer, ActionbarDamageIndicatorDisplay> entry) {
    if (entry.getValue() == null) {
      return true;
    }

    MinecraftClient minecraft = MinecraftClient.getInstance();
    Entity entity = minecraft.world.getEntityById(entry.getKey());

    if (!(entity instanceof LivingEntity)) {
      return true;
    }

    if (!minecraft.world.isChunkLoaded(entity.getBlockPos())) {
      return true;
    }

    return !entity.isAlive();
  }

}
