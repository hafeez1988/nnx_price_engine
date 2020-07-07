package com.nnx.controller;

import static com.nnx.common.CommonConstants.NNX_STATUS_CODE_DESCRPTION;
import static com.nnx.common.CommonConstants.NNX_STATUS_CODE_HEADER;
import static com.nnx.common.CommonConstants.NNX_STATUS_MESSAGE_DESCRIPTION;
import static com.nnx.common.CommonConstants.NNX_STATUS_MESSAGE_HEADER;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nnx.common.LoggerUtil;
import com.nnx.controller.dto.ProvisionProductRequest;
import com.nnx.controller.dto.ProvisionProductResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Controller
public class PriceEngineController {

    private final Logger logger = LoggerFactory.getLogger(PriceEngineController.class);

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
    public @ResponseBody ProvisionProductResponse addOrUpdateProduct(@PathVariable String clientId,
            @RequestBody ProvisionProductRequest provisionProductRequest, HttpServletRequest request) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        LoggerUtil.putParametersToLog("ADD_OR_UPDATE_PRODUCT", request.getRemoteAddr());

        /**
         * TODO: Needs implementation
         */

        stopWatch.stop();

        LoggerUtil.logInfo(logger, "Email config created successfully in {}", stopWatch);

        return null;
    }
}
