package com.example.trendyolgobaseapi.core.response;

public class ApiResponse<T> {
    private boolean success;
    private MessageType message;
    private T data; // data null olabilir

    public ApiResponse(boolean success, MessageType message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // getter ve setter
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public MessageType getMessage() { return message; }
    public void setMessage(MessageType message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}