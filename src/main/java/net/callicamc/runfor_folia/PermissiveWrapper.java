package net.callicamc.runfor_folia;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;
import java.util.UUID;

public class PermissiveWrapper implements CommandSender {

    private final Player base;

    public PermissiveWrapper(Player base) {
        this.base = base;
    }

    // always allow permissions
    @Override public boolean hasPermission(String s) { return true; }
    @Override public boolean hasPermission(Permission p) { return true; }
    @Override public boolean isPermissionSet(String s) { return true; }
    @Override public boolean isPermissionSet(Permission p) { return true; }
    @Override public boolean isOp() { return true; }
    @Override public void recalculatePermissions() { }

    // send messages as the player
    @Override public void sendMessage(String s) { base.sendMessage(s); }
    @Override public void sendMessage(String[] strings) { base.sendMessage(strings); }
    public void sendMessage(UUID uuid, String s) { base.sendMessage(s); }
    public void sendMessage(UUID uuid, String[] strings) { base.sendMessage(strings); }

    // identity
    @Override public String getName() { return base.getName(); }
    public Player getPlayer() { return base; }
    @Override public org.bukkit.Server getServer() { return Bukkit.getServer(); }
    @Override public org.bukkit.command.CommandSender.Spigot spigot() { return base.spigot(); }

    // permission attachments
    @Override public void setOp(boolean value) { base.setOp(value); }
    @Override public PermissionAttachment addAttachment(Plugin plugin) { return base.addAttachment(plugin); }
    @Override public PermissionAttachment addAttachment(Plugin plugin, int ticks) { return base.addAttachment(plugin, ticks); }
    @Override public PermissionAttachment addAttachment(Plugin plugin, String permission, boolean value) { return base.addAttachment(plugin, permission, value); }
    @Override public PermissionAttachment addAttachment(Plugin plugin, String permission, boolean value, int ticks) { return base.addAttachment(plugin, permission, value, ticks); }
    @Override public void removeAttachment(PermissionAttachment attachment) { base.removeAttachment(attachment); }
    @Override public Set<PermissionAttachmentInfo> getEffectivePermissions() { return base.getEffectivePermissions(); }
}
