package eu.endercentral.crazy_advancements.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.Advancement;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.AdvancementFlag;
import eu.endercentral.crazy_advancements.advancement.ToastNotification;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.ShadowColor;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.core.ClientAsset;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

public class PacketConverter {
	
	private static final AdvancementRewards advancementRewards =
		new AdvancementRewards(0, new ArrayList<>(), new ArrayList<>(), Optional.empty());
	
	private static final HashMap<NameKey, Float> smallestX = new HashMap<>();
	private static final HashMap<NameKey, Float> smallestY = new HashMap<>();
	
	public static void setSmallestX(NameKey tab, float smallestX) {
		PacketConverter.smallestX.put(tab, smallestX);
	}
	
	public static float getSmallestX(NameKey key) {
		return smallestX.containsKey(key) ? smallestX.get(key) : 0;
	}
	
	public static void setSmallestY(NameKey tab, float smallestY) {
		PacketConverter.smallestY.put(tab, smallestY);
	}
	
	public static float getSmallestY(NameKey key) {
		return smallestY.containsKey(key) ? smallestY.get(key) : 0;
	}
	
	public static float generateX(NameKey tab, float displayX) {
		return displayX - getSmallestX(tab);
	}
	
	public static float generateY(NameKey tab, float displayY) {
		return displayY - getSmallestY(tab);
	}
	
	/**
	 * Creates an NMS Advancement
	 * 
	 * @param advancement The Advancement to use as a base
	 * @return The NMS Advancement
	 */
	public static net.minecraft.advancements.Advancement toNmsAdvancement(Advancement advancement) {
		final AdvancementDisplay display = advancement.getDisplay();
		
		final ItemStack icon = CraftItemStack.asNMSCopy(display.getIcon());

		final NameKey back = display.background();
		final Optional<ClientAsset.ResourceTexture> backgroundTexture = back == null
			? Optional.empty() : Optional.of(new ClientAsset.ResourceTexture(back.getMinecraftKey()));

		final Optional<ResourceLocation> parent = advancement.isRoot() ? Optional.empty()
			: Optional.of(advancement.getParent().getName().getMinecraftKey());
		
		float x = generateX(advancement.getTab(), display.generateX());
		float y = generateY(advancement.getTab(), display.generateY());
		
		final DisplayInfo advDisplay = new DisplayInfo(icon, PaperAdventure.asVanilla(advancement.isRoot() ? display.title().shadowColor(ShadowColor
			.shadowColor(0, 0, 0, 255)) : display.title()), PaperAdventure.asVanilla(display.description()), backgroundTexture,
			display.getFrame().getNMS(), false, false, advancement.hasFlag(AdvancementFlag.SEND_WITH_HIDDEN_BOOLEAN));
		advDisplay.setLocation(x, y);

        return new net.minecraft.advancements.Advancement(parent, Optional.of(advDisplay), advancementRewards,
			advancement.getCriteria().getCriteria(), advancement.getCriteria().getAdvancementRequirements(), false);
	}
	
	/**
	 * Creates an NMS Toast Advancement
	 * 
	 * @param notification The Toast Notification to use as a base
	 * @return The NMS Advancement
	 */
	public static net.minecraft.advancements.Advancement toNmsToastAdvancement(ToastNotification notification) {
		ItemStack icon = CraftItemStack.asNMSCopy(notification.getIcon());
		
		DisplayInfo advDisplay = new DisplayInfo(icon, PaperAdventure.asVanilla(notification.message()), PaperAdventure.asVanilla(Component.text("Toast Notification")),
			Optional.empty(), notification.getFrame().getNMS(), true, false, true);

        return new net.minecraft.advancements.Advancement(Optional.empty(), Optional.of(advDisplay), advancementRewards,
			ToastNotification.NOTIFICATION_CRITERIA.getCriteria(), ToastNotification.NOTIFICATION_CRITERIA.getAdvancementRequirements(), false);
	}
	
	/**
	 * Creates a Dummy Advancement<br>Internally used to generate temporary parent advancements that need to be referenced in the packet
	 * 
	 * @param name The name of the Advancement
	 * @return the Dummy Advancement
	 * @deprecated No longer required for parent dummies. Might be removed in a future version.
	 */
	@Deprecated(forRemoval = true, since = "2.1.15")
	public static net.minecraft.advancements.Advancement createDummy(NameKey name) {
        return new net.minecraft.advancements.Advancement(Optional.empty(), Optional.empty(), null, new HashMap<>(), new AdvancementRequirements(new ArrayList<>()), false);
	}
	
}