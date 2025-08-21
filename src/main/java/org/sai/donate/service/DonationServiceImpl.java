package org.sai.donate.service;




import org.sai.donate.model.DonationItem;
import org.sai.donate.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public DonationItem saveDonation(DonationItem donationItem) {

        return donationRepository.save(donationItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonationItem> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonationItem> getAvailableDonations() {
        return donationRepository.findByIsClaimed(false);
    }

    @Override
    @Transactional(readOnly = true)
    public DonationItem getDonationById(Long id) {
        Optional<DonationItem> optional = donationRepository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("Donation not found with id: " + id));
    }

    @Override
    public DonationItem claimDonation(Long id) {
        DonationItem donation = getDonationById(id);
        donation.setIsClaimed(true);
        return donationRepository.save(donation);
    }

    @Override
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}