/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.CompraVO;
import modelo.ProdutoVO;
import persistencia.ConexaoBanco;

/**
 *
 * @author 181700028
 */
public class CompraDAO {
    public void EntradaCompra(CompraVO cVO)throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "insert into compra values(null, ?, ?, ?)";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, (int) cVO.getIdCliente());
            pstm.setInt(2, (int) cVO.getIdProduto());
            pstm.setDouble(3, cVO.getValorTotal());
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro no entrada Compra!");
        }finally{
            con.close();
        }//fim do try catch finally   
    }//fim EntradaCompra, cadastra as informações de compra do cliente
    
    public ArrayList<CompraVO> buscarDadosProduto(String query)throws SQLException {
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            
            String sql = "select * from produto " + query;
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<CompraVO> pro = new ArrayList<>();
            
            while(rs.next()){
                CompraVO pVO = new CompraVO();
                
                pVO.setIdProduto(rs.getLong("idproduto"));
                pVO.setValorProduto(rs.getDouble("valorCusto"));
                pVO.setQuantidade(rs.getInt("quantidade"));
                
                pro.add(pVO);                               
            }//fim do while
            
            pstm.close();
            
            return pro;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar buscarDadosProduto" + se.getMessage());
        }finally{
            con.close();
        }//fim do try catch finally
        
}
    public ArrayList<CompraVO> buscarDadosCliente(String query)throws SQLException {
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            
            String sql = "select * from cliente " + query;
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<CompraVO> pro = new ArrayList<>();
            
            while(rs.next()){
                CompraVO pVO = new CompraVO();
                
                pVO.setIdProduto(rs.getLong("idcliente"));
                pVO.setCPF(rs.getString("nome"));
                
                pro.add(pVO);                               
            }//fim do while
            
            pstm.close();
            
            return pro;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar buscarDadosCliente" + se.getMessage());
        }finally{
            con.close();
        }//fim do try catch finally       
    }
    public void confirmarCompra(CompraVO pVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql;
            sql = "Update compra set "
                    + "idcliente = ' " + pVO.getIdCliente()+ " ', "
                    + "idproduto = " + pVO.getIdProduto()+ ", "
                    + "valorTotal = " + pVO.getValorTotal()+ " "
                    + " where idcompra = " + pVO.getIdCompra() + " ";
            sql = "Update produto set "
                    +"quantidade = ' " +pVO.getNovaQuantidade()+" '";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException se) {
            throw new SQLException("Erro ao Alterar Produto! " + se.getMessage());
        } finally {
            con.close();
        }//fim do Try catch finally
    }

}
