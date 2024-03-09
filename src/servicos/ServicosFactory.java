/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

public class ServicosFactory {
    
    private static ProdutoServicos produtoServicos = new ProdutoServicos();
    
    private static ColaboradoresServicos colaboradoresServicos = new ColaboradoresServicos();
    
    private static FornecedorServicos fornecedorServicos = new FornecedorServicos();
    
    private static ClienteServicos clienteServicos = new ClienteServicos();
    
    private static CompraServicos compraServicos = new CompraServicos();
    
    public static ProdutoServicos getProdutoServicos(){
        return produtoServicos;
    }//fim getProdutoServicos
    public static ColaboradoresServicos getColaboradoresServicos(){
        return colaboradoresServicos;
    }
    public static FornecedorServicos getFornecedorServicos(){
        return fornecedorServicos;
    }
    public static ClienteServicos getClienteServicos(){
        return clienteServicos;
    }
    public static CompraServicos getCompraServicos(){
        return compraServicos;
    }
}//fim ServicosFactory
