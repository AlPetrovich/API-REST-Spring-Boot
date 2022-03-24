package com.blog.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publications", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "title", nullable = false)
    private String title;

    @Column( name = "description", nullable = false)
    private String description;

    @Column( name = "content", nullable = false)
    private String content;

    //orphanRemoval -> se elimina la clase padre y sus objetos asociados
    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL, orphanRemoval = true) //private Publication publication;
    private Set<Comment> commentSet = new HashSet<>();
}
