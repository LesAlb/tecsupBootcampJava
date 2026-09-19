package com.examen.pedidos.exception;

import com.examen.pedidos.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<BaseResponse> handlePedidoNotFound(PedidoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                BaseResponse.builder()
                        .codigo(HttpStatus.NOT_FOUND.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build()
        );
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<BaseResponse> handleStockInsuficiente(StockInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BaseResponse.builder()
                        .codigo(HttpStatus.BAD_REQUEST.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                BaseResponse.builder()
                        .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build()
        );
    }
}
