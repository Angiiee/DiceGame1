<html>
<body>
<nav>
    <ul class="menu">
        <li>
            <form action="${pageContext.servletContext.contextPath}/controller" method="post" class="wrap">
                <input type="hidden" name="command" value="locale"/>
                <input type="text" value="ru-RU" class="localeLabel" name="locale">
                <input type="submit" name="submit" value="" class="button-localeRU">
            </form>
        </li>
        <li>
            <form action="${pageContext.servletContext.contextPath}/controller" method="GET" class="wrap">
                <input type="hidden" name="command" value="locale"/>
                <input type="text" value="en-EN" class="localeLabel" name="locale">
                <input type="submit" name="submit" value="" class="button-localeEN">
            </form>
        </li>
    </ul>
</nav>
</body>
</html>