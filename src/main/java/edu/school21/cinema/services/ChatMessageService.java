package edu.school21.cinema.services;

import edu.school21.cinema.controllers.MessageStomp;
import edu.school21.cinema.models.ChatMessage;
import edu.school21.cinema.models.Client;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.ChatMessageRepository;
import edu.school21.cinema.repositories.ClientRepository;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    private final ClientRepository clientRepository;

    private final FilmRepository filmRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ClientRepository clientRepository, FilmRepository filmRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.clientRepository = clientRepository;
        this.filmRepository = filmRepository;
    }

    public List<ChatMessage> findMessagesByFilmId(Integer id) {
        List<ChatMessage> byFilmId = chatMessageRepository.findByFilmId(id);
        byFilmId.sort(Comparator.comparing(ChatMessage::getCreated));
        return byFilmId;
    }

    public ChatMessage saveMessage(Integer filmId, MessageStomp payload, UUID userId) {
        Film film = filmRepository.findById(filmId);
        if (film == null) {
            throw new IllegalArgumentException("No film with id: " + filmId);
        }
        Client client = clientRepository.findById(userId);
        if (client == null) {
            throw new IllegalArgumentException("No client with id: " + userId);
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setCreated(LocalDateTime.now());
        chatMessage.setMessage(payload.getMessage());
        chatMessage.setAuthor(client);
        chatMessage.setFilm(film);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}
