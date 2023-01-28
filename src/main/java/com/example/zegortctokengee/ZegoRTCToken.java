package com.example.zegortctokengee;

import com.example.zegortctokengee.utils.TokenServerAssistant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ZegoRTCToken {
    long appId = 1770178411L;    // 请替换为你的 appId，从即构控制台获取
    String serverSecret = "db5e7faf10f8c50a3b05efb348aadb71";  // 请替换为你的 serverSecret，从即构控制台获取，
//    String userId = "test_user";    // 请替换为用户的 userID，同一 appId 下全网唯一
//    int effectiveTimeInSeconds = 300;   // 有效时间，单位：秒

    @GetMapping(path = "/")
    public Map<Object, Object> getToken (@RequestParam String channelId, @RequestParam String userId, @RequestParam int expired_ts){
        Map<Object, Object> payloadData = new HashMap<>();
        payloadData.put("roomId", channelId);

        Map<Object, Object> privilege = new HashMap<>();
        privilege.put(TokenServerAssistant.PrivilegeKeyLogin, TokenServerAssistant.PrivilegeEnable);
        privilege.put(TokenServerAssistant.PrivilegeKeyPublish, TokenServerAssistant.PrivilegeDisable);
        payloadData.put("privilege", privilege);
        payloadData.put("stream_id_list", null);
        String payload = payloadData.toString();
        TokenServerAssistant.VERBOSE = false;
        TokenServerAssistant.TokenInfo token = TokenServerAssistant.generateToken04(appId,  userId, serverSecret, expired_ts, payload);
        System.out.println(token.data);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token.data);
        return response;
    }

}
