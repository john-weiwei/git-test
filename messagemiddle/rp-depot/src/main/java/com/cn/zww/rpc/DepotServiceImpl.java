package com.cn.zww.rpc;

import com.cn.zww.service.DepotManager;
import com.cn.zww.vo.GoodTransferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangWeiWei
 * @date 2020/11/17 21:40
 * @description
 */
@Service
public class DepotServiceImpl implements DepotService {

    @Autowired
    private DepotManager depotManager;

    @Override
    public void changeDepot(GoodTransferVo goodTransferVo) {
        depotManager.operDepot(goodTransferVo);
    }
}
