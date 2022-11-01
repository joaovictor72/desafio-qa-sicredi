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
        Pessoa pessoa = new Pessoa("24094592008", "Teste", "teste@teste.com", 2000, 30, true);

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
        assertThat(pessoaInserida.getCpf(),is("24094592008"));
        assertThat(pessoaInserida.getNome(), is("Teste"));
        assertThat(pessoaInserida.getEmail(), is("teste@teste.com"));
        assertThat(pessoaInserida.getValor(), is(2000));
        assertThat(pessoaInserida.getParcelas(), is(30));
        assertEquals(pessoaInserida.isSeguro(), true);
    }

    @Description("Simulação com CPF Null")
    @Test
    public void criarSimulacaoComErro() {
        Pessoa pessoa = new Pessoa(null, "Teste", "teste@teste.com", 2000, 30, true);

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
        Pessoa pessoa = new Pessoa("24094592008", "Teste", "teste@teste.com", 2000, 30, true);

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
