package Model;

import Db.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contato {
    private int id;
    private String nome;
    private String email;
    private String telefone;

    
    public Contato() {}

    
    public Contato(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    
    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    

    
    public static boolean criar(String nome, String email, String telefone) {
        String sql = "INSERT INTO Contatos (nome, email, telefone) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao criar contato: " + e.getMessage());
            return false;
        }
    }

    
    public static List<Contato> listarTodos() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT id, nome, email, telefone FROM Contatos ORDER BY nome";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contato contato = new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
                contatos.add(contato);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar contatos: " + e.getMessage());
        }

        return contatos;
    }

    
    public static Contato buscarPorId(int id) {
        String sql = "SELECT id, nome, email, telefone FROM Contatos WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar contato: " + e.getMessage());
        }

        return null;
    }

    
    public static boolean atualizar(int id, String nome, String email, String telefone) {
        String sql = "UPDATE Contatos SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setInt(4, id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar contato: " + e.getMessage());
            return false;
        }
    }

    
    public static boolean deletar(int id) {
        String sql = "DELETE FROM Contatos WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao deletar contato: " + e.getMessage());
            return false;
        }
    }

    
    public static Contato buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email, telefone FROM Contatos WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar por email: " + e.getMessage());
        }

        return null;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
