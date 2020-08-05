<#import "/spring.ftl" as spring/>
<head>
<title>Registration</title>
	<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<#--<c:choose>-->
<#--	<c:when test="${not empty requestScope.get('lang')}">-->
<#--		<fmt:setLocale value="${requestScope.get('lang')}"/>-->
<#--	</c:when>-->
<#--	<c:otherwise>-->
<#--		<fmt:setLocale value="${cookie['lang'].value}"/>-->
<#--	</c:otherwise>-->
<#--</c:choose>-->
<#--<fmt:setBundle basename="text" scope="application"/>-->

<div class="container">
<section id="content">
		<form action="user/sign_up" method="post">
			<h1> <@spring.message 'page.title.registration_form'/></h1>

			<#if error??>
				 <@spring.message '${error}'/>
			</#if>

<#--			<c:if test="${not empty requestScope.errorsList}">-->
<#--				<c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">-->
<#--					<c:if test="${entry.value.size() >0}">-->
<#--						<c:forEach var="value" items="${entry.value}">-->
<#--							 <@spring.message '${value}'/>-->
<#--						</c:forEach>-->
<#--					</c:if>-->
<#--				</c:forEach>-->
<#--			</c:if>-->

			<div>
				<p> <@spring.message 'user.login'/></p>
				<input type="text"
					   placeholder=" <@spring.message 'user.login'/>"
					   id="login"
					   name="login"
					   minlength="5"
					   maxlength="15"
					   pattern="^[a-zA-Z0-9а-яА-ЯёЁ]{5,15}$"
					   required
					   title=" <@spring.message 'user.error.invalid_login'/>"
				/>
			</div>
			<div>
				<p> <@spring.message 'user.password'/></p>
				<input type="password"
					   placeholder=" <@spring.message 'user.password'/>"
					   id="password"
					   name="password"
					   minlength="7"
					   maxlength="25"
					   required
					   title=" <@spring.message 'user.error.invalid_password'/>"
				/>
			</div>
			<div>
				<p> <@spring.message 'user.repeat_password'/></p>
				<input type="password"
					   placeholder=" <@spring.message 'user.repeat_password'/>"
					   id="repeat_password"
					   name="repeat_password"
					   minlength="7"
					   maxlength="25"
					   required
					   title=" <@spring.message 'user.error.invalid_password'/>"
				/>
			</div>
			<div>
				<p> <@spring.message 'user.first_name'/></p>
				<input type="text"
					   placeholder=" <@spring.message 'user.first_name'/>"
					   id="first_name"
					   name="first_name"
					   minlength="3"
					   maxlength="15"
					   required
					   pattern="^[a-zA-Zа-яА-ЯёЁ]{3,15}$"
					   title=" <@spring.message 'user.error.invalid_first_name'/>"
				/>
			</div>
			<div>
				<p> <@spring.message 'user.last_name'/></p>

				<input type="text"
					   placeholder=" <@spring.message 'user.last_name'/>"
					   id="last_name"
					   name="last_name"
					   minlength="3"
					   maxlength="15"
					   required
					   pattern="^[a-zA-Zа-яА-ЯёЁ]{3,15}$"
					   title="<@spring.message 'user.error.invalid_last_name'/>"
				/>
			</div>
			<div>
				<p> <@spring.message 'user.email'/></p>

				<input type="text"
					   placeholder=" <@spring.message 'user.email'/>"
					   id="email"
					   name="email"
					   minlength="4"
					   maxlength="35"
					   required
					   pattern="([\w-\.]+)@\D((?:[\w]+\.)+)([a-zA-Z]{2,4})"
					   title="<@spring.message 'user.error.invalid_email'/>"
				/>
			</div>
			<div>
				<input type="submit" value=" <@spring.message 'page.button.register'/>">
			</div>

		</form>
	<form action="main" method="post">
		<div>
			<input type="submit" value=" <@spring.message 'page.button.back'/>">

		</div>
	</form>
		<!-- form -->
	</section>
	<!-- content -->
</div>
<!-- container -->
</body>

