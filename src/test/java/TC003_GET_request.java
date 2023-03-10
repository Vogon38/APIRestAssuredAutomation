import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_request {

    Dotenv dotenv = Dotenv.configure().directory(".idea/.env").load();
    String secret = dotenv.get("API_KEY");

    @Test
    void googleMapTest() {

        //Specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-27.2088951,-49.6341994&radius=1500&type=supermarket&key="+ secret);

        //Print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " +responseBody);

        //Validating headers
        String contentType = response.header("Content-Type");
        System.out.println("Content type is: " +contentType);
        Assert.assertEquals(contentType, "application/xml; charset=UTF-8");

        String contentEncoding = response.header("Content-Encoding");
        System.out.println("Content encoding is: " +contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");

    }

}
