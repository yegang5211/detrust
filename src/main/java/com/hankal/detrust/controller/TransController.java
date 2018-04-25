package com.hankal.detrust.controller;

import com.hankal.detrust.domain.Trans;
import com.hankal.detrust.domain.TransInfo;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.WalletService;
import com.hankal.detrust.vo.QueryRequestVo;
import com.hankal.detrust.vo.TransferVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Api("交易管理，包括转账、智能合约等")
public class TransController extends BaseController {
    @Autowired
    WalletService walletService;

    /*
      查看指定资产下的所有交易明细
     */
    @RequestMapping(value = "/wallet/asset/{tokenAddress}/trans", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取指定资产下的所有交易列表", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<Trans>> listAllTransByTokenAddress(
            @PathVariable("tokenAddress") String tokenAddress,
            @Valid QueryRequestVo requestVo) {
        List<Trans> list = walletService.getAllTransByUserId(requestVo.getUserId(), tokenAddress);

        return Result.success(list);
    }

    @RequestMapping(value = "/wallet/trans/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "【已实现】转账", httpMethod = "POST", notes = "暂无")
    @ResponseBody
    public Result<Boolean> transfer(@RequestBody @Valid TransferVo transferVo) {
        boolean result = walletService.transfer(transferVo);
        return Result.success(result);
    }

    /*
      查看指定资产下的所有交易明细
     */
    @RequestMapping(value = "/wallet/trans/{transHash}/transInfo", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取指定交易号的交易明细", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<TransInfo>> getTransInfo(@PathVariable("transHash") String transHash, @Valid QueryRequestVo requestVo) {
        List<TransInfo> list = walletService.getTransInfoBytransHash(transHash);
        return Result.success(list);
    }



}
