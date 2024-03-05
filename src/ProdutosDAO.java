/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(query);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            int resultado = prep.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar o produto.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
        } finally {
            try {
                prep.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    public void venderProduto(int idProduto) {
    conn = new conectaDAO().connectDB();
    String query = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    
    try {
        prep = conn.prepareStatement(query);
        prep.setInt(1, idProduto);
        
        int resultado = prep.executeUpdate();
        
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao vender o produto.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + e.getMessage());
    } finally {
        try {
            prep.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    conn = new conectaDAO().connectDB();
    String query = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();
    
    try {
        prep = conn.prepareStatement(query);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            produtosVendidos.add(produto);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        try {
            prep.close();
            resultset.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    return produtosVendidos;
}
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        String query = "SELECT * FROM produtos";
        
        try {
            prep = conn.prepareStatement(query);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                prep.close();
                resultset.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
        
        return listagem;
    }
}

