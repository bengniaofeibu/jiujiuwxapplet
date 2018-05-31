package com.applet.utils.common;

import com.applet.entity.UserInfo.EsUserInfo;
import com.applet.model.UserInfo;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EsUtil {


    @Autowired
    TransportClient transportClient;

    /**
     * 保存数据
     *
     * @param userInfo
     */
    public void save(String index, String type, UserInfo userInfo) {
        if (userInfo != null) {
            try {
                XContentBuilder content;
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                content = XContentFactory.jsonBuilder().startObject();

                content.field("location", "")
                        .field("addTime", time);
                content.field("userId", userInfo.getId());
                content.field("phone", userInfo.getPhone());
                content.field("name", userInfo.getNickname());
                content.endObject();
                this.transportClient.prepareIndex(index, type).setSource(content).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
