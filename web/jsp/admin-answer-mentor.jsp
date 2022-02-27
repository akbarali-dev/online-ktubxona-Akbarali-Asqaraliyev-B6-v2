<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Akbarali
  Date: 2/26/2022
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
    <script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
</head>
<body style="background-color: rgba(19,213,246,0.1);">
<div class="row mt-5 ml-0 mr-0" style="height: 400px;">
    <div class="col-md-6 offset-3 "
         style="background-color: white; border-radius:10px ;border: 2px solid gray;box-shadow: 5px 10px 8px #888888;z-index: 11;">



            <c:if test="${all == 'all'}">
                <c:forEach var="message" items="${messages}">

                    <div class="form-group">
                        <h4>${message.firstName.substring(0,1)}. ${message.lastName} </h4>
<%--                        <input hidden name="messageId" value="${message.courseId}">--%>
                        <div>
                            <a href="<c:url value="/admin/answer/${message.courseId}"/>" class="btn btn-success">
                                answer</a>
                        </div>
                    </div>


                </c:forEach>
            </c:if>

            <c:if test="${all == 'select'}">
                <h2 style="text-align: center"> ${messages.get(0).firstName}   ${messages.get(0).lastName}</h2>
                <c:forEach var="message" items="${messages}">

                    <div class="form-group">
                        <h4>${message.firstName.substring(0,1)}. ${message.lastName} </h4>
                        <div>
                            <a href="<c:url value="/admin/answer/${message.id}"/>" class="btn btn-success">
                                accept</a>
                            <a href="<c:url value="/admin/answer/${message.id}"/>" class="btn btn-success">
                                reject</a>
                        </div>
                    </div>


                </c:forEach>
            </c:if>

    </div>

</div>


<script>
    $(document).ready(function () {
        var multipleCancelButton = new Choices('#mentor', {
            removeItemButton: true,
            maxItemCount: 5,
            searchResultLimit: 5,
            renderChoiceLimit: 5
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>