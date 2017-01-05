
<%--
  ~ Copyright (c) 2016-2017 by Oleksii KHALIKOV.
  ~ ========================================================
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%--
  Autor: Oleksii Khalikov
  Date: 28.12.2016
  Time: 23:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GF Gesem (Login page)</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script>
        function showRegisterPlace() {
            document.getElementById('loginPlace').style.display = 'none';
            document.getElementById('loginLogin').value = '';
            document.getElementById('passwordLogin').value = '';
            document.getElementById('registerPlace').style.display = 'inline';
        }
        function showLoginPlace() {
            document.getElementById('loginPlace').style.display = 'inline';
            document.getElementById('registerPlace').style.display = 'none';
            document.getElementById('loginRegister').value = '';
            document.getElementById('passwordRegister').value = '';
            document.getElementById('passwordConfirmationRegister').value = '';
        }
        function hideRegisterPlace() {
            document.getElementById('registerPlace').style.display = 'none';
        }


        /*
         авторизация
         */
        var authRequest = new XMLHttpRequest();
        function sendAuthRequest(login, password) {
            authRequest.open('POST', 'auth/authenticate?login=' + login + '&password=' + password, true);
            authRequest.send();
        }
        function auth() {
            sendAuthRequest(document.getElementById('loginLogin').value, document.getElementById('passwordLogin').value);
        }
        authRequest.onreadystatechange = function () {
            if (authRequest.readyState == 4
                    && authRequest.status == 200) {
                if (authRequest.responseText == '1') {
                    window.location = 'auth/open';
                } else if (authRequest.responseText == '-1') {
                    alert('Ваш аккаунт еще не активирован. Обратитесь к администратору');
                } else {
                    alert('Не правильный логин или пароль');
                }
            }
        };

        /*
         регистрация
         */
        function register() {
            var validateLoginTypoBoolean = validateLoginTypo();
            var confirmPasswordsBoolean = confirmPasswords();
            if (validateLoginTypoBoolean && confirmPasswordsBoolean) {
                console.log('true');
                sendRegisterRequest(document.getElementById('loginRegister').value, document.getElementById('passwordRegister').value);
            } else {
                console.log('false');
            }
        }
        var loginPattern = /^\w{4,}$/;
        function validateLoginTypo() {
            if (loginPattern.test(document.getElementById('loginRegister').value)) {
                send(document.getElementById('loginRegister').value);
                return true;
            } else {
                document.getElementById('loginRegisterMessage').innerHTML = 'Need be more that 4 symbols';
                return false;
            }
        }
        function send(login) {
            loginUniqueCheckRequest.open('GET', 'auth/checkLogin?login=' + login, true);
            loginUniqueCheckRequest.send();
        }
        var loginUniqueCheckRequest = new XMLHttpRequest();
        loginUniqueCheckRequest.onreadystatechange = function () {
            if (loginUniqueCheckRequest.readyState == 4
                    && loginUniqueCheckRequest.status == 200) {
                if (loginUniqueCheckRequest.responseText == '1') {
                    document.getElementById('loginRegisterMessage').innerHTML = '';
                } else {
                    document.getElementById('loginRegisterMessage').innerHTML = 'Такой Login уже существует';
                }
            }
        };
        function confirmPasswords() {
            var password = document.getElementById('passwordRegister').value;
            var passwordConfirmation = document.getElementById('passwordConfirmationRegister').value;
            if (password == passwordConfirmation) {
                document.getElementById('passwordConfirmationRegisterMessage').innerHTML = '';
                return true;
            } else {
                document.getElementById('passwordConfirmationRegisterMessage').innerHTML = 'Пароли не совпадают';
                return false;
            }
        }
        function sendRegisterRequest(login, password) {
            registerRequest.open('POST', 'auth/register?login=' + login + '&password=' + password, true);
            registerRequest.send();
        }
        var registerRequest = new XMLHttpRequest();
        registerRequest.onreadystatechange = function () {
            if (registerRequest.readyState == 4
                    && registerRequest.status == 200) {
                if (registerRequest.responseText == '1') {
                    showLoginPlace();
                    document.getElementById('loginMessage').innerHTML = 'Регистрация прошла успешно <br/>Для активации аккаунта обратитесь к администратору';
                } else {
                    document.getElementById('registerMessage').innerHTML = 'Ошибка при регистрации';
                }
            }
        };

    </script>

</head>
<body onload="hideRegisterPlace()">
<div class="page-header" style="margin: auto; max-width: 80%">
    <img src="${pageContext.request.contextPath}/img/logo.png" align="middle" vspace="10px" width="350px" alt="GFalcon"><br/>
    <h1>GF Gesem
        <small>Management Accounting System v-1.0</small>
    </h1>

</div>
<div id="loginPlace">
    <form class="form-horizontal" style="width: 600px; margin: auto">
        <div class="form-group">
            <p id="loginMessage" class="bg-info"></p>
            <label for="loginLogin" class="col-sm-2 control-label">Пользователь</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="loginLogin" placeholder="Login">
            </div>
        </div>
        <div class="form-group">
            <label for="passwordLogin" class="col-sm-2 control-label">Пароль</label>

            <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordLogin" placeholder="Password">
            </div>
        </div>
        <!--
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Remember me
                    </label>
                </div>
            </div>
        </div>
        -->
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" onclick="auth()">Войти</button>
                <button type="button" class="btn btn-default" onclick="showRegisterPlace()">Зарегистрироваться</button>
            </div>
        </div>
    </form>
</div>
<div id="registerPlace">
    <form class="form-horizontal" style="width: 600px; margin: auto">
        <div class="form-group">
            <p id="loginRegisterMessage" class="bg-danger"></p>
            <label for="loginRegister" class="col-sm-2 control-label">Пользователь</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="loginRegister" placeholder="Login"
                       onchange="validateLoginTypo()">
            </div>
        </div>
        <div class="form-group">
            <label for="passwordRegister" class="col-sm-2 control-label">Пароль</label>

            <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordRegister" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label for="passwordConfirmationRegister" class="col-sm-2 control-label">Пароль</label>

            <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordConfirmationRegister" placeholder="Password">
            </div>
            <p id="passwordConfirmationRegisterMessage" class="bg-warning"></p>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" onclick="register()">Зарегистрироваться</button>
                <button type="button" class="btn btn-default" onclick="showLoginPlace()">Назад</button>
            </div>
        </div>
    </form>
</div>
<div class="panel panel-default" style="margin: auto; max-width: 80%">
    <div class="panel-body">
        <span class="label label-default">Copiright &copy;</span>
        <span class="label label-primary">Oleksii KHALIKOV</span>
    </div>
</div>
</body>
</html>
