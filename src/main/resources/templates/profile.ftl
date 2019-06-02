<#import "parts/common.ftl" as c>

<@c.page>
    <div>${username}</div>
    ${message?ifExists}

    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="some@some.com"/>
                </div>
            </div>
        <button class="btn btn-primary" type="submit">Сохранить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>


</@c.page>