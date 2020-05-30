package com.li.yun.biao.user.api;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.dto.param.query.ShPermissionQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShRolePermissionQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShRoleQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserRoleQueryPageParam;
import com.li.yun.biao.user.model.*;

import java.util.List;

/**
 * 权限接口
 */
public interface UsPermissionService {
    /**
     * 角色管理
     */
    DubboResponse<Long> addRole(ShRole role);

    DubboResponse<Long> updateRole(ShRole role);

    DubboResponse<Void> deleteRole(Long id);

    DubboResponse<ShRole> getRoleById(Long id);

    DubboResponse<PageResult<ShRole>> getRoleResult(ShRoleQueryPageParam queryPageParam);

    /**
     * 权限管理
     */
    DubboResponse<Long> addPermission(ShPermission permission);

    DubboResponse<Long> updatePermission(ShPermission permission);

    DubboResponse<Void> deletePermission(Long id);

    DubboResponse<ShPermission> getPermissionById(Long id);

    DubboResponse<ShPermission> getPermissionByCode(String permCode, Integer pType);

    DubboResponse<ShPermission> getPermissionBySort(Integer sort);

    DubboResponse<List<String>> getPermCode(Long uid, Integer pType);

    DubboResponse<List<ShPermission>> getPermissionListByPid(Long pid, Integer pType);

    DubboResponse<List<ShPermission>> getPermissionListByIdList(List<Long> idList, Integer pType);

    DubboResponse<PageResult<ShPermission>> getPermissionResult(ShPermissionQueryPageParam queryPageParam);


    /**
     * 角色权限
     */

    DubboResponse<Long> addRolePermission(ShRolePermission rolePermission);

    DubboResponse<Long> updateRolePermission(ShRolePermission rolePermission);

    DubboResponse<Void> deleteRolePermission(Long id);

    DubboResponse<Integer> deleteRolePermissionByPidAndRid(Long pid, Long rid, Integer pType);

    DubboResponse<ShRolePermission> getRolePermissionById(Long id);

    DubboResponse<ShRolePermission> getRolePermissionByPidAndRid(Long pid, Long rid, Integer pType);

    DubboResponse<List<Long>> getPermissionIdByRid(Long rid, Integer pType);

    DubboResponse<PageResult<ShRolePermission>> getRolePermissionResult(ShRolePermissionQueryPageParam queryPageParam);

    /**
     * 用户角色
     *
     * @param userRole
     * @return
     */
    DubboResponse<Long> addUserRole(ShUserRole userRole);

    DubboResponse<Long> updateUserRole(ShUserRole userRole);

    DubboResponse<Void> deleteUserRole(Long id);

    DubboResponse<ShUserRole> getUserRoleById(Long id);

    DubboResponse<ShUserRole> getUserRoleByUid(Long uid, Integer pType);

    DubboResponse<PageResult<ShUserRole>> getUserRoleResult(ShUserRoleQueryPageParam queryPageParam);

    DubboResponse<List<ShUserMenuVo>> getUserMenuVoByUid(Long uid, Integer pType);
}
