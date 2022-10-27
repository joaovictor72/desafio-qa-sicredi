package consultar_simulacao;

import base.Base;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class ConsultarSimulacao extends Base {
    @Description("Lista as simulações cadastradas.")
    @Test
    void consultarSimulacao() {
        Response response = get(BASE_URL_LOCAL + "/api/v1/simulacoes");
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Description("Retorna a simulação previamente cadastrada pelo CPF.")
    @Test
    void consultarSimulacaoPeloCPF() {
        Response response = get(BASE_URL_LOCAL + "/api/v1/simulacoes/58063164083");
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Description("Se o CPF não possuir uma simulação o HTTP Status 404 é retornado")
    @Test
    void consultaSimulacaoPeloCPFnaoExistente() {
        Response response = get(BASE_URL_LOCAL+ "/api/v1/simulacoes/01317496094");
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        String mensagem = response.asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(mensagem, "{\"mensagem\":\"CPF 01317496094 não encontrado\"}");
        Assert.assertEquals(statusCode, 404);
    }
}
