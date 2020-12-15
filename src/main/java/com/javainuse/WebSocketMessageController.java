package com.javainuse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageManager messageManager;

    public WebSocketMessageController(SimpMessagingTemplate simpMessagingTemplate, MessageManager messageManager) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageManager = messageManager;
    }
    /**private final UserRepository userRepository;
    private final BoardRepository boardRepository;**/

    /**public WebSocketMessageController(SimpMessagingTemplate simpMessagingTemplate, MessageManager messageManager, UserRepository userRepository, BoardRepository boardRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageManager = messageManager;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }**/

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload WebSocketMessage webSocketMessage) {
        /**User user = userRepository.findFirstByUserName(webSocketMessage.getSender());
        webSocketMessage.setUser(user);
        Optional<Board> board = boardRepository.findById(Long.valueOf(webSocketMessage.getBoardId()));
        webSocketMessage.setBoard(board.get());**/
        webSocketMessage = messageManager.add(webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);
    }

    @MessageMapping("/pushMessageToCentral")
    public void pushMessageToCentral(@Payload WebSocketMessage webSocketMessage) {
        webSocketMessage.setBoardId(CONFIG.CENTRAL_BOARD_ID);
        webSocketMessage = messageManager.add(webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + CONFIG.CENTRAL_BOARD_ID,
                webSocketMessage);
    }

    @MessageMapping("/editMessage")
    public void editMessage(@Payload WebSocketMessage webSocketMessage) {
        messageManager.replace(webSocketMessage.getId(), webSocketMessage);
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);
    }

    @MessageMapping("/deleteMessage")
    public void deleteMessage(@Payload WebSocketMessage webSocketMessage) {
        messageManager.remove(webSocketMessage.getId());
        simpMessagingTemplate.convertAndSend(
                CONFIG.BASIC_TOPIC + webSocketMessage.getBoardId(),
                webSocketMessage);
    }

}