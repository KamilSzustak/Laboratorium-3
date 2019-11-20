package pl.kamilszustak.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.kamilszustak.blog.model.Post

@Repository
interface PostRepository : JpaRepository<Post, Int>