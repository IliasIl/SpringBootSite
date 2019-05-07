<html>
<head>
    <title>Spring Security</title>
</head>
<body>
<div>Login page</div>
<form action="/login" method="post">
    <div><label>Username: <input type="text" name="username"/></label></div>
    <div><label>Password: <input type="password" name="password"/></label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
</form>
<a href="/registration">Add new user</a>
</body>

</html>