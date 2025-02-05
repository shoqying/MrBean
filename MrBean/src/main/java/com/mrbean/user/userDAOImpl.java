package com.mrbean.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrbean.productionplan.ProductionPlanVO;

@Repository
public class userDAOImpl implements userDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.user.userMapper";

    @Override
    public userVO getUserById(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".getUserById", userId);
    }

    @Override
    public List<userVO> getAllUsers() {
        return sqlSession.selectList(NAMESPACE + ".getAllUsers");
    }

    @Override
    public List<userVO> getUsersByRole(String role) {
        return sqlSession.selectList(NAMESPACE + ".getUsersByRole", role);
    }
    
    @Override
    public void createUser(userVO user) {
        sqlSession.insert(NAMESPACE + ".createUser", user);
    }

    @Override
    public void updateUser(userVO user) {
        sqlSession.update(NAMESPACE + ".updateUser", user);
    }
    
    @Override
    public void updatePassword(userVO user) {
        System.out.println("DAO - 암호화된 비밀번호 업데이트: " + user.getUUserid());
        sqlSession.update(NAMESPACE + ".updateUserPassword", user);
    }
    
    @Override
    public int countUsersByRole(String role) {
        System.out.println("DAO - Role Parameter: " + role);
        int count = sqlSession.selectOne(NAMESPACE + ".countUsersByRole", role);
        System.out.println("DAO - Result Count: " + count);
        return count;
    }
    
    @Override
    public List<ProductionPlanVO> getUserProductionPlans(String createdBy) {
        return sqlSession.selectList(NAMESPACE + ".getUserProductionPlans", createdBy);
      
    }
}

