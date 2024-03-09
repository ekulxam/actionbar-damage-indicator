package survivalblock.actionbardamageindicator.mixin;

import com.google.gson.GsonBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import survivalblock.actionbardamageindicator.ActionbarDamageIndicator;
import survivalblock.actionbardamageindicator.IndicatorStates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import survivalblock.actionbardamageindicator.config.ActionbarDamageIndicatorConfig;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

  @Inject(method = "renderEntity", at = @At(value = "RETURN"))
  private void renderEntity(Entity entity, double x, double y, double z, float g,
      MatrixStack matrix, VertexConsumerProvider v, CallbackInfo info) {
    if (entity instanceof LivingEntity) {
      MinecraftClient client = MinecraftClient.getInstance();
      if (entity.distanceTo(client.getCameraEntity()) > ActionbarDamageIndicatorConfig.tracking_distance) {
        return;
      }
      IndicatorStates.getState((LivingEntity) entity);
    }
  }

}
