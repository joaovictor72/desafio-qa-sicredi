package steps;

import base.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Pessoa;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConsultarRestricao extends Base {
    @Description("Consulta CPF sem restrição")
    @Test
    void consultaCPFsemRestricao() {
        Response response = get(BASE_URL_LOCAL+ "/api/v1/restricoes/" + Pessoa.geraCPF());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    // Mensagem de erro diferente da documentação
    @Description("Consulta CPF com restrição")
    @Test
    void consultaCPFcomRestricao(){
        Response response = get(BASE_URL_LOCAL+ "/api/v1/restricoes/" + Pessoa.buscaCPFcomRestricao());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
