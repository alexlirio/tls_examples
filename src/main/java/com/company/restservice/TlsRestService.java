package com.company.restservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


@Path("/resource")
public class TlsRestService {
	
	private final static Logger log = Logger.getLogger(TlsRestService.class);

    
    @GET
    @Path("/get")
    public Response get(@Context HttpServletRequest httpServletRequest, @Context HttpHeaders httpHeaders) {
    	
		log.info(String.format("HTTP Request URL = %s", httpServletRequest.getRequestURL()));
		log.info(String.format("HTTP Request Header(s) = %s", httpHeaders.getRequestHeaders().toString()));
		
    	return Response.status(200)
    			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
    			.entity("{\"result\":\"success\"}")
    			.build();
    }
    
}
