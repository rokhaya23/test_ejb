<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Liste des Étudiants</title>
</head>
<body>
    <h1>Liste des Étudiants</h1>
    <br>
    <a class="btn btn-success" href="user?action=add">Add</a>
    <br><br>
    <%
        String error = (String) session.getAttribute("errorMessage");
        if (error != null) {
    %>
    <div class="alert alert-danger" role="alert">
        <%= error %>
    </div>
    <%
            session.removeAttribute("errorMessage"); // Supprimer pour affichage unique
        }
    %>

    <table class="table table-bordered">
        <tr>
            <td>NOM</td>
            <td>PRENOM</td>
            <td>AGE</td>
            <td>ROLE</td>
            <td>ACTIONS</td>
        </tr>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.nom}</td>
                <td>${user.prenom}</td>
                <td>${user.age}</td>
                <td>${user.role.name}</td>
                <td>
                    <a class="btn btn-primary" href="user?action=edit&id=${user.id}">Modifier</a>
                    <a class="btn btn-danger" href="user?action=delete&id=${user.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
