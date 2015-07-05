package api.controller;

import boot.Main;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;
import util.TestResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Wojciech on 2015-05-15.
 */
public class InventoryControllerAPITest {

    @BeforeClass
    public static void beforeClass() {
        Main.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void aNewPortfolioShouldBeCreated() {
        TestResponse res = request("POST", "/portfolios?code=PF1&name=GrassHost&description=ILikeIt");
        Map<String, String> json = res.json();
        assertEquals(200, res.status);
        assertEquals("PF1", json.get("code"));
        assertEquals("GrassHost", json.get("name"));
        assertNotNull(json.get("id"));
        assertNull(json.get("parentPortfolio"));
    }
    @Test
    public void aNewPortfolioShouldBeGotById() {
        TestResponse res = request("POST", "/portfolios?code=PF1&name=GrassHost&description=ILikeIt");
        Map<String, String> json = res.json();
        TestResponse resFin = request("GET", "/portfolios/"+json.get("id"));
        Map<String, String> jsonFin = resFin.json();
        assertEquals(200, res.status);
        assertEquals(200, resFin.status);
        assertEquals("PF1", jsonFin.get("code"));
        assertEquals("GrassHost", jsonFin.get("name"));
        assertEquals(json.get("id"), jsonFin.get("id"));
        assertNull(jsonFin.get("parentPortfolio"));
    }

    /*@Test
    public void aNewThreePortfoliosInHierarchyShouldBeCreated() {
        TestResponse resPf1 = request("POST", "/portfolios?code=PF1&name=GrassHost&description=ILikeIt");
        Map<String, String> pf1 = resPf1.json();
        String pf1id = pf1.get("id");
        TestResponse resPf2 = request("POST", "/portfolios?code=PF2&name=Historicon&description=ILikeIt2&parentComponent=" + pf1id);
        Map<String, String> pf2 = resPf1.json();
        TestResponse res = request("GET", "/portfolios");
        Map<String, String> effect = res.json();
        assertEquals(200, resPf1.status);
        assertEquals(200, resPf2.status);
        assertEquals(200, res.status);

    }*/

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }
}
