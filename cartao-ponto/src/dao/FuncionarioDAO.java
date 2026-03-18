package dao;

import connection.ConnectionFactory;
import model.Departamento;
import model.Funcionario;

import java.sql.*;

public class FuncionarioDAO {

    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT f.id, f.matricula, f.nome_completo, " +
                "       d.id AS depto_id, d.nome AS depto_nome " +
                "FROM funcionario f " +
                "JOIN departamento d ON d.id = f.departamento_id " +
                "WHERE f.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Departamento depto = new Departamento(
                            rs.getInt("depto_id"),
                            rs.getString("depto_nome"));
                    return new Funcionario(
                            rs.getInt("id"),
                            rs.getString("matricula"),
                            rs.getString("nome_completo"),
                            depto);
                }
            }
        }
        return null;
    }
}