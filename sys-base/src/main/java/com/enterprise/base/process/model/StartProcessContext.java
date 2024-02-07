package com.enterprise.base.process.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * @author chenyi
 */
@Data
@Builder
public class StartProcessContext {
    private String processKey;
    private String businessKey;
    private String authenticator;
    private HashMap<String, Object> variables;

}
