package net.justmili.leftforgotten.mechanics.gameplay;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class AlphaFoodSystem {

    private static final ResourceLocation tagId = LeftForgotten.asResource("inedible");
    private static final TagKey<Item> INEDIBLE = TagKey.create(BuiltInRegistries.ITEM.key(), tagId);

    public static CompoundEventResult<ItemStack> onRightClickItem(Player player, InteractionHand hand) {
        if (player.level().dimension() != LFResources.getLevels.ALPHA_MINECRAFT) return CompoundEventResult.pass();
        if (hand != InteractionHand.MAIN_HAND) return CompoundEventResult.interruptTrue(player.getItemInHand(hand));
        ItemStack stack = player.getItemInHand(hand);
        float newHealth = player.getHealth();
        boolean consumed = false;

        if (stack.getItem() == Items.PORKCHOP) {
            newHealth += 1.5F;
            consumed = true;
        } else if (stack.getItem() == Items.COOKED_PORKCHOP) {
            newHealth += 4.0F;
            consumed = true;
        } else if (stack.getItem() == Items.BEEF) {
            newHealth += 3.0F;
            consumed = true;
        } else if (stack.getItem() == Items.COOKED_BEEF) {
            newHealth += 8.0F;
            consumed = true;
        } else if (stack.getItem() == Items.MUTTON) {
            newHealth += 2.0F;
            consumed = true;
        } else if (stack.getItem() == Items.COOKED_MUTTON) {
            newHealth += 6.0F;
            consumed = true;
        } else if (stack.getItem() == Items.CHICKEN) {
            newHealth += 2.0F;
            consumed = true;
        } else if (stack.getItem() == Items.COOKED_CHICKEN) {
            newHealth += 6.0F;
            consumed = true;
        } else if (stack.getItem() == Items.BREAD) {
            newHealth += 2.5F;
            consumed = true;
        } else if (stack.getItem() == Items.APPLE) {
            newHealth += 2.0F;
            consumed = true;
        } else if (stack.getItem() == Items.GOLDEN_APPLE) {
            newHealth += 10.0F;
            consumed = true;
        }

        if (consumed) {
            if (!healthCheck(player)) {
                stack.shrink(1);
                player.getInventory().setChanged();
                float maxHealth = player.getMaxHealth();
                player.setHealth(Math.min(newHealth, maxHealth));
                playEatSound(player.level(), player.getX(), player.getY(), player.getZ());

                return CompoundEventResult.pass();
            }
        }
        return CompoundEventResult.pass();
    }
    public static EventResult onRightClickBlock(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        if (hand != InteractionHand.MAIN_HAND) return EventResult.pass();

        ItemStack stack = player.getItemInHand(hand);

        if (player.level().dimension() != LFResources.getLevels.ALPHA_MINECRAFT) return EventResult.pass();

        int consumed;
        if (stack.is(INEDIBLE)) {
            consumed = 0;
        } else {
            consumed = 2;
        }

        if (consumed == 0) {
            return EventResult.interruptTrue();
        }
        return EventResult.pass();
    }

    private static void playEatSound(LevelAccessor world, double x, double y, double z) {
        double pitch = 0.8 + Math.random() * 0.4;

        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z),
                                SoundEvents.GENERIC_EAT,
                        SoundSource.NEUTRAL, 1.0f, (float) pitch);
            } else {
                _level.playLocalSound(x, y, z,
                                SoundEvents.GENERIC_EAT,
                        SoundSource.NEUTRAL, 1.0f, (float) pitch, false);
            }
        }
    }
    private static boolean healthCheck(Player player) {
        return player.getHealth() >= player.getMaxHealth();
    }
}