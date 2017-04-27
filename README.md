# Gesem
[![Build Status](https://travis-ci.org/GFalcon-UA/Gesem.svg?branch=master)](https://travis-ci.org/GFalcon-UA/Gesem)
The management accounting system for building company.

Customization system environment variables:
<table>
<tr>
<th>Variable name</th>
<th>Description</th>
<th>Example</th>
</tr>
<tr>
<td>`GESEM_SERVER_PORT` (optional)</td>
<td>The port of server where application will be started</td>
<td>8080</td>
</tr>
<tr>
<td>`DATABASE_URL` (requred)</td>
<td>The string for connecting to the PostgreSQL data base.<br/> It must have format like:<br/> `postgres://[user_name]:[user_pass]@[rdbs_ip]:[rdbs_port]/[db_name]`</td>
<td>postgres://postgres:postgres@localhost:5432/gesem</td>
</tr>
</table>


