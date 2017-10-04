
<ul>
    <c:forEach items="${menu}" var="elem">
        <a href="${elem.getUrl()}"><li>${elem.getElementName()}</li></a>
    </c:forEach>
</ul
