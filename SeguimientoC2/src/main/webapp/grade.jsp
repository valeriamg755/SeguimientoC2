<%--
  Created by IntelliJ IDEA.
  User: mvale
  Date: 03/10/2023
  Time: 07:28 a. m.
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
    <title>Grades CRUD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Grades Forms" %>
</h3>

<form action="grades-form" method="post">
    <div class="row mb-3">
        <label for="corte" class="col-form-label col-sm-2">Nota corte</label>
        <div class="col-sm-4"><input type="text" name="corte" id="corte" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("corte")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4'" +
                        "style='color: red;'>"+ errorsmap.get("corte") + "</div>");
            }
        %>
    </div>
    <div>
        <input type="submit" value="Actualizar" class="btn btn-primary">
    </div>
</form>
<br/>
<h3><%= "Consultar por ID" %>
</h3>
<form action="gradesbyid" method="post">
    <div class="row mb-3">
        <label for="id_grades" class="col-form-label col-sm-2">Id</label>
        <div class="col-sm-4"><input type="text" name="id_grades" id="id_grades" class="form-control"></div>
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
<form action="deletegrade" method="post">
    <div class="row mb-3">
        <label for="id_grades2" class="col-form-label col-sm-2">Id para eliminar</label>
        <div class="col-sm-4"><input type="text" name="id_grades2" id="id_grades2" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Eliminar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<div>
    <h3><%= "Lista de notas" %>
    </h3>
    <a href="grades-form">Vamos a listar notas</a>
</div>
</body>
</html>
