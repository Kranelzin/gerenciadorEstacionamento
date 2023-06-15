package Repositorio;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author marce
 */
public class Biblioteca {
    
    public static String encodeUrl(String s) {
        try {
            s = java.net.URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return s;
    }

    public static String decodeUrl(String s) {
        try {
            s = java.net.URLDecoder.decode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return s;
    }
    
}
