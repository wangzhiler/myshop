package com.wzl.myshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzl.myshop.commons.persistence.BaseEntity;
import com.wzl.myshop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by thinkpad on 2018/9/8.
 */

@Data
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "用户名6~20")
    private String username;

    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码6~20")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机格式不对")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;

}
