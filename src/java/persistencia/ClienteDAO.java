/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author renan
 */
public class ClienteDAO extends GeralDAO {

    private static final String INSERT = "insert into cliente(nome,endereco,email) values(?,?,?);";

    private static final String DELETE = "delete from cliente where idcliente = ?;";

    private static final String UPDATE = "update cliente set nome=?, endereco=?, email=? where idcliente = ?;";

    private static final String SELECT_ALL = "select idCliente,nome,endereco,email from cliente order by nome";
    
    private static final String SELECT_NOME = "select idCliente,nome,endereco,email from cliente WHERE nome LIKE ? order by nome;";

    public boolean inserir(Cliente cliente) {
        int retorno = getComando(INSERT, cliente.getNome(), cliente.getEndereco(), cliente.getEmail());
        return retorno > 0 ? true : false;
    }


    public void deletar(int id) {
        getComando(DELETE, id);
    }

    public void update(Cliente cliente) {
        getComando(UPDATE, cliente.getNome(), cliente.getEndereco(), cliente.getEmail(), cliente.getId());
    }

    public List<Cliente> getClientes() {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Cliente> listaClientes = new ArrayList();
        try {
            while (resultado.next()) {
                listaClientes.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    public List<Cliente> getClientesporNome(String nome){
        ResultSet resultado = getConsulta(SELECT_NOME, nome + "%");
        List<Cliente> listaClientes = new ArrayList();
        try {
            while (resultado.next()) {
                listaClientes.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
        
    }

    private Cliente popularCliente(ResultSet resultado) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultado.getInt("idCliente"));
        cliente.setNome(resultado.getString("nome"));
        cliente.setEndereco(resultado.getString("endereco"));
        cliente.setEmail(resultado.getString("email"));
        return cliente;
    }

}