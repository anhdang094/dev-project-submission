package dev.remitano.core.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "votes")
public class Vote extends GenericEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}

