<#macro login path ifRegister>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Имя пользователя"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>
        <#if !ifRegister>
            <a href="/registration">Регистрация</a>
        </#if>
        <button class="btn btn-primary" type="submit"><#if !ifRegister>
                Войти <#else> Зарегистрироваться </#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>
<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Выход</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>

<#macro a path text>

    <a href="${path}">${text}</a>
</#macro>

<#macro div mes>
    <div>${mes}</div>
</#macro>