package com.kenikydev.kenikyitems.entity.client;

import com.google.common.collect.Maps;
import com.kenikydev.kenikyitems.KenikyItems;
import com.kenikydev.kenikyitems.entity.TriceratopsVariant;
import com.kenikydev.kenikyitems.entity.custom.TriceratopsEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

/*
Renderizador personalizado para la entidad Triceratops
Extiende: MobRenderer, la clase base para renderizar criaturas
TriceratopsEntity: El tipo de entidad que renderiza
TriceratopsModel: El modelo 3D que usa para renderizar
 */
public class TriceratopsRenderer extends MobRenderer<TriceratopsEntity, TriceratopsModel<TriceratopsEntity>> {

    //Asocia cada variante del Triceratops con su textura correspondiente
    private static final Map<TriceratopsVariant, ResourceLocation>  LOCATION_BY_VARIANT = Util.make(
            Maps.newEnumMap(TriceratopsVariant.class), //Usa enum para mapear variantes a rutas de textura
            map -> {
                map.put(TriceratopsVariant.GREY,
                        ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "textures/entity/triceratops/triceratops_grey.png"));
                map.put(TriceratopsVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(KenikyItems.MODID, "textures/entity/triceratops/triceratops_green.png"));
            }
            );

    public TriceratopsRenderer(EntityRendererProvider.Context context) {
        super(context, new TriceratopsModel<>(context.bakeLayer(TriceratopsModel.LAYER_LOCATION)), 0.85f);
    }

    //Devuelve la textura correcta según la variante de la entidad
    @Override
    public ResourceLocation getTextureLocation(TriceratopsEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(TriceratopsEntity entity, float v, float v1, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (entity.isBaby()) { //Ajusta la escala de renderizado si el Triceratops es una cría
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, v, v1, poseStack, multiBufferSource, i);
    }
}
