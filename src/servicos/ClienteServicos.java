/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.DAOFactory;
import dao.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ClienteVO;
/**
 *
 * @author 181700028
 */
public class ClienteServicos {
 
    public void cadastrarCliente(ClienteVO cVO)throws SQLException{
        
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.cadastrarCliente(cVO);
        
    }//fim cadastrarColaborador
    
    public ArrayList<ClienteVO> buscarCliente() throws SQLException{
        
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.buscarCliente();
        
    }//fim buscarProduto
    
    public ArrayList<ClienteVO> filtrarCliente(String query) throws SQLException{
        
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        return cDAO.filtrarCliente(query);        
    }//fim filtrarColaborador
    
    public void deletarCliente(long idcliente) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.deletarCliente(idcliente);
    }
        public void alterarCliente(ClienteVO cVO) throws SQLException{
        ClienteDAO cDAO = DAOFactory.getClienteDAO();
        cDAO.alterarCliente(cVO);
    
    }

}
