package kz.kaspi.clientRequestManagement.util;

import kz.kaspi.clientRequestManagement.model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtils {

    public static String getAbbr(String organization) {
        StringBuilder abbrBuilder = new StringBuilder();
        for(String word: organization.toUpperCase().split("\\s+")){
            abbrBuilder.append(word.charAt(0));
        }
        return abbrBuilder.toString();
    }

    public static boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("87\\d{9}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static <S extends Client> int getHashCode(S s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.getPhone()).append(s.getOrganization()).append(s.getBin()).append(s.getFio());
        String st = sb.toString().toUpperCase().replaceAll("\\s+","");
        return st.hashCode();
    }
}
