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
import modelo.ColaboradorVO;
import persistencia.ConexaoBanco;

/**
 *
 * @author 181700028
 */
public class ColaboradoresDAO {
    private static ColaboradoresDAO colaboradoresDAO = new ColaboradoresDAO();
    
    //Fazendo uma cópia dos métodos da classe ColaboradoresDAO e disponibilizar para a classe que solicitar
    public void cadastrarColaborador(ColaboradorVO cVO)throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "insert into colaboradores values(null, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cVO.getNome());
            pstm.setString(2, cVO.getCPF());
            pstm.setString(3, cVO.getGenero());
            pstm.setDouble(4, cVO.getSalario());
            pstm.setString(5, cVO.getFuncao());
            pstm.setInt(6, cVO.getIdade());
            pstm.execute();
            pstm.close();
            
            
        } catch (SQLException se) {
            throw new SQLException("Erro no cadastro de Colaborador!");
        }finally{
            con.close();
        }//fim do try catch finally
        
    }//fim cadastrarColaborador
    
    public ArrayList<ColaboradorVO> buscarColaborador() throws SQLException{
        
        Connection con = new ConexaoBanco().getConexao(); // faz conexão com o BD
               
        try {
            String sql = "Select * from colaboradores";
            
            PreparedStatement pstm = con.prepareStatement(sql); //pstm q faz as alterações no BD
            
            ResultSet rs = pstm.executeQuery(); //armazena os dados que vieram
            ArrayList<ColaboradorVO> pro = new ArrayList<>();
            
            while(rs.next()){//rs.next = enquanto tiver um proximo, faz uma linha de cada vez
                ColaboradorVO cVO = new ColaboradorVO();
                
                cVO.setIdColaborador(rs.getLong("idcolaborador")); //pega as informações na coluna com o nome
                cVO.setNome(rs.getString("nome"));
                cVO.setCPF(rs.getString("cpf"));
                cVO.setGenero(rs.getString("genero"));
                cVO.setSalario(rs.getLong("salario"));
                cVO.setFuncao(rs.getString("funcao"));
                cVO.setIdade(rs.getInt("idade"));
                
                pro.add(cVO); //adiciona ao pro(arraylist) o cVO(informações do banco)
                
            }//fim do while
            
            pstm.close();
            
            return pro;
                        
        } catch(SQLException se){
            throw new SQLException("Erro ao buscar Colaborador"+ se.getMessage());
        }finally{
            con.close();
        }
        
    }//fim buscarProduto
    
    public ArrayList<ColaboradorVO> filtrarColaborador(String query)throws SQLException {
    
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            
            String sql = "select * from colaboradores " + query;
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            ArrayList<ColaboradorVO> pro = new ArrayList<>();
            
            while(rs.next()){
                ColaboradorVO cVO = new ColaboradorVO();
                
                cVO.setIdColaborador(rs.getLong("idcolaborador"));
                cVO.setNome(rs.getString("nome"));
                cVO.setCPF(rs.getString("cpf"));
                cVO.setGenero(rs.getString("genero"));
                cVO.setSalario(rs.getLong("salario"));
                cVO.setFuncao(rs.getString("funcao"));
                cVO.setIdade(rs.getInt("idade"));
                
                pro.add(cVO);                               
            }//fim do while
            
            pstm.close();
            
            return pro;
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao filtrar Colaborador" + se.getMessage());
        }finally{
            con.close();
        }//fim do try catch finally
        
}//fim filtrarColaborador
    
    public void deletarColaborador(long idcolaborador) throws SQLException{
       
        Connection con = new ConexaoBanco().getConexao();
        
        try {
            String sql = "delete from colaboradores where idcolaborador = ?";
            
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setLong(1, idcolaborador);
            pstm.execute();
            pstm.close();
            
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar colaborador!ColaboradorDAO"+ se.getMessage());
        }finally{
            con.close();
        }//fim da try catch finally
        
    }
    public void alterarColaborador(ColaboradorVO cVO) throws SQLException {
        Connection con = new ConexaoBanco().getConexao();

        try {
            String sql;
            sql = "Update colaboradores set "
                    + "nome = ' " + cVO.getNome() + " ', "
                    + "cpf = ' " + cVO.getCPF()+ " ', "
                    + "genero = ' " + cVO.getGenero()+ " ' , "
                    + "salario = " + cVO.getSalario()+ " , "
                    + "funcao = ' " + cVO.getFuncao()+ " ' , "
                    + "idade = " + cVO.getIdade()+ " "
                    + " where idcolaborador = " + cVO.getIdColaborador()+ " ";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException se) {
            throw new SQLException("Erro ao Alterar colaborador! " + se.getMessage());
        } finally {
            con.close();
        }//fim do Try catch finally
    }
}
