<!doctype html>
<html lang="en">

<head>
    <title>Menu</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <style>
        #main {
            padding-top : 5%;
            padding-bottom : 5%;
            margin-bottom : 2%;
            margin-top : 2%
        }
    </style>
</head>

<body>
    <%@include file="WEB-INF/navbar.html" %>
    <div class="container card" id="main">
        <div class="row">
            <div class="col-sm">
                <h3>My events :</h3>

                <!-- TODO : foreach sur la liste de mes events -->

            </div>
            <div class="col-sm">
                <h3>Create an event !</h3>
                <form action="createEvent" method="POST">
                    <div class="form-group">
                        <label for="">Name</label>
                        <input type="text" class="form-control" name="name" id="" aria-describedby="helpId" placeholder="Event name">
                    </div>
                    <div class="form-group">
                        <label for="">Description</label>
                        <input type="text" class="form-control" name="desc" id="" aria-describedby="helpId" placeholder="Description">
                        <small id="helpId" class="form-text text-muted">A small description of your event</small>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
    <div class="container">
        <form action="disconnect">
            <input type="submit" class="btn btn-danger" value="Disconnect"></button>
        </form>
    </div>
</body>

</html>