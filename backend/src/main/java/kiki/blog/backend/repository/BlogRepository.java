package kiki.blog.backend.repository;

import kiki.blog.backend.entity.Blog;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "kiki.blog.backend.repository")
@EntityScan(basePackages = "kiki.blog.backend.entity")
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query("select t from Blog t")
    List<Blog> find();
}
