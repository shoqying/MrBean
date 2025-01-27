package com.mrbean.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private userDAO userDAO;

    @Override
    public void createUser(userVO user) {
        System.out.println("Service - User 데이터: " + user); // 데이터 확인용 출력
        userDAO.createUser(user);
    }

    @Override
    public userVO getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<userVO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(userVO user) {
        userDAO.updateUser(user);
    }

    @Override
    public void updatePassword(userVO user) {
        System.out.println("Service - 암호화된 비밀번호 업데이트: " + user.getUUserid());
        userDAO.updatePassword(user); // DAO 호출
    }

    // 추가: userVO를 userDTO로 변환
    public userDTO getUserDTOById(String userId) {
        userVO userVO = userDAO.getUserById(userId);
        if (userVO == null) {
            return null;
        }
        // userVO -> userDTO 변환
        userDTO userDTO = new userDTO();
        userDTO.setUUserid(userVO.getUUserid());
        userDTO.setUUsername(userVO.getUUsername());
        userDTO.setUEmail(userVO.getUEmail());
        userDTO.setUPhonenumber(userVO.getUPhonenumber());
        userDTO.setURoleenum(userVO.getURoleenum().name()); // Enum -> String
        return userDTO;
    }

    // 추가: 전체 사용자 목록을 userDTO 리스트로 반환
    public List<userDTO> getAllUserDTOs() {
        return userDAO.getAllUsers().stream()
                .map(userVO -> {
                    userDTO userDTO = new userDTO();
                    userDTO.setUUserid(userVO.getUUserid());
                    userDTO.setUUsername(userVO.getUUsername());
                    userDTO.setUEmail(userVO.getUEmail());
                    userDTO.setUPhonenumber(userVO.getUPhonenumber());
                    userDTO.setURoleenum(userVO.getURoleenum().name());
                    return userDTO;
                })
                .collect(Collectors.toList());
    }
}
