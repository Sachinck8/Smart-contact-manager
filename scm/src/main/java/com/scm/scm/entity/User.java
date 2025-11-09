package com.scm.scm.entity;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
     private String id = UUID.randomUUID().toString();
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is mandatory")
    
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Lob
    @Size(max = 5000, message = "About section cannot exceed 5000 characters")
    private String about;
    @Column(length = 1000)
    private String profilePic;

    @Pattern(
        regexp = "^(?:\\+\\d{1,3})?\\s?\\d{7,15}$",
        message = "Invalid phone number format"
    )
    private String phoneNumber;
    private boolean enable = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Provider cannot be null")
    private Provider provider = Provider.SELF;
    private String emailToken;
     @OneToMany(
        mappedBy = "user",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<Contact> contacts = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
         @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
     }
     @Override
     public String getUsername() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
     }
}
