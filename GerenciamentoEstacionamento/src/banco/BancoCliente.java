package banco;

import Repositorio.Biblioteca;
import banco.comandos.Conexao;
import banco.comandos.Consulta;
import banco.comandos.Delete;
import banco.comandos.Insert;
import banco.comandos.Procedimento;
import banco.comandos.Update;
import enums.Meses;
import enums.TipoUsuario;
import exceptions.BancoIntegridadeException;
import exceptions.EstacionarVagaException;
import java.util.ArrayList;
import objetos.BoxVaga;
import objetos.Cliente;
import objetos.Endereco;
import objetos.Mensalidade;
import objetos.PagamentoMensalidade;
import objetos.Telefone;
import objetos.Veiculo;

/**
 *
 * @author marce
 */
public class BancoCliente {
    
    public static Cliente inerirNovoCliente(
            Conexao con, 
            int empresaId,
            TipoUsuario tipoUsuario,
            String nome, 
            String cpfCnpj, 
            ArrayList<String> emails, 
            ArrayList<Endereco> enderecos, 
            ArrayList<Telefone> telefones,
            ArrayList<Veiculo> veiculos
    ){
    
        int usuarioId = BancoUsuario.inerirUsuario(con, empresaId, tipoUsuario, nome, cpfCnpj);
        BancoUsuario.inserirEmails(con, usuarioId, emails);
        BancoUsuario.inserirEnderecos(con, usuarioId, enderecos);
        BancoUsuario.inserirTelefones(con, usuarioId, telefones);
        //TODO: ajustar com chamada de procedure
        
        Cliente cliente = new Cliente(
            usuarioId,
            nome,
            cpfCnpj,
            emails,
            enderecos,
            telefones,
            veiculos,
            empresaId
        );
        
        return cliente;
    }


    public static void inserirMensalidade(Conexao con, int usuarioId, Mensalidade mensalidade) {
        
        Insert insert = con.novoInsert();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("INSERT INTO MENSALIDADE ( ")
        .append("  USUARIO_ID, ")
        .append("  DATA_INICIO, ")
        .append("  DIA_VENCIMENTO, ")
        .append("  VALOR_MENSALIDADE ")
        .append(") ")
        .append("VALUES ( ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ?, ")
        .append("  ? ")
        .append(") ");
        
        insert.setSql(sql.toString());
        
        insert.executarComando(new Object[]{usuarioId, mensalidade.getDataInicio(), mensalidade.getDiaVencimento(), mensalidade.getValorMensalidade()});
        
    }

    public static void cadastrarBoxVagasReservadas(Conexao con, int usuarioId, ArrayList<BoxVaga> boxVagas) {
        
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE BOX_VAGA SET ")
        .append("  USUARIO_ID = ?, ")
        .append("  RESERVADA = TRUE ")

        .append("WHERE CODIGO = ? ")
        .append("AND VAGA = ? ");
        
        update.setSql(sql.toString());
        
        for(BoxVaga boxVaga : boxVagas){
            update.executarComando(new Object[] {usuarioId, boxVaga.getCodigo(), boxVaga.getVaga()});
        }
        
    }

    public static Cliente buscarCliente(Conexao con, String nomeCliente) throws EstacionarVagaException {
        
        Cliente cliente = null;
        int usuarioId = -1;
        int empresaId = -1;
        String nome = "";
        String cpfCnpj = "";
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  USUARIO_ID, ")
        .append("  NOME, ")
        .append("  CPF_CNPJ, ")
        .append("  EMPRESA_ID ")
        .append("FROM USUARIO ")
        .append("WHERE NOME = ? ");
        
        consulta.setSql(sql.toString());
        
        consulta.executarComando(new Object[]{nomeCliente});
        
        while(!consulta.fimConsulta()){

            usuarioId = consulta.getInt("USUARIO_ID");
            nome = consulta.getString("NOME");
            cpfCnpj = consulta.getString("CPF_CNPJ");

        }
        
        if(usuarioId == -1)
            return null;
        
        ArrayList<String> emails = BancoUsuario.buscarEmailsUsuario(con, usuarioId);
        ArrayList<Telefone> telefones = BancoUsuario.buscarTelefonesUsuario(con, usuarioId);
        ArrayList<Endereco> enderecos = BancoUsuario.buscarEnderecosUsuario(con, usuarioId);
        ArrayList<Veiculo>  veiculos = buscarVeiculos(con, usuarioId);
        ArrayList<BoxVaga> boxVagas = buscarBoxVagas(con, usuarioId);
        Mensalidade mensalidade = buscarMensalidade(con, usuarioId);
        
        cliente = new Cliente(
            usuarioId,
            nome,
            cpfCnpj,
            emails,
            enderecos,
            telefones,
            veiculos,
            empresaId
        );
        
        cliente.setBoxVaga(boxVagas);
        
        if(mensalidade != null)
            cliente.cadastrarPagamentoMensal(mensalidade, boxVagas);

        return cliente;
    }

