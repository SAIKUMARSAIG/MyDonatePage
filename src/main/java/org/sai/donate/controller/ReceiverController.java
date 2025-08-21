package org.sai.donate.controller;






import org.sai.donate.model.DonationItem;
import org.sai.donate.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receiver")
public class ReceiverController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public String receiverPage(Model model) {
        List<DonationItem> availableItems = donationService.getAvailableDonations();
        model.addAttribute("donations", availableItems);
        return "receiver";
    }

    @PostMapping("/claim/{id}")
    public String claimDonation(@PathVariable Long id) {
        donationService.claimDonation(id);
        return "redirect:/receiver?claimed";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<DonationItem> searchDonations(@RequestParam String category) {
        if (category == null || category.isEmpty()) {
            return donationService.getAvailableDonations();
        }
        return donationService.getAvailableDonations();
    }
}