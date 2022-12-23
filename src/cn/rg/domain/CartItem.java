package cn.rg.domain;

/**
 * 购物项
 */
public class CartItem {
    //商品
    private Product product;

    //小计
    private Double subtotal;

    //数量
    private Integer count;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSubtotal() {
        return this.product.getShop_price()*this.count;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = this.count*this.product.getShop_price();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CartItem() {
    }

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }
}
