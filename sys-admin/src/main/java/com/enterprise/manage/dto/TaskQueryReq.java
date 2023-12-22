package com.enterprise.manage.dto;

import lombok.Data;

/**
 * @author chenyi
 */
@Data
public class TaskQueryReq {
    private String processKey;
    // 责任人
    private String taskOwner;
    private String taskName;
    private int pageNum;
    private int pageSize;
}
