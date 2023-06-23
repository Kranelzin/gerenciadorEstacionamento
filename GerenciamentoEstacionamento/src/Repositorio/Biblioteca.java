package Repositorio;

import enums.Estados;
import exceptions.SenhaException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author marce
 */
public class Biblioteca {
    
    private static final String KEY = "FizzTheTrickster"; //mudar isso dps
    private static final String ALGORITHM = "AES";
    
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

    public static boolean verificarCamposVazios(String... campos) {
        boolean retorno = false;
        for(String campo :campos){
            if(campo == null || campo.contentEquals("")){
                retorno = true;
                break;
            }
        }
        
        return retorno;
    }
    
    public static String codificarSenha(String senha){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(senha.getBytes());
            return DatatypeConverter.printBase64Binary(encryptedBytes);
        } catch (Exception e) {
            throw new SenhaException("Falha ao codificar senha: " + e.getMessage());
        }
    }
    
    public static String decodificarSenha(String encryptedPassword){
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(DatatypeConverter.parseBase64Binary(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new SenhaException("Falha ao decodificar senha: " + e.getMessage());
        }
        
    }

    public static boolean verificarValorValido(Class<?> classe, Object value) {
        return classe.isInstance(value);
    }
    
    public static void exibirAlerta(String mensagem) {
        exibirAlerta("Alerta!", mensagem);
    }

    public static void exibirAlerta(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static boolean somenteNumeros(String texto) {
        
        for(char c : texto.toCharArray()){
            if(!Character.isDigit(c))        
                return false;
        }
        
        return true;
    }
}
