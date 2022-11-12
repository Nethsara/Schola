package me.siyum.schola.controller;

import me.siyum.schola.dao.CRUDUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static String getToken() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT token FROM login_token");
        String token = generateToken();
        while (res.next()) {
            if (token.equalsIgnoreCase(res.getString(1))) {
                getToken();
            }
        }
        return token;
    }

    public static String generateToken() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
