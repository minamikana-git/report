package org.hotamachi.report;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {
    private final Report plugin;
    private final DiscordBot discordBot;

    public ReportCommand(Report plugin, DiscordBot bot) {
        this.plugin = plugin;
        this.discordBot = bot;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("report")) {
            // 'reload' 引数の確認
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("report.reload")) {
                    plugin.reloadConfig(); // 設定ファイルをリロード
                    sender.sendMessage(ChatColor.GREEN + "設定ファイルをリロードしました。");
                } else {
                    sender.sendMessage(ChatColor.RED + "このコマンドを実行する権限がありません。");
                }
                return true;
            }

            // プレイヤーの報告処理
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length > 0) {
                    // 不正行為の報告内容を取得
                    String reportMessage = String.join(" ", args);

                    // Discordにメッセージを送信
                    discordBot.sendMessageToChannel(reportMessage);
                    player.sendMessage(ChatColor.GREEN + "不正行為の報告が完了しました。");
                } else {
                    player.sendMessage(ChatColor.YELLOW + "報告する内容をチャット欄に入力してください。");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "このコマンドはプレイヤーのみ実行できます。");
            }
            return true;
        }
        return false;
    }
}
