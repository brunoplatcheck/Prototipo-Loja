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
import modelo.ClienteVO;
import persistencia.ConexaoBanco;

/**
 *
 * @author 181700028
 */
public class ClienteDAO {
   public void cadastrarCliente(ClienteVO cVO)throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "insert into cliente values(null, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cVO.getNome());
            pstm.setString(2, cVO.getCpf());
            pstm.setString(3, cVO.getGenero());
            pstm.setString(4, cVO.getRua());
            pstm.setString(5, cVO.getNumero());
            pstm.setString(6, cVO.getComplemento());
            pstm.setString(7,cVO.getStatus());
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro no cadastro de cliente!");
        }finally{
            con.close();
        }//fim do try catch finally
        
    }//fim cadastrarCliente
    
    public ArrayList<ClienteVO> buscarCliente() throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao(); // faz conexão com o BD
               
        try {
            String sql = "Select * from cliente";
            
            PreparedStatement pstm = con.prepareStatement(sql); //pstm q faz as alterações no BD
            
            ResultSet rs = pstm.executeQuery(); //armazena os dados que vieram
            ArrayList<ClienteVO> pro = new ArrayList<>();
            
            while(rs.next()){//rs.next = enquanto tiver um proximo, faz uma linha de cada vez
                ClienteVO cVO = new ClienteVO();
                
                cVO.setIdCliente(rs.getInt("idcliente")); //pega as informações na coluna com o nome
                cVO.setNome(rs.getString("nome"));
                cVO.setCpf(rs.getString("cpf"));
                cVO.setGenero(rs.getString("genero"));
                cVO.setRua(rs.getString("rua"));
                cVO.setNumero(rs.getString("numero"));
                cVO.setComplemento(rs.getString("complemento"));
                cVO.setStatus(rs.getString("status"));
                
                pro.add(cVO); //adiciona ao pro(arraylist) o cVO(informações do banco)
                
            }//fim do while
            
            pstm.close();
            
            return pro;
                        
        } catch(SQLException se){
            throw new SQLException("Erro ao buscar cliente"+ se.getMessage());
        }finally{
            con.close();
        }
        
    }//fim buscarCliente
    
    public ArrayList<ClienteVO> filtrarCliente(String query)throws SQLException {
    
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            
            String sql = "select * from cliente " + query;
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<ClienteVO> pro = new ArrayList<>();
            
            while(rs.next()){
                ClienteVO cVO = new ClienteVO();
                
                cVO.setIdCliente(rs.getInt("idcliente"));
                cVO.setNome(rs.getString("nome"));
                cVO.setCpf(rs.getString("cpf"));
                cVO.setGenero(rs.getString("genero"));
                cVO.setRua(rs.getString("rua"));
                cVO.setNumero(rs.getString("numero"));
                cVO.setComplemento(rs.getString("complemento"));
                cVO.setStatus(rs.getString("status"));
                
                pro.add(cVO);                               
            }//fim do while
            
            pstm.close();
            
            return pro;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar Cliente" + se.getMessage());
        }finally{
            con.close();
        }//fim do try catch finally
        
}//fim filtrarCliente
    
    public void deletarCliente(long idcliente) throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "delete from cliente where idcliente = ?";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setLong(1, idcliente);
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar cliente!ClienteDAO"+ se.getMessage());
        }finally{
            con.close();
        }//fim da try catch finally
        
    }
    public void alterarCliente(ClienteVO cVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql;
            sql = "Update cliente set "
                    + "nome = ' " + cVO.getNome() + " ', "
                    + "cpf = ' " + cVO.getCpf()+ " ' , "
                    + "genero = ' " + cVO.getGenero()+ " ', "
                    + "rua = ' " + cVO.getRua()+ " ', "
                    + "numero = ' " + cVO.getNumero()+ " ', "
                    + "complemento = ' " + cVO.getComplemento()+ " ', "
                    + "status = ' " + cVO.getStatus()+ " '  "
                    + " where idcliente = " + cVO.getIdCliente()+ " ";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException se) {
            throw new SQLException("Erro ao Alterar cliente! " + se.getMessage());
        } finally {
            con.close();
        }//fim do Try catch finally
    }
}
