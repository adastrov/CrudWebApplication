<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
  <sec:authorize access="!isAuthenticated()">
    <a class="btn btn-success pull-right" href="<c:url value="/login" />" role="button">Войти</a>
  </sec:authorize>
</div>

<div class="container" style="width: 300px;">

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>

  <form:form action="save" method="post" modelAttribute="user">
    <h2 class="form-signin-heading">Регистрация</h2>
    <input type="text" class="form-control" name="username" placeholder="Эл. адрес или номер моб. телефона" required autofocus value="">
    <br>
    <input type="password" class="form-control" name="password" placeholder="Новый пароль" required value="">
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit" value="Save">Регистрация</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form:form>

</div>

</body>
</html>