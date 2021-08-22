package ua.com.alevel.servicetests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ua.com.alevel.customutil.DynamicArray;
import ua.com.alevel.entity.FootballTeam;
import ua.com.alevel.generationutil.FootballTeamGenerationUtil;
import ua.com.alevel.service.FootballTeamService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FootballTeamServiceTest {

    private final static FootballTeamService teamService = new FootballTeamService();
    private final static int FOOTBALL_TEAMS = 10;
    private final static int ARRAY_FIRST_INDEX = 0;
    private final static int ARRAY_FIVES_INDEX = 5;
    private final static int THREE_FOOTBALL_TEAMS = 3;
    private final static int SECOND_TEAM = 1;
    private final static int CHOOSING_BY_ID_FOOTBALL_TEAM = 0;
    private final static int ONE_FOOTBALL_TEAM = 1;
    private static final DynamicArray<FootballTeam> footballTeamDynamicArray = new DynamicArray<>();
    private static final DynamicArray<FootballTeam> newListOfFootballTeams = new DynamicArray<>();

    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        for (int footballTeam = ARRAY_FIRST_INDEX; footballTeam < FOOTBALL_TEAMS; footballTeam++) {
            FootballTeam team = FootballTeamGenerationUtil.generateFootballTeam(FootballTeamGenerationUtil.NAME_OF_THE_FOOTBALL_TEAM + footballTeam,
                    FootballTeamGenerationUtil.LOCATION_COUNTRY_FOOTBALL_TEAM + footballTeam, FootballTeamGenerationUtil.LOCATION_TOWN_FOOTBALL_TEAM + footballTeam,
                    FootballTeamGenerationUtil.YEAR_OF_FOOTBALL_TEAM_WAS_FOUNDED);
            teamService.createFootballTeamInRegistrationBase(team);
            footballTeamDynamicArray.addAdditionalPoint(team);
        }
        Assertions.assertEquals(FOOTBALL_TEAMS, footballTeamDynamicArray.getSize());
    }

    @SneakyThrows
    @Test
    @Order(1)
    public void testCreateFootballTeamInRegistrationBaseShouldBeCreatedFootballTeamInRegistrationBase() {
        FootballTeam footballTeam = FootballTeamGenerationUtil.generateFootballTeam();
        teamService.createFootballTeamInRegistrationBase(footballTeam);
        teamService.findAllFootballTeamsFromRegistrationBase();
        Assertions.assertEquals(FOOTBALL_TEAMS, footballTeamDynamicArray.getSize());
    }

    @SneakyThrows
    @Test
    @Order(2)
    public void testFindFootballByIdShouldReturnEqualsOneFootballTeam() {
        FootballTeam teamID = teamService.findFootballTeamFromRegistrationListById(footballTeamDynamicArray.get(SECOND_TEAM).getFootballTeamId());
        newListOfFootballTeams.addAdditionalPoint(teamID);
        for (int team = ARRAY_FIRST_INDEX; team < footballTeamDynamicArray.getSize(); team++) {
            if (newListOfFootballTeams.get(CHOOSING_BY_ID_FOOTBALL_TEAM).getFootballTeamId().equals(footballTeamDynamicArray.get(team).getFootballTeamId())) {
                newListOfFootballTeams.get(CHOOSING_BY_ID_FOOTBALL_TEAM);
                return;
            }
        }
        Assertions.assertEquals(ONE_FOOTBALL_TEAM, newListOfFootballTeams.getSize());
    }

    @SneakyThrows
    @Test
    @Order(3)
    public void testUpdateFootballTeamFromRegistrationBaseShouldUpdateFootballTeamFromRegistrationBase() {
        FootballTeam footballTeam = FootballTeamGenerationUtil.generateFootballTeam();
        teamService.findFootballTeamFromRegistrationListById(footballTeamDynamicArray.get(ARRAY_FIRST_INDEX).getFootballTeamId());
        footballTeam.setNameOfTheFootballTeam("NEW TEST");
        footballTeam.setLocationCountryFootballTeam("NEW TEST");
        footballTeam.setYearOfFootballTeamWasFounded(Integer.parseInt("1905"));
        footballTeam.setLocationTownFootballTeam("NEW TEST");
        teamService.updateFootballTeamInRegistrationBase(footballTeam);
        FootballTeam updateTeam = new FootballTeam();
        newListOfFootballTeams.addAdditionalPoint(updateTeam);
        Assertions.assertNotEquals(newListOfFootballTeams.get(ARRAY_FIRST_INDEX).getFootballTeamId(), footballTeamDynamicArray.get(ARRAY_FIRST_INDEX).getFootballTeamId());
    }

    @SneakyThrows
    @Test
    @Order(4)
    public void testDeleteFootballTeamFromRegistrationBaseShouldDeleteFootballTeamFromRegistrationBase() {
        for (int player = ARRAY_FIRST_INDEX; player < ARRAY_FIVES_INDEX; player++) {
            teamService.deleteFootballTeamFromRegistrationBase(footballTeamDynamicArray.get(player).getFootballTeamId());
        }
        FootballTeam footballTeam = new FootballTeam();
        for (int player = ARRAY_FIRST_INDEX; player < ARRAY_FIVES_INDEX; player++) {
            newListOfFootballTeams.addAdditionalPoint(footballTeam);
        }
        teamService.findAllFootballTeamsFromRegistrationBase();
        Assertions.assertEquals(FOOTBALL_TEAMS - THREE_FOOTBALL_TEAMS, newListOfFootballTeams.getSize());
    }

    @SneakyThrows
    @Test
    @Order(5)
    public void testFindAllFootballTeamsFromRegistrationBaseShouldFindAllFootballTeamsFromRegistrationBase() {
        teamService.findAllFootballTeamsFromRegistrationBase();
        Assertions.assertEquals(FOOTBALL_TEAMS, footballTeamDynamicArray.getSize());
    }
}
