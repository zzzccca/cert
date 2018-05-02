package wz.wzksb.cert.utils;

import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import wz.wzksb.cert.domain.Cert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wu on 18-1-31.
 */
public class TimeConversionUtil {

    public static String date2millis(String data) {
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy.MM.dd").parse(data));
        }catch (ParseException e){}
        return ""+calendar.getTimeInMillis();
    }

    public static String longdate2millis(String data) {
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(data));
        }catch (ParseException e){}
        return String.valueOf(calendar.getTimeInMillis());
    }

    public static String millis2longdate(String millis){
        Date date=new Date(Long.parseLong(millis));
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }

    public static String millis2date(String millis){
        Date date=new Date(Long.parseLong(millis));
        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    public static Page time(Page<Cert> certPage){
        for (int i = 0; i < certPage.getContent().size() && certPage.getContent().size() != 0; i++) {
            if (StringUtils.hasText(certPage.getContent().get(i).getMailReqDate())) {
                certPage.getContent().get(i).setMailReqDate(TimeConversionUtil.millis2longdate(certPage.getContent().get(i).getMailReqDate()));
            }
            if (StringUtils.hasText(certPage.getContent().get(i).getEmsDate())) {
                certPage.getContent().get(i).setEmsDate(TimeConversionUtil.millis2date(certPage.getContent().get(i).getEmsDate()));
            }
        }
        return certPage;
    }

}
