<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30/03/2025
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<html>
<head>
    <title>Modifier un Etudiant</title>
</head>
<body>
    <form action="user?action=update" method="POST">
        <input type="text" class="form-control" name="id" value="${user.id}" hidden>
        <label>Nom </label>
        <input type="text" class="form-control" name="nom" value="${user.nom}">
        <br>

        <label>Prenom </label>
        <input type="text" class="form-control" name="prenom" value="${user.prenom}">
        <br>

        <label>Age </label>
        <input type="number" class="form-control" name="age" value="${user.age}">
        <br>

        <label>Username</label>
        <input type="text" class="form-control" name="username" value="${user.username}">
        <br>

        <label>Password</label>
        <input type="password" class="form-control" name="password" value="${user.password}">
        <br>

        <label>Email </label>
        <input type="email" class="form-control" name="email" value="${user.email}">

        <button class="btn btn-info" name="save" type="submit">Update</button>
        <button class="btn btn-danger" name="save" type="reset">Retour</button>
    </form>
</body>
</html>
