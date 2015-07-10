package connetion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.AssetData;
import com.mysql.jdbc.PreparedStatement;
public class GoneCase {
	public static Connection prepareConn() throws InstantiationException, IllegalAccessException, SQLException {
		    System.out.println("-------- MySQL JDBC Connection Testing ------------");
		    Connection c=null;
			String username = "root";
	        String password = "";
	        String url = "jdbc:mysql://localhost:3306/assets";
	        try { 
	        Class.forName("com.mysql.jdbc.Driver").newInstance (); 
	        c = DriverManager.getConnection(url,username,password);
	      //  Statement sat = c.createStatement();
	    	 
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return c;
	}//prepareConn
 
	public static void closeConn(Connection c){
		try {
			if(c!=null)
				c.close();    
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
	}//closeConn
	
	public static int update(String sql) {
		int update = 0;
		Connection c=null;
		try {
			c = prepareConn();
			  
			Statement st = c.createStatement();
			update = st.executeUpdate(sql);    
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
		closeConn(c);
		return update;
	}

	 
	
	
	public ResultSet executequery(String sql){

        ResultSet rs = null;
		Connection c=null;
		try{
			c = prepareConn();
			  
			Statement st = c.createStatement();
			rs   = st.executeQuery(sql); 
			
			System.out.println("value of rsssss!!" + rs);
	 	}catch (Exception ex) {
			System.out.println (ex);
		}/*finally{
			 if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		}*/
		//closeConn(c);	 
		return rs;
	}
	 
/*	public List<AssetData> getList(String sql) {
		Connection c=null;
		List<AssetData> assets = null;
		StringBuffer	query = new StringBuffer();
		PreparedStatement	pstmt	= null;
		ResultSet	rs = null;

		try{
		query.append(sql);
		pstmt = (PreparedStatement) c.prepareStatement(query.toString()); 
		       rs = pstmt.executeQuery();
		   	List<AssetData> assets1 = new ArrayList<AssetData>();
			while (rs.next()) {
				AssetData asset = new AssetData();
				asset.setAssetId(rs.getInt("asset_id"));
				asset.setAssetTitle(rs.getString("asset_title"));
				asset.setAssetDesc(rs.getString("asset_desc"));
				asset.setAssetImageURL(rs.getString("asset_image_url"));
				asset.setAssetPrice(rs.getInt("asset_price"));
				asset.setAssetCategory(rs.getInt("asset_category"));

				o.add(asset);
			}
		              
		}catch(SQLException se){
			System.out.println (se);
		}finally { 

		try{
		if(pstmt!=null)
		pstmt.close();
		if(rs!=null)
		rs.close();
		}catch(Exception ex){
			System.out.println (ex);
		}
		}
		return o;
		}*/
	
	
 	 
	
/*	public static ArrayList<?> getAccountNoByName(String sql) {
		ArrayList<?> a = new ArrayList<Object>();
		Connection c=null;
		try {
			c = prepareConn();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				a.add(rs.getInt(1)+"");
			}
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
		closeConn(c);
		return a;
	}*/

	//------------
	
	 
	
	//--------------------------	
	 
	
 
	
	 
}
