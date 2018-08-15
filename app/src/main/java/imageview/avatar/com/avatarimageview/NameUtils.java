package imageview.avatar.com.avatarimageview;

public class NameUtils {

    public static String getShortName(String name) {
        try {
            String[] splittedNames = name.split(" ");//no i18n
            String shortName;
            if (splittedNames.length == 1) {
                shortName = splittedNames[0].substring(0, 2);
            } else {
                shortName = splittedNames[0].substring(0, 1) + splittedNames[splittedNames.length - 1].substring(0, 1);
            }
            return shortName.toUpperCase();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "";
        }
    }
}