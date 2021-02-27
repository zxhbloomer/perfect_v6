package com.perfect.core.serviceimpl.master.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.perfect.bean.entity.master.org.MStaffOrgEntity;
import com.perfect.bean.entity.master.user.MUserPermissionEntity;
import com.perfect.common.constant.PerfectDictConstant;
import com.perfect.core.mapper.master.user.MStaffMapper;
import com.perfect.core.mapper.master.user.MUserPermissionMapper;
import com.perfect.core.service.master.user.IMUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户权限关联表 服务实现类
 * </p>
 *
 * @author zxh
 * @since 2021-02-09
 */
@Service
public class MUserPermissionServiceImpl extends ServiceImpl<MUserPermissionMapper, MUserPermissionEntity> implements IMUserPermissionService {

    @Autowired
    private MUserPermissionMapper mapper;

    /**
     * 查询岗位员工
     * @param user_id
     * @return
     */
    @Override
    public List<MUserPermissionEntity> resetUserPermission(Long user_id) {
        // 1：删除user对应的所有m_user_permission数据
        mapper.delete(new QueryWrapper<MUserPermissionEntity>()
                .eq("user_id",user_id)
        );
        // 2：重构数据

        return null;
    }
}
