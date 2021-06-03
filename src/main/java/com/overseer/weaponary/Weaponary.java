package com.overseer.weaponary;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class Weaponary extends JavaPlugin {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wcreate")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length < 4) {
                    p.sendMessage("/wcreate 이름 종류 색깔 피해량");
                } else {
                    String Name = args[0];
                    Name.replace('_', ' ');
                    Material Material = org.bukkit.Material.valueOf(args[1]);
                    ChatColor Color = ChatColor.valueOf(args[2]);
                    int Damage = Integer.parseInt(args[3]);
                    ItemStack Weapon = new ItemStack(Material);
                    ItemMeta Meta = Weapon.getItemMeta();
                    Meta.setDisplayName(Color + Name);
                    AttributeModifier DMG = new AttributeModifier(UUID.randomUUID(), "Damage", Damage, Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    AttributeModifier SPD = new AttributeModifier(UUID.randomUUID(), "Speed", 16, Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    Meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, DMG);
                    Meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, SPD);
                    Weapon.setItemMeta(Meta);
                    Weapon.addEnchantment(Enchantment.LOYALTY, 0);
                    p.getInventory().addItem(Weapon);
                }
                return true;
            }
        }
        return false;
    }
}
