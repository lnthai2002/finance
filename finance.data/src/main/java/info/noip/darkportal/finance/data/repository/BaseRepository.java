package info.noip.darkportal.finance.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    //T findOne(ID id);
    <S extends T> S save(S entity);

    @Override
    void deleteById(ID id);
}
