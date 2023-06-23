package Repositorio;

import enums.MetodoRequisicao;
import enums.TipoConteudo;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author marce
 */

public class RequisicaoHttps {

    private final HashMap<String, String> requestProperty;
    public ArrayList<Parametro> parametros = new ArrayList<>();
    private final MetodoRequisicao metodo;
    private final TipoConteudo tipoConteudo;
    private final String endereco;
    private SSLSocketFactory sockFact;
    private String chaveArquivo;
    private File arquivo;
    private String corpoRaw;
    private String boundary = UUID.randomUUID().toString();
    private final int [] respostasServidor = {200,201,403,406,415};

    public RequisicaoHttps(
        String enderecoRequisicao,
        MetodoRequisicao metodoRequisicao,
        TipoConteudo tipoConteudo
    ) {
        this.endereco = enderecoRequisicao;
        this.metodo = metodoRequisicao;
        this.tipoConteudo = tipoConteudo;
        this.requestProperty = new HashMap<>();		
    }

    public void addRequestProperty(String parametro, String valor){
        this.requestProperty.put(parametro, valor);		
    }

    public void addParametro(String chave, ArrayList<String> valores) {
        addParametro(chave, valores, true);
    }

    public void addParametro(String chave, ArrayList<String> valores, boolean codificar) {
        
        if(parametroExiste(chave))
            throw new NoSuchElementException("O parâmetro " + chave + " já existe, não é possível inserir duas chaves iguais!");

        parametros.add(new Parametro(chave, valores, codificar));

    }

    public void addParametroJson(String chave, Object valores) {

        if(parametroExiste(chave))
            throw new NoSuchElementException("O parâmetro " + chave + " já existe, não é possível inserir duas chaves iguais!");
        
        HashMap<String, Object> jsonChaveParametro = new HashMap<>();

        jsonChaveParametro.put(chave, valores);

        this.corpoRaw = (String) Json.toJson(jsonChaveParametro);

    }

    public void addCertificado(String caminhoCertificado, String senhaCertificado) 
        throws KeyStoreException, 
        NoSuchAlgorithmException, 
        CertificateException, 
        IOException, 
        UnrecoverableKeyException, 
        KeyManagementException 
    {

        KeyStore clientStore = KeyStore.getInstance("PKCS12");	
        InputStream stream = new FileInputStream(caminhoCertificado);

        clientStore.load(stream, senhaCertificado.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(clientStore, senhaCertificado.toCharArray());
        KeyManager[] kms = kmf.getKeyManagers();

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kms, null, new SecureRandom());

        this.sockFact = sslContext.getSocketFactory();


    }

    public void addFile(String chaveArquivo, File arquivo) {
        this.chaveArquivo = chaveArquivo;
        this.arquivo = arquivo;
    }

    public void addCorpoRaw(String corpoRaw) {
        this.corpoRaw = corpoRaw;
    }
    
    public Retorno executarRequisicao() throws IOException {

        Retorno retorno = new Retorno();

        try {

            if(this.metodo == MetodoRequisicao.PATCH)
                allowMethods(metodo.getDescricao());

            if(this.tipoConteudo == TipoConteudo.MULTFORMDATA) {
                retorno = executarRequisicaoMultFormData();
                return retorno;
            }

            URL url = new URL(this.endereco);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod(this.metodo.getDescricao());
            if(this.metodo != MetodoRequisicao.GET)
                con.setRequestProperty("Content-Type", this.tipoConteudo.getValor());
            if (this.tipoConteudo == TipoConteudo.JSON)
                addRequestProperty("Accept", this.tipoConteudo.getValor());


            if(sockFact != null)
                con.setSSLSocketFactory(sockFact);

            for(Map.Entry<String, String> entrada : this.requestProperty.entrySet()){
                con.setRequestProperty(entrada.getKey(), entrada.getValue());
            }

            con.setDoOutput(true);

            if(this.metodo != MetodoRequisicao.GET) {
                try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                    out.writeBytes(montarCorpoRequisicao());
                    out.flush();
                }
            }

            InputStream in;
            int status = con.getResponseCode();

            if (isErro(status))
                in = con.getErrorStream();
            else
                in = con.getInputStream();			        		        	

            String contentEncoding = con.getContentEncoding() == null ? "" : con.getContentEncoding();

            if (!contentEncoding.contentEquals("")) {			
                if (contentEncoding.equalsIgnoreCase("gzip"))
                    in = new GZIPInputStream(in);
                else if (contentEncoding.equalsIgnoreCase("deflate"))
                    in = new InflaterInputStream(in, new Inflater(true));
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }        

            if (isErro(status))
                retorno.definirErro(response.toString());
            else
                retorno.setDados(response.toString());			
        }
        catch(IOException e) {
            retorno.definirErro(e.getMessage());
        }

