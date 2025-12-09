<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="https://jakarta.ee/jstl/core" prefix="c" %>
<!doctype html><html><head><title>Admin Login</title><link rel="stylesheet" href="assets/css/styles.css"></head>
<body>
<div class="nav"><a class="brand" href="${pageContext.request.contextPath}/">DriveDeal</a></div>
<div class="container"><div class="card" style="max-width:460px;margin:auto">
  <h2>Admin Login</h2>
  <c:if test="${not empty error}"><div class="card" style="background:#7f1d1d"> ${error} </div></c:if>
  <form method="post" action="${pageContext.request.contextPath}/auth/login">
    <label>Email</label><input class="input" type="email" name="email" required>
    <label>Password</label><input class="input" type="password" name="password" required>
    <button class="btn" type="submit" style="margin-top:1rem">Login</button>
  </form>
  <p>No admin yet? <a href="${pageContext.request.contextPath}/auth/register">Register</a></p>
</div></div>
</body></html>