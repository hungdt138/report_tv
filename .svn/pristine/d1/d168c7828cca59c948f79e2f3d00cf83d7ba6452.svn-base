package com.tv.xeeng.reporttool.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

public final class CSVUtils {

	 /**
     * download bug
     *
     * @param fileName name of file download
     * @param bugList  bug list for download
     * @return true if download success else false
     * @throws Exception
     */
    public static boolean downloadFile(String fileName, HttpServletResponse response, String data) throws Exception {
        StringBuffer buf = new StringBuffer();
        String content;
        String encodeContent;
        String COMMA = ",";

        try {
            // set output
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();

            // create header colum
            buf.append("UserID" + COMMA + "Name" + COMMA + "NickName"  + COMMA+ "Cash"  + COMMA + "IsOnline"  + COMMA);
            buf.append("RegisterDate"  + COMMA + "Refcode"  + COMMA + "PartnerId"  + COMMA + "LastLogin"  + COMMA + "Second LastLogin" );
            buf.append("CMTND"  + COMMA + "Phone"  + COMMA + "Sex");
            buf.append("\r\n");
            buf.append(data);
            
            // export data
            content = buf.toString();
            encodeContent = new String(content.getBytes("UTF-8"), "UTF-8");
            out.write(encodeContent.getBytes("UTF-8"));
    
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }	
    
    public static String toCSV(String input) {
        if (input == null || "".equals(input)) {
            return "\"\"";
        }
        input = input.trim();
        input = input.replaceAll("\"", "\"\"");
        input = "\"" + input + "\"";
        return input;
    }

    public static void main(String[] args) {
    }
}
