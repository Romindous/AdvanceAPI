package eu.endercentral.crazy_advancements;

import java.util.Optional;
import java.util.stream.Stream;
import io.papermc.paper.adventure.PaperAdventure;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a Message in JSON Format
 *
 * @author Axel
 */
@Deprecated
public record JSONMessage(BaseComponent json) {

	private static final Provider COMPONENT_SERIALIZER_PROVIDER = new TextHolderLookupProvider();


	/**
	 * Constructor for creating a JSON Message
	 *
	 * @param json A JSON representation of an ingame Message <a href="https://www.spigotmc.org/wiki/the-chat-component-api/">Read More</a>
	 */
	public JSONMessage {
	}

	/**
	 * Gets the Message as a BaseComponent
	 *
	 * @return the BaseComponent of an ingame Message
	 */
	@Override
	public BaseComponent json() {
		return json;
	}

	/**
	 * Gets an NMS representation of an ingame Message
	 *
	 * @return An {@link Component} representation of an ingame Message
	 */
	public Component getBaseComponent() {
		return Component.Serializer.fromJson(ComponentSerializer.toString(json), COMPONENT_SERIALIZER_PROVIDER);
	}

	public @NotNull net.kyori.adventure.text.Component getAdventure() {
		return PaperAdventure.asAdventure(getBaseComponent());
	}

	@Override
	public String toString() {
		return json.toPlainText();
	}


	private static class TextHolderLookupProvider implements Provider {

		@Override
		public Stream<ResourceKey<? extends Registry<?>>> listRegistryKeys() {
			return Stream.empty();
		}

		@Override
		public <T> Optional<RegistryLookup<T>> lookup(ResourceKey<? extends Registry<? extends T>> registryRef) {
			return Optional.empty();
		}

	}

}