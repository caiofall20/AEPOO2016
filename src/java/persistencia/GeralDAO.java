/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caio
 */
public class GeralDAO {
    public ResultSet getConsulta(String sql, Object... parametros) {
        FabricaConexao conexao = new FabricaConexao();
        PreparedStatement comando = null;
        try {
            comando = conexao.getConexao().prepareStatement(sql);
            for (int i = 1; i <= parametros.length; i++) {
                comando.setObject(i, parametros[i - 1]);
            }
            return comando.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GeralDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getComando(String sql, Object... parametros) {
        FabricaConexao conexao = new FabricaConexao();
        PreparedStatement comando = null;
        try {
            comando = conexao.getConexao().prepareStatement(sql);
            for (int i = 1; i <= parametros.length; i++) {
                comando.setObject(i, parametros[i - 1]);
            }
            return comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GeralDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
