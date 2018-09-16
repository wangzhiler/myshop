package com.wzl.myshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wzl.myshop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Data
public class TbContentCategory extends BaseEntity {

    private Long id;
    private Long parentId;

//    @Length(min=1, max=20, message = "1-20")
    private String name;
    private Integer status;

//    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

//    @JsonProperty(value = "isParent")
    private Boolean isParent;

    private TbContentCategory parent;


}
