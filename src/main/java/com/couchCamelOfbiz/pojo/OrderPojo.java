package com.couchCamelOfbiz.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "_id",
    "_rev",
    "adjustmentList",
    "grandTotal",
    "orderId",
    "remainingAmount",
    "deposit",
    "itemList",
    "tax",
    "totalQuantity",
    "entryDate",
    "paymentList",
    "customerId",
    "customerName"
})
public class OrderPojo {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String rev;
    @JsonProperty("adjustmentList")
    private List<AdjustmentList> adjustmentList = null;
    @JsonProperty("grandTotal")
    private Integer grandTotal;
    @JsonProperty("orderId")
    private Object orderId;
    @JsonProperty("remainingAmount")
    private Integer remainingAmount;
    @JsonProperty("deposit")
    private Integer deposit;
    @JsonProperty("itemList")
    private List<ItemList> itemList = null;
    @JsonProperty("tax")
    private Integer tax;
    @JsonProperty("totalQuantity")
    private Integer totalQuantity;
    @JsonProperty("entryDate")
    private String entryDate;
    @JsonProperty("paymentList")
    private List<PaymentList> paymentList = null;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("customerName")
    private String customerName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("_rev")
    public String getRev() {
        return rev;
    }

    @JsonProperty("_rev")
    public void setRev(String rev) {
        this.rev = rev;
    }

    @JsonProperty("adjustmentList")
    public List<AdjustmentList> getAdjustmentList() {
        return adjustmentList;
    }

    @JsonProperty("adjustmentList")
    public void setAdjustmentList(List<AdjustmentList> adjustmentList) {
    this.adjustmentList = adjustmentList;
    }

    @JsonProperty("grandTotal")
    public Integer getGrandTotal() {
        return grandTotal;
    }

    @JsonProperty("grandTotal")
    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    @JsonProperty("orderId")
    public Object getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("remainingAmount")
    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    @JsonProperty("remainingAmount")
    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    @JsonProperty("deposit")
    public Integer getDeposit() {
        return deposit;
    }

    @JsonProperty("deposit")
    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    @JsonProperty("itemList")
    public List<ItemList> getItemList() {
        return itemList;
    }

    @JsonProperty("itemList")
    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    @JsonProperty("tax")
    public Integer getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(Integer tax) {
        this.tax = tax;
    }

    @JsonProperty("totalQuantity")
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    @JsonProperty("totalQuantity")
    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @JsonProperty("entryDate")
    public String getEntryDate() {
        return entryDate;
    }

    @JsonProperty("entryDate")
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @JsonProperty("paymentList")
    public List<PaymentList> getPaymentList() {
        return paymentList;
    }	

    @JsonProperty("paymentList")
    public void setPaymentList(List<PaymentList> paymentList) {
        this.paymentList = paymentList;
    }

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("customerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}