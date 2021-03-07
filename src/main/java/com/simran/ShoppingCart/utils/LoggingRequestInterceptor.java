package com.simran.ShoppingCart.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
            final ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        response = log(request, body, response);

        return response;
    }

    private ClientHttpResponse log(final HttpRequest request, final byte[] body, final ClientHttpResponse response) throws IOException {
        final ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
        logger.info("----------------------- THIRD PARTY API REQUEST BEGIN -------------------------");;
        logger.info("Method: " +request.getMethod().toString());
        logger.info("URI: " +request.getURI().toString());
        logger.info("Headers     : {}", request.getHeaders() );
        logger.info("Request Body: " + new String(body));
      logger.info("Response body: " + IOUtils.toString(responseCopy.getBody()));
        logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~ THIRD PARTY API REQUEST END ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    
        return responseCopy;
    }

}
