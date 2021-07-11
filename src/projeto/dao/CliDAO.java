package projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projeto.jdbc.ConnectionFactory;
import projeto.model.Cli;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CliDAO {
    private Connection connection;
    
    public CliDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarCliente(Cli cli){
        try {
            String sql =
                    "INSERT INTO tb_clientes(nome,rg,cpf,email,telefone,celular,cep,endereco,numero,"
                    + "complemento, bairro,cidade,estado)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setString(6, cli.getCelular());
            stmt.setString(7, cli.getCep());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getNumero());
            stmt.setString(10, cli.getComplemento());
            stmt.setString(11, cli.getBairro());
            stmt.setString(12, cli.getCidade());
            stmt.setString(13, cli.getUf());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado! "+ ex);
        }
                 
    }
    
    public List<Cli> listarClientes(){
        try {
            List<Cli> lista = new ArrayList<>();            
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cli cli = new Cli();
                
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("estado"));
                
                lista.add(cli);
            }
            return lista;         
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);
            return null;
        }
    }
    
    public List<Cli> consultaPorNome(String nome){
        try {
            List<Cli> lista = new ArrayList<>();            
            String sql = "SELECT * FROM tb_clientes WHERE nome like ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cli cli = new Cli();
                
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("estado"));
                
                lista.add(cli);
            }
            return lista;         
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);
            return null;
        }
    }
    
    public Cli buscaPorNome(String nome){
        try {
            String sql = "SELECT * FROM tb_clientes WHERE nome = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            Cli cli = new Cli();
            
            if(rs.next()){                
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("estado"));                
            }
            return cli;      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            return null;
        }
    }
    
    public void excluirCliente(Cli cli){
          try {
                       
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,cli.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
                                 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);           
        }
        
    }
    
    public void alterarCliente(Cli cli){
         try {
                       
            String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, "
                    + "telefone=?, celular=?, cep=?, endereco=?, numero=?, "
                    + "complemento=?, bairro=?, cidade=?, estado=?"
                    + " where id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setString(6, cli.getCelular());
            stmt.setString(7, cli.getCep());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getNumero());
            stmt.setString(10, cli.getComplemento());
            stmt.setString(11, cli.getBairro());
            stmt.setString(12, cli.getCidade());
            stmt.setString(13, cli.getUf());
            
            stmt.setInt(14, cli.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!!!");
                                 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);           
        }
    }
}

