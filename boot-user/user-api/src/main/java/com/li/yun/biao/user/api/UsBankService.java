package com.li.yun.biao.user.api;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.dto.param.query.ShBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShBankLianHangHaoQueryPageParam;
import com.li.yun.biao.user.model.ShBankCard;
import com.li.yun.biao.user.model.ShBankLianhanghao;

/**
 * 银行接口
 */
public interface UsBankService {
    /**
     * 银行信息
     */
    DubboResponse<Long> addBankCard(ShBankCard bankCard);

    DubboResponse<Long> updateBankCard(ShBankCard bankCard);

    DubboResponse<Void> deleteBankCard(Long id);

    DubboResponse<ShBankCard> getBankCardById(Long id);

    DubboResponse<ShBankCard> getBankCardByBin(String bin);

    DubboResponse<ShBankCard> getBankCardByCardNo(String cardNo);

    DubboResponse<PageResult<ShBankCard>> getBankCardResult(ShBankCardQueryPageParam queryPageParam);

    /**
     * 联行号信息
     */
    DubboResponse<Long> addBankLianhanghao(ShBankLianhanghao bankLianhanghao);

    DubboResponse<Long> updateBankLianhanghao(ShBankLianhanghao bankLianhanghao);

    DubboResponse<Void> deleteBankLianhanghao(Long id);

    DubboResponse<ShBankLianhanghao> getBankLianhanghaoById(Long id);

    DubboResponse<ShBankLianhanghao> getLianhanghaoByBankNumber(String bankNumber);

    DubboResponse<ShBankLianhanghao> getLianhanghaoByBankName(String bankName);

    DubboResponse<PageResult<ShBankLianhanghao>> getBankLianhanghaoResult(ShBankLianHangHaoQueryPageParam queryPageParam);

}
