/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.DAOFactory;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ProdutoVO;

public class ProdutoServicos { //deixa os metodos criados na ProdutoDAO disponiveis publicamente
    
    public void cadastrarProduto(ProdutoVO pVO)throws SQLException{
        
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        pDAO.cadastrarProduto(pVO);
        
    }//fim cadastrarProduto
    
    public ArrayList<ProdutoVO> buscarProduto() throws SQLException{
        
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        return pDAO.buscarProduto();
        
    }//fim buscarProduto
    
    public ArrayList<ProdutoVO> filtrarProduto(String query) throws SQLException{
        
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        return pDAO.filtrarProduto(query);        
    }//fim filtrarProduto
    
    public void deletarProduto(long idproduto) throws SQLException{
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        pDAO.deletarProduto(idproduto);
    }
    public void alterarProduto(ProdutoVO pVO) throws SQLException{
        ProdutoDAO pDAO = DAOFactory.getProdutoDAO();
        pDAO.alterarProduto(pVO);
    
    }
}//fim ProdutoServicos
