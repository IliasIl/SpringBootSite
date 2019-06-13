<#macro login path ifRegister>
    <form action="${path}" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Username: </label>
            <div class="col-sm-6">
                <input type="text"
                       name="username"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       value="<#if user??>${user.username}</#if>"
                       placeholder="Имя пользователя"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password"
                       name="password"
                       class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Пароль"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if ifRegister>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Retype password:</label>
                <div class="col-sm-6">
                    <input type="password"
                           name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid','')}"
                           placeholder="Пароль"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email"
                           name="email"
                           class="form-control ${(emailError??)?string('is-invalid','')}"
                           value="<#if user??>${user.email}</#if>"
                           placeholder="some@some.com"/>
                   <#if emailError??>
                       <div class="invalid-feedback">
                           ${emailError}
                       </div>
                   </#if>

                </div>
            </div>
            <div class="col-sm-5">
                <div class="g-recaptcha" data-sitekey="6Lc9V6gUAAAAACcjWfL-lGY0nVynyalOlPG59kTN"></div>
                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>

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