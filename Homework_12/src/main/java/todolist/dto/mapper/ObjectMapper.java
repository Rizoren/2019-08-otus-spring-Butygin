package todolist.dto.mapper;

import java.util.List;

public interface ObjectMapper<E, D> {
    D toDto(E entity);
    E toEntity(D to);
    List<D> toListDto(List<E> entityList);
    List<E> toList(List<D> toList);
}
