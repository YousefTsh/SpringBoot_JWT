package com.security.token;

import com.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    @Enumerated(EnumType.STRING)
    private ToketType tokenType;

    private Boolean expired;

    private Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
