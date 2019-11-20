package pl.kamilszustak.blog.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kamilszustak.blog.model.Post
import pl.kamilszustak.blog.repository.PostRepository

@Service
@Primary
class PostServiceImpl @Autowired constructor(
    private val postRepository: PostRepository
) : PostService {

    override fun getAll(): List<Post> = postRepository.findAll()

    override fun getById(id: Int): Post? = postRepository.findByIdOrNull(id)

    override fun add(item: Post) {
        postRepository.save(item)
    }

    override fun deleteById(id: Int) {
        postRepository.deleteById(id)
    }

    override fun put(item: Post) {
        postRepository.save(item)
    }
}