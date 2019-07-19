package com.hb56.security.model.oceanrail;

import lombok.Data;

/**
 *
 * @author Been
 * @date 2018/10/22
 */
@Data
public class Role {

    private Long id;

    private String name;

    private Integer seq;

    private String description;

    private Integer status;
}
