<%-- 
    Document   : samplepage
    Created on : Nov 15, 2023, 4:04:12 PM
    Author     : Afiq
--%>
<%@page import="bean.Services"%>
<%@page import="bean.Requester"%>
<%@page import="bean.AssetLogs"%>
<%@page import="bean.Requests"%>
<%@page import="bean.RequestedAsset"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Assets"%>
<%
String custid = (String) session.getAttribute("userid");
String usercat = (String) session.getAttribute("usercat");
    if(custid == null && usercat.equals("CAT1"))
    {
        response.sendRedirect("index.jsp");
    }
    %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
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
              if (request.getAttribute("welcomemsg") != null) {
          %>
          <div class="mb-3">
              <div class="alert alert-success alert-dismissible fade show" role="alert">
                  <%=request.getAttribute("welcomemsg")%>
                  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
          </div>
          <%
              }
          %>
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
            <h6 class="card-title fw-semibold mb-4">List Assets</h6>
            <div class="table-responsive" style="max-height:300px;">
            <table class="table">
                <thead class="table-dark">
                <th scope="col" style="text-align:center">Asset ID</th>
                <th scope="col" style="text-align:center">Asset Name</th>
                <th scope="col" style="text-align:center">Quantity</th>
                <th scope="col" style="text-align:center">Level</th>
                </thead>
                <tbody>
                    <%
                        Assets asset = new Assets();

                        ArrayList<Assets> listAssets = new ArrayList();
                        listAssets = asset.getAllAsset();
                        String assettype = " ";
                        for (int i = 0; i < listAssets.size(); i++) {
                            asset = listAssets.get(i);
                            String assetid = asset.getAssetID();
                            String assetname = asset.getAssetName();
                            int qty = asset.getAssetOriginQty();
                            assettype = asset.getTypeID();
                    %>
                    <tr>

                        <th scope="col" style="text-align:center"><%=assetid%></th>
                        <th scope="col" style="text-align:center"><%=assetname%></th>
                        <th scope="col" style="text-align:center"><%=qty%></th>
                    <%
                        if(qty < 6)
                        {
                    %>
                        <th scope="col" style="text-align:center">
                            <span class="badge bg-danger rounded-3 fw-semibold" >Low</span>
                        </th>
                    <%
                        }
                        else if(qty >= 6 && qty <= 10)
                        {
                    %>
                        <th scope="col" style="text-align:center">
                            <span class="badge bg-warning rounded-3 fw-semibold">Warning</span>
                        </th>
                    <%
                        }
                        else if(qty >10)
                        {
                    %>
                        <th scope="col" style="text-align:center">
                            <span class="badge bg-success rounded-3 fw-semibold">Good</span>
                        </th>
                    <%
                        }

                        }

                    %>
                    </tr>
                </tbody>
            </table>
            </div>
          </div>
        </div>
        <div class="card">
            <div class="card-body">
                 <h6 class="card-title fw-semibold mb-4">Requested Asset Overview</h6>
                 
                 <div id="chart"></div>
                 <script>
                     var options = {
          series: [{
          name: 'Number asset has been requested',
          data: [<%
              listAssets = asset.getAllAsset();
              for(int i=0;i<listAssets.size();i++)
              {
                  asset = listAssets.get(i);
                  RequestedAsset reqast = new RequestedAsset();
                  int sumqty = reqast.getSumQuantity(asset.getAssetID());
          %>
                 <%=sumqty%>,
          <%
              }
          %>]
        }],
          chart: {
          type: 'bar',
          height: 350
        },
        plotOptions: {
          bar: {
            horizontal: true,
            columnWidth: '55%',
            endingShape: 'rounded'
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: [
           <%
              listAssets = asset.getAllAsset();
              for(int i=0;i<listAssets.size();i++)
              {
                  asset = listAssets.get(i);
          %>
                          '<%=asset.getAssetName()%>',
          <%
              }
          %>],
        },
        yaxis: {
          title: {
            text: 'Asset Available'
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return val 
            }
          }
        }
        };

        var chart = new ApexCharts(document.querySelector("#chart"), options);
        chart.render();
      
                 </script>
            </div>               
        </div>
        <div class="card">
            <div class="card-body">
                <h6 class="card-title fw-semibold mb-4">Requests Overview</h6>
                <div class="row">
                    <div class="col-6 col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title fw-semibold mb-4" style="text-align:center;">Number Requests</h6>
                                <%
                                    ArrayList <Requests> listrequest = new ArrayList();
                                    Requests req = new Requests();
                                    listrequest = req.getAllRequests();
                                    int counterpending=0;
                                    int counterapprove=0;
                                    int counteronuse=0;
                                    int counterreturn=0;
                                    int counterdecline=0;
                                    
                                    for(int i=0;i<listrequest.size();i++)
                                    {
                                        req = listrequest.get(i);
                                        if(req.getRequestStatus().equals("pending"))
                                        {
                                            counterpending++;
                                        }
                                        else if(req.getRequestStatus().equals("approve"))
                                        {
                                            counterapprove++;
                                        }
                                        else if(req.getRequestStatus().equals("on_use"))
                                        {
                                            counteronuse++;
                                        }
                                        else if(req.getRequestStatus().equals("return"))
                                        {
                                            counterreturn++;
                                        }
                                        else if(req.getRequestStatus().equals("decline"))
                                        {
                                            counterdecline++;
                                        }
 
                                    }
                                %>
                                
                                <h1 style="text-align:center;">
                                    <strong><%=listrequest.size()%></strong> 
                                </h1>
                                
                                <h6 style="text-align:center;">There are <strong><%=listrequest.size()%></strong> request in the system</h6>
                            </div>
                        </div>        
                    </div>
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title fw-semibold mb-4" style="text-align:center;">Requests Pending and Approved</h6>
                                <div class="row">
                                    <div class="col">
                                        <h1 style="text-align:center;">
                                            <strong><%=counterpending%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=counterpending%></strong> request pending</h6>
                                        </h1>
                                    </div>
                                    <div class="col">
                                        <h1 style="text-align:center;">
                                            <strong><%=counterapprove%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=counterapprove%></strong> request approve</h6>
                                        </h1>
                                    </div>
                                    <div class="col">
                                        <h1 style="text-align:center;">
                                            <strong><%=counteronuse%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=counteronuse%></strong> request on use</h6>
                                        </h1>
                                    </div>
                                    <div class="col">
                                        <h1 style="text-align:center;">
                                            <strong><%=counterreturn%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=counterreturn%></strong> request return</h6>
                                        </h1>
                                    </div> 
                                </div>
                            </div>
                        </div>    
                    </div>  
                </div>                
            </div>
        </div>
       
        <div class="card">
                <div class="card-body">
                     <h6 class="card-title fw-semibold mb-4">Asset Logs Overview</h6>
                     <%
                         ArrayList<AssetLogs> listastlogs = new ArrayList();
                             AssetLogs astlogs = new AssetLogs();
                             listastlogs = astlogs.getAllAssetLogs();

                             int detailupdate = 0;
                             int movement = 0;
                             int assetout = 0;
                             int assetreturn = 0;
                             int assetfirstin = 0;

                             for (int i = 0; i < listastlogs.size(); i++) 
                             {
                                 astlogs = listastlogs.get(i);
                                 if (astlogs.getUpdateOperation().equals("asset_detail_updated")||astlogs.getUpdateOperation().equals("asset_details_updated")) 
                                 {
                                    detailupdate++;
                                 }
                                 else if (astlogs.getUpdateOperation().equals("asset_first_in")) 
                                 {
                                     assetfirstin++;
                                 }
                                 else if (astlogs.getUpdateOperation().equals("asset_movement")) 
                                 {
                                    movement++;
                                 }
                                 else if (astlogs.getUpdateOperation().equals("asset_out")) 
                                 {
                                    assetout++;
                                 }
                                 else if (astlogs.getUpdateOperation().equals("asset_return")) 
                                 {
                                    assetreturn++;
                                 }
                             }

                     %>
                     <div class="row">
                        <div class="col-6 col-md-4">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="text-align:center;">
                                            <strong> <%=listastlogs.size()%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=listastlogs.size()%></strong> number of asset logs</h6>
                                        </h1>
                                    </div>           
                                </div>            
                            </div> 
                        </div>
                        <div class="col">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col col-md-9"> <h6 style="">Number of asset logs involves in detail update</h6></div>
                                        <div class="col col-md-1"><strong><h5><%=detailupdate%></h5></strong></div>
                                    </div>
                                    <div class="row">
                                        <div class="col col-md-9"><h6 style="">Number of asset logs involves in asset movement</h6></div>
                                        <div class="col"><strong><h5><%=movement%></h5></strong> </div>      
                                    </div>
                                    <div class="row">
                                        <div class="col col-md-9"><h6 style="">Number of asset logs involves in asset out and use</h6></div>
                                        <div class="col"><strong><h5> <%=assetout%></h5></strong></div>      
                                    </div>
                                    <div class="row">
                                        <div class="col col-md-9"><h6 style="">Number of asset logs involves in returned</h6></div>
                                        <div class="col"><strong><h5> <%=assetreturn%></h5></strong></div>   
                                    </div>
                                    <div class="row">
                                        <div class="col col-md-9"><h6 style="">Number of asset logs involves in asset first enter the system </h6></div>
                                        <div class="col"><strong><h5><%=assetfirstin%></h5></strong></div>     
                                    </div>
                                </div>           
                            </div>            
                        </div> 
                     </div>
                </div>
             </div>
            <div class="card">
                <div class ="card-body">
                    <h6 class="card-title fw-semibold mb-4">Requester Overview</h6>
                    <%
                       ArrayList<Requester> listrequester = new ArrayList();
                       Requester requester = new Requester();
                       listrequester = requester.getAllRequester();
                       
                       ArrayList <Services> listservices = new ArrayList();
                       Services service = new Services();
                       listservices = service.getAllServices();
                                               
                    %>
                    
                    <div class="row">
                        <div class="col-6 col-md-4">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="text-align:center;">
                                            <strong> <%=listrequester.size()%></strong> 
                                            <h6 style="text-align:center;">There are <strong><%=listrequester.size()%></strong> number of requester</h6>
                                        </h1>
                                    </div>           
                                </div>  
                                
                            </div> 
                        </div>
                        <div class="col">
                            <div class="card">
                                <div class="card-body">
                                <div class="overflow-scroll" style="max-height:300px;">
                                    <%
                                        for (int i = 0; i < listservices.size(); i++) {
                                            int accumulate=0;
                                            service = listservices.get(i);
                                            for(int m=0; m<listrequester.size();m++)
                                            {
                                                requester = listrequester.get(m);
                                                if(requester.getServID().equals(service.getServicesID()))
                                                {
                                                    accumulate++;
                                                }
                                    
                                            }
                                    %>
                                    <div class="row">
                                        <div class="col col-md-9"> <h6 style=""><%=service.getServicesName()%></h6></div>
                                        <div class="col col-md-1"><strong><%=accumulate%></strong></div>
                                    </div>
                                    <%
                                        }    
                                    %>
                                </div>
                                </div>
                            </div>        
                        </div>
                    </div>
                </div>
             </div>
             <div class="card">
                <div class="card-body">
                    <h6 class="card-title fw-semibold mb-4">Asset Reporting</h6>
                    <div class="col">
                        <div class="row m-3">
                            <form action="ManageAssetReportingHandler" method="GET">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <strong>
                                            Asset Reports        
                                        </strong>     
                                    </div>
                                    <input type="hidden" value="assetreport" name="source">
                                    <div>
                                        <button type="submit" class="btn btn-primary mx-3">
                                            <i class="ti ti-download"></i>
                                            <span>Download</span>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                         <div class="row m-3">
                            <form action="ManageAssetReportingHandler" method="GET">
                                <div class="d-flex justify-content-between">
                                    <div>
                                        <strong>
                                            Asset Logs Report   
                                        </strong>
                                    </div>
                                    <input type="hidden" value="assetlogsreport" name="source">
                                    <div>
                                        <button type="submit" class="btn btn-primary mx-3">
                                            <i class="ti ti-download"></i>
                                            <span>Download</span>
                                        </button>
                                    </div>
                                </div>
                            </form>
                             
                        </div>
                            <div class="row m-3">
                                <form action="ManageAssetReportingHandler" method="GET">
                                    <div class="d-flex justify-content-between">
                                        <div>
                                            <strong>
                                                Requested Asset Report
                                            </strong>
                                        </div>
                                        <input type="hidden" value="assetrequestreport" name="source">
                                        <div>
                                            <button type="submit" class="btn btn-primary mx-3">
                                                <i class="ti ti-download"></i>
                                                <span>Download</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                             <div class="row m-3">
                                 <form action="ManageAssetReportingHandler" method="GET">
                                     <div class="d-flex justify-content-between">
                                         <div>
                                             <strong>
                                                Asset Movement Logs Report 
                                             </strong>
                                         </div>
                                         <input type="hidden" value="assetmovementreport" name="source">
                                         <div>
                                             <button type="submit" class="btn btn-primary mx-3">
                                                 <i class="ti ti-download"></i>
                                                 <span>Download</span>
                                             </button>
                                         </div>
                                     </div>
                                 </form>  
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
  <script src="./assets/js/sidebarmenu.js"></script>
  <script src="./assets/js/app.min.js"></script>
  <script src="./assets/libs/simplebar/dist/simplebar.js"></script>
</body>

</html>
