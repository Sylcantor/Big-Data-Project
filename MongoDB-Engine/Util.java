package ProjetBD;

import java.util.Iterator;

public class Util {
    public static String hostName = "localhost";
    public static int port = 27017;
    public static String dbName = "zooDB";
    public static String userName = "teamZoo";
    public static String passWord = "passWord";

    public static String filePathToImport = "Json";

    public static String filePathToExport = "ExportedJson";

    public static void displayIterator(Iterator it, String message){
        System.out.println(" \n #### "+ message + " ################################");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
