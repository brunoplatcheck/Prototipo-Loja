/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dao.CompraDAO;
import dao.DAOFactory;
import java.sql.SQLException;
import modelo.ColaboradorVO;
import modelo.CompraVO;

/**
 *
 * @author 181700028
 */
public class CompraServicos {
    public void cadastrarCompra(CompraVO cVO)throws SQLException{
        
        CompraDAO cDAO = DAOFactory.getCompraDAO();
        cDAO.EntradaCompra(cVO);
        
    }
    public void Confirmar(CompraVO pVO) throws SQLException{
        CompraDAO cDAO = DAOFactory.getCompraDAO();
        cDAO.confirmarCompra(pVO);
    
    }
}
