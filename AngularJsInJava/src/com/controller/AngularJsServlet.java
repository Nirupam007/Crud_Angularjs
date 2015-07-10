 package com.controller;
 import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.AssetData;
//import com.model.PersonData;

public class AngularJsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AngularJsServlet() {
		super();
	}

	private void fixHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.addHeader("Access-Control-Max-Age", "86400");
	    
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		fixHeaders(response);
		
	    String db_url="jdbc:mysql://localhost:3306/assets";
	    String db_user = "root";
	    String db_pass = "";
	    // Register JDBC driver
	    
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");
		    // Open a connection
		    Connection conn = DriverManager.getConnection(db_url,db_user,db_pass);
		    Statement stmt = conn.createStatement();
		    
		    System.out.println("connection established successfully   get methd working...!!");
		    
		    String sql= "SELECT * FROM asset";
		    
	        ResultSet rs = stmt.executeQuery(sql);
		    
	        List<AssetData> assets = new ArrayList<AssetData>();
	        while(rs.next()){
	        	AssetData asset = new AssetData();
	        	asset.setAssetId(rs.getInt("asset_id"));
	        	asset.setAssetTitle(rs.getString("asset_title"));
	        	asset.setAssetDesc(rs.getString("asset_desc"));
	        	asset.setAssetImageURL(rs.getString("asset_image_url"));
	        	asset.setAssetPrice(rs.getInt("asset_price"));
	        	asset.setAssetCategory(rs.getInt("asset_category"));
	        	
	        	assets.add(asset);
	        }
	        
    		String json = new Gson().toJson(assets);
			response.setContentType("application/json");
			response.getWriter().write(json);
	        
	        
	    }catch(Exception e){
	    	System.out.print(e);
	    }
	}

}
