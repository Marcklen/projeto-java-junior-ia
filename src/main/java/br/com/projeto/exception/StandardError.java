package br.com.projeto.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError {

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime timeStamp;
	private Integer status;
	private String error;
	private String path;
}
