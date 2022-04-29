package me.luligabi.magicfungi.client.tooltip;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public interface MagicItemTooltipComponent extends TooltipComponent {


    default void drawMushroomType(MatrixStack matrices, int x, int y, int z, MushroomType mushroomType) {
        RenderSystem.setShaderTexture(0, getMushroomTypeTexture(mushroomType));
        DrawableHelper.drawTexture(matrices, x, y-3, z, 0F, 0F, 18, 18, 18, 18);
    }

    default void drawActionType(MatrixStack matrices, int x, int y, int z, ActionType actionType, boolean isGlyph) {
        RenderSystem.setShaderTexture(0, getActionTypeTexture(actionType));
        DrawableHelper.drawTexture(matrices, x + (isGlyph ? 22 : 44), y-3, z, 0F, 0F, 18, 18, 18, 18);
    }


    default Identifier getMushroomTypeTexture(MushroomType mushroomType) {
        return switch(mushroomType) {
            case IMPETUS -> impetusMushroomType;
            case CLYPEUS -> clypeusMushroomType;
            case UTILIS -> utilisMushroomType;
            case VIVIFICA -> vivificaMushroomType;
            case MORBUS -> morbusMushroomType;
            case INCOGNITA -> questionMark;
        };
    }

    default Identifier getActionTypeTexture(ActionType actionType) {
        return switch(actionType) {
            case BLOCK -> blockActionType;
            case ENTITY -> entityActionType;
            case PLAYER -> playerActionType;
            case WORLD -> worldActionType;
            case UNKNOWN -> questionMark;
        };
    }

    @Override
    default int getHeight() {
        return 17;
    }


    // Mushroom Type
    Identifier impetusMushroomType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/impetus.png");
    Identifier clypeusMushroomType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/clypeus.png");
    Identifier utilisMushroomType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/utilis.png");
    Identifier vivificaMushroomType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/vivifica.png");
    Identifier morbusMushroomType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/morbus.png");

    // Action Type
    Identifier blockActionType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/block.png");
    Identifier entityActionType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/entity.png");
    Identifier playerActionType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/player.png");
    Identifier worldActionType = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/world.png");

    // Misc.
    Identifier questionMark = new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/question_mark.png");

}