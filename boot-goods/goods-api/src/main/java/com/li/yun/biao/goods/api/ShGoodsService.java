package com.li.yun.biao.goods.api;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.common.model.Query;
import com.li.yun.biao.goods.dto.param.query.*;
import com.li.yun.biao.goods.model.*;

import java.util.List;

public interface ShGoodsService {
    DubboResponse<Boolean> automaticAddGoods();
    DubboResponse<Long> addGoods(ShGoods goods);
    DubboResponse<Long> updateGoods(ShGoods goods);
    DubboResponse<ShGoods> selectGoodsById(Long id);
    DubboResponse<PageResult<ShGoods>> getGoodsResultByQuery(ShGoodsQueryPageParam queryPageParam);

    DubboResponse<Boolean> automaticAddGoodsCategory();
    DubboResponse<Long> addGoodsCategory(ShGoodsCategory goodsCategory);
    DubboResponse<Long> updateGoodsCategory(ShGoodsCategory goodsCategory);
    DubboResponse<ShGoodsCategory> selectGoodsCategoryById(Long id);
    DubboResponse<PageResult<ShGoodsCategory>> getGoodsCategoryResultByQuery(ShGoodsCategoryQueryPageParam queryPageParam);

    DubboResponse<Boolean> automaticAddGoodsDetail();
    DubboResponse<Long> addGoodsDetail(ShGoodsDetail goodsDetail);
    DubboResponse<Long> updateGoodsDetail(ShGoodsDetail goodsDetail);
    DubboResponse<ShGoodsDetail> selectGoodsDetailById(Long id);
    DubboResponse<List<ShGoodsDetail>> getGoodsDetailList(Long goodsId);
    DubboResponse<PageResult<ShGoodsDetail>> getGoodsDetailResultByQuery(ShGoodsDetailQueryPageParam queryPageParam);

    DubboResponse<Boolean> automaticAddGoodsPhotoWall();
    DubboResponse<Long> addGoodsPhotoWall(ShGoodsPhotoWall goodsPhotoWall);
    DubboResponse<Long> updateGoodsPhotoWall(ShGoodsPhotoWall goodsPhotoWall);
    DubboResponse<ShGoodsPhotoWall> selectGoodsPhotoWallById(Long id);
    DubboResponse<PageResult<ShGoodsPhotoWall>> getGoodsPhotoWallResultByQuery(ShGoodsPhotoWallQueryPageParam queryPageParam);

    DubboResponse<Boolean> automaticAddGoodsAddress();
    DubboResponse<Long> addGoodsAddress(ShGoodsAddress goodsAddress);
    DubboResponse<Long> updateGoodsAddress(ShGoodsAddress goodsAddress);
    DubboResponse<Boolean> updateGoodsAddressNotDefault(Long uid);
    DubboResponse<Boolean> updateGoodsAddressDefault(Long id);
    DubboResponse<ShGoodsAddress> selectGoodsAddressById(Long id);
    DubboResponse<ShGoodsAddress> selectDefaultGoodsAddressByUid(Long uid);
    DubboResponse<List<ShGoodsAddress>> selectGoodsAddressByUid(Long uid,Integer status);
    DubboResponse<PageResult<ShGoodsAddress>> getGoodsAddressResultByQuery(ShGoodsAddressQueryPageParam queryPageParam);

}
