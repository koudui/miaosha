package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.PromoDO;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    PromoDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo_info
     *
     * @mbggenerated Sun Apr 07 15:39:37 CST 2019
     */
    int updateByPrimaryKey(PromoDO record);
}