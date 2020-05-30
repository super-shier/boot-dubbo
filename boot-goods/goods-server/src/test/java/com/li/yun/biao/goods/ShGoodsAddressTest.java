package com.li.yun.biao.goods;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.goods.api.ShGoodsService;
import com.li.yun.biao.goods.dto.param.query.ShGoodsAddressQueryPageParam;
import com.li.yun.biao.goods.model.ShGoodsAddress;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/29 10:16 AM
 * @description
 */
public class ShGoodsAddressTest extends BaseUserTest {
    @Resource
    private ShGoodsService shGoodsService;
    ShGoodsAddress address;

    @Before
    public void setup() {
        address = new ShGoodsAddress();
        address.setUid(1000001L);
        address.setProvinceCode(34L);
        address.setProvinceName("安徽省");
        address.setCityCode(3416L);
        address.setCityName("亳州市");
        address.setAreaCode(341623L);
        address.setAreaName("利辛县");
        address.setAddress("安徽省亳州市利辛县");
        address.setConsignee("十二");
        address.setMobile("18686868686");
        address.setCreateTime(new Date());
        address.setModifyTime(new Date());

    }

    @Test
    public void addGoodsAddress() {
        DubboResponse<Long> response = shGoodsService.addGoodsAddress(address);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void updateGoodsAddress() {
        DubboResponse<ShGoodsAddress> response = shGoodsService.selectGoodsAddressById(7L);
        if (!response.getSuccess() || Objects.isNull(response.getResponseObject())) {
            logger.info("********response:{}", JSON.toJSONString(response));
            return;
        }
        address = response.getResponseObject();
        address.setMobile("15557578989");
        address.setModifyTime(new Date());
        DubboResponse<Long> updateResponse = shGoodsService.updateGoodsAddress(address);
        logger.info("********response:{}", JSON.toJSONString(updateResponse));
    }

    @Test
    public void selectGoodsAddressById() {
        DubboResponse<ShGoodsAddress> response = shGoodsService.selectGoodsAddressById(7L);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void selectGoodsAddressByUid() {
        DubboResponse<List<ShGoodsAddress>> response = shGoodsService.selectGoodsAddressByUid(1000000L, 0);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void getGoodsAddressResultByQuery() {
        ShGoodsAddressQueryPageParam queryPageParam = new ShGoodsAddressQueryPageParam();
        queryPageParam.setStatus(1);
        DubboResponse<PageResult<ShGoodsAddress>> response = shGoodsService.getGoodsAddressResultByQuery(queryPageParam);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void updateGoodsAddressDefault() {
        Long uid = 1000000L;
        Long id = 11L;
        DubboResponse<Boolean> response = shGoodsService.updateGoodsAddressNotDefault(uid);
        logger.info("********response:{}", JSON.toJSONString(response));
        DubboResponse<Boolean> defaultResponse = shGoodsService.updateGoodsAddressDefault(id);
        logger.info("********defaultResponse:{}", JSON.toJSONString(defaultResponse));
    }

    @Test
    public void selectDefaultGoodsAddressByUid() {
        Long uid = 1000000L;
        DubboResponse<ShGoodsAddress> response = shGoodsService.selectDefaultGoodsAddressByUid(uid);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void automaticAddGoodsAddress() {
        DubboResponse<Boolean> response = shGoodsService.automaticAddGoodsAddress();
        logger.info("********response:{}", JSON.toJSONString(response));
    }
}
