package kiki.blog.backend.repository;

import kiki.blog.backend.entity.Blog;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "kiki.blog.backend.repository")
@EntityScan(basePackages = "kiki.blog.backend.entity")
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query("select b from Blog b")
    List<Blog> find();

    Blog save(Blog u);

    @Query("select b from Blog b where b.id=:id")
    Blog getBlog(int id);

    @Query(value="select * from Blog b where b.date regexp :year", nativeQuery = true)
    List<Blog> getBlogByYear(String year);

    @Modifying
    @Transactional
    @Query("update Blog b set b.reader = :reader where b.id=:id")
    void update(int id, int reader);
}
