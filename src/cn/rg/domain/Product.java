package cn.rg.domain;

import java.util.Date;

/**
 * `pid` varchar(32) NOT NULL,
 *   `pname` varchar(50) DEFAULT NULL,
 *   `market_price` double DEFAULT NULL,
 *   `shop_price` double DEFAULT NULL,
 *   `pimage` varchar(200) DEFAULT NULL,
 *   `pdate` date DEFAULT NULL,
 *   `is_hot` int(11) DEFAULT NULL,
 *   `pdesc` varchar(255) DEFAULT NULL,
 *   `pflag` int(11) DEFAULT NULL,
 *   `cid` varchar(32) DEFAULT NULL,
 *
 *
 */
public class Product {
    private String pid;
    private String pname;
    private Double market_price;
    private Double shop_price;
    private String pimage;
    private Date pdate;
    private Integer is_hot;
    private String pdesc;
    private Integer pflag;
    //当表的关系是 1对n时,在建立实体时候,需要在1的那个表建立一个n表的那个属性..
    private Category category;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getShop_price() {
        return shop_price;
    }

    public void setShop_price(Double shop_price) {
        this.shop_price = shop_price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public Integer getPflag() {
        return pflag;
    }

    public void setPflag(Integer pflag) {
        this.pflag = pflag;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", market_price=" + market_price +
                ", shop_price=" + shop_price +
                ", pimage='" + pimage + '\'' +
                ", pdate=" + pdate +
                ", is_hot=" + is_hot +
                ", pdesc='" + pdesc + '\'' +
                ", pflag=" + pflag +
                ", category=" + category +
                '}';
    }
}
