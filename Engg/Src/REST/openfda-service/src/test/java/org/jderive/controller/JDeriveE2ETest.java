package org.jderive.controller;

import org.apache.commons.collections.CollectionUtils;
import org.jderive.JDeriveApplication;
import org.jderive.api.JDeriveResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Durga on 6/24/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JDeriveApplication.class)
@WebIntegrationTest("server.port:0") //spring boot start the server with random port no.
public class JDeriveE2ETest {

    private RestTemplate restTemplate = null;

    private String baseURL;

    @Value("${local.server.port}")  //boot injects the port used for bootstart the application.
            int port;

    @Value("${server.address}")
    private String serverName;

    @Value("${server.context-path}")
    private String contextPath;

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();
        baseURL = "http://" + serverName + ":" + port + contextPath;
    }

    @Test
    public void testCountryApi() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/country/list", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

        if (CollectionUtils.isNotEmpty(jDeriveResponse.getCountryList())) {
            Long countryId = jDeriveResponse.getCountryList().get(0).getId();
            String countryCode = jDeriveResponse.getCountryList().get(0).getCode();
            jDeriveResponse = restTemplate.getForObject(baseURL + "/country/" + countryId, JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
            assertEquals(countryCode, jDeriveResponse.getCountryList().get(0).getCode());
        }
    }

    @Test
    public void testAgeGroupApi() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/agegroup/list", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

        if (CollectionUtils.isNotEmpty(jDeriveResponse.getAgeGroupList())) {
            Long ageGroupId = jDeriveResponse.getAgeGroupList().get(0).getId();
            String ageGroupName = jDeriveResponse.getAgeGroupList().get(0).getName();
            jDeriveResponse = restTemplate.getForObject(baseURL + "/agegroup/" + ageGroupId, JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
            assertEquals(ageGroupName, jDeriveResponse.getAgeGroupList().get(0).getName());
        }
    }

    @Test
    public void testWeightGroupApi() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/weightgroup/list", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

        if (CollectionUtils.isNotEmpty(jDeriveResponse.getWeightGroupList())) {
            Long weightGroupId = jDeriveResponse.getWeightGroupList().get(0).getId();
            String weightGroupName = jDeriveResponse.getWeightGroupList().get(0).getName();
            jDeriveResponse = restTemplate.getForObject(baseURL + "/weightgroup/" + weightGroupId, JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
            assertEquals(weightGroupName, jDeriveResponse.getWeightGroupList().get(0).getName());
        }
    }

    @Test
    public void testDrugApi() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/name/al", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

        if (CollectionUtils.isNotEmpty(jDeriveResponse.getDrugList())) {
            //Test DrugBy Id.
            Long drugId = jDeriveResponse.getDrugList().get(0).getId();
            String drugName = jDeriveResponse.getDrugList().get(0).getName();
            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/" + drugId, JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
            assertEquals(drugName, jDeriveResponse.getDrugList().get(0).getName());

            //characterization api test.
            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/" + drugId + "/characterization", JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

            //Reaction api test.
            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/" + drugId + "/reaction", JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

            //Spike test.
            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/" + drugId + "/spike", JDeriveResponse.class);

            //EventCountByDay with drug id.

            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/eventcount?drugId=" + drugId,
                    JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());

            //EventCountByMonth with drug id.

            jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/eventcount/month?drugId=" + drugId,
                    JDeriveResponse.class);
            assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
        }
    }

    @Test
    public void testEventCountByDay() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/eventcount", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
    }

    @Test
    public void testEventCountByMonth() {
        JDeriveResponse jDeriveResponse = restTemplate.getForObject(baseURL + "/drugs/eventcount/month", JDeriveResponse.class);
        assertEquals(HttpStatus.OK.toString(), jDeriveResponse.getStatusCode());
    }
}
