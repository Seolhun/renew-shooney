package hi.cord.com.user.common.service.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Common service.
 */
@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    //    private static final String FILE_PATH="/opt/tomcat/files/";
    /*
        (?=.*[0-9]) a digit must occur at least once
		(?=.*[a-z]) a lower case letter must occur at least once
		(?=.*[A-Z]) an upper case letter must occur at least once
		(?=.*[@#$%^&+=]) a special character must occur at least once
		(?=\\S+$) no whitespace allowed in the entire string
		.{8,} at least 8 characters
	*/
    @Override
    public boolean validPattern(String parameter, String patternName) {
        boolean validation = false;
        String pattern;
        switch (patternName) {
            case "password":
                pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20}";
                validation = parameter.matches(pattern);
                break;
            case "email":
                pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
                validation = parameter.matches(pattern);
                break;
            case "id":
                pattern = "^[A-Za-z0-9].{1,20}";
                validation = parameter.matches(pattern);
                break;
            case "name":
                pattern = ".[가-힣]{1,14}";
                validation = parameter.matches(pattern);
                break;
            case "nickname":
                pattern = ".[A-Za-z0-9가-힣]{1,14}";
                validation = parameter.matches(pattern);
                break;
            case "phone":
                pattern = "\\d{10,13}";
                validation = parameter.matches(pattern);
                break;
            case "tel":
                pattern = "\\d{9,12}";
                validation = parameter.matches(pattern);
                break;
        }
        return validation;
    }

    @Override
    public Long checkVDLong(String parameter, int default_value) {
        Long longValue;
        try {
            longValue = Long.parseLong(parameter);
        } catch (Exception e) {
            longValue = Integer.toUnsignedLong(default_value);
        }
        return longValue;
    }

    @Override
    public int checkVDInt(String parameter, int default_value) {
        int int_value;
        try {
            int_value = Integer.parseInt(parameter);
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }

    @Override
    public int checkVDInteger(Integer parameter, int default_value) {
        int int_value;
        try {
            int_value = parameter;
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }

    @Override
    public float checkVDFloat(String parameter, int default_value) {
        float int_value;
        try {
            int_value = Float.parseFloat(parameter);
        } catch (Exception e) {
            int_value = default_value;
        }
        return int_value;
    }


    @Override
    public Timestamp convertDateToday(String formatYouWnat) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatYouWnat);
        Date today = new Date();
        String fromDateStr = format.format(today);
        today = format.parse(fromDateStr);
        Timestamp fromStamp = new Timestamp(today.getTime());
        LOG.debug("r : convertDateToday {}", fromStamp);
        return fromStamp;
    }

    @Override
    public Timestamp convertDateFormat(String formatYouWnat, int day) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatYouWnat);
        Date today = new Date();
        Date toDate = new Date(today.getTime() + ((1000 * 60 * 60 * 24 * day)));
        String toDateStr = format.format(toDate);
        toDate = format.parse(toDateStr);
        Timestamp toStamp = new Timestamp(toDate.getTime());
        LOG.debug("r : convertDateFormat {}", toStamp);
        return toStamp;
    }

    @Override
    public String buildSHA256(String str) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }
            str = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            str = null;
        }
        return str;
    }

    @Override
    public JsonObject getResponseAPI(String apiUrl) throws IOException {
        HttpURLConnection conn = null;
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = new JsonObject();
        try {
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            //String output;
            String jsonStr;
            while ((jsonStr = bufferedReader.readLine()) != null) {
                jsonObject = parser.parse(jsonStr).getAsJsonObject();
                LOG.debug("r API output : {}", jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }
        return jsonObject;
    }

    // content에서 img 태그의 src값만 추출
    @Override
    public List<String> extractImgSrc(String content) {
        List<String> result = new ArrayList<>();
        Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
        Matcher matcher = nonValidPattern.matcher(content);

        while (matcher.find()) {
            result.add(matcher.group(1));
        }

        List<String> files = new ArrayList<>();
        for (String str : result) {
            str = str.replaceAll(":", "/");
            str = str.substring(19);
            files.add(str);
        }

        return files;
    }

    // html tag를 모두 제거함
    @Override
    public String removeTags(String content) {
        return content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }
}
