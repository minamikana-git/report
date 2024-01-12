package org.hotamachi.report;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordBot {
    private final JDA jda;
    private final String channelId;

    public DiscordBot(String token, String channelId) throws Exception {
        this.jda = JDABuilder.createDefault(token).build();
        this.channelId = channelId;
        jda.awaitReady(); // Botが起動するまで待機
    }

    public void sendMessageToChannel(String message) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel != null) {
            channel.sendMessage(message).queue();
        }
    }
}
