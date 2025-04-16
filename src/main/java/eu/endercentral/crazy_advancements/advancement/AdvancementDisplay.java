package eu.endercentral.crazy_advancements.advancement;

import javax.annotation.Nullable;
import eu.endercentral.crazy_advancements.CrazyAdvancementsAPI;
import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.NameKey;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.advancements.AdvancementType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Represents the Display Information of an Advancement
 * 
 * @author Axel
 *
 */
public class AdvancementDisplay {
	
	private ItemStack icon;
	private Component title, description;
	private AdvancementFrame frame;
	private transient AdvancementVisibility vis;
	private NameKey backgroundTexture;
	private float x = 0, y = 0;
	private Advancement positionOrigin;

	//Material Constructors
	
	/**
	 * 
	 * @param icon Icon {@link Material}
	 * @param title Title {@link JSONMessage}
	 * @param description Description {@link JSONMessage}
	 * @param frame {@link AdvancementFrame}
	 * @param backgroundTexture Background texture path
	 * @param visibility When an advancement is visible
	 */
	@Deprecated
	public AdvancementDisplay(Material icon, JSONMessage title, JSONMessage description, AdvancementFrame frame, String backgroundTexture, AdvancementVisibility visibility) {
		this(new ItemStack(icon), title, description, frame, backgroundTexture, visibility);
	}

	/**
	 *
	 * @param icon Icon {@link Material}
	 * @param title Title {@link JSONMessage}
	 * @param description Description {@link JSONMessage}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	@Deprecated
	public AdvancementDisplay(Material icon, JSONMessage title, JSONMessage description, AdvancementFrame frame, AdvancementVisibility visibility) {
		this.icon = new ItemStack(icon);
		this.title = title.getAdventure();
		this.description = description.getAdventure();
//		GsonComponentSerializer.gson().deserialize(ComponentSerializer.toString(title.json()));
		this.frame = frame;
		this.vis = visibility;
	}

	/**
	 *
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link JSONMessage}
	 * @param description Description {@link JSONMessage}
	 * @param frame {@link AdvancementFrame}
	 * @param backgroundTexture Background texture path
	 * @param visibility When an advancement is visible
	 */
	@Deprecated
	public AdvancementDisplay(ItemStack icon, JSONMessage title, JSONMessage description, AdvancementFrame frame, String backgroundTexture, AdvancementVisibility visibility) {
		this.icon = icon;
		this.title = title.getAdventure();
		this.description = description.getAdventure();
//		GsonComponentSerializer.gson().deserialize(ComponentSerializer.toString(title.json()));
		this.frame = frame;
		this.backgroundTexture = new NameKey(backgroundTexture);
		this.vis = visibility;
	}

	/**
	 *
	 * @param icon Icon {@link Material}
	 * @param title Title {@link String}
	 * @param description Description {@link String}
	 * @param frame {@link AdvancementFrame}
	 * @param backgroundTexture Background texture path
	 * @param visibility When an advancement is visible
	 */
	@Deprecated
	public AdvancementDisplay(Material icon, String title, String description, AdvancementFrame frame, String backgroundTexture, AdvancementVisibility visibility) {
		this(new ItemStack(icon), CrazyAdvancementsAPI.msg.deserialize(title), CrazyAdvancementsAPI.msg.deserialize(description), frame, backgroundTexture, visibility);
	}

	/**
	 *
	 * @param icon Icon {@link Material}
	 * @param title Title {@link String}
	 * @param description Description {@link String}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(Material icon, String title, String description, AdvancementFrame frame, AdvancementVisibility visibility) {
		this.icon = new ItemStack(icon);
		this.title = CrazyAdvancementsAPI.msg.deserialize(title);
		this.description = CrazyAdvancementsAPI.msg.deserialize(description);
//		GsonComponentSerializer.gson().deserialize(ComponentSerializer.toString(title.json()));
		this.frame = frame;
		this.vis = visibility;
	}
	
	/**
	 * 
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link String}
	 * @param description Description {@link String}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(ItemStack icon, String title, String description, AdvancementFrame frame, AdvancementVisibility visibility) {
		this(icon, CrazyAdvancementsAPI.msg.deserialize(title), CrazyAdvancementsAPI.msg.deserialize(description), frame, visibility);
	}

	/**
	 *
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link String}
	 * @param description Description {@link String}
	 * @param frame {@link AdvancementFrame}
	 * @param backgroundTexture Background texture path
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(ItemStack icon, String title, String description, AdvancementFrame frame, String backgroundTexture, AdvancementVisibility visibility) {
		this(icon, CrazyAdvancementsAPI.msg.deserialize(title), CrazyAdvancementsAPI.msg.deserialize(description), frame, backgroundTexture, visibility);
	}

	//ItemStack constructors

	/**
	 *
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link Component}
	 * @param description Description {@link Component}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(ItemStack icon, Component title, Component description, AdvancementFrame frame, AdvancementVisibility visibility) {
		this.icon = icon;
		this.title = title;
		this.description = description;
		this.frame = frame;
		this.vis = visibility;
	}

	/**
	 *
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link Component}
	 * @param description Description {@link Component}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(ItemStack icon, Component title, Component description, AdvancementFrame frame, String backgroundTexture, AdvancementVisibility visibility) {
		this.icon = icon;
		this.title = title;
		this.description = description;
		this.frame = frame;
		this.backgroundTexture = new NameKey(backgroundTexture);
		this.vis = visibility;
	}

	/**
	 *
	 * @param icon Icon {@link ItemStack}
	 * @param title Title {@link Component}
	 * @param description Description {@link Component}
	 * @param frame {@link AdvancementFrame}
	 * @param visibility When an advancement is visible
	 */
	public AdvancementDisplay(ItemStack icon, Component title, Component description, AdvancementFrame frame, NameKey backgroundTexture, AdvancementVisibility visibility) {
		this.icon = icon;
		this.title = title;
		this.description = description;
		this.frame = frame;
		this.backgroundTexture = backgroundTexture;
		this.vis = visibility;
	}
	
