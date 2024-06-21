package org.kukuking.back.extractor;

import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.TradeLog;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TradeLogResultSetExtractor implements ResultSetExtractor<List<TradeLog>> {
    @Override
    public List<TradeLog> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<TradeLog> tradeLogs = new ArrayList<>();
        while (rs.next()) {
            tradeLogs.add(TradeLog.builder()
                    .id(rs.getString("id"))
                    .cardId(rs.getString("card_id"))
                    .execute(rs.getDouble("execute"))
                    .reason(rs.getString("reason"))
                    .createTime(rs.getObject("create_time", LocalDateTime.class))
                    .build());
        }
        return tradeLogs;
    }
}
