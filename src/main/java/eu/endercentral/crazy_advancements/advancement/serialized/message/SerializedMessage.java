package eu.endercentral.crazy_advancements.advancement.serialized.message;


import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class SerializedMessage {
	
	private final String text;
	private final String selector;
	private final String keybind;
	private final String color;
	private final boolean bold;
	private final boolean italic;
	private final boolean underlined;
	private final HoverEvent hoverEvent;
	private final ClickEvent clickEvent;
	
	private final List<SerializedMessage> extra;
	
	public SerializedMessage(String text, String selector, String keybind, String color, boolean bold, boolean italic, boolean underlined, HoverEvent hoverEvent, ClickEvent clickEvent, List<SerializedMessage> extra) {
		this.text = text;
		this.selector = selector;
		this.keybind = keybind;
		this.color = color;
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.hoverEvent = hoverEvent;
		this.clickEvent = clickEvent;
		this.extra = extra;
	}
	
	public String getText() {
		return text;
	}
	
	public String getSelector() {
		return selector;
	}
	
	public String getKeybind() {
		return keybind;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean isBold() {
		return bold;
	}
	
	public boolean isItalic() {
		return italic;
	}
	
	public boolean isUnderlined() {
		return underlined;
	}
	
	public HoverEvent getHoverEvent() {
		return hoverEvent;
	}
	
	public ClickEvent getClickEvent() {
		return clickEvent;
	}
	
	public List<SerializedMessage> getExtra() {
		return extra;
	}

	public Component deserial() {
		Component message = Component.empty();
		if(getText() != null && !getText().isEmpty()) {
			message = Component.text(getText());
		} else if(getSelector() != null && !getSelector().isEmpty()) {
			message = Component.selector(getSelector());
		} else if(getKeybind() != null && !getKeybind().isEmpty()) {
			message = Component.keybind(getKeybind());
		}

		if (getColor() != null && !getColor().isEmpty()) {
			message = message.color(NamedTextColor.NAMES.valueOr(getColor().toLowerCase(), NamedTextColor.WHITE));
		}

		if (isBold()) message = message.decoration(TextDecoration.BOLD, TextDecoration.State.TRUE);
		if (isItalic()) message = message.decoration(TextDecoration.ITALIC, TextDecoration.State.TRUE);
		if (isUnderlined()) message = message.decoration(TextDecoration.UNDERLINED, TextDecoration.State.TRUE);

		if (getHoverEvent() != null) {
			message = message.hoverEvent(net.kyori.adventure.text.event.HoverEvent.hoverEvent(net.kyori.adventure.text.event.HoverEvent.Action
				.SHOW_TEXT, Component.text(getHoverEvent().getContents())));
		}

		if (getClickEvent() != null) {
			switch (net.kyori.adventure.text.event.ClickEvent.Action
				.valueOf(getClickEvent().getAction().toUpperCase(Locale.ROOT))) {
                case OPEN_URL -> net.kyori.adventure.text.event.ClickEvent.openUrl(getClickEvent().getValue());
                case OPEN_FILE -> net.kyori.adventure.text.event.ClickEvent.openFile(getClickEvent().getValue());
                case RUN_COMMAND -> net.kyori.adventure.text.event.ClickEvent.runCommand(getClickEvent().getValue());
                case SUGGEST_COMMAND -> net.kyori.adventure.text.event.ClickEvent.suggestCommand(getClickEvent().getValue());
                case COPY_TO_CLIPBOARD -> net.kyori.adventure.text.event.ClickEvent.copyToClipboard(getClickEvent().getValue());
            }
		}

		if (getExtra() != null) {
			message = message.children(getExtra().stream().map(sm -> sm.deserial()).toList());
		}

		return message;
	}

	@Deprecated
	public BaseComponent deserialize() {
		BaseComponent message = new net.md_5.bungee.api.chat.TextComponent("");
		if(getText() != null && !getText().isEmpty()) {
			message = new net.md_5.bungee.api.chat.TextComponent(getText());
		} else if(getSelector() != null && !getSelector().isEmpty()) {
			message = new net.md_5.bungee.api.chat.SelectorComponent(getSelector());
		} else if(getKeybind() != null && !getKeybind().isEmpty()) {
			message = new net.md_5.bungee.api.chat.KeybindComponent(getKeybind());
		}
		
		if(getColor() != null && !getColor().isEmpty()) {
			message.setColor(ChatColor.of(getColor().toUpperCase(Locale.ROOT)));
		}
		
		message.setBold(isBold());
		message.setItalic(isItalic());
		message.setUnderlined(isUnderlined());
		
		if(getHoverEvent() != null) {
			message.setHoverEvent(new net.md_5.bungee.api.chat.HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.valueOf(getHoverEvent().getAction().toUpperCase(Locale.ROOT)), new Text(getHoverEvent().getContents())));
		}
		
		if(getClickEvent() != null) {
			message.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.valueOf(getClickEvent().getAction().toUpperCase(Locale.ROOT)), getClickEvent().getValue()));
		}
		
		if(getExtra() != null) {
			for(SerializedMessage extra : getExtra()) {
				message.addExtra(extra.deserialize());
			}
		}
		
		return message;
	}
	
}