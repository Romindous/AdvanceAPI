package eu.endercentral.crazy_advancements;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import eu.endercentral.crazy_advancements.event.AdvancementScreenCloseEvent;
import eu.endercentral.crazy_advancements.event.AdvancementTabChangeEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class AdvancementPacketReceiver {
	
	private static final HashMap<String, ChannelHandler> handlers = new HashMap<>();
	private static Field channelField;
	private static Field networkManagerField;
	
	static {
		for (Field f : Connection.class.getDeclaredFields()) {
			if(f.getType().isAssignableFrom(Channel.class)) {
				channelField = f;
				channelField.setAccessible(true);
				break;
			}
		}
		
		for (Field f : ServerCommonPacketListenerImpl.class.getDeclaredFields()) {
			if(f.getType().isAssignableFrom(Connection.class)) {
				networkManagerField = f;
				networkManagerField.setAccessible(true);
				break;
			}
		}
	}
	
	public interface PacketReceivingHandler {
		boolean handle(Player p, ServerboundSeenAdvancementsPacket packet);
	}
	
	public ChannelHandler listen(final Player p, final PacketReceivingHandler handler) {
		Channel ch = getNettyChannel(p);
		ChannelPipeline pipe = ch.pipeline();
		
		ChannelHandler handle = new MessageToMessageDecoder<Packet<?>>() {
			@Override
			protected void decode(ChannelHandlerContext chc, Packet<?> packet, List<Object> out) {
				
				if(packet instanceof ServerboundSeenAdvancementsPacket) {
					if(!handler.handle(p, (ServerboundSeenAdvancementsPacket) packet)) {
						out.add(packet);
					}
					return;
				}
				
				out.add(packet);
			}
		};
		pipe.addAfter("decoder", "endercentral_crazy_advancements_listener_" + handler.hashCode(), handle);
		
		
		return handle;
	}
	
	public Channel getNettyChannel(Player p) {
		Connection manager = getNetworkManager(p);
	    Channel channel = null;
	    try {
	        channel = (Channel) channelField.get(manager);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    return channel;
	}
	
	public Connection getNetworkManager(Player p) {
		ServerGamePacketListenerImpl connection = ((CraftPlayer) p).getHandle().connection;
		Connection manager = null;
		try {
	        manager = (Connection) networkManagerField.get(connection);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    return manager;
	}
	
	public boolean close(Player p, ChannelHandler handler) {
	    try {
	        ChannelPipeline pipe = getNettyChannel(p).pipeline();
	        pipe.remove(handler);
	        return true;
	    } catch(Exception e) {
	        return false;
	    }
	}
	
	public HashMap<String, ChannelHandler> getHandlers() {
		return handlers;
	}
	
	public void initPlayer(Player p) {
		handlers.put(p.getName(), listen(p, (p1, packet) -> {

            if(packet.getAction() == ServerboundSeenAdvancementsPacket.Action.OPENED_TAB) {
                NameKey name = new NameKey(packet.getTab());
                AdvancementTabChangeEvent event = new AdvancementTabChangeEvent(p1, name);
                Bukkit.getPluginManager().callEvent(event);

                if(event.isCancelled()) {
                    CrazyAdvancementsAPI.clearActiveTab(p1);
                    return false;
                } else {
                    if(!event.getTabAdvancement().equals(name)) {
                        CrazyAdvancementsAPI.setActiveTab(p1, event.getTabAdvancement());
                    } else {
                        CrazyAdvancementsAPI.setActiveTab(p1, name, false);
                    }
                }
            } else {
                AdvancementScreenCloseEvent event = new AdvancementScreenCloseEvent(p1);
                Bukkit.getPluginManager().callEvent(event);
            }


            return true;
        }));
	}
	
}