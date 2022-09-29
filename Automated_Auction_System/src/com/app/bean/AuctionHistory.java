package com.app.bean;

import java.sql.Date;

public class AuctionHistory {
	
	private int auctionid;
	private int buyerid;
	private int sellerid;
	private float winningbid;
	private Date auctiondate;
	
	public AuctionHistory()
	{
		
	}

	public AuctionHistory(int auctionid, int buyerid, int sellerid, float winningbid, Date auctiondate) {
		super();
		this.auctionid = auctionid;
		this.buyerid = buyerid;
		this.sellerid = sellerid;
		this.winningbid = winningbid;
		this.auctiondate = auctiondate;
	}

	public int getAuctionid() {
		return auctionid;
	}

	public void setAuctionid(int auctionid) {
		this.auctionid = auctionid;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public float getWinningbid() {
		return winningbid;
	}

	public void setWinningbid(float winningbid) {
		this.winningbid = winningbid;
	}

	public Date getAuctiondate() {
		return auctiondate;
	}

	public void setAuctiondate(Date auctiondate) {
		this.auctiondate = auctiondate;
	}

	@Override
	public String toString() {
		return "AuctionHistory [auctionid=" + auctionid + ", buyerid=" + buyerid + ", sellerid=" + sellerid
				+ ", winningbid=" + winningbid + ", auctiondate=" + auctiondate + "]";
	}
	
	
	

}
