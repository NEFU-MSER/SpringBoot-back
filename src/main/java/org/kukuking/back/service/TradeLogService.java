package org.kukuking.back.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kukuking.back.DO.TradeLog;
import org.kukuking.back.repository.TradeLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeLogService {
    private final TradeLogRepository tradelogRepository;

    public List<TradeLog> findAll(String cardId) {
        return tradelogRepository.findAllByCardId(cardId);
    }

    public TradeLog findById(String id) {
        return tradelogRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(TradeLog tradelog) {
        try {
            tradelogRepository.save(tradelog);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void update(TradeLog tradelog) {
        try {
            tradelogRepository.save(tradelog);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            tradelogRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(List<String> idList) {
        try {
            tradelogRepository.deleteAllById(idList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
