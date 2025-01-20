package com.mrbean.finishedproductscontrol;

import java.util.List;

public interface FinishedProductsControlService {

	// 완제품 검사 관리 목록
	public List<FinishedProductsControlVO> getFinishedProductsControlList() throws Exception;

}
