package hi.cord.com.common.service.common;

import com.google.gson.JsonObject;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public interface CommonService {
    boolean validPattern(String parameter, String patternName);

    //Long 유효성 검사하기.
    Long checkVDLong(String parameter, int defaultValue);

    //int 유효성 검사하기.
    int checkVDInt(String parameter, int defaultValue);

    //Integer로 Null값 체크하기.
    int checkVDInteger(Integer parameter, int default_value);

    //Float 유효성 검사하기.
    float checkVDFloat(String parameter, int defaultValue);

    //데이트 포맷바꾸기.
    Timestamp convertDateToday(String formatYouWnat) throws Exception;

    Timestamp convertDateFormat(String formatYouWnat, int day) throws Exception;

    //Encode SHA256
    String buildSHA256(String str);

    //Post Email
    String mainSendMail(String toEmail, String from, String mailSubject, String subTitle, String mailContent) throws Exception;

    String getImgUsingJsoup(String imgSrc, String savedDirectoryName) throws IOException, StringIndexOutOfBoundsException;

    JsonObject getResponseAPI(String apiUrl) throws IOException;

    List<String> extractImgSrc(String content);

    String removeTags(String content);

    Pageable getPageable(Integer pageIndex, Integer pageSize);
}