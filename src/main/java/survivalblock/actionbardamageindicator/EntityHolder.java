package survivalblock.actionbardamageindicator;

import net.minecraft.entity.LivingEntity;

public class EntityHolder {
  private LivingEntity entity;

  public EntityHolder() {
  }

  public void setEntity(LivingEntity entity) {

    if (entity == null) {
      setEntityWork(null);
    }

    if (entity != null && entity != this.entity) {
      setEntityWork(entity);
    }
  }

  private void setEntityWork(LivingEntity entity) {
    this.entity = entity;
  }

  public LivingEntity getEntity() {
    return entity;
  }
}
