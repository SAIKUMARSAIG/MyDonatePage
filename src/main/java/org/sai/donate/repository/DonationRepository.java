package org.sai.donate.repository;



import org.sai.donate.model.DonationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<DonationItem, Long> {
//    List<DonationItem> findByCategory(String category);
    List<DonationItem> findByIsClaimed(Boolean isClaimed);
//    List<DonationItem> findByDonorEmail(String donorEmail);

    @Transactional
    @Modifying
    @Query("UPDATE DonationItem d SET d.isClaimed = :isClaimed WHERE d.id = :id")
    int updateClaimStatus(@Param("id") Long id, @Param("isClaimed") Boolean isClaimed);
}