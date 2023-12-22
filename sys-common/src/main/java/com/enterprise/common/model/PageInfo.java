package com.enterprise.common.model;

import lombok.Data;

/**
 * @author chenyi
 */
@Data
public class PageInfo {
    private int currentPage = 1;
    private int pageSize = 10;
    private String orderBy;
}
