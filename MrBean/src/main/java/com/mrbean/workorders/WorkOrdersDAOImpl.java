package com.mrbean.workorders;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class WorkOrdersDAOImpl implements WorkOrdersDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(WorkOrdersDAOImpl.class);
    
    @Inject
    private SqlSession sqs;
    
    private static final String NAMESPACE = "com.mrbean.mappers.workOrdersMapper.";
    
    @Override
    public String createWorkOrderNumber(String date) {
        return sqs.selectOne(NAMESPACE + "getLastNumberForDate", date);
    }
    
    @Override
    public void insertWorkOrders(WorkOrdersVO workVO) {
        sqs.insert(NAMESPACE + "insertWO", workVO);
    }
    
    @Override
    public List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO) {
        return sqs.selectList(NAMESPACE + "selectWO", workVO);
    }
    
    @Override
    public void deleteWorkOrders(int workId) {
        sqs.delete(NAMESPACE + "deleteWO", workId);
    }
    
    @Override
    public void updateWorkStatus(WorkOrdersVO workVO) {
        sqs.update(NAMESPACE + "updateWorkStatus", workVO);
    }
    
    @Override
    public List<WorkOrdersVO> findByPlanId(int planId) {
        return sqs.selectList(NAMESPACE + "findByPlanId", planId);
    }
    
    @Override
    public Integer getPlanIdByWorkId(int workId) {
        return sqs.selectOne(NAMESPACE + "getPlanIdByWorkId", workId);
    }
    
    @Override
    public String getBomIdByPName(String pName) {
        return sqs.selectOne(NAMESPACE + "getBomIdByPName", pName);
    }
    
    @Override
    public Map<String, Object> getBomInfoById(String bomId) {
        return sqs.selectOne(NAMESPACE + "getBomInfoById", bomId);
    }
    
    @Override
    public String getRmCodeFromWorkOrder(int workId) {
        try {
            return sqs.selectOne(NAMESPACE + "getRmCodeFromWorkOrder", workId);
        } catch (Exception e) {
            logger.error("Error getting rm_code for workId: " + workId, e);
            throw e;
        }
    }
    
    @Override
    public List<Map<String, Object>> getStockMaterialsByRmCode(String rmCode) {
        try {
            return sqs.selectList(NAMESPACE + "getStockMaterialsByRmCode", rmCode);
        } catch (Exception e) {
            logger.error("Error getting stock materials for rm_code: " + rmCode, e);
            throw e;
        }
    }
    
    @Override
    public void updateStockMaterials(Map<String, Object> params) {
        try {
            int updatedRows = sqs.update(NAMESPACE + "updateStockMaterials", params);
            if (updatedRows == 0) {
                logger.warn("No rows updated for stock materials with params: " + params);
            }
        } catch (Exception e) {
            logger.error("Error updating stock materials with params: " + params, e);
            throw e;
        }
    }
    
    @Override
    public void insertLotHistory(Map<String, Object> params) {
        try {
            sqs.insert(NAMESPACE + "insertLotHistory", params);
        } catch (Exception e) {
            logger.error("Error inserting lot history with params: " + params, e);
            throw e;
        }
    }
}