        return retorno;		
    }

    private String montarCorpoRequisicao() {
        StringBuilder corpoRequisicao = new StringBuilder();

        switch(tipoConteudo) {

            case JSON:
                corpoRequisicao.append(this.corpoRaw);
                break;
                
            case RAW:
                corpoRequisicao.append(this.corpoRaw);
                break;

            case URLENCODED:
                for(Parametro parametro: this.parametros) {
                    String append = (corpoRequisicao.toString().isEmpty() ? "" : "&") + parametro.getChave() + "=" + formataValor(parametro.getValores());
                    corpoRequisicao.append(append);
                }
                break;

            default:
                break;
        }

        return corpoRequisicao.toString();
    }

    private boolean parametroExiste(String chave) {
        for(Parametro parametro: this.parametros) {
            if(parametro.getChave().equals(chave))
                return true;
        }
        return false;
    }

    private String formataValor(ArrayList<String> valores) {
        return valores.size() > 1 ? valores.toString() : valores.get(0);
    }

    private boolean isErro(int status) {
        for(int i: respostasServidor) {
            if(status == i)
                return false;
        }
        
        return true;
    }

    private Retorno executarRequisicaoMultFormData() throws IOException {
        Retorno retorno = new Retorno();

        boundary = UUID.randomUUID().toString();
        String LINE = "\r\n";
        URL url = new URL(this.endereco);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); 
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        for(Map.Entry<String, String> entrada : this.requestProperty.entrySet()){
            httpConn.setRequestProperty(entrada.getKey(), entrada.getValue());
        }

        OutputStream outputStream = httpConn.getOutputStream();
        
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "utf-8"), true)) {
            for(Parametro parametro: this.parametros) {
                writer.append("--" + boundary).append(LINE);
                writer.append("Content-Disposition: form-data; name=\"" + parametro.getChave() + "\"").append(LINE);
                writer.append("Content-Type: text/plain; charset=" + "utf-8").append(LINE);
                writer.append(LINE);
                writer.append(formataValor(parametro.getValores())).append(LINE);
            }
            
            if(this.arquivo != null) {
                String fileName = this.arquivo.getName();
                writer.append("--" + boundary).append(LINE);
                writer.append("Content-Disposition: form-data; name=\"" + this.chaveArquivo + "\"; filename=\"" + fileName + "\"").append(LINE);
                writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE);
                writer.append("Content-Transfer-Encoding: binary").append(LINE);
                writer.append(LINE);
                writer.flush();
                
                try (FileInputStream inputStream = new FileInputStream(this.arquivo)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    
                    while ((bytesRead = inputStream.read(buffer)) != -1)
                        outputStream.write(buffer, 0, bytesRead);
                    
                    outputStream.flush();
                }
                writer.append(LINE);
            }
            
            writer.flush();
            writer.append("--" + boundary + "--").append(LINE);
        }

        int status = httpConn.getResponseCode();
        boolean statusOk = (status == HttpURLConnection.HTTP_OK || status == 201);
        InputStream in = statusOk ? httpConn.getInputStream() : httpConn.getErrorStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] newBuffer = new byte[1024];
        int length;
        while ((length = in.read(newBuffer)) != -1)
                result.write(newBuffer, 0, length);

        httpConn.disconnect();

        if(statusOk)
                retorno.setDados(result.toString());
        else
            retorno.definirErro(result.toString());

        return retorno;
    }

    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");  // Remove final modifier          

            Field modifiersField = Field.class.getDeclaredField("modifiers"); //Pego os modificadores
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL); // torno a class acessível por ser statica
            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null, newMethods);
        } 

        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        
    }
	
}


