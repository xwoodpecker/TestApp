package com.javainuse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {
/**
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageManager messageManager;
    roleService

    public WebSocketMessageController(SimpMessagingTemplate simpMessagingTemplate, MessageManager messageManager) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageManager = messageManager;
    }
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;**/

    /**public WebSocketMessageController(SimpMessagingTemplate simpMessagingTemplate, MessageManager messageManager, UserRepository userRepository, BoardRepository boardRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageManager = messageManager;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @PreAuthorize("#username == authentication.principal.username")
    @PreAuthorize("roleService.verify(webSocketMessage.boardId, webSocketMessage.username) and xyz")
    @MessageMapping("user/sendMessage")
    public void sendMessage(@Payload WebSocketMessage webSocketMessage) {
        /**User user = userRepository.findFirstByUserName(webSocketMessage.getSender());
        webSocketMessage.setUser(user);
        Optional<Board> board = boardRepository.findById(Long.valueOf(webSocketMessage.getBoardId()));
        webSocketMessage.setBoard(board.get());
        webSocketMessage = messageManager.add(webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);
    }
    @PreAuthorize("#username == authentication.principal.username")
    @Secured("hasRole('COORDINATOR')")
    @MessageMapping("coordinator/pushMessageToCentral")
    public void pushMessageToCentral(@Payload WebSocketMessage webSocketMessage) {
        webSocketMessage.setBoardId(CONFIG.CENTRAL_BOARD_ID);
        webSocketMessage = messageManager.add(webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + CONFIG.CENTRAL_BOARD_ID,
                webSocketMessage);
    }

    @PreAuthorize("#username == authentication.principal.username")
    @MessageMapping("user/editMessage")
    public void editMessage(@Payload WebSocketMessage webSocketMessage) {
        messageManager.replace(webSocketMessage.getId(), webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);
    }

    @PreAuthorize("#username == authentication.principal.username")
    @MessageMapping("coordinator/deleteMessage")
    public void deleteMessage(@Payload WebSocketMessage webSocketMessage) {
        messageManager.remove(webSocketMessage.getId());
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);


    }
    **/



}