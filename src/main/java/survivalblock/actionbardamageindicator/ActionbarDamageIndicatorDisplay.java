package survivalblock.actionbardamageindicator;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import survivalblock.actionbardamageindicator.config.ActionbarDamageIndicatorConfig;

public class ActionbarDamageIndicatorDisplay {

  public final LivingEntity entity;
  public float health;
  public int lastDmg;
  public int lastDmgCumulative;
  public float lastHealth;
  public float lastDmgDelay;

  private static final float HEALTH_INDICATOR_DELAY = 10;

  public ActionbarDamageIndicatorDisplay(LivingEntity entity) {
    this.entity = entity;
  }

  public void tick() {
    health = Math.min(entity.getHealth(), entity.getMaxHealth());

    if (lastHealth < 0.1) {
      reset();

    } else if (lastHealth != health) {
      handleHealthChange();

    } else if (lastDmgDelay == 0.0F) {
      reset();
    }
  }

  private void reset() {
    lastHealth = health;
    lastDmg = 0;
    lastDmgCumulative = 0;
  }
  private void handleHealthChange() {
    lastDmg = MathHelper.ceil(lastHealth) - MathHelper.ceil(health);
    lastDmgCumulative += lastDmg;

    lastDmgDelay = HEALTH_INDICATOR_DELAY * 2;
    lastHealth = health;
    Entity entityBeingLookedAt = ActionbarDamageIndicator.ENTITY_HOLDER.getEntity();
    if (entity != null && entityBeingLookedAt != null){
      if (entity == entityBeingLookedAt && MinecraftClient.getInstance() != null && MinecraftClient.getInstance().player != null){
        Text actionbardamageindicator = Text.literal(String.valueOf(entity.getName().getString())).append(" sustained ").append(String.valueOf(lastDmg)).append(" damage.");
        Text healthIndicator = Text.literal(String.valueOf(entity.getName().getString())).append( " has ").append(String.valueOf(Math.round(entity.getHealth()))).append("/").append(String.valueOf(entity.getMaxHealth())).append(" health remaining.");
        if(ActionbarDamageIndicatorConfig.show_health){
          MinecraftClient.getInstance().player.sendMessage(Text.literal(actionbardamageindicator.getString()).append(" ").append(healthIndicator.getString()), true);
        } else {
          MinecraftClient.getInstance().player.sendMessage(actionbardamageindicator, true);
        }
      }
    }
  }

}
