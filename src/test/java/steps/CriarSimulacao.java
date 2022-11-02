package steps;

import base.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pojo.Pessoa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class CriarSimulacao extends Base {
    @Description("Uma simulação cadastrada com sucesso retorna o HTTP Status 201")
    @Test
    public void criarSimulacao() {
        Pessoa pessoa = new Pessoa(Pessoa.geraCPF(), Pessoa.geraNome(), Pessoa.geraEmail(), Pessoa.geraValor(),Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(201)
                    .extract()
                    .body()
                    .as(Pessoa.class);
        assertEquals(pessoaInserida.isSeguro(), true);
    }

    @Description("Simulação com CPF Null")
    @Test
    public void criarSimulacaoComCPFNull() {
        Pessoa pessoa = new Pessoa(null, Pessoa.geraNome(), Pessoa.geraEmail(), Pessoa.geraValor(), Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                .body(pessoa)
                    .when()
                .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                    .then()
                    .log().all()
                    .statusCode(400)
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }

    @Description("Simulação com Nome Null")
    @Test
    public void criarSimulacaoComNomeNull() {
        Pessoa pessoa = new Pessoa(Pessoa.geraCPF(), null, Pessoa.geraEmail(), Pessoa.geraValor(), Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400)
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }


    @Description("Simulação com Email Null")
    @Test
    public void criarSimulacaoComEmailNull() {
        Pessoa pessoa = new Pessoa(Pessoa.geraCPF(), Pessoa.geraNome(), null, Pessoa.geraValor(), Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400)
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }

    @Description("Simulação com Valor Null")
    @Test
    public void criarSimulacaoComValorNull() {
        Pessoa pessoa = new Pessoa(Pessoa.geraCPF(), Pessoa.geraNome(), Pessoa.geraEmail(), null, Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400)
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }

    @Description("Simulação com Parcela Null")
    @Test
    public void criarSimulacaoComParcelaNull() {
        Pessoa pessoa = new Pessoa(Pessoa.geraCPF(), Pessoa.geraNome(), Pessoa.geraEmail(), Pessoa.geraValor(), null, true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400)
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }


    // Mensagem do erro 409 não tratada, retorna apenas o 400 com "CPF duplicado"
    @Description("Cadastro de CPF duplicado")
    @Test
    public void criarSimulacaoCPFexistente() {
        Pessoa pessoa = new Pessoa("17822386034", Pessoa.geraNome(), Pessoa.geraEmail(), Pessoa.geraValor(), Pessoa.geraParcelas(), true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .post(BASE_URL_LOCAL + "/api/v1/simulacoes")
                .then()
                    .log().all()
                    .statusCode(400)
                    .body("mensagem", is("CPF duplicado"))
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }
}
