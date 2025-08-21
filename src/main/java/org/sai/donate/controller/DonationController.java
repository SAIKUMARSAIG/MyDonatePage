package org.sai.donate.controller;



import jakarta.validation.Valid;
import org.sai.donate.model.DonationItem;
import org.sai.donate.service.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/donor")
public class DonationController {

    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);

    @Autowired
    private DonationService donationService;

    @Value("${upload.directory}")
    private String uploadDirectory;

    @GetMapping
    public String donorPage(Model model) {
        model.addAttribute("donationItem", new DonationItem());
        return "donor";
    }


    @PostMapping("/donate")
    public String donateItem(@Valid @ModelAttribute("donationItem") DonationItem donationItem,
                             BindingResult bindingResult,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            return "donor";
        }

        try {
            logger.info("Received donation: {}", donationItem.getItemName());


            if (donationItem.getItemName() == null || donationItem.getItemName().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Item name is required");
                return "redirect:/donor?error";
            }

            if (!imageFile.isEmpty()) {
                // Create upload directory if it doesn't exist
                Path uploadPath = Paths.get(uploadDirectory);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                    logger.info("Created upload directory: {}", uploadDirectory);
                }

                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path path = Paths.get(uploadDirectory + fileName);
                Files.write(path, imageFile.getBytes());
                donationItem.setImagePath("/uploads/" + fileName);
                logger.info("Image saved: {}", fileName);
            }


            donationItem.setDonationDate(LocalDateTime.now());
            donationItem.setIsClaimed(false);

            DonationItem savedItem = donationService.saveDonation(donationItem);
            logger.info("Donation saved with ID: {}", savedItem.getId());

            redirectAttributes.addFlashAttribute("message", "Donation submitted successfully!");
            return "redirect:/donor?success";
        } catch (IOException e) {
            logger.error("Error saving image: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error saving image: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error saving donation: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", "Error saving donation: " + e.getMessage());
        }
        return "redirect:/donor?error";
    }






    @GetMapping("/list")
    @ResponseBody
    public List<DonationItem> getDonationsByDonor(@RequestParam String email) {
        return donationService.getAllDonations();
    }
}