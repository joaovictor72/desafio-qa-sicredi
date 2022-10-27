package consultar_restricao;

import base.Base;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConsultarRestricao extends Base {
    @Description("Consulta CPF sem restrição")
    @Test
    void consultaCPFsemRestricao() {
        given()
                    .header("Content-Type", "application/json")
                    .get(BASE_URL_LOCAL + "/api/v1/restricoes/25668236005")
                .then()
                    .statusCode(204)
                    .body(is(not(nullValue())))
                    .log().all();
    }

    // Mensagem de erro diferente da documentação
    @Description("Consulta CPF com restrição")
    @Test
    void consultaCPFcomRestricao() {
        given()
                    .header("Content-Type", "application/json")
                    .get(BASE_URL_LOCAL + "/api/v1/restricoes/97093236014")
                .then()
                    .statusCode(200)
                    .body("mensagem", is("O CPF 97093236014 tem problema"))
                    .body(is(not(nullValue())))
                    .log().all();
    }

}
