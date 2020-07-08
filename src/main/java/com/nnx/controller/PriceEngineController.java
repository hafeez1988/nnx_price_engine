package com.nnx.controller;

import static com.nnx.common.CommonConstants.NNX_STATUS_CODE_DESCRPTION;
import static com.nnx.common.CommonConstants.NNX_STATUS_CODE_HEADER;
import static com.nnx.common.CommonConstants.NNX_STATUS_MESSAGE_DESCRIPTION;
import static com.nnx.common.CommonConstants.NNX_STATUS_MESSAGE_HEADER;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnx.application.PriceEngineService;
import com.nnx.common.LoggerUtil;
import com.nnx.controller.dto.CalculateProductCostRequest;
import com.nnx.controller.dto.CalculateProductCostResponse;
import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Controller
public class PriceEngineController {

    private final Logger logger = LoggerFactory.getLogger(PriceEngineController.class);

    @Autowired
    private PriceEngineService priceEngineService;

    @ApiOperation(value = "Provisions or updates a product detail", notes = "Creates or updates a product detail with its product configurations ", tags = {
        "addOrUpdateProduct"})
    @ApiResponses({@ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)}),
        @ApiResponse(code = 500, message = "Internal Server Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)})})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/product", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProvisionProductResponse addOrUpdateProduct(
            @RequestBody ProvisionProductRequest provisionProductRequest, HttpServletRequest request) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        LoggerUtil.putParametersToLog("ADD_OR_UPDATE_PRODUCT", request.getRemoteAddr());

        ProvisionProductResponse provisionProductResponse = priceEngineService
                .provisionProduct(provisionProductRequest);

        stopWatch.stop();

        LoggerUtil.logInfo(logger, "Product provisioned successfully in {}", stopWatch);

        return provisionProductResponse;
    }

    @ApiOperation(value = "Retrieves all products details", notes = "Returns a list of all products and configuration details ", tags = {
        "retrieveProducts"})
    @ApiResponses({@ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)}),
        @ApiResponse(code = 500, message = "Internal Server Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)})})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ProvisionProductResponse> retrieveProducts(HttpServletRequest request) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        LoggerUtil.putParametersToLog("RETRIEVE_PRODUCTS", request.getRemoteAddr());

        List<ProvisionProductResponse> productsListResponse = priceEngineService.listProducts();

        stopWatch.stop();

        LoggerUtil.logInfo(logger, "Products retrieved successfully in {}", stopWatch);

        return productsListResponse;
    }

    @ApiOperation(value = "Calculate product cost", notes = "Calculates the product price per unit or carton ", tags = {
        "calculateProductCost"})
    @ApiResponses({@ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)}),
        @ApiResponse(code = 500, message = "Internal Server Error with detail headers", responseHeaders = {
            @ResponseHeader(name = NNX_STATUS_CODE_HEADER, description = NNX_STATUS_CODE_DESCRPTION, response = String.class),
            @ResponseHeader(name = NNX_STATUS_MESSAGE_HEADER, description = NNX_STATUS_MESSAGE_DESCRIPTION, response = String.class)})})
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/product/price", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CalculateProductCostResponse calculateProductCost(
            @RequestBody CalculateProductCostRequest calculateProductCostRequest, HttpServletRequest request) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        LoggerUtil.putParametersToLog("CALCULATE_PRODUCT_COST", request.getRemoteAddr());

        CalculateProductCostResponse calculateProductCostResponse = priceEngineService
                .calculateCost(calculateProductCostRequest);

        stopWatch.stop();

        LoggerUtil.logInfo(logger, "Product cost calculated successfully in {}", stopWatch);

        return calculateProductCostResponse;
    }
}
