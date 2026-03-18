package model;

public class Funcionario {
    private int          id;
    private String       matricula;
    private String       nomeCompleto;
    private Departamento departamento;

    public Funcionario(int id, String matricula, String nomeCompleto, Departamento departamento) {
        this.id           = id;
        this.matricula    = matricula;
        this.nomeCompleto = nomeCompleto;
        this.departamento = departamento;
    }

    public int          getId()           { return id; }
    public String       getMatricula()    { return matricula; }
    public String       getNomeCompleto() { return nomeCompleto; }
    public Departamento getDepartamento() { return departamento; }
}