<!doctype html>
<html lang="en">
  <head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
      #loginForm  {
        padding-right: 20%;
        padding-left: 20%;
      }
    </style>
  </head>
  <body>
    <%@ include file="WEB-INF/navbar.html" %>

    <div class="jumbotron text-center">
      <h1 class="display-3">Tricount</h1>
      <p class="lead">The easiest way to manage your budget</p>
      <hr class="my-2">
    </div>

    <div id="loginForm" class="container">
      <form action="" method="post">
        <div class="form-group">
          <label>Email</label>
          <input type="email" class="form-control" name="" id="" aria-describedby="emailHelpId" placeholder="">
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" class="form-control" name="" id="" placeholder="">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
      </div>
      <%@ include file="WEB-INF/bootstrap.html" %>
  </body>
</html>