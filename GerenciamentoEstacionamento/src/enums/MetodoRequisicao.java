package enums;

/**
 *
 * @author marce
 */
public enum MetodoRequisicao {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH");

    private final String descricao;

    MetodoRequisicao(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
