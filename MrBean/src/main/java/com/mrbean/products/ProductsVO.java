package com.mrbean.products;

import lombok.Data;

@Data
public class ProductsVO {
	
	private String pCode; // ����ǰ�ڵ�
	private String p_name; // ����ǰ��
	private String p_description; // ����
	private int bom_id; // BOM_ID

}