package dao;

import connection.ConnectionFactory;
import model.RegistroPonto;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroPontoDAO {

    public List<RegistroPonto> buscarPorFuncionarioEData(int funcionarioId, LocalDate data)
            throws SQLException {

        String sql = "SELECT id, funcionario_id, data_hora " +
                "FROM registro_ponto " +
                "WHERE funcionario_id = ? " +
                "  AND DATE(data_hora) = ? " +
                "ORDER BY data_hora ASC";

        List<RegistroPonto> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, funcionarioId);
            ps.setDate(2, Date.valueOf(data));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LocalDateTime dataHora =
                            rs.getTimestamp("data_hora").toLocalDateTime();
                    lista.add(new RegistroPonto(
                            rs.getInt("id"),
                            rs.getInt("funcionario_id"),
                            dataHora));
                }
            }
        }
        return lista;
    }
}