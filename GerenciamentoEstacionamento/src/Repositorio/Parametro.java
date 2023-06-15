package Repositorio;

import java.util.ArrayList;

/**
 *
 * @author marce
 */
public class Parametro {
    private String chave;
    private ArrayList<String> valores;
    private ArrayList<String> valoresCodificados;


    public Parametro(String chave, ArrayList<String> valores, boolean codificar) {
        this.chave = chave;
        if(codificar) {
            for(String valor : valores){
                String valorCodificado = Biblioteca.encodeUrl(valor);
                valoresCodificados.add(valor);
            }

            this.valores = valoresCodificados;
        }
    }

    public String getChave() {
        return chave;
    }

    public ArrayList<String> getValores() {
        return valores;
    }

    public boolean parametroValidoParaSubJson(String chave) {
        return (this.chave.equals(chave) && this.valores.isEmpty());
    }

}