    private static ArrayList<Veiculo> buscarVeiculos(Conexao con, int usuarioId) {
        
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  VEU.PLACA, ")
        .append("  VEI.MODELO, ")
        .append("  VEI.MARCA ")

        .append("FROM VEICULO_USUARIO VEU ")
                
        .append("INNER JOIN VEICULO VEI ")
        .append("ON VEU.VEICULO_ID = VEI.VEICULO_ID ")
        
        .append("WHERE VEU.USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{usuarioId});

        while(!consulta.fimConsulta()){
            Veiculo veiculo = new Veiculo(
                consulta.getString("PLACA"),
                consulta.getString("MODELO"),
                consulta.getString("MARCA")
            );
            
            veiculos.add(veiculo);
        }
        
        return veiculos;
    }

    private static Mensalidade buscarMensalidade(Conexao con, int usuarioId) {
        
        Mensalidade mensalidade = null;
        
        ArrayList<PagamentoMensalidade> pagamentosMensalidade = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  MENSALIDADE_ID, ")
        .append("  DIA_VENCIMENTO, ")
        .append("  DATA_INICIO, ")
        .append("  VALOR_MENSALIDADE ")

        .append("FROM MENSALIDADE ")
                
        .append("WHERE USUARIO_ID = ? ")
        .append("AND DATA_FIM IS NULL ");
        
        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{usuarioId});

        while(!consulta.fimConsulta()){
            int mensalidadeId = consulta.getInt("MENSALIDADE_ID");
            
            mensalidade = new Mensalidade(
                    consulta.getInt("DIA_VECIMENTO"),
                    consulta.getDate("DATA_INICIO"),
                    consulta.getBigDecimal("VALOR_MENSALIDE")
            
            );
            
            pagamentosMensalidade = buscarPagamentosMensalidade(con, mensalidadeId);
            
            if(pagamentosMensalidade.size() > 0)
                mensalidade.setPagamentosRealizados(pagamentosMensalidade);
        }
        
        return mensalidade;
    }

    private static ArrayList<PagamentoMensalidade> buscarPagamentosMensalidade(Conexao con, int mensalidadeId) {
        
        ArrayList<PagamentoMensalidade> pagamentosMensalidade = new ArrayList<>();
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  PAM.MES_REFERENCIA, ")
        .append("  PAM.ANO_REFERECIA, ")
        .append("  PAG.VALOR, ")
        .append("  PAG.DATA_PAGAMENTO ")
                
        .append("FROM PAGAMENTO_MENSALIDADE PAM ")
                
        .append("INNER JOIN PAGAMENTO PAG ")
        .append("ON PAM.PAGAMENTO_ID = PAG.PAGAMENTO_ID ")
                
        .append("WHERE PAM.MENSALIDADE_ID = ? ");
        
        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{mensalidadeId});

        while(!consulta.fimConsulta()){
            
            PagamentoMensalidade pagamento = new PagamentoMensalidade(
                consulta.getBigDecimal("VALOR"),
                consulta.getDate("DATA_PAGAMENTO"),
                Meses.obterPorIndice(consulta.getInt("MES_REFERENCIA")),
                consulta.getInt("ANO_REFERENCIA")
            );
            
            pagamentosMensalidade.add(pagamento);
        
        }
        
