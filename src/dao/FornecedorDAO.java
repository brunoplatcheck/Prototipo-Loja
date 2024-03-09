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
import modelo.FornecedorVO;
import persistencia.ConexaoBanco;

/**
 *
 * @author 181700028
 */
public class FornecedorDAO {
    private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    //Fazendo uma cópia dos métodos da classe FornecedorDAO e disponibilizar para a classe que solicitar
    public void cadastrarFornecedor(FornecedorVO fVO)throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "insert into fornecedor values(null, ?, ?, ?, ?)";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, fVO.getNome());
            pstm.setString(2, fVO.getCNPJ());
            pstm.setString(3, fVO.getTelefone());
            pstm.setString(4, fVO.getContato());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro no cadastro de Fornecedor!");
        }finally{
            con.close();
        }//fim do try catch finally
        
    }//fim cadastrarFornecedor
    
    public ArrayList<FornecedorVO> buscarFornecedor() throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao(); // faz conexão com o BD
               
        try {
            String sql = "Select * from fornecedor";
            
            PreparedStatement pstm = con.prepareStatement(sql); //pstm q faz as alterações no BD
            
            ResultSet rs = pstm.executeQuery(); //armazena os dados que vieram
            ArrayList<FornecedorVO> pro = new ArrayList<>();
            
            while(rs.next()){//rs.next = enquanto tiver um proximo, faz uma linha de cada vez
                FornecedorVO fVO = new FornecedorVO();
                
                fVO.setIdFornecedor(rs.getLong("idfornecedor")); //pega as informações na coluna com o nome
                fVO.setNome(rs.getString("nome"));
                fVO.setCNPJ(rs.getString("cnpj"));
                fVO.setTelefone(rs.getString("telefone"));
                fVO.setContato(rs.getString("contato"));
                
                pro.add(fVO); //adiciona ao pro(arraylist) o fVO(informações do banco)
                
            }//fim do while
            
            pstm.close();
            
            return pro;
                        
        } catch(SQLException se){
            throw new SQLException("Erro ao buscar Fornecedor"+ se.getMessage());
        }finally{
            con.close();
        }
        
    }//fim buscarFornecedor
    
    public ArrayList<FornecedorVO> filtrarFornecedor(String query)throws SQLException {
    
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            
            String sql = "select * from fornecedor " + query;
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<FornecedorVO> pro = new ArrayList<>();
            
            while(rs.next()){
                FornecedorVO fVO = new FornecedorVO();
                
                fVO.setIdFornecedor(rs.getLong("idfornecedor"));
                fVO.setNome(rs.getString("nome"));
                fVO.setCNPJ(rs.getString("cnpj"));
                fVO.setTelefone(rs.getString("telefone"));
                fVO.setContato(rs.getString("contato"));
                
                pro.add(fVO);                               
            }//fim do while
            
            pstm.close();
            
            return pro;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar Fornecedor" + se.getMessage());
        }finally{
            con.close();
        }//fim do try catch finally
        
}//fim filtrarFornecedor
    
    public void deletarFornecedor(long idfornecedor) throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "delete from fornecedor where idfornecedor = ?";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setLong(1, idfornecedor);
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar fornecedor!FornecedorDAO"+ se.getMessage());
        }finally{
            con.close();
        }//fim da try catch finally
    }
    public void alterarFornecedor(FornecedorVO fVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql;
            sql = "Update fornecedor set "
                    + "nome = ' " + fVO.getNome() + " ', "
                    + "cnpj = ' " + fVO.getCNPJ() + " ', "
                    + "telefone = ' " + fVO.getTelefone() + " ', "
                    + "contato = ' " + fVO.getContato() + " ' "
                    + " where idfornecedor = " + fVO.getIdFornecedor()+ " ";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException se) {
            throw new SQLException("Erro ao Alterar Fornecedor! " + se.getMessage());
        } finally {
            con.close();
        }//fim do Try catch finally
    }
}

    

