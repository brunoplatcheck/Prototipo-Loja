
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cralves
 */
public class ConexaoBanco {
    
    //Método que efetue a conexão com o Banco de Dados
    public Connection getConexao(){
        Connection c = null;
        try {
            String url = "jdbc:mysql://localhost:3306/loja?user=root&password=";
            c = DriverManager.getConnection(url);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao Conectar! " + se.getMessage());
        }//fecho o Catch
        return c;
    }//fecha o método getConexao
    
}//fecha a classe ConexaoBanco
