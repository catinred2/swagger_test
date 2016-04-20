package com.test.action;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.GetInfoParam;
import com.test.entity.GetInfoResult;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class TestAction  {
	private ObjectMapper objectMapper = new ObjectMapper();
	@RequestMapping(value = "/hello/world", method = { RequestMethod.POST })
	@ResponseBody
	@ApiOperation(value="Notes",response = GetInfoResult.class)
	public void getInfo() throws Exception {
		GetInfoParam param = readParamByBody(GetInfoParam.class);
		GetInfoResult result = new GetInfoResult();
		result.setText(param.getId().toString());
		writeValue(result);
	}
	protected HttpServletResponse getHttpServletResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	protected HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	protected void writeValue(Object value) throws Exception {
		writeValue(getHttpServletResponse(), value);
	}
	protected void writeValue(HttpServletResponse response, Object value) throws Exception {
		response.setContentType("application/json; charset=UTF-8");
		objectMapper.writeValue(response.getOutputStream(), value);
	}
	
	protected <T> T readParamByBody(Class<T> clazz) throws Exception {
		HttpServletRequest request = getHttpServletRequest();
		String body = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
		/**
		 * some business logic here, for e.g, logging, session.
		 */
		// doSomething();
		T t = objectMapper.readValue(body, clazz);
		return t;
	}
}
