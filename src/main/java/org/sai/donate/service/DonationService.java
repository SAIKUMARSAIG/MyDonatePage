package org.sai.donate.service;

//public class DonationService {
//}



import org.sai.donate.model.DonationItem;
import java.util.List;

public interface DonationService {
    DonationItem saveDonation(DonationItem donationItem);
    List<DonationItem> getAllDonations();
    List<DonationItem> getAvailableDonations();
    DonationItem getDonationById(Long id);
    DonationItem claimDonation(Long id);
    void deleteDonation(Long id);
}