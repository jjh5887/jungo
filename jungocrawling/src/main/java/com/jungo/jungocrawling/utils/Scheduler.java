package com.jungo.jungocrawling.utils;

import java.util.Date;

import com.jungo.jungocrawling.Account.AccountRepository;
import com.jungo.jungocrawling.Account.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    DateRepository dateRepository;

    @Autowired
    AccountRepository accountRepository;

    @Scheduled(cron = "5 * * * * *")
    public void deleteItem(){
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
        Date now = new Date();

        String time = format1.format(now);
        
        
        String Syear = time.substring(0,4);
        String Sdate = time.substring(4,10);
        Syear = Syear.replace("-","");
        Sdate = Sdate.replace("-","");

        int year = Integer.parseInt(Syear);
        int date = Integer.parseInt(Sdate);

        System.out.println(year);
        System.out.println(date);

        int month = date / 100;
        int day = date % 100;
        if (day == 31)
            day = 30;
        if (month == 1) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        
        int deleteDate = year * 10000 + month * 100 + day;
        Long OldDate = Long.valueOf(deleteDate);
        System.out.println(deleteDate);


        List<com.jungo.jungocrawling.Account.Date> dates = null;
        if (dateRepository.existsByDate(OldDate)) {
            dates = dateRepository.findByDate(OldDate);
            System.out.println(dates.get(0).getId());
            if (accountRepository.existsById(dates.get(0).getId()))
                accountRepository.deleteByOldid(dates.get(0).getId());
            dateRepository.deleteByDate(OldDate);
        }


    }
}
