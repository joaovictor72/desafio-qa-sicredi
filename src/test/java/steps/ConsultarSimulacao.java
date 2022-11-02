package steps;

import base.Base;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Pessoa;

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
        Response response = get(BASE_URL_LOCAL + "/api/v1/simulacoes/66414919004");
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Description("Se o CPF não possuir uma simulação o HTTP Status 404 é retornado")
    @Test
    void consultaSimulacaoPeloCPFnaoExistente() {
        Response response = get(BASE_URL_LOCAL+ "/api/v1/simulacoes/" + Pessoa.geraCPF());
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }
}
