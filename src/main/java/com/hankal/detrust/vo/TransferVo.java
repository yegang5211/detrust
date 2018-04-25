package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "转账操作", description = "普通账户或智能账户的转账")
public class TransferVo extends RequestVo {
    /*
        web3.eth.getTransaction  返回匹配指定交易哈希值的交易。

     */

    @ApiModelProperty(required = true, value = "交易的哈希值", name = "transHash", example = "必填")
    @NotNull
    private String transHash;

    @ApiModelProperty(required = true, value = "交易所在的区块哈希", name = "blockHash", example = "必填")
    @NotNull
    private String blockHash;

    @ApiModelProperty(required = true, value = "交易所在的区块号", name = "blockNumber", example = "必填")
    @NotNull
    private int blockNumber;

    @ApiModelProperty(required = true, value = "交易类型，0-普通交易,1-合约交易", name = "transTypeCode", example = "必填")
    @NotNull
    private int transTypeCode = 0;//0-普通交易,1-合约交易

    @ApiModelProperty(required = true, value = "钱包账户地址", name = "walletAddress", example = "必填")
    @NotNull
    private String walletAddress;

    @ApiModelProperty(required = true, value = "合约地址，从钱包账户的资产列表中可以获取到", name = "tokenAddress", example = "必填")
    @NotNull
    private String tokenAddress;

    @ApiModelProperty(required = true, value = "20字节，交易发起者的地址", name = "fromAddress", example = "必填")
    @NotNull
    private String fromAddress;

    @ApiModelProperty(required = true, value = "20字节，交易接收者的地址", name = "toAddress", example = "必填")
    @NotNull
    private String toAddress;

    @ApiModelProperty(required = true, value = "BigNumber 交易附带的货币量(例如矿工费)，单位为Wei", name = "value", example = "必填")
    @NotNull
    private BigDecimal value;

    @ApiModelProperty(required = true, value = "交易的发起者在之前进行过的交易数量", name = "nonce", example = "必填")
    @NotNull
    private int nonce;

    @ApiModelProperty(required = false, value = "交易的备注信息", name = "remark", example = "必填")
    private String remark;

    @ApiModelProperty(required = true, value = "BigNumber 交易发起者提供的gas", name = "gas", example = "必填")
    @NotNull
    private BigDecimal gas;

    @ApiModelProperty(required = true, value = "BigNumber 交易发起者配置的gas价格，单位是wei", name = "gasPrice", example = "必填")
    @NotNull
    private BigDecimal gasPrice;

    @ApiModelProperty(required = false, value = "交易附带的数据 (智能合约交易，就是代码的字节码Code)", name = "data", example = "选填")
    private String data;

    @ApiModelProperty(required = true, value = "交易日期", name = "transDateTime", example = "必填")
    @NotNull
    private Date transDateTime;


    /*
        web3.eth.getTransactionReceipt  通过一个交易哈希，返回一个交易的收据：

     */

    @ApiModelProperty(required = true, value = "BigNumber - 执行当前这个交易单独花费的gas", name = "gasUsed", example = "必填")
    @NotNull
    private BigDecimal gasUsed;

    @ApiModelProperty(required = true, value = "BigNumber - 当前交易执行后累计花费的gas总值", name = "comulativeGasUsed", example = "必填")
    @NotNull
    private BigDecimal comulativeGasUsed;

    @ApiModelProperty(required = false, value = "20字节，创建的合约地址。如果是一个合约创建交易，返回合约地址，其它情况返回null", name = "contractAddress", example = "必填")
    @NotNull
    private String contractAddress;

    /*
        web3.eth.getTransactionReceipt  通过一个交易哈希，返回一个交易的收据：

     */
    @ApiModelProperty(required = true, value = "交易的发起者在之前进行过的交易数量", name = "blockNonce", example = "必填")
    @NotNull
    private String blockNonce;

    @ApiModelProperty(required = true, value = "BigNumber - 当前区块累计使用的总的gas", name = "blockGasUsed", example = "必填")
    @NotNull
    private BigDecimal blockGasUsed;

    @ApiModelProperty(required = true, value = "BigNumber - 当前区块允许使用的最大gas", name = "gasLimit", example = "必填")
    @NotNull
    private BigDecimal gasLimit;


    /*
        通用的创建时间时间
     */
    @ApiModelProperty(required = false, hidden = true, value = "数据创建的时间，如果不提供，按服务器时间记录", name = "contractAddress", example = "")
    private Date createTime;


    public String getTransHash() {
        return transHash;
    }

    public void setTransHash(String transHash) {
        this.transHash = transHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getTransTypeCode() {
        return transTypeCode;
    }

    public void setTransTypeCode(int transTypeCode) {
        this.transTypeCode = transTypeCode;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getGas() {
        return gas;
    }

    public void setGas(BigDecimal gas) {
        this.gas = gas;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(Date transDateTime) {
        this.transDateTime = transDateTime;
    }

    public BigDecimal getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigDecimal gasUsed) {
        this.gasUsed = gasUsed;
    }

    public BigDecimal getComulativeGasUsed() {
        return comulativeGasUsed;
    }

    public void setComulativeGasUsed(BigDecimal comulativeGasUsed) {
        this.comulativeGasUsed = comulativeGasUsed;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigDecimal gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getBlockNonce() {
        return blockNonce;
    }

    public void setBlockNonce(String blockNonce) {
        this.blockNonce = blockNonce;
    }

    public BigDecimal getBlockGasUsed() {
        return blockGasUsed;
    }

    public void setBlockGasUsed(BigDecimal blockGasUsed) {
        this.blockGasUsed = blockGasUsed;
    }
}
