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
           
            stmt.setString(6, cli.getCelular());        
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getQuantidade());
            stmt.setString(10, cli.getAdicional());
            stmt.setString(11, cli.getSabor());
            stmt.setString(12, cli.getEspeciais());
    
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
                
                cli.setCelular(rs.getString("celular"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setQuantidade(rs.getInt("quantidade"));
                cli.setAdicional(rs.getString("adicional"));
                cli.setSabor(rs.getString("sabor"));
                cli.setEspeciais(rs.getString("especiais"));
                 
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
                
                cli.setCelular(rs.getString("celular"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setQuantidade(rs.getInt("quantidade"));
                cli.setAdicional(rs.getString("adicional"));
                cli.setSabor(rs.getString("sabor"));
                cli.setEspeciais(rs.getString("especiais"));
                
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
                cli.setCelular(rs.getString("celular"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setQuantidade(rs.getInt("quantidade"));
                cli.setAdicional(rs.getString("adicional"));
                cli.setSabor(rs.getString("sabor"));
                cli.setEspeciais(rs.getString("especiais"));              
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
            //stmt.setInt(1,cli.getId());
            
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
            
            stmt.setString(6, cli.getCelular());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getQuantidade());
            stmt.setString(10, cli.getAdicional());
            stmt.setString(11, cli.getSabor());
            stmt.setString(12, cli.getEspeciais());
            
            //stmt.setInt(14, cli.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!!!");
                                 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ ex);           
        }
    }
}