	/**
	 * Represents the Frame of an Advancement
	 * 
	 * @author Axel
	 *
	 */
	public enum AdvancementFrame {
		
		/**
		 * A Task has the default Frame and defaults to a green Color in Completion Messages
		 */
		TASK(AdvancementType.TASK),
		/**
		 * A Goal has a rounded off Frame and defaults to a green Color in Completion Messages
		 */
		GOAL(AdvancementType.GOAL),
		/**
		 * A Challenge has a differently shaped Frame and defaults to a purple Color in Completion Messages and it's Toast plays a Sound when displayed
		 */
		CHALLENGE(AdvancementType.CHALLENGE)
		;
		
		private final AdvancementType nms;
		
		AdvancementFrame(AdvancementType nms) {
			this.nms = nms;
		}
		
		/**
		 * Get the NMS Representation of this AdvancementFrame
		 * 
		 * @return THE NMS Representation
		 */
		public AdvancementType getNMS() {
			return nms;
		}
		
		/**
		 * Parses the AdvancementFrame by it's name
		 * 
		 * @param name The case-insensitive name
		 * @return The AdvancementFrame
		 */
		public static AdvancementFrame parse(String name) {
			for(AdvancementFrame frame : values()) {
				if(frame.name().equalsIgnoreCase(name)) {
					return frame;
				}
			}
			return TASK;
		}
		
		/**
		 * Parses the AdvancementFrame by it's name
		 * 
		 * @param name The case-insensitive name
		 * @return The AdvancementFrame or null if no matching Frame is found
		 */
		public static AdvancementFrame parseStrict(String name) {
			for(AdvancementFrame frame : values()) {
				if(frame.name().equalsIgnoreCase(name)) {
					return frame;
				}
			}
			return null;
		}
		
	}
	
	/**
	 * 
	 * @return Icon {@link ItemStack}
	 */
	public ItemStack getIcon() {
		return icon;
	}
	
	/**
	 * 
	 * @return Title {@link JSONMessage}
	 */
	@Deprecated
	public JSONMessage getTitle() {
		return new JSONMessage(new TextComponent(CrazyAdvancementsAPI.msg.serialize(title)));
	}

	/**
	 *
	 * @return Title {@link Component}
	 */
	public Component title() {
		return title;
	}
	
	/**
	 * 
	 * @return Description {@link JSONMessage}
	 */
	@Deprecated
	public JSONMessage getDescription() {
		return new JSONMessage(new TextComponent(CrazyAdvancementsAPI.msg.serialize(description)));
	}

	/**
	 *
	 * @return Title {@link Component}
	 */
	public Component description() {
		return description;
	}
	
	/**
	 * 
	 * @return {@link AdvancementFrame}
	 */
	public AdvancementFrame getFrame() {
		return frame;
	}
	
	/**
	 * 
	 * @return Background texture path
	 */
	@Nullable
	public String getBackgroundTexture() {
		return backgroundTexture == null ? null : backgroundTexture.toString();
	}

	/**
	 *
	 * @return Background texture path
	 */
	@Nullable
	public NameKey background() {
		return backgroundTexture;
	}
	
	/**
	 * Sets the background texture
	 * 
	 * @param backgroundTexture Background Texture path
	 */
	@Deprecated
	public void setBackgroundTexture(@Nullable String backgroundTexture) {
		this.backgroundTexture = backgroundTexture == null ? null : new NameKey(backgroundTexture);
	}

