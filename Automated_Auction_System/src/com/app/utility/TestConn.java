package com.app.utility;

import java.sql.Connection;

public class TestConn {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.provideConnection();
		
		System.out.println(conn);

	}

}
