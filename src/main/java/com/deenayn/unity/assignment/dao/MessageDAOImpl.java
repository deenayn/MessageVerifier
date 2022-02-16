package com.deenayn.unity.assignment.dao;

import com.deenayn.unity.assignment.model.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MessageDAOImpl implements MessageDAO {

    static final String INSERT_QUERY = "INSERT INTO message_info (ts, sender, message, sent_from_ip, priority) VALUES" +
            "(:ts, :sender, to_json(:message::json), :sent_from_ip, :priority)";

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public MessageDAOImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public void save(MessageInfo messageInfo) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("ts", messageInfo.getTs());
            paramMap.put("sender", messageInfo.getSender());
            paramMap.put("message", messageInfo.getMessage().toString());
            paramMap.put("sent_from_ip", messageInfo.getSentFromIp());
            paramMap.put("priority", messageInfo.getPriority());
            namedJdbcTemplate.update(INSERT_QUERY, paramMap);
            log.info("Successfully saved data to database, data = {}", messageInfo.toString());
        } catch (Exception e) {
            log.error("Couldn't save data to database, reason = {}", e.getMessage());
            throw e;
        }
    }
}
