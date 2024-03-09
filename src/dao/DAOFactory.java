/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

public class DAOFactory {
    
    //Instanciando o objeto a classe ProdutoDAO
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static ColaboradoresDAO colaboradorDAO = new ColaboradoresDAO();
    private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static CompraDAO compraDAO = new CompraDAO();
    
    //Fazendo uma cópia dos métodos da classe ProdutoDAO e disponibilizar para a classe que solicitar
    public static ProdutoDAO getProdutoDAO(){
        return produtoDAO;
    }//fim getProdutoDAO
    public static ColaboradoresDAO getColaboradoresDAO(){
        return colaboradorDAO;
    }
    public static FornecedorDAO getFornecedorDAO(){
        return fornecedorDAO;
    }
    public static ClienteDAO getClienteDAO(){
        return clienteDAO;
    }
    public static CompraDAO getCompraDAO(){
        return compraDAO;
    }
}//fim DAOFactory
