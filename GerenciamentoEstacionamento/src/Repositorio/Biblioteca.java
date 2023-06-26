package Repositorio;

import exceptions.SenhaException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    public static boolean verificarValorValido(Class<?> classe, Object valor) {
        return valor == null || classe.isInstance(valor);
    }
    
    public static void exibirAlerta(String mensagem) {
        exibirAlerta("Alerta!", mensagem);
    }

    public static void exibirAlerta(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static int exibirCaixaDialogo(String titulo, String mensagem){
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
    }
    
    public static boolean somenteNumeros(String texto) {
        
        for(char c : texto.toCharArray()){
            if(!Character.isDigit(c))        
                return false;
        }
        
        return true;
    }
    
    public static boolean verificarContemNosComponentes(ArrayList<String> componentes, ArrayList<String> componentesVerificar) throws Exception{
        
        if(componentes.size() != componentesVerificar.size())
            throw new Exception("verificarContemNosComponentes: listas com tamanhos diferentes! ");
        
        for(int i =0; i < componentes.size(); i++){
            if(!componentes.get(i).contentEquals(componentesVerificar.get(i)))
                return false;
        }
        
        
        return true;
    }

    public static String inserirListaSql(ArrayList<Integer> lista) {
        String sql = "";
        
        for(int i = 0; i < lista.size(); i++){
            if(i == lista.size())
                sql += lista.get(i);
            else
                sql+= lista.get(i) + ",";
        }
        
        return sql;
    }
}
