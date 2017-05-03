<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Java Servlet JSON</title>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/game/styleGame.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/listHelper.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/game/gameHelper.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
    </head>

<body>

<div id="gameRequest">
    <div id="selectGameType">
        <label for="serverType">Server</label>
        <input type="radio" name="gameTypeRadio" value="SERVER" id="serverType" checked>
        <label for="multiuserType">Multiuser</label>
        <input type="radio" name="gameTypeRadio" value="MULTIUSER" id="multiuserType">
    </div>
    <label for="inputRate">Rate:</label>
    <input type="number" id="inputRate" min="0" step="0.01" value="0.00">
    <button class="btn btn-primary" type="button" onclick="sendAjax()">Play!</button>
    <div id="multiuserTypeOption" class="hideBlock">
        <label for="newMultiuserGame">New Game</label>
        <input type="radio" name="multiuserGameOptionRadio" value="NEW" id="newMultiuserGame">
        <label for="existingMultiuserGame">Existing Game</label>
        <input type="radio" name="multiuserGameOptionRadio" onclick="getPossibleGames()" value="EXISTING" id="existingMultiuserGame">
    </div>
</div>

<div id="resultString" style="color: #000;"></div>
<a href="${pageContext.servletContext.contextPath}/jsp/common/main.jsp">Home</a>

<div class="container">
    <h1>Open games</h1>
    <table id="table1" class="possibleGame table table-bordered table-striped">
        <thead>
        <tr>
            <th>Game Id</th>
            <th>Login</th>
            <th>Rate</th>
            <th>Join</th>
        </tr>
        </thead>
        <tbody id="tbodyid">
        </tbody>
    </table>
</div>

</body>
</html>