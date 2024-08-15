<%-- 
    Document   : authentication-login
    Created on : Nov 15, 2023, 3:58:08 PM
    Author     : Afiq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>KPJ AssetWise</title>
        <link rel="shortcut icon" type="image/png" href="./assets/images/logos/kpjlogo.png" width="90" />
        <link rel="stylesheet" href="./assets/css/styles.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    </head>

    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid m-3">
                <a class="navbar-brand" href="./index.jsp">
                    <img src="./assets/images/logos/kpjlogo.svg" alt="Logo" width="auto" height="auto" class="d-inline-block align-text-top">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item mx-4">
                            <a class="nav-link active" aria-current="page" href="./index.jsp"><h3><strong>Home</strong></h3></a>
                        </li>
                        <li class="nav-item mx-4">
                            <a class="nav-link" aria-current="page" href="./request.jsp"><h3>Request</h3></a>
                        </li>
                        <li class="nav-item mx-4">
                            <a class="nav-link" href="./assetavailable.jsp"><h3>Asset Available</h3></a>
                        </li>
                  

                        <!--
                        <li class="nav-item dropdown">
                          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                          </a>
                          <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                          </ul>
                        </li>
                        
                        <li class="nav-item">
                          <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                        -->
                    </ul>

                </div>
            </div>
        </nav>
        <!--  Body Wrapper -->
        <div class="container-fluid">
            <div class="card">
                <div class="card-body">
                    <div class="card">
                        <div class="card-body">
                            <div class="container text-center">
                                <div class="row justify-content-around">
                                    <div class="col-5">
                                        <h1 class="text-start"><strong>Welcome to KPJ AssetWise IT Asset Management System</strong></h1>
                                        <p class="card-text text-start mt-4 mb-4">
                                            Welcome to the KPJ AssetWise an IT asset management system that manage the asset of Information Technology Department’s asset. With this integrated system, requests for assets are seamlessly processed, tracked, and fulfilled, ensuring a cohesive and well-organized approach to asset allocation across all departments. This comprehensive asset management solution simplifies the entire process, enhancing productivity and resource allocation throughout the organization.
                                        </p>
                                        <div class="d-flex justify-content-around">
                                                
                                            <a href="#formlogin" class="btn btn-primary btn-lg">Login as User</a>
                                            <form action="ManageRequestHandler" method="GET">
                                                <input type="hidden" name="source" value="openrequest" class="btn btn-primary btn-lg">
                                                <input type="submit" value="Request Asset Here" class="btn btn-primary btn-lg">
                                            </form>
                                            
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <img src="./assets/images/logos/welcome.png" width="900" height="900" class="img-fluid" alt="..." >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                  
                    
                    <!--
                    <div class="row">
                         <div class="col-sm-6 col-xl-3">
                            <div class="card overflow-hidden rounded-2">
                                <div class="position-relative">
                                    <a href="javascript:void(0)"><img src="../assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                    <a href="javascript:void(0)" class="bg-primary rounded-circle p-2 text-white d-inline-flex position-absolute bottom-0 end-0 mb-n3 me-3" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Add To Cart"><i class="ti ti-basket fs-4"></i></a>                      
                                </div>
                                <div class="card-body pt-3 p-4">
                                    <h6 class="fw-semibold fs-4">Boat Headphone</h6>
                                    <div class="d-flex align-items-center justify-content-between">
                                        <h6 class="fw-semibold fs-4 mb-0">$50 <span class="ms-2 fw-normal text-muted fs-3"><del>$65</del></span></h6>
                                        <ul class="list-unstyled d-flex align-items-center mb-0">
                                            <li><a class="me-1" href="javascript:void(0)"><i class="ti ti-star text-warning"></i></a></li>
                                            <li><a class="me-1" href="javascript:void(0)"><i class="ti ti-star text-warning"></i></a></li>
                                            <li><a class="me-1" href="javascript:void(0)"><i class="ti ti-star text-warning"></i></a></li>
                                            <li><a class="me-1" href="javascript:void(0)"><i class="ti ti-star text-warning"></i></a></li>
                                            <li><a class="" href="javascript:void(0)"><i class="ti ti-star text-warning"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    -->
                      <div class="card" id="formlogin">
                        <div class="min-vh-100 d-flex align-items-center justify-content-center">
                            <div class="d-flex align-items-center justify-content-center w-100">
                                <div class="row justify-content-center w-100">
                                    <div class="col-md-8 col-lg-6 col-xxl-3">
                                        <div class="card mb-0">
                                            <div class="card-body">
                                                <a href="./index.html" class="text-nowrap logo-img text-center d-block py-3 w-100">
                                                    <img src="./assets/images/logos/kpjlogo.svg" width="320" alt="">
                                                </a>
                                                <p class="text-center">Your Asset Management Partner</p>
                                                <form id="formlogin" action="UsersHandler" method="POST" >
                                                    <div class="mb-3">
                                                        <input type="hidden" value="login" name="source">
                                                        <label for="exampleInputEmail1" class="form-label">Email</label>
                                                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" required="true">
                                                    </div>
                                                    <div class="mb-4">
                                                        <label for="exampleInputPassword1" class="form-label">Password</label>
                                                        <input type="password" class="form-control" id="exampleInputPassword1" name="password" required="true">
                                                    </div>
                                                    <div class="mb-4">
                                                        <%
                                                            if(request.getAttribute("errMessage") != null)
                                                            {
                                                        %>
                                                            <p style="color:red;" class="text-center"><%= request.getAttribute("errMessage") %></p>
                                                        <%
                                                            }
                                                        %>

                                                    </div>
                                                    <button class="btn btn-primary w-100 py-8 fs-4 mb-4 rounded-2">Sign In</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        <script src="./assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="./assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
      </body>
      
      </html>