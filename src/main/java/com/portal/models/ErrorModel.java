package com.portal.models;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
	private HttpStatus status;

	private String errorMessage;

	private LocalDateTime timeStamp;

	private String requestId;

}
