/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caio
 */
public class FabricaConexao {

    private Connection conexao;

    public Connection getConexao() {

        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/aepoo20161?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }

    public void fecharConexao() {

        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
/*
    // testar conexão
    */
    public static void main(String[] args) throws SQLException {
        FabricaConexao fabrica = new FabricaConexao();
        PreparedStatement comando = fabrica.getConexao().prepareStatement("select * from cliente");
        ResultSet resultado = comando.executeQuery();
        while (resultado.next()) {
            System.out.println(resultado.getString("nome"));
            System.out.println(resultado.getString("email"));
        }
 comando = fabrica.getConexao().prepareStatement("insert into cliente (nome,endereco,email) values(?,?,?)");
 comando.setString(1, "luiz Antonio");
 comando.setString(2, "Macaiba");
 comando.setString(3, "luiz@gmail.com");
   comando.executeUpdate();
   
   
   fabrica.fecharConexao();
 
    }
}
