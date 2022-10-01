package com.app.bean;

import java.sql.Date;

public class Auction {

    private int auctionid;
    private int sellerid;
    private int prodid;
    private Date startdate;
    private Date enddate;
    private float price;
    private int buyerid;

    public Auction() {

    }

    public Auction(int auctionid, int sellerid, int prodid, Date startdate, Date enddate, float price, int buyerid) {
        super();
        this.auctionid = auctionid;
        this.sellerid = sellerid;
        this.prodid = prodid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.price = price;
        this.buyerid = buyerid;
    }

    public int getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    public int getSellerid() {
        return sellerid;
    }

    public void setSellerid(int sellerid) {
        this.sellerid = sellerid;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(int buyerid) {
        this.buyerid = buyerid;
    }

    @Override
    public String toString() {
        return "Auction [auctionid=" + auctionid + ", sellerid=" + sellerid + ", prodid=" + prodid + ", startdate="
                + startdate + ", enddate=" + enddate + ", price=" + price + ", buyerid=" + buyerid + "]";
    }

}
