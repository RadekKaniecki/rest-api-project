package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Trello List Dto test", true));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "Trello Board Dto test", trelloListDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(trelloBoardDtos.get(0).getId(), trelloBoards.get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getName(), trelloBoards.get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).getId(), trelloBoards.get(0).getLists().get(0).getId());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).getName(), trelloBoards.get(0).getLists().get(0).getName());
        assertEquals(trelloBoardDtos.get(0).getLists().get(0).isClosed(), trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "Trello List test", true));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Trello Board test", trelloList));

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(trelloBoards.get(0).getId(), trelloBoardDtos.get(0).getId());
        assertEquals(trelloBoards.get(0).getName(), trelloBoardDtos.get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(), trelloBoardDtos.get(0).getLists().get(0).getId());
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), trelloBoardDtos.get(0).getLists().get(0).getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).isClosed(), trelloBoardDtos.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "Trello List Dto test", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(trelloListDto.get(0).getId(), trelloLists.get(0).getId());
        assertEquals(trelloListDto.get(0).getName(), trelloLists.get(0).getName());
        assertEquals(trelloListDto.get(0).isClosed(), trelloLists.get(0).isClosed());
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "Trello List test", true));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(trelloList.get(0).getId(), trelloListDtos.get(0).getId());
        assertEquals(trelloList.get(0).getName(), trelloListDtos.get(0).getName());
        assertEquals(trelloList.get(0).isClosed(), trelloListDtos.get(0).isClosed());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test card", "Test card description", "Test pos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test card", "Test card description", "Test pos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }
}
