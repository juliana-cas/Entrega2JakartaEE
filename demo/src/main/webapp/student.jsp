
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3><%= "Student´s form" %>
</h3>

<form action="student-form" method="post">
    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-form-label col-sm-2">Email</label>
        <div class="col-sm-4"><input type="text" name="email" id="email" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="degree" class="col-form-label col-sm-2">Degree</label>
        <div class="col-sm-4"><input type="text" name="degree" id="degree" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <label for="semester" class="col-form-label col-sm-2">Semester</label>
        <div class="col-sm-4"><input type="text" name="semester" id="semester" class="form-control"></div>
    </div>
    <div class="row mb-3">
        <div>
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </div>
</form>
<br/>
<a href="student-form">Vamos a StudentController</a>
</body>
</html>