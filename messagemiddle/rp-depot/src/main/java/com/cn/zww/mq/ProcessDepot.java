package com.cn.zww.mq;

import com.cn.zww.service.DepotManager;
import com.cn.zww.vo.GoodTransferVo;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangWeiWei
 * @date 2020/11/17 21:35
 * @description
 */
@Service
public class ProcessDepot implements ChannelAwareMessageListener {
    private static Logger logger = LoggerFactory.getLogger(ProcessDepot.class);

    @Autowired
    private DepotManager depotManager;

    private static Gson gson = new Gson();
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String msg = new String(message.getBody());
            logger.info(">>>>>>>>>>>>>>接收到消息:"+msg);
            GoodTransferVo goodTransferVo = gson.fromJson(msg,GoodTransferVo.class);
            try {
                depotManager.operDepot(goodTransferVo);
<<<<<<< HEAD
                //throw new RuntimeException("库存系统异常了！！！！！");
=======
//                throw new RuntimeException("库存系统异常了！！！！！");
>>>>>>> a25d19730c05c8b28d3d59f12440172853930829
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),
                        false);
                logger.info(">>>>>>>>>>>>>>库存处理完成，应答Mq服务");
            } catch (Exception e) {
                logger.error(e.getMessage());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),
                        false,true);
                logger.info(">>>>>>>>>>>>>>库存处理失败，拒绝消息，要求Mq重新派发");
                throw e;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
