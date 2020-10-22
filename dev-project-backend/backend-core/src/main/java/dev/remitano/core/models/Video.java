package dev.remitano.core.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "videos")
public class Video extends GenericEntity {

    @Column(name = "link", unique = true)
    private String link;

    @Column(name = "title")
    private String title;

    @Column(name = "share_by")
    private Long shareBy;

    @Column(name = "vote_up")
    private Long voteUp;

    @Column(name = "vote_down")
    private Long voteDown;

    @Column(name = "descriptions")
    private String descriptions;

}

