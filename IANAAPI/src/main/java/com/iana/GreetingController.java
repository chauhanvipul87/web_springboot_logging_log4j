package com.iana;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GreetingController {
 
	/*http://www.codeproject.com/Articles/820877/Support-Both-Json-and-XML-Serializations-in-Spring*/ 
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    private static final Logger logger = Logger.getLogger(GreetingController.class);
 
    
    @Autowired
    private GreetingService greetingService;
    
    
    
    @ApiOperation(value = "getGreeting", nickname = "getGreeting")
    @RequestMapping(method = RequestMethod.GET, path="/greeting", produces={"application/json", "application/xml"})
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Niklas")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized")
            }) 
    
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	  	logger.error("Message logged at ERROR level");
    	    logger.warn("Message logged at WARN level");
    	    logger.info("Message logged at INFO level");
    	    logger.debug("Message logged at DEBUG level");

    	
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }
}