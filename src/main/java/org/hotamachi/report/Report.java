package org.hotamachi.report;

import org.bukkit.plugin.java.JavaPlugin;

public class Report extends JavaPlugin {
    private DiscordBot discordBot;

    @Override
    public void onEnable() {
        saveDefaultConfig(); // config.ymlがなければ作成

        // 設定からトークンとチャンネルIDを取得
        String token = getConfig().getString("discord.token");
        String channelId = getConfig().getString("discord.channel-id");

        // DiscordBotを初期化
        try {
            discordBot = new DiscordBot(token, channelId);
        } catch (Exception e) {
            getLogger().severe("DiscordBotの初期化に失敗: " + e.getMessage());
            this.setEnabled(false);
            return;
        }

        // コマンドエグゼキューターを設定
        this.getCommand("report").setExecutor(new ReportCommand(this, discordBot));
    }
}
