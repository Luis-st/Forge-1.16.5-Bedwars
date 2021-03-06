package net.luis.bedwars.events.player;

import net.luis.bedwars.Bedwars;
import net.luis.bedwars.base.util.ChatRank;
import net.luis.bedwars.init.ModBedwarsCapability;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Bedwars.MOD_ID, bus = Bus.FORGE)
public class OnServerChatEvent {
	
	@SubscribeEvent
	public static void ServerChat(ServerChatEvent event) {
		
		// TODO : enderchest -> team -> each team one chest
		// TODO : finish this (add command part)
		
		String message = event.getMessage();
		ServerPlayerEntity serverPlayer = event.getPlayer();
		
		serverPlayer.getCapability(ModBedwarsCapability.BEDWARS, null).ifPresent(bedwarsHandler -> {
			
			ChatRank chatRank = bedwarsHandler.getChatRank();
			
			ITextComponent userComponent = new StringTextComponent("[" + event.getUsername() + "]: ").mergeStyle(TextFormatting.RESET);
			ITextComponent rankComponent = new StringTextComponent(chatRank.getRankName() + " ").mergeStyle(chatRank.getRankFormatting());
			ITextComponent messageComponent = new StringTextComponent(message).mergeStyle(TextFormatting.RESET);
			
			event.setComponent(new StringTextComponent("").append(rankComponent).append(userComponent).append(messageComponent));
			
		});
		
	}

}
