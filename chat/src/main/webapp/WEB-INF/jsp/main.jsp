<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div class="bubble-box settings">
            <div class="state">
                <p>${state.stateName}</p>
                <p>${state.id}</p>
                <p>${state.branchName}</p>
            </div>

            <form:form method="post" modelAttribute="branchDto">
                <form:input type="text" placeholder="Branch name" path="name"/>
                <input type="submit" value="Add branch" formaction="branch/add"/>
            </form:form>

            <form:form method="post" modelAttribute="nodeDto">
                <form:input type="text" placeholder="Node id" path="id"/>
                <input type="submit" value="Move to node" formaction="node/move"/>
            </form:form>

            <form:form method="post" modelAttribute="branchDto">
                <form:select path="name">
                        <c:forEach items="${branches}" var="b">
                            <option value="${b.name}">${b.name}</option>
                        </c:forEach>

                        <input type="submit" value="Choose branch" formaction="branch/switch"/>
                </form:select>
            </form:form>
        </div>

        <div class="bubble-box chat">


            <form:form method="post" modelAttribute="messageDto">
                <form:input type="text" placeholder="Text" path="text"/>
                <form:input type="text" placeholder="Author" path="author"/>
                <input type="submit" value="Add message"/>
            </form:form>

            <div class="chat-view">
                <c:forEach items="${messages}" var="message">
                    <div class="message">
                        <p> ${message.id}:${message.author} </p>
                        <p> ${message.text} </p>
                    </div>
                </c:forEach>
            </div>

        </div>
    </body>

</html>
