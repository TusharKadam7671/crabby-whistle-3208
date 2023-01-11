package com.app.usecases;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.bean.Product;
import com.app.exception.ProductException;
import com.app.utility.DBUtil;

public class GetId {

    public static int getId(String username) {

        int userid = 0;

        try (Connection conn = DBUtil.provideConnection()) {

            PreparedStatement ps = conn.prepareStatement("select userid from user where username = ? ");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                userid = rs.getInt("userid");

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return userid;
    }

}
