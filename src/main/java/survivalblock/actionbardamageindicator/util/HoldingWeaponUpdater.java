package survivalblock.actionbardamageindicator.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.SwordItem;
import survivalblock.actionbardamageindicator.ActionbarDamageIndicator;

public class HoldingWeaponUpdater {
  public static void update() {
    MinecraftClient minecraft = MinecraftClient.getInstance();
    PlayerEntity player = minecraft.player;
    if (player == null) {
      ActionbarDamageIndicator.IS_HOLDING_WEAPON = false;
      return;
    }
    ActionbarDamageIndicator.IS_HOLDING_WEAPON =
        isWeapon(player.getMainHandStack()) || isWeapon(player.getOffHandStack());
  }

  private static boolean isWeapon(ItemStack item) {
    return item.getItem() instanceof SwordItem || item.getItem() instanceof BowItem
        || item.getItem() instanceof PotionItem;
  }
}
