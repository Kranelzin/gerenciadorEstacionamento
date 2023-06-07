package objetos;

/**
 *
 * @author marce
 */

public class Veiculo {
    private final String placa;
    private final String modelo;
    private final String marca;
    private final int quantidadeRodas;
    
    public Veiculo(
        String placa,
        String modelo,
        String marca,
        int quantidadeRodas
    ){
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.quantidadeRodas = quantidadeRodas;
    }
    
    public String getPlaca(){
        return placa;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public int getQuantidadeRodas(){
        return quantidadeRodas;
    }
}
