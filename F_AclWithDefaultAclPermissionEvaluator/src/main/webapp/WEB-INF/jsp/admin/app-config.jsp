<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.appconfig.label"/></h2>
<div class="dropdown">
   <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
          data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <spring:message code="app.lang.title"/>
   </button>
   <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
      <a class="dropdown-item" href="?lang=en"><spring:message code="app.lang.english"/></a> 
      <a class="dropdown-item" href="?lang=hi_IN"><spring:message code="app.lang.hindi"/></a>
      <a class="dropdown-item" href="?lang=cn"><spring:message code="app.lang.chinese"/></a>
   </div>
</div>
<br>
   
<div class="dropdown">
   <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <spring:message code="app.theme.title"/>
   </button>
   <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
      <a class="dropdown-item" href="?mytheme=black"><spring:message code="app.theme.black"/></a> 
      <a class="dropdown-item" href="?mytheme=blue"><spring:message code="app.theme.blue"/></a>
      <a class="dropdown-item" href="?mytheme=pink"><spring:message code="app.theme.pink"/></a>
   </div>
</div>