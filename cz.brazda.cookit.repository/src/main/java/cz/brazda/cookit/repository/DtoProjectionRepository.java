package cz.brazda.cookit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional
public interface DtoProjectionRepository<U, V> extends JpaRepository<U, V> {
    <T> T findById(Long id, Class<T> type);
}
