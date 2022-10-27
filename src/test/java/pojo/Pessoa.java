package pojo;

public class Pessoa {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private Integer valor;
    private Integer parcelas;
    private boolean seguro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcela) {
        this.parcelas = parcela;
    }
    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }


    public Pessoa(String cpf, String nome, String email, Integer valor, Integer parcela, Boolean seguro) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.valor = valor;
        this.parcelas = parcela;
        this.seguro = seguro;
    }
}