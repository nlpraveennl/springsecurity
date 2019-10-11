<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>

<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.dashboard.label" /></h2>
<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.dashboard.welcome.label" /> ${user.userName}</h2>
