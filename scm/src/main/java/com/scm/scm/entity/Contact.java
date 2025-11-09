package com.scm.scm.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    private String id= UUID.randomUUID().toString();
    @NotBlank(message = "Contact name is required")
    @Size(min = 2, max = 100, message = "Contact name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;
    @Email(message = "Invalid email format")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    private String email;
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
    @Pattern(
        regexp = "^(?:\\+\\d{1,3})?\\s?\\d{7,15}$",
        message = "Invalid phone number format"
    )
    private String phoneNumber;
    @Column(length = 1000 )
    private String picture;
    @Lob
    @Size(max = 5000, message = "Description cannot exceed 5000 characters")
    private String description;
    private boolean favorite = false;
    @Size(max = 255, message = "Website link cannot exceed 255 characters")
    @Pattern(
        regexp = "^(https?://)?[\\w.-]+(\\.[\\w\\.-]+)+[/#?]?.*$",
        message = "Invalid website URL"
    )
    private String websiteLink;
    @Size(max = 255, message = "LinkedIn link cannot exceed 255 characters")
    @Pattern(
        regexp = "^(https?://)?(www\\.)?linkedin\\.com/.*$",
        message = "Invalid LinkedIn URL"
    )
    private String linkedinLink;
    
    @Size(max = 255, message = "Cloudinary image ID cannot exceed 255 characters")
    private String cloudinaryImageId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User is required")
    private User user;

}
