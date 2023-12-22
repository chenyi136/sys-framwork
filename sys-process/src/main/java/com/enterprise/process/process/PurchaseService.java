package com.enterprise.process.process;

import com.enterprise.process.process.model.PurchaseBusinessAudit;
import java.util.List;

/**
 * @author chenyi
 */
public interface PurchaseService {
//    void addPurchaseAudit(PurchaseAudit purchaseAudit);


    List<PurchaseBusinessAudit> queryPurchaseAudit(String userId, int auditStatus);


    void updateStatus(String userId, int status);
}
