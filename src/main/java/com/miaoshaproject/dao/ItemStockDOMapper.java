package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemStockDO;

public interface ItemStockDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    int insert(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    int insertSelective(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    ItemStockDO selectByPrimaryKey(Integer id);
    ItemStockDO selectByItemId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    int updateByPrimaryKeySelective(ItemStockDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbggenerated Fri Apr 05 22:23:15 CST 2019
     */
    int updateByPrimaryKey(ItemStockDO record);
}