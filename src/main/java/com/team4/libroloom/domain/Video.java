package com.team4.libroloom.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    private String name;

    private URL url;

    private URL image;

    private LocalDateTime uploadedDate;

    private int Length;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "video")
    private List<Note> notes = new ArrayList<>();

    //******Associate Method*******///
    public void addNote(Note note){
        this.notes.add(note);
        note.setVideo(this);
    }
    public void setMember(Member member){
        this.member = member;
        member.getVideos().add(this);
    }

}
