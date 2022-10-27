package deletar_simulacao;

import base.Base;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletarSimulacao extends Base {
    // Retorna 200 ao invés de 204, 204 não existe e resultará em erro
    @Description("Remove uma simulação previamente cadastrada pelo seu ID.")
    @Test
    public void deveRemoverUsuario() {
        given()
                    .log().all()
                .when()
                    .delete(BASE_URL_LOCAL + "/api/v1/simulacoes/11")
                .then()
                    .log().all()
                    .statusCode(204);
    }


   // Retorna 200 ao invés de 404, 404 não existe e resultará em erro
    @Description("Remove Usuário não existente")
    @Test
    public void deveRemoverUsuarioNaoExistente() {
        given()
                    .log().all()
                .when()
                    .delete(BASE_URL_LOCAL + "/api/v1/simulacoes/29")
                .then()
                    .log().all()
                    .statusCode(200);
    }
}
