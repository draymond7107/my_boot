package com.draymond.my_boot.queryvo;


import com.draymond.commons.vo.QueryVO;
import lombok.Data;

/**
 * @author ZhangSuchao
 * @create 2019/4/2
 * @since 1.0.0
 */
@Data
public class UserQuery extends QueryVO {
    //用户id
    private Integer id;
    //用户名
    private String userName;
    //老师名称
    private String teacherName;


}
