<%--
  Created by IntelliJ IDEA.
  User: mvale
  Date: 03/10/2023
  Time: 07:29 a. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%
    List<String> errors = (List<String>)request.getAttribute("errores");
%>
<%
    Map<String,String> errorsmap = (Map<String,String>)request.getAttribute("errorsmap");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher CRUD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Teacher Forms" %></h3>

<form action="teacher-form" method="post">
    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control" value="${param.name}"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("name")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4'" +
                        "style='color: red;'>"+ errorsmap.get("name") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-form-label col-sm-2">Email</label>
        <div class="col-sm-4"><input type="text" name="email" id="email" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("email")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' " +
                        "style='color: red;'>"+ errorsmap.get("email") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <div>
            <input type="submit" value="Actualizar" class="btn btn-primary">
        </div>
    </div>
</form>
<br/>
<h3><%= "Consultar por ID" %>
</h3>
<form action="teacherbyid" method="post">
    <div class="row mb-3">
        <label for="id_teacher" class="col-form-label col-sm-2">Id del docente</label>
        <div class="col-sm-4"><input type="text" name="id_teacher" id="id_teacher" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Buscar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<h3><%= "Eliminar por ID" %>
</h3>
<form action="teacherdelete" method="post">
    <div class="row mb-3">
        <label for="id_teacher2" class="col-form-label col-sm-2">Id del docente</label>
        <div class="col-sm-4"><input type="text" name="id_teacher2" id="id_teacher2" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Eliminar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<div>
    <h3><%= "Lista de profesores" %>
    </h3>
    <a href="teacher-form">Vamos a Listar profesores</a>
</div>
</body>
</html>
