package com.perfect.core.service.client.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.perfect.bean.entity.master.user.MUserLiteEntity;
import com.perfect.bean.vo.master.user.MUserLiteVo;

/**
 * <p>
 * 用户表 简单 服务类
 * </p>
 *
 * @author zxh
 * @since 2019-07-13
 */
public interface IMUserLiteService extends IService<MUserLiteEntity> {

    /**
     * 重建用户简单
     *
     * @param user_id
     * @return
     */
    MUserLiteVo reBulidUserLiteData(Long user_id);

    /**
     * 更细用户默认菜单
     *
     * @param user_id
     * @return
     */
    MUserLiteVo updDefaultMenu(Long user_id);

}
