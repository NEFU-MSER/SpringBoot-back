package org.kukuking.back.repository;

import org.kukuking.back.DO.TradeLog;
import org.kukuking.back.extractor.TradeLogResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TradeLogRepository extends CrudRepository<TradeLog, String> {
    @Query(value = "select * from trade_log as t",
            resultSetExtractorClass = TradeLogResultSetExtractor.class)
    List<TradeLog> findAllTradeLogs();

    @Query(value = "select * from trade_log as tl where tl.card_id = :cardId",
            resultSetExtractorClass = TradeLogResultSetExtractor.class)
    List<TradeLog> findAllByCardId(String cardId);
}
