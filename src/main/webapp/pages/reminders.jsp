<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="calendar" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Мои напоминания</title>

    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/main.css" />" rel="stylesheet">

</head>

<body>

<div class="navbar">

    <sec:authorize access="isAuthenticated()">
        <div>
            <span class="userName">Ваш логин: <span class="boldText"><sec:authentication property="principal.username" /> </span></span>
            <a class="btn btn-danger pull-right" href="<c:url value="/logout" />" role="button">Выйти</a>
        </div>

        <h3>Все напоминания:</h3>(<a href="addReminder">добавить</a>)

        <h1>Список напоминаний : ${rem}</h1>

        <ol>
            <c:forEach items="${reminders}" var="reminder">
                <li>
                        ${reminder.done} ${reminder.date} ${reminder.theme}  (<a href="/editReminder?id=${reminder.id}">Изменить</a>)   (<a href="/delete?id=${reminder.id}">Удалить</a>)
                </li>
            </c:forEach>
        </ol>

    </sec:authorize>
</div>

<footer>
    <p>© Padalka Pavel 2016</p>
</footer>
</body>
</html>