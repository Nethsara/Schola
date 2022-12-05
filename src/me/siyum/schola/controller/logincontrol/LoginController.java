package me.siyum.schola.controller.logincontrol;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.util.Env;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    public static boolean writeToken(String id, String role) throws SQLException, IOException, ClassNotFoundException {
        String token = generateToken();
        Env.token = token;
        return CRUDUtil.execute("INSERT INTO login_tokens VALUES(?,?,?)",
                token,
                id,
                role);
    }

    private static String generateToken() {

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

    public static ArrayList<String> loginValidate(String user, String pass) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT role,eID FROM users WHERE username=? AND password=?",
                user, pass
        );
        ArrayList<String> list = new ArrayList<>();
        if (res.next()) {
            list.add(
                    res.getString(1)
            );
            list.add(
                    res.getString(2)
            );
            return list;
        }
        return null;

    }
}
