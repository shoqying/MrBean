package com.mrbean.finishedproductscontrol;

import java.util.List;

public interface FinishedProductsControlDAO {
	
	// 원자재 검사 관리 목록
	public List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception;

}
