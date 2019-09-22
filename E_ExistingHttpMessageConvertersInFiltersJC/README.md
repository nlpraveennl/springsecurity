For more details refer

https://stackoverflow.com/questions/57598113/spring-mvc-content-type-aware-response-from-filter


**Problem**<br>
when you're writing Spring REST endpoint, Spring offers quite convenient way how to handle multiple response formats based on the content-type which client accepts decided by `Accept Header in request`. You just need to register MessageConverters and basically that's it. Spring takes care of the rest.

However, how do you access those converters or how do you let them translate any Object into ServletResponse in filters?

Here, I have a Filter that is the first in the chain. And when any filter on a way to controller fails, I want to send user the response in a format based on user's Accept header (json/xml/...).

What i need is, Filter should have access to message converters configured in Spring REST endpoint and based on accept header i do want to send the response same way as in case of Spring REST endpoint.

**Solution**<br>
For more detail on solution refer [Accepted solution answer which is awarded bounty in stack overflow](https://stackoverflow.com/a/57676746/2825798)

**Testing with this example**
Assuming fiter which is in first order encountered an exception, then it's response serving logic based on accept header is triggered and it will send response based on accept header sent by client.

To test this filter has made a block which is got triggered if `URL contains MessageConverterTest`.<br>
Refer below screenshots<br>

![postman](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/image.png)
