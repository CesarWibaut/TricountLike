<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Fonts -->
  <link rel="dns-prefetch" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

  <title>Register</title>
</head>

<body>

  <%@ include file="WEB-INF/navbar.html" %>


  <main class="my-form">
    <div class="cotainer">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card">
            <div class="card-header">Register</div>
            <div class="card-body">
              <form name="my-form" onsubmit="return validform()" action="register" method="POST">
                <div class="form-group row">
                  <label for="first_name" class="col-md-4 col-form-label text-md-right">First Name</label>
                  <div class="col-md-6">
                    <input type="text" id="full_name" class="form-control" name="firstname">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="last_name" class="col-md-4 col-form-label text-md-right">Last Name</label>
                  <div class="col-md-6">
                    <input type="text" id="full_name" class="form-control" name="lastname">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                  <div class="col-md-6">
                    <input type="email" id="email_address" class="form-control" name="email">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                  <div class="col-md-6">
                    <input type="password" id="password" class="form-control" name="password">
                  </div>
                </div>


                <div class="col-md-6 offset-md-4">
                  <button type="submit" class="btn btn-primary">
                    Register
                  </button>
                </div>
            </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    </div>

  </main>
  <%@ include file="WEB-INF/bootstrap.html" %>
</body>

</html>