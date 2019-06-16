package com.example.management_system.component;

import java.time.LocalDateTime;

/**
 * @author 赵鑫
 * @create 2019-06-16 21:35
 */
public class Utils {
    public static boolean isAgainst(LocalDateTime time1Begin, LocalDateTime time1End, LocalDateTime time2Begin, LocalDateTime time2End) {
        boolean result = true;
        if (time1Begin.isAfter(time2End) || time2Begin.isAfter(time1End)){
            result = false;
        }
        return result;
    }
}
