<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="/">
    <!--
        <style type="text/css">
            .container {
                margin-right: auto;
                margin-left: auto;
                padding-left: 15px;
                padding-right: 15px;
            }
            * {
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }
            *:before,
            *:after {
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }
            .dl-horizontal dd:before,
            .dl-horizontal dd:after,
            .container:before,
            .container:after,
            .row:before,
            .row:after,
            .panel-body:before,
            .panel-body:after {
                display: table;
                content: " ";
            }
            .row {
                margin-right: -15px;
                margin-left: -15px;
            }
            .col-md-offset-3 {
                margin-left: 25%;
                min-width: 992px;
            }
            .col-md-6 {
                width: 50%;
                float: left;
                position: relative;
                min-height: 1px;
                padding-right: 15px;
                padding-left: 15px;
                min-width: 992px;
            }
            .panel {
                box-shadow: rgba(0,0,0,.05);
                margin-bottom: 20px;
                background-color: #ffffff;
                border: 1px solid transparent;
                border-radius: 4px;
            }
            .panel-default {
                border-color: rgba(221,221,221,1);
            }
            .panel-heading {
                padding: 10px 15px;
                border-bottom: 1px solid transparent;
                border-top-left-radius: 3px;
                border-top-right-radius: 3px;
            }
            .panel-body {
                padding: 15px;
            }
            .panel-footer {
                padding: 10px 15px;
                background-color: #f5f5f5;
                border-top: 1px solid #ddd;
                border-bottom-right-radius: 3px;
                border-bottom-left-radius: 3px;
            }
            .panel-default > .panel-heading {
                color: #333;
                background: #f5f5f5 linear-gradient(to bottom, #f5f5f5 0, #e8e8e8 100%) repeat-x;
                border-color: #ddd;
            }
        </style>
        -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!--
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/dist/css/bootstrap.min.css' crossorigin="anonymous">
        <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/dist/css/bootstrap-theme.min.css" crossorigin="anonymous">-->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h1 class="panel-title">GF Gesem</h1><br>
                    <small>Management Accounting System v 1.0.0</small>
                </div>
                <div class="panel-body">
                <#if logout>
                    <div class="alert alert-info" role="alert">You've been logged out successfully.</div>
                </#if>
                <#if error>
                    <div class="alert alert-danger" role="alert">Invalid Username or Password!</div>
                </#if>

                    <form method="post">
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" placeholder="Username"
                                   name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password"
                                   name="password">
                        </div>
                        <button type="submit" class="btn btn-default">Log in</button>
                    </form>
                </div>
                <div class="panel-footer">
                    <span class="label label-default">Copiright &copy;</span>
                    <span class="label label-primary">Oleksii KHALIKOV</span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>