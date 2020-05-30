package com.li.yun.biao.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.li.yun.biao.common.enums.EnumPermissionMenuType;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.api.UsPermissionService;
import com.li.yun.biao.user.dao.ShPermissionDao;
import com.li.yun.biao.user.dao.ShRoleDao;
import com.li.yun.biao.user.dao.ShRolePermissionDao;
import com.li.yun.biao.user.dao.ShUserRoleDao;
import com.li.yun.biao.user.dto.param.query.ShPermissionQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShRolePermissionQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShRoleQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserRoleQueryPageParam;
import com.li.yun.biao.user.model.*;
import com.li.yun.biao.user.repository.ShPermissionRepository;
import com.li.yun.biao.user.repository.ShRolePermissionRepository;
import com.li.yun.biao.user.repository.ShRoleRepository;
import com.li.yun.biao.user.repository.ShUserRoleRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service("usPermissionService")
@Service(version = "1.0.0", timeout = 3000, interfaceClass = UsPermissionService.class)
public class UsPermissionServiceImpl implements UsPermissionService {
    private static final Logger logger = LoggerFactory.getLogger(UsPermissionServiceImpl.class);
    @Resource
    private ShRoleDao shRoleDao;
    @Resource
    private ShUserRoleDao shUserRoleDao;
    @Resource
    private ShPermissionDao shPermissionDao;
    @Resource
    private ShRolePermissionDao shRolePermissionDao;
    @Resource
    private ShRoleRepository shRoleRepository;
    @Resource
    private ShUserRoleRepository shUserRoleRepository;
    @Resource
    private ShPermissionRepository shPermissionRepository;
    @Resource
    private ShRolePermissionRepository shRolePermissionRepository;


    @Override
    public DubboResponse<Long> addRole(ShRole role) {
        return new DubboResponse<>(shRoleDao.save(role).getId());
    }

    @Override
    public DubboResponse<Long> updateRole(ShRole role) {
        return new DubboResponse<>(shRoleDao.saveAndFlush(role).getId());
    }

