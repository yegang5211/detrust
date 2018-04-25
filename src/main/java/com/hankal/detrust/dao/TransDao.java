package com.hankal.detrust.dao;

import com.hankal.detrust.domain.TransInfo;
import com.hankal.detrust.vo.TransferVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface TransDao {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Insert(
            "insert into trans_receipt (trans_hash, block_hash, block_number, from_address, to_address, cumulative_gas_used, gas_used, contract_address, create_time) " +
                    " values(#{transHash},#{blockHash},#{blockNumber},#{fromAddress},#{toAddress},#{comulativeGasUsed},#{gasUsed},#{contractAddress},#{createTime}); " +
                    "" +
                    "insert into trans (trans_hash, block_hash, block_number, trans_type_code, wallet_address, token_address, from_address, to_address, value, nonce, remark, gas, gas_price, data, trans_datetime, create_time) " +
                    " values(#{transHash},#{blockHash},#{blockNumber},#{transTypeCode},#{walletAddress},#{tokenAddress},#{fromAddress},#{toAddress},#{value},#{nonce},#{remark},#{gas},#{gasPrice},#{data},#{transDateTime},#{createTime}); " +
                    "" +
                    "insert into block (block_hash, block_number, block_nonce, gas_limit, block_gas_used, create_time ) " +
                    " values(#{blockHash},#{blockNumber},#{blockNonce},#{gasLimit},#{blockGasUsed},#{createTime}); " +
                    "" +
                    "insert into asset_trans (trans_hash, wallet_address, token_address, user_id) " +
                    " values (#{transHash},#{walletAddress},#{tokenAddress},#{userId});  ")
    public boolean transfer(TransferVo transferVo);

    @Select("select a.from_address as fromAddress, a.to_address as toAddress, a.gas, gas_price as gasPrice, a.remark, b.block_number as blockNumber, a.trans_datetime as transDateTime " +
            "from trans a, block b where a.block_hash = b.block_hash and a.trans_hash=#{tranHash} ")
    public List<TransInfo> getByTranHash(@Param("tranHash") String tranHash);
}
