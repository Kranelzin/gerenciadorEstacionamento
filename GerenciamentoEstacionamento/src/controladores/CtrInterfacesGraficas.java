package controladores;

import interfaceGrafica.Menu;
import interfaceGrafica.cadastros.CadastroInfoBasica;
import interfaceGrafica.cadastros.CadastroInfoEndereco;
import interfaceGrafica.cadastros.CadastroInfoVeiculo;
import interfaceGrafica.cadastros.MenuCadastros;

/**
 *
 * @author marce
 */
public class CtrInterfacesGraficas {
    
    private static CadastroInfoBasica cadastroInfoBasica;
    private static CadastroInfoEndereco cadastroInfoEndereco;
    private static MenuCadastros menuCadastros;
    private static Menu menu;
    private static CadastroInfoVeiculo cadastroInfoVeiculo;

    public static CadastroInfoBasica getCadastroInfoBasica() {
        return cadastroInfoBasica;
    }

    public static CadastroInfoEndereco getCadastroInfoEndereco() {
        return cadastroInfoEndereco;
    }

    public static MenuCadastros getMenuCadastros() {
        return menuCadastros;
    }

    public static Menu getMenu() {
        return menu;
    }
    
    public static CadastroInfoVeiculo getCadastroInfoVeiculo(){
        return cadastroInfoVeiculo;
    }
    public static void setCadastroInfoBasica(CadastroInfoBasica novaTela){
        cadastroInfoBasica = novaTela;
    }
    
    public static void setCadastroInfoEndereco(CadastroInfoEndereco novaTela){
        cadastroInfoEndereco = novaTela;
    }
    
    public static void setmMenuCadastros(MenuCadastros novaTela){
        menuCadastros = novaTela;
    }
    
    public static void setMenu(Menu novaTela){
        menu = novaTela;
    }

    public static void setCadastroVeiculo(CadastroInfoVeiculo novaTela) {
        cadastroInfoVeiculo = novaTela;
    }
    
    public static void limpar() {
        menu = null;
        menuCadastros = null;
        cadastroInfoBasica = null;
        cadastroInfoEndereco = null;
        cadastroInfoVeiculo = null;
    }
  
}