        return pagamentosMensalidade;
    }

    private static ArrayList<BoxVaga> buscarBoxVagas(Conexao con, int usuarioId) throws EstacionarVagaException {
        
        ArrayList<BoxVaga> boxVagas = new ArrayList<>();
        
        Veiculo veiculo = null;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  EMPRESA_ID, ")
        .append("  CODIGO, ")
        .append("  VAGA, ")
        .append("  VEICULO_USUARIO_ID, ")
        .append("  DATA_HORA_ULT_ENTRADA, ")
        .append("  RESERVADA, ")
        .append("  DATA_HORA_ULT_SAIDA ")

        .append("FROM BOX_VAGA ")
                
        .append("WHERE USUARIO_ID = ? ");
        
        consulta.setSql(sql.toString());

        consulta.executarComando(new Object[]{usuarioId});

        while(!consulta.fimConsulta()){
            
            int veiculoUsuarioId = consulta.getInt("VEICULO_USUARIO_ID");
            
            if(veiculoUsuarioId != 0)
                veiculo = buscarVeiculoUsuario(con, veiculoUsuarioId);
            
            BoxVaga boxVaga = new BoxVaga(
                consulta.getInt("EMPRESA_ID"),
                consulta.getString("CODIGO"),
                consulta.getInt("VAGA"),
                consulta.getTimestamp("DATA_HORA_ULT_ENTRADA"),
                consulta.getTimestamp("DATA_HORA_ULT_SAIDA"),
                consulta.getBoolean("RESERVADA"),
                veiculo
            );
            
            boxVagas.add(boxVaga);
        }
        
        return boxVagas;
    }

    private static Veiculo buscarVeiculoUsuario(Conexao con, int veiculoUsuarioId) {
        
        Veiculo veiculo = null;
        
        Consulta consulta = con.novaConsulta();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("SELECT ")
        .append("  VEU.PLACA, ")
        .append("  VEI.MODELO, ")
        .append("  VEI.MARCA ")

        .append("FROM VEICULO_USUARIO VEU ")
                
        .append("INNER JOIN VEICULO VEI ")
        .append("ON VEU.VEICULO_ID = VEI.VEICULO_ID ")
        
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

    public static void updateCliente(
        Conexao con, 
        int usuarioId, 
        String nome, 
        String cpfCnpj, 
        ArrayList<String> emails, 
        ArrayList<Telefone> telefones, 
        ArrayList<Endereco> enderecos, 
        ArrayList<Veiculo> veiculos
    ) throws Exception{
        
        BancoUsuario.UpdateUsuarioSemLogin(con, usuarioId, nome, cpfCnpj, emails, telefones, enderecos);
        updateVeiculos(con, usuarioId, veiculos);
    }

    private static void updateVeiculos(Conexao con, int usuarioId, ArrayList<Veiculo> veiculosUpdate) throws Exception {
        
        Procedimento procedimento = con.novoProcedimento();
       
        for(Veiculo veiculo : veiculosUpdate){
            procedimento.executarProcedimento("INSERT_UPDATE_CLIENTE", new Object[]{usuarioId,veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca()});
        }
        
    }

    
    private static void updateMensalidade(Conexao con, int usuarioId, Mensalidade mensalidade) {
        Update update = con.novoUpdate();
        
        StringBuilder sql = new StringBuilder();
        
        sql
        .append("UPDATE MENSALIDADE ")
        .append("SET ")
                
        .append("  VALOR_MENSALIDADE = = ? ")
        .append("  DATA_INICIO = ? ")
        .append("  DATA_FIM = ? ")
        .append("  DIA_VENCIMENTO = ? ")

        .append("WHERE USUARIO_ID = ? ");
        
        update.setSql(sql.toString());
        
        update.executarComando(
            new Object[]{
                usuarioId, 
                mensalidade.getValorMensalidade(), 
                mensalidade.getDataInicio(), 
                mensalidade.getDataFim(),
                mensalidade.getDiaVencimento()
            }
        );
    }

}
