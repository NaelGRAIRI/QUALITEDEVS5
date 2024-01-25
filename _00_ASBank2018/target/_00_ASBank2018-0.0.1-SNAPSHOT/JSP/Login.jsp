<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Page de connexion</title>
    <link rel="stylesheet" href="style/styles.css" />
</head>
<body>
<div class="login-container">
    <h1>Login</h1>
    <s:form name="myForm" action="controller.Connect.login.action" method="POST">
        <div class="form-group">
            <label for="userCde">Code user:</label>
            <s:textfield id="userCde" name="userCde" />
        </div>
        <div class="form-group">
            <label for="userPwd">Password:</label>
            <s:password id="userPwd" name="userPwd" />
        </div>
        <div class="form-group">
            <s:submit name="submit" class="submit-btn" />
        </div>
    </s:form>
    <s:form name="myFormRetour" action="retourAccueil" method="POST">
        <div class="form-group">
            <s:submit name="Retour" value="Retour à l'accueil" class="return-btn" />
        </div>
    </s:form>
</div>
<jsp:include page="/JSP/Footer.jsp" />
</body>
</html>
