package com.nnx;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nnx.common.JsonUtil;
import com.nnx.controller.dto.CalculateProductCostRequest;
import com.nnx.controller.dto.CalculateProductCostResponse;
import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringIntegrationTest
@SpringBootTest
public class PriceEngineControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddOrUpdateProduct() throws Exception {

        ProvisionProductRequest provisionProductRequest = new ProvisionProductRequest();
        provisionProductRequest.setName("test-product-1");
        provisionProductRequest.setCategory("category1");
        provisionProductRequest.setPricePerUnit(8.75);
        provisionProductRequest.setPricePerCarton(175);
        provisionProductRequest.setUnitsPerCarton(20);

        String content = JsonUtil.convertObjectToJSON(provisionProductRequest);

        MvcResult mvcResult = mvc.perform(put("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ProvisionProductResponse provisionProductResponse = JsonUtil
                .convertJSONToObject(mvcResult.getResponse().getContentAsString(), ProvisionProductResponse.class);

        Assert.assertNotNull(provisionProductResponse);
        Assert.assertNotNull(provisionProductResponse.getProductId());
        Assert.assertNotNull(provisionProductResponse.getCreatedTime());
        Assert.assertNotNull(provisionProductResponse.getUpdatedTime());
        Assert.assertNotNull(provisionProductResponse.getProductConfig());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getProductId());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getConfigId());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getCreatedTime());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getUpdatedTime());
    }

    @Test
    public void testAddOrUpdateProductBadRequest() throws Exception {

        mvc.perform(put("/product")).andExpect(status().isBadRequest());
    }

    @Test
    public void testRetrieveProducts() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/products")).andExpect(status().isOk()).andReturn();
        ProvisionProductResponse[] provisionProductResponses = JsonUtil
                .convertJSONToObject(mvcResult.getResponse().getContentAsString(), ProvisionProductResponse[].class);

        Assert.assertTrue(provisionProductResponses != null && provisionProductResponses.length == 1);
        Assert.assertNotNull(provisionProductResponses[0].getProductId());
        Assert.assertEquals("test-product-1", provisionProductResponses[0].getName());
    }

    @Test
    public void testCalculateProductCost() throws Exception {

        double pricePerUnit = 8.75;
        double pricePerCarton = 175;
        long unitsPerCarton = 20;
        long numberOfProductUnits = 25;

        /*
         * Provisions a new product
         */
        ProvisionProductRequest provisionProductRequest = new ProvisionProductRequest();
        provisionProductRequest.setName("test-product-2");
        provisionProductRequest.setCategory("category2");
        provisionProductRequest.setPricePerUnit(pricePerUnit);
        provisionProductRequest.setPricePerCarton(pricePerCarton);
        provisionProductRequest.setUnitsPerCarton(unitsPerCarton);

        String content = JsonUtil.convertObjectToJSON(provisionProductRequest);
        MvcResult mvcResult = mvc.perform(put("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ProvisionProductResponse provisionProductResponse = JsonUtil
                .convertJSONToObject(mvcResult.getResponse().getContentAsString(), ProvisionProductResponse.class);

        Assert.assertNotNull(provisionProductResponse);
        Assert.assertNotNull(provisionProductResponse.getProductId());
        Assert.assertNotNull(provisionProductResponse.getProductConfig());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getProductId());
        Assert.assertNotNull(provisionProductResponse.getProductConfig().getConfigId());

        /*
         * Retrieves calculated product price
         */
        CalculateProductCostRequest calculateProductCostRequest = new CalculateProductCostRequest();
        calculateProductCostRequest.setProductId(provisionProductResponse.getProductId());
        calculateProductCostRequest.setNumberOfUnits(numberOfProductUnits);

        content = JsonUtil.convertObjectToJSON(calculateProductCostRequest);
        mvcResult = mvc.perform(post("/product/price").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        CalculateProductCostResponse calculateProductCostResponse = JsonUtil
                .convertJSONToObject(mvcResult.getResponse().getContentAsString(), CalculateProductCostResponse.class);

        Assert.assertNotNull(calculateProductCostResponse);
        Assert.assertNotNull(calculateProductCostResponse.getProductId());
        Assert.assertNotNull(calculateProductCostResponse.getProductName());
        Assert.assertNotNull(calculateProductCostResponse.getCalculatedTotalPrice());

        double numberOfRequiredCartons = Math.floor(numberOfProductUnits / unitsPerCarton);
        double numberOfRemainingUnits = numberOfProductUnits % unitsPerCarton;
        double expectedCalculatedCost = (numberOfRequiredCartons * pricePerCarton)
                + (numberOfRemainingUnits * pricePerUnit);
        Assert.assertTrue(expectedCalculatedCost == calculateProductCostResponse.getCalculatedTotalPrice());
    }

    @Test
    public void testCalculateProductCostBadRequest() throws Exception {

        mvc.perform(post("/product/price")).andExpect(status().isBadRequest());
    }
}
