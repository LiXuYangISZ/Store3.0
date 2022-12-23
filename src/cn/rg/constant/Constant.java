package cn.rg.constant;

/**
 * 项目中使用到的一些常量,为了便于后期维护,我们将其定义成变量,这样到后期如果需要修改则只需要修改改文件即可..
 */
public interface Constant {
	/**
	 * 用户未激活
	 */
	int USER_IS_NOT_ACTIVE = 0;
	
	
	/**
	 * 用户已激活
	 */
	int USER_IS_ACTIVE = 1;
	/**
	 * 记住用户名
	 *
	 */
	String SAVE_NAME = "ok";
	/**
	 * redis的存储分类类表的key
	 */
	String STORE_CATEGORY_LIST = "STORE_CATEGORY_LIST";
	/**
	 * redis的服务器地址
	 */
	String REDIS_HOST ="127.0.0.1";
	/**
	 * redis的服务器端口
	 */
	int REDIS_PORT = 6379;

	/**
	 * 热门商品
	 *
	 */
	int  PRODUCT_IS_HOT = 1;
	/**
	 * 商品未下架
	 */
	int PRODUCT_IS_UP = 0;

	/**
	 * 商品已下架
	 */
	int PRODUCT_IS_DOWN = 1;
	/**
	 * 订单未付款
	 */
	int ORDER_WEIFUKUAN = 0;
	/**
	 * 订单已付款
	 */
	int ORDER_YIFUKUAN = 1;
	/**
	 * 订单已发货
	 */
	int ORDER_YIFAHUO = 2;
	/**
	 * 订单已完成
	 */
	int ORDER_YIWANCHENG = 3;
	
	

}
