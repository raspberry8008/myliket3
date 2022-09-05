package com.myliket.myliket3.web;

import com.myliket.myliket3.domain.dto.request.todo.TodoPathDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoSaveDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoUpdateDto;
import com.myliket.myliket3.domain.dto.response.common.Response;
import com.myliket.myliket3.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categorys")
public class TodoApiController {

    private final TodoService todoService;

    /**
     * 전체 카테고리의 할일을 조회 API
     *
     * @return ResponseEntity<Response> 200 OK, 전체 할일목록
     */
    @GetMapping(value = "/todos")
    public ResponseEntity<Response> allTodoList() throws Exception {
        return ResponseEntity.ok().body(todoService.allTodoList());
    }

    /**
     * 카테고리 {categoryId} 에 대한 전체 할일 조회 API
     *
     * @return ResponseEntity<Response> 200 OK, 카테고리 {categoryId} 의 할일목록
     */
    @GetMapping(value = "/{categoryId}/todos")
    public ResponseEntity<?> getCategoryTodoList(@PathVariable("categoryId") @NotBlank UUID categoryId) throws Exception {
        TodoPathDto.PathCategoryId pathCategoryId = TodoPathDto.PathCategoryId.builder().categoryId(categoryId).build();
//        TodoDetail todoDetail = TodoDetail.builder().category(pathCategoryId.toEntity()).build();
        return ResponseEntity.ok().body(todoService.getCategoryTodoList(pathCategoryId));
    }

    /**
     * 할일 상세조회 API
     *
     * @param categoryId(카테고리아이디), todoNo(할일 고유번호)
     * @return ResponseEntity<Response> 200 OK, 할일 상세정보
     */

    @GetMapping(value = "/{categoryId}/todos/{todoNo}")
    public ResponseEntity<?> getTodoDetail(@PathVariable("categoryId") @NotBlank UUID categoryId
            , @PathVariable("todoNo") @NotBlank Long todoNo) throws Exception {
        TodoPathDto.PathTodoNo pathTodoNo = TodoPathDto.PathTodoNo.builder().categoryId(categoryId).todoNo(todoNo).build();
        Response response = todoService.getTodoDetail(pathTodoNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 할일 등록 API
     *
     * @param todoSaveDto(Object) 등록할 할일정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PostMapping(value = "/{categoryId}/todos")
    public ResponseEntity<Void> insertTodo(@PathVariable("categoryId") @NotBlank UUID categoryId
            , @RequestBody @Validated TodoSaveDto todoSaveDto) throws Exception {
        todoService.insertTodo(todoSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 할일 수정 API
     *
     * @param todoUpdateDto(Object) 수정할 할일 정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PutMapping(value = "/{categoryId}/todos/{todoNo}")
    public ResponseEntity<Object> updateTodo(@PathVariable("categoryId") @NotBlank UUID categoryId, @PathVariable("todoNo") @NotBlank Long todoNo,
                                             @RequestBody @Validated TodoUpdateDto todoUpdateDto) throws Exception {
        todoService.updateTodo(todoUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 할일 삭제 API
     *
     * @param categoryId, todoNo 삭제할 할일의 카테고리아이디와 할일 고유번호
     * @return ResponseEntity<Object> 201 CREATED : 이동할 페이지 없음
     */
    @DeleteMapping(value = "/{categoryId}/todos/{todoNo}")
    public ResponseEntity<Object> deleteTodo(@PathVariable("categoryId") @NotBlank UUID categoryId
            , @PathVariable("todoNo") @NotBlank Long todoNo) throws Exception {

        TodoPathDto.PathTodoNo todoDto = TodoPathDto.PathTodoNo.builder().categoryId(categoryId).todoNo(todoNo).build();
        todoService.deleteTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
