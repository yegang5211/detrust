package com.hankal.detrust.controller;

import com.hankal.detrust.domain.Asset;
import com.hankal.detrust.domain.WalletAsset;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.WalletService;
import com.hankal.detrust.vo.QueryRequestVo;
import com.hankal.detrust.vo.RequestVo;
import com.hankal.detrust.vo.WalletAssetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Controller
@Api("资产管理，包括查询、分配、移除资产，查看交易记录等")
public class AssetController extends BaseController {
    @Autowired
    WalletService walletService;

    /*
      获取所有的资产列表
     */
    @RequestMapping(value = "/wallet/assets", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取所有的资产列表，用于客户为自己的钱包账号添加或移除TOKEN", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<Asset>> getAllAssets(@Valid RequestVo requestVo) {
        List<Asset> list = walletService.getAllAssets();
        return Result.success(list);
    }

    @RequestMapping(value = "/wallet/asset", method = RequestMethod.POST)
    @ApiOperation(value = "【已实现】为钱包账户添加资产（TOKEN）", httpMethod = "POST", notes = "暂无")
    @ResponseBody
    public Result<Boolean> appendAssetToWallet(@NotNull @RequestBody WalletAssetVo walletAssetVo) {
        boolean reuslt = walletService.appendAssetToWallet(
                walletAssetVo.getWalletAddress(), walletAssetVo.getTokenAddress(),
                walletAssetVo.getUserId(), new BigDecimal(0));

        return Result.success(true);
    }

    @RequestMapping(value = "/wallet/asset", method = RequestMethod.DELETE)
    @ApiOperation(value = "【已实现】将资产（TOKEN）从钱包账户移除", httpMethod = "DELETE", notes = "暂无")
    @ResponseBody
    public Result<Boolean> removeAssetToWallet(@RequestBody @Valid WalletAssetVo walletAssetVo) {
        boolean result = walletService.removeAssetToWallet(
                walletAssetVo.getWalletAddress(), walletAssetVo.getTokenAddress(), walletAssetVo.getUserId());
        return Result.success(result);
    }

    /*
      查看指定钱包账户下的所有资产列表
     */
    @RequestMapping(value = "/wallet/{walletAddress}/assets", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取指定钱包账户名下的所有资产列表", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<WalletAsset>> listAllAssetByWalletAddress(
            @NotNull @PathVariable("walletAddress") String walletAddress, @Valid QueryRequestVo requestVo) {
        List<WalletAsset> list = walletService.getAllAssetByPhoneAndWallet(requestVo.getUserId(), walletAddress);
        return Result.success(list);
    }
}
