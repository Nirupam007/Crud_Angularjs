package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import connetion.GoneCase;

//import connetion.GoneCase;

public class UpdateAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */

	public UpdateAsset() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void fixHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods",
				"GET, POST, PUT, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With");
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

		fixHeaders(response);
		
		try {
			InputStreamReader isr = new InputStreamReader(
					request.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String line = in.readLine();

			JSONObject object = new JSONObject(line);
			int asset_id = object.getInt("assetId");
			
			System.out.println("asset id: "+asset_id);
			String asset_title = object.getString("assetTitle");
			String asset_desc = object.getString("assetDesc");

			String asset_image_url = object.getString("assetImageURL");
			int asset_price = object.getInt("assetPrice");
			int asset_category = object.getInt("assetCategory");

			String sql = "update asset set asset_title='" + asset_title
					+ "',asset_desc='" + asset_desc + "',asset_image_url='"
					+ asset_image_url + "',asset_price='" + asset_price
					+ "',asset_category='" + asset_category
					+ "'  where asset_id= '" + asset_id + "'";

			int update = GoneCase.update(sql);

			String msg = " ";
			if (update != 0) {
				msg = " Record has been inserted";
				pw.println("<font size='6' color=blue>" + msg + "</font>");

			} else {
				msg = "failed to insert the data";
				pw.println("<font size='6' color=blue>" + msg + "</font>");
			}

		} catch (Exception e) {
			pw.println(e);
		}
	}
}