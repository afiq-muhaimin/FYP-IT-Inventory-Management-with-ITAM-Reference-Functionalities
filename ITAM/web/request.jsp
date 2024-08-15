<%-- 
    Document   : authentication-login
    Created on : Nov 15, 2023, 3:58:08 PM
    Author     : Afiq
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="bean.Services"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                            <a class="nav-link active" href="./index.jsp"><h3>Home</h3></a>
                        </li>
                        <li class="nav-item mx-4">
                            <a class="nav-link" aria-current="page" href="./request.jsp"><h3><strong>Request</strong></h3></a>
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
                    <div id="dateformdiv" class="card p-4">
                         <%
                            if (request.getAttribute("successmsg") != null) 
                            {
                        %>      
                            <div class="alert alert-success" role="alert">
                                <h4 class="alert-heading">Success!</h4>
                                <p><%=request.getAttribute("successmsg")%></p>
                                <div class="d-flex flex-row-reverse">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </div>
                        <%
                            }
                        %>
                       
                        <div class="card-body">
                            <h4 class="text-start mb-2"><strong>Request Asset</strong></h4>
                            <form id="dateform" action="ManageRequestHandler" method="GET">
                                <div class="my-3">
                                    <label for="exampleInputEmail1" class="form-label">Name</label>
                                    <input type="text" id="name" class="form-control" aria-describedby="emailHelp" name="name" required="true">
                                </div>
                                <div class="my-3">
                                    <label for="exampleInputEmail1" class="form-label">Email</label>
                                    <input type="email" id="email" class="form-control" aria-describedby="emailHelp" name="email" required="true">
                                </div>
                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Services</label>
                                    <select class="form-select"  name="serviceid" id="service" aria-label="Default select example" required="true" onchange="toggleInput2()">
                                       <%
                                            Services service = new Services();
                                            ArrayList<Services>allservices = new ArrayList();
                                            allservices = service.getAllServices();
                                            
                                            for(int i=0;i<allservices.size();i++)
                                            {
                                                service = allservices.get(i);
                                                String servid = service.getServicesID();
                                                String servdetails = service.getServicesName();
                                       %>
                                           <option value="<%=servid%>"><%=servdetails%></option>      
                                       <%
                                           }
                                       %>
                                    </select>
                                </div>
                                <div class="my-3">
                                    <label for="exampleInputEmail1" class="form-label">Phone Number</label>
                                    <input type="text" class="form-control" id="phonenum" aria-describedby="emailHelp" name="phonenum" required="true">
                                </div>
                                <div class="row my-3">

                                    <div class="col mx-2">
                                        <label class="mb-3" for="checkInDate">Date Taken</label>
                                        <input required="true" type="text" name="datetaken" class="form-control" id="checkInDate" placeholder="Enter date">
                                    </div>
                                    <div class="col mx-3">
                                        <label class="mb-3" for="checkOutDate" >Date Return</label>
                                        <input required="true" type="text" name="datereturn" class="form-control" id="checkOutDate" placeholder="Enter date">
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function() {
                                            const checkInDateInput = document.getElementById("checkInDate");
                                            const checkOutDateInput = document.getElementById("checkOutDate");

                                            const firstCalendar = flatpickr(checkInDateInput, {
                                                minDate: "today",
                                                onChange: function(selectedDates, dateStr, instance) {
                                                    const selectedDate = selectedDates[0];
                                                    const minCheckOutDate = selectedDate ? selectedDate.setDate(selectedDate.getDate() + 1) : "today";
                                                    secondCalendar.set("minDate", minCheckOutDate);
                                                    const currentCheckOutDate = secondCalendar.selectedDates[0];
                                                    if (currentCheckOutDate < minCheckOutDate) {
                                                        secondCalendar.setDate(minCheckOutDate);
                                                    }
                                                }
                                            });

                                            const secondCalendar = flatpickr(checkOutDateInput, {
                                                minDate: "today",
                                            });

                                            const form = document.querySelector("#dateform");
                                            form.addEventListener("submit", function(event) 
                                            {
                                                if (!checkInDateInput.value || !checkOutDateInput.value) {
                                                    event.preventDefault();
                                                    
                                                    alert("Please enter both Date Taken and Date Return.");
                                                    
                                                }
                                               
                                            });
                                        });
                                        
                                        /*
                                        function openModal() 
                                        {
                                            var name = document.getElementById("name").value;
                                            var email = document.getElementById("email").value;
                                            var service = document.getElementById("service").value;
                                            var phonenum = document.getElementById("phonenum").value;

                                            if (name.trim() === '' && email.trim() === '' && service.trim() === '' && phonenum.trim() === '') {
                                              alert('Please fill the details first.');
                                            } else {
                                              $('#exampleModal').modal('show');// this line working kalau tukar jadi "hide" modal tak keluar
                                            }
                                          }
                                          */
                                    </script>
                                    <input type="hidden" value="sendrequest" name="source">
                                </div>   
                                <div class="d-flex flex-row-reverse p-3">
                                    <button class="btn btn-primary" type="submit" aria-expanded="false" aria-controls="collapseExample">
                                        Find Asset
                                    </button>
                                    <!-- collapse here shows the available asset-->
                                </div>
                               </form>
                            
                                <!--
                                <div class="collapse" id="collapseExample">
                                    
                                    <div class="card card-body">
                                        <div class="row">
                                            
                                             <div class="col-sm-6 col-xl-3">
                                                 <div class="card overflow-hidden rounded-2">
                                                     <div class="position-relative">
                                                         <a href="javascript:void(0)"><img src="./assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                                                              
                                                     </div>
                                                     <div class="card-body pt-3 p-4">
                                                         <h6 class="fw-semibold fs-4">Boat Headphone</h6>
                                                         <div class="d-flex justify-content-center mt-2">
                                                             <a href="ManageRequestHandler" class="btn btn-primary">Request</a>
                                                         </div>
                                                         
                                                     </div>
                                                 </div>
                                             </div>
                                             <div class="col-sm-6 col-xl-3">
                                                 <div class="card overflow-hidden rounded-2">
                                                     <div class="position-relative">
                                                         <a href="javascript:void(0)"><img src="./assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                                                           
                                                     </div>
                                                     <div class="card-body pt-3 p-4">
                                                         <h6 class="fw-semibold fs-4">Boat Headphone</h6>
                                                         <div class="d-flex justify-content-center mt-2">
                                                             <a href="ManageRequestHandler" class="btn btn-primary">Request</a>
                                                         </div>
                                                         
                                                     </div>
                                                 </div>
                                             </div>
                                            <div class="col-sm-6 col-xl-3">
                                                 <div class="card overflow-hidden rounded-2">
                                                     <div class="position-relative">
                                                         <a href="javascript:void(0)"><img src="./assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                                                              
                                                     </div>
                                                     <div class="card-body pt-3 p-4">
                                                         <h6 class="fw-semibold fs-4">Asset 1</h6>
                                                         <div class="d-flex justify-content-center mt-2">
                                                             <a href="ManageRequestHandler" class="btn btn-primary">Request</a>
                                                         </div>
                                                         
                                                     </div>
                                                 </div>
                                             </div>
                                            <div class="col-sm-6 col-xl-3">
                                                 <div class="card overflow-hidden rounded-2">
                                                     <div class="position-relative">
                                                         <a href="javascript:void(0)"><img src="./assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                                                           
                                                     </div>
                                                     <div class="card-body pt-3 p-4">
                                                         <h6 class="fw-semibold fs-4">Asset 1</h6>
                                                         <div class="d-flex justify-content-center mt-2">
                                                             <a href="ManageRequestHandler" class="btn btn-primary">Request</a>
                                                         </div>
                                                         
                                                     </div>
                                                 </div>
                                             </div>
                                        </div>
                                </div>
                            
                        --></div>
                         
                    </div>
                    <div class="card p-4">
                         <%
                            if(request.getAttribute("errmsg") != null)
                            {
                        %>
                            <div class="alert alert-warning" role="alert">
                                <h4 class="alert-heading">Requester Not Found!</h4>
                                <p><%=request.getAttribute("errmsg")%>
                                
                                </p> 
                                <div class="d-flex flex-row-reverse">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>

                            </div>
                        <%
                            }
                        %>
                        <div class="card-body">
                            
                            <h4 class="text-start mb-2"><strong>Check Request Status</strong></h4>
                            <form action="ManageRequestHandler" method="GET">
                                <input type="hidden" name="source" value="checkstatus">
                                <div class="row my-3">
                                    <div class="my-3">
                                        <label for="exampleInputEmail1" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" required="true">
                                    </div>
                                    <div class="my-3">
                                        <label for="exampleInputEmail1" class="form-label">Phone Number</label>
                                        <input type="text" class="form-control" id="exampleInputEmail1" name="phonenum" required="true">
                                    </div>
                                </div>
                                <div class="d-flex flex-row-reverse">
                                    <button class="btn btn-primary" type="submit" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                        Check Request Status
                                    </button>
                                    <!-- collapse here shows the available asset-->
                                </div> 
                            </form>
                            <div class="table-responsive" style="max-height:300px;">
                            <table class="table mt-3">
                                <thead class="table-dark">
                                <th scope="col">Request ID</th>
                                <th scope="col">Date Taken</th>
                                <th scope="col">Date Return</th>
                                <th scope="col">Asset Requested</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Request Status</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${listrequest}" >
                                    <tr>
                                    <th scope="col">${item.requestID}</th>
                                    <th scope="col"><fmt:formatDate pattern = "yyyy-MM-dd" value="${item.dateTaken}"/></th>
                                    <%--<th scope="col">${item.dateTaken}</th>--%>
                                    <th scope="col"><fmt:formatDate pattern = "yyyy-MM-dd" value="${item.dateReturn}"/></th>
                                    <th scope="col">
                                        <c:forEach var="item2" items="${listreqast}">
                                            <c:if test="${item2.requestID==item.requestID}">
                                                <c:forEach var="item3" items="${listasset}">
                                                    <c:if test="${item3.assetID == item2.assetID}">
                                                        ${item3.assetName}</br>
                                                    </c:if>
                                                </c:forEach>
                                                    <!--somethings wrong output pasal first requester keluar luar table-->
                                            </c:if>
                                        </c:forEach>
                                    </th>
                                    <th>
                                        <c:forEach var="item2" items="${listreqast}">
                                            <c:if test="${item2.requestID==item.requestID}">
                                                ${item2.quantity}</br>
                                                    <!--somethings wrong output pasal first requester keluar luar table-->
                                            </c:if>
                                        </c:forEach>
                                    </th>
                                    <th scope="col">${item.requestStatus}</th>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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