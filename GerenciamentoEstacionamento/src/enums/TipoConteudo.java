package enums;

/**
 *
 * @author marce
 */
public enum TipoConteudo {

    JSON("application/json"),
    URLENCODED("application/x-www-form-urlencoded"),
    MULTFORMDATA("multipart/form-data"),
    RAW("application/raw"),
    XML("application/xml");

    private final String valor;

    TipoConteudo(final String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
