<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="mb-1">Add new user</div>
${message?ifExists}
<@l.login "/registration" true />
<div class="mb-1">Please after you press create button check you email and confirm your registration by clicking the link from SWEATER</div>
</@c.page>