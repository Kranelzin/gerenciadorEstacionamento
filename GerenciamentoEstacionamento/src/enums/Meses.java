package enums;

/**
 *
 * @author marce
 */

public enum Meses {
    JANEIRO(1,"Janeiro"),
    FEVEREIRO(2,"Fevereiro"),
    MARÇO(3, "Março"),
    ABRIL(4,"Abril"),
    MAIO(5,"Maio"),
    JUNHO(6,"Junho"),
    JULHO(7,"Julho"),
    AGOSTO(8,"Agosto"),
    SETEMBRO(9,"Setembro"),
    OUTUBRO(10,"Outubro"),
    NOVEMBRO(11, "Novembro"),
    DEZEMBRO(12, "Dezembro");
    
    private final int indice;
    private final String mes;
    
    Meses(int indice, String mes){
        this.indice = indice;
        this.mes = mes;
    }
    
    public String getMes(){
        return mes;
    }
    
    public int getIndice(){
        return indice;
    }
    
    public static Meses obterPorIndice(int indice) {
        Meses[] meses = Meses.values();
        
        for (Meses mes : meses) {
            if (mes.indice == indice) {
                return mes;
            }
        }
        throw new IllegalArgumentException("Não foi encontrado um mês com o índice fornecido.");
    }
}
    

