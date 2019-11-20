package pl.kamilszustak.blog.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
    var content: String
) : DatabaseEntity() {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    var post: Post = Post()
}