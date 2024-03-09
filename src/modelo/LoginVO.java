package modelo;


/**
 *
 * @author cralves
 */
public class LoginVO {
    //Atributos
    private int idLogin;
    private String login, senha;
    
    //Métodos Acessores

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}//fecha a classe LoginVO
