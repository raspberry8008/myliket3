package com.myliket.myliket3.service.todo;

import com.myliket.myliket3.domain.todo.TodoDetail;
import com.myliket.myliket3.domain.todo.TodoDetailRepository;
import com.myliket.myliket3.web.dto.Response;
import com.myliket.myliket3.web.dto.TodoDto;
import com.myliket.myliket3.web.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoDetailRepository todoDetailRepository;

    @Override
    public Response allTodoList() throws Exception {
        List<TodoDetail> resultList = todoDetailRepository.findAll();
        return Response.builder().resultList(resultList).build();
    }

    @Override
    public Response getCategoryTodoList(TodoDto.PathCategoryId pathCategoryId) throws Exception {
        List<TodoDetail> resultList = todoDetailRepository.findByCategoryId(pathCategoryId.getCategoryId());
        return Response.builder().resultList(resultList).build();
    }

    @Override
    public Response getTodoDetail(TodoDto.PathTodoNo pathTodoNo) throws Exception {
        Optional<TodoDetail> resultEntity = todoDetailRepository.findById(pathTodoNo.getTodoNo());
        if (resultEntity.isEmpty()){
            return Response.builder().data(new TodoResponseDto(new TodoDetail())).build();

        } else {
            TodoDetail resultDto =todoDetailRepository.getReferenceById(pathTodoNo.getTodoNo());
            return Response.builder().data(new TodoResponseDto(resultDto)).build();
        }
    }

    @Override
    public void insertTodo(TodoDto.RequestInsert requestInsert) throws Exception {
        System.out.println("requestInsert:"+requestInsert);
        todoDetailRepository.save(requestInsert.toEntity());
    }

    @Override
    public void updateTodo(TodoDto.RequestUpdate requestUpdate) throws Exception {
        todoDetailRepository.save(requestUpdate.toEntity());
    }

    @Override
    public void deleteTodo(TodoDto.PathTodoNo pathTodoNo) throws Exception {
        Optional<TodoDetail> resultEntity = todoDetailRepository.findById(pathTodoNo.getTodoNo());
        if (!(resultEntity.isEmpty())){
            TodoDetail resultDto =todoDetailRepository.getReferenceById(pathTodoNo.getTodoNo());
            todoDetailRepository.delete(resultDto);
        }
    }
}
