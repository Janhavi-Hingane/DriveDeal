<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="https://jakarta.ee/jstl/core" prefix="c" %>
<!doctype html><html><head><title>DriveDeal – Buy & Sell Cars</title><link rel="stylesheet" href="assets/css/styles.css"></head>
<body>
<jsp:include page="/WEB-INF/nav.jspf" />
<div class="container">
  <div class="card">
    <h2>Find Your Next Car</h2>
    <form method="get" action="${pageContext.request.contextPath}/cars" class="grid" style="grid-template-columns:repeat(5,minmax(0,1fr));align-items:end">
      <div><label>Make</label><input class="input" name="make" value="${param.make}"></div>
      <div><label>Fuel</label>
        <select name="fuel" class="input">
          <option value="">Any</option>
          <c:forEach var="f" items="${['Petrol','Diesel','CNG','Electric']}">
            <option value="${f}" <c:if test="${param.fuel==f}">selected</c:if>>${f}</option>
          </c:forEach>
        </select>
      </div>
      <div><label>Min Price</label><input class="input" name="minPrice" type="number" step="10000" value="${param.minPrice}"></div>
      <div><label>Max Price</label><input class="input" name="maxPrice" type="number" step="10000" value="${param.maxPrice}"></div>
      <div><button class="btn" type="submit">Search</button></div>
    </form>
  </div>
  <div class="grid" style="grid-template-columns:repeat(3,minmax(0,1fr)); margin-top:1rem">
    <c:forEach var="c" items="${cars}">
      <div class="card">
        <img src="${empty c.imageUrl ? 'https://picsum.photos/seed/'+c.id+'/600/400' : c.imageUrl}" alt="${c.make} ${c.model}">
        <h3>${c.make} ${c.model} <span class="badge">${c.year}</span></h3>
        <p class="badge">${c.fuelType}</p>
        <p><strong>₹ ${c.price}</strong> • ${c.mileage} km</p>
        <p style="opacity:.8">${c.description}</p>
      </div>
    </c:forEach>
    <c:if test="${empty cars}"><div class="card">No cars found. Adjust filters and try again.</div></c:if>
  </div>
</div>
<footer>&copy; 2025 DriveDeal</footer>
</body></html>