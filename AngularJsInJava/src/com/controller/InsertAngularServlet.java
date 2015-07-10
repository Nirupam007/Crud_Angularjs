package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class InsertAngularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */

	public InsertAngularServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
 	/*private void fixHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.addHeader("Access-Control-Max-Age", "86400");
	    
	} */
 
	private void fixHeaders(HttpServletResponse response) {
 	     response.addHeader("Access-Control-Allow-Origin", "*");
 	     response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
 	     response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
 	     response.addHeader("Access-Control-Max-Age", "86400");
 	  
 	     
 	 }
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String db_url = "jdbc:mysql://localhost:3306/assets";
		String db_user = "root";
		String db_pass = "";

		fixHeaders(response);

		try {

			InputStreamReader isr = new InputStreamReader(
					request.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String line = in.readLine();
			JSONObject object = new JSONObject(line);

			String asset_title = object.getString("assetTitle");
			String asset_desc = object.getString("assetDesc");
			String asset_image_url = object.getString("assetImageURL");
			String asset_price = object.getString("assetPrice");
			String asset_category = object.getString("assetCategory");

			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection conn = DriverManager.getConnection(db_url,
					db_user, db_pass);
			conn.createStatement();
			System.out
					.println("connection established successfully  post...!!");
			PreparedStatement ps = conn
					.prepareStatement("insert into asset(asset_title,asset_desc,asset_image_url,asset_price,asset_category) values(?,?,?,?,?)");

			ps.setString(1, asset_title);
			ps.setString(2, asset_desc);
			ps.setString(3, asset_image_url);
			ps.setString(4, asset_price);
			ps.setString(5, asset_category);

			int i = ps.executeUpdate();
			// conn.commit();
			String msg = " ";
			if (i != 0) {
				msg = "Record has been inserted";
				pw.println(msg);

			} else {
				msg = "failed to insert the data";
				pw.println(msg);
			}
			ps.close();
		} catch (Exception e) {
			pw.println(e);
		}
	}
}