package com.tv.xeeng.reporttool.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by thanhnvt on 23/06/2014.
 */
public class FileHelper {
    static Logger logger = Logger.getLogger(FileHelper.class);

    private static String readFile(String path, Charset encoding)
    {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        } catch (Exception ex) {
            logger.error(ex);
            return "";
        }
    }

    public static String readFileUTF8(String path) {
        String content = readFile(path, StandardCharsets.UTF_8);

        return content;
    }

    public static String getMatchLog(String matchId) {
        matchId = matchId.replace(".", ""); // no LFI :-P

        File logsBaseDir = new File(Constant.PATH_MATCH_LOG_BASE);
        for (String gameName : logsBaseDir.list()) {
            File gameDir = new File(Constant.PATH_MATCH_LOG_BASE + gameName);
            if (gameDir.isDirectory()) {
                String matchLogPath = "{dir}/match_{matchId}_mini.txt"
                        .replace("{dir}", gameDir.getAbsolutePath())
                        .replace("{matchId}", matchId);
                File matchLog = new File(matchLogPath);
                if (matchLog.exists()) {
                    return readFileUTF8(matchLog.getAbsolutePath());
                }
            }
        }

        return null;
    }
}
