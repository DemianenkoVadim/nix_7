package ua.com.alevel.servicetests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ua.com.alevel.customutil.DynamicArray;
import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.generationutil.FootballPlayerGenerationUtil;
import ua.com.alevel.service.FootballPlayerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FootballPlayerServiceTest {

    private final static FootballPlayerService playerService = new FootballPlayerService();
    private final static int FOOTBALL_PLAYERS = 10;
    private final static int ARRAY_FIRST_INDEX = 0;
    private final static int ARRAY_FIVES_INDEX = 5;
    private final static int THREE_FOOTBALL_PLAYERS = 3;
    private final static int THIRD_FOOTBALL_PLAYER = 3;
    private final static int CHOOSING_BY_ID_FOOTBALL_PLAYER = 0;
    private final static int ONE_FOOTBALL_PLAYER = 1;
    private static final DynamicArray<FootballPlayer> footballPlayerDynamicArray = new DynamicArray<>();
    private static final DynamicArray<FootballPlayer> newListOfFootballPlayers = new DynamicArray<>();

    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        for (int footballPlayer = ARRAY_FIRST_INDEX; footballPlayer < FOOTBALL_PLAYERS; footballPlayer++) {
            FootballPlayer player = FootballPlayerGenerationUtil.generateFootballPlayer(FootballPlayerGenerationUtil.FIRST_NAME + footballPlayer,
                    FootballPlayerGenerationUtil.LAST_NAME + footballPlayer, FootballPlayerGenerationUtil.FOOTBALL_PLAYER_POSITION + footballPlayer,
                    FootballPlayerGenerationUtil.FOOTBALL_PLAYER_NATIONAL + footballPlayer, FootballPlayerGenerationUtil.FOOTBALL_PLAYER_AGE);
            playerService.createFootballPlayerInRegistrationBase(player);
            footballPlayerDynamicArray.addAdditionalPoint(player);
        }
        assertEquals(FOOTBALL_PLAYERS, footballPlayerDynamicArray.getSize());
    }

    @SneakyThrows
    @Test
    @Order(1)
    public void testCreateFootballPlayerInRegistrationBaseShouldBeCreatedFootballPlayerInRegistrationBase() {
        FootballPlayer footballPlayer = FootballPlayerGenerationUtil.generateFootballPlayer();
        playerService.createFootballPlayerInRegistrationBase(footballPlayer);
        playerService.findAllFootballPlayersFromRegistrationBase();
        assertEquals(FOOTBALL_PLAYERS, footballPlayerDynamicArray.getSize());
    }

    @SneakyThrows
    @Test
    @Order(2)
    public void testFindFootballByIdShouldReturnEqualsOneFootballPlayer() {
        FootballPlayer playerID = playerService.findFootballPlayerFromRegistrationBaseById(footballPlayerDynamicArray.get(THIRD_FOOTBALL_PLAYER).getFootballPlayerId());
        newListOfFootballPlayers.addAdditionalPoint(playerID);
        for (int player = ARRAY_FIRST_INDEX; player < footballPlayerDynamicArray.getSize(); player++) {
            if (newListOfFootballPlayers.get(CHOOSING_BY_ID_FOOTBALL_PLAYER).getFootballPlayerId().equals(footballPlayerDynamicArray.get(player).getFootballPlayerId())) {
                newListOfFootballPlayers.get(CHOOSING_BY_ID_FOOTBALL_PLAYER);
                return;
            }
        }
        Assertions.assertEquals(ONE_FOOTBALL_PLAYER, newListOfFootballPlayers.getSize());
    }

    @SneakyThrows
    @Test
    @Order(3)
    public void testUpdateFootballPlayerFromRegistrationBaseShouldUpdateFootballPlayerFromRegistrationBase() {
        FootballPlayer footballPlayer = FootballPlayerGenerationUtil.generateFootballPlayer();
        playerService.findFootballPlayerFromRegistrationBaseById(footballPlayerDynamicArray.get(ARRAY_FIRST_INDEX).getFootballPlayerId());
        footballPlayer.setFootballPlayerNational("Ukraine");
        footballPlayer.setFootballPlayerPosition("Forward");
        footballPlayer.setAgeOfFootballPlayer(Integer.parseInt("24"));
        footballPlayer.setLastName("Petrov");
        footballPlayer.setFirstName("Andrey");
        playerService.updateFootballPlayerInRegistrationBase(footballPlayer);
        FootballPlayer updatePlayer = new FootballPlayer();
        newListOfFootballPlayers.addAdditionalPoint(updatePlayer);
        Assertions.assertNotEquals(newListOfFootballPlayers.get(ARRAY_FIRST_INDEX).getFootballPlayerId(), footballPlayerDynamicArray.get(ARRAY_FIRST_INDEX).getFootballPlayerId());
    }

    @SneakyThrows
    @Test
    @Order(4)
    public void testDeleteFootballPlayerFromRegistrationBaseShouldDeleteFootballPlayerFromRegistrationBase() {
        for (int player = ARRAY_FIRST_INDEX; player < ARRAY_FIVES_INDEX; player++) {
            playerService.deleteFootballPlayerFromRegistrationBase(footballPlayerDynamicArray.get(player).getFootballPlayerId());
        }
        FootballPlayer footballPlayer = new FootballPlayer();
        for (int player = ARRAY_FIRST_INDEX; player < ARRAY_FIVES_INDEX; player++) {
            newListOfFootballPlayers.addAdditionalPoint(footballPlayer);
        }
        playerService.findAllFootballPlayersFromRegistrationBase();
        Assertions.assertEquals(FOOTBALL_PLAYERS - THREE_FOOTBALL_PLAYERS, newListOfFootballPlayers.getSize());
    }

    @SneakyThrows
    @Test
    @Order(5)
    public void testFindAllFootballPlayersFromRegistrationBaseShouldFindAllFootballPlayersFromRegistrationBase() {
        playerService.findAllFootballPlayersFromRegistrationBase();
        Assertions.assertEquals(FOOTBALL_PLAYERS, footballPlayerDynamicArray.getSize());
    }
}
