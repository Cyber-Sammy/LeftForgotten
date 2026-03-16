package net.justmili.leftforgotten.init.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class LFBiomeModifiers {
    private static final ResourceKey<PlacedFeature> BRITTLE_BEDROCK = ResourceKey.create(Registries.PLACED_FEATURE, LeftForgotten.asResource("brittle_bedrock"));
    private static final ResourceKey<PlacedFeature> RED_FLOWER = ResourceKey.create(Registries.PLACED_FEATURE, LeftForgotten.asResource("red_flower"));
    private static final ResourceKey<PlacedFeature> YELLOW_FLOWER = ResourceKey.create(Registries.PLACED_FEATURE, LeftForgotten.asResource("yellow_flower"));
    private static final ResourceKey<PlacedFeature> PATCH_CACTUS = ResourceKey.create(Registries.PLACED_FEATURE, LeftForgotten.asResource("patch_cactus"));

    public static void register() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES,
            BRITTLE_BEDROCK
        );
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("plains")),
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("forest"))
            ),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            RED_FLOWER
        );
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("plains")),
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("forest"))
            ),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            YELLOW_FLOWER
        );
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("plains")),
                ResourceKey.create(Registries.BIOME, LeftForgotten.asResource("forest"))
            ),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            PATCH_CACTUS
        );
    }
}