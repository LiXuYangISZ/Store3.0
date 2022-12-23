package cn.rg.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *  购物订单
 */
public class Cart {
    private Map <String,CartItem> itemMap = new HashMap<String, CartItem>();//购物项
    private Double total = 0.0;//总金额
    //加入购物车  实际上就是修改cartitem

    public Map <String, CartItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map <String, CartItem> itemMap) {
        this.itemMap = itemMap;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void addToCart(CartItem item){
        //获取商品的pid
        String pid = item.getProduct().getPid();
        //判断购物车中是否有
        if(itemMap.containsKey(pid)){
            // 有 修改数量 == 原来的数量 + 新加的数量
            CartItem preItem = itemMap.get(pid);
            preItem.setCount(preItem.getCount()+item.getCount());

        }else{
            //无
            itemMap.put(pid,item);
        }
        //修改总金额
        System.out.println("total:"+total);
        System.out.println("item:"+item.getSubtotal());
        total += item.getSubtotal();
        System.out.println("hahhaaa");
    }

    /**
     * 从购物车中移除一个购物项
     * @param pid
     */
    public void removeFromCart(String pid){
        //根据pid移除购物项
        CartItem item = itemMap.remove(pid);
        //修改总金额.
        total -= item.getSubtotal();
    }

    /**
     * 清空购物车
     */
    public void clearCart(){
        itemMap.clear();
        total = 0.0;
    }
    /**
     * 获取所有的购物项
     */
    public Collection<CartItem> getCartItems(){
        return itemMap.values();
    }

}
