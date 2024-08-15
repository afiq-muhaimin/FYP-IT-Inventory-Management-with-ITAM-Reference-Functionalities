/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AssetLogs;
import bean.Assets;
import bean.RequestedAsset;
import bean.Requester;
import bean.Requests;
import bean.Types;
import bean.Users;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




/**
 *
 * @author Afiq
 */
public class ManageAssetReportingHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageAssetReporting</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAssetReporting at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String source = request.getParameter("source");

        if(source.equals("assetreport"))
        {
            String filePath = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\templateAsset.xlsx";
            try 
            {
                // Read the existing workbook
                FileInputStream fileInputStream = new FileInputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();

                // Get the first sheet
                XSSFSheet sheet = workbook.getSheetAt(0);

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);

                // Create a CellStyle with the bold font
                CellStyle style = workbook.createCellStyle();
                style.setFont(boldFont);
                
                CellStyle style1 = workbook.createCellStyle();
                style1.setDataFormat(workbook.createDataFormat().getFormat("#0.00"));
                
                CellStyle style2 = workbook.createCellStyle();
                style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                
                CellStyle style3 = workbook.createCellStyle();
                style3.setFillForegroundColor(IndexedColors.RED.getIndex());
                style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                
                CellStyle style4 = workbook.createCellStyle();
                style4.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row row5= sheet.createRow(5);
                Cell cellrow5 = row5.createCell(2);
                cellrow5.setCellValue("IT Asset Report");
                cellrow5.setCellStyle(style);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  

                cellrow5 = row5.createCell(5);
                cellrow5.setCellValue("Date Downloaded: " + formatter.format(date));
                cellrow5.setCellStyle(style);

                Row rowtotalasset= sheet.createRow(6);
                Cell celltotalasset = rowtotalasset.createCell(2);
                celltotalasset.setCellValue("Total Asset Available: "); 
                celltotalasset.setCellStyle(style);

                Cell cellvaluetotal = rowtotalasset.createCell(3);
                

                Assets asset = new Assets();
                ArrayList<Assets> listAssets = asset.getAllAsset();

                Types type = new Types();
                ArrayList<Types> listTypes = type.getAllTypes();
                int totalasset = 0;
                int lastrow = 9;
                for (int i = 0; i < listAssets.size(); i++) 
                {
                    
                    Row assetrow= sheet.createRow(9+i);
                    lastrow++;
                    Assets assettoprint = listAssets.get(i);
                    String[] data = asset.assetToCSVData(assettoprint);
                    for (int m = 0; m < listTypes.size(); m++) 
                    {
                        type = listTypes.get(m);
                        if (assettoprint.getTypeID().equals(type.getTypesID())) {
                            data[4] = type.getTypesDetails();
                        }
                        
                    }
                    
                    int qty = assettoprint.getAssetOriginQty();
                    totalasset = totalasset + qty;
                    if (qty < 6) {
                        data[5] = "Low";
                    } else if (qty >= 6 && qty <= 10) {
                        data[5] = "Warning";
                    } else if (qty > 10) {
                        data[5] = "Enough";
                    }
                    
                    for (int m = 0; m < data.length; m++) {
                        Cell cell1 = assetrow.createCell(1 + m);

                        if (m == 3) {
                            int qtyconv = Integer.parseInt(data[m]);
                            cell1.setCellValue(qtyconv);
                            //cell1.setCellStyle(style1);
                        }
                        else
                        {
                            cell1.setCellValue(data[m]);
                        }
                       
                        if(m==5)
                        {
                            if(data[m].equals("Low"))
                            {
                                cell1.setCellStyle(style3);
                            }
                            else if(data[m].equals("Warning"))
                            {
                                cell1.setCellStyle(style2);
                            }
                            else if(data[m].equals("Enough"))
                            {
                                cell1.setCellStyle(style4);
                            }
                        }
                    }
                }
                
                cellvaluetotal.setCellValue(totalasset); 
                cellvaluetotal.setCellStyle(style);
                
                Assets ast = new Assets();
                Row avgAsset = sheet.createRow(lastrow+1);
                Cell cellAvgAsset = avgAsset.createCell(1);
                cellAvgAsset.setCellValue("Average Asset Qty:");
                Cell cellAvgAsset1 = avgAsset.createCell(3);
                cellAvgAsset1.setCellValue(ast.AssetAvg());
                cellAvgAsset1.setCellStyle(style);
                
                Row minAsset = sheet.createRow(lastrow+3);
                Cell cellMinAsset = minAsset.createCell(1);
                cellMinAsset.setCellValue("Minimum Asset Qty:");
                Cell cellMinAsset1 = minAsset.createCell(3);
                cellMinAsset1.setCellValue(ast.AssetMin());
                cellMinAsset1.setCellStyle(style);
                
                Row maxAsset = sheet.createRow(lastrow+5);
                Cell cellMaxAsset = maxAsset.createCell(1);
                cellMaxAsset.setCellValue("Maximum Asset Qty:");
                Cell cellMaxAsset1 = maxAsset.createCell(3);
                cellMaxAsset1.setCellValue(ast.AssetMax());
                cellMaxAsset1.setCellStyle(style);
                
                String fileOut = "C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/AssetReport.xlsx";
                FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
                workbook.write(fileOutputStream);
                
                String filepath2 = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\";
                String filename = "AssetReport.xlsx";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                java.io.FileInputStream filedownload = new java.io.FileInputStream(filepath2 + filename);
                int i;
                while ((i = filedownload.read()) != -1) {
                    out.write(i);
                }
                filedownload.close();
                
                fileOutputStream.close();

                // Close the workbook to release resources
                workbook.close();

                System.out.println("Data appended to the table successfully.");

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(source.equals("assetlogsreport"))
        {
            String filePath = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\templateAssetLogs.xlsx";
            try 
            {
                // Read the existing workbook
                FileInputStream fileInputStream = new FileInputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();

                // Get the first sheet
                XSSFSheet sheet = workbook.getSheetAt(0);

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);

                // Create a CellStyle with the bold font
                CellStyle style = workbook.createCellStyle();
                style.setFont(boldFont);
                
                style.setAlignment(HorizontalAlignment.CENTER);
                
		Row row5= sheet.createRow(5);
                Cell cellrow5 = row5.createCell(2);
                cellrow5.setCellValue("IT Asset Log Report");
                cellrow5.setCellStyle(style);
                

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  

                cellrow5 = row5.createCell(5);
                cellrow5.setCellValue("Date Downloaded: " + formatter.format(date));
                cellrow5.setCellStyle(style);

                Row rowtotalasset= sheet.createRow(6);
                Cell celltotalasset = rowtotalasset.createCell(2);
                celltotalasset.setCellValue("Total Asset Logs: "); 
                celltotalasset.setCellStyle(style);
                
                AssetLogs astlog = new AssetLogs();
                ArrayList<AssetLogs> listastlog= new ArrayList();
                listastlog = astlog.getAllAssetLogs();
                int iterator=0;
                int lastrow = 9;
                for (AssetLogs log : listastlog) {
                    Row assetlogrow= sheet.createRow(9+iterator);
                    String data[] = log.AssetLogToCSVData(log);
                    Users user = new Users();
                    ArrayList<Users> listuser = user.getAllUsers();
                    
                    for(Users usr : listuser)
                    {
                        if(log.getUserID().equals(usr.getUserID()))
                        {
                            data[2] = usr.getUserFullName();
                        } 
                    }
                    
                    Assets asset = new Assets();
                    ArrayList<Assets> listasset = asset.getAllAsset();
                    
                    for(Assets ast : listasset)
                    {
                        if(log.getAssetID().equals(ast.getAssetID()))
                        {
                            data[1] = ast.getAssetName();
                        }
                    }
                    
                    for (int m = 0; m < data.length; m++) 
                    {
                        Cell cell1 = assetlogrow.createCell(1 + m);
                        if (m == 4)
                        {
                            int qtyconv = Integer.parseInt(data[m]);
                            cell1.setCellValue(qtyconv);
                        }
                        else
                        {
                            cell1.setCellValue(data[m]);
                        }
                        
                    }
                    
                    iterator++;
                    lastrow++;
                }
                Cell cellvaluetotal = rowtotalasset.createCell(3);
                cellvaluetotal.setCellValue(iterator); 
                cellvaluetotal.setCellStyle(style);
                
                Row avgAssetLogs = sheet.createRow(lastrow+1);
                Cell cellAvgAssetLog = avgAssetLogs.createCell(1);
                cellAvgAssetLog.setCellValue("Average Asset Log Qty:");
                Cell cellAvgAssetLog1 = avgAssetLogs.createCell(3);
                cellAvgAssetLog1.setCellValue(astlog.AssetLogAvg());
                cellAvgAssetLog1.setCellStyle(style);
                
                Row minAssetLogs = sheet.createRow(lastrow+3);
                Cell cellMinAssetLogs = minAssetLogs.createCell(1);
                cellMinAssetLogs.setCellValue("Minimum Asset Log Qty:");
                Cell cellMinAssetLogs1 = minAssetLogs.createCell(3);
                cellMinAssetLogs1.setCellValue(astlog.AssetLogMin());
                cellMinAssetLogs1.setCellStyle(style);
                
                Row maxAssetLogs = sheet.createRow(lastrow+5);
                Cell cellMaxAssetLogs = maxAssetLogs.createCell(1);
                cellMaxAssetLogs.setCellValue("Maximum Asset Log Qty:");
                Cell cellMaxAssetLogs1 = maxAssetLogs.createCell(3);
                cellMaxAssetLogs1.setCellValue(astlog.AssetLogMax());
                cellMaxAssetLogs1.setCellStyle(style);
                
                String fileOut = "C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/AssetLogReport.xlsx";
                FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
                workbook.write(fileOutputStream);
                
                String filepath2 = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\";
                String filename = "AssetLogReport.xlsx";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                java.io.FileInputStream filedownload = new java.io.FileInputStream(filepath2 + filename);
                int i;
                while ((i = filedownload.read()) != -1) {
                    out.write(i);
                }
                filedownload.close();
                workbook.close();

                System.out.println("Data appended to the table successfully.");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(source.equals("assetrequestreport"))
        {
            String filePath = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\templateAssetRequestReport.xlsx";
            try
            {
                 // Read the existing workbook
                FileInputStream fileInputStream = new FileInputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();

                // Get the first sheet
                XSSFSheet sheet = workbook.getSheetAt(0);
                Font boldFont = workbook.createFont();
                boldFont.setBold(true);
                                
                CellStyle style = workbook.createCellStyle();  
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setFont(boldFont);
                
                Row row5= sheet.createRow(5);
                Cell cellrow5 = row5.createCell(2);
                cellrow5.setCellValue("IT Asset Request Report");
                cellrow5.setCellStyle(style);
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  

                cellrow5 = row5.createCell(5);
                cellrow5.setCellValue("Date Downloaded: " + formatter.format(date));
                cellrow5.setCellStyle(style);

                Row rowtotalasset= sheet.createRow(6);
                Cell celltotalasset = rowtotalasset.createCell(2);
                celltotalasset.setCellValue("Total Requests: "); 
                celltotalasset.setCellStyle(style);
                
                Requests requests = new Requests();
                ArrayList<Requests> listrequests = requests.getAllRequests();
                List<Integer> avgday = requests.requestAvgDay();
                List<Integer> totalassetrqt = requests.TotalAssetPerRequest();
                int iterator = 0;
                int lastrow = 9;
                for(Requests req : listrequests)
                {
                    Row requestrow= sheet.createRow(9+iterator);
                    String[] data = req.RequestToCSVData(req);
                    
                    Users user = new Users ();
                    ArrayList<Users> listuser = user.getAllUsers();
                    
                    for(Users usr : listuser)
                    {
                        if(req.getUserID().equals(usr.getUserID()))
                        {
                            data[1] = usr.getUserFullName();
                        }
                    }
                    
                    Requester requester = new Requester();
                    ArrayList<Requester> listrequester = requester.getAllRequester();
                    
                    for(Requester reqt : listrequester)
                    {
                        if(req.getRequesterID().equals(reqt.getRequesterID()))
                        {
                            data[7] = reqt.getRequesterName();
                        }
                    }
                    
                    String assetsname = "";
                    
                    RequestedAsset requestedAsset = new RequestedAsset();
                    ArrayList<RequestedAsset> listrequestedAsset = requestedAsset.getAllRequested();
                    for (RequestedAsset reqast : listrequestedAsset)
                    {
                        if(reqast.getRequestID().equals(req.getRequestID()))
                        {                            
                            
                            Assets asset = new Assets();
                            ArrayList<Assets>listasset = asset.getAllAsset();
                            
                            for(Assets ast : listasset)
                            {
                                if(ast.getAssetID().equals(reqast.getAssetID()))
                                {
                                    assetsname = "Asset: "+ast.getAssetName() + ", Qty: " + reqast.getQuantity()+ ", " + assetsname;
                                }
                            }
                        }
                    }
                    
                    data[8] = assetsname;
                    
                    for (int m = 0; m < data.length; m++) 
                    { 
                        Cell cell1 = requestrow.createCell(1 + m);
                        if(m == 10)
                        {
                            cell1.setCellValue(avgday.get(iterator));
                        }
                        else if(m==9)
                        {
                            cell1.setCellValue(totalassetrqt.get(iterator));
                        }
                        else
                        {
                            cell1.setCellValue(data[m]);
                        }
                        
                    }
                    
                    iterator++;
                    lastrow++;
                }
                
                Cell cellvaluetotal = rowtotalasset.createCell(3);
                cellvaluetotal.setCellValue(iterator); 
                cellvaluetotal.setCellStyle(style);
                
                Row avgAssetLogs = sheet.createRow(lastrow+1);
                Cell cellAvgAssetLog = avgAssetLogs.createCell(1);
                cellAvgAssetLog.setCellValue("Maximum Qty Asset Requested: ");
                Cell cellAvgAssetLog1 = avgAssetLogs.createCell(3);
                cellAvgAssetLog1.setCellValue(requests.RequestQtyMax());
                cellAvgAssetLog1.setCellStyle(style);
                
                Row minAssetLogs = sheet.createRow(lastrow+3);
                Cell cellMinAssetLogs = minAssetLogs.createCell(1);
                cellMinAssetLogs.setCellValue("Minimum Qty Asset Requested:");
                Cell cellMinAssetLogs1 = minAssetLogs.createCell(3);
                cellMinAssetLogs1.setCellValue(requests.RequestQtyMin());
                cellMinAssetLogs1.setCellStyle(style);
                
                Row maxAssetLogs = sheet.createRow(lastrow+5);
                Cell cellMaxAssetLogs = maxAssetLogs.createCell(1);
                cellMaxAssetLogs.setCellValue("Maximum Request by User:");
                Cell cellMaxAssetLogs1 = maxAssetLogs.createCell(3);
                cellMaxAssetLogs1.setCellValue(requests.RequestByUserMax());
                cellMaxAssetLogs1.setCellStyle(style);
               
                Row maxAssetRequested= sheet.createRow(lastrow+7);
                Cell cellMaxAssetRequested = maxAssetRequested.createCell(1);
                cellMaxAssetRequested.setCellValue("Minimum Request by User:");
                Cell cellMaxAssetRequested1 = maxAssetRequested.createCell(3);
                cellMaxAssetRequested1.setCellValue(requests.RequestByUserMin());
                cellMaxAssetRequested1.setCellStyle(style);
                
                maxAssetLogs = sheet.createRow(lastrow+9);
                cellMaxAssetLogs = maxAssetLogs.createCell(1);
                cellMaxAssetLogs.setCellValue("Asset Most Requested by User:");
                cellMaxAssetLogs1 = maxAssetLogs.createCell(3);
                
                Assets asset = new Assets();
                String assetmin = "";
                String assetmax = "";
                
                ArrayList<Assets>listast = asset.getAllAsset();
                for(Assets ast : listast)
                {
                    if(ast.getAssetID().equals(requests.assetRequestedMin()))
                    {
                        assetmin = ast.getAssetName();
                    }
                    
                    if(ast.getAssetID().equals(requests.assetRequestedMax()))
                    {
                        assetmax = ast.getAssetName();
                    }
                }
                
                cellMaxAssetLogs1.setCellValue(assetmax);
                cellMaxAssetLogs1.setCellStyle(style);
                
                maxAssetRequested= sheet.createRow(lastrow+11);
                cellMaxAssetRequested = maxAssetRequested.createCell(1);
                cellMaxAssetRequested.setCellValue("Asset Least Requested by User:");
                cellMaxAssetRequested1 = maxAssetRequested.createCell(3);
                cellMaxAssetRequested1.setCellValue(assetmin);
                cellMaxAssetRequested1.setCellStyle(style);
                
                String fileOut = "C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/AssetRequestReport.xlsx";
                FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
                workbook.write(fileOutputStream);
                
                String filepath2 = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\";
                String filename = "AssetRequestReport.xlsx";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                java.io.FileInputStream filedownload = new java.io.FileInputStream(filepath2 + filename);
                int i;
                while ((i = filedownload.read()) != -1) {
                    out.write(i);
                }
                filedownload.close();
                workbook.close();
                
                workbook.close();
                System.out.println("Data appended to the table successfully.");

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(source.equals("assetmovementreport"))
        {
            String filePath = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\templateAssetLogs.xlsx";
            try 
            {
                // Read the existing workbook
                FileInputStream fileInputStream = new FileInputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                fileInputStream.close();

                // Get the first sheet
                XSSFSheet sheet = workbook.getSheetAt(0);

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);

                // Create a CellStyle with the bold font
                CellStyle style = workbook.createCellStyle();
                style.setFont(boldFont);
                
                style.setAlignment(HorizontalAlignment.CENTER);
                
		Row row5= sheet.createRow(5);
                Cell cellrow5 = row5.createCell(2);
                cellrow5.setCellValue("IT Asset Movement Report");
                cellrow5.setCellStyle(style);
                

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Date date = new Date();  

                cellrow5 = row5.createCell(5);
                cellrow5.setCellValue("Date Downloaded: " + formatter.format(date));
                cellrow5.setCellStyle(style);

                Row rowtotalasset= sheet.createRow(6);
                Cell celltotalasset = rowtotalasset.createCell(2);
                celltotalasset.setCellValue("Total Asset Logs: "); 
                celltotalasset.setCellStyle(style);
                
                AssetLogs astlog = new AssetLogs();
                ArrayList<AssetLogs> listastlog= new ArrayList();
                listastlog = astlog.getAllAssetLogs();
                int iterator=0;
                int lastrow = 9;
                for (AssetLogs log : listastlog) {
                    String status = log.getUpdateOperation();
                    
                    if(status.equals("asset_movement"))
                    {
                        Row assetlogrow= sheet.createRow(9+iterator);
                        String data[] = log.AssetLogToCSVData(log);
                        Users user = new Users();
                        ArrayList<Users> listuser = user.getAllUsers();

                        for(Users usr : listuser)
                        {
                            if(log.getUserID().equals(usr.getUserID()))
                            {
                                data[2] = usr.getUserFullName();
                            } 
                        }

                        Assets asset = new Assets();
                        ArrayList<Assets> listasset = asset.getAllAsset();

                        for(Assets ast : listasset)
                        {
                            if(log.getAssetID().equals(ast.getAssetID()))
                            {
                                data[1] = ast.getAssetName();
                            }
                        }

                        for (int m = 0; m < data.length; m++) 
                        {
                            Cell cell1 = assetlogrow.createCell(1 + m);
                            if (m == 4)
                            {
                                int qtyconv = Integer.parseInt(data[m]);
                                cell1.setCellValue(qtyconv);
                            }
                            else
                            {
                                cell1.setCellValue(data[m]);
                            }

                        }

                        iterator++;
                        lastrow++;
                    }
                    
                }
                Cell cellvaluetotal = rowtotalasset.createCell(3);
                cellvaluetotal.setCellValue(iterator); 
                cellvaluetotal.setCellStyle(style);
                
                Row avgAssetLogs = sheet.createRow(lastrow+1);
                Cell cellAvgAssetLog = avgAssetLogs.createCell(1);
                cellAvgAssetLog.setCellValue("Average Asset Log Qty:");
                Cell cellAvgAssetLog1 = avgAssetLogs.createCell(3);
                cellAvgAssetLog1.setCellValue(astlog.AssetLogAvg());
                cellAvgAssetLog1.setCellStyle(style);
                
                Row minAssetLogs = sheet.createRow(lastrow+3);
                Cell cellMinAssetLogs = minAssetLogs.createCell(1);
                cellMinAssetLogs.setCellValue("Minimum Asset Log Qty:");
                Cell cellMinAssetLogs1 = minAssetLogs.createCell(3);
                cellMinAssetLogs1.setCellValue(astlog.AssetLogMin());
                cellMinAssetLogs1.setCellStyle(style);
                
                Row maxAssetLogs = sheet.createRow(lastrow+5);
                Cell cellMaxAssetLogs = maxAssetLogs.createCell(1);
                cellMaxAssetLogs.setCellValue("Maximum Asset Log Qty:");
                Cell cellMaxAssetLogs1 = maxAssetLogs.createCell(3);
                cellMaxAssetLogs1.setCellValue(astlog.AssetLogMax());
                cellMaxAssetLogs1.setCellStyle(style);
                
                String fileOut = "C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/AssetMovementReport.xlsx";
                FileOutputStream fileOutputStream = new FileOutputStream(fileOut);
                workbook.write(fileOutputStream);
                
                String filepath2 = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\";
                String filename = "AssetMovementReport.xlsx";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                java.io.FileInputStream filedownload = new java.io.FileInputStream(filepath2 + filename);
                int i;
                while ((i = filedownload.read()) != -1) {
                    out.write(i);
                }
                filedownload.close();
                workbook.close();

                System.out.println("Data appended to the table successfully.");
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String source = request.getParameter("source");

        if (source.equals("assetreport1")) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Sheet1");

                // Create sample data
                Object[][] data = {
                    {"Name", "Age", "Email"},
                    {"John Doe", 30, "john.doe@example.com"},
                    {"Jane Smith", 25, "jane.smith@example.com"}
                };

                // Create a cell style with a specific background color
                CellStyle coloredCellStyle = workbook.createCellStyle();
                coloredCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                coloredCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                // Write data to Excel file with formatting
                for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
                    Row row = sheet.createRow(rowIndex);
                    for (int columnIndex = 0; columnIndex < data[rowIndex].length; columnIndex++) {
                        Cell cell = row.createCell(columnIndex);
                        cell.setCellValue(data[rowIndex][columnIndex].toString());

                        // Apply formatting to specific columns (e.g., columns 1 and 2)
                        if (columnIndex == 1 || columnIndex == 2) {
                            cell.setCellStyle(coloredCellStyle);
                        }
                    }
                }

                // Save the workbook to a file
                try (FileOutputStream fileOut = new FileOutputStream("C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/output.xlsx")) {
                    workbook.write(fileOut);
                    System.out.println("Data written to Excel successfully!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            String filepath2 = "C:\\Users\\Afiq\\OneDrive\\Documents\\NetBeansProjects\\ITAM\\web\\assets\\reports\\";
            String filename = "output.xlsx";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath2 + filename);
            int i;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
        } else if (source.equals("assetreport2")) {
            try (Workbook workbook = new XSSFWorkbook()) {
                // Create a sheet
                Sheet sheet = workbook.createSheet("Sheet1");

                // Sample data
                String[] data = {"Header 1", "Header 2", "Header 3"};

                // Populate the first row with data
                Row headerRow = sheet.createRow(0);
                for (int colNum = 0; colNum < data.length; colNum++) {
                    Cell cell = headerRow.createCell(colNum);
                    cell.setCellValue(data[colNum]);
                }

                // Specify the column to merge (column index starts from 0)
                int columnIndexToMerge = 1;

                // Specify the range of cells to merge in the first row
                int startCell = 1;  // Start merging from the second cell (index 1)
                int endCell = 2;    // End merging at the third cell (index 2)

                // Create a CellRangeAddress for the specified row and cells
                CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, startCell, endCell);

                // Merge the specified cells in the first row
                sheet.addMergedRegion(mergedRegion);

                // Style the merged cells (optional)
                CellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);

                for (int i = startCell; i <= endCell; i++) {
                    Cell mergedCell = headerRow.getCell(i);
                    mergedCell.setCellStyle(style);
                }

                // Save the workbook to a file
                try (FileOutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
                    workbook.write(fileOut);
                }

                System.out.println("Workbook created successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(source.equals("assetreport3"))
        {
            String reporttitle = "KPJ Damansara Specialist IT Asset";
            String[] header = {"AssetID", "Asset Name", "Asset Remarks","Asset Current Quantity","Type"};
            
            try(Workbook workbook = new XSSFWorkbook())
            {
                Sheet sheet = workbook.createSheet("Sheet1");
                Row headerRow = sheet.createRow(0);
                Cell cell = headerRow.createCell(0);
                cell.setCellValue(reporttitle);
                
                int startCell = 0;
                int endCell = 5;
                
                CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, startCell, endCell);
                sheet.addMergedRegion(mergedRegion);
                
                CellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);
                
                for (int i = startCell; i <= endCell; i++) {
                    Cell mergedCell = headerRow.getCell(i);
                    mergedCell.setCellStyle(style);
                }
                
                try (FileOutputStream fileOut = new FileOutputStream("C:/Users/Afiq/OneDrive/Documents/NetBeansProjects/ITAM/web/assets/reports/output2.xlsx")) {
                    workbook.write(fileOut);
                }

                System.out.println("Workbook created successfully.");
                
                
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
