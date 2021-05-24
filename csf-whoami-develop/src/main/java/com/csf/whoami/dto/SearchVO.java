package com.csf.whoami.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchVO {

    private Long roleId = 0L;

    private String keyword;

    private long productTypeId = 0L;

    private Long maximum = 99999L;

    private Long minimum = 0L;

    private long subjectId = 0L;

    private long ageId = 0L;

    private String usable = "ALL";

    private String startDate;

    private String endDate;

    private int page = 1;

    private String publisher;

    private Long mediaTypeId = 0L;

    private Long qnaTypeId = 0L;

    private Long themeTypeId = 1L;

    private Long bannerTypeId;

    private String userType = "ALL";

    public String getEndDate() throws Exception {
        String date = this.endDate;
        if (date != null && !date.equals("")) {
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
