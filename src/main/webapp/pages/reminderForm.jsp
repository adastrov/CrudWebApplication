<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Регистрация нового пользователя</title>

  <!-- Bootstrap core CSS -->
  <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value="/pages/css/signin.css" />" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->
</head>

<body>

<div class="navbar">
  <sec:authorize access="isAuthenticated()">
    <div>
      <span class="userName">Ваш логин: <span class="boldText"><sec:authentication property="principal.username" /> </span></span>
      <a class="btn btn-danger pull-right" href="<c:url value="/logout" />" role="button">Выйти</a>
    </div>
  </sec:authorize>
</div>

<div class="container" style="width: 600px;">

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>

  <form:form action="update" method="post" modelAttribute="rem">
    <h2 class="form-signin-heading">Редактирование напоминания</h2>

    <textarea class="hidden" type="text" class="form-control" name="dataId" placeholder="">${reminder.getId()}</textarea>

    <textarea type="text" class="form-control" name="theme" placeholder="Текст напоминания">${reminder.getTheme()}</textarea>

    <button class="btn btn-lg btn-primary btn-block" type="submit" value="update">Сохранить</button>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form:form>

</div>

</body>
</html>