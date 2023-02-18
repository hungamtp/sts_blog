package hunnid.com.blog.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DateUtils {

    public static final String TIME_ZONE = "Asia/Ho_Chi_Minh";

    public static LocalDateTime getLocalDateTimeByTimeZone(){
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(TIME_ZONE));
        return dateTime.toLocalDateTime();
    }

}
