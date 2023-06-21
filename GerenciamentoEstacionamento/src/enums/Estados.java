package enums;

/**
 *
 * @author marce
 */
public enum Estados {
    AC(0, "AC"),
    AL(1, "AL"),
    AP(2, "AP"),
    AM(3, "AM"),
    BA(4, "BA"),
    CE(5, "CE"),
    DF(6, "DF"),
    ES(7, "ES"),
    GO(8, "GO"),
    MA(9, "MA"),
    MT(10, "MT"),
    MS(11, "MS"),
    MG(12, "MG"),
    PA(13, "PA"),
    PB(14, "PB"),
    PR(15, "PR"),
    PE(16, "PE"),
    PI(17, "PI"),
    RJ(18, "RJ"),
    RN(19, "RN"),
    RS(20, "RS"),
    RO(21, "RO"),
    RR(22, "RR"),
    SC(23, "SC"),
    SP(24, "SP"),
    SE(25, "SE"),
    TO(26, "TO");

    public static boolean existeEstado(String uf) {
        try{
            obterPorDescricao(uf);
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }

    private final int indice;
    private final String uf;

    Estados(int indice, String uf) {
        this.indice = indice;
        this.uf = uf;
    }

    public int getIndex() {
        return indice;
    }

    public String getUF() {
        return uf;
    }
    
    public static Estados obterPorIndice(int indice) {
        Estados[] estados = Estados.values();
        
        for (Estados estado : estados) {
            if (estado.indice == indice)
                return estado;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum estado com o índice fornecido.");
    }
    
    public static Estados obterPorDescricao(String uf) {
        Estados[] estados = Estados.values();
        
        for (Estados estado : estados) {
            if (estado.uf.contentEquals(uf))
                return estado;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum estado com o índice fornecido.");
    }
    

}
