package com.controller;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import connetion.GoneCase;

public class DeleteAsset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteAsset() {
		super();
	}
 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		PrintWriter pw = response.getWriter();
		InputStreamReader isr = new InputStreamReader(request.getInputStream());
		BufferedReader in = new BufferedReader(isr);
		String line = in.readLine();

		JSONObject object = null;
		try {
			object = new JSONObject(line);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int asset_id = 0;
		try {
			asset_id = object.getInt("assetId");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (asset_id == 0) {
			out.println("Please insert valid data");
			 

		} else {
			try {
				 

				String sql = " DELETE FROM asset where asset_id = "+asset_id;
				int update = GoneCase.update(sql);

				String msg = "";
				if (update != 0) {
					msg = " Record has been del";
					pw.println("<font size='6' color=blue>" + msg + "</font>");

				} else {
					msg = "failed to del the data";
					pw.println("<font size='6' color=blue>" + msg + "</font>");
				}

			} catch (Exception e) {
				System.out.print(e);
			}

		}
	}
}