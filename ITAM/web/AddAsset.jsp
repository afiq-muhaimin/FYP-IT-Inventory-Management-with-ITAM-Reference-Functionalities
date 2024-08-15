<%-- 
    Document   : samplepage
    Created on : Nov 15, 2023, 4:04:12 PM
    Author     : Afiq
--%>
<%@page import="bean.Assets"%>
<%@page import="bean.Types"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CompanySupplier"%>
<%
String custid = (String) session.getAttribute("userid");
String usercat = (String) session.getAttribute("usercat");
    if(custid == null && (!usercat.equals("CAT1") || !usercat.equals("CAT2")))
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
  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
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
        <div class="card">
          <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Add Asset & Purchase</h5>
            <div class="card">
                <div class="card-body">
                 <form action="ManageAssetHandler" method="POST">
                    <div class="mb-3">
                      <h6 class="card-title fw-semibold mb-4">Asset Details</h6>
                      <label for="exampleInputEmail1" class="form-label">Company Name</label>
                      <input type="hidden" value="addasset" name="source">
                      <input type="hidden" value ="<%=custid%>" name="userid">
                      <input type="hidden" value="<%=usercat%>" name="usercat">
                      <select class="form-select" name="companyid" id="selectcomp" aria-label="Default select example" onchange="toggleInput()">
                          <option selected>Open this select menu</option>
                          <% 
                            CompanySupplier company = new CompanySupplier();
                            
                            ArrayList <CompanySupplier> listCompanies = new ArrayList();
                            listCompanies = company.getAllCompanySupplier();
                            for(int i=0; i<listCompanies.size();i++)
                            {
                                company = listCompanies.get(i);
                                System.out.println(company.getCompanySupplierID());
                                System.out.println(company.getCompanySupplierName());
                                String companyid = company.getCompanySupplierID();
                                String companyname = company.getCompanySupplierName();
                           %>
                           <option value=<%= companyid%>><%= companyname%></option>
                           <%
                            }
                           %>
                           <option value=newcompany>New Company</option>
                      </select>
                      <script>
                          function toggleInput() 
                          {
                            var selectElement = document.getElementById("selectcomp");
                            var inputElement = document.getElementById("compname");

                            // Check if the selected option is 'disable'
                            if (selectElement.value !== 'newcompany') {
                              inputElement.disabled = true;
                            } else {
                              inputElement.disabled = false;
                            }
                            
                          }
                      </script>
                      <!--
                      <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div
                      -->
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Company Name <label style="color:red">* Please insert new company supplier name if the name is not exist</label></label>
                      <input type="text" name="newcompanyname" class="form-control" id="compname" required="true" disabled="true">
                    </div>
                    <div class="mb-3">
                      <label for="exampleInputPassword1" class="form-label">Date Purchase</label>
                      <input required="true" name="datepurchase" type="text" name="datetaken" class="form-control" id="datepurchase" placeholder="Enter date" required>
                      <script>
                          flatpickr("#datepurchase",{});
                      </script>
                    </div>
                      <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Invoice Number</label>
                      <input type="text" name="invoice" class="form-control" id="exampleInputPassword1" required="true">
                    </div>
                      <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">PO Number</label>
                      <input type="text" name="ponumber" class="form-control" id="exampleInputPassword1" required="true">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Asset Name</label>
                        <select class="form-select"  name="assetname" id="selectasset" aria-label="Default select example" required="true" onchange="toggleInput2()">
                            <option selected>Open this select menu</option>
                            <% 
                            Assets asset = new Assets();

                            ArrayList <Assets> listAssets = new ArrayList();
                            listAssets = asset.getAllAsset();
                            String assettype = " ";
                            for(int i=0; i<listAssets.size(); i++)
                            {
                                asset = listAssets.get(i);
                                System.out.println(asset.getAssetID());
                                System.out.println(asset.getAssetName());
                                String assetid = asset.getAssetID();
                                String assetname = asset.getAssetName();
                                assettype=asset.getTypeID();
                           %>
                            <option value=<%= assetid%>><%= assetname%></option>
                           <%
                            }
                           %>
                           <option value=newassetname>New Asset</option>
                        </select>
                          
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">New Asset Name</label>
                      <input type="text" name="newassetname" class="form-control" id="assetname" required="true" disabled="true">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Asset Type</label>
                        <select class="form-select"  name="assettype" id="assettype" aria-label="Default select example" required="true">
                            <option selected>Default</option>
                            <% 
                            Types type = new Types();

                            ArrayList <Types> listTypes = new ArrayList();
                            listTypes = type.getAllTypes();
                            for(int i=0; i<listTypes.size(); i++)
                            {
                                type = listTypes.get(i);
                                System.out.println(type.getTypesID());
                                System.out.println(type.getTypesDetails());
                                String typeid = type.getTypesID();
                                String typename = type.getTypesDetails();
                           %>
                          
                           <script>
                                function toggleInput2() 
                                {
                                  var selectElement = document.getElementById("selectasset"); //first select
                                  var inputElement = document.getElementById("assetname");    
                                  var selectElement2 = document.getElementById("assettype");  //second select
                                 
                                  var selectedvalue = selectElement.value; //assetid
                                  
                                  // Check if the selected option is 'disable'
                                  if (selectElement.value !== 'newassetname') {
                                    inputElement.disabled = true;
                                    selectElement2.disabled = true;
                                   
                                  } else {
                                    inputElement.disabled = false;
                                    selectElement2.disabled = false;
                                  }

                                }
                           </script>
                           <option value=<%= typeid%>><%= typename%></option>
                           <%
                            }
                           %>
                        </select>
                    </div>
                       
                    <label for="exampleInputPassword1" class="form-label">Asset Remarks</label>
                    <div class="form-floating mb-3">
                          <div class="form-floating">
                              <textarea class="form-control" name="assetremarks" placeholder="Leave a comment here" id="floatingTextarea" required="true"></textarea>
                              <label for="floatingTextarea">Asset Remarks</label>
                          </div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Purchased Quantity</label>
                      <input type="number" name="assetqty" min="1" max="1000" class="form-control" id="exampleInputPassword1" placeholder="Quantity received" required="true">
                    </div>

                    <div class="d-flex flex-row-reverse">
                        <button type="submit" class="btn btn-primary mx-3">Submit</button>
                        <button type="reset" class="btn btn-light mx-3">Reset</button>                        
                    </div>
                    
                  </form>
                </div>
              </div>
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
