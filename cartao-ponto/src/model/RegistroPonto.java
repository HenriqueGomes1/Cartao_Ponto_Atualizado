package model;

import java.time.LocalDateTime;

public class RegistroPonto {
    private int           id;
    private int           funcionarioId;
    private LocalDateTime dataHora;

    public RegistroPonto(int id, int funcionarioId, LocalDateTime dataHora) {
        this.id            = id;
        this.funcionarioId = funcionarioId;
        this.dataHora      = dataHora;
    }

    public int           getId()            { return id; }
    public int           getFuncionarioId() { return funcionarioId; }
    public LocalDateTime getDataHora()      { return dataHora; }
}