package com.codemika.cyberbank.card.repository;

import com.codemika.cyberbank.card.entity.DebitCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для дебетовых карт
 */
@Repository
public interface DebitCardRepository extends JpaRepository<DebitCardEntity, Long> {

    @Modifying
    @Query("UPDATE DebitCardEntity card SET card.isActive = ?1 WHERE card.id = ?2")
    void updateById(Boolean isActive, Long id);

    List<DebitCardEntity> findAllByOwnerUserId(Long id);
    Optional<DebitCardEntity> findCardByAccountNumber(String AccountNumber);

    Optional<DebitCardEntity> findAllByAccountNumber(String AccountNumber);

    @Modifying
    @Query("UPDATE DebitCardEntity card SET card.balance = ?1 WHERE card.id = ?2")
    void moneyTransfer(Long value, Long id);

    @Modifying
    @Query("UPDATE DebitCardEntity card SET card.title = ?1 WHERE card.id = ?2")
    void updateCardTitle(String title, Long id);

    @Modifying
    @Query("UPDATE DebitCardEntity card SET card.pincode = ?1 WHERE card.id = ?2")
    void updateCardPinCode(String pincode, Long id);

    void deleteByAccountNumber(String accountNumber);
}
