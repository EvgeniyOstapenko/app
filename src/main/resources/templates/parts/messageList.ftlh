<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page />


<div class="card-columns" id="message-list" data-toggle="modal" data-target="#exampleModal">
    <#list page.content as message>
    <div class="card my-3" data-id="${message.id}">
        <#if message.filename??>
        <img src="/img/${message.filename}" class="card-img-top" data-target="#carouselExample" data-slide-to="3"/>
    </#if>
    <div class="m-2">
        <span>${message.text}</span><br/>
        <i>#${message.tag}</i>
    </div>
    <div class="card-footer text-muted container">
        <div class="row">
            <a class="col align-self-center" href="/user-messages/${message.author.id}">${message.authorName}</a>
            <#if message.author.id == currentUserId>
            <a class="col btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                Edit
            </a>
        </#if>
    </div>
</div>
</div>
<#else>
No message
</#list>
</div>




<!-- Modal -->
<!--
This part is straight out of Bootstrap docs. Just a carousel inside a modal.
-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div id="carouselExample" class="carousel slide" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExample" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExample" data-slide-to="1"></li>
            <li data-target="#carouselExample" data-slide-to="2"></li>
            <li data-target="#carouselExample" data-slide-to="3"></li>
          </ol>
          <div class="carousel-inner">




          <#list page.content as message>
          <div class="card my-3" data-id="${message.id}">
              <img src="/img/${message.filename}" class="card-img-top" data-target="#carouselExample" data-slide-to="3"/>
          </div>

          </#list>



          </div>
          <a class="carousel-control-prev" href="#carouselExample" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExample" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<@p.pager url page />