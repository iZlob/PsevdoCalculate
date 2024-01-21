import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class DBCreator {
    String _expression;
    HashMap<String,String[]> dbSymbols;
    DBCreator(String exp) throws Exception {
        _expression = exp;

        dbSymbols = new HashMap<String, String[]>(){{
            put("0", new String[]{"██████",
                                  "█────█",
                                  "█─██─█",
                                  "█─██─█",
                                  "█─██─█",
                                  "█────█",
                                  "██████"});
            put("1", new String[]{"████",
                                  "██─█",
                                  "█──█",
                                  "██─█",
                                  "██─█",
                                  "██─█",
                                  "████"});
            put("2", new String[]{"██████",
                                  "█────█",
                                  "█─██─█",
                                  "███──█",
                                  "█──███",
                                  "█────█",
                                  "██████"});
            put("3", new String[]{"█████",
                                  "█───█",
                                  "███─█",
                                  "█───█",
                                  "███─█",
                                  "█───█",
                                  "█████"});
            put("4", new String[]{"██████",
                                  "█─██─█",
                                  "█─██─█",
                                  "█────█",
                                  "████─█",
                                  "████─█",
                                  "██████"});
            put("5", new String[]{"██████",
                                  "█────█",
                                  "█─████",
                                  "█────█",
                                  "████─█",
                                  "█────█",
                                  "██████"});
            put("6", new String[]{"██████",
                                  "█────█",
                                  "█─████",
                                  "█────█",
                                  "█─██─█",
                                  "█────█",
                                  "██████"});
            put("7", new String[]{"██████",
                                  "█────█",
                                  "█─██─█",
                                  "███──█",
                                  "██──██",
                                  "█──███",
                                  "██████"});
            put("8", new String[]{"██████",
                                  "█────█",
                                  "█─██─█",
                                  "█────█",
                                  "█─██─█",
                                  "█────█",
                                  "██████"});
            put("9", new String[]{"██████",
                                  "█────█",
                                  "█─██─█",
                                  "█────█",
                                  "████─█",
                                  "█────█",
                                  "██████"});
            put("+", new String[]{"████████",
                                  "████████",
                                  "███──███",
                                  "█──────█",
                                  "███──███",
                                  "████████",
                                  "████████"});
            put("-", new String[]{"████████",
                                  "████████",
                                  "████████",
                                  "█──────█",
                                  "████████",
                                  "████████",
                                  "████████"});
            put("*", new String[]{"███████",
                                  "███████",
                                  "██─█─██",
                                  "███─███",
                                  "██─█─██",
                                  "███████",
                                  "███████"});
            put("/", new String[]{"███████",
                                  "█████─█",
                                  "████─██",
                                  "███─███",
                                  "██─████",
                                  "█─█████",
                                  "███████"});
            put("=", new String[]{"███████",
                                  "███████",
                                  "█─────█",
                                  "███████",
                                  "█─────█",
                                  "███████",
                                  "███████"});
            put(",", new String[]{"███████",
                                  "███████",
                                  "███████",
                                  "███████",
                                  "██───██",
                                  "██───██",
                                  "███████"});

        }};

        WriteToSqlite();
    }
    private void WriteToSqlite() throws Exception {
        try(SQLite db = new SQLite()){
            try {
                String[] linesDB = new String[]{"", "", "", "", "", "", ""};
                char[] lettersExpression = _expression.toCharArray();

                for (char letter : lettersExpression) {
                    if (dbSymbols.containsKey(String.valueOf(letter))) {
                        for (int i = 0; i < linesDB.length; i++) {
                            linesDB[i] += dbSymbols.get(String.valueOf(letter))[i];
                        }
                    }
                }

                db.ExecuteWithoutResult("DROP TABLE IF EXISTS CalculateExpression");
                db.ExecuteWithoutResult(
                        "CREATE TABLE IF NOT EXISTS CalculateExpression (" +
                                "ID INT PRIMARY KEY, " +
                                "LINE1 VARCHAR(100) NOT NULL, " +
                                "LINE2 VARCHAR(100) NOT NULL, " +
                                "LINE3 VARCHAR(100) NOT NULL, " +
                                "LINE4 VARCHAR(100) NOT NULL, " +
                                "LINE5 VARCHAR(100) NOT NULL, " +
                                "LINE6 VARCHAR(100) NOT NULL, " +
                                "LINE7 VARCHAR(100) NOT NULL);");

                String query = String.format("INSERT INTO CalculateExpression (ID, LINE1, LINE2, LINE3, LINE4, LINE5, LINE6, LINE7) VALUES" +
                                             "(1, '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                                             linesDB[0], linesDB[1], linesDB[2], linesDB[3], linesDB[4], linesDB[5], linesDB[6]);
                db.ExecuteWithoutResult(query);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void getPseudoExpression() throws Exception {
        try(SQLite db = new SQLite()){
            try {
                String query = "SELECT LINE1, LINE2, LINE3, LINE4, LINE5, LINE6, LINE7 FROM CalculateExpression";

                Statement statement = db._dbConnection.createStatement();
                ResultSet result = statement.executeQuery(query);

                while(result.next()){
                    for (int i = 1; i <= result.getMetaData().getColumnCount(); i++){
                        System.out.println(result.getString(i));
                    }
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
