package com.myliket.myliket3.web;

import com.myliket.myliket3.service.todo.TodoService;
import com.myliket.myliket3.web.dto.Response;
import com.myliket.myliket3.web.dto.TodoDto;
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
    public ResponseEntity<Response> getCategoryTodoList(@PathVariable("categoryId") @NotBlank UUID categoryId) throws Exception {
        TodoDto.PathCategoryId pathCategoryId = TodoDto.PathCategoryId.builder().categoryId(categoryId).build();
        Response response = todoService.getCategoryTodoList(pathCategoryId);
        return ResponseEntity.ok().body(response);
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
        TodoDto.PathTodoNo pathTodoNo = TodoDto.PathTodoNo.builder().categoryId(categoryId).todoNo(todoNo).build();

        return new ResponseEntity<>(todoService.getTodoDetail(pathTodoNo), HttpStatus.OK);
    }

    /**
     * 할일 등록 API
     *
     * @param requestInsert(Object) 등록할 할일정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PostMapping(value = "/{categoryId}/todos")
    public ResponseEntity<Void> insertTodo(@PathVariable("categoryId") @NotBlank UUID categoryId
            , @RequestBody @Validated TodoDto.RequestInsert requestInsert) throws Exception {
        todoService.insertTodo(requestInsert);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 할일 수정 API
     *
     * @param requestUpdate(Object) 수정할 할일 정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PutMapping(value = "/{categoryId}/todos/{todoNo}")
    public ResponseEntity<Object> updateTodo(@PathVariable("categoryId") @NotBlank UUID categoryId, @PathVariable("todoNo") @NotBlank Long todoNo,
                                             @RequestBody @Validated TodoDto.RequestUpdate requestUpdate) throws Exception {

        todoService.updateTodo(requestUpdate);

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

        TodoDto.PathTodoNo todoDto = TodoDto.PathTodoNo.builder().categoryId(categoryId).todoNo(todoNo).build();

        todoService.deleteTodo(todoDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