    @Override
    public DubboResponse<Void> deleteRole(Long id) {
        shRoleDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShRole> getRoleById(Long id) {
        return new DubboResponse<>(shRoleDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<PageResult<ShRole>> getRoleResult(ShRoleQueryPageParam queryPageParam) {
        Specification<ShRole> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("p_type"), queryPageParam.getPType()));
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShRole>(shRoleRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Long> addPermission(ShPermission permission) {
        return new DubboResponse<>(shPermissionDao.save(permission).getId());
    }

    @Override
    public DubboResponse<Long> updatePermission(ShPermission permission) {
        return new DubboResponse<>(shPermissionDao.saveAndFlush(permission).getId());
    }

    @Override
    public DubboResponse<Void> deletePermission(Long id) {
        shPermissionDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShPermission> getPermissionById(Long id) {
        return new DubboResponse<>(shPermissionDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShPermission> getPermissionByCode(String permCode, Integer pType) {
        return new DubboResponse<>(shPermissionDao.findByPermCodeAndPType(permCode, pType));
    }

    @Override
    public DubboResponse<ShPermission> getPermissionBySort(Integer sort) {
        return new DubboResponse<>(shPermissionDao.findBySort(sort));
    }

    @Override
    public DubboResponse<List<String>> getPermCode(Long uid, Integer pType) {
        List<String> permCodes = new ArrayList<>();
        ShUserRole userRole = shUserRoleDao.findByUidAndPType(uid, pType);
        if (Objects.isNull(userRole)) {
            return new DubboResponse<>(permCodes);
        }
        List<Long> rids = shRolePermissionDao.findPermissionIdByRidAndPType(userRole.getRid(), pType);
        if (CollectionUtils.isEmpty(rids)) {
            return new DubboResponse<>(permCodes);
        }
        return new DubboResponse<>(shPermissionDao.findPermCodeByIds(rids));
    }

    @Override
    public DubboResponse<List<ShPermission>> getPermissionListByPid(Long pid, Integer pType) {
        return new DubboResponse<>(shPermissionDao.findByPidAndPType(pid, pType));
    }

    @Override
    public DubboResponse<List<ShPermission>> getPermissionListByIdList(List<Long> idList, Integer pType) {
        if (CollectionUtils.isEmpty(idList)) {
            return new DubboResponse<>(Lists.newArrayList());
        }
        return new DubboResponse<>(shPermissionDao.findPermissionListByIdList(idList, pType));
    }

    @Override
    public DubboResponse<PageResult<ShPermission>> getPermissionResult(ShPermissionQueryPageParam queryPageParam) {
        Specification<ShPermission> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("pid"), queryPageParam.getPid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getState())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("state"), queryPageParam.getState()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("type"), queryPageParam.getType()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getPermCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("perm_code"), queryPageParam.getPermCode()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("p_type"), queryPageParam.getPType()));
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShPermission>(shPermissionRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Long> addRolePermission(ShRolePermission rolePermission) {
        return new DubboResponse<>(shRolePermissionDao.save(rolePermission).getId());
    }

    @Override
    public DubboResponse<Long> updateRolePermission(ShRolePermission rolePermission) {
        return new DubboResponse<>(shRolePermissionDao.saveAndFlush(rolePermission).getId());
    }

    @Override
    public DubboResponse<Void> deleteRolePermission(Long id) {
        shRolePermissionDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<Integer> deleteRolePermissionByPidAndRid(Long pid, Long rid, Integer pType) {
        return new DubboResponse<>(shRolePermissionDao.deleteRolePermissionByPidAndRid(pid, rid, pType));
    }

    @Override
    public DubboResponse<ShRolePermission> getRolePermissionById(Long id) {
        return new DubboResponse<>(shRolePermissionDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShRolePermission> getRolePermissionByPidAndRid(Long pid, Long rid, Integer pType) {
        return new DubboResponse<>(shRolePermissionDao.findRolePermissionByPidAndRid(pid, rid, pType));
    }

    @Override
    public DubboResponse<List<Long>> getPermissionIdByRid(Long rid, Integer pType) {
        return new DubboResponse<>(shRolePermissionDao.findPermissionIdByRidAndPType(rid, pType));
    }

    @Override
    public DubboResponse<PageResult<ShRolePermission>> getRolePermissionResult(ShRolePermissionQueryPageParam queryPageParam) {
        Specification<ShRolePermission> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("pid"), queryPageParam.getPid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getRid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("rid"), queryPageParam.getRid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("p_type"), queryPageParam.getPType()));
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShRolePermission>(shRolePermissionRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Long> addUserRole(ShUserRole userRole) {
        return new DubboResponse<>(shUserRoleDao.save(userRole).getId());
    }

    @Override
    public DubboResponse<Long> updateUserRole(ShUserRole userRole) {
        return new DubboResponse<>(shUserRoleDao.saveAndFlush(userRole).getId());
    }

    @Override
    public DubboResponse<Void> deleteUserRole(Long id) {
        shUserRoleDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShUserRole> getUserRoleById(Long id) {
        return new DubboResponse<>(shUserRoleDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShUserRole> getUserRoleByUid(Long uid, Integer pType) {
        return new DubboResponse<>(shUserRoleDao.findByUidAndPType(uid, pType));
    }

    @Override
    public DubboResponse<PageResult<ShUserRole>> getUserRoleResult(ShUserRoleQueryPageParam queryPageParam) {
        Specification<ShUserRole> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getUid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("uid"), queryPageParam.getUid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getRid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("rid"), queryPageParam.getRid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getPType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("p_type"), queryPageParam.getPType()));
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShUserRole>(shUserRoleRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<List<ShUserMenuVo>> getUserMenuVoByUid(Long uid, Integer pType) {
        ShUserRole userRole = shUserRoleDao.findByUidAndPType(uid, pType);
        if (Objects.isNull(userRole)) return new DubboResponse<>();
        Long rid = userRole.getRid();
        List<Long> rids = shRolePermissionDao.findPermissionIdByRidAndPType(rid, pType);
        List<ShPermission> permissionList = rids.isEmpty() ? Lists.newArrayList() : shPermissionDao.findPermissionListByIdList(rids, pType);
        List<ShUserMenuVo> userMenuVos = new ArrayList<>();
        permissionList.forEach(permission -> {
            ShUserMenuVo userMenuVo = new ShUserMenuVo();
            userMenuVo.setId(permission.getId());
            userMenuVo.setIcon(permission.getIcon());
            userMenuVo.setPermCode(permission.getPermCode());
            userMenuVo.setTitle(permission.getTitle());
            userMenuVo.setUrl(permission.getUrl());
            List<ShPermission> permissionLists = shPermissionDao.findByPidAndPType(permission.getId(), pType);
            List<ShUserMenuVo> userMenuVoList = new ArrayList<>();
            permissionLists.forEach(permissions -> {
                ShRolePermission rolePermission = shRolePermissionDao.findRolePermissionByPidAndRid(permissions.getId(), rid, pType);
                if (Objects.nonNull(rolePermission)) {
                    ShUserMenuVo menuVo = new ShUserMenuVo();
                    menuVo.setId(permissions.getId());
                    menuVo.setIcon(permissions.getIcon());
                    menuVo.setPermCode(permissions.getPermCode());
                    menuVo.setTitle(permissions.getTitle());
                    if (Objects.equals(EnumPermissionMenuType.JAVA_WEB.type, pType)) {
                        menuVo.setUrl("/" + permission.getUrl().replace("/", "")
                                + permissions.getUrl());
                    } else {
                        menuVo.setUrl("/" + permission.getUrl() + permissions.getUrl());
                    }
                    userMenuVoList.add(menuVo);
                }
            });
            userMenuVo.setMvList(userMenuVoList);
            userMenuVos.add(userMenuVo);
        });
        return new DubboResponse<>(userMenuVos);
    }
}
