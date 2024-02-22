
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
   public Connection connectDB(){
        Connection conn = null;
        
        try {
            // Altere as informações abaixo de acordo com o seu banco de dados MySQL
            String host = "localhost"; // Endereço do servidor do banco de dados
            String port = "3306"; // Porta do servidor do banco de dados
            String database = "leilao"; // Nome do banco de dados
            String username = "root"; // Nome de usuário do banco de dados
            String password = "theteits1213"; // Senha do banco de dados
            
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&useSSL=false";
            
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage());
        }
        return conn;
    }
    
}
