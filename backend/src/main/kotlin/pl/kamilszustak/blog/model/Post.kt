package pl.kamilszustak.blog.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Column(name = "title")
    var title: String = "",

    @Column(name = "content")
    var content: String = ""
) : DatabaseEntity() {

    @JsonManagedReference
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf()
}