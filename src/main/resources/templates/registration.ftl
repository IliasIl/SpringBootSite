<html>
<head>
    <title>Spring Security</title>
</head>
<body>
<div>Add new user</div>
{{#message}}
    {{message}}
{{/message}}
<form action="/registration" method="post">
    <div><label><input type="text" name="username"/></label></div>
    <div><label><input type="password" name="password"/></label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
</form>
</body>

</html>