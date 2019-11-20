package pl.kamilszustak.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.kamilszustak.blog.model.Comment

interface CommentRepository : JpaRepository<Comment, Int>