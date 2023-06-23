package enums;

/**
 *
 * @author marce
 */
public enum Estados {
    
    AC(1, "AC"),
    AL(2, "AL"),
    AM(3, "AM"),
    AP(4, "AP"),
    BA(5, "BA"),
    CE(6, "CE"),
    DF(7, "DF"),
    ES(8, "ES"),
    GO(9, "GO"),
    MA(10, "MA"),
    MG(11, "MG"),
    MS(12, "MS"),
    MT(13, "MT"),
    PA(14, "PA"),
    PB(15, "PB"),
    PE(16, "PE"),
    PI(17, "PI"),
    PR(18, "PR"),  
    RJ(19, "RJ"),
    RN(20, "RN"),
    RO(21, "RO"),
    RR(22, "RR"),
    RS(23, "RS"),
    SC(24, "SC"),
    SE(25, "SE"),
    SP(26, "SP"), 
    TO(27, "TO");

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

    public int getIndice() {
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
