package me.siyum.schola.controller.logincontrol;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.util.Env;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    public static boolean writeToken(String id, String role) throws SQLException, IOException, ClassNotFoundException {
        String token = generateToken();
        System.out.println(token);
        Env.token = token;
        System.out.println(Env.token + " env");
        return CRUDUtil.execute("INSERT INTO login_tokens VALUES(?,?,?)",
                token,
                id,
                role);
    }

    private static int getTokenID() throws SQLException, ClassNotFoundException {
        int lgtID = 0;
        ResultSet res = CRUDUtil.execute("SELECT lgtID FROM login_token WHERE id DESC");
        if (res.next()) {
            lgtID = res.getInt(1);
        }
        return lgtID;
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

    private static void createFile() throws IOException {
        File folder = new File("C:/Schola");
        if (!folder.exists()) {
            boolean mkdir = folder.mkdir();
        }

        File token = new File(Env.file);
        if (!token.exists()) {
            final boolean newFile = token.createNewFile();
        }
    }

    public static ArrayList<String> loginValidate(String user, String pass) throws SQLException, ClassNotFoundException {
        System.out.println(user + " " + pass);
        ResultSet res = CRUDUtil.execute("SELECT role,eID FROM users WHERE username=? AND password=?",
                user, pass
        );
        ArrayList<String> list = new ArrayList<>();
        if (res.next()) {
            System.out.println(res.getString(1));
            System.out.println(res.getString(2));
            list.add(
                    res.getString(1)
            );
            list.add(
                    res.getString(2)
            );
        }
        System.out.println(list);
        return list;
    }
}
