package com.jaychouzzz.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Classname MyAuthorizationCodeTokenResponseClient
 * @description 自定义票据获取策略  解决不能访问google facebook等外网网站
 * @Author chuanfang
 * @Date 2020/6/15 16:12
 * @Version 1.0
 */
@Component
public final class MyAuthorizationCodeTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    private static final String INVALID_TOKEN_RESPONSE_ERROR_CODE = "invalid_token_response";

    private Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter =
            new OAuth2AuthorizationCodeGrantRequestEntityConverter();

    private RestOperations restOperations;

    public MyAuthorizationCodeTokenResponseClient(RestTemplate restTemplate) {
        restTemplate.setMessageConverters(Arrays.asList(
                new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter()));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.restOperations = restTemplate;
    }

    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        Assert.notNull(authorizationCodeGrantRequest, "authorizationCodeGrantRequest cannot be null");

        RequestEntity<?> request = this.requestEntityConverter.convert(authorizationCodeGrantRequest);
        ResponseEntity<OAuth2AccessTokenResponse> response;
        try {
            response = this.restOperations.exchange(request, OAuth2AccessTokenResponse.class);
        } catch (RestClientException ex) {
            OAuth2Error oauth2Error = new OAuth2Error(INVALID_TOKEN_RESPONSE_ERROR_CODE,
                    "An error occurred while attempting to retrieve the OAuth 2.0 Access Token Response: " + ex.getMessage(), null);
            throw new OAuth2AuthorizationException(oauth2Error, ex);
        }
        OAuth2AccessTokenResponse tokenResponse = response.getBody();

        if (CollectionUtils.isEmpty(tokenResponse.getAccessToken().getScopes())) {
            // As per spec, in Section 5.1 Successful Access Token Response
            // https://tools.ietf.org/html/rfc6749#section-5.1
            // If AccessTokenResponse.scope is empty, then default to the scope
            // originally requested by the client in the Token Request
            tokenResponse = OAuth2AccessTokenResponse.withResponse(tokenResponse)
                    .scopes(authorizationCodeGrantRequest.getClientRegistration().getScopes())
                    .build();
        }

        return tokenResponse;
    }

    /**
     * Sets the {@link Converter} used for converting the {@link OAuth2AuthorizationCodeGrantRequest}
     * to a {@link RequestEntity} representation of the OAuth 2.0 Access Token Request.
     *
     * @param requestEntityConverter the {@link Converter} used for converting to a {@link RequestEntity} representation of the Access Token Request
     */
    public void setRequestEntityConverter(Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter) {
        Assert.notNull(requestEntityConverter, "requestEntityConverter cannot be null");
        this.requestEntityConverter = requestEntityConverter;
    }

    /**
     * Sets the {@link RestOperations} used when requesting the OAuth 2.0 Access Token Response.
     *
     * <p>
     * <b>NOTE:</b> At a minimum, the supplied {@code restOperations} must be configured with the following:
     * <ol>
     * </ol>
     *
     * @param restOperations the {@link RestOperations} used when requesting the Access Token Response
     */
    public void setRestOperations(RestOperations restOperations) {
        Assert.notNull(restOperations, "restOperations cannot be null");
        this.restOperations = restOperations;
    }
}
