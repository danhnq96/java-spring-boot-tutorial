package com.sprint1backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long numberItem=0;
    private long numberTicketSellDateStart=0;
    private long priceTicketSellDateStart=0;
    private long numberTicketSellDateEnd=0;
    private long priceTicketSellDateEnd=0;
    private long numberTicketSellDateStartCompare=0;
    private long priceTicketSellDateStartCompare=0;
    private long numberTicketSellDateEndCompare=0;
    private long priceTicketSellDateEndCompare=0;
    private String timeSelectionCompare="";
    private String item="";
    private String type="";
    private String message="";
    private Date dateStart;
    private Date dateEnd;
    private Date compareReportDateStart;
    private Date compareReportDateEnd;

    public Statistic() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id_statistic) {
        this.id = id_statistic;
    }

    public long getNumberItem() {
        return numberItem;
    }

    public void setNumberItem(long number) {
        this.numberItem = number;
    }

    public long getNumberTicketSellDateStart() {
        return numberTicketSellDateStart;
    }

    public void setNumberTicketSellDateStart(long numberTicketSellDateStart) {
        this.numberTicketSellDateStart = numberTicketSellDateStart;
    }

    public long getPriceTicketSellDateStart() {
        return priceTicketSellDateStart;
    }

    public void setPriceTicketSellDateStart(long priceTicketSellDateStart) {
        this.priceTicketSellDateStart = priceTicketSellDateStart;
    }

    public long getNumberTicketSellDateEnd() {
        return numberTicketSellDateEnd;
    }

    public void setNumberTicketSellDateEnd(long numberTicketSellDateEnd) {
        this.numberTicketSellDateEnd = numberTicketSellDateEnd;
    }

    public long getPriceTicketSellDateEnd() {
        return priceTicketSellDateEnd;
    }

    public void setPriceTicketSellDateEnd(long priceTicketSellDateEnd) {
        this.priceTicketSellDateEnd = priceTicketSellDateEnd;
    }

    public long getNumberTicketSellDateStartCompare() {
        return numberTicketSellDateStartCompare;
    }

    public void setNumberTicketSellDateStartCompare(long numberTicketSellDateStartCompare) {
        this.numberTicketSellDateStartCompare = numberTicketSellDateStartCompare;
    }

    public long getPriceTicketSellDateStartCompare() {
        return priceTicketSellDateStartCompare;
    }

    public void setPriceTicketSellDateStartCompare(long priceTicketSellDateStartCompare) {
        this.priceTicketSellDateStartCompare = priceTicketSellDateStartCompare;
    }

    public long getNumberTicketSellDateEndCompare() {
        return numberTicketSellDateEndCompare;
    }

    public void setNumberTicketSellDateEndCompare(long numberTicketSellDateEndCompare) {
        this.numberTicketSellDateEndCompare = numberTicketSellDateEndCompare;
    }

    public long getPriceTicketSellDateEndCompare() {
        return priceTicketSellDateEndCompare;
    }

    public void setPriceTicketSellDateEndCompare(long priceTicketSellDateEndCompare) {
        this.priceTicketSellDateEndCompare = priceTicketSellDateEndCompare;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSelectionCompare() {
        return timeSelectionCompare;
    }

    public void setTimeSelectionCompare(String timeSelectionCompare) {
        this.timeSelectionCompare = timeSelectionCompare;
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

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", numberItem=" + numberItem +
                ", numberTicketSellDateStart=" + numberTicketSellDateStart +
                ", priceTicketSellDateStart=" + priceTicketSellDateStart +
                ", numberTicketSellDateEnd=" + numberTicketSellDateEnd +
                ", priceTicketSellDateEnd=" + priceTicketSellDateEnd +
                ", numberTicketSellDateStartCompare=" + numberTicketSellDateStartCompare +
                ", priceTicketSellDateStartCompare=" + priceTicketSellDateStartCompare +
                ", numberTicketSellDateEndCompare=" + numberTicketSellDateEndCompare +
                ", priceTicketSellDateEndCompare=" + priceTicketSellDateEndCompare +
                ", timeSelectionCompare='" + timeSelectionCompare + '\'' +
                ", item='" + item + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", compareReportDateStart=" + compareReportDateStart +
                ", compareReportDateEnd=" + compareReportDateEnd +
                '}';
    }
}
