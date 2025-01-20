package com.mrbean.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class userDAOImpl implements userDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.mappers.userMapper";

    @Override
    public userVO getUserById(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".readUser", userId);
    }

    @Override
    public List<userVO> getAllUsers() {
        return sqlSession.selectList(NAMESPACE + ".readAllUsers");
    }

    @Override
    public void createUser(userVO user) {
        sqlSession.insert(NAMESPACE + ".createUser", user);
    }

    @Override
    public void updateUser(userVO user) {
        sqlSession.update(NAMESPACE + ".updateUser", user);
    }
}
