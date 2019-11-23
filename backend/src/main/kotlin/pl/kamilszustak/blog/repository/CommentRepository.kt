package pl.kamilszustak.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pl.kamilszustak.blog.model.Comment
import java.util.*

@Repository
interface CommentRepository : JpaRepository<Comment, Int> {

    @Query(
        value = "SELECT * FROM comments WHERE content LIKE %:text%",
        nativeQuery = true
    )
    fun findAllContaining(@Param("text") text: String): List<Comment>

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

    @Query(
        value = "SELECT COUNT(*) FROM comments",
        nativeQuery = true
    )
    fun countAll(): Int

    @Query(
        value = "SELECT * FROM comments WHERE id IN :ids",
        nativeQuery = true
    )
    fun findAllByIds(@Param("ids") ids: Array<Long>): List<Comment>

    @Query(
        value = "SELECT * FROM comments ORDER BY created_at DESC;",
        nativeQuery = true
    )
    fun findAllOrderedByCreatedAtDate(): List<Comment>

    @Query(
        value = "SELECT * FROM comments WHERE content IS NOT NULL AND LENGTH(content) > :length",
        nativeQuery = true
    )
    fun findAllLongerThan(@Param("length") length: Int): List<Comment>

    @Query(
        value = "SELECT TOP 1 * FROM comments WHERE (SELECT MAX(id) FROM comments) = id",
        nativeQuery = true
    )
    fun findTheNewest(): Comment

    @Query(
        value = "SELECT TOP 1 * FROM comments WHERE (SELECT MIN(id) FROM comments) = id",
        nativeQuery = true
    )
    fun findTheOldest(): Comment
}