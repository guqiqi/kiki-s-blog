package kiki.blog.backend.repository;

import kiki.blog.backend.entity.Tag;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "kiki.blog.backend.repository")
@EntityScan(basePackages = "kiki.blog.backend.entity")
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query("select t from Tag t")
    List<Tag> find();

    Tag save(Tag t);
}
