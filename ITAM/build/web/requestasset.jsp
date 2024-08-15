<%-- 
    Document   : authentication-login
    Created on : Nov 15, 2023, 3:58:08 PM
    Author     : Afiq
--%>

<%@page import="bean.RequestedAsset"%>
<%@page import="bean.Assets"%>
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
                <a class="navbar-brand" href="#">
                    <img src="./assets/images/logos/kpjlogo.svg" alt="Logo" width="auto" height="auto" class="d-inline-block align-text-top">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item mx-4">
                            <a class="nav-link active" aria-current="page" href="#"><h3><strong>Home</strong></h3></a>
                        </li>
                        <li class="nav-item mx-4">
                            <a class="nav-link" href="#"><h3>Asset Available</h3></a>
                        </li>
                        <li class="nav-item mx-4">
                            <a class="nav-link" href="#"><h3>More Info</h3></a>
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
                    <div class="card-body">
                        <div class="my-4">
                            <a href="./request.jsp" class="btn btn-outline-primary">
                                <span><i class="ti ti-arrow-left"></i></span>
                                <span>Back</span>  
                            </a>
                        </div>
                        <h4 class="text-start mb-4"><strong>Request Asset</strong></h4>
                        
                        <%
                            ArrayList<Assets> listassets = new ArrayList();
                            listassets = (ArrayList<Assets>)request.getAttribute("listAssets");
                            
                            ArrayList<Integer> listqtyavailable = new ArrayList();
                            listqtyavailable = (ArrayList<Integer>)request.getAttribute("qtyavailable");
                            Assets asset = new Assets();
                            //listassets = asset.getAllAsset();
                            ArrayList<String> astrequested = (ArrayList<String>) session.getAttribute("astreqbyuser");
                            ArrayList<Integer> qtyrequested = (ArrayList<Integer>) session.getAttribute("astqtyreqbyuser");
                            
                        %>
                        <a class="btn btn-outline-success m-3" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Asset Requested : <%=astrequested.size()%>
                        </a>
                        
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                <div class="d-flex justify-content-between">
                                        <h4>Asset Name</h4>
                                        <h4>Quantity</h4>
                                </div>
                                <%   
                                    for (int i = 0; i < astrequested.size(); i++) {
                                %>

                                <label for="exampleInputPassword1" class="form-label">
                                    
                                    <div class="d-flex justify-content-between">
                            <%
                                for(int m=0;m<listassets.size();m++)
                                {
                                    asset=listassets.get(m);
                                    String assetid = asset.getAssetID();
                                    String astreqid = astrequested.get(i);
                                    
                                    if(assetid.equals(astreqid))
                                    {
                            %>
                                        <h5><%=asset.getAssetName()%></h5>  
                            <%
                                    break;
                                    }
                                }
                            %>
                                          
                                        <h5><%=qtyrequested.get(i)%></h5>
                                    </div>
                                </label>

                                <%
                                    }
                                %>
                            </div>
                        </div>
                        
                        <div class="card card-body">
                            <div class="row">
                                <%
                               
                                for(int i=0;i<listassets.size();i++)
                                {
                                    asset=listassets.get(i);
                                %>
                                    <div class="col-sm-6 col-xl-3">
                                        <div class="card overflow-hidden rounded-2 text-center">
                                            <div class="position-relative">
                                                <%
                                                    String assetimage = asset.getAssetImage();
                                                    if (assetimage == null) {
                                                %>
                                                <a href="javascript:void(0)"><img src="./assets/images/products/s4.jpg" class="card-img-top rounded-0" alt="..."></a>
                                                    <%
                                                    } else {
                                                        String basePath = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\";
                                                        assetimage = assetimage.replace(basePath, " ").replace("\\", "/");
                                                        assetimage = "./" + assetimage.trim();

                                                    %>
                                                <a href="javascript:void(0)"><img src="<%=assetimage%>" class="card-img-top rounded-0" alt="..."></a>
                                                    <%
                                                        }
                                                    %>
                                            </div>
                                            <div class="card-body pt-3 p-4">
                                                <div class="align-items-center mt-2">
                                                    <p><h6 class="fw-semibold fs-4"><%=asset.getAssetID()%> : <%=asset.getAssetName()%></h6></p>
                                                </div>
                                                <div class="d-flex justify-content-center mt-2">
                                                    <!--
                                                    <form action="ManageRequestHandler" method="POST">
                                                        <input type="hidden" value="" name="assetid">
                                                        <button type="submit" class="btn btn-primary mx-3">Request</button>
                                                    </form>
                                                    -->
                                                    
                                                    <div class="d-grid col-6">
                                                        <button class="btn btn-primary" data-bs-target="#exampleModalToggle<%=asset.getAssetID()%>" data-bs-toggle="modal">
                                                            <span>
                                                                <i class="ti ti-basket"></i>
                                                            </span>
                                                            <span class="mx-3">
                                                                Request
                                                            </span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                                           
                                        </div>
                                    </div>
                                                            
                                    <div class="modal fade" id="exampleModalToggle<%=asset.getAssetID()%>" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel">Request</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form id="dateform" action="ManageRequestHandler" method="GET">
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <div class="row">
                                                                <div class="d-flex justify-content-between">
                                                                <label for="exampleInputPassword1" class="form-label">Quantity Request</label>
                                                                    Qty Available: <%=listqtyavailable.get(i)%>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" name="source" value="accumulatereq"> 
                                                            <input type="hidden" name="assetid" value="<%=asset.getAssetID()%>">
                                                            <input
                                                            <input type="number" name="quantity" min="1" max="<%=listqtyavailable.get(i)%>" class="form-control" id="exampleInputPassword1" required="true" placeholder="Number of asset">
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-primary">Request</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                               
                                    <%
                                     }
                                    %>
                            </div> 
                            <form action="ManageRequestHandler" method="POST">
                                <input type="hidden" name="source" value="request">
                                <div class="d-flex flex-row-reverse">
                                    
                                    <%
                                    if(astrequested.size()==0)
                                    {
                                    %>
                                    <div class="mx-4">
                                        <button type="submit" class="btn btn-primary" disabled="true">
                                            <span>Proceed</span>
                                            <span><i class="ti ti-arrow-narrow-right"></i></span>
                                        </button>
                                    </div>
                                    <%
                                    }
                                    else
                                    {
                                    %>
                                    <div class="mx-4">
                                        <button type="submit" class="btn btn-primary">
                                            <span>Proceed</span>
                                            <span><i class="ti ti-arrow-narrow-right"></i></span>
                                        </button>
                                    </div>
                                    <%
                                    }
                                    %>
                                    <a href="./request.jsp" class="btn btn-outline-danger">
                                        <span><i class="ti ti-arrow-left"></i></span>
                                        <span>Cancel</span>  
                                    </a>
                                </div>
                            </form>
                        </div> 
                    </div>
                </div>
            </div>
        </div> 
        <script src="./assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="./assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const checkInDateInput = document.getElementById("checkInDate");
                const checkOutDateInput = document.getElementById("checkOutDate");

                const firstCalendar = flatpickr(checkInDateInput, {
                    minDate: "today",
                    onChange: function (selectedDates, dateStr, instance) {
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
                form.addEventListener("submit", function (event) {
                    if (!checkInDateInput.value || !checkOutDateInput.value) {
                        event.preventDefault();
                        alert("Please enter both Date Taken and Date Return.");
                    }
                });
            });
        </script>
        <script>
         let cart = [];
                                                        
        function addToCart(productId,i) {
            const quantityInput = document.getElementById('quantity${i}');
            const quantity = parseInt(quantityInput.value, 10);

            const existingItem = cart.find(item => item.id === productId);
            if (existingItem) 
            {
                // If the item is already in the cart, update the quantity
                existingItem.quantity += quantity;
            } 
            else 
            {
                // If the item is not in the cart, add it with the specified quantity
                cart.push({ id: productId, quantity });
            }

            displayCart();
        }

        function displayCart() 
        {
            const cartItemsElement = document.getElementById('cart-items');
            cartItemsElement.innerHTML = '';
            let total = 0;
            cart.forEach(item => {
                const listItem = document.createElement('li');
                listItem.textContent='${item.id} x${item.quantity}';
                cartItemsElement.appendChild(listItem);
            });
        }                                             
    </script>
    </body>

</html>