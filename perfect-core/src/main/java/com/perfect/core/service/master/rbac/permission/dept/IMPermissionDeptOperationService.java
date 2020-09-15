package com.perfect.core.service.master.rbac.permission.dept;

import com.baomidou.mybatisplus.extension.service.IService;
import com.perfect.bean.entity.master.rbac.permission.MPermissionEntity;
import com.perfect.bean.vo.master.rbac.permission.MPermissionMenuOperationVo;
import com.perfect.bean.vo.master.rbac.permission.operation.OperationMenuDataVo;
import com.perfect.bean.vo.master.rbac.permission.operation.OperationMenuVo;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zxh
 * @since 2020-07-27
 */
public interface IMPermissionDeptOperationService extends IService<MPermissionEntity> {

    /**
     * 获取所有数据
     */
    OperationMenuVo getTreeData(OperationMenuDataVo searchCondition) ;

    /**
     * 复制选中的菜单
     */
    void setSystemMenuData2PermissionData(OperationMenuDataVo searchCondition) ;

    /**
     * 保存权限操作数据和菜单权限
     */
    boolean savePermission(MPermissionMenuOperationVo condition) ;
}
