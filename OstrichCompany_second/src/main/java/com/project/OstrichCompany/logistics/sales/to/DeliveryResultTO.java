package com.project.OstrichCompany.logistics.sales.to;

import com.project.OstrichCompany.base.to.BaseTO;

public class DeliveryResultTO extends BaseTO {
	private String deliveryNo;
	private String contractDetailNo;
	private String warehouseCode;
	private String itemCode;
	private String itemName;
	private String unitOfDeliveryResult;
	private String deliveryDate;
	private int deliveryAmount;
	private String description;

	public String getContractDetailNo() {
		return contractDetailNo;
	}

	public void setContractDetailNo(String contractDetailNo) {
		this.contractDetailNo = contractDetailNo;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(int deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUnitOfDeliveryResult() {
		return unitOfDeliveryResult;
	}

	public void setUnitOfDeliveryResult(String unitOfDeliveryResult) {
		this.unitOfDeliveryResult = unitOfDeliveryResult;
	}
}