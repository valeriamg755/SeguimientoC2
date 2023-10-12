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
<%@ page import="org.example.mapping.dto.TeacherDto" %>
<%
    List<String> errors = (List<String>)request.getAttribute("errores");
%>
<%
    Map<String,String> errorsmap = (Map<String,String>)request.getAttribute("errorsmap");
%>
<%
    List<TeacherDto> teachers = (List<TeacherDto>)getServletContext().getAttribute("teacherDtoList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Subject CRUD</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Subject Forms" %></h3>

<form action="subject-form" method="post">
    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("name")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4'" +
                        "style='color: red;'>"+ errorsmap.get("name") + "</div>");
            }else
        %>
    </div>

    <div class="row mb-3">
        <%
            if(teachers != null && !teachers.isEmpty()){
        %>
        <label for="teacher" class="col-form-label col-sm-2">Profesor</label>
        <div class="col-sm-4">
            <select id="teacher" name="teacher">
                <%for (TeacherDto teacher: teachers) { %>
                <option><%=teacher.name()%></option>
                <% } %>
                <% } %>
            </select>
        </div>
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
<form action="subjectbyid" method="post">
    <div class="row mb-3">
        <label for="id_subject" class="col-form-label col-sm-2">Id de la asignatura</label>
        <div class="col-sm-4"><input type="text" name="id_subject" id="id_subject" class="form-control"></div>
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
<form action="subjectdelete" method="post">
    <div class="row mb-3">
        <label for="id_subject2" class="col-form-label col-sm-2">Id de la asignatura</label>
        <div class="col-sm-4"><input type="text" name="id_subject2" id="id_subject2" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Eliminar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<h3><%= "Lista de asignaturas" %></h3>
<a href="subject-form">Vamos a listar asignaturas</a>
</body>
</html>
