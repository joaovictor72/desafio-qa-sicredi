package pojo;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }

        if (soma % 11 == 0 | soma % 11 == 1) { primDig = new Integer(0); }
        else { primDig = new Integer(11 - (soma % 11)); }

        soma = 0;peso = 11;

        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }
        soma += primDig.intValue() * 2;

        if (soma % 11 == 0 | soma % 11 == 1) { segDig = new Integer(0); }
        else { segDig = new Integer(11 - (soma % 11)); }

        return primDig.toString() + segDig.toString();
    }
    public static String geraCPF() {
        String iniciais = "";
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = new Integer((int) (Math.random() * 10));
            iniciais += numero.toString();
        }   return iniciais + calcDigVerif(iniciais);
    }
    public static String buscaCPFcomRestricao() {

        List<String> cpfComRestricao =
                Arrays.asList(
                        "97093236014",
                        "60094146012",
                        "84809766080",
                        "62648716050",
                        "26276298085",
                        "01317496094",
                        "55856777050",
                        "19626829001",
                        "24094592008",
                        "58063164083"
                );

        String randomCpf = cpfComRestricao.get(new Random().nextInt(cpfComRestricao.size()));

        return randomCpf;
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