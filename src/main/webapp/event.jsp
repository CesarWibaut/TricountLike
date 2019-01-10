<!doctype html>
<html lang="en">
  <head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  </head>
  <body>
      <%@ include file="WEB-INF/navbar.html" %>
      <%@ page isELIgnored="false" %>
    <div class="jumbotron text-center">
        <h1 class="display-3">${event.title}</h1>
        <p class="lead">${event.descr}</p>
    </div>
      <div class="container">
          
      </div>
  </body>
</html>