package com.example.springboot.back.board.web;



import com.example.springboot.back.board.entity.Board;
import com.example.springboot.back.board.entity.BoardRepository;
// import com.example.springboot.backend.board.mapper.BoardMapper;
import com.example.springboot.back.board.web.dtos.BoardDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;
    
    @Autowired
    // private final BoardMapper mapper;

    /**
     * 게시글 목록 가져오기
     */
    public List<BoardDto> getBoardList() {
        List<Board> boardEntities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for (Board entity : boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .idx(entity.getIdx())
                    .author(entity.getAuthor())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .createdAt(entity.getCreatedAt())
                    // .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }

    /**
     * 게시글 가져오기
     */
    public BoardDto getBoard(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    /**
     * 게시글 등록
     */
    public Board create(BoardDto boardDto) {
        // Long cnt = (mapper.countIdx(boardDto))+1;
        Board entity = Board.builder()
                // .idx(cnt)
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(entity);
    }

    /**
     * 게시글 수정
     */
    public Board update(BoardDto boardDto) {
        Board entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}

