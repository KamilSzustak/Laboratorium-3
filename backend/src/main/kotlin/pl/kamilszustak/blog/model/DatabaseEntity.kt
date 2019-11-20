package pl.kamilszustak.blog.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    @CreatedDate
    @Column(nullable = false, updatable = false)
    val creationDate: Date = Date()

    @LastModifiedDate
    @Column(nullable = false)
    val modificationDate: Date = Date()
}