package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "activation_key")
public class ActivationKey {
    @Id
    private String key;
    @DateTimeFormat
    @Column(name = "send_date")
    private Date sendDate;
    @ManyToOne
    private User user;
}
