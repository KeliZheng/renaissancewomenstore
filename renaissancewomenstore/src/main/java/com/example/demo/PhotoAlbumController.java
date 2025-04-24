package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PhotoAlbumController {

    @Autowired
    private PhotoAlbumStore photoAlbumStore;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("photos", photoAlbumStore.getPhotos());
        model.addAttribute("cart", photoAlbumStore.getCart());
        model.addAttribute("cartTotal", photoAlbumStore.getCartTotal());
        return "index"; // Renders src/main/resources/templates/index.html
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String photoId, @RequestParam int quantity, Model model) {
        String message = photoAlbumStore.addToCart(photoId, quantity);
        model.addAttribute("message", message);
        model.addAttribute("photos", photoAlbumStore.getPhotos());
        model.addAttribute("cart", photoAlbumStore.getCart());
        model.addAttribute("cartTotal", photoAlbumStore.getCartTotal());
        return "index";
    }

    @PostMapping("/checkout")
    public String checkout(Model model) {
        String message = photoAlbumStore.checkout();
        model.addAttribute("message", message);
        model.addAttribute("photos", photoAlbumStore.getPhotos());
        model.addAttribute("cart", photoAlbumStore.getCart());
        model.addAttribute("cartTotal", photoAlbumStore.getCartTotal());
        return "index";
    }
}