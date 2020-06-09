/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaychouzzz.security.filter;

import com.jaychouzzz.security.support.PhoneSmsCodeAuthenticationToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Processes an authentication form submission. Called
 * {@code AuthenticationProcessingFilter} prior to Spring Security 3.0.
 * <p>
 * Login forms must present two parameters to this filter: a username and password. The
 * default parameter names to use are contained in the static fields
 * {@link #SPRING_SECURITY_MOBILE_PHONE_KEY} and
 * {@link #SPRING_SECURITY_MOBILE_SMS_CODE_KEY}. The parameter names can also be changed by
 * setting the {@code usernameParameter} and {@code passwordParameter} properties.
 * <p>
 * This filter by default responds to the URL {@code /login}.
 *
 * @author Ben Alex
 * @author Colin Sampaleanu
 * @author Luke Taylor
 * @since 3.0
 */
public class SmsAuthenticationFilter extends
		AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	public static final String SPRING_SECURITY_MOBILE_PHONE_KEY = "phone";
	public static final String SPRING_SECURITY_MOBILE_SMS_CODE_KEY = "smsCode";

	private String phoneParameter = SPRING_SECURITY_MOBILE_PHONE_KEY;
	private String smsCodeParameter = SPRING_SECURITY_MOBILE_SMS_CODE_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public SmsAuthenticationFilter(AuthenticationManager manager) {
		super(new AntPathRequestMatcher("/mobile/login", "POST"));
		setAuthenticationManager(manager);
	}

	// ~ Methods
	// ========================================================================================================
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String phone = obtainPhone(request);
		String smsCode = obtainSmsCode(request);

		if (phone == null) {
			phone = "";
		}

		if (smsCode == null) {
			smsCode = "";
		}

		phone = phone.trim();

		PhoneSmsCodeAuthenticationToken authRequest = new PhoneSmsCodeAuthenticationToken(
				phone, smsCode);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * Enables subclasses to override the composition of the password, such as by
	 * including additional values and a separator.
	 * <p>
	 * This might be used for example if a postcode/zipcode was required in addition to
	 * the password. A delimiter such as a pipe (|) should be used to separate the
	 * password and extended value(s). The <code>AuthenticationDao</code> will need to
	 * generate the expected password in a corresponding manner.
	 * </p>
	 *
	 * @param request so that request attributes can be retrieved
	 *
	 * @return the password that will be presented in the <code>Authentication</code>
	 * request token to the <code>AuthenticationManager</code>
	 */
	@Nullable
	protected String obtainSmsCode(HttpServletRequest request) {
		return request.getParameter(smsCodeParameter);
	}

	/**
	 * Enables subclasses to override the composition of the username, such as by
	 * including additional values and a separator.
	 *
	 * @param request so that request attributes can be retrieved
	 *
	 * @return the username that will be presented in the <code>Authentication</code>
	 * request token to the <code>AuthenticationManager</code>
	 */
	@Nullable
	protected String obtainPhone(HttpServletRequest request) {
		return request.getParameter(phoneParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its details
	 * set
	 */
	protected void setDetails(HttpServletRequest request,
			PhoneSmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Sets the parameter name which will be used to obtain the phone from the login
	 * request.
	 *
	 * @param phoneParameter parameter name. Defaults to "phone".
	 */
	public void setPhoneParameter(String phoneParameter) {
		Assert.hasText(phoneParameter, "Username parameter must not be empty or null");
		this.phoneParameter = phoneParameter;
	}

	/**
	 * Sets the parameter name which will be used to obtain the smsCode from the login
	 * request..
	 *
	 * @param smsCodeParameter the parameter name. Defaults to "smsCode".
	 */
	public void setSmsCodeParameter(String smsCodeParameter ) {
		Assert.hasText(smsCodeParameter, "Password parameter must not be empty or null");
		this.smsCodeParameter = smsCodeParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter. If set to
	 * true, and an authentication request is received which is not a POST request, an
	 * exception will be raised immediately and authentication will not be attempted. The
	 * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
	 * authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getPhoneParameter() {
		return phoneParameter;
	}

	public final String getSmsCodeParameter() {
		return smsCodeParameter;
	}
}
