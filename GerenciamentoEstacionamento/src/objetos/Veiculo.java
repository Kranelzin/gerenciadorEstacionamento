package objetos;

/**
 *
 * @author marce
 */

public class Veiculo {
    private final String placa;
    private final String modelo;
    private final String marca;
    
    public Veiculo(
        String placa,
        String modelo,
        String marca
    ){
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
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
    
}
