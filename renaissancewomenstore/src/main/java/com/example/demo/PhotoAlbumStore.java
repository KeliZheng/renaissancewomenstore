package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// Represents a photo
class Photo {
    private String id;
    private String url;
    private String caption;
    private double price;

    public Photo(String id, String url, String caption, double price) {
        this.id = id;
        this.url = url;
        this.caption = caption;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Caption: " + caption + ", Price: $" + price;
    }
}

// Represents an item in the shopping cart
class CartItem {
    private Photo photo;
    private int quantity;

    public CartItem(Photo photo, int quantity) {
        this.photo = photo;
        this.quantity = quantity;
    }

    public Photo getPhoto() {
        return photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return photo.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Photo: " + photo + ", Quantity: " + quantity;
    }
}

// Manages the photo album and shopping cart
@Service
public class PhotoAlbumStore {
    private final List<Photo> photos = new ArrayList<>();
    private final List<CartItem> cart = new ArrayList<>();

    public PhotoAlbumStore() {
        // Initialize photos
        initializePhotos();
    }

    // Initializes the photos
    private void initializePhotos() {
        
        photos.add(new Photo("1", "/IMG_4244 2.jpg", "Dog", 10.99));
        photos.add(new Photo("2", "/IMG_4245 2.jpg", "Cityscape", 15.99));
        photos.add(new Photo("3", "/IMG_4246 2.JPG", "Mountain Landscape", 20.00));
        photos.add(new Photo("4", "/IMG_4249 2.JPG", "City at Night", 25.50));
        photos.add(new Photo("5", "/IMG_4253 2.JPG", "Forest Path", 18.00));
        photos.add(new Photo("6", "/IMG_5606.png", "Abstract Art", 12.00));
        photos.add(new Photo("7", "/IMG_5608.jpg", "Abstract Art", 12.00));
    }

    // Get all photos
    public List<Photo> getPhotos() {
        return new ArrayList<>(photos);
    }

    // Add a photo to the cart
    public String addToCart(String photoId, int quantity) {
        if (quantity <= 0) {
            return "Invalid quantity. Please enter a positive number.";
        }

        Photo selectedPhoto = photos.stream()
                .filter(photo -> photo.getId().equals(photoId))
                .findFirst()
                .orElse(null);

        if (selectedPhoto == null) {
            return "Photo not found.";
        }

        // Check if the item is already in the cart
        for (CartItem item : cart) {
            if (item.getPhoto().getId().equals(photoId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return "Quantity updated in cart.";
            }
        }

        cart.add(new CartItem(selectedPhoto, quantity));
        return "Photo added to cart.";
    }

    // Get cart items
    public List<CartItem> getCart() {
        return new ArrayList<>(cart);
    }

    // Calculate cart total
    public double getCartTotal() {
        return cart.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    // Simulate checkout
    public String checkout() {
        if (cart.isEmpty()) {
            return "Your cart is empty. Nothing to checkout.";
        }

        // Simulate payment processing
        try {
            Thread.sleep(2000); // Simulate a 2-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean paymentSuccessful = Math.random() < 0.9; // 90% success rate for demo
        if (paymentSuccessful) {
            cart.clear(); // Clear the cart after successful checkout
            return "Payment successful! Thank you for your purchase.";
        } else {
            return "Payment failed. Please try again.";
        }
    }
}