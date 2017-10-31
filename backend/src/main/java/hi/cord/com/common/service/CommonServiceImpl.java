package hi.cord.com.common.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Common service.
 */
@Service
public class CommonServiceImpl implements CommonService {
    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    private JavaMailSender mailSender;

    /**
     * Instantiates a new Common service.
     *
     * @param mailSender the mail sender
     */
    @Autowired
    public CommonServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private static final String FILE_PATH = "/Users/hunseol/Desktop/project/shooney/stack/";
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
                pattern= ".[가-힣]{1,14}";
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
        Timestamp fromStamp = new java.sql.Timestamp(today.getTime());
        LOG.info("return : convertDateToday {}", fromStamp);
        return fromStamp;
    }

    @Override
    public Timestamp convertDateFormat(String formatYouWnat, int day) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatYouWnat);
        Date today = new Date();
        Date toDate = new Date(today.getTime() + ((1000 * 60 * 60 * 24 * day)));
        String toDateStr = format.format(toDate);
        toDate = format.parse(toDateStr);
        Timestamp toStamp = new java.sql.Timestamp(toDate.getTime());
        LOG.info("return : convertDateFormat {}", toStamp);
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

    private void mainSendMail(String toEmail, String from, String mailSubject, String mailContent) {
        new Thread(() -> {
            String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if (toEmail.matches(emailRegex)) {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    // true로서 멀티파트 메세지라는 의미
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    messageHelper.setFrom(from);
                    messageHelper.setTo(toEmail);
                    messageHelper.setSubject(mailSubject);
                    messageHelper.setText(
                            "<html>"
                                    + "<body>"
                                    + "<div style='text-align : left; font-color:black; font-size : 14px;'>"
                                    + mailContent
                                    + "</div>"
                                    + "</body>"
                                    + "</html>", true);
                    // 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
                    // FileSystemResource fileImage=new
                    // FileSystemResource("/resources/img/google.png");
                    // messageHelper.addAttachment("Google Png", fileImage);

                    // 로고 넣기
                    // ClassPathResource image=new
                    // ClassPathResource("/resources/img/google.png");
                    // messageHelper.addInline("Google_Logo", image);
                    mailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public String mainSendMail(String toEmail, String from, String mailSubject, String subTitle, String mailContent) throws Exception {
        if (toEmail == null) {
            return "fail";
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable runnable = () -> {
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
            String mailBody = "";
            mailBody += "<html>"
                    + "    <body>"
                    + "        <table style='width:60%; margin:0 auto; text-align:center; border:1px solid #000; margin-top:30px;'>"
                    + "            <tbody>"
                    + "                <tr>"
                    + "                    <td style='padding-top:30px;'>"
                    + "                        <img src='http://imedisyn.com/resources/assets/img/logo.png' style='height:70px; margin-top:30px; margin-bottom:10px; '></img>"
                    + "                        <hr style='width:70%; border-color:#000;'>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <h2 style='font-weight:400;'>" + subTitle + "</h2>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <p style='width:60%; margin:0 auto; background-color:#efefef; padding:30px;'>"
                    + "                            " + mailContent
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <hr style='width:70%; border-color:#000;'>"
                    + "                        <p style='font-size:8pt; color:#a2a2a2; margin-bottom:30px;'><br><br>"
                    + "                            본 메일은 발신 전용으로 회신되지 않습니다.<br>"
                    + "                            관련 문의사항은 hi-cord.com 고객센터를 이용하시기 바랍니다."
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "                <tr>"
                    + "                    <td>"
                    + "                        <p style='background-color:#efefef; min-height:10px; color:#444; padding:20px; margin:0;'>"
                    + "                            <strong>Tel.</strong> 02-747-7422 &nbsp;&nbsp;&nbsp;<strong>Mail.</strong> imedisyn@imedisyn.com &nbsp;&nbsp;&nbsp;<strong>Homepage.</strong> www.imedisyn.com"
                    + "                        </p>"
                    + "                    </td>"
                    + "                </tr>"
                    + "            </tbody>"
                    + "        </table>"
                    + "    </body>"
                    + "</html>";

            if (toEmail.matches(emailregex)) {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    // true로서 멀티파트 메세지라는 의미
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    messageHelper.setFrom(from);
                    messageHelper.setTo(toEmail);
                    messageHelper.setSubject(mailSubject);
                    messageHelper.setText(mailBody, true);
                    // 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
                    // FileSystemResource fileImage=new
                    // FileSystemResource("/resources/img/google.png");
                    // messageHelper.addAttachment("Google Png", fileImage);

                    // 로고 넣기
                    // ClassPathResource image=new
                    // ClassPathResource("/resources/img/google.png");
                    // messageHelper.addInline("Google_Logo", image);
                    mailSender.send(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(runnable);
        executorService.shutdown();
        return "success";
    }

    @Override
    public void validCheckAndSendError(MessageSource messageSource, BindingResult bindingResult, HttpServletRequest request, String inputValue, String objectName, String fieldName, String messagePropertyName) {
        try {
            FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[]{inputValue}, request.getLocale()));
            bindingResult.addError(error);
        } catch (Exception e) {
            FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[]{inputValue}, Locale.getDefault()));
            bindingResult.addError(error);
        }

    }

    @Override
    public String getImgUsingJsoup(String imgSrc, String savedDirectoryName) throws IOException, StringIndexOutOfBoundsException {
        int indexName = imgSrc.lastIndexOf("/");
        if (indexName == imgSrc.length())
            imgSrc = imgSrc.substring(1, indexName);
        indexName = imgSrc.lastIndexOf("/");
        String savedName = imgSrc.substring(indexName, imgSrc.length());

        savedDirectoryName = savedDirectoryName.toLowerCase();
        savedName = savedName.toLowerCase();

        File directory = new File(FILE_PATH + savedDirectoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        URL url = new URL(imgSrc);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(FILE_PATH + savedDirectoryName + savedName));

        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
        return FILE_PATH + savedDirectoryName + savedName;
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
                LOG.info("return API output : {}", jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
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
        String noTags = content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return noTags;
    }
}