	/**
	 * Sets the background texture
	 *
	 * @param background Background Texture path
	 */
	public void background(@Nullable NameKey background) {
		this.backgroundTexture = background;
	}
	
	/**
	 * Gets the relative X coordinate
	 * 
	 * @return relative X coordinate
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets the relative y coordinate
	 * 
	 * @return relative y coordinate
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Gets the absolute x coordinate
	 * 
	 * @return absolute x coordinate
	 */
	public float generateX() {
		if(getPositionOrigin() == null) {
			return x;
		} else {
			return getPositionOrigin().getDisplay().generateX() + x;
		}
	}
	
	/**
	 * Gets the absolute y coordinate
	 * 
	 * @return absolute y coordinate
	 */
	public float generateY() {
		if(getPositionOrigin() == null) {
			return y;
		} else {
			return getPositionOrigin().getDisplay().generateY() + y;
		}
	}
	
	/**
	 * Gets the {@link AdvancementVisibility}
	 * 
	 * @return when an advancement is visible
	 */
	public AdvancementVisibility getVisibility() {
		return vis != null ? vis : AdvancementVisibility.VANILLA;
	}
	
	/**
	 * 
	 * @param player Player to check
	 * @param advancement Advancement to check (because {@link AdvancementDisplay} is not bound to one advancement)
	 * @return true if it should be currently visible
	 */
	public boolean isVisible(Player player, Advancement advancement) {
		AdvancementVisibility visibility = getVisibility();
		Advancement parent = advancement.getParent();
		boolean parentVisible = parent == null || parent.getDisplay().isVisible(player, parent);
		return parentVisible && visibility.isVisible(player, advancement) || advancement.isGranted(player) || (visibility.isAlwaysVisibleWhenAnyChildIsGranted() && advancement.isAnythingGrantedAfter(player));
	}
	
	/**
	 * 
	 * @return the advancement that marks the origin of the coordinates
	 */
	public Advancement getPositionOrigin() {
		return positionOrigin;
	}
	
	
	
	
	
	/**
	 * Changes the Icon
	 * 
	 * @param icon New Icon Material to display
	 */
	public void setIcon(Material icon) {
		this.icon = new ItemStack(icon);
	}
	
	/**
	 * Changes the Icon
	 * 
	 * @param icon New Icon to display
	 */
	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}
	
	/**
	 * Changes the Title
	 * 
	 * @param title New title {@link JSONMessage}
	 */
	@Deprecated
	public void setTitle(JSONMessage title) {
		this.title = title.getAdventure();
	}
	
	/**
	 * Changes the Title
	 * 
	 * @param title New Title {@link String}
	 */
	@Deprecated
	public void setTitle(String title) {
		this.title = CrazyAdvancementsAPI.msg.deserialize(title);
	}

	/**
	 * Changes the Title
	 *
	 * @param title New Title {@link String}
	 */
	public void title(String title) {
		this.title = CrazyAdvancementsAPI.msg.deserialize(title);
	}

	/**
	 * Changes the Title
	 *
	 * @param title New Title {@link Component}
	 */
	public void title(Component title) {
		this.title = title;
	}
	
	/**
	 * Changes the Description
	 * 
	 * @param description New description {@link JSONMessage}
	 */
	@Deprecated
	public void setDescription(JSONMessage description) {
		this.description = description.getAdventure();
	}
	
	/**
	 * Changes the Description
	 * 
	 * @param description New Description {@link String}
	 */
	@Deprecated
	public void setDescription(String description) {
		this.description = CrazyAdvancementsAPI.msg.deserialize(description);
	}

	/**
	 * Changes the Description
	 *
	 * @param description New Description {@link String}
	 */
	public void description(String description) {
		this.title = CrazyAdvancementsAPI.msg.deserialize(description);
	}

	/**
	 * Changes the Description
	 *
	 * @param description New Description {@link Component}
	 */
	public void description(Component description) {
		this.title = description;
	}
	
	/**
	 * Changes the Frame
	 * 
	 * @param frame New Frame
	 */
	public void setFrame(AdvancementFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Changes the visibility
	 * 
	 * @param visibility New Visibility
	 */
	public void setVisibility(AdvancementVisibility visibility) {
		this.vis = visibility;
	}
	
	/**
	 * Changes the relative coordinates
	 * 
	 * @param x relative x coordinate
	 * @param y relative y coordinate
	 */
	public void setCoordinates(float x, float y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Changes the relative x coordinate
	 * 
	 * @param x relative x coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Changes the relative y coordinate
	 * 
	 * @param y relative y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Changes the advancement that marks the origin of the coordinates
	 * 
	 * @param positionOrigin New position origin
	 */
	public void setPositionOrigin(@Nullable Advancement positionOrigin) {
		this.positionOrigin = positionOrigin;
	}
	
	
	
	
}