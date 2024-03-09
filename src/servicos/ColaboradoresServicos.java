/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.DAOFactory;
import dao.ColaboradoresDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.ColaboradorVO;

/**
 *
 * @author 181700028
 */
public class ColaboradoresServicos {
    public void cadastrarColaborador(ColaboradorVO cVO)throws SQLException{
        
        ColaboradoresDAO cDAO = DAOFactory.getColaboradoresDAO();
        cDAO.cadastrarColaborador(cVO);
        
    }//fim cadastrarColaborador
    
    public ArrayList<ColaboradorVO> buscarColaborador() throws SQLException{
        
        ColaboradoresDAO cDAO = DAOFactory.getColaboradoresDAO();
        return cDAO.buscarColaborador();
        
    }//fim buscarProduto
    
    public ArrayList<ColaboradorVO> filtrarColaborador(String query) throws SQLException{
        
        ColaboradoresDAO cDAO = DAOFactory.getColaboradoresDAO();
        return cDAO.filtrarColaborador(query);        
    }//fim filtrarColaborador
    
    public void deletarColaborador(long idcolaborador) throws SQLException{
        ColaboradoresDAO cDAO = DAOFactory.getColaboradoresDAO();
        cDAO.deletarColaborador(idcolaborador);
    }
        public void alterarColaborador(ColaboradorVO cVO) throws SQLException{
        ColaboradoresDAO cDAO = DAOFactory.getColaboradoresDAO();
        cDAO.alterarColaborador(cVO);
    
    }
    
}

