package hunnid.com.blog.repository;

import hunnid.com.blog.entity.TranslationStringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TranslationStringTypeRepository extends JpaRepository<TranslationStringType, UUID> {
}
