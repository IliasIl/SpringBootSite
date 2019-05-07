<html>

<body>
<div>

    <form action="/logout" method="post"><input type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
    </form>
</div>

<div>
    <form method="post" action="filter">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <input typ="text" name="filt"/>
        <button type="submit">Найти</button>
    </form>
</div>

<div>
    <form method="post">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
        <input type="text" name="text" placeholder="Сообщение"/>
        <input type="text" name="tag" placeholder="Тэг"/>
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
{{#messages}}
<div>
    <span>{{id}}</span>
    <span>{{text}}</span>
    <span>{{tag}}</span>
    <strong>{{authorName}}</strong>
</div>
{{/messages}}
</body>


</html>