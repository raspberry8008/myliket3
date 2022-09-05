package com.myliket.myliket3.service.todo;

import com.myliket.myliket3.domain.dto.request.todo.TodoPathDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoSaveDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoUpdateDto;
import com.myliket.myliket3.domain.dto.response.common.Response;
import com.myliket.myliket3.domain.dto.response.todo.TodoDetailDto;
import com.myliket.myliket3.domain.dto.response.todo.TodoListDto;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import com.myliket.myliket3.domain.entity.todo.TodoDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoDetailRepository todoDetailRepository;

//    private final TodoDetailRepositorySupport todoDetailRepositorySupport;

    @Override
    public Response allTodoList() throws Exception {
        List<TodoDetail> resultList = todoDetailRepository.findAllByOrderByCategoryCreatedAtAscTodoNoAsc();
//        List<Tuple> resultList = todoDetailRepositorySupport.findAll();

        TodoListDto todoListDto = new TodoListDto();

        return Response.builder()
                .resultList(resultList.stream()
                        .map(todoListDto::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Response getCategoryTodoList(TodoPathDto.PathCategoryId pathCategoryId) throws Exception {

        List<TodoDetail> resultList = todoDetailRepository.findByCategory_CategoryIdOrderByTodoNoAsc(pathCategoryId.getCategoryId());

        TodoListDto todoListDto = new TodoListDto();

        return Response.builder()
                .resultList(resultList.stream()
                        .map(todoListDto::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Response getTodoDetail(TodoPathDto.PathTodoNo pathTodoNo) throws Exception {

        if (todoDetailRepository.findByCategory_CategoryIdAndTodoNo(pathTodoNo.getCategoryId(), pathTodoNo.getTodoNo()).isEmpty()) {
            return Response.builder().data(new TodoDetailDto()).build();
        } else {
            TodoDetail result = todoDetailRepository.getTodoDetailByCategory_CategoryIdAndTodoNo(pathTodoNo.getCategoryId(), pathTodoNo.getTodoNo());
            return Response.builder().data(new TodoDetailDto(result)).build();
        }
    }

    @Override
    public void insertTodo(TodoSaveDto todoSaveDto) throws Exception {
        todoDetailRepository.save(todoSaveDto.toEntity());
    }

    @Override
    public void updateTodo(TodoUpdateDto todoUpdateDto) throws Exception {
        todoDetailRepository.save(todoUpdateDto.toEntity());
    }

    @Override
    public void deleteTodo(TodoPathDto.PathTodoNo pathTodoNo) throws Exception {
        Optional<TodoDetail> resultEntity = todoDetailRepository.findById(pathTodoNo.getTodoNo());
        if (!(resultEntity.isEmpty())) {
            TodoDetail resultDto = todoDetailRepository.getReferenceById(pathTodoNo.getTodoNo());
            todoDetailRepository.delete(resultDto);
        }
    }
}
