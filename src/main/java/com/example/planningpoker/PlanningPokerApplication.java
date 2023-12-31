package com.example.planningpoker;

import com.example.planningpoker.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootApplication
public class PlanningPokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanningPokerApplication.class, args);
	}

}

@Controller
@RequiredArgsConstructor
@Slf4j
class PlanningPokerController {
	final RoomRepository roomRepository;

	@GetMapping("/server/{roomId}")
	public Mono<String> joiningForm(@PathVariable String roomId) {
//		return roomRepository
//				.roomById(UUID.fromString(roomId))
//				.map(room -> "room")
//				.onErrorReturn("error");

		return Mono.fromCallable(() -> UUID.fromString(roomId))
				.flatMap(uuid -> roomRepository.roomById(uuid).map(room -> "room"))
				.onErrorReturn("error");
	}

}

