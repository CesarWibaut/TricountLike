<!doctype html>
<html lang="en">

<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <style>
        .container {
            margin-bottom: 5%;
        }
    </style>
</head>

<body>
    <%@ include file="WEB-INF/navbar.html" %>
    <%@ page isELIgnored="false" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="jumbotron text-center">
        <h1 class="display-3">${event.name}</h1>
        <p class="lead">${event.descr}</p>
    </div>
    <div class="container">
        <div class="container card" id="main">
            <div class="row">
                <div class="col-sm">
                    <h2> Participants : </h2>
                    <br>
                    <c:forEach var="user" items="${event.users}">
                        <p>${user}</p>
                    </c:forEach>
                </div>
                <div class="col-sm">
                    <h2> Total spent : </h2>
                    <br>
                    <c:forEach var="spent" items="${event.spents}">
                        <p>${spent}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
        <br>
        <br>
        <div class="container card">
            <h2>Add a participant !</h2>
            <div class="form-check form-check-inline">
                <form action="addUser" method="POST">
                    <input type="hidden" name="eno" value='${ param["eno"] }'>
                    <div class="form-group">
                      <label for="">Email</label>
                      <input type="text" class="form-control" name="email" id="">
                      <small id="helpId" class="form-text text-muted">something@something.something</small>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
        <br>
        <br>
        <div class="container card">
            <div class="row">
                <div class="col-sm">
                    <br>
                    <h2>Spent something ?</h2>
                    <form action="addSpent" method="POST">
                        <input type="hidden" name="eno" value='${ param["eno"] }'>
                        <div class="form-group row">
                            <label for="inputPassword3" class="col-sm-3 col-form-label">Ammount</label>
                            <div class="col-sm">
                                <input type="integer" class="form-control" placeholder="Euros..." name="ammount">
                            </div>
                        </div>
                        <fieldset class="form-group row">
                            <legend class="col-form-legend col-sm-2">For</legend>
                            <c:forEach var="user" items="${event.users}">
                                <div class="col-sm-10">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name="for" id="gridRadios1"
                                                value=${user.uno} checked>
                                            ${user}
                                        </label>
                                    </div>
                                </div>
                            </c:forEach>
                            <button type="submit" class="btn btn-primary">Add spent !</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="container text-center">
            <a name="warning" id="warning" class="btn btn-danger" href="" role="button">Close project and distribute money</a>
        </div>
    </div>

</body>

</html>