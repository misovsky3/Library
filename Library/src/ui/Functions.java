package ui;

public class Functions {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
            if (d<0)
                return false;
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static void alert(String text){
        System.out.println("\u001B[31m" + text + "\u001B[0m");
    }


}
