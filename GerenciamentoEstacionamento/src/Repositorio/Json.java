package Repositorio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    private static Gson novoGson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson;
    }
    
    public static Object toJson(Object object){
        Gson gson = novoGson();
        return gson.toJson(object);
    }
    
    public static <Generico> Generico toObject(String json, Class<Generico> Generico){
        if (json.isEmpty())
            return null;

        Gson gson = novoGson();
        return gson.fromJson(json, Generico);

    }
    
}
