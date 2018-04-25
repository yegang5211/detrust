package com.hankal.detrust.controller;

import com.hankal.detrust.domain.Wallet;
import com.hankal.detrust.domain.WalletAccount;
import com.hankal.detrust.result.Result;
import com.hankal.detrust.service.WalletService;
import com.hankal.detrust.service.walletLogic.WalletBuildType;
import com.hankal.detrust.service.walletLogic.WalletBuilder;
import com.hankal.detrust.vo.QueryRequestVo;
import com.hankal.detrust.vo.WalletDelVo;
import com.hankal.detrust.vo.WalletVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Api("钱包账号管理，包括账户创建、账户删除、导入、导出、密文修改等")
public class WalletController extends BaseController {
    @Autowired
    WalletService walletService;

    /*
        创建钱包账户
     */
    @PostMapping(value = "/wallet", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【已实现】客户端成功创建一个钱包账户后调用服务接口，包括创建钱包，核查用户有效性，关联用户，默认分配ETH资产",
            httpMethod = "POST", notes = "客户端必须提供手机号码、验证码、登录密码等信息")
    @ResponseBody
    public Result<Boolean> createWallet(@RequestBody @Valid WalletVo walletVo) {
        Wallet wallet = WalletBuilder.Build(walletVo, WalletBuildType.CREATE);
        Boolean result = walletService.createNewWallet(wallet);
        return Result.success(result);
    }

    /*
    删除钱包账户
     */
    @DeleteMapping(value = "/wallet", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【已实现】删除钱包，更新钱包数据的删除标志",
            httpMethod = "DELETE", notes = "客户端提供walletAddress")
    @ResponseBody
    public Result<Boolean> deleteWallet(@RequestBody @Valid WalletDelVo walletDelVo) {
        return walletService.deleteWallet(walletDelVo.getWalletAddress());
    }

    /*
    修改钱包账户的密码
     */
    @PostMapping(value = "/wallet/passphrase", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【不实现】修改钱包密码", httpMethod = "POST", notes = "")
    @ResponseBody
    public Result<String> changeAccountPassphrase() {
        return Result.success("Hello，world");
    }

    /*
        修改钱包名称
     */
    @PostMapping(value = "/wallet/walletName", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【不实现】修改钱包名称", httpMethod = "POST", notes = "")
    @ResponseBody
    public Result<String> changeWalletName() {
        return Result.success("Hello，world");
    }

    /*
    导出keystore
     */
    @PostMapping(value = "/wallet/{address}/keystore", produces = {"application/json; charset=utf-8"})
    @ApiOperation(value = "【不实现】导出KeyStore", httpMethod = "POST", notes = "")
    @ResponseBody
    public Result<String> exportKeystore(@PathVariable("address") String address) {
        return Result.success("Hello，world");
    }

    /*
        查看指定手机号查看其名下的所有钱包账户列表
    */
    @RequestMapping(value = "/wallet/{mobilePhone}/address", method = RequestMethod.GET)
    @ApiOperation(value = "【已实现】获取指定手机名下的钱包账户列表，手机端在切换钱包账户时使用", httpMethod = "GET", notes = "暂无")
    @ResponseBody
    public Result<List<WalletAccount>> listAllWalletAccount(QueryRequestVo requestVo) {
        List<WalletAccount> list = walletService.getAllWalletAccounts(requestVo.getMobilePhone());
        return Result.success(list);
    }
}
