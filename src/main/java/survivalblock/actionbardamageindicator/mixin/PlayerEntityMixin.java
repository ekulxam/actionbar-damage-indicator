package survivalblock.actionbardamageindicator.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import survivalblock.actionbardamageindicator.ActionbarDamageIndicator;
import survivalblock.actionbardamageindicator.IndicatorStates;
import survivalblock.actionbardamageindicator.config.ActionbarDamageIndicatorConfig;
import survivalblock.actionbardamageindicator.util.HoldingWeaponUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

  protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
    super(type, world);
  }

  @Inject(method = "tick()V", at = @At("HEAD"))
  private void tick(CallbackInfo info) {
    if (!this.world.isClient) {
      return;
    }
    ActionbarDamageIndicator.ENTITY_HOLDER.setEntity(ActionbarDamageIndicator.RAYTRACE.getEntityInCrosshair(0, ActionbarDamageIndicatorConfig.tracking_distance));
    IndicatorStates.tick();
    HoldingWeaponUpdater.update();
  }

}
