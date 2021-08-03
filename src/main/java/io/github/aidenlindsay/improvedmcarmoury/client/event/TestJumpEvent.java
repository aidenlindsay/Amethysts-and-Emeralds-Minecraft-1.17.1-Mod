package io.github.aidenlindsay.improvedmcarmoury.client.event;

import io.github.aidenlindsay.improvedmcarmoury.Main;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE)
public class TestJumpEvent {

    @SubscribeEvent
    public static void jumpEvent(LivingJumpEvent event)
    {
        Main.LOGGER.info("jumpEvent fired");
        LivingEntity livingEntity = event.getEntityLiving();
        livingEntity.isCurrentlyGlowing();
    }
}
