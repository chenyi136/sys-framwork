package com.enterprise.process.process.impl;

import com.enterprise.process.process.PurchaseService;
import com.enterprise.process.process.model.PurchaseBusinessAudit;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author chenyi
 */
@Service
public class PurchaseAuditServiceImpl implements PurchaseService {

//    @Override
//    public void addPurchaseAudit(PurchaseAudit purchaseAudit) {
//        // add 业务表数据， 审核流程表数据
//    }

    @Override
    public List<PurchaseBusinessAudit> queryPurchaseAudit(String userId, int auditStatus) {
        // 查询三张表的组装数据
        return null;
    }

    @Override
    public void updateStatus(String userId, int status) {
        // 更新审核流程表、明细表的数据
    }
}
