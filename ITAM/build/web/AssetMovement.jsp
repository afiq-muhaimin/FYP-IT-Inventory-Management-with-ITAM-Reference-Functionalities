<%-- 
    Document   : samplepage
    Created on : Nov 15, 2023, 4:04:12 PM
    Author     : Afiq
--%>
<%@page import="bean.Types"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Assets"%>
<%
String custid = (String) session.getAttribute("userid");
String usercat = (String) session.getAttribute("usercat");
    if(custid == null && (usercat.equals("CAT1") || usercat.equals("CAT3") ))
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
                else if (usercat.equals("CAT3")) 
                {
            %>
            <li class="sidebar-item my-4">
              <a class="sidebar-link" href="./DashboardTechnician.jsp" aria-expanded="false">
                <span>
                  <i class="ti ti-layout-dashboard"></i>
                </span>
                <span class="hide-menu">Dashboard</span>
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
              if (request.getAttribute("successmsg") != null) {
          %>
          <div class="mb-3">
              <div class="alert alert-success alert-dismissible fade show" role="alert">
                  <%=request.getAttribute("successmsg")%>
                  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
          </div>
          <%
              }
          %>

        <div class="card">
          <div class="card-body">
            <h6 class="card-title fw-semibold ">Asset Movement</h6>
             <div class="card-body">
                 <div class="table-responsive" style="max-height: 500px;">
                    <table class="table">
                        
                        <thead class="table-dark">
                        <th scope="col" style="text-align:center">Asset ID</th>
                        <th scope="col" style="text-align:center">Asset Name</th>
                        <th scope="col" style="text-align:center">Asset Quantity</th>
                        <th scope="col" style="text-align:center">Asset Type</th> 
                        <th scope="col" style="text-align:center">Action</th> 
                        </thead>
                        <tbody>
                            <%
                             Assets asset = new Assets();

                                 ArrayList<Assets> listAssets = new ArrayList();
                                 listAssets = asset.getAllAsset();
                                 String assettype = " ";
                                 for (int i = 0; i < listAssets.size(); i++) 
                                 {
                                     asset = listAssets.get(i);
                                     String assetid = asset.getAssetID();
                                     String assetname = asset.getAssetName();
                                     int qty = asset.getAssetOriginQty();
                                     assettype = asset.getTypeID();
                            %>
                            <tr>
                               
                                    <th scope="col"><%=assetid%></th>
                                    <th scope="col"><%=assetname%></th>
                                    <th scope="col" style="text-align:center"><%=qty%></th>
                                    <%
                                        ArrayList<Types> listTypes = new ArrayList();
                                        Types type = new Types();
                                        listTypes = type.getAllTypes();
                                        
                                        for(int m=0;m<listTypes.size();m++)
                                        {
                                            type = listTypes.get(m);
                                            if(type.getTypesID().equals(assettype))
                                            {
                                    %>
                                    <th scope="col"><%=type.getTypesDetails()%></th>
                                    <%
                                            }
                                        }
                                        
                                    %>
                                    
                                    <th scope="col">
                                        <div class="d-flex justify-content-around">
                                         
                                            <form action="ManageAssetMovementHandler" method="GET">
                                                <input type="hidden" value="<%=assetid%>" name="assetid">
                                                <input type="hidden" value="addmovement" name="source"> 
                                                <button type="submit" class="btn btn-primary mx-3">Add Movement</button>
                                            </form>

                                            <form action="ManageAssetMovementHandler" method="GET">
                                                <input type="hidden" value="<%=assetid%>" name="assetid">
                                                <input type="hidden" value="viewmovement" name="source"> 
                                                <button type="submit" class="btn btn-primary mx-3">View Details</button>
                                            </form>
                                            
                                        </div>
                                    </th>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
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
