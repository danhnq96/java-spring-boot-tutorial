package com.technologyplan.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SearchVO {

    private String keyword;

    private String startDate;

    private String endDate;

    private int page = 1;

    private int pageSize = 5;

    public String getEndDate() throws Exception{
        String date = this.endDate;
        if(date != null && !date.equals("")){
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date to = transFormat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(to);
            c.add(Calendar.DATE, 1);
            to = c.getTime();
            date = transFormat.format(to);
        }
        return date;
    }
}
