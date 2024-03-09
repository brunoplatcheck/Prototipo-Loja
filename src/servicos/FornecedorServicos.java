/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.FornecedorDAO;
import dao.DAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.FornecedorVO;

/**
 *
 * @author 181700028
 */
public class FornecedorServicos {
    public void cadastrarFornecedor(FornecedorVO fVO)throws SQLException{
        
        FornecedorDAO fDAO = DAOFactory.getFornecedorDAO();
        fDAO.cadastrarFornecedor(fVO);
        
    }//fim cadastrarFornecedor
    
    public ArrayList<FornecedorVO> buscarFornecedor() throws SQLException{
        
        FornecedorDAO fDAO = DAOFactory.getFornecedorDAO();
        return fDAO.buscarFornecedor();
        
    }//fim buscarProduto
    
    public ArrayList<FornecedorVO> filtrarFornecedor(String query) throws SQLException{
        
        FornecedorDAO fDAO = DAOFactory.getFornecedorDAO();
        return fDAO.filtrarFornecedor(query);        
    }//fim filtrarColaborador
    
    public void deletarFornecedor(long idfornecedor) throws SQLException{
        FornecedorDAO fDAO = DAOFactory.getFornecedorDAO();
        fDAO.deletarFornecedor(idfornecedor);
    }
    public void alterarFornecedor(FornecedorVO pVO) throws SQLException{
        FornecedorDAO fDAO = DAOFactory.getFornecedorDAO();
        fDAO.alterarFornecedor(pVO);
    
    }
    
}
