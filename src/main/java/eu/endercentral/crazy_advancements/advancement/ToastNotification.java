package eu.endercentral.crazy_advancements.advancement;

import eu.endercentral.crazy_advancements.CrazyAdvancementsAPI;
import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay.AdvancementFrame;
import eu.endercentral.crazy_advancements.advancement.criteria.Criteria;
import eu.endercentral.crazy_advancements.advancement.progress.AdvancementProgress;
import eu.endercentral.crazy_advancements.packet.ToastPacket;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a Toast Notification
 * 
 * @author Axel
 *
 */
public class ToastNotification {
	
	public static final NameKey NOTIFICATION_NAME = new NameKey(CrazyAdvancementsAPI.API_NAMESPACE, "notification");
	public static final Criteria NOTIFICATION_CRITERIA = new Criteria(1);
	public static final AdvancementProgress NOTIFICATION_PROGRESS = new AdvancementProgress(NOTIFICATION_CRITERIA.getRequirements());
	
	static {
		NOTIFICATION_PROGRESS.setCriteriaProgress(1);
	}
	
	private final ItemStack icon;
	private final Component message;
	private final AdvancementFrame frame;
	
	/**
	 * Constructor for creating Toast Notifications
	 * 
	 * @param icon The displayed Icon
	 * @param message The displayed Message
	 * @param frame Determines the displayed Title and Sound Effect (evaluated client-side and modifiable via resource packs)
	 */
	@Deprecated
	public ToastNotification(ItemStack icon, JSONMessage message, AdvancementFrame frame) {
		this.icon = icon;
		this.message = message.getAdventure();
		this.frame = frame;
	}

	/**
	 * Constructor for creating Toast Notifications
	 *
	 * @param icon The displayed Icon
	 * @param message The displayed Message
	 * @param frame Determines the displayed Title and Sound Effect (evaluated client-side and modifiable via resource packs)
	 */
	@Deprecated
	public ToastNotification(Material icon, JSONMessage message, AdvancementFrame frame) {
		this.icon = new ItemStack(icon);
		this.message = message.getAdventure();
		this.frame = frame;
	}

	/**
	 * Constructor for creating Toast Notifications
	 *
	 * @param icon The displayed Icon
	 * @param message The displayed Message
	 * @param frame Determines the displayed Title and Sound Effect (evaluated client-side and modifiable via resource packs)
	 */
	@Deprecated
	public ToastNotification(Material icon, String message, AdvancementFrame frame) {
		this.icon = new ItemStack(icon);
		this.message = CrazyAdvancementsAPI.msg.deserialize(message);
		this.frame = frame;
	}
	
	/**
	 * Constructor for creating Toast Notifications
	 * 
	 * @param icon The displayed Icon
	 * @param message The displayed Message
	 * @param frame Determines the displayed Title and Sound Effect (evaluated client-side and modifiable via resource packs)
	 */
	public ToastNotification(ItemStack icon, String message, AdvancementFrame frame) {
		this.icon = icon;
		this.message = CrazyAdvancementsAPI.msg.deserialize(message);
		this.frame = frame;
	}

	/**
	 * Constructor for creating Toast Notifications
	 *
	 * @param icon The displayed Icon
	 * @param message The displayed Message
	 * @param frame Determines the displayed Title and Sound Effect (evaluated client-side and modifiable via resource packs)
	 */
	public ToastNotification(ItemStack icon, Component message, AdvancementFrame frame) {
		this.icon = icon;
		this.message = message;
		this.frame = frame;
	}
	
	/**
	 * Gets the Icon
	 * 
	 * @return The Icon
	 */
	public ItemStack getIcon() {
		return icon;
	}
	
	/**
	 * Gets the Title
	 * 
	 * @return The Title
	 */
	@Deprecated
	public JSONMessage getMessage() {
		return new JSONMessage(new TextComponent(CrazyAdvancementsAPI.msg.serialize(message)));
	}

	/**
	 * Gets the Title
	 *
	 * @return The Title
	 */
	public Component message() {
		return message;
	}
	
	/**
	 * Gets the Frame
	 * 
	 * @return The Frame
	 */
	public AdvancementFrame getFrame() {
		return frame;
	}
	
	/**
	 * Sends this Toast Notification to a Player
	 * 
	 * @param player The target Player
	 */
	public void send(Player player) {
        new ToastPacket(player, true, this).send();
		new ToastPacket(player, false, this).send();
	}
	
}