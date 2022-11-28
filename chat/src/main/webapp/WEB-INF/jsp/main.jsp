<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div class="chat">

            <form:form method="post" modelAttribute="messageDto">
                <form:input type="text" placeholder="Text" path="text"/>
                <form:input type="text" placeholder="Author" path="author"/>
                <input type="submit" value="Add message"/>
            </form:form>

            <div class="chat-view">
                <c:forEach items="${messages}" var="message">
                    <div class="message">
                        <p> ${message.author} </p>
                        <p> ${message.text} </p>
                    </div>
                </c:forEach>
            </div>

        </div>
    </body>
</html>
