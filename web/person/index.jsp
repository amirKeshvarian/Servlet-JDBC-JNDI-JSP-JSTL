<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 8/24/2023
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/MENU.jsp"/>
    <form action="/person/save.do" method="post">
        <input type="text" name="name">
        <input type="text" name="family">
        <input type="text" name="salary">
        <input type="submit" value="SAVE" >
    </form>
    <br/>
    <hr/>
    <br/>
    <form action="/person/findOne.do" method="get">
        <input type="text" name="id">
        <input type="submit" value="FIND">
        <input type="button" onclick="findAllPerson()" value="FIND ALL">
    </form>
    <script>
        function findAllPerson(){
            window.location = "/person/findAll.do";
        }
    </script>
    <br/>
    <br/>
    <table>
        <tr>
            <td>ID</td>
            <td>NAME</td>
            <td>FAMILY</td>
            <td>SALARY</td>
            <td>RECORD VERSION</td>
            <td>CHANGE</td>
            <td>REMOVE</td>
        </tr>
        <c:forEach items="${requestScope.list}" var="person">
            <tr>
                <form action="/person/change.do" method="post">
                    <td><input type="text" name="id" value="${person.id}" readonly/></td>
                    <td><input type="text" name="name" value="${person.name}"/></td>
                    <td><input type="text" name="family" value="${person.family}"/></td>
                    <td><input type="text" name="salary" value="${person.salary}"/></td>
                    <td><input type="text" name="recordversion" value="${person.recordVersion}" readonly/></td>
                    <td><input type="submit" value="EXECUTE"/></td>
                    <td><input type="button" value="EXECUTE" onclick="removePerson(${person.id}, ${person.recordVersion})"/> </td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <form id="forRemove" action="/person/remove.do" method="POST" style="display:none;">
    </form>
    <script>
        function removePerson(id, recordversion) {
            if (confirm("are u sure?")) {
                let textarea1 = document.createElement("textarea");
                let textarea2 = document.createElement("textarea");
                textarea1.name = 'id';
                textarea1.value = id ;
                textarea1.type = 'hidden';
                textarea2.name = 'recordversion';
                textarea2.value = recordversion;
                textarea2.type = 'hidden';
                document.getElementById("forRemove").appendChild(textarea1);
                document.getElementById("forRemove").appendChild(textarea2);
                document.getElementById("forRemove").submit();
            }
        }
    </script>

</body>
</html>
