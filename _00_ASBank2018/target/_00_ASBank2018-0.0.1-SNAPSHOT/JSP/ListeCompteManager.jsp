<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Liste des comptes de la banque</title>
	<link rel="stylesheet" href="style/styles.css" />
</head>
<body>
<div class="btnLogout">
	<s:form name="myForm" action="logout" method="POST">
		<s:submit name="Retour" value="Logout" />
	</s:form>
</div>
<h1><s:if test="aDecouvert">Liste des comptes à découvert de la banque</s:if><s:else>Liste des comptes de la banque</s:else></h1>
<s:form name="myForm" action="retourTableauDeBordManager" method="POST">
	<s:submit name="Retour" value="Retour" />
</s:form>
<s:if test="aDecouvert">
	<p>Voici les comptes à découvert de la banque :</p>
</s:if>
<s:else>
	<p>Voici l'état des comptes de la banque :</p>
</s:else>
<table>
	<s:iterator value="allClients">
		<s:if test="(value.possedeComptesADecouvert() || !aDecouvert)">
			<tr>
				<td colspan="3"><b>Client :</b> <s:property value="value.prenom" /> <s:property value="value.nom" /> (n°<s:property value="value.numeroClient" />)</td>
				<s:if test="(!aDecouvert)">
					<td>
						<s:url action="urlAddAccount" var="addAccount">
							<s:param name="client">
								<s:property value="value.userId" />
							</s:param>
						</s:url>
						<s:a href="%{addAccount}">
							<img src="https://cdn4.iconfinder.com/data/icons/e-commerce-icon-set/48/More-128.png" style="width: 20px; height: 20px" alt="Créer un compte" title="Créer un compte pour ce client" />
						</s:a>
					</td>
					<td>
						<s:url action="deleteUser" var="deleteUser">
							<s:param name="client">
								<s:property value="value.userId" />
							</s:param>
						</s:url>
						<s:a href="%{deleteUser}" onclick="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?')">
							<img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/512/trash-.png" style="width: 20px; height: 20px" alt="Supprimer ce client" title="Supprimer ce client et tous ses comptes associés" />
						</s:a>
					</td>
				</s:if>
			</tr>
			<s:iterator value="value.accounts">
				<s:if test="(value.solde < 0 || !aDecouvert)">
					<tr>
						<td><s:property value="key" /></td>
						<s:if test="%{value.className == \"CompteAvecDecouvert\"}">
							<td>Découvert possible</td>
						</s:if>
						<s:else>
							<td>Simple</td>
						</s:else>
						<s:if test="%{value.solde >= 0}">
							<td><s:property value="value.solde" /></td>
						</s:if>
						<s:else>
							<td class="soldeNegatif"><s:property value="value.solde" /></td>
						</s:else>
						<s:if test="(!aDecouvert)">
							<s:url action="editAccount" var="editAccount">
								<s:param name="compte">
									<s:property value="value.numeroCompte" />
								</s:param>
							</s:url>
							<td>
								<s:a href="%{editAccount}">
									<img src="https://freeflaticons.com/wp-content/uploads/2014/10/write-copy-14138051958gn4k.png" style="width: 20px; height: 20px" alt="Editer ce compte" title="Editer ce compte" />
								</s:a>
							</td>
							<td>
								<s:url action="deleteAccount" var="deleteAccount">
									<s:param name="compte">
										<s:property value="value.numeroCompte" />
									</s:param>
									<s:param name="client">
										<s:property value="value.owner.userId" />
									</s:param>
								</s:url>
								<s:a href="%{deleteAccount}" onclick="return confirm('Voulez-vous vraiment supprimer ce compte ?')">
									<img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/512/trash-.png" style="width: 20px; height: 20px" alt="Supprimer ce compte" title="Supprimer ce compte" />
								</s:a>
							</td>
						</s:if>
					</tr>
				</s:if>
			</s:iterator>
		</s:if>
	</s:iterator>
</table>
<jsp:include page="/JSP/Footer.jsp" />
</body>
</html>
