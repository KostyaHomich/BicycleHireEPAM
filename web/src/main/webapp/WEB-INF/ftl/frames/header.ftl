<#import "/spring.ftl" as spring/>
<section class="hero">
    <header>
<div class="wrapper">
<#include "./frames/lang.ftl">
<nav>
<div>
<ul>
<#if Session.signInUser.role?contains("ADMIN")>
<li><a style="font-size: 12px" href="users">
                                 <@spring.message 'page.header.users'/></a></li>

<#else>
<li><a style="font-size: 12px" href="bicycles_best">
                                 <@spring.message 'page.header.best_bicycles'/></a></li>
</#if>

<li><a style="font-size: 12px" href="bicycles">
                             <@spring.message 'page.header.bicycles'/></a></li>
<li><a style="font-size: 12px" href="point_hires">
                             <@spring.message 'page.header.point_hires'/></a></li>
<li><a style="font-size: 12px" href="user">
                             <@spring.message 'page.header.button.account'/></a></li>
</ul>

<a href="logout"
                       class="login_btn">
                         <@spring.message 'page.header.button.logout'/>
</a>

</div>
</nav>
</div>
</header>
</section>

