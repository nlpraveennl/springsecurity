Exception Handling can be made local to Controller or you can define global exception handler with a class annotated with `@ControllerAdvice` 

### 1. Handling Exception locally in controller.
```java
@RestController
public class MyRestController1
{
	@Autowired
	UserService userService;
	
	@ExceptionHandler(NullPointerException.class)
	public Error sendNullPointerResponse()
	{
		Error error = new Error(500, "NullPointerException caught");
		return error;
	}
	
	@ExceptionHandler(Exception.class)
	public Error sendExceptionResponse()
	{
		Error error = new Error(500, "Exception caught");
		return error;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public Error sendEmpNotFoundResponse()
	{
		Error error = new Error(404, "Employee not found");
		return error;
	}
	
	@RequestMapping("/api/error1/nullpointerException")
	public RoleMaster getNullPointerException()         
	{
		throw new NullPointerException();
	}
	
	@RequestMapping("/api/error1/arithmeticException")
	public RoleMaster getArithmeticException()         
	{
		throw new ArithmeticException("Devide by zero");
	}
	
	@RequestMapping("/api/error1/exception")
	public RoleMaster getException() throws Exception         
	{
		throw new Exception();
	}
	
	@RequestMapping("/api/user/{userId}")
	public UserDetails getEmployee(@PathVariable("userId") int userId) throws Exception         
	{
		UserDetails user = userService.getUser(userId);
		if(user != null)
		{
			return user;
		}
		else
		{
			throw new UserNotFoundException(userId);
		}
	}
}
```

#### And the output given below

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-1.png)

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-2.png)

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-3.png)

### 2. Declaring and configuring global exception handler

RestController For Demo
```java
@RestController
public class MyRestController2
{
	@RequestMapping("/api/error2/nullpointerException")
	public RoleMaster getNullPointerException()         
	{
		throw new NullPointerException("Null pointer exception");
	}
	
	@RequestMapping("/api/error2/arithmeticException")
	public RoleMaster getArithmeticException()         
	{
		throw new ArithmeticException("Devide by zero");
	}
	
	@RequestMapping("/api/error2/exception")
	public RoleMaster getException() throws Exception         
	{
		throw new Exception("Excpetion");
	}
}
```

Declare GlobalExceptionHandler component with methods annotated with `@ExceptionHandler`
```java
@ControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handlerNotFoundError(Exception ex)
	{
		return "404";
	}

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handle(RuntimeException ex)
	{
		ModelAndView model = new ModelAndView("runtime-exception");
		model.addObject("exception", ex);
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handle(Exception ex)
	{
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exception", ex);
		return model;
	}
}
```
And in your ServletContext configuration create a exceptionResolver bean 
```java
@Bean
public SimpleMappingExceptionResolver exceptionResolver()
{
  SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
  Properties exceptions = new Properties();
  exceptions.put(ArithmeticException.class, "runtime-exception");
  exceptions.put(NullPointerException.class, "runtime-exception");
  exceptions.put(Exception.class, "exception");
  resolver.setExceptionMappings(exceptions);
  return resolver;
}
```
#### And the output given below

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-4.png)

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-5.png)

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-6.png)

### Configuring custom 404 response
By default, when a page/resource does not exist the servlet container will throw a default 404 page. If you want a custom 404 response then you need to tell DispatcherServlet to throw the exception if no handler is found. We can do this by setting the throwExceptionIfNoHandlerFound servlet initialization parameter to true

In spring-mvc java based configuration is
```java
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    ...

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) 
    {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }
}
```
if xml based configuration, initialize your dispatcher servlet like this
```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>throwExceptionIfNoHandlerFound</param-name>
        <param-value>true</param-value>
    </init-param>
</servlet>
```

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/exception-handling-7.png)
