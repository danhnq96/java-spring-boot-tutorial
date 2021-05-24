package com.sprint1backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeReport;
    private String itemReport;
    private String timeSelection;
    private Date dateStart;
    private Date dateEnd;
    private String compareReportTimeSelection;
    private Date compareReportDateStart;
    private Date compareReportDateEnd;

    public Report() {
    }

    public Report(String typeReport, String itemReport, String timeSelection, Date dateStart, Date dateEnd, String compareReportTimeSelection, Date compareReportDateStart, Date compareReportDateEnd) {
        this.typeReport = typeReport;
        this.itemReport = itemReport;
        this.timeSelection = timeSelection;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.compareReportTimeSelection = compareReportTimeSelection;
        this.compareReportDateStart = compareReportDateStart;
        this.compareReportDateEnd = compareReportDateEnd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }

    public String getItemReport() {
        return itemReport;
    }

    public void setItemReport(String itemReport) {
        this.itemReport = itemReport;
    }

    public String getTimeSelection() {
        return timeSelection;
    }

    public void setTimeSelection(String timeSelection) {
        this.timeSelection = timeSelection;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCompareReportTimeSelection() {
        return compareReportTimeSelection;
    }

    public void setCompareReportTimeSelection(String compareReportTimeSelection) {
        this.compareReportTimeSelection = compareReportTimeSelection;
    }

    public Date getCompareReportDateStart() {
        return compareReportDateStart;
    }

    public void setCompareReportDateStart(Date compareReportDateStart) {
        this.compareReportDateStart = compareReportDateStart;
    }

    public Date getCompareReportDateEnd() {
        return compareReportDateEnd;
    }

    public void setCompareReportDateEnd(Date compareReportDateEnd) {
        this.compareReportDateEnd = compareReportDateEnd;
    }
}
