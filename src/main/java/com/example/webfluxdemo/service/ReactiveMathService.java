package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dto.MultiplyRequestDto;
import com.example.webfluxdemo.dto.Response;
import com.example.webfluxdemo.util.SleepUntil;
import org.springframework.http.codec.json.AbstractJackson2Encoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(()->input*input)
                .map(v -> new Response(v));
    }

    public Flux<Response> multiplicationTable(int input){
//        List<Response> list = IntStream.rangeClosed(1,10)
//                .peek(i -> SleepUntil.sleepSeconds(1))
//                .mapToObj(i -> new Response(i*input))
//                .collect(Collectors.toList());
//        return Flux.fromIterable(list);

        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
//             .doOnNext(i -> SleepUntil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math-service processing ::: " +i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> requestDtoMono) {

        return requestDtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }
}
