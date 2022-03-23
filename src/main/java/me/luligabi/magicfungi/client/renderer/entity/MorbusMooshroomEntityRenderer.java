package me.luligabi.magicfungi.client.renderer.entity;

import me.luligabi.magicfungi.client.renderer.entity.feature.MorbusMooshroomMushroomFeatureRenderer;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.entity.MorbusMooshroomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class MorbusMooshroomEntityRenderer extends MobEntityRenderer<MorbusMooshroomEntity, CowEntityModel<MorbusMooshroomEntity>> {

    public MorbusMooshroomEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
        addFeature(new MorbusMooshroomMushroomFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(MorbusMooshroomEntity mooshroomEntity) {
        return new Identifier(MagicFungi.MOD_ID, "textures/entity/morbus_mooshroom.png");
    }

}