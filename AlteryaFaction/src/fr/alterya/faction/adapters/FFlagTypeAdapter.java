package fr.alterya.faction.adapters;

import java.lang.reflect.Type;

import org.bukkit.craftbukkit.libs.com.google.gson.JsonDeserializationContext;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonDeserializer;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParseException;
import fr.alterya.faction.struct.FFlag;

public class FFlagTypeAdapter implements JsonDeserializer<FFlag>
{
	@Override
	public FFlag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		return FFlag.parse(json.getAsString());
	}
}
