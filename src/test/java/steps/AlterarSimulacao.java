package steps;

import base.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pojo.Pessoa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AlterarSimulacao extends Base {
    @Description("Alteração realizada no nome e email")
    @Test
    public void alterarSimulacao() {
        Pessoa pessoa = new Pessoa("24094592008", "Alterado!!", "teste@teste333.com", 2000, 30, true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .put(BASE_URL_LOCAL + "/api/v1/simulacoes/24094592008")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(Pessoa.class);
        assertThat(pessoaInserida.getCpf(),is("24094592008"));
        assertThat(pessoaInserida.getNome(), is("Alterado!!"));
        assertThat(pessoaInserida.getEmail(), is("teste@teste333.com"));
        assertThat(pessoaInserida.getValor(), is(2000));
        assertThat(pessoaInserida.getParcelas(), is(30));
        assertEquals(pessoaInserida.isSeguro(), true);
    }

    @Description("Alterar Simulação de CPF não cadastrado no sistema")
    @Test
    public void alterarSimulacaoCPFnaoEncontrado() {
        Pessoa pessoa = new Pessoa("01317496094", "Alterado!!", "teste@teste333.com", 2000, 30, true);

        Pessoa pessoaInserida = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(pessoa)
                .when()
                    .put(BASE_URL_LOCAL + "/api/v1/simulacoes/01317496094")
                .then()
                    .log().all()
                    .statusCode(404)
                    .body("mensagem", is("CPF 01317496094 não encontrado"))
                    .extract()
                    .body()
                    .as(Pessoa.class);
    }
}

