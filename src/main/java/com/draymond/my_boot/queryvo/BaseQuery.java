package com.draymond.my_boot.queryvo;


import com.draymond.commons.vo.QueryVO;
import lombok.Data;

/**
 * 通用的参数
 *
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@Data
public class BaseQuery extends QueryVO {
    //用户名,管理员名称，老师名称
    private String name;




}
