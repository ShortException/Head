

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field
import java.util.*


class Head {

    fun getCustomTextureHead(value: String?): ItemStack {
        val head = ItemStack(Material.PLAYER_HEAD)
        val meta = head.itemMeta as SkullMeta
        val profile = GameProfile(UUID.randomUUID(), "")
        profile.properties.put("textures", Property("textures", value))
        var profileField: Field? = null
        try {
            profileField = meta.javaClass.getDeclaredField("profile")
            profileField.isAccessible = true
            profileField.set(meta, profile)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        head.itemMeta = meta
        return head
    }

    fun getPlayerHead(player: Player): ItemStack {
        val item = ItemStack(Material.PLAYER_HEAD)
        val pSkull = item.itemMeta as SkullMeta
        pSkull.displayName(Component.text("${ChatColor.GOLD}${player.name}"))
        pSkull.owningPlayer = Bukkit.getOfflinePlayer(player.uniqueId)
        item.itemMeta = pSkull
        return item
    }

}