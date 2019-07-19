package com.hb56.security.model.oceanrail;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Been
 * @date 2018/10/22
 */
@Data
public class User implements Serializable {

    private Long id;

    private String loginname;

    private String name;

    private String password;

    private Integer sex;

    private Integer age;

    private Integer usertype;

    private Integer status;

    private Integer organizationId;

    private String roleName;

    private String systems;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdate;

    private String phone;

    private List<Role> rolesList;

    private String organizationName;

    private String roleIds;
    private Date createdateStart;
    private Date createdateEnd;

}
