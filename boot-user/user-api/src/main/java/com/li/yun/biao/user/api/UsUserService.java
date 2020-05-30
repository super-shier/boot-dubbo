package com.li.yun.biao.user.api;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.dto.param.query.ShUserBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserInfoQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserLoginRecordQueryPageParam;
import com.li.yun.biao.user.model.ShUserBankCard;
import com.li.yun.biao.user.model.ShUserInfo;
import com.li.yun.biao.user.model.ShUserLoginRecord;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户接口
 */
@Validated
public interface UsUserService {
    /**
     * 用户基础信息
     */

    DubboResponse<Boolean> automaticAddUserInfo();

    DubboResponse<Long> addUserInfo(ShUserInfo userInfo);

    DubboResponse<Long> updateUserInfo(ShUserInfo shUserInfo);

    DubboResponse<Void> deleteUserInfo(Long uid);

    DubboResponse<ShUserInfo> getUserInfoByUid(Long uid);

    DubboResponse<ShUserInfo> getUserInfoByMobile(String mobile, String passWord);

    DubboResponse<PageResult<ShUserInfo>> queryUserInfoPageResult(@Valid ShUserInfoQueryPageParam userInfoQueryPageParam);

    /**
     * 用户登陆记录
     */

    DubboResponse<Boolean> automaticAddUserLoginRecord();

    DubboResponse<Long> addUserLoginRecord(ShUserLoginRecord loginRecord);

    DubboResponse<Long> updateUserLoginRecord(ShUserLoginRecord loginRecord);

    DubboResponse<Void> deleteUserLoginRecord(Long id);

    DubboResponse<ShUserLoginRecord> getUserFirstLoginRecord(Long uid, Boolean isLast);

    DubboResponse<PageResult<ShUserLoginRecord>> getLoginRecordResultByQuery(ShUserLoginRecordQueryPageParam userLoginRecordQueryPageParam);

    /**
     * 用户银行卡信息
     */
    DubboResponse<Boolean> automaticAddUserBankCard();

    DubboResponse<Long> addUserBankCard(ShUserBankCard userBankCard);

    DubboResponse<Long> updateUserBankCard(ShUserBankCard userBankCard);

    DubboResponse<Void> deleteUserBankCard(Long id);

    DubboResponse<List<ShUserBankCard>> getUserBankCardUid(Long uid);

    DubboResponse<PageResult<ShUserBankCard>> getUserBankCardResultByQuery(ShUserBankCardQueryPageParam queryPageParam);
}
