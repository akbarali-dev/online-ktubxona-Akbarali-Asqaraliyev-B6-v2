<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Akbarali
  Date: 2/27/2022
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function makePUTrequest() {
            $.ajax({
                url: 'test.html',
                type: 'PUT',
                success: functio    n (result) {
                    // Do something with the result
                }
            });
        }

        function makeDELETErequest(url) {
            fetch(
                url,
                {method: 'DELETE'}
            ).then(response => location.reload())
        }
    </script>
    <title>chat</title>
</head>
<body>
<div>
    <a href="<c:url value="/admin/messages"/>" class="btn btn-success"> Message</a>

</div>
</body>
</html>
