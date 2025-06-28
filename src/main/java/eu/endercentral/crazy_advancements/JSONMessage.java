package eu.endercentral.crazy_advancements;

import com.google.gson.Gson;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Message in JSON Format
 *
 * @author Axel
 */
@Deprecated
public record JSONMessage(BaseComponent json) {

	private static final Gson GSON = GsonComponentSerializer.gson().serializer();

	/**
	 * Constructor for creating a JSON Message
	 *
	 * @param json A JSON representation of an ingame Message <a href="https://www.spigotmc.org/wiki/the-chat-component-api/">Read More</a>
	 */
	public JSONMessage {}

	/**
	 * Gets the Message as a BaseComponent
	 *
	 * @return the BaseComponent of an ingame Message
	 */
	@Override
	public BaseComponent json() {
		return json;
	}

	/*public Component getBaseComponent() {
		return PaperAdventure.asAdventureFromJson(ComponentSerializer.toString(json));
	}*/

	/**
	 * Gets an NMS representation of an ingame Message
	 *
	 * @return An {@link Component} representation of an ingame Message
	 */
	public @NotNull Component getAdventure() {
		return PaperAdventure.asAdventure(GSON
			.fromJson(ComponentSerializer.toString(json),
				net.minecraft.network.chat.Component.class));
	}

	@Override
	public String toString() {
		return json.toPlainText();
	}


	/*private static class TextHolderLookupProvider implements Provider {

		@Override
		public Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys() {
			return Stream.empty();
		}

		@Override
		public <T> Optional<RegistryLookup<T>> lookup(ResourceKey<? extends Registry<? extends T>> registryRef) {
			return Optional.empty();
		}

	}*/

}