package me.siyum.schola.controller.logincontrol;

import javafx.event.Event;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.util.Env;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public static boolean writeToken(int id, String role) throws SQLException, IOException, ClassNotFoundException {
        String token = getToken();
        createFile();
        FileWriter f = new FileWriter(Env.file);

        f.write(token);
        f.close();

        return CRUDUtil.execute("INSERT INTO login_token VALUES(?,?,?,?)",
                token,
                id,
                role,
                getTokenID());
    }

    private static int getTokenID() throws SQLException, ClassNotFoundException {
        int lgtID = 0;
        ResultSet res = CRUDUtil.execute("SELECT lgtID FROM login_token WHERE id DESC");
        if (res.next()) {
            lgtID = res.getInt(1);
        }
        return lgtID;
    }

    private static String getToken() throws SQLException, ClassNotFoundException, IOException {
        createFile();
        ResultSet res = CRUDUtil.execute("SELECT token FROM login_token");
        String token = generateToken();
        while (res.next()) {
            if (token.equalsIgnoreCase(res.getString(1))) {
                getToken();
            }
        }
        return token;
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

    public static int loginValidate(String user, String pass) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT role,eID FROM users WHERE username=? AND password=?",
                user, pass
        );
        if (res.next()) {
            if (res.getString("role").equals("admin")) {
                return 0;
            } else if (res.getString("role").equals("staff")) {
                return 1;
            } else if (res.getString("role").equals("receptionist")) {
                return 2;
            }
        }
        return -1;
    }

    public static void login(String role, Event aevt) throws IOException {
        if (role.contains("Receptionist")) {
            Navigation.navigate(Routes.RECEPTIONIST, aevt);
        }
    }
}
