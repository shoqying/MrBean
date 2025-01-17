package com.mrbean.productionplan;

import java.time.LocalDate;

import com.mrbean.enums.ProductionplanStatus;

import lombok.Data;

@Data
public class ProductionVO {
	private int planId; // �����ȹ ID
	private String planNumber; // ������� ��ȣ
	private String planType; // �����ȹ ����(��,��,�ݳ��)\
	private LocalDate planStartDate; // ��ȹ ��������
	private LocalDate planEndDate; // ��ȹ ��������
	private String productCode; // ��ǰ�ڵ�
	private String bomCode; // bom�ڵ�
	private int planQuantity; //�����ȹ����
	private int priority; //�켱����
	
	
	private String remark; // ���
	private String createdBy;//����� 
	
	private ProductionplanStatus status; //��ȹ���� �ʿ��Ѱ�? => �۾����ÿ��� �ʿ��ҵ���
	

	
	
	
	

}//ProductionVO


