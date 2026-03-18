import dao.FuncionarioDAO;
import dao.RegistroPontoDAO;
import model.Funcionario;
import model.RegistroPonto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        // ── Parâmetros da consulta ──────────────────────────────
        int       funcionarioId = 1;
        LocalDate data          = LocalDate.of(2026, 3, 10);
        // ───────────────────────────────────────────────────────

        FuncionarioDAO   funcDAO  = new FuncionarioDAO();
        RegistroPontoDAO pontoDAO = new RegistroPontoDAO();

        Funcionario         funcionario = funcDAO.buscarPorId(funcionarioId);
        List<RegistroPonto> registros   = pontoDAO.buscarPorFuncionarioEData(funcionarioId, data);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");

        // ── Cabeçalho ───────────────────────────────────────────
        System.out.println();
        System.out.println("RELATÓRIO DE PONTO");
        System.out.println("Funcionário : " + funcionario.getNomeCompleto());
        System.out.println("Departamento: " + funcionario.getDepartamento().getNome());
        System.out.println("Data        : " + data.format(fmtData));
        System.out.println();
        System.out.printf("%-8s %-8s %s%n", "Entrada", "Saída", "Horas");
        System.out.println("----------------------------");

        // ── Pares entrada/saída ─────────────────────────────────
        int totalMinutos = 0;

        for (int i = 0; i + 1 < registros.size(); i += 2) {
            LocalTime entrada = registros.get(i)    .getDataHora().toLocalTime();
            LocalTime saida   = registros.get(i + 1).getDataHora().toLocalTime();

            int minutos = (int) java.time.Duration.between(entrada, saida).toMinutes();
            totalMinutos += minutos;

            System.out.printf("%-8s %-8s %02d:%02d%n",
                    entrada.format(fmtHora),
                    saida.format(fmtHora),
                    minutos / 60,
                    minutos % 60);
        }

        // ── Rodapé ──────────────────────────────────────────────
        System.out.println("----------------------------");
        System.out.printf("Total trabalhado: %02d:%02d%n",
                totalMinutos / 60,
                totalMinutos % 60);
    }
}