package pl.kamilszustak.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pl.kamilszustak.blog.model.Comment
import pl.kamilszustak.blog.model.Post
import java.util.*

@Repository
interface CommentRepository : JpaRepository<Comment, Int> {

    @Query(
        value = "SELECT * FROM comments WHERE content LIKE %:text%",
        nativeQuery = true
    )
    fun findAllContaing(@Param("text") text: String): List<Comment>

    @Query(
        value = "SELECT * FROM comments WHERE created_at = :date",
        nativeQuery = true
    )
    fun findAllCreatedAt(@Param("date") date: Date): List<Comment>

    @Query(
        value = "SELECT * FROM comments comment INNER JOIN posts post ON comment.post_id = post.id",
        nativeQuery = true
    )
    fun findAllByPostId(@Param("postId") postId: Int): List<Comment>

    @Query(
        value = "SELECT TOP :amount * FROM comments",
        nativeQuery = true
    )
    fun findFirst(@Param("amount") amount: Int): List<Comment>

    @Query(
        value = "SELECT * FROM comments WHERE created_at BETWEEN :startDate AND :endDate",
        nativeQuery = true
    )
    fun findAllCreatedBetween(@Param("startDate") startDate: Date, @Param("endDate") endDate: Date): List<Comment>
}