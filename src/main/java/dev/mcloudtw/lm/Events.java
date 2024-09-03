package dev.mcloudtw.lm;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

public class Events implements Listener {
    @EventHandler
    public void LootGenerateEvent(LootGenerateEvent event) {
        int prize = 300 + (int)(Math.random() * 100);
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(
                MiniMessage.miniMessage().deserialize("<!i><gradient:gold:yellow>寶箱獎勵</gradient>")
        );
        meta.lore(
                List.of(
                        MiniMessage.miniMessage().deserialize("<!i><gray>手持寶藏獎勵<gold> /deposit </gold>即可換成遊戲幣</gray>"),
                        MiniMessage.miniMessage().deserialize("<!i><gray>價值<white> : </white><yellow>"+prize+" <gray>元</gray></yellow>")
                )
        );
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(
                NamespacedKey.fromString("nfcnotes:notevalue"),
                PersistentDataType.DOUBLE,
                (double)prize
        );
        pdc.set(
                NamespacedKey.fromString("nfcnotes:noteidentifier"),
                PersistentDataType.STRING,
            "9a12cb32-1a7e-4e41-be79-9938528b4375"
        );
        item.setItemMeta(meta);
        event.getInventoryHolder().getInventory().addItem(item);
    }

}
