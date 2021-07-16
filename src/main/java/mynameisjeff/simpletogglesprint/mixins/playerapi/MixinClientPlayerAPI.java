package mynameisjeff.simpletogglesprint.mixins.playerapi;

import com.mojang.authlib.GameProfile;
import mynameisjeff.simpletogglesprint.core.UtilsKt;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayerSP.class)
public abstract class MixinClientPlayerAPI extends AbstractClientPlayer {
    public MixinClientPlayerAPI(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Dynamic
    @Redirect(method = "localOnLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
    private boolean setSprintState(KeyBinding keyBinding) {
        return UtilsKt.shouldSetSprint(keyBinding);
    }
}
