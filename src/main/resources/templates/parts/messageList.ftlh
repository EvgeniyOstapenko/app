<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>




<div class="row" id="gallery" data-toggle="modal" data-target="#exampleModal">

    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/315d1638-fe7e-4e60-b959-1b741b4c9275.HbAy37dFOkA.jpg" alt="First slide" data-target="#carouselExample" data-slide-to="0">
    </div>
    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/3538b3da-cce1-4040-8224-1841ec9ddbf9.ITu8LxNidz8.jpg" alt="Second slide" data-target="#carouselExample" data-slide-to="1">
     </div>
    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/a14fc596-7a30-4d89-8507-137cf3b8554f.HbAy37dFOkA.jpg" alt="Third slide" data-target="#carouselExample" data-slide-to="2">
    </div>
    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/a29e62e4-3898-4b85-82c0-32ab6a06ea7f.ITu8LxNidz8.jpg" alt="Fourth slide" data-target="#carouselExample" data-slide-to="3">
    </div>

    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/a14fc596-7a30-4d89-8507-137cf3b8554f.HbAy37dFOkA.jpg" alt="Second slide" data-target="#carouselExample" data-slide-to="4">
     </div>
    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/3538b3da-cce1-4040-8224-1841ec9ddbf9.ITu8LxNidz8.jpg" alt="Third slide" data-target="#carouselExample" data-slide-to="5">
    </div>
    <div class="col-12 col-sm-6 col-lg-3">
      <img class="w-100" src="/img/315d1638-fe7e-4e60-b959-1b741b4c9275.HbAy37dFOkA.jpg" alt="Fourth slide" data-target="#carouselExample" data-slide-to="6">
    </div>

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
            <li data-target="#carouselExample" data-slide-to="4"></li>
            <li data-target="#carouselExample" data-slide-to="5"></li>
            <li data-target="#carouselExample" data-slide-to="6"></li>
          </ol>
          <div class="carousel-inner">

            <div class="carousel-item active">
              <img class="d-block w-100" src="/img/315d1638-fe7e-4e60-b959-1b741b4c9275.HbAy37dFOkA.jpg" alt="q0">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="/img/3538b3da-cce1-4040-8224-1841ec9ddbf9.ITu8LxNidz8.jpg" alt="q1">
             </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="/img/a14fc596-7a30-4d89-8507-137cf3b8554f.HbAy37dFOkA.jpg" alt="q2">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="/img/a29e62e4-3898-4b85-82c0-32ab6a06ea7f.ITu8LxNidz8.jpg" alt="q3">
            </div>

            <div class="carousel-item">""
              <img class="d-block w-100" src="/img/a14fc596-7a30-4d89-8507-137cf3b8554f.HbAy37dFOkA.jpg" alt="q4">
             </div>""
            <div class="carousel-item">""
              <img class="d-block w-100" src="/img/3538b3da-cce1-4040-8224-1841ec9ddbf9.ITu8LxNidz8.jpg" alt="q5">
            </div>""
            <div class="carousel-item">""
              <img class="d-block w-100" src="/img/315d1638-fe7e-4e60-b959-1b741b4c9275.HbAy37dFOkA.jpg" alt="q6">
            </div>""


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