
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023/05/07
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Category</title>
    <meta charset="UTF-8">
</head>
<body>
<table border="1" style="width: 100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categoryList}" var="category">
        <tr >
            <td style="text-align: center">${category.getId()}</td>
            <td style="text-align: center;width: 60%">${category.getName()}</td>
            <td style="text-align: center"><button>Edit</button> <button>Delete</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
