package by.yegorov.nasa.core.api.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import by.yegorov.nasa.core.utils.ExceptionUtils;

@SuppressWarnings({"WeakerAccess", "unused"})
public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    final DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm z", Locale.getDefault());//Thu, 14 Jul 2016 15:20 EDT

    public DateAdapter() {
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(df.format(src));
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return df.parse(jsonElement.getAsString());
        } catch (ParseException e) {
            ExceptionUtils.handleException(e, jsonElement.getAsString());
            return null;
        }
    }
}

