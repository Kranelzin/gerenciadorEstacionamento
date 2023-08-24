package banco;

import banco.comandos.Conexao;
import banco.comandos.Consulta;
import banco.comandos.Insert;
import banco.comandos.Update;
import exceptions.EstacionarVagaException;
import java.util.ArrayList;
import objetos.BoxVaga;
import objetos.Veiculo;

/**
 *
 * @author marce
 */
public class BancoBoxVaga {
    
    public static void inerirNovoBoxVaga(
            Conexao con, 
            int empresaId,
            String codigo,
            String vaga
    ){
    
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO BOX_VAGA ( ")
        .append("  EMPRESA_ID, ")
        .append("  CODIGO, ")
        .append("  VAGA ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
    
        insert.setSql(sql.toString());
        insert.executarComando(new Object[]{empresaId, codigo, vaga});

    }

    public static ArrayList<BoxVaga> getBoxVagas(Conexao con, int empresaId) throws EstacionarVagaException {
        ArrayList<BoxVaga> boxVagas = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  USUARIO_ID, ")
        .append("  VEICULO_USUARIO_ID, ")     
        .append("  EMPRESA_ID, ")
        .append("  CODIGO, ")
        .append("  VAGA, ")
        .append("  RESERVADA, ")
        .append("  DATA_HORA_ULT_ENTRADA, ")
        .append("  DATA_HORA_ULT_SAIDA ")
                
        .append("FROM BOX_VAGA ")

        .append("WHERE EMPRESA_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{empresaId});
        
        while(!consulta.fimConsulta()){
            
            Veiculo veiculo = null;
            
            int veiculoUsuarioId = consulta.getInt("VEICULO_USUARIO_ID");
            
            if(veiculoUsuarioId != 0)
                veiculo = buscarVeiculo(con, veiculoUsuarioId);
         
            BoxVaga boxVaga = new BoxVaga(
                consulta.getInt("EMPRESA_ID"),
                consulta.getString("CODIGO"),
                consulta.getInt("VAGA"),  
                consulta.getTimestamp("DATA_HORA_ULT_ENTRADA"),
                consulta.getTimestamp("DATA_HORA_ULT_SAIDA"),
                consulta.getBoolean("RESERVADA"),
                veiculo
            );
            
            int usuarioId = consulta.getInt("USUARIO_ID");
            
            if(usuarioId !=0)
                boxVaga.setUsuarioId(usuarioId);
            
            boxVagas.add(boxVaga);
        }
        
        return boxVagas;
    }

    private static Veiculo buscarVeiculo(Conexao con, int veiculoUsuarioId) {
        
        Veiculo veiculo = null;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  VEU.PLACA, ")
        .append("  VEI.MODELO, ")        
        .append("  VEI.MARCA ")

        .append("FROM VEICULO VEI ")
                
        .append("INNER JOIN VEICULO_USUARIO VEU ")
        .append("ON VEI.VEICULO_ID = VEU.VEICULO_ID ")

        .append("WHERE VEU.VEICULO_USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{veiculoUsuarioId});
        
        while(!consulta.fimConsulta()){
        
            veiculo = new Veiculo(
                 consulta.getString("PLACA"),
                 consulta.getString("MODELO"),
                 consulta.getString("MARCA")
             );
        }
        
        return veiculo;
    }

    public static void lotarVaga(Conexao con, int usuarioId, BoxVaga boxVaga) {
        
        int veiculoUsuarioId = buscarVeiculoUsuarioId(con, usuarioId, boxVaga.getVeiculo());
        
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE BOX_VAGA SET ")
        .append("  VEICULO_USUARIO_ID = ?, ")
        .append("  USUARIO_ID = ?, ")
        .append("  DATA_HORA_ULT_ENTRADA = ?, ")
        .append("  DATA_HORA_ULT_SAIDA = ? ")
        .append("WHERE CODIGO = ? ")
        .append("AND VAGA = ? ")
        .append("AND EMPRESA_ID = ? ");    
        
        update.setSql(sql.toString());
        
        update.executarComando(
            new Object[]{
                veiculoUsuarioId, 
                usuarioId, 
                boxVaga.getDataHoraUltEntrada(),
                boxVaga.getDataHoraUltSaida(),
                boxVaga.getCodigo(), 
                boxVaga.getVaga(), 
                boxVaga.getEmpresaId()
            }
        );
    }

    private static int buscarVeiculoUsuarioId(Conexao con, int usuarioId, Veiculo veiculo) {
        
        int veiculoUsuarioId = 0;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  VEICULO_USUARIO_ID ")
        .append("FROM VEICULO_USUARIO ")        
        .append("WHERE USUARIO_ID = ? ")
        .append("AND PLACA = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{usuarioId, veiculo.getPlaca()});
        
        while(!consulta.fimConsulta()){
            veiculoUsuarioId = consulta.getInt("VEICULO_USUARIO_ID");
        }
        
        return veiculoUsuarioId;
    }

    public static void liberarVaga(Conexao con, BoxVaga vagaLiberar) {
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE BOX_VAGA SET ")
        .append("  VEICULO_USUARIO_ID = NULL, ")
        .append("  USUARIO_ID = NULL, ")
        .append("  DATA_HORA_ULT_ENTRADA = ?, ")
        .append("  DATA_HORA_ULT_SAIDA = ? ")
        .append("WHERE CODIGO = ? ")
        .append("AND VAGA = ? ")
        .append("AND EMPRESA_ID = ? ");    
        
        update.setSql(sql.toString());
        
        update.executarComando(
            vagaLiberar.getDataHoraUltEntrada(),
            vagaLiberar.getDataHoraUltSaida(),
            vagaLiberar.getCodigo(),
            vagaLiberar.getVaga(), 
            vagaLiberar.getEmpresaId()
            
        );
    }
    
    
}