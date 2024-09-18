package com.Aaron.MFM.web.app.scheduled;

import com.Aaron.MFM.web.app.mapper.ChatInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTesks {

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    // 每日0点删除过期的聊天记录
    @Scheduled(cron = "0 0 3 * * ?")
    /*0：秒数，设置为 0，表示在每分钟的第 0 秒执行。
      1：分钟，设置为 1，表示在每小时的第 1 分钟执行。
      *：小时，设置为 *，表示每小时。
      *：月份中的天，设置为 *，表示每个月的每一天。
      *：月份，设置为 *，表示每个月。
      ?：星期几，设置为 ?，表示不关心星期几。*/
    public void deleteChatInfoExpired(){
        chatInfoMapper.deleteChatInfoExpired();
    }
}
