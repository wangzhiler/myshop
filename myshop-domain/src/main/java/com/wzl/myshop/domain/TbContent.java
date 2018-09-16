package com.wzl.myshop.domain;

import com.wzl.myshop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Data
public class TbContent extends BaseEntity {

    @NotNull(message = "父级目录不能为空")
    private Long categoryId;

    @Length(min=6, max=20, message="标题长度1~20")
    private String title;

    @Length(min=6, max=20, message="子标题长度1~20")
    private String subTitle;

    @Length(min=6, max=50, message="标题描述长度1~50")
    private String titleDesc;


    private String url;
    private String pic;
    private String pic2;

    @Length(min=1, message = "内容不能为空")
    private String content;

    private TbContentCategory tbContentCategory;

}
