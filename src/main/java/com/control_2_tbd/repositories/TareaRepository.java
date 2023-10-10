package com.control_2_tbd.repositories;

import com.control_2_tbd.entities.TareaEntity;
import java.util.List;

public interface TareaRepository {
    TareaEntity createTarea(TareaEntity tarea);
    TareaEntity updateTarea(TareaEntity tarea);
    void deleteTarea(int id);
    List<TareaEntity> findAllByUsuario(int idUsuario);
    List<TareaEntity> findAllCompletedByUsuario(int idUsuario);
}
