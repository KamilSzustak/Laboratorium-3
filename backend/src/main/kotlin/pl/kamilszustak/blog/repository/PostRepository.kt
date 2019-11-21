package pl.kamilszustak.blog.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pl.kamilszustak.blog.model.Post
import java.util.*

@Repository
interface PostRepository : JpaRepository<Post, Int> {

    @Query(
        value = "SELECT * FROM posts WHERE title LIKE %:text% OR content LIKE %:text%",
        nativeQuery = true
    )
    fun findAllContaing(@Param("text") text: String): List<Post>

    @Query(
        value = "SELECT * FROM posts WHERE created_at = :date",
        nativeQuery = true
    )
    fun findAllCreatedAt(@Param("date") date: Date): List<Post>

    @Query(
        value = "SELECT * FROM posts post INNER JOIN comments comments ON comments.post_id = post.id",
        nativeQuery = true
    )
    fun findAllWithComments(): List<Post>

    @Query(
        value = "SELECT TOP :amount * FROM posts",
        nativeQuery = true
    )
    fun findFirst(@Param("amount") amount: Int): List<Post>

    @Query(
        value = "SELECT * FROM posts WHERE created_at BETWEEN :startDate AND :endDate",
        nativeQuery = true
    )
    fun findAllCreatedBetween(@Param("startDate") startDate: Date, @Param("endDate") endDate: Date): List<Post>
}