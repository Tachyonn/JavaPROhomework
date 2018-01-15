<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<div class="container">
    <h3><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/><a href="/">Groups</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="home" class="btn btn-default navbar-btn">
                            Home
                        </button>
                    </li>
                    <li>
                        <button type="button" id="del_with_contacts" class="btn btn-default navbar-btn">
                            Delete Group with contacts
                        </button>
                    </li>
                    <li>
                        <button type="button" id="del_wo_contacts" class="btn btn-default navbar-btn">
                            Delete Group without contacts
                        </button>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Select</th>
            <th>Group ID</th>
            <th>Group Name</th>
            <th>Contacts count</th>
        </tr>
        </thead>
        <c:forEach items="${groups}" var="groupEntry">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${groupEntry.key.id}"
                           id="checkbox_${groupEntry.key.id}"/></td>
                <td>${groupEntry.key.id}</td>
                <td>${groupEntry.key.name}</td>
                <td>${groupEntry.value}</td>
            </tr>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
            <c:if test="${byGroupPages ne null}">
                <c:forEach var="i" begin="1" end="${byGroupPages}">
                    <li><a href="/group/${groupId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
</div>

<script>
    $('#del_with_contacts').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/groups/delwi", data, function (data, status) {
            window.location.reload();
        });
    });

    $('#del_wo_contacts').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/groups/delwo", data, function (data, status) {
            window.location.reload();
        });
    });

    $('#home').click(function () {
        window.location.href = '/';
    });
</script>
<body>
</body>
</html>