package com.hankal.detrust.dao;

import com.hankal.detrust.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface WalletDao {

    // 在一个Insert中插入多个Insert语句
    @Transactional
    @Insert("insert into wallet" +
            "(wallet_address,wallet_name,wallet_type_code,wallet_generate_type_code,passphrase,private_key,keystore,create_time) " +
            "values(#{walletAddress},#{walletName},#{walletTypeCode},#{walletGenerateTypeCode},#{passphrase},#{privateKey},#{keystore},#{createTime}) ;" +
            "" +
            "insert into user_wallet (user_id, phone_number, wallet_address) values(#{userId},#{phoneNumber},#{walletAddress}) ;" +
            "" +
            "insert into wallet_asset(wallet_address, token_address, user_id, create_time) " +
            "values(#{walletAddress}, #{tokenAddress}, #{userId}, #{createTime}) ")
    public boolean insertNewWallet(Wallet wallet);

    @Update("update wallet set deleted = 1 where wallet_address = #{walletAddress}")
    public boolean deleteWallet(@Param("walletAddress") String walletAddress);

    @Select("select a.wallet_address, a.wallet_name, sum(c.balance) as balance, a.keystore " +
            "            from wallet a, user_wallet b, wallet_asset c\n" +
            "            where b.phone_number = #{phoneNumber} and a.wallet_address = b.wallet_address and b.wallet_address = c.wallet_address\n" +
            "            and a.deleted = 0\n" +
            "            group by c.wallet_address, c.balance")
    public List<WalletAccount> getAllWalletAccounts(@Param("phoneNumber") String phoneNumber);

    @Select("select b.token_name as tokenName, a.wallet_address as walletAddress, b.token_address as tokenAddress, c.balance \n" +
            "            from wallet a, asset b, wallet_asset c\n" +
            "            where a.wallet_address = c.wallet_address and c.token_address = b.token_address\n" +
            "            and c.user_id=#{userId} and a.wallet_address = #{walletAddress}\n" +
            "            and a.deleted = 0 ")
    public List<WalletAsset> getAllAssetByAddress(@Param("userId") String userId,
                                                  @Param("walletAddress") String walletAddress);

    @Select("select b.wallet_address, a.balance, c.trans_datetime " +
            "from asset a, asset_trans b, trans c, wallet d " +
            "where d.wallet_address == b.wallet_address and a.token_address = b.token_address and b.wallet_address = c.wallet_address " +
            "and b.user_id = #{userId} and a.token_address = #{tokenAddress} " +
            "and a.deleted = 0 ")
    public List<Trans> getAllTransByTokenAddress(@Param("userId") String userId,
                                                 @Param("tokenAddress") String tokenAddress);

    @Select("select token_address,token_name,token_desc from asset")
    public List<Asset> getAllAssets();

    @Select("insert into wallet_asset(wallet_address,token_address,user_id, balance, create_time) " +
            "values(#{walletAddress},#{tokenAddress},#{userId},#{balance},#{createTime})")
    public Object appendAssetToWallet(@Param("walletAddress") String walletAddress,
                                      @Param("tokenAddress") String tokenAddress,
                                      @Param("userId") String userId,
                                      @Param("balance") BigDecimal balance,
                                      @Param("createTime") Date createTime);

    @Select("delete from wallet_asset " +
            "where wallet_address=#{walletAddress} and token_address=#{tokenAddress} and user_id=#{userId}")
    public Object removeAssetToWallet(@Param("walletAddress") String walletAddress,
                                      @Param("tokenAddress") String tokenAddress,
                                      @Param("userId") String userId);
}
