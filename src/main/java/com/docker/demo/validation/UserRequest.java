package com.docker.demo.validation;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 入参对象
 *
 * @author DUCHONG
 * @since 2020-08-24 23:33
 **/
@Data
@Builder
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -2655536314774756670L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Min(1)
    private Integer age;

    /**
     * 企业类型名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 昵称
     */
    private String nickName;

}
