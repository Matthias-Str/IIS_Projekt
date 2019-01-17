<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Markieren Sie die Anwesenheit der entsprechenden Studenten</title>
</head>
<body>
	<form class="registered-student-form">
		<div class = "registered-student-form-group">
			<input type="checkbox" name="student_checkbox" value="test1">test1<br>
			<input type="checkbox" name="student_checkbox" value="test2">test2<br>
			<ui:repeat value="#{bean.studentlist}" var="student">
				<h:outputText  value="#{student}"/>
			</ui:repeat>
		</div>
		<button action="#{camundaTaskForm.completeProcessInstanceForm()}">Best&auml;tigen</button>
	</form>
</body>
</html>