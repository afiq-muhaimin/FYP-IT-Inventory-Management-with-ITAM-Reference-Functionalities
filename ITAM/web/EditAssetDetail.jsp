<%-- 
    Document   : samplepage
    Created on : Nov 15, 2023, 4:04:12 PM
    Author     : Afiq
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CompanySupplier"%>
<%@page import="bean.PurchaseDetails"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
String custid = (String) session.getAttribute("userid");
String usercat = (String) session.getAttribute("usercat");
    if(custid == null && (custid.equals("CAT1") || usercat.equals("CAT2") ))
    {
        response.sendRedirect("index.jsp");
    }
    %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>KPJ AssetWise</title>
  <link rel="shortcut icon" type="image/png" href="../assets/images/logos/kpjlogo.png" />
  <link rel="stylesheet" href="./assets/css/styles.min.css" />
</head>

<body>
  <!--  Body Wrapper -->
  <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
    data-sidebar-position="fixed" data-header-position="fixed">
    <!-- Sidebar Start -->
    <aside class="left-sidebar">
      <!-- Sidebar scroll-->
      <div>
        <div class="brand-logo d-flex align-items-center justify-content-between">
          <a href="./index.html" class="text-nowrap logo-img">
            <img src="./assets/images/logos/kpjlogo.svg" width="180" alt="" />
          </a>
          <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
            <i class="ti ti-x fs-8"></i>
          </div>
        </div>
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav scroll-sidebar " data-simplebar="">
          <ul id="sidebarnav">
            <li class="nav-small-cap">
              <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
              <span class="hide-menu">Menu</span>
            </li>
            <%
                if(usercat.equals("CAT1"))
                {
                
            %>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./DashboardAdmin.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                <span class="hide-menu">Dashboard</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AddAsset.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-square-plus"></i>
                </span>
                <span class="hide-menu">Insert Purchase Detail</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./EditPurchase.jsp" aria-expanded="false">
                <span>
                 <i class="ti ti-edit"></i>
                </span>
                <span class="hide-menu">Purchase Detail</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AssetDetail.jsp" aria-expanded="false">
                <span>
                 <i class="ti ti-archive"></i>
                </span>
                <span class="hide-menu">Asset Details</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AssetLogs.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-file-invoice"></i>
                </span>
                <span class="hide-menu">Asset Logs</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
                <a class="sidebar-link" href="./RequestedAsset.jsp" aria-expanded="false">
                    <span>
                        <i class="ti ti-book-2"></i>
                    </span>
                    <span class="hide-menu">Requested Asset</span>
                </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AssetMovement.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                <span class="hide-menu">Update Asset Movement</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
                <a class="sidebar-link" href="./AssetImage.jsp" aria-expanded="false">
                    <span>
                       <i class="ti ti-photo-edit"></i>
                    </span>
                    <span class="hide-menu">Edit Asset Image</span>
                </a>
            </li>
             <li class="sidebar-item my-4">
                <a class="sidebar-link" href="./EditUsersPassword.jsp" aria-expanded="false">
                    <span>
                        <i class="ti ti-password"></i>
                    </span>
                    <span class="hide-menu">Edit Users Auth</span>
                </a>
            </li>
            <%
                }
                else if (usercat.equals("CAT2")) 
                {
            %>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./Dashboard.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                <span class="hide-menu">Dashboard</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AddAsset.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-square-plus"></i>
                </span>
                <span class="hide-menu">Insert Purchase Detail</span>
              </a>
            </li>
           
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./EditPurchase.jsp" aria-expanded="false">
                <span>
                 <i class="ti ti-edit"></i>
                </span>
                <span class="hide-menu">Purchase Detail</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AssetDetail.jsp" aria-expanded="false">
                <span>
                <i class="ti ti-archive"></i>
                </span>
                <span class="hide-menu">Asset Details</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./AssetLogs.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-file-invoice"></i>
                </span>
                <span class="hide-menu">Asset Logs</span>
              </a>
            </li>
            <li class="sidebar-item my-4">
                <a class="sidebar-link" href="./AssetImage.jsp" aria-expanded="false">
                    <span>
                        <i class="ti ti-photo-edit"></i>
                    </span>
                    <span class="hide-menu">Edit Asset Image</span>
                </a>
            </li>
             <li class="sidebar-item my-4">
                <a class="sidebar-link" href="./RequestedAsset.jsp" aria-expanded="false">
                    <span>
                        <i class="ti ti-book-2"></i>
                    </span>
                    <span class="hide-menu">Requested Asset</span>
                </a>
            </li>
            <%
                }
            %>
            
        </nav>
        <!-- End Sidebar navigation -->
      </div>
      <!-- End Sidebar scroll-->
    </aside>
    <!--  Sidebar End -->
    <!--  Main wrapper -->
    <div class="body-wrapper">
      <!--  Header Start -->
      <header class="app-header">
        <nav class="navbar navbar-expand-lg navbar-light">
          <ul class="navbar-nav">
            <li class="nav-item d-block d-xl-none">
              <a class="nav-link sidebartoggler nav-icon-hover" id="headerCollapse" href="javascript:void(0)">
                <i class="ti ti-menu-2"></i>
              </a>
            </li>
          </ul>
          <div class="navbar-collapse justify-content-end px-0" id="navbarNav">
            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
             
              <li class="nav-item dropdown">
                <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown" aria-expanded="false">
                  <img src="./assets/images/profile/user-general.jpg" alt="" width="35" height="35" class="rounded-circle">
                </a>
                <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                  <div class="message-body">
                      <form action="UsersHandler" method="GET">
                          <button class="d-flex align-items-center gap-2 dropdown-item" type="submit">
                              <i class="ti ti-user fs-6"></i>
                              <p class="mb-0 fs-3">My Profile</p>
                          </button>
                          <input type="hidden" value="<%=custid%>" name="userid">
                          <input type="hidden" value="thisuseronly" name="source">
                      </form>
                      <form action="UsersHandler" method="GET">
                          <input type="hidden" value="logout" name="source">
                          <div class="d-flex justify-content-center">
                              <input type="submit" class="btn btn-outline-primary mx-3 mt-2 d-block" value="Logout">
                          </div>
                      </form>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!--  Header End -->
      <div class="container-fluid">
          <%
              if (request.getAttribute("notsuccessmsg") != null) {
          %>
          <div class="mb-3">
              <div class="alert alert-success alert-dismissible fade show" role="alert">
                  <%=request.getAttribute("notsuccessmsg")%>
                  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
          </div>
          <%
              }
          %>
        <div class="card">
          <div class="card-body">
             
              <a href="./AssetDetail.jsp" class="btn btn-outline-primary">
                      <span><i class="ti ti-arrow-left"></i></span>
                      <span>Back</span>  
                  </a>
              <form action="ManageAssetHandler" method="POST">
                  
              <div class="mb-3">
                  <h5 class="card-title fw-semibold my-4">Edit Asset Detail</h5>
                
                  <input type="hidden" value ="<%=custid%>" name="userid">
                  <input type="hidden" value="<%=usercat%>" name="usercat">
                  
              </div>
            
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Asset Name</label>
                    <input type="text" value="${asset.assetName}" class="form-control" name="assetname" id="assetname"> 
                    <input type="hidden" value="${asset.assetName}" class="form-control" name="oldassetname"> 
                </div>
               
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Asset Type</label>
                    <select class="form-select"  name="assettype" id="assettype" aria-label="Default select example" required="true">
                        <c:forEach var="itemtypes" items="${listTypes}">
                            <c:if test="${asset.typeID == itemtypes.typesID}">
                                <option value="<c:out value="${itemtypes.typesID}"></c:out>" selected>${itemtypes.typesDetails}</option>
                            </c:if>
                            <c:if test="${asset.typeID != itemtypes.typesID}">
                                <option value="<c:out value="${itemtypes.typesID}"></c:out>">${itemtypes.typesDetails}</option>
                            </c:if>
                        </c:forEach>
                            <option value="default">Default</option>
                        <script>
                             function toggleInput2() 
                             {
                               var selectElement = document.getElementById("selectasset"); //first select
                               var inputElement = document.getElementById("assetname");    
                               var selectElement2 = document.getElementById("assettype");  //second select
                                 
                              
                                   
                               // Check if the selected option is 'disable'
                               if (selectElement.value !== 'newassetname') {
                                 inputElement.disabled = true;
                                 selectElement2.value='default';
                                 selectElement2.disabled = true;
                                   
                                } 
                                else 
                                {
                                 inputElement.disabled = false;
                                 selectElement2.disabled = false;
                                 selectElement2.selected = true;
                                }

                             }
                             
                            var selectElement2 = document.getElementById("assettype");
                            var inputElement = document.getElementById("assetname");
                            var assetoldname = "<c:out value="${asset.assetName}"/>";
                            var assetoldtype = "<c:out value="${asset.typeID}"/>";
                            var submitbtn = document.getElementById("submitid");
                            if(inputElement.value ===assetoldname && selectElement2.value===assetoldtype)
                            {
                                submitbtn.disabled = true;
                            }
                        </script>
                    </select>
                    <input type="hidden" value="${asset.typeID}" class="form-control" name="oldassettype"> 
                </div>
                <label for="exampleInputPassword1" class="form-label">Asset Remarks</label>
                <div class="form-floating mb-3">
                    <div class="form-floating">
                        <textarea class="form-control" id="textareaasset"name="assetremarks" value="<c:out value="${asset.assetRemarks}"/>" placeholder="Leave a comment here" id="floating<c:out value="${assetremarks}"/>Textarea" required="true"></textarea>
                        <label for="floatingTextarea">Asset Remarks</label>
                    </div>
                        <script>
                            var dynamicValue = "<c:out value="${asset.assetRemarks}"/>";
                            document.getElementById("textareaasset").value = dynamicValue;
                        </script>
                </div>
                <input type="hidden" value="${asset.assetRemarks}" class="form-control" name="oldassetremarks"> 
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Current Quantity <label style="color:red"> *Current quantity only can be change when the quantity of asset is change in purchase detail</label></label>
                    <input type="text" min="1" max="1000" value="${asset.assetOriginQty}" class="form-control" id="exampleInputPassword1" placeholder="Quantity received" disabled="true">
                </div>
                 <input type="hidden" value="${asset.assetOriginQty}" class="form-control" name="assetqty"> 
                <input type="hidden" value="update" name="source">
                <input type="hidden" value="${asset.assetID}" name="assetid">
                <div class="d-flex flex-row-reverse">
                    <button type="submit" id="submitid"class="btn btn-primary mx-3">Submit</button>
                    <button type="reset" class="btn btn-light mx-3">Reset</button> 
                </div>
            </form> 
            
            <!-- change ikut flow dalam word-->
            <!-- design dia buat form sama macam add asset just ada preadd data-->
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="./assets/libs/jquery/dist/jquery.min.js"></script>
  <script src="./assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="./assets/js/sidebarmenu.js"></script>
  <script src="./assets/js/app.min.js"></script>
  <script src="./assets/libs/simplebar/dist/simplebar.js"></script>
</body>

</html>
