<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3><img height="50" width="55" src="<c:url value="../static/logo.png"/>"/><a href="/">Admin's page</a></h3>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_user" class="btn btn-default navbar-btn">Add user</button>
                    </li>
                    <li>
                        <button type="button" id="delete" class="btn btn-default navbar-btn">Delete selected</button>
                    </li>
                    <li>
                        <button type="button" id="logout1" class="btn btn-default navbar-btn">Logout</button>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <table class="table table-striped" width="350">
        <thead>
        <tr>
            <th scope="col"><input class="form-check-input" type="checkbox" disabled></th>
            <th scope="col">User Name</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersList}" var="item">
            <tr>
                <th scope="row"><input type="checkbox"
                                       name="toDelete[]"
                                       value="${item.getId()}"
                                       id="checkbox_${item.getId()}"/>
                </th>
                <td>${item.getLogin()}</td>
                <td>${item.getRole()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="button" id="logout2" class="btn btn-default navbar-btn">Logout</button>

    <c:url value="/logout" var="logoutUrl"/>
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
    <script>
        $('#delete').click(function () {
            var data = {'toDelete[]': []};
            $(":checked").each(function () {
                data['toDelete[]'].push($(this).val());
            });
            $.post("/delete", data, function (data, status) {
                window.location.reload();
            });
        });

        $('#logout1,#logout2').click(function () {
            window.location.href = '/logout';
        });

        $('#add_user').click(function () {
            window.location.href = '/register';
        });
    </script>
</div>
</body>
</html>
