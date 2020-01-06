package com.demo.bdd;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpHeaders;
import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.client.methods.HttpGet;
import wiremock.org.apache.http.client.methods.HttpPost;
import wiremock.org.apache.http.entity.StringEntity;
import wiremock.org.apache.http.impl.client.CloseableHttpClient;
import wiremock.org.apache.http.impl.client.HttpClients;

public class StepDefsIntegrationTest {

    String addURI;
    HttpHeaders httpHeaders;
    WireMockServer wireMockServer;
    CloseableHttpClient httpClient;
    HttpResponse response ;

    @Before()
    public void serve(){
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();

        WireMock.configureFor("localhost",wireMockServer.port());
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/readnstore/v1/store/csv"))
                .withHeader("content-type",WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing("test-data1"))
                .willReturn(WireMock.aResponse().withStatus(200)));

        WireMock.configureFor("localhost",wireMockServer.port());
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/readnstore/v1/data"))
                .withHeader("content-type",WireMock.equalTo("application/json"))
                .willReturn(WireMock.aResponse().withStatus(200)));
    }


    @Given("^I set POST data API service$")
    public void setPost(){
        addURI = "http://localhost:8080/readnstore/v1/store/csv";
        System.out.println("URI"+addURI);
    }

    @When("^I set request HEADER$")
    public void requestHeader() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept","application/json");
        httpHeaders.add("Content-Type","application/json");
    }

    @And("send a POST HTTP request")
    public void sendAPOSTHTTPRequest() {
        try {
            HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/readnstore/v1/store/csv");
            StringEntity entity = new StringEntity("test-data1");
            request.addHeader("content-type", "application/json");
            request.setEntity(entity);
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(request);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Then("I receive a valid Response")
    public void iReceiveAValidResponse() {
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        WireMock.verify(WireMock.postRequestedFor(WireMock.urlEqualTo("/readnstore/v1/store/csv"))
                .withHeader("content-type", WireMock.equalTo("application/json")));
        wireMockServer.stop();
    }

    @Given("I set GET data API service")
    public void iSetGETDataAPIService() {
        addURI = "http://localhost:8080/readnstore/v1/data";
        System.out.println("URI"+addURI);
    }

    @And("send a GET HTTP request")
    public void sendAGETHTTPRequest() {
        try {
            HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/readnstore/v1/data");
            request.addHeader("content-type", "application/json");
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(request);
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Then("I receive a valid HTTP Response code {int}")
    public void iReceiveAValidHTTPResponseCode(int arg0) {
        Assert.assertEquals(arg0, response.getStatusLine().getStatusCode());
        wireMockServer.stop();
    }
}